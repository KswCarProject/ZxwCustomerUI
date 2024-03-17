package com.szchoiceway.customerui.service;

import android.content.Intent;
import android.os.Bundle;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.core.app.NotificationCompat;
import com.szchoiceway.customerui.broadcast.MediaNotificationBroadcastReceiver;
import com.szchoiceway.customerui.kt_like.EqualsUtil;

public class MusicNotificationListenerService extends NotificationListenerService {
    private String mediaFocusPackageName;
    private int musicInfoCount = 0;
    private String musicSinger;
    private String musicTitle;

    public void onCreate() {
        Log.d("MusicNotificationListenerService", "onCreate: ");
    }

    public void onNotificationPosted(StatusBarNotification statusBarNotification) {
        Log.d("MusicNotificationListenerService", "onNotificationPosted: " + this.mediaFocusPackageName);
        String packageName = statusBarNotification.getPackageName();
        Log.d("MusicNotificationListenerService", "onNotificationPosted: ");
        if (packageName != null && packageName.equals(MediaNotificationBroadcastReceiver.QQ_MUSIC_PAGENAME)) {
            Bundle bundle = statusBarNotification.getNotification().extras;
            this.musicTitle = bundle.getString(NotificationCompat.EXTRA_TITLE);
            this.musicSinger = bundle.getString(NotificationCompat.EXTRA_TEXT);
            String str = this.musicTitle;
            if ((str == null || "".equals(str.trim())) && statusBarNotification.getNotification().bigContentView != null) {
                this.musicInfoCount = 0;
                getMusicInfo((ViewGroup) statusBarNotification.getNotification().bigContentView.apply(this, (ViewGroup) null));
            }
            Intent intent = new Intent();
            intent.setAction("com.media.play");
            intent.putExtra("package_name", this.mediaFocusPackageName);
            intent.putExtra("music_title", this.musicTitle);
            intent.putExtra("music_singer", this.musicSinger);
            sendBroadcast(intent);
            Log.d("MusicNotificationListenerService", "onNotificationPosted: name: " + this.mediaFocusPackageName + "title: " + this.musicTitle + "singer: " + this.musicSinger);
            this.mediaFocusPackageName = packageName;
        }
    }

    public void onNotificationRemoved(StatusBarNotification statusBarNotification) {
        String packageName = statusBarNotification.getPackageName();
        if (packageName != null && EqualsUtil.equals((Object) this.mediaFocusPackageName, (Object) packageName)) {
            Intent intent = new Intent();
            intent.setAction("com.media.remove");
            sendBroadcast(intent);
        }
    }

    private void getAllChildView(View view) {
        ViewGroup viewGroup = (ViewGroup) view;
        for (int i = 0; i < viewGroup.getChildCount(); i++) {
            View childAt = viewGroup.getChildAt(i);
            if (childAt instanceof TextView) {
                Log.d("MusicNotificationListenerService", "getAllChildView: " + ((TextView) childAt).getText().toString());
            }
            if (childAt instanceof ViewGroup) {
                getAllChildView(childAt);
            }
        }
    }

    private void getMusicInfo(View view) {
        ViewGroup viewGroup = (ViewGroup) view;
        int i = 0;
        while (i < viewGroup.getChildCount()) {
            View childAt = viewGroup.getChildAt(i);
            if (this.musicInfoCount <= 1) {
                if (childAt instanceof TextView) {
                    TextView textView = (TextView) childAt;
                    Log.d("MusicNotificationListenerService", "getAllChildView: " + textView.getText().toString());
                    if (!"".equals(textView.getText().toString().trim())) {
                        if (this.musicInfoCount == 0) {
                            this.musicTitle = textView.getText().toString();
                        } else {
                            this.musicSinger = textView.getText().toString();
                        }
                        this.musicInfoCount++;
                    }
                }
                if (childAt instanceof ViewGroup) {
                    getMusicInfo(childAt);
                }
                i++;
            } else {
                return;
            }
        }
    }
}
