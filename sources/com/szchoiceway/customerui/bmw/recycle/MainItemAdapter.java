package com.szchoiceway.customerui.bmw.recycle;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.RelativeLayout;
import com.szchoiceway.customerui.R;
import com.szchoiceway.customerui.utils.SysProviderOpt;
import com.szchoiceway.customerui.views.CustomerRelativelayout;
import com.szchoiceway.zxwlib.focus.view.FocusRecyclerViewAdapter;
import java.util.ArrayList;
import java.util.List;

public class MainItemAdapter extends FocusRecyclerViewAdapter implements View.OnLongClickListener, View.OnClickListener {
    /* access modifiers changed from: private */
    public List<RecycleItemBase> datas = new ArrayList();
    /* access modifiers changed from: private */
    public Context mContext;
    /* access modifiers changed from: private */
    public int mCurDisplayMode = 0;
    private LayoutInflater mLiLayoutInflater;
    /* access modifiers changed from: private */
    public int mPointerIndex = -1;
    /* access modifiers changed from: private */
    public boolean mSmallMode = false;
    private OnItemClickListener onItemClickListener;
    private OnItemLongClickListener onItemLongClickListener;
    private OnSmallModeListener onSmallModeListener;

    public interface OnItemClickListener {
        void onItemClick(View view, int i);
    }

    public interface OnItemLongClickListener {
        void onItemLongClick(View view, int i);
    }

    public interface OnSmallModeListener {
        void onSmallModeSwitch(boolean z);
    }

    public MainItemAdapter(Context context) {
        this.mContext = context;
        this.mLiLayoutInflater = LayoutInflater.from(context);
        this.mCurDisplayMode = SysProviderOpt.getInstance(this.mContext).getRecordInteger("KESAIWEI_SYS_DISPLAY_MODE", 0);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener2) {
        this.onItemClickListener = onItemClickListener2;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener2) {
        this.onItemLongClickListener = onItemLongClickListener2;
    }

    public void setOnSmallModeListener(OnSmallModeListener onSmallModeListener2) {
        this.onSmallModeListener = onSmallModeListener2;
    }

    public MainViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new MainViewHolder(this.mLiLayoutInflater.inflate(R.layout.bmw_id8_mainpage_item, viewGroup, false));
    }

    public int getItemCount() {
        List<RecycleItemBase> list = this.datas;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    public boolean onLongClick(View view) {
        if (this.onItemLongClickListener == null) {
            return true;
        }
        this.onItemLongClickListener.onItemLongClick(view, ((Integer) view.getTag()).intValue());
        return true;
    }

    public void onClick(View view) {
        if (this.onItemClickListener != null) {
            this.onItemClickListener.onItemClick(view, ((Integer) view.getTag()).intValue());
        }
    }

    public void performItemClick(int i) {
        OnItemClickListener onItemClickListener2 = this.onItemClickListener;
        if (onItemClickListener2 != null) {
            onItemClickListener2.onItemClick((View) null, i);
        }
    }

    public class MainViewHolder extends FocusRecyclerViewAdapter.ViewHolder {
        RelativeLayout reContainer;

        public MainViewHolder(View view) {
            super(view);
            this.reContainer = (RelativeLayout) view.findViewById(R.id.itemContainer);
        }

        /* access modifiers changed from: protected */
        public void onBind(int i) {
            ViewGroup viewGroup;
            this.itemView.setOnClickListener(MainItemAdapter.this);
            this.itemView.setOnLongClickListener(MainItemAdapter.this);
            if (MainItemAdapter.this.mSmallMode) {
                ((CustomerRelativelayout) this.itemView).setLongClickTime(300);
            } else {
                ((CustomerRelativelayout) this.itemView).setLongClickTime(500);
            }
            RecycleItemBase recycleItemBase = (RecycleItemBase) MainItemAdapter.this.datas.get(i);
            View setView = recycleItemBase.getSetView(MainItemAdapter.this.mContext, MainItemAdapter.this.mSmallMode);
            this.itemView.getLayoutParams().height = recycleItemBase.getViewSize()[1];
            this.itemView.getLayoutParams().width = recycleItemBase.getViewSize()[0];
            ViewParent parent = setView.getParent();
            if (!(parent == null || (viewGroup = (ViewGroup) parent) == null)) {
                viewGroup.removeView(setView);
            }
            this.itemView.setTag(Integer.valueOf(i));
            this.reContainer.removeAllViews();
            this.reContainer.addView(setView);
            if (MainItemAdapter.this.mContext != null) {
                if (MainItemAdapter.this.mPointerIndex == i) {
                    int access$400 = MainItemAdapter.this.mCurDisplayMode;
                    if (access$400 == 0) {
                        setView.setForeground(MainItemAdapter.this.mContext.getDrawable(R.drawable.bmw_id8_shape_focus_bk_blue));
                    } else if (access$400 == 1) {
                        setView.setForeground(MainItemAdapter.this.mContext.getDrawable(R.drawable.bmw_id8_shape_focus_bk_red));
                    } else if (access$400 == 2) {
                        setView.setForeground(MainItemAdapter.this.mContext.getDrawable(R.drawable.bmw_id8_shape_focus_bk_yellow));
                    }
                } else {
                    setView.setForeground((Drawable) null);
                }
            }
            if (MainItemAdapter.this.mCurFocusPositon != i || MainItemAdapter.this.mSmallMode) {
                recycleItemBase.updataFocusState(false);
            } else {
                recycleItemBase.updataFocusState(true);
            }
        }
    }

    public void setPointerIndex(int i) {
        this.mPointerIndex = i;
    }

    public void setCurDisplayMode(int i) {
        this.mCurDisplayMode = i;
    }

    public void setmSmallMode(boolean z) {
        OnSmallModeListener onSmallModeListener2 = this.onSmallModeListener;
        if (onSmallModeListener2 != null) {
            onSmallModeListener2.onSmallModeSwitch(z);
        }
        this.mSmallMode = z;
        if (z) {
            int size = this.datas.size() - 1;
            if (this.datas.get(size) instanceof AddItem) {
                this.datas.remove(size);
            }
            notifyDataSetChanged();
        }
    }

    public boolean ismSmallMode() {
        return this.mSmallMode;
    }

    public List<RecycleItemBase> getDatas() {
        return this.datas;
    }

    public void setDatas(List<RecycleItemBase> list) {
        this.datas.clear();
        this.datas.addAll(list);
        notifyDataSetChanged();
    }
}
