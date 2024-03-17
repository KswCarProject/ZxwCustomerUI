package com.szchoiceway.customerui.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.util.Log;
import com.szchoiceway.customerui.bean.MediaBean;
import com.szchoiceway.customerui.kt_like.DataClassHelper;
import com.szchoiceway.customerui.kt_like.EqualsUtil;
import com.szchoiceway.customerui.kt_like.ListUtil;
import com.szchoiceway.customerui.utils.EventUtils;
import java.util.List;

public class MediaNotificationBroadcastReceiver extends BroadcastReceiver {
    public static final String ACTION;
    public static final String ENTITY_ALBUM_BITMAP;
    public static final String ENTITY_KEY;
    public static final String NETEASE_MUSIC_PAGENAME = "com.netease.cloudmusic";
    public static final String PLAY_PAUSE_MUSIC_BLUETOOTH_ACTION = "PLAY_PAUSE_MUSIC_BLUETOOTH_ACTION";
    public static final String QQ_MUSIC_PAGENAME = "com.tencent.qqmusic";
    private static final List<String> filterPackageList = ListUtil.listOf(EventUtils.YOUTUBE_MODE_PACKAGE_NAME);
    private static final List<String> receivePackageList = ListUtil.listOf(QQ_MUSIC_PAGENAME, NETEASE_MUSIC_PAGENAME);

    public void onMediaBeanChanged(MediaBean mediaBean) {
    }

    public void onPlayingStateChanged(boolean z) {
    }

    static {
        String name = MediaNotificationBroadcastReceiver.class.getName();
        ACTION = name;
        ENTITY_KEY = name + "_mediaBean";
        ENTITY_ALBUM_BITMAP = name + "_mediaBean_album_bitmap";
    }

    public static void sendMessage(Context context, MediaBean mediaBean) {
        Intent intent = new Intent(ACTION);
        if (!filterPackageList.contains(mediaBean.getPlayingAppPackageName())) {
            try {
                MediaBean mediaBean2 = (MediaBean) DataClassHelper.copy(mediaBean);
                Bitmap mediaAlbumBitmap = mediaBean2.getMediaAlbumBitmap();
                if (mediaAlbumBitmap != null) {
                    mediaBean2.setMediaAlbumBitmap((Bitmap) null);
                    intent.putExtra(ENTITY_ALBUM_BITMAP, mediaAlbumBitmap);
                }
                intent.putExtra(ENTITY_KEY, mediaBean2);
                context.sendBroadcast(intent);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static MediaNotificationBroadcastReceiver register(Context context) {
        MediaNotificationBroadcastReceiver mediaNotificationBroadcastReceiver = new MediaNotificationBroadcastReceiver();
        context.registerReceiver(mediaNotificationBroadcastReceiver, new IntentFilter(ACTION));
        return mediaNotificationBroadcastReceiver;
    }

    public void unregister(Context context) {
        context.unregisterReceiver(this);
    }

    public void onReceive(Context context, Intent intent) {
        MediaBean mediaBean;
        String action = intent.getAction();
        Log.e(PLAY_PAUSE_MUSIC_BLUETOOTH_ACTION, action);
        if (PLAY_PAUSE_MUSIC_BLUETOOTH_ACTION.equals(action)) {
            Log.e(PLAY_PAUSE_MUSIC_BLUETOOTH_ACTION, intent.getBooleanExtra("isPlaying", false) + "");
            onPlayingStateChanged(intent.getBooleanExtra("isPlaying", false));
        } else if (EqualsUtil.equals((Object) action, (Object) ACTION)) {
            Bitmap bitmap = null;
            try {
                mediaBean = (MediaBean) intent.getSerializableExtra(ENTITY_KEY);
            } catch (Exception e) {
                e.printStackTrace();
                mediaBean = null;
            }
            if (mediaBean != null) {
                try {
                    bitmap = (Bitmap) intent.getParcelableExtra(ENTITY_ALBUM_BITMAP);
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
                if (bitmap != null) {
                    mediaBean.setMediaAlbumBitmap(bitmap);
                }
                onMediaBeanChanged(mediaBean);
            }
        }
    }
}
