package com.szchoiceway.customerui.views;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.view.ViewOverlay;
import android.widget.RelativeLayout;

public class CustomerRelativelayout extends RelativeLayout {
    private long mLongClickDelayTime = 500;

    public /* bridge */ /* synthetic */ ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return super.generateLayoutParams(attributeSet);
    }

    public /* bridge */ /* synthetic */ ViewOverlay getOverlay() {
        return super.getOverlay();
    }

    public CustomerRelativelayout(Context context) {
        super(context);
    }

    public CustomerRelativelayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public CustomerRelativelayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public boolean postDelayed(Runnable runnable, long j) {
        if (TextUtils.equals("CheckForLongPress", runnable.getClass().getSimpleName())) {
            j = this.mLongClickDelayTime;
        }
        return super.postDelayed(runnable, j);
    }

    public void setLongClickTime(long j) {
        this.mLongClickDelayTime = j;
    }
}
