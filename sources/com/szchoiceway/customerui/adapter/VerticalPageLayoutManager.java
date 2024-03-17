package com.szchoiceway.customerui.adapter;

import android.graphics.Rect;
import android.util.SparseArray;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;

public class VerticalPageLayoutManager extends RecyclerView.LayoutManager implements PageDecorationLastJudge {
    private SparseArray<Rect> allItemFrames = new SparseArray<>();
    int columns = 0;
    int itemHeight = 0;
    int itemHeightUsed;
    int itemWidth = 0;
    int itemWidthUsed;
    int offsetX = 0;
    int offsetY = 0;
    int onePageSize = 0;
    int pageSize = 0;
    int rows = 0;
    int totalHeight = 0;
    int totalWidth = 0;

    public boolean canScrollVertically() {
        return true;
    }

    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        return null;
    }

    public VerticalPageLayoutManager(int i, int i2) {
        this.rows = i;
        this.columns = i2;
        this.onePageSize = i * i2;
    }

    public int scrollVerticallyBy(int i, RecyclerView.Recycler recycler, RecyclerView.State state) {
        detachAndScrapAttachedViews(recycler);
        int i2 = this.offsetY;
        int i3 = i2 + i;
        int i4 = this.totalHeight;
        if (i3 > i4) {
            i = i4 - i2;
        } else if (i3 < 0) {
            i = 0 - i2;
        }
        this.offsetY = i2 + i;
        offsetChildrenVertical(-i);
        recycleAndFillItems(recycler, state);
        return i;
    }

    private int getUsableWidth() {
        return (getWidth() - getPaddingLeft()) - getPaddingRight();
    }

    private int getUsableHeight() {
        return (getHeight() - getPaddingTop()) - getPaddingBottom();
    }

    public int getRows() {
        return this.rows;
    }

    public int getColumns() {
        return this.columns;
    }

    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        if (getItemCount() == 0) {
            removeAndRecycleAllViews(recycler);
        } else if (!state.isPreLayout()) {
            this.itemWidth = getUsableWidth() / this.columns;
            int usableHeight = getUsableHeight();
            int i = this.rows;
            int i2 = usableHeight / i;
            this.itemHeight = i2;
            this.itemWidthUsed = (this.columns - 1) * this.itemWidth;
            this.itemHeightUsed = (i - 1) * i2;
            computePageSize(state);
            this.totalHeight = (this.pageSize - 1) * getHeight();
            detachAndScrapAttachedViews(recycler);
            int itemCount = getItemCount();
            int i3 = 0;
            while (i3 < this.pageSize) {
                int i4 = 0;
                while (i4 < this.rows) {
                    int i5 = 0;
                    while (true) {
                        int i6 = this.columns;
                        if (i5 >= i6) {
                            break;
                        }
                        int i7 = (this.onePageSize * i3) + (i6 * i4) + i5;
                        if (i7 == itemCount) {
                            i4 = this.rows;
                            i3 = this.pageSize;
                            break;
                        } else if (i7 < itemCount) {
                            View viewForPosition = recycler.getViewForPosition(i7);
                            addView(viewForPosition);
                            measureChildWithMargins(viewForPosition, this.itemWidthUsed, this.itemHeightUsed);
                            int decoratedMeasuredWidth = getDecoratedMeasuredWidth(viewForPosition);
                            int decoratedMeasuredHeight = getDecoratedMeasuredHeight(viewForPosition);
                            Rect rect = this.allItemFrames.get(i7);
                            if (rect == null) {
                                rect = new Rect();
                            }
                            int usableWidth = (getUsableWidth() * i3) + (this.itemWidth * i5);
                            int i8 = this.itemHeight * i4;
                            rect.set(usableWidth, i8, decoratedMeasuredWidth + usableWidth, decoratedMeasuredHeight + i8);
                            this.allItemFrames.put(i7, rect);
                            i5++;
                        } else {
                            return;
                        }
                    }
                    i4++;
                }
                removeAndRecycleAllViews(recycler);
                i3++;
            }
            recycleAndFillItems(recycler, state);
        }
    }

    private void computePageSize(RecyclerView.State state) {
        this.pageSize = (state.getItemCount() / this.onePageSize) + (state.getItemCount() % this.onePageSize == 0 ? 0 : 1);
    }

    public void onDetachedFromWindow(RecyclerView recyclerView, RecyclerView.Recycler recycler) {
        super.onDetachedFromWindow(recyclerView, recycler);
        this.offsetX = 0;
        this.offsetY = 0;
    }

    private void recycleAndFillItems(RecyclerView.Recycler recycler, RecyclerView.State state) {
        if (!state.isPreLayout()) {
            Rect rect = new Rect(getPaddingLeft(), getPaddingTop() + this.offsetY, (getWidth() - getPaddingLeft()) - getPaddingRight(), ((getHeight() - getPaddingTop()) - getPaddingBottom()) + this.offsetY);
            Rect rect2 = new Rect();
            for (int i = 0; i < getChildCount(); i++) {
                View childAt = getChildAt(i);
                rect2.left = getDecoratedLeft(childAt);
                rect2.top = getDecoratedTop(childAt);
                rect2.right = getDecoratedRight(childAt);
                rect2.bottom = getDecoratedBottom(childAt);
                if (!Rect.intersects(rect, rect2)) {
                    removeAndRecycleView(childAt, recycler);
                }
            }
            for (int i2 = 0; i2 < getItemCount(); i2++) {
                if (Rect.intersects(rect, this.allItemFrames.get(i2))) {
                    View viewForPosition = recycler.getViewForPosition(i2);
                    addView(viewForPosition);
                    measureChildWithMargins(viewForPosition, this.itemWidthUsed, this.itemHeightUsed);
                    Rect rect3 = this.allItemFrames.get(i2);
                    layoutDecorated(viewForPosition, rect3.left, this.offsetY + rect3.top, rect3.right, rect3.bottom + this.offsetY);
                }
            }
        }
    }

    public boolean isLastRow(int i) {
        if (i < 0 || i >= getItemCount()) {
            return false;
        }
        int i2 = this.onePageSize;
        int i3 = (i % i2) + 1;
        if (i3 <= (this.rows - 1) * this.columns || i3 > i2) {
            return false;
        }
        return true;
    }

    public boolean isLastColumn(int i) {
        if (i < 0 || i >= getItemCount() || (i + 1) % this.columns != 0) {
            return false;
        }
        return true;
    }

    public boolean isPageLast(int i) {
        return (i + 1) % this.onePageSize == 0;
    }

    public int computeVerticalScrollRange(RecyclerView.State state) {
        computePageSize(state);
        return this.pageSize * getHeight();
    }

    public int computeVerticalScrollOffset(RecyclerView.State state) {
        return this.offsetY;
    }

    public int computeVerticalScrollExtent(RecyclerView.State state) {
        return getHeight();
    }
}
