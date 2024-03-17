package com.szchoiceway.customerui.fragment.launcher.ksw.mbux_1024.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.view.ViewOverlay;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.szchoiceway.customerui.R;

public abstract class KswMbux1024BaseLauncherIconView extends FrameLayout {
    private static final int DEFAULT_LAYOUT_ID = 1879834770;
    protected TextView subtitleTv;

    /* access modifiers changed from: protected */
    public abstract int getBackgroundImageViewId();

    /* access modifiers changed from: protected */
    public abstract int getBackgroundResId();

    /* access modifiers changed from: protected */
    public int getLayoutId() {
        return R.layout.ksw_mbux_1024x600_item_launcher;
    }

    /* access modifiers changed from: protected */
    public abstract String getSubtitle();

    /* access modifiers changed from: protected */
    public int getSubtitleId() {
        return R.id.subtitle_tv;
    }

    /* access modifiers changed from: protected */
    public abstract String getTitle();

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

    public KswMbux1024BaseLauncherIconView(Context context) {
        super(context);
        init();
    }

    public KswMbux1024BaseLauncherIconView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    public KswMbux1024BaseLauncherIconView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init();
    }

    private void init() {
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
            textView.setText(getTitle());
        }
        TextView textView2 = (TextView) findViewById(R.id.subtitle_tv);
        this.subtitleTv = textView2;
        if (textView2 != null) {
            textView2.setText(getSubtitle());
        }
        int subtitleId = getSubtitleId();
        if (subtitleId != R.id.subtitle_tv && subtitleId != -1) {
            this.subtitleTv.setId(subtitleId);
        }
    }
}
