package com.szchoiceway.customerui.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOverlay;
import android.widget.FrameLayout;
import androidx.appcompat.view.ContextThemeWrapper;
import com.szchoiceway.customerui.R;
import com.szchoiceway.customerui.model.LauncherModel;
import com.szchoiceway.customerui.utils.CrashHandler;
import com.szchoiceway.customerui.utils.EventUtils;
import com.szchoiceway.customerui.utils.MultipleUtils;
import com.szchoiceway.customerui.utils.SysProviderOpt;
import com.szchoiceway.zxwlib.bean.Customer;

public abstract class BaseView extends FrameLayout {
    private static final String TAG = "BaseView";
    public ContextThemeWrapper ctx;
    private View mView;
    public int m_iModeSet;
    public int m_iUIIndex;

    /* access modifiers changed from: protected */
    public abstract int inflateLayout();

    /* access modifiers changed from: protected */
    public abstract void initEvent(View view);

    /* access modifiers changed from: protected */
    public abstract void initView(View view);

    /* access modifiers changed from: protected */
    public void unInit() {
    }

    /* access modifiers changed from: protected */
    public /* bridge */ /* synthetic */ ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return super.generateDefaultLayoutParams();
    }

    public /* bridge */ /* synthetic */ ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return super.generateLayoutParams(attributeSet);
    }

    public /* bridge */ /* synthetic */ ViewOverlay getOverlay() {
        return super.getOverlay();
    }

    public BaseView(Context context) {
        this(context, (AttributeSet) null);
    }

    public BaseView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public BaseView(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0);
    }

    public BaseView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        int iModeSet = Customer.getIModeSet(this.mContext);
        this.m_iModeSet = iModeSet;
        this.m_iUIIndex = 0;
        if (!(iModeSet == 35 || iModeSet == 34)) {
            this.m_iUIIndex = Customer.getUIIndex(this.mContext);
        }
        LauncherModel.getInstance().init(context);
        CrashHandler.getInstance().init(this.mContext);
        MultipleUtils.setCustomDensity((Activity) null, context.getResources(), 0);
        removeAllViews();
        Context context2 = this.mContext;
        int i3 = R.style.landrover_blue;
        this.ctx = new ContextThemeWrapper(context2, (int) R.style.landrover_blue);
        if (this.m_iUIIndex == 5) {
            int recordInteger = SysProviderOpt.getInstance(this.mContext).getRecordInteger("KSW_LANDROVER_THEME_BACKGROUND_INDEX", 0);
            int i4 = this.m_iModeSet;
            if (i4 == 32 || i4 == 39) {
                this.ctx.setTheme(recordInteger != 0 ? R.style.landrover_white : i3);
            } else {
                this.ctx.setTheme(R.style.landrover_blue);
            }
            this.mView = View.inflate(this.ctx, inflateLayout(), this);
            return;
        }
        this.mView = View.inflate(context, inflateLayout(), this);
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        init();
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        unInit();
    }

    /* access modifiers changed from: protected */
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        MultipleUtils.setCustomDensity((Activity) null, this.mContext.getResources(), 0);
    }

    /* access modifiers changed from: protected */
    public void init() {
        initView(this.mView);
        initEvent(this.mView);
    }

    /* access modifiers changed from: protected */
    public float getDensity() {
        return getResources().getDisplayMetrics().density;
    }

    /* access modifiers changed from: protected */
    public int getScreenWidth() {
        return getResources().getDisplayMetrics().widthPixels;
    }

    /* access modifiers changed from: protected */
    public int getScreenHeitht() {
        return getResources().getDisplayMetrics().heightPixels;
    }

    /* access modifiers changed from: protected */
    public void setTheme() {
        int recordInteger = SysProviderOpt.getInstance(this.mContext).getRecordInteger("KSW_LANDROVER_THEME_BACKGROUND_INDEX", 0);
        removeAllViews();
        this.ctx.setTheme(recordInteger == 0 ? R.style.landrover_blue : R.style.landrover_white);
        Intent intent = new Intent(EventUtils.ACTION_LAUNCHER_CHANGE_STATUS_BAR_COLOR);
        intent.putExtra("blackFont", recordInteger);
        this.mContext.sendBroadcast(intent);
        View inflate = View.inflate(this.ctx, inflateLayout(), this);
        this.mView = inflate;
        initView(inflate);
    }
}
