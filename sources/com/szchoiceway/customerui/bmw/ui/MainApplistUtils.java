package com.szchoiceway.customerui.bmw.ui;

import android.text.TextUtils;
import android.util.Log;
import com.szchoiceway.customerui.utils.ShareUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class MainApplistUtils {
    public static final String LEFT_BTN_KEY = "leftBtnTag";
    public static final String LEFT_BTN_KEY_PEMP = "leftBtnTagPemp";
    public static final String RECYCLE_KEY = "recycleTag";

    public LinkedList<String> getLeftBtnList(int i) {
        String[] split;
        LinkedList<String> linkedList = new LinkedList<>();
        if (i == 20) {
            linkedList.add("AppList");
            linkedList.add("Settings");
            linkedList.add("Music");
            linkedList.add(MainPageUIController.BLUETOOTH_TAG);
            linkedList.add("Navi");
        } else {
            linkedList.add("AppList");
            linkedList.add("Music");
            linkedList.add(MainPageUIController.BLUETOOTH_TAG);
            linkedList.add("Navi");
        }
        String string = ShareUtil.getString(LEFT_BTN_KEY);
        if (i == 20) {
            string = ShareUtil.getString(LEFT_BTN_KEY_PEMP);
        }
        Log.i("TAG", "getLeftBtnList: " + string);
        if (!TextUtils.isEmpty(string) && (split = string.split(",")) != null && split.length > 0) {
            linkedList.clear();
            linkedList.addAll(Arrays.asList(split));
        }
        return linkedList;
    }

    public List<String> getRecycleList() {
        ArrayList arrayList = new ArrayList();
        arrayList.add("Navi");
        arrayList.add("Weather");
        arrayList.add("Music");
        arrayList.add(MainPageUIController.CARINFO_TAG);
        arrayList.add(MainPageUIController.MODE_TAG);
        arrayList.add(MainPageUIController.BLUETOOTH_TAG);
        arrayList.add("Dashboard");
        arrayList.add("Settings");
        arrayList.add("Video");
        String string = ShareUtil.getString(RECYCLE_KEY);
        Log.i("TAG", "getRecycleList0: " + string);
        if (TextUtils.isEmpty(string) || "Weather,".equals(string)) {
            saveRecycleList(arrayList);
        }
        String string2 = ShareUtil.getString(RECYCLE_KEY);
        Log.i("TAG", "getRecycleList1: " + string2);
        String[] split = string2.split(",");
        if (split != null && split.length > 0) {
            arrayList.clear();
            arrayList.addAll(Arrays.asList(split));
        }
        return arrayList;
    }

    public void saveLeftBtnList(LinkedList<String> linkedList, int i) {
        StringBuilder sb = new StringBuilder();
        int size = linkedList.size();
        for (int i2 = 0; i2 < size; i2++) {
            sb.append(linkedList.get(i2));
            if (i2 < size - 1) {
                sb.append(",");
            }
        }
        Log.d("TAG", "saveLeftBtnList: " + sb.toString());
        if (i == 20) {
            ShareUtil.putString(LEFT_BTN_KEY_PEMP, sb.toString());
        } else {
            ShareUtil.putString(LEFT_BTN_KEY, sb.toString());
        }
    }

    public void saveRecycleList(List<String> list) {
        StringBuilder sb = new StringBuilder();
        int size = list.size();
        for (int i = 0; i < size; i++) {
            sb.append(list.get(i));
            if (i < size - 1) {
                sb.append(",");
            }
        }
        ShareUtil.putString(RECYCLE_KEY, sb.toString());
    }
}
