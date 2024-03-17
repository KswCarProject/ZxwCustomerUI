package com.szchoiceway.customerui.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.RemoteException;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.recyclerview.widget.RecyclerView;
import com.szchoiceway.customerui.R;
import com.szchoiceway.customerui.adapter.HorizontalPageLayoutManager;
import com.szchoiceway.customerui.adapter.MRecyclerVierAdapter;
import com.szchoiceway.customerui.adapter.PagingScrollHelper;
import com.szchoiceway.customerui.base.BaseView;
import com.szchoiceway.customerui.base.LauncherView;
import com.szchoiceway.customerui.drag.DragAppInfo;
import com.szchoiceway.customerui.drag.DragAppListInfo;
import com.szchoiceway.customerui.model.LauncherModel;
import com.szchoiceway.customerui.utils.EventUtils;
import com.szchoiceway.customerui.utils.MultipleUtils;
import java.util.ArrayList;
import java.util.List;

public class AppListView extends BaseView implements PagingScrollHelper.onPageChangeListener, MRecyclerVierAdapter.OnItemClickListener, MRecyclerVierAdapter.OnItemLongClickListener {
    private static final String TAG = "AppListView";
    private ImageView appListBg;
    private List<String> auxPackageNames = new ArrayList();
    private boolean bAppsPageChanged = true;
    public boolean bInLeftFocus;
    private long currentTime = 0;
    private int iAppsFocusIndex = -1;
    private int iColumns = 7;
    private int iLastAppsPageIndex = 0;
    private int iRows = 2;
    private long lastTime = 0;
    private LauncherView launcherView;
    private View mActionBar;
    int mBaseDrawableId = 0;
    int mBottomOffset = 0;
    boolean mDelete = false;
    float mLastX = 0.0f;
    float mLastY = 0.0f;
    private View mLayoutRecyclerViewRoot;
    int mLeftOffset = 0;
    int mMoveOffset = 0;
    private MRecyclerVierAdapter mRecyclerVierAdapter;
    private RecyclerView mRecyclerView;
    int mRightOffset = 0;
    public PagingScrollHelper mScrollHelper;
    private Toast mTip;
    int mTopOffset = 0;
    private boolean mUninstall = false;
    private LauncherModel model = LauncherModel.getInstance();

    private void setDragDelView(boolean z) {
    }

    /* access modifiers changed from: protected */
    public int inflateLayout() {
        return R.layout.layout_applist;
    }

    /* access modifiers changed from: protected */
    public void initEvent(View view) {
    }

    public void onPageState(int i) {
    }

    public AppListView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void setLauncherView(LauncherView launcherView2) {
        this.launcherView = launcherView2;
    }

    /* access modifiers changed from: protected */
    public void initView(View view) {
        MultipleUtils.setCustomDensity((Activity) null, this.mContext.getResources(), 0);
        setRows(2);
        this.mLayoutRecyclerViewRoot = view.findViewById(R.id.LayoutRecyclerViewRoot);
        View findViewById = view.findViewById(R.id.VActionBar);
        this.mActionBar = findViewById;
        if (findViewById != null) {
            findViewById.setVisibility(0);
        }
        ImageView imageView = (ImageView) view.findViewById(R.id.appListBg);
        this.appListBg = imageView;
        if (imageView != null) {
            if (this.model.m_iModeSet == 19) {
                this.appListBg.setVisibility(0);
            } else {
                this.appListBg.setVisibility(8);
            }
        }
        this.mRecyclerView = (RecyclerView) view.findViewById(R.id.app_listview);
        MRecyclerVierAdapter mRecyclerVierAdapter2 = new MRecyclerVierAdapter(this, DragAppListInfo.mAppFragmentItemDetailsMap);
        this.mRecyclerVierAdapter = mRecyclerVierAdapter2;
        this.mRecyclerView.setAdapter(mRecyclerVierAdapter2);
        this.mRecyclerView.setLayoutManager(new HorizontalPageLayoutManager(this.iRows, this.iColumns));
        this.mRecyclerView.setItemAnimator((RecyclerView.ItemAnimator) null);
        PagingScrollHelper pagingScrollHelper = new PagingScrollHelper();
        this.mScrollHelper = pagingScrollHelper;
        pagingScrollHelper.setUpRecycleView(this.mRecyclerView);
        this.mScrollHelper.setOnPageChangeListener(this);
        this.mRecyclerView.setHorizontalScrollBarEnabled(true);
        this.mRecyclerVierAdapter.setOnItemClickListener(this);
        this.mRecyclerVierAdapter.setOnItemLongClickListener(this);
        initOffset();
        initAuxPackageNames();
    }

    /* access modifiers changed from: protected */
    public void unInit() {
        super.unInit();
        removeAllViews();
    }

    public void setActionBar(boolean z) {
        View view = this.mActionBar;
        if (view != null) {
            view.setVisibility(z ? 0 : 8);
        }
    }

    public void setRows(int i) {
        this.iRows = i;
    }

    public void setColumns(int i) {
        this.iColumns = i;
    }

    public void refreshAppList() {
        if (this.model.hasInItAppList) {
            MRecyclerVierAdapter mRecyclerVierAdapter2 = this.mRecyclerVierAdapter;
            if (mRecyclerVierAdapter2 != null) {
                mRecyclerVierAdapter2.setData();
                this.mRecyclerVierAdapter.notifyDataSetChanged();
            }
            PagingScrollHelper pagingScrollHelper = this.mScrollHelper;
            if (pagingScrollHelper != null) {
                int page = pagingScrollHelper.getPage();
                int pageCount = getPageCount();
                Log.d(TAG, "currentPage = " + page + ", count = " + pageCount);
                if (pageCount > 0 && page > pageCount - 1) {
                    this.mScrollHelper.scrollToPosition(0);
                }
            }
        }
    }

    public void scrollToPosition(int i) {
        PagingScrollHelper pagingScrollHelper = this.mScrollHelper;
        if (pagingScrollHelper != null) {
            pagingScrollHelper.scrollToPosition(i);
        }
    }

    public void setiAppsFocusIndex(int i) {
        this.iAppsFocusIndex = i;
        MRecyclerVierAdapter mRecyclerVierAdapter2 = this.mRecyclerVierAdapter;
        if (mRecyclerVierAdapter2 != null) {
            mRecyclerVierAdapter2.setiAppsFocusIndex(i);
        }
    }

    public void onPageChange(int i) {
        if (this.iLastAppsPageIndex != i) {
            MRecyclerVierAdapter mRecyclerVierAdapter2 = this.mRecyclerVierAdapter;
            if (mRecyclerVierAdapter2 != null) {
                mRecyclerVierAdapter2.notifyDataSetChanged();
                if (this.mRecyclerVierAdapter.getiAppsFocusIndex() != -1) {
                    int i2 = this.iLastAppsPageIndex;
                    if (i2 > i) {
                        this.iAppsFocusIndex = ((this.iRows * this.iColumns) * i2) - 1;
                    } else {
                        this.iAppsFocusIndex = this.iRows * this.iColumns * i;
                    }
                    this.mRecyclerVierAdapter.setiAppsFocusIndex(this.iAppsFocusIndex);
                }
            }
            Log.i(TAG, "onPageChange: index  = " + i);
            this.iLastAppsPageIndex = i;
            this.bAppsPageChanged = true;
        }
    }

    public int getPageCount() {
        MRecyclerVierAdapter mRecyclerVierAdapter2 = this.mRecyclerVierAdapter;
        int i = 0;
        if (mRecyclerVierAdapter2 == null) {
            return 0;
        }
        int i2 = this.iRows * this.iColumns;
        int itemCount = mRecyclerVierAdapter2.getItemCount() / i2;
        if (this.mRecyclerVierAdapter.getItemCount() % i2 > 0) {
            i = 1;
        }
        return itemCount + i;
    }

    public void onItemClick(int i) {
        DragAppInfo dragAppInfo = DragAppListInfo.mAppFragmentItemDetailsMap.get(DragAppListInfo.mAppsFragmentItemTagList.get(i));
        String appPackageName = dragAppInfo.getAppPackageName();
        String appClassName = dragAppInfo.getAppClassName();
        if (appPackageName.equals(EventUtils.LETTER_CARPLAY_MODE_PACKAGE_NAME)) {
            EventUtils.startActivityType(6, this.mContext, this.model.getEvtService());
        } else if (appPackageName.equals(EventUtils.ZLINK_MODE_PACKAGE_NAME) && !appClassName.equals(EventUtils.ZLINK_DLNA_MODE_CLASS_NAME)) {
            EventUtils.startActivityType(7, this.mContext, this.model.getEvtService());
        } else if (this.auxPackageNames.contains(appPackageName)) {
            try {
                if (this.model.getEvtService() != null && this.model.getEvtService().isUpgradeMode()) {
                    return;
                }
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            if (!"com.szchoiceway.ksw_dvr".equals(appPackageName) || !"com.szchoiceway.ksw_dvr.MainActivity".equals(appClassName)) {
                EventUtils.startActivityIfNotRunning(getContext(), appPackageName, appClassName);
            } else {
                EventUtils.onEnterDvr(getContext());
            }
        } else {
            try {
                Log.d(TAG, "sendThirdApp0");
                LauncherModel launcherModel = this.model;
                if (!(launcherModel == null || launcherModel.getEvtService() == null)) {
                    this.model.getEvtService().sendKSW_0x00_0x67_third();
                }
            } catch (RemoteException e2) {
                e2.printStackTrace();
            }
            EventUtils.startActivityIfNotRunning(getContext(), appPackageName, appClassName);
        }
        this.iAppsFocusIndex = i;
        MRecyclerVierAdapter mRecyclerVierAdapter2 = this.mRecyclerVierAdapter;
        if (mRecyclerVierAdapter2 != null) {
            mRecyclerVierAdapter2.setiAppsFocusIndex(-1);
        }
    }

    public void onItemLongClick(View view, int i) {
        DragAppInfo dragAppInfo = DragAppListInfo.mAppFragmentItemDetailsMap.get(DragAppListInfo.mAppsFragmentItemTagList.get(i));
        String appPackageName = dragAppInfo.getAppPackageName();
        String appClassName = dragAppInfo.getAppClassName();
        if (LauncherModel.getInstance().inEditMode) {
            LauncherView launcherView2 = this.launcherView;
            if (launcherView2 != null) {
                launcherView2.addThirdApp(appPackageName, appClassName);
            }
        } else if (appPackageName == null || !isSystemApplication(this.mContext, appPackageName)) {
            Intent intent = new Intent("android.intent.action.DELETE", Uri.parse("package:" + appPackageName));
            intent.setFlags(268435456);
            this.mContext.startActivity(intent);
        } else {
            Log.d(TAG, "系统app无法卸载");
            showTipString(this.mContext, this.mContext.getResources().getString(R.string.lb_uninstall_failed));
        }
    }

    public void initOffset() {
        this.mBaseDrawableId = R.drawable.imitate_auto_baidi_;
        this.mLeftOffset = (int) (getDensity() * 140.0f);
        this.mRightOffset = (int) (((float) getScreenWidth()) - (getDensity() * 35.0f));
        this.mTopOffset = (int) (getDensity() * 80.0f);
        this.mBottomOffset = (int) (((float) getScreenHeitht()) - (getDensity() * 35.0f));
        Log.i(TAG, "initOffset: mRightOffset = " + this.mRightOffset);
    }

    public void setAppsFocusMove(int i, boolean z) {
        PagingScrollHelper pagingScrollHelper;
        PagingScrollHelper pagingScrollHelper2;
        if (this.bAppsPageChanged) {
            if (i != 1) {
                if (i != 2) {
                    if (i != 5) {
                        if (i == 7) {
                            long currentTimeMillis = System.currentTimeMillis();
                            this.currentTime = currentTimeMillis;
                            if (Math.abs(currentTimeMillis - this.lastTime) >= 10 && z) {
                                MRecyclerVierAdapter mRecyclerVierAdapter2 = this.mRecyclerVierAdapter;
                                if (mRecyclerVierAdapter2 != null && mRecyclerVierAdapter2.getiAppsFocusIndex() == -1) {
                                    this.iAppsFocusIndex = (this.iRows * this.iColumns * this.iLastAppsPageIndex) + 1;
                                }
                                int i2 = this.iAppsFocusIndex - 1;
                                this.iAppsFocusIndex = i2;
                                if (i2 < 0) {
                                    this.iAppsFocusIndex = 0;
                                    int i3 = this.model.m_iUITypeVer;
                                }
                                int i4 = this.iAppsFocusIndex;
                                int i5 = this.iRows * this.iColumns;
                                int i6 = this.iLastAppsPageIndex;
                                if (i4 < i5 * i6 && (pagingScrollHelper = this.mScrollHelper) != null) {
                                    this.bAppsPageChanged = false;
                                    pagingScrollHelper.scrollToPosition(i6 - 1);
                                }
                                this.mRecyclerVierAdapter.setiAppsFocusIndex(this.iAppsFocusIndex);
                                this.lastTime = this.currentTime;
                            }
                        } else if (i == 8) {
                            long currentTimeMillis2 = System.currentTimeMillis();
                            this.currentTime = currentTimeMillis2;
                            if (Math.abs(currentTimeMillis2 - this.lastTime) >= 10 && z) {
                                MRecyclerVierAdapter mRecyclerVierAdapter3 = this.mRecyclerVierAdapter;
                                if (mRecyclerVierAdapter3 != null && mRecyclerVierAdapter3.getiAppsFocusIndex() == -1) {
                                    this.iAppsFocusIndex = ((this.iRows * this.iColumns) * this.iLastAppsPageIndex) - 1;
                                }
                                int i7 = this.iAppsFocusIndex + 1;
                                this.iAppsFocusIndex = i7;
                                if (i7 >= DragAppListInfo.mAppsFragmentItemTagList.size()) {
                                    this.iAppsFocusIndex = DragAppListInfo.mAppsFragmentItemTagList.size() - 1;
                                }
                                int i8 = this.iAppsFocusIndex;
                                int i9 = this.iRows * this.iColumns;
                                int i10 = this.iLastAppsPageIndex;
                                if (i8 >= i9 * (i10 + 1) && (pagingScrollHelper2 = this.mScrollHelper) != null) {
                                    this.bAppsPageChanged = false;
                                    pagingScrollHelper2.scrollToPosition(i10 + 1);
                                }
                                MRecyclerVierAdapter mRecyclerVierAdapter4 = this.mRecyclerVierAdapter;
                                if (mRecyclerVierAdapter4 != null) {
                                    mRecyclerVierAdapter4.setiAppsFocusIndex(this.iAppsFocusIndex);
                                }
                                this.lastTime = this.currentTime;
                            }
                        }
                    } else if (z && this.mRecyclerVierAdapter.getiAppsFocusIndex() != -1) {
                        this.mRecyclerVierAdapter.getOnItemClickListener().onItemClick(this.mRecyclerVierAdapter.getiAppsFocusIndex());
                    }
                } else if (this.model.m_iUITypeVer == 102) {
                    this.model.bInLeftFocus = false;
                    MRecyclerVierAdapter mRecyclerVierAdapter5 = this.mRecyclerVierAdapter;
                    if (mRecyclerVierAdapter5 != null) {
                        mRecyclerVierAdapter5.notifyDataSetChanged();
                    }
                }
            } else if (this.model.m_iUITypeVer == 102) {
                this.model.bInLeftFocus = true;
                MRecyclerVierAdapter mRecyclerVierAdapter6 = this.mRecyclerVierAdapter;
                if (mRecyclerVierAdapter6 != null) {
                    mRecyclerVierAdapter6.notifyDataSetChanged();
                }
            } else if (z) {
                this.model.showAppList(false);
            }
        }
    }

    private void initAuxPackageNames() {
        this.auxPackageNames.add("com.szchoiceway.ksw_aux");
        this.auxPackageNames.add("com.szchoiceway.ksw_cmmb");
        this.auxPackageNames.add("com.szchoiceway.ksw_dvd");
        this.auxPackageNames.add("com.szchoiceway.ksw_dvr");
        this.auxPackageNames.add("com.szchoiceway.ksw_fc");
    }

    public boolean isSystemApplication(Context context, String str) {
        try {
            if ((context.getPackageManager().getPackageInfo(str, 16384).applicationInfo.flags & 1) != 0) {
                return true;
            }
            return false;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void showTipString(Context context, String str) {
        Toast makeText = Toast.makeText(context, str, 0);
        this.mTip = makeText;
        makeText.setGravity(17, 0, 0);
        this.mTip.show();
    }
}
