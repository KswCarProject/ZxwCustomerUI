package com.szchoiceway.customerui.mianitem;

import android.content.Context;
import android.util.Log;
import com.szchoiceway.customerui.utils.ShareUtil;
import com.szchoiceway.customerui.utils.SysProviderOpt;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class ItemUtil {
    private static final String TAG = "ItemUtil";
    public static HashMap<String, ItemBase> mapTagItem;
    private List<String> allItemTags = new ArrayList();
    private AppUtilCallback appUtilCallback;
    private int iModeSet = 16;
    private Context mContext;
    private String mainItemStr = "";
    private List<String> mainItemTags;
    private String thirdItemStr = "";
    private List<String> thirdItemTags;

    public interface AppUtilCallback {
        void addThirdItem();

        void deleteThirdItem();

        void onItemClickByTag(String str);

        void refreshMainFocusRecyclerView(int i);
    }

    public ItemUtil(Context context, AppUtilCallback appUtilCallback2) {
        this.mContext = context;
        this.appUtilCallback = appUtilCallback2;
        initItemDatas();
    }

    public void initItemDatas() {
        String str;
        Object obj;
        Object obj2;
        String str2;
        String str3;
        Object obj3;
        String str4;
        Object obj4;
        this.iModeSet = SysProviderOpt.getInstance(this.mContext).getRecordInteger("KESAIWEI_SYS_MODE_SELECTION", this.iModeSet);
        Log.d(TAG, "initItemDatas iModeSet = " + this.iModeSet);
        this.mainItemTags = new ArrayList();
        this.thirdItemTags = new ArrayList();
        int i = this.iModeSet;
        Object obj5 = "Weather";
        String str5 = ItemTag.DVR_TAG;
        Object obj6 = "Dashboard";
        if (i == 18) {
            str = ItemTag.CARPLAY_TAG;
            String str6 = "MAIN_ITEM_TAGS_";
            obj = obj5;
            String str7 = str5;
            obj2 = obj6;
            String str8 = str6;
            this.mainItemStr = ShareUtil.getString(str6 + this.iModeSet);
            this.thirdItemStr = ShareUtil.getString("THIRD_ITEM_TAGS_" + this.iModeSet);
            Log.d(TAG, "thirdItemStr = " + this.thirdItemStr);
            if (!"".equals(this.mainItemStr)) {
                this.mainItemTags = shareToList(this.mainItemStr);
                str3 = str7;
            } else {
                this.mainItemTags.add("Navi");
                this.mainItemTags.add("Music");
                this.mainItemTags.add(ItemTag.BT_TAG);
                this.mainItemTags.add(obj);
                this.mainItemTags.add("Settings");
                this.mainItemTags.add("Video");
                this.mainItemTags.add("AppList");
                this.mainItemTags.add(ItemTag.ORIGINAL_TAG);
                this.mainItemTags.add(obj2);
                str3 = str7;
                this.mainItemTags.add(str3);
                this.mainItemStr = listToShare(this.mainItemTags);
                ShareUtil.putString(str8 + this.iModeSet, this.mainItemStr);
            }
            if (!"".equals(this.thirdItemStr)) {
                this.thirdItemTags = shareToList(this.thirdItemStr);
            }
        } else if (i != 22) {
            if (i == 27) {
                obj3 = obj5;
                str4 = str5;
                obj4 = obj6;
                this.mainItemTags.add("Video");
                this.mainItemTags.add("Music");
                this.mainItemTags.add("Navi");
                this.mainItemTags.add(ItemTag.BT_TAG);
                this.mainItemTags.add(ItemTag.CARPLAY_TAG);
                this.mainItemTags.add(ItemTag.ORIGINAL_TAG);
                this.mainItemTags.add(obj4);
                this.mainItemTags.add("AppList");
                this.mainItemTags.add(str4);
                this.mainItemTags.add("Settings");
            } else if (i == 29) {
                str4 = str5;
                obj4 = obj6;
                this.mainItemTags.add("Navi");
                this.mainItemTags.add("Music");
                this.mainItemTags.add(ItemTag.BT_TAG);
                obj3 = obj5;
                this.mainItemTags.add(obj3);
                this.mainItemTags.add("Settings");
                this.mainItemTags.add("Video");
                this.mainItemTags.add("AppList");
                this.mainItemTags.add(ItemTag.ORIGINAL_TAG);
                this.mainItemTags.add(obj4);
                this.mainItemTags.add(str4);
            } else if (i != 30) {
                str = ItemTag.CARPLAY_TAG;
                obj = obj5;
                str2 = str5;
                obj2 = obj6;
            } else {
                this.mainItemStr = ShareUtil.getString("MAIN_ITEM_TAGS_" + this.iModeSet);
                this.thirdItemStr = ShareUtil.getString("THIRD_ITEM_TAGS_" + this.iModeSet);
                Log.d(TAG, "thirdItemStr = " + this.thirdItemStr);
                if (!"".equals(this.mainItemStr)) {
                    this.mainItemTags = shareToList(this.mainItemStr);
                    str2 = str5;
                    obj2 = obj6;
                } else {
                    this.mainItemTags.add("Navi");
                    this.mainItemTags.add("Music");
                    this.mainItemTags.add(ItemTag.BT_TAG);
                    this.mainItemTags.add(ItemTag.ORIGINAL_TAG);
                    this.mainItemTags.add("Settings");
                    this.mainItemTags.add("Video");
                    this.mainItemTags.add("AppList");
                    this.mainItemTags.add(ItemTag.CARPLAY_TAG);
                    obj2 = obj6;
                    this.mainItemTags.add(obj2);
                    str2 = str5;
                    this.mainItemTags.add(str2);
                    this.mainItemStr = listToShare(this.mainItemTags);
                    ShareUtil.putString("MAIN_ITEM_TAGS_" + this.iModeSet, this.mainItemStr);
                }
                if (!"".equals(this.thirdItemStr)) {
                    this.thirdItemTags = shareToList(this.thirdItemStr);
                }
                str = ItemTag.CARPLAY_TAG;
                obj = obj5;
            }
            obj = obj3;
            str = ItemTag.CARPLAY_TAG;
        } else {
            String str9 = str5;
            obj2 = obj6;
            String str10 = "MAIN_ITEM_TAGS_";
            this.mainItemStr = ShareUtil.getString("MAIN_ITEM_TAGS_" + this.iModeSet);
            this.thirdItemStr = ShareUtil.getString("THIRD_ITEM_TAGS_" + this.iModeSet);
            Log.d(TAG, "thirdItemStr = " + this.thirdItemStr);
            if (!"".equals(this.mainItemStr)) {
                this.mainItemTags = shareToList(this.mainItemStr);
                obj = obj5;
            } else {
                this.mainItemTags.add("Navi");
                this.mainItemTags.add("Music");
                this.mainItemTags.add(ItemTag.BT_TAG);
                this.mainItemTags.add(ItemTag.ORIGINAL_TAG);
                this.mainItemTags.add("Settings");
                this.mainItemTags.add("Video");
                this.mainItemTags.add("AppList");
                this.mainItemTags.add(ItemTag.CARPLAY_TAG);
                this.mainItemTags.add(obj2);
                obj = obj5;
                this.mainItemTags.add(obj);
                this.mainItemStr = listToShare(this.mainItemTags);
                ShareUtil.putString(str10 + this.iModeSet, this.mainItemStr);
            }
            if (!"".equals(this.thirdItemStr)) {
                this.thirdItemTags = shareToList(this.thirdItemStr);
            }
            str = ItemTag.CARPLAY_TAG;
            str2 = str9;
        }
        ArrayList arrayList = new ArrayList();
        this.allItemTags = arrayList;
        arrayList.addAll(this.mainItemTags);
        if (this.thirdItemTags.size() > 0) {
            this.allItemTags.addAll(this.thirdItemTags);
        }
        HashMap<String, ItemBase> hashMap = new HashMap<>();
        mapTagItem = hashMap;
        hashMap.put("Navi", new ItemSimple("Navi", this.appUtilCallback, this));
        mapTagItem.put("Music", new ItemMusic(this.appUtilCallback, this));
        mapTagItem.put(ItemTag.BT_TAG, new ItemBt(this.appUtilCallback, this));
        mapTagItem.put(obj, new ItemWeather(this.appUtilCallback, this));
        mapTagItem.put("Settings", new ItemSimple("Settings", this.appUtilCallback, this));
        mapTagItem.put("Video", new ItemVideo(this.appUtilCallback, this));
        mapTagItem.put("AppList", new ItemSimple("AppList", this.appUtilCallback, this));
        mapTagItem.put(ItemTag.ORIGINAL_TAG, new ItemSimple(ItemTag.ORIGINAL_TAG, this.appUtilCallback, this));
        mapTagItem.put(obj2, new ItemDashboard(this.appUtilCallback, this));
        mapTagItem.put(str2, new ItemSimple(str2, this.appUtilCallback, this));
        String str11 = str;
        mapTagItem.put(str11, new ItemSimple(str11, this.appUtilCallback, this));
        if (this.thirdItemTags.size() > 0) {
            for (String next : this.thirdItemTags) {
                Log.d(TAG, "thirdTag = " + next);
                mapTagItem.put(next, new ItemThird(next, this));
            }
        }
    }

    public List<String> getMainItemTags() {
        return this.mainItemTags;
    }

    public List<String> getAllItemTags() {
        return this.allItemTags;
    }

    private List<String> shareToList(String str) {
        ArrayList arrayList = new ArrayList();
        if (!"".equals(str)) {
            arrayList.addAll(Arrays.asList(str.split("---")));
        }
        return arrayList;
    }

    private String listToShare(List<String> list) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i));
            if (i != list.size() - 1) {
                sb.append("---");
            }
        }
        return sb.toString();
    }

    public void exchangeItems(int i, int i2) {
        Log.d(TAG, "exchangeItems position1 = " + i + ", position2 = " + i2);
        if (i < this.mainItemTags.size() && i2 < this.mainItemTags.size()) {
            Collections.swap(this.mainItemTags, i, i2);
            this.mainItemStr = listToShare(this.mainItemTags);
            ShareUtil.putString("MAIN_ITEM_TAGS_" + this.iModeSet, this.mainItemStr);
        }
        if (i >= this.mainItemTags.size() && i2 >= this.mainItemTags.size()) {
            Collections.swap(this.thirdItemTags, i - this.mainItemTags.size(), i2 - this.mainItemTags.size());
            this.thirdItemStr = listToShare(this.thirdItemTags);
            ShareUtil.putString("THIRD_ITEM_TAGS_" + this.iModeSet, this.thirdItemStr);
        }
        this.allItemTags.clear();
        this.allItemTags.addAll(this.mainItemTags);
        if (this.thirdItemTags.size() > 0) {
            this.allItemTags.addAll(this.thirdItemTags);
        }
    }

    public void addThirdApp(String str, String str2) {
        String str3 = str + "/" + str2;
        List<String> list = this.thirdItemTags;
        if (list != null && list.size() < 10) {
            this.thirdItemTags.add(str3);
            mapTagItem.put(str3, new ItemThird(str3, this));
            this.thirdItemStr = listToShare(this.thirdItemTags);
            Log.d(TAG, "addThirdApp thirdItemStr0 = " + this.thirdItemStr);
            ShareUtil.putString("THIRD_ITEM_TAGS_" + this.iModeSet, this.thirdItemStr);
            this.thirdItemStr = ShareUtil.getString("THIRD_ITEM_TAGS_" + this.iModeSet);
            Log.d(TAG, "addThirdApp thirdItemStr1 = " + this.thirdItemStr);
            this.allItemTags.clear();
            this.allItemTags.addAll(this.mainItemTags);
            if (this.thirdItemTags.size() > 0) {
                this.allItemTags.addAll(this.thirdItemTags);
            }
            AppUtilCallback appUtilCallback2 = this.appUtilCallback;
            if (appUtilCallback2 != null) {
                appUtilCallback2.addThirdItem();
            }
        }
    }

    public void deleteThirdApp(String str) {
        Log.d(TAG, "deleteThirdApp tag = " + str);
        List<String> list = this.thirdItemTags;
        if (list != null && list.size() > 0) {
            this.thirdItemTags.remove(str);
            this.thirdItemStr = listToShare(this.thirdItemTags);
            Log.d(TAG, "thirdItemStr = " + this.thirdItemStr);
            ShareUtil.putString("THIRD_ITEM_TAGS_" + this.iModeSet, this.thirdItemStr);
            this.allItemTags.clear();
            this.allItemTags.addAll(this.mainItemTags);
            if (this.thirdItemTags.size() > 0) {
                this.allItemTags.addAll(this.thirdItemTags);
            }
            AppUtilCallback appUtilCallback2 = this.appUtilCallback;
            if (appUtilCallback2 != null) {
                appUtilCallback2.deleteThirdItem();
            }
        }
    }
}
