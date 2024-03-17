package com.txznet.weatherquery;

import android.util.Log;
import com.google.gson.Gson;
import java.lang.reflect.Type;

public class GsonUtil {
    private static final String TAG = "GsonUtil";

    public static String toJson(Object obj) {
        return new Gson().toJson(obj);
    }

    public static <T> T fromJson(byte[] bArr, Class<T> cls) {
        if (bArr == null) {
            return null;
        }
        try {
            return fromJson(new String(bArr), cls);
        } catch (Exception e) {
            Log.e(TAG, "json parse error", e);
            return null;
        }
    }

    public static <T> T fromJson(String str, Class<T> cls) {
        return new Gson().fromJson(str, cls);
    }

    public static <T> T fromJson(byte[] bArr, Type type) {
        if (bArr == null) {
            return null;
        }
        return fromJson(new String(bArr), type);
    }

    public static <T> T fromJson(String str, Type type) {
        return new Gson().fromJson(str, type);
    }
}
