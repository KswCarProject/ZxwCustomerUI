package com.szchoiceway.customerui.fragment.launcher.audi.gt6.view;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOverlay;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.szchoiceway.customerui.kt_like.JavaPair;
import com.szchoiceway.zxwlib.GyroScopeWithCompassView;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.jvm.functions.Function0;

public abstract class BaseAudiGT6LauncherIconView extends FrameLayout {
    private Lazy<DisplayMetrics> dm = LazyKt.lazy(new Function0() {
        public final Object invoke() {
            return BaseAudiGT6LauncherIconView.this.lambda$new$0$BaseAudiGT6LauncherIconView();
        }
    });
    private View iconTextFrameView;
    private TextView iconTextView;
    private View iconView;

    /* access modifiers changed from: protected */
    public abstract int getIconTextFrameViewId();

    /* access modifiers changed from: protected */
    public abstract int getIconTextViewId();

    /* access modifiers changed from: protected */
    public abstract int getIconViewId();

    /* access modifiers changed from: protected */
    public int getImageHeight() {
        return 180;
    }

    /* access modifiers changed from: protected */
    public int getImageWidth() {
        return GyroScopeWithCompassView.CARTYPE_MACAN_HI;
    }

    /* access modifiers changed from: protected */
    public abstract int getLayoutId();

    /* access modifiers changed from: protected */
    public abstract int getTextMarginStart();

    /* access modifiers changed from: protected */
    public abstract int getTextMarginTop();

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

    public /* synthetic */ DisplayMetrics lambda$new$0$BaseAudiGT6LauncherIconView() {
        return getContext().getResources().getDisplayMetrics();
    }

    public BaseAudiGT6LauncherIconView(Context context) {
        super(context);
        init();
    }

    public BaseAudiGT6LauncherIconView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    public BaseAudiGT6LauncherIconView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init();
    }

    /* access modifiers changed from: protected */
    public void init() {
        final View inflate = inflate(getContext(), getLayoutId(), this);
        inflate.addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() {
            public void onViewDetachedFromWindow(View view) {
            }

            public void onViewAttachedToWindow(View view) {
                JavaPair<Integer, Integer> viewSize = BaseAudiGT6LauncherIconView.this.getViewSize();
                if (!(((Integer) viewSize.first).intValue() == -1 && ((Integer) viewSize.second).intValue() == -1)) {
                    ViewGroup.LayoutParams layoutParams = inflate.getLayoutParams();
                    if (((Integer) viewSize.first).intValue() != -1) {
                        layoutParams.width = BaseAudiGT6LauncherIconView.this.getRealWidthSize(((Integer) viewSize.first).intValue());
                    }
                    if (((Integer) viewSize.second).intValue() != -1) {
                        layoutParams.height = BaseAudiGT6LauncherIconView.this.getRealHeightSize(((Integer) viewSize.second).intValue());
                    }
                }
                inflate.removeOnAttachStateChangeListener(this);
            }
        });
        this.iconView = inflate.findViewById(getIconViewId());
        this.iconTextView = (TextView) inflate.findViewById(getIconTextViewId());
        this.iconTextFrameView = inflate.findViewById(getIconTextFrameViewId());
        ViewGroup.LayoutParams layoutParams = this.iconView.getLayoutParams();
        layoutParams.width = getRealWidthSize(getImageWidth());
        layoutParams.height = getRealHeightSize(getImageHeight());
        int textMarginTop = getTextMarginTop();
        if (textMarginTop != 0) {
            if (textMarginTop < 0) {
                setPadding(getPaddingLeft(), getRealHeightSize(textMarginTop), getPaddingRight(), getPaddingBottom());
            } else {
                setMarginTop(this.iconTextView, textMarginTop);
            }
        }
        int textMarginStart = getTextMarginStart();
        if (textMarginStart != 0) {
            if (textMarginStart < 0) {
                setPadding(getRealWidthSize(textMarginStart), getPaddingTop(), getPaddingRight(), getPaddingBottom());
            } else {
                setMarginStart(this.iconTextView, textMarginStart);
            }
        }
        getIconTextView().setEllipsize(TextUtils.TruncateAt.END);
        getIconTextView().setSingleLine(true);
        getIconTextView().setMaxWidth(getRealWidthSize(120));
    }

    public final View getIconView() {
        return this.iconView;
    }

    public final TextView getIconTextView() {
        return this.iconTextView;
    }

    public final View getIconTextFrameView() {
        return this.iconTextFrameView;
    }

    public final int getRealWidthSize(int i) {
        return (int) (((((float) i) * 1.0f) / 1920.0f) * ((float) this.dm.getValue().widthPixels));
    }

    public final int getRealHeightSize(int i) {
        return (int) (((((float) i) * 1.0f) / 720.0f) * ((float) this.dm.getValue().heightPixels));
    }

    public final void setMarginStart(View view, int i) {
        ((ViewGroup.MarginLayoutParams) view.getLayoutParams()).setMarginStart(getRealWidthSize(i));
    }

    public final void setMarginTop(View view, int i) {
        ((ViewGroup.MarginLayoutParams) view.getLayoutParams()).topMargin = getRealHeightSize(i);
    }

    /* access modifiers changed from: protected */
    public JavaPair<Integer, Integer> getViewSize() {
        return new JavaPair<>(-1, -1);
    }
}
