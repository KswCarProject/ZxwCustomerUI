package com.szchoiceway.customerui.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import androidx.core.view.PointerIconCompat;
import com.szchoiceway.customerui.R;
import com.szchoiceway.customerui.utils.SysProviderOpt;
import com.szchoiceway.zxwlib.GyroScopeWithCompassView;

public class MyQAnalogClock2 extends View {
    private static final String TAG = "MyQAnalogClock2";
    private static SysProviderOpt mSysProviderOpt;
    private final int ZXW_DASH_BOARD_REFRESH_UI;
    int availableHeight;
    int availableWidth;
    private boolean bIsRotatingSpeed;
    BitmapDrawable bmdDial;
    BitmapDrawable bmdSecond;
    int centerX;
    int centerY;
    /* access modifiers changed from: private */
    public float iCurSpeedValue;
    private int iDeviationScale;
    private int iDistanceUnitValue;
    private int iMaxRotatingSpeed;
    private int iMaxSpeed;
    private float iMaxValue;
    /* access modifiers changed from: private */
    public int iSpeedValue;
    private int iTotalScale;
    Bitmap mBmpDial;
    Bitmap mBmpSecond;
    private Context mContext;
    private int mDialDrawableId;
    int mHeigh;
    Paint mPaint;
    private int mPointerDrawableId;
    int mTempHeigh;
    int mTempWidth;
    private TextView mTvCur;
    int mWidth;
    Handler tickHandler;
    /* access modifiers changed from: private */
    public Runnable tickRunnable;

    public MyQAnalogClock2(Context context) {
        this(context, (AttributeSet) null);
    }

    public MyQAnalogClock2(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public MyQAnalogClock2(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mTempWidth = 0;
        this.availableWidth = 100;
        this.availableHeight = 100;
        this.iCurSpeedValue = 0.0f;
        this.bIsRotatingSpeed = false;
        this.mDialDrawableId = R.drawable.shisubiaopan;
        this.mPointerDrawableId = R.drawable.shisuzhizhen;
        this.iTotalScale = GyroScopeWithCompassView.CARTYPE_SDF_HI;
        this.iDeviationScale = 120;
        this.iMaxSpeed = GyroScopeWithCompassView.CARTYPE_SDF_HI;
        this.iMaxRotatingSpeed = 8000;
        this.iMaxValue = (float) GyroScopeWithCompassView.CARTYPE_SDF_HI;
        this.ZXW_DASH_BOARD_REFRESH_UI = PointerIconCompat.TYPE_WAIT;
        this.iDistanceUnitValue = 0;
        this.tickRunnable = new Runnable() {
            public void run() {
                if (MyQAnalogClock2.this.iCurSpeedValue != ((float) MyQAnalogClock2.this.iSpeedValue)) {
                    if (Math.abs(MyQAnalogClock2.this.iCurSpeedValue - ((float) MyQAnalogClock2.this.iSpeedValue)) < 1.0f) {
                        MyQAnalogClock2 myQAnalogClock2 = MyQAnalogClock2.this;
                        float unused = myQAnalogClock2.iCurSpeedValue = (float) myQAnalogClock2.iSpeedValue;
                    } else if (Math.abs(MyQAnalogClock2.this.iCurSpeedValue - ((float) MyQAnalogClock2.this.iSpeedValue)) <= 2.0f) {
                        MyQAnalogClock2 myQAnalogClock22 = MyQAnalogClock2.this;
                        float unused2 = myQAnalogClock22.iCurSpeedValue = myQAnalogClock22.iCurSpeedValue + ((((float) MyQAnalogClock2.this.iSpeedValue) - MyQAnalogClock2.this.iCurSpeedValue) / 2.0f);
                    } else if (((float) MyQAnalogClock2.this.iSpeedValue) > MyQAnalogClock2.this.iCurSpeedValue) {
                        MyQAnalogClock2 myQAnalogClock23 = MyQAnalogClock2.this;
                        float unused3 = myQAnalogClock23.iCurSpeedValue = myQAnalogClock23.iCurSpeedValue + ((float) Math.round(((double) (((float) MyQAnalogClock2.this.iSpeedValue) - MyQAnalogClock2.this.iCurSpeedValue)) / 5.0d));
                    } else {
                        MyQAnalogClock2 myQAnalogClock24 = MyQAnalogClock2.this;
                        float unused4 = myQAnalogClock24.iCurSpeedValue = myQAnalogClock24.iCurSpeedValue + ((float) Math.floor(((double) (((float) MyQAnalogClock2.this.iSpeedValue) - MyQAnalogClock2.this.iCurSpeedValue)) / 5.0d));
                    }
                    MyQAnalogClock2.this.refreshTvCurView();
                    MyQAnalogClock2.this.postInvalidate();
                }
                MyQAnalogClock2.this.tickHandler.postDelayed(MyQAnalogClock2.this.tickRunnable, 50);
            }
        };
        this.mContext = context;
        mSysProviderOpt = SysProviderOpt.getInstance(context);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.MyQAnalogClock);
        this.bIsRotatingSpeed = obtainStyledAttributes.getBoolean(2, this.bIsRotatingSpeed);
        Log.i(TAG, "MyQAnalogClock2: bIsRotatingSpeed = " + this.bIsRotatingSpeed);
        if (this.bIsRotatingSpeed) {
            this.mDialDrawableId = R.drawable.shisuzhizhen;
            this.mPointerDrawableId = R.drawable.shisubiaopan;
            this.iMaxValue = (float) this.iMaxRotatingSpeed;
        }
        this.mDialDrawableId = obtainStyledAttributes.getResourceId(1, this.mDialDrawableId);
        this.mPointerDrawableId = obtainStyledAttributes.getResourceId(3, this.mPointerDrawableId);
        this.iTotalScale = obtainStyledAttributes.getInt(4, this.iTotalScale);
        this.iDeviationScale = obtainStyledAttributes.getInt(0, this.iDeviationScale);
        obtainStyledAttributes.recycle();
        setView();
        run();
    }

    public void setView() {
        this.iDistanceUnitValue = mSysProviderOpt.getRecordInteger(SysProviderOpt.KSW_DISTACNE_UNIT, this.iDistanceUnitValue);
        this.mBmpSecond = BitmapFactory.decodeResource(getResources(), this.mPointerDrawableId);
        this.bmdSecond = new BitmapDrawable(this.mBmpSecond);
        this.mBmpDial = BitmapFactory.decodeResource(getResources(), this.mDialDrawableId);
        this.bmdDial = new BitmapDrawable(this.mBmpDial);
        this.mWidth = this.mBmpDial.getWidth();
        int height = this.mBmpDial.getHeight();
        this.mHeigh = height;
        this.centerX = this.mWidth / 2;
        this.centerY = height / 2;
        Paint paint = new Paint();
        this.mPaint = paint;
        paint.setColor(-16776961);
    }

    public void run() {
        Handler handler = new Handler();
        this.tickHandler = handler;
        handler.postDelayed(this.tickRunnable, 50);
    }

    /* access modifiers changed from: package-private */
    public void refreshTvCurView() {
        TextView textView = this.mTvCur;
        if (textView == null) {
            return;
        }
        if (this.iDistanceUnitValue == 0) {
            textView.setText(((int) this.iCurSpeedValue) + "");
        } else {
            textView.setText(((int) (((double) this.iCurSpeedValue) * 0.62d)) + "");
        }
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float f = this.iCurSpeedValue;
        float f2 = 0.0f;
        if (f < 0.0f) {
            this.iCurSpeedValue = 0.0f;
        } else {
            float f3 = this.iMaxValue;
            if (f > f3) {
                this.iCurSpeedValue = (float) ((int) f3);
            }
        }
        float f4 = this.iCurSpeedValue / this.iMaxValue;
        int i = this.iTotalScale;
        float f5 = f4 * ((float) i);
        if (f5 >= 0.0f) {
            int i2 = this.iDeviationScale;
            if (f5 <= ((float) i2)) {
                f2 = ((float) (360 - i2)) + f5;
                BitmapDrawable bitmapDrawable = this.bmdDial;
                int i3 = this.centerX;
                int i4 = this.mWidth;
                int i5 = this.centerY;
                int i6 = this.mHeigh;
                bitmapDrawable.setBounds(i3 - (i4 / 2), i5 - (i6 / 2), i3 + (i4 / 2), i5 + (i6 / 2));
                this.bmdDial.draw(canvas);
                this.mTempWidth = this.bmdSecond.getIntrinsicWidth();
                this.mTempHeigh = this.bmdSecond.getIntrinsicHeight();
                canvas.save();
                canvas.rotate(f2, (float) this.centerX, (float) this.centerY);
                BitmapDrawable bitmapDrawable2 = this.bmdSecond;
                int i7 = this.centerX;
                int i8 = this.mTempWidth;
                int i9 = this.centerY;
                int i10 = this.mTempHeigh;
                bitmapDrawable2.setBounds(i7 - (i8 / 2), i9 - (i10 / 2), i7 + (i8 / 2), i9 + (i10 / 2));
                this.bmdSecond.draw(canvas);
                canvas.restore();
            }
        }
        int i11 = this.iDeviationScale;
        if (f5 > ((float) i11) && f5 <= ((float) i)) {
            f2 = f5 - ((float) i11);
        }
        BitmapDrawable bitmapDrawable3 = this.bmdDial;
        int i32 = this.centerX;
        int i42 = this.mWidth;
        int i52 = this.centerY;
        int i62 = this.mHeigh;
        bitmapDrawable3.setBounds(i32 - (i42 / 2), i52 - (i62 / 2), i32 + (i42 / 2), i52 + (i62 / 2));
        this.bmdDial.draw(canvas);
        this.mTempWidth = this.bmdSecond.getIntrinsicWidth();
        this.mTempHeigh = this.bmdSecond.getIntrinsicHeight();
        canvas.save();
        canvas.rotate(f2, (float) this.centerX, (float) this.centerY);
        BitmapDrawable bitmapDrawable22 = this.bmdSecond;
        int i72 = this.centerX;
        int i82 = this.mTempWidth;
        int i92 = this.centerY;
        int i102 = this.mTempHeigh;
        bitmapDrawable22.setBounds(i72 - (i82 / 2), i92 - (i102 / 2), i72 + (i82 / 2), i92 + (i102 / 2));
        this.bmdSecond.draw(canvas);
        canvas.restore();
    }

    public void setiMaxValue(int i) {
        this.iMaxValue = (float) i;
    }

    public void setiTotalScale(int i) {
        this.iTotalScale = i;
    }

    public void setiDeviationScale(int i) {
        this.iDeviationScale = i;
    }

    public void removeHandlerMess() {
        Handler handler = this.tickHandler;
        if (handler != null) {
            Runnable runnable = this.tickRunnable;
            if (runnable != null) {
                handler.removeCallbacks(runnable);
                this.tickRunnable = null;
            }
            this.tickHandler = null;
        }
    }

    public void setmTvCur(TextView textView) {
        this.mTvCur = textView;
    }

    public void setiSpeedValue(int i) {
        this.iSpeedValue = i;
    }

    public void setDialDrawableId(int i) {
        this.mDialDrawableId = i;
        setView();
        refreshTvCurView();
    }

    public int getDialDrawableId() {
        return this.mDialDrawableId;
    }
}
