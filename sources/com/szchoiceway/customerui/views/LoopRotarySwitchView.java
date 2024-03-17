package com.szchoiceway.customerui.views;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOverlay;
import android.view.animation.DecelerateInterpolator;
import android.widget.RelativeLayout;
import com.szchoiceway.customerui.R;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.ListIterator;

public class LoopRotarySwitchView extends RelativeLayout {
    private static final int LoopR = 200;
    private static final int horizontal = 1;
    private static final int vertical = 0;
    /* access modifiers changed from: private */
    public float angle;
    /* access modifiers changed from: private */
    public AutoScrollDirection autoRotatinDirection;
    private boolean autoRotation;
    private float distance;
    /* access modifiers changed from: private */
    public boolean isCanClickListener;
    /* access modifiers changed from: private */
    public boolean isSlide;
    private float last_angle;
    private float limitX;
    LoopRotarySwitchViewHandler loopHandler;
    /* access modifiers changed from: private */
    public int loopRotationX;
    /* access modifiers changed from: private */
    public int loopRotationZ;
    private Context mContext;
    private GestureDetector mGestureDetector;
    private int mOrientation;
    private float multiple;
    /* access modifiers changed from: private */
    public OnItemClickListener onItemClickListener;
    /* access modifiers changed from: private */
    public OnItemSelectedListener onItemSelectedListener;
    private OnLoopViewTouchListener onLoopViewTouchListener;
    /* access modifiers changed from: private */
    public float r;
    private ValueAnimator rAnimation;
    private ValueAnimator restAnimator;
    /* access modifiers changed from: private */
    public int selectItem;
    /* access modifiers changed from: private */
    public int size;
    /* access modifiers changed from: private */
    public boolean touching;
    /* access modifiers changed from: private */
    public double v;
    /* access modifiers changed from: private */
    public List<View> views;
    private float x;
    private ValueAnimator xAnimation;
    private ValueAnimator zAnimation;

    public enum AutoScrollDirection {
        left,
        right
    }

    public interface OnItemClickListener {
        void onItemClick(int i, View view);
    }

    public interface OnItemSelectedListener {
        void selected(int i, View view);
    }

    public interface OnLoopViewTouchListener {
        void onTouch(MotionEvent motionEvent);
    }

    static /* synthetic */ float access$218(LoopRotarySwitchView loopRotarySwitchView, double d) {
        float f = (float) (((double) loopRotarySwitchView.angle) + d);
        loopRotarySwitchView.angle = f;
        return f;
    }

    public /* bridge */ /* synthetic */ ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return super.generateLayoutParams(attributeSet);
    }

    public /* bridge */ /* synthetic */ ViewOverlay getOverlay() {
        return super.getOverlay();
    }

    public LoopRotarySwitchView(Context context) {
        this(context, (AttributeSet) null);
    }

    public LoopRotarySwitchView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public LoopRotarySwitchView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mOrientation = 1;
        this.restAnimator = null;
        this.rAnimation = null;
        this.zAnimation = null;
        this.xAnimation = null;
        this.loopRotationX = 0;
        this.loopRotationZ = 0;
        this.mGestureDetector = null;
        this.selectItem = 0;
        this.size = 0;
        this.r = 200.0f;
        this.multiple = 2.0f;
        this.distance = 2.0f * 200.0f;
        this.angle = 0.0f;
        this.last_angle = 0.0f;
        this.autoRotation = false;
        this.touching = false;
        this.autoRotatinDirection = AutoScrollDirection.left;
        this.views = new ArrayList();
        this.onItemSelectedListener = null;
        this.onLoopViewTouchListener = null;
        this.onItemClickListener = null;
        this.isCanClickListener = true;
        this.limitX = 30.0f;
        this.loopHandler = new LoopRotarySwitchViewHandler(Math.abs((int) (10.0d - this.v))) {
            /* JADX WARNING: Removed duplicated region for block: B:13:0x003f A[Catch:{ Exception -> 0x0050 }] */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public void doScroll() {
                /*
                    r4 = this;
                    com.szchoiceway.customerui.views.LoopRotarySwitchView r0 = com.szchoiceway.customerui.views.LoopRotarySwitchView.this     // Catch:{ Exception -> 0x0050 }
                    int r0 = r0.size     // Catch:{ Exception -> 0x0050 }
                    if (r0 == 0) goto L_0x0054
                    int[] r0 = com.szchoiceway.customerui.views.LoopRotarySwitchView.AnonymousClass10.$SwitchMap$com$szchoiceway$customerui$views$LoopRotarySwitchView$AutoScrollDirection     // Catch:{ Exception -> 0x0050 }
                    com.szchoiceway.customerui.views.LoopRotarySwitchView r1 = com.szchoiceway.customerui.views.LoopRotarySwitchView.this     // Catch:{ Exception -> 0x0050 }
                    com.szchoiceway.customerui.views.LoopRotarySwitchView$AutoScrollDirection r1 = r1.autoRotatinDirection     // Catch:{ Exception -> 0x0050 }
                    int r1 = r1.ordinal()     // Catch:{ Exception -> 0x0050 }
                    r0 = r0[r1]     // Catch:{ Exception -> 0x0050 }
                    r1 = 1
                    r2 = 0
                    if (r0 == r1) goto L_0x0029
                    r1 = 2
                    if (r0 == r1) goto L_0x001f
                    r0 = r2
                    goto L_0x0033
                L_0x001f:
                    r0 = -360(0xfffffffffffffe98, float:NaN)
                    com.szchoiceway.customerui.views.LoopRotarySwitchView r1 = com.szchoiceway.customerui.views.LoopRotarySwitchView.this     // Catch:{ Exception -> 0x0050 }
                    int r1 = r1.size     // Catch:{ Exception -> 0x0050 }
                    int r0 = r0 / r1
                    goto L_0x0032
                L_0x0029:
                    r0 = 360(0x168, float:5.04E-43)
                    com.szchoiceway.customerui.views.LoopRotarySwitchView r1 = com.szchoiceway.customerui.views.LoopRotarySwitchView.this     // Catch:{ Exception -> 0x0050 }
                    int r1 = r1.size     // Catch:{ Exception -> 0x0050 }
                    int r0 = r0 / r1
                L_0x0032:
                    float r0 = (float) r0     // Catch:{ Exception -> 0x0050 }
                L_0x0033:
                    com.szchoiceway.customerui.views.LoopRotarySwitchView r1 = com.szchoiceway.customerui.views.LoopRotarySwitchView.this     // Catch:{ Exception -> 0x0050 }
                    float r1 = r1.angle     // Catch:{ Exception -> 0x0050 }
                    r3 = 1135869952(0x43b40000, float:360.0)
                    int r1 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
                    if (r1 != 0) goto L_0x0044
                    com.szchoiceway.customerui.views.LoopRotarySwitchView r1 = com.szchoiceway.customerui.views.LoopRotarySwitchView.this     // Catch:{ Exception -> 0x0050 }
                    float unused = r1.angle = r2     // Catch:{ Exception -> 0x0050 }
                L_0x0044:
                    com.szchoiceway.customerui.views.LoopRotarySwitchView r4 = com.szchoiceway.customerui.views.LoopRotarySwitchView.this     // Catch:{ Exception -> 0x0050 }
                    float r1 = r4.angle     // Catch:{ Exception -> 0x0050 }
                    float r1 = r1 + r0
                    r0 = 0
                    r4.AnimRotationTo(r1, r0)     // Catch:{ Exception -> 0x0050 }
                    goto L_0x0054
                L_0x0050:
                    r4 = move-exception
                    r4.printStackTrace()
                L_0x0054:
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.szchoiceway.customerui.views.LoopRotarySwitchView.AnonymousClass1.doScroll():void");
            }
        };
        this.isSlide = true;
        this.mContext = context;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.LoopRotarySwitchView);
        this.mOrientation = obtainStyledAttributes.getInt(2, 1);
        this.autoRotation = obtainStyledAttributes.getBoolean(0, false);
        this.r = obtainStyledAttributes.getDimension(3, 200.0f);
        int i2 = obtainStyledAttributes.getInt(1, 0);
        obtainStyledAttributes.recycle();
        this.mGestureDetector = new GestureDetector(context, getGeomeryController());
        if (this.mOrientation == 1) {
            this.loopRotationZ = 0;
        } else {
            this.loopRotationZ = 90;
        }
        if (i2 == 0) {
            this.autoRotatinDirection = AutoScrollDirection.left;
        } else {
            this.autoRotatinDirection = AutoScrollDirection.right;
        }
        this.loopHandler.setLoop(this.autoRotation);
    }

    /* renamed from: com.szchoiceway.customerui.views.LoopRotarySwitchView$10  reason: invalid class name */
    static /* synthetic */ class AnonymousClass10 {
        static final /* synthetic */ int[] $SwitchMap$com$szchoiceway$customerui$views$LoopRotarySwitchView$AutoScrollDirection;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|6) */
        /* JADX WARNING: Code restructure failed: missing block: B:7:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        static {
            /*
                com.szchoiceway.customerui.views.LoopRotarySwitchView$AutoScrollDirection[] r0 = com.szchoiceway.customerui.views.LoopRotarySwitchView.AutoScrollDirection.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$szchoiceway$customerui$views$LoopRotarySwitchView$AutoScrollDirection = r0
                com.szchoiceway.customerui.views.LoopRotarySwitchView$AutoScrollDirection r1 = com.szchoiceway.customerui.views.LoopRotarySwitchView.AutoScrollDirection.left     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$szchoiceway$customerui$views$LoopRotarySwitchView$AutoScrollDirection     // Catch:{ NoSuchFieldError -> 0x001d }
                com.szchoiceway.customerui.views.LoopRotarySwitchView$AutoScrollDirection r1 = com.szchoiceway.customerui.views.LoopRotarySwitchView.AutoScrollDirection.right     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.szchoiceway.customerui.views.LoopRotarySwitchView.AnonymousClass10.<clinit>():void");
        }
    }

    private <T> void sortList(List<View> list) {
        SortComparator sortComparator = new SortComparator();
        Object[] array = list.toArray(new Object[list.size()]);
        Arrays.sort(array, sortComparator);
        ListIterator<View> listIterator = list.listIterator();
        int i = 0;
        while (listIterator.hasNext()) {
            listIterator.next();
            listIterator.set(array[i]);
            i++;
        }
        for (int i2 = 0; i2 < list.size(); i2++) {
            list.get(i2).bringToFront();
        }
    }

    private class SortComparator implements Comparator<View> {
        private SortComparator() {
        }

        public int compare(View view, View view2) {
            try {
                return (int) ((view.getScaleX() * 1000.0f) - (view2.getScaleX() * 1000.0f));
            } catch (Exception unused) {
                return 0;
            }
        }
    }

    private GestureDetector.SimpleOnGestureListener getGeomeryController() {
        return new GestureDetector.SimpleOnGestureListener() {
            public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
                Log.i("TAG", "LoopRSV-onScroll手勢调用了...");
                Log.i("TAG", "LoopRSV-onScroll-distanceX = " + f + "distanceY = " + f2);
                LoopRotarySwitchView loopRotarySwitchView = LoopRotarySwitchView.this;
                double d = (double) (f / 4.0f);
                double d2 = (double) (f2 / 4.0f);
                double unused = loopRotarySwitchView.v = Math.abs((Math.cos(Math.toRadians((double) loopRotarySwitchView.loopRotationZ)) * d) + (Math.sin(Math.toRadians((double) LoopRotarySwitchView.this.loopRotationZ)) * d2));
                LoopRotarySwitchView loopRotarySwitchView2 = LoopRotarySwitchView.this;
                LoopRotarySwitchView.access$218(loopRotarySwitchView2, (Math.cos(Math.toRadians((double) loopRotarySwitchView2.loopRotationZ)) * d) + (Math.sin(Math.toRadians((double) LoopRotarySwitchView.this.loopRotationZ)) * d2));
                Log.i("TAG", "LoopRSV-onScroll-angle =" + LoopRotarySwitchView.this.angle);
                Log.i("TAG", "LoopRSV-onScroll-v =" + LoopRotarySwitchView.this.v);
                LoopRotarySwitchView.this.initView();
                return true;
            }
        };
    }

    public void initView() {
        for (int i = 0; i < this.views.size(); i++) {
            double d = (double) ((this.angle + 180.0f) - ((float) ((i * 360) / this.size)));
            double d2 = d % 360.0d;
            if (d2 < 0.0d) {
                d2 += 360.0d;
            }
            float sin = ((float) Math.sin(Math.toRadians(d))) * this.r;
            float f = this.r;
            float f2 = this.distance;
            float cos = (f2 - (((float) Math.cos(Math.toRadians(d))) * f)) / (f2 + f);
            this.views.get(i).setScaleX(cos);
            this.views.get(i).setScaleY(cos);
            float sin2 = ((float) Math.sin(Math.toRadians(((double) this.loopRotationX) * Math.cos(Math.toRadians(d))))) * this.r;
            float f3 = (-((float) Math.sin(Math.toRadians((double) (-this.loopRotationZ))))) * sin;
            this.views.get(i).setTranslationX(sin + ((((float) Math.cos(Math.toRadians((double) (-this.loopRotationZ)))) * sin) - sin));
            this.views.get(i).setTranslationY(sin2 + f3);
            if (324.0d < d2 && d2 < 360.0d) {
                this.views.get(i).setAlpha((float) (1.0d - ((360.0d - d2) / ((double) (360 / this.size)))));
            } else if (216.0d < d2 && d2 < 252.0d) {
                this.views.get(i).setAlpha((float) ((252.0d - d2) / ((double) (360 / this.size))));
            } else if (252.0d > d2 || d2 > 324.0d) {
                this.views.get(i).setAlpha(1.0f);
                this.views.get(i).setClickable(true);
            } else {
                this.views.get(i).setAlpha(0.0f);
                this.views.get(i).setClickable(false);
            }
        }
        ArrayList arrayList = new ArrayList();
        arrayList.clear();
        for (int i2 = 0; i2 < this.views.size(); i2++) {
            arrayList.add(this.views.get(i2));
        }
        sortList(arrayList);
        postInvalidate();
    }

    /* access modifiers changed from: protected */
    public void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        initView();
        if (this.autoRotation) {
            LoopRotarySwitchViewHandler loopRotarySwitchViewHandler = this.loopHandler;
            loopRotarySwitchViewHandler.sendEmptyMessageDelayed(1000, loopRotarySwitchViewHandler.loopTime);
        }
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        if (z) {
            checkChildView();
            OnItemSelectedListener onItemSelectedListener2 = this.onItemSelectedListener;
            if (onItemSelectedListener2 != null) {
                this.isCanClickListener = true;
                int i5 = this.selectItem;
                onItemSelectedListener2.selected(i5, this.views.get(i5));
            }
            RAnimation();
        }
    }

    public void RAnimation() {
        RAnimation(1.0f, this.r);
    }

    public void RAnimation(boolean z) {
        if (z) {
            RAnimation(1.0f, 200.0f);
        } else {
            RAnimation(200.0f, 1.0f);
        }
    }

    public void RAnimation(float f, float f2) {
        ValueAnimator ofFloat = ValueAnimator.ofFloat(new float[]{f, f2});
        this.rAnimation = ofFloat;
        ofFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float unused = LoopRotarySwitchView.this.r = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                LoopRotarySwitchView.this.initView();
            }
        });
        Log.i("TAG", "RAnimation-onAnimationUpdate-半径动画...");
        this.rAnimation.setInterpolator(new DecelerateInterpolator());
        this.rAnimation.setDuration(0);
        this.rAnimation.start();
    }

    public void checkChildView() {
        for (int i = 0; i < this.views.size(); i++) {
            this.views.remove(i);
        }
        int childCount = getChildCount();
        this.size = childCount;
        for (final int i2 = 0; i2 < childCount; i2++) {
            View childAt = getChildAt(i2);
            this.views.add(childAt);
            childAt.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (LoopRotarySwitchView.this.isCanClickListener && LoopRotarySwitchView.this.onItemClickListener != null) {
                        LoopRotarySwitchView.this.onItemClickListener.onItemClick(i2, (View) LoopRotarySwitchView.this.views.get(i2));
                    }
                }
            });
        }
    }

    public void restPosition() {
        int i = this.size;
        if (i != 0) {
            float f = (float) (360 / i);
            float f2 = this.angle;
            if (f2 < 0.0f) {
                f = -f;
            }
            float f3 = ((float) ((int) (f2 / f))) * f;
            float f4 = (((float) ((int) (f2 / f))) * f) + f;
            if (f2 < 0.0f ? f2 - this.last_angle < 0.0f : f2 - this.last_angle > 0.0f) {
                f3 = f4;
            }
            AnimRotationTo(f3, (Runnable) null);
        }
    }

    /* access modifiers changed from: private */
    public void AnimRotationTo(float f, final Runnable runnable) {
        float f2 = this.angle;
        if (f2 != f) {
            ValueAnimator ofFloat = ValueAnimator.ofFloat(new float[]{f2, f});
            this.restAnimator = ofFloat;
            ofFloat.setInterpolator(new DecelerateInterpolator());
            this.restAnimator.setDuration(190);
            this.restAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    if (!LoopRotarySwitchView.this.touching) {
                        float unused = LoopRotarySwitchView.this.angle = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                        LoopRotarySwitchView.this.initView();
                    }
                }
            });
            this.restAnimator.addListener(new Animator.AnimatorListener() {
                public void onAnimationCancel(Animator animator) {
                }

                public void onAnimationRepeat(Animator animator) {
                }

                public void onAnimationStart(Animator animator) {
                }

                public void onAnimationEnd(Animator animator) {
                    if (!LoopRotarySwitchView.this.touching) {
                        LoopRotarySwitchView loopRotarySwitchView = LoopRotarySwitchView.this;
                        int unused = loopRotarySwitchView.selectItem = loopRotarySwitchView.calculateItem();
                        if (LoopRotarySwitchView.this.selectItem < 0) {
                            LoopRotarySwitchView loopRotarySwitchView2 = LoopRotarySwitchView.this;
                            int unused2 = loopRotarySwitchView2.selectItem = loopRotarySwitchView2.size + LoopRotarySwitchView.this.selectItem;
                        }
                        if (LoopRotarySwitchView.this.onItemSelectedListener != null && LoopRotarySwitchView.this.isSlide) {
                            LoopRotarySwitchView.this.onItemSelectedListener.selected(LoopRotarySwitchView.this.selectItem, (View) LoopRotarySwitchView.this.views.get(LoopRotarySwitchView.this.selectItem));
                        }
                        boolean unused3 = LoopRotarySwitchView.this.isSlide = true;
                    }
                }
            });
            if (runnable != null) {
                this.restAnimator.addListener(new Animator.AnimatorListener() {
                    public void onAnimationCancel(Animator animator) {
                    }

                    public void onAnimationRepeat(Animator animator) {
                    }

                    public void onAnimationStart(Animator animator) {
                    }

                    public void onAnimationEnd(Animator animator) {
                        runnable.run();
                    }
                });
            }
            this.restAnimator.start();
        }
    }

    /* access modifiers changed from: private */
    public int calculateItem() {
        float f = this.angle;
        int i = this.size;
        return ((int) (f / ((float) (360 / i)))) % i;
    }

    private boolean onTouch(MotionEvent motionEvent) {
        boolean onTouchEvent = this.mGestureDetector.onTouchEvent(motionEvent);
        if (motionEvent.getAction() == 0) {
            this.last_angle = this.angle;
            this.touching = true;
        }
        if (onTouchEvent) {
            getParent().requestDisallowInterceptTouchEvent(true);
            Log.i("TAG", "触摸 旋转屏幕了...");
        }
        if (motionEvent.getAction() != 1 && motionEvent.getAction() != 3) {
            return true;
        }
        this.touching = false;
        restPosition();
        return true;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() == 0) {
            Log.i("TAG", "LoopRotarySwitchView--onTouchEvent--down");
        }
        OnLoopViewTouchListener onLoopViewTouchListener2 = this.onLoopViewTouchListener;
        if (onLoopViewTouchListener2 != null) {
            onLoopViewTouchListener2.onTouch(motionEvent);
        }
        isCanClickListener(motionEvent);
        return true;
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        onTouch(motionEvent);
        OnLoopViewTouchListener onLoopViewTouchListener2 = this.onLoopViewTouchListener;
        if (onLoopViewTouchListener2 != null) {
            onLoopViewTouchListener2.onTouch(motionEvent);
        }
        isCanClickListener(motionEvent);
        return super.dispatchTouchEvent(motionEvent);
    }

    public void isCanClickListener(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (action == 0) {
            this.x = motionEvent.getX();
            if (this.autoRotation) {
                this.loopHandler.removeMessages(1000);
            }
        } else if (action == 1 || action == 3) {
            if (this.autoRotation) {
                LoopRotarySwitchViewHandler loopRotarySwitchViewHandler = this.loopHandler;
                loopRotarySwitchViewHandler.sendEmptyMessageDelayed(1000, loopRotarySwitchViewHandler.loopTime);
            }
            float x2 = motionEvent.getX();
            float f = this.x;
            if (x2 - f > this.limitX || f - motionEvent.getX() > this.limitX) {
                this.isCanClickListener = false;
            } else {
                this.isCanClickListener = true;
            }
        }
    }

    public List<View> getViews() {
        return this.views;
    }

    public float getAngle() {
        return this.angle;
    }

    public void setAngle(float f) {
        this.angle = f;
    }

    public float getDistance() {
        return this.distance;
    }

    public void setDistance(float f) {
        this.distance = f;
    }

    public float getR() {
        return this.r;
    }

    public int getSelectItem() {
        return this.selectItem;
    }

    /* JADX WARNING: Removed duplicated region for block: B:21:0x0063  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x006d  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0073  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x007a  */
    /* JADX WARNING: Removed duplicated region for block: B:30:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setSelectItem(int r4, boolean r5) {
        /*
            r3 = this;
            r3.isSlide = r5
            if (r4 < 0) goto L_0x007e
            int r5 = r3.getSelectItem()
            r0 = 360(0x168, float:5.04E-43)
            if (r5 != 0) goto L_0x0028
            java.util.List<android.view.View> r5 = r3.views
            int r5 = r5.size()
            int r5 = r5 + -1
            if (r4 != r5) goto L_0x001f
            float r4 = r3.angle
            int r5 = r3.size
            int r5 = r0 / r5
        L_0x001c:
            float r5 = (float) r5
            float r4 = r4 - r5
            goto L_0x005a
        L_0x001f:
            float r4 = r3.angle
            int r5 = r3.size
            int r5 = r0 / r5
        L_0x0025:
            float r5 = (float) r5
            float r4 = r4 + r5
            goto L_0x005a
        L_0x0028:
            int r5 = r3.getSelectItem()
            java.util.List<android.view.View> r1 = r3.views
            int r1 = r1.size()
            int r1 = r1 + -1
            if (r5 != r1) goto L_0x0046
            if (r4 != 0) goto L_0x003f
            float r4 = r3.angle
            int r5 = r3.size
            int r5 = r0 / r5
            goto L_0x0025
        L_0x003f:
            float r4 = r3.angle
            int r5 = r3.size
            int r5 = r0 / r5
            goto L_0x001c
        L_0x0046:
            int r5 = r3.getSelectItem()
            if (r4 <= r5) goto L_0x0053
            float r4 = r3.angle
            int r5 = r3.size
            int r5 = r0 / r5
            goto L_0x0025
        L_0x0053:
            float r4 = r3.angle
            int r5 = r3.size
            int r5 = r0 / r5
            goto L_0x001c
        L_0x005a:
            int r5 = r3.size
            int r0 = r0 / r5
            float r0 = (float) r0
            r1 = 0
            int r2 = (r4 > r1 ? 1 : (r4 == r1 ? 0 : -1))
            if (r2 >= 0) goto L_0x0064
            float r0 = -r0
        L_0x0064:
            float r2 = r4 / r0
            int r2 = (int) r2
            float r2 = (float) r2
            float r2 = r2 * r0
            int r0 = (r4 > r1 ? 1 : (r4 == r1 ? 0 : -1))
            if (r0 < 0) goto L_0x0073
            float r0 = r3.last_angle
            float r4 = r4 - r0
            int r4 = (r4 > r1 ? 1 : (r4 == r1 ? 0 : -1))
            goto L_0x0078
        L_0x0073:
            float r0 = r3.last_angle
            float r4 = r4 - r0
            int r4 = (r4 > r1 ? 1 : (r4 == r1 ? 0 : -1))
        L_0x0078:
            if (r5 <= 0) goto L_0x007e
            r4 = 0
            r3.AnimRotationTo(r2, r4)
        L_0x007e:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.szchoiceway.customerui.views.LoopRotarySwitchView.setSelectItem(int, boolean):void");
    }

    public LoopRotarySwitchView setR(float f) {
        this.r = f;
        this.distance = this.multiple * f;
        return this;
    }

    public void setOnItemSelectedListener(OnItemSelectedListener onItemSelectedListener2) {
        this.onItemSelectedListener = onItemSelectedListener2;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener2) {
        this.onItemClickListener = onItemClickListener2;
    }

    public OnItemClickListener getOnItemClickListener() {
        OnItemClickListener onItemClickListener2 = this.onItemClickListener;
        if (onItemClickListener2 != null) {
            return onItemClickListener2;
        }
        return null;
    }

    public void setOnLoopViewTouchListener(OnLoopViewTouchListener onLoopViewTouchListener2) {
        this.onLoopViewTouchListener = onLoopViewTouchListener2;
    }

    public LoopRotarySwitchView setAutoRotation(boolean z) {
        this.autoRotation = z;
        this.loopHandler.setLoop(z);
        return this;
    }

    public long getAutoRotationTime() {
        return this.loopHandler.loopTime;
    }

    public LoopRotarySwitchView setAutoRotationTime(long j) {
        this.loopHandler.setLoopTime(j);
        return this;
    }

    public boolean isAutoRotation() {
        return this.autoRotation;
    }

    public LoopRotarySwitchView setMultiple(float f) {
        this.multiple = f;
        return this;
    }

    public LoopRotarySwitchView setAutoScrollDirection(AutoScrollDirection autoScrollDirection) {
        this.autoRotatinDirection = autoScrollDirection;
        return this;
    }

    public void createXAnimation(int i, int i2, boolean z) {
        ValueAnimator valueAnimator = this.xAnimation;
        if (valueAnimator != null && valueAnimator.isRunning()) {
            this.xAnimation.cancel();
        }
        ValueAnimator ofInt = ValueAnimator.ofInt(new int[]{i, i2});
        this.xAnimation = ofInt;
        ofInt.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int unused = LoopRotarySwitchView.this.loopRotationX = ((Integer) valueAnimator.getAnimatedValue()).intValue();
                LoopRotarySwitchView.this.initView();
            }
        });
        this.xAnimation.setInterpolator(new DecelerateInterpolator());
        this.xAnimation.setDuration(2000);
        if (z) {
            this.xAnimation.start();
        }
    }

    public ValueAnimator createZAnimation(int i, int i2, boolean z) {
        ValueAnimator valueAnimator = this.zAnimation;
        if (valueAnimator != null && valueAnimator.isRunning()) {
            this.zAnimation.cancel();
        }
        ValueAnimator ofInt = ValueAnimator.ofInt(new int[]{i, i2});
        this.zAnimation = ofInt;
        ofInt.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int unused = LoopRotarySwitchView.this.loopRotationZ = ((Integer) valueAnimator.getAnimatedValue()).intValue();
                LoopRotarySwitchView.this.initView();
            }
        });
        this.zAnimation.setInterpolator(new DecelerateInterpolator());
        this.zAnimation.setDuration(2000);
        if (z) {
            this.zAnimation.start();
        }
        return this.zAnimation;
    }

    public LoopRotarySwitchView setOrientation(int i) {
        boolean z = true;
        if (i != 1) {
            z = false;
        }
        setHorizontal(z, false);
        return this;
    }

    public LoopRotarySwitchView setHorizontal(boolean z, boolean z2) {
        if (!z2) {
            if (z) {
                setLoopRotationZ(0);
            } else {
                setLoopRotationZ(90);
            }
            initView();
        } else if (z) {
            createZAnimation(getLoopRotationZ(), 0, true);
        } else {
            createZAnimation(getLoopRotationZ(), 90, true);
        }
        return this;
    }

    public LoopRotarySwitchView setLoopRotationX(int i) {
        this.loopRotationX = i;
        return this;
    }

    public LoopRotarySwitchView setLoopRotationZ(int i) {
        this.loopRotationZ = i;
        return this;
    }

    public int getLoopRotationX() {
        return this.loopRotationX;
    }

    public int getLoopRotationZ() {
        return this.loopRotationZ;
    }

    public ValueAnimator getRestAnimator() {
        return this.restAnimator;
    }

    public ValueAnimator getrAnimation() {
        return this.rAnimation;
    }

    public void setzAnimation(ValueAnimator valueAnimator) {
        this.zAnimation = valueAnimator;
    }

    public ValueAnimator getzAnimation() {
        return this.zAnimation;
    }

    public void setxAnimation(ValueAnimator valueAnimator) {
        this.xAnimation = valueAnimator;
    }

    public ValueAnimator getxAnimation() {
        return this.xAnimation;
    }
}
