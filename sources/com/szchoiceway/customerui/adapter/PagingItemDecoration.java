package com.szchoiceway.customerui.adapter;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;

public class PagingItemDecoration extends RecyclerView.ItemDecoration {
    private static final int[] ATTRS = {16843284};
    private Drawable mDivider;
    PageDecorationLastJudge mPageDecorationLastJudge;

    public PagingItemDecoration(Context context, PageDecorationLastJudge pageDecorationLastJudge) {
        if (pageDecorationLastJudge != null) {
            this.mPageDecorationLastJudge = pageDecorationLastJudge;
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(ATTRS);
            this.mDivider = obtainStyledAttributes.getDrawable(0);
            obtainStyledAttributes.recycle();
            return;
        }
        throw new IllegalArgumentException("pageDecorationLastJudge must be no null");
    }

    public void onDraw(Canvas canvas, RecyclerView recyclerView, RecyclerView.State state) {
        drawHorizontal(canvas, recyclerView);
        drawVertical(canvas, recyclerView);
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

    public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, RecyclerView.State state) {
        int childAdapterPosition = recyclerView.getChildAdapterPosition(view);
        if (this.mPageDecorationLastJudge.isPageLast(childAdapterPosition)) {
            rect.set(0, 0, 0, 0);
        } else if (this.mPageDecorationLastJudge.isLastRow(childAdapterPosition)) {
            rect.set(0, 0, this.mDivider.getIntrinsicWidth(), 0);
        } else if (this.mPageDecorationLastJudge.isLastColumn(childAdapterPosition)) {
            rect.set(0, 0, 0, this.mDivider.getIntrinsicHeight());
        } else {
            rect.set(0, 0, this.mDivider.getIntrinsicWidth(), this.mDivider.getIntrinsicHeight());
        }
    }
}
