package com.szchoiceway.customerui.service;

import android.app.ActivityManager;
import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.AudioPlaybackConfiguration;
import android.media.session.MediaController;
import android.media.session.MediaSessionManager;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;
import com.szchoiceway.customerui.bean.MediaBean;
import com.szchoiceway.customerui.broadcast.MediaNotificationBroadcastReceiver;
import com.szchoiceway.customerui.kt_like.DataClassHelper;
import com.szchoiceway.customerui.utils.LogHelps;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class MediaNotificationService extends Service {
    private static final String MEDIA_NOTIFICATION_SERVICE_CREATE_HANDLER_THREAD = "MediaNotificationService_createHandlerThread";
    private static final int WHAT_1 = 1;
    /* access modifiers changed from: private */
    public String curPackageName;
    /* access modifiers changed from: private */
    public final Handler handler = createHandlerThread(MEDIA_NOTIFICATION_SERVICE_CREATE_HANDLER_THREAD, new Handler.Callback() {
        public boolean handleMessage(Message message) {
            if (message.what != 1) {
                return false;
            }
            MediaNotificationService mediaNotificationService = MediaNotificationService.this;
            mediaNotificationService.updateCurrentMediaState(mediaNotificationService.curPackageName);
            return false;
        }
    });
    /* access modifiers changed from: private */
    public boolean isMediaPlay;
    /* access modifiers changed from: private */
    public String mAudioFocusPkg;
    /* access modifiers changed from: private */
    public long mCurrentTimeMillisecond;
    /* access modifiers changed from: private */
    public Map<String, MediaController> mMediaControllerMap = new HashMap();

    public IBinder onBind(Intent intent) {
        return null;
    }

    public static void startService(Context context) {
        Log.d("MediaNotificationService", "startServiceMediaNotificationService");
        context.startService(new Intent(context, MediaNotificationService.class));
    }

    public void onCreate() {
        super.onCreate();
        LogHelps.i("");
        initMediaSessionManager(this);
    }

    public int onStartCommand(Intent intent, int i, int i2) {
        super.onStartCommand(intent, i, i2);
        return 1;
    }

    private void initMediaSessionManager(Context context) {
        LogHelps.i("context start:" + context);
        ((MediaSessionManager) context.getSystemService("media_session")).addOnActiveSessionsChangedListener(new MediaSessionManager.OnActiveSessionsChangedListener() {
            public final void onActiveSessionsChanged(List list) {
                MediaNotificationService.this.lambda$initMediaSessionManager$0$MediaNotificationService(list);
            }
        }, new ComponentName(context, MediaNotificationService.class));
        ((AudioManager) context.getSystemService("audio")).registerAudioPlaybackCallback(new AudioManager.AudioPlaybackCallback() {
            public void onPlaybackConfigChanged(List<AudioPlaybackConfiguration> list) {
                super.onPlaybackConfigChanged(list);
                boolean unused = MediaNotificationService.this.isMediaPlay = false;
                Iterator<AudioPlaybackConfiguration> it = list.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    AudioPlaybackConfiguration next = it.next();
                    try {
                        Object invoke = next.getClass().getMethod("getPlayerState", new Class[0]).invoke(next, new Object[0]);
                        LogHelps.i("playerState:" + invoke);
                        if ((invoke instanceof Integer) && ((Integer) invoke).intValue() == 2) {
                            boolean unused2 = MediaNotificationService.this.isMediaPlay = true;
                            break;
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                String runningTasksTopActivityPackageName = MediaNotificationService.getRunningTasksTopActivityPackageName(MediaNotificationService.this.getBaseContext());
                LogHelps.i("runningTasksTopActivityPackageName: " + runningTasksTopActivityPackageName + ", curPackageName: " + MediaNotificationService.this.curPackageName);
                MediaController mediaController = (MediaController) MediaNotificationService.this.mMediaControllerMap.get(runningTasksTopActivityPackageName);
                if (mediaController == null && (mediaController = (MediaController) MediaNotificationService.this.mMediaControllerMap.get(MediaNotificationService.this.curPackageName)) == null) {
                    LogHelps.i("MediaController is null");
                    return;
                }
                LogHelps.i("AudioManagerIsMediaPlay: " + MediaNotificationService.this.isMediaPlay, "topActivity_packageName:" + runningTasksTopActivityPackageName, "curPackageName:" + mediaController.getPackageName(), "mediaController:" + mediaController, "getMetadata:" + mediaController.getMetadata());
                String unused3 = MediaNotificationService.this.curPackageName = mediaController.getPackageName();
                MediaNotificationService mediaNotificationService = MediaNotificationService.this;
                String unused4 = mediaNotificationService.mAudioFocusPkg = mediaNotificationService.curPackageName;
                if (mediaController.getPlaybackState() != null) {
                    long unused5 = MediaNotificationService.this.mCurrentTimeMillisecond = mediaController.getPlaybackState().getPosition();
                }
                MediaNotificationService mediaNotificationService2 = MediaNotificationService.this;
                mediaNotificationService2.sendDelayedMessage(mediaNotificationService2.handler, 1, 100);
            }
        }, this.handler);
        LogHelps.i("context end:" + context);
    }

    public /* synthetic */ void lambda$initMediaSessionManager$0$MediaNotificationService(List list) {
        if (list == null) {
            LogHelps.i("controllers == null");
            return;
        }
        LogHelps.i(" controllers.size = " + list.size());
        HashSet hashSet = new HashSet();
        synchronized (this) {
            Iterator it = list.iterator();
            while (it.hasNext()) {
                MediaController mediaController = (MediaController) it.next();
                String packageName = mediaController.getPackageName();
                if (!hashSet.contains(packageName)) {
                    hashSet.add(packageName);
                    LogHelps.i("for_packageName:" + packageName, "mediaController:" + mediaController, "getPlaybackState:" + mediaController.getPlaybackState());
                    this.mMediaControllerMap.put(packageName, mediaController);
                }
            }
        }
        sendDelayedMessage(this.handler, 1, 100);
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x005e  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x00fd  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void updateCurrentMediaState(java.lang.String r13) {
        /*
            r12 = this;
            java.lang.String r0 = r12.mAudioFocusPkg
            java.lang.String r1 = "com.szchoiceway"
            boolean r0 = com.szchoiceway.customerui.utils.ViewHelps.contains(r0, r1)
            r1 = 0
            r2 = 1
            if (r0 == 0) goto L_0x0029
            java.lang.String[] r13 = new java.lang.String[r2]
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r2 = "is szchoiceway return mAudioFocusPkg:"
            java.lang.StringBuilder r0 = r0.append(r2)
            java.lang.String r12 = r12.mAudioFocusPkg
            java.lang.StringBuilder r12 = r0.append(r12)
            java.lang.String r12 = r12.toString()
            r13[r1] = r12
            com.szchoiceway.customerui.utils.LogHelps.i(r13)
            return
        L_0x0029:
            com.szchoiceway.customerui.bean.MediaBean r0 = new com.szchoiceway.customerui.bean.MediaBean
            r0.<init>()
            java.util.Map<java.lang.String, android.media.session.MediaController> r3 = r12.mMediaControllerMap
            java.lang.Object r3 = r3.get(r13)
            android.media.session.MediaController r3 = (android.media.session.MediaController) r3
            r4 = 0
            if (r3 == 0) goto L_0x0137
            android.media.session.PlaybackState r6 = r3.getPlaybackState()
            if (r6 == 0) goto L_0x0057
            android.media.session.PlaybackState r6 = r3.getPlaybackState()
            long r6 = r6.getPosition()
            r12.mCurrentTimeMillisecond = r6
            android.media.session.PlaybackState r6 = r3.getPlaybackState()
            int r6 = r6.getState()
            r7 = 3
            if (r6 != r7) goto L_0x0057
            r6 = r2
            goto L_0x0058
        L_0x0057:
            r6 = r1
        L_0x0058:
            android.media.MediaMetadata r3 = r3.getMetadata()
            if (r3 == 0) goto L_0x00fd
            java.lang.String r4 = "android.media.metadata.TITLE"
            java.lang.String r5 = r3.getString(r4)
            java.lang.String r7 = "android.media.metadata.ARTIST"
            java.lang.String r8 = r3.getString(r7)
            java.lang.String[] r9 = new java.lang.String[r2]
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            r10.<init>()
            java.lang.String r11 = "metadata.getBitmap(MediaMetadata.METADATA_KEY_ALBUM_ART);: title: "
            java.lang.StringBuilder r10 = r10.append(r11)
            java.lang.StringBuilder r5 = r10.append(r5)
            java.lang.String r10 = ", singer: "
            java.lang.StringBuilder r5 = r5.append(r10)
            java.lang.StringBuilder r5 = r5.append(r8)
            java.lang.String r8 = ", album: "
            java.lang.StringBuilder r5 = r5.append(r8)
            java.lang.String r8 = "android.media.metadata.ALBUM_ART"
            android.graphics.Bitmap r10 = r3.getBitmap(r8)
            java.lang.StringBuilder r5 = r5.append(r10)
            java.lang.String r5 = r5.toString()
            r9[r1] = r5
            com.szchoiceway.customerui.utils.LogHelps.i(r9)
            com.szchoiceway.customerui.bean.MediaBean r1 = r0.setMediaPlay(r6)
            com.szchoiceway.customerui.bean.MediaBean r1 = r1.setShowUIMediaInfo(r2)
            java.lang.String r4 = r3.getString(r4)
            com.szchoiceway.customerui.bean.MediaBean r1 = r1.setMediaTitle(r4)
            java.lang.String r4 = r3.getString(r7)
            com.szchoiceway.customerui.bean.MediaBean r1 = r1.setMediaArtist(r4)
            java.lang.String r4 = "android.media.metadata.ALBUM_ARTIST"
            java.lang.String r4 = r3.getString(r4)
            com.szchoiceway.customerui.bean.MediaBean r1 = r1.setMediaAlbumArtist(r4)
            android.graphics.Bitmap r4 = r3.getBitmap(r8)
            com.szchoiceway.customerui.bean.MediaBean r1 = r1.setMediaAlbumBitmap(r4)
            java.lang.String r4 = "android.media.metadata.ALBUM"
            java.lang.String r4 = r3.getString(r4)
            com.szchoiceway.customerui.bean.MediaBean r1 = r1.setMediaAlbum(r4)
            java.lang.String r4 = "android.media.metadata.DURATION"
            long r3 = r3.getLong(r4)
            com.szchoiceway.customerui.bean.MediaBean r1 = r1.setMediaTotalTimeMillisecond(r3)
            long r3 = r12.mCurrentTimeMillisecond
            com.szchoiceway.customerui.bean.MediaBean r1 = r1.setMediaCurrentTimeMillisecond(r3)
            r1.setPlayingAppPackageName(r13)
            r12.updateMediaBean(r0)
            if (r6 == 0) goto L_0x00f7
            long r0 = r12.mCurrentTimeMillisecond
            r3 = 500(0x1f4, double:2.47E-321)
            long r0 = r0 + r3
            r12.mCurrentTimeMillisecond = r0
            android.os.Handler r12 = r12.handler
            r12.sendEmptyMessageDelayed(r2, r3)
            goto L_0x00fc
        L_0x00f7:
            android.os.Handler r12 = r12.handler
            r12.removeMessages(r2)
        L_0x00fc:
            return
        L_0x00fd:
            java.lang.String r2 = "media data is null"
            java.lang.String[] r2 = new java.lang.String[]{r2}
            com.szchoiceway.customerui.utils.LogHelps.i(r2)
            com.szchoiceway.customerui.bean.MediaBean r2 = r0.setMediaPlay(r1)
            r3 = 1880031432(0x700f00c8, float:1.770292E29)
            java.lang.String r6 = r12.getString(r3)
            com.szchoiceway.customerui.bean.MediaBean r2 = r2.setMediaTitle(r6)
            java.lang.String r3 = r12.getString(r3)
            com.szchoiceway.customerui.bean.MediaBean r2 = r2.setMediaArtist(r3)
            java.lang.String r3 = ""
            com.szchoiceway.customerui.bean.MediaBean r2 = r2.setMediaAlbumArtist(r3)
            com.szchoiceway.customerui.bean.MediaBean r2 = r2.setMediaAlbum(r3)
            r3 = 0
            com.szchoiceway.customerui.bean.MediaBean r2 = r2.setMediaAlbumBitmap(r3)
            com.szchoiceway.customerui.bean.MediaBean r2 = r2.setMediaCurrentTimeMillisecond(r4)
            com.szchoiceway.customerui.bean.MediaBean r2 = r2.setMediaTotalTimeMillisecond(r4)
            r2.setPlayingAppPackageName(r13)
        L_0x0137:
            boolean r13 = r12.isMediaPlay
            com.szchoiceway.customerui.bean.MediaBean r13 = r0.setMediaPlay(r13)
            com.szchoiceway.customerui.bean.MediaBean r13 = r13.setShowUIMediaInfo(r1)
            com.szchoiceway.customerui.bean.MediaBean r13 = r13.setMediaTotalTimeMillisecond(r4)
            r13.setMediaCurrentTimeMillisecond(r4)
            r12.updateMediaBean(r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.szchoiceway.customerui.service.MediaNotificationService.updateCurrentMediaState(java.lang.String):void");
    }

    private void updateMediaBean(MediaBean mediaBean) {
        LogHelps.i("mediaBean: " + DataClassHelper.toString(mediaBean));
        MediaNotificationBroadcastReceiver.sendMessage(this, mediaBean);
    }

    public static Handler createHandlerThread(String str, Handler.Callback callback) {
        HandlerThread handlerThread = new HandlerThread(str);
        handlerThread.start();
        return new Handler(handlerThread.getLooper(), new Handler.Callback(handlerThread, callback) {
            public final /* synthetic */ HandlerThread f$0;
            public final /* synthetic */ Handler.Callback f$1;

            {
                this.f$0 = r1;
                this.f$1 = r2;
            }

            public final boolean handleMessage(Message message) {
                return MediaNotificationService.lambda$createHandlerThread$1(this.f$0, this.f$1, message);
            }
        });
    }

    static /* synthetic */ boolean lambda$createHandlerThread$1(HandlerThread handlerThread, Handler.Callback callback, Message message) {
        int i = message.what;
        if (i == -1001) {
            handlerThread.quitSafely();
            return false;
        } else if (i == -1000) {
            handlerThread.quit();
            return false;
        } else if (callback == null) {
            return false;
        } else {
            callback.handleMessage(message);
            return false;
        }
    }

    public static String getRunningTasksTopActivityPackageName(Context context) {
        ComponentName runningTasksTopActivity = getRunningTasksTopActivity(context);
        if (runningTasksTopActivity == null) {
            return null;
        }
        return runningTasksTopActivity.getPackageName();
    }

    public static ComponentName getRunningTasksTopActivity(Context context) {
        List<ActivityManager.RunningTaskInfo> runningTasks = getRunningTasks(context, 1);
        return (runningTasks == null || runningTasks.size() <= 0) ? new ComponentName("", "") : runningTasks.get(0).topActivity;
    }

    public static List<ActivityManager.RunningTaskInfo> getRunningTasks(Context context, int i) {
        List<ActivityManager.RunningTaskInfo> list;
        try {
            list = getActivityManager(context).getRunningTasks(i);
        } catch (Exception e) {
            e.printStackTrace();
            list = null;
        }
        return list == null ? new ArrayList() : list;
    }

    public static ActivityManager getActivityManager(Context context) {
        return (ActivityManager) context.getSystemService("activity");
    }

    /* access modifiers changed from: private */
    public void sendDelayedMessage(Handler handler2, int i, long j) {
        handler2.removeMessages(i);
        handler2.sendEmptyMessageDelayed(i, j);
    }
}
