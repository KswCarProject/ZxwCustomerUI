package com.szchoiceway.customerui.fragment;

import android.animation.ValueAnimator;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.graphics.Bitmap;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.RemoteException;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.constraintlayout.widget.Group;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import com.szchoiceway.customerui.R;
import com.szchoiceway.customerui.adapter.HorizontalPageLayoutManager;
import com.szchoiceway.customerui.adapter.PagingScrollHelper;
import com.szchoiceway.customerui.base.BaseView;
import com.szchoiceway.customerui.dialog.AppListDialog;
import com.szchoiceway.customerui.dialog.BenzControlDialog;
import com.szchoiceway.customerui.dialog.ThemeDialog;
import com.szchoiceway.customerui.fragment.launcher.strategy.ILauncherStrategy;
import com.szchoiceway.customerui.fragment.launcher.strategy.LauncherStrategyUtil;
import com.szchoiceway.customerui.kt_like.ArraysUtil;
import com.szchoiceway.customerui.kt_like.Function1Void;
import com.szchoiceway.customerui.kt_like.JavaStandard;
import com.szchoiceway.customerui.mianitem.ItemBase;
import com.szchoiceway.customerui.mianitem.ItemBt;
import com.szchoiceway.customerui.mianitem.ItemDashboard;
import com.szchoiceway.customerui.mianitem.ItemMusic;
import com.szchoiceway.customerui.mianitem.ItemTag;
import com.szchoiceway.customerui.mianitem.ItemUtil;
import com.szchoiceway.customerui.mianitem.MainAdapter;
import com.szchoiceway.customerui.mianitem.MyItemTouchHelperCallback;
import com.szchoiceway.customerui.model.LauncherModel;
import com.szchoiceway.customerui.utils.AppInfo;
import com.szchoiceway.customerui.utils.DebounceHelper;
import com.szchoiceway.customerui.utils.EventUtils;
import com.szchoiceway.customerui.utils.MultipleUtils;
import com.szchoiceway.customerui.utils.SysProviderOpt;
import com.szchoiceway.customerui.utils.WeatherIconUtil;
import com.szchoiceway.customerui.views.AudiGT6LoopRotarySwitchView;
import com.szchoiceway.customerui.views.MyImageButton;
import com.szchoiceway.customerui.views.MyQAnalogClock2;
import com.szchoiceway.customerui.views.MySeekbar;
import com.szchoiceway.customerui.views.MyViewPager;
import com.szchoiceway.customerui.views.PressedView;
import com.szchoiceway.eventcenter.IEventService;
import com.szchoiceway.zxwlib.GyroScopeWithCompassView;
import com.szchoiceway.zxwlib.bean.Customer;
import com.txznet.weatherquery.TXZWeather;
import com.txznet.weatherquery.WeatherQueryManager;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class MainFragment extends BaseView implements View.OnClickListener, View.OnLongClickListener, ThemeDialog.OnBackgroundChangeListener, ThemeDialog.OnThemeChangeListener, PagingScrollHelper.onPageChangeListener {
    public static final String ACTION_LAUNCHER_KEY_CTRL = "com.szchoiceway.ACTION_LAUNCHER_KEY_CTRL";
    private static final String TAG = "MainFragment";
    private static final int delayMills = 300;
    public static MainFragment instace;
    private List<AppInfo> appInfoList;
    private AppListDialog appListDialog;
    private DebounceHelper<Integer> audiGt6DebounceHelper;
    private TextView audi_right_weather_uv_tv;
    private TextView audi_right_weather_visibility_tv;
    private TextView audi_right_weather_wind_tv;
    private BenzControlDialog benzControlDialog;
    private int benzControlIndex = 0;
    private ImageButton benzControlShow;
    private View bt_status_iv;
    private ImageButton btnDashboard;
    /* access modifiers changed from: private */
    public ImageButton btnDirectionLeft;
    /* access modifiers changed from: private */
    public ImageButton btnDirectionRight;
    private Button btnLeftApp0;
    private Button btnLeftApp1;
    private Button btnLeftApp2;
    int[] btnList1 = null;
    int[] btnList2 = null;
    int[] btnList3 = null;
    int[] btnList4 = null;
    private View btnMusic;
    private final Runnable buttonRunnable = new Runnable() {
        public void run() {
            AnimationDrawable animationDrawable;
            if (MainFragment.this.mMainButtons != null && MainFragment.this.iMainFocusIndex < MainFragment.this.mMainButtons.length && MainFragment.this.iMainFocusIndex >= 0) {
                ImageView imageView = (ImageView) MainFragment.this.imgViews.get(MainFragment.this.iMainFocusIndex);
                if (!(imageView == null || (animationDrawable = (AnimationDrawable) imageView.getBackground()) == null)) {
                    animationDrawable.start();
                }
                View view = MainFragment.this.mMainButtons[MainFragment.this.iMainFocusIndex];
                if (view != null) {
                    view.setSelected(false);
                }
            }
        }
    };
    private View car_info_group;
    private boolean changeMusicBg = false;
    private List<CheckBox> chkPagings;
    private long clickPlayTime = 0;
    private float currentAngle = 0.0f;
    /* access modifiers changed from: private */
    public int currentPageIndex = 0;
    /* access modifiers changed from: private */
    public float currentRotateAngle = 0.0f;
    private TextView current_temp_info_tv;
    private boolean editEnable = false;
    /* access modifiers changed from: private */
    public Group fangxiang_group;
    private TextView fangxiang_road_tv;
    /* access modifiers changed from: private */
    public ImageView gt6AudiDashboardPointer_iv;
    /* access modifiers changed from: private */
    public TextView gt6AudiDashboardRotateSpeedValue_tv;
    private TextView gt6AudiDashboardSpeedValue_tv;
    private TextView gt6AudiDashboardTemp_tv;
    private TextView gt6AudiFangxiang_a_tv;
    /* access modifiers changed from: private */
    public Handler gt6AudiHandler;
    private ImageView gt6Audifangxiang;
    /* access modifiers changed from: private */
    public TextView gt6Audifangxiang_riqi_tv;
    /* access modifiers changed from: private */
    public TextView gt6Audifangxiang_tianqi_tv;
    private TextView gt6Audifangxiang_time_tv;
    /* access modifiers changed from: private */
    public TextView gt6Audifangxiang_weak_tv;
    /* access modifiers changed from: private */
    public TextView gt6Audifangxiang_wendu_tv;
    private ImageView gt6_audi_iv_MusicCoverBg;
    private TextView gt6_audi_qi_tv;
    private TextView gt6_audi_wendu_tv;
    private final Handler handler = new Handler(Looper.getMainLooper()) {
    };
    private int iColumns = 5;
    /* access modifiers changed from: private */
    public int iLastMainFocusIndex = 0;
    /* access modifiers changed from: private */
    public int iLastMainPageIndex = 0;
    private int iLeftFocusIndex = -1;
    public int iMainFocusIndex = -1;
    /* access modifiers changed from: private */
    public int iPagerItemViewItemCount = 2;
    private int iRows = 1;
    private ImageView iconApp0;
    private ImageView iconApp1;
    private ImageView iconApp2;
    private ImageView imgLandroverBg;
    /* access modifiers changed from: private */
    public List<ImageView> imgViews = new ArrayList();
    /* access modifiers changed from: private */
    public boolean isGaodeNavigating = false;
    private boolean isSmallResolution = false;
    private int itemSize = 0;
    /* access modifiers changed from: private */
    public ItemUtil itemUtil;
    private String language = "en";
    private float lastAngle = 0.0f;
    private int lastCheckedIndex = -1;
    private int lastImgIndex = -1;
    private int lastPageCount = 2;
    private float lastRotateAngle = 0.0f;
    /* access modifiers changed from: private */
    public ILauncherStrategy launcherStrategy;
    private View layoutEdit;
    private View layoutMain;
    private AudiGT6LoopRotarySwitchView loop_view;
    private Activity mActivity;
    private int[] mBenzNaviBgDrawables = new int[0];
    private Drawable mBlurMusicCoverDrawable = null;
    private int mBtState = 0;
    private View mBtnMusicPlayPause;
    private CheckBox mChkBelt;
    private CheckBox mChkBrake;
    private CheckBox mChkVideoState;
    private MyQAnalogClock2 mClockSpeed;
    private String mConnectBtName = "";
    public ServiceConnection mEvtSc = new ServiceConnection() {
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            IEventService unused = MainFragment.this.mEvtService = IEventService.Stub.asInterface(iBinder);
            Log.i(MainFragment.TAG, "onServiceConnected: mEvtService = " + MainFragment.this.mEvtService);
            MainFragment.this.refreshView();
        }

        public void onServiceDisconnected(ComponentName componentName) {
            Log.i(MainFragment.TAG, "onServiceDisconnected: ");
            IEventService unused = MainFragment.this.mEvtService = null;
        }
    };
    /* access modifiers changed from: private */
    public IEventService mEvtService;
    /* access modifiers changed from: private */
    public TextView mGt6AudiRangeTemp;
    /* access modifiers changed from: private */
    public List<ImageView> mImgWeatherDetails;
    /* access modifiers changed from: private */
    public ImageView mImgWeatherStatus;
    private ImageView mIvHandbrake;
    private ImageView mIvMediaTypeBg;
    private ImageView mIvSeatBelt;
    /* access modifiers changed from: private */
    public View mLayoutWeatherContent;
    /* access modifiers changed from: private */
    public View mLayoutWeatherStatus;
    private View[] mLeftButtons;
    public MainAdapter mMainAdapter;
    public MainAdapter mMainAdapterEdit;
    private ImageView mMainBg;
    private int[] mMainBgDrawables = new int[0];
    private int mMainBgIndex = 0;
    /* access modifiers changed from: private */
    public View[] mMainButtons;
    private ImageView mMainFg;
    private MainTopView mMainTopView;
    private ImageView mMusicCover;
    private ImageView mMusicCoverBg;
    private Bitmap mMusicCoverBm = null;
    /* access modifiers changed from: private */
    public ImageView mPage0;
    /* access modifiers changed from: private */
    public ImageView mPage1;
    /* access modifiers changed from: private */
    public ImageView mPage2;
    /* access modifiers changed from: private */
    public ImageView mPage3;
    private LinearLayout mPageIconLayout;
    private PagerAdapter mPagerAdapter;
    /* access modifiers changed from: private */
    public final SysProviderOpt mProvider = SysProviderOpt.getInstance(this.mContext);
    /* access modifiers changed from: private */
    public TextView mRangeTemp;
    private RecyclerView mRecyclerView;
    private RecyclerView mRecyclerViewEdit;
    private PagingScrollHelper mScrollHelper;
    private SeekBar mSkBarProgress;
    private MySeekbar mSkBarProgress2;
    private TextView mTvCurrTime;
    private TextView mTvHandbrake;
    private TextView mTvMusicAblumInfor;
    private TextView mTvMusicArtistInfor;
    private TextView mTvMusicTitleInfor;
    private TextView mTvQil;
    private TextView mTvSeatBelt;
    private TextView mTvShunShiSuDu;
    private TextView mTvSpeedUnit;
    private TextView mTvTemp;
    private TextView mTvTotTime;
    private TextView mTvVideoMessage;
    private TextView mTvVideoTitleInfor;
    /* access modifiers changed from: private */
    public List<TextView> mTvWeatherDetails;
    /* access modifiers changed from: private */
    public TextView mTvWeatherStatus;
    private boolean mUnit = false;
    /* access modifiers changed from: private */
    public TextView mUnitTemp;
    private int mValidPlayStatus = 0;
    /* access modifiers changed from: private */
    public TextView mValueTemp;
    /* access modifiers changed from: private */
    public MyViewPager mViewPager;
    /* access modifiers changed from: private */
    public List<View> mViewWeatherDetails;
    /* access modifiers changed from: private */
    public ArrayList<View> mViews;
    /* access modifiers changed from: private */
    public TextView mWeatherCity;
    /* access modifiers changed from: private */
    public TextView mWeatherContent;
    /* access modifiers changed from: private */
    public List<String> mWeatherDetailList;
    /* access modifiers changed from: private */
    public HashMap<String, String> mWeatherDetailMap;
    /* access modifiers changed from: private */
    public ImageView mWeatherPic;
    /* access modifiers changed from: private */
    public int m_iUITypeVer = 41;
    private View[] mainFocusFrameViews;
    private int maxAngle = 120;
    /* access modifiers changed from: private */
    public int maxRotateAngle = GyroScopeWithCompassView.CARTYPE_SDF_HI;
    /* access modifiers changed from: private */
    public int maxRotateSpeed = 8000;
    private int maxSpeed = 280;
    private LauncherModel model;
    private MyBroadcast myBroadcast;
    private ImageView myClock;
    private ImageView myRotateClock;
    private View oc_status_iv;
    private boolean pageChange = false;
    /* access modifiers changed from: private */
    public int rightWidget;
    private ValueAnimator rotatingSpeedAnimator;
    private String temperUnit = "c";
    private ThemeDialog themeDialog;
    private TextView tvDateDay;
    private TextView tvDateMonth;
    private TextView txtBTMessage;
    View view1 = null;
    View view2 = null;
    View view3 = null;
    View view4 = null;
    private View viewMusic;
    private View viewMusicContent;
    /* access modifiers changed from: private */
    public View weatherPic_group;
    private WeatherQueryManager weatherQueryManager;
    private String xml_client = "";
    /* access modifiers changed from: private */
    public View yibiaopan_group;
    /* access modifiers changed from: private */
    public View yinyuefengmian_group;

    /* access modifiers changed from: protected */
    public void initEvent(View view) {
    }

    public void refreshLocalChanged() {
    }

    public void setCurPlayVideoPath(String str) {
    }

    public MainFragment(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        initData();
    }

    /* access modifiers changed from: protected */
    public int inflateLayout() {
        instace = this;
        Log.d(TAG, "instace = " + instace);
        this.isSmallResolution = Customer.isSmallResolution(this.mContext);
        int i = LauncherModel.getInstance().m_iModeSet;
        ILauncherStrategy launcherStrategy2 = LauncherStrategyUtil.getLauncherStrategy(getContext(), i);
        this.launcherStrategy = launcherStrategy2;
        if (launcherStrategy2 != null) {
            return launcherStrategy2.getLayoutId();
        }
        if (!this.isSmallResolution) {
            if (LauncherModel.getInstance().m_iModeSet == 21 || LauncherModel.getInstance().m_iModeSet == 31) {
                return R.layout.kesaiwei_1920x720_benz_fragment_main_ntg;
            }
            if (LauncherModel.getInstance().m_iModeSet == 18 || LauncherModel.getInstance().m_iModeSet == 30) {
                return R.layout.kesaiwei_1920x720_benz_fragment_main_common;
            }
            if (LauncherModel.getInstance().m_iModeSet == 22) {
                return R.layout.kesaiwei_1920x720_benz_fragment_main_fy;
            }
            if (LauncherModel.getInstance().m_iModeSet == 19) {
                return R.layout.pemp_id7_fragment_main;
            }
            if (LauncherModel.getInstance().m_iModeSet == 23) {
                return "1560x700".equals(LauncherModel.getInstance().mStrResolution) ? R.layout.audi_fragment_main_1560x700 : R.layout.audi_fragment_main;
            }
            if (LauncherModel.getInstance().m_iModeSet == 24) {
                return R.layout.audi_fy_fragment_main;
            }
            if (LauncherModel.getInstance().m_iModeSet == 27) {
                return R.layout.audi_ty_fragment_main;
            }
            if (LauncherModel.getInstance().m_iModeSet == 40) {
                return R.layout.audi_bl_fragment_main;
            }
            if (this.m_iUIIndex == 5) {
                return this.m_iModeSet == 39 ? R.layout.landrover_copilot_fragment_main : R.layout.landrover_fragment_main;
            }
            if (LauncherModel.getInstance().m_iModeSet == 29) {
                return R.layout.audi_cdlc_fragment_main;
            }
            if (LauncherModel.getInstance().m_iModeSet == 26) {
                return R.layout.common_gs_fragment_main;
            }
            if (LauncherModel.getInstance().m_iModeSet == 38) {
                return R.layout.gt6_audi_fragment_main;
            }
            return R.layout.kesaiwei_1920x720_evo_id7_fragment_main;
        } else if (LauncherModel.getInstance().m_iModeSet == 18) {
            return R.layout.small_kesaiwei_1920x720_benz_fragment_main_common;
        } else {
            if (i == 21 || i == 31) {
                return R.layout.small_kesaiwei_1024x600_benz_fragment_main_common;
            }
            return i == 23 ? R.layout.small_audi_fragment_main : R.layout.kesaiwei_1920x720_evo_id7_fragment_main;
        }
    }

    private void initData() {
        try {
            Intent intent = new Intent();
            intent.setAction("com.szchoiceway.eventcenter.EventService");
            intent.setPackage("com.szchoiceway.eventcenter");
            this.mContext.bindService(intent, this.mEvtSc, 1);
            Log.e(TAG, "onBindService true ");
        } catch (Exception e) {
            Log.e(TAG, "onBindService error " + e.toString());
        }
        this.model = LauncherModel.getInstance();
        this.m_iUITypeVer = this.mProvider.getRecordInteger("Set_User_UI_Type", this.m_iUITypeVer);
        this.xml_client = this.model.xml_client;
        this.mWeatherDetailMap = new HashMap<>();
        this.mWeatherDetailList = new ArrayList();
        this.weatherQueryManager = WeatherQueryManager.getInstance();
        this.mUnit = this.mProvider.getRecordBoolean(SysProviderOpt.KSW_DISTACNE_UNIT, this.mUnit);
        Log.d(TAG, "initData modeSet: " + this.m_iModeSet + ", uitype: " + this.m_iUITypeVer);
    }

    /* access modifiers changed from: protected */
    public void initView(View view) {
        Log.d(TAG, "initView");
        this.isSmallResolution = Customer.isSmallResolution(this.mContext);
        Activity activity = getActivity();
        this.mActivity = activity;
        MultipleUtils.setCustomDensity(activity, this.mContext.getResources(), 0);
        initMainBg(view);
        initLeftViews(view);
        initDialog(view);
        initOtherView(view);
        initNormalView(view);
        initRecyclerView(view);
        initViewPager(view);
        try {
            this.weatherQueryManager.setUserSettingListener(this.mActivity, new WeatherQueryManager.UserSettingListener() {
                public final void noticeChange() {
                    MainFragment.this.refreshWeatherView();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initMainBg(View view) {
        this.mMainBg = (ImageView) view.findViewById(R.id.mainBackground);
        this.mMainFg = (ImageView) view.findViewById(R.id.mainForeground);
        if (this.m_iUIIndex == 6) {
            this.mMainBgIndex = this.mProvider.getRecordInteger(SysProviderOpt.KSW_BENZ_THEME_BACKGROUND_INDEX, 0);
            if (this.m_iModeSet == 18 || this.m_iModeSet == 30) {
                if (this.isSmallResolution) {
                    this.mMainBgDrawables = new int[]{R.drawable.small_mbux2_bg_main_2_1, R.drawable.small_mbux2_bg_main_2_2, R.drawable.small_mbux2_bg_main_2_3, R.drawable.small_mbux2_bg_main_2_4, R.drawable.small_mbux2_bg_main_2_5, R.drawable.small_mbux2_bg_main_2_6, R.drawable.small_mbux2_bg_main_2_7};
                } else {
                    this.mMainBgDrawables = new int[]{R.drawable.ksw_1920x720_benz_beijing_lan, R.drawable.ksw_1920x720_benz_beijing_yinghong, R.drawable.ksw_1920x720_benz_beijing_huang, R.drawable.ksw_1920x720_benz_beijing_dahong, R.drawable.ksw_1920x720_benz_beijing_lanlv, R.drawable.ksw_1920x720_benz_beijing_qianhong, R.drawable.ksw_1920x720_benz_beijing_hui};
                }
            } else if (this.m_iModeSet == 21 || this.m_iModeSet == 31) {
                View findViewById = view.findViewById(R.id.page2);
                if (findViewById != null) {
                    findViewById.setVisibility(0);
                }
                this.mMainBgDrawables = new int[]{R.drawable.ksw_1920x720_benz_beijing_lan, R.drawable.ksw_1920x720_benz_beijing_yinghong, R.drawable.ksw_1920x720_benz_beijing_huang, R.drawable.ksw_1920x720_benz_beijing_dahong, R.drawable.ksw_1920x720_benz_beijing_lanlv, R.drawable.ksw_1920x720_benz_beijing_qianhong, R.drawable.ksw_1920x720_benz_beijing_hui};
                this.mBenzNaviBgDrawables = new int[]{R.drawable.mbux2_bg_main_1_1, R.drawable.mbux2_bg_main_1_2, R.drawable.mbux2_bg_main_1_3, R.drawable.mbux2_bg_main_1_4, R.drawable.mbux2_bg_main_1_5, R.drawable.mbux2_bg_main_1_6, R.drawable.mbux2_bg_main_1_7};
            } else if (this.m_iModeSet == 22) {
                this.mMainBgDrawables = new int[]{R.drawable.fy_mbux_bg_main_1, R.drawable.fy_mbux_bg_main_2, R.drawable.fy_mbux_bg_main_3, R.drawable.fy_mbux_bg_main_5, R.drawable.fy_mbux_bg_main_4, R.drawable.fy_mbux_bg_main_7, R.drawable.fy_mbux_bg_main_6, R.drawable.fy_mbux_bg_main_8};
            }
        } else if (this.m_iModeSet == 40) {
            this.mMainBgIndex = this.mProvider.getRecordInteger(SysProviderOpt.KSW_AUDI_BENTLEY_THEME_BACKGROUND_INDEX, 0);
            this.mMainBgDrawables = new int[]{R.drawable.audi_bl_beijing_hong, R.drawable.audi_bl_beijing_huang, R.drawable.audi_bl_beijing_lan};
        }
        refreshMainBackground();
    }

    private void initLeftViews(View view) {
        int[] iArr = {R.id.btnLeftNavi, R.id.btnLeftMusic, R.id.btnLeftSetting, R.id.btnLeftOriginaCar, R.id.btnLeftApps, R.id.btnLeftApp0, R.id.btnLeftApp1, R.id.btnLeftApp2, R.id.focusLeftMusic, R.id.focusLeftNavi, R.id.focusLeftSettings, R.id.focusLeftOriginal, R.id.focusLeftApps, R.id.btnLeftBt, R.id.btnLeftVideo, R.id.btnLeftHome};
        for (int i = 0; i < 16; i++) {
            View findViewById = view.findViewById(iArr[i]);
            if (findViewById != null) {
                findViewById.setOnClickListener(this);
            }
        }
        if (isDefaultUI()) {
            this.mLeftButtons = new View[]{view.findViewById(R.id.focusLeftMusic), view.findViewById(R.id.focusLeftNavi), view.findViewById(R.id.focusLeftSettings), view.findViewById(R.id.focusLeftOriginal), view.findViewById(R.id.focusLeftApps)};
        } else if (this.m_iModeSet == 19) {
            AppListDialog appListDialog2 = new AppListDialog(this.mContext);
            this.appListDialog = appListDialog2;
            this.appInfoList = appListDialog2.getAppInfoList();
            Button button = (Button) view.findViewById(R.id.btnLeftApp0);
            this.btnLeftApp0 = button;
            if (button != null) {
                button.setOnLongClickListener(this);
            }
            Button button2 = (Button) view.findViewById(R.id.btnLeftApp1);
            this.btnLeftApp1 = button2;
            if (button2 != null) {
                button2.setOnLongClickListener(this);
            }
            Button button3 = (Button) view.findViewById(R.id.btnLeftApp2);
            this.btnLeftApp2 = button3;
            if (button3 != null) {
                button3.setOnLongClickListener(this);
            }
            this.mLeftButtons = new View[]{view.findViewById(R.id.btnLeftApps), view.findViewById(R.id.btnLeftSetting), this.btnLeftApp0, this.btnLeftApp1, this.btnLeftApp2};
            this.iconApp0 = (ImageView) view.findViewById(R.id.iconApp0);
            this.iconApp1 = (ImageView) view.findViewById(R.id.iconApp1);
            this.iconApp2 = (ImageView) view.findViewById(R.id.iconApp2);
            refreshPempLeftApps();
        } else if (this.m_iUIIndex == 4 && this.m_iModeSet != 29) {
            if (this.m_iModeSet == 40) {
                this.mLeftButtons = new View[]{view.findViewById(R.id.btnLeftHome), view.findViewById(R.id.btnLeftApps), view.findViewById(R.id.btnLeftOriginaCar), view.findViewById(R.id.btnLeftVideo), view.findViewById(R.id.btnLeftBt), view.findViewById(R.id.btnLeftNavi)};
                return;
            }
            this.mLeftButtons = new View[]{view.findViewById(R.id.btnLeftMusic), view.findViewById(R.id.btnLeftNavi), view.findViewById(R.id.btnLeftBt), view.findViewById(R.id.btnLeftSetting), view.findViewById(R.id.btnLeftOriginaCar)};
        }
    }

    private void initDialog(View view) {
        if (this.m_iUIIndex == 6) {
            ThemeDialog themeDialog2 = new ThemeDialog(this.mContext);
            this.themeDialog = themeDialog2;
            if (themeDialog2.getIsShow()) {
                this.themeDialog.hideThemeDialog();
            }
            ThemeDialog themeDialog3 = this.themeDialog;
            if (themeDialog3 != null) {
                themeDialog3.setOnBackgroundChangeListener(this);
                this.themeDialog.setOnThemeChangeListener(this);
            }
            this.benzControlDialog = new BenzControlDialog(this.mContext);
            int[] iArr = {R.id.benzControlShow, R.id.btnTheme, R.id.txtTheme};
            for (int i = 0; i < 3; i++) {
                View findViewById = view.findViewById(iArr[i]);
                if (findViewById != null) {
                    findViewById.setOnClickListener(this);
                }
            }
            ImageButton imageButton = (ImageButton) view.findViewById(R.id.benzControlShow);
            this.benzControlShow = imageButton;
            if (imageButton != null) {
                imageButton.setOnClickListener(this);
                int recordInteger = this.mProvider.getRecordInteger(SysProviderOpt.SYS_BENZ_CONTROL_INDEX, 0);
                this.benzControlIndex = recordInteger;
                if (recordInteger != 0) {
                    this.benzControlShow.setVisibility(0);
                } else {
                    this.benzControlShow.setVisibility(8);
                }
            }
        } else if (this.m_iUIIndex == 5) {
            ThemeDialog themeDialog4 = new ThemeDialog(this.mContext);
            this.themeDialog = themeDialog4;
            if (themeDialog4.getIsShow()) {
                this.themeDialog.hideThemeDialog();
            }
            this.themeDialog.setOnBackgroundChangeListener(this);
            ImageView imageView = (ImageView) view.findViewById(R.id.imgLandroverBg);
            this.imgLandroverBg = imageView;
            if (imageView != null) {
                imageView.setOnLongClickListener(new View.OnLongClickListener() {
                    public final boolean onLongClick(View view) {
                        return MainFragment.this.lambda$initDialog$0$MainFragment(view);
                    }
                });
            }
            if (this.m_iModeSet != 32 && this.m_iModeSet != 39) {
                setLandRoverBg();
            }
        }
    }

    public /* synthetic */ boolean lambda$initDialog$0$MainFragment(View view) {
        ThemeDialog themeDialog2 = this.themeDialog;
        if (themeDialog2 == null) {
            return true;
        }
        themeDialog2.showThemeDialog();
        return true;
    }

    private void initOtherView(View view) {
        if (this.m_iUIIndex == 6) {
            if (this.m_iModeSet == 18 || this.m_iModeSet == 22 || this.m_iModeSet == 30) {
                this.layoutMain = view.findViewById(R.id.layoutMain);
                this.layoutEdit = view.findViewById(R.id.layoutEdit);
                this.editEnable = true;
            }
        } else if (this.m_iModeSet == 26) {
            ImageButton imageButton = (ImageButton) view.findViewById(R.id.btnDirectionLeft);
            this.btnDirectionLeft = imageButton;
            if (imageButton != null) {
                imageButton.setVisibility(8);
                this.btnDirectionLeft.setOnClickListener(new View.OnClickListener() {
                    public final void onClick(View view) {
                        MainFragment.this.lambda$initOtherView$1$MainFragment(view);
                    }
                });
            }
            ImageButton imageButton2 = (ImageButton) view.findViewById(R.id.btnDirectionRight);
            this.btnDirectionRight = imageButton2;
            if (imageButton2 != null) {
                imageButton2.setVisibility(0);
                this.btnDirectionRight.setOnClickListener(new View.OnClickListener() {
                    public final void onClick(View view) {
                        MainFragment.this.lambda$initOtherView$2$MainFragment(view);
                    }
                });
            }
        } else if (this.m_iModeSet == 40) {
            this.mMainTopView = new MainTopView(this.mContext, view);
        }
        ILauncherStrategy iLauncherStrategy = this.launcherStrategy;
        if (iLauncherStrategy != null) {
            iLauncherStrategy.setView(view);
        }
    }

    public /* synthetic */ void lambda$initOtherView$1$MainFragment(View view) {
        MyViewPager myViewPager = this.mViewPager;
        if (myViewPager != null) {
            myViewPager.setCurrentItem(0);
        }
    }

    public /* synthetic */ void lambda$initOtherView$2$MainFragment(View view) {
        MyViewPager myViewPager = this.mViewPager;
        if (myViewPager != null) {
            myViewPager.setCurrentItem(1);
        }
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
    }

    private void initNormalView(View view) {
        if (LauncherModel.getInstance().m_iModeSet == 38) {
            this.audiGt6DebounceHelper = new DebounceHelper<>(new DebounceHelper.Collector() {
                public final void collect(Object obj) {
                    MainFragment.this.lambda$initNormalView$3$MainFragment((Integer) obj);
                }
            });
            AudiGT6LoopRotarySwitchView audiGT6LoopRotarySwitchView = (AudiGT6LoopRotarySwitchView) view.findViewById(R.id.loop_view);
            this.loop_view = audiGT6LoopRotarySwitchView;
            audiGT6LoopRotarySwitchView.setMultiple(8.0f).setLoopRotationX(-21.0f).setR((float) dp(345));
            this.loop_view.setXOffset((float) dp(-60));
            this.loop_view.setYOffset((float) dp(-19));
            final ImageView imageView = (ImageView) view.findViewById(R.id.car_light_iv);
            this.loop_view.setOnItemClickListener(new AudiGT6LoopRotarySwitchView.OnItemClickListener() {
                public void onItemClick(int i, View view) {
                    Log.d(MainFragment.TAG, "item click: " + i + ", view: " + view);
                    view.setId(-1);
                    if (i == 0) {
                        view.setId(R.id.btnDashBoard);
                    } else if (i == 1) {
                        view.setId(R.id.btnOriginalCar);
                    } else if (i == 2) {
                        view.setId(R.id.btnNavi);
                    } else if (i == 3) {
                        view.setId(R.id.btnVideo);
                    } else if (i == 4) {
                        view.setId(R.id.btnAux);
                    } else if (i == 5) {
                        view.setId(R.id.btnMusic);
                    } else if (i == 6) {
                        view.setId(R.id.btnCarplay);
                    } else if (i == 7) {
                        view.setId(R.id.btnBt);
                    } else if (i == 8) {
                        view.setId(R.id.btnDvr);
                    } else if (i == 9) {
                        view.setId(R.id.btnSettins);
                    }
                    if (view.getId() != -1) {
                        MainFragment.this.onClick(view);
                    }
                }
            });
            this.loop_view.setSelectItemValue(1);
            this.loop_view.setOnItemSelectedListener(new AudiGT6LoopRotarySwitchView.OnItemSelectedListener() {
                public void selected(int i, View view) {
                    Log.d(MainFragment.TAG, "item selected: " + i + ", view: " + view);
                    MainFragment mainFragment = MainFragment.this;
                    mainFragment.iMainFocusIndex = (i + 2) % mainFragment.mMainButtons.length;
                    imageView.setImageLevel(MainFragment.this.iMainFocusIndex + 1);
                    MainFragment.this.refreshMainFocus();
                }
            });
            registerReceiver();
            setGt6AudiOnClick(view);
            final ImageView imageView2 = (ImageView) view.findViewById(R.id.car_img_iv);
            this.fangxiang_group = (Group) view.findViewById(R.id.fangxiang_group);
            this.yinyuefengmian_group = view.findViewById(R.id.yinyuefengmian_group);
            this.car_info_group = view.findViewById(R.id.car_info_group);
            this.weatherPic_group = view.findViewById(R.id.weatherPic_group);
            this.yibiaopan_group = view.findViewById(R.id.yibiaopan_group);
            initGt6AudiWealthAndGaoDeInfomation(view);
            initGt5AudiWealthView(view);
            initGt6AudiCarInfo(view);
            initGt6AudiMusicView(view);
            initGt6AudiDashboardView(view);
            this.gt6AudiDashboardPointer_iv.setRotation(-120.0f);
            Handler handler2 = new Handler(Looper.getMainLooper());
            this.gt6AudiHandler = handler2;
            handler2.post(new Runnable() {
                public void run() {
                    int recordInteger = MainFragment.this.mProvider.getRecordInteger(SysProviderOpt.AUDI_GT6_LEFT_CAR_ICON, 0);
                    if (recordInteger == 0) {
                        imageView2.setVisibility(8);
                    } else {
                        imageView2.setVisibility(0);
                        imageView2.setImageLevel(recordInteger);
                    }
                    MainFragment mainFragment = MainFragment.this;
                    int unused = mainFragment.rightWidget = mainFragment.mProvider.getRecordInteger(SysProviderOpt.AUDI_GT6_RIGHT_WIDGET, 0);
                    MainFragment.this.hideAllGt6AudiRightWidget();
                    if (MainFragment.this.rightWidget == 1) {
                        MainFragment.this.fangxiang_group.setVisibility(0);
                        if (MainFragment.this.isGaodeNavigating) {
                            MainFragment.this.fangxiang_group.setVisibility(0);
                            MainFragment.this.weatherPic_group.setVisibility(8);
                        } else {
                            MainFragment.this.fangxiang_group.setVisibility(8);
                            MainFragment.this.weatherPic_group.setVisibility(0);
                        }
                    }
                    if (MainFragment.this.rightWidget == 2) {
                        MainFragment.this.weatherPic_group.setVisibility(0);
                    }
                    if (MainFragment.this.rightWidget == 3) {
                        MainFragment.this.yibiaopan_group.setVisibility(0);
                    }
                    if (MainFragment.this.rightWidget == 4) {
                        MainFragment.this.yinyuefengmian_group.setVisibility(0);
                    }
                    MainFragment.this.gt6Audifangxiang_riqi_tv.setText(new SimpleDateFormat("yyyy/M/d", Locale.getDefault()).format(new Date()));
                    MainFragment.this.gt6Audifangxiang_weak_tv.setText(new SimpleDateFormat("EEEE", Locale.getDefault()).format(new Date()));
                    new SimpleDateFormat("yyyy/M/d", Locale.getDefault());
                    new SimpleDateFormat("EEEE", Locale.getDefault());
                    MainFragment.this.gt6AudiHandler.postDelayed(this, 1000);
                }
            });
            this.mMainButtons = (View[]) ArraysUtil.arrayOf(view.findViewById(R.id.btnDashBoard), view.findViewById(R.id.btnOriginalCar), view.findViewById(R.id.btnNavi), view.findViewById(R.id.btnVideo), view.findViewById(R.id.btnAux), view.findViewById(R.id.btnMusic), view.findViewById(R.id.btnCarplay), view.findViewById(R.id.btnBt), view.findViewById(R.id.btnDvr), view.findViewById(R.id.btnSettins));
            this.mainFocusFrameViews = (View[]) ArraysUtil.arrayOf(view.findViewById(R.id.btnDashBoard_frame), view.findViewById(R.id.btnOriginalCar_frame), view.findViewById(R.id.btnNavi_frame), view.findViewById(R.id.btnVideo_frame), view.findViewById(R.id.btnAux_frame), view.findViewById(R.id.btnMusic_frame), view.findViewById(R.id.btnCarplay_frame), view.findViewById(R.id.btnBt_frame), view.findViewById(R.id.btnDvr_frame), view.findViewById(R.id.btnSettins_frame));
            refreshWeatherView();
            setSpeedUnit();
        }
    }

    public /* synthetic */ void lambda$initNormalView$3$MainFragment(Integer num) {
        AudiGT6LoopRotarySwitchView audiGT6LoopRotarySwitchView;
        if (num.intValue() == 0) {
            AudiGT6LoopRotarySwitchView audiGT6LoopRotarySwitchView2 = this.loop_view;
            if (audiGT6LoopRotarySwitchView2 != null) {
                audiGT6LoopRotarySwitchView2.scrollToPreItem();
            }
        } else if (num.intValue() == 1 && (audiGT6LoopRotarySwitchView = this.loop_view) != null) {
            audiGT6LoopRotarySwitchView.scrollToNextItem();
        }
    }

    private int dp(int i) {
        return (int) TypedValue.applyDimension(1, (float) i, getContext().getResources().getDisplayMetrics());
    }

    private void initGt6AudiWealthAndGaoDeInfomation(View view) {
        ImageView imageView = (ImageView) view.findViewById(R.id.fangxiang);
        this.gt6Audifangxiang = imageView;
        imageView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                MainFragment.this.enterNavi();
            }
        });
        this.gt6AudiFangxiang_a_tv = (TextView) view.findViewById(R.id.fangxiang_a_tv);
        this.gt6Audifangxiang_time_tv = (TextView) view.findViewById(R.id.fangxiang_time_tv);
        this.gt6Audifangxiang_tianqi_tv = (TextView) view.findViewById(R.id.fangxiang_tianqi_tv);
        this.gt6Audifangxiang_wendu_tv = (TextView) view.findViewById(R.id.fangxiang_wendu_tv);
        this.gt6Audifangxiang_riqi_tv = (TextView) view.findViewById(R.id.fangxiang_riqi_tv);
        this.gt6Audifangxiang_weak_tv = (TextView) view.findViewById(R.id.fangxiang_weak_tv);
        this.fangxiang_road_tv = (TextView) view.findViewById(R.id.fangxiang_road_tv);
    }

    private void initGt5AudiWealthView(View view) {
        ImageView imageView = (ImageView) view.findViewById(R.id.weatherPic);
        this.mWeatherPic = imageView;
        imageView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                MainFragment.this.enterWeather();
            }
        });
        this.mLayoutWeatherContent = view.findViewById(R.id.layoutWeatherContent);
        View findViewById = view.findViewById(R.id.layoutWeatherStatus);
        this.mLayoutWeatherStatus = findViewById;
        findViewById.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                MainFragment.this.enterWeather();
            }
        });
        this.mImgWeatherStatus = (ImageView) view.findViewById(R.id.imgWeatherStatus);
        this.mTvWeatherStatus = (TextView) view.findViewById(R.id.tvWeatherStatus);
        this.mWeatherCity = (TextView) view.findViewById(R.id.weatherCity);
        this.mWeatherContent = (TextView) view.findViewById(R.id.weatherContent);
        this.mGt6AudiRangeTemp = (TextView) view.findViewById(R.id.rangeTemp);
        this.mValueTemp = (TextView) view.findViewById(R.id.current_temp_info_tv);
        this.mUnitTemp = (TextView) view.findViewById(R.id.current_temp_unit_tv);
        this.mViewWeatherDetails = (List) JavaStandard.also(new ArrayList(), new Function1Void(view) {
            public final /* synthetic */ View f$0;

            {
                this.f$0 = r1;
            }

            public final void invoke(Object obj) {
                MainFragment.lambda$initGt5AudiWealthView$4(this.f$0, (ArrayList) obj);
            }
        });
        this.mImgWeatherDetails = (List) JavaStandard.also(new ArrayList(), new Function1Void(view) {
            public final /* synthetic */ View f$0;

            {
                this.f$0 = r1;
            }

            public final void invoke(Object obj) {
                MainFragment.lambda$initGt5AudiWealthView$5(this.f$0, (ArrayList) obj);
            }
        });
        this.mTvWeatherDetails = (List) JavaStandard.also(new ArrayList(), new Function1Void(view) {
            public final /* synthetic */ View f$1;

            {
                this.f$1 = r2;
            }

            public final void invoke(Object obj) {
                MainFragment.this.lambda$initGt5AudiWealthView$6$MainFragment(this.f$1, (ArrayList) obj);
            }
        });
    }

    static /* synthetic */ void lambda$initGt5AudiWealthView$4(View view, ArrayList arrayList) {
        View findViewById = view.findViewById(R.id.audi_right_weather_wind_group);
        Objects.requireNonNull(arrayList);
        JavaStandard.also(findViewById, new Function1Void(arrayList) {
            public final /* synthetic */ ArrayList f$0;

            {
                this.f$0 = r1;
            }

            public final void invoke(Object obj) {
                boolean unused = this.f$0.add((View) obj);
            }
        });
        View findViewById2 = view.findViewById(R.id.audi_right_weather_visibility_group);
        Objects.requireNonNull(arrayList);
        JavaStandard.also(findViewById2, new Function1Void(arrayList) {
            public final /* synthetic */ ArrayList f$0;

            {
                this.f$0 = r1;
            }

            public final void invoke(Object obj) {
                boolean unused = this.f$0.add((View) obj);
            }
        });
        View findViewById3 = view.findViewById(R.id.audi_right_weather_uv_grouop);
        Objects.requireNonNull(arrayList);
        JavaStandard.also(findViewById3, new Function1Void(arrayList) {
            public final /* synthetic */ ArrayList f$0;

            {
                this.f$0 = r1;
            }

            public final void invoke(Object obj) {
                boolean unused = this.f$0.add((View) obj);
            }
        });
    }

    static /* synthetic */ void lambda$initGt5AudiWealthView$5(View view, ArrayList arrayList) {
        Objects.requireNonNull(arrayList);
        JavaStandard.also((ImageView) view.findViewById(R.id.audi_right_weather_wind_iv), new Function1Void(arrayList) {
            public final /* synthetic */ ArrayList f$0;

            {
                this.f$0 = r1;
            }

            public final void invoke(Object obj) {
                boolean unused = this.f$0.add((ImageView) obj);
            }
        });
        Objects.requireNonNull(arrayList);
        JavaStandard.also((ImageView) view.findViewById(R.id.audi_right_weather_visibility_iv), new Function1Void(arrayList) {
            public final /* synthetic */ ArrayList f$0;

            {
                this.f$0 = r1;
            }

            public final void invoke(Object obj) {
                boolean unused = this.f$0.add((ImageView) obj);
            }
        });
        Objects.requireNonNull(arrayList);
        JavaStandard.also((ImageView) view.findViewById(R.id.audi_right_weather_uv_iv), new Function1Void(arrayList) {
            public final /* synthetic */ ArrayList f$0;

            {
                this.f$0 = r1;
            }

            public final void invoke(Object obj) {
                boolean unused = this.f$0.add((ImageView) obj);
            }
        });
    }

    public /* synthetic */ void lambda$initGt5AudiWealthView$6$MainFragment(View view, ArrayList arrayList) {
        Objects.requireNonNull(arrayList);
        this.audi_right_weather_wind_tv = (TextView) JavaStandard.also((TextView) view.findViewById(R.id.audi_right_weather_wind_tv), new Function1Void(arrayList) {
            public final /* synthetic */ ArrayList f$0;

            {
                this.f$0 = r1;
            }

            public final void invoke(Object obj) {
                boolean unused = this.f$0.add((TextView) obj);
            }
        });
        Objects.requireNonNull(arrayList);
        this.audi_right_weather_visibility_tv = (TextView) JavaStandard.also((TextView) view.findViewById(R.id.audi_right_weather_visibility_tv), new Function1Void(arrayList) {
            public final /* synthetic */ ArrayList f$0;

            {
                this.f$0 = r1;
            }

            public final void invoke(Object obj) {
                boolean unused = this.f$0.add((TextView) obj);
            }
        });
        Objects.requireNonNull(arrayList);
        this.audi_right_weather_uv_tv = (TextView) JavaStandard.also((TextView) view.findViewById(R.id.audi_right_weather_uv_tv), new Function1Void(arrayList) {
            public final /* synthetic */ ArrayList f$0;

            {
                this.f$0 = r1;
            }

            public final void invoke(Object obj) {
                boolean unused = this.f$0.add((TextView) obj);
            }
        });
    }

    private void initGt6AudiCarInfo(View view) {
        this.gt6_audi_qi_tv = (TextView) view.findViewById(R.id.gt6_audi_qi_tv);
        this.gt6_audi_wendu_tv = (TextView) view.findViewById(R.id.gt6_audi_wendu_tv);
    }

    private void initGt6AudiMusicView(View view) {
        view.findViewById(R.id.gt6_audi_part_cover_bg).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                MainFragment.this.enterMusic();
            }
        });
        this.mTvMusicTitleInfor = (TextView) view.findViewById(R.id.tv_MusicTitleInfor);
        this.mMusicCover = (ImageView) view.findViewById(R.id.iv_MusicCoverBg);
        this.mTvMusicTitleInfor.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                MainFragment.this.enterMusic();
            }
        });
        this.mMusicCover.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                MainFragment.this.enterMusic();
            }
        });
        view.findViewById(R.id.btnMusicPrev).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                MainFragment.this.playPrev();
            }
        });
        view.findViewById(R.id.btnMusicPlayPause).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                MainFragment.this.playPause();
            }
        });
        view.findViewById(R.id.btnMusicNext).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                MainFragment.this.playNext();
            }
        });
    }

    private void initGt6AudiDashboardView(View view) {
        ImageView imageView = (ImageView) view.findViewById(R.id.zhizhen);
        this.gt6AudiDashboardPointer_iv = imageView;
        imageView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                MainFragment.this.enterDashboard();
            }
        });
        this.gt6AudiDashboardRotateSpeedValue_tv = (TextView) view.findViewById(R.id.zhizhen_bottom_tv);
        this.gt6AudiDashboardSpeedValue_tv = (TextView) view.findViewById(R.id.dingquan_qi_tv);
        this.gt6AudiDashboardTemp_tv = (TextView) view.findViewById(R.id.dingquan_wendu_tv);
    }

    private void setGt6AudiOnClick(View view) {
        view.findViewById(R.id.btnPower).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent("com.android.quicksetting.BROADCAST");
                intent.putExtra(NotificationCompat.CATEGORY_MESSAGE, "backlight");
                MainFragment.this.mContext.sendBroadcast(intent);
            }
        });
        View findViewById = view.findViewById(R.id.bt_status_iv);
        this.bt_status_iv = findViewById;
        findViewById.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                MainFragment.this.enterBT();
            }
        });
        View findViewById2 = view.findViewById(R.id.oc_status_iv);
        this.oc_status_iv = findViewById2;
        findViewById2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                MainFragment.this.enterOriginal();
            }
        });
        view.findViewById(R.id.btnApps).setOnClickListener(this);
    }

    /* access modifiers changed from: private */
    public void hideAllGt6AudiRightWidget() {
        Group group = this.fangxiang_group;
        if (group != null) {
            group.setVisibility(8);
        }
        View view = this.yinyuefengmian_group;
        if (view != null) {
            view.setVisibility(8);
        }
        View view5 = this.car_info_group;
        if (view5 != null) {
            view5.setVisibility(8);
        }
        View view6 = this.weatherPic_group;
        if (view6 != null) {
            view6.setVisibility(8);
        }
        View view7 = this.yibiaopan_group;
        if (view7 != null) {
            view7.setVisibility(8);
        }
    }

    public void onResume() {
        if (this.benzControlShow != null) {
            int recordInteger = this.mProvider.getRecordInteger(SysProviderOpt.SYS_BENZ_CONTROL_INDEX, 0);
            if (this.benzControlIndex != recordInteger) {
                BenzControlDialog benzControlDialog2 = this.benzControlDialog;
                if (benzControlDialog2 != null && benzControlDialog2.isShow) {
                    this.benzControlDialog.hideDialog();
                }
                this.benzControlDialog = new BenzControlDialog(this.mContext);
                this.benzControlIndex = recordInteger;
            }
            if (recordInteger != 0) {
                this.benzControlShow.setVisibility(0);
            } else {
                this.benzControlShow.setVisibility(8);
            }
        }
    }

    public void onPause() {
        hideDialog();
    }

    /* access modifiers changed from: protected */
    public void unInit() {
        super.unInit();
        if (this.weatherQueryManager != null) {
            this.weatherQueryManager = null;
        }
        removeAllViews();
    }

    private void initViewPager(View view) {
        LayoutInflater layoutInflater;
        int[] iArr;
        int[] iArr2;
        int[] iArr3;
        int[] iArr4;
        int[] iArr5;
        View view5 = view;
        Log.i(TAG, "initViewPager:  m_iUITypeVer = " + this.m_iUITypeVer + ", m_iModeSet = " + this.m_iModeSet);
        MyViewPager myViewPager = (MyViewPager) view5.findViewById(R.id.mPager);
        this.mViewPager = myViewPager;
        if (myViewPager != null) {
            this.mPage0 = (ImageView) view5.findViewById(R.id.page0);
            this.mPage1 = (ImageView) view5.findViewById(R.id.page1);
            this.mPage2 = (ImageView) view5.findViewById(R.id.page2);
            this.mPage3 = (ImageView) view5.findViewById(R.id.page3);
            setDefaultPageIconIfNotNull();
            if (this.m_iUIIndex == 5) {
                layoutInflater = LayoutInflater.from(this.ctx);
            } else {
                layoutInflater = LayoutInflater.from(getContext());
            }
            ILauncherStrategy iLauncherStrategy = this.launcherStrategy;
            int i = 0;
            if (iLauncherStrategy != null) {
                if (iLauncherStrategy.getPagerItemCount() != -1) {
                    this.iPagerItemViewItemCount = this.launcherStrategy.getPagerItemCount();
                }
                if (this.launcherStrategy.getLeftButtonViewArray() != null) {
                    this.mLeftButtons = this.launcherStrategy.getLeftButtonViewArray();
                }
                if (this.launcherStrategy.getPager1() != null) {
                    this.view1 = this.launcherStrategy.getPager1();
                }
                if (this.launcherStrategy.getPager2() != null) {
                    this.view2 = this.launcherStrategy.getPager2();
                }
                if (this.launcherStrategy.getPager3() != null) {
                    this.view3 = this.launcherStrategy.getPager3();
                }
                if (this.launcherStrategy.getPager4() != null) {
                    this.view4 = this.launcherStrategy.getPager4();
                }
                if (this.launcherStrategy.getPager1ButtonIdArray() != null) {
                    this.btnList1 = this.launcherStrategy.getPager1ButtonIdArray();
                }
                if (this.launcherStrategy.getPager2ButtonIdArray() != null) {
                    this.btnList2 = this.launcherStrategy.getPager2ButtonIdArray();
                }
                if (this.launcherStrategy.getPager3ButtonIdArray() != null) {
                    this.btnList3 = this.launcherStrategy.getPager3ButtonIdArray();
                }
                if (this.launcherStrategy.getPager4ButtonIdArray() != null) {
                    this.btnList4 = this.launcherStrategy.getPager4ButtonIdArray();
                }
                ILauncherStrategy iLauncherStrategy2 = this.launcherStrategy;
                if (iLauncherStrategy2 != null) {
                    this.mMainButtons = iLauncherStrategy2.getMainButtonArray();
                }
            } else if (this.m_iUITypeVer == 41) {
                if (isDefaultUI()) {
                    this.iPagerItemViewItemCount = 2;
                    this.view1 = layoutInflater.inflate(R.layout.kesaiwei_1920x720_evo_id7_whats1, (ViewGroup) null);
                    this.view2 = layoutInflater.inflate(R.layout.kesaiwei_1920x720_evo_id7_whats2, (ViewGroup) null);
                    this.view3 = layoutInflater.inflate(R.layout.kesaiwei_1920x720_evo_id7_whats3, (ViewGroup) null);
                    this.btnList1 = new int[]{R.id.btnNavi, R.id.btnWeather};
                    this.btnList2 = new int[]{R.id.btnMusic, R.id.btnBt};
                    this.btnList3 = new int[]{R.id.btnVideo, R.id.btnDashBoard};
                    View[] viewArr = {this.view1.findViewById(R.id.focusMainNavi), this.view1.findViewById(R.id.focusMainWeather), this.view2.findViewById(R.id.focusMainMusic), this.view2.findViewById(R.id.focusMainBt), this.view3.findViewById(R.id.focusMainVideo), this.view3.findViewById(R.id.focusMainDashBoard)};
                    this.mMainButtons = viewArr;
                    for (View view6 : viewArr) {
                        if (view6 != null) {
                            view6.setOnClickListener(this);
                        }
                    }
                    View view7 = this.view1;
                    if (view7 != null) {
                        this.mLayoutWeatherContent = view7.findViewById(R.id.layoutWeatherContent);
                        this.mLayoutWeatherStatus = this.view1.findViewById(R.id.layoutWeatherStatus);
                        this.mImgWeatherStatus = (ImageView) this.view1.findViewById(R.id.imgWeatherStatus);
                        this.mTvWeatherStatus = (TextView) this.view1.findViewById(R.id.tvWeatherStatus);
                        this.mWeatherPic = (ImageView) this.view1.findViewById(R.id.weatherPic);
                        this.mWeatherCity = (TextView) this.view1.findViewById(R.id.weatherCity);
                        this.mWeatherContent = (TextView) this.view1.findViewById(R.id.weatherContent);
                        this.mValueTemp = (TextView) this.view1.findViewById(R.id.valueTemp);
                        this.mUnitTemp = (TextView) this.view1.findViewById(R.id.unitTemp);
                        this.mRangeTemp = (TextView) this.view1.findViewById(R.id.rangeTemp);
                        ArrayList arrayList = new ArrayList();
                        this.mViewWeatherDetails = arrayList;
                        arrayList.add(this.view1.findViewById(R.id.viewWeatherDetail0));
                        this.mViewWeatherDetails.add(this.view1.findViewById(R.id.viewWeatherDetail1));
                        this.mViewWeatherDetails.add(this.view1.findViewById(R.id.viewWeatherDetail2));
                        ArrayList arrayList2 = new ArrayList();
                        this.mImgWeatherDetails = arrayList2;
                        arrayList2.add((ImageView) this.view1.findViewById(R.id.imgWeatherDetail0));
                        this.mImgWeatherDetails.add((ImageView) this.view1.findViewById(R.id.imgWeatherDetail1));
                        this.mImgWeatherDetails.add((ImageView) this.view1.findViewById(R.id.imgWeatherDetail2));
                        ArrayList arrayList3 = new ArrayList();
                        this.mTvWeatherDetails = arrayList3;
                        arrayList3.add((TextView) this.view1.findViewById(R.id.weatherDetail0));
                        this.mTvWeatherDetails.add((TextView) this.view1.findViewById(R.id.weatherDetail1));
                        this.mTvWeatherDetails.add((TextView) this.view1.findViewById(R.id.weatherDetail2));
                    }
                    View view8 = this.view2;
                    if (view8 != null) {
                        this.mIvMediaTypeBg = (ImageView) view8.findViewById(R.id.iv_MediaTypeBg);
                        this.mMusicCoverBg = (ImageView) this.view2.findViewById(R.id.iv_MusicCoverBg);
                        this.mMusicCover = (ImageView) this.view2.findViewById(R.id.iv_MusicCover);
                        this.mTvMusicTitleInfor = (TextView) this.view2.findViewById(R.id.tv_MusicTitleInfor);
                        this.mTvMusicArtistInfor = (TextView) this.view2.findViewById(R.id.tv_MusicArtistInfor);
                        this.mTvMusicAblumInfor = (TextView) this.view2.findViewById(R.id.tv_MusicAblumInfor);
                        this.mSkBarProgress = (SeekBar) this.view2.findViewById(R.id.SkBarProgress);
                        this.mTvCurrTime = (TextView) this.view2.findViewById(R.id.TvCurrTime);
                        this.mTvTotTime = (TextView) this.view2.findViewById(R.id.TvTotTime);
                        SeekBar seekBar = this.mSkBarProgress;
                        if (seekBar != null) {
                            seekBar.setEnabled(false);
                        }
                        TextView textView = (TextView) this.view2.findViewById(R.id.txtBTMessage);
                        this.txtBTMessage = textView;
                        if (textView != null) {
                            textView.setText(getResources().getString(R.string.lb_bt_message_not_connected));
                        }
                    }
                    View view9 = this.view3;
                    if (view9 != null) {
                        this.mTvQil = (TextView) view9.findViewById(R.id.TvQil);
                        this.mTvTemp = (TextView) this.view3.findViewById(R.id.TvTemp);
                        this.mTvHandbrake = (TextView) this.view3.findViewById(R.id.TvHandbrake);
                        this.mTvSeatBelt = (TextView) this.view3.findViewById(R.id.TvSeatBelt);
                        this.mTvShunShiSuDu = (TextView) this.view3.findViewById(R.id.TvShunShiSuDu);
                        this.mTvSpeedUnit = (TextView) this.view3.findViewById(R.id.TvSpeedUnit);
                        this.mClockSpeed = (MyQAnalogClock2) this.view3.findViewById(R.id.ClockSpeed);
                    }
                } else if (this.m_iModeSet == 19) {
                    this.iPagerItemViewItemCount = 2;
                    this.view1 = layoutInflater.inflate(R.layout.pemp_id7_whats1, (ViewGroup) null);
                    this.view2 = layoutInflater.inflate(R.layout.pemp_id7_whats2, (ViewGroup) null);
                    this.view3 = layoutInflater.inflate(R.layout.pemp_id7_whats3, (ViewGroup) null);
                    this.view4 = layoutInflater.inflate(R.layout.pemp_id7_whats4, (ViewGroup) null);
                    this.mMainButtons = new View[]{this.view1.findViewById(R.id.btnNavi), this.view1.findViewById(R.id.btnOriginalCar), this.view2.findViewById(R.id.btnBt), this.view2.findViewById(R.id.btnMusic), this.view3.findViewById(R.id.btnDashBoard), this.view3.findViewById(R.id.btnVideo), this.view4.findViewById(R.id.btnCarplay), this.view4.findViewById(R.id.btnWeather)};
                    this.btnList1 = new int[]{R.id.btnNavi, R.id.btnOriginalCar};
                    this.btnList2 = new int[]{R.id.btnBt, R.id.btnMusic, R.id.btnMusicPrev, R.id.btnMusicPlayPause, R.id.btnMusicNext};
                    this.btnList3 = new int[]{R.id.btnDashBoard, R.id.btnVideo};
                    this.btnList4 = new int[]{R.id.btnCarplay, R.id.btnWeather};
                    this.tvDateDay = (TextView) this.view1.findViewById(R.id.dateDay);
                    this.tvDateMonth = (TextView) this.view1.findViewById(R.id.dateMonth);
                    refreshDateTime();
                    this.txtBTMessage = (TextView) this.view2.findViewById(R.id.txtBTMessage);
                    View findViewById = this.view2.findViewById(R.id.musicContent);
                    this.viewMusicContent = findViewById;
                    if (findViewById != null) {
                        findViewById.setVisibility(8);
                    }
                    View findViewById2 = this.view2.findViewById(R.id.btnMusic);
                    this.btnMusic = findViewById2;
                    if (findViewById2 != null) {
                        findViewById2.setBackground(ContextCompat.getDrawable(this.mContext, R.drawable.pemp_id7_btn_music_n));
                    }
                    View findViewById3 = this.view2.findViewById(R.id.btnMusicPlayPause);
                    this.mBtnMusicPlayPause = findViewById3;
                    if (findViewById3 != null) {
                        findViewById3.setBackground(ContextCompat.getDrawable(this.mContext, R.drawable.pemp_id7_btn_music_play));
                    }
                    this.mMusicCover = (ImageView) this.view2.findViewById(R.id.iv_MusicCover);
                    this.mTvMusicTitleInfor = (TextView) this.view2.findViewById(R.id.tv_MusicTitleInfor);
                    this.mTvMusicArtistInfor = (TextView) this.view2.findViewById(R.id.tv_MusicArtistInfor);
                    this.mTvMusicAblumInfor = (TextView) this.view2.findViewById(R.id.tv_MusicAblumInfor);
                    this.mTvCurrTime = (TextView) this.view2.findViewById(R.id.TvCurrTime);
                    this.mTvTotTime = (TextView) this.view2.findViewById(R.id.TvTotTime);
                    MySeekbar mySeekbar = (MySeekbar) this.view2.findViewById(R.id.SkBarProgress2);
                    this.mSkBarProgress2 = mySeekbar;
                    if (mySeekbar != null) {
                        mySeekbar.setOnSeekListener(new MySeekbar.OnSeekListener() {
                            public void onSeeking(int i) {
                                if (MainFragment.this.mViewPager != null) {
                                    MainFragment.this.mViewPager.setScrollAble(false);
                                }
                            }

                            public void onSeekStop(int i) {
                                if (MainFragment.this.mViewPager != null) {
                                    MainFragment.this.mViewPager.setScrollAble(true);
                                }
                            }
                        });
                    }
                    this.mLayoutWeatherContent = this.view4.findViewById(R.id.layoutWeatherContent);
                    this.mLayoutWeatherStatus = this.view4.findViewById(R.id.layoutWeatherStatus);
                    this.mImgWeatherStatus = (ImageView) this.view4.findViewById(R.id.imgWeatherStatus);
                    this.mTvWeatherStatus = (TextView) this.view4.findViewById(R.id.tvWeatherStatus);
                    this.mWeatherPic = (ImageView) this.view4.findViewById(R.id.weatherPic);
                    this.mWeatherCity = (TextView) this.view4.findViewById(R.id.weatherCity);
                    this.mWeatherContent = (TextView) this.view4.findViewById(R.id.weatherContent);
                    this.mValueTemp = (TextView) this.view4.findViewById(R.id.valueTemp);
                    this.mUnitTemp = (TextView) this.view4.findViewById(R.id.unitTemp);
                    this.mRangeTemp = (TextView) this.view4.findViewById(R.id.rangeTemp);
                    this.btnDashboard = (ImageButton) this.view3.findViewById(R.id.btnDashBoard);
                    this.mTvQil = (TextView) this.view3.findViewById(R.id.TvQil);
                    this.mIvSeatBelt = (ImageView) this.view3.findViewById(R.id.imgSeatBelt);
                    this.mIvHandbrake = (ImageView) this.view3.findViewById(R.id.imgHandbrake);
                    this.myClock = (ImageView) this.view3.findViewById(R.id.myClock);
                    this.mTvShunShiSuDu = (TextView) this.view3.findViewById(R.id.tvSpeed);
                } else if (this.m_iModeSet == 21 || this.m_iModeSet == 31) {
                    this.iPagerItemViewItemCount = 3;
                    if (!this.isSmallResolution) {
                        if (this.m_iModeSet == 31) {
                            this.view1 = layoutInflater.inflate(R.layout.benz_ntg6_2_whats1, (ViewGroup) null);
                            this.view2 = layoutInflater.inflate(R.layout.benz_ntg6_2_whats2, (ViewGroup) null);
                            this.view3 = layoutInflater.inflate(R.layout.benz_ntg6_2_whats3, (ViewGroup) null);
                        } else {
                            this.view1 = layoutInflater.inflate(R.layout.benz_ntg6_whats1, (ViewGroup) null);
                            this.view2 = layoutInflater.inflate(R.layout.benz_ntg6_whats2, (ViewGroup) null);
                            this.view3 = layoutInflater.inflate(R.layout.benz_ntg6_whats3, (ViewGroup) null);
                        }
                    } else if (this.m_iModeSet == 31) {
                        this.view1 = layoutInflater.inflate(R.layout.benz_ntg6_1024x600_v1_whats1, (ViewGroup) null);
                        this.view2 = layoutInflater.inflate(R.layout.benz_ntg6_1024x600_v1_whats2, (ViewGroup) null);
                        this.view3 = layoutInflater.inflate(R.layout.benz_ntg6_1024x600_v1_whats3, (ViewGroup) null);
                    } else {
                        this.view1 = layoutInflater.inflate(R.layout.benz_ntg6_1024x600_whats1, (ViewGroup) null);
                        this.view2 = layoutInflater.inflate(R.layout.benz_ntg6_1024x600_whats2, (ViewGroup) null);
                        this.view3 = layoutInflater.inflate(R.layout.benz_ntg6_1024x600_whats3, (ViewGroup) null);
                    }
                    this.mTvMusicTitleInfor = (TextView) this.view2.findViewById(R.id.tv_MusicTitleInfor);
                    this.txtBTMessage = (TextView) this.view1.findViewById(R.id.txtBTMessage);
                    this.btnMusic = this.view2.findViewById(R.id.btnMusic);
                    this.viewMusic = this.view2.findViewById(R.id.viewMusic);
                    this.mMusicCover = (ImageView) this.view2.findViewById(R.id.iv_MusicCover);
                    this.viewMusic.setOnClickListener(this);
                    this.btnList1 = new int[]{R.id.btnBt, R.id.btnBtPrev, R.id.btnBtNext, R.id.btnNavi, R.id.btnNavPrev, R.id.btnNavNext, R.id.btnOriginalCar, R.id.btnOriginalPrev, R.id.btnOriginalNext};
                    this.btnList2 = new int[]{R.id.btnVideo, R.id.btnVideoPrev, R.id.btnVideoNext, R.id.btnMusic, R.id.btnMusicPrev, R.id.btnMusicNext, R.id.btnSettins, R.id.btnSettinsPrev, R.id.btnSettinsNext};
                    this.btnList3 = new int[]{R.id.btnCarplay, R.id.btnCarplayPrev, R.id.btnCarplayNext, R.id.btnApps, R.id.btnAppsPrev, R.id.btnAppsNext, R.id.btnDashBoard, R.id.btnDashBoardPrev, R.id.btnDashBoardNext};
                    this.mMainButtons = new View[]{this.view1.findViewById(R.id.btnBt), this.view1.findViewById(R.id.btnNavi), this.view1.findViewById(R.id.btnOriginalCar), this.view2.findViewById(R.id.btnVideo), this.view2.findViewById(R.id.btnMusic), this.view2.findViewById(R.id.btnSettins), this.view3.findViewById(R.id.btnCarplay), this.view3.findViewById(R.id.btnApps), this.view3.findViewById(R.id.btnDashBoard)};
                } else if (this.m_iUIIndex != 4 || this.m_iModeSet == 29) {
                    if (this.m_iUIIndex == 5) {
                        if (this.m_iModeSet == 39) {
                            this.view1 = layoutInflater.inflate(R.layout.landrover_copilot_whats1, (ViewGroup) null);
                            this.view2 = layoutInflater.inflate(R.layout.landrover_copilot_whats2, (ViewGroup) null);
                            this.view3 = layoutInflater.inflate(R.layout.landrover_copilot_whats3, (ViewGroup) null);
                            this.btnList1 = new int[]{R.id.btnMusic, R.id.btnMusicPrev, R.id.btnMusicPlayPause, R.id.btnMusicNext, R.id.btnVideo, R.id.btnCarplay};
                            this.btnList2 = new int[]{R.id.btnApps, R.id.btnBtTransmit, R.id.btnBt};
                            this.btnList3 = new int[]{R.id.btnSettins};
                            View view10 = this.view1;
                            if (view10 != null) {
                                View findViewById4 = view10.findViewById(R.id.btnMusicPlayPause);
                                this.mBtnMusicPlayPause = findViewById4;
                                if (findViewById4 != null) {
                                    findViewById4.setBackgroundResource(getAttrId(R.attr.landrover_main_btn_music_play));
                                }
                                SeekBar seekBar2 = (SeekBar) this.view1.findViewById(R.id.SkBarProgress);
                                this.mSkBarProgress = seekBar2;
                                if (seekBar2 != null) {
                                    seekBar2.setEnabled(false);
                                }
                                this.mTvMusicTitleInfor = (TextView) this.view1.findViewById(R.id.tv_MusicTitleInfor);
                                this.mTvMusicArtistInfor = (TextView) this.view1.findViewById(R.id.tv_MusicArtistInfor);
                                this.mTvMusicAblumInfor = (TextView) this.view1.findViewById(R.id.tv_MusicAblumInfor);
                                this.mTvVideoMessage = (TextView) this.view1.findViewById(R.id.tv_VideoMessage);
                                this.mTvVideoTitleInfor = (TextView) this.view1.findViewById(R.id.tv_VideoTitleInfor);
                                this.mChkVideoState = (CheckBox) this.view1.findViewById(R.id.chkVideoState);
                            }
                            View view11 = this.view2;
                            if (view11 != null) {
                                TextView textView2 = (TextView) view11.findViewById(R.id.txtBTMessage);
                                this.txtBTMessage = textView2;
                                if (textView2 != null) {
                                    textView2.setText(getResources().getString(R.string.lb_bt_message_not_connected));
                                }
                            }
                        } else {
                            this.view1 = layoutInflater.inflate(R.layout.landrover_whats1, (ViewGroup) null);
                            this.view2 = layoutInflater.inflate(R.layout.landrover_whats2, (ViewGroup) null);
                            this.view3 = layoutInflater.inflate(R.layout.landrover_whats3, (ViewGroup) null);
                            this.view4 = layoutInflater.inflate(R.layout.landrover_whats4, (ViewGroup) null);
                            this.btnList1 = new int[]{R.id.btnNavi, R.id.btnMusic, R.id.btnMusicPrev, R.id.btnMusicPlayPause, R.id.btnMusicNext, R.id.btnBt, R.id.btnBtRecords, R.id.btnBtContacts, R.id.btnBtMusic};
                            this.btnList2 = new int[]{R.id.btnVideo, R.id.btnWeather, R.id.btnSettins};
                            this.btnList3 = new int[]{R.id.btnOriginalCar, R.id.btnApps, R.id.btnDashBoard};
                            this.btnList4 = new int[]{R.id.btnCarplay, R.id.btnDvr, R.id.btnNull};
                            View view12 = this.view1;
                            if (view12 != null) {
                                View findViewById5 = view12.findViewById(R.id.btnMusicPlayPause);
                                this.mBtnMusicPlayPause = findViewById5;
                                if (findViewById5 != null) {
                                    findViewById5.setBackgroundResource(getAttrId(R.attr.landrover_main_btn_music_play));
                                }
                                SeekBar seekBar3 = (SeekBar) this.view1.findViewById(R.id.SkBarProgress);
                                this.mSkBarProgress = seekBar3;
                                if (seekBar3 != null) {
                                    seekBar3.setEnabled(false);
                                }
                                this.mTvMusicTitleInfor = (TextView) this.view1.findViewById(R.id.tv_MusicTitleInfor);
                                this.mTvMusicArtistInfor = (TextView) this.view1.findViewById(R.id.tv_MusicArtistInfor);
                                this.mTvMusicAblumInfor = (TextView) this.view1.findViewById(R.id.tv_MusicAblumInfor);
                                TextView textView3 = (TextView) this.view1.findViewById(R.id.txtBTMessage);
                                this.txtBTMessage = textView3;
                                if (textView3 != null) {
                                    textView3.setText(getResources().getString(R.string.lb_bt_message_not_connected));
                                }
                            }
                            View view13 = this.view2;
                            if (view13 != null) {
                                this.mTvVideoMessage = (TextView) view13.findViewById(R.id.tv_VideoMessage);
                                this.mTvVideoTitleInfor = (TextView) this.view2.findViewById(R.id.tv_VideoTitleInfor);
                                this.mChkVideoState = (CheckBox) this.view2.findViewById(R.id.chkVideoState);
                                this.mLayoutWeatherContent = this.view2.findViewById(R.id.layoutWeatherContent);
                                this.mLayoutWeatherStatus = this.view2.findViewById(R.id.layoutWeatherStatus);
                                this.mImgWeatherStatus = (ImageView) this.view2.findViewById(R.id.imgWeatherStatus);
                                this.mTvWeatherStatus = (TextView) this.view2.findViewById(R.id.tvWeatherStatus);
                                this.mWeatherPic = (ImageView) this.view2.findViewById(R.id.weatherPic);
                                this.mWeatherCity = (TextView) this.view2.findViewById(R.id.weatherCity);
                                this.mWeatherContent = (TextView) this.view2.findViewById(R.id.weatherContent);
                                this.mValueTemp = (TextView) this.view2.findViewById(R.id.valueTemp);
                                this.mUnitTemp = (TextView) this.view2.findViewById(R.id.unitTemp);
                                this.mRangeTemp = (TextView) this.view2.findViewById(R.id.rangeTemp);
                            }
                            View view14 = this.view3;
                            if (view14 != null) {
                                this.mChkBelt = (CheckBox) view14.findViewById(R.id.chkBelt);
                                this.mChkBrake = (CheckBox) this.view3.findViewById(R.id.chkBrake);
                                this.mTvShunShiSuDu = (TextView) this.view3.findViewById(R.id.TvShunShiSuDu);
                                this.mTvSpeedUnit = (TextView) this.view3.findViewById(R.id.TvSpeedUnit);
                                this.mTvTemp = (TextView) this.view3.findViewById(R.id.TvTemp);
                            }
                        }
                    } else if (this.m_iModeSet == 26) {
                        this.iPagerItemViewItemCount = 5;
                        this.view1 = layoutInflater.inflate(R.layout.common_gs_whats1, (ViewGroup) null);
                        View inflate = layoutInflater.inflate(R.layout.common_gs_whats2, (ViewGroup) null);
                        this.view2 = inflate;
                        this.btnList1 = new int[]{R.id.btnOriginalCar, R.id.btnDashBoard, R.id.btnNavi, R.id.btnBt, R.id.btnApps};
                        this.btnList2 = new int[]{R.id.btnMusic, R.id.btnVideo, R.id.btnBrowser, R.id.btnFile, R.id.btnSettins};
                        this.mMainButtons = new View[10];
                        if (!(this.view1 == null || inflate == null)) {
                            for (int i2 = 0; i2 < 5; i2++) {
                                this.mMainButtons[i2] = this.view1.findViewById(this.btnList1[i2]);
                                this.mMainButtons[i2 + 5] = this.view2.findViewById(this.btnList2[i2]);
                            }
                            this.mTvQil = (TextView) this.view1.findViewById(R.id.TvQil);
                            this.txtBTMessage = (TextView) this.view1.findViewById(R.id.txtBTMessage);
                            this.mTvMusicTitleInfor = (TextView) this.view2.findViewById(R.id.tv_MusicTitleInfor);
                            this.myRotateClock = (ImageView) this.view1.findViewById(R.id.imgRotateClock);
                        }
                    } else {
                        this.iPagerItemViewItemCount = 2;
                        this.view1 = layoutInflater.inflate(R.layout.kesaiwei_1920x720_evo_id7_whats1, (ViewGroup) null);
                        this.view2 = layoutInflater.inflate(R.layout.kesaiwei_1920x720_evo_id7_whats2, (ViewGroup) null);
                        this.view3 = layoutInflater.inflate(R.layout.kesaiwei_1920x720_evo_id7_whats3, (ViewGroup) null);
                        this.btnList1 = new int[]{R.id.btnNavi, R.id.btnWeather};
                        this.btnList2 = new int[]{R.id.btnMusic, R.id.btnBt};
                        this.btnList3 = new int[]{R.id.btnVideo, R.id.btnDashBoard};
                        this.mMainButtons = new View[]{this.view1.findViewById(R.id.btnNavi), this.view1.findViewById(R.id.btnWeather), this.view2.findViewById(R.id.btnMusic), this.view2.findViewById(R.id.btnBt), this.view3.findViewById(R.id.btnVideo), this.view3.findViewById(R.id.btnDashBoard)};
                        View view15 = this.view1;
                        if (view15 != null) {
                            this.mWeatherPic = (ImageView) view15.findViewById(R.id.weatherPic);
                            this.mWeatherCity = (TextView) this.view1.findViewById(R.id.weatherCity);
                            this.mWeatherContent = (TextView) this.view1.findViewById(R.id.weatherContent);
                            this.mValueTemp = (TextView) this.view1.findViewById(R.id.valueTemp);
                            this.mUnitTemp = (TextView) this.view1.findViewById(R.id.unitTemp);
                            this.mRangeTemp = (TextView) this.view1.findViewById(R.id.rangeTemp);
                            ArrayList arrayList4 = new ArrayList();
                            this.mViewWeatherDetails = arrayList4;
                            arrayList4.add(this.view1.findViewById(R.id.viewWeatherDetail0));
                            this.mViewWeatherDetails.add(this.view1.findViewById(R.id.viewWeatherDetail1));
                            this.mViewWeatherDetails.add(this.view1.findViewById(R.id.viewWeatherDetail2));
                            ArrayList arrayList5 = new ArrayList();
                            this.mImgWeatherDetails = arrayList5;
                            arrayList5.add((ImageView) this.view1.findViewById(R.id.imgWeatherDetail0));
                            this.mImgWeatherDetails.add((ImageView) this.view1.findViewById(R.id.imgWeatherDetail1));
                            this.mImgWeatherDetails.add((ImageView) this.view1.findViewById(R.id.imgWeatherDetail2));
                            ArrayList arrayList6 = new ArrayList();
                            this.mTvWeatherDetails = arrayList6;
                            arrayList6.add((TextView) this.view1.findViewById(R.id.weatherDetail0));
                            this.mTvWeatherDetails.add((TextView) this.view1.findViewById(R.id.weatherDetail1));
                            this.mTvWeatherDetails.add((TextView) this.view1.findViewById(R.id.weatherDetail2));
                        }
                        View view16 = this.view2;
                        if (view16 != null) {
                            this.mIvMediaTypeBg = (ImageView) view16.findViewById(R.id.iv_MediaTypeBg);
                            this.mMusicCoverBg = (ImageView) this.view2.findViewById(R.id.iv_MusicCoverBg);
                            this.mMusicCover = (ImageView) this.view2.findViewById(R.id.iv_MusicCover);
                            this.mTvMusicTitleInfor = (TextView) this.view2.findViewById(R.id.tv_MusicTitleInfor);
                            this.mTvMusicArtistInfor = (TextView) this.view2.findViewById(R.id.tv_MusicArtistInfor);
                            this.mTvMusicAblumInfor = (TextView) this.view2.findViewById(R.id.tv_MusicAblumInfor);
                            this.mSkBarProgress = (SeekBar) this.view2.findViewById(R.id.SkBarProgress);
                            this.mTvCurrTime = (TextView) this.view2.findViewById(R.id.TvCurrTime);
                            this.mTvTotTime = (TextView) this.view2.findViewById(R.id.TvTotTime);
                            SeekBar seekBar4 = this.mSkBarProgress;
                            if (seekBar4 != null) {
                                seekBar4.setEnabled(false);
                            }
                            TextView textView4 = (TextView) this.view2.findViewById(R.id.txtBTMessage);
                            this.txtBTMessage = textView4;
                            if (textView4 != null) {
                                textView4.setText(getResources().getString(R.string.lb_bt_message_not_connected));
                            }
                        }
                        View view17 = this.view3;
                        if (view17 != null) {
                            this.mTvQil = (TextView) view17.findViewById(R.id.TvQil);
                            this.mTvTemp = (TextView) this.view3.findViewById(R.id.TvTemp);
                            this.mTvHandbrake = (TextView) this.view3.findViewById(R.id.TvHandbrake);
                            this.mTvSeatBelt = (TextView) this.view3.findViewById(R.id.TvSeatBelt);
                            this.mTvShunShiSuDu = (TextView) this.view3.findViewById(R.id.TvShunShiSuDu);
                            this.mTvSpeedUnit = (TextView) this.view3.findViewById(R.id.TvSpeedUnit);
                            this.mClockSpeed = (MyQAnalogClock2) this.view3.findViewById(R.id.ClockSpeed);
                        }
                    }
                } else if (this.m_iModeSet == 40) {
                    this.iPagerItemViewItemCount = 10;
                    this.mMainButtons = new View[13];
                    this.view1 = layoutInflater.inflate(R.layout.audi_bl_page1, (ViewGroup) null);
                    this.view2 = layoutInflater.inflate(R.layout.audi_bl_page2, (ViewGroup) null);
                    this.btnList1 = new int[]{R.id.btnVideo, R.id.btnMusic, R.id.btnBt, R.id.btnNavi, R.id.btnCarplay, R.id.btnOriginalCar, R.id.btnWeather, R.id.btnSettins, R.id.btnDvr, R.id.btnDashBoard};
                    this.btnList2 = new int[]{R.id.btnFile, R.id.btnBrowser, R.id.btnApps};
                    int i3 = 0;
                    while (true) {
                        int[] iArr6 = this.btnList1;
                        if (i3 >= iArr6.length) {
                            break;
                        }
                        this.mMainButtons[i3] = this.view1.findViewById(iArr6[i3]);
                        i3++;
                    }
                    int i4 = 0;
                    while (true) {
                        int[] iArr7 = this.btnList2;
                        if (i4 >= iArr7.length) {
                            break;
                        }
                        this.mMainButtons[this.iPagerItemViewItemCount + i4] = this.view2.findViewById(iArr7[i4]);
                        i4++;
                    }
                } else {
                    this.iPagerItemViewItemCount = 8;
                    this.mMainButtons = new View[13];
                    if (this.m_iModeSet == 23) {
                        if ("1560x700".equals(LauncherModel.getInstance().mStrResolution)) {
                            this.view1 = layoutInflater.inflate(R.layout.audi_whats1_1560x700, (ViewGroup) null);
                            this.view2 = layoutInflater.inflate(R.layout.audi_whats2_1560x700, (ViewGroup) null);
                        } else if ("1024x600".equalsIgnoreCase(LauncherModel.getInstance().mStrResolution)) {
                            this.view1 = layoutInflater.inflate(R.layout.small_audi_whats1, (ViewGroup) null);
                            this.view2 = layoutInflater.inflate(R.layout.small_audi_whats2, (ViewGroup) null);
                        } else {
                            this.view1 = layoutInflater.inflate(R.layout.audi_whats1, (ViewGroup) null);
                            this.view2 = layoutInflater.inflate(R.layout.audi_whats2, (ViewGroup) null);
                        }
                        this.btnList1 = new int[]{R.id.btnVideo, R.id.btnMusic, R.id.btnBt, R.id.btnNavi, R.id.btnCarplay, R.id.btnOriginalCar, R.id.btnWeather, R.id.btnSettins};
                        iArr5 = new int[]{R.id.imgVideo, R.id.imgMusic, R.id.imgBt, R.id.imgNavi, R.id.imgCarplay, R.id.imgOriginalCar, R.id.imgWeather, R.id.imgSettings};
                    } else {
                        this.view1 = layoutInflater.inflate(R.layout.audi_fy_whats1, (ViewGroup) null);
                        this.view2 = layoutInflater.inflate(R.layout.audi_fy_whats2, (ViewGroup) null);
                        this.btnList1 = new int[]{R.id.btnNavi, R.id.btnMusic, R.id.btnVideo, R.id.btnCarplay, R.id.btnBt, R.id.btnOriginalCar, R.id.btnWeather, R.id.btnSettins};
                        iArr5 = new int[]{R.id.imgNavi, R.id.imgMusic, R.id.imgVideo, R.id.imgCarplay, R.id.imgBt, R.id.imgOriginalCar, R.id.imgWeather, R.id.imgSettings};
                    }
                    this.btnList2 = new int[]{R.id.btnDvr, R.id.btnDashBoard, R.id.btnFile, R.id.btnBrowser, R.id.btnApps};
                    int[] iArr8 = {R.id.imgDvr, R.id.imgDashboard, R.id.imgFile, R.id.imgBrowser, R.id.imgApps};
                    int i5 = 0;
                    while (true) {
                        int[] iArr9 = this.btnList1;
                        if (i5 >= iArr9.length) {
                            break;
                        }
                        this.mMainButtons[i5] = this.view1.findViewById(iArr9[i5]);
                        i5++;
                    }
                    int i6 = 0;
                    while (true) {
                        int[] iArr10 = this.btnList2;
                        if (i6 >= iArr10.length) {
                            break;
                        }
                        this.mMainButtons[this.iPagerItemViewItemCount + i6] = this.view2.findViewById(iArr10[i6]);
                        i6++;
                    }
                    for (int findViewById6 : iArr5) {
                        this.imgViews.add((ImageView) this.view1.findViewById(findViewById6));
                    }
                    for (int i7 = 0; i7 < 5; i7++) {
                        this.imgViews.add((ImageView) this.view2.findViewById(iArr8[i7]));
                    }
                }
            }
            refreshWeatherView();
            if (this.mEvtService != null) {
                refreshView();
            }
            if (this.view1 != null && (iArr4 = this.btnList1) != null && iArr4.length > 0) {
                int i8 = 0;
                while (true) {
                    int[] iArr11 = this.btnList1;
                    if (i8 >= iArr11.length) {
                        break;
                    }
                    View findViewById7 = this.view1.findViewById(iArr11[i8]);
                    if (findViewById7 != null) {
                        findViewById7.setOnClickListener(this);
                        if (this.m_iUIIndex == 5 || this.m_iModeSet == 40) {
                            findViewById7.setOnLongClickListener(new View.OnLongClickListener() {
                                public final boolean onLongClick(View view) {
                                    return MainFragment.this.lambda$initViewPager$7$MainFragment(view);
                                }
                            });
                        }
                    }
                    i8++;
                }
            }
            if (this.view2 != null && (iArr3 = this.btnList2) != null && iArr3.length > 0) {
                int i9 = 0;
                while (true) {
                    int[] iArr12 = this.btnList2;
                    if (i9 >= iArr12.length) {
                        break;
                    }
                    View findViewById8 = this.view2.findViewById(iArr12[i9]);
                    if (findViewById8 != null) {
                        findViewById8.setOnClickListener(this);
                        if (this.m_iUIIndex == 5 || this.m_iModeSet == 40) {
                            findViewById8.setOnLongClickListener(new View.OnLongClickListener() {
                                public final boolean onLongClick(View view) {
                                    return MainFragment.this.lambda$initViewPager$8$MainFragment(view);
                                }
                            });
                        }
                    }
                    i9++;
                }
            }
            if (this.view3 != null && (iArr2 = this.btnList3) != null && iArr2.length > 0) {
                int i10 = 0;
                while (true) {
                    int[] iArr13 = this.btnList3;
                    if (i10 >= iArr13.length) {
                        break;
                    }
                    View findViewById9 = this.view3.findViewById(iArr13[i10]);
                    if (findViewById9 != null) {
                        findViewById9.setOnClickListener(this);
                        if (this.m_iUIIndex == 5) {
                            findViewById9.setOnLongClickListener(new View.OnLongClickListener() {
                                public final boolean onLongClick(View view) {
                                    return MainFragment.this.lambda$initViewPager$9$MainFragment(view);
                                }
                            });
                        }
                    }
                    i10++;
                }
            }
            if (this.view4 != null && (iArr = this.btnList4) != null && iArr.length > 0) {
                while (true) {
                    int[] iArr14 = this.btnList4;
                    if (i >= iArr14.length) {
                        break;
                    }
                    View findViewById10 = this.view4.findViewById(iArr14[i]);
                    if (findViewById10 != null) {
                        findViewById10.setOnClickListener(this);
                        if (this.m_iUIIndex == 5) {
                            findViewById10.setOnLongClickListener(new View.OnLongClickListener() {
                                public final boolean onLongClick(View view) {
                                    return MainFragment.this.lambda$initViewPager$10$MainFragment(view);
                                }
                            });
                        }
                    }
                    i++;
                }
            }
            if (this.m_iUIIndex == 6) {
                refreshBenzButtonsTheme();
            }
            ArrayList<View> arrayList7 = new ArrayList<>();
            this.mViews = arrayList7;
            View view18 = this.view1;
            if (view18 != null) {
                arrayList7.add(view18);
            }
            View view19 = this.view2;
            if (view19 != null) {
                this.mViews.add(view19);
            }
            View view20 = this.view3;
            if (view20 != null) {
                this.mViews.add(view20);
            }
            View view21 = this.view4;
            if (view21 != null) {
                this.mViews.add(view21);
            }
            if (this.m_iUIIndex == 5) {
                Iterator<View> it = this.mViews.iterator();
                while (it.hasNext()) {
                    View findViewById11 = it.next().findViewById(R.id.rootView);
                    if (findViewById11 != null) {
                        findViewById11.setOnLongClickListener(new View.OnLongClickListener() {
                            public final boolean onLongClick(View view) {
                                return MainFragment.this.lambda$initViewPager$11$MainFragment(view);
                            }
                        });
                    }
                }
            }
            AnonymousClass18 r1 = new PagerAdapter() {
                public boolean isViewFromObject(View view, Object obj) {
                    return view == obj;
                }

                public int getCount() {
                    return MainFragment.this.mViews.size();
                }

                public Object instantiateItem(ViewGroup viewGroup, int i) {
                    if (MainFragment.this.m_iUITypeVer == 48 && (i = i % MainFragment.this.mViews.size()) < 0) {
                        i += MainFragment.this.mViews.size();
                    }
                    viewGroup.addView((View) MainFragment.this.mViews.get(i));
                    return MainFragment.this.mViews.get(i);
                }

                public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
                    if (MainFragment.this.m_iUITypeVer == 48 && (i = i % MainFragment.this.mViews.size()) < 0) {
                        i += MainFragment.this.mViews.size();
                    }
                    viewGroup.removeView((View) MainFragment.this.mViews.get(i));
                }
            };
            this.mPagerAdapter = r1;
            MyViewPager myViewPager2 = this.mViewPager;
            if (myViewPager2 != null) {
                myViewPager2.setAdapter(r1);
                this.mViewPager.setOnPageChangeListener(new MyOnPageChangeListener());
            }
            setSpeedUnit();
        }
    }

    public /* synthetic */ boolean lambda$initViewPager$7$MainFragment(View view) {
        ThemeDialog themeDialog2 = this.themeDialog;
        if (themeDialog2 == null) {
            return true;
        }
        themeDialog2.showThemeDialog();
        return true;
    }

    public /* synthetic */ boolean lambda$initViewPager$8$MainFragment(View view) {
        ThemeDialog themeDialog2 = this.themeDialog;
        if (themeDialog2 == null) {
            return true;
        }
        themeDialog2.showThemeDialog();
        return true;
    }

    public /* synthetic */ boolean lambda$initViewPager$9$MainFragment(View view) {
        ThemeDialog themeDialog2 = this.themeDialog;
        if (themeDialog2 == null) {
            return true;
        }
        themeDialog2.showThemeDialog();
        return true;
    }

    public /* synthetic */ boolean lambda$initViewPager$10$MainFragment(View view) {
        ThemeDialog themeDialog2 = this.themeDialog;
        if (themeDialog2 == null) {
            return true;
        }
        themeDialog2.showThemeDialog();
        return true;
    }

    public /* synthetic */ boolean lambda$initViewPager$11$MainFragment(View view) {
        ThemeDialog themeDialog2 = this.themeDialog;
        if (themeDialog2 == null) {
            return true;
        }
        themeDialog2.showThemeDialog();
        return true;
    }

    private void initRecyclerView(View view) {
        this.mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerViewMain);
        Log.d(TAG, "initRecyclerView mRecyclerView = " + this.mRecyclerView);
        if (this.mRecyclerView != null) {
            this.iPagerItemViewItemCount = this.iRows * this.iColumns;
            this.mRecyclerViewEdit = (RecyclerView) view.findViewById(R.id.recyclerViewEdit);
            if (this.mRecyclerView != null) {
                this.itemUtil = new ItemUtil(this.mContext, new ItemUtil.AppUtilCallback() {
                    public void onItemClickByTag(String str) {
                        if ("showAppList".equals(str)) {
                            LauncherModel.getInstance().showAppList(true);
                        } else if (!LauncherModel.getInstance().inEditMode) {
                            if ("playPrev".equals(str)) {
                                MainFragment.this.playPrev();
                            } else if ("playNext".equals(str)) {
                                MainFragment.this.playNext();
                            } else {
                                MainFragment.this.onItemClickByTag(str);
                            }
                        }
                    }

                    public void refreshMainFocusRecyclerView(int i) {
                        MainFragment.this.refreshMainFocusRecyclerView(i);
                    }

                    public void addThirdItem() {
                        Log.d(MainFragment.TAG, "addThirdItem");
                        if (MainFragment.this.mMainAdapter != null) {
                            MainFragment.this.mMainAdapter.setData(MainFragment.this.itemUtil.getAllItemTags());
                            MainFragment.this.mMainAdapter.notifyDataSetChanged();
                        }
                        if (MainFragment.this.mMainAdapterEdit != null) {
                            MainFragment.this.mMainAdapterEdit.setData(MainFragment.this.itemUtil.getAllItemTags());
                            MainFragment.this.mMainAdapterEdit.notifyDataSetChanged();
                        }
                        MainFragment.this.refreshIconPaging();
                    }

                    public void deleteThirdItem() {
                        Log.d(MainFragment.TAG, "deleteThirdItem");
                        if (MainFragment.this.mMainAdapter != null) {
                            MainFragment.this.mMainAdapter.setData(MainFragment.this.itemUtil.getAllItemTags());
                            MainFragment.this.mMainAdapter.notifyDataSetChanged();
                        }
                        if (MainFragment.this.mMainAdapterEdit != null) {
                            MainFragment.this.mMainAdapterEdit.setData(MainFragment.this.itemUtil.getAllItemTags());
                            MainFragment.this.mMainAdapterEdit.notifyDataSetChanged();
                        }
                        MainFragment.this.refreshIconPaging();
                    }
                });
                MainAdapter mainAdapter = new MainAdapter(this.mContext, this.itemUtil, false);
                this.mMainAdapter = mainAdapter;
                mainAdapter.setData(this.itemUtil.getAllItemTags());
                this.itemSize = this.itemUtil.getAllItemTags().size();
                this.mRecyclerView.setAdapter(this.mMainAdapter);
                HorizontalPageLayoutManager horizontalPageLayoutManager = new HorizontalPageLayoutManager(this.iRows, this.iColumns);
                this.mRecyclerView.setItemAnimator((RecyclerView.ItemAnimator) null);
                PagingScrollHelper pagingScrollHelper = new PagingScrollHelper();
                this.mScrollHelper = pagingScrollHelper;
                pagingScrollHelper.setINTERVAL(4.0d);
                this.mScrollHelper.setTime(2);
                this.mScrollHelper.setOnPageChangeListener(this);
                this.mScrollHelper.setUpRecycleView(this.mRecyclerView);
                this.mRecyclerView.setLayoutManager(horizontalPageLayoutManager);
                this.mMainAdapter.setPageHelper(this.mScrollHelper);
                this.mMainAdapter.setOnItemClickListener(new MainAdapter.OnItemClickListener() {
                    public final void onItemClick(int i) {
                        MainFragment.this.lambda$initRecyclerView$12$MainFragment(i);
                    }
                });
                this.mMainAdapter.setOnItemLongClickListener(new MainAdapter.OnItemLongClickListener() {
                    public final void onItemLongClick(int i) {
                        MainFragment.this.lambda$initRecyclerView$13$MainFragment(i);
                    }
                });
            }
            if (this.mRecyclerViewEdit != null) {
                MainAdapter mainAdapter2 = new MainAdapter(this.mContext, this.itemUtil, true);
                this.mMainAdapterEdit = mainAdapter2;
                mainAdapter2.setData(this.itemUtil.getAllItemTags());
                this.mRecyclerViewEdit.setAdapter(this.mMainAdapterEdit);
                this.mRecyclerViewEdit.setLayoutManager(new LinearLayoutManager(this.mContext, 0, false));
                new ItemTouchHelper(new MyItemTouchHelperCallback(this.mMainAdapterEdit)).attachToRecyclerView(this.mRecyclerViewEdit);
                this.mMainAdapterEdit.setOnItemClickListener(new MainAdapter.OnItemClickListener() {
                    public final void onItemClick(int i) {
                        MainFragment.this.lambda$initRecyclerView$14$MainFragment(i);
                    }
                });
                this.mMainAdapterEdit.setOnEditModeChangeListener(new MainAdapter.OnEditModeChangeListener() {
                    public final void onEditModeChange(boolean z) {
                        MainFragment.this.lambda$initRecyclerView$15$MainFragment(z);
                    }
                });
            }
            this.mPageIconLayout = (LinearLayout) view.findViewById(R.id.pageIconLayout);
            refreshIconPaging();
        }
    }

    public /* synthetic */ void lambda$initRecyclerView$12$MainFragment(int i) {
        onItemClickByTag(this.itemUtil.getAllItemTags().get(i));
        refreshMainFocusRecyclerView(i);
    }

    public /* synthetic */ void lambda$initRecyclerView$13$MainFragment(int i) {
        if (this.editEnable && !LauncherModel.getInstance().inEditMode) {
            Log.d(TAG, "position = " + i);
            enterEditMode(i);
        }
    }

    public /* synthetic */ void lambda$initRecyclerView$14$MainFragment(int i) {
        if ("AppList".equals(this.itemUtil.getAllItemTags().get(i))) {
            enterApplist();
        }
        refreshMainFocusRecyclerView(i);
    }

    public /* synthetic */ void lambda$initRecyclerView$15$MainFragment(boolean z) {
        if (!z) {
            exitEditMode();
        }
    }

    public void refreshIconPagingIndex(int i) {
        List<CheckBox> list = this.chkPagings;
        if (list != null && !list.isEmpty()) {
            int i2 = 0;
            while (i2 < this.chkPagings.size()) {
                this.chkPagings.get(i2).setChecked(i2 == i);
                i2++;
            }
        }
    }

    public void refreshIconPaging() {
        LinearLayout linearLayout = this.mPageIconLayout;
        if (linearLayout != null) {
            linearLayout.removeAllViews();
            int size = this.itemUtil.getAllItemTags().size();
            this.itemSize = size;
            int i = this.iPagerItemViewItemCount;
            int i2 = (size / i) + (size % i > 0 ? 1 : 0);
            this.chkPagings = new ArrayList();
            int i3 = 0;
            while (i3 < i2) {
                View inflate = View.inflate(this.mContext, R.layout.benz_main_page_icon_layout, (ViewGroup) null);
                CheckBox checkBox = (CheckBox) inflate.findViewById(R.id.chkPaging);
                if (checkBox != null) {
                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(EventUtils.getPx(this.mContext, 39), EventUtils.getPx(this.mContext, 39));
                    if (this.m_iModeSet == 18 || this.m_iModeSet == 30) {
                        checkBox.setBackgroundResource(R.drawable.benz_chk_page_icon);
                        if (this.isSmallResolution) {
                            layoutParams = new LinearLayout.LayoutParams(EventUtils.getPx(this.mContext, 21), EventUtils.getPx(this.mContext, 21));
                            if (i3 > 0) {
                                layoutParams.setMarginStart(EventUtils.getPx(this.mContext, 6));
                            }
                        } else {
                            layoutParams = new LinearLayout.LayoutParams(EventUtils.getPx(this.mContext, 39), EventUtils.getPx(this.mContext, 39));
                        }
                    } else if (this.m_iModeSet == 22) {
                        checkBox.setBackgroundResource(R.drawable.benz_fy_chk_page_icon);
                        layoutParams = new LinearLayout.LayoutParams(EventUtils.getPx(this.mContext, 16), EventUtils.getPx(this.mContext, 16));
                        layoutParams.setMarginStart(EventUtils.getPx(this.mContext, 10));
                    } else if (this.m_iModeSet == 27) {
                        checkBox.setBackgroundResource(R.drawable.audi_ty_chk_page_icon);
                        layoutParams = new LinearLayout.LayoutParams(EventUtils.getPx(this.mContext, 43), EventUtils.getPx(this.mContext, 23));
                    } else if (this.m_iModeSet == 29) {
                        checkBox.setBackgroundResource(R.drawable.audi_cdlc_chk_page_icon);
                        layoutParams = new LinearLayout.LayoutParams(EventUtils.getPx(this.mContext, 17), EventUtils.getPx(this.mContext, 16));
                        layoutParams.setMarginStart(EventUtils.getPx(this.mContext, 15));
                    }
                    this.mPageIconLayout.addView(inflate, layoutParams);
                    this.chkPagings.add(checkBox);
                    checkBox.setChecked(i3 == this.iLastMainPageIndex);
                }
                i3++;
            }
        }
    }

    public void setHandbrakeStatus(boolean z) {
        TextView textView = this.mTvHandbrake;
        if (textView != null) {
            textView.setText(getContext().getString(z ? R.string.lb_Hand_brake_release : R.string.lb_Hand_brake_pull));
        }
        ImageView imageView = this.mIvHandbrake;
        if (imageView != null) {
            imageView.setBackgroundResource(z ? R.drawable.pemp_id8_main_edit_icon_dashboard_brake_f : R.drawable.pemp_id8_main_edit_icon_dashboard_brake);
        }
        CheckBox checkBox = this.mChkBrake;
        if (checkBox != null) {
            checkBox.setChecked(z);
        }
    }

    public void setSeatBeltStatus(boolean z) {
        TextView textView = this.mTvSeatBelt;
        if (textView != null) {
            textView.setText(getContext().getString(z ? R.string.lb_Seat_belt_Yes : R.string.lb_Seat_belt_No));
        }
        ImageView imageView = this.mIvSeatBelt;
        if (imageView != null) {
            imageView.setBackgroundResource(z ? R.drawable.pemp_id8_main_edit_icon_dashboard_seatbelt : R.drawable.pemp_id8_main_edit_icon_dashboard_seatbelt_f);
        }
        CheckBox checkBox = this.mChkBelt;
        if (checkBox != null) {
            checkBox.setChecked(z);
        }
    }

    public void setRotatingSpeed(int i) {
        if (this.myRotateClock != null) {
            float f = ((((float) i) * 1.0f) / ((float) this.maxRotateSpeed)) * ((float) this.maxRotateAngle);
            this.currentRotateAngle = f;
            if (f != this.lastRotateAngle) {
                RotateAnimation rotateAnimation = new RotateAnimation(this.lastRotateAngle, this.currentRotateAngle, 1, 0.5f, 1, 0.5f);
                rotateAnimation.setDuration(200);
                rotateAnimation.setFillAfter(true);
                this.myRotateClock.startAnimation(rotateAnimation);
            }
        }
        float f2 = (float) i;
        if (this.lastRotateAngle != f2) {
            ValueAnimator valueAnimator = this.rotatingSpeedAnimator;
            if (valueAnimator != null) {
                valueAnimator.cancel();
                this.rotatingSpeedAnimator.removeAllListeners();
                this.rotatingSpeedAnimator.removeAllUpdateListeners();
            }
            ValueAnimator ofFloat = ValueAnimator.ofFloat(new float[]{this.lastRotateAngle, (float) Math.min(Math.max(i, 0), 8000)});
            this.rotatingSpeedAnimator = ofFloat;
            ofFloat.setDuration(500);
            this.rotatingSpeedAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    float floatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                    if (MainFragment.this.gt6AudiDashboardPointer_iv != null) {
                        MainFragment mainFragment = MainFragment.this;
                        float unused = mainFragment.currentRotateAngle = (floatValue / ((float) mainFragment.maxRotateSpeed)) * ((float) MainFragment.this.maxRotateAngle);
                        MainFragment.this.gt6AudiDashboardPointer_iv.setRotation(MainFragment.this.currentRotateAngle - 120.0f);
                    }
                    if (MainFragment.this.gt6AudiDashboardRotateSpeedValue_tv != null) {
                        MainFragment.this.gt6AudiDashboardRotateSpeedValue_tv.setText("" + ((int) floatValue));
                    }
                }
            });
            this.rotatingSpeedAnimator.start();
            this.lastRotateAngle = f2;
        }
    }

    public void refreshGaodeNaviInfo(Bundle bundle) {
        TextView textView;
        ImageView imageView;
        String str;
        int i = bundle.getInt(com.szchoiceway.zxwlib.utils.EventUtils.EXTRA_STATE, -1);
        if (i == 8 || i == 10) {
            this.isGaodeNavigating = true;
        } else if (i == 9 || i == 12) {
            this.isGaodeNavigating = false;
        }
        if (this.rightWidget == 1) {
            if (this.isGaodeNavigating) {
                Group group = this.fangxiang_group;
                if (group != null) {
                    group.setVisibility(0);
                }
                View view = this.weatherPic_group;
                if (view != null) {
                    view.setVisibility(8);
                }
            } else {
                Group group2 = this.fangxiang_group;
                if (group2 != null) {
                    group2.setVisibility(8);
                }
                View view5 = this.weatherPic_group;
                if (view5 != null) {
                    view5.setVisibility(0);
                }
            }
        }
        int i2 = bundle.getInt("ROUTE_REMAIN_DIS", -1);
        if (i2 != -1) {
            if (i2 < 1000) {
                TextView textView2 = this.gt6AudiFangxiang_a_tv;
                if (textView2 != null) {
                    textView2.setText(i2 + "m");
                }
            } else {
                String format = new DecimalFormat("0.00").format(((double) i2) / 1000.0d);
                TextView textView3 = this.gt6AudiFangxiang_a_tv;
                if (textView3 != null) {
                    textView3.setText(format + "km");
                }
            }
        }
        int i3 = bundle.getInt("ROUTE_REMAIN_TIME", -1);
        int i4 = i3 % 60;
        int i5 = (i3 % 3600) / 60;
        int i6 = i3 / 3600;
        if (i3 != -1) {
            DecimalFormat decimalFormat = new DecimalFormat("00");
            if (i6 != 0) {
                str = decimalFormat.format(((double) i6) * 1.0d) + ":" + decimalFormat.format(((double) i5) * 1.0d);
            } else {
                str = decimalFormat.format(((double) i5) * 1.0d) + ":" + decimalFormat.format(((double) i4) * 1.0d);
            }
            TextView textView4 = this.gt6Audifangxiang_time_tv;
            if (textView4 != null) {
                textView4.setText(str);
            }
        }
        int i7 = bundle.getInt("NEW_ICON", -1);
        if (i7 != -1 && (imageView = this.gt6Audifangxiang) != null && i7 >= 1 && i7 <= 20) {
            imageView.setImageLevel(i7);
        }
        String string = bundle.getString("NEXT_ROAD_NAME", (String) null);
        if (string != null && (textView = this.fangxiang_road_tv) != null) {
            textView.setText(string);
        }
    }

    public void setSpeed(int i) {
        if (isDefaultUI()) {
            MyQAnalogClock2 myQAnalogClock2 = this.mClockSpeed;
            if (myQAnalogClock2 != null) {
                TextView textView = this.mTvShunShiSuDu;
                if (textView != null) {
                    myQAnalogClock2.setmTvCur(textView);
                }
                this.mClockSpeed.setiSpeedValue(i);
            }
        } else if (this.m_iModeSet == 19 || this.m_iUIIndex == 5) {
            if (this.mUnit) {
                i = (int) (((float) i) * 0.62f);
            }
            TextView textView2 = this.mTvShunShiSuDu;
            if (textView2 != null) {
                textView2.setText(i + "");
            }
            if (this.myClock != null) {
                float f = ((((float) i) * 1.0f) / ((float) this.maxSpeed)) * ((float) this.maxAngle);
                this.currentAngle = f;
                if (f != this.lastAngle) {
                    RotateAnimation rotateAnimation = new RotateAnimation(this.lastAngle, this.currentAngle, 1, 0.5f, 1, 0.5f);
                    rotateAnimation.setDuration(200);
                    rotateAnimation.setFillAfter(true);
                    this.myClock.startAnimation(rotateAnimation);
                    this.lastAngle = this.currentAngle;
                }
            }
        }
    }

    public void setSpeedUnit() {
        boolean recordBoolean = this.mProvider.getRecordBoolean(SysProviderOpt.KSW_DISTACNE_UNIT, this.mUnit);
        this.mUnit = recordBoolean;
        TextView textView = this.mTvSpeedUnit;
        if (textView != null) {
            textView.setText(recordBoolean ? "mph" : "km/h");
        }
        if (isDefaultUI()) {
            MyQAnalogClock2 myQAnalogClock2 = this.mClockSpeed;
            if (myQAnalogClock2 != null) {
                if (this.mUnit) {
                    myQAnalogClock2.setDialDrawableId(R.drawable.shisubiaopan2);
                    this.mClockSpeed.setiDeviationScale(120);
                    this.mClockSpeed.setiTotalScale(GyroScopeWithCompassView.CARTYPE_SDF_HI);
                } else {
                    myQAnalogClock2.setDialDrawableId(R.drawable.shisubiaopan);
                    this.mClockSpeed.setiDeviationScale(GyroScopeWithCompassView.CARTYPE_GS4_MI);
                    this.mClockSpeed.setiTotalScale(GyroScopeWithCompassView.CarTYPE_MALIBU_XL_HI);
                }
                this.mClockSpeed.setiMaxValue(GyroScopeWithCompassView.CarTYPE_MALIBU_XL_HI);
                this.mClockSpeed.postInvalidate();
            }
        } else if (this.m_iModeSet == 19) {
            ImageButton imageButton = this.btnDashboard;
            if (imageButton != null) {
                imageButton.setBackgroundResource(this.mUnit ? R.drawable.pemp_id7_btn_dashboard_imperial : R.drawable.pemp_id7_btn_dashboard);
            }
            this.maxSpeed = this.mUnit ? GyroScopeWithCompassView.CARTYPE_Nazhijie_U3_LO : 280;
        }
    }

    public void setTempValue(String str) {
        if (str != null) {
            if (this.mTvTemp != null) {
                if (str.contains(",")) {
                    str = str.replace(",", ".");
                }
                this.mTvTemp.setText(str);
            }
            if (this.gt6_audi_wendu_tv != null) {
                if (str.contains(",")) {
                    str = str.replace(",", ".");
                }
                this.gt6_audi_wendu_tv.setText(str);
            }
            if (this.gt6AudiDashboardTemp_tv != null) {
                if (str.contains(",")) {
                    str = str.replace(",", ".");
                }
                this.gt6AudiDashboardTemp_tv.setText(str);
            }
        }
    }

    public void setDrivingRangeValue(String str) {
        TextView textView;
        if (str != null && (textView = this.gt6_audi_qi_tv) != null) {
            textView.setText(str);
        }
    }

    public void setQilValue(String str) {
        String str2 = str;
        if (str2 != null) {
            double parseDouble = Double.parseDouble(str);
            if (this.mTvQil != null) {
                int recordInteger = this.mProvider.getRecordInteger(SysProviderOpt.KSW_OIL_UNIT, 0);
                if (recordInteger == 0) {
                    this.mTvQil.setText(str2 + "L");
                } else if (recordInteger == 1) {
                    this.mTvQil.setText(((int) (parseDouble / 3.78d)) + "gal");
                } else {
                    this.mTvQil.setText(((int) (parseDouble / 4.55d)) + "gal");
                }
            }
            if (this.gt6AudiDashboardSpeedValue_tv != null) {
                int recordInteger2 = this.mProvider.getRecordInteger(SysProviderOpt.KSW_OIL_UNIT, 0);
                if (recordInteger2 == 0) {
                    this.gt6AudiDashboardSpeedValue_tv.setText(str2 + "L");
                } else if (recordInteger2 == 1) {
                    this.gt6AudiDashboardSpeedValue_tv.setText(((int) (parseDouble / 3.78d)) + "gal");
                } else {
                    this.gt6AudiDashboardSpeedValue_tv.setText(((int) (parseDouble / 4.55d)) + "gal");
                }
            }
        }
    }

    public void setOilConsumption(String str) {
        if (this.mRecyclerView != null) {
            ((ItemDashboard) ItemUtil.mapTagItem.get("Dashboard")).setOilConsumption(str);
        }
    }

    public void setMusicCoverBg(Bitmap bitmap) {
        if (this.mRecyclerView != null) {
            ItemBase itemBase = ItemUtil.mapTagItem.get("Music");
            if (itemBase != null) {
                ((ItemMusic) itemBase).setMusicCoverBg(bitmap);
                return;
            }
            return;
        }
        this.mMusicCoverBm = bitmap;
        if (this.m_iModeSet == 21 || this.m_iModeSet == 31) {
            try {
                if (this.mEvtService.getValidMode() != EventUtils.eSrcMode.SRC_MUSIC.getIntValue()) {
                    return;
                }
                if (this.mMusicCoverBm != null) {
                    this.mBlurMusicCoverDrawable = new BitmapDrawable(this.mMusicCoverBm);
                } else {
                    this.mBlurMusicCoverDrawable = null;
                }
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public void refreshBTMessage() {
        int i = LauncherModel.getInstance().btConnectStatus;
        Log.d(TAG, "refreshBTMessage state = " + i);
        if (this.mRecyclerView != null) {
            ((ItemBt) ItemUtil.mapTagItem.get(ItemTag.BT_TAG)).refreshBTMessage();
        }
        if (this.txtBTMessage != null) {
            if (i == 1) {
                if (this.m_iModeSet == 26) {
                    this.txtBTMessage.setText(getResources().getString(R.string.lb_gs_bt_message_connected));
                    ILauncherStrategy iLauncherStrategy = this.launcherStrategy;
                    if (iLauncherStrategy != null) {
                        iLauncherStrategy.setBtInfo(getResources().getString(R.string.lb_gs_bt_message_connected));
                    }
                } else {
                    this.txtBTMessage.setText(getResources().getString(R.string.lb_bt_message_connected));
                    ILauncherStrategy iLauncherStrategy2 = this.launcherStrategy;
                    if (iLauncherStrategy2 != null) {
                        iLauncherStrategy2.setBtInfo(getResources().getString(R.string.lb_bt_message_connected));
                    }
                }
            } else if (i == 0) {
                if (this.m_iModeSet == 26) {
                    this.mConnectBtName = getResources().getString(R.string.lb_gs_bt_message_not_connect);
                    this.txtBTMessage.setText(getResources().getString(R.string.lb_gs_bt_message_not_connect));
                    ILauncherStrategy iLauncherStrategy3 = this.launcherStrategy;
                    if (iLauncherStrategy3 != null) {
                        iLauncherStrategy3.setBtInfo(getResources().getString(R.string.lb_gs_bt_message_not_connect));
                    }
                } else {
                    this.mConnectBtName = getResources().getString(R.string.lb_bt_message_not_connected);
                    this.txtBTMessage.setText(getResources().getString(R.string.lb_bt_message_not_connected));
                    ILauncherStrategy iLauncherStrategy4 = this.launcherStrategy;
                    if (iLauncherStrategy4 != null) {
                        iLauncherStrategy4.setBtInfo(getResources().getString(R.string.lb_bt_message_not_connected));
                    }
                }
            } else if (this.m_iModeSet == 26) {
                this.mConnectBtName = getResources().getString(R.string.lb_gs_bt_message_closed);
                this.txtBTMessage.setText(getResources().getString(R.string.lb_gs_bt_message_closed));
                ILauncherStrategy iLauncherStrategy5 = this.launcherStrategy;
                if (iLauncherStrategy5 != null) {
                    iLauncherStrategy5.setBtInfo(getResources().getString(R.string.lb_gs_bt_message_closed));
                }
            } else {
                this.mConnectBtName = getResources().getString(R.string.lb_bt_message_close);
                this.txtBTMessage.setText(getResources().getString(R.string.lb_bt_message_close));
                ILauncherStrategy iLauncherStrategy6 = this.launcherStrategy;
                if (iLauncherStrategy6 != null) {
                    iLauncherStrategy6.setBtInfo(getResources().getString(R.string.lb_bt_message_close));
                }
            }
        }
        MainTopView mainTopView = this.mMainTopView;
        if (mainTopView != null) {
            mainTopView.refreshBtView(i);
        }
    }

    public void refreshBTConnectName(String str) {
        if (LauncherModel.getInstance().btConnectStatus == 1) {
            this.mConnectBtName = str;
            if (this.mRecyclerView != null) {
                ((ItemBt) ItemUtil.mapTagItem.get(ItemTag.BT_TAG)).refreshBTConnectName(str);
            }
            if (this.txtBTMessage != null && this.m_iModeSet != 26) {
                this.txtBTMessage.setText(str);
                ILauncherStrategy iLauncherStrategy = this.launcherStrategy;
                if (iLauncherStrategy != null) {
                    iLauncherStrategy.setBtInfo(str);
                }
            }
        }
    }

    public void setViewPagerItem(int i) {
        MyViewPager myViewPager = this.mViewPager;
        if (myViewPager != null) {
            myViewPager.setCurrentItem(i);
        }
    }

    public class MyOnPageChangeListener implements ViewPager.OnPageChangeListener {
        public void onPageScrollStateChanged(int i) {
        }

        public void onPageScrolled(int i, float f, int i2) {
        }

        public MyOnPageChangeListener() {
        }

        public void onPageSelected(int i) {
            int i2 = i;
            int unused = MainFragment.this.currentPageIndex = i2;
            MainFragment.this.refreshMainBackground();
            if (i2 != 0) {
                if (i2 != 1) {
                    if (i2 != 2) {
                        if (i2 == 3) {
                            if (MainFragment.this.m_iModeSet == 19) {
                                MainFragment.this.mPage0.setImageDrawable(ContextCompat.getDrawable(MainFragment.this.mContext, R.drawable.pemp_id7_main_indicato_normal));
                                MainFragment.this.mPage1.setImageDrawable(ContextCompat.getDrawable(MainFragment.this.mContext, R.drawable.pemp_id7_main_indicato_normal));
                                MainFragment.this.mPage2.setImageDrawable(ContextCompat.getDrawable(MainFragment.this.mContext, R.drawable.pemp_id7_main_indicato_normal));
                                MainFragment.this.mPage3.setImageDrawable(ContextCompat.getDrawable(MainFragment.this.mContext, R.drawable.pemp_id7_main_indicato_selected));
                            } else if (MainFragment.this.m_iUIIndex == 5) {
                                MainFragment.this.mPage0.setImageResource(MainFragment.this.getAttrId(R.attr.landrover_main_indicato_n));
                                MainFragment.this.mPage1.setImageResource(MainFragment.this.getAttrId(R.attr.landrover_main_indicato_n));
                                MainFragment.this.mPage2.setImageResource(MainFragment.this.getAttrId(R.attr.landrover_main_indicato_n));
                                if (MainFragment.this.mPage3 != null) {
                                    MainFragment.this.mPage3.setImageResource(MainFragment.this.getAttrId(R.attr.landrover_main_indicato_sel));
                                }
                            }
                        }
                    } else if (MainFragment.this.m_iUITypeVer == 41) {
                        if (MainFragment.this.isDefaultUI()) {
                            MainFragment.this.mPage0.setImageDrawable(ContextCompat.getDrawable(MainFragment.this.mContext, R.drawable.ksw_id7_indicato_normal));
                            MainFragment.this.mPage1.setImageDrawable(ContextCompat.getDrawable(MainFragment.this.mContext, R.drawable.ksw_id7_indicato_normal));
                            MainFragment.this.mPage2.setImageDrawable(ContextCompat.getDrawable(MainFragment.this.mContext, R.drawable.ksw_id7_indicato_home_selected));
                        } else if (MainFragment.this.m_iModeSet == 19) {
                            MainFragment.this.mPage0.setImageDrawable(ContextCompat.getDrawable(MainFragment.this.mContext, R.drawable.pemp_id7_main_indicato_normal));
                            MainFragment.this.mPage1.setImageDrawable(ContextCompat.getDrawable(MainFragment.this.mContext, R.drawable.pemp_id7_main_indicato_normal));
                            MainFragment.this.mPage2.setImageDrawable(ContextCompat.getDrawable(MainFragment.this.mContext, R.drawable.pemp_id7_main_indicato_selected));
                            MainFragment.this.mPage3.setImageDrawable(ContextCompat.getDrawable(MainFragment.this.mContext, R.drawable.pemp_id7_main_indicato_normal));
                        } else if (MainFragment.this.m_iModeSet == 21 || MainFragment.this.m_iModeSet == 31) {
                            MainFragment.this.mPage0.setImageDrawable(ContextCompat.getDrawable(MainFragment.this.mContext, R.drawable.ksw_1920x720_benz_fanye_n));
                            MainFragment.this.mPage1.setImageDrawable(ContextCompat.getDrawable(MainFragment.this.mContext, R.drawable.ksw_1920x720_benz_fanye_n));
                            MainFragment.this.mPage2.setImageDrawable(ContextCompat.getDrawable(MainFragment.this.mContext, R.drawable.ksw_1920x720_benz_fanye_d));
                        } else if (MainFragment.this.m_iUIIndex == 5) {
                            MainFragment.this.mPage0.setImageResource(MainFragment.this.getAttrId(R.attr.landrover_main_indicato_n));
                            MainFragment.this.mPage1.setImageResource(MainFragment.this.getAttrId(R.attr.landrover_main_indicato_n));
                            MainFragment.this.mPage2.setImageResource(MainFragment.this.getAttrId(R.attr.landrover_main_indicato_sel));
                            if (MainFragment.this.mPage3 != null) {
                                MainFragment.this.mPage3.setImageResource(MainFragment.this.getAttrId(R.attr.landrover_main_indicato_n));
                            }
                        }
                    }
                } else if (MainFragment.this.m_iUITypeVer == 41) {
                    if (MainFragment.this.isDefaultUI()) {
                        MainFragment.this.mPage0.setImageDrawable(ContextCompat.getDrawable(MainFragment.this.mContext, R.drawable.ksw_id7_indicato_normal));
                        MainFragment.this.mPage1.setImageDrawable(ContextCompat.getDrawable(MainFragment.this.mContext, R.drawable.ksw_id7_indicato_home_selected));
                        MainFragment.this.mPage2.setImageDrawable(ContextCompat.getDrawable(MainFragment.this.mContext, R.drawable.ksw_id7_indicato_normal));
                    } else if (MainFragment.this.m_iModeSet == 19) {
                        MainFragment.this.mPage0.setImageDrawable(ContextCompat.getDrawable(MainFragment.this.mContext, R.drawable.pemp_id7_main_indicato_normal));
                        MainFragment.this.mPage1.setImageDrawable(ContextCompat.getDrawable(MainFragment.this.mContext, R.drawable.pemp_id7_main_indicato_selected));
                        MainFragment.this.mPage2.setImageDrawable(ContextCompat.getDrawable(MainFragment.this.mContext, R.drawable.pemp_id7_main_indicato_normal));
                        MainFragment.this.mPage3.setImageDrawable(ContextCompat.getDrawable(MainFragment.this.mContext, R.drawable.pemp_id7_main_indicato_normal));
                    } else if (MainFragment.this.m_iModeSet == 21 || MainFragment.this.m_iModeSet == 31) {
                        MainFragment.this.mPage0.setImageDrawable(ContextCompat.getDrawable(MainFragment.this.mContext, R.drawable.ksw_1920x720_benz_fanye_n));
                        MainFragment.this.mPage1.setImageDrawable(ContextCompat.getDrawable(MainFragment.this.mContext, R.drawable.ksw_1920x720_benz_fanye_d));
                        MainFragment.this.mPage2.setImageDrawable(ContextCompat.getDrawable(MainFragment.this.mContext, R.drawable.ksw_1920x720_benz_fanye_n));
                    } else if (MainFragment.this.m_iUIIndex != 4 || MainFragment.this.m_iModeSet == 29) {
                        if (MainFragment.this.m_iUIIndex == 5) {
                            MainFragment.this.mPage0.setImageResource(MainFragment.this.getAttrId(R.attr.landrover_main_indicato_n));
                            MainFragment.this.mPage1.setImageResource(MainFragment.this.getAttrId(R.attr.landrover_main_indicato_sel));
                            MainFragment.this.mPage2.setImageResource(MainFragment.this.getAttrId(R.attr.landrover_main_indicato_n));
                            if (MainFragment.this.mPage3 != null) {
                                MainFragment.this.mPage3.setImageResource(MainFragment.this.getAttrId(R.attr.landrover_main_indicato_n));
                            }
                        } else if (MainFragment.this.m_iModeSet == 26) {
                            if (MainFragment.this.btnDirectionLeft != null) {
                                MainFragment.this.btnDirectionLeft.setVisibility(0);
                            }
                            if (MainFragment.this.btnDirectionRight != null) {
                                MainFragment.this.btnDirectionRight.setVisibility(8);
                            }
                        }
                    } else if (MainFragment.this.m_iModeSet == 40) {
                        MainFragment.this.mPage0.setImageDrawable(ContextCompat.getDrawable(MainFragment.this.mContext, R.drawable.audi_bl_fanye_n));
                        MainFragment.this.mPage1.setImageDrawable(ContextCompat.getDrawable(MainFragment.this.mContext, R.drawable.audi_bl_fanye_d));
                    } else {
                        MainFragment.this.mPage0.setImageDrawable(ContextCompat.getDrawable(MainFragment.this.mContext, R.drawable.audi_mib3_main_indicato_n));
                        MainFragment.this.mPage1.setImageDrawable(ContextCompat.getDrawable(MainFragment.this.mContext, R.drawable.audi_mib3_main_indicato_sel));
                    }
                }
            } else if (MainFragment.this.m_iUITypeVer == 41) {
                if (MainFragment.this.isDefaultUI()) {
                    MainFragment.this.mPage0.setImageDrawable(ContextCompat.getDrawable(MainFragment.this.mContext, R.drawable.ksw_id7_indicato_home_selected));
                    MainFragment.this.mPage1.setImageDrawable(ContextCompat.getDrawable(MainFragment.this.mContext, R.drawable.ksw_id7_indicato_normal));
                    MainFragment.this.mPage2.setImageDrawable(ContextCompat.getDrawable(MainFragment.this.mContext, R.drawable.ksw_id7_indicato_normal));
                } else if (MainFragment.this.m_iModeSet == 19) {
                    MainFragment.this.mPage0.setImageDrawable(ContextCompat.getDrawable(MainFragment.this.mContext, R.drawable.pemp_id7_main_indicato_selected));
                    MainFragment.this.mPage1.setImageDrawable(ContextCompat.getDrawable(MainFragment.this.mContext, R.drawable.pemp_id7_main_indicato_normal));
                    MainFragment.this.mPage2.setImageDrawable(ContextCompat.getDrawable(MainFragment.this.mContext, R.drawable.pemp_id7_main_indicato_normal));
                    MainFragment.this.mPage3.setImageDrawable(ContextCompat.getDrawable(MainFragment.this.mContext, R.drawable.pemp_id7_main_indicato_normal));
                } else if (MainFragment.this.m_iModeSet == 21 || MainFragment.this.m_iModeSet == 31) {
                    MainFragment.this.mPage0.setImageDrawable(ContextCompat.getDrawable(MainFragment.this.mContext, R.drawable.ksw_1920x720_benz_fanye_d));
                    MainFragment.this.mPage1.setImageDrawable(ContextCompat.getDrawable(MainFragment.this.mContext, R.drawable.ksw_1920x720_benz_fanye_n));
                    MainFragment.this.mPage2.setImageDrawable(ContextCompat.getDrawable(MainFragment.this.mContext, R.drawable.ksw_1920x720_benz_fanye_n));
                } else if (MainFragment.this.m_iUIIndex != 4 || MainFragment.this.m_iModeSet == 29) {
                    if (MainFragment.this.m_iUIIndex == 5) {
                        MainFragment.this.mPage0.setImageResource(MainFragment.this.getAttrId(R.attr.landrover_main_indicato_sel));
                        MainFragment.this.mPage1.setImageResource(MainFragment.this.getAttrId(R.attr.landrover_main_indicato_n));
                        MainFragment.this.mPage2.setImageResource(MainFragment.this.getAttrId(R.attr.landrover_main_indicato_n));
                        if (MainFragment.this.mPage3 != null) {
                            MainFragment.this.mPage3.setImageResource(MainFragment.this.getAttrId(R.attr.landrover_main_indicato_n));
                        }
                    } else if (MainFragment.this.m_iModeSet == 26) {
                        if (MainFragment.this.btnDirectionLeft != null) {
                            MainFragment.this.btnDirectionLeft.setVisibility(8);
                        }
                        if (MainFragment.this.btnDirectionRight != null) {
                            MainFragment.this.btnDirectionRight.setVisibility(0);
                        }
                    }
                } else if (MainFragment.this.m_iModeSet == 40) {
                    MainFragment.this.mPage0.setImageDrawable(ContextCompat.getDrawable(MainFragment.this.mContext, R.drawable.audi_bl_fanye_d));
                    MainFragment.this.mPage1.setImageDrawable(ContextCompat.getDrawable(MainFragment.this.mContext, R.drawable.audi_bl_fanye_n));
                } else {
                    MainFragment.this.mPage0.setImageDrawable(ContextCompat.getDrawable(MainFragment.this.mContext, R.drawable.audi_mib3_main_indicato_sel));
                    MainFragment.this.mPage1.setImageDrawable(ContextCompat.getDrawable(MainFragment.this.mContext, R.drawable.audi_mib3_main_indicato_n));
                }
            }
            if (MainFragment.this.m_iUITypeVer == 41) {
                if (MainFragment.this.iMainFocusIndex != -1) {
                    if (MainFragment.this.iLastMainPageIndex > i2) {
                        MainFragment mainFragment = MainFragment.this;
                        mainFragment.iMainFocusIndex = (mainFragment.iPagerItemViewItemCount * MainFragment.this.iLastMainPageIndex) - 1;
                    } else {
                        MainFragment mainFragment2 = MainFragment.this;
                        mainFragment2.iMainFocusIndex = mainFragment2.iPagerItemViewItemCount * i2;
                    }
                    MainFragment.this.refreshMainFocus();
                }
                int unused2 = MainFragment.this.iLastMainPageIndex = i2;
                MainFragment mainFragment3 = MainFragment.this;
                int unused3 = mainFragment3.iLastMainFocusIndex = mainFragment3.iLastMainPageIndex * MainFragment.this.iPagerItemViewItemCount;
            }
            if (MainFragment.this.launcherStrategy != null && MainFragment.this.m_iModeSet == 33) {
                MainFragment.this.launcherStrategy.setPageSelected(i2);
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:106:0x017c A[Catch:{ Exception -> 0x024d }] */
    /* JADX WARNING: Removed duplicated region for block: B:109:0x0183 A[Catch:{ Exception -> 0x024d }] */
    /* JADX WARNING: Removed duplicated region for block: B:112:0x018a A[Catch:{ Exception -> 0x024d }] */
    /* JADX WARNING: Removed duplicated region for block: B:115:0x0195 A[Catch:{ Exception -> 0x024d }] */
    /* JADX WARNING: Removed duplicated region for block: B:118:0x01a0 A[Catch:{ Exception -> 0x024d }] */
    /* JADX WARNING: Removed duplicated region for block: B:121:0x01b1 A[Catch:{ Exception -> 0x024d }] */
    /* JADX WARNING: Removed duplicated region for block: B:124:0x01b8 A[Catch:{ Exception -> 0x024d }] */
    /* JADX WARNING: Removed duplicated region for block: B:165:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:85:0x012d A[Catch:{ Exception -> 0x024d }] */
    /* JADX WARNING: Removed duplicated region for block: B:88:0x0141 A[Catch:{ Exception -> 0x024d }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void refreshPlayState() {
        /*
            r14 = this;
            androidx.recyclerview.widget.RecyclerView r0 = r14.mRecyclerView
            if (r0 == 0) goto L_0x0023
            java.util.HashMap<java.lang.String, com.szchoiceway.customerui.mianitem.ItemBase> r14 = com.szchoiceway.customerui.mianitem.ItemUtil.mapTagItem
            java.lang.String r0 = "Music"
            java.lang.Object r14 = r14.get(r0)
            com.szchoiceway.customerui.mianitem.ItemBase r14 = (com.szchoiceway.customerui.mianitem.ItemBase) r14
            com.szchoiceway.customerui.mianitem.ItemMusic r14 = (com.szchoiceway.customerui.mianitem.ItemMusic) r14
            r14.refreshPlayState()
            java.util.HashMap<java.lang.String, com.szchoiceway.customerui.mianitem.ItemBase> r14 = com.szchoiceway.customerui.mianitem.ItemUtil.mapTagItem
            java.lang.String r0 = "Video"
            java.lang.Object r14 = r14.get(r0)
            com.szchoiceway.customerui.mianitem.ItemBase r14 = (com.szchoiceway.customerui.mianitem.ItemBase) r14
            com.szchoiceway.customerui.mianitem.ItemVideo r14 = (com.szchoiceway.customerui.mianitem.ItemVideo) r14
            r14.refreshPlayState()
            return
        L_0x0023:
            com.szchoiceway.eventcenter.IEventService r0 = r14.mEvtService
            if (r0 == 0) goto L_0x026a
            int r0 = r0.getValidMode()     // Catch:{ Exception -> 0x024d }
            com.szchoiceway.customerui.utils.EventUtils$eSrcMode r1 = com.szchoiceway.customerui.utils.EventUtils.eSrcMode.SRC_MUSIC     // Catch:{ Exception -> 0x024d }
            int r1 = r1.getIntValue()     // Catch:{ Exception -> 0x024d }
            r2 = 1880031483(0x700f00fb, float:1.7703017E29)
            r3 = 1879310969(0x70040279, float:1.6342004E29)
            r4 = 1879575320(0x70080b18, float:1.684135E29)
            r5 = 5
            r6 = 19
            r7 = 1
            r8 = 8
            r9 = 0
            if (r0 != r1) goto L_0x01bd
            com.szchoiceway.eventcenter.IEventService r0 = r14.mEvtService     // Catch:{ Exception -> 0x024d }
            java.lang.String r0 = r0.getValidModeTitleInfor()     // Catch:{ Exception -> 0x024d }
            com.szchoiceway.eventcenter.IEventService r1 = r14.mEvtService     // Catch:{ Exception -> 0x024d }
            java.lang.String r1 = r1.getValidModeArtistInfor()     // Catch:{ Exception -> 0x024d }
            com.szchoiceway.eventcenter.IEventService r10 = r14.mEvtService     // Catch:{ Exception -> 0x024d }
            java.lang.String r10 = r10.getValidModeAblumInfor()     // Catch:{ Exception -> 0x024d }
            com.szchoiceway.eventcenter.IEventService r11 = r14.mEvtService     // Catch:{ Exception -> 0x024d }
            int r11 = r11.getValidCurTime()     // Catch:{ Exception -> 0x024d }
            com.szchoiceway.eventcenter.IEventService r12 = r14.mEvtService     // Catch:{ Exception -> 0x024d }
            int r12 = r12.getValidTotTime()     // Catch:{ Exception -> 0x024d }
            android.widget.TextView r13 = r14.mTvMusicTitleInfor     // Catch:{ Exception -> 0x024d }
            if (r13 == 0) goto L_0x0072
            r13.setFocusable(r7)     // Catch:{ Exception -> 0x024d }
            android.widget.TextView r13 = r14.mTvMusicTitleInfor     // Catch:{ Exception -> 0x024d }
            r13.setSelected(r7)     // Catch:{ Exception -> 0x024d }
            android.widget.TextView r13 = r14.mTvMusicTitleInfor     // Catch:{ Exception -> 0x024d }
            r13.setText(r0)     // Catch:{ Exception -> 0x024d }
        L_0x0072:
            com.szchoiceway.customerui.fragment.launcher.strategy.ILauncherStrategy r13 = r14.launcherStrategy     // Catch:{ Exception -> 0x024d }
            if (r13 == 0) goto L_0x0079
            r13.setMusicInfo(r0)     // Catch:{ Exception -> 0x024d }
        L_0x0079:
            android.widget.TextView r0 = r14.mTvMusicArtistInfor     // Catch:{ Exception -> 0x024d }
            if (r0 == 0) goto L_0x0080
            r0.setText(r1)     // Catch:{ Exception -> 0x024d }
        L_0x0080:
            android.widget.TextView r0 = r14.mTvMusicAblumInfor     // Catch:{ Exception -> 0x024d }
            if (r0 == 0) goto L_0x0087
            r0.setText(r10)     // Catch:{ Exception -> 0x024d }
        L_0x0087:
            android.widget.SeekBar r0 = r14.mSkBarProgress     // Catch:{ Exception -> 0x024d }
            if (r0 == 0) goto L_0x008e
            r0.setMax(r12)     // Catch:{ Exception -> 0x024d }
        L_0x008e:
            com.szchoiceway.customerui.views.MySeekbar r0 = r14.mSkBarProgress2     // Catch:{ Exception -> 0x024d }
            if (r0 == 0) goto L_0x0095
            r0.setMax(r12)     // Catch:{ Exception -> 0x024d }
        L_0x0095:
            android.widget.ImageView r0 = r14.mIvMediaTypeBg     // Catch:{ Exception -> 0x024d }
            if (r0 == 0) goto L_0x00a3
            android.graphics.Bitmap r1 = r14.mMusicCoverBm     // Catch:{ Exception -> 0x024d }
            if (r1 != 0) goto L_0x009f
            r1 = r9
            goto L_0x00a0
        L_0x009f:
            r1 = r8
        L_0x00a0:
            r0.setVisibility(r1)     // Catch:{ Exception -> 0x024d }
        L_0x00a3:
            android.widget.ImageView r0 = r14.mMusicCoverBg     // Catch:{ Exception -> 0x024d }
            if (r0 == 0) goto L_0x00b2
            android.graphics.Bitmap r1 = r14.mMusicCoverBm     // Catch:{ Exception -> 0x024d }
            r10 = 10
            android.graphics.Bitmap r1 = com.szchoiceway.customerui.utils.BlurUtil.doBlur((android.graphics.Bitmap) r1, (int) r10, (boolean) r9)     // Catch:{ Exception -> 0x024d }
            r0.setImageBitmap(r1)     // Catch:{ Exception -> 0x024d }
        L_0x00b2:
            int r0 = r14.m_iModeSet     // Catch:{ Exception -> 0x024d }
            r1 = 31
            r10 = 21
            if (r0 == r10) goto L_0x00e1
            int r0 = r14.m_iModeSet     // Catch:{ Exception -> 0x024d }
            if (r0 != r1) goto L_0x00bf
            goto L_0x00e1
        L_0x00bf:
            int r0 = r14.m_iModeSet     // Catch:{ Exception -> 0x024d }
            if (r0 != r6) goto L_0x0129
            android.view.View r0 = r14.btnMusic     // Catch:{ Exception -> 0x024d }
            if (r0 == 0) goto L_0x00d9
            boolean r1 = r14.changeMusicBg     // Catch:{ Exception -> 0x024d }
            if (r1 != 0) goto L_0x00d9
            android.content.Context r1 = r14.mContext     // Catch:{ Exception -> 0x024d }
            r10 = 1879575316(0x70080b14, float:1.6841342E29)
            android.graphics.drawable.Drawable r1 = androidx.core.content.ContextCompat.getDrawable(r1, r10)     // Catch:{ Exception -> 0x024d }
            r0.setBackground(r1)     // Catch:{ Exception -> 0x024d }
            r14.changeMusicBg = r7     // Catch:{ Exception -> 0x024d }
        L_0x00d9:
            android.view.View r0 = r14.viewMusicContent     // Catch:{ Exception -> 0x024d }
            if (r0 == 0) goto L_0x0129
            r0.setVisibility(r9)     // Catch:{ Exception -> 0x024d }
            goto L_0x0129
        L_0x00e1:
            android.graphics.Bitmap r0 = r14.mMusicCoverBm     // Catch:{ Exception -> 0x024d }
            if (r0 == 0) goto L_0x0102
            android.view.View r0 = r14.btnMusic     // Catch:{ Exception -> 0x024d }
            if (r0 == 0) goto L_0x00ec
            r0.setVisibility(r8)     // Catch:{ Exception -> 0x024d }
        L_0x00ec:
            android.view.View r0 = r14.viewMusic     // Catch:{ Exception -> 0x024d }
            if (r0 == 0) goto L_0x00f3
            r0.setVisibility(r9)     // Catch:{ Exception -> 0x024d }
        L_0x00f3:
            android.widget.ImageView r0 = r14.mMusicCover     // Catch:{ Exception -> 0x024d }
            if (r0 == 0) goto L_0x00fa
            r0.setVisibility(r9)     // Catch:{ Exception -> 0x024d }
        L_0x00fa:
            android.widget.ImageView r0 = r14.gt6_audi_iv_MusicCoverBg     // Catch:{ Exception -> 0x024d }
            if (r0 == 0) goto L_0x011e
            r0.setVisibility(r9)     // Catch:{ Exception -> 0x024d }
            goto L_0x011e
        L_0x0102:
            android.view.View r0 = r14.btnMusic     // Catch:{ Exception -> 0x024d }
            if (r0 == 0) goto L_0x0109
            r0.setVisibility(r9)     // Catch:{ Exception -> 0x024d }
        L_0x0109:
            android.view.View r0 = r14.viewMusic     // Catch:{ Exception -> 0x024d }
            if (r0 == 0) goto L_0x0110
            r0.setVisibility(r8)     // Catch:{ Exception -> 0x024d }
        L_0x0110:
            android.widget.ImageView r0 = r14.mMusicCover     // Catch:{ Exception -> 0x024d }
            if (r0 == 0) goto L_0x0117
            r0.setVisibility(r8)     // Catch:{ Exception -> 0x024d }
        L_0x0117:
            android.widget.ImageView r0 = r14.gt6_audi_iv_MusicCoverBg     // Catch:{ Exception -> 0x024d }
            if (r0 == 0) goto L_0x011e
            r0.setVisibility(r8)     // Catch:{ Exception -> 0x024d }
        L_0x011e:
            int r0 = r14.m_iModeSet     // Catch:{ Exception -> 0x024d }
            if (r0 == r10) goto L_0x0126
            int r0 = r14.m_iModeSet     // Catch:{ Exception -> 0x024d }
            if (r0 != r1) goto L_0x0129
        L_0x0126:
            r14.refreshMainBackground()     // Catch:{ Exception -> 0x024d }
        L_0x0129:
            android.widget.ImageView r0 = r14.mMusicCover     // Catch:{ Exception -> 0x024d }
            if (r0 == 0) goto L_0x0137
            android.graphics.drawable.BitmapDrawable r1 = new android.graphics.drawable.BitmapDrawable     // Catch:{ Exception -> 0x024d }
            android.graphics.Bitmap r10 = r14.mMusicCoverBm     // Catch:{ Exception -> 0x024d }
            r1.<init>(r10)     // Catch:{ Exception -> 0x024d }
            r0.setBackground(r1)     // Catch:{ Exception -> 0x024d }
        L_0x0137:
            int r0 = r14.mValidPlayStatus     // Catch:{ Exception -> 0x024d }
            com.szchoiceway.eventcenter.IEventService r1 = r14.mEvtService     // Catch:{ Exception -> 0x024d }
            int r1 = r1.getValidPlayStatus()     // Catch:{ Exception -> 0x024d }
            if (r0 == r1) goto L_0x0178
            com.szchoiceway.eventcenter.IEventService r0 = r14.mEvtService     // Catch:{ Exception -> 0x024d }
            int r0 = r0.getValidPlayStatus()     // Catch:{ Exception -> 0x024d }
            r14.mValidPlayStatus = r0     // Catch:{ Exception -> 0x024d }
            int r0 = r14.m_iModeSet     // Catch:{ Exception -> 0x024d }
            if (r0 != r6) goto L_0x0162
            android.view.View r0 = r14.mBtnMusicPlayPause     // Catch:{ Exception -> 0x024d }
            if (r0 == 0) goto L_0x0178
            android.content.Context r1 = r14.mContext     // Catch:{ Exception -> 0x024d }
            int r3 = r14.mValidPlayStatus     // Catch:{ Exception -> 0x024d }
            if (r3 != r7) goto L_0x015a
            r4 = 1879575319(0x70080b17, float:1.6841347E29)
        L_0x015a:
            android.graphics.drawable.Drawable r1 = androidx.core.content.ContextCompat.getDrawable(r1, r4)     // Catch:{ Exception -> 0x024d }
            r0.setBackground(r1)     // Catch:{ Exception -> 0x024d }
            goto L_0x0178
        L_0x0162:
            int r0 = r14.m_iUIIndex     // Catch:{ Exception -> 0x024d }
            if (r0 != r5) goto L_0x0178
            android.view.View r0 = r14.mBtnMusicPlayPause     // Catch:{ Exception -> 0x024d }
            if (r0 == 0) goto L_0x0178
            int r1 = r14.mValidPlayStatus     // Catch:{ Exception -> 0x024d }
            if (r1 != r7) goto L_0x0171
            r3 = 1879310968(0x70040278, float:1.6342002E29)
        L_0x0171:
            int r1 = r14.getAttrId(r3)     // Catch:{ Exception -> 0x024d }
            r0.setBackgroundResource(r1)     // Catch:{ Exception -> 0x024d }
        L_0x0178:
            android.widget.SeekBar r0 = r14.mSkBarProgress     // Catch:{ Exception -> 0x024d }
            if (r0 == 0) goto L_0x017f
            r0.setProgress(r11)     // Catch:{ Exception -> 0x024d }
        L_0x017f:
            com.szchoiceway.customerui.views.MySeekbar r0 = r14.mSkBarProgress2     // Catch:{ Exception -> 0x024d }
            if (r0 == 0) goto L_0x0186
            r0.setCurrentProgress(r11)     // Catch:{ Exception -> 0x024d }
        L_0x0186:
            android.widget.TextView r0 = r14.mTvCurrTime     // Catch:{ Exception -> 0x024d }
            if (r0 == 0) goto L_0x0191
            java.lang.String r1 = com.szchoiceway.customerui.utils.EventUtils.getProgressFromPosition(r11)     // Catch:{ Exception -> 0x024d }
            r0.setText(r1)     // Catch:{ Exception -> 0x024d }
        L_0x0191:
            android.widget.TextView r0 = r14.mTvTotTime     // Catch:{ Exception -> 0x024d }
            if (r0 == 0) goto L_0x019c
            java.lang.String r1 = com.szchoiceway.customerui.utils.EventUtils.getProgressFromPosition(r12)     // Catch:{ Exception -> 0x024d }
            r0.setText(r1)     // Catch:{ Exception -> 0x024d }
        L_0x019c:
            android.widget.TextView r0 = r14.mTvVideoTitleInfor     // Catch:{ Exception -> 0x024d }
            if (r0 == 0) goto L_0x01ad
            android.content.Context r1 = r14.mContext     // Catch:{ Exception -> 0x024d }
            android.content.res.Resources r1 = r1.getResources()     // Catch:{ Exception -> 0x024d }
            java.lang.String r1 = r1.getString(r2)     // Catch:{ Exception -> 0x024d }
            r0.setText(r1)     // Catch:{ Exception -> 0x024d }
        L_0x01ad:
            android.widget.TextView r0 = r14.mTvVideoMessage     // Catch:{ Exception -> 0x024d }
            if (r0 == 0) goto L_0x01b4
            r0.setVisibility(r8)     // Catch:{ Exception -> 0x024d }
        L_0x01b4:
            android.widget.CheckBox r14 = r14.mChkVideoState     // Catch:{ Exception -> 0x024d }
            if (r14 == 0) goto L_0x026a
            r14.setChecked(r9)     // Catch:{ Exception -> 0x024d }
            goto L_0x026a
        L_0x01bd:
            com.szchoiceway.eventcenter.IEventService r0 = r14.mEvtService     // Catch:{ Exception -> 0x024d }
            int r0 = r0.getValidMode()     // Catch:{ Exception -> 0x024d }
            com.szchoiceway.customerui.utils.EventUtils$eSrcMode r1 = com.szchoiceway.customerui.utils.EventUtils.eSrcMode.SRC_MOVIE     // Catch:{ Exception -> 0x024d }
            int r1 = r1.getIntValue()     // Catch:{ Exception -> 0x024d }
            if (r0 != r1) goto L_0x020a
            r14.mValidPlayStatus = r9     // Catch:{ Exception -> 0x024d }
            com.szchoiceway.eventcenter.IEventService r0 = r14.mEvtService     // Catch:{ Exception -> 0x024d }
            java.lang.String r0 = r0.getValidModeTitleInfor()     // Catch:{ Exception -> 0x024d }
            android.widget.TextView r1 = r14.mTvVideoTitleInfor     // Catch:{ Exception -> 0x024d }
            if (r1 == 0) goto L_0x01da
            r1.setText(r0)     // Catch:{ Exception -> 0x024d }
        L_0x01da:
            android.widget.TextView r0 = r14.mTvVideoMessage     // Catch:{ Exception -> 0x024d }
            if (r0 == 0) goto L_0x01e1
            r0.setVisibility(r9)     // Catch:{ Exception -> 0x024d }
        L_0x01e1:
            android.widget.CheckBox r0 = r14.mChkVideoState     // Catch:{ Exception -> 0x024d }
            if (r0 == 0) goto L_0x01e8
            r0.setChecked(r7)     // Catch:{ Exception -> 0x024d }
        L_0x01e8:
            android.view.View r0 = r14.mBtnMusicPlayPause     // Catch:{ Exception -> 0x024d }
            if (r0 == 0) goto L_0x026a
            int r0 = r14.m_iModeSet     // Catch:{ Exception -> 0x024d }
            if (r0 != r6) goto L_0x01fc
            android.view.View r0 = r14.mBtnMusicPlayPause     // Catch:{ Exception -> 0x024d }
            android.content.Context r14 = r14.mContext     // Catch:{ Exception -> 0x024d }
            android.graphics.drawable.Drawable r14 = androidx.core.content.ContextCompat.getDrawable(r14, r4)     // Catch:{ Exception -> 0x024d }
            r0.setBackground(r14)     // Catch:{ Exception -> 0x024d }
            goto L_0x026a
        L_0x01fc:
            int r0 = r14.m_iUIIndex     // Catch:{ Exception -> 0x024d }
            if (r0 != r5) goto L_0x026a
            android.view.View r0 = r14.mBtnMusicPlayPause     // Catch:{ Exception -> 0x024d }
            int r14 = r14.getAttrId(r3)     // Catch:{ Exception -> 0x024d }
            r0.setBackgroundResource(r14)     // Catch:{ Exception -> 0x024d }
            goto L_0x026a
        L_0x020a:
            r14.mValidPlayStatus = r9     // Catch:{ Exception -> 0x024d }
            android.view.View r0 = r14.mBtnMusicPlayPause     // Catch:{ Exception -> 0x024d }
            if (r0 == 0) goto L_0x022d
            int r0 = r14.m_iModeSet     // Catch:{ Exception -> 0x024d }
            if (r0 != r6) goto L_0x0220
            android.view.View r0 = r14.mBtnMusicPlayPause     // Catch:{ Exception -> 0x024d }
            android.content.Context r1 = r14.mContext     // Catch:{ Exception -> 0x024d }
            android.graphics.drawable.Drawable r1 = androidx.core.content.ContextCompat.getDrawable(r1, r4)     // Catch:{ Exception -> 0x024d }
            r0.setBackground(r1)     // Catch:{ Exception -> 0x024d }
            goto L_0x022d
        L_0x0220:
            int r0 = r14.m_iUIIndex     // Catch:{ Exception -> 0x024d }
            if (r0 != r5) goto L_0x022d
            android.view.View r0 = r14.mBtnMusicPlayPause     // Catch:{ Exception -> 0x024d }
            int r1 = r14.getAttrId(r3)     // Catch:{ Exception -> 0x024d }
            r0.setBackgroundResource(r1)     // Catch:{ Exception -> 0x024d }
        L_0x022d:
            android.widget.TextView r0 = r14.mTvVideoTitleInfor     // Catch:{ Exception -> 0x024d }
            if (r0 == 0) goto L_0x023e
            android.content.Context r1 = r14.mContext     // Catch:{ Exception -> 0x024d }
            android.content.res.Resources r1 = r1.getResources()     // Catch:{ Exception -> 0x024d }
            java.lang.String r1 = r1.getString(r2)     // Catch:{ Exception -> 0x024d }
            r0.setText(r1)     // Catch:{ Exception -> 0x024d }
        L_0x023e:
            android.widget.TextView r0 = r14.mTvVideoMessage     // Catch:{ Exception -> 0x024d }
            if (r0 == 0) goto L_0x0245
            r0.setVisibility(r8)     // Catch:{ Exception -> 0x024d }
        L_0x0245:
            android.widget.CheckBox r14 = r14.mChkVideoState     // Catch:{ Exception -> 0x024d }
            if (r14 == 0) goto L_0x026a
            r14.setChecked(r9)     // Catch:{ Exception -> 0x024d }
            goto L_0x026a
        L_0x024d:
            r14 = move-exception
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "refreshPlayState: e = "
            java.lang.StringBuilder r0 = r0.append(r1)
            java.lang.String r14 = r14.toString()
            java.lang.StringBuilder r14 = r0.append(r14)
            java.lang.String r14 = r14.toString()
            java.lang.String r0 = "MainFragment"
            android.util.Log.i(r0, r14)
        L_0x026a:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.szchoiceway.customerui.fragment.MainFragment.refreshPlayState():void");
    }

    private void registerReceiver() {
        if (this.myBroadcast == null) {
            this.myBroadcast = new MyBroadcast();
        }
        if (this.mContext != null) {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.net.wifi.WIFI_STATE_CHANGED");
            intentFilter.addAction("android.net.wifi.RSSI_CHANGED");
            intentFilter.addAction("android.net.wifi.STATE_CHANGE");
            intentFilter.addAction("com.choiceway.eventcenter.EventUtils.KSW_ZXW_BT_CONNECED_SHOW_VIEW");
            intentFilter.addAction("android.bluetooth.adapter.action.STATE_CHANGED");
            intentFilter.addAction("com.szchoiceway.ACTION_LAUNCHER_KEY_CTRL");
            intentFilter.addAction("com.szchoiceway.action.mode_title_change");
            intentFilter.addAction("KESAIWEI_DISPLAY_MODE_CHANGED_ACTION");
            intentFilter.addAction("ZXW_ACTION_CHANGE_BRIGHTNESS_SYSTEM");
            intentFilter.addAction(EventUtils.ZXW_ACTION_NOTIFICATION_CLICK_MUTE);
            intentFilter.addAction(EventUtils.ZXW_ACTION_BT_PHONE_CALL_INTERFACE);
            intentFilter.addAction(EventUtils.ZXW_ACTION_KSW_UPDRADE_FAST_CHARGING_STATUS);
            intentFilter.addAction("ZXW_ACTION_KSW_THEME_CHANGE");
            intentFilter.addAction("android.intent.action.SIM_STATE_CHANGED");
            this.mContext.registerReceiver(this.myBroadcast, intentFilter);
            IntentFilter intentFilter2 = new IntentFilter();
            intentFilter2.addAction("android.intent.action.MEDIA_MOUNTED");
            intentFilter2.addAction("android.intent.action.MEDIA_UNMOUNTED");
            intentFilter2.addDataScheme("file");
            this.mContext.registerReceiver(this.myBroadcast, intentFilter2);
        }
    }

    class MyBroadcast extends BroadcastReceiver {
        MyBroadcast() {
        }

        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if ("android.bluetooth.adapter.action.STATE_CHANGED".equals(action)) {
                intent.getIntExtra("android.bluetooth.adapter.extra.STATE", 0);
                MainFragment.this.refreshBTStatus(0);
                Intent intent2 = new Intent("com.choiceway.eventcenter.EventUtils.KSW_ZXW_BT_CONNECED_SHOW_VIEW");
                intent2.putExtra("com.choiceway.eventcenter.EventUtils.KSW_ZXW_BT_CONNECED_SHOW_VIEW_DATA", false);
                MainFragment.this.mContext.sendBroadcast(intent2);
            } else if (!"com.choiceway.eventcenter.EventUtils.KSW_ZXW_BT_CONNECED_SHOW_VIEW".equals(action)) {
            } else {
                if (intent.getBooleanExtra("com.choiceway.eventcenter.EventUtils.KSW_ZXW_BT_CONNECED_SHOW_VIEW_DATA", false)) {
                    MainFragment.this.refreshBTStatus(1);
                } else {
                    MainFragment.this.refreshBTStatus(2);
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public void refreshBTStatus(int i) {
        View view = this.bt_status_iv;
        if (view != null) {
            boolean z = true;
            if (i != 1) {
                z = false;
            }
            view.setSelected(z);
        }
    }

    public void setMainFocusMove(int i, boolean z) {
        ItemUtil itemUtil2;
        if (this.m_iUIIndex != 5) {
            if (this.mRecyclerView == null || !this.pageChange) {
                switch (i) {
                    case 1:
                        if (this.m_iUIIndex != 7) {
                            if (!(this.m_iUIIndex == 6 || this.model.m_iModeSet == 26 || this.model.bInLeftFocus)) {
                                this.iLeftFocusIndex = 0;
                                this.model.bInLeftFocus = true;
                                this.iLastMainFocusIndex = this.iLastMainPageIndex * this.iPagerItemViewItemCount;
                                this.iMainFocusIndex = -1;
                            }
                            ILauncherStrategy iLauncherStrategy = this.launcherStrategy;
                            if (!(iLauncherStrategy == null || !iLauncherStrategy.isLeftMcuKeyEnabled() || this.launcherStrategy.getLeftNextMainFocusIndex(this.iMainFocusIndex) == -1)) {
                                this.iMainFocusIndex = this.launcherStrategy.getLeftNextMainFocusIndex(this.iMainFocusIndex);
                                break;
                            }
                        }
                        break;
                    case 2:
                        if (this.m_iUIIndex != 7) {
                            if (!(this.m_iUIIndex == 6 || this.model.m_iModeSet == 26 || !this.model.bInLeftFocus)) {
                                this.model.bInLeftFocus = false;
                                this.iLeftFocusIndex = -1;
                                this.iMainFocusIndex = this.iLastMainFocusIndex;
                            }
                            ILauncherStrategy iLauncherStrategy2 = this.launcherStrategy;
                            if (!(iLauncherStrategy2 == null || !iLauncherStrategy2.isRightMcuKeyEnabled() || this.launcherStrategy.getRightNextMainFocusIndex(this.iMainFocusIndex) == -1)) {
                                this.iMainFocusIndex = this.launcherStrategy.getRightNextMainFocusIndex(this.iMainFocusIndex);
                                break;
                            }
                        }
                        break;
                    case 3:
                        ILauncherStrategy iLauncherStrategy3 = this.launcherStrategy;
                        if (!(iLauncherStrategy3 == null || !iLauncherStrategy3.isUpMcuKeyEnabled() || this.launcherStrategy.getUpNextMainFocusIndex(this.iMainFocusIndex) == -1)) {
                            this.iMainFocusIndex = this.launcherStrategy.getUpNextMainFocusIndex(this.iMainFocusIndex);
                            break;
                        }
                    case 4:
                        ILauncherStrategy iLauncherStrategy4 = this.launcherStrategy;
                        if (!(iLauncherStrategy4 == null || !iLauncherStrategy4.isDownMcuKeyEnabled() || this.launcherStrategy.getDownNextMainFocusIndex(this.iMainFocusIndex) == -1)) {
                            this.iMainFocusIndex = this.launcherStrategy.getDownNextMainFocusIndex(this.iMainFocusIndex);
                            break;
                        }
                    case 5:
                        if (this.m_iUIIndex != 6 || !this.themeDialog.getIsShow()) {
                            if (!this.model.bInLeftFocus) {
                                if (this.m_iModeSet != 38) {
                                    enterMainFocus();
                                    break;
                                } else {
                                    AudiGT6LoopRotarySwitchView audiGT6LoopRotarySwitchView = this.loop_view;
                                    if (audiGT6LoopRotarySwitchView != null) {
                                        audiGT6LoopRotarySwitchView.selectedItemClick();
                                        break;
                                    }
                                }
                            } else {
                                enterLeftFocus();
                                break;
                            }
                        } else {
                            return;
                        }
                        break;
                    case 7:
                        if (this.m_iUIIndex != 6 || !this.themeDialog.getIsShow()) {
                            if (!this.model.bInLeftFocus) {
                                if (this.m_iModeSet != 38) {
                                    int i2 = this.iMainFocusIndex - 1;
                                    this.iMainFocusIndex = i2;
                                    View[] viewArr = this.mLeftButtons;
                                    if (viewArr != null && viewArr.length > 0) {
                                        if (i2 == -1) {
                                            this.model.bInLeftFocus = true;
                                            this.iLeftFocusIndex = this.mLeftButtons.length - 1;
                                            break;
                                        }
                                    } else if (i2 < 0) {
                                        this.iMainFocusIndex = 0;
                                        break;
                                    }
                                } else {
                                    DebounceHelper<Integer> debounceHelper = this.audiGt6DebounceHelper;
                                    if (debounceHelper != null) {
                                        debounceHelper.emit(0);
                                        break;
                                    }
                                }
                            } else {
                                int i3 = this.iLeftFocusIndex - 1;
                                this.iLeftFocusIndex = i3;
                                if (i3 < 0) {
                                    this.iLeftFocusIndex = 0;
                                    break;
                                }
                            }
                        } else {
                            return;
                        }
                        break;
                    case 8:
                        if (this.m_iUIIndex != 6 || !this.themeDialog.getIsShow()) {
                            if (!this.model.bInLeftFocus) {
                                if (this.m_iModeSet != 38) {
                                    int i4 = this.iMainFocusIndex + 1;
                                    this.iMainFocusIndex = i4;
                                    if (this.mRecyclerView != null && (itemUtil2 = this.itemUtil) != null) {
                                        if (i4 >= itemUtil2.getAllItemTags().size()) {
                                            this.iMainFocusIndex = this.itemUtil.getAllItemTags().size() - 1;
                                            break;
                                        }
                                    } else if (this.m_iUIIndex != 7) {
                                        int i5 = this.iMainFocusIndex;
                                        View[] viewArr2 = this.mMainButtons;
                                        if (i5 >= viewArr2.length) {
                                            this.iMainFocusIndex = viewArr2.length - 1;
                                            break;
                                        }
                                    } else {
                                        boolean recordBoolean = SysProviderOpt.getInstance(this.mContext).getRecordBoolean(SysProviderOpt.KSW_SHOW_AIR, true);
                                        View[] viewArr3 = this.mMainButtons;
                                        int length = viewArr3.length;
                                        if (!recordBoolean) {
                                            length = viewArr3.length - 1;
                                        }
                                        if (this.iMainFocusIndex >= length) {
                                            this.iMainFocusIndex = length - 1;
                                            break;
                                        }
                                    }
                                } else {
                                    DebounceHelper<Integer> debounceHelper2 = this.audiGt6DebounceHelper;
                                    if (debounceHelper2 != null) {
                                        debounceHelper2.emit(1);
                                        break;
                                    }
                                }
                            } else {
                                int i6 = this.iLeftFocusIndex + 1;
                                this.iLeftFocusIndex = i6;
                                View[] viewArr4 = this.mLeftButtons;
                                if (viewArr4 != null && viewArr4.length > 0 && i6 == viewArr4.length) {
                                    this.iLeftFocusIndex = -1;
                                    this.model.bInLeftFocus = false;
                                    this.iMainFocusIndex = this.iLastMainFocusIndex;
                                    break;
                                }
                            }
                        } else {
                            return;
                        }
                        break;
                }
                refreshLeftFocus();
                refreshMainFocus();
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:173:0x0222, code lost:
        if (r0.getValidMode() != com.szchoiceway.customerui.utils.EventUtils.eSrcMode.SRC_MUSIC.getIntValue()) goto L_0x0062;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:182:0x023f, code lost:
        if (r0.getValidMode() != com.szchoiceway.customerui.utils.EventUtils.eSrcMode.SRC_MUSIC.getIntValue()) goto L_0x0062;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:197:0x026a, code lost:
        if (r0.getValidMode() != com.szchoiceway.customerui.utils.EventUtils.eSrcMode.SRC_MUSIC.getIntValue()) goto L_0x0062;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x009f, code lost:
        if (r0.getValidMode() != com.szchoiceway.customerui.utils.EventUtils.eSrcMode.SRC_MOVIE.getIntValue()) goto L_0x0062;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x00c1, code lost:
        if (r0.getValidMode() != com.szchoiceway.customerui.utils.EventUtils.eSrcMode.SRC_MOVIE.getIntValue()) goto L_0x0062;
     */
    /* JADX WARNING: Removed duplicated region for block: B:361:0x0520  */
    /* JADX WARNING: Removed duplicated region for block: B:364:0x0528  */
    /* JADX WARNING: Removed duplicated region for block: B:365:0x052f  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onClick(android.view.View r17) {
        /*
            r16 = this;
            r1 = r16
            r2 = r17
            int r0 = r1.m_iModeSet
            r3 = 1879638183(0x700900a7, float:1.6960094E29)
            r4 = 19
            r5 = 6
            if (r0 != r4) goto L_0x001c
            com.szchoiceway.customerui.dialog.AppListDialog r0 = r1.appListDialog
            if (r0 == 0) goto L_0x001c
            boolean r0 = r0.isShow
            if (r0 == 0) goto L_0x001c
            com.szchoiceway.customerui.dialog.AppListDialog r0 = r1.appListDialog
            r0.hideDialog()
            goto L_0x0033
        L_0x001c:
            int r0 = r1.m_iUIIndex
            if (r0 != r5) goto L_0x0033
            int r0 = r17.getId()
            if (r0 == r3) goto L_0x0033
            com.szchoiceway.customerui.dialog.BenzControlDialog r0 = r1.benzControlDialog
            if (r0 == 0) goto L_0x0033
            boolean r0 = r0.isShow
            if (r0 == 0) goto L_0x0033
            com.szchoiceway.customerui.dialog.BenzControlDialog r0 = r1.benzControlDialog
            r0.hideDialog()
        L_0x0033:
            com.szchoiceway.customerui.model.LauncherModel r0 = r1.model
            r6 = 0
            r0.bInLeftFocus = r6
            r0 = -1
            r1.iLeftFocusIndex = r0
            int r7 = r17.getId()
            if (r7 == r3) goto L_0x004c
            int r3 = r17.getId()
            r7 = 1879638366(0x7009015e, float:1.696044E29)
            if (r3 == r7) goto L_0x004c
            r1.iMainFocusIndex = r0
        L_0x004c:
            int r3 = r17.getId()
            java.lang.String r7 = "com.txznet.weather.MainActivity"
            java.lang.String r8 = "com.txznet.weather"
            java.lang.String r10 = "XINCHENG"
            r12 = 41
            r13 = 5
            r14 = 7
            r6 = 38
            r15 = 4
            r11 = 3
            r9 = 1
            switch(r3) {
                case 1879638183: goto L_0x05a9;
                case 1879638198: goto L_0x0592;
                case 1879638199: goto L_0x0561;
                case 1879638200: goto L_0x0561;
                case 1879638201: goto L_0x0561;
                case 1879638206: goto L_0x0550;
                case 1879638225: goto L_0x0534;
                case 1879638226: goto L_0x04c0;
                case 1879638227: goto L_0x04b9;
                case 1879638228: goto L_0x04b2;
                case 1879638229: goto L_0x04a2;
                case 1879638230: goto L_0x04a2;
                case 1879638231: goto L_0x049b;
                case 1879638232: goto L_0x0494;
                case 1879638240: goto L_0x046b;
                case 1879638241: goto L_0x046b;
                case 1879638242: goto L_0x046b;
                case 1879638252: goto L_0x0421;
                case 1879638256: goto L_0x0416;
                case 1879638257: goto L_0x0416;
                case 1879638269: goto L_0x03ef;
                case 1879638270: goto L_0x03ef;
                case 1879638271: goto L_0x03ef;
                case 1879638285: goto L_0x03d4;
                case 1879638290: goto L_0x03b0;
                case 1879638291: goto L_0x038d;
                case 1879638292: goto L_0x036a;
                case 1879638293: goto L_0x0343;
                case 1879638294: goto L_0x0337;
                case 1879638295: goto L_0x0332;
                case 1879638296: goto L_0x0314;
                case 1879638297: goto L_0x02f7;
                case 1879638298: goto L_0x02ec;
                case 1879638299: goto L_0x02c7;
                case 1879638300: goto L_0x02c2;
                case 1879638311: goto L_0x0277;
                case 1879638312: goto L_0x024c;
                case 1879638314: goto L_0x022f;
                case 1879638316: goto L_0x0204;
                case 1879638326: goto L_0x01f5;
                case 1879638327: goto L_0x01f5;
                case 1879638328: goto L_0x0197;
                case 1879638337: goto L_0x0154;
                case 1879638340: goto L_0x0145;
                case 1879638341: goto L_0x0145;
                case 1879638353: goto L_0x011d;
                case 1879638354: goto L_0x011d;
                case 1879638355: goto L_0x011d;
                case 1879638366: goto L_0x010c;
                case 1879638367: goto L_0x00cc;
                case 1879638368: goto L_0x00aa;
                case 1879638369: goto L_0x0088;
                case 1879638377: goto L_0x006f;
                case 1879638378: goto L_0x0065;
                case 1879638379: goto L_0x0065;
                case 1879638550: goto L_0x0343;
                case 1879638551: goto L_0x0314;
                case 1879638552: goto L_0x02f7;
                case 1879638553: goto L_0x02ec;
                case 1879638554: goto L_0x02c7;
                case 1879638555: goto L_0x04c0;
                case 1879638556: goto L_0x0421;
                case 1879638557: goto L_0x0277;
                case 1879638558: goto L_0x0197;
                case 1879638559: goto L_0x00cc;
                case 1879638560: goto L_0x006f;
                case 1879639038: goto L_0x010c;
                case 1879639068: goto L_0x0277;
                default: goto L_0x0062;
            }
        L_0x0062:
            r3 = 0
            goto L_0x05bd
        L_0x0065:
            r1.iMainFocusIndex = r11
            android.content.Context r0 = r16.getContext()
            com.szchoiceway.customerui.utils.EventUtils.startActivityIfNotRunning(r0, r8, r7)
            goto L_0x0062
        L_0x006f:
            boolean r0 = r16.isDefaultUI()
            if (r0 == 0) goto L_0x0078
            r1.iMainFocusIndex = r9
            goto L_0x0080
        L_0x0078:
            com.szchoiceway.customerui.model.LauncherModel r0 = r1.model
            int r0 = r0.m_iModeSet
            if (r0 != r4) goto L_0x0080
            r1.iMainFocusIndex = r14
        L_0x0080:
            android.content.Context r0 = r16.getContext()
            com.szchoiceway.customerui.utils.EventUtils.startActivityIfNotRunning(r0, r8, r7)
            goto L_0x0062
        L_0x0088:
            int r0 = r1.m_iUIIndex
            if (r0 != r5) goto L_0x008f
            r1.iMainFocusIndex = r11
            goto L_0x0091
        L_0x008f:
            r1.iMainFocusIndex = r13
        L_0x0091:
            com.szchoiceway.eventcenter.IEventService r0 = r1.mEvtService     // Catch:{ RemoteException -> 0x00a2 }
            if (r0 == 0) goto L_0x00a6
            int r0 = r0.getValidMode()     // Catch:{ RemoteException -> 0x00a2 }
            com.szchoiceway.customerui.utils.EventUtils$eSrcMode r3 = com.szchoiceway.customerui.utils.EventUtils.eSrcMode.SRC_MOVIE     // Catch:{ RemoteException -> 0x00a2 }
            int r3 = r3.getIntValue()     // Catch:{ RemoteException -> 0x00a2 }
            if (r0 == r3) goto L_0x00a6
            goto L_0x0062
        L_0x00a2:
            r0 = move-exception
            r0.printStackTrace()
        L_0x00a6:
            r16.playPrev()
            goto L_0x0062
        L_0x00aa:
            int r0 = r1.m_iUIIndex
            if (r0 != r5) goto L_0x00b1
            r1.iMainFocusIndex = r11
            goto L_0x00b3
        L_0x00b1:
            r1.iMainFocusIndex = r13
        L_0x00b3:
            com.szchoiceway.eventcenter.IEventService r0 = r1.mEvtService     // Catch:{ RemoteException -> 0x00c4 }
            if (r0 == 0) goto L_0x00c8
            int r0 = r0.getValidMode()     // Catch:{ RemoteException -> 0x00c4 }
            com.szchoiceway.customerui.utils.EventUtils$eSrcMode r3 = com.szchoiceway.customerui.utils.EventUtils.eSrcMode.SRC_MOVIE     // Catch:{ RemoteException -> 0x00c4 }
            int r3 = r3.getIntValue()     // Catch:{ RemoteException -> 0x00c4 }
            if (r0 == r3) goto L_0x00c8
            goto L_0x0062
        L_0x00c4:
            r0 = move-exception
            r0.printStackTrace()
        L_0x00c8:
            r16.playNext()
            goto L_0x0062
        L_0x00cc:
            int r3 = r1.m_iUITypeVer
            if (r3 != r12) goto L_0x00ef
            boolean r3 = r16.isDefaultUI()
            if (r3 == 0) goto L_0x00e2
            com.szchoiceway.customerui.model.LauncherModel r3 = r1.model
            int r3 = r3.m_i_ksw_evo_id7_main_interface_index
            if (r3 != r9) goto L_0x00df
            r1.iMainFocusIndex = r9
            goto L_0x00ef
        L_0x00df:
            r1.iMainFocusIndex = r15
            goto L_0x00ef
        L_0x00e2:
            int r3 = r1.m_iModeSet
            if (r3 != r4) goto L_0x00e9
            r1.iMainFocusIndex = r13
            goto L_0x00ef
        L_0x00e9:
            int r3 = r1.m_iUIIndex
            if (r3 != r5) goto L_0x00ef
            r1.iMainFocusIndex = r11
        L_0x00ef:
            com.szchoiceway.customerui.fragment.launcher.strategy.ILauncherStrategy r3 = r1.launcherStrategy
            if (r3 == 0) goto L_0x0101
            int r3 = r3.getVideoMainFocusIndex()
            if (r3 == r0) goto L_0x0101
            com.szchoiceway.customerui.fragment.launcher.strategy.ILauncherStrategy r0 = r1.launcherStrategy
            int r0 = r0.getVideoMainFocusIndex()
            r1.iMainFocusIndex = r0
        L_0x0101:
            int r0 = r1.m_iModeSet
            if (r0 != r6) goto L_0x0107
            r1.iMainFocusIndex = r11
        L_0x0107:
            r16.enterVideo()
            goto L_0x0062
        L_0x010c:
            com.szchoiceway.customerui.dialog.ThemeDialog r0 = r1.themeDialog
            if (r0 == 0) goto L_0x0062
            boolean r0 = r0.getIsShow()
            if (r0 != 0) goto L_0x0062
            com.szchoiceway.customerui.dialog.ThemeDialog r0 = r1.themeDialog
            r0.showThemeDialog()
            goto L_0x0062
        L_0x011d:
            int r3 = r1.m_iUIIndex
            if (r3 != r5) goto L_0x0124
            r1.iMainFocusIndex = r13
            goto L_0x0126
        L_0x0124:
            r1.iMainFocusIndex = r15
        L_0x0126:
            com.szchoiceway.customerui.fragment.launcher.strategy.ILauncherStrategy r3 = r1.launcherStrategy
            if (r3 == 0) goto L_0x0138
            int r3 = r3.getSettingsMainFocusIndex()
            if (r3 == r0) goto L_0x0138
            com.szchoiceway.customerui.fragment.launcher.strategy.ILauncherStrategy r0 = r1.launcherStrategy
            int r0 = r0.getSettingsMainFocusIndex()
            r1.iMainFocusIndex = r0
        L_0x0138:
            int r0 = r1.m_iModeSet
            if (r0 != r6) goto L_0x0140
            r0 = 9
            r1.iMainFocusIndex = r0
        L_0x0140:
            r16.enterSettings()
            goto L_0x0062
        L_0x0145:
            int r0 = r1.m_iUIIndex
            if (r0 != r5) goto L_0x014d
            r0 = 2
            r1.iMainFocusIndex = r0
            goto L_0x014f
        L_0x014d:
            r1.iMainFocusIndex = r14
        L_0x014f:
            r16.enterOriginal()
            goto L_0x0062
        L_0x0154:
            r1.iMainFocusIndex = r14
            int r3 = r1.m_iUITypeVer
            if (r3 != r12) goto L_0x0062
            boolean r3 = r16.isDefaultUI()
            if (r3 == 0) goto L_0x0163
            r1.iMainFocusIndex = r15
            goto L_0x017a
        L_0x0163:
            int r3 = r1.m_iModeSet
            if (r3 != r4) goto L_0x016a
            r1.iMainFocusIndex = r9
            goto L_0x017a
        L_0x016a:
            int r3 = r1.m_iModeSet
            r4 = 33
            if (r3 != r4) goto L_0x0173
            r1.iMainFocusIndex = r14
            goto L_0x017a
        L_0x0173:
            int r3 = r1.m_iUIIndex
            if (r3 != r5) goto L_0x017a
            r3 = 2
            r1.iMainFocusIndex = r3
        L_0x017a:
            com.szchoiceway.customerui.fragment.launcher.strategy.ILauncherStrategy r3 = r1.launcherStrategy
            if (r3 == 0) goto L_0x018c
            int r3 = r3.getOriginalCarMainFocusIndex()
            if (r3 == r0) goto L_0x018c
            com.szchoiceway.customerui.fragment.launcher.strategy.ILauncherStrategy r0 = r1.launcherStrategy
            int r0 = r0.getOriginalCarMainFocusIndex()
            r1.iMainFocusIndex = r0
        L_0x018c:
            int r0 = r1.m_iModeSet
            if (r0 != r6) goto L_0x0192
            r1.iMainFocusIndex = r9
        L_0x0192:
            r16.enterOriginal()
            goto L_0x0062
        L_0x0197:
            int r3 = r1.m_iUITypeVer
            if (r3 != r12) goto L_0x01d7
            int r3 = r1.m_iModeSet
            if (r3 == 0) goto L_0x01c9
            java.lang.String r3 = r1.xml_client
            boolean r3 = r10.equals(r3)
            if (r3 == 0) goto L_0x01b0
            int r3 = r1.m_iModeSet
            if (r3 == r9) goto L_0x01c9
            int r3 = r1.m_iModeSet
            if (r3 != r11) goto L_0x01b0
            goto L_0x01c9
        L_0x01b0:
            boolean r3 = r16.isDefaultUI()
            if (r3 == 0) goto L_0x01ba
            r3 = 0
            r1.iMainFocusIndex = r3
            goto L_0x01d7
        L_0x01ba:
            r3 = 0
            int r7 = r1.m_iModeSet
            if (r7 != r4) goto L_0x01c2
            r1.iMainFocusIndex = r3
            goto L_0x01d7
        L_0x01c2:
            int r4 = r1.m_iUIIndex
            if (r4 != r5) goto L_0x01d7
            r1.iMainFocusIndex = r9
            goto L_0x01d7
        L_0x01c9:
            r3 = 0
            java.lang.String r4 = r1.xml_client
            boolean r4 = r10.equals(r4)
            if (r4 == 0) goto L_0x01d5
            r1.iMainFocusIndex = r3
            goto L_0x01d7
        L_0x01d5:
            r1.iMainFocusIndex = r9
        L_0x01d7:
            com.szchoiceway.customerui.fragment.launcher.strategy.ILauncherStrategy r3 = r1.launcherStrategy
            if (r3 == 0) goto L_0x01e9
            int r3 = r3.getNaviMainFocusIndex()
            if (r3 == r0) goto L_0x01e9
            com.szchoiceway.customerui.fragment.launcher.strategy.ILauncherStrategy r0 = r1.launcherStrategy
            int r0 = r0.getNaviMainFocusIndex()
            r1.iMainFocusIndex = r0
        L_0x01e9:
            int r0 = r1.m_iModeSet
            if (r0 != r6) goto L_0x01f0
            r0 = 2
            r1.iMainFocusIndex = r0
        L_0x01f0:
            r16.enterNavi()
            goto L_0x0062
        L_0x01f5:
            int r0 = r1.m_iUIIndex
            if (r0 != r5) goto L_0x01fc
            r1.iMainFocusIndex = r9
            goto L_0x01ff
        L_0x01fc:
            r3 = 0
            r1.iMainFocusIndex = r3
        L_0x01ff:
            r16.enterNavi()
            goto L_0x0062
        L_0x0204:
            int r0 = r1.m_iModeSet
            if (r0 != r4) goto L_0x020b
            r1.iMainFocusIndex = r11
            goto L_0x0214
        L_0x020b:
            int r0 = r1.m_iUIIndex
            if (r0 != r5) goto L_0x0212
            r1.iMainFocusIndex = r15
            goto L_0x0214
        L_0x0212:
            r1.iMainFocusIndex = r9
        L_0x0214:
            com.szchoiceway.eventcenter.IEventService r0 = r1.mEvtService     // Catch:{ RemoteException -> 0x0226 }
            if (r0 == 0) goto L_0x022a
            int r0 = r0.getValidMode()     // Catch:{ RemoteException -> 0x0226 }
            com.szchoiceway.customerui.utils.EventUtils$eSrcMode r3 = com.szchoiceway.customerui.utils.EventUtils.eSrcMode.SRC_MUSIC     // Catch:{ RemoteException -> 0x0226 }
            int r3 = r3.getIntValue()     // Catch:{ RemoteException -> 0x0226 }
            if (r0 == r3) goto L_0x022a
            goto L_0x0062
        L_0x0226:
            r0 = move-exception
            r0.printStackTrace()
        L_0x022a:
            r16.playPrev()
            goto L_0x0062
        L_0x022f:
            r1.iMainFocusIndex = r11
            com.szchoiceway.eventcenter.IEventService r0 = r1.mEvtService     // Catch:{ RemoteException -> 0x0243 }
            if (r0 == 0) goto L_0x0247
            int r0 = r0.getValidMode()     // Catch:{ RemoteException -> 0x0243 }
            com.szchoiceway.customerui.utils.EventUtils$eSrcMode r3 = com.szchoiceway.customerui.utils.EventUtils.eSrcMode.SRC_MUSIC     // Catch:{ RemoteException -> 0x0243 }
            int r3 = r3.getIntValue()     // Catch:{ RemoteException -> 0x0243 }
            if (r0 == r3) goto L_0x0247
            goto L_0x0062
        L_0x0243:
            r0 = move-exception
            r0.printStackTrace()
        L_0x0247:
            r16.playPause()
            goto L_0x0062
        L_0x024c:
            int r0 = r1.m_iModeSet
            if (r0 != r4) goto L_0x0253
            r1.iMainFocusIndex = r11
            goto L_0x025c
        L_0x0253:
            int r0 = r1.m_iUIIndex
            if (r0 != r5) goto L_0x025a
            r1.iMainFocusIndex = r15
            goto L_0x025c
        L_0x025a:
            r1.iMainFocusIndex = r9
        L_0x025c:
            com.szchoiceway.eventcenter.IEventService r0 = r1.mEvtService     // Catch:{ RemoteException -> 0x026e }
            if (r0 == 0) goto L_0x0272
            int r0 = r0.getValidMode()     // Catch:{ RemoteException -> 0x026e }
            com.szchoiceway.customerui.utils.EventUtils$eSrcMode r3 = com.szchoiceway.customerui.utils.EventUtils.eSrcMode.SRC_MUSIC     // Catch:{ RemoteException -> 0x026e }
            int r3 = r3.getIntValue()     // Catch:{ RemoteException -> 0x026e }
            if (r0 == r3) goto L_0x0272
            goto L_0x0062
        L_0x026e:
            r0 = move-exception
            r0.printStackTrace()
        L_0x0272:
            r16.playNext()
            goto L_0x0062
        L_0x0277:
            int r3 = r1.m_iUITypeVer
            if (r3 != r12) goto L_0x02a5
            boolean r3 = r16.isDefaultUI()
            if (r3 == 0) goto L_0x028e
            com.szchoiceway.customerui.model.LauncherModel r3 = r1.model
            int r3 = r3.m_i_ksw_evo_id7_main_interface_index
            if (r3 != r9) goto L_0x028a
            r1.iMainFocusIndex = r15
            goto L_0x02a5
        L_0x028a:
            r3 = 2
            r1.iMainFocusIndex = r3
            goto L_0x02a5
        L_0x028e:
            int r3 = r1.m_iModeSet
            if (r3 != r4) goto L_0x0295
            r1.iMainFocusIndex = r11
            goto L_0x02a5
        L_0x0295:
            int r3 = r1.m_iModeSet
            r4 = 33
            if (r3 != r4) goto L_0x029f
            r3 = 0
            r1.iMainFocusIndex = r3
            goto L_0x02a5
        L_0x029f:
            int r3 = r1.m_iUIIndex
            if (r3 != r5) goto L_0x02a5
            r1.iMainFocusIndex = r15
        L_0x02a5:
            com.szchoiceway.customerui.fragment.launcher.strategy.ILauncherStrategy r3 = r1.launcherStrategy
            if (r3 == 0) goto L_0x02b7
            int r3 = r3.getMusicMainFocusIndex()
            if (r3 == r0) goto L_0x02b7
            com.szchoiceway.customerui.fragment.launcher.strategy.ILauncherStrategy r0 = r1.launcherStrategy
            int r0 = r0.getMusicMainFocusIndex()
            r1.iMainFocusIndex = r0
        L_0x02b7:
            int r0 = r1.m_iModeSet
            if (r0 != r6) goto L_0x02bd
            r1.iMainFocusIndex = r13
        L_0x02bd:
            r16.enterMusic()
            goto L_0x0062
        L_0x02c2:
            r16.enterVideo()
            goto L_0x0062
        L_0x02c7:
            com.szchoiceway.customerui.model.LauncherModel r3 = r1.model
            r3.bInLeftFocus = r9
            int r3 = r1.m_iModeSet
            if (r3 != r4) goto L_0x02d2
            r1.iLeftFocusIndex = r9
            goto L_0x02d5
        L_0x02d2:
            r3 = 2
            r1.iLeftFocusIndex = r3
        L_0x02d5:
            com.szchoiceway.customerui.fragment.launcher.strategy.ILauncherStrategy r3 = r1.launcherStrategy
            if (r3 == 0) goto L_0x02e7
            int r3 = r3.getSettingsLeftFocusIndex()
            if (r3 == r0) goto L_0x02e7
            com.szchoiceway.customerui.fragment.launcher.strategy.ILauncherStrategy r0 = r1.launcherStrategy
            int r0 = r0.getSettingsLeftFocusIndex()
            r1.iLeftFocusIndex = r0
        L_0x02e7:
            r16.enterSettings()
            goto L_0x0062
        L_0x02ec:
            com.szchoiceway.customerui.model.LauncherModel r0 = r1.model
            r0.bInLeftFocus = r9
            r1.iLeftFocusIndex = r11
            r16.enterOriginal()
            goto L_0x0062
        L_0x02f7:
            com.szchoiceway.customerui.model.LauncherModel r3 = r1.model
            r3.bInLeftFocus = r9
            r1.iLeftFocusIndex = r9
            com.szchoiceway.customerui.fragment.launcher.strategy.ILauncherStrategy r3 = r1.launcherStrategy
            if (r3 == 0) goto L_0x030f
            int r3 = r3.getNaviLeftFocusIndex()
            if (r3 == r0) goto L_0x030f
            com.szchoiceway.customerui.fragment.launcher.strategy.ILauncherStrategy r0 = r1.launcherStrategy
            int r0 = r0.getNaviLeftFocusIndex()
            r1.iLeftFocusIndex = r0
        L_0x030f:
            r16.enterNavi()
            goto L_0x0062
        L_0x0314:
            com.szchoiceway.customerui.model.LauncherModel r3 = r1.model
            r3.bInLeftFocus = r9
            r3 = 0
            r1.iLeftFocusIndex = r3
            com.szchoiceway.customerui.fragment.launcher.strategy.ILauncherStrategy r3 = r1.launcherStrategy
            if (r3 == 0) goto L_0x032d
            int r3 = r3.getMusicLeftFocusIndex()
            if (r3 == r0) goto L_0x032d
            com.szchoiceway.customerui.fragment.launcher.strategy.ILauncherStrategy r0 = r1.launcherStrategy
            int r0 = r0.getMusicLeftFocusIndex()
            r1.iLeftFocusIndex = r0
        L_0x032d:
            r16.enterMusic()
            goto L_0x0062
        L_0x0332:
            com.szchoiceway.customerui.utils.EventUtils.sendKeyDownUpSync(r11)
            goto L_0x0062
        L_0x0337:
            com.szchoiceway.customerui.model.LauncherModel r0 = r1.model
            r0.bInLeftFocus = r9
            r0 = 2
            r1.iLeftFocusIndex = r0
            r16.enterBT()
            goto L_0x0062
        L_0x0343:
            com.szchoiceway.customerui.model.LauncherModel r3 = r1.model
            r3.bInLeftFocus = r9
            int r3 = r1.m_iModeSet
            if (r3 != r4) goto L_0x034f
            r3 = 0
            r1.iLeftFocusIndex = r3
            goto L_0x0351
        L_0x034f:
            r1.iLeftFocusIndex = r15
        L_0x0351:
            com.szchoiceway.customerui.fragment.launcher.strategy.ILauncherStrategy r3 = r1.launcherStrategy
            if (r3 == 0) goto L_0x0363
            int r3 = r3.getAppsLeftFocusIndex()
            if (r3 == r0) goto L_0x0363
            com.szchoiceway.customerui.fragment.launcher.strategy.ILauncherStrategy r0 = r1.launcherStrategy
            int r0 = r0.getAppsLeftFocusIndex()
            r1.iLeftFocusIndex = r0
        L_0x0363:
            com.szchoiceway.customerui.model.LauncherModel r0 = r1.model
            r0.showAppList(r9)
            goto L_0x0062
        L_0x036a:
            com.szchoiceway.customerui.model.LauncherModel r0 = r1.model
            r0.bInLeftFocus = r9
            r1.iLeftFocusIndex = r15
            com.szchoiceway.customerui.utils.SysProviderOpt r0 = r1.mProvider
            java.lang.String r3 = "LEFT_APP2_MODE_PACKAGE_NAME"
            java.lang.String r4 = "com.szchoiceway.videoplayer"
            java.lang.String r0 = r0.getRecordValue(r3, r4)
            com.szchoiceway.customerui.utils.SysProviderOpt r3 = r1.mProvider
            java.lang.String r4 = "LEFT_APP2_MODE_CLASS_NAME"
            java.lang.String r5 = "com.szchoiceway.videoplayer.MainActivity"
            java.lang.String r3 = r3.getRecordValue(r4, r5)
            android.content.Context r4 = r1.mContext
            com.szchoiceway.eventcenter.IEventService r5 = r1.mEvtService
            com.szchoiceway.customerui.utils.EventUtils.startThirdApp(r4, r5, r0, r3)
            goto L_0x0062
        L_0x038d:
            com.szchoiceway.customerui.model.LauncherModel r0 = r1.model
            r0.bInLeftFocus = r9
            r1.iLeftFocusIndex = r11
            com.szchoiceway.customerui.utils.SysProviderOpt r0 = r1.mProvider
            java.lang.String r3 = "LEFT_APP1_MODE_PACKAGE_NAME"
            java.lang.String r4 = "com.szchoiceway.musicplayer"
            java.lang.String r0 = r0.getRecordValue(r3, r4)
            com.szchoiceway.customerui.utils.SysProviderOpt r3 = r1.mProvider
            java.lang.String r4 = "LEFT_APP1_MODE_CLASS_NAME"
            java.lang.String r5 = "com.szchoiceway.musicplayer.MainActivity"
            java.lang.String r3 = r3.getRecordValue(r4, r5)
            android.content.Context r4 = r1.mContext
            com.szchoiceway.eventcenter.IEventService r5 = r1.mEvtService
            com.szchoiceway.customerui.utils.EventUtils.startThirdApp(r4, r5, r0, r3)
            goto L_0x0062
        L_0x03b0:
            com.szchoiceway.customerui.model.LauncherModel r0 = r1.model
            r0.bInLeftFocus = r9
            r0 = 2
            r1.iLeftFocusIndex = r0
            com.szchoiceway.customerui.utils.SysProviderOpt r0 = r1.mProvider
            java.lang.String r3 = "LEFT_APP0_MODE_PACKAGE_NAME"
            java.lang.String r4 = "com.android.chrome"
            java.lang.String r0 = r0.getRecordValue(r3, r4)
            com.szchoiceway.customerui.utils.SysProviderOpt r3 = r1.mProvider
            java.lang.String r4 = "LEFT_APP0_MODE_CLASS_NAME"
            java.lang.String r5 = "com.google.android.apps.chrome.Main"
            java.lang.String r3 = r3.getRecordValue(r4, r5)
            android.content.Context r4 = r1.mContext
            com.szchoiceway.eventcenter.IEventService r5 = r1.mEvtService
            com.szchoiceway.customerui.utils.EventUtils.startThirdApp(r4, r5, r0, r3)
            goto L_0x0062
        L_0x03d4:
            r3 = 10
            r1.iMainFocusIndex = r3
            com.szchoiceway.customerui.fragment.launcher.strategy.ILauncherStrategy r3 = r1.launcherStrategy
            if (r3 == 0) goto L_0x03ea
            int r3 = r3.getFileManagerMainFocusIndex()
            if (r3 == r0) goto L_0x03ea
            com.szchoiceway.customerui.fragment.launcher.strategy.ILauncherStrategy r0 = r1.launcherStrategy
            int r0 = r0.getFileManagerMainFocusIndex()
            r1.iMainFocusIndex = r0
        L_0x03ea:
            r16.enterFile()
            goto L_0x0062
        L_0x03ef:
            r3 = 9
            r1.iMainFocusIndex = r3
            com.szchoiceway.customerui.fragment.launcher.strategy.ILauncherStrategy r3 = r1.launcherStrategy
            if (r3 == 0) goto L_0x0405
            int r3 = r3.getDVRMainFocusIndex()
            if (r3 == r0) goto L_0x0405
            com.szchoiceway.customerui.fragment.launcher.strategy.ILauncherStrategy r0 = r1.launcherStrategy
            int r0 = r0.getDVRMainFocusIndex()
            r1.iMainFocusIndex = r0
        L_0x0405:
            int r0 = r1.m_iModeSet
            if (r0 != r6) goto L_0x040d
            r0 = 8
            r1.iMainFocusIndex = r0
        L_0x040d:
            android.content.Context r0 = r16.getContext()
            com.szchoiceway.customerui.utils.EventUtils.onEnterDvr(r0)
            goto L_0x0062
        L_0x0416:
            r0 = 8
            r1.iMainFocusIndex = r0
            android.content.Context r0 = r1.mContext
            com.szchoiceway.customerui.utils.EventUtils.onEnterDashBoard(r0)
            goto L_0x0062
        L_0x0421:
            boolean r3 = r16.isDefaultUI()
            if (r3 == 0) goto L_0x0433
            com.szchoiceway.customerui.model.LauncherModel r3 = r1.model
            int r3 = r3.m_i_ksw_evo_id7_main_interface_index
            if (r3 != r9) goto L_0x0430
            r1.iMainFocusIndex = r11
            goto L_0x044b
        L_0x0430:
            r1.iMainFocusIndex = r13
            goto L_0x044b
        L_0x0433:
            int r3 = r1.m_iModeSet
            r7 = 33
            if (r3 != r7) goto L_0x043c
            r1.iMainFocusIndex = r5
            goto L_0x044b
        L_0x043c:
            int r3 = r1.m_iUIIndex
            if (r3 != r5) goto L_0x0445
            r3 = 8
            r1.iMainFocusIndex = r3
            goto L_0x044b
        L_0x0445:
            int r3 = r1.m_iModeSet
            if (r3 != r4) goto L_0x044b
            r1.iMainFocusIndex = r15
        L_0x044b:
            com.szchoiceway.customerui.fragment.launcher.strategy.ILauncherStrategy r3 = r1.launcherStrategy
            if (r3 == 0) goto L_0x045d
            int r3 = r3.getDashBoardMainFocusIndex()
            if (r3 == r0) goto L_0x045d
            com.szchoiceway.customerui.fragment.launcher.strategy.ILauncherStrategy r0 = r1.launcherStrategy
            int r0 = r0.getDashBoardMainFocusIndex()
            r1.iMainFocusIndex = r0
        L_0x045d:
            int r0 = r1.m_iModeSet
            if (r0 != r6) goto L_0x0464
            r3 = 0
            r1.iMainFocusIndex = r3
        L_0x0464:
            android.content.Context r0 = r1.mContext
            com.szchoiceway.customerui.utils.EventUtils.onEnterDashBoard(r0)
            goto L_0x0062
        L_0x046b:
            r1.iMainFocusIndex = r5
            int r3 = r1.m_iModeSet
            r4 = 33
            if (r3 != r4) goto L_0x0477
            r3 = 8
            r1.iMainFocusIndex = r3
        L_0x0477:
            com.szchoiceway.customerui.fragment.launcher.strategy.ILauncherStrategy r3 = r1.launcherStrategy
            if (r3 == 0) goto L_0x0489
            int r3 = r3.getCarPlayMainFocusIndex()
            if (r3 == r0) goto L_0x0489
            com.szchoiceway.customerui.fragment.launcher.strategy.ILauncherStrategy r0 = r1.launcherStrategy
            int r0 = r0.getCarPlayMainFocusIndex()
            r1.iMainFocusIndex = r0
        L_0x0489:
            int r0 = r1.m_iModeSet
            if (r0 != r6) goto L_0x048f
            r1.iMainFocusIndex = r5
        L_0x048f:
            r16.enterCarplay()
            goto L_0x0062
        L_0x0494:
            java.lang.String r0 = "TransmitSetPage"
            r1.enterBtWithExtra(r0)
            goto L_0x0062
        L_0x049b:
            java.lang.String r0 = "CallRecordPage"
            r1.enterBtWithExtra(r0)
            goto L_0x0062
        L_0x04a2:
            int r0 = r1.m_iUIIndex
            if (r0 != r5) goto L_0x04aa
            r3 = 0
            r1.iMainFocusIndex = r3
            goto L_0x04ad
        L_0x04aa:
            r0 = 2
            r1.iMainFocusIndex = r0
        L_0x04ad:
            r16.enterBT()
            goto L_0x0062
        L_0x04b2:
            java.lang.String r0 = "BTMusic"
            r1.enterBtWithExtra(r0)
            goto L_0x0062
        L_0x04b9:
            java.lang.String r0 = "PhoneBookPage"
            r1.enterBtWithExtra(r0)
            goto L_0x0062
        L_0x04c0:
            int r3 = r1.m_iUITypeVer
            if (r3 != r12) goto L_0x0509
            int r3 = r1.m_iModeSet
            if (r3 == 0) goto L_0x0505
            java.lang.String r3 = r1.xml_client
            boolean r3 = r10.equals(r3)
            if (r3 == 0) goto L_0x04d9
            int r3 = r1.m_iModeSet
            if (r3 == r9) goto L_0x0505
            int r3 = r1.m_iModeSet
            if (r3 != r11) goto L_0x04d9
            goto L_0x0505
        L_0x04d9:
            boolean r3 = r16.isDefaultUI()
            if (r3 == 0) goto L_0x04ec
            com.szchoiceway.customerui.model.LauncherModel r3 = r1.model
            int r3 = r3.m_i_ksw_evo_id7_main_interface_index
            if (r3 != r9) goto L_0x04e9
            r3 = 2
            r1.iMainFocusIndex = r3
            goto L_0x0509
        L_0x04e9:
            r1.iMainFocusIndex = r11
            goto L_0x0509
        L_0x04ec:
            r3 = 2
            int r7 = r1.m_iModeSet
            if (r7 != r4) goto L_0x04f4
            r1.iMainFocusIndex = r3
            goto L_0x0509
        L_0x04f4:
            int r4 = r1.m_iModeSet
            r7 = 33
            if (r4 != r7) goto L_0x04fd
            r1.iMainFocusIndex = r3
            goto L_0x0509
        L_0x04fd:
            int r3 = r1.m_iUIIndex
            if (r3 != r5) goto L_0x0509
            r3 = 0
            r1.iMainFocusIndex = r3
            goto L_0x050a
        L_0x0505:
            r3 = 0
            r1.iMainFocusIndex = r15
            goto L_0x050a
        L_0x0509:
            r3 = 0
        L_0x050a:
            com.szchoiceway.customerui.fragment.launcher.strategy.ILauncherStrategy r4 = r1.launcherStrategy
            if (r4 == 0) goto L_0x051c
            int r4 = r4.getBtMainFocusIndex()
            if (r4 == r0) goto L_0x051c
            com.szchoiceway.customerui.fragment.launcher.strategy.ILauncherStrategy r0 = r1.launcherStrategy
            int r0 = r0.getBtMainFocusIndex()
            r1.iMainFocusIndex = r0
        L_0x051c:
            int r0 = r1.m_iModeSet
            if (r0 != r6) goto L_0x0522
            r1.iMainFocusIndex = r14
        L_0x0522:
            int r0 = r1.m_iModeSet
            r4 = 39
            if (r0 != r4) goto L_0x052f
            java.lang.String r0 = "SetPage"
            r1.enterBtWithExtra(r0)
            goto L_0x05bd
        L_0x052f:
            r16.enterBT()
            goto L_0x05bd
        L_0x0534:
            r3 = 0
            r16.enterBrowser()
            r4 = 11
            r1.iMainFocusIndex = r4
            com.szchoiceway.customerui.fragment.launcher.strategy.ILauncherStrategy r4 = r1.launcherStrategy
            if (r4 == 0) goto L_0x05bd
            int r4 = r4.getBrowserMainFocusIndex()
            if (r4 == r0) goto L_0x05bd
            com.szchoiceway.customerui.fragment.launcher.strategy.ILauncherStrategy r0 = r1.launcherStrategy
            int r0 = r0.getBrowserMainFocusIndex()
            r1.iMainFocusIndex = r0
            goto L_0x05bd
        L_0x0550:
            r3 = 0
            android.content.Context r0 = r1.mContext
            java.lang.String r4 = "com.szchoiceway.ksw_aux"
            java.lang.String r5 = "com.szchoiceway.ksw_aux.MainActivity"
            com.szchoiceway.customerui.utils.EventUtils.startActivityIfNotRunning(r0, r4, r5)
            int r0 = r1.m_iModeSet
            if (r0 != r6) goto L_0x05bd
            r1.iMainFocusIndex = r15
            goto L_0x05bd
        L_0x0561:
            r3 = 0
            int r4 = r1.m_iModeSet
            r7 = 33
            if (r4 != r7) goto L_0x056b
            r1.iMainFocusIndex = r15
            goto L_0x0574
        L_0x056b:
            int r4 = r1.m_iUIIndex
            if (r4 != r5) goto L_0x0572
            r1.iMainFocusIndex = r14
            goto L_0x0574
        L_0x0572:
            r1.iMainFocusIndex = r5
        L_0x0574:
            com.szchoiceway.customerui.fragment.launcher.strategy.ILauncherStrategy r4 = r1.launcherStrategy
            if (r4 == 0) goto L_0x0586
            int r4 = r4.getAppsMainFocusIndex()
            if (r4 == r0) goto L_0x0586
            com.szchoiceway.customerui.fragment.launcher.strategy.ILauncherStrategy r4 = r1.launcherStrategy
            int r4 = r4.getAppsMainFocusIndex()
            r1.iMainFocusIndex = r4
        L_0x0586:
            int r4 = r1.m_iModeSet
            if (r4 != r6) goto L_0x058c
            r1.iMainFocusIndex = r0
        L_0x058c:
            com.szchoiceway.customerui.model.LauncherModel r0 = r1.model
            r0.showAppList(r9)
            goto L_0x05bd
        L_0x0592:
            r3 = 0
            r16.showAirWnd()
            com.szchoiceway.customerui.fragment.launcher.strategy.ILauncherStrategy r4 = r1.launcherStrategy
            if (r4 == 0) goto L_0x05bd
            int r4 = r4.getAirMainFocusIndex()
            if (r4 == r0) goto L_0x05bd
            com.szchoiceway.customerui.fragment.launcher.strategy.ILauncherStrategy r0 = r1.launcherStrategy
            int r0 = r0.getAirMainFocusIndex()
            r1.iMainFocusIndex = r0
            goto L_0x05bd
        L_0x05a9:
            r3 = 0
            com.szchoiceway.customerui.dialog.BenzControlDialog r0 = r1.benzControlDialog
            if (r0 == 0) goto L_0x05bd
            boolean r0 = r0.isShow
            if (r0 == 0) goto L_0x05b8
            com.szchoiceway.customerui.dialog.BenzControlDialog r0 = r1.benzControlDialog
            r0.hideDialog()
            goto L_0x05bd
        L_0x05b8:
            com.szchoiceway.customerui.dialog.BenzControlDialog r0 = r1.benzControlDialog
            r0.showDialog()
        L_0x05bd:
            int r0 = r1.m_iUIIndex
            if (r0 == r15) goto L_0x05c7
            int r0 = r1.m_iModeSet
            r4 = 26
            if (r0 != r4) goto L_0x05f9
        L_0x05c7:
            android.view.View[] r0 = r1.mLeftButtons
            if (r0 == 0) goto L_0x05df
            r0 = r3
        L_0x05cc:
            android.view.View[] r4 = r1.mLeftButtons
            int r5 = r4.length
            if (r0 >= r5) goto L_0x05df
            r4 = r4[r0]
            if (r2 != r4) goto L_0x05dc
            com.szchoiceway.customerui.model.LauncherModel r4 = r1.model
            r4.bInLeftFocus = r9
            r1.iLeftFocusIndex = r0
            goto L_0x05df
        L_0x05dc:
            int r0 = r0 + 1
            goto L_0x05cc
        L_0x05df:
            com.szchoiceway.customerui.model.LauncherModel r0 = r1.model
            boolean r0 = r0.bInLeftFocus
            if (r0 != 0) goto L_0x05f9
            android.view.View[] r0 = r1.mMainButtons
            if (r0 == 0) goto L_0x05f9
            r6 = r3
        L_0x05ea:
            android.view.View[] r0 = r1.mMainButtons
            int r3 = r0.length
            if (r6 >= r3) goto L_0x05f9
            r0 = r0[r6]
            if (r2 != r0) goto L_0x05f6
            r1.iMainFocusIndex = r6
            goto L_0x05f9
        L_0x05f6:
            int r6 = r6 + 1
            goto L_0x05ea
        L_0x05f9:
            r16.refreshLeftFocus()
            int r0 = r1.m_iUIIndex
            if (r0 != r15) goto L_0x0608
            androidx.recyclerview.widget.RecyclerView r0 = r1.mRecyclerView
            if (r0 == 0) goto L_0x0608
            r16.refreshMainFocusOnClick()
            goto L_0x060b
        L_0x0608:
            r16.refreshMainFocus()
        L_0x060b:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.szchoiceway.customerui.fragment.MainFragment.onClick(android.view.View):void");
    }

    public boolean onLongClick(View view) {
        this.model.bInLeftFocus = true;
        this.iLeftFocusIndex = -1;
        this.iMainFocusIndex = -1;
        switch (view.getId()) {
            case R.id.btnLeftApp0:
                this.iLeftFocusIndex = 2;
                AppListDialog appListDialog2 = this.appListDialog;
                if (appListDialog2 != null) {
                    if (appListDialog2.leftAppIndex != 0) {
                        this.appListDialog.hideDialog();
                    }
                    this.appListDialog.showDialog(0);
                    break;
                }
                break;
            case R.id.btnLeftApp1:
                this.iLeftFocusIndex = 3;
                AppListDialog appListDialog3 = this.appListDialog;
                if (appListDialog3 != null) {
                    if (appListDialog3.leftAppIndex != 1) {
                        this.appListDialog.hideDialog();
                    }
                    this.appListDialog.showDialog(1);
                    break;
                }
                break;
            case R.id.btnLeftApp2:
                this.iLeftFocusIndex = 4;
                AppListDialog appListDialog4 = this.appListDialog;
                if (appListDialog4 != null) {
                    if (appListDialog4.leftAppIndex != 2) {
                        this.appListDialog.hideDialog();
                    }
                    this.appListDialog.showDialog(2);
                    break;
                }
                break;
        }
        refreshLeftFocus();
        refreshMainFocus();
        return true;
    }

    private void showAirWnd() {
        try {
            LauncherModel.getInstance().getEvtService().showLexusAirWnd();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void hideAirWnd() {
        try {
            LauncherModel.getInstance().getEvtService().hideLexusAirWnd();
        } catch (Exception unused) {
        }
    }

    public void onPageState(int i) {
        boolean z = true;
        if (i != 1) {
            z = false;
        }
        this.pageChange = z;
    }

    public void onPageChange(int i) {
        int i2;
        int i3 = this.iLastMainPageIndex;
        if (i3 != i) {
            int i4 = this.iMainFocusIndex;
            if (i4 != -1) {
                if (i3 > i) {
                    i2 = (this.iPagerItemViewItemCount * i3) - 1;
                } else {
                    i2 = this.iPagerItemViewItemCount * i;
                }
                if (i2 != i4) {
                    this.iMainFocusIndex = i2;
                    refreshMainFocus();
                }
            }
            refreshIconPagingIndex(i);
            this.iLastMainPageIndex = i;
            this.iLastMainFocusIndex = i * this.iPagerItemViewItemCount;
        }
    }

    private void refreshBenzButtonsTheme() {
        if (this.mRecyclerView != null) {
            for (String str : this.itemUtil.getMainItemTags()) {
                ItemUtil.mapTagItem.get(str).updateInfo();
            }
            return;
        }
        View[] viewArr = this.mMainButtons;
        if (viewArr != null) {
            for (View view : viewArr) {
                if (view instanceof MyImageButton) {
                    ((MyImageButton) view).init();
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public void refreshMainBackground() {
        if (this.mMainBg != null) {
            int i = this.mMainBgIndex;
            if (i < 0 || i > this.mMainBgDrawables.length) {
                this.mMainBgIndex = 0;
            }
            if (this.m_iModeSet == 21 || this.m_iModeSet == 31) {
                int i2 = this.currentPageIndex;
                if (i2 != 0) {
                    if (i2 != 1) {
                        if (i2 == 2) {
                            ImageView imageView = this.mMainFg;
                            if (imageView != null) {
                                imageView.setVisibility(8);
                            }
                            this.mMainBg.setBackgroundResource(this.mMainBgDrawables[this.mMainBgIndex]);
                        }
                    } else if (this.mBlurMusicCoverDrawable == null) {
                        ImageView imageView2 = this.mMainFg;
                        if (imageView2 != null) {
                            imageView2.setVisibility(8);
                        }
                        this.mMainBg.setBackgroundResource(this.mMainBgDrawables[this.mMainBgIndex]);
                    } else {
                        ImageView imageView3 = this.mMainFg;
                        if (imageView3 != null) {
                            imageView3.setVisibility(0);
                        }
                        this.mMainBg.setBackground(this.mBlurMusicCoverDrawable);
                    }
                } else if (this.mMainBgIndex < this.mBenzNaviBgDrawables.length) {
                    ImageView imageView4 = this.mMainFg;
                    if (imageView4 != null) {
                        imageView4.setVisibility(8);
                    }
                    this.mMainBg.setBackgroundResource(this.mBenzNaviBgDrawables[this.mMainBgIndex]);
                }
            } else {
                this.mMainBg.setBackgroundResource(this.mMainBgDrawables[this.mMainBgIndex]);
            }
        }
    }

    public void setTheme() {
        refreshBenzButtonsTheme();
    }

    public void setMainBackground() {
        this.mMainBgIndex = this.mProvider.getRecordInteger(SysProviderOpt.KSW_BENZ_THEME_BACKGROUND_INDEX, 0);
        refreshMainBackground();
    }

    public void setLandRoverBackground() {
        Log.d(TAG, "setLandRoverBackground send ZXW_ACTION_KSW_THEME_CHANGE");
        this.mContext.sendBroadcast(new Intent("ZXW_ACTION_KSW_THEME_CHANGE"));
        if (this.m_iModeSet == 32 || this.m_iModeSet == 39) {
            setLandRoverTheme();
        } else {
            setLandRoverBg();
        }
    }

    private void setLandRoverTheme() {
        super.setTheme();
        this.mViewPager.setCurrentItem(this.currentPageIndex);
        refreshView();
        View view = this.mBtnMusicPlayPause;
        if (view != null) {
            view.setBackgroundResource(getAttrId(this.mValidPlayStatus == 1 ? R.attr.landrover_main_btn_music_pause : R.attr.landrover_main_btn_music_play));
        }
        refreshBTConnectName(this.mConnectBtName);
    }

    private void setLandRoverBg() {
        int recordInteger = SysProviderOpt.getInstance(this.mContext).getRecordInteger("KSW_LANDROVER_THEME_BACKGROUND_INDEX", 0);
        ImageView imageView = this.imgLandroverBg;
        if (imageView != null) {
            imageView.setBackgroundResource(recordInteger == 0 ? R.drawable.landrover_main_bk1 : R.drawable.landrover_main_bk2_black);
        }
    }

    public void hideDialog() {
        ThemeDialog themeDialog2 = this.themeDialog;
        if (themeDialog2 != null && themeDialog2.getIsShow()) {
            this.themeDialog.hideThemeDialog();
        }
        BenzControlDialog benzControlDialog2 = this.benzControlDialog;
        if (benzControlDialog2 != null && benzControlDialog2.isShow) {
            this.benzControlDialog.hideDialog();
        }
        AppListDialog appListDialog2 = this.appListDialog;
        if (appListDialog2 != null && appListDialog2.isShow) {
            this.appListDialog.hideDialog();
        }
        hideAirWnd();
    }

    public void onClickRecentTask() {
        hideAirWnd();
    }

    private Activity getActivity() {
        View view;
        do {
            Context context = this.getContext();
            this = this;
            if (context != null && (context instanceof Activity)) {
                return (Activity) context;
            }
            view = (View) this.getParent();
            this = view;
        } while (view != null);
        return null;
    }

    public void refreshWeatherView() {
        this.language = Locale.getDefault().getLanguage();
        this.temperUnit = SysProviderOpt.getInstance(this.mContext).getRecordInteger(SysProviderOpt.KSW_TEMP_UNIT, 0) == 0 ? "c" : "f";
        if (this.weatherQueryManager == null) {
            this.weatherQueryManager = WeatherQueryManager.getInstance();
        }
        try {
            this.weatherQueryManager.sendWeatherRequest(this.mActivity, new WeatherQueryManager.WeatherCallback() {
                public void onSuccess(TXZWeather tXZWeather, Bundle bundle) {
                    if (MainFragment.this.mLayoutWeatherContent != null) {
                        MainFragment.this.mLayoutWeatherContent.setVisibility(0);
                    }
                    if (MainFragment.this.mLayoutWeatherStatus != null) {
                        MainFragment.this.mLayoutWeatherStatus.setVisibility(8);
                    }
                    if (tXZWeather.getCity() != null) {
                        MainFragment.this.mWeatherDetailList.clear();
                        MainFragment.this.mWeatherDetailMap.clear();
                        if (bundle != null) {
                            try {
                                if (bundle.get("details") instanceof String[]) {
                                    for (String str : (String[]) bundle.get("details")) {
                                        if (!"3".equals(str)) {
                                            MainFragment.this.mWeatherDetailList.add(str);
                                        } else if (tXZWeather.getSnowingProbability() == null || "".equals(tXZWeather.getSnowingProbability())) {
                                            MainFragment.this.mWeatherDetailList.add("3-rain");
                                        } else {
                                            MainFragment.this.mWeatherDetailList.add("3-snow");
                                        }
                                    }
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                        MainFragment.this.mWeatherDetailMap.put("0", tXZWeather.getWindSpeed());
                        MainFragment.this.mWeatherDetailMap.put("1", tXZWeather.getHumidity());
                        MainFragment.this.mWeatherDetailMap.put("2", tXZWeather.getVisibility());
                        if (tXZWeather.getSnowingProbability() != null && !"".equals(tXZWeather.getSnowingProbability())) {
                            MainFragment.this.mWeatherDetailMap.put("3-snow", tXZWeather.getSnowingProbability());
                        } else if (tXZWeather.getSnowingProbability() == null || "".equals(tXZWeather.getRainingProbability()) || "%".equals(tXZWeather.getRainingProbability())) {
                            MainFragment.this.mWeatherDetailMap.put("3-rain", "0%");
                        } else {
                            MainFragment.this.mWeatherDetailMap.put("3-rain", tXZWeather.getRainingProbability());
                        }
                        MainFragment.this.mWeatherDetailMap.put("4", tXZWeather.getUvIndex());
                        if (!(MainFragment.this.mViewWeatherDetails == null || MainFragment.this.mTvWeatherDetails == null || MainFragment.this.mImgWeatherDetails == null)) {
                            for (int i = 0; i < 3; i++) {
                                View view = (View) MainFragment.this.mViewWeatherDetails.get(i);
                                ImageView imageView = (ImageView) MainFragment.this.mImgWeatherDetails.get(i);
                                TextView textView = (TextView) MainFragment.this.mTvWeatherDetails.get(i);
                                if (i < MainFragment.this.mWeatherDetailList.size()) {
                                    if (view != null) {
                                        view.setVisibility(0);
                                    }
                                    if (imageView != null) {
                                        imageView.setBackground(WeatherIconUtil.getWeatherCardDetailIcon(MainFragment.this.mContext, (String) MainFragment.this.mWeatherDetailList.get(i), false));
                                    }
                                    if (textView != null) {
                                        textView.setText((CharSequence) MainFragment.this.mWeatherDetailMap.get(MainFragment.this.mWeatherDetailList.get(i)));
                                    }
                                } else if (view != null) {
                                    view.setVisibility(8);
                                }
                            }
                        }
                        if (MainFragment.this.mWeatherCity != null) {
                            MainFragment.this.mWeatherCity.setText(tXZWeather.getCity());
                        }
                        if (MainFragment.this.mWeatherContent != null) {
                            MainFragment.this.mWeatherContent.setText(tXZWeather.getPhrase());
                        }
                        if (MainFragment.this.gt6Audifangxiang_tianqi_tv != null) {
                            MainFragment.this.gt6Audifangxiang_tianqi_tv.setText(tXZWeather.getPhrase());
                        }
                        if (MainFragment.this.mWeatherPic != null) {
                            MainFragment.this.mWeatherPic.setBackground(MainFragment.this.mContext.getDrawable(WeatherIconUtil.getWeatherPicID(tXZWeather.getPhraseID(), MainFragment.this.m_iModeSet, MainFragment.this.m_iUIIndex, SysProviderOpt.getInstance(MainFragment.this.mContext).getRecordInteger("KSW_LANDROVER_THEME_BACKGROUND_INDEX", 0))));
                        }
                        if (MainFragment.this.mValueTemp != null) {
                            MainFragment.this.mValueTemp.setText(tXZWeather.getTemperature());
                        }
                        if (MainFragment.this.mUnitTemp != null) {
                            MainFragment.this.mUnitTemp.setText(tXZWeather.getTemperUnit());
                        }
                        if (MainFragment.this.mRangeTemp != null) {
                            MainFragment.this.mRangeTemp.setText(tXZWeather.getMinTemperature() + "~" + tXZWeather.getMaxTemperature() + tXZWeather.getTemperUnit());
                        }
                        if (MainFragment.this.gt6Audifangxiang_wendu_tv != null) {
                            MainFragment.this.gt6Audifangxiang_wendu_tv.setText(tXZWeather.getMinTemperature() + tXZWeather.getTemperUnit() + "~" + tXZWeather.getMaxTemperature() + tXZWeather.getTemperUnit());
                        }
                        if (MainFragment.this.mGt6AudiRangeTemp != null) {
                            MainFragment.this.mGt6AudiRangeTemp.setText(tXZWeather.getMinTemperature() + "~" + tXZWeather.getMaxTemperature() + tXZWeather.getTemperUnit());
                        }
                    }
                }

                public void onFailed(int i) {
                    String str;
                    int i2 = 8;
                    if (MainFragment.this.mLayoutWeatherContent != null) {
                        MainFragment.this.mLayoutWeatherContent.setVisibility(i == 0 ? 0 : 8);
                    }
                    if (MainFragment.this.mLayoutWeatherStatus != null) {
                        View access$8700 = MainFragment.this.mLayoutWeatherStatus;
                        if (i != 0) {
                            i2 = 0;
                        }
                        access$8700.setVisibility(i2);
                    }
                    if (MainFragment.this.mImgWeatherStatus != null) {
                        int i3 = MainFragment.this.m_iModeSet;
                        int i4 = R.drawable.id8_main_icon_weather_error;
                        if (i3 == 32) {
                            int recordInteger = SysProviderOpt.getInstance(MainFragment.this.mContext).getRecordInteger("KSW_LANDROVER_THEME_BACKGROUND_INDEX", 0);
                            ImageView access$10500 = MainFragment.this.mImgWeatherStatus;
                            if (recordInteger != 0) {
                                i4 = R.drawable.id8_main_icon_weather_error_white;
                            }
                            access$10500.setBackgroundResource(i4);
                        } else {
                            MainFragment.this.mImgWeatherStatus.setBackgroundResource(R.drawable.id8_main_icon_weather_error);
                        }
                    }
                    if (MainFragment.this.mTvWeatherStatus != null) {
                        if (i == 3) {
                            str = MainFragment.this.mContext.getString(R.string.lb_weather_result_failed);
                        } else if (i != 4) {
                            str = i != 5 ? MainFragment.this.mContext.getString(R.string.lb_weather_result_loading) : MainFragment.this.mContext.getString(R.string.lb_weather_result_no_gps);
                        } else {
                            str = MainFragment.this.mContext.getString(R.string.lb_weather_result_unactivated);
                        }
                        MainFragment.this.mTvWeatherStatus.setText(str);
                    }
                    Log.d(MainFragment.TAG, "onFailed txzWeather errorCode = " + i);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public IEventService getEvtService() {
        return this.mEvtService;
    }

    /* access modifiers changed from: private */
    public void refreshView() {
        if (BluetoothAdapter.getDefaultAdapter().isEnabled()) {
            LauncherModel.getInstance().btConnectStatus = 0;
            refreshBTMessage();
            try {
                if (this.mEvtService.GetBTStatus() >= 3) {
                    LauncherModel.getInstance().btConnectStatus = 1;
                    refreshBTMessage();
                }
                this.mEvtService.sendMcuData_KSW(new byte[]{-14, 0, EventUtils.MCU_KEY1_3, 2, 5, 0});
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        } else {
            LauncherModel.getInstance().btConnectStatus = -1;
            refreshBTMessage();
        }
        refreshPlayState();
    }

    /* access modifiers changed from: private */
    public void enterMusic() {
        EventUtils.startActivityType(0, this.mContext, this.mEvtService);
    }

    private void enterVideo() {
        EventUtils.startActivityType(1, this.mContext, this.mEvtService);
    }

    /* access modifiers changed from: private */
    public void enterNavi() {
        EventUtils.startActivityType(2, this.mContext);
    }

    /* access modifiers changed from: private */
    public void enterBT() {
        EventUtils.startActivityType(3, this.mContext);
    }

    /* access modifiers changed from: private */
    public void enterOriginal() {
        EventUtils.startActivityType(4, this.mContext);
    }

    private void enterSettings() {
        EventUtils.startActivityType(5, this.mContext);
    }

    private void enterCarplay() {
        try {
            if (EventUtils.getInstallStatus(this.mContext, EventUtils.ZLINK_MODE_PACKAGE_NAME)) {
                EventUtils.startActivityType(7, this.mContext, this.mEvtService);
            } else {
                EventUtils.startActivityType(6, this.mContext, this.mEvtService);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* access modifiers changed from: private */
    public void enterWeather() {
        EventUtils.startActivityType(7, this.mContext);
    }

    private void enterDvr() {
        EventUtils.startActivityType(8, this.mContext);
    }

    /* access modifiers changed from: private */
    public void enterDashboard() {
        EventUtils.startActivityType(9, this.mContext);
    }

    private void enterFile() {
        EventUtils.startActivityType(10, this.mContext);
    }

    private void enterBrowser() {
        EventUtils.startActivityType(11, this.mContext);
    }

    private void enterApplist() {
        this.model.showAppList(true);
    }

    private void enterBtWithExtra(String str) {
        if (this.mProvider.getRecordInteger("KESAIWEI_RECORD_BT_INDEX", 0) == 1) {
            this.mContext.sendBroadcast(new Intent("com.szchoiceway.eventcenter.EventUtils.ACTION_SWITCH_ORIGINACAR"));
            return;
        }
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putString("GotoPageNum", str);
        intent.putExtras(bundle);
        intent.addCategory("android.intent.category.LAUNCHER");
        intent.setComponent(new ComponentName("com.szchoiceway.btsuite", "com.szchoiceway.btsuite.BTMainActivity"));
        intent.setFlags(270532608);
        this.mContext.startActivity(intent);
    }

    public void playPrev() {
        long currentTimeMillis = System.currentTimeMillis();
        if (Math.abs(currentTimeMillis - this.clickPlayTime) >= 1000) {
            this.clickPlayTime = currentTimeMillis;
            Intent intent = new Intent("com.choiceway.eventcenter.EventUtils.ZXW_CAN_KEY_EVT");
            intent.putExtra("com.choiceway.eventcenter.EventUtils.ZXW_CAN_KEY_EVT_EXTRA", 3);
            this.mContext.sendBroadcast(intent);
        }
    }

    public void playNext() {
        long currentTimeMillis = System.currentTimeMillis();
        if (Math.abs(currentTimeMillis - this.clickPlayTime) >= 1000) {
            this.clickPlayTime = currentTimeMillis;
            Intent intent = new Intent("com.choiceway.eventcenter.EventUtils.ZXW_CAN_KEY_EVT");
            intent.putExtra("com.choiceway.eventcenter.EventUtils.ZXW_CAN_KEY_EVT_EXTRA", 2);
            this.mContext.sendBroadcast(intent);
        }
    }

    /* access modifiers changed from: private */
    public void playPause() {
        long currentTimeMillis = System.currentTimeMillis();
        if (Math.abs(currentTimeMillis - this.clickPlayTime) >= 1000) {
            this.clickPlayTime = currentTimeMillis;
            Intent intent = new Intent("com.choiceway.eventcenter.EventUtils.ZXW_CAN_KEY_EVT");
            intent.putExtra("com.choiceway.eventcenter.EventUtils.ZXW_CAN_KEY_EVT_EXTRA", 6);
            this.mContext.sendBroadcast(intent);
        }
    }

    public void refreshBenzControl(ArrayList<Integer> arrayList) {
        this.benzControlDialog.refreshBenzControl(arrayList);
    }

    public void refreshDateTime() {
        Calendar instance = Calendar.getInstance();
        int i = instance.get(5);
        int i2 = instance.get(2) + 1;
        TextView textView = this.tvDateDay;
        String str = "";
        if (textView != null) {
            textView.setText(i + str);
        }
        switch (i2) {
            case 1:
                str = getResources().getString(R.string.lb_month1);
                break;
            case 2:
                str = getResources().getString(R.string.lb_month2);
                break;
            case 3:
                str = getResources().getString(R.string.lb_month3);
                break;
            case 4:
                str = getResources().getString(R.string.lb_month4);
                break;
            case 5:
                str = getResources().getString(R.string.lb_month5);
                break;
            case 6:
                str = getResources().getString(R.string.lb_month6);
                break;
            case 7:
                str = getResources().getString(R.string.lb_month7);
                break;
            case 8:
                str = getResources().getString(R.string.lb_month8);
                break;
            case 9:
                str = getResources().getString(R.string.lb_month9);
                break;
            case 10:
                str = getResources().getString(R.string.lb_month10);
                break;
            case 11:
                str = getResources().getString(R.string.lb_month11);
                break;
            case 12:
                str = getResources().getString(R.string.lb_month12);
                break;
        }
        TextView textView2 = this.tvDateMonth;
        if (textView2 != null) {
            textView2.setText(str);
        }
    }

    public void refreshLeftApp(int i) {
        if (i == 0) {
            refreshPempLeftApp0();
        } else if (i == 1) {
            refreshPempLeftApp1();
        } else if (i == 2) {
            refreshPempLeftApp2();
        }
    }

    private void refreshLeftFocus() {
        View[] viewArr = this.mLeftButtons;
        if (viewArr != null && viewArr.length != 0) {
            int i = 0;
            while (true) {
                View[] viewArr2 = this.mLeftButtons;
                if (i < viewArr2.length) {
                    if (viewArr2[i] != null) {
                        if (isDefaultUI()) {
                            this.mLeftButtons[i].setVisibility(i == this.iLeftFocusIndex ? 0 : 8);
                        } else {
                            this.mLeftButtons[i].setSelected(i == this.iLeftFocusIndex);
                        }
                    }
                    i++;
                } else {
                    return;
                }
            }
        }
    }

    public void refreshMainFocus() {
        ImageView imageView;
        AnimationDrawable animationDrawable;
        ItemUtil itemUtil2;
        int i;
        if (this.mRecyclerView == null || (itemUtil2 = this.itemUtil) == null) {
            View[] viewArr = this.mMainButtons;
            if (viewArr != null && viewArr.length != 0) {
                int i2 = 0;
                while (true) {
                    View[] viewArr2 = this.mMainButtons;
                    if (i2 >= viewArr2.length) {
                        break;
                    }
                    if (viewArr2[i2] != null) {
                        if (isDefaultUI()) {
                            this.mMainButtons[i2].setVisibility(i2 == this.iMainFocusIndex ? 0 : 8);
                        } else {
                            this.mMainButtons[i2].setSelected(i2 == this.iMainFocusIndex);
                        }
                    }
                    JavaStandard.runIfNonNull(this.mainFocusFrameViews, new Function1Void(i2) {
                        public final /* synthetic */ int f$1;

                        {
                            this.f$1 = r2;
                        }

                        public final void invoke(Object obj) {
                            MainFragment.this.lambda$refreshMainFocus$17$MainFragment(this.f$1, (View[]) obj);
                        }
                    });
                    i2++;
                }
                if (this.m_iModeSet == 23 || this.m_iModeSet == 24) {
                    if (this.iMainFocusIndex >= 0) {
                        this.handler.removeCallbacks(this.buttonRunnable);
                        this.handler.postDelayed(this.buttonRunnable, 300);
                    }
                    int i3 = this.lastImgIndex;
                    if (!(i3 < 0 || i3 == this.iMainFocusIndex || (imageView = this.imgViews.get(i3)) == null || (animationDrawable = (AnimationDrawable) imageView.getBackground()) == null)) {
                        animationDrawable.stop();
                    }
                }
                int i4 = this.iMainFocusIndex;
                this.lastImgIndex = i4;
                MyViewPager myViewPager = this.mViewPager;
                if (myViewPager != null && i4 >= 0) {
                    myViewPager.setCurrentItem(i4 / this.iPagerItemViewItemCount);
                }
            }
        } else if (this.iMainFocusIndex < itemUtil2.getAllItemTags().size()) {
            PagingScrollHelper pagingScrollHelper = this.mScrollHelper;
            if (pagingScrollHelper != null && (i = this.iMainFocusIndex) >= 0) {
                pagingScrollHelper.scrollToPosition(i / this.iPagerItemViewItemCount);
            }
            MainAdapter mainAdapter = this.mMainAdapter;
            if (mainAdapter != null) {
                mainAdapter.setFocusPosition(this.iMainFocusIndex);
            }
            MainAdapter mainAdapter2 = this.mMainAdapterEdit;
            if (mainAdapter2 != null) {
                mainAdapter2.setFocusPosition(this.iMainFocusIndex);
            }
        }
    }

    public /* synthetic */ void lambda$refreshMainFocus$17$MainFragment(int i, View[] viewArr) {
        JavaStandard.runIfNonNull((View) ArraysUtil.getOrNull(viewArr, i), new Function1Void(i) {
            public final /* synthetic */ int f$1;

            {
                this.f$1 = r2;
            }

            public final void invoke(Object obj) {
                MainFragment.this.lambda$refreshMainFocus$16$MainFragment(this.f$1, (View) obj);
            }
        });
    }

    public /* synthetic */ void lambda$refreshMainFocus$16$MainFragment(int i, View view) {
        view.setVisibility(i == this.iMainFocusIndex ? 0 : 8);
    }

    private void refreshMainCheckedAudi() {
        int i = this.iMainFocusIndex;
        if (i >= 0) {
            ((PressedView) this.mMainButtons[i]).setChecked(true);
        }
        int i2 = this.lastCheckedIndex;
        if (i2 >= 0 && i2 != this.iMainFocusIndex) {
            ((PressedView) this.mMainButtons[i2]).setChecked(false);
        }
        this.lastCheckedIndex = this.iMainFocusIndex;
    }

    private void refreshMainFocusOnClick() {
        ImageView imageView;
        AnimationDrawable animationDrawable;
        ImageView imageView2;
        AnimationDrawable animationDrawable2;
        refreshMainCheckedAudi();
        int i = this.iMainFocusIndex;
        if (!(i < 0 || (imageView2 = this.imgViews.get(i)) == null || (animationDrawable2 = (AnimationDrawable) imageView2.getBackground()) == null)) {
            animationDrawable2.start();
        }
        int i2 = this.lastImgIndex;
        if (!(i2 < 0 || i2 == this.iMainFocusIndex || (imageView = this.imgViews.get(i2)) == null || (animationDrawable = (AnimationDrawable) imageView.getBackground()) == null)) {
            animationDrawable.stop();
        }
        this.lastImgIndex = this.iMainFocusIndex;
    }

    public void refreshMainFocusRecyclerView(int i) {
        if (this.iMainFocusIndex != i) {
            int size = this.itemUtil.getAllItemTags().size();
            if (i >= 0 && i < size) {
                this.iMainFocusIndex = i;
                MainAdapter mainAdapter = this.mMainAdapter;
                if (mainAdapter != null) {
                    mainAdapter.setFocusPosition(i);
                }
                MainAdapter mainAdapter2 = this.mMainAdapterEdit;
                if (mainAdapter2 != null) {
                    mainAdapter2.setFocusPosition(i);
                }
            }
        }
    }

    private void enterLeftFocus() {
        int i = this.iLeftFocusIndex;
        View[] viewArr = this.mLeftButtons;
        if (i < viewArr.length && i >= 0) {
            viewArr[i].performClick();
        }
    }

    private void enterMainFocus() {
        if (this.mRecyclerView == null || this.itemUtil == null) {
            int i = this.iMainFocusIndex;
            View[] viewArr = this.mMainButtons;
            if (i < viewArr.length && i >= 0) {
                viewArr[i].performClick();
                return;
            }
            return;
        }
        MainAdapter mainAdapter = this.mMainAdapter;
        if (mainAdapter != null) {
            mainAdapter.getOnItemClickListener().onItemClick(this.iMainFocusIndex);
        }
    }

    private void refreshPempLeftApps() {
        refreshPempLeftApp0();
        refreshPempLeftApp1();
        refreshPempLeftApp2();
    }

    public void refreshPempLeftApp0() {
        String recordValue = this.mProvider.getRecordValue(EventUtils.LEFT_APP0_MODE_PACKAGE_NAME, "com.android.chrome");
        String recordValue2 = this.mProvider.getRecordValue(EventUtils.LEFT_APP0_MODE_CLASS_NAME, "com.google.android.apps.chrome.Main");
        boolean recordBoolean = SysProviderOpt.getInstance(this.mContext).getRecordBoolean(SysProviderOpt.MAISILUO_SYS_GOOGLEPLAY, false);
        if (!EventUtils.getInstallStatus(this.mContext, recordValue) || (isGoogleApp(recordValue) && !recordBoolean)) {
            Button button = this.btnLeftApp0;
            if (button != null) {
                button.setText(this.mContext.getResources().getString(R.string.lb_left_bro));
            }
            ImageView imageView = this.iconApp0;
            if (imageView != null) {
                imageView.setImageDrawable(ContextCompat.getDrawable(this.mContext, R.drawable.pemp_id7_icon_browser));
            }
            this.mProvider.updateRecord(EventUtils.LEFT_APP0_MODE_PACKAGE_NAME, "com.android.chrome");
            this.mProvider.updateRecord(EventUtils.LEFT_APP0_MODE_CLASS_NAME, "com.google.android.apps.chrome.Main");
            return;
        }
        for (AppInfo next : this.appInfoList) {
            if (recordValue.equals(next.getPackageName()) && recordValue2.equals(next.getClassName())) {
                Button button2 = this.btnLeftApp0;
                if (button2 != null) {
                    button2.setText(next.getLabel());
                }
                ImageView imageView2 = this.iconApp0;
                if (imageView2 != null) {
                    imageView2.setImageDrawable(next.getIcon());
                    return;
                }
                return;
            }
        }
    }

    public void refreshPempLeftApp1() {
        String recordValue = this.mProvider.getRecordValue(EventUtils.LEFT_APP1_MODE_PACKAGE_NAME, "com.szchoiceway.musicplayer");
        String recordValue2 = this.mProvider.getRecordValue(EventUtils.LEFT_APP1_MODE_CLASS_NAME, "com.szchoiceway.musicplayer.MainActivity");
        boolean recordBoolean = SysProviderOpt.getInstance(this.mContext).getRecordBoolean(SysProviderOpt.MAISILUO_SYS_GOOGLEPLAY, false);
        if (!EventUtils.getInstallStatus(this.mContext, recordValue) || (isGoogleApp(recordValue) && !recordBoolean)) {
            Button button = this.btnLeftApp1;
            if (button != null) {
                button.setText(this.mContext.getResources().getString(R.string.lb_left_music));
            }
            ImageView imageView = this.iconApp1;
            if (imageView != null) {
                imageView.setImageDrawable(ContextCompat.getDrawable(this.mContext, R.drawable.pemp_id7_icon_music));
            }
            this.mProvider.updateRecord(EventUtils.LEFT_APP1_MODE_PACKAGE_NAME, "com.szchoiceway.musicplayer");
            this.mProvider.updateRecord(EventUtils.LEFT_APP1_MODE_CLASS_NAME, "com.szchoiceway.musicplayer.MainActivity");
            return;
        }
        for (AppInfo next : this.appInfoList) {
            if (recordValue.equals(next.getPackageName()) && recordValue2.equals(next.getClassName())) {
                Button button2 = this.btnLeftApp1;
                if (button2 != null) {
                    button2.setText(next.getLabel());
                }
                ImageView imageView2 = this.iconApp1;
                if (imageView2 != null) {
                    imageView2.setImageDrawable(next.getIcon());
                    return;
                }
                return;
            }
        }
    }

    public void refreshPempLeftApp2() {
        String recordValue = this.mProvider.getRecordValue(EventUtils.LEFT_APP2_MODE_PACKAGE_NAME, "com.szchoiceway.videoplayer");
        String recordValue2 = this.mProvider.getRecordValue(EventUtils.LEFT_APP2_MODE_CLASS_NAME, "com.szchoiceway.videoplayer.MainActivity");
        boolean recordBoolean = SysProviderOpt.getInstance(this.mContext).getRecordBoolean(SysProviderOpt.MAISILUO_SYS_GOOGLEPLAY, false);
        if (!EventUtils.getInstallStatus(this.mContext, recordValue) || (isGoogleApp(recordValue) && !recordBoolean)) {
            Button button = this.btnLeftApp2;
            if (button != null) {
                button.setText(this.mContext.getResources().getString(R.string.lb_left_video));
            }
            ImageView imageView = this.iconApp2;
            if (imageView != null) {
                imageView.setImageDrawable(ContextCompat.getDrawable(this.mContext, R.drawable.pemp_id7_icon_video));
            }
            this.mProvider.updateRecord(EventUtils.LEFT_APP2_MODE_PACKAGE_NAME, "com.szchoiceway.videoplayer");
            this.mProvider.updateRecord(EventUtils.LEFT_APP2_MODE_CLASS_NAME, "com.szchoiceway.videoplayer.MainActivity");
            return;
        }
        for (AppInfo next : this.appInfoList) {
            if (recordValue.equals(next.getPackageName()) && recordValue2.equals(next.getClassName())) {
                Button button2 = this.btnLeftApp2;
                if (button2 != null) {
                    button2.setText(next.getLabel());
                }
                ImageView imageView2 = this.iconApp2;
                if (imageView2 != null) {
                    imageView2.setImageDrawable(next.getIcon());
                    return;
                }
                return;
            }
        }
    }

    public boolean isGoogleApp(String str) {
        return "com.google.android.gm".equals(str) || "com.android.vending".equals(str) || "com.google.android.apps.maps".equals(str) || "com.google.android.googlequicksearchbox".equals(str);
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x008d, code lost:
        if (r5.equals("Weather") == false) goto L_0x001c;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onItemClickByTag(java.lang.String r5) {
        /*
            r4 = this;
            com.szchoiceway.customerui.mianitem.ItemUtil r0 = r4.itemUtil
            if (r0 != 0) goto L_0x0005
            return
        L_0x0005:
            java.util.List r0 = r0.getMainItemTags()
            boolean r0 = r0.contains(r5)
            r1 = 1
            r2 = 0
            if (r0 == 0) goto L_0x00ca
            r5.hashCode()
            r0 = -1
            int r3 = r5.hashCode()
            switch(r3) {
                case -2076010264: goto L_0x0090;
                case -1406873644: goto L_0x0087;
                case 2162: goto L_0x007c;
                case 69120: goto L_0x0071;
                case 2420678: goto L_0x0066;
                case 74710533: goto L_0x005b;
                case 82650203: goto L_0x0050;
                case 870465087: goto L_0x0045;
                case 956107380: goto L_0x0039;
                case 1443687921: goto L_0x002c;
                case 1499275331: goto L_0x001f;
                default: goto L_0x001c;
            }
        L_0x001c:
            r1 = r0
            goto L_0x009a
        L_0x001f:
            java.lang.String r1 = "Settings"
            boolean r5 = r5.equals(r1)
            if (r5 != 0) goto L_0x0028
            goto L_0x001c
        L_0x0028:
            r1 = 10
            goto L_0x009a
        L_0x002c:
            java.lang.String r1 = "Original"
            boolean r5 = r5.equals(r1)
            if (r5 != 0) goto L_0x0035
            goto L_0x001c
        L_0x0035:
            r1 = 9
            goto L_0x009a
        L_0x0039:
            java.lang.String r1 = "Dashboard"
            boolean r5 = r5.equals(r1)
            if (r5 != 0) goto L_0x0042
            goto L_0x001c
        L_0x0042:
            r1 = 8
            goto L_0x009a
        L_0x0045:
            java.lang.String r1 = "AppList"
            boolean r5 = r5.equals(r1)
            if (r5 != 0) goto L_0x004e
            goto L_0x001c
        L_0x004e:
            r1 = 7
            goto L_0x009a
        L_0x0050:
            java.lang.String r1 = "Video"
            boolean r5 = r5.equals(r1)
            if (r5 != 0) goto L_0x0059
            goto L_0x001c
        L_0x0059:
            r1 = 6
            goto L_0x009a
        L_0x005b:
            java.lang.String r1 = "Music"
            boolean r5 = r5.equals(r1)
            if (r5 != 0) goto L_0x0064
            goto L_0x001c
        L_0x0064:
            r1 = 5
            goto L_0x009a
        L_0x0066:
            java.lang.String r1 = "Navi"
            boolean r5 = r5.equals(r1)
            if (r5 != 0) goto L_0x006f
            goto L_0x001c
        L_0x006f:
            r1 = 4
            goto L_0x009a
        L_0x0071:
            java.lang.String r1 = "Dvr"
            boolean r5 = r5.equals(r1)
            if (r5 != 0) goto L_0x007a
            goto L_0x001c
        L_0x007a:
            r1 = 3
            goto L_0x009a
        L_0x007c:
            java.lang.String r1 = "Bt"
            boolean r5 = r5.equals(r1)
            if (r5 != 0) goto L_0x0085
            goto L_0x001c
        L_0x0085:
            r1 = 2
            goto L_0x009a
        L_0x0087:
            java.lang.String r2 = "Weather"
            boolean r5 = r5.equals(r2)
            if (r5 != 0) goto L_0x009a
            goto L_0x001c
        L_0x0090:
            java.lang.String r1 = "Carplay"
            boolean r5 = r5.equals(r1)
            if (r5 != 0) goto L_0x0099
            goto L_0x001c
        L_0x0099:
            r1 = r2
        L_0x009a:
            switch(r1) {
                case 0: goto L_0x00c6;
                case 1: goto L_0x00c2;
                case 2: goto L_0x00be;
                case 3: goto L_0x00ba;
                case 4: goto L_0x00b6;
                case 5: goto L_0x00b2;
                case 6: goto L_0x00ae;
                case 7: goto L_0x00aa;
                case 8: goto L_0x00a6;
                case 9: goto L_0x00a2;
                case 10: goto L_0x009e;
                default: goto L_0x009d;
            }
        L_0x009d:
            goto L_0x00db
        L_0x009e:
            r4.enterSettings()
            goto L_0x00db
        L_0x00a2:
            r4.enterOriginal()
            goto L_0x00db
        L_0x00a6:
            r4.enterDashboard()
            goto L_0x00db
        L_0x00aa:
            r4.enterApplist()
            goto L_0x00db
        L_0x00ae:
            r4.enterVideo()
            goto L_0x00db
        L_0x00b2:
            r4.enterMusic()
            goto L_0x00db
        L_0x00b6:
            r4.enterNavi()
            goto L_0x00db
        L_0x00ba:
            r4.enterDvr()
            goto L_0x00db
        L_0x00be:
            r4.enterBT()
            goto L_0x00db
        L_0x00c2:
            r4.enterWeather()
            goto L_0x00db
        L_0x00c6:
            r4.enterCarplay()
            goto L_0x00db
        L_0x00ca:
            java.lang.String r0 = "/"
            java.lang.String[] r5 = r5.split(r0)
            r0 = r5[r2]
            r5 = r5[r1]
            android.content.Context r1 = r4.mContext
            com.szchoiceway.eventcenter.IEventService r4 = r4.mEvtService
            com.szchoiceway.customerui.utils.EventUtils.startThirdApp(r1, r4, r0, r5)
        L_0x00db:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.szchoiceway.customerui.fragment.MainFragment.onItemClickByTag(java.lang.String):void");
    }

    public void enterEditMode(int i) {
        MainAdapter mainAdapter = this.mMainAdapter;
        if (mainAdapter != null) {
            this.lastPageCount = (mainAdapter.getItemCount() / this.iPagerItemViewItemCount) + (this.mMainAdapter.getItemCount() % this.iPagerItemViewItemCount > 0 ? 1 : 0);
        }
        if (!LauncherModel.getInstance().inEditMode) {
            LauncherModel.getInstance().inEditMode = true;
            this.mMainAdapterEdit.setEditModePosition(i);
            View view = this.layoutMain;
            if (view != null) {
                view.setVisibility(8);
            }
            View view5 = this.layoutEdit;
            if (view5 != null) {
                view5.setVisibility(0);
            }
        }
    }

    public void exitEditMode() {
        int i;
        if (LauncherModel.getInstance().inEditMode) {
            LauncherModel.getInstance().inEditMode = false;
            MainAdapter mainAdapter = this.mMainAdapterEdit;
            if (mainAdapter != null) {
                mainAdapter.exitEditMode();
            }
            MainAdapter mainAdapter2 = this.mMainAdapter;
            if (mainAdapter2 != null) {
                mainAdapter2.setData(this.itemUtil.getAllItemTags());
                this.mMainAdapter.notifyDataSetChanged();
                i = (this.mMainAdapter.getItemCount() / this.iPagerItemViewItemCount) + (this.mMainAdapter.getItemCount() % this.iPagerItemViewItemCount > 0 ? 1 : 0);
            } else {
                i = 0;
            }
            if (i != this.lastPageCount) {
                this.mScrollHelper.scrollToPosition(0);
            }
            this.lastPageCount = i;
            View view = this.layoutMain;
            if (view != null) {
                view.setVisibility(0);
            }
            View view5 = this.layoutEdit;
            if (view5 != null) {
                view5.setVisibility(8);
            }
        }
    }

    public void addThirdApp(String str, String str2) {
        if (!this.itemUtil.getAllItemTags().contains(str + "/" + str2)) {
            this.itemUtil.addThirdApp(str, str2);
        }
    }

    /* access modifiers changed from: private */
    public int getAttrId(int i) {
        TypedValue typedValue = new TypedValue();
        this.ctx.getTheme().resolveAttribute(i, typedValue, true);
        return typedValue.resourceId;
    }

    private void setDefaultPageIconIfNotNull() {
        int i;
        int i2;
        Context context = getContext();
        if (context != null) {
            if (this.mPage0 != null && ((i2 = this.m_iModeSet) == 21 || i2 == 31)) {
                this.mPage0.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ksw_1920x720_benz_fanye_d));
            }
            ILauncherStrategy iLauncherStrategy = this.launcherStrategy;
            if (iLauncherStrategy != null) {
                iLauncherStrategy.setPageSelected(0);
            }
            ImageView[] imageViewArr = {this.mPage1, this.mPage2, this.mPage3};
            for (int i3 = 0; i3 < 3; i3++) {
                ImageView imageView = imageViewArr[i3];
                if (imageView != null && ((i = this.m_iModeSet) == 21 || i == 31)) {
                    imageView.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ksw_1920x720_benz_fanye_n));
                }
            }
        }
    }

    public static MainFragment getInstance() {
        return instace;
    }

    public void refreshStorageView() {
        MainTopView mainTopView = this.mMainTopView;
        if (mainTopView != null) {
            mainTopView.refreshStorageView();
        }
    }

    public void refreshWifiView(int i) {
        MainTopView mainTopView = this.mMainTopView;
        if (mainTopView != null) {
            mainTopView.refreshWifiView(i);
        }
    }

    public void refreshSignalView(int i, String str) {
        MainTopView mainTopView = this.mMainTopView;
        if (mainTopView != null) {
            mainTopView.refreshSignalView(i, str);
        }
    }

    public boolean isDefaultUI() {
        return this.m_iModeSet == 16 || this.m_iModeSet >= 100;
    }
}
