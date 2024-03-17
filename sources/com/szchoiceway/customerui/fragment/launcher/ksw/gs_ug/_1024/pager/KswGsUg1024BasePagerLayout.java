package com.szchoiceway.customerui.fragment.launcher.ksw.gs_ug._1024.pager;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewOverlay;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Constraints;

public abstract class KswGsUg1024BasePagerLayout extends ConstraintLayout {
    /* access modifiers changed from: protected */
    public abstract View getView1();

    /* access modifiers changed from: protected */
    public abstract View getView2();

    /* access modifiers changed from: protected */
    public abstract View getView3();

    public /* bridge */ /* synthetic */ ViewOverlay getOverlay() {
        return super.getOverlay();
    }

    public KswGsUg1024BasePagerLayout(Context context) {
        super(context);
        init();
    }

    public KswGsUg1024BasePagerLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    public KswGsUg1024BasePagerLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init();
    }

    private void init() {
        buildLayout(getView1(), getView2(), getView3());
    }

    private void buildLayout(View view, View view2, View view3) {
        int generateViewId = View.generateViewId();
        int generateViewId2 = View.generateViewId();
        int generateViewId3 = View.generateViewId();
        inflateNormalView(view, generateViewId);
        setView1HorizontalId(view, generateViewId2);
        inflateNormalView(view2, generateViewId2);
        setView2HorizontalId(view2, generateViewId, generateViewId3);
        inflateNormalView(view3, generateViewId3);
        setView3HorizontalId(view3, generateViewId2);
    }

    private void inflateNormalView(View view, int i) {
        Constraints.LayoutParams layoutParams = new Constraints.LayoutParams(0, 0);
        layoutParams.matchConstraintPercentWidth = 0.32f;
        layoutParams.dimensionRatio = "278:475";
        layoutParams.topToTop = 0;
        view.setLayoutParams(layoutParams);
        view.setId(i);
        addView(view);
    }

    private void setView1HorizontalId(View view, int i) {
        ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) view.getLayoutParams();
        layoutParams.startToStart = 0;
        layoutParams.endToStart = i;
    }

    private void setView2HorizontalId(View view, int i, int i2) {
        ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) view.getLayoutParams();
        layoutParams.startToEnd = i;
        layoutParams.endToStart = i2;
    }

    private void setView3HorizontalId(View view, int i) {
        ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) view.getLayoutParams();
        layoutParams.startToEnd = i;
        layoutParams.endToEnd = 0;
    }
}
