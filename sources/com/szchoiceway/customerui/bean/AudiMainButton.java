package com.szchoiceway.customerui.bean;

public class AudiMainButton {
    private int animationDrawableId;
    private int backgroundDrawableId;
    private int imgDrawableId;
    private String tag;

    public void setBackgroundDrawableId(int i) {
        this.backgroundDrawableId = i;
    }

    public int getBackgroundDrawableId() {
        return this.backgroundDrawableId;
    }

    public void setAnimationDrawableId(int i) {
        this.animationDrawableId = i;
    }

    public int getAnimationDrawableId() {
        return this.animationDrawableId;
    }

    public void setImgDrawableId(int i) {
        this.imgDrawableId = i;
    }

    public int getImgDrawableId() {
        return this.imgDrawableId;
    }

    public void setTag(String str) {
        this.tag = str;
    }

    public String getTag() {
        return this.tag;
    }
}
