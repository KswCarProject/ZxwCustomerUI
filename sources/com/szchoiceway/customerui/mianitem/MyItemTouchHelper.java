package com.szchoiceway.customerui.mianitem;

import androidx.recyclerview.widget.RecyclerView;

public interface MyItemTouchHelper {
    void onItemClear(RecyclerView.ViewHolder viewHolder);

    void onItemDissmiss(RecyclerView.ViewHolder viewHolder);

    void onItemMove(RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder viewHolder2);

    void onItemSelect(RecyclerView.ViewHolder viewHolder);
}
