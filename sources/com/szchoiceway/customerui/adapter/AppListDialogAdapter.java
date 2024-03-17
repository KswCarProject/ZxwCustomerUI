package com.szchoiceway.customerui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import com.szchoiceway.customerui.R;
import com.szchoiceway.customerui.utils.AppInfo;
import com.szchoiceway.customerui.utils.EventUtils;
import com.szchoiceway.customerui.utils.SysProviderOpt;
import java.util.List;

public class AppListDialogAdapter extends BaseAdapter {
    private static final String TAG = "AppListDialogAdapter";
    private List<AppInfo> appInfoList;
    private Context mContext;
    private SysProviderOpt mProvider;

    public long getItemId(int i) {
        return (long) i;
    }

    public AppListDialogAdapter(Context context, List<AppInfo> list) {
        this.mContext = context;
        this.mProvider = SysProviderOpt.getInstance(context);
        this.appInfoList = list;
    }

    public int getCount() {
        return this.appInfoList.size();
    }

    public Object getItem(int i) {
        return this.appInfoList.get(i);
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = LayoutInflater.from(this.mContext).inflate(R.layout.pemp_id7_dialog_applist_item, (ViewGroup) null);
        }
        CheckBox checkBox = (CheckBox) view.findViewById(R.id.appCheck);
        if (checkBox != null) {
            checkBox.setChecked(isSelectedApp(i));
        }
        ImageView imageView = (ImageView) view.findViewById(R.id.appIcon);
        if (imageView != null) {
            imageView.setImageDrawable(this.appInfoList.get(i).getIcon());
        }
        TextView textView = (TextView) view.findViewById(R.id.appLabel);
        if (textView != null) {
            textView.setText(this.appInfoList.get(i).getLabel());
        }
        return view;
    }

    private boolean isSelectedApp(int i) {
        String packageName = this.appInfoList.get(i).getPackageName();
        String className = this.appInfoList.get(i).getClassName();
        String recordValue = this.mProvider.getRecordValue(EventUtils.LEFT_APP0_MODE_PACKAGE_NAME, "com.android.chrome");
        String recordValue2 = this.mProvider.getRecordValue(EventUtils.LEFT_APP0_MODE_CLASS_NAME, "com.google.android.apps.chrome.Main");
        String recordValue3 = this.mProvider.getRecordValue(EventUtils.LEFT_APP1_MODE_PACKAGE_NAME, "com.szchoiceway.musicplayer");
        String recordValue4 = this.mProvider.getRecordValue(EventUtils.LEFT_APP1_MODE_CLASS_NAME, "com.szchoiceway.musicplayer.MainActivity");
        String recordValue5 = this.mProvider.getRecordValue(EventUtils.LEFT_APP2_MODE_PACKAGE_NAME, "com.szchoiceway.videoplayer");
        String recordValue6 = this.mProvider.getRecordValue(EventUtils.LEFT_APP2_MODE_CLASS_NAME, "com.szchoiceway.videoplayer.MainActivity");
        if (packageName.equals(recordValue) && className.equals(recordValue2)) {
            return true;
        }
        if (!packageName.equals(recordValue3) || !className.equals(recordValue4)) {
            return packageName.equals(recordValue5) && className.equals(recordValue6);
        }
        return true;
    }
}
