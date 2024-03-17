package com.szchoiceway.customerui.fragment;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.szchoiceway.customerui.R;
import com.szchoiceway.customerui.utils.EventUtils;
import com.szchoiceway.customerui.utils.SysProviderOpt;

public class MainTopView {
    private static final String TAG = "MainTopView";
    private ImageView imgMainTopBt;
    private ImageView imgMainTopHD;
    private ImageView imgMainTopSD;
    private ImageView imgMainTopSignal;
    private ImageView imgMainTopUsb;
    private ImageView imgMainTopWifi;
    private Context mContext;
    private View mView;
    private String resolution = "1920x720";
    private TextView tvMainTopSignalType;

    public MainTopView(Context context, View view) {
        this.mContext = context;
        this.mView = view;
        this.resolution = SysProviderOpt.getInstance(context).getRecordValue("KSW_UI_RESOLUTION", "");
        init();
    }

    private void init() {
        View findViewById = this.mView.findViewById(R.id.btnMainTopTask);
        if (findViewById != null) {
            findViewById.setOnClickListener(new View.OnClickListener() {
                public final void onClick(View view) {
                    MainTopView.this.lambda$init$0$MainTopView(view);
                }
            });
            findViewById.setOnLongClickListener(new View.OnLongClickListener() {
                public final boolean onLongClick(View view) {
                    return MainTopView.this.lambda$init$1$MainTopView(view);
                }
            });
        }
        View findViewById2 = this.mView.findViewById(R.id.btnMainTopReturn);
        if (findViewById2 != null) {
            findViewById2.setOnClickListener($$Lambda$MainTopView$AWvScwOiGvlDyJxfD9_VP9DpiXU.INSTANCE);
        }
        this.mView.findViewById(R.id.btnMainTopScreen).setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                MainTopView.this.lambda$init$3$MainTopView(view);
            }
        });
        ImageView imageView = (ImageView) this.mView.findViewById(R.id.imgMainTopHd);
        this.imgMainTopHD = imageView;
        if (imageView != null) {
            imageView.setVisibility("1920x720".equals(this.resolution) ? 0 : 8);
        }
        this.imgMainTopUsb = (ImageView) this.mView.findViewById(R.id.imgMainTopUsb);
        this.imgMainTopWifi = (ImageView) this.mView.findViewById(R.id.imgMainTopWifi);
        this.imgMainTopSD = (ImageView) this.mView.findViewById(R.id.imgMainTopSd);
        this.imgMainTopBt = (ImageView) this.mView.findViewById(R.id.imgMainTopBt);
        this.imgMainTopSignal = (ImageView) this.mView.findViewById(R.id.imgMainTopSignal);
        this.tvMainTopSignalType = (TextView) this.mView.findViewById(R.id.tvMainTopSignalType);
        refreshStorageView();
        Intent intent = new Intent(EventUtils.ZXW_ACTION_MAIN_TOP_REFRESH_VIEW_REQUEST);
        intent.putExtra(EventUtils.ZXW_ACTION_MAIN_TOP_REFRESH_VIEW_REQUEST_KEY, "WIFI");
        Log.d(TAG, "sendBroadcast ZXW_ACTION_MAIN_TOP_REFRESH_VIEW_REQUEST WIFI");
        this.mContext.sendBroadcast(intent);
        Intent intent2 = new Intent(EventUtils.ZXW_ACTION_MAIN_TOP_REFRESH_VIEW_REQUEST);
        intent2.putExtra(EventUtils.ZXW_ACTION_MAIN_TOP_REFRESH_VIEW_REQUEST_KEY, "SIGNAL");
        Log.d(TAG, "sendBroadcast ZXW_ACTION_MAIN_TOP_REFRESH_VIEW_REQUEST SIGNAL");
        this.mContext.sendBroadcast(intent2);
    }

    public /* synthetic */ void lambda$init$0$MainTopView(View view) {
        Intent intent = new Intent(EventUtils.ZXW_ACTION_MAIN_TOP_ON_CLICK);
        intent.putExtra(EventUtils.ZXW_ACTION_MAIN_TOP_ON_CLICK_KEY, "TASK");
        intent.putExtra(EventUtils.ZXW_ACTION_MAIN_TOP_ON_CLICK_DATA, 0);
        this.mContext.sendBroadcast(intent);
    }

    public /* synthetic */ boolean lambda$init$1$MainTopView(View view) {
        Intent intent = new Intent(EventUtils.ZXW_ACTION_MAIN_TOP_ON_CLICK);
        intent.putExtra(EventUtils.ZXW_ACTION_MAIN_TOP_ON_CLICK_KEY, "TASK");
        intent.putExtra(EventUtils.ZXW_ACTION_MAIN_TOP_ON_CLICK_DATA, 1);
        this.mContext.sendBroadcast(intent);
        return true;
    }

    public /* synthetic */ void lambda$init$3$MainTopView(View view) {
        Intent intent = new Intent(EventUtils.ZXW_ACTION_MAIN_TOP_ON_CLICK);
        intent.putExtra(EventUtils.ZXW_ACTION_MAIN_TOP_ON_CLICK_KEY, "SCREEN");
        intent.putExtra(EventUtils.ZXW_ACTION_MAIN_TOP_ON_CLICK_DATA, 0);
        this.mContext.sendBroadcast(intent);
    }

    public void refreshStorageView() {
        int storageType = EventUtils.getStorageType(this.mContext);
        if (storageType == 0) {
            ImageView imageView = this.imgMainTopUsb;
            if (!(imageView == null || imageView.getVisibility() == 8)) {
                this.imgMainTopUsb.setVisibility(8);
            }
            ImageView imageView2 = this.imgMainTopSD;
            if (imageView2 != null && imageView2.getVisibility() != 8) {
                this.imgMainTopSD.setVisibility(8);
            }
        } else if (storageType == 1) {
            ImageView imageView3 = this.imgMainTopUsb;
            if (!(imageView3 == null || imageView3.getVisibility() == 0)) {
                this.imgMainTopUsb.setVisibility(0);
            }
            ImageView imageView4 = this.imgMainTopSD;
            if (imageView4 != null && imageView4.getVisibility() != 8) {
                this.imgMainTopSD.setVisibility(8);
            }
        } else if (storageType == 2) {
            ImageView imageView5 = this.imgMainTopUsb;
            if (!(imageView5 == null || imageView5.getVisibility() == 8)) {
                this.imgMainTopUsb.setVisibility(8);
            }
            ImageView imageView6 = this.imgMainTopSD;
            if (imageView6 != null && imageView6.getVisibility() != 0) {
                this.imgMainTopSD.setVisibility(0);
            }
        } else if (storageType == 3) {
            ImageView imageView7 = this.imgMainTopUsb;
            if (!(imageView7 == null || imageView7.getVisibility() == 0)) {
                this.imgMainTopUsb.setVisibility(0);
            }
            ImageView imageView8 = this.imgMainTopSD;
            if (imageView8 != null && imageView8.getVisibility() != 0) {
                this.imgMainTopSD.setVisibility(0);
            }
        }
    }

    public void refreshBtView(int i) {
        ImageView imageView = this.imgMainTopBt;
        if (imageView != null) {
            imageView.setVisibility(i == 1 ? 0 : 8);
        }
    }

    public void refreshWifiView(int i) {
        Log.d(TAG, "refreshWifiView wifiLevel = " + i);
        ImageView imageView = this.imgMainTopWifi;
        if (imageView == null) {
            return;
        }
        if (i > 0) {
            if (imageView.getVisibility() != 0) {
                this.imgMainTopWifi.setVisibility(0);
            }
            this.imgMainTopWifi.setImageLevel(i);
        } else if (imageView.getVisibility() != 8) {
            this.imgMainTopWifi.setVisibility(8);
        }
    }

    public void refreshSignalView(int i, String str) {
        Log.d(TAG, "refreshSignalView level = " + i + ", type = " + str);
        if (this.imgMainTopSignal != null) {
            if (i > 0 || !"".equals(str)) {
                if (this.imgMainTopSignal.getVisibility() != 0) {
                    this.imgMainTopSignal.setVisibility(0);
                }
                this.imgMainTopSignal.setImageLevel(i);
            } else if (this.imgMainTopSignal.getVisibility() != 8) {
                this.imgMainTopSignal.setVisibility(8);
            }
        }
        if (this.tvMainTopSignalType == null) {
            return;
        }
        if (i > 0 || !"".equals(str)) {
            if (this.tvMainTopSignalType.getVisibility() != 0) {
                this.tvMainTopSignalType.setVisibility(0);
            }
            this.tvMainTopSignalType.setText(str);
        } else if (this.tvMainTopSignalType.getVisibility() != 8) {
            this.tvMainTopSignalType.setVisibility(8);
        }
    }
}
