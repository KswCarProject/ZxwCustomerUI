package com.szchoiceway.customerui.mianitem;

import android.util.Log;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

public class MyItemTouchHelperCallback extends ItemTouchHelper.Callback {
    private static final String TAG = "MyItemTouchHelperCallback";
    private MainAdapter mAdapter;

    public boolean isItemViewSwipeEnabled() {
        return false;
    }

    public boolean isLongPressDragEnabled() {
        return true;
    }

    public MyItemTouchHelperCallback(MainAdapter mainAdapter) {
        this.mAdapter = mainAdapter;
    }

    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        return makeMovementFlags(12, 3);
    }

    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder viewHolder2) {
        this.mAdapter.onItemMove(viewHolder, viewHolder2);
        return true;
    }

    public void onSwiped(RecyclerView.ViewHolder viewHolder, int i) {
        Log.d(TAG, "onSwiped direction = " + i);
    }

    public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int i) {
        super.onSelectedChanged(viewHolder, i);
        Log.d(TAG, "actionState = " + i);
        if (i != 0) {
            this.mAdapter.onItemSelect(viewHolder);
        }
    }

    public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        super.clearView(recyclerView, viewHolder);
        this.mAdapter.onItemClear(viewHolder);
    }
}
