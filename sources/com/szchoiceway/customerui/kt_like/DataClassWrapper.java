package com.szchoiceway.customerui.kt_like;

import com.szchoiceway.customerui.kt_like.DataClass;

public class DataClassWrapper<T extends DataClass> {
    private T instance;

    public DataClassWrapper(T t) {
        this.instance = t;
    }

    public boolean equals(Object obj) {
        if (!EqualsUtil.equals((Object) this.instance.getClass(), (Object) obj.getClass())) {
            return false;
        }
        return DataClassHelper.equals(this.instance, (DataClass) obj);
    }

    public int hashCode() {
        return DataClassHelper.hashCode(this.instance);
    }

    public String toString() {
        return DataClassHelper.toString(this.instance);
    }

    public T copy() {
        return (DataClass) DataClassHelper.copy(this.instance);
    }

    public T get() {
        return this.instance;
    }
}
