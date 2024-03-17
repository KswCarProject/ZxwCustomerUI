package com.szchoiceway.customerui.kt_like;

public interface StringHelper {
    boolean isNullOrEmpty(String str) {
        return StringUtil.isNullOrEmpty(str);
    }

    boolean isNotNullAndNotEmpty(String str) {
        return !StringUtil.isNullOrEmpty(str);
    }

    String getNonNullString(String str, String str2) {
        return StringUtil.getNonNullValue(str, str2);
    }

    String toString(Object obj) {
        return DataClassHelper.toString(obj);
    }

    String toString(Object obj, Class cls) {
        return DataClassHelper.toString(obj, cls);
    }

    Integer toInt(String str) {
        return Integer.valueOf(Integer.parseInt(str));
    }

    Integer safeToInt(String str, int i) {
        try {
            return toInt(str);
        } catch (Exception unused) {
            return Integer.valueOf(i);
        }
    }
}
