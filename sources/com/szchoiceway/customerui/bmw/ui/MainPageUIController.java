package com.szchoiceway.customerui.bmw.ui;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.RemoteException;
import android.util.Log;
import android.view.MotionEvent;
import android.view.Surface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.szchoiceway.customerui.R;
import com.szchoiceway.customerui.bmw.BMWMainFragment;
import com.szchoiceway.customerui.bmw.recycle.AddItem;
import com.szchoiceway.customerui.bmw.recycle.CarInfoItem;
import com.szchoiceway.customerui.bmw.recycle.DashboardItem;
import com.szchoiceway.customerui.bmw.recycle.MainItemAdapter;
import com.szchoiceway.customerui.bmw.recycle.ModusItem;
import com.szchoiceway.customerui.bmw.recycle.MusicItem;
import com.szchoiceway.customerui.bmw.recycle.NavigationItem;
import com.szchoiceway.customerui.bmw.recycle.PhoneItem;
import com.szchoiceway.customerui.bmw.recycle.RecycleItemBase;
import com.szchoiceway.customerui.bmw.recycle.SettingsItem;
import com.szchoiceway.customerui.bmw.recycle.SpaceItemDecoration;
import com.szchoiceway.customerui.bmw.recycle.ThirdAppItem;
import com.szchoiceway.customerui.bmw.recycle.VideoItem;
import com.szchoiceway.customerui.bmw.recycle.WeatherItem;
import com.szchoiceway.customerui.bmw.services.VirtualDisplayService;
import com.szchoiceway.customerui.bmw.ui.ModeSelectPage;
import com.szchoiceway.customerui.bmw.weather.WeatherInfo;
import com.szchoiceway.customerui.drag.DragAppInfo;
import com.szchoiceway.customerui.model.LauncherModel;
import com.szchoiceway.customerui.utils.EventUtils;
import com.szchoiceway.customerui.utils.ShareUtil;
import com.szchoiceway.customerui.utils.SysProviderOpt;
import com.szchoiceway.eventcenter.IEventService;
import com.szchoiceway.zxwlib.focus.FocusObserver;
import com.szchoiceway.zxwlib.focus.view.FocusIconWithTitleView;
import com.szchoiceway.zxwlib.focus.view.FocusRecycleView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class MainPageUIController extends UIControllerBase implements View.OnClickListener {
    public static final String ADD_TAG = "Add";
    public static final String APPLIST_TAG = "AppList";
    public static final String BLUETOOTH_TAG = "Bluetooth";
    public static final String CARINFO_TAG = "CarInfo";
    public static final String DASHBOARD_TAG = "Dashboard";
    public static final int MAX_ITEM_COUNT = 15;
    public static final String MODE_TAG = "Modes";
    public static final String MUSIC_TAG = "Music";
    public static final String NAVI_TAG = "Navi";
    public static final String SETTINGS_TAG = "Settings";
    private static final String TAG = "MainPageUIController";
    public static final String VIDEO_TAG = "Video";
    public static final String WEATHER_TAG = "Weather";
    private List<String> auxPackageNames = new ArrayList();
    /* access modifiers changed from: private */
    public List<View> copyView = new ArrayList();
    private int form = 0;
    public int iSpeed = 0;
    /* access modifiers changed from: private */
    public boolean itemPress;
    private List<Integer> leftBtnIds;
    private int mBTState = 0;
    private BMWMainFragment mBmwMainFragment;
    private Rect mChangeImageBackgroundRect = null;
    private int mCurDisplayMode = 0;
    private List<RecycleItemBase> mDatas = new ArrayList();
    /* access modifiers changed from: private */
    public Map<String, Integer> mDefaultIconMap = new HashMap();
    private Map<String, RecycleItemBase> mDefaultItemMap = new HashMap();
    private Map<String, Integer> mDefaultNameMap = new HashMap();
    private FocusObserver mFocusObserver;
    private boolean mInModeSelect = false;
    private LinearLayoutManager mLayoutManage;
    private FocusIconWithTitleView mLeftBtn1;
    private FocusIconWithTitleView mLeftBtn2;
    private FocusIconWithTitleView mLeftBtn3;
    private FocusIconWithTitleView mLeftBtn4;
    private FocusIconWithTitleView mLeftBtn5;
    /* access modifiers changed from: private */
    public LinkedList<String> mLeftBtnInfoList = new LinkedList<>();
    private MainApplistUtils mMainApplistUtils;
    private ConstraintLayout mMainContent;
    /* access modifiers changed from: private */
    public MainItemAdapter mMainItemAdapter;
    private MainPageReceiver mMainPageReceiver = new MainPageReceiver();
    private ModeSelectPage mModeSelectPage;
    private View mModeSelectView;
    private int mOldFocusId = 0;
    private int mOldFocusItemIndex = -1;
    private FocusRecycleView mRecycleList;
    /* access modifiers changed from: private */
    public List<String> mRecycleViewInfoList = new ArrayList();
    private boolean mStartMove = true;
    private TextView mTxtModeTitle;
    private boolean mVisible = true;
    public List<String> mWeatherDetailList;
    public HashMap<String, String> mWeatherDetailMap;
    public int mWeatherFailedCode = -1;
    public WeatherInfo mWeatherInfo;
    private int m_iModeSet = 16;
    private RelativeLayout rlView;
    /* access modifiers changed from: private */
    public boolean ryCanScroll = true;
    private int scorllTime = 1000;
    private int startX;
    private int startY;
    private long time = 0;
    private int to = 0;

    public void setFocusObserver(FocusObserver focusObserver) {
    }

    public MainPageUIController(Context context) {
        super(context);
        this.m_iModeSet = SysProviderOpt.getInstance(context).getRecordInteger("KESAIWEI_SYS_MODE_SELECTION", this.m_iModeSet);
    }

    public int getLayout() {
        return this.m_iModeSet == 20 ? R.layout.pemp_bmw_id8_mainpage : R.layout.bmw_id8_mainpage;
    }

    public void init(View view, BMWMainFragment bMWMainFragment) {
        Log.d(TAG, "init");
        this.mContext.startService(new Intent(this.mContext, VirtualDisplayService.class));
        this.mMainPageReceiver.registerRec();
        this.mBmwMainFragment = bMWMainFragment;
        this.mMainContent = (ConstraintLayout) view.findViewById(R.id.mainContent);
        initBtnInfo();
        this.rlView = (RelativeLayout) view.findViewById(R.id.rlView);
        TextView textView = (TextView) view.findViewById(R.id.txtModeTitle);
        this.mTxtModeTitle = textView;
        if (textView != null) {
            textView.setVisibility(8);
        }
        this.leftBtnIds = new ArrayList();
        FocusIconWithTitleView focusIconWithTitleView = (FocusIconWithTitleView) view.findViewById(R.id.leftBtn1);
        this.mLeftBtn1 = focusIconWithTitleView;
        if (focusIconWithTitleView != null) {
            focusIconWithTitleView.setOnClickListener(this);
            this.leftBtnIds.add(Integer.valueOf(R.id.leftBtn1));
        }
        FocusIconWithTitleView focusIconWithTitleView2 = (FocusIconWithTitleView) view.findViewById(R.id.leftBtn2);
        this.mLeftBtn2 = focusIconWithTitleView2;
        if (focusIconWithTitleView2 != null) {
            focusIconWithTitleView2.setOnClickListener(this);
            this.leftBtnIds.add(Integer.valueOf(R.id.leftBtn2));
        }
        FocusIconWithTitleView focusIconWithTitleView3 = (FocusIconWithTitleView) view.findViewById(R.id.leftBtn3);
        this.mLeftBtn3 = focusIconWithTitleView3;
        if (focusIconWithTitleView3 != null) {
            focusIconWithTitleView3.setOnClickListener(this);
            this.leftBtnIds.add(Integer.valueOf(R.id.leftBtn3));
        }
        FocusIconWithTitleView focusIconWithTitleView4 = (FocusIconWithTitleView) view.findViewById(R.id.leftBtn4);
        this.mLeftBtn4 = focusIconWithTitleView4;
        if (focusIconWithTitleView4 != null) {
            focusIconWithTitleView4.setOnClickListener(this);
            this.leftBtnIds.add(Integer.valueOf(R.id.leftBtn4));
        }
        FocusIconWithTitleView focusIconWithTitleView5 = (FocusIconWithTitleView) view.findViewById(R.id.leftBtn5);
        this.mLeftBtn5 = focusIconWithTitleView5;
        if (focusIconWithTitleView5 != null) {
            focusIconWithTitleView5.setOnClickListener(this);
            this.leftBtnIds.add(Integer.valueOf(R.id.leftBtn5));
        }
        this.mRecycleList = (FocusRecycleView) view.findViewById(R.id.recycleList);
        MainItemAdapter mainItemAdapter = new MainItemAdapter(this.mContext);
        this.mMainItemAdapter = mainItemAdapter;
        mainItemAdapter.setDatas(this.mDatas);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.mContext);
        this.mLayoutManage = linearLayoutManager;
        linearLayoutManager.setOrientation(0);
        FocusRecycleView focusRecycleView = this.mRecycleList;
        if (focusRecycleView != null) {
            focusRecycleView.setLayoutManager(this.mLayoutManage);
            this.mRecycleList.addItemDecoration(new SpaceItemDecoration(0, 0, 20, 0, 20));
            this.mRecycleList.setAdapter(this.mMainItemAdapter);
        }
        MainItemAdapter mainItemAdapter2 = this.mMainItemAdapter;
        if (mainItemAdapter2 != null) {
            mainItemAdapter2.setOnItemClickListener(new MainItemAdapter.OnItemClickListener() {
                public final void onItemClick(View view, int i) {
                    MainPageUIController.this.lambda$init$0$MainPageUIController(view, i);
                }
            });
            this.mMainItemAdapter.setOnItemLongClickListener(new MainItemAdapter.OnItemLongClickListener() {
                public final void onItemLongClick(View view, int i) {
                    MainPageUIController.this.lambda$init$1$MainPageUIController(view, i);
                }
            });
            this.mMainItemAdapter.setOnSmallModeListener(new MainItemAdapter.OnSmallModeListener() {
                public final void onSmallModeSwitch(boolean z) {
                    MainPageUIController.this.lambda$init$2$MainPageUIController(z);
                }
            });
            this.mMainItemAdapter.notifyDataSetChanged();
        }
        refreshLeftBtn();
        refreshLeftBtnBk();
        initListener();
        FocusObserver focusObserver = new FocusObserver();
        this.mFocusObserver = focusObserver;
        focusObserver.setmControlByKnob(true);
        FocusObserver focusObserver2 = this.mFocusObserver;
        if (focusObserver2 != null) {
            focusObserver2.setLayout(this.mMainContent);
            reSetFocus();
        }
        initAuxPackageNames();
        refreshMainItem();
    }

    public /* synthetic */ void lambda$init$0$MainPageUIController(View view, int i) {
        if (this.mMainItemAdapter.ismSmallMode()) {
            this.mOldFocusItemIndex = i;
            this.mRecycleList.setScrollbarFadingEnabled(true);
            this.mRecycleList.setHorizontalScrollbarThumbDrawable((Drawable) null);
            this.mMainItemAdapter.setmSmallMode(false);
            initRecycleDatas();
            this.mMainItemAdapter.setDatas(this.mDatas);
            reSetFocus();
            return;
        }
        this.mFocusObserver.setmCurFocusViewId(R.id.recycleList);
        this.mFocusObserver.setmCurPosition(i);
        this.mMainItemAdapter.setCurFocusItemPosition(i);
        this.mMainItemAdapter.notifyDataSetChanged();
        startActivity(this.mDatas.get(i).getTag());
    }

    public /* synthetic */ void lambda$init$1$MainPageUIController(View view, int i) {
        Log.d(TAG, "onItemLongClick smallMode = " + this.mMainItemAdapter.ismSmallMode());
        if (!this.mMainItemAdapter.ismSmallMode()) {
            this.mMainItemAdapter.setmSmallMode(true);
            this.mOldFocusId = R.id.recycleList;
            this.mOldFocusItemIndex = i;
            this.mRecycleList.setScrollbarFadingEnabled(false);
            this.mRecycleList.setHorizontalScrollbarThumbDrawable(this.mContext.getResources().getDrawable(R.drawable.recycleview_scrollbar, (Resources.Theme) null));
        } else if (!(this.mDatas.get(i) instanceof AddItem) && !this.itemPress) {
            copyItem(view, i);
            this.itemPress = true;
            this.ryCanScroll = false;
        }
    }

    public /* synthetic */ void lambda$init$2$MainPageUIController(boolean z) {
        TextView textView = this.mTxtModeTitle;
        if (textView != null) {
            textView.setVisibility(z ? 0 : 8);
        }
    }

    public void refreshMainItem() {
        boolean recordBoolean = SysProviderOpt.getInstance(this.mContext).getRecordBoolean(SysProviderOpt.KSW_HAVE_WEATHER, true);
        boolean recordBoolean2 = SysProviderOpt.getInstance(this.mContext).getRecordBoolean(SysProviderOpt.KSW_HAVE_WEATHER_MAIN, true);
        if (this.mMainItemAdapter != null) {
            String string = ShareUtil.getString(MainApplistUtils.RECYCLE_KEY);
            String[] split = string.split(",");
            ArrayList arrayList = new ArrayList(Arrays.asList(split));
            if ((!recordBoolean || !recordBoolean2) && arrayList.contains("Weather")) {
                if (!recordBoolean) {
                    int i = 0;
                    while (true) {
                        if (i >= string.length()) {
                            break;
                        } else if ("Weather".equals(split[i])) {
                            SysProviderOpt.getInstance(this.mContext).updateRecord(SysProviderOpt.KSW_HAVE_WEATHER_INDEX, "" + i);
                            break;
                        } else {
                            i++;
                        }
                    }
                }
                if (!recordBoolean2) {
                    SysProviderOpt.getInstance(this.mContext).updateRecord(SysProviderOpt.KSW_HAVE_WEATHER_INDEX, "8");
                }
                arrayList.remove("Weather");
                this.mMainApplistUtils.saveRecycleList(arrayList);
                this.mRecycleViewInfoList = this.mMainApplistUtils.getRecycleList();
                initRecycleDatas();
                this.mMainItemAdapter.setDatas(this.mDatas);
            }
            if (recordBoolean && recordBoolean2 && !arrayList.contains("Weather")) {
                arrayList.add(SysProviderOpt.getInstance(this.mContext).getRecordInteger(SysProviderOpt.KSW_HAVE_WEATHER_INDEX, 0), "Weather");
                this.mMainApplistUtils.saveRecycleList(arrayList);
                this.mRecycleViewInfoList = this.mMainApplistUtils.getRecycleList();
                initRecycleDatas();
                this.mMainItemAdapter.setDatas(this.mDatas);
            }
        }
    }

    public void refreshPlayState() {
        MusicItem musicItem = (MusicItem) this.mDefaultItemMap.get("Music");
        if (musicItem != null) {
            musicItem.refreshPlayInfo();
        }
        VideoItem videoItem = (VideoItem) this.mDefaultItemMap.get("Video");
        if (videoItem != null) {
            videoItem.refreshPlayInfo();
        }
    }

    private void initBtnInfo() {
        this.mDefaultIconMap.put("AppList", Integer.valueOf(R.drawable.id8_main_left_icon_app));
        this.mDefaultIconMap.put("Navi", Integer.valueOf(R.drawable.id8_main_left_icon_navi));
        this.mDefaultIconMap.put("Music", Integer.valueOf(R.drawable.id8_main_left_icon_music));
        this.mDefaultIconMap.put(BLUETOOTH_TAG, Integer.valueOf(R.drawable.id8_main_left_icon_bt));
        this.mDefaultIconMap.put(CARINFO_TAG, Integer.valueOf(R.drawable.id8_main_left_icon_car));
        this.mDefaultIconMap.put("Dashboard", Integer.valueOf(R.drawable.id8_main_left_icon_dashboard));
        this.mDefaultIconMap.put(MODE_TAG, Integer.valueOf(R.drawable.id8_main_left_icon_modus));
        this.mDefaultIconMap.put("Settings", Integer.valueOf(R.drawable.id8_main_left_icon_set));
        this.mDefaultIconMap.put("Video", Integer.valueOf(R.drawable.id8_main_left_icon_video));
        this.mDefaultNameMap.put("AppList", Integer.valueOf(R.string.lb_left_apps));
        this.mDefaultNameMap.put("Navi", Integer.valueOf(R.string.lb_left_navi));
        this.mDefaultNameMap.put("Music", Integer.valueOf(R.string.lb_left_music));
        this.mDefaultNameMap.put(BLUETOOTH_TAG, Integer.valueOf(R.string.lb_left_bt));
        this.mDefaultNameMap.put(CARINFO_TAG, Integer.valueOf(R.string.lb_original));
        this.mDefaultNameMap.put("Dashboard", Integer.valueOf(R.string.lb_dash_board));
        this.mDefaultNameMap.put(MODE_TAG, Integer.valueOf(R.string.lb_modus));
        this.mDefaultNameMap.put("Settings", Integer.valueOf(R.string.lb_left_set));
        this.mDefaultNameMap.put("Video", Integer.valueOf(R.string.lb_video));
        this.mDefaultItemMap.put("Navi", new NavigationItem());
        this.mDefaultItemMap.put("Music", new MusicItem());
        this.mDefaultItemMap.put(CARINFO_TAG, new CarInfoItem());
        this.mDefaultItemMap.put(MODE_TAG, new ModusItem());
        this.mDefaultItemMap.put(BLUETOOTH_TAG, new PhoneItem());
        this.mDefaultItemMap.put("Dashboard", new DashboardItem(this));
        this.mDefaultItemMap.put("Settings", new SettingsItem());
        this.mDefaultItemMap.put("Video", new VideoItem());
        this.mDefaultIconMap.put("Weather", Integer.valueOf(R.drawable.id8_main_left_icon_weather));
        this.mDefaultNameMap.put("Weather", Integer.valueOf(R.string.lb_weather));
        this.mDefaultItemMap.put("Weather", new WeatherItem(this));
        MainApplistUtils mainApplistUtils = new MainApplistUtils();
        this.mMainApplistUtils = mainApplistUtils;
        this.mLeftBtnInfoList = mainApplistUtils.getLeftBtnList(this.m_iModeSet);
        this.mRecycleViewInfoList = this.mMainApplistUtils.getRecycleList();
        initRecycleDatas();
    }

    private void initRecycleDatas() {
        this.mDatas = new ArrayList();
        for (int i = 0; i < this.mRecycleViewInfoList.size(); i++) {
            String str = this.mRecycleViewInfoList.get(i);
            if (this.mDefaultItemMap.containsKey(str)) {
                this.mDatas.add(this.mDefaultItemMap.get(str));
            } else {
                ThirdAppItem thirdAppItem = new ThirdAppItem();
                String[] split = str.split("/");
                if (split != null && split.length == 2) {
                    thirdAppItem.setInfo(getAppInfo(split[0], split[1]));
                    thirdAppItem.setMainPageUIController(this);
                    this.mDatas.add(thirdAppItem);
                }
            }
        }
        if (this.mDatas.size() < 15) {
            MainItemAdapter mainItemAdapter = this.mMainItemAdapter;
            if (mainItemAdapter == null || !mainItemAdapter.ismSmallMode()) {
                this.mDatas.add(new AddItem());
            }
        }
    }

    private void refreshLeftBtn() {
        String str = this.mLeftBtnInfoList.get(0);
        FocusIconWithTitleView focusIconWithTitleView = this.mLeftBtn1;
        if (focusIconWithTitleView != null) {
            focusIconWithTitleView.setIcon(getIcon(str));
            this.mLeftBtn1.setTiteText(getName(str));
        }
        String str2 = this.mLeftBtnInfoList.get(1);
        FocusIconWithTitleView focusIconWithTitleView2 = this.mLeftBtn2;
        if (focusIconWithTitleView2 != null) {
            focusIconWithTitleView2.setIcon(getIcon(str2));
            this.mLeftBtn2.setTiteText(getName(str2));
        }
        String str3 = this.mLeftBtnInfoList.get(2);
        FocusIconWithTitleView focusIconWithTitleView3 = this.mLeftBtn3;
        if (focusIconWithTitleView3 != null) {
            focusIconWithTitleView3.setIcon(getIcon(str3));
            this.mLeftBtn3.setTiteText(getName(str3));
        }
        String str4 = this.mLeftBtnInfoList.get(3);
        FocusIconWithTitleView focusIconWithTitleView4 = this.mLeftBtn4;
        if (focusIconWithTitleView4 != null) {
            focusIconWithTitleView4.setIcon(getIcon(str4));
            this.mLeftBtn4.setTiteText(getName(str4));
        }
        if (this.mLeftBtnInfoList.size() > 4) {
            String str5 = this.mLeftBtnInfoList.get(4);
            FocusIconWithTitleView focusIconWithTitleView5 = this.mLeftBtn5;
            if (focusIconWithTitleView5 != null) {
                focusIconWithTitleView5.setIcon(getIcon(str5));
                this.mLeftBtn5.setTiteText(getName(str5));
            }
        }
    }

    private Drawable getIcon(String str) {
        DragAppInfo appInfo;
        if (this.mDefaultIconMap.containsKey(str)) {
            return this.mContext.getDrawable(this.mDefaultIconMap.get(str).intValue());
        }
        String[] split = str.split("/");
        if (split == null || split.length != 2 || (appInfo = getAppInfo(split[0], split[1])) == null) {
            return null;
        }
        return appInfo.getDrawable();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0027, code lost:
        r2 = getAppInfo(r3[0], r3[1]);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.lang.String getName(java.lang.String r3) {
        /*
            r2 = this;
            java.util.Map<java.lang.String, java.lang.Integer> r0 = r2.mDefaultNameMap
            boolean r0 = r0.containsKey(r3)
            if (r0 == 0) goto L_0x001b
            java.util.Map<java.lang.String, java.lang.Integer> r0 = r2.mDefaultNameMap
            java.lang.Object r3 = r0.get(r3)
            java.lang.Integer r3 = (java.lang.Integer) r3
            int r3 = r3.intValue()
            android.content.Context r2 = r2.mContext
            java.lang.String r2 = r2.getString(r3)
            goto L_0x003a
        L_0x001b:
            java.lang.String r0 = "/"
            java.lang.String[] r3 = r3.split(r0)
            if (r3 == 0) goto L_0x0038
            int r0 = r3.length
            r1 = 2
            if (r0 != r1) goto L_0x0038
            r0 = 0
            r0 = r3[r0]
            r1 = 1
            r3 = r3[r1]
            com.szchoiceway.customerui.drag.DragAppInfo r2 = r2.getAppInfo(r0, r3)
            if (r2 == 0) goto L_0x0038
            java.lang.String r2 = r2.getAppName()
            goto L_0x003a
        L_0x0038:
            java.lang.String r2 = ""
        L_0x003a:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.szchoiceway.customerui.bmw.ui.MainPageUIController.getName(java.lang.String):java.lang.String");
    }

    public void onClick(View view) {
        int id = view.getId();
        MainItemAdapter mainItemAdapter = this.mMainItemAdapter;
        if (mainItemAdapter == null || !mainItemAdapter.ismSmallMode()) {
            if (this.mInModeSelect) {
                this.mContext.getMainThreadHandler().postDelayed(new Runnable() {
                    public final void run() {
                        MainPageUIController.this.hideModeSelectPage();
                    }
                }, 500);
            }
            FocusObserver focusObserver = this.mFocusObserver;
            if (focusObserver != null) {
                focusObserver.setmCurFocusViewId(id);
            }
            switch (id) {
                case R.id.leftBtn1:
                    startActivity(this.mLeftBtnInfoList.get(0));
                    return;
                case R.id.leftBtn2:
                    startActivity(this.mLeftBtnInfoList.get(1));
                    return;
                case R.id.leftBtn3:
                    startActivity(this.mLeftBtnInfoList.get(2));
                    return;
                case R.id.leftBtn4:
                    startActivity(this.mLeftBtnInfoList.get(3));
                    return;
                case R.id.leftBtn5:
                    startActivity(this.mLeftBtnInfoList.get(4));
                    return;
                default:
                    return;
            }
        }
    }

    private void startActivity(String str) {
        MainItemAdapter mainItemAdapter = this.mMainItemAdapter;
        if (mainItemAdapter == null || !mainItemAdapter.ismSmallMode()) {
            str.hashCode();
            char c = 65535;
            switch (str.hashCode()) {
                case -2077170046:
                    if (str.equals(CARINFO_TAG)) {
                        c = 0;
                        break;
                    }
                    break;
                case -1406873644:
                    if (str.equals("Weather")) {
                        c = 1;
                        break;
                    }
                    break;
                case -322116978:
                    if (str.equals(BLUETOOTH_TAG)) {
                        c = 2;
                        break;
                    }
                    break;
                case 65665:
                    if (str.equals(ADD_TAG)) {
                        c = 3;
                        break;
                    }
                    break;
                case 2420678:
                    if (str.equals("Navi")) {
                        c = 4;
                        break;
                    }
                    break;
                case 74517264:
                    if (str.equals(MODE_TAG)) {
                        c = 5;
                        break;
                    }
                    break;
                case 74710533:
                    if (str.equals("Music")) {
                        c = 6;
                        break;
                    }
                    break;
                case 82650203:
                    if (str.equals("Video")) {
                        c = 7;
                        break;
                    }
                    break;
                case 870465087:
                    if (str.equals("AppList")) {
                        c = 8;
                        break;
                    }
                    break;
                case 956107380:
                    if (str.equals("Dashboard")) {
                        c = 9;
                        break;
                    }
                    break;
                case 1499275331:
                    if (str.equals("Settings")) {
                        c = 10;
                        break;
                    }
                    break;
            }
            switch (c) {
                case 0:
                    this.mContext.sendBroadcast(new Intent("com.szchoiceway.eventcenter.EventUtils.ACTION_SWITCH_ORIGINACAR"));
                    return;
                case 1:
                    EventUtils.startActivityIfNotRunning(this.mContext, "com.txznet.weather", "com.txznet.weather.MainActivity");
                    return;
                case 2:
                    enterBT();
                    return;
                case 3:
                    BMWMainFragment bMWMainFragment = this.mBmwMainFragment;
                    if (bMWMainFragment != null) {
                        bMWMainFragment.setEditMode(true);
                    }
                    saveOldFocus();
                    LauncherModel.getInstance().showAppList(true);
                    return;
                case 4:
                    enterNavi();
                    return;
                case 5:
                    if (this.mModeSelectPage == null) {
                        this.mModeSelectPage = new ModeSelectPage();
                    }
                    if (this.mModeSelectView == null) {
                        this.mModeSelectView = this.mModeSelectPage.getSetView(this.mContext);
                        this.mModeSelectPage.setDisplayModeChangeListener(new ModeSelectPage.DisplayModeChangeListener() {
                            public void onModeChange(int i, int i2) {
                                if (i != i2) {
                                    MainPageUIController.this.refreshItemDisplayMode();
                                    MainPageUIController.this.refreshLeftBtnBk();
                                    if (MainPageUIController.this.mMainItemAdapter != null) {
                                        MainPageUIController.this.mMainItemAdapter.setCurDisplayMode(i);
                                    }
                                }
                                MainPageUIController.this.hideModeSelectPage();
                            }
                        });
                    }
                    if (this.rlView != null && this.mModeSelectView != null) {
                        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams((int) this.mContext.getResources().getDimension(R.dimen.screenWidth), (int) this.mContext.getResources().getDimension(R.dimen.screenHeight));
                        layoutParams.leftMargin = 0;
                        layoutParams.topMargin = 0;
                        this.mModeSelectView.setLayoutParams(layoutParams);
                        this.rlView.addView(this.mModeSelectView);
                        FocusRecycleView focusRecycleView = this.mRecycleList;
                        if (focusRecycleView != null) {
                            focusRecycleView.setVisibility(8);
                        }
                        this.mInModeSelect = true;
                        if (this.mFocusObserver != null) {
                            saveOldFocus();
                            this.mFocusObserver.setLayout(this.mMainContent);
                            this.mFocusObserver.setmCurFocusViewId(R.id.btnModePersonal);
                            return;
                        }
                        return;
                    }
                    return;
                case 6:
                    enterMusic();
                    return;
                case 7:
                    enterVideo();
                    return;
                case 8:
                    BMWMainFragment bMWMainFragment2 = this.mBmwMainFragment;
                    if (bMWMainFragment2 != null) {
                        bMWMainFragment2.setEditMode(false);
                    }
                    saveOldFocus();
                    LauncherModel.getInstance().showAppList(true);
                    return;
                case 9:
                    EventUtils.onEnterDashBoard(this.mContext);
                    return;
                case 10:
                    EventUtils.startActivityIfNotRunning(this.mContext, "com.szchoiceway.settings", "com.szchoiceway.settings.MainActivity");
                    return;
                default:
                    Log.i(TAG, "startActivity: " + str);
                    String[] split = str.split("/");
                    if (split != null) {
                        try {
                            if (split.length == 2) {
                                String str2 = split[0];
                                String str3 = split[1];
                                if (str3.startsWith(".")) {
                                    str3 = str2 + split[1];
                                }
                                Log.i(TAG, "startActivity: " + str2 + "  |  " + str3);
                                LauncherModel instance = LauncherModel.getInstance();
                                if (str2.equals(EventUtils.LETTER_CARPLAY_MODE_PACKAGE_NAME)) {
                                    EventUtils.startActivityType(6, this.mContext, instance.getEvtService());
                                    return;
                                } else if (str2.equals(EventUtils.ZLINK_MODE_PACKAGE_NAME) && !str3.equals(EventUtils.ZLINK_DLNA_MODE_CLASS_NAME)) {
                                    EventUtils.startActivityType(7, this.mContext, instance.getEvtService());
                                    return;
                                } else if (this.auxPackageNames.contains(str2)) {
                                    try {
                                        if (instance.getEvtService() != null && instance.getEvtService().isUpgradeMode()) {
                                            return;
                                        }
                                    } catch (RemoteException e) {
                                        e.printStackTrace();
                                    }
                                    if (!"com.szchoiceway.ksw_dvr".equals(str2) || !"com.szchoiceway.ksw_dvr.MainActivity".equals(str3)) {
                                        EventUtils.startActivityIfNotRunning(this.mContext, str2, str3);
                                        return;
                                    } else {
                                        EventUtils.onEnterDvr(this.mContext);
                                        return;
                                    }
                                } else {
                                    if (instance != null) {
                                        try {
                                            if (instance.getEvtService() != null) {
                                                instance.getEvtService().sendKSW_0x00_0x67_third();
                                            }
                                        } catch (RemoteException e2) {
                                            e2.printStackTrace();
                                        }
                                    }
                                    EventUtils.startActivityIfNotRunning(this.mContext, str2, str3);
                                    return;
                                }
                            } else {
                                return;
                            }
                        } catch (Exception e3) {
                            e3.printStackTrace();
                            return;
                        }
                    } else {
                        return;
                    }
            }
        }
    }

    private void sendSurface(Surface surface) {
        Intent intent = new Intent("sendSurface_a");
        Bundle bundle = new Bundle();
        bundle.putParcelable("Surface", surface);
        intent.putExtra("BundleValue", bundle);
        this.mContext.sendBroadcast(intent);
    }

    /* access modifiers changed from: private */
    public void hideModeSelectPage() {
        View view;
        RelativeLayout relativeLayout = this.rlView;
        if (relativeLayout != null && (view = this.mModeSelectView) != null) {
            relativeLayout.removeView(view);
            FocusRecycleView focusRecycleView = this.mRecycleList;
            if (focusRecycleView != null) {
                focusRecycleView.setVisibility(0);
            }
            this.mInModeSelect = false;
            FocusObserver focusObserver = this.mFocusObserver;
            if (focusObserver != null) {
                focusObserver.setLayout(this.mMainContent);
                reSetFocus();
            }
        }
    }

    private void initListener() {
        this.mRecycleList.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            public void onRequestDisallowInterceptTouchEvent(boolean z) {
            }

            public boolean onInterceptTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {
                if (MainPageUIController.this.copyView.size() > 0 && MainPageUIController.this.itemPress) {
                    MainPageUIController mainPageUIController = MainPageUIController.this;
                    mainPageUIController.ryMoveEvent((View) mainPageUIController.copyView.get(MainPageUIController.this.copyView.size() - 1), motionEvent);
                }
                return !MainPageUIController.this.ryCanScroll;
            }

            public void onTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {
                Log.i(MainPageUIController.TAG, "onTouchEvent: " + motionEvent.getAction());
                if (MainPageUIController.this.copyView.size() > 0 && MainPageUIController.this.itemPress) {
                    MainPageUIController mainPageUIController = MainPageUIController.this;
                    mainPageUIController.ryMoveEvent((View) mainPageUIController.copyView.get(MainPageUIController.this.copyView.size() - 1), motionEvent);
                }
            }
        });
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x019c  */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x01f3  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void ryMoveEvent(android.view.View r18, android.view.MotionEvent r19) {
        /*
            r17 = this;
            r0 = r17
            r1 = r18
            if (r1 != 0) goto L_0x0007
            return
        L_0x0007:
            r2 = 2
            int[] r3 = new int[r2]
            r1.getLocationOnScreen(r3)
            int r4 = r19.getAction()
            java.lang.String r5 = "ryMoveEvent: 左侧"
            java.lang.String r6 = "|"
            java.lang.String r7 = " |  "
            java.lang.String r8 = "ryMoveEvent:xx---------- "
            r10 = 3
            r12 = 0
            java.lang.String r13 = "MainPageUIController"
            r14 = 1
            if (r4 == r14) goto L_0x0027
            if (r4 == r2) goto L_0x002c
            if (r4 == r10) goto L_0x0027
            goto L_0x0469
        L_0x0027:
            r2 = r5
            r11 = r13
            r5 = r14
            goto L_0x02b6
        L_0x002c:
            boolean r4 = r0.mStartMove
            if (r4 == 0) goto L_0x0040
            r0.mStartMove = r12
            float r4 = r19.getRawX()
            int r4 = (int) r4
            r0.startX = r4
            float r4 = r19.getRawY()
            int r4 = (int) r4
            r0.startY = r4
        L_0x0040:
            float r4 = r19.getRawX()
            int r4 = (int) r4
            float r10 = r19.getRawY()
            int r10 = (int) r10
            java.lang.StringBuilder r15 = new java.lang.StringBuilder
            r15.<init>()
            java.lang.String r11 = "ryMoveEvent:moveX    "
            java.lang.StringBuilder r11 = r15.append(r11)
            java.lang.StringBuilder r11 = r11.append(r4)
            java.lang.String r11 = r11.toString()
            android.util.Log.i(r13, r11)
            java.lang.StringBuilder r11 = new java.lang.StringBuilder
            r11.<init>()
            java.lang.String r15 = "ryMoveEvent:moveY    "
            java.lang.StringBuilder r11 = r11.append(r15)
            java.lang.StringBuilder r11 = r11.append(r10)
            java.lang.String r11 = r11.toString()
            android.util.Log.i(r13, r11)
            int r11 = r0.startX
            int r11 = r4 - r11
            int r15 = r0.startY
            int r15 = r10 - r15
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r9 = "ryMoveEvent:move_bigX    "
            java.lang.StringBuilder r2 = r2.append(r9)
            java.lang.StringBuilder r2 = r2.append(r11)
            java.lang.String r2 = r2.toString()
            android.util.Log.i(r13, r2)
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r9 = "ryMoveEvent:move_bigY    "
            java.lang.StringBuilder r2 = r2.append(r9)
            java.lang.StringBuilder r2 = r2.append(r15)
            java.lang.String r2 = r2.toString()
            android.util.Log.i(r13, r2)
            android.view.ViewGroup$LayoutParams r2 = r18.getLayoutParams()
            android.widget.RelativeLayout$LayoutParams r2 = (android.widget.RelativeLayout.LayoutParams) r2
            int r9 = r2.leftMargin
            int r14 = r2.topMargin
            int r9 = r9 + r11
            int r14 = r14 + r15
            java.lang.StringBuilder r11 = new java.lang.StringBuilder
            r11.<init>()
            java.lang.String r15 = "ryMoveEvent:left    "
            java.lang.StringBuilder r11 = r11.append(r15)
            java.lang.StringBuilder r11 = r11.append(r9)
            java.lang.String r11 = r11.toString()
            android.util.Log.i(r13, r11)
            java.lang.StringBuilder r11 = new java.lang.StringBuilder
            r11.<init>()
            java.lang.String r15 = "ryMoveEvent:v.getLeft="
            java.lang.StringBuilder r11 = r11.append(r15)
            int r15 = r18.getLeft()
            java.lang.StringBuilder r11 = r11.append(r15)
            java.lang.String r11 = r11.toString()
            android.util.Log.i(r13, r11)
            java.lang.StringBuilder r11 = new java.lang.StringBuilder
            r11.<init>()
            java.lang.String r15 = "ryMoveEvent:top    "
            java.lang.StringBuilder r11 = r11.append(r15)
            java.lang.StringBuilder r11 = r11.append(r14)
            java.lang.String r11 = r11.toString()
            android.util.Log.i(r13, r11)
            r2.setMargins(r9, r14, r12, r12)
            r1.setLayoutParams(r2)
            r0.startX = r4
            r0.startY = r10
            r2 = r3[r12]
            int r4 = r18.getWidth()
            int r2 = r2 + r4
            r4 = 1920(0x780, float:2.69E-42)
            java.lang.String r9 = "ryMoveEvent:lastIndex    "
            if (r2 <= r4) goto L_0x014d
            long r10 = android.os.SystemClock.elapsedRealtime()
            long r14 = r0.time
            long r14 = r10 - r14
            int r2 = r0.scorllTime
            r16 = r13
            long r12 = (long) r2
            int r2 = (r14 > r12 ? 1 : (r14 == r12 ? 0 : -1))
            if (r2 <= 0) goto L_0x014a
            r0.time = r10
            androidx.recyclerview.widget.LinearLayoutManager r2 = r0.mLayoutManage
            int r2 = r2.findLastVisibleItemPosition()
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            r10.<init>()
            java.lang.StringBuilder r9 = r10.append(r9)
            java.lang.StringBuilder r9 = r9.append(r2)
            java.lang.String r9 = r9.toString()
            r11 = r16
            android.util.Log.i(r11, r9)
            com.szchoiceway.zxwlib.focus.view.FocusRecycleView r9 = r0.mRecycleList
            r10 = 1
            int r2 = r2 + r10
            r9.scrollToPosition(r2)
            goto L_0x018c
        L_0x014a:
            r11 = r16
            goto L_0x018c
        L_0x014d:
            r2 = r12
            r11 = r13
            r10 = r3[r2]
            r2 = 192(0xc0, float:2.69E-43)
            if (r10 >= r2) goto L_0x018c
            long r12 = android.os.SystemClock.elapsedRealtime()
            long r14 = r0.time
            long r14 = r12 - r14
            int r2 = r0.scorllTime
            r16 = r5
            long r4 = (long) r2
            int r2 = (r14 > r4 ? 1 : (r14 == r4 ? 0 : -1))
            if (r2 <= 0) goto L_0x018e
            r0.time = r12
            androidx.recyclerview.widget.LinearLayoutManager r2 = r0.mLayoutManage
            int r2 = r2.findFirstVisibleItemPosition()
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.StringBuilder r4 = r4.append(r9)
            java.lang.StringBuilder r4 = r4.append(r2)
            java.lang.String r4 = r4.toString()
            android.util.Log.i(r11, r4)
            if (r2 <= 0) goto L_0x018e
            com.szchoiceway.zxwlib.focus.view.FocusRecycleView r4 = r0.mRecycleList
            r5 = 1
            int r2 = r2 - r5
            r4.scrollToPosition(r2)
            goto L_0x018f
        L_0x018c:
            r16 = r5
        L_0x018e:
            r5 = 1
        L_0x018f:
            com.szchoiceway.zxwlib.focus.view.FocusRecycleView r2 = r0.mRecycleList
            r4 = 0
            r9 = r3[r4]
            r10 = r3[r5]
            boolean r2 = r0.isInChangeImageZone(r2, r9, r10)
            if (r2 == 0) goto L_0x01f3
            com.szchoiceway.zxwlib.focus.view.FocusRecycleView r1 = r0.mRecycleList
            r2 = r3[r4]
            float r2 = (float) r2
            r3 = r3[r5]
            float r3 = (float) r3
            int r1 = r0.findChildIndexUnder(r1, r2, r3)
            androidx.recyclerview.widget.LinearLayoutManager r2 = r0.mLayoutManage
            int r2 = r2.findFirstVisibleItemPosition()
            int r1 = r1 + r2
            r0.to = r1
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.StringBuilder r1 = r1.append(r8)
            int r2 = r0.form
            java.lang.StringBuilder r1 = r1.append(r2)
            java.lang.StringBuilder r1 = r1.append(r7)
            int r2 = r0.to
            java.lang.StringBuilder r1 = r1.append(r2)
            java.lang.StringBuilder r1 = r1.append(r6)
            com.szchoiceway.customerui.bmw.recycle.MainItemAdapter r2 = r0.mMainItemAdapter
            java.util.List r2 = r2.getDatas()
            int r2 = r2.size()
            java.lang.StringBuilder r1 = r1.append(r2)
            java.lang.String r1 = r1.toString()
            android.util.Log.i(r11, r1)
            com.szchoiceway.customerui.bmw.recycle.MainItemAdapter r1 = r0.mMainItemAdapter
            if (r1 == 0) goto L_0x0469
            int r2 = r0.to
            r1.setPointerIndex(r2)
            com.szchoiceway.customerui.bmw.recycle.MainItemAdapter r0 = r0.mMainItemAdapter
            r0.notifyDataSetChanged()
            goto L_0x0469
        L_0x01f3:
            com.szchoiceway.customerui.bmw.recycle.MainItemAdapter r2 = r0.mMainItemAdapter
            if (r2 == 0) goto L_0x0200
            r5 = -1
            r2.setPointerIndex(r5)
            com.szchoiceway.customerui.bmw.recycle.MainItemAdapter r2 = r0.mMainItemAdapter
            r2.notifyDataSetChanged()
        L_0x0200:
            r2 = r16
            android.util.Log.i(r11, r2)
            r2 = 0
            r2 = r3[r2]
            r4 = 1
            r3 = r3[r4]
            int r1 = r18.getHeight()
            r4 = 2
            int r1 = r1 / r4
            int r3 = r3 + r1
            com.szchoiceway.zxwlib.focus.view.FocusIconWithTitleView r1 = r0.mLeftBtn2
            r4 = 0
            if (r1 == 0) goto L_0x021a
            r1.setForeground(r4)
        L_0x021a:
            com.szchoiceway.zxwlib.focus.view.FocusIconWithTitleView r1 = r0.mLeftBtn3
            if (r1 == 0) goto L_0x0221
            r1.setForeground(r4)
        L_0x0221:
            com.szchoiceway.zxwlib.focus.view.FocusIconWithTitleView r1 = r0.mLeftBtn4
            if (r1 == 0) goto L_0x0228
            r1.setForeground(r4)
        L_0x0228:
            com.szchoiceway.zxwlib.focus.view.FocusIconWithTitleView r1 = r0.mLeftBtn5
            if (r1 == 0) goto L_0x022f
            r1.setForeground(r4)
        L_0x022f:
            r1 = 1879573450(0x700803ca, float:1.6837817E29)
            int r4 = r0.mCurDisplayMode
            r5 = 1
            if (r4 == r5) goto L_0x023f
            r5 = 2
            if (r4 == r5) goto L_0x023b
            goto L_0x0242
        L_0x023b:
            r1 = 1879573452(0x700803cc, float:1.683782E29)
            goto L_0x0242
        L_0x023f:
            r1 = 1879573451(0x700803cb, float:1.6837819E29)
        L_0x0242:
            com.szchoiceway.zxwlib.focus.view.FocusIconWithTitleView r4 = r0.mLeftBtn2
            boolean r4 = r0.isInChangeImageZone(r4, r2, r3)
            if (r4 == 0) goto L_0x025f
            com.szchoiceway.zxwlib.focus.view.FocusIconWithTitleView r2 = r0.mLeftBtn2
            if (r2 == 0) goto L_0x0469
            android.content.Context r2 = r0.mContext
            if (r2 == 0) goto L_0x0469
            com.szchoiceway.zxwlib.focus.view.FocusIconWithTitleView r2 = r0.mLeftBtn2
            android.content.Context r0 = r0.mContext
            android.graphics.drawable.Drawable r0 = r0.getDrawable(r1)
            r2.setForeground(r0)
            goto L_0x0469
        L_0x025f:
            com.szchoiceway.zxwlib.focus.view.FocusIconWithTitleView r4 = r0.mLeftBtn3
            boolean r4 = r0.isInChangeImageZone(r4, r2, r3)
            if (r4 == 0) goto L_0x027c
            com.szchoiceway.zxwlib.focus.view.FocusIconWithTitleView r2 = r0.mLeftBtn2
            if (r2 == 0) goto L_0x0469
            android.content.Context r2 = r0.mContext
            if (r2 == 0) goto L_0x0469
            com.szchoiceway.zxwlib.focus.view.FocusIconWithTitleView r2 = r0.mLeftBtn3
            android.content.Context r0 = r0.mContext
            android.graphics.drawable.Drawable r0 = r0.getDrawable(r1)
            r2.setForeground(r0)
            goto L_0x0469
        L_0x027c:
            com.szchoiceway.zxwlib.focus.view.FocusIconWithTitleView r4 = r0.mLeftBtn4
            boolean r4 = r0.isInChangeImageZone(r4, r2, r3)
            if (r4 == 0) goto L_0x0299
            com.szchoiceway.zxwlib.focus.view.FocusIconWithTitleView r2 = r0.mLeftBtn4
            if (r2 == 0) goto L_0x0469
            android.content.Context r2 = r0.mContext
            if (r2 == 0) goto L_0x0469
            com.szchoiceway.zxwlib.focus.view.FocusIconWithTitleView r2 = r0.mLeftBtn4
            android.content.Context r0 = r0.mContext
            android.graphics.drawable.Drawable r0 = r0.getDrawable(r1)
            r2.setForeground(r0)
            goto L_0x0469
        L_0x0299:
            com.szchoiceway.zxwlib.focus.view.FocusIconWithTitleView r4 = r0.mLeftBtn5
            boolean r2 = r0.isInChangeImageZone(r4, r2, r3)
            if (r2 == 0) goto L_0x0469
            com.szchoiceway.zxwlib.focus.view.FocusIconWithTitleView r2 = r0.mLeftBtn5
            if (r2 == 0) goto L_0x0469
            android.content.Context r2 = r0.mContext
            if (r2 == 0) goto L_0x0469
            com.szchoiceway.zxwlib.focus.view.FocusIconWithTitleView r2 = r0.mLeftBtn5
            android.content.Context r0 = r0.mContext
            android.graphics.drawable.Drawable r0 = r0.getDrawable(r1)
            r2.setForeground(r0)
            goto L_0x0469
        L_0x02b6:
            r0.mStartMove = r5
            r4 = 0
            r0.itemPress = r4
            r0.ryCanScroll = r5
            java.util.List<android.view.View> r5 = r0.copyView
            r5.remove(r1)
            android.widget.RelativeLayout r5 = r0.rlView
            r5.removeView(r1)
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r9 = "ryMoveEvent:action_up   "
            java.lang.StringBuilder r5 = r5.append(r9)
            r9 = r3[r4]
            java.lang.StringBuilder r5 = r5.append(r9)
            java.lang.String r9 = " | "
            java.lang.StringBuilder r5 = r5.append(r9)
            r9 = 1
            r12 = r3[r9]
            java.lang.StringBuilder r5 = r5.append(r12)
            java.lang.String r5 = r5.toString()
            android.util.Log.i(r11, r5)
            com.szchoiceway.customerui.bmw.recycle.MainItemAdapter r5 = r0.mMainItemAdapter
            if (r5 == 0) goto L_0x02f4
            r9 = -1
            r5.setPointerIndex(r9)
        L_0x02f4:
            com.szchoiceway.zxwlib.focus.view.FocusIconWithTitleView r5 = r0.mLeftBtn2
            if (r5 == 0) goto L_0x0303
            android.content.Context r5 = r0.mContext
            if (r5 == 0) goto L_0x0303
            com.szchoiceway.zxwlib.focus.view.FocusIconWithTitleView r5 = r0.mLeftBtn2
            r9 = 0
            r5.setForeground(r9)
            goto L_0x0304
        L_0x0303:
            r9 = 0
        L_0x0304:
            com.szchoiceway.zxwlib.focus.view.FocusIconWithTitleView r5 = r0.mLeftBtn2
            if (r5 == 0) goto L_0x0311
            android.content.Context r5 = r0.mContext
            if (r5 == 0) goto L_0x0311
            com.szchoiceway.zxwlib.focus.view.FocusIconWithTitleView r5 = r0.mLeftBtn3
            r5.setForeground(r9)
        L_0x0311:
            com.szchoiceway.zxwlib.focus.view.FocusIconWithTitleView r5 = r0.mLeftBtn4
            if (r5 == 0) goto L_0x031e
            android.content.Context r5 = r0.mContext
            if (r5 == 0) goto L_0x031e
            com.szchoiceway.zxwlib.focus.view.FocusIconWithTitleView r5 = r0.mLeftBtn4
            r5.setForeground(r9)
        L_0x031e:
            com.szchoiceway.zxwlib.focus.view.FocusIconWithTitleView r5 = r0.mLeftBtn5
            if (r5 == 0) goto L_0x032b
            android.content.Context r5 = r0.mContext
            if (r5 == 0) goto L_0x032b
            com.szchoiceway.zxwlib.focus.view.FocusIconWithTitleView r5 = r0.mLeftBtn5
            r5.setForeground(r9)
        L_0x032b:
            com.szchoiceway.zxwlib.focus.view.FocusRecycleView r5 = r0.mRecycleList
            r4 = 0
            r9 = r3[r4]
            r12 = 1
            r13 = r3[r12]
            boolean r5 = r0.isInChangeImageZone(r5, r9, r13)
            if (r5 == 0) goto L_0x03c9
            com.szchoiceway.zxwlib.focus.view.FocusRecycleView r1 = r0.mRecycleList
            r2 = r3[r4]
            float r2 = (float) r2
            r3 = r3[r12]
            float r3 = (float) r3
            int r1 = r0.findChildIndexUnder(r1, r2, r3)
            androidx.recyclerview.widget.LinearLayoutManager r2 = r0.mLayoutManage
            int r2 = r2.findFirstVisibleItemPosition()
            int r1 = r1 + r2
            r0.to = r1
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.StringBuilder r1 = r1.append(r8)
            int r2 = r0.form
            java.lang.StringBuilder r1 = r1.append(r2)
            java.lang.StringBuilder r1 = r1.append(r7)
            int r2 = r0.to
            java.lang.StringBuilder r1 = r1.append(r2)
            java.lang.StringBuilder r1 = r1.append(r6)
            com.szchoiceway.customerui.bmw.recycle.MainItemAdapter r2 = r0.mMainItemAdapter
            java.util.List r2 = r2.getDatas()
            int r2 = r2.size()
            java.lang.StringBuilder r1 = r1.append(r2)
            java.lang.String r1 = r1.toString()
            android.util.Log.i(r11, r1)
            com.szchoiceway.customerui.bmw.recycle.MainItemAdapter r1 = r0.mMainItemAdapter
            if (r1 == 0) goto L_0x03a8
            java.util.List r1 = r1.getDatas()
            int r1 = r1.size()
            if (r1 <= 0) goto L_0x03a8
            int r1 = r0.form
            if (r1 < 0) goto L_0x03a8
            int r1 = r0.to
            if (r1 < 0) goto L_0x03a8
            com.szchoiceway.customerui.bmw.recycle.MainItemAdapter r1 = r0.mMainItemAdapter
            java.util.List r1 = r1.getDatas()
            int r2 = r0.form
            int r3 = r0.to
            java.util.Collections.swap(r1, r2, r3)
            com.szchoiceway.customerui.bmw.recycle.MainItemAdapter r1 = r0.mMainItemAdapter
            r1.notifyDataSetChanged()
        L_0x03a8:
            int r1 = r0.form
            if (r1 < 0) goto L_0x03be
            int r2 = r0.to
            if (r2 < 0) goto L_0x03be
            java.util.List<com.szchoiceway.customerui.bmw.recycle.RecycleItemBase> r3 = r0.mDatas
            java.util.Collections.swap(r3, r1, r2)
            java.util.List<java.lang.String> r1 = r0.mRecycleViewInfoList
            int r2 = r0.form
            int r3 = r0.to
            java.util.Collections.swap(r1, r2, r3)
        L_0x03be:
            com.szchoiceway.customerui.bmw.ui.MainApplistUtils r1 = r0.mMainApplistUtils
            if (r1 == 0) goto L_0x0469
            java.util.List<java.lang.String> r0 = r0.mRecycleViewInfoList
            r1.saveRecycleList(r0)
            goto L_0x0469
        L_0x03c9:
            android.util.Log.i(r11, r2)
            r2 = 0
            r2 = r3[r2]
            r4 = 1
            r3 = r3[r4]
            int r1 = r18.getHeight()
            r4 = 2
            int r1 = r1 / r4
            int r3 = r3 + r1
            com.szchoiceway.zxwlib.focus.view.FocusIconWithTitleView r1 = r0.mLeftBtn2
            boolean r1 = r0.isInChangeImageZone(r1, r2, r3)
            if (r1 == 0) goto L_0x03fa
            java.util.List<java.lang.String> r1 = r0.mRecycleViewInfoList
            int r2 = r0.form
            java.lang.Object r1 = r1.get(r2)
            java.lang.String r1 = (java.lang.String) r1
            java.util.LinkedList<java.lang.String> r2 = r0.mLeftBtnInfoList
            boolean r2 = r2.contains(r1)
            if (r2 != 0) goto L_0x045b
            java.util.LinkedList<java.lang.String> r2 = r0.mLeftBtnInfoList
            r3 = 1
            r2.set(r3, r1)
            goto L_0x045b
        L_0x03fa:
            com.szchoiceway.zxwlib.focus.view.FocusIconWithTitleView r1 = r0.mLeftBtn3
            boolean r1 = r0.isInChangeImageZone(r1, r2, r3)
            if (r1 == 0) goto L_0x041b
            java.util.List<java.lang.String> r1 = r0.mRecycleViewInfoList
            int r2 = r0.form
            java.lang.Object r1 = r1.get(r2)
            java.lang.String r1 = (java.lang.String) r1
            java.util.LinkedList<java.lang.String> r2 = r0.mLeftBtnInfoList
            boolean r2 = r2.contains(r1)
            if (r2 != 0) goto L_0x045b
            java.util.LinkedList<java.lang.String> r2 = r0.mLeftBtnInfoList
            r3 = 2
            r2.set(r3, r1)
            goto L_0x045b
        L_0x041b:
            com.szchoiceway.zxwlib.focus.view.FocusIconWithTitleView r1 = r0.mLeftBtn4
            boolean r1 = r0.isInChangeImageZone(r1, r2, r3)
            if (r1 == 0) goto L_0x043b
            java.util.List<java.lang.String> r1 = r0.mRecycleViewInfoList
            int r2 = r0.form
            java.lang.Object r1 = r1.get(r2)
            java.lang.String r1 = (java.lang.String) r1
            java.util.LinkedList<java.lang.String> r2 = r0.mLeftBtnInfoList
            boolean r2 = r2.contains(r1)
            if (r2 != 0) goto L_0x045b
            java.util.LinkedList<java.lang.String> r2 = r0.mLeftBtnInfoList
            r2.set(r10, r1)
            goto L_0x045b
        L_0x043b:
            com.szchoiceway.zxwlib.focus.view.FocusIconWithTitleView r1 = r0.mLeftBtn5
            boolean r1 = r0.isInChangeImageZone(r1, r2, r3)
            if (r1 == 0) goto L_0x045b
            java.util.List<java.lang.String> r1 = r0.mRecycleViewInfoList
            int r2 = r0.form
            java.lang.Object r1 = r1.get(r2)
            java.lang.String r1 = (java.lang.String) r1
            java.util.LinkedList<java.lang.String> r2 = r0.mLeftBtnInfoList
            boolean r2 = r2.contains(r1)
            if (r2 != 0) goto L_0x045b
            java.util.LinkedList<java.lang.String> r2 = r0.mLeftBtnInfoList
            r3 = 4
            r2.set(r3, r1)
        L_0x045b:
            com.szchoiceway.customerui.bmw.ui.MainApplistUtils r1 = r0.mMainApplistUtils
            if (r1 == 0) goto L_0x0466
            java.util.LinkedList<java.lang.String> r2 = r0.mLeftBtnInfoList
            int r3 = r0.m_iModeSet
            r1.saveLeftBtnList(r2, r3)
        L_0x0466:
            r17.refreshLeftBtn()
        L_0x0469:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.szchoiceway.customerui.bmw.ui.MainPageUIController.ryMoveEvent(android.view.View, android.view.MotionEvent):void");
    }

    private boolean isInChangeImageZone(View view, int i, int i2) {
        if (view == null) {
            return false;
        }
        if (this.mChangeImageBackgroundRect == null) {
            this.mChangeImageBackgroundRect = new Rect();
        }
        view.getDrawingRect(this.mChangeImageBackgroundRect);
        int[] iArr = new int[2];
        view.getLocationOnScreen(iArr);
        this.mChangeImageBackgroundRect.left = iArr[0];
        this.mChangeImageBackgroundRect.top = iArr[1];
        this.mChangeImageBackgroundRect.right += iArr[0];
        this.mChangeImageBackgroundRect.bottom += iArr[1];
        return this.mChangeImageBackgroundRect.contains(i, i2);
    }

    private void copyItem(View view, int i) {
        this.form = i;
        int[] iArr = new int[2];
        view.getLocationOnScreen(iArr);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(view.getWidth(), view.getHeight());
        layoutParams.leftMargin = iArr[0] + 10;
        layoutParams.topMargin = 10;
        Bitmap viewToBitmap = getViewToBitmap(view);
        ImageView imageView = new ImageView(this.mContext);
        imageView.setImageBitmap(viewToBitmap);
        imageView.setLayoutParams(layoutParams);
        Log.i(TAG, "copyItem:-------- " + layoutParams.leftMargin);
        imageView.setAlpha(0.5f);
        this.copyView.add(imageView);
        this.rlView.addView(imageView);
    }

    private Bitmap getViewToBitmap(View view) {
        Bitmap createBitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
        view.draw(new Canvas(createBitmap));
        return createBitmap;
    }

    public int findChildIndexUnder(View view, float f, float f2) {
        int left = view.getLeft();
        float f3 = f - ((float) left);
        float top = f2 - ((float) view.getTop());
        Log.i(TAG, "findChildViewUnder:    " + f3 + "   |   " + top);
        if (!(view instanceof ViewGroup)) {
            ((ViewGroup) view).getChildCount();
            return -1;
        } else if (!(view instanceof RecyclerView)) {
            return -1;
        } else {
            Log.i(TAG, "findChildViewUnder: left = " + left);
            int childCount = ((RecyclerView) view).getChildCount();
            Log.i("TAG", "findChildViewUnder:RecyclerView    " + childCount);
            for (int i = 0; i < childCount; i++) {
                RelativeLayout relativeLayout = (RelativeLayout) ((ViewGroup) view).getChildAt(i);
                Log.i(TAG, "findChildViewUnder: " + relativeLayout.toString());
                float translationX = relativeLayout.getTranslationX();
                float translationY = relativeLayout.getTranslationY();
                if (f3 >= ((float) relativeLayout.getLeft()) + translationX && f3 <= ((float) relativeLayout.getRight()) + translationX && top >= ((float) relativeLayout.getTop()) + translationY && top <= ((float) relativeLayout.getBottom()) + translationY) {
                    return i;
                }
            }
            return -1;
        }
    }

    /* access modifiers changed from: private */
    public DragAppInfo getAppInfo(String str, String str2) {
        ApplicationInfo applicationInfo;
        Log.i(TAG, "getAppInfo:getre   " + str + " |  activityName " + str2);
        DragAppInfo dragAppInfo = new DragAppInfo();
        PackageManager packageManager = this.mContext.getPackageManager();
        try {
            applicationInfo = packageManager.getApplicationInfo(str, 128);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            applicationInfo = null;
        }
        if (applicationInfo == null) {
            return null;
        }
        String charSequence = packageManager.getApplicationLabel(applicationInfo).toString();
        Log.i(TAG, "getAppInfo:getrexxx   " + charSequence);
        Drawable applicationIcon = packageManager.getApplicationIcon(applicationInfo);
        if (str2.startsWith(".")) {
            str2 = str + str2;
        }
        Log.i(TAG, "getAppInfo:getrexxx  className   " + str2);
        dragAppInfo.setTag(str2);
        dragAppInfo.setAppName(charSequence);
        dragAppInfo.setDrawable(applicationIcon);
        dragAppInfo.setAppClassName(str2);
        dragAppInfo.setAppPackageName(str);
        return dragAppInfo;
    }

    public void addItem(DragAppInfo dragAppInfo) {
        String appPackageName = dragAppInfo.getAppPackageName();
        String appClassName = dragAppInfo.getAppClassName();
        String substring = appClassName.substring(appPackageName.length());
        if (!substring.startsWith(".")) {
            "." + substring;
        }
        String str = appPackageName + "/" + appClassName;
        Log.i(TAG, "addItem: " + str);
        if (!this.mRecycleViewInfoList.contains(str)) {
            this.mRecycleViewInfoList.add(str);
            MainApplistUtils mainApplistUtils = this.mMainApplistUtils;
            if (mainApplistUtils != null) {
                mainApplistUtils.saveRecycleList(this.mRecycleViewInfoList);
            }
            checkDatasLast();
            ThirdAppItem thirdAppItem = new ThirdAppItem();
            thirdAppItem.setInfo(dragAppInfo);
            thirdAppItem.setMainPageUIController(this);
            this.mDatas.add(thirdAppItem);
            if (this.mDatas.size() < 15) {
                this.mDatas.add(new AddItem());
            }
            MainItemAdapter mainItemAdapter = this.mMainItemAdapter;
            if (mainItemAdapter != null) {
                mainItemAdapter.setDatas(this.mDatas);
            }
        }
    }

    public void unInit() {
        MainPageReceiver mainPageReceiver = this.mMainPageReceiver;
        if (mainPageReceiver != null) {
            mainPageReceiver.unregisterRec();
        }
    }

    public void setMusicCoverBg(Bitmap bitmap) {
        IEventService evtService = LauncherModel.getInstance().getEvtService();
        if (evtService != null) {
            try {
                if (evtService.getValidMode() == EventUtils.eSrcMode.SRC_MUSIC.getIntValue()) {
                    ((MusicItem) this.mDefaultItemMap.get("Music")).setIcon(bitmap);
                }
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean onKeyDown(int i) {
        if (i == 4 || i == 3) {
            MainItemAdapter mainItemAdapter = this.mMainItemAdapter;
            if (mainItemAdapter != null && mainItemAdapter.ismSmallMode() && this.mRecycleList != null) {
                this.mMainItemAdapter.setmSmallMode(false);
                this.mRecycleList.setScrollbarFadingEnabled(true);
                if (Build.VERSION.SDK_INT >= 29) {
                    this.mRecycleList.setHorizontalScrollbarThumbDrawable((Drawable) null);
                }
                initRecycleDatas();
                this.mMainItemAdapter.setDatas(this.mDatas);
                reSetFocus();
                return true;
            } else if (this.mInModeSelect) {
                hideModeSelectPage();
                return true;
            }
        }
        return false;
    }

    public void setBtStatus() {
        this.mBTState = LauncherModel.getInstance().btConnectStatus;
        PhoneItem phoneItem = (PhoneItem) this.mDefaultItemMap.get(BLUETOOTH_TAG);
        if (phoneItem != null) {
            phoneItem.setBtState(this.mBTState);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:33:0x00b2, code lost:
        if (r7 == 2) goto L_0x00b6;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setMainFocusMove(int r7, boolean r8) {
        /*
            r6 = this;
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "setMainFocusMove: "
            java.lang.StringBuilder r0 = r0.append(r1)
            java.lang.StringBuilder r0 = r0.append(r7)
            java.lang.String r1 = " } "
            java.lang.StringBuilder r0 = r0.append(r1)
            java.lang.StringBuilder r0 = r0.append(r8)
            java.lang.String r0 = r0.toString()
            java.lang.String r2 = "MainPageUIController"
            android.util.Log.i(r2, r0)
            if (r7 >= 0) goto L_0x0025
            return
        L_0x0025:
            boolean r0 = r6.needMoveByF()
            if (r0 == 0) goto L_0x00bb
            if (r8 == 0) goto L_0x00bb
            boolean r0 = r6.mVisible
            if (r0 != 0) goto L_0x0033
            goto L_0x00bb
        L_0x0033:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r3 = "setMainFocusMove:------ "
            java.lang.StringBuilder r0 = r0.append(r3)
            java.lang.StringBuilder r0 = r0.append(r7)
            java.lang.StringBuilder r0 = r0.append(r1)
            java.lang.StringBuilder r8 = r0.append(r8)
            java.lang.String r8 = r8.toString()
            android.util.Log.i(r2, r8)
            r8 = 7
            r0 = 4
            r1 = 3
            if (r7 != r8) goto L_0x0058
            r7 = r1
            goto L_0x005d
        L_0x0058:
            r8 = 8
            if (r7 != r8) goto L_0x005d
            r7 = r0
        L_0x005d:
            com.szchoiceway.zxwlib.focus.FocusObserver r8 = r6.mFocusObserver
            if (r8 == 0) goto L_0x00bb
            int r8 = r8.getmCurFocusViewId()
            r2 = 1879638835(0x70090333, float:1.6961326E29)
            if (r8 != r2) goto L_0x00b5
            com.szchoiceway.zxwlib.focus.FocusObserver r8 = r6.mFocusObserver
            int r8 = r8.getmCurPosition()
            r3 = 1
            if (r8 != 0) goto L_0x0095
            if (r7 != r1) goto L_0x00ab
            com.szchoiceway.zxwlib.focus.view.FocusRecycleView r2 = r6.mRecycleList
            java.util.List<java.lang.Integer> r4 = r6.leftBtnIds
            int r5 = r4.size()
            int r5 = r5 - r3
            java.lang.Object r4 = r4.get(r5)
            java.lang.Integer r4 = (java.lang.Integer) r4
            int r4 = r4.intValue()
            r2.setNextFocusUpId(r4)
            com.szchoiceway.zxwlib.focus.FocusObserver r2 = r6.mFocusObserver
            if (r2 == 0) goto L_0x00ab
            androidx.constraintlayout.widget.ConstraintLayout r4 = r6.mMainContent
            r2.setLayout(r4)
            goto L_0x00ab
        L_0x0095:
            com.szchoiceway.zxwlib.focus.view.FocusRecycleView r4 = r6.mRecycleList
            int r4 = r4.getNextFocusUpId()
            if (r4 == r2) goto L_0x00ab
            com.szchoiceway.zxwlib.focus.view.FocusRecycleView r4 = r6.mRecycleList
            r4.setNextFocusUpId(r2)
            com.szchoiceway.zxwlib.focus.FocusObserver r2 = r6.mFocusObserver
            if (r2 == 0) goto L_0x00ab
            androidx.constraintlayout.widget.ConstraintLayout r4 = r6.mMainContent
            r2.setLayout(r4)
        L_0x00ab:
            if (r7 != r3) goto L_0x00b1
            if (r8 <= 0) goto L_0x00b5
            r0 = r1
            goto L_0x00b6
        L_0x00b1:
            r8 = 2
            if (r7 != r8) goto L_0x00b5
            goto L_0x00b6
        L_0x00b5:
            r0 = r7
        L_0x00b6:
            com.szchoiceway.zxwlib.focus.FocusObserver r6 = r6.mFocusObserver
            r6.doNextFocus(r0)
        L_0x00bb:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.szchoiceway.customerui.bmw.ui.MainPageUIController.setMainFocusMove(int, boolean):void");
    }

    private boolean needMoveByF() {
        if (this.mBTState > 3) {
            return false;
        }
        MainItemAdapter mainItemAdapter = this.mMainItemAdapter;
        return mainItemAdapter == null || !mainItemAdapter.ismSmallMode();
    }

    public void onVisibilityChanged(int i) {
        FocusObserver focusObserver;
        ConstraintLayout constraintLayout;
        this.mVisible = i == 0;
        if (i == 0) {
            MainItemAdapter mainItemAdapter = this.mMainItemAdapter;
            if (mainItemAdapter != null && !mainItemAdapter.ismSmallMode() && (focusObserver = this.mFocusObserver) != null && (constraintLayout = this.mMainContent) != null) {
                focusObserver.setLayout(constraintLayout);
                reSetFocus();
                return;
            }
            return;
        }
        saveOldFocus();
    }

    public void setSpeed(int i) {
        this.iSpeed = i;
        DashboardItem dashboardItem = (DashboardItem) this.mDefaultItemMap.get("Dashboard");
        if (dashboardItem != null) {
            dashboardItem.setSpeed(i);
        }
    }

    public void setSpeedUnit() {
        DashboardItem dashboardItem = (DashboardItem) this.mDefaultItemMap.get("Dashboard");
        if (dashboardItem != null) {
            dashboardItem.setSpeedUnit();
        }
    }

    public void setHandbrakeStatus(boolean z) {
        DashboardItem dashboardItem = (DashboardItem) this.mDefaultItemMap.get("Dashboard");
        if (dashboardItem != null) {
            dashboardItem.setHandbrakeStatus(z);
        }
    }

    public void setSeatBeltStatus(boolean z) {
        DashboardItem dashboardItem = (DashboardItem) this.mDefaultItemMap.get("Dashboard");
        if (dashboardItem != null) {
            dashboardItem.setSeatBeltStatus(z);
        }
    }

    public void setQilValue(String str) {
        DashboardItem dashboardItem = (DashboardItem) this.mDefaultItemMap.get("Dashboard");
        if (dashboardItem != null) {
            dashboardItem.setQilValue(str);
        }
    }

    public void refreshWeatherInfo(WeatherInfo weatherInfo, HashMap<String, String> hashMap, List<String> list) {
        Log.d(TAG, "refreshWeatherInfo");
        this.mWeatherInfo = weatherInfo;
        this.mWeatherDetailMap = hashMap;
        this.mWeatherDetailList = list;
        WeatherItem weatherItem = (WeatherItem) this.mDefaultItemMap.get("Weather");
        if (weatherItem != null) {
            weatherItem.setWeatherInfo(weatherInfo, hashMap, list);
        }
    }

    public void refreshWeatherFailed(int i) {
        Log.d(TAG, "refreshWeatherFailed");
        this.mWeatherFailedCode = i;
        WeatherItem weatherItem = (WeatherItem) this.mDefaultItemMap.get("Weather");
        if (weatherItem != null) {
            weatherItem.refreshWeatherFailed(i);
        }
    }

    public void setCurPlayVideoPath(String str) {
        VideoItem videoItem = (VideoItem) this.mDefaultItemMap.get("Video");
        if (videoItem != null) {
            videoItem.setCurPlayVideoPath(str);
        }
    }

    private void checkDatasLast() {
        int size = this.mDatas.size() - 1;
        if (ADD_TAG.equals(this.mDatas.get(size).getTag())) {
            this.mDatas.remove(size);
        }
    }

    public void deleteItem(String str) {
        Log.i(TAG, "deleteItem:start-------------  " + this.mRecycleViewInfoList.size());
        Log.i(TAG, "deleteItem:start-------tag------  " + str);
        int indexOf = this.mRecycleViewInfoList.indexOf(str);
        Log.i(TAG, "deleteItem: " + indexOf);
        if (indexOf >= 0 && indexOf < this.mRecycleViewInfoList.size()) {
            this.mRecycleViewInfoList.remove(indexOf);
            Log.i(TAG, "deleteItem:end--------------   " + this.mRecycleViewInfoList.size());
            MainApplistUtils mainApplistUtils = this.mMainApplistUtils;
            if (mainApplistUtils != null) {
                mainApplistUtils.saveRecycleList(this.mRecycleViewInfoList);
            }
            if (this.mMainItemAdapter != null) {
                checkDatasLast();
                this.mDatas.remove(indexOf);
                this.mMainItemAdapter.setDatas(this.mDatas);
                int size = this.mDatas.size();
                Log.i(TAG, "deleteItem:index=  " + indexOf + "  | len=  " + size);
                if (size < 15) {
                    this.mDatas.add(new AddItem());
                }
            }
        }
    }

    public void deleteItemByAppRemoved(String str) {
        Log.i(TAG, "deleteItemByAppRemoved:start-------------  " + this.mRecycleViewInfoList.size());
        int indexOf = this.mRecycleViewInfoList.indexOf(str);
        Log.i(TAG, "deleteItemByAppRemoved: " + indexOf);
        if (indexOf >= 0 && indexOf < this.mRecycleViewInfoList.size()) {
            this.mRecycleViewInfoList.remove(indexOf);
            Log.i(TAG, "deleteItemByAppRemoved:end--------------   " + this.mRecycleViewInfoList.size());
            MainApplistUtils mainApplistUtils = this.mMainApplistUtils;
            if (mainApplistUtils != null) {
                mainApplistUtils.saveRecycleList(this.mRecycleViewInfoList);
            }
            if (this.mMainItemAdapter != null) {
                checkDatasLast();
                this.mDatas.remove(indexOf);
                int size = this.mDatas.size();
                Log.i(TAG, "deleteItemByAppRemoved:index=  " + indexOf + "  | len=  " + size);
                if (size < 15) {
                    this.mDatas.add(new AddItem());
                }
                this.mMainItemAdapter.setDatas(this.mDatas);
            }
        }
        if (this.mLeftBtnInfoList.contains(str)) {
            this.mLeftBtnInfoList.set(this.mLeftBtnInfoList.indexOf(str), "Deleted");
            refreshLeftBtn();
            MainApplistUtils mainApplistUtils2 = this.mMainApplistUtils;
            if (mainApplistUtils2 != null) {
                mainApplistUtils2.saveLeftBtnList(this.mLeftBtnInfoList, this.m_iModeSet);
            }
        }
    }

    public class MainPageReceiver extends BroadcastReceiver {
        private boolean isReg = false;

        public MainPageReceiver() {
        }

        public void registerRec() {
            if (!this.isReg) {
                IntentFilter intentFilter = new IntentFilter();
                intentFilter.addAction("android.intent.action.PACKAGE_REMOVED");
                intentFilter.addDataScheme("package");
                MainPageUIController.this.mContext.registerReceiver(this, intentFilter);
                this.isReg = true;
                Log.i(MainPageUIController.TAG, "registerRec: onReceivehgeohgoe");
            }
        }

        public void unregisterRec() {
            if (this.isReg) {
                Log.i(MainPageUIController.TAG, "unregisterRec: onReceivehgeohgoe");
                MainPageUIController.this.mContext.unregisterReceiver(this);
                this.isReg = false;
            }
        }

        public void onReceive(Context context, Intent intent) {
            String[] split;
            if ("android.intent.action.PACKAGE_REMOVED".equals(intent.getAction())) {
                ArrayList<String> arrayList = new ArrayList<>();
                arrayList.addAll(MainPageUIController.this.mLeftBtnInfoList);
                arrayList.addAll(MainPageUIController.this.mRecycleViewInfoList);
                Log.i(MainPageUIController.TAG, "onReceivehgeohgoe: " + arrayList.size());
                for (String str : arrayList) {
                    if (!MainPageUIController.this.mDefaultIconMap.containsKey(str) && (split = str.split("/")) != null && split.length == 2 && MainPageUIController.this.getAppInfo(split[0], split[1]) == null) {
                        MainPageUIController.this.deleteItemByAppRemoved(str);
                        return;
                    }
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public void refreshItemDisplayMode() {
        for (RecycleItemBase updateInfo : this.mDatas) {
            updateInfo.updateInfo();
        }
        MainItemAdapter mainItemAdapter = this.mMainItemAdapter;
        if (mainItemAdapter != null) {
            mainItemAdapter.setDatas(this.mDatas);
        }
    }

    /* access modifiers changed from: private */
    public void refreshLeftBtnBk() {
        int recordInteger = SysProviderOpt.getInstance(this.mContext).getRecordInteger("KESAIWEI_SYS_DISPLAY_MODE", 0);
        this.mCurDisplayMode = recordInteger;
        int i = R.drawable.id8_main_left_btn_b_f;
        int i2 = R.drawable.bmw_id8_left_btn_forground;
        if (recordInteger != 0) {
            if (recordInteger != 1) {
                if (recordInteger == 2) {
                    if (this.m_iModeSet == 20) {
                        i2 = R.drawable.pemp_bmw_id8_left_btn_forground_yellow;
                        i = R.drawable.pemp_id8_main_left_btn_y_f;
                    } else {
                        i2 = R.drawable.bmw_id8_left_btn_forground_yellow;
                        i = R.drawable.id8_main_left_btn_y_f;
                    }
                }
            } else if (this.m_iModeSet == 20) {
                i2 = R.drawable.pemp_bmw_id8_left_btn_forground_red;
                i = R.drawable.pemp_id8_main_left_btn_r_f;
            } else {
                i2 = R.drawable.bmw_id8_left_btn_forground_red;
                i = R.drawable.id8_main_left_btn_r_f;
            }
        } else if (this.m_iModeSet == 20) {
            i2 = R.drawable.pemp_bmw_id8_left_btn_forground;
            i = R.drawable.pemp_id8_main_left_btn_b_f;
        }
        refreshLeftBtnBk(i2, i);
    }

    private void refreshLeftBtnBk(int i, int i2) {
        FocusIconWithTitleView focusIconWithTitleView = this.mLeftBtn1;
        if (focusIconWithTitleView != null) {
            focusIconWithTitleView.setImageForeground(i);
            this.mLeftBtn1.setFocusedDrawable(i2);
        }
        FocusIconWithTitleView focusIconWithTitleView2 = this.mLeftBtn2;
        if (focusIconWithTitleView2 != null) {
            focusIconWithTitleView2.setImageForeground(i);
            this.mLeftBtn2.setFocusedDrawable(i2);
        }
        FocusIconWithTitleView focusIconWithTitleView3 = this.mLeftBtn3;
        if (focusIconWithTitleView3 != null) {
            focusIconWithTitleView3.setImageForeground(i);
            this.mLeftBtn3.setFocusedDrawable(i2);
        }
        FocusIconWithTitleView focusIconWithTitleView4 = this.mLeftBtn4;
        if (focusIconWithTitleView4 != null) {
            focusIconWithTitleView4.setImageForeground(i);
            this.mLeftBtn4.setFocusedDrawable(i2);
        }
        FocusIconWithTitleView focusIconWithTitleView5 = this.mLeftBtn5;
        if (focusIconWithTitleView5 != null) {
            focusIconWithTitleView5.setImageForeground(i);
            this.mLeftBtn5.setFocusedDrawable(i2);
        }
    }

    private void saveOldFocus() {
        FocusObserver focusObserver = this.mFocusObserver;
        if (focusObserver != null) {
            this.mOldFocusId = focusObserver.getmCurFocusViewId();
            this.mOldFocusItemIndex = this.mFocusObserver.getmCurPosition();
        }
    }

    private void reSetFocus() {
        FocusObserver focusObserver = this.mFocusObserver;
        if (focusObserver != null) {
            if (this.mOldFocusId == 0) {
                this.mOldFocusId = R.id.leftBtn1;
            }
            focusObserver.setmCurFocusViewId(this.mOldFocusId);
            Log.i(TAG, "reSetFocus: " + Integer.toHexString(this.mOldFocusId) + " | " + this.mOldFocusItemIndex);
            if (this.mOldFocusId == R.id.recycleList && this.mRecycleList != null && this.mMainItemAdapter != null) {
                Log.i(TAG, "reSetFocus: " + this.mMainItemAdapter.getItemCount());
                if (this.mOldFocusItemIndex >= this.mMainItemAdapter.getItemCount()) {
                    this.mOldFocusItemIndex = this.mRecycleList.getFirstVisiblePosition();
                }
                Log.i(TAG, "reSetFocus:mOldFocusItemIndex =  " + this.mOldFocusItemIndex);
                this.mFocusObserver.setmCurPosition(this.mOldFocusItemIndex);
                MainItemAdapter mainItemAdapter = this.mMainItemAdapter;
                if (mainItemAdapter != null) {
                    mainItemAdapter.setCurFocusItemPosition(this.mOldFocusItemIndex);
                }
                this.mRecycleList.scrollToPosition(this.mOldFocusItemIndex);
            }
        }
    }

    private void enterNavi() {
        EventUtils.startActivityType(2, this.mContext);
    }

    private void enterMusic() {
        String recordValue = SysProviderOpt.getInstance(this.mContext).getRecordValue(SysProviderOpt.MUSIC_PACKAGENAME, "com.szchoiceway.musicplayer");
        String recordValue2 = SysProviderOpt.getInstance(this.mContext).getRecordValue(SysProviderOpt.MUSIC_ACTIVITYNAME, "com.szchoiceway.musicplayer.MainActivity");
        if (!"com.szchoiceway.musicplayer".equals(recordValue)) {
            try {
                IEventService evtService = LauncherModel.getInstance().getEvtService();
                if (evtService != null) {
                    evtService.sendMode(EventUtils.eSrcMode.SRC_MUSIC.getIntValue(), false);
                }
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        EventUtils.startActivityIfNotRunning(this.mContext, recordValue, recordValue2);
    }

    private void enterVideo() {
        String recordValue = SysProviderOpt.getInstance(this.mContext).getRecordValue("Video_PackageName", "com.szchoiceway.videoplayer");
        String recordValue2 = SysProviderOpt.getInstance(this.mContext).getRecordValue("Video_ActivityName", "com.szchoiceway.videoplayer.MainActivity");
        if (!"com.szchoiceway.videoplayer".equals(recordValue)) {
            try {
                IEventService evtService = LauncherModel.getInstance().getEvtService();
                if (evtService != null) {
                    evtService.sendMode(EventUtils.eSrcMode.SRC_MOVIE.getIntValue(), false);
                }
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        } else {
            sendSurface((Surface) null);
        }
        EventUtils.startActivityIfNotRunning(this.mContext, recordValue, recordValue2);
    }

    private void enterBT() {
        EventUtils.startActivityType(3, this.mContext);
    }

    private void initAuxPackageNames() {
        this.auxPackageNames.add("com.szchoiceway.ksw_aux");
        this.auxPackageNames.add("com.szchoiceway.ksw_cmmb");
        this.auxPackageNames.add("com.szchoiceway.ksw_dvd");
        this.auxPackageNames.add("com.szchoiceway.ksw_dvr");
        this.auxPackageNames.add("com.szchoiceway.ksw_fc");
    }
}
