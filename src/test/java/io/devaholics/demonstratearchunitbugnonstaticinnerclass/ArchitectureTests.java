package io.devaholics.demonstratearchunitbugnonstaticinnerclass;

import com.tngtech.archunit.core.domain.JavaConstructor;
import com.tngtech.archunit.core.domain.JavaParameter;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchCondition;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.lang.ConditionEvents;
import com.tngtech.archunit.lang.SimpleConditionEvent;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.constructors;

@AnalyzeClasses
public class ArchitectureTests {

    @ArchTest
    static final ArchRule constructorsHaveNullableParametersAtTheEnd = constructors()
            .that().areNotDeclaredIn(ConstructorExpectedFail.class)
            .should(haveNullableParametersAtTheEnd());

    private static ArchCondition<? super JavaConstructor> haveNullableParametersAtTheEnd() {
        return new ArchCondition<>("have nullable parameters at the end of the parameter list") {
            @Override
            public void check(JavaConstructor item, ConditionEvents events) {
                var parameters = item.getParameters();

                var firstNullableParameter = parameters.stream()
                        .filter(this::isAnnotatedNullable)
                        .findFirst();
                if (firstNullableParameter.isEmpty()) {
                    return;
                }

                var firstNullableIndex = parameters.indexOf(firstNullableParameter.get());
                for (int i = firstNullableIndex + 1; i < parameters.size(); i++) {
                    var currentParameter = parameters.get(i);
                    if (!isAnnotatedNullable(currentParameter)) {
                        String message = String.format("Parameter number %d is not nullable and should be ordered before position %d of first nullable %s",
                                currentParameter.getIndex() + 1,
                                firstNullableIndex + 1,
                                firstNullableParameter.get().getDescription()
                        );
                        events.add(SimpleConditionEvent.violated(item, message));
                    }
                }
            }

            private boolean isAnnotatedNullable(JavaParameter javaParameter) {
                return javaParameter.isAnnotatedWith(javax.annotation.Nullable.class);
            }
        };

    }

}

