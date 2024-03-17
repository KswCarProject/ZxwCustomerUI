package com.szchoiceway.customerui.views;

import android.content.Context;
import android.os.Build;
import android.view.View;
import android.widget.RelativeLayout;
import com.szchoiceway.customerui.R;
import com.szchoiceway.customerui.utils.EventUtils;
import com.szchoiceway.zxwlib.bean.Customer;

public class StatusBarUtil {
    private static final String TAG = "StatusBarUtil";
    private Context mContext;
    private int m_iModeSet;
    private int m_iUIIndex;
    private String resolution;
    private StatusBarView statusBarView;

    public StatusBarUtil(StatusBarView statusBarView2, Context context, String str, int i, int i2) {
        this.statusBarView = statusBarView2;
        this.mContext = context;
        this.resolution = str;
        this.m_iUIIndex = i;
        this.m_iModeSet = i2;
    }

    public View getView() {
        View view;
        int i;
        int i2 = this.m_iUIIndex;
        if (i2 == 3) {
            if ("1280x480".equals(this.resolution)) {
                view = View.inflate(this.mContext, R.layout.layout_status_bar_ksw_1280x480_pemp_id7, this.statusBarView);
            } else {
                view = View.inflate(this.mContext, R.layout.layout_status_bar_ksw_1920x720_pemp_id7, this.statusBarView);
            }
        } else if (i2 == 2) {
            view = View.inflate(this.mContext, R.layout.layout_status_bar_ksw_1920x720_bmw_id8, this.statusBarView);
        } else if (i2 == 6) {
            if ("1024x600".equals(this.resolution)) {
                view = View.inflate(this.mContext, R.layout.small_layout_status_bar_ksw_1024x600_benz, this.statusBarView);
            } else if ("1280x720".equals(this.resolution)) {
                view = View.inflate(this.mContext, R.layout.small_layout_status_bar_ksw_1280x720_benz, this.statusBarView);
            } else if ("1280x480".equals(this.resolution)) {
                view = View.inflate(this.mContext, R.layout.layout_status_bar_ksw_1280x480_benz, this.statusBarView);
            } else {
                view = View.inflate(this.mContext, R.layout.layout_status_bar_ksw_1920x720_benz, this.statusBarView);
            }
        } else if (i2 == 4) {
            int i3 = this.m_iModeSet;
            if (i3 == 34) {
                view = View.inflate(this.mContext, R.layout.layout_status_bar_ksw_gs_ug, this.statusBarView);
            } else if (i3 == 35) {
                view = View.inflate(this.mContext, R.layout.layout_status_bar_ksw_gs_ug_1024, this.statusBarView);
            } else if ("1560x700".equals(this.resolution)) {
                view = View.inflate(this.mContext, R.layout.layout_status_bar_ksw_1560x700_audi, this.statusBarView);
            } else if ("1280x480".equals(this.resolution)) {
                view = View.inflate(this.mContext, R.layout.layout_status_bar_ksw_1280x480_audi, this.statusBarView);
            } else if (Customer.isSmallResolution(this.mContext)) {
                view = View.inflate(this.mContext, R.layout.small_layout_status_bar_ksw_1024x600_benz, this.statusBarView);
            } else {
                view = View.inflate(this.mContext, R.layout.layout_status_bar_ksw_1920x720_audi, this.statusBarView);
            }
        } else if (i2 == 5) {
            view = View.inflate(this.mContext, R.layout.layout_status_bar_ksw_landrover, this.statusBarView);
        } else if ("1280x480".equals(this.resolution)) {
            view = View.inflate(this.mContext, R.layout.layout_status_bar_ksw_1280x480, this.statusBarView);
        } else {
            view = View.inflate(this.mContext, R.layout.layout_status_bar_ksw_1920x720, this.statusBarView);
        }
        if (!(!"GT7-CAR".equals(Build.MODEL) || (i = this.m_iUIIndex) == 2 || i == 5)) {
            adjustingLayout(view);
        }
        return view;
    }

    private void adjustingLayout(View view) {
        if (view != null && this.m_iModeSet != 34) {
            View findViewById = view.findViewById(R.id.btnHome);
            if (findViewById != null) {
                RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) findViewById.getLayoutParams();
                layoutParams.topMargin = EventUtils.getPx(this.mContext, 12);
                findViewById.setLayoutParams(layoutParams);
            }
            View findViewById2 = view.findViewById(R.id.iconSD);
            if (findViewById2 != null) {
                findViewById2.setLayoutParams((RelativeLayout.LayoutParams) findViewById2.getLayoutParams());
            }
        }
    }
}
