package com.szchoiceway.customerui.fragment.launcher.ksw.mbux_1024.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;
import com.szchoiceway.customerui.R;

public class KswMbux1024BtLauncherIonView extends KswMbux1024BaseLauncherIconView {
    /* access modifiers changed from: protected */
    public int getBackgroundImageViewId() {
        return R.id.btnBt;
    }

    /* access modifiers changed from: protected */
    public int getBackgroundResId() {
        return R.drawable.ksw_mbux_1024x600_main_phone;
    }

    /* access modifiers changed from: protected */
    public int getSubtitleId() {
        return R.id.txtBTMessage;
    }

    public KswMbux1024BtLauncherIonView(Context context) {
        super(context);
    }

    public KswMbux1024BtLauncherIonView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public KswMbux1024BtLauncherIonView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    /* access modifiers changed from: protected */
    public String getTitle() {
        return getContext().getString(R.string.lb_bt_phone);
    }

    /* access modifiers changed from: protected */
    public String getSubtitle() {
        return getContext().getString(R.string.lb_bt_message);
    }

    public TextView getSubtitleView() {
        return (TextView) findViewById(getSubtitleId());
    }
}
