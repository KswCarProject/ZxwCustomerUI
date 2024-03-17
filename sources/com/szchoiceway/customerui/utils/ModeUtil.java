package com.szchoiceway.customerui.utils;

import android.content.Context;
import android.widget.TextView;
import com.szchoiceway.customerui.R;
import com.szchoiceway.zxwlib.GyroScopeWithCompassView;

public class ModeUtil {
    private static final String TAG = "StatusBarView";
    private Context mContext;

    public ModeUtil(Context context) {
        this.mContext = context;
    }

    public void refreshMode(TextView textView, int i) {
        if (textView != null) {
            if (i == 6) {
                textView.setText(this.mContext.getResources().getString(R.string.lb_left_bt));
            } else if (i == 7) {
                textView.setText(this.mContext.getResources().getString(R.string.lb_btmusic));
            } else if (i == 10) {
                textView.setText(this.mContext.getResources().getString(R.string.lb_video));
            } else if (i == 11) {
                textView.setText(this.mContext.getResources().getString(R.string.lb_music));
            } else if (i == 43) {
                textView.setText(this.mContext.getResources().getString(R.string.lb_home));
            } else if (i == 45) {
                textView.setText(this.mContext.getResources().getString(R.string.lb_dash_board));
            } else if (i != 49) {
                switch (i) {
                    case 200:
                        textView.setText(this.mContext.getResources().getString(R.string.lb_fatset));
                        return;
                    case GyroScopeWithCompassView.CARTYPE_Polo2016_LO /*201*/:
                        textView.setText(this.mContext.getResources().getString(R.string.lb_feedback));
                        return;
                    case GyroScopeWithCompassView.CARTYPE_Polo2016_HI /*202*/:
                        textView.setText(this.mContext.getResources().getString(R.string.lb_set_nav));
                        return;
                    case GyroScopeWithCompassView.CARTYPE_DAQIE2013_LO /*203*/:
                        textView.setText(this.mContext.getResources().getString(R.string.lb_set_lan));
                        return;
                    case GyroScopeWithCompassView.CARTYPE_DAQIE2013_HI /*204*/:
                        textView.setText(this.mContext.getResources().getString(R.string.lb_set_time));
                        return;
                    case GyroScopeWithCompassView.CARTYPE_MALIBU_H_H /*205*/:
                        textView.setText(this.mContext.getResources().getString(R.string.lb_set_volume));
                        return;
                    case GyroScopeWithCompassView.CARTYPE_Kaluola_OLD_LO /*206*/:
                        textView.setText(this.mContext.getResources().getString(R.string.lb_set_eq));
                        return;
                    case GyroScopeWithCompassView.CARTYPE_Kaluola_OLD_HI /*207*/:
                        textView.setText(this.mContext.getResources().getString(R.string.lb_set_system));
                        return;
                    case GyroScopeWithCompassView.CARTYPE_JIANGLING_S330_LO /*208*/:
                        textView.setText(this.mContext.getResources().getString(R.string.lb_set_factory));
                        return;
                    case GyroScopeWithCompassView.CARTYPE_JIANGLING_S330_HI /*209*/:
                        textView.setText(this.mContext.getResources().getString(R.string.lb_set_system_info));
                        return;
                    case GyroScopeWithCompassView.CARTYPE_DAOQI_GOMGYANG_LO /*210*/:
                        textView.setText(this.mContext.getResources().getString(R.string.lb_bt_dial));
                        return;
                    case GyroScopeWithCompassView.CARTYPE_DAOQI_GONGYANG_HI /*211*/:
                        textView.setText(this.mContext.getResources().getString(R.string.lb_bt_call_record));
                        return;
                    case GyroScopeWithCompassView.CARTYPE_KOLEOS_LO_LO /*212*/:
                        textView.setText(this.mContext.getResources().getString(R.string.lb_bt_phone_number));
                        return;
                    case GyroScopeWithCompassView.CARTYPE_HavalH6_Coolpe /*213*/:
                        textView.setText(this.mContext.getResources().getString(R.string.lb_bt_set));
                        return;
                    case GyroScopeWithCompassView.CARTYPE_EDGE_2013_HI /*214*/:
                        textView.setText(this.mContext.getResources().getString(R.string.lb_bt_music));
                        return;
                    case GyroScopeWithCompassView.CARTYPE_EDGE_2013_LO /*215*/:
                        textView.setText(this.mContext.getResources().getString(R.string.lb_care_mode));
                        return;
                    case GyroScopeWithCompassView.CARTYPE_XIANDAI_IX25 /*216*/:
                        textView.setText(this.mContext.getResources().getString(R.string.lb_edit_mode));
                        return;
                    default:
                        textView.setText("");
                        return;
                }
            } else {
                textView.setText(this.mContext.getResources().getString(R.string.lb_settings));
            }
        }
    }
}
