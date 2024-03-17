package com.szchoiceway.customerui.mianitem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import com.szchoiceway.customerui.R;
import com.szchoiceway.customerui.mianitem.ItemUtil;
import com.szchoiceway.customerui.utils.EventUtils;
import com.szchoiceway.customerui.views.MyImageButton;
import com.szchoiceway.zxwlib.bean.Customer;

public class ItemSimple extends ItemBase implements View.OnClickListener {
    private static final String TAG = "ItemSimple";
    private ImageButton btnMain;
    private ImageButton btnMainBg;
    private ImageButton btnMainNext;
    private ImageButton btnMainPrev;
    private ItemUtil.AppUtilCallback callback;
    private boolean currentInEditMode;
    private int iModeSet;
    private ImageView imgIcon;
    private boolean inEditMode;
    private boolean isSmallResolution = false;
    private ItemUtil itemUtil;
    private View layoutEdit;
    private Context mContext;
    private View rootView = null;
    private String tag = "";
    private TextView textMain;
    private TextView textMessage;

    public ItemSimple(String str, ItemUtil.AppUtilCallback appUtilCallback, ItemUtil itemUtil2) {
        this.tag = str;
        this.callback = appUtilCallback;
        this.itemUtil = itemUtil2;
    }

    public View getRootView(Context context, int i) {
        this.mContext = context;
        this.iModeSet = i;
        boolean isSmallResolution2 = Customer.isSmallResolution(context);
        this.isSmallResolution = isSmallResolution2;
        if (isSmallResolution2) {
            this.rootView = LayoutInflater.from(this.mContext).inflate(R.layout.small_benz_main_item_layout, (ViewGroup) null);
        } else if (i == 22) {
            this.rootView = LayoutInflater.from(this.mContext).inflate(R.layout.benz_fy_main_item_layout, (ViewGroup) null);
        } else if (i == 27) {
            this.rootView = LayoutInflater.from(this.mContext).inflate(R.layout.audi_ty_main_item_layout, (ViewGroup) null);
        } else if (i == 29) {
            this.rootView = LayoutInflater.from(this.mContext).inflate(R.layout.audi_cdlc_main_item_layout, (ViewGroup) null);
        } else {
            this.rootView = LayoutInflater.from(this.mContext).inflate(R.layout.benz_main_item_layout, (ViewGroup) null);
        }
        initView();
        return this.rootView;
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:117:0x020a, code lost:
        if (r5 != 30) goto L_0x0225;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:129:0x0231, code lost:
        if (r5 != 30) goto L_0x024c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:171:0x02d9, code lost:
        if (r5 != 30) goto L_0x02ff;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:186:0x030b, code lost:
        if (r5 != 30) goto L_0x0326;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:228:0x03b3, code lost:
        if (r5 != 30) goto L_0x03ce;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:240:0x03da, code lost:
        if (r5 != 30) goto L_0x03f5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:280:0x0479, code lost:
        if (r5 != 30) goto L_0x0494;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:292:0x04a0, code lost:
        if (r5 != 30) goto L_0x04bb;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x00e5, code lost:
        if (r0.equals(com.szchoiceway.customerui.mianitem.ItemTag.CARPLAY_TAG) == false) goto L_0x00a6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:63:0x013b, code lost:
        if (r5 != 30) goto L_0x0156;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:75:0x0162, code lost:
        if (r5 != 30) goto L_0x017d;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void initView() {
        /*
            r8 = this;
            android.view.View r0 = r8.rootView
            if (r0 != 0) goto L_0x0005
            return
        L_0x0005:
            r1 = 1879638674(0x70090292, float:1.6961021E29)
            android.view.View r0 = r0.findViewById(r1)
            r8.layoutEdit = r0
            r1 = 0
            if (r0 == 0) goto L_0x001c
            boolean r2 = r8.currentInEditMode
            if (r2 == 0) goto L_0x0017
            r2 = r1
            goto L_0x0019
        L_0x0017:
            r2 = 8
        L_0x0019:
            r0.setVisibility(r2)
        L_0x001c:
            android.view.View r0 = r8.rootView
            r2 = 1879638301(0x7009011d, float:1.6960317E29)
            android.view.View r0 = r0.findViewById(r2)
            android.widget.ImageButton r0 = (android.widget.ImageButton) r0
            r8.btnMain = r0
            r2 = 1
            if (r0 == 0) goto L_0x0040
            boolean r3 = r8.currentInEditMode
            if (r3 == 0) goto L_0x0033
            r3 = 1056964608(0x3f000000, float:0.5)
            goto L_0x0035
        L_0x0033:
            r3 = 1065353216(0x3f800000, float:1.0)
        L_0x0035:
            r0.setAlpha(r3)
            android.widget.ImageButton r0 = r8.btnMain
            boolean r3 = r8.currentInEditMode
            r3 = r3 ^ r2
            r0.setEnabled(r3)
        L_0x0040:
            android.view.View r0 = r8.rootView
            r3 = 1879638304(0x70090120, float:1.6960323E29)
            android.view.View r0 = r0.findViewById(r3)
            android.widget.ImageButton r0 = (android.widget.ImageButton) r0
            r8.btnMainPrev = r0
            if (r0 == 0) goto L_0x0052
            r0.setOnClickListener(r8)
        L_0x0052:
            android.view.View r0 = r8.rootView
            r3 = 1879638303(0x7009011f, float:1.696032E29)
            android.view.View r0 = r0.findViewById(r3)
            android.widget.ImageButton r0 = (android.widget.ImageButton) r0
            r8.btnMainNext = r0
            if (r0 == 0) goto L_0x0064
            r0.setOnClickListener(r8)
        L_0x0064:
            android.view.View r0 = r8.rootView
            r3 = 1879638302(0x7009011e, float:1.6960319E29)
            android.view.View r0 = r0.findViewById(r3)
            android.widget.ImageButton r0 = (android.widget.ImageButton) r0
            r8.btnMainBg = r0
            android.view.View r0 = r8.rootView
            r3 = 1879638623(0x7009025f, float:1.6960925E29)
            android.view.View r0 = r0.findViewById(r3)
            android.widget.ImageView r0 = (android.widget.ImageView) r0
            r8.imgIcon = r0
            android.view.View r0 = r8.rootView
            r3 = 1879638946(0x700903a2, float:1.6961535E29)
            android.view.View r0 = r0.findViewById(r3)
            android.widget.TextView r0 = (android.widget.TextView) r0
            r8.textMain = r0
            android.view.View r0 = r8.rootView
            r3 = 1879638947(0x700903a3, float:1.6961537E29)
            android.view.View r0 = r0.findViewById(r3)
            android.widget.TextView r0 = (android.widget.TextView) r0
            r8.textMessage = r0
            java.lang.String r0 = r8.tag
            r0.hashCode()
            r3 = -1
            int r4 = r0.hashCode()
            r5 = 2
            switch(r4) {
                case -2076010264: goto L_0x00df;
                case 69120: goto L_0x00d4;
                case 2420678: goto L_0x00c9;
                case 870465087: goto L_0x00be;
                case 1443687921: goto L_0x00b3;
                case 1499275331: goto L_0x00a8;
                default: goto L_0x00a6;
            }
        L_0x00a6:
            r1 = r3
            goto L_0x00e8
        L_0x00a8:
            java.lang.String r1 = "Settings"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x00b1
            goto L_0x00a6
        L_0x00b1:
            r1 = 5
            goto L_0x00e8
        L_0x00b3:
            java.lang.String r1 = "Original"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x00bc
            goto L_0x00a6
        L_0x00bc:
            r1 = 4
            goto L_0x00e8
        L_0x00be:
            java.lang.String r1 = "AppList"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x00c7
            goto L_0x00a6
        L_0x00c7:
            r1 = 3
            goto L_0x00e8
        L_0x00c9:
            java.lang.String r1 = "Navi"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x00d2
            goto L_0x00a6
        L_0x00d2:
            r1 = r5
            goto L_0x00e8
        L_0x00d4:
            java.lang.String r1 = "Dvr"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x00dd
            goto L_0x00a6
        L_0x00dd:
            r1 = r2
            goto L_0x00e8
        L_0x00df:
            java.lang.String r2 = "Carplay"
            boolean r0 = r0.equals(r2)
            if (r0 != 0) goto L_0x00e8
            goto L_0x00a6
        L_0x00e8:
            r0 = 27
            r2 = 29
            r3 = 22
            r4 = 18
            r6 = 30
            switch(r1) {
                case 0: goto L_0x04fd;
                case 1: goto L_0x043e;
                case 2: goto L_0x036f;
                case 3: goto L_0x0295;
                case 4: goto L_0x01c6;
                case 5: goto L_0x00f7;
                default: goto L_0x00f5;
            }
        L_0x00f5:
            goto L_0x056b
        L_0x00f7:
            android.widget.ImageButton r1 = r8.btnMain
            if (r1 == 0) goto L_0x012f
            int r7 = r8.iModeSet
            if (r7 == r4) goto L_0x0116
            if (r7 == r3) goto L_0x010f
            if (r7 == r6) goto L_0x0104
            goto L_0x012f
        L_0x0104:
            com.szchoiceway.customerui.views.MyImageButton r1 = (com.szchoiceway.customerui.views.MyImageButton) r1
            int[] r5 = new int[r5]
            r5 = {1879573405, 1879573416} // fill-array
            r1.setTheme(r5)
            goto L_0x012f
        L_0x010f:
            r5 = 1879573366(0x70080376, float:1.6837658E29)
            r1.setBackgroundResource(r5)
            goto L_0x012f
        L_0x0116:
            boolean r7 = r8.isSmallResolution
            if (r7 == 0) goto L_0x0125
            com.szchoiceway.customerui.views.MyImageButton r1 = (com.szchoiceway.customerui.views.MyImageButton) r1
            int[] r5 = new int[r5]
            r5 = {1879575972, 1879575982} // fill-array
            r1.setTheme(r5)
            goto L_0x012f
        L_0x0125:
            com.szchoiceway.customerui.views.MyImageButton r1 = (com.szchoiceway.customerui.views.MyImageButton) r1
            int[] r5 = new int[r5]
            r5 = {1879574750, 1879574761} // fill-array
            r1.setTheme(r5)
        L_0x012f:
            android.widget.ImageButton r1 = r8.btnMainPrev
            if (r1 == 0) goto L_0x0156
            int r5 = r8.iModeSet
            if (r5 == r4) goto L_0x0145
            if (r5 == r3) goto L_0x0145
            if (r5 == r2) goto L_0x013e
            if (r5 == r6) goto L_0x0145
            goto L_0x0156
        L_0x013e:
            r5 = 1879572746(0x7008010a, float:1.6836487E29)
            r1.setBackgroundResource(r5)
            goto L_0x0156
        L_0x0145:
            boolean r5 = r8.isSmallResolution
            if (r5 == 0) goto L_0x0150
            r5 = 1879575951(0x70080d8f, float:1.6842541E29)
            r1.setBackgroundResource(r5)
            goto L_0x0156
        L_0x0150:
            r5 = 1879574514(0x700807f2, float:1.6839827E29)
            r1.setBackgroundResource(r5)
        L_0x0156:
            android.widget.ImageButton r1 = r8.btnMainNext
            if (r1 == 0) goto L_0x017d
            int r5 = r8.iModeSet
            if (r5 == r4) goto L_0x016c
            if (r5 == r3) goto L_0x016c
            if (r5 == r2) goto L_0x0165
            if (r5 == r6) goto L_0x016c
            goto L_0x017d
        L_0x0165:
            r3 = 1879572745(0x70080109, float:1.6836485E29)
            r1.setBackgroundResource(r3)
            goto L_0x017d
        L_0x016c:
            boolean r3 = r8.isSmallResolution
            if (r3 == 0) goto L_0x0177
            r3 = 1879575950(0x70080d8e, float:1.684254E29)
            r1.setBackgroundResource(r3)
            goto L_0x017d
        L_0x0177:
            r3 = 1879574513(0x700807f1, float:1.6839825E29)
            r1.setBackgroundResource(r3)
        L_0x017d:
            android.widget.ImageButton r1 = r8.btnMainBg
            if (r1 == 0) goto L_0x0195
            int r3 = r8.iModeSet
            if (r3 == r0) goto L_0x018f
            if (r3 == r2) goto L_0x0188
            goto L_0x0195
        L_0x0188:
            r2 = 1879572744(0x70080108, float:1.6836483E29)
            r1.setBackgroundResource(r2)
            goto L_0x0195
        L_0x018f:
            r2 = 1879573336(0x70080358, float:1.6837601E29)
            r1.setBackgroundResource(r2)
        L_0x0195:
            android.widget.ImageView r1 = r8.imgIcon
            if (r1 == 0) goto L_0x01a4
            int r2 = r8.iModeSet
            if (r2 == r0) goto L_0x019e
            goto L_0x01a4
        L_0x019e:
            r0 = 1879573274(0x7008031a, float:1.6837484E29)
            r1.setBackgroundResource(r0)
        L_0x01a4:
            android.widget.TextView r0 = r8.textMain
            if (r0 == 0) goto L_0x01b4
            android.content.Context r1 = r8.mContext
            r2 = 1880031462(0x700f00e6, float:1.7702977E29)
            java.lang.CharSequence r1 = r1.getText(r2)
            r0.setText(r1)
        L_0x01b4:
            android.widget.TextView r0 = r8.textMessage
            if (r0 == 0) goto L_0x056b
            android.content.Context r1 = r8.mContext
            r2 = 1880031457(0x700f00e1, float:1.7702968E29)
            java.lang.CharSequence r1 = r1.getText(r2)
            r0.setText(r1)
            goto L_0x056b
        L_0x01c6:
            android.widget.ImageButton r1 = r8.btnMain
            if (r1 == 0) goto L_0x01fe
            int r7 = r8.iModeSet
            if (r7 == r4) goto L_0x01e5
            if (r7 == r3) goto L_0x01de
            if (r7 == r6) goto L_0x01d3
            goto L_0x01fe
        L_0x01d3:
            com.szchoiceway.customerui.views.MyImageButton r1 = (com.szchoiceway.customerui.views.MyImageButton) r1
            int[] r5 = new int[r5]
            r5 = {1879573404, 1879573415} // fill-array
            r1.setTheme(r5)
            goto L_0x01fe
        L_0x01de:
            r5 = 1879573365(0x70080375, float:1.6837656E29)
            r1.setBackgroundResource(r5)
            goto L_0x01fe
        L_0x01e5:
            boolean r7 = r8.isSmallResolution
            if (r7 == 0) goto L_0x01f4
            com.szchoiceway.customerui.views.MyImageButton r1 = (com.szchoiceway.customerui.views.MyImageButton) r1
            int[] r5 = new int[r5]
            r5 = {1879575971, 1879575981} // fill-array
            r1.setTheme(r5)
            goto L_0x01fe
        L_0x01f4:
            com.szchoiceway.customerui.views.MyImageButton r1 = (com.szchoiceway.customerui.views.MyImageButton) r1
            int[] r5 = new int[r5]
            r5 = {1879574749, 1879574760} // fill-array
            r1.setTheme(r5)
        L_0x01fe:
            android.widget.ImageButton r1 = r8.btnMainPrev
            if (r1 == 0) goto L_0x0225
            int r5 = r8.iModeSet
            if (r5 == r4) goto L_0x0214
            if (r5 == r3) goto L_0x0214
            if (r5 == r2) goto L_0x020d
            if (r5 == r6) goto L_0x0214
            goto L_0x0225
        L_0x020d:
            r5 = 1879572743(0x70080107, float:1.6836481E29)
            r1.setBackgroundResource(r5)
            goto L_0x0225
        L_0x0214:
            boolean r5 = r8.isSmallResolution
            if (r5 == 0) goto L_0x021f
            r5 = 1879575949(0x70080d8d, float:1.6842537E29)
            r1.setBackgroundResource(r5)
            goto L_0x0225
        L_0x021f:
            r5 = 1879574505(0x700807e9, float:1.683981E29)
            r1.setBackgroundResource(r5)
        L_0x0225:
            android.widget.ImageButton r1 = r8.btnMainNext
            if (r1 == 0) goto L_0x024c
            int r5 = r8.iModeSet
            if (r5 == r4) goto L_0x023b
            if (r5 == r3) goto L_0x023b
            if (r5 == r2) goto L_0x0234
            if (r5 == r6) goto L_0x023b
            goto L_0x024c
        L_0x0234:
            r3 = 1879572742(0x70080106, float:1.683648E29)
            r1.setBackgroundResource(r3)
            goto L_0x024c
        L_0x023b:
            boolean r3 = r8.isSmallResolution
            if (r3 == 0) goto L_0x0246
            r3 = 1879575948(0x70080d8c, float:1.6842535E29)
            r1.setBackgroundResource(r3)
            goto L_0x024c
        L_0x0246:
            r3 = 1879574504(0x700807e8, float:1.6839808E29)
            r1.setBackgroundResource(r3)
        L_0x024c:
            android.widget.ImageButton r1 = r8.btnMainBg
            if (r1 == 0) goto L_0x0264
            int r3 = r8.iModeSet
            if (r3 == r0) goto L_0x025e
            if (r3 == r2) goto L_0x0257
            goto L_0x0264
        L_0x0257:
            r2 = 1879572741(0x70080105, float:1.6836478E29)
            r1.setBackgroundResource(r2)
            goto L_0x0264
        L_0x025e:
            r2 = 1879573335(0x70080357, float:1.68376E29)
            r1.setBackgroundResource(r2)
        L_0x0264:
            android.widget.ImageView r1 = r8.imgIcon
            if (r1 == 0) goto L_0x0273
            int r2 = r8.iModeSet
            if (r2 == r0) goto L_0x026d
            goto L_0x0273
        L_0x026d:
            r0 = 1879573246(0x700802fe, float:1.6837431E29)
            r1.setBackgroundResource(r0)
        L_0x0273:
            android.widget.TextView r0 = r8.textMain
            if (r0 == 0) goto L_0x0283
            android.content.Context r1 = r8.mContext
            r2 = 1880031416(0x700f00b8, float:1.770289E29)
            java.lang.CharSequence r1 = r1.getText(r2)
            r0.setText(r1)
        L_0x0283:
            android.widget.TextView r0 = r8.textMessage
            if (r0 == 0) goto L_0x056b
            android.content.Context r1 = r8.mContext
            r2 = 1880031281(0x700f0031, float:1.7702635E29)
            java.lang.CharSequence r1 = r1.getText(r2)
            r0.setText(r1)
            goto L_0x056b
        L_0x0295:
            android.widget.ImageButton r1 = r8.btnMain
            if (r1 == 0) goto L_0x02cd
            int r7 = r8.iModeSet
            if (r7 == r4) goto L_0x02b4
            if (r7 == r3) goto L_0x02ad
            if (r7 == r6) goto L_0x02a2
            goto L_0x02cd
        L_0x02a2:
            com.szchoiceway.customerui.views.MyImageButton r1 = (com.szchoiceway.customerui.views.MyImageButton) r1
            int[] r5 = new int[r5]
            r5 = {1879573397, 1879573408} // fill-array
            r1.setTheme(r5)
            goto L_0x02cd
        L_0x02ad:
            r5 = 1879573359(0x7008036f, float:1.6837645E29)
            r1.setBackgroundResource(r5)
            goto L_0x02cd
        L_0x02b4:
            boolean r7 = r8.isSmallResolution
            if (r7 == 0) goto L_0x02c3
            com.szchoiceway.customerui.views.MyImageButton r1 = (com.szchoiceway.customerui.views.MyImageButton) r1
            int[] r5 = new int[r5]
            r5 = {1879575965, 1879575975} // fill-array
            r1.setTheme(r5)
            goto L_0x02cd
        L_0x02c3:
            com.szchoiceway.customerui.views.MyImageButton r1 = (com.szchoiceway.customerui.views.MyImageButton) r1
            int[] r5 = new int[r5]
            r5 = {1879574742, 1879574753} // fill-array
            r1.setTheme(r5)
        L_0x02cd:
            android.widget.ImageButton r1 = r8.btnMainPrev
            if (r1 == 0) goto L_0x02ff
            int r5 = r8.iModeSet
            if (r5 == r4) goto L_0x02e3
            if (r5 == r3) goto L_0x02e3
            if (r5 == r2) goto L_0x02dc
            if (r5 == r6) goto L_0x02e3
            goto L_0x02ff
        L_0x02dc:
            r5 = 1879572724(0x700800f4, float:1.6836445E29)
            r1.setBackgroundResource(r5)
            goto L_0x02ff
        L_0x02e3:
            boolean r5 = r8.inEditMode
            if (r5 == 0) goto L_0x02ee
            r5 = 1879574452(0x700807b4, float:1.683971E29)
            r1.setBackgroundResource(r5)
            goto L_0x02ff
        L_0x02ee:
            boolean r5 = r8.isSmallResolution
            if (r5 == 0) goto L_0x02f9
            r5 = 1879575937(0x70080d81, float:1.6842515E29)
            r1.setBackgroundResource(r5)
            goto L_0x02ff
        L_0x02f9:
            r5 = 1879574451(0x700807b3, float:1.6839708E29)
            r1.setBackgroundResource(r5)
        L_0x02ff:
            android.widget.ImageButton r1 = r8.btnMainNext
            if (r1 == 0) goto L_0x0326
            int r5 = r8.iModeSet
            if (r5 == r4) goto L_0x0315
            if (r5 == r3) goto L_0x0315
            if (r5 == r2) goto L_0x030e
            if (r5 == r6) goto L_0x0315
            goto L_0x0326
        L_0x030e:
            r3 = 1879572723(0x700800f3, float:1.6836444E29)
            r1.setBackgroundResource(r3)
            goto L_0x0326
        L_0x0315:
            boolean r3 = r8.isSmallResolution
            if (r3 == 0) goto L_0x0320
            r3 = 1879575936(0x70080d80, float:1.6842513E29)
            r1.setBackgroundResource(r3)
            goto L_0x0326
        L_0x0320:
            r3 = 1879574450(0x700807b2, float:1.6839706E29)
            r1.setBackgroundResource(r3)
        L_0x0326:
            android.widget.ImageButton r1 = r8.btnMainBg
            if (r1 == 0) goto L_0x033e
            int r3 = r8.iModeSet
            if (r3 == r0) goto L_0x0338
            if (r3 == r2) goto L_0x0331
            goto L_0x033e
        L_0x0331:
            r2 = 1879572722(0x700800f2, float:1.6836442E29)
            r1.setBackgroundResource(r2)
            goto L_0x033e
        L_0x0338:
            r2 = 1879573327(0x7008034f, float:1.6837584E29)
            r1.setBackgroundResource(r2)
        L_0x033e:
            android.widget.ImageView r1 = r8.imgIcon
            if (r1 == 0) goto L_0x034d
            int r2 = r8.iModeSet
            if (r2 == r0) goto L_0x0347
            goto L_0x034d
        L_0x0347:
            r0 = 1879573237(0x700802f5, float:1.6837414E29)
            r1.setBackgroundResource(r0)
        L_0x034d:
            android.widget.TextView r0 = r8.textMain
            if (r0 == 0) goto L_0x035d
            android.content.Context r1 = r8.mContext
            r2 = 1880031497(0x700f0109, float:1.7703043E29)
            java.lang.CharSequence r1 = r1.getText(r2)
            r0.setText(r1)
        L_0x035d:
            android.widget.TextView r0 = r8.textMessage
            if (r0 == 0) goto L_0x056b
            android.content.Context r1 = r8.mContext
            r2 = 1880031312(0x700f0050, float:1.7702694E29)
            java.lang.CharSequence r1 = r1.getText(r2)
            r0.setText(r1)
            goto L_0x056b
        L_0x036f:
            android.widget.ImageButton r1 = r8.btnMain
            if (r1 == 0) goto L_0x03a7
            int r7 = r8.iModeSet
            if (r7 == r4) goto L_0x038e
            if (r7 == r3) goto L_0x0387
            if (r7 == r6) goto L_0x037c
            goto L_0x03a7
        L_0x037c:
            com.szchoiceway.customerui.views.MyImageButton r1 = (com.szchoiceway.customerui.views.MyImageButton) r1
            int[] r5 = new int[r5]
            r5 = {1879573403, 1879573414} // fill-array
            r1.setTheme(r5)
            goto L_0x03a7
        L_0x0387:
            r5 = 1879573364(0x70080374, float:1.6837654E29)
            r1.setBackgroundResource(r5)
            goto L_0x03a7
        L_0x038e:
            boolean r7 = r8.isSmallResolution
            if (r7 == 0) goto L_0x039d
            com.szchoiceway.customerui.views.MyImageButton r1 = (com.szchoiceway.customerui.views.MyImageButton) r1
            int[] r5 = new int[r5]
            r5 = {1879575970, 1879575980} // fill-array
            r1.setTheme(r5)
            goto L_0x03a7
        L_0x039d:
            com.szchoiceway.customerui.views.MyImageButton r1 = (com.szchoiceway.customerui.views.MyImageButton) r1
            int[] r5 = new int[r5]
            r5 = {1879574748, 1879574759} // fill-array
            r1.setTheme(r5)
        L_0x03a7:
            android.widget.ImageButton r1 = r8.btnMainPrev
            if (r1 == 0) goto L_0x03ce
            int r5 = r8.iModeSet
            if (r5 == r4) goto L_0x03bd
            if (r5 == r3) goto L_0x03bd
            if (r5 == r2) goto L_0x03b6
            if (r5 == r6) goto L_0x03bd
            goto L_0x03ce
        L_0x03b6:
            r5 = 1879572740(0x70080104, float:1.6836476E29)
            r1.setBackgroundResource(r5)
            goto L_0x03ce
        L_0x03bd:
            boolean r5 = r8.isSmallResolution
            if (r5 == 0) goto L_0x03c8
            r5 = 1879575947(0x70080d8b, float:1.6842534E29)
            r1.setBackgroundResource(r5)
            goto L_0x03ce
        L_0x03c8:
            r5 = 1879574503(0x700807e7, float:1.6839806E29)
            r1.setBackgroundResource(r5)
        L_0x03ce:
            android.widget.ImageButton r1 = r8.btnMainNext
            if (r1 == 0) goto L_0x03f5
            int r5 = r8.iModeSet
            if (r5 == r4) goto L_0x03e4
            if (r5 == r3) goto L_0x03e4
            if (r5 == r2) goto L_0x03dd
            if (r5 == r6) goto L_0x03e4
            goto L_0x03f5
        L_0x03dd:
            r3 = 1879572739(0x70080103, float:1.6836474E29)
            r1.setBackgroundResource(r3)
            goto L_0x03f5
        L_0x03e4:
            boolean r3 = r8.isSmallResolution
            if (r3 == 0) goto L_0x03ef
            r3 = 1879575946(0x70080d8a, float:1.6842532E29)
            r1.setBackgroundResource(r3)
            goto L_0x03f5
        L_0x03ef:
            r3 = 1879574502(0x700807e6, float:1.6839804E29)
            r1.setBackgroundResource(r3)
        L_0x03f5:
            android.widget.ImageButton r1 = r8.btnMainBg
            if (r1 == 0) goto L_0x040d
            int r3 = r8.iModeSet
            if (r3 == r0) goto L_0x0407
            if (r3 == r2) goto L_0x0400
            goto L_0x040d
        L_0x0400:
            r2 = 1879572738(0x70080102, float:1.6836472E29)
            r1.setBackgroundResource(r2)
            goto L_0x040d
        L_0x0407:
            r2 = 1879573334(0x70080356, float:1.6837598E29)
            r1.setBackgroundResource(r2)
        L_0x040d:
            android.widget.ImageView r1 = r8.imgIcon
            if (r1 == 0) goto L_0x041c
            int r2 = r8.iModeSet
            if (r2 == r0) goto L_0x0416
            goto L_0x041c
        L_0x0416:
            r0 = 1879573259(0x7008030b, float:1.6837456E29)
            r1.setBackgroundResource(r0)
        L_0x041c:
            android.widget.TextView r0 = r8.textMain
            if (r0 == 0) goto L_0x042c
            android.content.Context r1 = r8.mContext
            r2 = 1880031410(0x700f00b2, float:1.7702879E29)
            java.lang.CharSequence r1 = r1.getText(r2)
            r0.setText(r1)
        L_0x042c:
            android.widget.TextView r0 = r8.textMessage
            if (r0 == 0) goto L_0x056b
            android.content.Context r1 = r8.mContext
            r2 = 1880031411(0x700f00b3, float:1.770288E29)
            java.lang.CharSequence r1 = r1.getText(r2)
            r0.setText(r1)
            goto L_0x056b
        L_0x043e:
            android.widget.ImageButton r1 = r8.btnMain
            if (r1 == 0) goto L_0x046d
            int r7 = r8.iModeSet
            if (r7 == r4) goto L_0x0454
            if (r7 == r6) goto L_0x0449
            goto L_0x046d
        L_0x0449:
            com.szchoiceway.customerui.views.MyImageButton r1 = (com.szchoiceway.customerui.views.MyImageButton) r1
            int[] r5 = new int[r5]
            r5 = {1879573401, 1879573412} // fill-array
            r1.setTheme(r5)
            goto L_0x046d
        L_0x0454:
            boolean r7 = r8.isSmallResolution
            if (r7 == 0) goto L_0x0463
            com.szchoiceway.customerui.views.MyImageButton r1 = (com.szchoiceway.customerui.views.MyImageButton) r1
            int[] r5 = new int[r5]
            r5 = {1879575968, 1879575978} // fill-array
            r1.setTheme(r5)
            goto L_0x046d
        L_0x0463:
            com.szchoiceway.customerui.views.MyImageButton r1 = (com.szchoiceway.customerui.views.MyImageButton) r1
            int[] r5 = new int[r5]
            r5 = {1879574746, 1879574757} // fill-array
            r1.setTheme(r5)
        L_0x046d:
            android.widget.ImageButton r1 = r8.btnMainPrev
            if (r1 == 0) goto L_0x0494
            int r5 = r8.iModeSet
            if (r5 == r4) goto L_0x0483
            if (r5 == r3) goto L_0x0483
            if (r5 == r2) goto L_0x047c
            if (r5 == r6) goto L_0x0483
            goto L_0x0494
        L_0x047c:
            r5 = 1879572734(0x700800fe, float:1.6836464E29)
            r1.setBackgroundResource(r5)
            goto L_0x0494
        L_0x0483:
            boolean r5 = r8.isSmallResolution
            if (r5 == 0) goto L_0x048e
            r5 = 1879575943(0x70080d87, float:1.6842526E29)
            r1.setBackgroundResource(r5)
            goto L_0x0494
        L_0x048e:
            r5 = 1879574490(0x700807da, float:1.6839781E29)
            r1.setBackgroundResource(r5)
        L_0x0494:
            android.widget.ImageButton r1 = r8.btnMainNext
            if (r1 == 0) goto L_0x04bb
            int r5 = r8.iModeSet
            if (r5 == r4) goto L_0x04aa
            if (r5 == r3) goto L_0x04aa
            if (r5 == r2) goto L_0x04a3
            if (r5 == r6) goto L_0x04aa
            goto L_0x04bb
        L_0x04a3:
            r3 = 1879572733(0x700800fd, float:1.6836462E29)
            r1.setBackgroundResource(r3)
            goto L_0x04bb
        L_0x04aa:
            boolean r3 = r8.isSmallResolution
            if (r3 == 0) goto L_0x04b5
            r3 = 1879575942(0x70080d86, float:1.6842524E29)
            r1.setBackgroundResource(r3)
            goto L_0x04bb
        L_0x04b5:
            r3 = 1879574489(0x700807d9, float:1.683978E29)
            r1.setBackgroundResource(r3)
        L_0x04bb:
            android.widget.ImageButton r1 = r8.btnMainBg
            if (r1 == 0) goto L_0x04d3
            int r3 = r8.iModeSet
            if (r3 == r0) goto L_0x04cd
            if (r3 == r2) goto L_0x04c6
            goto L_0x04d3
        L_0x04c6:
            r2 = 1879572732(0x700800fc, float:1.683646E29)
            r1.setBackgroundResource(r2)
            goto L_0x04d3
        L_0x04cd:
            r2 = 1879573331(0x70080353, float:1.6837592E29)
            r1.setBackgroundResource(r2)
        L_0x04d3:
            android.widget.ImageView r1 = r8.imgIcon
            if (r1 == 0) goto L_0x04e2
            int r2 = r8.iModeSet
            if (r2 == r0) goto L_0x04dc
            goto L_0x04e2
        L_0x04dc:
            r0 = 1879573255(0x70080307, float:1.6837448E29)
            r1.setBackgroundResource(r0)
        L_0x04e2:
            android.widget.TextView r0 = r8.textMain
            if (r0 == 0) goto L_0x04eb
            java.lang.String r1 = "DVR"
            r0.setText(r1)
        L_0x04eb:
            android.widget.TextView r0 = r8.textMessage
            if (r0 == 0) goto L_0x056b
            android.content.Context r1 = r8.mContext
            r2 = 1880031348(0x700f0074, float:1.7702762E29)
            java.lang.CharSequence r1 = r1.getText(r2)
            r0.setText(r1)
            goto L_0x056b
        L_0x04fd:
            android.widget.ImageButton r1 = r8.btnMain
            if (r1 == 0) goto L_0x0519
            int r2 = r8.iModeSet
            if (r2 == r3) goto L_0x0513
            if (r2 == r6) goto L_0x0508
            goto L_0x0519
        L_0x0508:
            com.szchoiceway.customerui.views.MyImageButton r1 = (com.szchoiceway.customerui.views.MyImageButton) r1
            int[] r2 = new int[r5]
            r2 = {1879573399, 1879573410} // fill-array
            r1.setTheme(r2)
            goto L_0x0519
        L_0x0513:
            r2 = 1879573361(0x70080371, float:1.6837649E29)
            r1.setBackgroundResource(r2)
        L_0x0519:
            android.widget.ImageButton r1 = r8.btnMainPrev
            if (r1 == 0) goto L_0x0523
            r2 = 1879574467(0x700807c3, float:1.6839738E29)
            r1.setBackgroundResource(r2)
        L_0x0523:
            android.widget.ImageButton r1 = r8.btnMainNext
            if (r1 == 0) goto L_0x052d
            r2 = 1879574466(0x700807c2, float:1.6839736E29)
            r1.setBackgroundResource(r2)
        L_0x052d:
            android.widget.ImageButton r1 = r8.btnMainBg
            if (r1 == 0) goto L_0x053c
            int r2 = r8.iModeSet
            if (r2 == r0) goto L_0x0536
            goto L_0x053c
        L_0x0536:
            r2 = 1879573329(0x70080351, float:1.6837588E29)
            r1.setBackgroundResource(r2)
        L_0x053c:
            android.widget.ImageView r1 = r8.imgIcon
            if (r1 == 0) goto L_0x054b
            int r2 = r8.iModeSet
            if (r2 == r0) goto L_0x0545
            goto L_0x054b
        L_0x0545:
            r0 = 1879573271(0x70080317, float:1.6837479E29)
            r1.setBackgroundResource(r0)
        L_0x054b:
            android.widget.TextView r0 = r8.textMain
            if (r0 == 0) goto L_0x055b
            android.content.Context r1 = r8.mContext
            r2 = 1880031333(0x700f0065, float:1.7702733E29)
            java.lang.CharSequence r1 = r1.getText(r2)
            r0.setText(r1)
        L_0x055b:
            android.widget.TextView r0 = r8.textMessage
            if (r0 == 0) goto L_0x056b
            android.content.Context r1 = r8.mContext
            r2 = 1880031334(0x700f0066, float:1.7702735E29)
            java.lang.CharSequence r1 = r1.getText(r2)
            r0.setText(r1)
        L_0x056b:
            r8.updateInfo()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.szchoiceway.customerui.mianitem.ItemSimple.initView():void");
    }

    public void onClick(View view) {
        if (view.getId() == R.id.btnMainPrev && this.callback != null) {
            String tag2 = getTag();
            int indexOf = this.itemUtil.getMainItemTags().indexOf(tag2);
            if (this.inEditMode) {
                tag2 = "showAppList";
            }
            this.callback.onItemClickByTag(tag2);
            this.callback.refreshMainFocusRecyclerView(indexOf);
        }
    }

    public void updateInfo() {
        ImageButton imageButton = this.btnMain;
        if (imageButton != null && (imageButton instanceof MyImageButton)) {
            ((MyImageButton) imageButton).init();
        }
    }

    public int[] getViewSize() {
        if (this.isSmallResolution) {
            return new int[]{EventUtils.getPx(this.mContext, this.mContext.getResources().getDisplayMetrics().widthPixels / 5), EventUtils.getPx(this.mContext, 300)};
        }
        int i = this.iModeSet;
        if (i == 22) {
            return new int[]{EventUtils.getPx(this.mContext, 384), EventUtils.getPx(this.mContext, 444)};
        } else if (i == 27) {
            return new int[]{EventUtils.getPx(this.mContext, 317), EventUtils.getPx(this.mContext, 545)};
        } else if (i == 29) {
            return new int[]{EventUtils.getPx(this.mContext, 356), EventUtils.getPx(this.mContext, 501)};
        } else {
            return new int[]{EventUtils.getPx(this.mContext, 384), EventUtils.getPx(this.mContext, 441)};
        }
    }

    public String getTag() {
        return this.tag;
    }

    public void setCurrentEditMode(boolean z) {
        this.currentInEditMode = z;
    }

    public void setEditMode(boolean z) {
        this.inEditMode = z;
    }
}
