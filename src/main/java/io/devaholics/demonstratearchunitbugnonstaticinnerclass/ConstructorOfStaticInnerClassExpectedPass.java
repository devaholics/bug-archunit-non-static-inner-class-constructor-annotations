package io.devaholics.demonstratearchunitbugnonstaticinnerclass;

import javax.annotation.Nullable;

public class ConstructorOfStaticInnerClassExpectedPass {

    private final Integer a;

    public ConstructorOfStaticInnerClassExpectedPass(Integer a) {
        this.a = a;
    }

    private static class Inner {
        private final Boolean b;
        @Nullable
        private final String c;

        public Inner(Boolean b, @Nullable String c) {
            this.b = b;
            this.c = c;
        }
    }
}
