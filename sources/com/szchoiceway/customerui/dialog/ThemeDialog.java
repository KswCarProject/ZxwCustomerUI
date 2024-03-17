package com.szchoiceway.customerui.dialog;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import com.szchoiceway.customerui.R;
import com.szchoiceway.customerui.model.LauncherModel;
import com.szchoiceway.customerui.utils.SysProviderOpt;
import com.szchoiceway.zxwlib.bean.Customer;

public class ThemeDialog implements RadioGroup.OnCheckedChangeListener, View.OnClickListener {
    private static String TAG = "BenzThemeDialog";
    private int benzBackgroundIndex = 0;
    private int benzButtonTheme = 0;
    private CheckBox chkLandroverBg0;
    private CheckBox chkLandroverBg1;
    private CheckBox chkSetThemeClassic;
    private CheckBox chkSetThemeModern;
    private Button hideDialog;
    private ImageView imgClassic;
    private ImageView imgModern;
    private boolean isShow = false;
    private boolean isSmallResolution = false;
    private int landroverBgIndex = 0;
    private OnBackgroundChangeListener mBackgroundChangeListener;
    private Context mContext;
    private SysProviderOpt mProvider;
    private View mRootView;
    private OnThemeChangeListener mThemeChangeListener;
    private int m_iModeSet = 18;
    private int m_iUIIndex = 0;
    private WindowManager.LayoutParams params = new WindowManager.LayoutParams();
    private RadioGroup rgBackground;
    private View rootView;
    private TextView textThemeClassic;
    private TextView textThemeModern;
    private WindowManager wm;

    public interface OnBackgroundChangeListener {
        void setLandRoverBackground();

        void setMainBackground();
    }

    public interface OnThemeChangeListener {
        void setTheme();
    }

    public ThemeDialog(Context context) {
        this.mContext = context;
        this.wm = (WindowManager) context.getSystemService("window");
        SysProviderOpt instance = SysProviderOpt.getInstance(context);
        this.mProvider = instance;
        int recordInteger = instance.getRecordInteger("KESAIWEI_SYS_MODE_SELECTION", this.m_iModeSet);
        this.m_iModeSet = recordInteger;
        if (!(recordInteger == 35 || recordInteger == 34)) {
            this.m_iUIIndex = Customer.getUIIndex(this.mContext);
        }
        this.isSmallResolution = Customer.isSmallResolution(this.mContext);
        Log.d(TAG, "m_iModeSet = " + this.m_iModeSet + ", isLargeResolution = " + this.isSmallResolution);
        initView();
    }

    private void initView() {
        if (this.m_iModeSet == 22) {
            this.mRootView = View.inflate(this.mContext, R.layout.benz_fy_dialog_theme, (ViewGroup) null);
        } else if (this.m_iUIIndex == 5) {
            View inflate = View.inflate(this.mContext, R.layout.landrover_dialog_theme, (ViewGroup) null);
            this.mRootView = inflate;
            View findViewById = inflate.findViewById(R.id.rootView);
            this.rootView = findViewById;
            int i = this.m_iModeSet;
            findViewById.setBackgroundResource((i == 32 || i == 39) ? R.drawable.landrover_main_bk3 : R.drawable.landrover_main_bk3_black);
        } else if (this.isSmallResolution) {
            this.mRootView = View.inflate(this.mContext, R.layout.small_kesaiwei_1920x720_benz_theme_dialog, (ViewGroup) null);
        } else {
            this.mRootView = View.inflate(this.mContext, R.layout.kesaiwei_1920x720_benz_theme_dialog, (ViewGroup) null);
        }
        ImageView imageView = (ImageView) this.mRootView.findViewById(R.id.imgClassic);
        this.imgClassic = imageView;
        if (imageView != null && (LauncherModel.getInstance().m_iModeSet == 30 || LauncherModel.getInstance().m_iModeSet == 31)) {
            this.imgClassic.setBackgroundResource(R.drawable.mbux2_coat_set_icon1_1);
        }
        ImageView imageView2 = (ImageView) this.mRootView.findViewById(R.id.imgModern);
        this.imgModern = imageView2;
        if (imageView2 != null && (LauncherModel.getInstance().m_iModeSet == 30 || LauncherModel.getInstance().m_iModeSet == 31)) {
            this.imgModern.setBackgroundResource(R.drawable.mbux2_coat_set_icon1_2);
        }
        CheckBox checkBox = (CheckBox) this.mRootView.findViewById(R.id.chkSetThemeClassic);
        this.chkSetThemeClassic = checkBox;
        if (checkBox != null) {
            checkBox.setOnClickListener(this);
        }
        TextView textView = (TextView) this.mRootView.findViewById(R.id.textThemeClassic);
        this.textThemeClassic = textView;
        if (textView != null) {
            textView.setVisibility(0);
        }
        CheckBox checkBox2 = (CheckBox) this.mRootView.findViewById(R.id.chkSetThemeModern);
        this.chkSetThemeModern = checkBox2;
        if (checkBox2 != null) {
            checkBox2.setOnClickListener(this);
        }
        TextView textView2 = (TextView) this.mRootView.findViewById(R.id.textThemeModern);
        this.textThemeModern = textView2;
        if (textView2 != null) {
            textView2.setVisibility(0);
        }
        RadioGroup radioGroup = (RadioGroup) this.mRootView.findViewById(R.id.rgBackground);
        this.rgBackground = radioGroup;
        if (radioGroup != null) {
            radioGroup.setOnCheckedChangeListener(this);
        }
        this.chkLandroverBg0 = (CheckBox) this.mRootView.findViewById(R.id.chkLandroverBg0);
        this.chkLandroverBg1 = (CheckBox) this.mRootView.findViewById(R.id.chkLandroverBg1);
        int[] iArr = {R.id.tvTheme1, R.id.tvTheme2, R.id.tvTheme3, R.id.tvTheme4, R.id.tvTheme5, R.id.tvTheme6, R.id.tvTheme7, R.id.tvTheme8, R.id.chkLandroverBg0, R.id.chkLandroverBg1, R.id.btnReturnLeft, R.id.btnReturnTop, R.id.btnReturnRight, R.id.btnReturnBottom};
        for (int i2 = 0; i2 < 14; i2++) {
            View findViewById2 = this.mRootView.findViewById(iArr[i2]);
            if (findViewById2 != null) {
                findViewById2.setOnClickListener(this);
            }
        }
        Button button = (Button) this.mRootView.findViewById(R.id.hideDialog);
        this.hideDialog = button;
        if (button != null) {
            button.setOnClickListener(this);
        }
        if (this.m_iModeSet == 22 || this.m_iUIIndex == 5) {
            this.params.x = 0;
            this.params.y = 0;
            this.params.width = (int) this.mContext.getResources().getDimension(R.dimen.screenWidth);
            this.params.height = (int) this.mContext.getResources().getDimension(R.dimen.screenHeight);
        } else if (this.isSmallResolution) {
            int dimension = (int) this.mContext.getResources().getDimension(R.dimen.small_themeDialog_width);
            int dimension2 = (int) this.mContext.getResources().getDimension(R.dimen.small_themeDialog_height);
            this.mContext.getResources().getDisplayMetrics();
            Log.d(TAG, "width = " + dimension + ", height = " + dimension2);
            this.params.width = dimension;
            this.params.height = dimension2;
        } else {
            this.params.x = (int) this.mContext.getResources().getDimension(R.dimen.themeDialog_x);
            this.params.y = (int) this.mContext.getResources().getDimension(R.dimen.themeDialog_y);
            this.params.width = (int) this.mContext.getResources().getDimension(R.dimen.themeDialog_width);
            this.params.height = (int) this.mContext.getResources().getDimension(R.dimen.themeDialog_height);
        }
        this.params.windowAnimations = 16973910;
        this.params.type = 2038;
        this.params.format = 1;
        this.params.flags = 296;
        if (this.isSmallResolution) {
            this.params.gravity = 17;
        } else {
            this.params.gravity = 51;
        }
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id != R.id.hideDialog) {
            switch (id) {
                case R.id.btnReturnBottom:
                case R.id.btnReturnLeft:
                case R.id.btnReturnRight:
                case R.id.btnReturnTop:
                    break;
                default:
                    switch (id) {
                        case R.id.chkLandroverBg0:
                            this.mProvider.updateRecord("KSW_LANDROVER_THEME_BACKGROUND_INDEX", "0");
                            initLandRoverBg();
                            this.mBackgroundChangeListener.setLandRoverBackground();
                            return;
                        case R.id.chkLandroverBg1:
                            this.mProvider.updateRecord("KSW_LANDROVER_THEME_BACKGROUND_INDEX", "1");
                            initLandRoverBg();
                            this.mBackgroundChangeListener.setLandRoverBackground();
                            return;
                        default:
                            switch (id) {
                                case R.id.chkSetThemeClassic:
                                    Log.d(TAG, "onClick = chkSetThemeClassic");
                                    this.benzButtonTheme = 1;
                                    this.mProvider.updateRecord(SysProviderOpt.KSW_BENZ_THEME_INDEX, this.benzButtonTheme + "");
                                    refreshThemeType();
                                    return;
                                case R.id.chkSetThemeModern:
                                    Log.d(TAG, "onClick = chkSetThemeModern");
                                    this.benzButtonTheme = 2;
                                    this.mProvider.updateRecord(SysProviderOpt.KSW_BENZ_THEME_INDEX, this.benzButtonTheme + "");
                                    refreshThemeType();
                                    return;
                                default:
                                    switch (id) {
                                        case R.id.tvTheme1:
                                            RadioGroup radioGroup = this.rgBackground;
                                            if (radioGroup != null) {
                                                radioGroup.check(R.id.rbColor_lan);
                                                return;
                                            }
                                            return;
                                        case R.id.tvTheme2:
                                            RadioGroup radioGroup2 = this.rgBackground;
                                            if (radioGroup2 != null) {
                                                radioGroup2.check(R.id.rbColor_yinghong);
                                                return;
                                            }
                                            return;
                                        case R.id.tvTheme3:
                                            RadioGroup radioGroup3 = this.rgBackground;
                                            if (radioGroup3 != null) {
                                                radioGroup3.check(R.id.rbColor_huang);
                                                return;
                                            }
                                            return;
                                        case R.id.tvTheme4:
                                            RadioGroup radioGroup4 = this.rgBackground;
                                            if (radioGroup4 != null) {
                                                radioGroup4.check(R.id.rbColor_dahong);
                                                return;
                                            }
                                            return;
                                        case R.id.tvTheme5:
                                            RadioGroup radioGroup5 = this.rgBackground;
                                            if (radioGroup5 != null) {
                                                radioGroup5.check(R.id.rbColor_lanlv);
                                                return;
                                            }
                                            return;
                                        case R.id.tvTheme6:
                                            RadioGroup radioGroup6 = this.rgBackground;
                                            if (radioGroup6 != null) {
                                                radioGroup6.check(R.id.rbColor_qianhong);
                                                return;
                                            }
                                            return;
                                        case R.id.tvTheme7:
                                            RadioGroup radioGroup7 = this.rgBackground;
                                            if (radioGroup7 != null) {
                                                radioGroup7.check(R.id.rbColor_hui);
                                                return;
                                            }
                                            return;
                                        case R.id.tvTheme8:
                                            RadioGroup radioGroup8 = this.rgBackground;
                                            if (radioGroup8 != null) {
                                                radioGroup8.check(R.id.rbColor_map);
                                                return;
                                            }
                                            return;
                                        default:
                                            return;
                                    }
                            }
                    }
            }
        }
        hideThemeDialog();
    }

    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        switch (i) {
            case R.id.rbColor_dahong:
                this.mProvider.updateRecord(SysProviderOpt.KSW_BENZ_THEME_BACKGROUND_INDEX, "3");
                break;
            case R.id.rbColor_huang:
                this.mProvider.updateRecord(SysProviderOpt.KSW_BENZ_THEME_BACKGROUND_INDEX, "2");
                break;
            case R.id.rbColor_hui:
                this.mProvider.updateRecord(SysProviderOpt.KSW_BENZ_THEME_BACKGROUND_INDEX, "6");
                break;
            case R.id.rbColor_lan:
                this.mProvider.updateRecord(SysProviderOpt.KSW_BENZ_THEME_BACKGROUND_INDEX, "0");
                break;
            case R.id.rbColor_lanlv:
                this.mProvider.updateRecord(SysProviderOpt.KSW_BENZ_THEME_BACKGROUND_INDEX, "4");
                break;
            case R.id.rbColor_map:
                this.mProvider.updateRecord(SysProviderOpt.KSW_BENZ_THEME_BACKGROUND_INDEX, "7");
                break;
            case R.id.rbColor_qianhong:
                this.mProvider.updateRecord(SysProviderOpt.KSW_BENZ_THEME_BACKGROUND_INDEX, "5");
                break;
            case R.id.rbColor_yinghong:
                this.mProvider.updateRecord(SysProviderOpt.KSW_BENZ_THEME_BACKGROUND_INDEX, "1");
                break;
        }
        this.mBackgroundChangeListener.setMainBackground();
    }

    private void refreshThemeType() {
        if (this.benzButtonTheme == 1) {
            CheckBox checkBox = this.chkSetThemeClassic;
            if (checkBox != null) {
                checkBox.setChecked(true);
            }
            TextView textView = this.textThemeClassic;
            if (textView != null) {
                textView.setTextColor(Color.parseColor("#00a8ff"));
            }
            CheckBox checkBox2 = this.chkSetThemeModern;
            if (checkBox2 != null) {
                checkBox2.setChecked(false);
            }
            TextView textView2 = this.textThemeModern;
            if (textView2 != null) {
                textView2.setTextColor(Color.parseColor("#ffffff"));
            }
        } else {
            CheckBox checkBox3 = this.chkSetThemeClassic;
            if (checkBox3 != null) {
                checkBox3.setChecked(false);
            }
            TextView textView3 = this.textThemeClassic;
            if (textView3 != null) {
                textView3.setTextColor(Color.parseColor("#ffffff"));
            }
            CheckBox checkBox4 = this.chkSetThemeModern;
            if (checkBox4 != null) {
                checkBox4.setChecked(true);
            }
            TextView textView4 = this.textThemeModern;
            if (textView4 != null) {
                textView4.setTextColor(Color.parseColor("#00a8ff"));
            }
        }
        OnThemeChangeListener onThemeChangeListener = this.mThemeChangeListener;
        if (onThemeChangeListener != null) {
            onThemeChangeListener.setTheme();
        }
    }

    private void initRgBackground() {
        RadioGroup radioGroup = this.rgBackground;
        if (radioGroup != null) {
            switch (this.benzBackgroundIndex) {
                case 0:
                    radioGroup.check(R.id.rbColor_lan);
                    return;
                case 1:
                    radioGroup.check(R.id.rbColor_yinghong);
                    return;
                case 2:
                    radioGroup.check(R.id.rbColor_huang);
                    return;
                case 3:
                    radioGroup.check(R.id.rbColor_dahong);
                    return;
                case 4:
                    radioGroup.check(R.id.rbColor_lanlv);
                    return;
                case 5:
                    radioGroup.check(R.id.rbColor_qianhong);
                    return;
                case 6:
                    radioGroup.check(R.id.rbColor_hui);
                    return;
                case 7:
                    radioGroup.check(R.id.rbColor_map);
                    return;
                default:
                    return;
            }
        }
    }

    private void initLandRoverBg() {
        boolean z = false;
        int recordInteger = this.mProvider.getRecordInteger("KSW_LANDROVER_THEME_BACKGROUND_INDEX", 0);
        this.landroverBgIndex = recordInteger;
        CheckBox checkBox = this.chkLandroverBg0;
        if (checkBox != null && this.chkLandroverBg1 != null) {
            checkBox.setChecked(recordInteger == 0);
            CheckBox checkBox2 = this.chkLandroverBg1;
            if (this.landroverBgIndex == 1) {
                z = true;
            }
            checkBox2.setChecked(z);
        }
    }

    public void setOnThemeChangeListener(OnThemeChangeListener onThemeChangeListener) {
        this.mThemeChangeListener = onThemeChangeListener;
    }

    public void setOnBackgroundChangeListener(OnBackgroundChangeListener onBackgroundChangeListener) {
        this.mBackgroundChangeListener = onBackgroundChangeListener;
    }

    public void showThemeDialog() {
        if (!this.isShow) {
            this.benzButtonTheme = this.mProvider.getRecordInteger(SysProviderOpt.KSW_BENZ_THEME_INDEX, 0);
            this.benzBackgroundIndex = this.mProvider.getRecordInteger(SysProviderOpt.KSW_BENZ_THEME_BACKGROUND_INDEX, 0);
            this.isShow = true;
            this.wm.addView(this.mRootView, this.params);
            initRgBackground();
            refreshThemeType();
            initLandRoverBg();
        }
    }

    public void hideThemeDialog() {
        if (this.isShow) {
            this.isShow = false;
            this.wm.removeView(this.mRootView);
        }
    }

    public boolean getIsShow() {
        return this.isShow;
    }
}
