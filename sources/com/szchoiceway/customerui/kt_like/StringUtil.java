package com.szchoiceway.customerui.kt_like;

public class StringUtil {
    public static String getNonNullValue(String str, String str2) {
        return str != null ? str : str2;
    }

    public static String toString(String... strArr) {
        StringBuilder sb = new StringBuilder();
        for (String append : strArr) {
            sb.append(append);
        }
        return sb.toString();
    }

    public static boolean isNullOrEmpty(CharSequence charSequence) {
        return charSequence == null || charSequence.length() == 0;
    }

    public static String safeToString(Object obj) {
        return obj == null ? "" : obj.toString();
    }
}
