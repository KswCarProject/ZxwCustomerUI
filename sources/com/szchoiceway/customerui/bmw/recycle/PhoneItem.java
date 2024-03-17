package com.szchoiceway.customerui.bmw.recycle;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.RemoteException;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.szchoiceway.customerui.R;
import com.szchoiceway.customerui.bmw.ui.MainPageUIController;
import com.szchoiceway.customerui.model.LauncherModel;
import com.szchoiceway.customerui.utils.EventUtils;
import com.szchoiceway.customerui.utils.SysProviderOpt;
import com.szchoiceway.eventcenter.IEventService;
import com.szchoiceway.zxwlib.utils.LogUtils;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Calendar;
import java.util.Set;

public class PhoneItem extends RecycleItemBase {
    private ImageView imgBk;
    private View imgLine;
    private int mBtState = 0;
    Context mContext;
    private View mImgPhone;
    private int mMode = 0;
    View mPhoneView;
    private boolean mSmallMode = false;
    private TextView mTip;
    private TextView mTitle;
    private TextView tvDateDay;
    private TextView tvDateMonth;

    public String getTag() {
        return MainPageUIController.BLUETOOTH_TAG;
    }

    public View getSetView(Context context, boolean z) {
        View view;
        this.mContext = context;
        this.m_iModeSet = SysProviderOpt.getInstance(context).getRecordInteger("KESAIWEI_SYS_MODE_SELECTION", this.m_iModeSet);
        if (this.mSmallMode == z && (view = this.mPhoneView) != null) {
            return view;
        }
        this.mSmallMode = z;
        if (this.m_iModeSet == 20) {
            if (z) {
                this.mPhoneView = LayoutInflater.from(context).inflate(R.layout.pemp_bmw_id8_phone_item_layout_small, (ViewGroup) null);
            } else {
                this.mPhoneView = LayoutInflater.from(context).inflate(R.layout.pemp_bmw_id8_phone_item_layout, (ViewGroup) null);
            }
        } else if (z) {
            this.mPhoneView = LayoutInflater.from(context).inflate(R.layout.bmw_id8_phone_item_layout_small, (ViewGroup) null);
        } else {
            this.mPhoneView = LayoutInflater.from(context).inflate(R.layout.bmw_id8_phone_item_layout, (ViewGroup) null);
        }
        init();
        return this.mPhoneView;
    }

    private void init() {
        ImageView imageView = (ImageView) this.mPhoneView.findViewById(R.id.imgBk);
        this.imgBk = imageView;
        if (imageView != null) {
            imageView.setVisibility(this.mSmallMode ? 8 : 0);
        }
        this.imgLine = this.mPhoneView.findViewById(R.id.imgLine);
        this.mTitle = (TextView) this.mPhoneView.findViewById(R.id.title);
        this.mTip = (TextView) this.mPhoneView.findViewById(R.id.tip);
        this.mImgPhone = this.mPhoneView.findViewById(R.id.imgPhone);
        this.tvDateDay = (TextView) this.mPhoneView.findViewById(R.id.tvDateDay);
        this.tvDateMonth = (TextView) this.mPhoneView.findViewById(R.id.tvDateMonth);
        updateInfo();
        refreshStateImg();
        setBtState();
        refreshDateTime();
    }

    public void updateInfo() {
        if (this.mTitle != null) {
            this.mTitle.setText(this.mContext.getString(R.string.lb_bt_phone).toUpperCase());
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

    private void setBtState() {
        try {
            IEventService evtService = LauncherModel.getInstance().getEvtService();
            if ((evtService != null ? evtService.GetBTStatus() : 0) >= 3) {
                this.mBtState = 1;
            } else if (BluetoothAdapter.getDefaultAdapter().isEnabled()) {
                this.mBtState = 0;
            } else {
                this.mBtState = -1;
            }
            Log.d("PhoneItem", "mBtState = " + this.mBtState);
            refreshStateImg();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void setBtState(int i) {
        this.mBtState = i;
        refreshStateImg();
    }

    private void refreshStateImg() {
        int i;
        if (this.mImgPhone != null) {
            if (this.m_iModeSet == 20) {
                int i2 = this.mBtState;
                if (i2 == 1) {
                    i = this.mSmallMode ? R.drawable.pemp_id8_main_edit_icon_phone_connected : R.drawable.pemp_id8_main_icon_phone_connected;
                    TextView textView = this.mTip;
                    if (textView != null) {
                        textView.setText(R.string.lb_bt_message_connected);
                    }
                } else if (i2 == 0) {
                    i = this.mSmallMode ? R.drawable.pemp_id8_main_edit_icon_phone_unconnected : R.drawable.pemp_id8_main_icon_phone_unconnected;
                    TextView textView2 = this.mTip;
                    if (textView2 != null) {
                        textView2.setText(R.string.lb_bt_message_not_connected);
                    }
                } else {
                    i = this.mSmallMode ? R.drawable.pemp_id8_main_edit_icon_phone_unconnected : R.drawable.pemp_id8_main_icon_phone_unconnected;
                    TextView textView3 = this.mTip;
                    if (textView3 != null) {
                        textView3.setText(R.string.lb_bt_message_close);
                    }
                }
            } else {
                int i3 = this.mBtState;
                if (i3 == 1) {
                    i = this.mSmallMode ? R.drawable.id8_main_edit_icon_phone_connected : R.drawable.id8_main_icon_phone_connected;
                    TextView textView4 = this.mTip;
                    if (textView4 != null) {
                        textView4.setText(R.string.lb_bt_message_connected);
                    }
                } else if (i3 == 0) {
                    i = this.mSmallMode ? R.drawable.id8_main_edit_icon_phone_unconnected : R.drawable.id8_main_icon_phone_unconnected;
                    TextView textView5 = this.mTip;
                    if (textView5 != null) {
                        textView5.setText(R.string.lb_bt_message_not_connected);
                    }
                } else {
                    i = this.mSmallMode ? R.drawable.id8_main_edit_icon_phone_unconnected : R.drawable.id8_main_icon_phone_unconnected;
                    TextView textView6 = this.mTip;
                    if (textView6 != null) {
                        textView6.setText(R.string.lb_bt_message_close);
                    }
                }
            }
            this.mImgPhone.setBackgroundResource(i);
        }
    }

    public String getConnectedBtDevice() {
        Set<BluetoothDevice> bondedDevices = BluetoothAdapter.getDefaultAdapter().getBondedDevices();
        if (bondedDevices != null && bondedDevices.size() > 0) {
            for (BluetoothDevice next : bondedDevices) {
                try {
                    Method declaredMethod = BluetoothDevice.class.getDeclaredMethod("isConnected", (Class[]) null);
                    declaredMethod.setAccessible(true);
                    boolean booleanValue = ((Boolean) declaredMethod.invoke(next, (Object[]) null)).booleanValue();
                    LogUtils.i("TAG", "getConnectedBtDevice  isConnected：" + booleanValue);
                    if (booleanValue) {
                        LogUtils.i("TAG", "getConnectedBtDevice: " + next.getName());
                        return next.getName();
                    }
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e2) {
                    e2.printStackTrace();
                } catch (InvocationTargetException e3) {
                    e3.printStackTrace();
                }
            }
        }
        return null;
    }

    public int[] getViewSize() {
        if (this.mSmallMode) {
            return new int[]{EventUtils.getPx(this.mContext, 326), (int) this.mContext.getResources().getDimension(R.dimen.screenHeight)};
        }
        return new int[]{EventUtils.getPx(this.mContext, 434), (int) this.mContext.getResources().getDimension(R.dimen.screenHeight)};
    }

    private Drawable getDrawable(int i) {
        return this.mContext.getResources().getDrawable(i, (Resources.Theme) null);
    }

    private int getColor(int i) {
        return this.mContext.getResources().getColor(i, (Resources.Theme) null);
    }

    public void refreshDateTime() {
        Calendar instance = Calendar.getInstance();
        int i = instance.get(5);
        int i2 = instance.get(2) + 1;
        TextView textView = this.tvDateDay;
        String str = "";
        if (textView != null) {
            textView.setText(i + str);
        }
        switch (i2) {
            case 1:
                str = this.mContext.getResources().getString(R.string.lb_month1);
                break;
            case 2:
                str = this.mContext.getResources().getString(R.string.lb_month2);
                break;
            case 3:
                str = this.mContext.getResources().getString(R.string.lb_month3);
                break;
            case 4:
                str = this.mContext.getResources().getString(R.string.lb_month4);
                break;
            case 5:
                str = this.mContext.getResources().getString(R.string.lb_month5);
                break;
            case 6:
                str = this.mContext.getResources().getString(R.string.lb_month6);
                break;
            case 7:
                str = this.mContext.getResources().getString(R.string.lb_month7);
                break;
            case 8:
                str = this.mContext.getResources().getString(R.string.lb_month8);
                break;
            case 9:
                str = this.mContext.getResources().getString(R.string.lb_month9);
                break;
            case 10:
                str = this.mContext.getResources().getString(R.string.lb_month10);
                break;
            case 11:
                str = this.mContext.getResources().getString(R.string.lb_month11);
                break;
            case 12:
                str = this.mContext.getResources().getString(R.string.lb_month12);
                break;
        }
        TextView textView2 = this.tvDateMonth;
        if (textView2 != null) {
            textView2.setText(str);
        }
    }
}
