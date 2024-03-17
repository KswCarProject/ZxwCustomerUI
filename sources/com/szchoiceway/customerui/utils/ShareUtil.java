package com.szchoiceway.customerui.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Base64;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ShareUtil {
    private static SharedPreferences.Editor editor;
    private static SharedPreferences sharedPreferences;

    public static void init(Context context) {
        SharedPreferences sharedPreferences2 = context.getSharedPreferences("config", 0);
        sharedPreferences = sharedPreferences2;
        editor = sharedPreferences2.edit();
    }

    public static void init(Context context, String str) {
        SharedPreferences sharedPreferences2 = context.getSharedPreferences(str, 0);
        sharedPreferences = sharedPreferences2;
        editor = sharedPreferences2.edit();
    }

    public static void clearData() {
        editor.clear().commit();
    }

    public static void clearData(Context context, String str) {
        SharedPreferences sharedPreferences2 = context.getSharedPreferences(str, 0);
        sharedPreferences = sharedPreferences2;
        sharedPreferences2.edit().clear().commit();
    }

    public static void putString(String str, String str2) {
        editor.putString(str, str2);
        editor.commit();
    }

    public static String getString(String str) {
        return sharedPreferences.getString(str, "");
    }

    public static void putInt(String str, int i) {
        editor.putInt(str, i);
        editor.commit();
    }

    public static int getInt(String str) {
        return sharedPreferences.getInt(str, -1);
    }

    public static boolean getBoolean(String str) {
        return sharedPreferences.getBoolean(str, false);
    }

    public static void putBoolean(String str, boolean z) {
        editor.putBoolean(str, z);
        editor.commit();
    }

    public static void putStringArray(String str, List<String> list) {
        editor.putInt(str, list.size());
        for (int i = 0; i < list.size(); i++) {
            editor.remove("Status_" + i);
            editor.putString("Status_" + i, list.get(i));
        }
        editor.commit();
    }

    public static List<String> getStringArray(String str) {
        ArrayList arrayList = new ArrayList();
        arrayList.clear();
        int i = sharedPreferences.getInt(str, 0);
        for (int i2 = 0; i2 < i; i2++) {
            arrayList.add(sharedPreferences.getString("Status_" + i2, (String) null));
        }
        return arrayList;
    }

    public static void putIntArray(String str, List<Integer> list) {
        editor.putInt(str, list.size());
        for (int i = 0; i < list.size(); i++) {
            editor.remove("Status_" + i);
            editor.putInt("Status_" + i, list.get(i).intValue());
        }
        editor.commit();
    }

    public static List<Integer> getIntArray(String str) {
        ArrayList arrayList = new ArrayList();
        arrayList.clear();
        int i = sharedPreferences.getInt(str, 0);
        for (int i2 = 0; i2 < i; i2++) {
            arrayList.add(Integer.valueOf(sharedPreferences.getInt("Status_" + i2, -1)));
        }
        return arrayList;
    }

    public static void putBean(String str, Object obj) {
        if (obj instanceof Serializable) {
            try {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                new ObjectOutputStream(byteArrayOutputStream).writeObject(obj);
                editor.putString(str, new String(Base64.encode(byteArrayOutputStream.toByteArray(), 0))).commit();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            throw new IllegalArgumentException("the obj must implement Serializble");
        }
    }

    public static Object getBean(String str) {
        try {
            String string = sharedPreferences.getString(str, "");
            if (string.equals("")) {
                return null;
            }
            return new ObjectInputStream(new ByteArrayInputStream(Base64.decode(string.getBytes(), 1))).readObject();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
