package com.szchoiceway.customerui.bmw.recycle;

import android.util.Log;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;
import java.util.Collections;

public class MyItemTouchHelperCallBack extends ItemTouchHelper.Callback {
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        return makeMovementFlags(15, 0);
    }

    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder viewHolder2) {
        int adapterPosition = viewHolder.getAdapterPosition();
        int adapterPosition2 = viewHolder2.getAdapterPosition();
        MainItemAdapter mainItemAdapter = (MainItemAdapter) recyclerView.getAdapter();
        if (mainItemAdapter != null) {
            try {
                if (mainItemAdapter.getDatas().size() > 0) {
                    if (adapterPosition < adapterPosition2) {
                        int i = adapterPosition;
                        while (i < adapterPosition2) {
                            int i2 = i + 1;
                            Collections.swap(mainItemAdapter.getDatas(), i, i2);
                            i = i2;
                        }
                    } else {
                        for (int i3 = adapterPosition; i3 > adapterPosition2; i3--) {
                            Collections.swap(mainItemAdapter.getDatas(), i3, i3 - 1);
                        }
                    }
                    mainItemAdapter.notifyItemMoved(adapterPosition, adapterPosition2);
                    try {
                        Log.i("TAG", "onMove: " + viewHolder.itemView.getX());
                        return true;
                    } catch (Exception unused) {
                        return true;
                    }
                }
            } catch (Exception unused2) {
            }
        }
        return false;
    }

    public void onSwiped(RecyclerView.ViewHolder viewHolder, int i) {
        Log.i("TAG", "onSwiped: " + i);
        Log.i("TAG", "onSwiped:getX " + viewHolder.itemView.getX());
    }
}
