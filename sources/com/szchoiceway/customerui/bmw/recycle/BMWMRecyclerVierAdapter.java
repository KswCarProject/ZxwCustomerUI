package com.szchoiceway.customerui.bmw.recycle;

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
import com.szchoiceway.customerui.R;
import com.szchoiceway.customerui.drag.DragAppInfo;
import com.szchoiceway.customerui.drag.DragAppListInfo;
import com.szchoiceway.customerui.drag.DrawableUtils;
import com.szchoiceway.customerui.fragment.AppListView;
import com.szchoiceway.zxwlib.focus.view.FocusRecyclerViewAdapter;
import java.util.HashMap;

public class BMWMRecyclerVierAdapter extends FocusRecyclerViewAdapter implements View.OnClickListener, View.OnLongClickListener, View.OnTouchListener {
    private static final String TAG = "BMWMRecyclerVierAdapter";
    private AppListView mAppListView;
    /* access modifiers changed from: private */
    public Context mContext;
    private boolean mInEditMode = false;
    /* access modifiers changed from: private */
    public int mKSWDisplayMode = 0;
    private OnItemClickListener onItemClickListener;
    private OnItemLongClickListener onItemLongClickListener;
    /* access modifiers changed from: private */
    public boolean showFocus = false;

    public interface OnItemClickListener {
        void onItemClick(View view, int i);
    }

    public interface OnItemLongClickListener {
        void onItemLongClick(View view, int i);
    }

    public BMWMRecyclerVierAdapter(AppListView appListView, HashMap<String, DragAppInfo> hashMap) {
        this.mAppListView = appListView;
        this.mContext = appListView.getContext();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener2) {
        this.onItemClickListener = onItemClickListener2;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener2) {
        this.onItemLongClickListener = onItemLongClickListener2;
    }

    public void setData(HashMap<String, DragAppInfo> hashMap) {
        notifyDataSetChanged();
    }

    public MHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new MHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_app_item, viewGroup, false));
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
            onItemClickListener2.onItemClick(view, ((Integer) view.getTag()).intValue());
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

    public void performItemClick(int i) {
        OnItemClickListener onItemClickListener2 = this.onItemClickListener;
        if (onItemClickListener2 != null) {
            onItemClickListener2.onItemClick((View) null, i);
        }
    }

    class MHolder extends FocusRecyclerViewAdapter.ViewHolder {
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

        /* access modifiers changed from: protected */
        public void onBind(int i) {
            setIconInfo(i);
            RelativeLayout relativeLayout = this.mLayoutItemContent;
            if (relativeLayout != null) {
                relativeLayout.setTag(Integer.valueOf(i));
                this.mLayoutItemContent.setOnTouchListener(BMWMRecyclerVierAdapter.this);
                this.mLayoutItemContent.setOnClickListener(BMWMRecyclerVierAdapter.this);
                this.mLayoutItemContent.setOnLongClickListener(BMWMRecyclerVierAdapter.this);
            }
            if (this.mIvFocus != null) {
                int access$000 = BMWMRecyclerVierAdapter.this.mKSWDisplayMode;
                if (access$000 == 0) {
                    this.mIvFocus.setBackgroundResource(R.drawable.bwm_id8_applist_btn_bg_blue);
                } else if (access$000 == 1) {
                    this.mIvFocus.setBackgroundResource(R.drawable.bwm_id8_applist_btn_bg_red);
                } else if (access$000 == 2) {
                    this.mIvFocus.setBackgroundResource(R.drawable.bwm_id8_applist_btn_bg_yellow);
                }
                if (!BMWMRecyclerVierAdapter.this.showFocus) {
                    this.mIvFocus.setSelected(false);
                } else if (BMWMRecyclerVierAdapter.this.mCurFocusPositon == i) {
                    this.mIvFocus.setSelected(true);
                } else {
                    this.mIvFocus.setSelected(false);
                }
            }
        }

        private void setIconInfo(int i) {
            DragAppInfo dragAppInfo;
            Drawable drawable;
            if (i <= DragAppListInfo.mAppsFragmentItemTagList.size() - 1 && (dragAppInfo = DragAppListInfo.mAppFragmentItemDetailsMap.get(DragAppListInfo.mAppsFragmentItemTagList.get(i))) != null) {
                ImageView imageView = this.mIvIcon;
                if (imageView != null) {
                    imageView.setImageDrawable((Drawable) null);
                    int drawableId = dragAppInfo.getDrawableId();
                    if (drawableId == 0) {
                        drawable = DrawableUtils.getMergeDrawable(dragAppInfo.getDrawable(), BMWMRecyclerVierAdapter.this.mContext.getDrawable(R.drawable.imitate_auto_yingyong_baidi_));
                    } else {
                        drawable = BMWMRecyclerVierAdapter.this.mContext.getDrawable(drawableId);
                    }
                    this.mIvIcon.setImageDrawable(drawable);
                }
                TextView textView = this.mTvName;
                if (textView != null) {
                    textView.setText(dragAppInfo.getAppName());
                }
            }
        }
    }

    public void setmInEditMode(boolean z) {
        this.mInEditMode = z;
    }

    public void setmKSWDisplayMode(int i) {
        this.mKSWDisplayMode = i;
    }

    public void hideFocus() {
        this.showFocus = false;
    }

    public void showFocus() {
        this.showFocus = true;
    }

    public boolean getFocusStatus() {
        return this.showFocus;
    }
}
