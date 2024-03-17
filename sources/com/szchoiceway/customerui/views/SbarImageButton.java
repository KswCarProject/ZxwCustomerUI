package com.szchoiceway.customerui.views;

import android.content.Context;
import android.content.res.ColorStateList;
import android.util.AttributeSet;
import android.widget.ImageButton;

public class SbarImageButton extends ImageButton {
    private static final String TAG = "SbarImageButton";
    private OnSetImageTintListener mListener = null;
    private OnSetImageTintListener mListener2 = null;

    public interface OnSetImageTintListener {
        void setImageTintList(ColorStateList colorStateList);

        void setImageTintList2(ColorStateList colorStateList);
    }

    public SbarImageButton(Context context) {
        super(context);
    }

    public SbarImageButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public SbarImageButton(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public SbarImageButton(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
    }

    public void setImageTintListListener(OnSetImageTintListener onSetImageTintListener) {
        this.mListener = onSetImageTintListener;
    }

    public void setImageTintListListener2(OnSetImageTintListener onSetImageTintListener) {
        this.mListener2 = onSetImageTintListener;
    }

    public void setImageTintList(ColorStateList colorStateList) {
        super.setImageTintList(colorStateList);
        OnSetImageTintListener onSetImageTintListener = this.mListener;
        if (onSetImageTintListener != null) {
            onSetImageTintListener.setImageTintList(colorStateList);
        }
        OnSetImageTintListener onSetImageTintListener2 = this.mListener2;
        if (onSetImageTintListener2 != null) {
            onSetImageTintListener2.setImageTintList2(colorStateList);
        }
    }
}
