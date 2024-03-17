package com.szchoiceway.customerui.utils;

public class StringUtil {
    public static String toString(String... strArr) {
        StringBuilder sb = new StringBuilder();
        for (String append : strArr) {
            sb.append(append);
        }
        return sb.toString();
    }

    public static boolean isNullOrEmpty(String str) {
        return str == null || str.length() == 0;
    }

    public static String safeToString(Object obj) {
        return obj == null ? "" : obj.toString();
    }
}
