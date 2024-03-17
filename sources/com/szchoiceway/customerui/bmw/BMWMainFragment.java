package com.szchoiceway.customerui.bmw;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import com.szchoiceway.customerui.bmw.ui.MainPageUIController;
import com.szchoiceway.customerui.bmw.ui.UIControllerBase;
import com.szchoiceway.customerui.bmw.weather.WeatherInfo;
import com.szchoiceway.customerui.drag.DragAppInfo;
import com.szchoiceway.customerui.fragment.MainFragment;
import com.szchoiceway.zxwlib.focus.FocusObserver;
import com.txznet.weatherquery.TXZWeather;
import com.txznet.weatherquery.WeatherQueryManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BMWMainFragment extends MainFragment {
    private static final String TAG = "BMWMainFragment";
    private Activity activity;
    private boolean isReg = false;
    MainFragmentCallBack mMainFragmentCallBack;
    /* access modifiers changed from: private */
    public UIControllerBase mUIController;
    /* access modifiers changed from: private */
    public List<String> mWeatherDetailList;
    /* access modifiers changed from: private */
    public HashMap<String, String> mWeatherDetailMap;
    private WeatherQueryManager weatherQueryManager;

    public interface MainFragmentCallBack {
        void setInEditMode(boolean z);
    }

    public void hideDialog() {
    }

    /* access modifiers changed from: protected */
    public void initEvent(View view) {
    }

    public void setRotatingSpeed(int i) {
    }

    public void setTempValue(String str) {
    }

    public BMWMainFragment(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    /* access modifiers changed from: protected */
    public int inflateLayout() {
        this.mUIController = new MainPageUIController(this.mContext);
        Log.i(TAG, "inflateLayout:BMWMainFragment ");
        return this.mUIController.getLayout();
    }

    public void onResume() {
        super.onResume();
        refreshWeatherItem();
    }

    public void refreshWeatherItem() {
        UIControllerBase uIControllerBase = this.mUIController;
        if (uIControllerBase != null) {
            uIControllerBase.refreshMainItem();
        }
    }

    /* access modifiers changed from: protected */
    public void initView(View view) {
        this.activity = getActivity();
        UIControllerBase uIControllerBase = this.mUIController;
        if (uIControllerBase != null) {
            uIControllerBase.init(view, this);
        }
        try {
            this.weatherQueryManager = WeatherQueryManager.getInstance();
            Log.i(TAG, "initView:isReg " + this.isReg);
            if (!this.isReg) {
                this.isReg = true;
                this.weatherQueryManager.setUserSettingListener(this.activity, new WeatherQueryManager.UserSettingListener() {
                    public void noticeChange() {
                        Log.d(BMWMainFragment.TAG, "weather update");
                        BMWMainFragment.this.refreshWeatherView();
                    }
                });
            }
            refreshWeatherView();
        } catch (Exception unused) {
        }
    }

    /* access modifiers changed from: protected */
    public void unInit() {
        super.unInit();
        UIControllerBase uIControllerBase = this.mUIController;
        if (uIControllerBase != null) {
            uIControllerBase.unInit();
        }
    }

    public void refreshPlayState() {
        UIControllerBase uIControllerBase = this.mUIController;
        if (uIControllerBase != null) {
            uIControllerBase.refreshPlayState();
        }
    }

    public void setHandbrakeStatus(boolean z) {
        UIControllerBase uIControllerBase = this.mUIController;
        if (uIControllerBase != null) {
            uIControllerBase.setHandbrakeStatus(z);
        }
    }

    public void setSeatBeltStatus(boolean z) {
        UIControllerBase uIControllerBase = this.mUIController;
        if (uIControllerBase != null) {
            uIControllerBase.setSeatBeltStatus(z);
        }
    }

    public void setSpeed(int i) {
        UIControllerBase uIControllerBase = this.mUIController;
        if (uIControllerBase != null) {
            uIControllerBase.setSpeed(i);
        }
    }

    public void setSpeedUnit() {
        UIControllerBase uIControllerBase = this.mUIController;
        if (uIControllerBase != null) {
            uIControllerBase.setSpeedUnit();
        }
    }

    public void setQilValue(String str) {
        UIControllerBase uIControllerBase = this.mUIController;
        if (uIControllerBase != null) {
            uIControllerBase.setQilValue(str);
        }
    }

    public void setMusicCoverBg(Bitmap bitmap) {
        UIControllerBase uIControllerBase = this.mUIController;
        if (uIControllerBase != null) {
            uIControllerBase.setMusicCoverBg(bitmap);
        }
    }

    public void refreshBTMessage() {
        UIControllerBase uIControllerBase = this.mUIController;
        if (uIControllerBase != null) {
            uIControllerBase.setBtStatus();
        }
    }

    public void setMainFocusMove(int i, boolean z) {
        UIControllerBase uIControllerBase = this.mUIController;
        if (uIControllerBase != null) {
            uIControllerBase.setMainFocusMove(i, z);
        }
    }

    public void setCurPlayVideoPath(String str) {
        UIControllerBase uIControllerBase = this.mUIController;
        if (uIControllerBase != null) {
            uIControllerBase.setCurPlayVideoPath(str);
        }
    }

    public void setFocusObserver(FocusObserver focusObserver) {
        UIControllerBase uIControllerBase = this.mUIController;
        if (uIControllerBase != null) {
            uIControllerBase.setFocusObserver(focusObserver);
        }
    }

    public boolean onKeyDown(int i) {
        UIControllerBase uIControllerBase = this.mUIController;
        if (uIControllerBase != null) {
            return uIControllerBase.onKeyDown(i);
        }
        return false;
    }

    public void addItem(DragAppInfo dragAppInfo) {
        UIControllerBase uIControllerBase = this.mUIController;
        if (uIControllerBase != null) {
            uIControllerBase.addItem(dragAppInfo);
        }
    }

    public void setEditMode(boolean z) {
        MainFragmentCallBack mainFragmentCallBack = this.mMainFragmentCallBack;
        if (mainFragmentCallBack != null) {
            mainFragmentCallBack.setInEditMode(z);
        }
    }

    public void setMainFragmentCallBack(MainFragmentCallBack mainFragmentCallBack) {
        this.mMainFragmentCallBack = mainFragmentCallBack;
    }

    /* access modifiers changed from: protected */
    public void onVisibilityChanged(View view, int i) {
        super.onVisibilityChanged(view, i);
        Log.i(TAG, "onVisibilityChanged: " + i);
        UIControllerBase uIControllerBase = this.mUIController;
        if (uIControllerBase != null) {
            uIControllerBase.onVisibilityChanged(i);
            refreshWeatherView();
        }
    }

    private Activity getActivity() {
        View view;
        do {
            Context context = this.getContext();
            Log.d(TAG, "view: " + this + ", context: " + context);
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
        if (this.mWeatherDetailMap == null) {
            this.mWeatherDetailMap = new HashMap<>();
        }
        if (this.mWeatherDetailList == null) {
            this.mWeatherDetailList = new ArrayList();
        }
        this.weatherQueryManager.sendWeatherRequest(this.activity, new WeatherQueryManager.WeatherCallback() {
            public void onSuccess(TXZWeather tXZWeather, Bundle bundle) {
                Log.d(BMWMainFragment.TAG, "weatherRequest onSuccess");
                if (tXZWeather.getCity() != null) {
                    BMWMainFragment.this.mWeatherDetailList.clear();
                    BMWMainFragment.this.mWeatherDetailMap.clear();
                    if (bundle != null) {
                        try {
                            if (bundle.get("details") instanceof String[]) {
                                for (String str : (String[]) bundle.get("details")) {
                                    if (!"3".equals(str)) {
                                        BMWMainFragment.this.mWeatherDetailList.add(str);
                                    } else if (tXZWeather.getSnowingProbability() == null || "".equals(tXZWeather.getSnowingProbability())) {
                                        BMWMainFragment.this.mWeatherDetailList.add("3-rain");
                                    } else {
                                        BMWMainFragment.this.mWeatherDetailList.add("3-snow");
                                    }
                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    BMWMainFragment.this.mWeatherDetailMap.put("0", tXZWeather.getWindSpeed());
                    BMWMainFragment.this.mWeatherDetailMap.put("1", tXZWeather.getHumidity());
                    BMWMainFragment.this.mWeatherDetailMap.put("2", tXZWeather.getVisibility());
                    if (tXZWeather.getSnowingProbability() != null && !"".equals(tXZWeather.getSnowingProbability())) {
                        BMWMainFragment.this.mWeatherDetailMap.put("3-snow", tXZWeather.getSnowingProbability());
                    } else if (tXZWeather.getSnowingProbability() == null || "".equals(tXZWeather.getRainingProbability()) || "%".equals(tXZWeather.getRainingProbability())) {
                        BMWMainFragment.this.mWeatherDetailMap.put("3-rain", "0%");
                    } else {
                        BMWMainFragment.this.mWeatherDetailMap.put("3-rain", tXZWeather.getRainingProbability());
                    }
                    BMWMainFragment.this.mWeatherDetailMap.put("4", tXZWeather.getUvIndex());
                    WeatherInfo weatherInfo = new WeatherInfo();
                    weatherInfo.setCurCity(tXZWeather.getCity());
                    weatherInfo.setPhrase(tXZWeather.getPhrase());
                    weatherInfo.setPhraseID(tXZWeather.getPhraseID());
                    weatherInfo.setCurTemp(tXZWeather.getTemperature());
                    weatherInfo.setCurTempUnit(tXZWeather.getTemperUnit());
                    weatherInfo.setMinTemp(tXZWeather.getMinTemperature());
                    weatherInfo.setMaxTemp(tXZWeather.getMaxTemperature());
                    weatherInfo.setCurWind(tXZWeather.getWindSpeed());
                    weatherInfo.setCurVisiblity(tXZWeather.getVisibility());
                    weatherInfo.setCurRain(tXZWeather.getRainingProbability());
                    weatherInfo.setHourWeathers(tXZWeather.getHoursData());
                    if (BMWMainFragment.this.mUIController != null) {
                        BMWMainFragment.this.mUIController.refreshWeatherInfo(weatherInfo, BMWMainFragment.this.mWeatherDetailMap, BMWMainFragment.this.mWeatherDetailList);
                        BMWMainFragment.this.mUIController.refreshWeatherFailed(0);
                    }
                }
            }

            public void onFailed(int i) {
                Log.d(BMWMainFragment.TAG, "weatherRequest onFailed errorCode = " + i);
                if (BMWMainFragment.this.mUIController != null) {
                    BMWMainFragment.this.mUIController.refreshWeatherFailed(i);
                }
            }
        });
    }

    /* access modifiers changed from: protected */
    public void onRestoreInstanceState(Parcelable parcelable) {
        Log.i(TAG, "onRestoreInstanceState: xxxxxxxxxxxxxx------------------------");
        try {
            super.onRestoreInstanceState(parcelable);
        } catch (Exception unused) {
        }
    }
}
