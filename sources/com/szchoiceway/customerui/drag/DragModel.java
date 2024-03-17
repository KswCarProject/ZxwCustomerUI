package com.szchoiceway.customerui.drag;

public class DragModel {
    private static DragModel dragModel;
    private DragCallback mCallback;
    DragAppInfo mDragAppInfo = new DragAppInfo();
    boolean mDragComplete = true;
    int mDragState;
    float[] touchXY = new float[2];

    public interface DragCallback {
        void onMove();
    }

    private DragModel() {
    }

    public static DragModel getInstance() {
        if (dragModel == null) {
            synchronized (DragModel.class) {
                if (dragModel == null) {
                    dragModel = new DragModel();
                }
            }
        }
        return dragModel;
    }

    public void setCallback(DragCallback dragCallback) {
        this.mCallback = dragCallback;
    }

    public void setTouchXY(float[] fArr) {
        DragCallback dragCallback;
        this.touchXY = fArr;
        int i = this.mDragState;
        if ((i == 2 || i == 0) && (dragCallback = this.mCallback) != null) {
            dragCallback.onMove();
        }
    }

    public void setDragState(int i) {
        this.mDragState = i;
    }

    public int getDragState() {
        return this.mDragState;
    }

    public void setDragAppInfo(DragAppInfo dragAppInfo) {
        this.mDragAppInfo = dragAppInfo;
    }

    public float[] getTouchXY() {
        return this.touchXY;
    }

    public DragAppInfo getDragAppInfo() {
        return this.mDragAppInfo;
    }

    public void setDragComplete(boolean z) {
        this.mDragComplete = z;
    }

    public boolean isDragComplete() {
        return this.mDragComplete;
    }
}
