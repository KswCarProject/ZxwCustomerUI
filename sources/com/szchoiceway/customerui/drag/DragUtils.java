package com.szchoiceway.customerui.drag;

import android.util.Log;
import android.view.DragEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

public class DragUtils {
    private static final String TAG = "DragUtils";
    static float lastx;

    public interface DragStatus {
        void complete();

        void onMove();

        void onStart();
    }

    public static void bindDragInZone(View view, final DragStatus dragStatus, final DragModel dragModel) {
        final float[] fArr = new float[2];
        final boolean[] zArr = {false};
        new String[]{""};
        view.setOnDragListener(new View.OnDragListener() {
            public boolean onDrag(View view, DragEvent dragEvent) {
                boolean z = false;
                switch (dragEvent.getAction()) {
                    case 1:
                        Log.i("rex", "onDrag:     拖拽开始");
                        DragStatus.this.onStart();
                        return true;
                    case 2:
                        Log.i("rex", "onDrag:     拖拽位置");
                        Log.i("TAG", "onDrag:--------     " + view.toString());
                        fArr[0] = dragEvent.getX();
                        fArr[1] = dragEvent.getY();
                        dragModel.setTouchXY(fArr);
                        DragStatus.this.onMove();
                        return true;
                    case 3:
                        Log.i("rex", "onDrag:     拖拽完成之后松开手指");
                        Log.e("rex", "拖拽完成之后松开手指" + dragEvent.getX() + "----" + dragEvent.getY());
                        return true;
                    case 4:
                        Log.i("rex", "onDrag:     拖拽完成");
                        StringBuilder append = new StringBuilder().append("onDrag: ");
                        if (dragEvent.getLocalState() == null) {
                            z = true;
                        }
                        Log.i("TAG", append.append(z).toString());
                        DragStatus.this.complete();
                        return true;
                    case 5:
                        Log.i("rex", "拖拽进入目标区域" + dragEvent.getX() + "----" + dragEvent.getY());
                        Log.i("rex", "onDrag:     拖拽进入目标区域");
                        zArr[0] = true;
                        DragUtils.lastx = dragEvent.getX();
                        return true;
                    case 6:
                        zArr[0] = false;
                        Log.i("rex", "onDrag:     拖拽到目标区域外");
                        return true;
                    default:
                        return false;
                }
            }
        });
    }

    public static View findChildViewUnder(View view, float f, float f2) {
        int left = view.getLeft();
        float f3 = f - ((float) left);
        float top = f2 - ((float) view.getTop());
        Log.i(TAG, "findChildViewUnder:    " + f3 + "   |   " + top);
        if (!(view instanceof ViewGroup)) {
            return null;
        }
        if (view instanceof ViewPager) {
            ViewPager viewPager = (ViewPager) view;
            View childAt = viewPager.getChildAt(viewPager.getCurrentItem());
            if (!(childAt instanceof ViewGroup)) {
                return null;
            }
            ViewGroup viewGroup = (ViewGroup) childAt;
            int childCount = viewGroup.getChildCount();
            Log.i(TAG, "findChildViewUnder:item ChildCount    " + childCount);
            for (int i = 0; i < childCount; i++) {
                Log.i(TAG, "findChildViewUnder: i = " + i);
                View childAt2 = viewGroup.getChildAt(i);
                if (childAt2 instanceof DragLinearLayout) {
                    DragLinearLayout dragLinearLayout = (DragLinearLayout) childAt2;
                    int childCount2 = dragLinearLayout.getChildCount();
                    Log.i(TAG, "findChildViewUnder: 一行   " + childCount2);
                    for (int i2 = 0; i2 < childCount2; i2++) {
                        Log.i(TAG, "findChildViewUnder: j = " + i2);
                        View childAt3 = dragLinearLayout.getChildAt(i2);
                        if (childAt3 instanceof DragLayout) {
                            float x = childAt2.getX();
                            float y = childAt2.getY();
                            Log.i(TAG, "findChildViewUnder: ------------ " + i + " --------------------");
                            Log.i(TAG, "findChildViewUnder: translationX = " + x);
                            Log.i(TAG, "findChildViewUnder: translationY = " + y);
                            Log.i(TAG, "findChildViewUnder: getLeft " + childAt3.getLeft());
                            Log.i(TAG, "findChildViewUnder: getRight " + childAt3.getRight());
                            Log.i(TAG, "findChildViewUnder: getTop " + childAt3.getTop());
                            Log.i(TAG, "findChildViewUnder: getBottom " + childAt3.getBottom());
                            if (f3 >= ((float) childAt3.getLeft()) + x && f3 <= ((float) childAt3.getRight()) + x && top >= ((float) childAt3.getTop()) + y && top <= ((float) childAt3.getBottom()) + y) {
                                return childAt3;
                            }
                        }
                    }
                    continue;
                }
            }
            return null;
        } else if (view instanceof RecyclerView) {
            Log.i(TAG, "findChildViewUnder: left = " + left);
            int childCount3 = ((RecyclerView) view).getChildCount();
            Log.i("TAG", "findChildViewUnder:RecyclerView    " + childCount3);
            int i3 = 0;
            while (i3 < childCount3) {
                RelativeLayout relativeLayout = (RelativeLayout) ((ViewGroup) view).getChildAt(i3);
                Log.i(TAG, "findChildViewUnder: " + relativeLayout.toString());
                float translationX = relativeLayout.getTranslationX();
                float translationY = relativeLayout.getTranslationY();
                if (f3 < ((float) relativeLayout.getLeft()) + translationX || f3 > ((float) relativeLayout.getRight()) + translationX || top < ((float) relativeLayout.getTop()) + translationY || top > ((float) relativeLayout.getBottom()) + translationY) {
                    i3++;
                } else {
                    int childCount4 = relativeLayout.getChildCount();
                    for (int i4 = 0; i4 < childCount4; i4++) {
                        View childAt4 = relativeLayout.getChildAt(i4);
                        if (childAt4 instanceof RelativeLayout) {
                            Log.i(TAG, "findChildViewUnder: instanceof RelativeLayout");
                            return childAt4;
                        }
                    }
                    return null;
                }
            }
            return null;
        } else {
            ((ViewGroup) view).getChildCount();
            return null;
        }
    }
}
