package com.szchoiceway.customerui.bmw.ui;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.szchoiceway.customerui.R;
import com.szchoiceway.customerui.utils.SysProviderOpt;

public class ModeSelectPage implements View.OnClickListener {
    private static final String TAG = "ModeSelectPage";
    Context mContext;
    DisplayModeChangeListener mDisplayModeChangeListener;
    View mImgLine1;
    View mImgLine2;
    View mImgLine3;
    View mItemView;
    int mMode = 0;
    SysProviderOpt mProvider;
    TextView mTextModeChange;
    TextView mTip1;
    TextView mTip2;
    TextView mTip3;
    TextView mTitle1;
    TextView mTitle2;
    TextView mTitle3;

    interface DisplayModeChangeListener {
        void onModeChange(int i, int i2);
    }

    public View getSetView(Context context) {
        this.mContext = context;
        SysProviderOpt instance = SysProviderOpt.getInstance(context);
        this.mProvider = instance;
        int recordInteger = instance.getRecordInteger("KESAIWEI_SYS_MODE_SELECTION", 17);
        Log.d(TAG, "m_iModeSet = " + recordInteger);
        if (recordInteger == 20) {
            this.mItemView = LayoutInflater.from(context).inflate(R.layout.pemp_bmw_id8_mode_select_page, (ViewGroup) null);
        } else {
            this.mItemView = LayoutInflater.from(context).inflate(R.layout.bmw_id8_mode_select_page, (ViewGroup) null);
        }
        init();
        return this.mItemView;
    }

    private void init() {
        int[] iArr = {R.id.btnModeSport, R.id.btnModePersonal, R.id.btnModeEfficient};
        for (int i = 0; i < 3; i++) {
            View findViewById = this.mItemView.findViewById(iArr[i]);
            if (findViewById != null) {
                findViewById.setOnClickListener(this);
            }
        }
        this.mTextModeChange = (TextView) this.mItemView.findViewById(R.id.textModeChange);
        this.mTip1 = (TextView) this.mItemView.findViewById(R.id.tip1);
        this.mTip2 = (TextView) this.mItemView.findViewById(R.id.tip2);
        this.mTip3 = (TextView) this.mItemView.findViewById(R.id.tip3);
        this.mTitle1 = (TextView) this.mItemView.findViewById(R.id.title1);
        this.mTitle2 = (TextView) this.mItemView.findViewById(R.id.title2);
        this.mTitle3 = (TextView) this.mItemView.findViewById(R.id.title3);
        this.mImgLine1 = this.mItemView.findViewById(R.id.imgLine1);
        this.mImgLine2 = this.mItemView.findViewById(R.id.imgLine2);
        this.mImgLine3 = this.mItemView.findViewById(R.id.imgLine3);
        refresh();
    }

    public void onClick(View view) {
        int i = 0;
        switch (view.getId()) {
            case R.id.btnModePersonal:
                i = 2;
                break;
            case R.id.btnModeSport:
                i = 1;
                break;
        }
        if (this.mMode != i) {
            SysProviderOpt sysProviderOpt = this.mProvider;
            if (sysProviderOpt != null) {
                sysProviderOpt.updateRecord("KESAIWEI_SYS_DISPLAY_MODE", i + "");
            }
            if (this.mContext != null) {
                Intent intent = new Intent();
                intent.setAction("KESAIWEI_DISPLAY_MODE_CHANGED_ACTION");
                Log.d(TAG, "mode = " + i);
                this.mContext.sendBroadcast(intent);
            }
        }
        if (this.mDisplayModeChangeListener != null) {
            Log.i("TAG", "onClick:updateInfo " + i + " | " + this.mMode);
            this.mDisplayModeChangeListener.onModeChange(i, this.mMode);
        }
        if (i != this.mMode) {
            refresh();
            this.mMode = i;
        }
    }

    public void refresh() {
        SysProviderOpt sysProviderOpt = this.mProvider;
        if (sysProviderOpt != null && this.mContext != null) {
            this.mMode = sysProviderOpt.getRecordInteger("KESAIWEI_SYS_DISPLAY_MODE", 0);
            Log.i("TAG", "refresh: " + this.mMode);
            if (this.mTextModeChange != null) {
                this.mTextModeChange.setText(this.mContext.getString(R.string.lb_change_modus).toUpperCase());
            }
            if (this.mTitle1 != null) {
                this.mTitle1.setText(this.mContext.getString(R.string.lb_personal).toUpperCase());
            }
            if (this.mTitle2 != null) {
                this.mTitle2.setText(this.mContext.getString(R.string.lb_sport).toUpperCase());
            }
            if (this.mTitle3 != null) {
                this.mTitle3.setText(this.mContext.getString(R.string.lb_efficient).toUpperCase());
            }
            TextView textView = this.mTip1;
            if (textView != null) {
                textView.setVisibility(8);
            }
            TextView textView2 = this.mTip2;
            if (textView2 != null) {
                textView2.setVisibility(8);
            }
            TextView textView3 = this.mTip3;
            if (textView3 != null) {
                textView3.setVisibility(8);
            }
            View view = this.mImgLine1;
            if (view != null) {
                view.setVisibility(4);
            }
            View view2 = this.mImgLine2;
            if (view2 != null) {
                view2.setVisibility(4);
            }
            View view3 = this.mImgLine3;
            if (view3 != null) {
                view3.setVisibility(4);
            }
            int i = this.mMode;
            if (i == 0) {
                TextView textView4 = this.mTextModeChange;
                if (textView4 != null) {
                    textView4.setTextColor(this.mContext.getResources().getColor(R.color.bmw_id8_mainpage_item_title_color, (Resources.Theme) null));
                }
                TextView textView5 = this.mTip3;
                if (textView5 != null) {
                    textView5.setVisibility(0);
                }
                View view4 = this.mImgLine3;
                if (view4 != null) {
                    view4.setVisibility(0);
                }
            } else if (i == 1) {
                TextView textView6 = this.mTextModeChange;
                if (textView6 != null) {
                    textView6.setTextColor(this.mContext.getResources().getColor(R.color.bmw_id8_mainpage_item_title_color_sport, (Resources.Theme) null));
                }
                TextView textView7 = this.mTip2;
                if (textView7 != null) {
                    textView7.setVisibility(0);
                }
                View view5 = this.mImgLine2;
                if (view5 != null) {
                    view5.setVisibility(0);
                }
            } else if (i == 2) {
                TextView textView8 = this.mTextModeChange;
                if (textView8 != null) {
                    textView8.setTextColor(this.mContext.getResources().getColor(R.color.bmw_id8_mainpage_item_title_color_personal, (Resources.Theme) null));
                }
                TextView textView9 = this.mTip1;
                if (textView9 != null) {
                    textView9.setVisibility(0);
                }
                View view6 = this.mImgLine1;
                if (view6 != null) {
                    view6.setVisibility(0);
                }
            }
        }
    }

    public void setDisplayModeChangeListener(DisplayModeChangeListener displayModeChangeListener) {
        this.mDisplayModeChangeListener = displayModeChangeListener;
    }
}
