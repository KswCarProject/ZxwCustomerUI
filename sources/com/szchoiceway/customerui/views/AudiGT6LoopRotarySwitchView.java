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
import com.szchoiceway.customerui.fragment.launcher.audi.gt6.view.AudiGt6OriginalCarLauncherIconView;
import com.szchoiceway.customerui.kt_like.JavaPair;
import com.szchoiceway.customerui.kt_like.JavaStandard;
import com.szchoiceway.customerui.kt_like.JavaTriple;
import com.szchoiceway.customerui.kt_like.ListUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import kotlin.jvm.functions.Function1;

public class AudiGT6LoopRotarySwitchView extends RelativeLayout {
    private static final float INITIALIZATION_ANGLE = 30.0f;
    private static final int LoopR = 200;
    private static final int horizontal = 1;
    private static final int vertical = 0;
    /* access modifiers changed from: private */
    public float angle;
    /* access modifiers changed from: private */
    public AutoScrollDirection autoRotatinDirection;
    private boolean autoRotation;
    private float distance;
    private int invisibilityAngle;
    private int invisibilityEndAngle;
    private int invisibilityStartAngle;
    /* access modifiers changed from: private */
    public boolean isCanClickListener;
    /* access modifiers changed from: private */
    public boolean isSlide;
    private float last_angle;
    private float limitX;
    LoopRotarySwitchViewHandler loopHandler;
    /* access modifiers changed from: private */
    public float loopRotationX;
    /* access modifiers changed from: private */
    public float loopRotationZ;
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
    private float xOffset;
    private float yOffset;
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

    static /* synthetic */ float access$318(AudiGT6LoopRotarySwitchView audiGT6LoopRotarySwitchView, double d) {
        float f = (float) (((double) audiGT6LoopRotarySwitchView.angle) + d);
        audiGT6LoopRotarySwitchView.angle = f;
        return f;
    }

    public /* bridge */ /* synthetic */ ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return super.generateLayoutParams(attributeSet);
    }

    public /* bridge */ /* synthetic */ ViewOverlay getOverlay() {
        return super.getOverlay();
    }

    public AudiGT6LoopRotarySwitchView(Context context) {
        this(context, (AttributeSet) null);
    }

    public AudiGT6LoopRotarySwitchView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public AudiGT6LoopRotarySwitchView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mOrientation = 1;
        this.restAnimator = null;
        this.rAnimation = null;
        this.zAnimation = null;
        this.xAnimation = null;
        this.loopRotationX = 0.0f;
        this.loopRotationZ = 0.0f;
        this.mGestureDetector = null;
        this.selectItem = 0;
        this.size = 0;
        this.r = 200.0f;
        this.multiple = 2.0f;
        this.distance = 2.0f * 200.0f;
        this.angle = INITIALIZATION_ANGLE;
        this.last_angle = 0.0f;
        this.autoRotation = false;
        this.touching = false;
        this.autoRotatinDirection = AutoScrollDirection.left;
        this.views = new ArrayList();
        this.onItemSelectedListener = null;
        this.onLoopViewTouchListener = null;
        this.onItemClickListener = null;
        this.isCanClickListener = true;
        this.limitX = INITIALIZATION_ANGLE;
        this.xOffset = 0.0f;
        this.yOffset = 0.0f;
        this.invisibilityStartAngle = 300;
        this.invisibilityEndAngle = 360;
        this.invisibilityAngle = 360 - 300;
        this.loopHandler = new LoopRotarySwitchViewHandler(Math.abs((int) (10.0d - this.v))) {
            /* JADX WARNING: Removed duplicated region for block: B:13:0x0048 A[Catch:{ Exception -> 0x0059 }] */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public void doScroll() {
                /*
                    r4 = this;
                    com.szchoiceway.customerui.views.AudiGT6LoopRotarySwitchView r0 = com.szchoiceway.customerui.views.AudiGT6LoopRotarySwitchView.this     // Catch:{ Exception -> 0x0059 }
                    int r0 = r0.size     // Catch:{ Exception -> 0x0059 }
                    if (r0 == 0) goto L_0x005d
                    int[] r0 = com.szchoiceway.customerui.views.AudiGT6LoopRotarySwitchView.AnonymousClass11.$SwitchMap$com$szchoiceway$customerui$views$AudiGT6LoopRotarySwitchView$AutoScrollDirection     // Catch:{ Exception -> 0x0059 }
                    com.szchoiceway.customerui.views.AudiGT6LoopRotarySwitchView r1 = com.szchoiceway.customerui.views.AudiGT6LoopRotarySwitchView.this     // Catch:{ Exception -> 0x0059 }
                    com.szchoiceway.customerui.views.AudiGT6LoopRotarySwitchView$AutoScrollDirection r1 = r1.autoRotatinDirection     // Catch:{ Exception -> 0x0059 }
                    int r1 = r1.ordinal()     // Catch:{ Exception -> 0x0059 }
                    r0 = r0[r1]     // Catch:{ Exception -> 0x0059 }
                    r1 = 1
                    r2 = 0
                    if (r0 == r1) goto L_0x002e
                    r1 = 2
                    if (r0 == r1) goto L_0x001f
                    r0 = r2
                    goto L_0x003c
                L_0x001f:
                    com.szchoiceway.customerui.views.AudiGT6LoopRotarySwitchView r0 = com.szchoiceway.customerui.views.AudiGT6LoopRotarySwitchView.this     // Catch:{ Exception -> 0x0059 }
                    int r0 = r0.getRealAngle()     // Catch:{ Exception -> 0x0059 }
                    int r0 = -r0
                    com.szchoiceway.customerui.views.AudiGT6LoopRotarySwitchView r1 = com.szchoiceway.customerui.views.AudiGT6LoopRotarySwitchView.this     // Catch:{ Exception -> 0x0059 }
                    int r1 = r1.size     // Catch:{ Exception -> 0x0059 }
                    int r0 = r0 / r1
                    goto L_0x003b
                L_0x002e:
                    com.szchoiceway.customerui.views.AudiGT6LoopRotarySwitchView r0 = com.szchoiceway.customerui.views.AudiGT6LoopRotarySwitchView.this     // Catch:{ Exception -> 0x0059 }
                    int r0 = r0.getRealAngle()     // Catch:{ Exception -> 0x0059 }
                    com.szchoiceway.customerui.views.AudiGT6LoopRotarySwitchView r1 = com.szchoiceway.customerui.views.AudiGT6LoopRotarySwitchView.this     // Catch:{ Exception -> 0x0059 }
                    int r1 = r1.size     // Catch:{ Exception -> 0x0059 }
                    int r0 = r0 / r1
                L_0x003b:
                    float r0 = (float) r0     // Catch:{ Exception -> 0x0059 }
                L_0x003c:
                    com.szchoiceway.customerui.views.AudiGT6LoopRotarySwitchView r1 = com.szchoiceway.customerui.views.AudiGT6LoopRotarySwitchView.this     // Catch:{ Exception -> 0x0059 }
                    float r1 = r1.angle     // Catch:{ Exception -> 0x0059 }
                    r3 = 1135869952(0x43b40000, float:360.0)
                    int r1 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
                    if (r1 != 0) goto L_0x004d
                    com.szchoiceway.customerui.views.AudiGT6LoopRotarySwitchView r1 = com.szchoiceway.customerui.views.AudiGT6LoopRotarySwitchView.this     // Catch:{ Exception -> 0x0059 }
                    float unused = r1.angle = r2     // Catch:{ Exception -> 0x0059 }
                L_0x004d:
                    com.szchoiceway.customerui.views.AudiGT6LoopRotarySwitchView r4 = com.szchoiceway.customerui.views.AudiGT6LoopRotarySwitchView.this     // Catch:{ Exception -> 0x0059 }
                    float r1 = r4.angle     // Catch:{ Exception -> 0x0059 }
                    float r1 = r1 + r0
                    r0 = 0
                    r4.AnimRotationTo(r1, r0)     // Catch:{ Exception -> 0x0059 }
                    goto L_0x005d
                L_0x0059:
                    r4 = move-exception
                    r4.printStackTrace()
                L_0x005d:
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.szchoiceway.customerui.views.AudiGT6LoopRotarySwitchView.AnonymousClass1.doScroll():void");
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
            this.loopRotationZ = 0.0f;
        } else {
            this.loopRotationZ = 90.0f;
        }
        if (i2 == 0) {
            this.autoRotatinDirection = AutoScrollDirection.left;
        } else {
            this.autoRotatinDirection = AutoScrollDirection.right;
        }
        this.loopHandler.setLoop(this.autoRotation);
    }

    /* renamed from: com.szchoiceway.customerui.views.AudiGT6LoopRotarySwitchView$11  reason: invalid class name */
    static /* synthetic */ class AnonymousClass11 {
        static final /* synthetic */ int[] $SwitchMap$com$szchoiceway$customerui$views$AudiGT6LoopRotarySwitchView$AutoScrollDirection;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|6) */
        /* JADX WARNING: Code restructure failed: missing block: B:7:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        static {
            /*
                com.szchoiceway.customerui.views.AudiGT6LoopRotarySwitchView$AutoScrollDirection[] r0 = com.szchoiceway.customerui.views.AudiGT6LoopRotarySwitchView.AutoScrollDirection.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$szchoiceway$customerui$views$AudiGT6LoopRotarySwitchView$AutoScrollDirection = r0
                com.szchoiceway.customerui.views.AudiGT6LoopRotarySwitchView$AutoScrollDirection r1 = com.szchoiceway.customerui.views.AudiGT6LoopRotarySwitchView.AutoScrollDirection.left     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$szchoiceway$customerui$views$AudiGT6LoopRotarySwitchView$AutoScrollDirection     // Catch:{ NoSuchFieldError -> 0x001d }
                com.szchoiceway.customerui.views.AudiGT6LoopRotarySwitchView$AutoScrollDirection r1 = com.szchoiceway.customerui.views.AudiGT6LoopRotarySwitchView.AutoScrollDirection.right     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.szchoiceway.customerui.views.AudiGT6LoopRotarySwitchView.AnonymousClass11.<clinit>():void");
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

    /* access modifiers changed from: private */
    public int getRealAngle() {
        return 360 - this.invisibilityAngle;
    }

    private GestureDetector.SimpleOnGestureListener getGeomeryController() {
        return new GestureDetector.SimpleOnGestureListener() {
            public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
                Log.i("TAG", "LoopRSV-onScroll手勢调用了...");
                Log.i("TAG", "LoopRSV-onScroll-distanceX = " + f + "distanceY = " + f2);
                AudiGT6LoopRotarySwitchView audiGT6LoopRotarySwitchView = AudiGT6LoopRotarySwitchView.this;
                double d = (double) (f / 4.0f);
                double d2 = (double) (f2 / 4.0f);
                double unused = audiGT6LoopRotarySwitchView.v = Math.abs((Math.cos(Math.toRadians((double) audiGT6LoopRotarySwitchView.loopRotationZ)) * d) + (Math.sin(Math.toRadians((double) AudiGT6LoopRotarySwitchView.this.loopRotationZ)) * d2));
                AudiGT6LoopRotarySwitchView audiGT6LoopRotarySwitchView2 = AudiGT6LoopRotarySwitchView.this;
                AudiGT6LoopRotarySwitchView.access$318(audiGT6LoopRotarySwitchView2, (Math.cos(Math.toRadians((double) audiGT6LoopRotarySwitchView2.loopRotationZ)) * d) + (Math.sin(Math.toRadians((double) AudiGT6LoopRotarySwitchView.this.loopRotationZ)) * d2));
                Log.i("TAG", "LoopRSV-onScroll-angle =" + AudiGT6LoopRotarySwitchView.this.angle);
                Log.i("TAG", "LoopRSV-onScroll-v =" + AudiGT6LoopRotarySwitchView.this.v);
                AudiGT6LoopRotarySwitchView.this.initView();
                return true;
            }
        };
    }

    public void initView() {
        float f = this.angle;
        int i = this.invisibilityStartAngle;
        if (f >= ((float) (i + 30))) {
            this.angle = f - ((float) i);
        }
        float f2 = this.angle;
        if (f2 < INITIALIZATION_ANGLE) {
            this.angle = f2 + ((float) i);
        }
        Log.d("AudiGT6LoopRotarySwitchView", "angle: " + this.angle);
        ArrayList arrayList = new ArrayList();
        for (int i2 = 0; i2 < this.views.size(); i2++) {
            double realAngle = ((double) (this.angle + 240.0f)) - (((((double) i2) * 1.0d) * ((double) getRealAngle())) / ((double) this.size));
            if (realAngle < 0.0d) {
                realAngle += 360.0d;
            }
            if (realAngle > 510.0d) {
                realAngle %= 360.0d;
            }
            if (JavaStandard.isInRange(realAngle, (double) this.invisibilityStartAngle, 510.0d)) {
                double resetRadians = resetRadians(i2, realAngle);
                if (resetRadians == realAngle) {
                    resetRadians = (realAngle + ((double) this.invisibilityAngle)) % 360.0d;
                }
                arrayList.add(new JavaTriple(Double.valueOf(resetRadians), true, Integer.valueOf(i2)));
            } else {
                double resetRadians2 = resetRadians(i2, realAngle);
                if (resetRadians2 != realAngle) {
                    realAngle = resetRadians2;
                }
                arrayList.add(new JavaTriple(Double.valueOf(realAngle), false, Integer.valueOf(i2)));
            }
        }
        ((List) JavaStandard.let(ListUtil.groupBy(arrayList, $$Lambda$AudiGT6LoopRotarySwitchView$nDphLcr8enHmNG1_SK2vLcQaI4s.INSTANCE), new Function1() {
            public final Object invoke(Object obj) {
                return AudiGT6LoopRotarySwitchView.this.lambda$initView$5$AudiGT6LoopRotarySwitchView((Map) obj);
            }
        })).forEach(new Consumer() {
            public final void accept(Object obj) {
                AudiGT6LoopRotarySwitchView.this.lambda$initView$6$AudiGT6LoopRotarySwitchView((JavaPair) obj);
            }
        });
        ArrayList arrayList2 = new ArrayList();
        arrayList2.clear();
        for (int i3 = 0; i3 < this.views.size(); i3++) {
            arrayList2.add(this.views.get(i3));
        }
        postInvalidate();
    }

    public /* synthetic */ List lambda$initView$5$AudiGT6LoopRotarySwitchView(Map map) {
        map.forEach(new BiConsumer() {
            public final void accept(Object obj, Object obj2) {
                AudiGT6LoopRotarySwitchView.this.lambda$initView$2$AudiGT6LoopRotarySwitchView((Double) obj, (List) obj2);
            }
        });
        ArrayList arrayList = new ArrayList();
        map.forEach(new BiConsumer(arrayList) {
            public final /* synthetic */ List f$0;

            {
                this.f$0 = r1;
            }

            public final void accept(Object obj, Object obj2) {
                ((List) obj2).forEach(new Consumer(this.f$0) {
                    public final /* synthetic */ List f$0;

                    {
                        this.f$0 = r1;
                    }

                    public final void accept(Object obj) {
                        this.f$0.add(new JavaPair((Double) ((JavaTriple) obj).first, (Integer) ((JavaTriple) obj).third));
                    }
                });
            }
        });
        return arrayList;
    }

    public /* synthetic */ void lambda$initView$2$AudiGT6LoopRotarySwitchView(Double d, List list) {
        if (list.size() > 1) {
            list.forEach(new Consumer() {
                public final void accept(Object obj) {
                    AudiGT6LoopRotarySwitchView.this.lambda$initView$1$AudiGT6LoopRotarySwitchView((JavaTriple) obj);
                }
            });
        }
    }

    public /* synthetic */ void lambda$initView$1$AudiGT6LoopRotarySwitchView(JavaTriple javaTriple) {
        if (!((Boolean) javaTriple.second).booleanValue()) {
            javaTriple.first = Double.valueOf(((Double) javaTriple.first).doubleValue() + ((double) this.invisibilityAngle));
        }
    }

    public /* synthetic */ void lambda$initView$6$AudiGT6LoopRotarySwitchView(JavaPair javaPair) {
        double doubleValue = ((Double) javaPair.first).doubleValue();
        this.views.get(((Integer) javaPair.second).intValue()).setZ((float) doubleValue);
        float sin = ((float) Math.sin(Math.toRadians(((Double) javaPair.first).doubleValue()))) * this.r;
        float f = this.r;
        float f2 = this.distance;
        float cos = (f2 - (((float) Math.cos(Math.toRadians(((Double) javaPair.first).doubleValue()))) * f)) / (f2 + f);
        this.views.get(((Integer) javaPair.second).intValue()).setScaleX(cos);
        this.views.get(((Integer) javaPair.second).intValue()).setScaleY(cos);
        float sin2 = ((float) Math.sin(Math.toRadians(((double) this.loopRotationX) * Math.cos(Math.toRadians(((Double) javaPair.first).doubleValue()))))) * this.r;
        float f3 = (-((float) Math.sin(Math.toRadians((double) (-this.loopRotationZ))))) * sin;
        this.views.get(((Integer) javaPair.second).intValue()).setTranslationX(sin + ((((float) Math.cos(Math.toRadians((double) (-this.loopRotationZ)))) * sin) - sin) + this.xOffset);
        this.views.get(((Integer) javaPair.second).intValue()).setTranslationY(sin2 + f3 + this.yOffset);
        if (JavaStandard.isInRange(doubleValue, 240.0d, 360.0d)) {
            this.views.get(((Integer) javaPair.second).intValue()).setAlpha(0.0f);
        } else {
            this.views.get(((Integer) javaPair.second).intValue()).setAlpha(1.0f);
        }
    }

    private double resetRadians(int i, double d) {
        Log.d("AudiGT6LoopRotarySwitchView", "index:" + i + ", view:" + this.views.get(i).getClass().getSimpleName() + ", radians:" + d);
        boolean z = this.views.get(i) instanceof AudiGt6OriginalCarLauncherIconView;
        return d;
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

    public View getView(int i) {
        return this.views.get(i);
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
                float unused = AudiGT6LoopRotarySwitchView.this.r = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                AudiGT6LoopRotarySwitchView.this.initView();
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
        this.views.clear();
        int childCount = getChildCount();
        this.size = childCount;
        for (final int i2 = 0; i2 < childCount; i2++) {
            View childAt = getChildAt(i2);
            this.views.add(childAt);
            childAt.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (AudiGT6LoopRotarySwitchView.this.isCanClickListener && AudiGT6LoopRotarySwitchView.this.onItemClickListener != null) {
                        AudiGT6LoopRotarySwitchView.this.onItemClickListener.onItemClick(i2, (View) AudiGT6LoopRotarySwitchView.this.views.get(i2));
                    }
                }
            });
        }
    }

    public void restPosition() {
        if (this.size != 0) {
            float realAngle = (float) (getRealAngle() / this.size);
            float f = this.angle;
            if (f < 0.0f) {
                realAngle = -realAngle;
            }
            float f2 = ((float) ((int) (f / realAngle))) * realAngle;
            float f3 = (((float) ((int) (f / realAngle))) * realAngle) + realAngle;
            if (f < 0.0f ? f - this.last_angle < 0.0f : f - this.last_angle > 0.0f) {
                f2 = f3;
            }
            AnimRotationTo(f2, (Runnable) null);
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
                    if (!AudiGT6LoopRotarySwitchView.this.touching) {
                        float unused = AudiGT6LoopRotarySwitchView.this.angle = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                        AudiGT6LoopRotarySwitchView.this.initView();
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
                    if (!AudiGT6LoopRotarySwitchView.this.touching) {
                        AudiGT6LoopRotarySwitchView audiGT6LoopRotarySwitchView = AudiGT6LoopRotarySwitchView.this;
                        int unused = audiGT6LoopRotarySwitchView.selectItem = audiGT6LoopRotarySwitchView.calculateItem();
                        if (AudiGT6LoopRotarySwitchView.this.selectItem < 0) {
                            AudiGT6LoopRotarySwitchView audiGT6LoopRotarySwitchView2 = AudiGT6LoopRotarySwitchView.this;
                            int unused2 = audiGT6LoopRotarySwitchView2.selectItem = audiGT6LoopRotarySwitchView2.size + AudiGT6LoopRotarySwitchView.this.selectItem;
                        }
                        if (AudiGT6LoopRotarySwitchView.this.onItemSelectedListener != null && AudiGT6LoopRotarySwitchView.this.isSlide) {
                            AudiGT6LoopRotarySwitchView.this.onItemSelectedListener.selected(AudiGT6LoopRotarySwitchView.this.selectItem, (View) AudiGT6LoopRotarySwitchView.this.views.get(AudiGT6LoopRotarySwitchView.this.selectItem));
                        }
                        boolean unused3 = AudiGT6LoopRotarySwitchView.this.isSlide = true;
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
        int realAngle = getRealAngle();
        int i = this.size;
        return ((int) (f / ((float) (realAngle / i)))) % i;
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

    public void setSelectItemValue(int i) {
        this.selectItem = i;
    }

    /* JADX WARNING: Removed duplicated region for block: B:24:0x007c  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0086  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x008c  */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x0093  */
    /* JADX WARNING: Removed duplicated region for block: B:33:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setSelectItem(int r4, boolean r5) {
        /*
            r3 = this;
            int r0 = r3.size
            if (r0 != 0) goto L_0x0005
            return
        L_0x0005:
            r3.isSlide = r5
            if (r4 < 0) goto L_0x0097
            int r5 = r3.getSelectItem()
            if (r5 != 0) goto L_0x0031
            java.util.List<android.view.View> r5 = r3.views
            int r5 = r5.size()
            int r5 = r5 + -1
            if (r4 != r5) goto L_0x0025
            float r4 = r3.angle
            int r5 = r3.getRealAngle()
            int r0 = r3.size
            int r5 = r5 / r0
        L_0x0022:
            float r5 = (float) r5
            float r4 = r4 - r5
            goto L_0x006f
        L_0x0025:
            float r4 = r3.angle
            int r5 = r3.getRealAngle()
            int r0 = r3.size
            int r5 = r5 / r0
        L_0x002e:
            float r5 = (float) r5
            float r4 = r4 + r5
            goto L_0x006f
        L_0x0031:
            int r5 = r3.getSelectItem()
            java.util.List<android.view.View> r0 = r3.views
            int r0 = r0.size()
            int r0 = r0 + -1
            if (r5 != r0) goto L_0x0055
            if (r4 != 0) goto L_0x004b
            float r4 = r3.angle
            int r5 = r3.getRealAngle()
            int r0 = r3.size
            int r5 = r5 / r0
            goto L_0x002e
        L_0x004b:
            float r4 = r3.angle
            int r5 = r3.getRealAngle()
            int r0 = r3.size
            int r5 = r5 / r0
            goto L_0x0022
        L_0x0055:
            int r5 = r3.getSelectItem()
            if (r4 <= r5) goto L_0x0065
            float r4 = r3.angle
            int r5 = r3.getRealAngle()
            int r0 = r3.size
            int r5 = r5 / r0
            goto L_0x002e
        L_0x0065:
            float r4 = r3.angle
            int r5 = r3.getRealAngle()
            int r0 = r3.size
            int r5 = r5 / r0
            goto L_0x0022
        L_0x006f:
            int r5 = r3.getRealAngle()
            int r0 = r3.size
            int r5 = r5 / r0
            float r5 = (float) r5
            r1 = 0
            int r2 = (r4 > r1 ? 1 : (r4 == r1 ? 0 : -1))
            if (r2 >= 0) goto L_0x007d
            float r5 = -r5
        L_0x007d:
            float r2 = r4 / r5
            int r2 = (int) r2
            float r2 = (float) r2
            float r2 = r2 * r5
            int r5 = (r4 > r1 ? 1 : (r4 == r1 ? 0 : -1))
            if (r5 < 0) goto L_0x008c
            float r5 = r3.last_angle
            float r4 = r4 - r5
            int r4 = (r4 > r1 ? 1 : (r4 == r1 ? 0 : -1))
            goto L_0x0091
        L_0x008c:
            float r5 = r3.last_angle
            float r4 = r4 - r5
            int r4 = (r4 > r1 ? 1 : (r4 == r1 ? 0 : -1))
        L_0x0091:
            if (r0 <= 0) goto L_0x0097
            r4 = 0
            r3.AnimRotationTo(r2, r4)
        L_0x0097:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.szchoiceway.customerui.views.AudiGT6LoopRotarySwitchView.setSelectItem(int, boolean):void");
    }

    public void selectedItemClick() {
        OnItemClickListener onItemClickListener2;
        int i = this.selectItem;
        if (i >= 0 && i < this.size && (onItemClickListener2 = this.onItemClickListener) != null) {
            onItemClickListener2.onItemClick((i + 2) % this.views.size(), this.views.get(this.selectItem));
        }
    }

    public void scrollToPreItem() {
        int i = this.selectItem - 1;
        if (i < 0) {
            i = this.size - 1;
        }
        setSelectItem(i, true);
    }

    public void scrollToNextItem() {
        int i = this.selectItem + 1;
        if (i == this.size) {
            i = 0;
        }
        setSelectItem(i, true);
    }

    public AudiGT6LoopRotarySwitchView setR(float f) {
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

    public AudiGT6LoopRotarySwitchView setAutoRotation(boolean z) {
        this.autoRotation = z;
        this.loopHandler.setLoop(z);
        return this;
    }

    public long getAutoRotationTime() {
        return this.loopHandler.loopTime;
    }

    public AudiGT6LoopRotarySwitchView setAutoRotationTime(long j) {
        this.loopHandler.setLoopTime(j);
        return this;
    }

    public boolean isAutoRotation() {
        return this.autoRotation;
    }

    public AudiGT6LoopRotarySwitchView setMultiple(float f) {
        this.multiple = f;
        return this;
    }

    public AudiGT6LoopRotarySwitchView setAutoScrollDirection(AutoScrollDirection autoScrollDirection) {
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
                float unused = AudiGT6LoopRotarySwitchView.this.loopRotationX = (float) ((Integer) valueAnimator.getAnimatedValue()).intValue();
                AudiGT6LoopRotarySwitchView.this.initView();
            }
        });
        this.xAnimation.setInterpolator(new DecelerateInterpolator());
        this.xAnimation.setDuration(2000);
        if (z) {
            this.xAnimation.start();
        }
    }

    public ValueAnimator createZAnimation(float f, float f2, boolean z) {
        ValueAnimator valueAnimator = this.zAnimation;
        if (valueAnimator != null && valueAnimator.isRunning()) {
            this.zAnimation.cancel();
        }
        ValueAnimator ofFloat = ValueAnimator.ofFloat(new float[]{f, f2});
        this.zAnimation = ofFloat;
        ofFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float unused = AudiGT6LoopRotarySwitchView.this.loopRotationZ = (float) ((Integer) valueAnimator.getAnimatedValue()).intValue();
                AudiGT6LoopRotarySwitchView.this.initView();
            }
        });
        this.zAnimation.setInterpolator(new DecelerateInterpolator());
        this.zAnimation.setDuration(2000);
        if (z) {
            this.zAnimation.start();
        }
        return this.zAnimation;
    }

    public AudiGT6LoopRotarySwitchView setOrientation(int i) {
        boolean z = true;
        if (i != 1) {
            z = false;
        }
        setHorizontal(z, false);
        return this;
    }

    public AudiGT6LoopRotarySwitchView setHorizontal(boolean z, boolean z2) {
        if (!z2) {
            if (z) {
                setLoopRotationZ(0.0f);
            } else {
                setLoopRotationZ(90.0f);
            }
            initView();
        } else if (z) {
            createZAnimation(getLoopRotationZ(), 0.0f, true);
        } else {
            createZAnimation(getLoopRotationZ(), 90.0f, true);
        }
        return this;
    }

    public AudiGT6LoopRotarySwitchView setLoopRotationX(float f) {
        this.loopRotationX = f;
        return this;
    }

    public AudiGT6LoopRotarySwitchView setLoopRotationZ(float f) {
        this.loopRotationZ = f;
        return this;
    }

    public float getLoopRotationX() {
        return this.loopRotationX;
    }

    public void setXOffset(float f) {
        this.xOffset = f;
    }

    public void setYOffset(float f) {
        this.yOffset = f;
    }

    public float getLoopRotationZ() {
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
