package com.szchoiceway.customerui.adapter;

import android.animation.ValueAnimator;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class PagingScrollHelper {
    public static final String TAG = "PagingScrollHelper";
    /* access modifiers changed from: private */
    public double INTERVAL = 6.0d;
    /* access modifiers changed from: private */
    public long TIME = 5;
    int accelerated = 50;
    int endPoint = 0;
    /* access modifiers changed from: private */
    public float endX = 0.0f;
    /* access modifiers changed from: private */
    public float endY = 0.0f;
    private int firstItemPosition = -2;
    private int indexPage;
    int intiSpeed = 10;
    int intiSpeed2 = -10;
    private boolean isAdd;
    private int lastItemPosition = -1;
    private ValueAnimator mAnimator = null;
    private float mDensity;
    /* access modifiers changed from: private */
    public int mDx = 0;
    /* access modifiers changed from: private */
    public int mDy = 0;
    private Handler mHandler = new Handler() {
        public void handleMessage(Message message) {
            super.handleMessage(message);
            if (message.what == 1) {
                removeMessages(1);
                if (PagingScrollHelper.this.pointList.size() > 0) {
                    int size = PagingScrollHelper.this.pointList.size();
                    Point point = (Point) PagingScrollHelper.this.pointList.get(0);
                    Point point2 = (Point) PagingScrollHelper.this.pointList.get(size - 1);
                    float unused = PagingScrollHelper.this.startX = point.x;
                    float unused2 = PagingScrollHelper.this.startY = point.y;
                    float unused3 = PagingScrollHelper.this.endX = point2.x;
                    float unused4 = PagingScrollHelper.this.endY = point2.y;
                    if (PagingScrollHelper.this.endX != PagingScrollHelper.this.startX) {
                        PagingScrollHelper.this.pointList.clear();
                        PagingScrollHelper.this.fl();
                    }
                }
            }
        }
    };
    /* access modifiers changed from: private */
    public MyOnFlingListener mOnFlingListener = new MyOnFlingListener();
    private onPageChangeListener mOnPageChangeListener;
    private MyOnScrollListener mOnScrollListener = new MyOnScrollListener();
    private MyOnTouchListener mOnTouchListener = new MyOnTouchListener();
    /* access modifiers changed from: private */
    public ORIENTATION mOrientation = ORIENTATION.HORIZONTAL;
    private int mPage = 0;
    /* access modifiers changed from: private */
    public RecyclerView mRecyclerView = null;
    private int offsetX = 0;
    private int offsetY = 0;
    private int pageNum = -1;
    /* access modifiers changed from: private */
    public List<Point> pointList = new ArrayList();
    /* access modifiers changed from: private */
    public boolean post = false;
    int startPoint = 0;
    /* access modifiers changed from: private */
    public float startX = 0.0f;
    /* access modifiers changed from: private */
    public float startY = 0.0f;
    /* access modifiers changed from: private */
    public float startxp;
    /* access modifiers changed from: private */
    public Handler tickHandler = new Handler();
    /* access modifiers changed from: private */
    public final Runnable tickRunnable = new Runnable() {
        public void run() {
            if (PagingScrollHelper.this.endPoint == PagingScrollHelper.this.startPoint || !PagingScrollHelper.this.post) {
                PagingScrollHelper.this.stop();
                return;
            }
            if (PagingScrollHelper.this.startPoint < PagingScrollHelper.this.endPoint) {
                float access$300 = ((float) PagingScrollHelper.this.endPoint) - PagingScrollHelper.this.startxp;
                PagingScrollHelper pagingScrollHelper = PagingScrollHelper.this;
                int unused = pagingScrollHelper.x = (int) Math.ceil(((double) access$300) / pagingScrollHelper.INTERVAL);
                if (PagingScrollHelper.this.intiSpeed < PagingScrollHelper.this.x) {
                    PagingScrollHelper pagingScrollHelper2 = PagingScrollHelper.this;
                    int unused2 = pagingScrollHelper2.x = pagingScrollHelper2.intiSpeed;
                    PagingScrollHelper.this.intiSpeed += PagingScrollHelper.this.accelerated;
                }
            } else {
                float access$3002 = ((float) PagingScrollHelper.this.endPoint) - PagingScrollHelper.this.startxp;
                PagingScrollHelper pagingScrollHelper3 = PagingScrollHelper.this;
                int unused3 = pagingScrollHelper3.x = (int) Math.floor(((double) access$3002) / pagingScrollHelper3.INTERVAL);
                if (PagingScrollHelper.this.intiSpeed2 > PagingScrollHelper.this.x) {
                    PagingScrollHelper pagingScrollHelper4 = PagingScrollHelper.this;
                    int unused4 = pagingScrollHelper4.x = pagingScrollHelper4.intiSpeed2;
                    PagingScrollHelper.this.intiSpeed2 -= PagingScrollHelper.this.accelerated;
                }
            }
            PagingScrollHelper pagingScrollHelper5 = PagingScrollHelper.this;
            PagingScrollHelper.access$316(pagingScrollHelper5, (float) pagingScrollHelper5.x);
            if (PagingScrollHelper.this.x == 0) {
                PagingScrollHelper.this.stop();
                PagingScrollHelper.this.mOnFlingListener.onFling(0, 0);
            }
            PagingScrollHelper.this.mRecyclerView.scrollBy(PagingScrollHelper.this.x, 0);
            PagingScrollHelper.this.tickHandler.postDelayed(PagingScrollHelper.this.tickRunnable, PagingScrollHelper.this.TIME);
        }
    };
    private int totalNum;
    /* access modifiers changed from: private */
    public boolean touchEnable = true;
    /* access modifiers changed from: private */
    public int x = 0;

    private enum ORIENTATION {
        HORIZONTAL,
        VERTICAL,
        NULL
    }

    public interface onPageChangeListener {
        void onPageChange(int i);

        void onPageState(int i);
    }

    public void setDuration(int i) {
    }

    static /* synthetic */ int access$1312(PagingScrollHelper pagingScrollHelper, int i) {
        int i2 = pagingScrollHelper.offsetX + i;
        pagingScrollHelper.offsetX = i2;
        return i2;
    }

    static /* synthetic */ int access$1412(PagingScrollHelper pagingScrollHelper, int i) {
        int i2 = pagingScrollHelper.offsetY + i;
        pagingScrollHelper.offsetY = i2;
        return i2;
    }

    static /* synthetic */ float access$316(PagingScrollHelper pagingScrollHelper, float f) {
        float f2 = pagingScrollHelper.startxp + f;
        pagingScrollHelper.startxp = f2;
        return f2;
    }

    public void addPoint(Point point) {
        List<Point> list = this.pointList;
        if (list != null) {
            list.clear();
            this.pointList.add(point);
        }
    }

    public void setUpRecycleView(RecyclerView recyclerView) {
        if (recyclerView != null) {
            this.mRecyclerView = recyclerView;
            recyclerView.setOnFlingListener(this.mOnFlingListener);
            recyclerView.setOnScrollListener(this.mOnScrollListener);
            recyclerView.setOnTouchListener(this.mOnTouchListener);
            updateLayoutManger();
            this.mDensity = recyclerView.getContext().getResources().getDisplayMetrics().density;
            return;
        }
        throw new IllegalArgumentException("recycleView must be not null");
    }

    public void updateLayoutManger() {
        RecyclerView.LayoutManager layoutManager = this.mRecyclerView.getLayoutManager();
        if (layoutManager != null) {
            if (layoutManager.canScrollVertically()) {
                this.mOrientation = ORIENTATION.VERTICAL;
            } else if (layoutManager.canScrollHorizontally()) {
                this.mOrientation = ORIENTATION.HORIZONTAL;
            } else {
                this.mOrientation = ORIENTATION.NULL;
            }
            ValueAnimator valueAnimator = this.mAnimator;
            if (valueAnimator != null) {
                valueAnimator.cancel();
            }
            this.offsetX = 0;
            this.offsetY = 0;
        }
    }

    public int getPageCount() {
        if (this.mRecyclerView == null || this.mOrientation == ORIENTATION.NULL) {
            return 0;
        }
        if (this.mOrientation == ORIENTATION.VERTICAL && this.mRecyclerView.computeVerticalScrollExtent() != 0) {
            return this.mRecyclerView.computeVerticalScrollRange() / this.mRecyclerView.computeVerticalScrollExtent();
        }
        if (this.mRecyclerView.computeHorizontalScrollExtent() != 0) {
            return this.mRecyclerView.computeHorizontalScrollRange() / this.mRecyclerView.computeHorizontalScrollExtent();
        }
        return 0;
    }

    public void scrollToPosition(int i) {
        int i2;
        if (i < 0) {
            i = 0;
        }
        if (i >= getPageCount()) {
            i = getPageCount() - 1;
        }
        int i3 = this.mOrientation == ORIENTATION.VERTICAL ? this.offsetY : this.offsetX;
        if (this.mOrientation == ORIENTATION.VERTICAL) {
            i2 = this.mRecyclerView.getHeight();
        } else {
            i2 = this.mRecyclerView.getWidth();
        }
        int i4 = i2 * i;
        if (i3 != i4) {
            setPoint(i3, i4);
            start();
        }
    }

    public void setPoint(int i, int i2) {
        this.startPoint = i;
        this.endPoint = i2;
        this.startxp = (float) i;
    }

    /* access modifiers changed from: package-private */
    public void start() {
        this.post = true;
        this.accelerated = (int) Math.ceil((double) (((float) (this.mRecyclerView.getWidth() / 16)) / this.mDensity));
        float f = this.startX;
        float f2 = this.endX;
        int i = (int) (f - f2);
        this.intiSpeed = i;
        if (i == 0 || i < 10) {
            this.intiSpeed = 10;
        }
        int i2 = (int) (f - f2);
        this.intiSpeed2 = i2;
        if (i2 == 0 || i2 > -10) {
            this.intiSpeed2 = -10;
        }
        onPageChangeListener onpagechangelistener = this.mOnPageChangeListener;
        if (onpagechangelistener != null) {
            onpagechangelistener.onPageState(1);
        }
        this.tickHandler.removeCallbacks(this.tickRunnable);
        this.tickHandler.postDelayed(this.tickRunnable, this.TIME);
    }

    /* access modifiers changed from: package-private */
    public void stop() {
        this.post = false;
        this.x = 0;
        this.mDx = 0;
        this.mDy = 0;
        onPageChangeListener onpagechangelistener = this.mOnPageChangeListener;
        if (onpagechangelistener != null) {
            onpagechangelistener.onPageState(0);
            this.mOnPageChangeListener.onPageChange(getPageIndex());
        }
        this.tickHandler.removeCallbacks(this.tickRunnable);
    }

    public void setINTERVAL(double d) {
        this.INTERVAL = d;
    }

    /* access modifiers changed from: private */
    public void touch() {
        float f = this.endX;
        float f2 = this.endY;
        long uptimeMillis = SystemClock.uptimeMillis();
        long j = uptimeMillis;
        long j2 = uptimeMillis;
        float f3 = f;
        float f4 = f2;
        MotionEvent obtain = MotionEvent.obtain(j, j2, 0, f3, f4, 0);
        MotionEvent obtain2 = MotionEvent.obtain(j, j2, 1, f3, f4, 0);
        this.mRecyclerView.onTouchEvent(obtain);
        this.mRecyclerView.onTouchEvent(obtain2);
        obtain.recycle();
        obtain2.recycle();
    }

    private class MyOnFlingListener extends RecyclerView.OnFlingListener {
        private MyOnFlingListener() {
        }

        public boolean onFling(int i, int i2) {
            if (PagingScrollHelper.this.mOrientation != ORIENTATION.NULL && (i != 0 || i2 != 0)) {
                return true;
            }
            PagingScrollHelper.this.touch();
            return false;
        }
    }

    /* access modifiers changed from: private */
    public void fl() {
        int i;
        int i2;
        int pageCount = getPageCount();
        float f = this.startX - this.endX;
        int i3 = (f > 0.0f ? 1 : (f == 0.0f ? 0 : -1));
        int i4 = 0;
        if (i3 == 0) {
            this.mRecyclerView.scrollBy(-this.mDx, 0);
            return;
        }
        int startPageIndex = getStartPageIndex();
        if (this.mOrientation == ORIENTATION.VERTICAL) {
            i = this.offsetY;
            if (f == 2.14748365E9f) {
                startPageIndex += this.indexPage;
            } else if (f < 0.0f) {
                startPageIndex--;
            } else if (i3 > 0) {
                startPageIndex++;
            } else {
                int i5 = this.pageNum;
                if (i5 != -1) {
                    startPageIndex = i5 - 1;
                    i = 0;
                }
            }
            if (startPageIndex < 0) {
                startPageIndex = 0;
            }
            if (startPageIndex >= pageCount) {
                startPageIndex = pageCount - 1;
            }
            i2 = this.mRecyclerView.getHeight();
        } else {
            int i6 = this.offsetX;
            if (f == 2.14748365E9f) {
                startPageIndex += this.indexPage;
            } else if (f < 0.0f) {
                startPageIndex--;
                this.isAdd = false;
            } else if (i3 > 0) {
                startPageIndex++;
                this.isAdd = true;
            } else {
                int i7 = this.pageNum;
                if (i7 != -1) {
                    startPageIndex = i7 - 1;
                    i6 = 0;
                }
            }
            if (startPageIndex < 0) {
                startPageIndex = 0;
            }
            if (startPageIndex >= pageCount) {
                startPageIndex = pageCount - 1;
            }
            i2 = this.mRecyclerView.getWidth();
        }
        int i8 = i2 * startPageIndex;
        if (i8 >= 0) {
            i4 = i8;
        }
        setPage(startPageIndex);
        setPoint(i, i4);
        start();
    }

    private class MyOnScrollListener extends RecyclerView.OnScrollListener {
        public void onScrollStateChanged(RecyclerView recyclerView, int i) {
        }

        private MyOnScrollListener() {
        }

        public void onScrolled(RecyclerView recyclerView, int i, int i2) {
            PagingScrollHelper.access$1312(PagingScrollHelper.this, i);
            PagingScrollHelper.access$1412(PagingScrollHelper.this, i2);
            int unused = PagingScrollHelper.this.mDx = i;
            int unused2 = PagingScrollHelper.this.mDy = i2;
        }
    }

    private class MyOnTouchListener implements View.OnTouchListener {
        private MyOnTouchListener() {
        }

        /* JADX WARNING: Removed duplicated region for block: B:39:0x011f  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean onTouch(android.view.View r6, android.view.MotionEvent r7) {
            /*
                r5 = this;
                com.szchoiceway.customerui.adapter.PagingScrollHelper r6 = com.szchoiceway.customerui.adapter.PagingScrollHelper.this
                boolean r6 = r6.touchEnable
                r0 = 0
                r1 = 0
                if (r6 != 0) goto L_0x0028
                com.szchoiceway.customerui.adapter.PagingScrollHelper r6 = com.szchoiceway.customerui.adapter.PagingScrollHelper.this
                float unused = r6.startX = r1
                com.szchoiceway.customerui.adapter.PagingScrollHelper r6 = com.szchoiceway.customerui.adapter.PagingScrollHelper.this
                float unused = r6.startY = r1
                com.szchoiceway.customerui.adapter.PagingScrollHelper r6 = com.szchoiceway.customerui.adapter.PagingScrollHelper.this
                float unused = r6.endX = r1
                com.szchoiceway.customerui.adapter.PagingScrollHelper r6 = com.szchoiceway.customerui.adapter.PagingScrollHelper.this
                float unused = r6.endY = r1
                com.szchoiceway.customerui.adapter.PagingScrollHelper r5 = com.szchoiceway.customerui.adapter.PagingScrollHelper.this
                java.util.List r5 = r5.pointList
                r5.clear()
                return r0
            L_0x0028:
                float r6 = r7.getX()
                float r2 = r7.getY()
                int r3 = (r6 > r1 ? 1 : (r6 == r1 ? 0 : -1))
                if (r3 != 0) goto L_0x0039
                int r3 = (r2 > r1 ? 1 : (r2 == r1 ? 0 : -1))
                if (r3 != 0) goto L_0x0039
                return r0
            L_0x0039:
                int r7 = r7.getAction()
                r3 = 1
                if (r7 == 0) goto L_0x0105
                if (r7 == r3) goto L_0x0057
                r1 = 2
                if (r7 == r1) goto L_0x0047
                goto L_0x011c
            L_0x0047:
                com.szchoiceway.customerui.adapter.PagingScrollHelper r7 = com.szchoiceway.customerui.adapter.PagingScrollHelper.this
                java.util.List r7 = r7.pointList
                com.szchoiceway.customerui.adapter.PagingScrollHelper$Point r1 = new com.szchoiceway.customerui.adapter.PagingScrollHelper$Point
                r1.<init>(r6, r2)
                r7.add(r1)
                goto L_0x011c
            L_0x0057:
                com.szchoiceway.customerui.adapter.PagingScrollHelper r6 = com.szchoiceway.customerui.adapter.PagingScrollHelper.this
                java.util.List r6 = r6.pointList
                int r6 = r6.size()
                if (r6 <= 0) goto L_0x011d
                com.szchoiceway.customerui.adapter.PagingScrollHelper r7 = com.szchoiceway.customerui.adapter.PagingScrollHelper.this
                java.util.List r7 = r7.pointList
                int r2 = r6 / 2
                if (r2 >= 0) goto L_0x006e
                r2 = r0
            L_0x006e:
                java.lang.Object r7 = r7.get(r2)
                com.szchoiceway.customerui.adapter.PagingScrollHelper$Point r7 = (com.szchoiceway.customerui.adapter.PagingScrollHelper.Point) r7
                com.szchoiceway.customerui.adapter.PagingScrollHelper r2 = com.szchoiceway.customerui.adapter.PagingScrollHelper.this
                java.util.List r2 = r2.pointList
                int r6 = r6 - r3
                java.lang.Object r6 = r2.get(r6)
                com.szchoiceway.customerui.adapter.PagingScrollHelper$Point r6 = (com.szchoiceway.customerui.adapter.PagingScrollHelper.Point) r6
                com.szchoiceway.customerui.adapter.PagingScrollHelper r2 = com.szchoiceway.customerui.adapter.PagingScrollHelper.this
                float r4 = r7.x
                float unused = r2.startX = r4
                com.szchoiceway.customerui.adapter.PagingScrollHelper r2 = com.szchoiceway.customerui.adapter.PagingScrollHelper.this
                float r7 = r7.y
                float unused = r2.startY = r7
                com.szchoiceway.customerui.adapter.PagingScrollHelper r7 = com.szchoiceway.customerui.adapter.PagingScrollHelper.this
                float r2 = r6.x
                float unused = r7.endX = r2
                com.szchoiceway.customerui.adapter.PagingScrollHelper r7 = com.szchoiceway.customerui.adapter.PagingScrollHelper.this
                float r6 = r6.y
                float unused = r7.endY = r6
                com.szchoiceway.customerui.adapter.PagingScrollHelper r6 = com.szchoiceway.customerui.adapter.PagingScrollHelper.this
                float r6 = r6.startX
                com.szchoiceway.customerui.adapter.PagingScrollHelper r7 = com.szchoiceway.customerui.adapter.PagingScrollHelper.this
                float r7 = r7.endX
                float r6 = r6 - r7
                int r7 = (r6 > r1 ? 1 : (r6 == r1 ? 0 : -1))
                if (r7 >= 0) goto L_0x00b6
                com.szchoiceway.customerui.adapter.PagingScrollHelper r7 = com.szchoiceway.customerui.adapter.PagingScrollHelper.this
                int r7 = r7.mDx
                if (r7 > 0) goto L_0x00c4
            L_0x00b6:
                int r6 = (r6 > r1 ? 1 : (r6 == r1 ? 0 : -1))
                if (r6 <= 0) goto L_0x00c2
                com.szchoiceway.customerui.adapter.PagingScrollHelper r7 = com.szchoiceway.customerui.adapter.PagingScrollHelper.this
                int r7 = r7.mDx
                if (r7 < 0) goto L_0x00c4
            L_0x00c2:
                if (r6 != 0) goto L_0x00de
            L_0x00c4:
                com.szchoiceway.customerui.adapter.PagingScrollHelper r6 = com.szchoiceway.customerui.adapter.PagingScrollHelper.this
                java.util.List r6 = r6.pointList
                java.lang.Object r6 = r6.get(r0)
                com.szchoiceway.customerui.adapter.PagingScrollHelper$Point r6 = (com.szchoiceway.customerui.adapter.PagingScrollHelper.Point) r6
                com.szchoiceway.customerui.adapter.PagingScrollHelper r7 = com.szchoiceway.customerui.adapter.PagingScrollHelper.this
                float r1 = r6.x
                float unused = r7.startX = r1
                com.szchoiceway.customerui.adapter.PagingScrollHelper r7 = com.szchoiceway.customerui.adapter.PagingScrollHelper.this
                float r6 = r6.y
                float unused = r7.startY = r6
            L_0x00de:
                com.szchoiceway.customerui.adapter.PagingScrollHelper r6 = com.szchoiceway.customerui.adapter.PagingScrollHelper.this
                float r6 = r6.endX
                com.szchoiceway.customerui.adapter.PagingScrollHelper r7 = com.szchoiceway.customerui.adapter.PagingScrollHelper.this
                float r7 = r7.startX
                int r6 = (r6 > r7 ? 1 : (r6 == r7 ? 0 : -1))
                if (r6 != 0) goto L_0x00f6
                com.szchoiceway.customerui.adapter.PagingScrollHelper r6 = com.szchoiceway.customerui.adapter.PagingScrollHelper.this
                int r6 = r6.mDx
                if (r6 == 0) goto L_0x00fb
            L_0x00f6:
                com.szchoiceway.customerui.adapter.PagingScrollHelper r6 = com.szchoiceway.customerui.adapter.PagingScrollHelper.this
                r6.fl()
            L_0x00fb:
                com.szchoiceway.customerui.adapter.PagingScrollHelper r6 = com.szchoiceway.customerui.adapter.PagingScrollHelper.this
                java.util.List r6 = r6.pointList
                r6.clear()
                goto L_0x011d
            L_0x0105:
                com.szchoiceway.customerui.adapter.PagingScrollHelper r7 = com.szchoiceway.customerui.adapter.PagingScrollHelper.this
                java.util.List r7 = r7.pointList
                r7.clear()
                com.szchoiceway.customerui.adapter.PagingScrollHelper r7 = com.szchoiceway.customerui.adapter.PagingScrollHelper.this
                java.util.List r7 = r7.pointList
                com.szchoiceway.customerui.adapter.PagingScrollHelper$Point r1 = new com.szchoiceway.customerui.adapter.PagingScrollHelper$Point
                r1.<init>(r6, r2)
                r7.add(r1)
            L_0x011c:
                r3 = r0
            L_0x011d:
                if (r3 == 0) goto L_0x0129
                com.szchoiceway.customerui.adapter.PagingScrollHelper r6 = com.szchoiceway.customerui.adapter.PagingScrollHelper.this
                float unused = r6.startX
                com.szchoiceway.customerui.adapter.PagingScrollHelper r5 = com.szchoiceway.customerui.adapter.PagingScrollHelper.this
                float unused = r5.endX
            L_0x0129:
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.szchoiceway.customerui.adapter.PagingScrollHelper.MyOnTouchListener.onTouch(android.view.View, android.view.MotionEvent):boolean");
        }
    }

    public int getPage() {
        return this.mPage;
    }

    public void setPage(int i) {
        this.mPage = i;
    }

    private int getPageIndex() {
        if (this.mOrientation == ORIENTATION.VERTICAL) {
            return this.offsetY / this.mRecyclerView.getHeight();
        }
        return this.offsetX / this.mRecyclerView.getWidth();
    }

    private int getStartPageIndex() {
        if (this.mOrientation == ORIENTATION.VERTICAL) {
            return this.endPoint / this.mRecyclerView.getHeight();
        }
        return this.endPoint / this.mRecyclerView.getWidth();
    }

    public void setOnPageChangeListener(onPageChangeListener onpagechangelistener) {
        this.mOnPageChangeListener = onpagechangelistener;
    }

    public void setPageNum(int i) {
        this.mRecyclerView.scrollToPosition(0);
        updateLayoutManger();
        this.pageNum = i;
        this.mOnFlingListener.onFling(0, 0);
    }

    public void setIndexPage(int i) {
        this.indexPage = i;
        this.mOnFlingListener.onFling(Integer.MAX_VALUE, Integer.MAX_VALUE);
    }

    public static class Point {
        float x;
        float y;

        public Point(float f, float f2) {
            this.x = f;
            this.y = f2;
        }
    }

    public void setTime(long j) {
        this.TIME = j;
    }

    public void setTouchEnable(boolean z) {
        Log.d(TAG, "setTouchEnable touchEnable = " + z);
        this.touchEnable = z;
    }
}
