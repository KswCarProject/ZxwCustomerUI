package com.szchoiceway.customerui.mianitem;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import com.szchoiceway.customerui.R;
import com.szchoiceway.customerui.drag.DragAppInfo;
import com.szchoiceway.customerui.utils.AppUtil;
import com.szchoiceway.customerui.utils.EventUtils;
import com.szchoiceway.zxwlib.bean.Customer;

public class ItemThird extends ItemBase {
    private static final String TAG = "ItemThird";
    private String className = "";
    private boolean currentInEditMode;
    private int iModeSet = 16;
    private ImageView imgAppIcon;
    private boolean isSmallResolution = false;
    private ItemUtil itemUtil;
    private View layoutEdit;
    private Context mContext;
    private String packageName = "";
    private View rootView = null;
    private String tag = "";
    private TextView textMain;

    public void setEditMode(boolean z) {
    }

    public void updateInfo() {
    }

    public ItemThird(String str, ItemUtil itemUtil2) {
        this.itemUtil = itemUtil2;
        this.tag = str;
        String[] split = str.split("/");
        this.packageName = split[0];
        this.className = split[1];
    }

    public View getRootView(Context context, int i) {
        this.mContext = context;
        boolean isSmallResolution2 = Customer.isSmallResolution(context);
        this.isSmallResolution = isSmallResolution2;
        this.iModeSet = i;
        if (isSmallResolution2) {
            this.rootView = LayoutInflater.from(this.mContext).inflate(R.layout.small_benz_main_item_layout_third, (ViewGroup) null);
        } else {
            this.rootView = LayoutInflater.from(this.mContext).inflate(R.layout.benz_main_item_layout_third, (ViewGroup) null);
        }
        initView();
        return this.rootView;
    }

    private void initView() {
        View findViewById = this.rootView.findViewById(R.id.layoutEdit);
        this.layoutEdit = findViewById;
        if (findViewById != null) {
            findViewById.setVisibility(this.currentInEditMode ? 0 : 8);
        }
        ImageButton imageButton = (ImageButton) this.rootView.findViewById(R.id.btnMain);
        float f = 0.5f;
        if (imageButton != null) {
            imageButton.setAlpha(this.currentInEditMode ? 0.5f : 1.0f);
        }
        ImageView imageView = (ImageView) this.rootView.findViewById(R.id.imgAppIcon);
        this.imgAppIcon = imageView;
        if (imageView != null) {
            imageView.setBackground(getAppDrawable());
            ImageView imageView2 = this.imgAppIcon;
            if (!this.currentInEditMode) {
                f = 1.0f;
            }
            imageView2.setAlpha(f);
        }
        TextView textView = (TextView) this.rootView.findViewById(R.id.textMain);
        this.textMain = textView;
        if (textView != null) {
            textView.setText(getAppName());
        }
    }

    public int[] getViewSize() {
        if (this.isSmallResolution) {
            return new int[]{EventUtils.getPx(this.mContext, this.mContext.getResources().getDisplayMetrics().widthPixels / 5), EventUtils.getPx(this.mContext, 300)};
        }
        return new int[]{EventUtils.getPx(this.mContext, 384), EventUtils.getPx(this.mContext, 441)};
    }

    public String getTag() {
        return this.tag;
    }

    public void setCurrentEditMode(boolean z) {
        this.currentInEditMode = z;
    }

    private Drawable getAppDrawable() {
        DragAppInfo dragAppInfo;
        if ("".equals(this.className) || (dragAppInfo = AppUtil.mAllDragAppInfoMap.get(this.className)) == null) {
            return null;
        }
        int drawableId = dragAppInfo.getDrawableId();
        if (drawableId != 0) {
            return this.mContext.getDrawable(drawableId);
        }
        return dragAppInfo.getDrawable();
    }

    private String getAppName() {
        DragAppInfo dragAppInfo;
        if ("".equals(this.className) || (dragAppInfo = AppUtil.mAllDragAppInfoMap.get(this.className)) == null) {
            return null;
        }
        return dragAppInfo.getAppName();
    }
}
