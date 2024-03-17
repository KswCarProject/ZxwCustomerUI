package com.szchoiceway.customerui.kt_like;

import kotlin.jvm.functions.Function1;

public interface JavaStandardHelper {
    <T> T also(T t, Function1Void<T> function1Void) {
        return JavaStandard.also(t, function1Void);
    }

    <T> void alsoVoid(T t, Function1Void<T> function1Void) {
        JavaStandard.also(t, function1Void);
    }

    <T, R> R let(T t, Function1<T, R> function1) {
        return JavaStandard.let(t, function1);
    }

    <T> T as(Object obj) {
        return JavaStandard.as(obj);
    }

    <T> void runIfNonNull(T t, Function1Void<T> function1Void) {
        JavaStandard.runIfNonNull(t, function1Void);
    }

    int safeToNumber(String str, int i) {
        return JavaStandard.safeToNumber(str, i);
    }

    void repeat(int i, Function1Void<Integer> function1Void) {
        JavaStandard.repeat(i, function1Void);
    }

    String buildString(Function1Void<StringBuilder> function1Void) {
        return JavaStandard.buildString(function1Void);
    }
}
