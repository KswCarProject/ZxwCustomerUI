package com.szchoiceway.customerui.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.szchoiceway.customerui.R;
import com.szchoiceway.customerui.drag.DragAppInfo;
import com.szchoiceway.customerui.drag.DragAppListInfo;
import com.szchoiceway.customerui.drag.DrawableUtils;
import com.szchoiceway.customerui.fragment.AppListView;
import com.szchoiceway.zxwlib.SysProviderOpt;
import com.szchoiceway.zxwlib.bean.Customer;
import java.util.HashMap;

public class MRecyclerVierAdapter extends RecyclerView.Adapter<MHolder> implements View.OnClickListener, View.OnLongClickListener, View.OnTouchListener {
    private static final String TAG = "MRecyclerVierAdapter";
    private int iAppsFocusIndex = -1;
    private int lastFocusIndex = -1;
    private AppListView mAppListView;
    private Context mContext;
    private OnItemClickListener onItemClickListener;
    private OnItemLongClickListener onItemLongClickListener;
    private String resolution = "";

    public interface OnItemClickListener {
        void onItemClick(int i);
    }

    public interface OnItemLongClickListener {
        void onItemLongClick(View view, int i);
    }

    public MRecyclerVierAdapter(AppListView appListView, HashMap<String, DragAppInfo> hashMap) {
        this.mAppListView = appListView;
        Context context = appListView.getContext();
        this.mContext = context;
        this.resolution = SysProviderOpt.getInstance(context).getRecordValue("KSW_UI_RESOLUTION");
    }

    public OnItemClickListener getOnItemClickListener() {
        return this.onItemClickListener;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener2) {
        this.onItemClickListener = onItemClickListener2;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener2) {
        this.onItemLongClickListener = onItemLongClickListener2;
    }

    public void setData() {
        Log.d(TAG, "setData");
        this.iAppsFocusIndex = -1;
        this.lastFocusIndex = -1;
    }

    public void setiAppsFocusIndex(int i) {
        this.lastFocusIndex = this.iAppsFocusIndex;
        this.iAppsFocusIndex = i;
        Log.i(TAG, "setiAppsFocusIndex: iAppsFocusIndex = " + i);
        int i2 = this.lastFocusIndex;
        if (i2 >= 0) {
            notifyItemChanged(i2);
        }
        if (i >= 0) {
            notifyItemChanged(i);
        }
    }

    public int getiAppsFocusIndex() {
        return this.iAppsFocusIndex;
    }

    public MHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view;
        LayoutInflater from = LayoutInflater.from(viewGroup.getContext());
        if (Customer.isSmallResolution(this.mContext)) {
            view = from.inflate(R.layout.small_layout_app_item, viewGroup, false);
        } else {
            view = from.inflate(R.layout.layout_app_item, viewGroup, false);
        }
        return new MHolder(view);
    }

    public void onBindViewHolder(MHolder mHolder, int i) {
        setIconInfo(mHolder, i);
        if (mHolder.mLayoutItemContent != null) {
            mHolder.mLayoutItemContent.setTag(Integer.valueOf(i));
            mHolder.mLayoutItemContent.setOnTouchListener(this);
            mHolder.mLayoutItemContent.setOnClickListener(this);
            mHolder.mLayoutItemContent.setOnLongClickListener(this);
        }
    }

    private void setIconInfo(MHolder mHolder, int i) {
        Drawable drawable;
        if (this.iAppsFocusIndex == i) {
            mHolder.mIvFocus.setSelected(true);
        } else {
            mHolder.mIvFocus.setSelected(false);
        }
        DragAppInfo dragAppInfo = DragAppListInfo.mAppFragmentItemDetailsMap.get(DragAppListInfo.mAppsFragmentItemTagList.get(i));
        if (dragAppInfo != null) {
            if (mHolder.mIvIcon != null) {
                mHolder.mIvIcon.setImageDrawable((Drawable) null);
                int drawableId = dragAppInfo.getDrawableId();
                if (drawableId == 0) {
                    Drawable drawable2 = dragAppInfo.getDrawable();
                    if ("1280x480".equals(this.resolution)) {
                        drawable = DrawableUtils.getMergeDrawable(drawable2, this.mContext.getDrawable(R.drawable.baidi_1280x480));
                    } else {
                        drawable = DrawableUtils.getMergeDrawable(drawable2, this.mContext.getDrawable(R.drawable.imitate_auto_yingyong_baidi_));
                    }
                } else {
                    drawable = this.mContext.getDrawable(drawableId);
                }
                mHolder.mIvIcon.setImageDrawable(drawable);
            }
            if (mHolder.mTvName != null) {
                mHolder.mTvName.setText(dragAppInfo.getAppName());
            }
        }
    }

    public int getItemCount() {
        if (DragAppListInfo.mAppsFragmentItemTagList == null) {
            return 0;
        }
        return DragAppListInfo.mAppsFragmentItemTagList.size();
    }

    public void onClick(View view) {
        OnItemClickListener onItemClickListener2 = this.onItemClickListener;
        if (onItemClickListener2 != null) {
            onItemClickListener2.onItemClick(((Integer) view.getTag()).intValue());
        }
    }

    public boolean onLongClick(View view) {
        OnItemLongClickListener onItemLongClickListener2 = this.onItemLongClickListener;
        if (onItemLongClickListener2 != null) {
            onItemLongClickListener2.onItemLongClick(view, ((Integer) view.getTag()).intValue());
        }
        view.setAlpha(1.0f);
        return true;
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        Log.i(TAG, "onTouch: motionEvent.getAction() = " + motionEvent.getAction());
        int action = motionEvent.getAction();
        if (action == 0) {
            view.setAlpha(0.5f);
            return false;
        } else if (action != 1 && action != 2 && action != 3) {
            return false;
        } else {
            view.setAlpha(1.0f);
            return false;
        }
    }

    static class MHolder extends RecyclerView.ViewHolder {
        ImageView mIvFocus;
        ImageView mIvIcon;
        RelativeLayout mLayoutItemContent;
        TextView mTvName;

        public MHolder(View view) {
            super(view);
            this.mLayoutItemContent = (RelativeLayout) view.findViewById(R.id.LayoutItemContent);
            this.mIvFocus = (ImageView) view.findViewById(R.id.IvFocus);
            this.mIvIcon = (ImageView) view.findViewById(R.id.IvIcon);
            this.mTvName = (TextView) view.findViewById(R.id.TvName);
        }
    }
}
