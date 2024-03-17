package com.szchoiceway.customerui.kt_like;

import java.util.Map;
import kotlin.Pair;

public interface MapHelper {
    <F, S> Pair<F, S> to(F f, S s) {
        return new Pair<>(f, s);
    }

    static <K, V> Map<K, V> mapOf(Pair<K, V>... pairArr) {
        return MapUtil.mapOf(pairArr);
    }
}
