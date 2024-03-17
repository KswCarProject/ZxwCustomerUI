package com.szchoiceway.customerui.kt_like;

import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;

public interface ArraysUtil {
    static <T> T[] arrayOf(T... tArr) {
        return tArr;
    }

    static <T> T getOrNull(T[] tArr, int i) {
        if (i >= tArr.length) {
            return null;
        }
        return tArr[i];
    }

    static <T> T getOrDefault(T[] tArr, int i, T t) {
        T orNull = getOrNull(tArr, i);
        return orNull != null ? orNull : t;
    }

    static <T> T getOrElse(T[] tArr, int i, Function0<T> function0) {
        T orNull = getOrNull(tArr, i);
        if (orNull != null) {
            return orNull;
        }
        return function0.invoke();
    }

    static <T> T find(T[] tArr, Function1<T, Boolean> function1) {
        for (T t : tArr) {
            if (function1.invoke(t).booleanValue()) {
                return t;
            }
        }
        return null;
    }

    static <T> T[] take(T[] tArr, int i) {
        T[] tArr2 = new Object[i];
        for (int i2 = 0; i2 < i; i2++) {
            tArr2[i2] = tArr[i2];
        }
        return tArr2;
    }

    static <T> T[] takeLast(T[] tArr, int i) {
        T[] tArr2 = new Object[i];
        for (int length = tArr.length - i; length < tArr.length; length++) {
            tArr2[length] = tArr[length];
        }
        return tArr2;
    }

    static <T> T first(T[] tArr) {
        return tArr[0];
    }

    static <T> T firstOrNull(T[] tArr) {
        return getOrNull(tArr, 0);
    }

    static <T> T firstOrDefault(T[] tArr, T t) {
        return getOrDefault(tArr, 0, t);
    }

    static <T> T firstOrElse(T[] tArr, Function0<T> function0) {
        return getOrElse(tArr, 0, function0);
    }

    static <T> T last(T[] tArr) {
        return tArr[tArr.length - 1];
    }

    static <T> T lastOrNull(T[] tArr) {
        return getOrNull(tArr, tArr.length - 1);
    }

    static <T> T lastOrDefault(T[] tArr, T t) {
        return getOrDefault(tArr, tArr.length - 1, t);
    }

    static <T> T lastOrElse(T[] tArr, Function0<T> function0) {
        return getOrElse(tArr, tArr.length - 1, function0);
    }

    static <T> void forEach(T[] tArr, Function1Void<T> function1Void) {
        for (T invoke : tArr) {
            function1Void.invoke(invoke);
        }
    }

    static <T> void forEachIndexed(T[] tArr, Function2Void<T, Integer> function2Void) {
        JavaStandard.repeat(tArr.length, new Function1Void(tArr) {
            public final /* synthetic */ Object[] f$1;

            {
                this.f$1 = r2;
            }

            public final void invoke(Object obj) {
                Function2Void.this.invoke(this.f$1[((Integer) obj).intValue()], (Integer) obj);
            }
        });
    }
}
