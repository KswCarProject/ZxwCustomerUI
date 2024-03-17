package com.szchoiceway.customerui.views;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.RadioButton;

public class BentleyButton extends RadioButton {
    private static final String TAG = "BentleyButton";
    private boolean hasSetAlpha = false;
    private boolean isPressed = false;
    private boolean isSelected = false;

    public BentleyButton(Context context) {
        super(context);
        init(context);
    }

    public BentleyButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context);
    }

    public BentleyButton(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(context);
    }

    private void init(Context context) {
        setButtonDrawable((Drawable) null);
    }

    public void setSelected(boolean z) {
        super.setSelected(z);
        this.isSelected = z;
        if (this.isPressed) {
            restoreAlpha();
        } else if (z) {
            setAlpha();
        } else {
            restoreAlpha();
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (action == 0) {
            this.isPressed = true;
            restoreAlpha();
        } else if (action == 1 || action == 3) {
            this.isPressed = false;
            if (this.isSelected) {
                setAlpha();
            } else {
                restoreAlpha();
            }
        }
        return super.onTouchEvent(motionEvent);
    }

    public void setAlpha() {
        if (!this.hasSetAlpha) {
            this.hasSetAlpha = true;
            setAlpha(0.8f);
        }
    }

    public void restoreAlpha() {
        if (this.hasSetAlpha) {
            this.hasSetAlpha = false;
            setAlpha(1.0f);
        }
    }

    public boolean isHasSetAlpha() {
        return this.hasSetAlpha;
    }
}
