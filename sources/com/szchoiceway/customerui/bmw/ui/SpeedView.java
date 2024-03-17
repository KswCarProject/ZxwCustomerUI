package com.szchoiceway.customerui.bmw.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import androidx.core.internal.view.SupportMenu;
import com.szchoiceway.customerui.R;
import com.szchoiceway.zxwlib.GyroScopeWithCompassView;
import kotlin.jvm.internal.CharCompanionObject;

public class SpeedView extends View {
    private int mLatelyIndex = 0;
    Paint mPaint = new Paint();
    Paint mPaintText = new Paint();
    boolean mSmallMode = true;
    private int mSpeed = 0;
    int mTextSize = 32;
    Bitmap mThume;
    Point p1;
    Point p2;
    Point p3;
    Point p4;
    Point p5;
    Point p6;

    public SpeedView(Context context) {
        super(context);
    }

    public SpeedView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context, attributeSet);
    }

    private void init(Context context, AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.SpeedView);
        this.mSmallMode = obtainStyledAttributes.getBoolean(0, false);
        obtainStyledAttributes.recycle();
        initPoint();
        this.mPaint.setStyle(Paint.Style.STROKE);
        this.mPaint.setAntiAlias(true);
        this.mPaint.setColor(SupportMenu.CATEGORY_MASK);
        this.mPaint.setStrokeWidth(3.0f);
        this.mPaintText.setAntiAlias(true);
        this.mPaintText.setColor(-1);
        this.mPaintText.setTextSize((float) this.mTextSize);
        if (this.mSmallMode) {
            this.mThume = BitmapFactory.decodeResource(getResources(), R.drawable.id8_main_edit_icon_dashboard_line);
        } else {
            this.mThume = BitmapFactory.decodeResource(getResources(), R.drawable.id8_main_icon_dashboard_line);
        }
    }

    private void initPoint() {
        if (this.mSmallMode) {
            this.mTextSize = 23;
            this.p1 = new Point(GyroScopeWithCompassView.CarTYPE_MALIBU_XL_HI, 475);
            this.p2 = new Point(GyroScopeWithCompassView.CARTYPE_MACAN_HI, GyroScopeWithCompassView.CARTYPE_OULANDE_NEW_LO);
            this.p3 = new Point(GyroScopeWithCompassView.CARTYPE_SLS2007_LO, GyroScopeWithCompassView.CARTYPE_MAITENG2017_MANUAL_LO);
            this.p4 = new Point(GyroScopeWithCompassView.CARTYPE_SLS2007_HI, GyroScopeWithCompassView.CARTYPE_XIANDAI_IX35_LO);
            this.p5 = new Point(GyroScopeWithCompassView.CARTYPE_AX7_LO, GyroScopeWithCompassView.CARTYPE_VW_TURUI_LO);
            this.p6 = new Point(GyroScopeWithCompassView.CARTYPE_SUTENG_HI, GyroScopeWithCompassView.CARTYPE_GS4_MI);
            return;
        }
        this.mTextSize = 32;
        this.p1 = new Point(357, 665);
        this.p2 = new Point(GyroScopeWithCompassView.CARTYPE_WEILANG_HI, 375);
        this.p3 = new Point(GyroScopeWithCompassView.CARTYPE_HIGHLANDER_HI, 388);
        this.p4 = new Point(GyroScopeWithCompassView.CARTYPE_GUANDAO_LO, 379);
        this.p5 = new Point(GyroScopeWithCompassView.CARTYPE_LINGDONG_HI, 370);
        this.p6 = new Point(336, GyroScopeWithCompassView.CARTYPE_MALIBU_H_H);
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        float f;
        float[] fArr;
        int i;
        char c;
        Canvas canvas2 = canvas;
        super.onDraw(canvas);
        Path path = new Path();
        path.moveTo((float) this.p1.x, (float) this.p1.y);
        path.lineTo((float) this.p2.x, (float) this.p2.y);
        PathMeasure pathMeasure = new PathMeasure();
        pathMeasure.setPath(path, false);
        float length = pathMeasure.getLength();
        int i2 = 0;
        while (true) {
            f = 15.0f;
            fArr = null;
            i = 2;
            if (i2 >= 21) {
                break;
            }
            float[] fArr2 = new float[2];
            pathMeasure.getPosTan((((float) i2) * length) / 21.0f, fArr2, (float[]) null);
            if (i2 % 5 == 0) {
                String str = (i2 * 10) + "";
                float measureText = this.mPaintText.measureText(str);
                Paint.FontMetrics fontMetrics = this.mPaintText.getFontMetrics();
                float f2 = fontMetrics.bottom - fontMetrics.top;
                float f3 = (fArr2[0] - measureText) - 15.0f;
                float f4 = fArr2[1] + (f2 * 0.25f);
                if (i2 == 0) {
                    f4 = fArr2[1];
                }
                if (this.mLatelyIndex == i2) {
                    this.mPaintText.setColor(-1);
                } else {
                    this.mPaintText.setColor(-7829368);
                }
                canvas2.drawText(str, f3, f4, this.mPaintText);
            }
            i2++;
        }
        Path path2 = new Path();
        path2.moveTo((float) this.p3.x, (float) this.p3.y);
        path2.quadTo((float) this.p4.x, (float) this.p4.y, (float) this.p5.x, (float) this.p5.y);
        path2.lineTo((float) this.p6.x, (float) this.p6.y);
        PathMeasure pathMeasure2 = new PathMeasure();
        pathMeasure2.setPath(path2, false);
        float length2 = pathMeasure2.getLength();
        int i3 = 0;
        while (i3 < 13) {
            float[] fArr3 = new float[i];
            pathMeasure2.getPosTan((((float) i3) * length2) / 13.0f, fArr3, fArr);
            if (i3 % 5 != 0 || i3 == 0) {
                c = CharCompanionObject.MIN_VALUE;
            } else {
                String str2 = ((i3 * 10) + 200) + "";
                float measureText2 = this.mPaintText.measureText(str2);
                Paint.FontMetrics fontMetrics2 = this.mPaintText.getFontMetrics();
                float f5 = fontMetrics2.bottom - fontMetrics2.top;
                float f6 = (fArr3[0] - measureText2) - f;
                float f7 = fArr3[1];
                c = CharCompanionObject.MIN_VALUE;
                float f8 = f7 + (f5 * 0.25f);
                if (this.mLatelyIndex - 20 == i3) {
                    this.mPaintText.setColor(-1);
                } else {
                    this.mPaintText.setColor(-7829368);
                }
                canvas2.drawText(str2, f6, f8, this.mPaintText);
            }
            i3++;
            char c2 = c;
            f = 15.0f;
            fArr = null;
            i = 2;
        }
        int i4 = this.mSpeed;
        if (i4 <= 200) {
            float[] fArr4 = new float[2];
            pathMeasure.getPosTan(((length * ((float) i4)) * 0.1f) / 21.0f, fArr4, (float[]) null);
            canvas2.drawBitmap(this.mThume, fArr4[0], fArr4[1], this.mPaint);
            return;
        }
        float[] fArr5 = new float[2];
        pathMeasure2.getPosTan(((length2 * ((float) (i4 - 200))) * 0.1f) / 13.0f, fArr5, (float[]) null);
        canvas2.drawBitmap(this.mThume, fArr5[0], fArr5[1], this.mPaint);
    }

    public void setSpeed(int i) {
        this.mSpeed = i;
        int i2 = i / 10;
        this.mLatelyIndex = i2 - (i2 % 5);
        Log.i("TAG", "setSpeed: " + this.mLatelyIndex);
        postInvalidate();
    }

    class Point {
        int x;
        int y;

        Point(int i, int i2) {
            this.x = i;
            this.y = i2;
        }
    }
}
