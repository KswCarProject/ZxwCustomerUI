package com.szchoiceway.customerui.bmw.recycle;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.graphics.SurfaceTexture;
import android.graphics.drawable.Drawable;
import android.hardware.display.VirtualDisplay;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Surface;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import com.szchoiceway.customerui.R;
import com.szchoiceway.customerui.model.LauncherModel;
import com.szchoiceway.customerui.utils.EventUtils;
import com.szchoiceway.customerui.utils.SysProviderOpt;
import com.szchoiceway.customerui.views.CustomerFramelayout;
import com.szchoiceway.zxwlib.GyroScopeWithCompassView;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class VideoItem extends RecycleItemBase implements View.OnClickListener {
    private static final String BUNDLE_VALUE = "BundleValue";
    private static final String GET_SURFACE = "getSurface";
    private static final String IS_IN_PIP = "isInPIPMode";
    private static final String IS_IN_PIP_EXTRA = "isInPIPMode_extra";
    private static final String NOTIFY_SEND_SURFACE = "notify_send_surface";
    private static final String SEND_SURFACE = "sendSurface";
    private static final String SURFACE = "Surface";
    private static final String SURFACE_SIZE = "surface_size";
    private static final String TAG = "VideoItem";
    /* access modifiers changed from: private */
    public ImageView Vcover;
    private ExecutorService executorService;
    private ImageView imgBk;
    private View imgLine;
    private View imgV;
    boolean isInFocus = false;
    Context mContext;
    private View mCover;
    private String mCurPlayPath = "";
    Handler mHandler = new Handler(Looper.getMainLooper()) {
        public void handleMessage(Message message) {
            super.handleMessage(message);
            int i = message.what;
            if (i != 111) {
                if (i == 222) {
                    Log.d(VideoItem.TAG, "handler 222");
                    if (VideoItem.this.Vcover != null) {
                        VideoItem.this.Vcover.setVisibility(8);
                    }
                    VideoItem.this.isInFocus = false;
                    VideoItem.this.reSendSurface();
                }
            } else if (!VideoItem.this.isInFocus && VideoItem.this.mTextureContaniner != null) {
                VideoItem.this.mTextureContaniner.removeAllViews();
                VideoItem.this.textureView = new TextureView(VideoItem.this.mContext);
                VideoItem.this.mTextureContaniner.addView(VideoItem.this.textureView);
                VideoItem.this.textureView.setSurfaceTextureListener(new TextureView.SurfaceTextureListener() {
                    public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i, int i2) {
                    }

                    public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
                    }

                    public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i, int i2) {
                        if (surfaceTexture != null) {
                            Surface surface = new Surface(surfaceTexture);
                            Log.i("TAG", "onSurfaceTextureAvailable: gegwgwegew");
                            VideoItem.this.sendSurface(surface);
                        }
                    }

                    public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
                        Log.i("TAG", "onSurfaceTextureDestroyed: gegwgwegew");
                        VideoItem.this.sendSurface((Surface) null);
                        VideoItem.this.textureView = null;
                        return true;
                    }
                });
            }
        }
    };
    /* access modifiers changed from: private */
    public boolean mIsInPIP = false;
    private int mMode = 0;
    private boolean mSmallMode = false;
    private SurfaceReceiver mSurfaceModule = new SurfaceReceiver();
    /* access modifiers changed from: private */
    public CustomerFramelayout mTextureContaniner;
    private TextView mTitle;
    private TextView mVideoTitle;
    View mVideoView;
    private CheckBox mVidoPlaypause;
    TextureView textureView;
    VirtualDisplay virtualDisplay;

    public String getTag() {
        return "Video";
    }

    public View getSetView(Context context, boolean z) {
        View view;
        this.mContext = context;
        this.m_iModeSet = SysProviderOpt.getInstance(context).getRecordInteger("KESAIWEI_SYS_MODE_SELECTION", this.m_iModeSet);
        if (this.mSmallMode == z && (view = this.mVideoView) != null) {
            return view;
        }
        this.mSmallMode = z;
        if (this.m_iModeSet == 20) {
            if (z) {
                this.mVideoView = LayoutInflater.from(context).inflate(R.layout.pemp_bmw_id8_video_item_layout_small, (ViewGroup) null);
            } else {
                this.mVideoView = LayoutInflater.from(context).inflate(R.layout.pemp_bmw_id8_video_item_layout, (ViewGroup) null);
            }
        } else if (z) {
            this.mVideoView = LayoutInflater.from(context).inflate(R.layout.bmw_id8_video_item_layout_small, (ViewGroup) null);
        } else {
            this.mVideoView = LayoutInflater.from(context).inflate(R.layout.bmw_id8_video_item_layout, (ViewGroup) null);
        }
        init();
        return this.mVideoView;
    }

    private void init() {
        this.mSurfaceModule.registerRec();
        if (this.executorService == null) {
            this.executorService = Executors.newSingleThreadExecutor();
        }
        this.Vcover = (ImageView) this.mVideoView.findViewById(R.id.Vcover);
        this.mCover = this.mVideoView.findViewById(R.id.cover);
        ImageView imageView = (ImageView) this.mVideoView.findViewById(R.id.imgBk);
        this.imgBk = imageView;
        if (imageView != null) {
            imageView.setVisibility(this.mSmallMode ? 8 : 0);
        }
        this.imgLine = this.mVideoView.findViewById(R.id.imgLine);
        this.imgV = this.mVideoView.findViewById(R.id.imgV);
        this.mTitle = (TextView) this.mVideoView.findViewById(R.id.title);
        this.mVideoTitle = (TextView) this.mVideoView.findViewById(R.id.videoTitle);
        CheckBox checkBox = (CheckBox) this.mVideoView.findViewById(R.id.vidoPlaypause);
        this.mVidoPlaypause = checkBox;
        if (checkBox != null) {
            checkBox.setOnClickListener(this);
        }
        refreshPlayInfo();
        updateInfo();
        if (isVideoMode()) {
            String str = this.mCurPlayPath;
            this.mCurPlayPath = "";
            setCurPlayVideoPath(str);
        }
        CustomerFramelayout customerFramelayout = (CustomerFramelayout) this.mVideoView.findViewById(R.id.textureContaniner);
        this.mTextureContaniner = customerFramelayout;
        customerFramelayout.setLayoutVisibilityChangedListener(new CustomerFramelayout.LayoutVisibilityChangedListener() {
            public void onVisibilityChanged(int i) {
                if (i == 0) {
                    if (VideoItem.this.textureView == null) {
                        VideoItem.this.reSendSurface();
                    } else {
                        VideoItem.this.notifySendSurfaceToVideoplayer();
                    }
                } else if (VideoItem.this.mHandler != null) {
                    VideoItem.this.mHandler.removeMessages(111);
                }
            }
        });
        reSendSurface();
    }

    public void updateInfo() {
        if (this.mTitle != null) {
            this.mTitle.setText(this.mContext.getString(R.string.lb_video).toUpperCase());
        }
        this.mMode = SysProviderOpt.getInstance(this.mContext).getRecordInteger("KESAIWEI_SYS_DISPLAY_MODE", 0);
        if (this.m_iModeSet == 20) {
            int i = this.mMode;
            if (i == 0) {
                TextView textView = this.mTitle;
                if (textView != null) {
                    textView.setTextColor(getColor(R.color.bmw_id8_mainpage_item_title_color));
                }
                ImageView imageView = this.imgBk;
                if (imageView != null) {
                    imageView.setImageResource(this.mSmallMode ? R.drawable.pemp_bmw_id8_weather_item_background_blue_small : R.drawable.pemp_bmw_id8_weather_item_background_blue);
                }
                View view = this.imgLine;
                if (view != null) {
                    view.setBackgroundResource(this.mSmallMode ? R.drawable.pemp_id8_main_edit_icon_weather_line_b : R.drawable.pemp_id8_main_icon_weather_line_b);
                }
            } else if (i == 1) {
                TextView textView2 = this.mTitle;
                if (textView2 != null) {
                    textView2.setTextColor(getColor(R.color.bmw_id8_mainpage_item_title_color_sport));
                }
                ImageView imageView2 = this.imgBk;
                if (imageView2 != null) {
                    imageView2.setImageResource(this.mSmallMode ? R.drawable.pemp_bmw_id8_weather_item_background_red_small : R.drawable.pemp_bmw_id8_weather_item_background_red);
                }
                View view2 = this.imgLine;
                if (view2 != null) {
                    view2.setBackgroundResource(this.mSmallMode ? R.drawable.pemp_id8_main_edit_icon_weather_line_r : R.drawable.pemp_id8_main_icon_weather_line_r);
                }
            } else if (i == 2) {
                TextView textView3 = this.mTitle;
                if (textView3 != null) {
                    textView3.setTextColor(getColor(R.color.bmw_id8_mainpage_item_title_color_personal));
                }
                ImageView imageView3 = this.imgBk;
                if (imageView3 != null) {
                    imageView3.setImageResource(this.mSmallMode ? R.drawable.pemp_bmw_id8_weather_item_background_yellow_small : R.drawable.pemp_bmw_id8_weather_item_background_yellow);
                }
                View view3 = this.imgLine;
                if (view3 != null) {
                    view3.setBackgroundResource(this.mSmallMode ? R.drawable.pemp_id8_main_edit_icon_weather_line_y : R.drawable.pemp_id8_main_icon_weather_line_y);
                }
            }
        } else {
            int i2 = this.mMode;
            if (i2 == 0) {
                TextView textView4 = this.mTitle;
                if (textView4 != null) {
                    textView4.setTextColor(getColor(R.color.bmw_id8_mainpage_item_title_color));
                }
                ImageView imageView4 = this.imgBk;
                if (imageView4 != null) {
                    imageView4.setImageResource(this.mSmallMode ? R.drawable.bmw_id8_weather_item_background_blue_small : R.drawable.bmw_id8_weather_item_background_blue);
                }
                View view4 = this.imgLine;
                if (view4 != null) {
                    view4.setBackgroundResource(this.mSmallMode ? R.drawable.id8_main_edit_icon_weather_line_b : R.drawable.id8_main_icon_weather_line_b);
                }
            } else if (i2 == 1) {
                TextView textView5 = this.mTitle;
                if (textView5 != null) {
                    textView5.setTextColor(getColor(R.color.bmw_id8_mainpage_item_title_color_sport));
                }
                ImageView imageView5 = this.imgBk;
                if (imageView5 != null) {
                    imageView5.setImageResource(this.mSmallMode ? R.drawable.bmw_id8_weather_item_background_red_small : R.drawable.bmw_id8_weather_item_background_red);
                }
                View view5 = this.imgLine;
                if (view5 != null) {
                    view5.setBackgroundResource(this.mSmallMode ? R.drawable.id8_main_edit_icon_weather_line_r : R.drawable.id8_main_icon_weather_line_r);
                }
            } else if (i2 == 2) {
                TextView textView6 = this.mTitle;
                if (textView6 != null) {
                    textView6.setTextColor(getColor(R.color.bmw_id8_mainpage_item_title_color_personal));
                }
                ImageView imageView6 = this.imgBk;
                if (imageView6 != null) {
                    imageView6.setImageResource(this.mSmallMode ? R.drawable.bmw_id8_weather_item_background_yellow_small : R.drawable.bmw_id8_weather_item_background_yellow);
                }
                View view6 = this.imgLine;
                if (view6 != null) {
                    view6.setBackgroundResource(this.mSmallMode ? R.drawable.id8_main_edit_icon_weather_line_y : R.drawable.id8_main_icon_weather_line_y);
                }
            }
        }
    }

    public void updataFocusState(boolean z) {
        ImageView imageView;
        ImageView imageView2;
        if (!z) {
            ImageView imageView3 = this.imgBk;
            if (imageView3 != null) {
                imageView3.setForeground((Drawable) null);
            }
        } else if (this.m_iModeSet == 20) {
            int i = this.mMode;
            if (i == 0) {
                ImageView imageView4 = this.imgBk;
                if (imageView4 != null) {
                    imageView4.setForeground(getDrawable(this.mSmallMode ? R.drawable.pemp_id8_main_edit_icon_weather_b_f : R.drawable.pemp_id8_main_icon_weather_b_f));
                }
            } else if (i == 1) {
                ImageView imageView5 = this.imgBk;
                if (imageView5 != null) {
                    imageView5.setForeground(getDrawable(this.mSmallMode ? R.drawable.pemp_id8_main_edit_icon_weather_r_f : R.drawable.pemp_id8_main_icon_weather_r_f));
                }
            } else if (i == 2 && (imageView2 = this.imgBk) != null) {
                imageView2.setForeground(getDrawable(this.mSmallMode ? R.drawable.pemp_id8_main_edit_icon_weather_y_f : R.drawable.pemp_id8_main_icon_weather_y_f));
            }
        } else {
            int i2 = this.mMode;
            if (i2 == 0) {
                ImageView imageView6 = this.imgBk;
                if (imageView6 != null) {
                    imageView6.setForeground(getDrawable(this.mSmallMode ? R.drawable.id8_main_edit_icon_weather_b_f : R.drawable.id8_main_icon_weather_b_f));
                }
            } else if (i2 == 1) {
                ImageView imageView7 = this.imgBk;
                if (imageView7 != null) {
                    imageView7.setForeground(getDrawable(this.mSmallMode ? R.drawable.id8_main_edit_icon_weather_r_f : R.drawable.id8_main_icon_weather_r_f));
                }
            } else if (i2 == 2 && (imageView = this.imgBk) != null) {
                imageView.setForeground(getDrawable(this.mSmallMode ? R.drawable.id8_main_edit_icon_weather_y_f : R.drawable.id8_main_icon_weather_y_f));
            }
        }
    }

    public int[] getViewSize() {
        if (this.mSmallMode) {
            return new int[]{EventUtils.getPx(this.mContext, 316), (int) this.mContext.getResources().getDimension(R.dimen.screenHeight)};
        }
        return new int[]{EventUtils.getPx(this.mContext, 434), (int) this.mContext.getResources().getDimension(R.dimen.screenHeight)};
    }

    private Drawable getDrawable(int i) {
        return this.mContext.getResources().getDrawable(i, (Resources.Theme) null);
    }

    public void onClick(View view) {
        if (view.getId() == R.id.vidoPlaypause) {
            if (view instanceof CheckBox) {
                CheckBox checkBox = (CheckBox) view;
                checkBox.setChecked(!checkBox.isChecked());
            }
            if (!isVideoMode()) {
                com.szchoiceway.zxwlib.utils.EventUtils.startActivityIfNotRuning(this.mContext, "com.szchoiceway.videoplayer", "com.szchoiceway.videoplayer.MainActivity");
                return;
            }
            Intent intent = new Intent("com.choiceway.eventcenter.EventUtils.ZXW_CAN_KEY_EVT");
            intent.putExtra("com.choiceway.eventcenter.EventUtils.ZXW_CAN_KEY_EVT_EXTRA", 6);
            this.mContext.sendBroadcast(intent);
        }
    }

    private boolean isVideoMode() {
        LauncherModel instance = LauncherModel.getInstance();
        try {
            if (instance.getEvtService() == null || instance.getEvtService().getValidMode() != EventUtils.eSrcMode.SRC_MOVIE.getIntValue()) {
                return false;
            }
            return true;
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void refreshPlayInfo() {
        Drawable drawable;
        Drawable drawable2;
        LauncherModel instance = LauncherModel.getInstance();
        if (LauncherModel.getInstance().getEvtService() != null) {
            try {
                if (isVideoMode()) {
                    String validModeTitleInfor = instance.getEvtService().getValidModeTitleInfor();
                    instance.getEvtService().getValidModeArtistInfor();
                    instance.getEvtService().getValidModeAblumInfor();
                    instance.getEvtService().getValidCurTime();
                    instance.getEvtService().getValidTotTime();
                    int validPlayStatus = instance.getEvtService().getValidPlayStatus();
                    TextView textView = this.mVideoTitle;
                    if (textView != null) {
                        textView.setText(validModeTitleInfor);
                    }
                    CheckBox checkBox = this.mVidoPlaypause;
                    if (checkBox != null) {
                        checkBox.setChecked(validPlayStatus == 1);
                    }
                    View view = this.mCover;
                    if (view != null) {
                        view.setVisibility(8);
                    }
                    if (this.imgV != null && this.m_iModeSet == 20) {
                        if (!this.mSmallMode) {
                            View view2 = this.imgV;
                            if (validPlayStatus == 1) {
                                drawable2 = this.mContext.getResources().getDrawable(R.drawable.pemp_id8_main_icon_video_2);
                            } else {
                                drawable2 = this.mContext.getResources().getDrawable(R.drawable.pemp_id8_main_icon_video);
                            }
                            view2.setBackground(drawable2);
                        } else {
                            View view3 = this.imgV;
                            if (validPlayStatus == 1) {
                                drawable = this.mContext.getResources().getDrawable(R.drawable.pemp_id8_main_edit_icon_video_2);
                            } else {
                                drawable = this.mContext.getResources().getDrawable(R.drawable.pemp_id8_main_edit_icon_video);
                            }
                            view3.setBackground(drawable);
                        }
                    }
                    CustomerFramelayout customerFramelayout = this.mTextureContaniner;
                    if (customerFramelayout != null) {
                        customerFramelayout.setVisibility(0);
                        return;
                    }
                    return;
                }
                CustomerFramelayout customerFramelayout2 = this.mTextureContaniner;
                if (customerFramelayout2 != null) {
                    customerFramelayout2.setVisibility(8);
                }
                TextView textView2 = this.mVideoTitle;
                if (textView2 != null) {
                    textView2.setText("");
                }
                CheckBox checkBox2 = this.mVidoPlaypause;
                if (checkBox2 != null) {
                    checkBox2.setChecked(false);
                }
                this.mCurPlayPath = "";
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public void setCurPlayVideoPath(String str) {
        Log.i("TAG", "setCurPlayVideoPath: " + str);
        this.mCurPlayPath = str;
    }

    private int getColor(int i) {
        return this.mContext.getResources().getColor(i, (Resources.Theme) null);
    }

    /* access modifiers changed from: private */
    public void sendSurface(Surface surface) {
        Intent intent = new Intent(SEND_SURFACE);
        Bundle bundle = new Bundle();
        bundle.putParcelable(SURFACE, surface);
        intent.putExtra(BUNDLE_VALUE, bundle);
        intent.putExtra(SURFACE_SIZE, getSurfaceViewSize());
        this.mContext.sendBroadcast(intent);
    }

    private int[] getSurfaceViewSize() {
        if (this.mSmallMode) {
            return new int[]{EventUtils.getPx(this.mContext, 310), EventUtils.getPx(this.mContext, 522)};
        }
        return new int[]{EventUtils.getPx(this.mContext, 428), EventUtils.getPx(this.mContext, 714)};
    }

    public class SurfaceReceiver extends BroadcastReceiver {
        private boolean isReg = false;

        public SurfaceReceiver() {
        }

        public void registerRec() {
            if (!this.isReg) {
                IntentFilter intentFilter = new IntentFilter();
                intentFilter.addAction(VideoItem.GET_SURFACE);
                intentFilter.addAction(VideoItem.IS_IN_PIP);
                intentFilter.addAction("com.choiceway.eventcenter.EventUtils.ZXW_ORIGINAL_MCU_KEY_FOCUS_MOVE_EVT");
                VideoItem.this.mContext.registerReceiver(this, intentFilter);
                this.isReg = true;
            }
        }

        public void unregisterRec() {
            if (this.isReg) {
                VideoItem.this.mContext.unregisterReceiver(this);
                this.isReg = false;
            }
        }

        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals(VideoItem.GET_SURFACE)) {
                Log.i("TAG", "onReceive:gegwgwgegewewgewggwe ");
                VideoItem.this.notifySendSurfaceToVideoplayer();
            } else if (action.equals(VideoItem.IS_IN_PIP)) {
                boolean unused = VideoItem.this.mIsInPIP = intent.getBooleanExtra(VideoItem.IS_IN_PIP_EXTRA, false);
            } else if (action.equals("com.choiceway.eventcenter.EventUtils.ZXW_ORIGINAL_MCU_KEY_FOCUS_MOVE_EVT")) {
                VideoItem.this.isInFocus = true;
                if (VideoItem.this.Vcover != null && VideoItem.this.Vcover.getVisibility() == 8) {
                    VideoItem.this.Vcover.setVisibility(0);
                    if (VideoItem.this.textureView != null) {
                        VideoItem.this.Vcover.setImageBitmap(VideoItem.this.textureView.getBitmap());
                    }
                }
                VideoItem.this.mHandler.removeMessages(GyroScopeWithCompassView.CARTYPE_HAIMA_S7_HI);
                VideoItem.this.mHandler.sendEmptyMessageDelayed(GyroScopeWithCompassView.CARTYPE_HAIMA_S7_HI, 1000);
            }
        }
    }

    /* access modifiers changed from: private */
    public void reSendSurface() {
        Handler handler = this.mHandler;
        if (handler != null) {
            handler.removeMessages(111);
            this.mHandler.sendEmptyMessage(111);
        }
    }

    private boolean isNeedPlayOnHomePage() {
        Context context = this.mContext;
        if (context == null) {
            return false;
        }
        String topActivity = com.szchoiceway.zxwlib.utils.EventUtils.getTopActivity(context);
        Log.i("TAG", "isNeedPlayOnHomePage:gegwgwegew " + topActivity);
        return !TextUtils.isEmpty(topActivity) && topActivity.startsWith("com.android.launcher3");
    }

    /* access modifiers changed from: private */
    public void notifySendSurfaceToVideoplayer() {
        this.mContext.sendBroadcast(new Intent(NOTIFY_SEND_SURFACE));
    }
}
