package com.szchoiceway.customerui.livedata;

import androidx.lifecycle.LiveData;

public class DefaultValueLiveData<T> {
    public final T defaultValue;
    public LiveData<T> liveData;

    public DefaultValueLiveData(LiveData<T> liveData2, T t) {
        this.liveData = liveData2;
        this.defaultValue = t;
    }

    public T getValue() {
        return this.liveData.getValue() != null ? this.liveData.getValue() : this.defaultValue;
    }
}
