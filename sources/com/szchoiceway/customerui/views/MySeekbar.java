package com.szchoiceway.customerui.views;

import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.view.View;
import com.szchoiceway.customerui.R;
import com.szchoiceway.customerui.utils.EventUtils;

public class MySeekbar extends View {
    private Bitmap backgroundBitmap;
    private int current = 0;
    private Context mContext;
    private int mMeasureHeight = 0;
    private int mMeasureWidth = 0;
    private Paint mPaint;
    public OnSeekListener mSeekListener;
    float mX = 0.0f;
    private Matrix matrix;
    private int max = 1000;
    private int min = 0;
    private Bitmap progressBitmap;
    private Bitmap thumbBitmap;
    private int topOffset;

    public interface OnSeekListener {
        void onSeekStop(int i);

        void onSeeking(int i);
    }

    public MySeekbar(Context context) {
        super(context);
    }

    public MySeekbar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context, attributeSet);
    }

    public MySeekbar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(context, attributeSet);
    }

    private void init(Context context, AttributeSet attributeSet) {
        this.mContext = context;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.MySeekbar2);
        this.backgroundBitmap = ((BitmapDrawable) obtainStyledAttributes.getDrawable(0)).getBitmap();
        this.progressBitmap = ((BitmapDrawable) obtainStyledAttributes.getDrawable(3)).getBitmap();
        this.thumbBitmap = ((BitmapDrawable) obtainStyledAttributes.getDrawable(4)).getBitmap();
        this.max = obtainStyledAttributes.getInt(2, 1000);
        this.current = obtainStyledAttributes.getInt(1, 200);
        obtainStyledAttributes.recycle();
        Paint paint = new Paint();
        this.mPaint = paint;
        paint.setAntiAlias(true);
        this.topOffset = (int) this.mContext.getResources().getDimension(R.dimen.seekbar_offset);
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        this.mMeasureWidth = View.MeasureSpec.getSize(i);
        int size = View.MeasureSpec.getSize(i2);
        this.mMeasureHeight = size;
        setMeasuredDimension(this.mMeasureWidth, size);
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Bitmap bitmap = this.backgroundBitmap;
        if (bitmap != null) {
            canvas.drawBitmap(bitmap, 0.0f, (float) this.topOffset, this.mPaint);
        }
        if (this.current != 0) {
            if (!(this.progressBitmap == null || this.thumbBitmap == null)) {
                this.matrix = new Matrix();
                float f = ((float) this.current) / ((float) this.max);
                if (((float) this.progressBitmap.getWidth()) * f > ((float) this.thumbBitmap.getWidth())) {
                    this.matrix.setScale(f, 1.0f);
                    Bitmap bitmap2 = this.progressBitmap;
                    canvas.drawBitmap(Bitmap.createBitmap(bitmap2, 0, 0, bitmap2.getWidth(), this.progressBitmap.getHeight(), this.matrix, true), -2.0f, (float) this.topOffset, this.mPaint);
                }
            }
            Bitmap bitmap3 = this.thumbBitmap;
            if (bitmap3 != null) {
                canvas.drawBitmap(bitmap3, (((((float) this.current) * 1.0f) / ((float) this.max)) * ((float) this.mMeasureWidth)) - ((float) bitmap3.getWidth()), (float) this.topOffset, this.mPaint);
            }
        }
    }

    public void setMax(int i) {
        this.max = i;
    }

    public void setCurrentProgress(int i) {
        this.current = i;
        postInvalidate();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0012, code lost:
        if (r0 != 3) goto L_0x0094;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean onTouchEvent(android.view.MotionEvent r7) {
        /*
            r6 = this;
            int r0 = r7.getAction()
            r1 = 1
            r2 = 1077936128(0x40400000, float:3.0)
            if (r0 == 0) goto L_0x0088
            r3 = 1065353216(0x3f800000, float:1.0)
            r4 = 0
            if (r0 == r1) goto L_0x0052
            r5 = 2
            if (r0 == r5) goto L_0x0016
            r2 = 3
            if (r0 == r2) goto L_0x0052
            goto L_0x0094
        L_0x0016:
            float r7 = r7.getX()
            r6.mX = r7
            int r0 = (r7 > r4 ? 1 : (r7 == r4 ? 0 : -1))
            if (r0 < 0) goto L_0x0094
            int r0 = r6.mMeasureWidth
            float r5 = (float) r0
            int r5 = (r7 > r5 ? 1 : (r7 == r5 ? 0 : -1))
            if (r5 < 0) goto L_0x002b
            float r7 = (float) r0
            r6.mX = r7
            goto L_0x0031
        L_0x002b:
            int r7 = (r7 > r2 ? 1 : (r7 == r2 ? 0 : -1))
            if (r7 >= 0) goto L_0x0031
            r6.mX = r2
        L_0x0031:
            float r7 = r6.mX
            float r0 = (float) r0
            float r7 = r7 / r0
            int r0 = r6.max
            float r2 = (float) r0
            float r7 = r7 * r2
            float r7 = r7 * r3
            int r2 = (r7 > r4 ? 1 : (r7 == r4 ? 0 : -1))
            if (r2 >= 0) goto L_0x003f
            goto L_0x0040
        L_0x003f:
            r4 = r7
        L_0x0040:
            float r7 = (float) r0
            int r7 = (r4 > r7 ? 1 : (r4 == r7 ? 0 : -1))
            if (r7 <= 0) goto L_0x0046
            float r4 = (float) r0
        L_0x0046:
            int r7 = (int) r4
            r6.setCurrentProgress(r7)
            com.szchoiceway.customerui.views.MySeekbar$OnSeekListener r6 = r6.mSeekListener
            if (r6 == 0) goto L_0x0094
            r6.onSeeking(r7)
            goto L_0x0094
        L_0x0052:
            float r7 = r7.getX()
            r6.mX = r7
            com.szchoiceway.customerui.views.MySeekbar$OnSeekListener r0 = r6.mSeekListener
            if (r0 == 0) goto L_0x0094
            int r0 = r6.mMeasureWidth
            float r2 = (float) r0
            int r7 = (r7 > r2 ? 1 : (r7 == r2 ? 0 : -1))
            if (r7 <= 0) goto L_0x0066
            float r7 = (float) r0
            r6.mX = r7
        L_0x0066:
            float r7 = r6.mX
            float r0 = (float) r0
            float r7 = r7 / r0
            int r0 = r6.max
            float r2 = (float) r0
            float r7 = r7 * r2
            float r7 = r7 * r3
            int r2 = (r7 > r4 ? 1 : (r7 == r4 ? 0 : -1))
            if (r2 >= 0) goto L_0x0074
            goto L_0x0075
        L_0x0074:
            r4 = r7
        L_0x0075:
            float r7 = (float) r0
            int r7 = (r4 > r7 ? 1 : (r4 == r7 ? 0 : -1))
            if (r7 <= 0) goto L_0x007b
            float r4 = (float) r0
        L_0x007b:
            int r7 = (int) r4
            r6.sendMusicProgress(r7)
            r6.setCurrentProgress(r7)
            com.szchoiceway.customerui.views.MySeekbar$OnSeekListener r6 = r6.mSeekListener
            r6.onSeekStop(r7)
            goto L_0x0094
        L_0x0088:
            float r7 = r7.getX()
            r6.mX = r7
            int r7 = (r7 > r2 ? 1 : (r7 == r2 ? 0 : -1))
            if (r7 >= 0) goto L_0x0094
            r6.mX = r2
        L_0x0094:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.szchoiceway.customerui.views.MySeekbar.onTouchEvent(android.view.MotionEvent):boolean");
    }

    private void sendMusicProgress(int i) {
        if (i < 1) {
            i = 1;
        }
        Intent intent = new Intent(EventUtils.ZXW_ACTION_MUSIC_BAR);
        intent.putExtra(EventUtils.ZXW_ACTION_MUSIC_BAR_EXTRA, i);
        this.mContext.sendBroadcast(intent);
    }

    public void setOnSeekListener(OnSeekListener onSeekListener) {
        this.mSeekListener = onSeekListener;
    }
}
