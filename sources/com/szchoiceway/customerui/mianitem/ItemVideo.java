package com.szchoiceway.customerui.mianitem;

import android.content.Context;
import android.os.RemoteException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import com.szchoiceway.customerui.R;
import com.szchoiceway.customerui.mianitem.ItemUtil;
import com.szchoiceway.customerui.model.LauncherModel;
import com.szchoiceway.customerui.utils.EventUtils;
import com.szchoiceway.customerui.views.MyImageButton;
import com.szchoiceway.eventcenter.IEventService;
import com.szchoiceway.zxwlib.bean.Customer;

public class ItemVideo extends ItemBase implements View.OnClickListener {
    private ImageButton btnMain;
    private ImageButton btnMainBg;
    private ImageButton btnMainNext;
    private ImageButton btnMainPrev;
    private ItemUtil.AppUtilCallback callback;
    private boolean currentInEditMode;
    private int iModeSet;
    private ImageView imgIcon;
    private boolean isSmallResolution = false;
    private ItemUtil itemUtil;
    private View layoutEdit;
    private Context mContext;
    private View rootView = null;
    private TextView textMain;
    private TextView textMessage;

    public String getTag() {
        return "Video";
    }

    public void setEditMode(boolean z) {
    }

    public ItemVideo(ItemUtil.AppUtilCallback appUtilCallback, ItemUtil itemUtil2) {
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
            this.rootView = LayoutInflater.from(this.mContext).inflate(R.layout.audi_ty_main_item_layout_video, (ViewGroup) null);
        } else if (i == 29) {
            this.rootView = LayoutInflater.from(this.mContext).inflate(R.layout.audi_cdlc_main_item_layout, (ViewGroup) null);
        } else {
            this.rootView = LayoutInflater.from(this.mContext).inflate(R.layout.benz_main_item_layout, (ViewGroup) null);
        }
        initView();
        return this.rootView;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:35:0x009f, code lost:
        if (r0 != 30) goto L_0x00c0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x00da, code lost:
        if (r0 != 30) goto L_0x00fb;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void initView() {
        /*
            r6 = this;
            android.view.View r0 = r6.rootView
            if (r0 != 0) goto L_0x0005
            return
        L_0x0005:
            r1 = 1879638674(0x70090292, float:1.6961021E29)
            android.view.View r0 = r0.findViewById(r1)
            r6.layoutEdit = r0
            if (r0 == 0) goto L_0x001b
            boolean r1 = r6.currentInEditMode
            if (r1 == 0) goto L_0x0016
            r1 = 0
            goto L_0x0018
        L_0x0016:
            r1 = 8
        L_0x0018:
            r0.setVisibility(r1)
        L_0x001b:
            android.view.View r0 = r6.rootView
            r1 = 1879638301(0x7009011d, float:1.6960317E29)
            android.view.View r0 = r0.findViewById(r1)
            android.widget.ImageButton r0 = (android.widget.ImageButton) r0
            r6.btnMain = r0
            r1 = 30
            r2 = 22
            r3 = 18
            if (r0 == 0) goto L_0x0083
            boolean r4 = r6.currentInEditMode
            if (r4 == 0) goto L_0x0037
            r4 = 1056964608(0x3f000000, float:0.5)
            goto L_0x0039
        L_0x0037:
            r4 = 1065353216(0x3f800000, float:1.0)
        L_0x0039:
            r0.setAlpha(r4)
            android.widget.ImageButton r0 = r6.btnMain
            boolean r4 = r6.currentInEditMode
            r4 = r4 ^ 1
            r0.setEnabled(r4)
            android.widget.ImageButton r0 = r6.btnMain
            boolean r4 = r0 instanceof com.szchoiceway.customerui.views.MyImageButton
            if (r4 == 0) goto L_0x0078
            int r4 = r6.iModeSet
            r5 = 2
            if (r4 == r3) goto L_0x005e
            if (r4 == r1) goto L_0x0053
            goto L_0x0083
        L_0x0053:
            com.szchoiceway.customerui.views.MyImageButton r0 = (com.szchoiceway.customerui.views.MyImageButton) r0
            int[] r4 = new int[r5]
            r4 = {1879573406, 1879573417} // fill-array
            r0.setTheme(r4)
            goto L_0x0083
        L_0x005e:
            boolean r4 = r6.isSmallResolution
            if (r4 == 0) goto L_0x006d
            com.szchoiceway.customerui.views.MyImageButton r0 = (com.szchoiceway.customerui.views.MyImageButton) r0
            int[] r4 = new int[r5]
            r4 = {1879575973, 1879575983} // fill-array
            r0.setTheme(r4)
            goto L_0x0083
        L_0x006d:
            com.szchoiceway.customerui.views.MyImageButton r0 = (com.szchoiceway.customerui.views.MyImageButton) r0
            int[] r4 = new int[r5]
            r4 = {1879574751, 1879574762} // fill-array
            r0.setTheme(r4)
            goto L_0x0083
        L_0x0078:
            int r4 = r6.iModeSet
            if (r4 == r2) goto L_0x007d
            goto L_0x0083
        L_0x007d:
            r4 = 1879573367(0x70080377, float:1.683766E29)
            r0.setBackgroundResource(r4)
        L_0x0083:
            android.view.View r0 = r6.rootView
            r4 = 1879638304(0x70090120, float:1.6960323E29)
            android.view.View r0 = r0.findViewById(r4)
            android.widget.ImageButton r0 = (android.widget.ImageButton) r0
            r6.btnMainPrev = r0
            r4 = 29
            if (r0 == 0) goto L_0x00c0
            r0.setOnClickListener(r6)
            int r0 = r6.iModeSet
            if (r0 == r3) goto L_0x00ab
            if (r0 == r2) goto L_0x00ab
            if (r0 == r4) goto L_0x00a2
            if (r0 == r1) goto L_0x00ab
            goto L_0x00c0
        L_0x00a2:
            android.widget.ImageButton r0 = r6.btnMainPrev
            r5 = 1879572737(0x70080101, float:1.683647E29)
            r0.setBackgroundResource(r5)
            goto L_0x00c0
        L_0x00ab:
            boolean r0 = r6.isSmallResolution
            if (r0 == 0) goto L_0x00b8
            android.widget.ImageButton r0 = r6.btnMainPrev
            r5 = 1879575945(0x70080d89, float:1.684253E29)
            r0.setBackgroundResource(r5)
            goto L_0x00c0
        L_0x00b8:
            android.widget.ImageButton r0 = r6.btnMainPrev
            r5 = 1879574501(0x700807e5, float:1.6839802E29)
            r0.setBackgroundResource(r5)
        L_0x00c0:
            android.view.View r0 = r6.rootView
            r5 = 1879638303(0x7009011f, float:1.696032E29)
            android.view.View r0 = r0.findViewById(r5)
            android.widget.ImageButton r0 = (android.widget.ImageButton) r0
            r6.btnMainNext = r0
            if (r0 == 0) goto L_0x00fb
            r0.setOnClickListener(r6)
            int r0 = r6.iModeSet
            if (r0 == r3) goto L_0x00e6
            if (r0 == r2) goto L_0x00e6
            if (r0 == r4) goto L_0x00dd
            if (r0 == r1) goto L_0x00e6
            goto L_0x00fb
        L_0x00dd:
            android.widget.ImageButton r0 = r6.btnMainNext
            r1 = 1879572736(0x70080100, float:1.6836468E29)
            r0.setBackgroundResource(r1)
            goto L_0x00fb
        L_0x00e6:
            boolean r0 = r6.isSmallResolution
            if (r0 == 0) goto L_0x00f3
            android.widget.ImageButton r0 = r6.btnMainNext
            r1 = 1879575944(0x70080d88, float:1.6842528E29)
            r0.setBackgroundResource(r1)
            goto L_0x00fb
        L_0x00f3:
            android.widget.ImageButton r0 = r6.btnMainNext
            r1 = 1879574500(0x700807e4, float:1.68398E29)
            r0.setBackgroundResource(r1)
        L_0x00fb:
            android.view.View r0 = r6.rootView
            r1 = 1879638302(0x7009011e, float:1.6960319E29)
            android.view.View r0 = r0.findViewById(r1)
            android.widget.ImageButton r0 = (android.widget.ImageButton) r0
            r6.btnMainBg = r0
            r1 = 27
            if (r0 == 0) goto L_0x0120
            int r2 = r6.iModeSet
            if (r2 == r1) goto L_0x011a
            if (r2 == r4) goto L_0x0113
            goto L_0x0120
        L_0x0113:
            r2 = 1879572747(0x7008010b, float:1.6836489E29)
            r0.setBackgroundResource(r2)
            goto L_0x0120
        L_0x011a:
            r2 = 1879573337(0x70080359, float:1.6837603E29)
            r0.setBackgroundResource(r2)
        L_0x0120:
            android.view.View r0 = r6.rootView
            r2 = 1879638623(0x7009025f, float:1.6960925E29)
            android.view.View r0 = r0.findViewById(r2)
            android.widget.ImageView r0 = (android.widget.ImageView) r0
            r6.imgIcon = r0
            if (r0 == 0) goto L_0x013a
            int r2 = r6.iModeSet
            if (r2 == r1) goto L_0x0134
            goto L_0x013a
        L_0x0134:
            r2 = 1879573280(0x70080320, float:1.6837496E29)
            r0.setBackgroundResource(r2)
        L_0x013a:
            android.view.View r0 = r6.rootView
            r2 = 1879638946(0x700903a2, float:1.6961535E29)
            android.view.View r0 = r0.findViewById(r2)
            android.widget.TextView r0 = (android.widget.TextView) r0
            r6.textMain = r0
            r2 = 1880031289(0x700f0039, float:1.770265E29)
            if (r0 == 0) goto L_0x0184
            com.szchoiceway.customerui.model.LauncherModel r0 = com.szchoiceway.customerui.model.LauncherModel.getInstance()
            java.lang.String r0 = r0.videoTitle
            java.lang.String r3 = ""
            boolean r0 = r3.equals(r0)
            if (r0 == 0) goto L_0x0179
            int r0 = r6.iModeSet
            if (r0 != r1) goto L_0x016a
            android.widget.TextView r0 = r6.textMain
            android.content.Context r1 = r6.mContext
            java.lang.CharSequence r1 = r1.getText(r2)
            r0.setText(r1)
            goto L_0x0184
        L_0x016a:
            android.widget.TextView r0 = r6.textMain
            android.content.Context r1 = r6.mContext
            r3 = 1880031481(0x700f00f9, float:1.7703013E29)
            java.lang.CharSequence r1 = r1.getText(r3)
            r0.setText(r1)
            goto L_0x0184
        L_0x0179:
            android.widget.TextView r0 = r6.textMain
            com.szchoiceway.customerui.model.LauncherModel r1 = com.szchoiceway.customerui.model.LauncherModel.getInstance()
            java.lang.String r1 = r1.videoTitle
            r0.setText(r1)
        L_0x0184:
            android.view.View r0 = r6.rootView
            r1 = 1879638947(0x700903a3, float:1.6961537E29)
            android.view.View r0 = r0.findViewById(r1)
            android.widget.TextView r0 = (android.widget.TextView) r0
            r6.textMessage = r0
            if (r0 == 0) goto L_0x019c
            android.content.Context r1 = r6.mContext
            java.lang.CharSequence r1 = r1.getText(r2)
            r0.setText(r1)
        L_0x019c:
            r6.refreshPlayState()
            r6.updateInfo()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.szchoiceway.customerui.mianitem.ItemVideo.initView():void");
    }

    public void onClick(View view) {
        int indexOf = this.itemUtil.getMainItemTags().indexOf(getTag());
        switch (view.getId()) {
            case R.id.btnMainNext:
                ItemUtil.AppUtilCallback appUtilCallback = this.callback;
                if (appUtilCallback != null) {
                    appUtilCallback.onItemClickByTag("playNext");
                    this.callback.refreshMainFocusRecyclerView(indexOf);
                    return;
                }
                return;
            case R.id.btnMainPrev:
                ItemUtil.AppUtilCallback appUtilCallback2 = this.callback;
                if (appUtilCallback2 != null) {
                    appUtilCallback2.onItemClickByTag("playPrev");
                    this.callback.refreshMainFocusRecyclerView(indexOf);
                    return;
                }
                return;
            default:
                return;
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

    public void setCurrentEditMode(boolean z) {
        this.currentInEditMode = z;
    }

    public void refreshPlayState() {
        IEventService evtService = LauncherModel.getInstance().getEvtService();
        if (evtService != null) {
            try {
                if (evtService.getValidMode() == EventUtils.eSrcMode.SRC_MOVIE.getIntValue()) {
                    String validModeTitleInfor = evtService.getValidModeTitleInfor();
                    evtService.getValidModeArtistInfor();
                    evtService.getValidModeAblumInfor();
                    evtService.getValidCurTime();
                    evtService.getValidTotTime();
                    TextView textView = this.textMain;
                    if (textView != null) {
                        textView.setText(validModeTitleInfor);
                        LauncherModel.getInstance().videoTitle = validModeTitleInfor;
                        return;
                    }
                    return;
                }
                TextView textView2 = this.textMain;
                if (textView2 == null) {
                    return;
                }
                if (this.iModeSet == 27) {
                    textView2.setText(this.mContext.getText(R.string.lb_Do_not_watch_the_video_while_driving));
                    LauncherModel.getInstance().videoTitle = this.mContext.getText(R.string.lb_Do_not_watch_the_video_while_driving).toString();
                    return;
                }
                textView2.setText(this.mContext.getText(R.string.lb_video));
                LauncherModel.getInstance().videoTitle = this.mContext.getText(R.string.lb_video).toString();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }
}
