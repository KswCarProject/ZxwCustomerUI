package com.szchoiceway.customerui.views;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.icu.text.DateTimePatternGenerator;
import android.os.Handler;
import android.text.format.DateFormat;
import android.util.AttributeSet;
import android.widget.TextView;
import com.szchoiceway.customerui.views.ClockTextView;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

public class ClockTextView extends TextView {
    private static final int AM_PM_STYLE_GONE = 2;
    private static final int AM_PM_STYLE_NORMAL = 0;
    private static final int AM_PM_STYLE_SMALL = 1;
    public static final String CLOCK_TEXT_ACTION = "com.szchoiceway.customerui.views";
    public static final String EXTRA_TIMEZONE = "time-zone";
    public static final String KEY_IS_24 = "is24";
    public static final String KEY_TIME = "time";
    private static final String TAG = "ClockTextView";
    private final int mAmPmStyle = 0;
    private boolean mAttached;
    /* access modifiers changed from: private */
    public Calendar mCalendar;
    /* access modifiers changed from: private */
    public SimpleDateFormat mClockFormat;
    /* access modifiers changed from: private */
    public String mClockFormatString;
    private SimpleDateFormat mContentDescriptionFormat;
    private final BroadcastReceiver mIntentReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            Handler handler = ClockTextView.this.getHandler();
            if (handler != null) {
                String action = intent.getAction();
                if (action.equals("android.intent.action.TIMEZONE_CHANGED")) {
                    handler.post(new Runnable(intent.getStringExtra(ClockTextView.EXTRA_TIMEZONE)) {
                        public final /* synthetic */ String f$1;

                        {
                            this.f$1 = r2;
                        }

                        public final void run() {
                            ClockTextView.AnonymousClass1.this.lambda$onReceive$0$ClockTextView$1(this.f$1);
                        }
                    });
                } else if (action.equals("android.intent.action.CONFIGURATION_CHANGED")) {
                    handler.post(new Runnable(ClockTextView.this.getResources().getConfiguration().locale) {
                        public final /* synthetic */ Locale f$1;

                        {
                            this.f$1 = r2;
                        }

                        public final void run() {
                            ClockTextView.AnonymousClass1.this.lambda$onReceive$1$ClockTextView$1(this.f$1);
                        }
                    });
                }
                handler.post(new Runnable() {
                    public final void run() {
                        ClockTextView.AnonymousClass1.this.lambda$onReceive$2$ClockTextView$1();
                    }
                });
            }
        }

        public /* synthetic */ void lambda$onReceive$0$ClockTextView$1(String str) {
            Calendar unused = ClockTextView.this.mCalendar = Calendar.getInstance(TimeZone.getTimeZone(str));
            if (ClockTextView.this.mClockFormat != null) {
                ClockTextView.this.mClockFormat.setTimeZone(ClockTextView.this.mCalendar.getTimeZone());
            }
        }

        public /* synthetic */ void lambda$onReceive$1$ClockTextView$1(Locale locale) {
            if (!locale.equals(ClockTextView.this.mLocale)) {
                Locale unused = ClockTextView.this.mLocale = locale;
                String unused2 = ClockTextView.this.mClockFormatString = "";
            }
        }

        public /* synthetic */ void lambda$onReceive$2$ClockTextView$1() {
            ClockTextView.this.updateClock();
        }
    };
    /* access modifiers changed from: private */
    public Locale mLocale;
    private boolean mShowSeconds = false;

    public ClockTextView(Context context) {
        super(context);
    }

    public ClockTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public ClockTextView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public ClockTextView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (!this.mAttached) {
            this.mAttached = true;
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.intent.action.TIME_TICK");
            intentFilter.addAction("android.intent.action.TIME_SET");
            intentFilter.addAction("android.intent.action.TIMEZONE_CHANGED");
            intentFilter.addAction("android.intent.action.CONFIGURATION_CHANGED");
            intentFilter.addAction("android.intent.action.USER_SWITCHED");
            this.mContext.registerReceiver(this.mIntentReceiver, intentFilter);
        }
        this.mCalendar = Calendar.getInstance(TimeZone.getDefault());
        this.mClockFormatString = "";
        updateClock();
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.mAttached) {
            this.mAttached = false;
            this.mContext.unregisterReceiver(this.mIntentReceiver);
        }
    }

    private final CharSequence getSmallTime() {
        String str;
        SimpleDateFormat simpleDateFormat;
        Context context = getContext();
        boolean is24HourFormat = DateFormat.is24HourFormat(context);
        DateTimePatternGenerator instance = DateTimePatternGenerator.getInstance(context.getResources().getConfiguration().locale);
        if (this.mShowSeconds) {
            str = instance.getBestPattern(is24HourFormat ? "Hms" : "hms");
        } else {
            str = instance.getBestPattern(is24HourFormat ? "Hm" : "hm");
        }
        if (!str.equals(this.mClockFormatString)) {
            this.mContentDescriptionFormat = new SimpleDateFormat(str);
            simpleDateFormat = new SimpleDateFormat(str);
            this.mClockFormat = simpleDateFormat;
            this.mClockFormatString = str;
        } else {
            simpleDateFormat = this.mClockFormat;
        }
        return simpleDateFormat.format(this.mCalendar.getTime());
    }

    /* access modifiers changed from: package-private */
    public final void updateClock() {
        this.mCalendar.setTimeInMillis(System.currentTimeMillis());
        CharSequence smallTime = getSmallTime();
        Intent intent = new Intent();
        intent.setAction(CLOCK_TEXT_ACTION);
        intent.putExtra("time", smallTime);
        intent.putExtra(KEY_IS_24, DateFormat.is24HourFormat(getContext()));
        getContext().sendBroadcast(intent);
        setText(smallTime);
        setContentDescription(this.mContentDescriptionFormat.format(this.mCalendar.getTime()));
    }
}
