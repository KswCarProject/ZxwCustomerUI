package com.szchoiceway.customerui.views;

import android.content.Context;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import com.szchoiceway.customerui.R;

public class SettingImageButton extends MyImageButton {
    public SettingImageButton(Context context) {
        super(context);
    }

    public SettingImageButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public SettingImageButton(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public void init() {
        super.init();
        int applyDimension = (int) TypedValue.applyDimension(1, 115.0f, getContext().getResources().getDisplayMetrics());
        int dimension = (int) getContext().getResources().getDimension(R.dimen.small_benz_ntg6_icon_height);
        if (this.themeNum == 2) {
            applyDimension = (int) TypedValue.applyDimension(1, 147.0f, getContext().getResources().getDisplayMetrics());
        }
        ViewGroup.LayoutParams layoutParams = getLayoutParams();
        if (layoutParams == null) {
            layoutParams = new RelativeLayout.LayoutParams(applyDimension, dimension);
        }
        layoutParams.width = applyDimension;
        setLayoutParams(layoutParams);
    }
}
