package com.szchoiceway.customerui.fragment.launcher.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.view.ViewOverlay;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.szchoiceway.customerui.R;

public abstract class BaseLauncherIconView extends FrameLayout {
    /* access modifiers changed from: protected */
    public abstract int getBackgroundImageViewId();

    /* access modifiers changed from: protected */
    public abstract int getBackgroundResId();

    /* access modifiers changed from: protected */
    public abstract int getLayoutId();

    /* access modifiers changed from: protected */
    public abstract String getTile();

    /* access modifiers changed from: protected */
    public void init() {
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

    public BaseLauncherIconView(Context context) {
        super(context);
        initInner();
    }

    public BaseLauncherIconView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        initInner();
    }

    public BaseLauncherIconView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        initInner();
    }

    private void initInner() {
        inflate(getContext(), getLayoutId(), this);
        ImageView imageView = (ImageView) findViewById(R.id.background_iv);
        int backgroundImageViewId = getBackgroundImageViewId();
        if (imageView != null) {
            imageView.setImageResource(getBackgroundResId());
            if (backgroundImageViewId > 0) {
                imageView.setId(backgroundImageViewId);
            }
        }
        TextView textView = (TextView) findViewById(R.id.title_tv);
        if (textView != null) {
            textView.setText(getTile());
        }
        init();
    }
}
