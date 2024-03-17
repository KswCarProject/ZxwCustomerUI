package com.szchoiceway.customerui.dialog;

import android.content.Context;
import android.content.Intent;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.TextView;
import com.szchoiceway.customerui.R;
import com.szchoiceway.customerui.utils.EventUtils;
import com.szchoiceway.customerui.utils.SysProviderOpt;
import com.szchoiceway.zxwlib.bean.Customer;
import java.util.ArrayList;

public class BenzControlDialog implements View.OnClickListener {
    private static String TAG = "BenzControlDialog2";
    private CheckBox chkBag;
    private CheckBox chkChassisRise;
    private CheckBox chkDashboardLeft;
    private CheckBox chkDashboardRight;
    private CheckBox chkRadar;
    private CheckBox chkRadar2;
    private CheckBox chkSport;
    public boolean isShow = false;
    private boolean isSmallResolution = false;
    private Context mContext;
    private SysProviderOpt mProvider;
    private View mRootView;
    private WindowManager.LayoutParams params = new WindowManager.LayoutParams();
    private TextView tvEspStatus;
    private View viewDashboardLeft;
    private View viewDashboardRight;
    private WindowManager wm;

    public BenzControlDialog(Context context) {
        this.mContext = context;
        this.wm = (WindowManager) context.getSystemService("window");
        this.mProvider = SysProviderOpt.getInstance(context);
        this.isSmallResolution = Customer.isSmallResolution(this.mContext);
        initView();
    }

    private void initView() {
        int recordInteger = this.mProvider.getRecordInteger(SysProviderOpt.SYS_BENZ_CONTROL_INDEX, 1);
        if (recordInteger == 1) {
            if (this.isSmallResolution) {
                this.mRootView = View.inflate(this.mContext, R.layout.small_kesaiwei_benz_control1, (ViewGroup) null);
            } else {
                this.mRootView = View.inflate(this.mContext, R.layout.kesaiwei_benz_control1, (ViewGroup) null);
            }
        } else if (recordInteger == 2) {
            if (this.isSmallResolution) {
                this.mRootView = View.inflate(this.mContext, R.layout.small_kesaiwei_benz_control2, (ViewGroup) null);
            } else {
                this.mRootView = View.inflate(this.mContext, R.layout.kesaiwei_benz_control2, (ViewGroup) null);
            }
        } else if (this.isSmallResolution) {
            this.mRootView = View.inflate(this.mContext, R.layout.small_kesaiwei_benz_control3, (ViewGroup) null);
        } else {
            this.mRootView = View.inflate(this.mContext, R.layout.kesaiwei_benz_control3, (ViewGroup) null);
        }
        if (this.isSmallResolution) {
            DisplayMetrics displayMetrics = this.mContext.getResources().getDisplayMetrics();
            int dimension = (int) this.mContext.getResources().getDimension(R.dimen.small_benzControlDialog_height);
            this.params.x = 0;
            this.params.y = (displayMetrics.heightPixels - dimension) / 2;
            this.params.width = (int) this.mContext.getResources().getDimension(R.dimen.small_benzControlDialog_width);
            this.params.height = dimension;
        } else {
            this.params.x = 0;
            this.params.y = 0;
            this.params.width = (int) this.mContext.getResources().getDimension(R.dimen.benzControlDialog_width);
            this.params.height = (int) this.mContext.getResources().getDimension(R.dimen.benzControlDialog_height);
        }
        this.params.windowAnimations = 16973910;
        this.params.type = 2038;
        this.params.format = 1;
        this.params.flags = 296;
        this.params.gravity = 51;
        this.chkBag = (CheckBox) this.mRootView.findViewById(R.id.chkBag);
        this.chkChassisRise = (CheckBox) this.mRootView.findViewById(R.id.chkChassisRise);
        this.chkSport = (CheckBox) this.mRootView.findViewById(R.id.chkSport);
        this.chkRadar = (CheckBox) this.mRootView.findViewById(R.id.chkRadar);
        this.chkRadar2 = (CheckBox) this.mRootView.findViewById(R.id.chkRadar2);
        this.chkDashboardLeft = (CheckBox) this.mRootView.findViewById(R.id.chkDashboardLeft);
        this.chkDashboardRight = (CheckBox) this.mRootView.findViewById(R.id.chkDashboardRight);
        CheckBox checkBox = this.chkDashboardLeft;
        if (checkBox != null) {
            checkBox.setChecked(false);
        }
        CheckBox checkBox2 = this.chkDashboardRight;
        if (checkBox2 != null) {
            checkBox2.setChecked(false);
        }
        View findViewById = this.mRootView.findViewById(R.id.viewDashboardRight);
        this.viewDashboardRight = findViewById;
        if (findViewById != null) {
            findViewById.setVisibility(8);
        }
        View findViewById2 = this.mRootView.findViewById(R.id.viewDashboardLeft);
        this.viewDashboardLeft = findViewById2;
        if (findViewById2 != null) {
            findViewById2.setVisibility(8);
        }
        int[] iArr = {R.id.btnEsp, R.id.btnDashboardLeft, R.id.btnDashboardRight, R.id.btnFoldLeft, R.id.btnFoldRight, R.id.btnDashBoardRightBack, R.id.btnDashBoardRightSub, R.id.btnDashBoardRightAdd, R.id.btnDashBoardLeftBack, R.id.btnDashBoardLeftSub, R.id.btnDashBoardLeftAdd, R.id.btnChassisRise, R.id.btnRadar2, R.id.hideBenzDashboard, R.id.btnSport, R.id.btnSeat, R.id.btnCurtain};
        for (int i = 0; i < 17; i++) {
            View findViewById3 = this.mRootView.findViewById(iArr[i]);
            if (findViewById3 != null) {
                findViewById3.setOnClickListener(this);
            }
        }
        this.tvEspStatus = (TextView) this.mRootView.findViewById(R.id.tvEspStatus);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnChassisRise:
                ksw_Send8902BackcarMod_0x27(0, 1);
                return;
            case R.id.btnCurtain:
                ksw_Send8902BackcarMod_0x27(5, 9);
                return;
            case R.id.btnDashBoardLeftAdd:
                ksw_Send8902BackcarMod_0x27(3, 1);
                return;
            case R.id.btnDashBoardLeftBack:
                hideBenzDashboardLeft();
                return;
            case R.id.btnDashBoardLeftSub:
                ksw_Send8902BackcarMod_0x27(3, 2);
                return;
            case R.id.btnDashBoardRightAdd:
                ksw_Send8902BackcarMod_0x27(4, 1);
                return;
            case R.id.btnDashBoardRightBack:
                hideBenzDashboardRight();
                return;
            case R.id.btnDashBoardRightSub:
                ksw_Send8902BackcarMod_0x27(4, 2);
                return;
            case R.id.btnDashboardLeft:
                showBenzDashboardLeft();
                return;
            case R.id.btnDashboardRight:
                showBenzDashboardRight();
                return;
            case R.id.btnEsp:
                ksw_Send8902BackcarMod_0x27(5, 5);
                return;
            case R.id.btnFoldLeft:
                ksw_Send8902BackcarMod_0x27(5, 6);
                return;
            case R.id.btnFoldRight:
                ksw_Send8902BackcarMod_0x27(5, 7);
                return;
            case R.id.btnRadar2:
                ksw_Send8902BackcarMod_0x27(2, 1);
                return;
            case R.id.btnSeat:
                ksw_Send8902BackcarMod_0x27(5, 8);
                return;
            case R.id.btnSport:
                ksw_Send8902BackcarMod_0x27(1, 1);
                return;
            case R.id.hideBenzDashboard:
                hideDialog();
                return;
            default:
                return;
        }
    }

    public void refreshBenzControl(ArrayList<Integer> arrayList) {
        Log.d(TAG, "refreshBenzControl");
        boolean z = false;
        for (int i = 0; i < arrayList.size(); i++) {
            Log.d(TAG, " data" + i + " = " + arrayList.get(i));
        }
        CheckBox checkBox = this.chkChassisRise;
        if (checkBox != null) {
            checkBox.setChecked(arrayList.get(0).intValue() == 1);
        }
        CheckBox checkBox2 = this.chkSport;
        if (checkBox2 != null) {
            checkBox2.setChecked(arrayList.get(1).intValue() == 1);
        }
        CheckBox checkBox3 = this.chkRadar;
        if (checkBox3 != null) {
            checkBox3.setChecked(arrayList.get(2).intValue() == 1);
        }
        CheckBox checkBox4 = this.chkRadar2;
        if (checkBox4 != null) {
            checkBox4.setChecked(arrayList.get(2).intValue() == 1);
        }
        CheckBox checkBox5 = this.chkBag;
        if (checkBox5 != null) {
            if (arrayList.get(5).intValue() == 1) {
                z = true;
            }
            checkBox5.setChecked(z);
        }
    }

    private void ksw_Send8902BackcarMod_0x27(int i, int i2) {
        ArrayList arrayList = new ArrayList();
        for (int i3 = 0; i3 < 6; i3++) {
            if (i3 == i) {
                arrayList.add(Integer.valueOf(i2));
            } else {
                arrayList.add(0);
            }
        }
        Intent intent = new Intent(EventUtils.ZXW_ACTION_UPDATE_BENZ_CONTROL_DATA_RECEIVE);
        intent.putIntegerArrayListExtra("data", arrayList);
        this.mContext.sendBroadcast(intent);
    }

    private void showBenzDashboardLeft() {
        CheckBox checkBox = this.chkDashboardLeft;
        if (checkBox != null) {
            checkBox.setChecked(true);
        }
        CheckBox checkBox2 = this.chkDashboardRight;
        if (checkBox2 != null) {
            checkBox2.setChecked(true);
        }
        View view = this.viewDashboardLeft;
        if (view != null && view.getVisibility() == 8) {
            this.viewDashboardLeft.setVisibility(0);
        }
        View view2 = this.viewDashboardRight;
        if (view2 != null && view2.getVisibility() == 0) {
            this.viewDashboardRight.setVisibility(8);
        }
    }

    private void showBenzDashboardRight() {
        CheckBox checkBox = this.chkDashboardLeft;
        if (checkBox != null) {
            checkBox.setChecked(true);
        }
        CheckBox checkBox2 = this.chkDashboardRight;
        if (checkBox2 != null) {
            checkBox2.setChecked(true);
        }
        View view = this.viewDashboardRight;
        if (view != null && view.getVisibility() == 8) {
            this.viewDashboardRight.setVisibility(0);
        }
        View view2 = this.viewDashboardLeft;
        if (view2 != null && view2.getVisibility() == 0) {
            this.viewDashboardLeft.setVisibility(8);
        }
    }

    private void hideBenzDashboardLeft() {
        CheckBox checkBox = this.chkDashboardLeft;
        if (checkBox != null) {
            checkBox.setChecked(false);
        }
        CheckBox checkBox2 = this.chkDashboardRight;
        if (checkBox2 != null) {
            checkBox2.setChecked(false);
        }
        View view = this.viewDashboardLeft;
        if (view != null && view.getVisibility() == 0) {
            this.viewDashboardLeft.setVisibility(8);
        }
    }

    private void hideBenzDashboardRight() {
        CheckBox checkBox = this.chkDashboardLeft;
        if (checkBox != null) {
            checkBox.setChecked(false);
        }
        CheckBox checkBox2 = this.chkDashboardRight;
        if (checkBox2 != null) {
            checkBox2.setChecked(false);
        }
        View view = this.viewDashboardRight;
        if (view != null && view.getVisibility() == 0) {
            this.viewDashboardRight.setVisibility(8);
        }
    }

    private void hideBenzDashboard() {
        CheckBox checkBox = this.chkDashboardLeft;
        if (checkBox != null) {
            checkBox.setChecked(false);
        }
        CheckBox checkBox2 = this.chkDashboardRight;
        if (checkBox2 != null) {
            checkBox2.setChecked(false);
        }
        View view = this.viewDashboardRight;
        if (view != null && view.getVisibility() == 0) {
            this.viewDashboardRight.setVisibility(8);
        }
        View view2 = this.viewDashboardLeft;
        if (view2 != null && view2.getVisibility() == 0) {
            this.viewDashboardLeft.setVisibility(8);
        }
    }

    public void showDialog() {
        this.isShow = true;
        this.wm.addView(this.mRootView, this.params);
    }

    public void hideDialog() {
        this.isShow = false;
        hideBenzDashboard();
        this.wm.removeView(this.mRootView);
    }
}
