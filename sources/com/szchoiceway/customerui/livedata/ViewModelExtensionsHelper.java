package com.szchoiceway.customerui.livedata;

import androidx.lifecycle.LiveData;
import com.szchoiceway.customerui.kt_like.ExtensionsHelper;

public interface ViewModelExtensionsHelper extends ExtensionsHelper {
    int visibilityToInt(boolean z) {
        return z ? 0 : 8;
    }

    int visibilityToIntNot(boolean z) {
        return z ? 8 : 0;
    }

    <T> DefaultValueLiveData<T> DefaultValueLiveData(LiveData<T> liveData, T t) {
        return new DefaultValueLiveData<>(liveData, t);
    }
}
