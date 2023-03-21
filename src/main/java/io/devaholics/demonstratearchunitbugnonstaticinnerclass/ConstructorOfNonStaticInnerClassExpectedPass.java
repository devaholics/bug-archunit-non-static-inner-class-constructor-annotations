package io.devaholics.demonstratearchunitbugnonstaticinnerclass;

import javax.annotation.Nullable;

public class ConstructorOfNonStaticInnerClassExpectedPass {

    private final Integer a;

    public ConstructorOfNonStaticInnerClassExpectedPass(Integer a) {
        this.a = a;
    }

    class Inner {
        private final Boolean b;
        @Nullable
        private final String c;

        Inner(Boolean b, @Nullable String c) {
            this.b = b;
            this.c = c;
        }
    }
}
