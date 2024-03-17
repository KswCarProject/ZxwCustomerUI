package com.szchoiceway.customerui.kt_like;

import java.util.List;

public interface ListHelper {
    <T> List<T> listOf(T... tArr) {
        return ListUtil.listOf(tArr);
    }

    <T> void forEach(List<T> list, Function1Void<T> function1Void) {
        ListUtil.forEach(list, function1Void);
    }

    <T> void forEach(T[] tArr, Function1Void<T> function1Void) {
        ArraysUtil.forEach(tArr, function1Void);
    }

    Integer sum(List<Integer> list) {
        return ListUtil.sum(list);
    }

    <T> void forEachIndexed(List<T> list, Function2Void<T, Integer> function2Void) {
        ListUtil.forEachIndexed(list, function2Void);
    }

    <T> void forEachIndexed(T[] tArr, Function2Void<T, Integer> function2Void) {
        ArraysUtil.forEachIndexed(tArr, function2Void);
    }

    <T> T getOrNull(List<T> list, int i) {
        return ListUtil.getOrNull(list, i);
    }

    <T> T getOrNull(T[] tArr, int i) {
        return ArraysUtil.getOrNull(tArr, i);
    }

    <T> T first(List<T> list) {
        return ListUtil.first(list);
    }

    <T> T last(List<T> list) {
        return ListUtil.last(list);
    }
}
