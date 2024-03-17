package com.szchoiceway.customerui.bmw.recycle;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.szchoiceway.customerui.R;
import com.szchoiceway.customerui.bmw.ui.MainPageUIController;
import com.szchoiceway.customerui.utils.EventUtils;
import com.szchoiceway.customerui.utils.SysProviderOpt;

public class ModusItem extends RecycleItemBase {
    private ImageView imgBk;
    private View imgLine;
    private View mBk;
    Context mContext;
    private int mMode = 0;
    View mModeView;
    private boolean mSmallMode = false;
    private TextView mTip;
    private TextView mTitle;

    public String getTag() {
        return MainPageUIController.MODE_TAG;
    }

    public View getSetView(Context context, boolean z) {
        View view;
        this.mContext = context;
        this.m_iModeSet = SysProviderOpt.getInstance(context).getRecordInteger("KESAIWEI_SYS_MODE_SELECTION", this.m_iModeSet);
        if (this.mSmallMode == z && (view = this.mModeView) != null) {
            return view;
        }
        this.mSmallMode = z;
        if (this.m_iModeSet == 20) {
            if (z) {
                this.mModeView = LayoutInflater.from(context).inflate(R.layout.pemp_bmw_id8_mode_item_layout_small, (ViewGroup) null);
            } else {
                this.mModeView = LayoutInflater.from(context).inflate(R.layout.pemp_bmw_id8_mode_item_layout, (ViewGroup) null);
            }
        } else if (z) {
            this.mModeView = LayoutInflater.from(context).inflate(R.layout.bmw_id8_mode_item_layout_small, (ViewGroup) null);
        } else {
            this.mModeView = LayoutInflater.from(context).inflate(R.layout.bmw_id8_mode_item_layout, (ViewGroup) null);
        }
        init();
        return this.mModeView;
    }

    private void init() {
        ImageView imageView = (ImageView) this.mModeView.findViewById(R.id.imgBk);
        this.imgBk = imageView;
        if (imageView != null) {
            imageView.setVisibility(this.mSmallMode ? 8 : 0);
        }
        this.imgLine = this.mModeView.findViewById(R.id.imgLine);
        this.mTitle = (TextView) this.mModeView.findViewById(R.id.title);
        this.mTip = (TextView) this.mModeView.findViewById(R.id.tip);
        this.mBk = this.mModeView.findViewById(R.id.bk);
        updateInfo();
    }

    public void updateInfo() {
        if (this.mTitle != null) {
            this.mTitle.setText(this.mContext.getString(R.string.lb_modus).toUpperCase());
        }
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
                TextView textView2 = this.mTip;
                if (textView2 != null) {
                    textView2.setText(getText(R.string.lb_efficient, true));
                }
                View view2 = this.mBk;
                if (view2 != null) {
                    view2.setBackgroundResource(this.mSmallMode ? R.drawable.pemp_id8_main_edit_icon_modus_efficient : R.drawable.pemp_id8_main_icon_modus_efficient);
                }
            } else if (i == 1) {
                TextView textView3 = this.mTitle;
                if (textView3 != null) {
                    textView3.setTextColor(getColor(R.color.bmw_id8_mainpage_item_title_color_sport));
                }
                ImageView imageView2 = this.imgBk;
                if (imageView2 != null) {
                    imageView2.setImageResource(this.mSmallMode ? R.drawable.pemp_bmw_id8_weather_item_background_red_small : R.drawable.pemp_bmw_id8_weather_item_background_red);
                }
                View view3 = this.imgLine;
                if (view3 != null) {
                    view3.setBackgroundResource(this.mSmallMode ? R.drawable.pemp_id8_main_edit_icon_weather_line_r : R.drawable.pemp_id8_main_icon_weather_line_r);
                }
                TextView textView4 = this.mTip;
                if (textView4 != null) {
                    textView4.setText(getText(R.string.lb_sport, true));
                }
                View view4 = this.mBk;
                if (view4 != null) {
                    view4.setBackgroundResource(this.mSmallMode ? R.drawable.pemp_id8_main_edit_icon_modus_sport : R.drawable.pemp_id8_main_icon_modus_sport);
                }
            } else if (i == 2) {
                TextView textView5 = this.mTitle;
                if (textView5 != null) {
                    textView5.setTextColor(getColor(R.color.bmw_id8_mainpage_item_title_color_personal));
                }
                ImageView imageView3 = this.imgBk;
                if (imageView3 != null) {
                    imageView3.setImageResource(this.mSmallMode ? R.drawable.pemp_bmw_id8_weather_item_background_yellow_small : R.drawable.pemp_bmw_id8_weather_item_background_yellow);
                }
                View view5 = this.imgLine;
                if (view5 != null) {
                    view5.setBackgroundResource(this.mSmallMode ? R.drawable.pemp_id8_main_edit_icon_weather_line_y : R.drawable.pemp_id8_main_icon_weather_line_y);
                }
                TextView textView6 = this.mTip;
                if (textView6 != null) {
                    textView6.setText(getText(R.string.lb_personal, true));
                }
                View view6 = this.mBk;
                if (view6 != null) {
                    view6.setBackgroundResource(this.mSmallMode ? R.drawable.pemp_id8_main_edit_icon_modus_personal : R.drawable.pemp_id8_main_icon_modus_personal);
                }
            }
        } else {
            int i2 = this.mMode;
            if (i2 == 0) {
                TextView textView7 = this.mTitle;
                if (textView7 != null) {
                    textView7.setTextColor(getColor(R.color.bmw_id8_mainpage_item_title_color));
                }
                ImageView imageView4 = this.imgBk;
                if (imageView4 != null) {
                    imageView4.setImageResource(this.mSmallMode ? R.drawable.bmw_id8_weather_item_background_blue_small : R.drawable.bmw_id8_weather_item_background_blue);
                }
                View view7 = this.imgLine;
                if (view7 != null) {
                    view7.setBackgroundResource(this.mSmallMode ? R.drawable.id8_main_edit_icon_weather_line_b : R.drawable.id8_main_icon_weather_line_b);
                }
                TextView textView8 = this.mTip;
                if (textView8 != null) {
                    textView8.setText(getText(R.string.lb_efficient, true));
                }
                View view8 = this.mBk;
                if (view8 != null) {
                    view8.setBackgroundResource(this.mSmallMode ? R.drawable.id8_main_edit_icon_modus_efficient : R.drawable.id8_main_icon_modus_efficient);
                }
            } else if (i2 == 1) {
                TextView textView9 = this.mTitle;
                if (textView9 != null) {
                    textView9.setTextColor(getColor(R.color.bmw_id8_mainpage_item_title_color_sport));
                }
                ImageView imageView5 = this.imgBk;
                if (imageView5 != null) {
                    imageView5.setImageResource(this.mSmallMode ? R.drawable.bmw_id8_weather_item_background_red_small : R.drawable.bmw_id8_weather_item_background_red);
                }
                View view9 = this.imgLine;
                if (view9 != null) {
                    view9.setBackgroundResource(this.mSmallMode ? R.drawable.id8_main_edit_icon_weather_line_r : R.drawable.id8_main_icon_weather_line_r);
                }
                TextView textView10 = this.mTip;
                if (textView10 != null) {
                    textView10.setText(getText(R.string.lb_sport, true));
                }
                View view10 = this.mBk;
                if (view10 != null) {
                    view10.setBackgroundResource(this.mSmallMode ? R.drawable.id8_main_edit_icon_modus_sport : R.drawable.id8_main_icon_modus_sport);
                }
            } else if (i2 == 2) {
                TextView textView11 = this.mTitle;
                if (textView11 != null) {
                    textView11.setTextColor(getColor(R.color.bmw_id8_mainpage_item_title_color_personal));
                }
                ImageView imageView6 = this.imgBk;
                if (imageView6 != null) {
                    imageView6.setImageResource(this.mSmallMode ? R.drawable.bmw_id8_weather_item_background_yellow_small : R.drawable.bmw_id8_weather_item_background_yellow);
                }
                View view11 = this.imgLine;
                if (view11 != null) {
                    view11.setBackgroundResource(this.mSmallMode ? R.drawable.id8_main_edit_icon_weather_line_y : R.drawable.id8_main_icon_weather_line_y);
                }
                TextView textView12 = this.mTip;
                if (textView12 != null) {
                    textView12.setText(getText(R.string.lb_personal, true));
                }
                View view12 = this.mBk;
                if (view12 != null) {
                    view12.setBackgroundResource(this.mSmallMode ? R.drawable.id8_main_edit_icon_modus_personal : R.drawable.id8_main_icon_modus_personal);
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

    private Drawable getDrawable(int i) {
        return this.mContext.getResources().getDrawable(i, (Resources.Theme) null);
    }

    private int getColor(int i) {
        return this.mContext.getResources().getColor(i, (Resources.Theme) null);
    }

    private String getText(int i, boolean z) {
        String string = this.mContext.getResources().getString(i);
        return z ? string.toUpperCase() : string;
    }
}
