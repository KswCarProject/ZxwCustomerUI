package com.szchoiceway.widget.base;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.util.Log;
import com.szchoiceway.widget.service.MService;

public class BaseWidget extends AppWidgetProvider {
    private static final String TAG = "BaseWidget";
    protected final MService.ReceiverListener mListener = new MService.ReceiverListener() {
        public final void onReceive(Intent intent) {
            BaseWidget.this.onReceive(intent);
        }
    };
    protected MService mService = null;

    /* access modifiers changed from: protected */
    public void onReceive(Intent intent) {
    }

    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] iArr) {
        super.onUpdate(context, appWidgetManager, iArr);
        bindService();
    }

    private void bindService() {
        BaseApp.getContext().bindService(new Intent(BaseApp.getContext(), MService.class), new ServiceConnection() {
            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                BaseWidget.this.mService = ((MService.MBinder) iBinder).getService();
                Log.i(BaseWidget.TAG, "onServiceConnected: mService = " + BaseWidget.this.mService);
                BaseWidget.this.onServiceConnected();
            }

            public void onServiceDisconnected(ComponentName componentName) {
                BaseWidget.this.mService = null;
            }
        }, 1);
    }

    /* access modifiers changed from: protected */
    public void onServiceConnected() {
        MService mService2 = this.mService;
        if (mService2 != null) {
            mService2.addListener(this.mListener);
        }
    }

    public void onDeleted(Context context, int[] iArr) {
        super.onDeleted(context, iArr);
        MService mService2 = this.mService;
        if (mService2 != null) {
            mService2.removeListener(this.mListener);
        }
    }
}
