package com.szchoiceway.customerui.drag;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import androidx.core.internal.view.SupportMenu;

public class DrawableUtils {
    public static Drawable getMergeDrawable(Drawable drawable, Drawable drawable2) {
        if (drawable == null) {
            return null;
        }
        Bitmap drawableToBitmap = drawableToBitmap(drawable);
        Bitmap drawableToBitmap2 = drawableToBitmap(drawable2);
        return new BitmapDrawable(getBitmapByShader(drawableToBitmap, drawableToBitmap2, drawableToBitmap2.getWidth(), drawableToBitmap2.getHeight()));
    }

    public static Bitmap getBitmapByShader(Bitmap bitmap, Bitmap bitmap2, int i, int i2) {
        float f = (float) i;
        float f2 = (float) i2;
        Bitmap bitMapScale = bitMapScale(bitmap, (((float) ((int) (f * 0.6f))) * 1.0f) / ((float) bitmap.getWidth()), (((float) ((int) (f2 * 0.6f))) * 1.0f) / ((float) bitmap.getHeight()));
        Canvas canvas = new Canvas(bitmap2);
        canvas.setDrawFilter(new PaintFlagsDrawFilter(0, 3));
        canvas.drawBitmap(bitmap2, new Matrix(), (Paint) null);
        canvas.drawBitmap(bitMapScale, (float) ((int) (f * 0.2f)), (float) ((int) (0.2f * f2)), (Paint) null);
        return bitmap2;
    }

    public static Drawable getRoundBitmapByShader(Drawable drawable, int i, int i2, int i3, int i4) {
        if (drawable == null) {
            return null;
        }
        Bitmap bitmapByShader = getBitmapByShader(drawableToBitmap(drawable), i, i2);
        int height = bitmapByShader.getHeight();
        float width = (((float) i) * 1.0f) / ((float) bitmapByShader.getWidth());
        float f = (((float) i2) * 1.0f) / ((float) height);
        Matrix matrix = new Matrix();
        matrix.setScale(width, f);
        Bitmap createBitmap = Bitmap.createBitmap(i, i2, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        Paint paint = new Paint(1);
        BitmapShader bitmapShader = new BitmapShader(bitmapByShader, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        bitmapShader.setLocalMatrix(matrix);
        paint.setShader(bitmapShader);
        float f2 = (float) i4;
        RectF rectF = new RectF(f2, f2, (float) (i - i4), (float) (i2 - i4));
        float f3 = (float) i3;
        canvas.drawRoundRect(rectF, f3, f3, paint);
        if (i4 > 0) {
            Paint paint2 = new Paint(1);
            paint2.setColor(0);
            paint2.setStyle(Paint.Style.STROKE);
            paint2.setStrokeWidth(f2);
            canvas.drawRoundRect(rectF, f3, f3, paint2);
        }
        return new BitmapDrawable(createBitmap);
    }

    public static Bitmap getBitmapByShader(Bitmap bitmap, int i, int i2) {
        float f = (float) i;
        int i3 = (int) (0.15f * f);
        int i4 = (int) (f * 0.5f);
        int i5 = (int) (((float) i2) * 0.5f);
        Bitmap bitMapScale = bitMapScale(bitmap, (((float) i4) * 1.0f) / ((float) bitmap.getWidth()), (((float) i5) * 1.0f) / ((float) bitmap.getHeight()));
        int i6 = i3 * 2;
        Bitmap createBitmap = Bitmap.createBitmap(i4 + i6, i5 + i6, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        canvas.drawColor(SupportMenu.CATEGORY_MASK);
        float f2 = (float) i3;
        canvas.drawBitmap(bitMapScale, f2, f2, new Paint(1));
        return createBitmap;
    }

    public static Bitmap bitMapScale(Bitmap bitmap, float f, float f2) {
        Matrix matrix = new Matrix();
        matrix.postScale(f, f2);
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
    }

    public static Bitmap drawableToBitmap(Drawable drawable) {
        Bitmap.Config config;
        if (drawable == null) {
            return null;
        }
        int intrinsicWidth = drawable.getIntrinsicWidth();
        int intrinsicHeight = drawable.getIntrinsicHeight();
        if (drawable.getOpacity() != -1) {
            config = Bitmap.Config.ARGB_8888;
        } else {
            config = Bitmap.Config.RGB_565;
        }
        Bitmap createBitmap = Bitmap.createBitmap(intrinsicWidth, intrinsicHeight, config);
        Canvas canvas = new Canvas(createBitmap);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        drawable.draw(canvas);
        return createBitmap;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:23:0x008e, code lost:
        if ("com.szchoiceway.settings.CareSetActivity".equals(r4) != false) goto L_0x0122;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:0x0120, code lost:
        if ("com.szchoiceway.settings.CareSetActivity".equals(r4) != false) goto L_0x0122;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:63:0x0122, code lost:
        r5 = com.szchoiceway.customerui.R.drawable.care_set_icon;
     */
    /* JADX WARNING: Removed duplicated region for block: B:83:0x016a  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.szchoiceway.customerui.drag.DragAppInfo exchangeIcon(android.content.Context r20, com.szchoiceway.customerui.drag.DragAppInfo r21) {
        /*
            r0 = r21
            com.szchoiceway.customerui.utils.SysProviderOpt r1 = com.szchoiceway.customerui.utils.SysProviderOpt.getInstance(r20)
            java.lang.String r2 = "KSW_UI_RESOLUTION"
            java.lang.String r1 = r1.getRecordValue(r2)
            java.lang.String r2 = r21.getTag()
            java.lang.String r3 = r21.getAppPackageName()
            java.lang.String r4 = r21.getAppClassName()
            java.lang.String r5 = "1280x480"
            boolean r1 = r5.equals(r1)
            java.lang.String r7 = "com.szchoiceway.settings.CareSetActivity"
            java.lang.String r8 = "com.szchoiceway.settings.GPSActivity"
            java.lang.String r9 = "com.android.chrome"
            java.lang.String r10 = "com.szchoiceway.settings.ManualActivity"
            java.lang.String r11 = "com.szchoiceway.transmitbt"
            java.lang.String r12 = "com.google.android.apps.maps"
            java.lang.String r13 = "com.szchoiceway.settings.FeedbackActivity"
            java.lang.String r14 = "com.szchoiceway.ksw_fc"
            java.lang.String r15 = "com.android.vending"
            java.lang.String r5 = "com.google.android.googlequicksearchbox.VoiceSearchActivity"
            java.lang.String r6 = "com.szchoiceway.settings"
            java.lang.String r0 = "com.google.android.youtube"
            r16 = r2
            java.lang.String r2 = "com.google.android.googlequicksearchbox.SearchActivity"
            r17 = r9
            java.lang.String r9 = "com.szchoiceway"
            r18 = r12
            java.lang.String r12 = "com.google.android.googlequicksearchbox"
            if (r1 == 0) goto L_0x00d3
            boolean r1 = r12.equals(r3)
            if (r1 == 0) goto L_0x0060
            boolean r0 = r2.equals(r4)
            if (r0 == 0) goto L_0x0055
            r5 = 1879573732(0x700804e4, float:1.683835E29)
            goto L_0x0160
        L_0x0055:
            boolean r0 = r5.equals(r4)
            if (r0 == 0) goto L_0x015f
            r5 = 1879575158(0x70080a76, float:1.6841043E29)
            goto L_0x0160
        L_0x0060:
            boolean r1 = r3.startsWith(r9)
            if (r1 == 0) goto L_0x00a3
            boolean r0 = r6.equals(r3)
            if (r0 == 0) goto L_0x0092
            boolean r0 = r13.equals(r4)
            if (r0 == 0) goto L_0x0077
            r5 = 1879573561(0x70080439, float:1.6838026E29)
            goto L_0x0160
        L_0x0077:
            boolean r0 = r10.equals(r4)
            if (r0 == 0) goto L_0x0082
            r5 = 1879575159(0x70080a77, float:1.6841045E29)
            goto L_0x0160
        L_0x0082:
            boolean r0 = r8.equals(r4)
            if (r0 == 0) goto L_0x008a
            goto L_0x0119
        L_0x008a:
            boolean r0 = r7.equals(r4)
            if (r0 == 0) goto L_0x015f
            goto L_0x0122
        L_0x0092:
            boolean r0 = r14.equals(r3)
            if (r0 == 0) goto L_0x009d
            r5 = 1879573344(0x70080360, float:1.6837617E29)
            goto L_0x0160
        L_0x009d:
            boolean r0 = r11.equals(r3)
            goto L_0x015f
        L_0x00a3:
            boolean r0 = r3.startsWith(r0)
            if (r0 == 0) goto L_0x00ae
            r5 = 1879576137(0x70080e49, float:1.6842892E29)
            goto L_0x0160
        L_0x00ae:
            boolean r0 = r3.startsWith(r15)
            if (r0 == 0) goto L_0x00b9
            r5 = 1879573735(0x700804e7, float:1.6838355E29)
            goto L_0x0160
        L_0x00b9:
            r1 = r18
            boolean r0 = r3.startsWith(r1)
            if (r0 == 0) goto L_0x00c6
            r5 = 1879573733(0x700804e5, float:1.6838351E29)
            goto L_0x0160
        L_0x00c6:
            r0 = r17
            boolean r0 = r3.startsWith(r0)
            if (r0 == 0) goto L_0x015f
            r5 = 1879573734(0x700804e6, float:1.6838353E29)
            goto L_0x0160
        L_0x00d3:
            r19 = r17
            r1 = r18
            boolean r12 = r12.equals(r3)
            if (r12 == 0) goto L_0x00f3
            boolean r0 = r2.equals(r4)
            if (r0 == 0) goto L_0x00e8
            r5 = 1879572568(0x70080058, float:1.683615E29)
            goto L_0x0160
        L_0x00e8:
            boolean r0 = r5.equals(r4)
            if (r0 == 0) goto L_0x015f
            r5 = 1879572569(0x70080059, float:1.6836153E29)
            goto L_0x0160
        L_0x00f3:
            boolean r2 = r3.startsWith(r9)
            if (r2 == 0) goto L_0x0135
            boolean r0 = r6.equals(r3)
            if (r0 == 0) goto L_0x0126
            boolean r0 = r13.equals(r4)
            if (r0 == 0) goto L_0x0109
            r5 = 1879572566(0x70080056, float:1.6836147E29)
            goto L_0x0160
        L_0x0109:
            boolean r0 = r10.equals(r4)
            if (r0 == 0) goto L_0x0113
            r5 = 1879572570(0x7008005a, float:1.6836155E29)
            goto L_0x0160
        L_0x0113:
            boolean r0 = r8.equals(r4)
            if (r0 == 0) goto L_0x011c
        L_0x0119:
            r5 = 1879900160(0x700d0000, float:1.7454955E29)
            goto L_0x0160
        L_0x011c:
            boolean r0 = r7.equals(r4)
            if (r0 == 0) goto L_0x015f
        L_0x0122:
            r5 = 1879573490(0x700803f2, float:1.6837892E29)
            goto L_0x0160
        L_0x0126:
            boolean r0 = r14.equals(r3)
            if (r0 == 0) goto L_0x0130
            r5 = 1879573343(0x7008035f, float:1.6837615E29)
            goto L_0x0160
        L_0x0130:
            boolean r0 = r11.equals(r3)
            goto L_0x015f
        L_0x0135:
            boolean r0 = r3.startsWith(r0)
            if (r0 == 0) goto L_0x013f
            r5 = 1879574163(0x70080693, float:1.6839164E29)
            goto L_0x0160
        L_0x013f:
            boolean r0 = r3.startsWith(r15)
            if (r0 == 0) goto L_0x0149
            r5 = 1879574162(0x70080692, float:1.6839162E29)
            goto L_0x0160
        L_0x0149:
            boolean r0 = r3.startsWith(r1)
            if (r0 == 0) goto L_0x0153
            r5 = 1879572567(0x70080057, float:1.6836149E29)
            goto L_0x0160
        L_0x0153:
            r0 = r19
            boolean r0 = r3.startsWith(r0)
            if (r0 == 0) goto L_0x015f
            r5 = 1879574161(0x70080691, float:1.683916E29)
            goto L_0x0160
        L_0x015f:
            r5 = 0
        L_0x0160:
            java.util.Map<java.lang.String, java.lang.Integer> r0 = com.szchoiceway.customerui.drag.DragAppListInfo.mMainItemTagDrawableIdMap
            r1 = r16
            boolean r0 = r0.containsKey(r1)
            if (r0 != 0) goto L_0x0173
            java.util.Map<java.lang.String, java.lang.Integer> r0 = com.szchoiceway.customerui.drag.DragAppListInfo.mMainItemTagDrawableIdMap
            java.lang.Integer r2 = java.lang.Integer.valueOf(r5)
            r0.put(r1, r2)
        L_0x0173:
            r0 = r21
            r0.setDrawableId(r5)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.szchoiceway.customerui.drag.DrawableUtils.exchangeIcon(android.content.Context, com.szchoiceway.customerui.drag.DragAppInfo):com.szchoiceway.customerui.drag.DragAppInfo");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0088, code lost:
        if ("com.szchoiceway.settings.CareSetActivity".equals(r2) != false) goto L_0x011c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:65:0x011a, code lost:
        if ("com.szchoiceway.settings.CareSetActivity".equals(r2) != false) goto L_0x011c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:66:0x011c, code lost:
        r5 = com.szchoiceway.customerui.R.drawable.care_set_icon;
     */
    /* JADX WARNING: Removed duplicated region for block: B:85:0x015c A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:86:0x015e  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static android.graphics.drawable.Drawable getThirdCardIcon(android.content.Context r19, java.lang.String r20, java.lang.String r21) {
        /*
            r0 = r19
            r1 = r20
            r2 = r21
            r3 = 0
            if (r0 != 0) goto L_0x000a
            return r3
        L_0x000a:
            com.szchoiceway.customerui.utils.SysProviderOpt r4 = com.szchoiceway.customerui.utils.SysProviderOpt.getInstance(r19)
            java.lang.String r5 = "KSW_UI_RESOLUTION"
            java.lang.String r4 = r4.getRecordValue(r5)
            java.lang.String r6 = "1280x480"
            boolean r4 = r6.equals(r4)
            java.lang.String r8 = "com.szchoiceway.settings.CareSetActivity"
            java.lang.String r9 = "com.szchoiceway.settings.GPSActivity"
            java.lang.String r10 = "com.android.chrome"
            java.lang.String r11 = "com.szchoiceway.settings.ManualActivity"
            java.lang.String r12 = "com.szchoiceway.transmitbt"
            java.lang.String r13 = "com.google.android.apps.maps"
            java.lang.String r14 = "com.szchoiceway.settings.FeedbackActivity"
            java.lang.String r15 = "com.szchoiceway.ksw_fc"
            java.lang.String r5 = "com.android.vending"
            java.lang.String r6 = "com.google.android.googlequicksearchbox.VoiceSearchActivity"
            java.lang.String r7 = "com.szchoiceway.settings"
            java.lang.String r3 = "com.google.android.youtube"
            java.lang.String r0 = "com.google.android.googlequicksearchbox.SearchActivity"
            r16 = r10
            java.lang.String r10 = "com.szchoiceway"
            r17 = r13
            java.lang.String r13 = "com.google.android.googlequicksearchbox"
            if (r4 == 0) goto L_0x00cd
            boolean r4 = r13.equals(r1)
            if (r4 == 0) goto L_0x005a
            boolean r0 = r0.equals(r2)
            if (r0 == 0) goto L_0x004f
            r5 = 1879573732(0x700804e4, float:1.683835E29)
            goto L_0x015a
        L_0x004f:
            boolean r0 = r6.equals(r2)
            if (r0 == 0) goto L_0x0159
            r5 = 1879575158(0x70080a76, float:1.6841043E29)
            goto L_0x015a
        L_0x005a:
            boolean r0 = r1.startsWith(r10)
            if (r0 == 0) goto L_0x009d
            boolean r0 = r7.equals(r1)
            if (r0 == 0) goto L_0x008c
            boolean r0 = r14.equals(r2)
            if (r0 == 0) goto L_0x0071
            r5 = 1879573561(0x70080439, float:1.6838026E29)
            goto L_0x015a
        L_0x0071:
            boolean r0 = r11.equals(r2)
            if (r0 == 0) goto L_0x007c
            r5 = 1879575159(0x70080a77, float:1.6841045E29)
            goto L_0x015a
        L_0x007c:
            boolean r0 = r9.equals(r2)
            if (r0 == 0) goto L_0x0084
            goto L_0x0113
        L_0x0084:
            boolean r0 = r8.equals(r2)
            if (r0 == 0) goto L_0x0159
            goto L_0x011c
        L_0x008c:
            boolean r0 = r15.equals(r1)
            if (r0 == 0) goto L_0x0097
            r5 = 1879573344(0x70080360, float:1.6837617E29)
            goto L_0x015a
        L_0x0097:
            boolean r0 = r12.equals(r1)
            goto L_0x0159
        L_0x009d:
            boolean r0 = r1.startsWith(r3)
            if (r0 == 0) goto L_0x00a8
            r5 = 1879576137(0x70080e49, float:1.6842892E29)
            goto L_0x015a
        L_0x00a8:
            boolean r0 = r1.startsWith(r5)
            if (r0 == 0) goto L_0x00b3
            r5 = 1879573735(0x700804e7, float:1.6838355E29)
            goto L_0x015a
        L_0x00b3:
            r4 = r17
            boolean r0 = r1.startsWith(r4)
            if (r0 == 0) goto L_0x00c0
            r5 = 1879573733(0x700804e5, float:1.6838351E29)
            goto L_0x015a
        L_0x00c0:
            r0 = r16
            boolean r0 = r1.startsWith(r0)
            if (r0 == 0) goto L_0x0159
            r5 = 1879573734(0x700804e6, float:1.6838353E29)
            goto L_0x015a
        L_0x00cd:
            r18 = r16
            r4 = r17
            boolean r13 = r13.equals(r1)
            if (r13 == 0) goto L_0x00ed
            boolean r0 = r0.equals(r2)
            if (r0 == 0) goto L_0x00e2
            r5 = 1879572568(0x70080058, float:1.683615E29)
            goto L_0x015a
        L_0x00e2:
            boolean r0 = r6.equals(r2)
            if (r0 == 0) goto L_0x0159
            r5 = 1879572569(0x70080059, float:1.6836153E29)
            goto L_0x015a
        L_0x00ed:
            boolean r0 = r1.startsWith(r10)
            if (r0 == 0) goto L_0x012f
            boolean r0 = r7.equals(r1)
            if (r0 == 0) goto L_0x0120
            boolean r0 = r14.equals(r2)
            if (r0 == 0) goto L_0x0103
            r5 = 1879572566(0x70080056, float:1.6836147E29)
            goto L_0x015a
        L_0x0103:
            boolean r0 = r11.equals(r2)
            if (r0 == 0) goto L_0x010d
            r5 = 1879572570(0x7008005a, float:1.6836155E29)
            goto L_0x015a
        L_0x010d:
            boolean r0 = r9.equals(r2)
            if (r0 == 0) goto L_0x0116
        L_0x0113:
            r5 = 1879900160(0x700d0000, float:1.7454955E29)
            goto L_0x015a
        L_0x0116:
            boolean r0 = r8.equals(r2)
            if (r0 == 0) goto L_0x0159
        L_0x011c:
            r5 = 1879573490(0x700803f2, float:1.6837892E29)
            goto L_0x015a
        L_0x0120:
            boolean r0 = r15.equals(r1)
            if (r0 == 0) goto L_0x012a
            r5 = 1879573343(0x7008035f, float:1.6837615E29)
            goto L_0x015a
        L_0x012a:
            boolean r0 = r12.equals(r1)
            goto L_0x0159
        L_0x012f:
            boolean r0 = r1.startsWith(r3)
            if (r0 == 0) goto L_0x0139
            r5 = 1879574163(0x70080693, float:1.6839164E29)
            goto L_0x015a
        L_0x0139:
            boolean r0 = r1.startsWith(r5)
            if (r0 == 0) goto L_0x0143
            r5 = 1879574162(0x70080692, float:1.6839162E29)
            goto L_0x015a
        L_0x0143:
            boolean r0 = r1.startsWith(r4)
            if (r0 == 0) goto L_0x014d
            r5 = 1879572567(0x70080057, float:1.6836149E29)
            goto L_0x015a
        L_0x014d:
            r0 = r18
            boolean r0 = r1.startsWith(r0)
            if (r0 == 0) goto L_0x0159
            r5 = 1879574161(0x70080691, float:1.683916E29)
            goto L_0x015a
        L_0x0159:
            r5 = 0
        L_0x015a:
            if (r5 != 0) goto L_0x015e
            r0 = 0
            return r0
        L_0x015e:
            r0 = r19
            android.graphics.drawable.Drawable r0 = r0.getDrawable(r5)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.szchoiceway.customerui.drag.DrawableUtils.getThirdCardIcon(android.content.Context, java.lang.String, java.lang.String):android.graphics.drawable.Drawable");
    }
}
