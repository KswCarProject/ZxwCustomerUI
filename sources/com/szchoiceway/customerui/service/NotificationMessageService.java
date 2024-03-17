package com.szchoiceway.customerui.service;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import androidx.core.app.NotificationCompat;
import com.szchoiceway.customerui.kt_like.EqualsUtil;
import com.szchoiceway.customerui.kt_like.Function1Void;
import com.szchoiceway.customerui.kt_like.JavaStandard;
import com.szchoiceway.customerui.utils.EventUtils;
import com.szchoiceway.customerui.utils.LogHelps;

public class NotificationMessageService extends NotificationListenerService {
    private String packageName = EventUtils.ESUPER_MODE_PACKAGE_NAME;

    public void onNotificationPosted(StatusBarNotification statusBarNotification) {
        super.onNotificationPosted(statusBarNotification);
        if (EqualsUtil.equals((Object) this.packageName, (Object) statusBarNotification.getPackageName())) {
            Bundle bundle = statusBarNotification.getNotification().extras;
            String appNameByPackageName = getAppNameByPackageName(this.packageName);
            drawableToBitmap(getAppIconByPackageName(this.packageName));
            String string = bundle.getString(NotificationCompat.EXTRA_TITLE);
            String string2 = bundle.getString(NotificationCompat.EXTRA_TEXT);
            LogHelps.i(appNameByPackageName + ", " + string + ", " + string2 + ", " + statusBarNotification.getPostTime() + ", " + statusBarNotification.getId());
        }
    }

    private String getAppNameByPackageName(String str) {
        PackageManager packageManager = getPackageManager();
        try {
            return packageManager.getApplicationLabel(packageManager.getPackageInfo(str, 8192).applicationInfo).toString();
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    private Drawable getAppIconByPackageName(String str) {
        PackageManager packageManager = getPackageManager();
        try {
            return packageManager.getPackageInfo(str, 8192).applicationInfo.loadIcon(packageManager);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Bitmap drawableToBitmap(Drawable drawable) {
        int intrinsicWidth = drawable.getIntrinsicWidth();
        int intrinsicHeight = drawable.getIntrinsicHeight();
        Bitmap createBitmap = Bitmap.createBitmap(intrinsicWidth, intrinsicHeight, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        drawable.setBounds(0, 0, intrinsicWidth, intrinsicHeight);
        drawable.draw(canvas);
        return createBitmap;
    }

    public void onNotificationRemoved(StatusBarNotification statusBarNotification) {
        super.onNotificationRemoved(statusBarNotification);
        LogHelps.d(statusBarNotification.getId() + "");
        sendBroadcast((Intent) JavaStandard.also(new Intent(), new Function1Void(statusBarNotification) {
            public final /* synthetic */ StatusBarNotification f$1;

            {
                this.f$1 = r2;
            }

            public final void invoke(Object obj) {
                NotificationMessageService.this.lambda$onNotificationRemoved$0$NotificationMessageService(this.f$1, (Intent) obj);
            }
        }));
    }

    public /* synthetic */ void lambda$onNotificationRemoved$0$NotificationMessageService(StatusBarNotification statusBarNotification, Intent intent) {
        intent.setAction("com.szchoiceway.customerui.service.NotificationMessageService_com.es.file.explorer.manager");
        intent.putExtra("removed", true);
        intent.putExtra("id", statusBarNotification.getId());
        intent.putExtra("packageName", this.packageName);
    }
}
