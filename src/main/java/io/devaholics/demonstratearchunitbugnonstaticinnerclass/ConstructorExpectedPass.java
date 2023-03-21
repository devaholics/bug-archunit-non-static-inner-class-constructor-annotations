package io.devaholics.demonstratearchunitbugnonstaticinnerclass;

import javax.annotation.Nullable;

public class ConstructorExpectedPass {

    private final Integer a;
    private final Boolean b;
    @Nullable
    private final String c;

    public ConstructorExpectedPass(Integer a, Boolean b, @Nullable String c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }
}
