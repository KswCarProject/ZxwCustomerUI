package com.szchoiceway.customerui.bean;

import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import com.szchoiceway.customerui.utils.LogHelps;
import com.szchoiceway.customerui.utils.ViewHelps;
import java.io.Serializable;
import java.util.Objects;

public class ComponentBean implements Serializable {
    public static String BACKSLASH = "/";
    private int mBackgroundResource;
    private final String mClass;
    private Drawable mIcon = null;
    private final String mKey;
    private String mLabel;
    private final String mPackage;
    private int mResId;
    private Object object = null;

    public ComponentBean setObject(Object obj) {
        this.object = obj;
        return this;
    }

    public Object getObject() {
        return this.object;
    }

    public ComponentBean setBackgroundResource(int i) {
        this.mBackgroundResource = i;
        return this;
    }

    public int getBackgroundResource() {
        return this.mBackgroundResource;
    }

    public static ComponentBean build(String str) {
        return build(str, (String) null);
    }

    public static ComponentBean build(String str, String str2) {
        return new ComponentBean(str, str2);
    }

    public static String createKey(String str, String str2) {
        StringBuilder sb = new StringBuilder();
        sb.append(str).append(BACKSLASH).append(str2);
        return sb.toString();
    }

    public ComponentBean(String str) {
        if (ViewHelps.contains(str, BACKSLASH)) {
            String[] split = str.split(BACKSLASH);
            this.mPackage = split[0];
            this.mClass = split[1];
        } else {
            this.mPackage = str;
            this.mClass = null;
        }
        this.mKey = createKey(this.mPackage, this.mClass);
    }

    public ComponentBean(String str, String str2) {
        this.mPackage = str;
        this.mClass = str2;
        this.mKey = createKey(str, str2);
    }

    public String getKey() {
        LogHelps.i("getKey:" + this.mKey);
        return this.mKey;
    }

    public int getKeyHashCode() {
        LogHelps.i("getKeyHashCode:" + this.mKey.hashCode());
        return this.mKey.hashCode();
    }

    public String getPackageName() {
        return TextUtils.isEmpty(this.mPackage) ? "" : this.mPackage;
    }

    public String getClassName() {
        return TextUtils.isEmpty(this.mClass) ? "" : this.mClass;
    }

    public String getLabel() {
        return this.mLabel;
    }

    public ComponentBean setLabel(String str) {
        this.mLabel = str;
        return this;
    }

    public Drawable getIcon() {
        return this.mIcon;
    }

    public ComponentBean setIcon(Drawable drawable) {
        this.mIcon = drawable;
        return this;
    }

    public int getResId() {
        return this.mResId;
    }

    public ComponentBean setResId(int i) {
        this.mResId = i;
        return this;
    }

    public boolean contains(String str) {
        return Objects.equals(str, this.mPackage);
    }

    public boolean contains(String str, String str2) {
        return Objects.equals(str, this.mPackage) && Objects.equals(str2, this.mClass);
    }

    public boolean containsKey(String str) {
        return Objects.equals(this.mKey, str);
    }

    public static String getPackageName(String str) {
        if (!ViewHelps.contains(str, BACKSLASH)) {
            return str;
        }
        try {
            return str.split(BACKSLASH)[0];
        } catch (Exception e) {
            LogHelps.e("Exception " + e.getMessage());
            return str;
        }
    }

    public static String getClassName(String str) {
        if (!ViewHelps.contains(str, BACKSLASH)) {
            return str;
        }
        try {
            return str.split(BACKSLASH)[1];
        } catch (Exception e) {
            LogHelps.e("Exception " + e.getMessage());
            return str;
        }
    }

    public String toString() {
        return "ComponentBean{mKey='" + this.mKey + '\'' + ", mLabel='" + this.mLabel + '\'' + '}';
    }
}
