package com.szchoiceway.customerui.drag;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.view.ViewOverlay;
import android.widget.RelativeLayout;

public class DragLayout extends RelativeLayout {
    public /* bridge */ /* synthetic */ ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return super.generateLayoutParams(attributeSet);
    }

    public /* bridge */ /* synthetic */ ViewOverlay getOverlay() {
        return super.getOverlay();
    }

    public DragLayout(Context context) {
        super(context);
    }

    public DragLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public DragLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }
}
