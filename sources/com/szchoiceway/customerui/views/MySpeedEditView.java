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

public class MySpeedEditView extends View {
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
                if (MySpeedEditView.this.currentSpeed > MySpeedEditView.this.lastSpeed) {
                    MySpeedEditView mySpeedEditView = MySpeedEditView.this;
                    int unused = mySpeedEditView.lastSpeed = mySpeedEditView.lastSpeed + 2;
                    MySpeedEditView.this.postInvalidate();
                    sendEmptyMessage(0);
                }
                if (MySpeedEditView.this.currentSpeed < MySpeedEditView.this.lastSpeed) {
                    MySpeedEditView mySpeedEditView2 = MySpeedEditView.this;
                    int unused2 = mySpeedEditView2.lastSpeed = mySpeedEditView2.lastSpeed - 2;
                    MySpeedEditView.this.postInvalidate();
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

    public MySpeedEditView(Context context) {
        super(context);
    }

    public MySpeedEditView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context, attributeSet);
    }

    public MySpeedEditView(Context context, AttributeSet attributeSet, int i) {
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
            this.speedProgress = ((BitmapDrawable) context.getResources().getDrawable(R.drawable.id8_main_edit_icon_dashboard_r_2)).getBitmap();
        } else if (recordInteger != 2) {
            this.speedProgress = ((BitmapDrawable) context.getResources().getDrawable(R.drawable.id8_main_edit_icon_dashboard_b_2)).getBitmap();
        } else {
            this.speedProgress = ((BitmapDrawable) context.getResources().getDrawable(R.drawable.id8_main_edit_icon_dashboard_y_2)).getBitmap();
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
            int i = this.maxSpeed;
            float height2 = ((float) getHeight()) - ((((float) ((this.lastSpeed + 20) * getHeight())) * 1.0f) / ((float) i));
            Matrix matrix = new Matrix();
            matrix.setScale(1.0f, (((float) (this.lastSpeed + 20)) * 1.0f) / ((float) i));
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
            canvas.restore();
        }
    }

    public void setSpeed(int i) {
        if (i > 8) {
            i -= 8;
        }
        this.currentSpeed = i;
        if (i % 2 == 1) {
            this.currentSpeed = i + 1;
        }
        this.handler.sendEmptyMessage(0);
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
                int recordInteger = MySpeedEditView.this.mProvider.getRecordInteger("KESAIWEI_SYS_DISPLAY_MODE", 0);
                if (recordInteger == 1) {
                    Bitmap unused = MySpeedEditView.this.speedProgress = ((BitmapDrawable) context.getResources().getDrawable(R.drawable.id8_main_icon_dashboard_r_2)).getBitmap();
                } else if (recordInteger != 2) {
                    Bitmap unused2 = MySpeedEditView.this.speedProgress = ((BitmapDrawable) context.getResources().getDrawable(R.drawable.id8_main_icon_dashboard_b_2)).getBitmap();
                } else {
                    Bitmap unused3 = MySpeedEditView.this.speedProgress = ((BitmapDrawable) context.getResources().getDrawable(R.drawable.id8_main_icon_dashboard_y_2)).getBitmap();
                }
                MySpeedEditView.this.postInvalidate();
            }
        }
    }
}
