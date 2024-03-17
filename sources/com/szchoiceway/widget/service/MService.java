package com.szchoiceway.widget.service;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Binder;
import android.os.IBinder;
import android.os.SystemClock;
import android.util.Log;
import androidx.core.app.NotificationCompat;
import com.szchoiceway.customerui.R;
import com.szchoiceway.widget.WeatherWidget;
import java.util.ArrayList;
import java.util.List;

public class MService extends Service {
    private static final String TAG = "MService";
    private static final int id = 12;
    private MReceiver mReceiver;
    /* access modifiers changed from: private */
    public List<ReceiverListener> mReceiverListeners = new ArrayList();

    public interface ReceiverListener {
        void onReceive(Intent intent);
    }

    public IBinder onBind(Intent intent) {
        return new MBinder();
    }

    public class MBinder extends Binder {
        public MBinder() {
        }

        public MService getService() {
            return MService.this;
        }
    }

    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "onCreate: ");
        stopForeground();
        registerReceiver();
    }

    public int onStartCommand(Intent intent, int i, int i2) {
        Log.i(TAG, "onStartCommand: ");
        return super.onStartCommand(intent, i, i2);
    }

    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver();
    }

    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    public void addListener(ReceiverListener receiverListener) {
        if (receiverListener != null && !this.mReceiverListeners.contains(receiverListener)) {
            this.mReceiverListeners.add(receiverListener);
        }
        Log.i(TAG, "addListener: mReceiverListeners.size = " + this.mReceiverListeners.size());
    }

    public void removeListener(ReceiverListener receiverListener) {
        this.mReceiverListeners.remove(receiverListener);
    }

    private void registerReceiver() {
        if (this.mReceiver == null) {
            this.mReceiver = new MReceiver();
        }
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(WeatherWidget.ACTION_WEATHER_UPDATE);
        registerReceiver(this.mReceiver, intentFilter);
    }

    private void unregisterReceiver() {
        MReceiver mReceiver2 = this.mReceiver;
        if (mReceiver2 != null) {
            unregisterReceiver(mReceiver2);
            this.mReceiver = null;
        }
    }

    final class MReceiver extends BroadcastReceiver {
        MReceiver() {
        }

        public void onReceive(Context context, Intent intent) {
            intent.getAction();
            for (ReceiverListener onReceive : MService.this.mReceiverListeners) {
                onReceive.onReceive(intent);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void stopForeground() {
        Notification build = new NotificationCompat.Builder((Context) this, "channel_12").setOngoing(true).setChannelId("channel_12").setSmallIcon((int) R.mipmap.ic_launcher).build();
        NotificationChannel notificationChannel = new NotificationChannel("channel_12", getString(R.string.app_name), 3);
        notificationChannel.setLockscreenVisibility(1);
        ((NotificationManager) getSystemService("notification")).createNotificationChannel(notificationChannel);
        startForeground(12, build);
        SystemClock.sleep(50);
        stopForeground(1);
    }
}
