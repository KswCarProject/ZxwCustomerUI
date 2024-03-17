package com.szchoiceway.customerui.drag;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.Log;
import com.szchoiceway.customerui.R;
import com.szchoiceway.customerui.utils.EventUtils;
import com.szchoiceway.customerui.utils.ShareUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DragAppListInfo {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final String SP_FILE_APPS_KEY = "appTag";
    public static final String SP_FILE_MAIN = "mainAppTag";
    public static final String SP_FILE_MAIN_KEY = "mainTag";
    public static final String SP_FILE_TYPE_KEY = "type";
    private static final String TAG = "DragAppListInfo";
    public static HashMap<String, DragAppInfo> mAppFragmentItemDetailsMap = new HashMap<>();
    public static List<String> mAppsFragmentItemTagList = new ArrayList();
    private static int mArrayDrawableId = R.array.MainAppDrawableId;
    private static int mArrayId = R.array.MainAppId;
    private static int mArrayTag = R.array.MainAppTag;
    public static Map<String, DragAppInfo> mMainItemDetailsMap = new HashMap();
    public static List<Integer> mMainItemIdList = new ArrayList();
    public static Map<Integer, String> mMainItemIdTagMap = new HashMap();
    public static Map<String, Integer> mMainItemTagDrawableIdMap = new HashMap();
    public static List<String> mMainItemTagList = new ArrayList();

    public static void initMainArray(int i, String str, String str2) {
        String str3 = i + "_" + str + "_" + str2;
        Log.i(TAG, "initMainArray: dragUIType = " + str3);
        if (!str3.equalsIgnoreCase(ShareUtil.getString("type"))) {
            ShareUtil.putString(SP_FILE_MAIN_KEY, "");
            ShareUtil.putString(SP_FILE_APPS_KEY, "");
        }
        ShareUtil.putString("type", str3);
    }

    public static void initMainDefault(Context context, HashMap<String, DragAppInfo> hashMap) {
        Log.i("TAG", "initMainDefault:     " + hashMap.size());
        TypedArray obtainTypedArray = context.getResources().obtainTypedArray(mArrayId);
        TypedArray obtainTypedArray2 = context.getResources().obtainTypedArray(mArrayTag);
        TypedArray obtainTypedArray3 = context.getResources().obtainTypedArray(mArrayDrawableId);
        mMainItemIdList.clear();
        mMainItemTagList.clear();
        mMainItemIdTagMap.clear();
        mMainItemTagDrawableIdMap.clear();
        mMainItemDetailsMap.clear();
        for (int i = 0; i < obtainTypedArray2.length(); i++) {
            int resourceId = obtainTypedArray.getResourceId(i, 0);
            String string = obtainTypedArray2.getString(i);
            int resourceId2 = obtainTypedArray3.getResourceId(i, 0);
            mMainItemIdList.add(Integer.valueOf(resourceId));
            mMainItemTagList.add(string);
            mMainItemTagDrawableIdMap.put(string, Integer.valueOf(resourceId2));
        }
        obtainTypedArray.recycle();
        obtainTypedArray2.recycle();
        obtainTypedArray3.recycle();
        String[] strArr = null;
        String string2 = ShareUtil.getString(SP_FILE_MAIN_KEY);
        if (!TextUtils.isEmpty(string2)) {
            strArr = string2.split(",");
        }
        if (strArr != null && strArr.length > 0) {
            mMainItemTagList.clear();
            mMainItemTagList.addAll(Arrays.asList(strArr));
        }
        if (mMainItemTagList.size() > 0) {
            for (int i2 = 0; i2 < mMainItemTagList.size(); i2++) {
                Log.i(TAG, "initMainDefault: tag = " + mMainItemTagList.get(i2));
                mMainItemIdTagMap.put(mMainItemIdList.get(i2), mMainItemTagList.get(i2));
                initMainDefaultDetails(context, mMainItemTagList.get(i2), hashMap);
            }
        }
        for (Map.Entry next : mMainItemDetailsMap.entrySet()) {
            Log.i(TAG, "initMainDefault: key = " + ((String) next.getKey()) + ", value = " + ((DragAppInfo) next.getValue()));
        }
    }

    private static void initMainDefaultDetails(Context context, String str, HashMap<String, DragAppInfo> hashMap) {
        Log.i(TAG, "initMainDefaultDetails: tag = " + str);
        DragAppInfo dragAppInfo = new DragAppInfo();
        if (mMainItemTagDrawableIdMap.containsKey(str)) {
            if (context.getString(R.string.lbl_navi_tag).equals(str)) {
                dragAppInfo.setTag(str);
                dragAppInfo.setAppName(context.getString(R.string.lb_navi));
                dragAppInfo.setAppPackageName("com.szchoiceway.navigation");
                dragAppInfo.setAppClassName("com.szchoiceway.navigation.MainActivity");
                dragAppInfo.setDrawableId(mMainItemTagDrawableIdMap.get(str).intValue());
                mMainItemDetailsMap.put(str, dragAppInfo);
            } else if (context.getString(R.string.lbl_music_tag).equals(str)) {
                dragAppInfo.setTag(str);
                dragAppInfo.setAppName(context.getString(R.string.lb_music));
                dragAppInfo.setAppPackageName("com.szchoiceway.musicplayer");
                dragAppInfo.setAppClassName("com.szchoiceway.musicplayer.MainActivity");
                dragAppInfo.setDrawableId(mMainItemTagDrawableIdMap.get(str).intValue());
                mMainItemDetailsMap.put(str, dragAppInfo);
            } else if (context.getString(R.string.lbl_bluetooth_tag).equals(str)) {
                dragAppInfo.setTag(str);
                dragAppInfo.setAppName(context.getString(R.string.lb_left_bt));
                dragAppInfo.setAppPackageName("com.szchoiceway.btsuite");
                dragAppInfo.setAppClassName("com.szchoiceway.btsuite.BTMainActivity");
                dragAppInfo.setDrawableId(mMainItemTagDrawableIdMap.get(str).intValue());
                mMainItemDetailsMap.put(str, dragAppInfo);
            } else if (context.getString(R.string.lbl_video_tag).equals(str)) {
                dragAppInfo.setTag(str);
                dragAppInfo.setAppName(context.getString(R.string.lb_video));
                dragAppInfo.setAppPackageName("com.szchoiceway.videoplayer");
                dragAppInfo.setAppClassName("com.szchoiceway.videoplayer.MainActivity");
                dragAppInfo.setDrawableId(mMainItemTagDrawableIdMap.get(str).intValue());
                mMainItemDetailsMap.put(str, dragAppInfo);
            } else if (context.getString(R.string.lbl_originalcar_tag).equals(str)) {
                dragAppInfo.setTag(str);
                dragAppInfo.setAppName(context.getString(R.string.lb_yuanche_q5_ksw));
                dragAppInfo.setAppPackageName(str);
                dragAppInfo.setAppClassName(str);
                dragAppInfo.setDrawableId(mMainItemTagDrawableIdMap.get(str).intValue());
                mMainItemDetailsMap.put(str, dragAppInfo);
            } else if (context.getString(R.string.lbl_carauto_tag).equals(str)) {
                dragAppInfo.setTag(str);
                dragAppInfo.setAppName(context.getString(R.string.lb_carplay));
                dragAppInfo.setAppPackageName(str);
                dragAppInfo.setAppClassName(str);
                dragAppInfo.setDrawableId(mMainItemTagDrawableIdMap.get(str).intValue());
                mMainItemDetailsMap.put(str, dragAppInfo);
            } else if (context.getString(R.string.lbl_setting_tag).equals(str)) {
                dragAppInfo.setTag(str);
                dragAppInfo.setAppName(context.getString(R.string.lb_settings));
                dragAppInfo.setAppPackageName("com.szchoiceway.settings");
                dragAppInfo.setAppClassName("com.szchoiceway.settings.MainActivity");
                dragAppInfo.setDrawableId(mMainItemTagDrawableIdMap.get(str).intValue());
                mMainItemDetailsMap.put(str, dragAppInfo);
            } else if (context.getString(R.string.lbl_apps_tag).equals(str)) {
                dragAppInfo.setTag(str);
                dragAppInfo.setAppName(context.getString(R.string.lbl_applist));
                dragAppInfo.setAppPackageName(str);
                dragAppInfo.setAppClassName(str);
                dragAppInfo.setDrawableId(mMainItemTagDrawableIdMap.get(str).intValue());
                mMainItemDetailsMap.put(str, dragAppInfo);
            } else if (context.getString(R.string.lbl_googleplay_tag).equals(str)) {
                dragAppInfo.setTag(str);
                dragAppInfo.setAppName(context.getString(R.string.lb_goolge_play));
                dragAppInfo.setAppPackageName(str);
                dragAppInfo.setAppClassName(str);
                dragAppInfo.setDrawableId(mMainItemTagDrawableIdMap.get(str).intValue());
                mMainItemDetailsMap.put(str, dragAppInfo);
            } else if (context.getString(R.string.lbl_browser_tag).equals(str)) {
                dragAppInfo.setTag(str);
                dragAppInfo.setAppName(context.getString(R.string.lb_Browser));
                dragAppInfo.setAppPackageName("com.android.chrome");
                dragAppInfo.setAppClassName("com.google.android.apps.chrome.Main");
                dragAppInfo.setDrawableId(mMainItemTagDrawableIdMap.get(str).intValue());
                mMainItemDetailsMap.put(str, dragAppInfo);
            } else if (context.getString(R.string.lbl_youtube_tag).equals(str)) {
                dragAppInfo.setTag(str);
                dragAppInfo.setAppName(context.getString(R.string.lb_youtube));
                dragAppInfo.setAppPackageName(EventUtils.YOUTUBE_MODE_PACKAGE_NAME);
                dragAppInfo.setAppClassName(EventUtils.YOUTUBE_MODE_CLASS_NAME);
                dragAppInfo.setDrawableId(mMainItemTagDrawableIdMap.get(str).intValue());
                mMainItemDetailsMap.put(str, dragAppInfo);
            } else if (context.getString(R.string.lbl_bluetooth_music_tag).equals(str)) {
                dragAppInfo.setTag(str);
                dragAppInfo.setAppName(context.getString(R.string.lb_btmusic));
                dragAppInfo.setAppPackageName("com.szchoiceway.btsuite");
                dragAppInfo.setAppClassName("com.szchoiceway.btsuite.BTMusicActivity");
                dragAppInfo.setDrawableId(mMainItemTagDrawableIdMap.get(str).intValue());
                mMainItemDetailsMap.put(str, dragAppInfo);
            } else if (context.getString(R.string.lbl_cleartask_tag).equals(str)) {
                dragAppInfo.setTag(str);
                dragAppInfo.setAppName(context.getString(R.string.lb_clear_task));
                dragAppInfo.setAppPackageName(str);
                dragAppInfo.setAppClassName(str);
                dragAppInfo.setDrawableId(mMainItemTagDrawableIdMap.get(str).intValue());
                mMainItemDetailsMap.put(str, dragAppInfo);
            } else if (context.getString(R.string.lbl_frontview_tag).equals(str)) {
                dragAppInfo.setTag(str);
                dragAppInfo.setAppName("F.CAM");
                dragAppInfo.setAppPackageName(str);
                dragAppInfo.setAppClassName(str);
                dragAppInfo.setDrawableId(mMainItemTagDrawableIdMap.get(str).intValue());
                mMainItemDetailsMap.put(str, dragAppInfo);
            } else if (context.getString(R.string.lbl_rightview_tag).equals(str)) {
                dragAppInfo.setTag(str);
                dragAppInfo.setAppName("R.CAM");
                dragAppInfo.setAppPackageName(str);
                dragAppInfo.setAppClassName(str);
                dragAppInfo.setDrawableId(mMainItemTagDrawableIdMap.get(str).intValue());
                mMainItemDetailsMap.put(str, dragAppInfo);
            } else if (context.getString(R.string.lbl_hdmi_tag).equals(str)) {
                dragAppInfo.setTag(str);
                dragAppInfo.setAppName("HDMI");
                dragAppInfo.setAppPackageName(str);
                dragAppInfo.setAppClassName(str);
                dragAppInfo.setDrawableId(mMainItemTagDrawableIdMap.get(str).intValue());
                mMainItemDetailsMap.put(str, dragAppInfo);
            } else if (context.getString(R.string.lbl_netflix_tag).equals(str)) {
                dragAppInfo.setTag(str);
                dragAppInfo.setAppName("Netflix");
                dragAppInfo.setAppPackageName(EventUtils.NETFLIX_MODE_PACKAGE_NAME);
                dragAppInfo.setAppClassName(EventUtils.NETFLIX_MODE_CLASS_NAME);
                dragAppInfo.setDrawableId(mMainItemTagDrawableIdMap.get(str).intValue());
                mMainItemDetailsMap.put(str, dragAppInfo);
            } else if (context.getString(R.string.lbl_spotify_tag).equals(str)) {
                dragAppInfo.setTag(str);
                dragAppInfo.setAppName("Spotify");
                dragAppInfo.setAppPackageName(EventUtils.SPOTIFY_MODE_PACKAGE_NAME);
                dragAppInfo.setAppClassName(EventUtils.SPOTIFY_MODE_CLASS_NAME);
                dragAppInfo.setDrawableId(mMainItemTagDrawableIdMap.get(str).intValue());
                mMainItemDetailsMap.put(str, dragAppInfo);
            } else if (hashMap.size() > 0 && hashMap.containsKey(str)) {
                mMainItemDetailsMap.put(str, hashMap.get(str));
            } else if (!mMainItemDetailsMap.containsKey(str)) {
                mMainItemDetailsMap.put(str, new DragAppInfo());
            }
        } else if (hashMap.size() > 0 && hashMap.containsKey(str)) {
            mMainItemDetailsMap.put(str, hashMap.get(str));
        } else if (!mMainItemDetailsMap.containsKey(str)) {
            mMainItemDetailsMap.put(str, new DragAppInfo());
        }
    }

    public static void initAppsDefault(Context context, HashMap<String, DragAppInfo> hashMap, boolean z, List<String> list) {
        Log.i(TAG, "initAppsDefault:     " + hashMap.size());
        mAppsFragmentItemTagList.clear();
        String string = ShareUtil.getString(SP_FILE_APPS_KEY);
        String[] split = !TextUtils.isEmpty(string) ? string.split(",") : null;
        if (split != null && split.length > 0) {
            for (String str : split) {
                if (hashMap.containsKey(str)) {
                    if (!z) {
                        mAppsFragmentItemTagList.add(str);
                        Log.i(TAG, "initAppsDefault: -------------------        " + str);
                    } else if (!mMainItemDetailsMap.containsKey(str)) {
                        mAppsFragmentItemTagList.add(str);
                    }
                }
            }
        }
        for (int i = 0; i < list.size(); i++) {
            if (hashMap.get(list.get(i)) != null) {
                mAppsFragmentItemTagList.add(list.get(i));
            }
        }
        ArrayList<DragAppInfo> arrayList = new ArrayList<>();
        for (Map.Entry<String, DragAppInfo> key : hashMap.entrySet()) {
            String str2 = (String) key.getKey();
            if (!list.contains(str2)) {
                arrayList.add(hashMap.get(str2));
            }
        }
        arrayList.sort($$Lambda$DragAppListInfo$IOzCRGDAIbjJFnBsxAyGceCsvFc.INSTANCE);
        for (DragAppInfo dragAppInfo : arrayList) {
            mAppsFragmentItemTagList.add(dragAppInfo.appClassName);
        }
        for (int size = mAppsFragmentItemTagList.size() - 1; size >= 0; size--) {
            String str3 = mAppsFragmentItemTagList.get(size);
            DragAppInfo dragAppInfo2 = hashMap.get(str3);
            if (dragAppInfo2 != null) {
                DrawableUtils.exchangeIcon(context, dragAppInfo2);
                mAppFragmentItemDetailsMap.put(str3, dragAppInfo2);
            } else {
                Log.d(TAG, "lost key = " + str3);
            }
        }
        for (String next : mAppsFragmentItemTagList) {
        }
        Log.d("initAppsDefault", "mAppsFragmentItemTagList.size = " + mAppsFragmentItemTagList.size());
    }

    static /* synthetic */ int lambda$initAppsDefault$0(DragAppInfo dragAppInfo, DragAppInfo dragAppInfo2) {
        return (int) (dragAppInfo.getInstallTime() - dragAppInfo2.getInstallTime());
    }

    public static void saveMainItemExchange(Context context, int i, int i2, DragAppInfo dragAppInfo) {
        int indexOf = mMainItemIdList.indexOf(Integer.valueOf(i));
        int indexOf2 = mMainItemIdList.indexOf(Integer.valueOf(i2));
        String tag = dragAppInfo.getTag();
        Log.i(TAG, "saveMainItemExchange: fromIndex = " + indexOf + ", targetIndex = " + indexOf2);
        Log.i(TAG, "saveMainItemExchange: fromTag = " + tag);
        if (indexOf == -1) {
            mMainItemTagList.set(indexOf2, tag);
        } else if (indexOf2 == -1) {
            mMainItemTagList.set(indexOf, tag);
        } else {
            mMainItemTagList.set(indexOf2, tag);
            mMainItemTagList.set(indexOf, mMainItemIdTagMap.get(Integer.valueOf(i2)));
        }
        mMainItemIdTagMap.clear();
        for (int i3 = 0; i3 < mMainItemTagList.size(); i3++) {
            Log.i(TAG, "saveMainItemExchange: DragAppListInfo.mMainItemTagList.get(" + i3 + ") = " + mMainItemTagList.get(i3));
            mMainItemIdTagMap.put(mMainItemIdList.get(i3), mMainItemTagList.get(i3));
        }
        Log.i(TAG, "saveMainItemExchange: dragAppInfo = " + dragAppInfo.toString());
        if (!mMainItemDetailsMap.containsKey(tag)) {
            Log.i(TAG, "saveMainItemExchange: No containsKey...");
            mMainItemDetailsMap.put(tag, dragAppInfo);
        }
        saveToSharedPreferences(context);
    }

    public static void saveToSharedPreferences(Context context) {
        StringBuilder sb = new StringBuilder();
        int size = mMainItemTagList.size();
        for (int i = 0; i < size; i++) {
            sb.append(mMainItemTagList.get(i));
            if (i < size - 1) {
                sb.append(",");
            }
        }
        Log.i("TAG", "saveToSharedPreferences: " + sb.toString());
        ShareUtil.putString(SP_FILE_MAIN_KEY, sb.toString());
    }

    public static void saveAppsFragmentItemExchange(Context context, int i, int i2) {
        mAppsFragmentItemTagList.set(i, mAppsFragmentItemTagList.get(i2));
        mAppsFragmentItemTagList.set(i2, mAppsFragmentItemTagList.get(i));
        saveToSharedPreferencesApps(context);
    }

    public static void saveToSharedPreferencesApps(Context context) {
        StringBuilder sb = new StringBuilder();
        int size = mAppsFragmentItemTagList.size();
        for (int i = 0; i < size; i++) {
            sb.append(mAppsFragmentItemTagList.get(i));
            if (i < size - 1) {
                sb.append(",");
            }
        }
        ShareUtil.putString(SP_FILE_APPS_KEY, sb.toString());
    }
}
