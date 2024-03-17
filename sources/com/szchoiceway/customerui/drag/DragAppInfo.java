package com.szchoiceway.customerui.drag;

import android.graphics.drawable.Drawable;

public class DragAppInfo {
    String appClassName = "";
    String appName = "";
    String appPackageName = "";
    Drawable drawable;
    int drawableId = 0;
    long installTime = 0;
    String tag = "";

    public int getDrawableId() {
        return this.drawableId;
    }

    public void setDrawableId(int i) {
        this.drawableId = i;
    }

    public String getAppName() {
        return this.appName;
    }

    public void setAppName(String str) {
        this.appName = str;
    }

    public String getTag() {
        return this.tag;
    }

    public void setTag(String str) {
        this.tag = str;
    }

    public String getAppPackageName() {
        return this.appPackageName;
    }

    public void setAppPackageName(String str) {
        this.appPackageName = str;
    }

    public String getAppClassName() {
        return this.appClassName;
    }

    public void setAppClassName(String str) {
        this.appClassName = str;
    }

    public Drawable getDrawable() {
        return this.drawable;
    }

    public void setDrawable(Drawable drawable2) {
        this.drawable = drawable2;
    }

    public long getInstallTime() {
        return this.installTime;
    }

    public void setInstallTime(long j) {
        this.installTime = j;
    }

    public String toString() {
        return "DragAppInfo{tag='" + this.tag + '\'' + ", appName='" + this.appName + '\'' + ", appPackageName='" + this.appPackageName + '\'' + ", appClassName='" + this.appClassName + '\'' + ", drawable=" + this.drawable + ", drawableId=" + this.drawableId + '}';
    }
}
