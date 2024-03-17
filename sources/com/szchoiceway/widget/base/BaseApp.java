package com.szchoiceway.widget.base;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import com.szchoiceway.customerui.utils.MultipleUtils;
import com.szchoiceway.widget.service.MService;

public class BaseApp extends Application {
    public static final String TAG = "BaseApp";
    public static Context mContext;

    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "onCreate: ");
        MultipleUtils.setCustomDensity((Activity) null, getResources(), 0);
        mContext = this;
        startService(new Intent(this, MService.class));
    }

    public static Context getContext() {
        return mContext;
    }
}
