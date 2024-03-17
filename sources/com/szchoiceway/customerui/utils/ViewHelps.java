package com.szchoiceway.customerui.utils;

public class ViewHelps {
    public static boolean isEmpty(CharSequence charSequence) {
        return charSequence == null || charSequence.length() == 0;
    }

    public static boolean contains(String str, String str2) {
        if (isEmpty(str)) {
            return false;
        }
        try {
            return str.contains(str2);
        } catch (Exception unused) {
            return false;
        }
    }
}
