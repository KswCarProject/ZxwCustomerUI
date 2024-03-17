package com.szchoiceway.customerui.utils;

import android.app.Activity;
import android.content.Context;
import android.view.View;

public class ContextUtil {
    public static Activity getActivity(View view) {
        do {
            Context context = view.getContext();
            if (context instanceof Activity) {
                return (Activity) context;
            }
            view = (View) view.getParent();
        } while (view != null);
        return null;
    }
}
