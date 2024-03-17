package com.szchoiceway.customerui.utils;

import android.app.Activity;
import android.content.Context;
import android.os.SystemClock;
import android.provider.Settings;
import android.text.TextUtils;
import android.text.format.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

public class TimeHelps {
    public static final int DELAY_MILLISECONDS_HH = 3600000;
    public static final int DELAY_MILLISECONDS_dd = 86400000;
    public static final int DELAY_MILLISECONDS_mm = 60000;
    public static final int DELAY_MILLISECONDS_ss = 1000;

    public static String getPlayTime(long j) {
        long j2 = j / 3600;
        long j3 = j % 3600;
        return String.format("%02d:%02d:%02d", new Object[]{Long.valueOf(j2), Long.valueOf(j3 / 60), Long.valueOf(j3 % 60)});
    }

    public static String getPlayTime2(long j) {
        long j2 = j / 3600;
        long j3 = j % 3600;
        long j4 = j3 / 60;
        long j5 = j3 % 60;
        if (j2 <= 0) {
            return String.format("%02d:%02d", new Object[]{Long.valueOf(j4), Long.valueOf(j5)});
        }
        return String.format("%02d:%02d:%02d", new Object[]{Long.valueOf(j2), Long.valueOf(j4), Long.valueOf(j5)});
    }

    public static long dateStr2Timestamp(String str, SimpleDateFormat simpleDateFormat) {
        try {
            return simpleDateFormat.parse(str).getTime() / 1000;
        } catch (Exception e) {
            LogHelps.e("Exception wrong:" + e.getMessage());
            return 0;
        }
    }

    public static String getSimpleDateTime(String str, SimpleDateFormat simpleDateFormat) {
        if (str == null || str.isEmpty()) {
            return "";
        }
        if (str.length() != 10) {
            return str;
        }
        try {
            return simpleDateFormat.format(new Date(new Date(Long.parseLong(str) * 1000).getTime()));
        } catch (Exception e) {
            LogHelps.e("Exception wrong:" + e.getMessage());
            return str;
        }
    }

    public static String getSimpleDateTime(long j, SimpleDateFormat simpleDateFormat) {
        if (j <= 0) {
            return "";
        }
        if (((long) 0) + j != 13) {
            return j + "";
        }
        try {
            return simpleDateFormat.format(new Date(j));
        } catch (Exception e) {
            LogHelps.e("Exception wrong:" + e.getMessage());
            return j + "";
        }
    }

    public static String getWeekOfDate(long j) {
        Date date = new Date(j * 1000);
        String[] strArr = {"周日", "周一", "周二", "周三", "周四", "周五", "周六"};
        Calendar instance = Calendar.getInstance();
        instance.setTime(date);
        int i = instance.get(7) - 1;
        if (i < 0) {
            i = 0;
        }
        return strArr[i];
    }

    public static int getYear() {
        return Calendar.getInstance().get(1);
    }

    public static int getMonth() {
        return Calendar.getInstance().get(2) + 1;
    }

    public static int getDay() {
        return Calendar.getInstance().get(5);
    }

    public static int getHour() {
        return Calendar.getInstance().get(11);
    }

    public static int getHour(long j) {
        Calendar instance = Calendar.getInstance();
        instance.setTimeInMillis(j);
        return instance.get(11);
    }

    public static int getMinute() {
        return Calendar.getInstance().get(12);
    }

    public static int getMinute(long j) {
        Calendar instance = Calendar.getInstance();
        instance.setTimeInMillis(j);
        return instance.get(12);
    }

    public static int getSecond() {
        return Calendar.getInstance().get(13);
    }

    public static int getSecond(long j) {
        Calendar instance = Calendar.getInstance();
        instance.setTimeInMillis(j);
        return instance.get(13);
    }

    public static void setDST(boolean z) {
        long timeInMillis = Calendar.getInstance().getTimeInMillis();
        SystemClock.setCurrentTimeMillis(z ? timeInMillis + 3600000 : timeInMillis - 3600000);
    }

    public static int getBeforePresentDay(String str) {
        if (TextUtils.isEmpty(str) || str.length() != 10) {
            return 0;
        }
        int i = 1;
        try {
            long parseLong = Long.parseLong(str) * 1000;
            long hour = (long) (getHour(parseLong) * 60 * 60 * 1000);
            long minute = (long) (getMinute(parseLong) * 60 * 1000);
            long second = (long) (getSecond(parseLong) * 60 * 1000);
            long currentTimeMillis = System.currentTimeMillis() - parseLong;
            long j = currentTimeMillis % 86400000;
            int i2 = (int) (currentTimeMillis / 86400000);
            String[] strArr = new String[1];
            try {
                strArr[0] = "getBeforePresentDay: " + hour + "***" + minute + "***" + second;
                LogHelps.i(strArr);
                return j > ((86400000 - hour) - minute) - second ? i2 + 1 : i2;
            } catch (Exception e) {
                e = e;
                i = 1;
                String[] strArr2 = new String[i];
                strArr2[0] = "Exception wrong:" + e.getMessage();
                LogHelps.e(strArr2);
                return 0;
            }
        } catch (Exception e2) {
            e = e2;
            String[] strArr22 = new String[i];
            strArr22[0] = "Exception wrong:" + e.getMessage();
            LogHelps.e(strArr22);
            return 0;
        }
    }

    public static long getNextValentineDayTimeStamp() {
        return dateStr2Timestamp((getYear() + 1) + "-02-14", new SimpleDateFormat("yyyy-MM-dd")) * 1000;
    }

    public static String getDateFormat(String str, Locale locale) {
        if (locale == null) {
            locale = Locale.CHINA;
        }
        try {
            return new SimpleDateFormat(str, locale).format(new Date());
        } catch (Exception e) {
            LogHelps.e("Exception wrong:" + e.getMessage());
            return "";
        }
    }

    public static String getDateFormat(String str) {
        return getDateFormat(str, Locale.CHINA);
    }

    public static boolean is24HourFormat(Context context) {
        return DateFormat.is24HourFormat(context);
    }

    public static boolean isTime24(Context context) {
        if (context != null) {
            return Objects.equals(getTime1224(context), "24");
        }
        LogHelps.w("wrong context null !!!");
        return false;
    }

    public static String getTime1224(Context context) {
        if (context != null) {
            return Settings.System.getString(context.getContentResolver(), "time_12_24");
        }
        LogHelps.w("wrong context null !!!");
        return "";
    }

    public static void setTime1224(Context context, String str) {
        if (context == null) {
            LogHelps.w("wrong context null !!!");
        }
        Settings.System.putString(context.getContentResolver(), "time_12_24", str);
    }

    public static String getSystemTime(Activity activity, int i, int i2, int i3, int i4) {
        String str;
        String str2;
        String str3;
        Calendar instance = Calendar.getInstance();
        String time1224 = getTime1224(activity);
        if (time1224 == null || time1224.equals("12")) {
            if (instance.get(9) == 0) {
                str3 = activity.getString(i3);
            } else {
                str3 = activity.getString(i4);
            }
            str2 = new SimpleDateFormat(activity.getString(i), Locale.getDefault()).format(new Date());
            str = str3;
        } else {
            str2 = new SimpleDateFormat(activity.getString(i2), Locale.getDefault()).format(new Date());
            str = "";
        }
        return str2 + str;
    }
}
