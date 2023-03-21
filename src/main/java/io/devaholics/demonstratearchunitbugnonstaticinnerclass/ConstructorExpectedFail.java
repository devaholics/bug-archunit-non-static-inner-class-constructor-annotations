package io.devaholics.demonstratearchunitbugnonstaticinnerclass;

import javax.annotation.Nullable;

public class ConstructorExpectedFail {

    private final Integer a;
    @Nullable
    private final Boolean b;
    private final String c;

    public ConstructorExpectedFail(Integer a, @Nullable Boolean b, String c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }
}
