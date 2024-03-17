package com.szchoiceway.customerui.drag;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.view.View;
import androidx.constraintlayout.solver.widgets.analyzer.BasicMeasure;
import androidx.core.view.ViewCompat;

public class MySha extends View.DragShadowBuilder {
    private Bitmap image;

    public MySha(Bitmap bitmap) {
        this.image = bitmap;
    }

    public void onDrawShadow(Canvas canvas) {
        canvas.drawColor(-16711936);
        canvas.drawBitmap(this.image, 0.0f, 0.0f, (Paint) null);
        Paint paint = new Paint();
        paint.setTextSize(30.0f);
        paint.setColor(ViewCompat.MEASURED_STATE_MASK);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawText("hahahahahahhah", 0.0f, 30.0f, paint);
    }

    public static int getFontHeight(Integer num) {
        Paint paint = new Paint();
        if (num != null) {
            paint.setTextSize((float) num.intValue());
        }
        Paint.FontMetrics fontMetrics = paint.getFontMetrics();
        return (int) (fontMetrics.descent - fontMetrics.ascent);
    }

    public void onProvideShadowMetrics(Point point, Point point2) {
        point.x = this.image.getWidth() * 4;
        point.y = this.image.getHeight() * 4;
        point2.x = point.x / 2;
        point2.y = point.y / 2;
        this.image = Bitmap.createScaledBitmap(this.image, point.x, point.y, true);
    }

    public static Bitmap createViewBitmap(View view, int i, int i2) {
        view.measure(View.MeasureSpec.makeMeasureSpec(i, BasicMeasure.EXACTLY), View.MeasureSpec.makeMeasureSpec(i2, BasicMeasure.EXACTLY));
        view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
        Bitmap createBitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        canvas.drawColor(-1);
        view.draw(canvas);
        return createBitmap;
    }
}
