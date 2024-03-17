package com.szchoiceway.customerui.mianitem;

import android.content.Context;
import android.util.Log;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.RelativeLayout;
import androidx.core.internal.view.SupportMenu;
import androidx.recyclerview.widget.RecyclerView;
import com.szchoiceway.customerui.R;
import com.szchoiceway.customerui.adapter.PagingScrollHelper;
import com.szchoiceway.customerui.mianitem.MainAdapter;
import com.szchoiceway.customerui.model.LauncherModel;
import java.util.ArrayList;
import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MyViewHolder> implements MyItemTouchHelper, View.OnDragListener {
    private static final String TAG = "MainAdapter";
    private List<String> datas;
    private int dragStartPos = -1;
    private boolean editEnable = false;
    private OnEditModeChangeListener editModeChangeListener;
    private String editTag = "";
    private int focusPosition = -1;
    private PagingScrollHelper helper;
    private int iModeSet = 18;
    private boolean isEdit = false;
    private ItemUtil itemUtil;
    private int lastFocusPosition = -1;
    private OnItemClickListener listener;
    private OnItemLongClickListener longClicklistener;
    private Context mContext;

    public interface OnEditModeChangeListener {
        void onEditModeChange(boolean z);
    }

    public interface OnItemClickListener {
        void onItemClick(int i);
    }

    public interface OnItemLongClickListener {
        void onItemLongClick(int i);
    }

    public MainAdapter(Context context, ItemUtil itemUtil2, boolean z) {
        this.mContext = context;
        this.itemUtil = itemUtil2;
        this.isEdit = z;
        this.iModeSet = LauncherModel.getInstance().m_iModeSet;
        this.datas = new ArrayList();
    }

    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new MyViewHolder(LayoutInflater.from(this.mContext).inflate(R.layout.main_page_item, viewGroup, false));
    }

    public void onBindViewHolder(MyViewHolder myViewHolder, int i) {
        ViewGroup viewGroup;
        View findViewById;
        String str = this.datas.get(i);
        ItemBase itemBase = ItemUtil.mapTagItem.get(str);
        int i2 = this.iModeSet;
        if (i2 == 18 || i2 == 22 || i2 == 30) {
            itemBase.setCurrentEditMode(this.editTag.equals(str));
            itemBase.setEditMode(this.isEdit);
        }
        View rootView = itemBase.getRootView(this.mContext, this.iModeSet);
        View findViewById2 = rootView.findViewById(R.id.btnEditLeft);
        if (findViewById2 != null) {
            findViewById2.setOnClickListener(new View.OnClickListener(i) {
                public final /* synthetic */ int f$1;

                {
                    this.f$1 = r2;
                }

                public final void onClick(View view) {
                    MainAdapter.this.lambda$onBindViewHolder$0$MainAdapter(this.f$1, view);
                }
            });
        }
        View findViewById3 = rootView.findViewById(R.id.btnEditRight);
        if (findViewById3 != null) {
            findViewById3.setOnClickListener(new View.OnClickListener(i) {
                public final /* synthetic */ int f$1;

                {
                    this.f$1 = r2;
                }

                public final void onClick(View view) {
                    MainAdapter.this.lambda$onBindViewHolder$1$MainAdapter(this.f$1, view);
                }
            });
        }
        View findViewById4 = rootView.findViewById(R.id.btnEditOk);
        if (findViewById4 != null) {
            findViewById4.setOnClickListener(new View.OnClickListener() {
                public final void onClick(View view) {
                    MainAdapter.this.lambda$onBindViewHolder$2$MainAdapter(view);
                }
            });
            findViewById4.setOnLongClickListener(new View.OnLongClickListener(myViewHolder) {
                public final /* synthetic */ MainAdapter.MyViewHolder f$1;

                {
                    this.f$1 = r2;
                }

                public final boolean onLongClick(View view) {
                    return MainAdapter.this.lambda$onBindViewHolder$3$MainAdapter(this.f$1, view);
                }
            });
        }
        View findViewById5 = rootView.findViewById(R.id.btnMain);
        if (findViewById5 != null) {
            findViewById5.setSelected(i == this.focusPosition);
            findViewById5.setOnClickListener(new View.OnClickListener(i) {
                public final /* synthetic */ int f$1;

                {
                    this.f$1 = r2;
                }

                public final void onClick(View view) {
                    MainAdapter.this.lambda$onBindViewHolder$4$MainAdapter(this.f$1, view);
                }
            });
            findViewById5.setOnLongClickListener(new View.OnLongClickListener(i) {
                public final /* synthetic */ int f$1;

                {
                    this.f$1 = r2;
                }

                public final boolean onLongClick(View view) {
                    return MainAdapter.this.lambda$onBindViewHolder$5$MainAdapter(this.f$1, view);
                }
            });
        }
        View findViewById6 = rootView.findViewById(R.id.btnMainBg);
        if (findViewById6 != null) {
            findViewById6.setOnClickListener(new View.OnClickListener(i) {
                public final /* synthetic */ int f$1;

                {
                    this.f$1 = r2;
                }

                public final void onClick(View view) {
                    MainAdapter.this.lambda$onBindViewHolder$6$MainAdapter(this.f$1, view);
                }
            });
        }
        if (str.equals("Music") && (findViewById = rootView.findViewById(R.id.viewMusic)) != null) {
            findViewById.setSelected(i == this.focusPosition);
            findViewById.setOnClickListener(new View.OnClickListener(i) {
                public final /* synthetic */ int f$1;

                {
                    this.f$1 = r2;
                }

                public final void onClick(View view) {
                    MainAdapter.this.lambda$onBindViewHolder$7$MainAdapter(this.f$1, view);
                }
            });
            findViewById.setOnLongClickListener(new View.OnLongClickListener(i) {
                public final /* synthetic */ int f$1;

                {
                    this.f$1 = r2;
                }

                public final boolean onLongClick(View view) {
                    return MainAdapter.this.lambda$onBindViewHolder$8$MainAdapter(this.f$1, view);
                }
            });
        }
        ViewParent parent = rootView.getParent();
        if (!(parent == null || (viewGroup = (ViewGroup) parent) == null)) {
            viewGroup.removeView(rootView);
        }
        myViewHolder.itemContainer.getLayoutParams().width = itemBase.getViewSize()[0];
        myViewHolder.itemContainer.getLayoutParams().height = itemBase.getViewSize()[1];
        myViewHolder.itemContainer.removeAllViews();
        myViewHolder.itemContainer.addView(rootView);
    }

    public /* synthetic */ void lambda$onBindViewHolder$0$MainAdapter(int i, View view) {
        if (i > 0 && i != this.itemUtil.getMainItemTags().size()) {
            this.itemUtil.exchangeItems(i, i - 1);
            setData(this.itemUtil.getAllItemTags());
            notifyDataSetChanged();
        }
    }

    public /* synthetic */ void lambda$onBindViewHolder$1$MainAdapter(int i, View view) {
        if (i < getItemCount() - 1 && i != this.itemUtil.getMainItemTags().size() - 1) {
            this.itemUtil.exchangeItems(i, i + 1);
            setData(this.itemUtil.getAllItemTags());
            notifyDataSetChanged();
        }
    }

    public /* synthetic */ void lambda$onBindViewHolder$2$MainAdapter(View view) {
        OnEditModeChangeListener onEditModeChangeListener = this.editModeChangeListener;
        if (onEditModeChangeListener != null) {
            onEditModeChangeListener.onEditModeChange(false);
        }
    }

    public /* synthetic */ boolean lambda$onBindViewHolder$3$MainAdapter(MyViewHolder myViewHolder, View view) {
        Log.d(TAG, "editTag = " + this.editTag);
        if (this.itemUtil.getAllItemTags().indexOf(this.editTag) < this.itemUtil.getMainItemTags().size()) {
            return true;
        }
        onItemClear(myViewHolder);
        this.itemUtil.deleteThirdApp(this.editTag);
        return true;
    }

    public /* synthetic */ void lambda$onBindViewHolder$4$MainAdapter(int i, View view) {
        OnItemClickListener onItemClickListener = this.listener;
        if (onItemClickListener != null) {
            onItemClickListener.onItemClick(i);
        }
    }

    public /* synthetic */ boolean lambda$onBindViewHolder$5$MainAdapter(int i, View view) {
        OnItemLongClickListener onItemLongClickListener = this.longClicklistener;
        if (onItemLongClickListener == null) {
            return true;
        }
        onItemLongClickListener.onItemLongClick(i);
        return true;
    }

    public /* synthetic */ void lambda$onBindViewHolder$6$MainAdapter(int i, View view) {
        OnItemClickListener onItemClickListener = this.listener;
        if (onItemClickListener != null) {
            onItemClickListener.onItemClick(i);
        }
    }

    public /* synthetic */ void lambda$onBindViewHolder$7$MainAdapter(int i, View view) {
        OnItemClickListener onItemClickListener = this.listener;
        if (onItemClickListener != null) {
            onItemClickListener.onItemClick(i);
        }
    }

    public /* synthetic */ boolean lambda$onBindViewHolder$8$MainAdapter(int i, View view) {
        OnItemLongClickListener onItemLongClickListener = this.longClicklistener;
        if (onItemLongClickListener == null) {
            return true;
        }
        onItemLongClickListener.onItemLongClick(i);
        return true;
    }

    public int getItemCount() {
        return this.datas.size();
    }

    public void onItemMove(RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder viewHolder2) {
        int adapterPosition = viewHolder.getAdapterPosition();
        int adapterPosition2 = viewHolder2.getAdapterPosition();
        if ((adapterPosition >= this.itemUtil.getMainItemTags().size() || adapterPosition2 < this.itemUtil.getMainItemTags().size()) && (adapterPosition < this.itemUtil.getMainItemTags().size() || adapterPosition2 >= this.itemUtil.getMainItemTags().size())) {
            this.itemUtil.exchangeItems(adapterPosition, adapterPosition2);
            this.datas.clear();
            this.datas.addAll(this.itemUtil.getAllItemTags());
            notifyItemMoved(adapterPosition, adapterPosition2);
            return;
        }
        onItemClear(viewHolder);
    }

    public void onItemDissmiss(RecyclerView.ViewHolder viewHolder) {
        int adapterPosition = viewHolder.getAdapterPosition();
        this.datas.remove(adapterPosition);
        notifyItemRemoved(adapterPosition);
    }

    public void onItemSelect(RecyclerView.ViewHolder viewHolder) {
        viewHolder.itemView.setScaleX(1.1f);
        viewHolder.itemView.setScaleY(1.1f);
        PagingScrollHelper pagingScrollHelper = this.helper;
        if (pagingScrollHelper != null) {
            pagingScrollHelper.setTouchEnable(false);
        }
    }

    public void onItemClear(RecyclerView.ViewHolder viewHolder) {
        viewHolder.itemView.setScaleX(1.0f);
        viewHolder.itemView.setScaleY(1.0f);
        PagingScrollHelper pagingScrollHelper = this.helper;
        if (pagingScrollHelper != null) {
            pagingScrollHelper.setTouchEnable(true);
        }
        notifyDataSetChanged();
    }

    public boolean onDrag(View view, DragEvent dragEvent) {
        int action = dragEvent.getAction();
        if (action == 3) {
            view.setBackgroundColor(0);
            Log.d(TAG, "ACTION_DROP startPost = " + this.dragStartPos + ", currentPos = " + view.getTag());
        } else if (action == 5) {
            view.setBackgroundColor(SupportMenu.CATEGORY_MASK);
            Log.d(TAG, "ACTION_DRAG_ENTERED startPost = " + this.dragStartPos + ", currentPos = " + view.getTag());
        } else if (action == 6) {
            view.setBackgroundColor(0);
            Log.d(TAG, "ACTION_DRAG_EXITED startPost = " + this.dragStartPos + ", currentPos = " + view.getTag());
        }
        return false;
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        RelativeLayout itemContainer;

        public MyViewHolder(View view) {
            super(view);
            this.itemContainer = (RelativeLayout) view.findViewById(R.id.itemContainer);
        }
    }

    public void setData(List<String> list) {
        Log.d(TAG, "setData size = " + list.size());
        if (this.datas != null && list.size() > 0) {
            this.datas.clear();
            this.datas.addAll(list);
        }
    }

    public void setFocusPosition(int i) {
        int i2 = this.focusPosition;
        this.lastFocusPosition = i2;
        this.focusPosition = i;
        notifyItemChanged(i2);
        notifyItemChanged(this.focusPosition);
    }

    public void setEditModePosition(int i) {
        this.editTag = this.datas.get(i);
        Log.d(TAG, "setEditModePosition editPosition = " + i + ", editTag = " + this.editTag);
        notifyItemChanged(i);
    }

    public void exitEditMode() {
        notifyItemChanged(this.datas.indexOf(this.editTag));
        this.editTag = "";
    }

    public void setEditEnable(boolean z) {
        this.editEnable = z;
    }

    public void setPageHelper(PagingScrollHelper pagingScrollHelper) {
        this.helper = pagingScrollHelper;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.listener = onItemClickListener;
    }

    public OnItemClickListener getOnItemClickListener() {
        return this.listener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener) {
        this.longClicklistener = onItemLongClickListener;
    }

    public void setOnEditModeChangeListener(OnEditModeChangeListener onEditModeChangeListener) {
        this.editModeChangeListener = onEditModeChangeListener;
    }
}
