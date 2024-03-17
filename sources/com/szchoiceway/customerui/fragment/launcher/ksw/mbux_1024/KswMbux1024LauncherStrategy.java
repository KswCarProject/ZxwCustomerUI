package com.szchoiceway.customerui.fragment.launcher.ksw.mbux_1024;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.szchoiceway.customerui.R;
import com.szchoiceway.customerui.fragment.launcher.ksw.mbux_1024.view.KswMbux1024BtLauncherIonView;
import com.szchoiceway.customerui.fragment.launcher.ksw.mbux_1024.view.KswMbux1024MusicLauncherIconView;
import com.szchoiceway.customerui.fragment.launcher.strategy.BaseLauncherStrategy;
import com.szchoiceway.customerui.kt_like.ListUtil;
import com.szchoiceway.customerui.views.MyViewPager;
import java.util.ArrayList;

public class KswMbux1024LauncherStrategy extends BaseLauncherStrategy {
    private static final int LAYOUT_ID = 1879834932;
    private static final int PAGER1_LAYOUT_ID = 1879834771;
    private static final int PAGER2_LAYOUT_ID = 1879834772;
    private static final int PAGER3_LAYOUT_ID = 1879834773;
    private View appView;
    private KswMbux1024BtLauncherIonView btRootView;
    private View btView;
    private View carAutoView;
    private int currentPage = 0;
    private View dashBoardView;
    private View hdVideoView;
    private KswMbux1024MusicLauncherIconView musicRootView;
    private View musicView;
    private View naviView;
    private View originalCarView;
    private final ArrayList<View> pageIconViewList = new ArrayList<>(3);
    private View pager1;
    private View pager1LeftView;
    private View pager1RightView;
    private View pager2;
    private View pager2LeftView;
    private View pager2RightView;
    private View pager3;
    private View pager3LeftView;
    private View pager3RightView;
    private View settingsView;
    private MyViewPager viewPager;

    static /* synthetic */ void lambda$setPager1LeftRightClick$1(View view) {
    }

    static /* synthetic */ void lambda$setPager3LeftRightClick$6(View view) {
    }

    public static boolean matchModeSet(int i) {
        return i == 33;
    }

    public int getLayoutId() {
        return R.layout.small_kesaiwei_1024x600_mbux_fragment_main_common;
    }

    public int getPagerItemCount() {
        return 3;
    }

    public KswMbux1024LauncherStrategy(Context context) {
        super(context);
    }

    /* access modifiers changed from: protected */
    public void setViewInner(View view) {
        this.viewPager = (MyViewPager) view.findViewById(R.id.mPager);
        this.pageIconViewList.clear();
        View findViewById = view.findViewById(R.id.page0);
        View findViewById2 = view.findViewById(R.id.page1);
        View findViewById3 = view.findViewById(R.id.page2);
        this.pageIconViewList.add(findViewById);
        this.pageIconViewList.add(findViewById2);
        this.pageIconViewList.add(findViewById3);
    }

    public void setPageSelected(int i) {
        View view = (View) ListUtil.getOrNull(this.pageIconViewList, i);
        if (view != null) {
            this.currentPage = i;
            this.pageIconViewList.forEach($$Lambda$KswMbux1024LauncherStrategy$YkuA1yBx9w3dkKbOutBBmBwBSU.INSTANCE);
            view.setSelected(true);
        }
    }

    public TextView getMusicSubtitleTextView() {
        return this.musicRootView.getSubtitleView();
    }

    public TextView getBtSubtitleTextView() {
        return this.btRootView.getSubtitleView();
    }

    public void initPagerList() {
        ViewGroup viewGroup = this.view instanceof ViewGroup ? (ViewGroup) this.view : null;
        if (this.pager1 == null) {
            this.pager1 = initPager1(viewGroup);
        }
        if (this.pager2 == null) {
            this.pager2 = initPager2(viewGroup);
        }
        if (this.pager3 == null) {
            this.pager3 = initPager3(viewGroup);
        }
    }

    private View initPager1(ViewGroup viewGroup) {
        View inflate = getLayoutInflater().inflate(R.layout.ksw_mbux_1024x600_whats1, viewGroup, false);
        this.pager1LeftView = inflate.findViewById(R.id.left_icon_iv);
        this.pager1RightView = inflate.findViewById(R.id.right_icon_iv);
        setPager1LeftRightClick();
        this.musicRootView = (KswMbux1024MusicLauncherIconView) inflate.findViewById(R.id.music_root);
        this.musicView = inflate.findViewById(R.id.btnMusic);
        this.naviView = inflate.findViewById(R.id.btnNavi);
        this.btRootView = (KswMbux1024BtLauncherIonView) inflate.findViewById(R.id.bt_root);
        this.btView = inflate.findViewById(R.id.btnBt);
        return inflate;
    }

    private View initPager2(ViewGroup viewGroup) {
        View inflate = getLayoutInflater().inflate(R.layout.ksw_mbux_1024x600_whats2, viewGroup, false);
        this.pager2LeftView = inflate.findViewById(R.id.left_icon_iv);
        this.pager2RightView = inflate.findViewById(R.id.right_icon_iv);
        setPager2LeftRightClick();
        this.hdVideoView = inflate.findViewById(R.id.btnVideo);
        this.appView = inflate.findViewById(R.id.btnApps);
        this.settingsView = inflate.findViewById(R.id.btnSettins);
        return inflate;
    }

    private View initPager3(ViewGroup viewGroup) {
        View inflate = getLayoutInflater().inflate(R.layout.ksw_mbux_1024x600_whats3, viewGroup, false);
        this.pager3LeftView = inflate.findViewById(R.id.left_icon_iv);
        this.pager3RightView = inflate.findViewById(R.id.right_icon_iv);
        setPager3LeftRightClick();
        this.dashBoardView = inflate.findViewById(R.id.btnDashBoard);
        this.originalCarView = inflate.findViewById(R.id.btnOriginalCar);
        this.carAutoView = inflate.findViewById(R.id.btnCarplay);
        return inflate;
    }

    private void setPager1LeftRightClick() {
        this.pager1LeftView.setOnClickListener($$Lambda$KswMbux1024LauncherStrategy$g_D6HKX9w3V9dqH7ePtBo0MsfNQ.INSTANCE);
        this.pager1RightView.setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                KswMbux1024LauncherStrategy.this.lambda$setPager1LeftRightClick$2$KswMbux1024LauncherStrategy(view);
            }
        });
    }

    public /* synthetic */ void lambda$setPager1LeftRightClick$2$KswMbux1024LauncherStrategy(View view) {
        this.currentPage = 1;
        updateViewPagerCurrentPage();
    }

    private void setPager2LeftRightClick() {
        this.pager2LeftView.setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                KswMbux1024LauncherStrategy.this.lambda$setPager2LeftRightClick$3$KswMbux1024LauncherStrategy(view);
            }
        });
        this.pager2RightView.setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                KswMbux1024LauncherStrategy.this.lambda$setPager2LeftRightClick$4$KswMbux1024LauncherStrategy(view);
            }
        });
    }

    public /* synthetic */ void lambda$setPager2LeftRightClick$3$KswMbux1024LauncherStrategy(View view) {
        this.currentPage = 0;
        updateViewPagerCurrentPage();
    }

    public /* synthetic */ void lambda$setPager2LeftRightClick$4$KswMbux1024LauncherStrategy(View view) {
        this.currentPage = 2;
        updateViewPagerCurrentPage();
    }

    private void setPager3LeftRightClick() {
        this.pager3LeftView.setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                KswMbux1024LauncherStrategy.this.lambda$setPager3LeftRightClick$5$KswMbux1024LauncherStrategy(view);
            }
        });
        this.pager3RightView.setOnClickListener($$Lambda$KswMbux1024LauncherStrategy$itnFHXMG4rxcMUCmlmzN8IdeVE.INSTANCE);
    }

    public /* synthetic */ void lambda$setPager3LeftRightClick$5$KswMbux1024LauncherStrategy(View view) {
        this.currentPage = 1;
        updateViewPagerCurrentPage();
    }

    private void updateViewPagerCurrentPage() {
        this.viewPager.setCurrentItem(this.currentPage);
    }

    public View getPager1() {
        return this.pager1;
    }

    public View getPager2() {
        return this.pager2;
    }

    public View getPager3() {
        return this.pager3;
    }

    public int[] getPager1ButtonIdArray() {
        return new int[]{R.id.btnMusic, R.id.btnNavi, R.id.btnBt};
    }

    public int[] getPager2ButtonIdArray() {
        return new int[]{R.id.btnVideo, R.id.btnApps, R.id.btnSettins};
    }

    public int[] getPager3ButtonIdArray() {
        return new int[]{R.id.btnDashBoard, R.id.btnOriginalCar, R.id.btnCarplay};
    }

    public View[] getMainButtonArray() {
        return new View[]{this.musicView, this.naviView, this.btView, this.hdVideoView, this.appView, this.settingsView, this.dashBoardView, this.originalCarView, this.carAutoView};
    }
}
