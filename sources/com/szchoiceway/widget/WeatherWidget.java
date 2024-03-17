package com.szchoiceway.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.RemoteViews;
import com.mapzen.valhalla.TransitStop;
import com.szchoiceway.customerui.R;
import com.szchoiceway.widget.base.BaseApp;
import com.szchoiceway.widget.base.BaseWidget;

public class WeatherWidget extends BaseWidget {
    public static final String ACTION_WEATHER_LOAD = "com.choiceway.action.WEATHER_LOAD";
    public static final String ACTION_WEATHER_LOAD2 = "com.choiceway.action.VIDEO_MODE_CHANGED";
    public static final String ACTION_WEATHER_UPDATE = "com.choiceway.action.WEATHER_UPDATE";
    private static final String TAG = "WeatherWidget";
    private String mCode = "1";
    private String mName = "深圳";
    private RemoteViews mRemoteViews;
    private String mTemperature = "24℃";
    private String mText = "晴";

    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] iArr) {
        super.onUpdate(context, appWidgetManager, iArr);
        this.mRemoteViews = new RemoteViews(context.getPackageName(), R.layout.layout_widget_weather);
        Log.i(TAG, "onUpdate: ");
        Intent intent = new Intent();
        intent.setClassName("com.choiceway.weather", "com.choiceway.weather.MainActivity");
        this.mRemoteViews.setOnClickPendingIntent(R.id.layout_weather_root, PendingIntent.getActivity(context, 31, intent, 0));
        getWeather();
    }

    public void onDeleted(Context context, int[] iArr) {
        super.onDeleted(context, iArr);
    }

    public void onReceive(Intent intent) {
        if (ACTION_WEATHER_UPDATE.equals(intent.getAction())) {
            Log.i(TAG, "onReceive: 收到天气数据更新了...");
            Bundle bundleExtra = intent.getBundleExtra("results");
            this.mName = bundleExtra.getString(TransitStop.KEY_NAME, "深圳");
            this.mText = bundleExtra.getString("text", "晴");
            this.mCode = bundleExtra.getString("code", "1");
            this.mTemperature = bundleExtra.getString("temperature", "23℃");
            updataWeather();
        }
    }

    private void getWeather() {
        Intent intent = new Intent();
        intent.setComponent(new ComponentName("com.choiceway.weather", "com.choiceway.weather.service.WeatherService"));
        BaseApp.getContext().startForegroundService(intent);
    }

    private void updataWeather() {
        String str = this.mName + this.mText + this.mCode + this.mTemperature;
        Log.i(TAG, "updataWeather: info = " + str);
        this.mRemoteViews.setTextViewText(R.id.tv_weather_info, str);
        AppWidgetManager.getInstance(BaseApp.getContext()).updateAppWidget(new ComponentName(BaseApp.getContext(), WeatherWidget.class), this.mRemoteViews);
    }
}
