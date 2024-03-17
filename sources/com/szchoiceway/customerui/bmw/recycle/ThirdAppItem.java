package com.szchoiceway.customerui.bmw.recycle;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.szchoiceway.customerui.R;
import com.szchoiceway.customerui.bmw.ui.MainPageUIController;
import com.szchoiceway.customerui.drag.DragAppInfo;
import com.szchoiceway.customerui.drag.DrawableUtils;
import com.szchoiceway.customerui.utils.AppUtil;
import com.szchoiceway.customerui.utils.EventUtils;
import com.szchoiceway.customerui.utils.SysProviderOpt;

public class ThirdAppItem extends RecycleItemBase implements View.OnClickListener {
    private static final String TAG = "BMWThirdAppItem";
    private ImageView imgBk;
    private View imgLine;
    private ImageView mAppIcon;
    Context mContext;
    private DragAppInfo mDragAppInfo = null;
    private int mMode = 0;
    private boolean mSmallMode = false;
    private String mTag = "";
    View mThirdView;
    private TextView mTitle;
    private MainPageUIController mainPageUIController;

    public View getSetView(Context context, boolean z) {
        View view;
        this.mContext = context;
        this.m_iModeSet = SysProviderOpt.getInstance(context).getRecordInteger("KESAIWEI_SYS_MODE_SELECTION", this.m_iModeSet);
        if (this.mSmallMode == z && (view = this.mThirdView) != null) {
            return view;
        }
        this.mSmallMode = z;
        if (this.m_iModeSet == 20) {
            if (z) {
                this.mThirdView = LayoutInflater.from(context).inflate(R.layout.pemp_bmw_id8_third_item_layout_small, (ViewGroup) null);
            } else {
                this.mThirdView = LayoutInflater.from(context).inflate(R.layout.pemp_bmw_id8_third_item_layout, (ViewGroup) null);
            }
        } else if (z) {
            this.mThirdView = LayoutInflater.from(context).inflate(R.layout.bmw_id8_third_item_layout_small, (ViewGroup) null);
        } else {
            this.mThirdView = LayoutInflater.from(context).inflate(R.layout.bmw_id8_third_item_layout, (ViewGroup) null);
        }
        init();
        return this.mThirdView;
    }

    private void init() {
        ImageView imageView = (ImageView) this.mThirdView.findViewById(R.id.imgBk);
        this.imgBk = imageView;
        if (imageView != null) {
            imageView.setVisibility(this.mSmallMode ? 8 : 0);
        }
        this.imgLine = this.mThirdView.findViewById(R.id.imgLine);
        Button button = (Button) this.mThirdView.findViewById(R.id.btnDelete);
        if (button != null) {
            button.setOnClickListener(this);
        }
        this.mAppIcon = (ImageView) this.mThirdView.findViewById(R.id.appIcon);
        this.mTitle = (TextView) this.mThirdView.findViewById(R.id.title);
        refreshInfo();
        updateInfo();
    }

    public void updateInfo() {
        this.mMode = SysProviderOpt.getInstance(this.mContext).getRecordInteger("KESAIWEI_SYS_DISPLAY_MODE", 0);
        if (this.m_iModeSet == 20) {
            int i = this.mMode;
            if (i == 0) {
                TextView textView = this.mTitle;
                if (textView != null) {
                    textView.setTextColor(getColor(R.color.bmw_id8_mainpage_item_title_color));
                }
                ImageView imageView = this.imgBk;
                if (imageView != null) {
                    imageView.setImageResource(this.mSmallMode ? R.drawable.pemp_bmw_id8_weather_item_background_blue_small : R.drawable.pemp_bmw_id8_weather_item_background_blue);
                }
                View view = this.imgLine;
                if (view != null) {
                    view.setBackgroundResource(this.mSmallMode ? R.drawable.pemp_id8_main_edit_icon_weather_line_b : R.drawable.pemp_id8_main_icon_weather_line_b);
                }
            } else if (i == 1) {
                TextView textView2 = this.mTitle;
                if (textView2 != null) {
                    textView2.setTextColor(getColor(R.color.bmw_id8_mainpage_item_title_color_sport));
                }
                ImageView imageView2 = this.imgBk;
                if (imageView2 != null) {
                    imageView2.setImageResource(this.mSmallMode ? R.drawable.pemp_bmw_id8_weather_item_background_red_small : R.drawable.pemp_bmw_id8_weather_item_background_red);
                }
                View view2 = this.imgLine;
                if (view2 != null) {
                    view2.setBackgroundResource(this.mSmallMode ? R.drawable.pemp_id8_main_edit_icon_weather_line_r : R.drawable.pemp_id8_main_icon_weather_line_r);
                }
            } else if (i == 2) {
                TextView textView3 = this.mTitle;
                if (textView3 != null) {
                    textView3.setTextColor(getColor(R.color.bmw_id8_mainpage_item_title_color_personal));
                }
                ImageView imageView3 = this.imgBk;
                if (imageView3 != null) {
                    imageView3.setImageResource(this.mSmallMode ? R.drawable.pemp_bmw_id8_weather_item_background_yellow_small : R.drawable.pemp_bmw_id8_weather_item_background_yellow);
                }
                View view3 = this.imgLine;
                if (view3 != null) {
                    view3.setBackgroundResource(this.mSmallMode ? R.drawable.pemp_id8_main_edit_icon_weather_line_y : R.drawable.pemp_id8_main_icon_weather_line_y);
                }
            }
        } else {
            int i2 = this.mMode;
            if (i2 == 0) {
                TextView textView4 = this.mTitle;
                if (textView4 != null) {
                    textView4.setTextColor(getColor(R.color.bmw_id8_mainpage_item_title_color));
                }
                ImageView imageView4 = this.imgBk;
                if (imageView4 != null) {
                    imageView4.setImageResource(this.mSmallMode ? R.drawable.bmw_id8_weather_item_background_blue_small : R.drawable.bmw_id8_weather_item_background_blue);
                }
                View view4 = this.imgLine;
                if (view4 != null) {
                    view4.setBackgroundResource(this.mSmallMode ? R.drawable.id8_main_edit_icon_weather_line_b : R.drawable.id8_main_icon_weather_line_b);
                }
            } else if (i2 == 1) {
                TextView textView5 = this.mTitle;
                if (textView5 != null) {
                    textView5.setTextColor(getColor(R.color.bmw_id8_mainpage_item_title_color_sport));
                }
                ImageView imageView5 = this.imgBk;
                if (imageView5 != null) {
                    imageView5.setImageResource(this.mSmallMode ? R.drawable.bmw_id8_weather_item_background_red_small : R.drawable.bmw_id8_weather_item_background_red);
                }
                View view5 = this.imgLine;
                if (view5 != null) {
                    view5.setBackgroundResource(this.mSmallMode ? R.drawable.id8_main_edit_icon_weather_line_r : R.drawable.id8_main_icon_weather_line_r);
                }
            } else if (i2 == 2) {
                TextView textView6 = this.mTitle;
                if (textView6 != null) {
                    textView6.setTextColor(getColor(R.color.bmw_id8_mainpage_item_title_color_personal));
                }
                ImageView imageView6 = this.imgBk;
                if (imageView6 != null) {
                    imageView6.setImageResource(this.mSmallMode ? R.drawable.bmw_id8_weather_item_background_yellow_small : R.drawable.bmw_id8_weather_item_background_yellow);
                }
                View view6 = this.imgLine;
                if (view6 != null) {
                    view6.setBackgroundResource(this.mSmallMode ? R.drawable.id8_main_edit_icon_weather_line_y : R.drawable.id8_main_icon_weather_line_y);
                }
            }
        }
    }

    public void updataFocusState(boolean z) {
        ImageView imageView;
        ImageView imageView2;
        if (!z) {
            ImageView imageView3 = this.imgBk;
            if (imageView3 != null) {
                imageView3.setForeground((Drawable) null);
            }
        } else if (this.m_iModeSet == 20) {
            int i = this.mMode;
            if (i == 0) {
                ImageView imageView4 = this.imgBk;
                if (imageView4 != null) {
                    imageView4.setForeground(getDrawable(this.mSmallMode ? R.drawable.pemp_id8_main_edit_icon_weather_b_f : R.drawable.pemp_id8_main_icon_weather_b_f));
                }
            } else if (i == 1) {
                ImageView imageView5 = this.imgBk;
                if (imageView5 != null) {
                    imageView5.setForeground(getDrawable(this.mSmallMode ? R.drawable.pemp_id8_main_edit_icon_weather_r_f : R.drawable.pemp_id8_main_icon_weather_r_f));
                }
            } else if (i == 2 && (imageView2 = this.imgBk) != null) {
                imageView2.setForeground(getDrawable(this.mSmallMode ? R.drawable.pemp_id8_main_edit_icon_weather_y_f : R.drawable.pemp_id8_main_icon_weather_y_f));
            }
        } else {
            int i2 = this.mMode;
            if (i2 == 0) {
                ImageView imageView6 = this.imgBk;
                if (imageView6 != null) {
                    imageView6.setForeground(getDrawable(this.mSmallMode ? R.drawable.id8_main_edit_icon_weather_b_f : R.drawable.id8_main_icon_weather_b_f));
                }
            } else if (i2 == 1) {
                ImageView imageView7 = this.imgBk;
                if (imageView7 != null) {
                    imageView7.setForeground(getDrawable(this.mSmallMode ? R.drawable.id8_main_edit_icon_weather_r_f : R.drawable.id8_main_icon_weather_r_f));
                }
            } else if (i2 == 2 && (imageView = this.imgBk) != null) {
                imageView.setForeground(getDrawable(this.mSmallMode ? R.drawable.id8_main_edit_icon_weather_y_f : R.drawable.id8_main_icon_weather_y_f));
            }
        }
    }

    public int[] getViewSize() {
        if (this.mSmallMode) {
            return new int[]{EventUtils.getPx(this.mContext, 316), (int) this.mContext.getResources().getDimension(R.dimen.screenHeight)};
        }
        return new int[]{EventUtils.getPx(this.mContext, 434), (int) this.mContext.getResources().getDimension(R.dimen.screenHeight)};
    }

    public String getTag() {
        return this.mTag;
    }

    private Drawable getDrawable(int i) {
        return this.mContext.getResources().getDrawable(i, (Resources.Theme) null);
    }

    public void onClick(View view) {
        if (view.getId() == R.id.btnDelete) {
            Log.i("TAG", "onClick: delete");
            MainPageUIController mainPageUIController2 = this.mainPageUIController;
            if (mainPageUIController2 != null) {
                mainPageUIController2.deleteItem(this.mTag);
            }
        }
    }

    public void setInfo(DragAppInfo dragAppInfo) {
        this.mDragAppInfo = dragAppInfo;
        refreshInfo();
    }

    private void refreshInfo() {
        Drawable drawable;
        DragAppInfo dragAppInfo = this.mDragAppInfo;
        if (dragAppInfo != null) {
            String appPackageName = dragAppInfo.getAppPackageName();
            String appClassName = this.mDragAppInfo.getAppClassName();
            this.mTag = appPackageName + "/" + appClassName;
            Log.d(TAG, "refreshInfo mTag = " + this.mTag);
            DragAppInfo dragAppInfo2 = AppUtil.mAllDragAppInfoMap.get(appClassName);
            if (dragAppInfo2 != null) {
                TextView textView = this.mTitle;
                if (textView != null) {
                    textView.setText(dragAppInfo2.getAppName());
                }
                if (this.mAppIcon != null) {
                    if (dragAppInfo2.getDrawableId() != 0) {
                        drawable = this.mContext.getDrawable(dragAppInfo2.getDrawableId());
                    } else {
                        drawable = DrawableUtils.getMergeDrawable(dragAppInfo2.getDrawable(), this.mContext.getDrawable(R.drawable.imitate_auto_yingyong_baidi_));
                    }
                    this.mAppIcon.setImageDrawable(drawable);
                }
            }
        }
    }

    public void setMainPageUIController(MainPageUIController mainPageUIController2) {
        this.mainPageUIController = mainPageUIController2;
    }

    private int getColor(int i) {
        return this.mContext.getResources().getColor(i, (Resources.Theme) null);
    }
}
