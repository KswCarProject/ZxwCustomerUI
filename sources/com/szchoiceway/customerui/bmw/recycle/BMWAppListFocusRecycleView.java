package com.szchoiceway.customerui.bmw.recycle;

import android.content.Context;
import android.util.AttributeSet;
import androidx.recyclerview.widget.RecyclerView;
import com.szchoiceway.customerui.adapter.HorizontalPageLayoutManager;
import com.szchoiceway.zxwlib.focus.view.FocusRecycleView;

public class BMWAppListFocusRecycleView extends FocusRecycleView {
    private int mCueDisplayPage = 0;

    public BMWAppListFocusRecycleView(Context context) {
        super(context);
    }

    public BMWAppListFocusRecycleView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public BMWAppListFocusRecycleView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public int getFirstVisiblePosition() {
        RecyclerView.LayoutManager layoutManager = getLayoutManager();
        if (!(layoutManager instanceof HorizontalPageLayoutManager)) {
            return super.getFirstVisiblePosition();
        }
        HorizontalPageLayoutManager horizontalPageLayoutManager = (HorizontalPageLayoutManager) layoutManager;
        int columns = horizontalPageLayoutManager.getColumns();
        return this.mCueDisplayPage * columns * horizontalPageLayoutManager.getRows();
    }

    public void setmCueDisplayPage(int i) {
        this.mCueDisplayPage = i;
    }
}
