package com.szchoiceway.widget;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import com.szchoiceway.widget.base.BaseWidget;

public class PlayerWidget extends BaseWidget {
    public static final String TAG = "PlayerWidget";

    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] iArr) {
        super.onUpdate(context, appWidgetManager, iArr);
        Log.i(TAG, "onUpdate: ");
    }

    /* access modifiers changed from: protected */
    public void onReceive(Intent intent) {
        super.onReceive(intent);
        Log.i(TAG, "onReceive: ");
    }

    public void onDeleted(Context context, int[] iArr) {
        super.onDeleted(context, iArr);
    }
}
