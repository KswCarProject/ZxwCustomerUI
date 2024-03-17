package com.szchoiceway.customerui.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewOverlay;
import androidx.viewpager.widget.ViewPager;

public class MyViewPager extends ViewPager {
    private boolean scrollable = true;

    public /* bridge */ /* synthetic */ ViewOverlay getOverlay() {
        return super.getOverlay();
    }

    public MyViewPager(Context context) {
        super(context);
    }

    public MyViewPager(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void setScrollAble(boolean z) {
        this.scrollable = z;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (!this.scrollable) {
            return false;
        }
        return super.onTouchEvent(motionEvent);
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (this.scrollable) {
            return super.onInterceptTouchEvent(motionEvent);
        }
        getParent().requestDisallowInterceptTouchEvent(false);
        return false;
    }
}
