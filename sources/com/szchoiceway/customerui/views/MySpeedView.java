package com.szchoiceway.customerui.views;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.drawable.BitmapDrawable;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import com.szchoiceway.customerui.R;
import com.szchoiceway.customerui.utils.SysProviderOpt;

public class MySpeedView extends View {
    private static final int MODE_EFFICT = 0;
    private static final int MODE_PERSONAL = 2;
    private static final int MODE_SPORT = 1;
    private static final String TAG = "MySpeedView";
    /* access modifiers changed from: private */
    public int currentSpeed = 0;
    private Handler handler = new Handler() {
        public void handleMessage(Message message) {
            super.handleMessage(message);
            if (message.what == 0) {
                if (MySpeedView.this.currentSpeed > MySpeedView.this.lastSpeed) {
                    MySpeedView mySpeedView = MySpeedView.this;
                    int unused = mySpeedView.lastSpeed = mySpeedView.lastSpeed + 2;
                    MySpeedView.this.postInvalidate();
                    sendEmptyMessage(0);
                }
                if (MySpeedView.this.currentSpeed < MySpeedView.this.lastSpeed) {
                    MySpeedView mySpeedView2 = MySpeedView.this;
                    int unused2 = mySpeedView2.lastSpeed = mySpeedView2.lastSpeed - 2;
                    MySpeedView.this.postInvalidate();
                    sendEmptyMessage(0);
                }
            }
        }
    };
    /* access modifiers changed from: private */
    public int lastSpeed = 0;
    private Context mContext;
    private Paint mPaint;
    /* access modifiers changed from: private */
    public SysProviderOpt mProvider;
    private int maxSpeed = 330;
    private int minSpeed = 0;
    private SpeedBroadcast speedBroadcast;
    /* access modifiers changed from: private */
    public Bitmap speedProgress;

    public MySpeedView(Context context) {
        super(context);
    }

    public MySpeedView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context, attributeSet);
    }

    public MySpeedView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(context, attributeSet);
    }

    private void init(Context context, AttributeSet attributeSet) {
        this.mContext = context;
        registerBroadcast();
        this.currentSpeed = context.obtainStyledAttributes(attributeSet, R.styleable.MySpeedView).getInteger(0, 80);
        SysProviderOpt instance = SysProviderOpt.getInstance(context);
        this.mProvider = instance;
        int recordInteger = instance.getRecordInteger("KESAIWEI_SYS_DISPLAY_MODE", 0);
        if (recordInteger == 1) {
            this.speedProgress = ((BitmapDrawable) context.getResources().getDrawable(R.drawable.id8_main_icon_dashboard_r_2)).getBitmap();
        } else if (recordInteger != 2) {
            this.speedProgress = ((BitmapDrawable) context.getResources().getDrawable(R.drawable.id8_main_icon_dashboard_b_2)).getBitmap();
        } else {
            this.speedProgress = ((BitmapDrawable) context.getResources().getDrawable(R.drawable.id8_main_icon_dashboard_y_2)).getBitmap();
        }
        Paint paint = new Paint();
        this.mPaint = paint;
        paint.setAntiAlias(true);
        Log.d(TAG, "");
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.lastSpeed > 0) {
            float height = ((float) getHeight()) - ((((float) (this.lastSpeed * getHeight())) * 1.0f) / ((float) this.maxSpeed));
            Matrix matrix = new Matrix();
            int i = this.lastSpeed;
            if (i < 50) {
                int i2 = this.maxSpeed;
                float height2 = ((float) getHeight()) - ((((float) ((this.lastSpeed + 30) * getHeight())) * 1.0f) / ((float) i2));
                matrix.setScale(1.0f, (((float) (this.lastSpeed + 30)) * 1.0f) / ((float) i2));
                Bitmap bitmap = this.speedProgress;
                Bitmap createBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), this.speedProgress.getHeight(), matrix, true);
                canvas.save();
                Path path = new Path();
                path.moveTo(0.0f, height);
                path.lineTo((float) getWidth(), height2);
                path.lineTo((float) getWidth(), (float) getHeight());
                path.lineTo(0.0f, (float) getHeight());
                path.lineTo(0.0f, height);
                canvas.clipPath(path);
                canvas.drawBitmap(createBitmap, 0.0f, height2, this.mPaint);
            } else if (i >= 150 || i <= 50) {
                int i3 = this.maxSpeed;
                float height3 = ((float) getHeight()) - ((((float) ((this.lastSpeed + 15) * getHeight())) * 1.0f) / ((float) i3));
                matrix.setScale(1.0f, (((float) (this.lastSpeed + 15)) * 1.0f) / ((float) i3));
                Bitmap bitmap2 = this.speedProgress;
                Bitmap createBitmap2 = Bitmap.createBitmap(bitmap2, 0, 0, bitmap2.getWidth(), this.speedProgress.getHeight(), matrix, true);
                canvas.save();
                Path path2 = new Path();
                path2.moveTo(0.0f, height);
                path2.lineTo((float) getWidth(), height3);
                path2.lineTo((float) getWidth(), (float) getHeight());
                path2.lineTo(0.0f, (float) getHeight());
                path2.lineTo(0.0f, height);
                canvas.clipPath(path2);
                canvas.drawBitmap(createBitmap2, 0.0f, height3, this.mPaint);
            } else {
                int i4 = this.maxSpeed;
                float height4 = ((float) getHeight()) - ((((float) ((this.lastSpeed + 20) * getHeight())) * 1.0f) / ((float) i4));
                matrix.setScale(1.0f, (((float) (this.lastSpeed + 20)) * 1.0f) / ((float) i4));
                Bitmap bitmap3 = this.speedProgress;
                Bitmap createBitmap3 = Bitmap.createBitmap(bitmap3, 0, 0, bitmap3.getWidth(), this.speedProgress.getHeight(), matrix, true);
                canvas.save();
                Path path3 = new Path();
                path3.moveTo(0.0f, height);
                path3.lineTo((float) getWidth(), height4);
                path3.lineTo((float) getWidth(), (float) getHeight());
                path3.lineTo(0.0f, (float) getHeight());
                path3.lineTo(0.0f, height);
                canvas.clipPath(path3);
                canvas.drawBitmap(createBitmap3, 0.0f, height4, this.mPaint);
            }
            canvas.restore();
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0027, code lost:
        if (r3 > 100) goto L_0x0032;
     */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0040  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setSpeed(int r3, boolean r4) {
        /*
            r2 = this;
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "setSpeed speed = "
            java.lang.StringBuilder r0 = r0.append(r1)
            java.lang.StringBuilder r0 = r0.append(r3)
            java.lang.String r0 = r0.toString()
            java.lang.String r1 = "MySpeedView"
            android.util.Log.d(r1, r0)
            if (r4 == 0) goto L_0x002a
            r4 = 13
            if (r3 <= r4) goto L_0x0025
            r4 = 50
            if (r3 >= r4) goto L_0x0025
            int r3 = r3 + -13
            goto L_0x0039
        L_0x0025:
            r4 = 100
            if (r3 <= r4) goto L_0x0039
            goto L_0x0032
        L_0x002a:
            r4 = 150(0x96, float:2.1E-43)
            r0 = 8
            if (r3 <= r0) goto L_0x0035
            if (r3 >= r4) goto L_0x0035
        L_0x0032:
            int r3 = r3 + -8
            goto L_0x0039
        L_0x0035:
            if (r3 <= r4) goto L_0x0039
            int r3 = r3 + -3
        L_0x0039:
            r2.currentSpeed = r3
            int r4 = r3 % 2
            r0 = 1
            if (r4 != r0) goto L_0x0043
            int r3 = r3 + r0
            r2.currentSpeed = r3
        L_0x0043:
            android.os.Handler r2 = r2.handler
            r3 = 0
            r2.sendEmptyMessage(r3)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.szchoiceway.customerui.views.MySpeedView.setSpeed(int, boolean):void");
    }

    private void registerBroadcast() {
        this.speedBroadcast = new SpeedBroadcast();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("KESAIWEI_DISPLAY_MODE_CHANGED_ACTION");
        this.mContext.registerReceiver(this.speedBroadcast, intentFilter);
    }

    class SpeedBroadcast extends BroadcastReceiver {
        SpeedBroadcast() {
        }

        public void onReceive(Context context, Intent intent) {
            if ("KESAIWEI_DISPLAY_MODE_CHANGED_ACTION".equals(intent.getAction())) {
                int recordInteger = MySpeedView.this.mProvider.getRecordInteger("KESAIWEI_SYS_DISPLAY_MODE", 0);
                if (recordInteger == 1) {
                    Bitmap unused = MySpeedView.this.speedProgress = ((BitmapDrawable) context.getResources().getDrawable(R.drawable.id8_main_icon_dashboard_r_2)).getBitmap();
                } else if (recordInteger != 2) {
                    Bitmap unused2 = MySpeedView.this.speedProgress = ((BitmapDrawable) context.getResources().getDrawable(R.drawable.id8_main_icon_dashboard_b_2)).getBitmap();
                } else {
                    Bitmap unused3 = MySpeedView.this.speedProgress = ((BitmapDrawable) context.getResources().getDrawable(R.drawable.id8_main_icon_dashboard_y_2)).getBitmap();
                }
                MySpeedView.this.postInvalidate();
            }
        }
    }
}
