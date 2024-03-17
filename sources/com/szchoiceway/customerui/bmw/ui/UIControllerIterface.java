package com.szchoiceway.customerui.bmw.ui;

import android.graphics.Bitmap;
import android.view.View;
import com.szchoiceway.customerui.bmw.BMWMainFragment;
import com.szchoiceway.customerui.bmw.weather.WeatherInfo;
import com.szchoiceway.customerui.drag.DragAppInfo;
import com.szchoiceway.zxwlib.focus.FocusObserver;
import java.util.HashMap;
import java.util.List;

public interface UIControllerIterface {
    void addItem(DragAppInfo dragAppInfo);

    int getLayout();

    void init(View view, BMWMainFragment bMWMainFragment);

    boolean onKeyDown(int i);

    void onVisibilityChanged(int i);

    void refreshMainItem();

    void refreshPlayState();

    void refreshWeatherFailed(int i);

    void refreshWeatherInfo(WeatherInfo weatherInfo, HashMap<String, String> hashMap, List<String> list);

    void setBtStatus();

    void setCurPlayVideoPath(String str);

    void setFocusObserver(FocusObserver focusObserver);

    void setHandbrakeStatus(boolean z);

    void setMainFocusMove(int i, boolean z);

    void setMusicCoverBg(Bitmap bitmap);

    void setQilValue(String str);

    void setSeatBeltStatus(boolean z);

    void setSpeed(int i);

    void setSpeedUnit();

    void unInit();
}
