package com.szchoiceway.customerui.fragment.launcher.strategy;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

public abstract class BaseLauncherStrategy implements ILauncherStrategy {
    /* access modifiers changed from: protected */
    public final Context context;
    private LayoutInflater layoutInflater;
    protected View view;

    /* access modifiers changed from: protected */
    public abstract void setViewInner(View view2);

    public BaseLauncherStrategy(Context context2) {
        this.context = context2;
    }

    public final void setView(View view2) {
        this.view = view2;
        initLeftButton();
        initPagerList();
        setViewInner(view2);
    }

    /* access modifiers changed from: protected */
    public LayoutInflater getLayoutInflater() {
        if (this.layoutInflater == null) {
            this.layoutInflater = LayoutInflater.from(this.context);
        }
        return this.layoutInflater;
    }
}
