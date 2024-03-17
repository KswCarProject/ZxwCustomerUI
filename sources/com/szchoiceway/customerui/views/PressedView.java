package com.szchoiceway.customerui.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import com.szchoiceway.customerui.R;
import com.szchoiceway.customerui.utils.EventUtils;
import com.szchoiceway.customerui.utils.SysProviderOpt;

public class PressedView extends View {
    private static final String TAG = "PressedView";
    private boolean actionDown = false;
    private boolean actionMove = false;
    /* access modifiers changed from: private */
    public boolean actionUp = false;
    private boolean checked = false;
    private Bitmap checkedBitmap;
    private Handler handler = new Handler() {
        public void handleMessage(Message message) {
            super.handleMessage(message);
            int i = message.what;
            if (i != 0) {
                if (i == 1) {
                    PressedView.this.performClick();
                }
            } else if (PressedView.this.percent < PressedView.this.totalPercent) {
                PressedView.access$008(PressedView.this);
                PressedView.this.postInvalidate();
                sendEmptyMessage(0);
            } else if (PressedView.this.actionUp) {
                int unused = PressedView.this.percent = 0;
                PressedView.this.postInvalidate();
                boolean unused2 = PressedView.this.actionUp = false;
            }
        }
    };
    private int mHeight;
    private int mMeasureHeight;
    private int mMeasureWidth;
    private Paint mPaint;
    private Path mPath;
    private int mWidth;
    private Bitmap normalBitmap;
    /* access modifiers changed from: private */
    public int percent = 0;
    private float pressWidth = 0.0f;
    private Bitmap pressedBitmap1;
    private Bitmap pressedBitmap2;
    private boolean selected = false;
    private Bitmap selectedBitmap;
    float startX = 0.0f;
    float startY = 0.0f;
    /* access modifiers changed from: private */
    public int totalPercent = 8;
    private int viewHeight;

    static /* synthetic */ int access$008(PressedView pressedView) {
        int i = pressedView.percent;
        pressedView.percent = i + 1;
        return i;
    }

    public PressedView(Context context) {
        super(context);
    }

    public PressedView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context, attributeSet);
    }

    public PressedView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(context, attributeSet);
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
    public void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        this.mWidth = i;
        this.mHeight = i2;
        scaleViews();
    }

    private void init(Context context, AttributeSet attributeSet) {
        SysProviderOpt.getInstance(context).getRecordInteger("KESAIWEI_SYS_MODE_SELECTION", 24);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.PressedView);
        Drawable drawable = obtainStyledAttributes.getDrawable(2);
        if (drawable != null) {
            this.pressedBitmap1 = ((BitmapDrawable) drawable).getBitmap();
        }
        Drawable drawable2 = obtainStyledAttributes.getDrawable(3);
        if (drawable2 != null) {
            this.pressedBitmap2 = ((BitmapDrawable) drawable2).getBitmap();
        }
        Drawable drawable3 = obtainStyledAttributes.getDrawable(4);
        if (drawable3 != null) {
            this.selectedBitmap = ((BitmapDrawable) drawable3).getBitmap();
        }
        Drawable drawable4 = obtainStyledAttributes.getDrawable(0);
        if (drawable4 != null) {
            this.checkedBitmap = ((BitmapDrawable) drawable4).getBitmap();
        }
        Drawable drawable5 = obtainStyledAttributes.getDrawable(1);
        if (drawable5 != null) {
            this.normalBitmap = ((BitmapDrawable) drawable5).getBitmap();
        }
        obtainStyledAttributes.recycle();
        Paint paint = new Paint();
        this.mPaint = paint;
        paint.setAntiAlias(true);
        this.mPath = new Path();
    }

    private void scaleViews() {
        Matrix matrix = new Matrix();
        if (this.pressedBitmap1 != null) {
            matrix.reset();
            if (!(this.pressedBitmap1.getWidth() == this.mWidth && this.pressedBitmap1.getHeight() == this.mHeight)) {
                matrix.setScale((((float) this.mWidth) * 1.0f) / ((float) this.pressedBitmap1.getWidth()), (((float) this.mHeight) * 1.0f) / ((float) this.pressedBitmap1.getHeight()));
                Bitmap bitmap = this.pressedBitmap1;
                this.pressedBitmap1 = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), this.pressedBitmap1.getHeight(), matrix, true);
            }
        }
        if (this.pressedBitmap2 != null) {
            matrix.reset();
            if (!(this.pressedBitmap2.getWidth() == this.mWidth && this.pressedBitmap2.getHeight() == this.mHeight)) {
                matrix.setScale((((float) this.mWidth) * 1.0f) / ((float) this.pressedBitmap2.getWidth()), (((float) this.mHeight) * 1.0f) / ((float) this.pressedBitmap2.getHeight()));
                Bitmap bitmap2 = this.pressedBitmap2;
                this.pressedBitmap2 = Bitmap.createBitmap(bitmap2, 0, 0, bitmap2.getWidth(), this.pressedBitmap2.getHeight(), matrix, true);
            }
        }
        if (this.selectedBitmap != null) {
            matrix.reset();
            if (!(this.selectedBitmap.getWidth() == this.mWidth && this.selectedBitmap.getHeight() == this.mHeight)) {
                matrix.setScale((((float) this.mWidth) * 1.0f) / ((float) this.selectedBitmap.getWidth()), (((float) this.mHeight) * 1.0f) / ((float) this.selectedBitmap.getHeight()));
                Bitmap bitmap3 = this.selectedBitmap;
                this.selectedBitmap = Bitmap.createBitmap(bitmap3, 0, 0, bitmap3.getWidth(), this.selectedBitmap.getHeight(), matrix, true);
            }
        }
        if (this.checkedBitmap != null) {
            matrix.reset();
            if (!(this.checkedBitmap.getWidth() == this.mWidth && this.checkedBitmap.getHeight() == this.mHeight)) {
                matrix.setScale((((float) this.mWidth) * 1.0f) / ((float) this.checkedBitmap.getWidth()), (((float) this.mHeight) * 1.0f) / ((float) this.checkedBitmap.getHeight()));
                Bitmap bitmap4 = this.checkedBitmap;
                this.checkedBitmap = Bitmap.createBitmap(bitmap4, 0, 0, bitmap4.getWidth(), this.checkedBitmap.getHeight(), matrix, true);
            }
        }
        if (this.normalBitmap != null) {
            matrix.reset();
            if (this.normalBitmap.getWidth() != this.mWidth || this.normalBitmap.getHeight() != this.mHeight) {
                matrix.setScale((((float) this.mWidth) * 1.0f) / ((float) this.normalBitmap.getWidth()), (((float) this.mHeight) * 1.0f) / ((float) this.normalBitmap.getHeight()));
                Bitmap bitmap5 = this.normalBitmap;
                this.normalBitmap = Bitmap.createBitmap(bitmap5, 0, 0, bitmap5.getWidth(), this.normalBitmap.getHeight(), matrix, true);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(this.normalBitmap, 0.0f, 0.0f, this.mPaint);
        int i = this.percent;
        int i2 = this.totalPercent;
        if (i == i2) {
            canvas.drawBitmap(this.pressedBitmap2, 0.0f, 0.0f, this.mPaint);
        } else if (i < i2 && i > 0) {
            float f = (((float) i) * 1.0f) / ((float) i2);
            int i3 = this.mMeasureWidth;
            float f2 = f * ((float) i3);
            this.pressWidth = f2;
            this.mPath.moveTo((((float) i3) - f2) / 2.0f, 0.0f);
            this.mPath.lineTo((((float) this.mMeasureWidth) + this.pressWidth) / 2.0f, 0.0f);
            this.mPath.lineTo((((float) this.mMeasureWidth) + this.pressWidth) / 2.0f, (float) this.mMeasureHeight);
            this.mPath.lineTo((((float) this.mMeasureWidth) - this.pressWidth) / 2.0f, (float) this.mMeasureHeight);
            this.mPath.lineTo((((float) this.mMeasureWidth) - this.pressWidth) / 2.0f, 0.0f);
            canvas.clipPath(this.mPath);
            canvas.drawBitmap(this.pressedBitmap1, 0.0f, 0.0f, this.mPaint);
            this.mPath.reset();
        }
        if (this.selected) {
            canvas.drawBitmap(this.selectedBitmap, 0.0f, 0.0f, this.mPaint);
        }
        if (this.checked) {
            canvas.drawBitmap(this.checkedBitmap, 0.0f, 0.0f, this.mPaint);
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (action == 0) {
            this.actionDown = true;
            this.actionUp = false;
            this.actionMove = false;
            this.startX = motionEvent.getX();
            this.startY = motionEvent.getY();
            this.percent = 0;
            this.handler.removeMessages(0);
            this.handler.sendEmptyMessage(0);
        } else if (action != 1) {
            if (action != 2) {
                if (action == 3) {
                    actionUp();
                }
            } else if (Math.abs(motionEvent.getX() - this.startX) > ((float) EventUtils.getPx(this.mContext, 80)) || Math.abs(motionEvent.getY() - this.startY) > ((float) EventUtils.getPx(this.mContext, 80))) {
                this.actionMove = true;
                actionUp();
            }
        } else if (!this.actionMove) {
            actionUp();
            this.handler.sendEmptyMessage(1);
        }
        return true;
    }

    public void actionUp() {
        this.actionUp = true;
        if (this.percent == this.totalPercent) {
            this.handler.removeMessages(0);
            this.handler.sendEmptyMessage(0);
        }
        this.actionDown = false;
    }

    public boolean hasActionDown() {
        return this.actionDown;
    }

    public void setSelected(boolean z) {
        this.selected = z;
        postInvalidate();
    }

    public void setChecked(boolean z) {
        this.checked = z;
        postInvalidate();
    }
}
