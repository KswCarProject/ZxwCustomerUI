package com.szchoiceway.customerui.adapter;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.View;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

public class DividerGridItemDecoration extends RecyclerView.ItemDecoration {
    private static final int[] ATTRS = {16843284};
    private Drawable mDivider;

    public DividerGridItemDecoration(Context context) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(ATTRS);
        this.mDivider = obtainStyledAttributes.getDrawable(0);
        obtainStyledAttributes.recycle();
    }

    public void onDraw(Canvas canvas, RecyclerView recyclerView, RecyclerView.State state) {
        drawHorizontal(canvas, recyclerView);
        drawVertical(canvas, recyclerView);
    }

    private int getSpanCount(RecyclerView recyclerView) {
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            return ((GridLayoutManager) layoutManager).getSpanCount();
        }
        if (layoutManager instanceof StaggeredGridLayoutManager) {
            return ((StaggeredGridLayoutManager) layoutManager).getSpanCount();
        }
        return -1;
    }

    public void drawHorizontal(Canvas canvas, RecyclerView recyclerView) {
        int childCount = recyclerView.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = recyclerView.getChildAt(i);
            RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) childAt.getLayoutParams();
            int bottom = childAt.getBottom() + layoutParams.bottomMargin;
            this.mDivider.setBounds(childAt.getLeft() - layoutParams.leftMargin, bottom, childAt.getRight() + layoutParams.rightMargin + this.mDivider.getIntrinsicWidth(), this.mDivider.getIntrinsicHeight() + bottom);
            this.mDivider.draw(canvas);
        }
    }

    public void drawVertical(Canvas canvas, RecyclerView recyclerView) {
        int childCount = recyclerView.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = recyclerView.getChildAt(i);
            RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) childAt.getLayoutParams();
            int top = childAt.getTop() - layoutParams.topMargin;
            int bottom = childAt.getBottom() + layoutParams.bottomMargin;
            int right = childAt.getRight() + layoutParams.rightMargin;
            this.mDivider.setBounds(right, top, this.mDivider.getIntrinsicWidth() + right, bottom);
            this.mDivider.draw(canvas);
        }
    }

    private boolean isLastColum(RecyclerView recyclerView, int i, int i2, int i3) {
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            if ((i + 1) % i2 == 0) {
                return true;
            }
            return false;
        } else if (!(layoutManager instanceof StaggeredGridLayoutManager)) {
            return false;
        } else {
            if (((StaggeredGridLayoutManager) layoutManager).getOrientation() == 1) {
                if ((i + 1) % i2 == 0) {
                    return true;
                }
                return false;
            } else if (i >= i3 - (i3 % i2)) {
                return true;
            } else {
                return false;
            }
        }
    }

    private boolean isLastRaw(RecyclerView recyclerView, int i, int i2, int i3) {
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            if (i >= i3 - (i3 % i2)) {
                return true;
            }
            return false;
        } else if (!(layoutManager instanceof StaggeredGridLayoutManager)) {
            return false;
        } else {
            if (((StaggeredGridLayoutManager) layoutManager).getOrientation() == 1) {
                if (i >= i3 - (i3 % i2)) {
                    return true;
                }
                return false;
            } else if ((i + 1) % i2 == 0) {
                return true;
            } else {
                return false;
            }
        }
    }

    public void getItemOffsets(Rect rect, int i, RecyclerView recyclerView) {
        int spanCount = getSpanCount(recyclerView);
        int itemCount = recyclerView.getAdapter().getItemCount();
        if (isLastRaw(recyclerView, i, spanCount, itemCount)) {
            rect.set(0, 0, this.mDivider.getIntrinsicWidth(), 0);
        } else if (isLastColum(recyclerView, i, spanCount, itemCount)) {
            rect.set(0, 0, 0, this.mDivider.getIntrinsicHeight());
        } else {
            rect.set(0, 0, this.mDivider.getIntrinsicWidth(), this.mDivider.getIntrinsicHeight());
        }
    }
}
