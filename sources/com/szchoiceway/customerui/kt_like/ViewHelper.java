package com.szchoiceway.customerui.kt_like;

import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

public interface ViewHelper {
    Context getContext() {
        return null;
    }

    float dp(float f) {
        return TypedValue.applyDimension(1, f, getContext().getResources().getDisplayMetrics());
    }

    float sp(float f) {
        return TypedValue.applyDimension(2, f, getContext().getResources().getDisplayMetrics());
    }

    int dpInt(float f) {
        return (int) TypedValue.applyDimension(1, f, getContext().getResources().getDisplayMetrics());
    }

    Drawable getDrawable(int i) {
        return ContextCompat.getDrawable(getContext(), i);
    }

    Bitmap drawableToBitmap(Drawable drawable) {
        int intrinsicWidth = drawable.getIntrinsicWidth();
        int intrinsicHeight = drawable.getIntrinsicHeight();
        drawable.setBounds(0, 0, intrinsicWidth, intrinsicHeight);
        return (Bitmap) JavaStandard.also(Bitmap.createBitmap(intrinsicWidth, intrinsicHeight, Bitmap.Config.ARGB_8888), new Function1Void(drawable) {
            public final /* synthetic */ Drawable f$0;

            {
                this.f$0 = r1;
            }

            public final void invoke(Object obj) {
                this.f$0.draw(new Canvas((Bitmap) obj));
            }
        });
    }

    FragmentActivity getActivity() {
        for (Context context = getContext(); context instanceof ContextWrapper; context = ((ContextWrapper) context).getBaseContext()) {
            if (context instanceof FragmentActivity) {
                return (FragmentActivity) context;
            }
        }
        return null;
    }

    String getString(int i) {
        return getContext().getString(i);
    }

    void visibility(View view) {
        view.setVisibility(0);
    }

    void invisibility(View view) {
        view.setVisibility(4);
    }

    void gone(View view) {
        view.setVisibility(8);
    }

    void toast(String str) {
        Toast.makeText(getContext(), str, 0).show();
    }

    View inflate(Context context, int i, ViewGroup viewGroup, boolean z) {
        return LayoutInflater.from(context).inflate(i, viewGroup, z);
    }
}
