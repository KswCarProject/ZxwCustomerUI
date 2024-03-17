package com.szchoiceway.customerui.kt_like;

import java.util.List;
import kotlin.jvm.functions.Function2;

public interface EqualsUtil {
    static boolean equals(Object obj, Object obj2) {
        return obj != null && obj.equals(obj2);
    }

    static <T> boolean equals(List<T> list, List<T> list2) {
        return equals(list, list2, $$Lambda$EqualsUtil$U9YjeYBxD4rmTYdEjvyMLDj1glY.INSTANCE);
    }

    static /* synthetic */ Boolean lambda$equals$0(Object obj, Object obj2) {
        return Boolean.valueOf(obj == obj2);
    }

    static <T> boolean equals(List<T> list, List<T> list2, Function2<T, T, Boolean> function2) {
        if (list == null || list2 == null) {
            return false;
        }
        if (list == list2) {
            return true;
        }
        if (list.isEmpty() || list.size() != list2.size()) {
            return false;
        }
        for (int i = 0; i < list.size(); i++) {
            if (!function2.invoke(list.get(i), list2.get(i)).booleanValue()) {
                return false;
            }
        }
        return true;
    }
}
