package com.szchoiceway.customerui.fragment.launcher.lexus.default_ui.pager;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewOverlay;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Guideline;
import com.szchoiceway.customerui.fragment.launcher.lexus.default_ui.icon.LexusDefaultBtLauncherIconView;
import com.szchoiceway.customerui.fragment.launcher.lexus.default_ui.icon.LexusDefaultDashBoardLauncherIconView;
import com.szchoiceway.customerui.fragment.launcher.lexus.default_ui.icon.LexusDefaultFileManagerLauncherIconView;
import com.szchoiceway.customerui.fragment.launcher.lexus.default_ui.icon.LexusDefaultMusicLauncherIconView;
import com.szchoiceway.customerui.fragment.launcher.lexus.default_ui.icon.LexusDefaultNaviLauncherIconView;
import com.szchoiceway.customerui.fragment.launcher.lexus.default_ui.icon.LexusDefaultOriginalCarLauncherIconView;
import com.szchoiceway.customerui.fragment.launcher.lexus.default_ui.icon.LexusDefaultSettingsLauncherIconView;
import com.szchoiceway.customerui.fragment.launcher.lexus.default_ui.icon.LexusDefaultVideoLauncherIconView;

public class LexusDefaultPager1Layout extends ConstraintLayout {
    public /* bridge */ /* synthetic */ ViewOverlay getOverlay() {
        return super.getOverlay();
    }

    public LexusDefaultPager1Layout(Context context) {
        super(context);
        init();
    }

    public LexusDefaultPager1Layout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    public LexusDefaultPager1Layout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init();
    }

    private void init() {
        int generateViewId = View.generateViewId();
        int generateViewId2 = View.generateViewId();
        addGuideline(generateViewId, generateViewId2);
        addViews(new LexusDefaultNaviLauncherIconView(getContext()), new LexusDefaultBtLauncherIconView(getContext()), new LexusDefaultMusicLauncherIconView(getContext()), new LexusDefaultDashBoardLauncherIconView(getContext()), generateViewId);
        addViews(new LexusDefaultOriginalCarLauncherIconView(getContext()), new LexusDefaultSettingsLauncherIconView(getContext()), new LexusDefaultVideoLauncherIconView(getContext()), new LexusDefaultFileManagerLauncherIconView(getContext()), generateViewId2);
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

    private void addViews(View view, View view2, View view3, View view4, int i) {
        View[] viewArr = {view, view2, view3, view4};
        int generateViewId = View.generateViewId();
        int generateViewId2 = View.generateViewId();
        int[] iArr = new int[6];
        iArr[0] = generateViewId;
        iArr[5] = generateViewId2;
        int i2 = 0;
        while (i2 < 4) {
            int generateViewId3 = View.generateViewId();
            viewArr[i2].setId(generateViewId3);
            i2++;
            iArr[i2] = generateViewId3;
        }
        initLeftRightGuideline(generateViewId, generateViewId2);
        for (int i3 = 0; i3 < 4; i3++) {
            View view5 = viewArr[i3];
            ConstraintLayout.LayoutParams layoutParams = new ConstraintLayout.LayoutParams(0, 0);
            layoutParams.startToEnd = iArr[i3];
            layoutParams.endToStart = iArr[i3 + 2];
            layoutParams.horizontalChainStyle = 1;
            layoutParams.topToTop = i;
            layoutParams.dimensionRatio = "223:124";
            layoutParams.matchConstraintPercentWidth = 0.174f;
            addView(view5, layoutParams);
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
        layoutParams2.guidePercent = 0.928f;
        Guideline guideline2 = new Guideline(getContext());
        guideline2.setId(i2);
        addView(guideline2, layoutParams2);
    }
}
