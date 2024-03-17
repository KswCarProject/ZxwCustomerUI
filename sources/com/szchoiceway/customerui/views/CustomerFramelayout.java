package com.szchoiceway.customerui.views;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOverlay;
import android.widget.FrameLayout;

public class CustomerFramelayout extends FrameLayout {
    LayoutVisibilityChangedListener layoutVisibilityChangedListener;

    public interface LayoutVisibilityChangedListener {
        void onVisibilityChanged(int i);
    }

    /* access modifiers changed from: protected */
    public /* bridge */ /* synthetic */ ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return super.generateDefaultLayoutParams();
    }

    public /* bridge */ /* synthetic */ ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return super.generateLayoutParams(attributeSet);
    }

    public /* bridge */ /* synthetic */ ViewOverlay getOverlay() {
        return super.getOverlay();
    }

    public CustomerFramelayout(Context context) {
        super(context);
    }

    public CustomerFramelayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    /* access modifiers changed from: protected */
    public void onVisibilityChanged(View view, int i) {
        super.onVisibilityChanged(view, i);
        Log.i("TAG", "onVisibilityChanged:gegwgwegew== " + i);
        LayoutVisibilityChangedListener layoutVisibilityChangedListener2 = this.layoutVisibilityChangedListener;
        if (layoutVisibilityChangedListener2 != null) {
            layoutVisibilityChangedListener2.onVisibilityChanged(i);
        }
    }

    public void setLayoutVisibilityChangedListener(LayoutVisibilityChangedListener layoutVisibilityChangedListener2) {
        this.layoutVisibilityChangedListener = layoutVisibilityChangedListener2;
    }
}
