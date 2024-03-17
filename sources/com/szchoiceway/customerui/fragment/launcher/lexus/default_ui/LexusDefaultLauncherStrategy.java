package com.szchoiceway.customerui.fragment.launcher.lexus.default_ui;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.ViewGroup;
import com.szchoiceway.customerui.R;
import com.szchoiceway.customerui.fragment.MainFragment;
import com.szchoiceway.customerui.fragment.launcher.strategy.BaseLauncherStrategy;
import com.szchoiceway.customerui.kt_like.JavaStandard;
import com.szchoiceway.customerui.kt_like.ListUtil;
import com.szchoiceway.customerui.utils.SysProviderOpt;
import java.util.function.Consumer;

public class LexusDefaultLauncherStrategy extends BaseLauncherStrategy {
    private static final int LAYOUT_ID = 1879834833;
    private static final int PAGER1_LAYOUT_ID = 1879834835;
    private static final int PAGER2_LAYOUT_ID = 1879834836;
    /* access modifiers changed from: private */
    public View airRootView;
    /* access modifiers changed from: private */
    public View airView;
    /* access modifiers changed from: private */
    public View appView;
    private View btView;
    /* access modifiers changed from: private */
    public View carPlayView;
    /* access modifiers changed from: private */
    public View clickedView;
    private View dashBoardView;
    private View dvrView;
    private View fileManagerView;
    /* access modifiers changed from: private */
    public Handler handler = new Handler(Looper.getMainLooper());
    /* access modifiers changed from: private */
    public boolean isAirShowing = true;
    /* access modifiers changed from: private */
    public View[] mainButtonArray = new View[12];
    private View musicView;
    private View naviView;
    private View originalCarView;
    private View pager1;
    private View pager2;
    private View settingsView;
    private View videoView;

    public static boolean matchModeSet(int i) {
        return i == 36;
    }

    public int getAirMainFocusIndex() {
        return 11;
    }

    public int getAppsMainFocusIndex() {
        return 9;
    }

    public int getBtMainFocusIndex() {
        return 1;
    }

    public int getCarPlayMainFocusIndex() {
        return 10;
    }

    public int getDVRMainFocusIndex() {
        return 8;
    }

    public int getDashBoardMainFocusIndex() {
        return 3;
    }

    public int getFileManagerMainFocusIndex() {
        return 7;
    }

    public int getLayoutId() {
        return R.layout.lexus_default_fragment_main_common;
    }

    public int getMusicMainFocusIndex() {
        return 2;
    }

    public int getNaviMainFocusIndex() {
        return 0;
    }

    public int getOriginalCarMainFocusIndex() {
        return 4;
    }

    public int getPagerItemCount() {
        return 8;
    }

    public int getSettingsMainFocusIndex() {
        return 5;
    }

    public int getUpNextMainFocusIndex(int i) {
        return (i < 0 || i > 3) ? i <= 7 ? i - 4 : i <= 9 ? i : i - 2 : i;
    }

    public int getVideoMainFocusIndex() {
        return 6;
    }

    public boolean isDownMcuKeyEnabled() {
        return true;
    }

    public boolean isLeftMcuKeyEnabled() {
        return true;
    }

    public boolean isRightMcuKeyEnabled() {
        return true;
    }

    public boolean isUpMcuKeyEnabled() {
        return true;
    }

    public LexusDefaultLauncherStrategy(Context context) {
        super(context);
    }

    /* access modifiers changed from: protected */
    public void setViewInner(View view) {
        this.handler.post(new Runnable() {
            public void run() {
                LexusDefaultLauncherStrategy lexusDefaultLauncherStrategy = LexusDefaultLauncherStrategy.this;
                boolean unused = lexusDefaultLauncherStrategy.isAirShowing = SysProviderOpt.getInstance(lexusDefaultLauncherStrategy.context).getRecordBoolean(SysProviderOpt.KSW_SHOW_AIR, true);
                if (LexusDefaultLauncherStrategy.this.isAirShowing) {
                    LexusDefaultLauncherStrategy.this.airRootView.setVisibility(0);
                    LexusDefaultLauncherStrategy.this.mainButtonArray[9] = LexusDefaultLauncherStrategy.this.appView;
                    LexusDefaultLauncherStrategy.this.mainButtonArray[10] = LexusDefaultLauncherStrategy.this.carPlayView;
                    LexusDefaultLauncherStrategy.this.mainButtonArray[11] = LexusDefaultLauncherStrategy.this.airView;
                } else {
                    if (LexusDefaultLauncherStrategy.this.airView.isSelected()) {
                        JavaStandard.runIfNonNull(MainFragment.instace, $$Lambda$LexusDefaultLauncherStrategy$1$WIBxbBI7s3ZQRtp57B8e9X1n5o.INSTANCE);
                    }
                    LexusDefaultLauncherStrategy.this.airRootView.setVisibility(8);
                    LexusDefaultLauncherStrategy.this.mainButtonArray[9] = LexusDefaultLauncherStrategy.this.appView;
                    LexusDefaultLauncherStrategy.this.mainButtonArray[10] = LexusDefaultLauncherStrategy.this.carPlayView;
                    LexusDefaultLauncherStrategy.this.mainButtonArray[11] = null;
                }
                LexusDefaultLauncherStrategy.this.handler.postDelayed(this, 1000);
            }

            static /* synthetic */ void lambda$run$0(MainFragment mainFragment) {
                mainFragment.iMainFocusIndex = 8;
                mainFragment.refreshMainFocus();
            }
        });
        initViewSelStatusListener();
    }

    private void initViewSelStatusListener() {
        ListUtil.listOf(this.naviView, this.btView, this.musicView, this.dashBoardView, this.originalCarView, this.settingsView, this.videoView, this.fileManagerView, this.dvrView, this.appView, this.carPlayView, this.airView).forEach(new Consumer() {
            public final void accept(Object obj) {
                LexusDefaultLauncherStrategy.this.lambda$initViewSelStatusListener$0$LexusDefaultLauncherStrategy((View) obj);
            }
        });
    }

    public /* synthetic */ void lambda$initViewSelStatusListener$0$LexusDefaultLauncherStrategy(View view) {
        view.setAccessibilityDelegate(new View.AccessibilityDelegate() {
            public void sendAccessibilityEvent(View view, int i) {
                super.sendAccessibilityEvent(view, i);
                if (i == 1) {
                    if (LexusDefaultLauncherStrategy.this.clickedView != null) {
                        LexusDefaultLauncherStrategy.this.clickedView.setActivated(false);
                    }
                    View unused = LexusDefaultLauncherStrategy.this.clickedView = view;
                } else if (i == 4 && LexusDefaultLauncherStrategy.this.clickedView != null && LexusDefaultLauncherStrategy.this.clickedView != view) {
                    LexusDefaultLauncherStrategy.this.clickedView.setActivated(true);
                }
            }
        });
    }

    public void initPagerList() {
        ViewGroup viewGroup;
        try {
            viewGroup = (ViewGroup) this.view;
        } catch (Exception unused) {
            viewGroup = null;
        }
        if (this.pager1 == null) {
            this.pager1 = initPager1(viewGroup);
        }
        if (this.pager2 == null) {
            this.pager2 = initPager2(viewGroup);
        }
        this.mainButtonArray = new View[]{this.naviView, this.btView, this.musicView, this.dashBoardView, this.originalCarView, this.settingsView, this.videoView, this.fileManagerView, this.dvrView, this.appView, this.carPlayView, this.airView};
    }

    private View initPager1(ViewGroup viewGroup) {
        View inflate = getLayoutInflater().inflate(R.layout.lexus_default_whats1, viewGroup, false);
        this.naviView = inflate.findViewById(R.id.btnNavi);
        this.btView = inflate.findViewById(R.id.btnBt);
        this.musicView = inflate.findViewById(R.id.btnMusic);
        this.dashBoardView = inflate.findViewById(R.id.btnDashBoard);
        this.originalCarView = inflate.findViewById(R.id.btnOriginalCar);
        this.settingsView = inflate.findViewById(R.id.btnSettins);
        this.videoView = inflate.findViewById(R.id.btnVideo);
        this.fileManagerView = inflate.findViewById(R.id.btnFile);
        return inflate;
    }

    private View initPager2(ViewGroup viewGroup) {
        View inflate = getLayoutInflater().inflate(R.layout.lexus_default_whats2, viewGroup, false);
        this.dvrView = inflate.findViewById(R.id.btnDvr);
        View findViewById = inflate.findViewById(R.id.btnAir);
        this.airView = findViewById;
        this.airRootView = (View) findViewById.getParent();
        this.carPlayView = inflate.findViewById(R.id.btnCarplay);
        this.appView = inflate.findViewById(R.id.btnApps);
        return inflate;
    }

    public View getPager1() {
        return this.pager1;
    }

    public View getPager2() {
        return this.pager2;
    }

    public int[] getPager1ButtonIdArray() {
        return new int[]{R.id.btnNavi, R.id.btnBt, R.id.btnMusic, R.id.btnDashBoard, R.id.btnOriginalCar, R.id.btnSettins, R.id.btnVideo, R.id.btnFile};
    }

    public int[] getPager2ButtonIdArray() {
        return new int[]{R.id.btnDvr, R.id.btnApps, R.id.btnCarplay, R.id.btnAir};
    }

    public View[] getMainButtonArray() {
        return this.mainButtonArray;
    }

    public int getLeftNextMainFocusIndex(int i) {
        return Math.max(i - 1, 0);
    }

    public int getRightNextMainFocusIndex(int i) {
        return Math.min(i + 1, getMainButtonArray().length - 1);
    }

    public int getDownNextMainFocusIndex(int i) {
        if (i >= 0 && i <= 3) {
            return i + 4;
        }
        if (i <= 7) {
            return i;
        }
        if (this.isAirShowing) {
            return i <= 9 ? i + 2 : i;
        }
        return i + (10 - i);
    }
}
