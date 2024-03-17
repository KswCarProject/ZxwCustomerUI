package com.szchoiceway.customerui.kt_like;

public class JavaPair<F, S> {
    public F first;
    public S second;

    public JavaPair(F f, S s) {
        this.first = f;
        this.second = s;
    }

    public int hashCode() {
        return DataClassHelper.hashCode(this);
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof JavaPair)) {
            return false;
        }
        JavaPair javaPair = (JavaPair) obj;
        if (!EqualsUtil.equals((Object) this.first, (Object) javaPair.first) || !EqualsUtil.equals((Object) this.second, (Object) javaPair.second)) {
            return false;
        }
        return true;
    }

    public String toString() {
        return DataClassHelper.toString(this);
    }
}
