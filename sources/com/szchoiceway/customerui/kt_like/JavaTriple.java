package com.szchoiceway.customerui.kt_like;

public class JavaTriple<F, S, T> {
    public F first;
    public S second;
    public T third;

    public JavaTriple(F f, S s, T t) {
        this.first = f;
        this.second = s;
        this.third = t;
    }

    public int hashCode() {
        return DataClassHelper.hashCode(this);
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof JavaTriple)) {
            return false;
        }
        return DataClassHelper.equals(this, obj);
    }

    public String toString() {
        return DataClassHelper.toString(this);
    }
}
