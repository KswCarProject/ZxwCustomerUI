package com.szchoiceway.customerui.bmw.recycle;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;
import com.szchoiceway.customerui.R;
import com.szchoiceway.customerui.bmw.ui.MainPageUIController;
import com.szchoiceway.customerui.utils.EventUtils;
import com.szchoiceway.customerui.utils.SysProviderOpt;
import com.szchoiceway.customerui.views.MySpeedEditView;
import com.szchoiceway.customerui.views.MySpeedView;
import com.szchoiceway.zxwlib.GyroScopeWithCompassView;
import java.util.Random;

public class DashboardItem extends RecycleItemBase {
    private static final String TAG = "DashboardItem";
    private static int iSpeed;
    private ValueAnimator animator;
    private ImageView bk1;
    private ImageView bk2;
    private float currentAngle = 0.0f;
    int gg = 0;
    private ImageView imgBk;
    private View imgLine;
    private float lastAngle = 0.0f;
    int lsGG = 0;
    Context mContext;
    View mDashboardView;
    private String mFuleVale = "0";
    private boolean mHandbrakeStatus;
    private ImageView mImgHandbrake;
    private ImageView mImgSeatBelt;
    private int mMode = 0;
    private TextView mOilConsumption;
    private boolean mSeatBeltStatus;
    private boolean mSmallMode = false;
    /* access modifiers changed from: private */
    public TextView mSpeed;
    private TextView mSpeedUnit;
    private TextView mTitle;
    private boolean mUnit;
    private MainPageUIController mainPageUIController;
    private int maxAngle = GyroScopeWithCompassView.CARTYPE_AOHU;
    private int maxSpeed = 300;
    Handler mh = new Handler() {
        public void handleMessage(Message message) {
            super.handleMessage(message);
            if (message.what == 111) {
                Random random = new Random();
                DashboardItem.this.gg = random.nextInt(330);
                DashboardItem dashboardItem = DashboardItem.this;
                dashboardItem.setCurSpeed(dashboardItem.gg);
                DashboardItem.this.mSpeed.setText(DashboardItem.this.gg + "");
                DashboardItem.this.mh.sendEmptyMessageDelayed(111, 100);
            }
        }
    };
    private ImageView myClock;
    private MySpeedEditView mySpeedEditView;
    private MySpeedView mySpeedView;

    public String getTag() {
        return "Dashboard";
    }

    public DashboardItem(MainPageUIController mainPageUIController2) {
        this.mainPageUIController = mainPageUIController2;
    }

    public View getSetView(Context context, boolean z) {
        View view;
        this.mContext = context;
        this.m_iModeSet = SysProviderOpt.getInstance(context).getRecordInteger("KESAIWEI_SYS_MODE_SELECTION", this.m_iModeSet);
        boolean z2 = this.mSmallMode;
        if (z2 == z && (view = this.mDashboardView) != null) {
            return view;
        }
        if (z2 != z) {
            this.mSmallMode = z;
            this.lastAngle = 0.0f;
        }
        if (this.m_iModeSet == 20) {
            if (z) {
                this.mDashboardView = LayoutInflater.from(context).inflate(R.layout.pemp_bmw_id8_dashboard_item_layout_small, (ViewGroup) null);
            } else {
                this.mDashboardView = LayoutInflater.from(context).inflate(R.layout.pemp_bmw_id8_dashboard_item_layout, (ViewGroup) null);
            }
        } else if (z) {
            this.mDashboardView = LayoutInflater.from(context).inflate(R.layout.bmw_id8_dashboard_item_layout_small, (ViewGroup) null);
        } else if ("1280x480".equals(SysProviderOpt.getInstance(this.mContext).getRecordValue("KSW_UI_RESOLUTION"))) {
            this.mDashboardView = LayoutInflater.from(context).inflate(R.layout.bmw_id8_1280x480_dashboard_item_layout, (ViewGroup) null);
        } else {
            this.mDashboardView = LayoutInflater.from(context).inflate(R.layout.bmw_id8_dashboard_item_layout, (ViewGroup) null);
        }
        init();
        return this.mDashboardView;
    }

    private void init() {
        int i = 0;
        int recordInteger = SysProviderOpt.getInstance(this.mContext).getRecordInteger(SysProviderOpt.KSW_DATA_PRODUCT_INDEX, 0);
        ImageView imageView = (ImageView) this.mDashboardView.findViewById(R.id.imgBk);
        this.imgBk = imageView;
        if (imageView != null) {
            imageView.setVisibility(this.mSmallMode ? 8 : 0);
        }
        this.bk1 = (ImageView) this.mDashboardView.findViewById(R.id.bk1);
        this.bk2 = (ImageView) this.mDashboardView.findViewById(R.id.bk2);
        this.imgLine = this.mDashboardView.findViewById(R.id.imgLine);
        this.mTitle = (TextView) this.mDashboardView.findViewById(R.id.title);
        this.mSpeed = (TextView) this.mDashboardView.findViewById(R.id.speed);
        this.mySpeedView = (MySpeedView) this.mDashboardView.findViewById(R.id.mySpeedView);
        this.mySpeedEditView = (MySpeedEditView) this.mDashboardView.findViewById(R.id.mySpeedEditView);
        this.mSpeedUnit = (TextView) this.mDashboardView.findViewById(R.id.speedUnit);
        ImageView imageView2 = (ImageView) this.mDashboardView.findViewById(R.id.imgHandbrake);
        this.mImgHandbrake = imageView2;
        if (imageView2 != null) {
            imageView2.setVisibility(recordInteger == 2 ? 8 : 0);
        }
        ImageView imageView3 = (ImageView) this.mDashboardView.findViewById(R.id.imgSeatBelt);
        this.mImgSeatBelt = imageView3;
        if (imageView3 != null) {
            imageView3.setVisibility(recordInteger == 2 ? 8 : 0);
        }
        View findViewById = this.mDashboardView.findViewById(R.id.imgOil);
        Log.d(TAG, "imgOil = " + findViewById);
        if (findViewById != null) {
            findViewById.setVisibility(recordInteger == 2 ? 8 : 0);
        }
        TextView textView = (TextView) this.mDashboardView.findViewById(R.id.oilConsumption);
        this.mOilConsumption = textView;
        if (textView != null) {
            if (recordInteger == 2) {
                i = 8;
            }
            textView.setVisibility(i);
        }
        this.myClock = (ImageView) this.mDashboardView.findViewById(R.id.myClock1);
        updateInfo();
        setSpeedUnit();
        setHandbrakeStatus();
        setSeatBeltStatus();
        setQilValue();
        int i2 = this.mainPageUIController.iSpeed;
        iSpeed = i2;
        setCurSpeed(i2);
    }

    public void setSpeedUnit() {
        boolean recordBoolean = SysProviderOpt.getInstance(this.mContext).getRecordBoolean(SysProviderOpt.KSW_DISTACNE_UNIT, false);
        this.mUnit = recordBoolean;
        TextView textView = this.mSpeedUnit;
        if (textView != null) {
            textView.setText(recordBoolean ? "mph" : "km/h");
        }
    }

    public void setHandbrakeStatus(boolean z) {
        this.mHandbrakeStatus = z;
        setHandbrakeStatus();
    }

    private void setHandbrakeStatus() {
        if (this.mImgHandbrake == null) {
            return;
        }
        if (this.m_iModeSet == 20) {
            if (this.mSmallMode) {
                this.mImgHandbrake.setBackgroundResource(this.mHandbrakeStatus ? R.drawable.pemp_id8_main_edit_icon_dashboard_brake_f : R.drawable.pemp_id8_main_edit_icon_dashboard_brake);
            } else {
                this.mImgHandbrake.setBackgroundResource(this.mHandbrakeStatus ? R.drawable.pemp_id8_main_icon_dashboard_brake_f : R.drawable.pemp_id8_main_icon_dashboard_brake);
            }
        } else if (this.mSmallMode) {
            this.mImgHandbrake.setBackgroundResource(this.mHandbrakeStatus ? R.drawable.id8_main_edit_icon_dashboard_brake_f : R.drawable.id8_main_edit_icon_dashboard_brake);
        } else {
            this.mImgHandbrake.setBackgroundResource(this.mHandbrakeStatus ? R.drawable.id8_main_icon_dashboard_brake_f : R.drawable.id8_main_icon_dashboard_brake);
        }
    }

    public void setSeatBeltStatus(boolean z) {
        this.mSeatBeltStatus = z;
        setSeatBeltStatus();
    }

    private void setSeatBeltStatus() {
        if (this.mImgSeatBelt == null) {
            return;
        }
        if (this.m_iModeSet == 20) {
            if (this.mSmallMode) {
                this.mImgSeatBelt.setBackgroundResource(this.mSeatBeltStatus ? R.drawable.pemp_id8_main_edit_icon_dashboard_seatbelt : R.drawable.pemp_id8_main_edit_icon_dashboard_seatbelt_f);
            } else {
                this.mImgSeatBelt.setBackgroundResource(this.mSeatBeltStatus ? R.drawable.pemp_id8_main_icon_dashboard_seatbelt : R.drawable.pemp_id8_main_icon_dashboard_seatbelt_f);
            }
        } else if (this.mSmallMode) {
            this.mImgSeatBelt.setBackgroundResource(this.mSeatBeltStatus ? R.drawable.id8_main_edit_icon_dashboard_seatbelt : R.drawable.id8_main_edit_icon_dashboard_seatbelt_f);
        } else {
            this.mImgSeatBelt.setBackgroundResource(this.mSeatBeltStatus ? R.drawable.id8_main_icon_dashboard_seatbelt : R.drawable.id8_main_icon_dashboard_seatbelt_f);
        }
    }

    public void setQilValue(String str) {
        this.mFuleVale = str;
        setQilValue();
    }

    private void setQilValue() {
        if (this.mOilConsumption != null) {
            double parseDouble = Double.parseDouble(this.mFuleVale);
            int recordInteger = SysProviderOpt.getInstance(this.mContext).getRecordInteger(SysProviderOpt.KSW_OIL_UNIT, 0);
            if (recordInteger == 0) {
                this.mOilConsumption.setText(parseDouble + "L");
            } else if (recordInteger == 1) {
                this.mOilConsumption.setText(((int) (parseDouble / 3.78d)) + "gal");
            } else if (recordInteger == 2) {
                this.mOilConsumption.setText(((int) (parseDouble / 4.55d)) + "gal");
            }
        }
    }

    public void setSpeed(int i) {
        Log.i("TAG", "setSpeed:--------- " + i);
        if (this.mUnit) {
            i = (int) (((float) i) * 0.62f);
        }
        iSpeed = i;
        setCurSpeed(i);
    }

    public void setCurSpeed(int i) {
        Log.d(TAG, "setCurSpeed speed = " + i + ", smallMode = " + this.mSmallMode);
        if (this.m_iModeSet == 20) {
            TextView textView = this.mSpeed;
            if (textView != null) {
                textView.setText("" + i);
            }
            if (this.myClock != null) {
                this.currentAngle = ((((float) i) * 1.0f) / ((float) this.maxSpeed)) * ((float) this.maxAngle);
                Log.d(TAG, "currentAngle = " + this.currentAngle + ", lastAngle = " + this.lastAngle);
                if (this.currentAngle != this.lastAngle) {
                    RotateAnimation rotateAnimation = new RotateAnimation(this.lastAngle, this.currentAngle, 1, 0.5f, 1, 0.5f);
                    rotateAnimation.setDuration(200);
                    rotateAnimation.setFillAfter(true);
                    Log.d(TAG, "startAnimation");
                    this.myClock.startAnimation(rotateAnimation);
                    this.lastAngle = this.currentAngle;
                    return;
                }
                return;
            }
            return;
        }
        MySpeedView mySpeedView2 = this.mySpeedView;
        if (mySpeedView2 != null) {
            mySpeedView2.setSpeed(i, this.mUnit);
        }
        MySpeedEditView mySpeedEditView2 = this.mySpeedEditView;
        if (mySpeedEditView2 != null) {
            mySpeedEditView2.setSpeed(i);
        }
        ValueAnimator valueAnimator = this.animator;
        if (valueAnimator != null && valueAnimator.isRunning()) {
            this.animator.cancel();
        }
        ValueAnimator ofInt = ValueAnimator.ofInt(new int[]{this.lsGG, i});
        this.animator = ofInt;
        ofInt.setDuration(350);
        this.animator.setInterpolator(new AccelerateInterpolator());
        this.animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int intValue = ((Integer) valueAnimator.getAnimatedValue()).intValue();
                if (DashboardItem.this.mSpeed != null) {
                    DashboardItem.this.mSpeed.setText(intValue + "");
                }
            }
        });
        this.animator.start();
        this.lsGG = i;
    }

    public void updateInfo() {
        if (this.mTitle != null) {
            this.mTitle.setText(this.mContext.getString(R.string.lb_dash_board).toUpperCase());
        }
        this.mMode = SysProviderOpt.getInstance(this.mContext).getRecordInteger("KESAIWEI_SYS_DISPLAY_MODE", 0);
        int i = this.m_iModeSet;
        int i2 = R.drawable.id8_main_edit_icon_dashboard_b_1;
        int i3 = R.drawable.id8_main_edit_icon_dashboard_y;
        int i4 = R.drawable.id8_main_edit_icon_dashboard_r;
        int i5 = R.drawable.id8_main_edit_icon_dashboard_b;
        if (i == 20) {
            int i6 = this.mMode;
            if (i6 == 0) {
                TextView textView = this.mTitle;
                if (textView != null) {
                    textView.setTextColor(getColor(R.color.bmw_id8_mainpage_item_title_color));
                }
                ImageView imageView = this.imgBk;
                if (imageView != null) {
                    imageView.setImageResource(this.mSmallMode ? R.drawable.pemp_bmw_id8_navi_item_background_blue_small : R.drawable.pemp_bmw_id8_navi_item_background_blue);
                }
                View view = this.imgLine;
                if (view != null) {
                    view.setBackgroundResource(this.mSmallMode ? R.drawable.pemp_id8_main_edit_icon_navi_line_b : R.drawable.pemp_id8_main_icon_navi_line_b);
                }
                ImageView imageView2 = this.bk1;
                if (imageView2 != null) {
                    if (!this.mSmallMode) {
                        i5 = R.drawable.id8_main_icon_dashboard_b;
                    }
                    imageView2.setBackgroundResource(i5);
                }
                ImageView imageView3 = this.bk2;
                if (imageView3 != null) {
                    if (!this.mSmallMode) {
                        i2 = R.drawable.id8_main_icon_dashboard_b_1;
                    }
                    imageView3.setBackgroundResource(i2);
                }
            } else if (i6 == 1) {
                TextView textView2 = this.mTitle;
                if (textView2 != null) {
                    textView2.setTextColor(getColor(R.color.bmw_id8_mainpage_item_title_color_sport));
                }
                ImageView imageView4 = this.imgBk;
                if (imageView4 != null) {
                    imageView4.setImageResource(this.mSmallMode ? R.drawable.pemp_bmw_id8_navi_item_background_red_small : R.drawable.pemp_bmw_id8_navi_item_background_red);
                }
                View view2 = this.imgLine;
                if (view2 != null) {
                    view2.setBackgroundResource(this.mSmallMode ? R.drawable.pemp_id8_main_edit_icon_navi_line_r : R.drawable.pemp_id8_main_icon_navi_line_r);
                }
                ImageView imageView5 = this.bk1;
                if (imageView5 != null) {
                    if (!this.mSmallMode) {
                        i4 = R.drawable.id8_main_icon_dashboard_r;
                    }
                    imageView5.setBackgroundResource(i4);
                }
                ImageView imageView6 = this.bk2;
                if (imageView6 != null) {
                    imageView6.setBackgroundResource(this.mSmallMode ? R.drawable.id8_main_edit_icon_dashboard_r_1 : R.drawable.id8_main_icon_dashboard_r_1);
                }
            } else if (i6 == 2) {
                TextView textView3 = this.mTitle;
                if (textView3 != null) {
                    textView3.setTextColor(getColor(R.color.bmw_id8_mainpage_item_title_color_personal));
                }
                ImageView imageView7 = this.imgBk;
                if (imageView7 != null) {
                    imageView7.setImageResource(this.mSmallMode ? R.drawable.pemp_bmw_id8_navi_item_background_yellow_small : R.drawable.pemp_bmw_id8_navi_item_background_yellow);
                }
                View view3 = this.imgLine;
                if (view3 != null) {
                    view3.setBackgroundResource(this.mSmallMode ? R.drawable.pemp_id8_main_edit_icon_navi_line_y : R.drawable.pemp_id8_main_icon_navi_line_y);
                }
                ImageView imageView8 = this.bk1;
                if (imageView8 != null) {
                    if (!this.mSmallMode) {
                        i3 = R.drawable.id8_main_icon_dashboard_y;
                    }
                    imageView8.setBackgroundResource(i3);
                }
                ImageView imageView9 = this.bk2;
                if (imageView9 != null) {
                    imageView9.setBackgroundResource(this.mSmallMode ? R.drawable.id8_main_edit_icon_dashboard_y_1 : R.drawable.id8_main_icon_dashboard_y_1);
                }
            }
        } else {
            int i7 = this.mMode;
            if (i7 == 0) {
                TextView textView4 = this.mTitle;
                if (textView4 != null) {
                    textView4.setTextColor(getColor(R.color.bmw_id8_mainpage_item_title_color));
                }
                ImageView imageView10 = this.imgBk;
                if (imageView10 != null) {
                    imageView10.setImageResource(this.mSmallMode ? R.drawable.bmw_id8_navi_item_background_blue_small : R.drawable.bmw_id8_navi_item_background_blue);
                }
                View view4 = this.imgLine;
                if (view4 != null) {
                    view4.setBackgroundResource(this.mSmallMode ? R.drawable.id8_main_edit_icon_navi_line_b : R.drawable.id8_main_icon_navi_line_b);
                }
                ImageView imageView11 = this.bk1;
                if (imageView11 != null) {
                    if (!this.mSmallMode) {
                        i5 = R.drawable.id8_main_icon_dashboard_b;
                    }
                    imageView11.setBackgroundResource(i5);
                }
                ImageView imageView12 = this.bk2;
                if (imageView12 != null) {
                    if (!this.mSmallMode) {
                        i2 = R.drawable.id8_main_icon_dashboard_b_1;
                    }
                    imageView12.setBackgroundResource(i2);
                }
            } else if (i7 == 1) {
                TextView textView5 = this.mTitle;
                if (textView5 != null) {
                    textView5.setTextColor(getColor(R.color.bmw_id8_mainpage_item_title_color_sport));
                }
                ImageView imageView13 = this.imgBk;
                if (imageView13 != null) {
                    imageView13.setImageResource(this.mSmallMode ? R.drawable.bmw_id8_navi_item_background_red_small : R.drawable.bmw_id8_navi_item_background_red);
                }
                View view5 = this.imgLine;
                if (view5 != null) {
                    view5.setBackgroundResource(this.mSmallMode ? R.drawable.id8_main_edit_icon_navi_line_r : R.drawable.id8_main_icon_navi_line_r);
                }
                ImageView imageView14 = this.bk1;
                if (imageView14 != null) {
                    if (!this.mSmallMode) {
                        i4 = R.drawable.id8_main_icon_dashboard_r;
                    }
                    imageView14.setBackgroundResource(i4);
                }
                ImageView imageView15 = this.bk2;
                if (imageView15 != null) {
                    imageView15.setBackgroundResource(this.mSmallMode ? R.drawable.id8_main_edit_icon_dashboard_r_1 : R.drawable.id8_main_icon_dashboard_r_1);
                }
            } else if (i7 == 2) {
                TextView textView6 = this.mTitle;
                if (textView6 != null) {
                    textView6.setTextColor(getColor(R.color.bmw_id8_mainpage_item_title_color_personal));
                }
                ImageView imageView16 = this.imgBk;
                if (imageView16 != null) {
                    imageView16.setImageResource(this.mSmallMode ? R.drawable.bmw_id8_navi_item_background_yellow_small : R.drawable.bmw_id8_navi_item_background_yellow);
                }
                View view6 = this.imgLine;
                if (view6 != null) {
                    view6.setBackgroundResource(this.mSmallMode ? R.drawable.id8_main_edit_icon_navi_line_y : R.drawable.id8_main_icon_navi_line_y);
                }
                ImageView imageView17 = this.bk1;
                if (imageView17 != null) {
                    if (!this.mSmallMode) {
                        i3 = R.drawable.id8_main_icon_dashboard_y;
                    }
                    imageView17.setBackgroundResource(i3);
                }
                ImageView imageView18 = this.bk2;
                if (imageView18 != null) {
                    imageView18.setBackgroundResource(this.mSmallMode ? R.drawable.id8_main_edit_icon_dashboard_y_1 : R.drawable.id8_main_icon_dashboard_y_1);
                }
            }
        }
    }

    public void updataFocusState(boolean z) {
        ImageView imageView;
        ImageView imageView2;
        if (!z) {
            ImageView imageView3 = this.imgBk;
            if (imageView3 != null) {
                imageView3.setForeground((Drawable) null);
            }
        } else if (this.m_iModeSet == 20) {
            int i = this.mMode;
            if (i == 0) {
                ImageView imageView4 = this.imgBk;
                if (imageView4 != null) {
                    imageView4.setForeground(getDrawable(this.mSmallMode ? R.drawable.pemp_id8_main_edit_icon_navi_b_f : R.drawable.pemp_id8_main_icon_navi_b_f));
                }
            } else if (i == 1) {
                ImageView imageView5 = this.imgBk;
                if (imageView5 != null) {
                    imageView5.setForeground(getDrawable(this.mSmallMode ? R.drawable.pemp_id8_main_edit_icon_navi_r_f : R.drawable.pemp_id8_main_icon_navi_r_f));
                }
            } else if (i == 2 && (imageView2 = this.imgBk) != null) {
                imageView2.setForeground(getDrawable(this.mSmallMode ? R.drawable.pemp_id8_main_edit_icon_navi_y_f : R.drawable.pemp_id8_main_icon_navi_y_f));
            }
        } else {
            int i2 = this.mMode;
            if (i2 == 0) {
                ImageView imageView6 = this.imgBk;
                if (imageView6 != null) {
                    imageView6.setForeground(getDrawable(this.mSmallMode ? R.drawable.id8_main_edit_icon_navi_b_f : R.drawable.id8_main_icon_navi_b_f));
                }
            } else if (i2 == 1) {
                ImageView imageView7 = this.imgBk;
                if (imageView7 != null) {
                    imageView7.setForeground(getDrawable(this.mSmallMode ? R.drawable.id8_main_edit_icon_navi_r_f : R.drawable.id8_main_icon_navi_r_f));
                }
            } else if (i2 == 2 && (imageView = this.imgBk) != null) {
                imageView.setForeground(getDrawable(this.mSmallMode ? R.drawable.id8_main_edit_icon_navi_y_f : R.drawable.id8_main_icon_navi_y_f));
            }
        }
    }

    public int[] getViewSize() {
        if (this.mSmallMode) {
            return new int[]{EventUtils.getPx(this.mContext, 371), (int) this.mContext.getResources().getDimension(R.dimen.screenHeight)};
        }
        return new int[]{EventUtils.getPx(this.mContext, 509), (int) this.mContext.getResources().getDimension(R.dimen.screenHeight)};
    }

    private Drawable getDrawable(int i) {
        return this.mContext.getResources().getDrawable(i, (Resources.Theme) null);
    }

    private int getColor(int i) {
        return this.mContext.getResources().getColor(i, (Resources.Theme) null);
    }
}
