package com.szchoiceway.customerui.kt_like;

import com.szchoiceway.customerui.kt_like.JavaStandard;
import com.szchoiceway.customerui.kt_like.ListUtil;
import java.util.List;

public interface OptionHelper {
    <T> JavaStandard.Option<T> option(T t) {
        return JavaStandard.option(t);
    }

    <T> ListUtil.Option<T> option(List<T> list) {
        return ListUtil.option(list);
    }
}
