package com.szchoiceway.customerui.kt_like;

public interface ExtensionsHelper extends OptionHelper, JavaStandardHelper, StringHelper, ListHelper, ObjectHelper, EqualsHelper, MapHelper, ViewHelper, MathHelper {
    boolean isFalse(boolean z) {
        return !z;
    }

    boolean not(boolean z) {
        return !z;
    }

    float toFloat(int i) {
        return ((float) i) * 1.0f;
    }

    float toFloat(long j) {
        return ((float) j) * 1.0f;
    }
}
