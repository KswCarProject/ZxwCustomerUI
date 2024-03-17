package com.szchoiceway.customerui.bmw.recycle;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.szchoiceway.customerui.R;
import com.szchoiceway.customerui.bmw.ui.MainPageUIController;
import com.szchoiceway.customerui.bmw.weather.WeatherIconBase;
import com.szchoiceway.customerui.bmw.weather.WeatherIconTXZ;
import com.szchoiceway.customerui.bmw.weather.WeatherInfo;
import com.szchoiceway.customerui.utils.EventUtils;
import com.szchoiceway.customerui.utils.SysProviderOpt;
import com.szchoiceway.customerui.utils.WeatherIconUtil;
import com.txznet.weatherquery.HourWeather;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class WeatherItem extends RecycleItemBase {
    private static final String TAG = "WeatherItem";
    private ImageView imgBk;
    private View imgLine;
    private View mBk;
    Context mContext;
    private View mImgWeather;
    private List<ImageView> mImgWeatherDetails;
    private ImageView mImgWeatherIcon1;
    private ImageView mImgWeatherIcon2;
    private ImageView mImgWeatherIcon3;
    private ImageView mImgWeatherIcon4;
    private ImageView mImgWeatherIcon5;
    private ImageView mImgWeatherStatus;
    private View mLayoutWeatherContent;
    private View mLayoutWeatherStatus;
    private int mMode = 0;
    private boolean mSmallMode = false;
    private TextView mTitle;
    private TextView mTvCity;
    private TextView mTvDetails;
    private TextView mTvTemp;
    private TextView mTvTemp1;
    private TextView mTvTemp2;
    private TextView mTvTemp3;
    private TextView mTvTemp4;
    private TextView mTvTemp5;
    private TextView mTvTempRang;
    private TextView mTvTempUnit;
    private TextView mTvTime1;
    private TextView mTvTime2;
    private TextView mTvTime3;
    private TextView mTvTime4;
    private TextView mTvTime5;
    private List<TextView> mTvWeatherDetails;
    private TextView mTvWeatherStatus;
    private List<View> mViewWeatherDetails;
    private WeatherIconBase mWeatherIconBase;
    private WeatherInfo mWeatherInfo;
    View mWeatherView;
    private MainPageUIController mainPageUIController;

    public String getTag() {
        return "Weather";
    }

    public WeatherItem(MainPageUIController mainPageUIController2) {
        this.mainPageUIController = mainPageUIController2;
    }

    public View getSetView(Context context, boolean z) {
        View view;
        this.mContext = context;
        this.m_iModeSet = SysProviderOpt.getInstance(context).getRecordInteger("KESAIWEI_SYS_MODE_SELECTION", this.m_iModeSet);
        if (this.mSmallMode == z && (view = this.mWeatherView) != null) {
            return view;
        }
        this.mSmallMode = z;
        if (this.m_iModeSet == 20) {
            if (z) {
                this.mWeatherView = LayoutInflater.from(context).inflate(R.layout.pemp_bmw_id8_weather_item_layout_small, (ViewGroup) null);
            } else {
                this.mWeatherView = LayoutInflater.from(context).inflate(R.layout.pemp_bmw_id8_weather_item_layout, (ViewGroup) null);
            }
        } else if (z) {
            this.mWeatherView = LayoutInflater.from(context).inflate(R.layout.bmw_id8_weather_item_layout_small, (ViewGroup) null);
        } else {
            this.mWeatherView = LayoutInflater.from(context).inflate(R.layout.bmw_id8_weather_item_layout, (ViewGroup) null);
        }
        init();
        return this.mWeatherView;
    }

    private void init() {
        Log.d(TAG, "init");
        if (this.mWeatherIconBase == null) {
            this.mWeatherIconBase = new WeatherIconTXZ();
        }
        ImageView imageView = (ImageView) this.mWeatherView.findViewById(R.id.imgBk);
        this.imgBk = imageView;
        if (imageView != null) {
            imageView.setVisibility(this.mSmallMode ? 8 : 0);
        }
        this.imgLine = this.mWeatherView.findViewById(R.id.imgLine);
        this.mTitle = (TextView) this.mWeatherView.findViewById(R.id.title);
        this.mBk = this.mWeatherView.findViewById(R.id.bk);
        this.mLayoutWeatherContent = this.mWeatherView.findViewById(R.id.layoutWeatherContent);
        this.mLayoutWeatherStatus = this.mWeatherView.findViewById(R.id.layoutWeatherStatus);
        this.mImgWeatherStatus = (ImageView) this.mWeatherView.findViewById(R.id.imgWeatherStatus);
        this.mTvWeatherStatus = (TextView) this.mWeatherView.findViewById(R.id.tvWeatherStatus);
        this.mImgWeather = this.mWeatherView.findViewById(R.id.imgWeather);
        this.mImgWeatherIcon1 = (ImageView) this.mWeatherView.findViewById(R.id.weatherIcon1);
        this.mImgWeatherIcon2 = (ImageView) this.mWeatherView.findViewById(R.id.weatherIcon2);
        this.mImgWeatherIcon3 = (ImageView) this.mWeatherView.findViewById(R.id.weatherIcon3);
        this.mImgWeatherIcon4 = (ImageView) this.mWeatherView.findViewById(R.id.weatherIcon4);
        this.mImgWeatherIcon5 = (ImageView) this.mWeatherView.findViewById(R.id.weatherIcon5);
        this.mTvTime1 = (TextView) this.mWeatherView.findViewById(R.id.time1);
        this.mTvTime2 = (TextView) this.mWeatherView.findViewById(R.id.time2);
        this.mTvTime3 = (TextView) this.mWeatherView.findViewById(R.id.time3);
        this.mTvTime4 = (TextView) this.mWeatherView.findViewById(R.id.time4);
        this.mTvTime5 = (TextView) this.mWeatherView.findViewById(R.id.time5);
        this.mTvTemp1 = (TextView) this.mWeatherView.findViewById(R.id.temp1);
        this.mTvTemp2 = (TextView) this.mWeatherView.findViewById(R.id.temp2);
        this.mTvTemp3 = (TextView) this.mWeatherView.findViewById(R.id.temp3);
        this.mTvTemp4 = (TextView) this.mWeatherView.findViewById(R.id.temp4);
        this.mTvTemp5 = (TextView) this.mWeatherView.findViewById(R.id.temp5);
        this.mTvTemp = (TextView) this.mWeatherView.findViewById(R.id.temp);
        this.mTvTempUnit = (TextView) this.mWeatherView.findViewById(R.id.tempUnit);
        this.mTvTempRang = (TextView) this.mWeatherView.findViewById(R.id.tempRange);
        this.mTvCity = (TextView) this.mWeatherView.findViewById(R.id.city);
        this.mTvDetails = (TextView) this.mWeatherView.findViewById(R.id.details);
        ArrayList arrayList = new ArrayList();
        this.mViewWeatherDetails = arrayList;
        arrayList.add(this.mWeatherView.findViewById(R.id.viewWeatherDetail0));
        this.mViewWeatherDetails.add(this.mWeatherView.findViewById(R.id.viewWeatherDetail1));
        this.mViewWeatherDetails.add(this.mWeatherView.findViewById(R.id.viewWeatherDetail2));
        ArrayList arrayList2 = new ArrayList();
        this.mImgWeatherDetails = arrayList2;
        arrayList2.add((ImageView) this.mWeatherView.findViewById(R.id.imgWeatherDetail0));
        this.mImgWeatherDetails.add((ImageView) this.mWeatherView.findViewById(R.id.imgWeatherDetail1));
        this.mImgWeatherDetails.add((ImageView) this.mWeatherView.findViewById(R.id.imgWeatherDetail2));
        ArrayList arrayList3 = new ArrayList();
        this.mTvWeatherDetails = arrayList3;
        arrayList3.add((TextView) this.mWeatherView.findViewById(R.id.weatherDetail0));
        this.mTvWeatherDetails.add((TextView) this.mWeatherView.findViewById(R.id.weatherDetail1));
        this.mTvWeatherDetails.add((TextView) this.mWeatherView.findViewById(R.id.weatherDetail2));
        Button button = (Button) this.mWeatherView.findViewById(R.id.btnDelete);
        if (button != null) {
            button.setOnClickListener(new View.OnClickListener() {
                public final void onClick(View view) {
                    WeatherItem.this.lambda$init$0$WeatherItem(view);
                }
            });
        }
        updateInfo();
        setWeatherInfo(this.mainPageUIController.mWeatherInfo, this.mainPageUIController.mWeatherDetailMap, this.mainPageUIController.mWeatherDetailList);
        refreshWeatherFailed(this.mainPageUIController.mWeatherFailedCode);
    }

    public /* synthetic */ void lambda$init$0$WeatherItem(View view) {
        if (this.mainPageUIController != null) {
            SysProviderOpt.getInstance(this.mContext).updateRecord(SysProviderOpt.KSW_HAVE_WEATHER_MAIN, "0");
            this.mainPageUIController.refreshMainItem();
        }
    }

    public void setWeatherInfo(WeatherInfo weatherInfo, HashMap<String, String> hashMap, List<String> list) {
        View view = this.mLayoutWeatherContent;
        if (view != null) {
            view.setVisibility(0);
        }
        View view2 = this.mLayoutWeatherStatus;
        if (view2 != null) {
            view2.setVisibility(8);
        }
        this.mWeatherInfo = weatherInfo;
        refreshIcon(hashMap, list);
    }

    public void refreshWeatherFailed(int i) {
        String str;
        Log.d(TAG, "refreshWeatherFailed errorCode = " + i);
        View view = this.mLayoutWeatherContent;
        int i2 = 0;
        if (view != null) {
            view.setVisibility(i == 0 ? 0 : 8);
        }
        View view2 = this.mLayoutWeatherStatus;
        if (view2 != null) {
            if (i == 0) {
                i2 = 8;
            }
            view2.setVisibility(i2);
        }
        ImageView imageView = this.mImgWeatherStatus;
        if (imageView != null) {
            imageView.setBackgroundResource(R.drawable.id8_main_icon_weather_error);
        }
        if (this.mTvWeatherStatus != null) {
            if (i == 3) {
                str = this.mContext.getString(R.string.lb_weather_result_failed);
            } else if (i != 4) {
                str = i != 5 ? this.mContext.getString(R.string.lb_weather_result_loading) : this.mContext.getString(R.string.lb_weather_result_no_gps);
            } else {
                str = this.mContext.getString(R.string.lb_weather_result_unactivated);
            }
            this.mTvWeatherStatus.setText(str);
        }
    }

    private void refreshIcon(HashMap<String, String> hashMap, List<String> list) {
        WeatherInfo weatherInfo;
        if (!(this.mWeatherIconBase == null || (weatherInfo = this.mWeatherInfo) == null)) {
            if (this.mBk != null) {
                this.mBk.setBackgroundResource(this.mWeatherIconBase.getWeatherIconId(weatherInfo.getPhraseID(), this.mSmallMode, this.m_iModeSet));
            }
            if (this.mImgWeather != null) {
                this.mImgWeather.setBackgroundResource(this.mWeatherIconBase.getWeatherIconIdBig(this.mWeatherInfo.getPhraseID(), this.mSmallMode, this.m_iModeSet));
            }
            if (this.mTvTemp != null) {
                this.mTvTemp.setText(this.mWeatherInfo.getCurTemp());
            }
            if (this.mTvTempUnit != null) {
                this.mTvTempUnit.setText(this.mWeatherInfo.getCurTempUnit());
            }
            if (this.mTvTempRang != null) {
                this.mTvTempRang.setText(this.mWeatherInfo.getMinTemp() + "~" + this.mWeatherInfo.getMaxTemp() + this.mWeatherInfo.getCurTempUnit());
            }
            if (this.mTvCity != null) {
                this.mTvCity.setText(this.mWeatherInfo.getCurCity());
            }
            if (this.mTvDetails != null) {
                this.mTvDetails.setText(this.mWeatherInfo.getPhrase());
            }
            List<HourWeather> hourWeathers = this.mWeatherInfo.getHourWeathers();
            if (hourWeathers != null && hourWeathers.size() >= 5) {
                updateBottomInfo(this.mImgWeatherIcon1, this.mTvTime1, this.mTvTemp1, hourWeathers.get(0));
                updateBottomInfo(this.mImgWeatherIcon2, this.mTvTime2, this.mTvTemp2, hourWeathers.get(1));
                updateBottomInfo(this.mImgWeatherIcon3, this.mTvTime3, this.mTvTemp3, hourWeathers.get(2));
                updateBottomInfo(this.mImgWeatherIcon4, this.mTvTime4, this.mTvTemp4, hourWeathers.get(3));
                updateBottomInfo(this.mImgWeatherIcon5, this.mTvTime5, this.mTvTemp5, hourWeathers.get(4));
            }
        }
        if (hashMap != null && list != null && this.mViewWeatherDetails != null && this.mTvWeatherDetails != null && this.mImgWeatherDetails != null) {
            for (int i = 0; i < 3; i++) {
                View view = this.mViewWeatherDetails.get(i);
                ImageView imageView = this.mImgWeatherDetails.get(i);
                TextView textView = this.mTvWeatherDetails.get(i);
                if (i < list.size()) {
                    if (view != null) {
                        view.setVisibility(0);
                    }
                    if (imageView != null) {
                        imageView.setBackground(WeatherIconUtil.getWeatherCardDetailIcon(this.mContext, list.get(i), this.mSmallMode));
                    }
                    if (textView != null) {
                        textView.setText(hashMap.get(list.get(i)));
                    }
                } else if (view != null) {
                    view.setVisibility(8);
                }
            }
        }
    }

    private void updateBottomInfo(ImageView imageView, TextView textView, TextView textView2, HourWeather hourWeather) {
        WeatherInfo weatherInfo;
        if (this.mWeatherIconBase != null && (weatherInfo = this.mWeatherInfo) != null) {
            String curTempUnit = weatherInfo.getCurTempUnit();
            int weatherIconIdMini = this.mWeatherIconBase.getWeatherIconIdMini(hourWeather.getPhraseID(), this.mSmallMode);
            if (imageView != null) {
                imageView.setBackgroundResource(weatherIconIdMini);
            }
            if (textView != null) {
                String time = hourWeather.getTime();
                Spannable formatTime = formatTime(time);
                if (formatTime == null) {
                    textView.setText(time);
                } else {
                    textView.setText(formatTime);
                }
            }
            if (textView2 != null) {
                textView2.setText(hourWeather.getTemperature() + curTempUnit);
            }
        }
    }

    public void updateInfo() {
        if (this.mTitle != null) {
            this.mTitle.setText(this.mContext.getString(R.string.lb_weather).toUpperCase());
        }
        this.mMode = SysProviderOpt.getInstance(this.mContext).getRecordInteger("KESAIWEI_SYS_DISPLAY_MODE", 0);
        if (this.m_iModeSet == 20) {
            int i = this.mMode;
            if (i == 0) {
                TextView textView = this.mTitle;
                if (textView != null) {
                    textView.setTextColor(getColor(R.color.bmw_id8_mainpage_item_title_color));
                }
                ImageView imageView = this.imgBk;
                if (imageView != null) {
                    imageView.setImageResource(this.mSmallMode ? R.drawable.pemp_bmw_id8_weather_item_background_blue_small : R.drawable.pemp_bmw_id8_weather_item_background_blue);
                }
                View view = this.imgLine;
                if (view != null) {
                    view.setBackgroundResource(this.mSmallMode ? R.drawable.pemp_id8_main_edit_icon_weather_line_b : R.drawable.pemp_id8_main_icon_weather_line_b);
                }
            } else if (i == 1) {
                TextView textView2 = this.mTitle;
                if (textView2 != null) {
                    textView2.setTextColor(getColor(R.color.bmw_id8_mainpage_item_title_color_sport));
                }
                ImageView imageView2 = this.imgBk;
                if (imageView2 != null) {
                    imageView2.setImageResource(this.mSmallMode ? R.drawable.pemp_bmw_id8_weather_item_background_red_small : R.drawable.pemp_bmw_id8_weather_item_background_red);
                }
                View view2 = this.imgLine;
                if (view2 != null) {
                    view2.setBackgroundResource(this.mSmallMode ? R.drawable.pemp_id8_main_edit_icon_weather_line_r : R.drawable.pemp_id8_main_icon_weather_line_r);
                }
            } else if (i == 2) {
                TextView textView3 = this.mTitle;
                if (textView3 != null) {
                    textView3.setTextColor(getColor(R.color.bmw_id8_mainpage_item_title_color_personal));
                }
                ImageView imageView3 = this.imgBk;
                if (imageView3 != null) {
                    imageView3.setImageResource(this.mSmallMode ? R.drawable.pemp_bmw_id8_weather_item_background_yellow_small : R.drawable.pemp_bmw_id8_weather_item_background_yellow);
                }
                View view3 = this.imgLine;
                if (view3 != null) {
                    view3.setBackgroundResource(this.mSmallMode ? R.drawable.pemp_id8_main_edit_icon_weather_line_y : R.drawable.pemp_id8_main_icon_weather_line_y);
                }
            }
        } else {
            int i2 = this.mMode;
            if (i2 == 0) {
                TextView textView4 = this.mTitle;
                if (textView4 != null) {
                    textView4.setTextColor(getColor(R.color.bmw_id8_mainpage_item_title_color));
                }
                ImageView imageView4 = this.imgBk;
                if (imageView4 != null) {
                    imageView4.setImageResource(this.mSmallMode ? R.drawable.bmw_id8_weather_item_background_blue_small : R.drawable.bmw_id8_weather_item_background_blue);
                }
                View view4 = this.imgLine;
                if (view4 != null) {
                    view4.setBackgroundResource(this.mSmallMode ? R.drawable.id8_main_edit_icon_weather_line_b : R.drawable.id8_main_icon_weather_line_b);
                }
            } else if (i2 == 1) {
                TextView textView5 = this.mTitle;
                if (textView5 != null) {
                    textView5.setTextColor(getColor(R.color.bmw_id8_mainpage_item_title_color_sport));
                }
                ImageView imageView5 = this.imgBk;
                if (imageView5 != null) {
                    imageView5.setImageResource(this.mSmallMode ? R.drawable.bmw_id8_weather_item_background_red_small : R.drawable.bmw_id8_weather_item_background_red);
                }
                View view5 = this.imgLine;
                if (view5 != null) {
                    view5.setBackgroundResource(this.mSmallMode ? R.drawable.id8_main_edit_icon_weather_line_r : R.drawable.id8_main_icon_weather_line_r);
                }
            } else if (i2 == 2) {
                TextView textView6 = this.mTitle;
                if (textView6 != null) {
                    textView6.setTextColor(getColor(R.color.bmw_id8_mainpage_item_title_color_personal));
                }
                ImageView imageView6 = this.imgBk;
                if (imageView6 != null) {
                    imageView6.setImageResource(this.mSmallMode ? R.drawable.bmw_id8_weather_item_background_yellow_small : R.drawable.bmw_id8_weather_item_background_yellow);
                }
                View view6 = this.imgLine;
                if (view6 != null) {
                    view6.setBackgroundResource(this.mSmallMode ? R.drawable.id8_main_edit_icon_weather_line_y : R.drawable.id8_main_icon_weather_line_y);
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
                    imageView4.setForeground(getDrawable(this.mSmallMode ? R.drawable.pemp_id8_main_edit_icon_weather_b_f : R.drawable.pemp_id8_main_icon_weather_b_f));
                }
            } else if (i == 1) {
                ImageView imageView5 = this.imgBk;
                if (imageView5 != null) {
                    imageView5.setForeground(getDrawable(this.mSmallMode ? R.drawable.pemp_id8_main_edit_icon_weather_r_f : R.drawable.pemp_id8_main_icon_weather_r_f));
                }
            } else if (i == 2 && (imageView2 = this.imgBk) != null) {
                imageView2.setForeground(getDrawable(this.mSmallMode ? R.drawable.pemp_id8_main_edit_icon_weather_y_f : R.drawable.pemp_id8_main_icon_weather_y_f));
            }
        } else {
            int i2 = this.mMode;
            if (i2 == 0) {
                ImageView imageView6 = this.imgBk;
                if (imageView6 != null) {
                    imageView6.setForeground(getDrawable(this.mSmallMode ? R.drawable.id8_main_edit_icon_weather_b_f : R.drawable.id8_main_icon_weather_b_f));
                }
            } else if (i2 == 1) {
                ImageView imageView7 = this.imgBk;
                if (imageView7 != null) {
                    imageView7.setForeground(getDrawable(this.mSmallMode ? R.drawable.id8_main_edit_icon_weather_r_f : R.drawable.id8_main_icon_weather_r_f));
                }
            } else if (i2 == 2 && (imageView = this.imgBk) != null) {
                imageView.setForeground(getDrawable(this.mSmallMode ? R.drawable.id8_main_edit_icon_weather_y_f : R.drawable.id8_main_icon_weather_y_f));
            }
        }
    }

    public int[] getViewSize() {
        if (this.mSmallMode) {
            return new int[]{EventUtils.getPx(this.mContext, 316), (int) this.mContext.getResources().getDimension(R.dimen.screenHeight)};
        }
        return new int[]{EventUtils.getPx(this.mContext, 434), (int) this.mContext.getResources().getDimension(R.dimen.screenHeight)};
    }

    private Drawable getDrawable(int i) {
        return this.mContext.getResources().getDrawable(i, (Resources.Theme) null);
    }

    private int getColor(int i) {
        return this.mContext.getResources().getColor(i, (Resources.Theme) null);
    }

    private Spannable formatTime(String str) {
        String str2;
        try {
            if (TextUtils.isEmpty(str)) {
                return null;
            }
            Log.i("TAG", "formatTime: " + str);
            String substring = str.substring(0, 2);
            Log.i("TAG", "formatTime: " + substring);
            Log.i("TAG", "formatTime: " + substring.length());
            int parseInt = Integer.parseInt(substring);
            int i = 12;
            if (parseInt > 12) {
                str2 = (parseInt - 12) + "PM";
            } else {
                str2 = parseInt + "AM";
            }
            SpannableString spannableString = new SpannableString(str2);
            if (!this.mSmallMode) {
                i = 17;
            }
            spannableString.setSpan(new AbsoluteSizeSpan(i, false), str2.length() - 2, str2.length(), 17);
            return spannableString;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
