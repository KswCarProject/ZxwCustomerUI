package com.szchoiceway.customerui.utils;

import android.graphics.drawable.Drawable;

public class AppInfo {
    String className;
    Drawable icon;
    String label;
    String packageName;

    public void setLabel(String str) {
        this.label = str;
    }

    public String getLabel() {
        return this.label;
    }

    public void setPackageName(String str) {
        this.packageName = str;
    }

    public String getPackageName() {
        return this.packageName;
    }

    public void setClassName(String str) {
        this.className = str;
    }

    public String getClassName() {
        return this.className;
    }

    public void setIcon(Drawable drawable) {
        this.icon = drawable;
    }

    public Drawable getIcon() {
        return this.icon;
    }
}
