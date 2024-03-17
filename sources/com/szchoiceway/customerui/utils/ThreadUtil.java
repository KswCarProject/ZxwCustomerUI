package com.szchoiceway.customerui.utils;

import android.os.Handler;
import android.os.Looper;

public class ThreadUtil {
    private static final Handler mHandler = new Handler(Looper.getMainLooper());

    public static void runInThread(Runnable runnable) {
        new Thread(runnable).start();
    }

    public static void runInUiThread(Runnable runnable) {
        mHandler.post(runnable);
    }

    public static void runInUiThread(Runnable runnable, long j) {
        mHandler.postDelayed(runnable, j);
    }

    public static Handler get() {
        return mHandler;
    }
}
