package com.szchoiceway.customerui.fragment.launcher.lexus.default_ui.pager;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewOverlay;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Guideline;
import com.szchoiceway.customerui.fragment.launcher.lexus.default_ui.icon.LexusDefaultAirLauncherIconView;
import com.szchoiceway.customerui.fragment.launcher.lexus.default_ui.icon.LexusDefaultAppLauncherIconView;
import com.szchoiceway.customerui.fragment.launcher.lexus.default_ui.icon.LexusDefaultCarPlayLauncherIconView;
import com.szchoiceway.customerui.fragment.launcher.lexus.default_ui.icon.LexusDefaultDVRLauncherIconView;

public class LexusDefaultPager2Layout extends ConstraintLayout {
    public /* bridge */ /* synthetic */ ViewOverlay getOverlay() {
        return super.getOverlay();
    }

    public LexusDefaultPager2Layout(Context context) {
        super(context);
        init();
    }

    public LexusDefaultPager2Layout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    public LexusDefaultPager2Layout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init();
    }

    private void init() {
        int generateViewId = View.generateViewId();
        int generateViewId2 = View.generateViewId();
        addGuideline(generateViewId, generateViewId2);
        LexusDefaultDVRLauncherIconView lexusDefaultDVRLauncherIconView = new LexusDefaultDVRLauncherIconView(getContext());
        LexusDefaultAirLauncherIconView lexusDefaultAirLauncherIconView = new LexusDefaultAirLauncherIconView(getContext());
        LexusDefaultCarPlayLauncherIconView lexusDefaultCarPlayLauncherIconView = new LexusDefaultCarPlayLauncherIconView(getContext());
        addViews(lexusDefaultDVRLauncherIconView, new LexusDefaultAppLauncherIconView(getContext()), generateViewId);
        addViews(lexusDefaultCarPlayLauncherIconView, lexusDefaultAirLauncherIconView, generateViewId2);
    }

    private void addGuideline(int i, int i2) {
        ConstraintLayout.LayoutParams layoutParams = new ConstraintLayout.LayoutParams(0, 0);
        layoutParams.orientation = 0;
        layoutParams.guidePercent = 0.222f;
        Guideline guideline = new Guideline(getContext());
        guideline.setId(i);
        addView(guideline, layoutParams);
        ConstraintLayout.LayoutParams layoutParams2 = new ConstraintLayout.LayoutParams(0, 0);
        layoutParams2.orientation = 0;
        layoutParams2.guidePercent = 0.643f;
        Guideline guideline2 = new Guideline(getContext());
        guideline2.setId(i2);
        addView(guideline2, layoutParams2);
    }

    private void addViews(View view, View view2, int i) {
        View[] viewArr = {view, view2};
        if (view2 == null) {
            viewArr = new View[]{view};
        }
        int generateViewId = View.generateViewId();
        int generateViewId2 = View.generateViewId();
        int[] iArr = {generateViewId, generateViewId2};
        for (View id : viewArr) {
            id.setId(View.generateViewId());
        }
        initLeftRightGuideline(generateViewId, generateViewId2);
        for (int i2 = 0; i2 < viewArr.length; i2++) {
            View view3 = viewArr[i2];
            ConstraintLayout.LayoutParams layoutParams = new ConstraintLayout.LayoutParams(0, 0);
            layoutParams.startToStart = iArr[i2];
            layoutParams.topToTop = i;
            layoutParams.dimensionRatio = "223:124";
            layoutParams.matchConstraintPercentWidth = 0.174f;
            addView(view3, layoutParams);
        }
    }

    private void initLeftRightGuideline(int i, int i2) {
        ConstraintLayout.LayoutParams layoutParams = new ConstraintLayout.LayoutParams(0, 0);
        layoutParams.orientation = 1;
        layoutParams.guidePercent = 0.071f;
        Guideline guideline = new Guideline(getContext());
        guideline.setId(i);
        addView(guideline, layoutParams);
        ConstraintLayout.LayoutParams layoutParams2 = new ConstraintLayout.LayoutParams(0, 0);
        layoutParams2.orientation = 1;
        layoutParams2.guidePercent = 0.299f;
        Guideline guideline2 = new Guideline(getContext());
        guideline2.setId(i2);
        addView(guideline2, layoutParams2);
    }
}
