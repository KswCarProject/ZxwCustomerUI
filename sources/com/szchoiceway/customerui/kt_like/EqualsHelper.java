package com.szchoiceway.customerui.kt_like;

import java.util.List;
import kotlin.jvm.functions.Function2;

public interface EqualsHelper {
    boolean equals(String str, String str2) {
        return EqualsUtil.equals((Object) str, (Object) str2);
    }

    <T> boolean equals(List<T> list, List<T> list2) {
        return EqualsUtil.equals(list, list2);
    }

    <T> boolean equals(List<T> list, List<T> list2, Function2<T, T, Boolean> function2) {
        return EqualsUtil.equals(list, list2, function2);
    }
}
