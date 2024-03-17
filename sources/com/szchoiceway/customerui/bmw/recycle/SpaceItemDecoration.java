package com.szchoiceway.customerui.bmw.recycle;

import android.graphics.Rect;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;

public class SpaceItemDecoration extends RecyclerView.ItemDecoration {
    private int bottom;
    private int firstItemLeftOffset;
    private int left;
    private int right;
    private int top;

    public SpaceItemDecoration(int i, int i2, int i3, int i4, int i5) {
        this.left = i;
        this.top = i2;
        this.right = i3;
        this.bottom = i4;
        this.firstItemLeftOffset = i5;
    }

    public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, RecyclerView.State state) {
        if (recyclerView.getChildAdapterPosition(view) >= 0) {
            if (recyclerView.getChildAdapterPosition(view) == 0) {
                rect.left = this.firstItemLeftOffset;
            } else {
                rect.left = this.left;
            }
            rect.top = this.top;
            rect.right = this.right;
            rect.bottom = this.bottom;
        }
    }
}
