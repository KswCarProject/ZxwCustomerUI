package com.szchoiceway.customerui.utils;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.input.InputManager;
import android.media.MediaMetadataRetriever;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.RemoteException;
import android.os.SystemClock;
import android.os.storage.DiskInfo;
import android.os.storage.StorageManager;
import android.os.storage.VolumeInfo;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.Toast;
import com.szchoiceway.customerui.R;
import com.szchoiceway.customerui.broadcast.MediaNotificationBroadcastReceiver;
import com.szchoiceway.customerui.model.LauncherModel;
import com.szchoiceway.eventcenter.IEventService;
import com.szchoiceway.zxwlib.GyroScopeWithCompassView;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import kotlin.UByte;

public class EventUtils {
    public static final String ACTION_LAUNCHER_CHANGE_STATUS_BAR_COLOR = "ZXW_ACTION_LAUNCHER_CHANGE_STATUS_BAR_COLOR";
    public static final String ACTION_REFRESH_PHONE_SIGNAL = "com.choiceway.eventcenter.EventUtils.ACTION_REFRESH_PHONE_SIGNAL";
    public static final String ACTION_REFRESH_PHONE_SIGNAL2 = "com.choiceway.eventcenter.EventUtils.ACTION_REFRESH_PHONE_SIGNAL2";
    public static final String ACTION_REFRESH_PHONE_SIGNAL_DATA_LEVEL = "com.choiceway.eventcenter.EventUtils.ACTION_REFRESH_PHONE_SIGNAL_DATA_LEVEL";
    public static final String ACTION_REFRESH_PHONE_SIGNAL_DATA_STATE = "com.choiceway.eventcenter.EventUtils.ACTION_REFRESH_PHONE_SIGNAL_DATA_STATE";
    public static final String ACTION_REFRESH_PHONE_SIGNAL_DATA_TYPE = "com.choiceway.eventcenter.EventUtils.ACTION_REFRESH_PHONE_SIGNAL_DATA_TYPE";
    public static final String ACTION_SWITCH_ORIGINACAR = "com.szchoiceway.eventcenter.EventUtils.ACTION_SWITCH_ORIGINACAR";
    public static final String ACTION_VOICE_CTRL = "com.szchoiceway.ACTION_VOICE_CTRL";
    public static final String ANXIAO_MODE_PACKAGE_NAME = "com.atelectronic.atavm3d";
    public static final String APK_INSTALL_MODE_CLASS_NAME = "com.szchoiceway.apkinstall.MainActivity";
    public static final String APK_INSTALL_MODE_PACKAGE_NAME = "com.szchoiceway.apkinstall";
    public static final String BNRCD_MODE_CLASS_NAME = "com.szchoiceway.Mazida.BNRMazidaCDActivity";
    public static final String BNRCD_MODE_PACKAGE_NAME = "com.szchoiceway.VScanbus";
    public static final String BTMUSIC_MODE_CLASS_NAME = "com.szchoiceway.btsuite.BTMusicActivity";
    public static final String BT_CallRecordKey = "CallRecordPage";
    public static final String BT_DialPageKey = "DialPage";
    public static final String BT_MODE_CLASS_NAME = "com.szchoiceway.btsuite.BTMainActivity";
    public static final String BT_MODE_PACKAGE_NAME = "com.szchoiceway.btsuite";
    public static final String BT_MusicPageKey = "BTMusic";
    public static final String BT_PhoneBookPageKey = "PhoneBookPage";
    public static final String BT_SetPageKey = "SetPage";
    public static final String BT_TransmitSetPageKey = "TransmitSetPage";
    public static final String CANBUS_MODE_CLASS_NAME = "com.szchoiceway.VScanbus.CanMainActivity";
    public static final String CANBUS_MODE_PACKAGE_NAME = "com.szchoiceway.VScanbus";
    public static final String CARLINK_MODE_CLASS_NAME = "com.ucarhu.carlink.UCarDemoActivity";
    public static final String CARLINK_MODE_PACKAGE_NAME = "com.ucarhu.carlink";
    public static final String CAR_AIR_DATA = "EventUtils.CAR_AIR_DATA";
    public static final String DSP_MODE_CLASS_NAME = "com.choiceway.dsp.MainActivity";
    public static final String DSP_MODE_PACKAGE_NAME = "com.choiceway.dsp";
    public static final String EQUALIZER_CLASS_NAME = "com.szchoiceway.equalizer.MainActivity";
    public static final String EQUALIZER_PACKAGE_NAME = "com.szchoiceway.equalizer";
    public static final String ESTRONGS_MODE_CLASS_NAME = "com.estrongs.android.pop.view.FileExplorerActivity";
    public static final String ESTRONGS_MODE_PACKAGE_NAME = "com.estrongs.android.pop";
    public static final String ESUPER_MODE_CLASS_NAME = "com.frames.filemanager.module.activity.FirstActivity";
    public static final String ESUPER_MODE_PACKAGE_NAME = "com.es.file.explorer.manager";
    public static final int EVENT_KEY_EVENT = 4098;
    public static final int EVENT_MODE_CHANGE = 4097;
    public static final String EVENT_PACKAGE_NAME = "com.szchoiceway.eventcenter";
    public static final String EVENT_SERVICE_NAME = "com.szchoiceway.eventcenter.EventService";
    public static final int EVENT_START = 4096;
    public static final String EXPLORER_MODE_CLASS_NAME = "com.google.android.apps.chrome.Main";
    public static final String EXPLORER_MODE_CLASS_NAME2 = "com.google.android.apps.chrome.Main";
    public static final String EXPLORER_MODE_PACKAGE_NAME = "com.android.chrome";
    public static final String EXPLORER_MODE_PACKAGE_NAME2 = "com.android.chrome";
    public static final String EXTRA_VOICE_KEY_WORD = "VoiceKeyWord";
    public static final String EXTRA_VOICE_PARAM = "VoiceParam";
    public static final String GOOGLE_MAP_MODE_CLASS_NAME = "com.google.android.maps.MapsActivity";
    public static final String GOOGLE_MAP_MODE_PACKAGE_NAME = "com.google.android.apps.maps";
    public static final int HANDLER_MENU_TOUCH_POS = 252;
    public static final String HBCP_EVT_CONTACT_NUM = "com.szchoiceway.btsuite.HBCP_EVT_CONTACT_NUM";
    public static final String HBCP_EVT_CUR_CONNECTED_DEVICE_NAME = "com.szchoiceway.btsuite.HBCP_EVT_CUR_CONNECTED_DEVICE_NAME";
    public static final String HBCP_EVT_HSHF_STATUS = "com.szchoiceway.btsuite.HBCP_EVT_HSHF_STATUS";
    public static final int HBCP_STATUS_HSHF_ACTIVE_CALL = 6;
    public static final int HBCP_STATUS_HSHF_CONNECTED = 3;
    public static final int HBCP_STATUS_HSHF_CONNECTING = 2;
    public static final int HBCP_STATUS_HSHF_INCOMING_CALL = 5;
    public static final int HBCP_STATUS_HSHF_INITIALISING = 0;
    public static final int HBCP_STATUS_HSHF_OUTGOING_CALL = 4;
    public static final int HBCP_STATUS_HSHF_READY = 1;
    public static final String HICAR_MODE_CLASS_NAME = "com.huawei.hicar.app.HiCarActivity";
    public static final String HICAR_MODE_PACKAGE_NAME = "com.huawei.hicar";
    public static final String INTENT_EXTRA_INT_KEYNAME = "com.szchoiceway.btsuite.DATA_INT";
    public static final String INTENT_EXTRA_STR_KEYNAME = "com.szchoiceway.btsuite.DATA_STR";
    public static final String KEY_BRIGHTNESS_SETTINGS = "COM.SZCHOICEWAY_BRIGHTNESS_SETTINGS";
    public static final String KSW_ZXW_BT_CONNECED_SHOW_VIEW = "com.choiceway.eventcenter.EventUtils.KSW_ZXW_BT_CONNECED_SHOW_VIEW";
    public static final String KSW_ZXW_BT_CONNECED_SHOW_VIEW_DATA = "com.choiceway.eventcenter.EventUtils.KSW_ZXW_BT_CONNECED_SHOW_VIEW_DATA";
    public static final String KUWO_MODE_CLASS_NAME = "cn.kuwo.kwmusiccar.MainActivity";
    public static final String KUWO_MODE_PACKAGE_NAME = "cn.kuwo.kwmusiccar";
    public static final String LAUNCHER_IS_IN_MULTIWINDOWMODE = "com.szchoiceway.action.isInMultiWindowMode";
    public static final String LAUNCHER_ON_CONFIGURATION_CHANGE = "com.szchoiceway.action.LAUNCHER_ON_CONFIGURATION_CHANGE";
    public static final String LEFT_APP0_MODE_CLASS_NAME = "LEFT_APP0_MODE_CLASS_NAME";
    public static final String LEFT_APP0_MODE_PACKAGE_NAME = "LEFT_APP0_MODE_PACKAGE_NAME";
    public static final String LEFT_APP1_MODE_CLASS_NAME = "LEFT_APP1_MODE_CLASS_NAME";
    public static final String LEFT_APP1_MODE_PACKAGE_NAME = "LEFT_APP1_MODE_PACKAGE_NAME";
    public static final String LEFT_APP2_MODE_CLASS_NAME = "LEFT_APP2_MODE_CLASS_NAME";
    public static final String LEFT_APP2_MODE_PACKAGE_NAME = "LEFT_APP2_MODE_PACKAGE_NAME";
    public static final String LETTER_AIRPLAY_MODE_CLASS_NAME = "com.carletter.car.ui.AirplayMediaActivity";
    public static final String LETTER_AUTO_MODE_CLASS_NAME = "com.carletter.car.ui.H264MediaActivity";
    public static final int LETTER_CARPLAY_MODE_AIRPLAY = 1;
    public static final int LETTER_CARPLAY_MODE_ANDROID_AUTO = 3;
    public static final int LETTER_CARPLAY_MODE_ANDROID_LINK = 4;
    public static final int LETTER_CARPLAY_MODE_CARLIFE = 5;
    public static final int LETTER_CARPLAY_MODE_CARPLAY = 2;
    public static final String LETTER_CARPLAY_MODE_CLASS_NAME = "com.carletter.car.ui.CarletterActivity";
    public static final int LETTER_CARPLAY_MODE_DLNA = 6;
    public static final int LETTER_CARPLAY_MODE_HICAR = 7;
    public static final String LETTER_CARPLAY_MODE_PACKAGE_NAME = "com.carletter.car";
    public static final int LETTER_CARPLAY_MODE_UNKNOWN = 0;
    public static final String LETTER_DLNA_MODE_CLASS_NAME = "com.carletter.car.ui.StreamMediaActivity";
    public static final String LIDIAN_MODE_PACKAGE_NAME = "com.fibocom.multicamera";
    public static final String LIDIAN_MODE_SERVICE_NAME = "com.megaview.avm.service.AvmService";
    public static final String MCU_3DH_INFO = "com.szchoiceway.eventcenter.EventUtils.MCU_3DH_INFO";
    public static final byte MCU_KEY1_12 = 109;
    public static final byte MCU_KEY1_14 = 110;
    public static final byte MCU_KEY1_2 = 103;
    public static final byte MCU_KEY1_3 = 104;
    public static final byte MCU_KEY1_4 = 105;
    public static final byte MCU_KEY1_5 = 108;
    public static final byte MCU_KEY2_1 = 106;
    public static final byte MCU_KEY2_10 = 107;
    public static final byte MCU_KEY_AB = 86;
    public static final byte MCU_KEY_AC = 91;
    public static final byte MCU_KEY_AIR_MODE = 106;
    public static final byte MCU_KEY_AIR_SW = 95;
    public static final byte MCU_KEY_AMS = 12;
    public static final byte MCU_KEY_ANGLE = 31;
    public static final byte MCU_KEY_APPLIST = -7;
    public static final byte MCU_KEY_APS = 13;
    public static final byte MCU_KEY_AUTO = 94;
    public static final byte MCU_KEY_BEEP = -15;
    public static final byte MCU_KEY_BLACK = 21;
    public static final byte MCU_KEY_BLOW_FONT = 92;
    public static final byte MCU_KEY_BLOW_REVERSE = 93;
    public static final byte MCU_KEY_BND = 14;
    public static final byte MCU_KEY_BT = 59;
    public static final byte MCU_KEY_CALIBRATE = -17;
    public static final byte MCU_KEY_CAR_MEDIA = 115;
    public static final byte MCU_KEY_CLEAR = 83;
    public static final byte MCU_KEY_CLOCK = 73;
    public static final byte MCU_KEY_CLOSE_SCREEN = -5;
    public static final byte MCU_KEY_CMMB = 62;
    public static final byte MCU_KEY_DIM = -10;
    public static final byte MCU_KEY_DISP = 10;
    public static final byte MCU_KEY_DISPLAY = 32;
    public static final byte MCU_KEY_DOWN = 26;
    public static final byte MCU_KEY_DUAL = 88;
    public static final byte MCU_KEY_DVD = 57;
    public static final byte MCU_KEY_DVD_MENU = 84;
    public static final byte MCU_KEY_EJECT = 15;
    public static final byte MCU_KEY_ENTER = 28;
    public static final byte MCU_KEY_EQ = 51;
    public static final int MCU_KEY_EXIT = 511;
    public static final byte MCU_KEY_FAN_ADD = 99;
    public static final byte MCU_KEY_FAN_SUB = 98;
    public static final byte MCU_KEY_FF = 7;
    public static final byte MCU_KEY_FORCE_EJECT = 56;
    public static final byte MCU_KEY_F_CAM = 102;
    public static final byte MCU_KEY_GOTO = 47;
    public static final byte MCU_KEY_HANGUP = 22;
    public static final byte MCU_KEY_IDLE = -8;
    public static final byte MCU_KEY_LEFT = 24;
    public static final byte MCU_KEY_LEFT_RIGHT_BACK_CAM_LONG = 117;
    public static final byte MCU_KEY_LEFT_TEMP_ADD = 100;
    public static final byte MCU_KEY_LEFT_TEMP_SUB = 101;
    public static final byte MCU_KEY_LOCDX = 48;
    public static final byte MCU_KEY_LOUDNESS = 82;
    public static final byte MCU_KEY_L_R = 50;
    public static final byte MCU_KEY_MAX_AC = 90;
    public static final byte MCU_KEY_MENU = 9;
    public static final byte MCU_KEY_MIDDLE_CONTROL = 114;
    public static final byte MCU_KEY_MODE = 16;
    public static final byte MCU_KEY_MOVIE = -13;
    public static final byte MCU_KEY_MP5 = 60;
    public static final byte MCU_KEY_MUSIC = -14;
    public static final byte MCU_KEY_MUTE = 17;
    public static final byte MCU_KEY_NAV = 55;
    public static final byte MCU_KEY_NEXT = 2;
    public static final byte MCU_KEY_NONE = 0;
    public static final byte MCU_KEY_NP = -12;
    public static final byte MCU_KEY_NUM0 = 36;
    public static final byte MCU_KEY_NUM1 = 37;
    public static final byte MCU_KEY_NUM10 = 46;
    public static final byte MCU_KEY_NUM1_LONG = 64;
    public static final byte MCU_KEY_NUM2 = 38;
    public static final byte MCU_KEY_NUM2_LONG = 65;
    public static final byte MCU_KEY_NUM3 = 39;
    public static final byte MCU_KEY_NUM3_LONG = 66;
    public static final byte MCU_KEY_NUM4 = 40;
    public static final byte MCU_KEY_NUM4_LONG = 67;
    public static final byte MCU_KEY_NUM5 = 41;
    public static final byte MCU_KEY_NUM5_LONG = 68;
    public static final byte MCU_KEY_NUM6 = 42;
    public static final byte MCU_KEY_NUM6_LONG = 69;
    public static final byte MCU_KEY_NUM7 = 43;
    public static final byte MCU_KEY_NUM8 = 44;
    public static final byte MCU_KEY_NUM9 = 45;
    public static final byte MCU_KEY_PHONELINK = -6;
    public static final byte MCU_KEY_PIP = -16;
    public static final byte MCU_KEY_PLAY = 4;
    public static final byte MCU_KEY_PLAYPAUSE = 6;
    public static final byte MCU_KEY_POWER = 1;
    public static final byte MCU_KEY_PREV = 3;
    public static final byte MCU_KEY_PROG = 72;
    public static final byte MCU_KEY_RADIO = 54;
    public static final byte MCU_KEY_RADIO_NEXT = 70;
    public static final byte MCU_KEY_RADIO_PREV = 71;
    public static final byte MCU_KEY_RANDOM = 30;
    public static final byte MCU_KEY_REP = 11;
    public static final byte MCU_KEY_REPEAT = 29;
    public static final byte MCU_KEY_RETURN = 85;
    public static final byte MCU_KEY_RF = 8;
    public static final byte MCU_KEY_RIGHT = 27;
    public static final byte MCU_KEY_RIGHT_CAM = 112;
    public static final byte MCU_KEY_RIGHT_CAM_LONG = 113;
    public static final byte MCU_KEY_RIGHT_TEMP_ADD = 96;
    public static final byte MCU_KEY_RIGHT_TEMP_SUB = 97;
    public static final byte MCU_KEY_SEARCH = 87;
    public static final byte MCU_KEY_SEL = 58;
    public static final byte MCU_KEY_SETUP = 20;
    public static final byte MCU_KEY_SLOW = 53;
    public static final byte MCU_KEY_SOUNDLANG = 34;
    public static final byte MCU_KEY_STANDBY = -9;
    public static final byte MCU_KEY_STMONO = 49;
    public static final byte MCU_KEY_STOP = 5;
    public static final byte MCU_KEY_SUBT = 33;
    public static final byte MCU_KEY_SYS_ESC = 78;
    public static final byte MCU_KEY_SYS_HOME = 76;
    public static final byte MCU_KEY_SYS_MENU = 77;
    public static final byte MCU_KEY_SYS_WINCE = 79;
    public static final byte MCU_KEY_TAB = 89;
    public static final byte MCU_KEY_TALK = 23;
    public static final byte MCU_KEY_TFT_CLOSE = 75;
    public static final byte MCU_KEY_TFT_LONG_CLOSE = 81;
    public static final byte MCU_KEY_TFT_LONG_OPEN = 80;
    public static final byte MCU_KEY_TFT_OPEN = 74;
    public static final byte MCU_KEY_TITLE = 52;
    public static final byte MCU_KEY_TOUCH = 63;
    public static final byte MCU_KEY_TOUCH_CAL = -11;
    public static final byte MCU_KEY_TV = 61;
    public static final byte MCU_KEY_UP = 25;
    public static final byte MCU_KEY_VOL_ADD = 18;
    public static final byte MCU_KEY_VOL_SHOW = -24;
    public static final byte MCU_KEY_VOL_SUB = 19;
    public static final byte MCU_KEY_WAKE_UP = -57;
    public static final byte MCU_KEY_ZOOM = 35;
    public static final String MOVIE_MODE_CLASS_NAME = "com.szchoiceway.videoplayer.MainActivity";
    public static final String MOVIE_MODE_PACKAGE_NAME = "com.szchoiceway.videoplayer";
    public static final int MSG_CHECK_ACTIVITY = 1024;
    public static final String MUSIC_MODE_CLASS_NAME = "com.szchoiceway.musicplayer.MainActivity";
    public static final String MUSIC_MODE_PACKAGE_NAME = "com.szchoiceway.musicplayer";
    public static final String MZD_4_ICON = "com.choiceway.eventcenter.CanUtils.MZD_4_ICON";
    public static final String MZD_4_ICON_dianchidianya = "com.choiceway.eventcenter.CanUtils.MZD_4_ICON_dianchidianya";
    public static final String MZD_4_ICON_fadongjishuiwen = "com.choiceway.eventcenter.CanUtils.MZD_4_ICON_fadongjishuiwen";
    public static final String MZD_4_ICON_fanghua = "com.choiceway.eventcenter.CanUtils.MZD_4_ICON_fanghua";
    public static final String MZD_4_ICON_fujiashi = "com.choiceway.eventcenter.CanUtils.MZD_4_ICON_fujiashi";
    public static final String MZD_4_ICON_i_stop = "com.choiceway.eventcenter.CanUtils.MZD_4_ICON_i_stop";
    public static final String MZD_4_ICON_shousha = "com.choiceway.eventcenter.CanUtils.MZD_4_ICON_shousha";
    public static final String MZD_4_ICON_xiaodengzhuangtai = "com.choiceway.eventcenter.CanUtils.MZD_4_ICON_xiaodengzhuangtai";
    public static final String MZD_4_ICON_zhujiashi = "com.choiceway.eventcenter.CanUtils.MZD_4_ICON_zhujiashi";
    public static final String MZD_Launcher_Chesu = "com.choiceway.eventcenter.CanUtils.MZD_Launcher_Chesu";
    public static final String MZD_Launcher_Chesu_EXTRA = "com.choiceway.eventcenter.CanUtils.MZD_Launcher_Chesu_EXTRA";
    public static final String MZD_Launcher_Zhuansu = "com.choiceway.eventcenter.CanUtils.MZD_Launcher_Zhuansu";
    public static final String MZD_Launcher_Zhuansu_EXTRA = "com.choiceway.eventcenter.CanUtils.MZD_Launcher_Zhuansu_EXTRA";
    public static final String NAV_MODE_CLASS_NAME = "com.szchoiceway.navigation.MainActivity";
    public static final String NAV_MODE_PACKAGE_NAME = "com.szchoiceway.navigation";
    public static final String NETFLIX_MODE_CLASS_NAME = "com.netflix.mediaclient.ui.launch.UIWebViewActivity";
    public static final String NETFLIX_MODE_PACKAGE_NAME = "com.netflix.mediaclient";
    public static final String NOTIFY_WORKSPACE_PLAY_STATUS = "com.choiceway.eventcenter.EventUtils.NOTIFY_WORKSPACE_PLAY_STATUS";
    public static final String PHONEAPP_MODE_CLASS_NAME = "net.easyconn.WelcomeActivity";
    public static final String PHONEAPP_MODE_CLASS_NAME2 = "net.easyconn.ui.Zxw11MainActivity";
    public static final String PHONEAPP_MODE_PACKAGE_NAME = "net.easyconn";
    public static final String PLAY_STATUS_DATA = "EventUtils.PLAY_STATUS_DATA";
    public static final String RADIO_MODE_CLASS_NAME = "com.szchoiceway.radio.MainActivity";
    public static final String RADIO_MODE_PACKAGE_NAME = "com.szchoiceway.radio";
    public static final String REC_AUTONAVI_STANDARD = "AUTONAVI_STANDARD_BROADCAST_SEND";
    public static final String SET_CLIENT_SET = "SET_CLIENT_SET";
    public static final String SET_CLIENT_SET_EXTRA = "SET_CLIENT_SET_EXTRA";
    public static final String SET_DAY_NIGHT_MODE = "SET_DAY_NIGHT_MODE";
    public static final String SET_DAY_NIGHT_MODE_EXTRA = "SET_DAY_NIGHT_MODE_EXTRA";
    public static final String SET_MODE_CLASS_NAME = "com.szchoiceway.settings.MainActivity";
    public static final String SET_MODE_PACKAGE_NAME = "com.szchoiceway.settings";
    public static final String SET_MODE_SET = "SET_MODE_SET";
    public static final String SET_MODE_SET_EXTRA = "SET_MODE_SET_EXTRA";
    public static final String SET_RESOLUTION_SET = "SET_RESOLUTION_SET";
    public static final String SET_RESOLUTION_SET_EXTRA = "SET_RESOLUTION_SET_EXTRA";
    public static final String SHOW_APPLIST = "com.szchoiceway.customerui.showapplist";
    public static final String SPOTIFY_MODE_CLASS_NAME = "com.spotify.music.MainActivity";
    public static final String SPOTIFY_MODE_PACKAGE_NAME = "com.spotify.music";
    public static final int STORAGE_TYPE_BOTH = 3;
    public static final int STORAGE_TYPE_NULL = 0;
    public static final int STORAGE_TYPE_SD = 2;
    public static final int STORAGE_TYPE_U_DISK = 1;
    public static final String SYS_DAY_NIGHT_MODE = "Sys_Day_Night_Mode";
    public static final String TAG = "EventUtils";
    public static final String TXZ_TRIGGERRECORD_BUTTON_KEYWORDS = "com.txznet.triggerrecordbuttonKeywords";
    public static final String TXZ_TRIGGERRECORD_BUTTON_KEYWORDS2 = "txz.intent.action.smartwakeup.triggerRecordButton";
    public static final String TXZ_WEATHER_CLASS_NAME = "com.txznet.weather.MainActivity";
    public static final String TXZ_WEATHER_PACKAGE_NAME = "com.txznet.weather";
    public static final int TXZ_WEATHER_RESULT_NO_GPS = 5;
    public static final int TXZ_WEATHER_RESULT_QUERY_FAILED = 3;
    public static final int TXZ_WEATHER_RESULT_SUCCESS = 0;
    public static final int TXZ_WEATHER_RESULT_UNACTIVATED = 4;
    public static final String VALID_MODE_INFOR_CHANGE = "com.szchoiceway.eventcenter.EventUtils.VALID_MODE_INFOR_CHANGE";
    public static final String WEATHER_MODE_CLASS_NAME = "com.txznet.weather.MainActivity";
    public static final String WEATHER_MODE_PACKAGE_NAME = "com.txznet.weather";
    public static final String XYC360_MODE_PACKAGE_NAME = "com.ivicar.avm";
    public static final String YOUTUBE_MODE_CLASS_NAME = "com.google.android.youtube.app.honeycomb.Shell$HomeActivity";
    public static final String YOUTUBE_MODE_PACKAGE_NAME = "com.google.android.youtube";
    public static final String ZLINK_CARPLAY_MODE_CLASS_NAME = "com.zjinnova.android.zlink.features.main.MainActivity";
    public static final int ZLINK_CONNECT_MODE_ANDROID_AUTO = 1;
    public static final int ZLINK_CONNECT_MODE_CARLINK = 2;
    public static final int ZLINK_CONNECT_MODE_CARPLAY = 0;
    public static final int ZLINK_CONNECT_MODE_DLNA = 4;
    public static final int ZLINK_CONNECT_MODE_HICAR = 5;
    public static final int ZLINK_CONNECT_MODE_MIRROR_AIRPLAY = 3;
    public static final int ZLINK_CONNECT_MODE_NULL = -1;
    public static final String ZLINK_DLNA_MODE_CLASS_NAME = "com.zjinnova.android.zlink.features.dlna.DlnaActivity";
    public static final String ZLINK_MODE_PACKAGE_NAME = "com.zjinnova.zlink";
    public static final String ZXW_ACTION_BT_CALL_TIME = "com.szchoiceway.btsuite.ZXW_ACTION_BT_CALL_TIME";
    public static final String ZXW_ACTION_BT_CALL_TIME_EXTRA = "com.szchoiceway.btsuite.ZXW_ACTION_BT_CALL_TIME_EXTRA";
    public static final String ZXW_ACTION_BT_PHONE_CALL_INTERFACE = "ZXW_ACTION_BT_PHONE_CALL_INTERFACE";
    public static final String ZXW_ACTION_BT_PHONE_CALL_INTERFACE_EXTRA = "ZXW_ACTION_BT_PHONE_CALL_INTERFACE_EXTRA";
    public static final String ZXW_ACTION_CHANGE_BRIGHTNESS_EXTRA = "ZXW_ACTION_CHANGE_BRIGHTNESS_EXTRA";
    public static final String ZXW_ACTION_CHANGE_BRIGHTNESS_SETTINGS = "ZXW_ACTION_CHANGE_BRIGHTNESS_SETTINGS";
    public static final String ZXW_ACTION_CHANGE_BRIGHTNESS_SYSTEM = "ZXW_ACTION_CHANGE_BRIGHTNESS_SYSTEM";
    public static final String ZXW_ACTION_KSW_THEME_CHANGE = "ZXW_ACTION_KSW_THEME_CHANGE";
    public static final String ZXW_ACTION_KSW_UPDRADE_FAST_CHARGING_STATUS = "ZXW_ACTION_KSW_UPDRADE_FAST_CHARGING_STATUS";
    public static final String ZXW_ACTION_MAIN_TOP_ON_CLICK = "ZXW_ACTION_MAIN_TOP_ON_CLICK";
    public static final String ZXW_ACTION_MAIN_TOP_ON_CLICK_DATA = "ZXW_ACTION_MAIN_TOP_ON_CLICK_DATA";
    public static final String ZXW_ACTION_MAIN_TOP_ON_CLICK_KEY = "ZXW_ACTION_MAIN_TOP_ON_CLICK_KEY";
    public static final String ZXW_ACTION_MAIN_TOP_REFRESH_VIEW = "ZXW_ACTION_MAIN_TOP_REFRESH_VIEW";
    public static final String ZXW_ACTION_MAIN_TOP_REFRESH_VIEW_DATA = "ZXW_ACTION_MAIN_TOP_REFRESH_VIEW_DATA";
    public static final String ZXW_ACTION_MAIN_TOP_REFRESH_VIEW_DATA2 = "ZXW_ACTION_MAIN_TOP_REFRESH_VIEW_DATA2";
    public static final String ZXW_ACTION_MAIN_TOP_REFRESH_VIEW_KEY = "ZXW_ACTION_MAIN_TOP_REFRESH_VIEW_KEY";
    public static final String ZXW_ACTION_MAIN_TOP_REFRESH_VIEW_REQUEST = "ZXW_ACTION_MAIN_TOP_REFRESH_VIEW_REQUEST";
    public static final String ZXW_ACTION_MAIN_TOP_REFRESH_VIEW_REQUEST_KEY = "ZXW_ACTION_MAIN_TOP_REFRESH_VIEW_REQUEST_KEY";
    public static final String ZXW_ACTION_MUSIC_BAR = "com.choiceway.eventcenter.ZXW_ACTION_MUSIC_BAR";
    public static final String ZXW_ACTION_MUSIC_BAR_EXTRA = "com.choiceway.eventcenter.ZXW_ACTION_MUSIC_BAR_EXTRA";
    public static final String ZXW_ACTION_NOTIFICATION_CLICK_MUTE = "ZXW_ACTION_NOTIFICATION_CLICK_MUTE";
    public static final String ZXW_ACTION_NOTIIFY_DASHBOARD_MEDIA_PLAY_PATH = "com.szchoiceway.eventcenter.EventUtils.ZXW_ACTION_NOTIIFY_DASHBOARD_MEDIA_PLAY_PATH";
    public static final String ZXW_ACTION_NOTIIFY_MEDIA_PLAY_PATH = "com.szchoiceway.eventcenter.EventUtils.ZXW_ACTION_NOTIIFY_MEDIA_PLAY_PATH";
    public static final String ZXW_ACTION_NOTIIFY_MEDIA_PLAY_PATH_EXTRA = "com.szchoiceway.eventcenter.EventUtils.ZXW_ACTION_NOTIIFY_MEDIA_PLAY_PATH_EXTRA";
    public static final String ZXW_ACTION_NOTIIFY_MEDIA_TYPE = "com.szchoiceway.eventcenter.EventUtils.ZXW_ACTION_NOTIIFY_MEDIA_TYPE";
    public static final String ZXW_ACTION_NOTIIFY_MEDIA_TYPE_EXTRA = "com.szchoiceway.eventcenter.EventUtils.ZXW_ACTION_NOTIIFY_MEDIA_TYPE_EXTRA";
    public static final String ZXW_ACTION_NOTIIFY_VIDEO_PLAY_PATH = "com.szchoiceway.eventcenter.EventUtils.ZXW_ACTION_NOTIIFY_VIDEO_PLAY_PATH";
    public static final String ZXW_ACTION_NOTIIFY_VIDEO_PLAY_PATH_EXTRA = "com.szchoiceway.eventcenter.EventUtils.ZXW_ACTION_NOTIIFY_VIDEO_PLAY_PATH_EXTRA";
    public static final String ZXW_ACTION_PEMP_ID7_LEFT_APP_REFRESH = "ZXW_ACTION_PEMP_ID7_LEFT_APP_REFRESH";
    public static final String ZXW_ACTION_PEMP_ID7_LEFT_APP_REFRESH_EXTRA = "ZXW_ACTION_PEMP_ID7_LEFT_APP_REFRESH_EXTRA";
    public static final String ZXW_ACTION_REFRESH_AFTER_LOCALE_CHANGED = "ZXW_ACTION_REFRESH_AFTER_ACTION_LOCALE_CHANGED";
    public static final String ZXW_ACTION_REQUEST_BT_STATUS = "ZXW_ACTION_REQUEST_BT_STATUS";
    public static final String ZXW_ACTION_REQUEST_MUSIC_COVER = "ZXW_ACTION_REQUEST_MUSIC_COVER";
    public static final String ZXW_ACTION_SWITCH_LANDROVER_BACKGROUND = "ZXW_ACTION_SWITCH_LANDROVER_BACKGROUND";
    public static final String ZXW_ACTION_SWITCH_LANDROVER_BACKGROUND_INDEX = "ZXW_ACTION_SWITCH_LANDROVER_BACKGROUND_INDEX";
    public static final String ZXW_ACTION_SYS_BRIGHTNESS_SETTINGS = "com.szchoiceway.action.brightness_change";
    public static final String ZXW_ACTION_SYS_BRIGHTNESS_SETTINGS_EXTRA = "com.szchoiceway.action.brightness_change_extra";
    public static final String ZXW_ACTION_SYS_MODE_CHANGE_EVT = "com.szchoiceway.action.mode_change";
    public static final String ZXW_ACTION_SYS_MODE_CHANGE_EXTRA_EVT = "com.szchoiceway.action.mode_change_extra";
    public static final String ZXW_ACTION_SYS_MODE_TITLE_CHANGE_EVT = "com.szchoiceway.action.mode_title_change";
    public static final String ZXW_ACTION_SYS_MODE_TITLE_CHANGE_EXTRA_EVT = "com.szchoiceway.action.mode_title_change_extra";
    public static final String ZXW_ACTION_SYS_MODE_TITLE_CHANGE_EXTRA_SCREEN_EVT = "com.szchoiceway.action.mode_title_change_screen_extra";
    public static final String ZXW_ACTION_SYS_UPDATE_WEATHER = "ZXW_ACTION_SYS_UPDATE_WEATHER";
    public static final String ZXW_ACTION_UPDATE_BENZ_CONTROL_DATA_RECEIVE = "ZXW_ACTION_UPDATE_BENZ_CONTROL_DATA_RECEIVE";
    public static final String ZXW_ACTION_UPDATE_BENZ_CONTROL_DATA_SEND = "ZXW_ACTION_UPDATE_BENZ_CONTROL_DATA_SEND";
    public static final String ZXW_ACTION_UPDATE_GPS_TIME = "EventUtils.ZXW_ACTION_UPDATE_GPS_TIME";
    public static final String ZXW_CAN_KEY_EVT = "com.choiceway.eventcenter.EventUtils.ZXW_CAN_KEY_EVT";
    public static final String ZXW_CAN_KEY_EVT_EXTRA = "com.choiceway.eventcenter.EventUtils.ZXW_CAN_KEY_EVT_EXTRA";
    public static final String ZXW_CAN_START_UP_APPS = "com.szchoiceway.eventcenter.EventUtils.ZXW_CAN_START_UP_APPS";
    public static final String ZXW_DATABASE_NAVI_LIST_FILENAME = "com.szchoiceway.eventcenter.navigation";
    public static final int ZXW_ORIGINAL_MCU_KEY_DOWN = 4;
    public static final int ZXW_ORIGINAL_MCU_KEY_ENTER = 5;
    public static final String ZXW_ORIGINAL_MCU_KEY_FOCUS_MOVE_DATA = "com.choiceway.eventcenter.EventUtils.ZXW_ORIGINAL_MCU_KEY_FOCUS_MOVE_DATA";
    public static final String ZXW_ORIGINAL_MCU_KEY_FOCUS_MOVE_EVT = "com.choiceway.eventcenter.EventUtils.ZXW_ORIGINAL_MCU_KEY_FOCUS_MOVE_EVT";
    public static final int ZXW_ORIGINAL_MCU_KEY_LEFT = 1;
    public static final int ZXW_ORIGINAL_MCU_KEY_LEFT_HANDED = 7;
    public static final int ZXW_ORIGINAL_MCU_KEY_RIGHT = 2;
    public static final int ZXW_ORIGINAL_MCU_KEY_RIGHT_HANDED = 8;
    public static final int ZXW_ORIGINAL_MCU_KEY_UP = 3;
    public static final String ZXW_SENDBROADCAST8902MOD = "com.szchoiceway.eventcenter.EventUtils.ZXW_SENDBROADCAST8902MOD";
    public static final String ZXW_SENDBROADCAST8902MOD_EXTRA = "com.szchoiceway.eventcenter.EventUtils.ZXW_SENDBROADCAST8902MOD_EXTRA";
    public static final String ZXW_SENDBROADCAST8902MOD_ShunShiSuDu = "com.szchoiceway.eventcenter.EventUtils.ZXW_SENDBROADCAST8902MOD_ShunShiSuDu";
    public static final String ZXW_SENDBROADCAST8902MOD_SpeedUnit = "com.szchoiceway.eventcenter.EventUtils.ZXW_SENDBROADCAST8902MOD_SpeedUnit";
    public static final String ZXW_SENDBROADCAST8902MOD_anquandai = "com.szchoiceway.eventcenter.EventUtils.ZXW_SENDBROADCAST8902MOD_anquandai";
    public static final String ZXW_SENDBROADCAST8902MOD_fadongjizhuansu = "com.szchoiceway.eventcenter.EventUtils.ZXW_SENDBROADCAST8902MOD_fadongjizhuansu";
    public static final String ZXW_SENDBROADCAST8902MOD_huanjinwendu = "com.szchoiceway.eventcenter.EventUtils.ZXW_SENDBROADCAST8902MOD_huanjinwendu";
    public static final String ZXW_SENDBROADCAST8902MOD_shousha = "com.szchoiceway.eventcenter.EventUtils.ZXW_SENDBROADCAST8902MOD_shousha";
    public static final String ZXW_SENDBROADCAST8902MOD_xushilicheng = "com.szchoiceway.eventcenter.EventUtils.ZXW_SENDBROADCAST8902MOD_xushilicheng";
    public static final String ZXW_SENDBROADCAST8902MOD_youLiang = "com.szchoiceway.eventcenter.EventUtils.ZXW_SENDBROADCAST8902MOD_youLiang";
    public static final String ZXW_SENDBROADCAST8902MOD_youhao = "com.szchoiceway.eventcenter.EventUtils.ZXW_SENDBROADCAST8902MOD_youhao";
    public static final String ZXW_SYS_EXTRA = "com.choiceway.eventcenter.EventUtils.ZXW_SYS_EXTRA";
    public static final String ZXW_SYS_KEY_EVT = "com.choiceway.eventcenter.EventUtils.ZXW_SYS_KEY";
    private static String lastStr = "";
    private static long lastTime = 0;
    private static Toast mTip;
    /* access modifiers changed from: private */
    public static final Map<Integer, eSrcMode> mValueList = new HashMap();
    public static String[] musicPkgLst = {"com.android.music", KUWO_MODE_PACKAGE_NAME, "cn.kuwo.player", "com.kugou.android", "com.kugou.playerHD", "com.sds.android.ttpod", MediaNotificationBroadcastReceiver.QQ_MUSIC_PAGENAME, "com.tencent.qqmusicpad", "com.tencent.qqmusiccar", "com.duomi.android", "com.android.browser", "ru.yandex.music", "com.duomi.android", "com.mxtech.videoplayer.pro", "com.maxmpz.audioplayer", "com.jetappfactory.jetaudioplus", "com.android.chrome", "com.ankai.cardvr", SPOTIFY_MODE_PACKAGE_NAME, YOUTUBE_MODE_PACKAGE_NAME, ZLINK_MODE_PACKAGE_NAME, "com.kugou.android.auto", HICAR_MODE_PACKAGE_NAME, "com.kugou.android.auto"};

    public static int BIT_OFF(int i, int i2) {
        return i & (~(1 << i2));
    }

    public static int BIT_ON(int i, int i2) {
        return i | (1 << i2);
    }

    public enum eSrcMode {
        SRC_NONE(0),
        SRC_RADIO(1),
        SRC_DVD(2),
        SRC_USB(3),
        SRC_CARD(4),
        SRC_IPOD(5),
        SRC_BT(6),
        SRC_BTMUSIC(7),
        SRC_CMMB(8),
        SRC_TV(9),
        SRC_MOVIE(10),
        SRC_MUSIC(11),
        SRC_EBOOK(12),
        SRC_IMAGE(13),
        SRC_ANDROID(14),
        SRC_VMCD(15),
        SRC_NETWORK(16),
        SRC_CARMEDIA(17),
        SRC_CAR_BT(18),
        SRC_DVR(19),
        SRC_MOBILE_APP(20),
        SRC_DSP_RADIO(21),
        SRC_ATSL_AIRCONSOLE(30),
        SRC_PHONELINK(31),
        SRC_CARPLAY(32),
        SRC_TXZ_WEBCHAT(37),
        SRC_TXZ_MUSIC(38),
        SRC_AUX(40),
        SRC_BACKCAR(41),
        SRC_GPS(42),
        SRC_HOME(43),
        SRC_REHOME(44),
        SRC_COMPASS(45),
        SRC_STANDBY(46),
        SRC_EQ(47),
        SRC_BACKLIGHT_SET(48),
        SRC_SETUP(49),
        SRC_FRONT_CAMERA(50),
        SRC_BCAM(51),
        SRC_LEFT_CAMERA(52),
        SRC_DVR2(53),
        SRC_RIGHT_CAMERA(54),
        SRC_THIRD_APP(55),
        SRC_MCU_VERSION(80),
        SRC_TFT_VERSION(81),
        SRC_NULL(99),
        SRC_POWERON(100),
        SRC_POWEROFF(101),
        SRC_IDLE_MODE(103),
        SRC_IDLE_REST(104),
        SRC_MUSIC_NAVI(105),
        SRC_PHONE_APP(106),
        SRC_QUICK_ACCESS_1(107),
        SRC_QUICK_ACCESS_2(108),
        SRC_Original_TO_ARM(109),
        SRC_BT_ONLY(GyroScopeWithCompassView.CARTYPE_Langdong_HI_2015),
        SRC_ES_FILE_EXPLORER(GyroScopeWithCompassView.CARTYPE_Langdong_LO_2015),
        SRC_APP_LIST(GyroScopeWithCompassView.CARTYPE_Langdong_LO),
        SRC_EXPLORER(GyroScopeWithCompassView.CARTYPE_kewozi_HI),
        SRC_FEEDBACK(GyroScopeWithCompassView.CARTYPE_Polo2016_LO),
        SRC_FATSET(200),
        SRC_SETTINGS_NAVIGATION(GyroScopeWithCompassView.CARTYPE_Polo2016_HI),
        SRC_SETTINGS_LANGUAGE(GyroScopeWithCompassView.CARTYPE_DAQIE2013_LO),
        SRC_SETTINGS_DATATIMER(GyroScopeWithCompassView.CARTYPE_DAQIE2013_HI),
        SRC_SETTINGS_VOLUME(GyroScopeWithCompassView.CARTYPE_MALIBU_H_H),
        SRC_SETTINGS_EQ(GyroScopeWithCompassView.CARTYPE_Kaluola_OLD_LO),
        SRC_SETTINGS_SYSTEM(GyroScopeWithCompassView.CARTYPE_Kaluola_OLD_HI),
        SRC_SETTINGS_FACTORY(GyroScopeWithCompassView.CARTYPE_JIANGLING_S330_LO),
        SRC_SETTINGS_SYSTEM_INFO(GyroScopeWithCompassView.CARTYPE_JIANGLING_S330_HI),
        SRC_BT_DIAL_PAGE(GyroScopeWithCompassView.CARTYPE_DAOQI_GOMGYANG_LO),
        SRC_BT_CALL_RECORD_PAGE(GyroScopeWithCompassView.CARTYPE_DAOQI_GONGYANG_HI),
        SRC_BT_PHONE_BOOK_PAGE(GyroScopeWithCompassView.CARTYPE_KOLEOS_LO_LO),
        SRC_BT_SET_PAGE(GyroScopeWithCompassView.CARTYPE_HavalH6_Coolpe),
        SRC_BT_MUSIC_PAGE(GyroScopeWithCompassView.CARTYPE_EDGE_2013_HI);
        
        private int value;

        private eSrcMode(int i) {
            this.value = i;
            EventUtils.mValueList.put(Integer.valueOf(i), this);
        }

        public byte getValue() {
            return (byte) (this.value & 255);
        }

        public int getIntValue() {
            return this.value;
        }

        public static eSrcMode valueOf(int i) {
            eSrcMode esrcmode = (eSrcMode) EventUtils.mValueList.get(Integer.valueOf(i));
            return esrcmode == null ? SRC_NONE : esrcmode;
        }

        public String toString() {
            return Integer.toString(this.value);
        }
    }

    public static String bytesToHexString(byte[] bArr) {
        StringBuilder sb = new StringBuilder("");
        if (bArr == null || bArr.length <= 0) {
            return null;
        }
        for (byte b : bArr) {
            String hexString = Integer.toHexString(b & UByte.MAX_VALUE);
            sb.append("0x");
            if (hexString.length() < 2) {
                sb.append(0);
            }
            sb.append(hexString + " ");
        }
        return sb.toString();
    }

    public static String bytesToHexString2(byte[] bArr) {
        StringBuilder sb = new StringBuilder("");
        if (bArr == null || bArr.length <= 0) {
            return null;
        }
        for (int i = 0; i < bArr.length; i++) {
            String hexString = Integer.toHexString(bArr[i] & UByte.MAX_VALUE);
            if (i == 0) {
                sb.append("0x");
            }
            if (hexString.length() < 2) {
                sb.append(0);
            }
            sb.append(hexString);
        }
        return sb.toString();
    }

    public static boolean isActivityRunning(Context context, Class<?> cls) {
        return cls.getName().equals(((ActivityManager) context.getSystemService("activity")).getRunningTasks(1).get(0).topActivity.getClassName());
    }

    public static void startActivityIfNotRunning(Context context, String str, String str2) {
        if (str2.contains("===repeat")) {
            str2 = str2.split("===repeat")[0];
        }
        Log.d(TAG, "onItemClick pkg = " + str + ", cla = " + str2);
        Log.d(TAG, "startActivity packageName = " + str + ", className = " + str2 + ", context = " + context);
        if (context != null && str != null && str2 != null) {
            List<ActivityManager.RunningTaskInfo> runningTasks = ((ActivityManager) context.getSystemService("activity")).getRunningTasks(1);
            if (runningTasks == null || runningTasks.size() <= 0) {
                Intent intent = new Intent("android.intent.action.MAIN");
                intent.addCategory("android.intent.category.LAUNCHER");
                intent.setComponent(new ComponentName(str, str2));
                intent.setFlags(270532608);
                try {
                    context.startActivity(intent);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                ComponentName componentName = runningTasks.get(0).topActivity;
                if (!componentName.getClassName().equals(str2) || !componentName.getPackageName().equals(str)) {
                    Intent intent2 = new Intent("android.intent.action.MAIN");
                    intent2.addCategory("android.intent.category.LAUNCHER");
                    intent2.setComponent(new ComponentName(str, str2));
                    intent2.setFlags(270532608);
                    try {
                        context.startActivity(intent2);
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                }
            }
        }
    }

    public static void setWifiMode(Context context, boolean z) {
        ((WifiManager) context.getSystemService("wifi")).setWifiEnabled(z);
    }

    public static void killProcess(String str) {
        try {
            if (Build.VERSION.SDK_INT <= 23) {
                Runtime.getRuntime().exec("su \n");
            }
            Runtime.getRuntime().exec("am force-stop " + str + " \n");
            Log.i(TAG, "closeApplication " + str + " by aios-adapter!!");
        } catch (IOException e) {
            e.printStackTrace();
            Log.i("TAG", "killProcess: e = " + e.toString());
        }
    }

    public static boolean getInstallStatus(Context context, String str) {
        PackageManager packageManager = context.getPackageManager();
        new ArrayList();
        List<PackageInfo> installedPackages = packageManager.getInstalledPackages(0);
        boolean z = false;
        for (int i = 0; i < installedPackages.size(); i++) {
            if (str.equals(installedPackages.get(i).packageName)) {
                z = true;
            }
        }
        return z;
    }

    public static String getTopPackageName(Context context) {
        List<ActivityManager.RunningTaskInfo> runningTasks = ((ActivityManager) context.getSystemService("activity")).getRunningTasks(1);
        return (runningTasks == null || runningTasks.size() <= 0) ? "" : runningTasks.get(0).topActivity.getPackageName();
    }

    public static void showTipString(Context context, int i) {
        showTipString(context, context.getString(i));
    }

    public static void showTipString(Context context, String str) {
        if (!lastStr.equals(str) || Math.abs(System.currentTimeMillis() - lastTime) > 2000) {
            Toast makeText = Toast.makeText(context, str, 0);
            mTip = makeText;
            makeText.setGravity(17, 0, 60);
            mTip.show();
            lastTime = System.currentTimeMillis();
            lastStr = str;
        }
    }

    public static void hideTipString() {
        Toast toast = mTip;
        if (toast != null) {
            toast.cancel();
            mTip = null;
        }
    }

    public static void kill3rdMusicAPK(Context context) {
        List<ActivityManager.RunningTaskInfo> runningTasks = ((ActivityManager) context.getSystemService("activity")).getRunningTasks(100);
        if (runningTasks != null && runningTasks.size() > 0) {
            for (int i = 1; i < runningTasks.size(); i++) {
                ComponentName componentName = runningTasks.get(i).topActivity;
                for (int i2 = 0; i2 < musicPkgLst.length; i2++) {
                    if (componentName.getPackageName().equals(musicPkgLst[i2])) {
                        Log.i(TAG, "Music APK: " + componentName.getPackageName());
                        killProcess(musicPkgLst[i2]);
                    }
                }
            }
        }
    }

    public static void showAppList(Context context) {
        context.sendBroadcast(new Intent("com.szchoiceway.action.SHOW_APP_LIST"));
    }

    public static void sendKeyDownUpSync(int i) {
        Log.i(TAG, "sendKeyDownUpSync: key = " + i);
        new Thread(new Runnable(i) {
            public final /* synthetic */ int f$0;

            {
                this.f$0 = r1;
            }

            public final void run() {
                EventUtils.lambda$sendKeyDownUpSync$0(this.f$0);
            }
        }).start();
    }

    static /* synthetic */ void lambda$sendKeyDownUpSync$0(int i) {
        sendKeySync(new KeyEvent(0, i));
        SystemClock.sleep(300);
        sendKeySync(new KeyEvent(1, i));
    }

    private static void sendKeySync(KeyEvent keyEvent) {
        long downTime = keyEvent.getDownTime();
        long eventTime = keyEvent.getEventTime();
        int action = keyEvent.getAction();
        int keyCode = keyEvent.getKeyCode();
        int repeatCount = keyEvent.getRepeatCount();
        int metaState = keyEvent.getMetaState();
        int deviceId = keyEvent.getDeviceId();
        int scanCode = keyEvent.getScanCode();
        int source = keyEvent.getSource();
        int flags = keyEvent.getFlags();
        if (source == 0) {
            source = 257;
        }
        int i = source;
        if (eventTime == 0) {
            eventTime = SystemClock.uptimeMillis();
        }
        if (downTime == 0) {
            downTime = eventTime;
        }
        KeyEvent keyEvent2 = r4;
        KeyEvent keyEvent3 = new KeyEvent(downTime, eventTime, action, keyCode, repeatCount, metaState, deviceId, scanCode, flags | 8, i);
        InputManager.getInstance().injectInputEvent(keyEvent2, 2);
    }

    public static String formatTime(int i) {
        int i2 = i / 60;
        if ((i2 / 60) / 1000 > 0) {
            long j = (long) ((i / TimeHelps.DELAY_MILLISECONDS_mm) % 60);
            long j2 = (long) ((i / TimeHelps.DELAY_MILLISECONDS_HH) % 24);
            return String.format(Locale.CHINA, "%02d:%02d:%02d", new Object[]{Long.valueOf(j2), Long.valueOf(j), Long.valueOf((long) ((i / 1000) % 60))});
        } else if (i2 / 1000 > 0) {
            long j3 = (long) ((i / TimeHelps.DELAY_MILLISECONDS_mm) % 60);
            return String.format(Locale.CHINA, "00:%02d:%02d", new Object[]{Long.valueOf(j3), Long.valueOf((long) ((i / 1000) % 60))});
        } else {
            long j4 = (long) ((i / 1000) % 60);
            return String.format(Locale.CHINA, "00:00:%02d", new Object[]{Long.valueOf(j4)});
        }
    }

    public static boolean isActivityRuning(Context context, String str, String str2) {
        if (!(context == null || str == null || str2 == null)) {
            ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
            activityManager.getRecentTasks(100, 0);
            ComponentName componentName = activityManager.getRunningTasks(1).get(0).topActivity;
            if (!componentName.getClassName().equals(str2) || !componentName.getPackageName().equals(str)) {
                Log.i(TAG, "isActivityRuning: false");
            } else {
                Log.i(TAG, "isActivityRuning: true");
                return true;
            }
        }
        return false;
    }

    public static void onEnterOriginalCar(Context context) {
        if (LauncherModel.getInstance().getUITypeVer() == 102) {
            Intent intent = new Intent("com.szchoiceway.zxwauto.ACTION_AUTOMODEDISPLAY");
            intent.putExtra("AndroidAutoModeState", 2);
            context.sendBroadcast(intent);
        }
    }

    public static boolean startActivityIfNotRunning2(Context context, String str, String str2) {
        if (context == null || str == null || str2 == null) {
            return false;
        }
        List<ActivityManager.RunningTaskInfo> runningTasks = ((ActivityManager) context.getSystemService("activity")).getRunningTasks(1);
        if (runningTasks == null || runningTasks.size() <= 0) {
            Intent intent = new Intent("android.intent.action.MAIN");
            intent.addCategory("android.intent.category.LAUNCHER");
            intent.setComponent(new ComponentName(str, str2));
            intent.setFlags(270532608);
            context.startActivity(intent);
        } else {
            ComponentName componentName = runningTasks.get(0).topActivity;
            if (componentName.getClassName().equals(str2) && componentName.getPackageName().equals(str)) {
                return false;
            }
            Intent intent2 = new Intent("android.intent.action.MAIN");
            intent2.addCategory("android.intent.category.LAUNCHER");
            intent2.setComponent(new ComponentName(str, str2));
            intent2.setFlags(270532608);
            try {
                context.startActivity(intent2);
            } catch (Exception unused) {
                return false;
            }
        }
        return true;
    }

    public static void startActivityForPackage(Context context, String str) {
        try {
            context.startActivity(context.getPackageManager().getLaunchIntentForPackage(str));
        } catch (Exception e) {
            Log.e("TAG", "startActivityForPackage: e = " + e.toString());
            try {
                showTipString(context, context.getResources().getString(R.string.lb_no_device));
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public static void onEnterDashBoard(Context context) {
        startActivityIfNotRunning(context, "com.szchoiceway.ksw_dashboard", "com.szchoiceway.ksw_dashboard.MainActivity");
    }

    public static void onEnterPhoneLink(Context context) {
        if (!getInstallStatus(context, PHONEAPP_MODE_PACKAGE_NAME)) {
            try {
                showTipString(context, context.getResources().getString(R.string.lb_no_device));
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (!startActivityIfNotRunning2(context, PHONEAPP_MODE_PACKAGE_NAME, PHONEAPP_MODE_CLASS_NAME)) {
            startActivityIfNotRunning(context, PHONEAPP_MODE_PACKAGE_NAME, PHONEAPP_MODE_CLASS_NAME2);
        }
    }

    public static void onEnterDvr(Context context) {
        LauncherModel instance = LauncherModel.getInstance();
        int recordInteger = SysProviderOpt.getInstance(context).getRecordInteger(SysProviderOpt.KESAIWEI_RECORD_DVR, 0);
        if (instance.m_iUITypeVer == 48) {
            startActivityIfNotRunning(context, "com.ankai.cardvr", "com.ankai.cardvr.ui.GuideActivity");
        } else if (instance.m_iUITypeVer == 101) {
            startActivityIfNotRunning(context, "com.szchoiceway.auxplayer", "com.szchoiceway.auxplayer.MainActivity_DVR");
        } else if (recordInteger == 1) {
            startActivityIfNotRunning(context, "com.szchoiceway.ksw_dvr", "com.szchoiceway.ksw_dvr.MainActivity");
        } else if (recordInteger == 2) {
            String recordValue = SysProviderOpt.getInstance(context).getRecordValue(SysProviderOpt.KSW_DVR_APK_PACKAGENAME, "");
            if ("".equals(recordValue) || "com.anwensoft.cardvr".equals(recordValue)) {
                startActivityIfNotRunning(context, "com.anwensoft.cardvr", "com.anwensoft.cardvr.ui.GuideActivity");
            } else if ("".equals(recordValue) || "com.ankai.cardvr".equals(recordValue)) {
                startActivityIfNotRunning(context, "com.ankai.cardvr", "com.ankai.cardvr.ui.GuideActivity");
            } else {
                startActivityForPackage(context, recordValue);
            }
        } else {
            showTipString(context, context.getResources().getString(R.string.lb_no_device));
        }
    }

    public static String getProgressFromPosition(int i) {
        int i2 = i / 60;
        if ((i2 / 60) / 1000 > 0) {
            long j = (long) ((i / TimeHelps.DELAY_MILLISECONDS_mm) % 60);
            return String.format("%02d:%02d:%02d", new Object[]{Long.valueOf((long) ((i / TimeHelps.DELAY_MILLISECONDS_HH) % 24)), Long.valueOf(j), Long.valueOf((long) ((i / 1000) % 60))});
        } else if (i2 / 1000 > 0) {
            return String.format("00:%02d:%02d", new Object[]{Long.valueOf((long) ((i / TimeHelps.DELAY_MILLISECONDS_mm) % 60)), Long.valueOf((long) ((i / 1000) % 60))});
        } else {
            return String.format("00:00:%02d", new Object[]{Long.valueOf((long) ((i / 1000) % 60))});
        }
    }

    public static Bitmap loadingCover(String str) {
        Log.d(TAG, "loadingCover mediaUri = " + str);
        try {
            MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
            mediaMetadataRetriever.setDataSource(str);
            byte[] embeddedPicture = mediaMetadataRetriever.getEmbeddedPicture();
            if (embeddedPicture == null || embeddedPicture.length <= 0) {
                return null;
            }
            return BitmapFactory.decodeByteArray(embeddedPicture, 0, embeddedPicture.length);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getProgressFromPosition2(int i) {
        if (i > 3600000) {
            return new SimpleDateFormat("hh:mm:ss").format(Integer.valueOf(i));
        }
        return new SimpleDateFormat("mm:ss").format(Integer.valueOf(i));
    }

    public static boolean isNaviApk(Context context, String str) {
        ArrayList arrayList = new ArrayList();
        boolean z = false;
        try {
            SharedPreferences sharedPreferences = context.createPackageContext("com.szchoiceway.eventcenter", 2).getSharedPreferences("com.szchoiceway.eventcenter.navigation", 4);
            if (sharedPreferences.getAll().size() > 0) {
                arrayList.addAll(sharedPreferences.getAll().keySet());
            }
            Iterator it = arrayList.iterator();
            while (true) {
                if (it.hasNext()) {
                    if (str.equals((String) it.next())) {
                        z = true;
                        break;
                    }
                } else {
                    break;
                }
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        if ("com.txznet.aipal".equals(str) || "com.txznet.smartadapter".equals(str) || "com.zxwtxz.sdkdemo".equals(str)) {
            return true;
        }
        return z;
    }

    public static int getPx(Context context, int i) {
        return (int) (((float) i) / (240.0f / ((float) context.getResources().getDisplayMetrics().densityDpi)));
    }

    public static void startActivityType(int i, Context context, IEventService iEventService) {
        String str;
        String str2;
        String str3;
        String str4;
        if (context != null) {
            SysProviderOpt instance = SysProviderOpt.getInstance(context);
            String str5 = null;
            String str6 = ZLINK_CARPLAY_MODE_CLASS_NAME;
            if (i == 0) {
                str = instance.getRecordValue(SysProviderOpt.MUSIC_PACKAGENAME, "com.szchoiceway.musicplayer");
                str2 = instance.getRecordValue(SysProviderOpt.MUSIC_ACTIVITYNAME, "com.szchoiceway.musicplayer.MainActivity");
                if (iEventService != null && !"com.szchoiceway.musicplayer".equals(str)) {
                    try {
                        iEventService.sendMode(eSrcMode.SRC_MUSIC.getIntValue(), false);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
            } else if (i != 1) {
                if (i == 6) {
                    if (iEventService != null) {
                        try {
                            int letterConnectState = iEventService.getLetterConnectState();
                            int letterConnectMode = iEventService.getLetterConnectMode();
                            Log.d(TAG, "starCarplay mode = " + letterConnectMode + ", connectState = " + letterConnectState);
                            if (letterConnectState != 0) {
                                if (letterConnectMode != 1) {
                                    if (letterConnectMode == 2 || letterConnectMode == 3) {
                                        str4 = LETTER_AUTO_MODE_CLASS_NAME;
                                        str3 = str4;
                                        str6 = str3;
                                        str5 = LETTER_CARPLAY_MODE_PACKAGE_NAME;
                                    } else if (letterConnectMode != 4) {
                                        if (letterConnectMode == 6) {
                                            str4 = LETTER_DLNA_MODE_CLASS_NAME;
                                            str3 = str4;
                                            str6 = str3;
                                            str5 = LETTER_CARPLAY_MODE_PACKAGE_NAME;
                                        }
                                    }
                                }
                                str4 = LETTER_AIRPLAY_MODE_CLASS_NAME;
                                str3 = str4;
                                str6 = str3;
                                str5 = LETTER_CARPLAY_MODE_PACKAGE_NAME;
                            }
                            str3 = LETTER_CARPLAY_MODE_CLASS_NAME;
                            str6 = str3;
                            str5 = LETTER_CARPLAY_MODE_PACKAGE_NAME;
                        } catch (RemoteException e2) {
                            e2.printStackTrace();
                        }
                    }
                    str5 = LETTER_CARPLAY_MODE_PACKAGE_NAME;
                    str6 = LETTER_CARPLAY_MODE_CLASS_NAME;
                } else if (i != 7) {
                    str6 = null;
                } else {
                    if (iEventService != null) {
                        try {
                            int zlinkLinkState = iEventService.getZlinkLinkState();
                            int zlinkLinkMode = iEventService.getZlinkLinkMode();
                            Log.d(TAG, "zlinkLinkMode = " + zlinkLinkMode);
                            if (zlinkLinkMode == 2) {
                                str5 = CARLINK_MODE_PACKAGE_NAME;
                                str6 = CARLINK_MODE_CLASS_NAME;
                            } else if (zlinkLinkMode == 4) {
                                if (zlinkLinkState == 1) {
                                    str6 = ZLINK_DLNA_MODE_CLASS_NAME;
                                }
                            }
                        } catch (RemoteException e3) {
                            e3.printStackTrace();
                        }
                    }
                    str5 = ZLINK_MODE_PACKAGE_NAME;
                }
                startActivityIfNotRunning(context, str5, str6);
            } else {
                str = instance.getRecordValue("Video_PackageName", "com.szchoiceway.videoplayer");
                str2 = instance.getRecordValue("Video_ActivityName", "com.szchoiceway.videoplayer.MainActivity");
                if (iEventService != null && !"com.szchoiceway.videoplayer".equals(str)) {
                    try {
                        iEventService.sendMode(eSrcMode.SRC_MOVIE.getIntValue(), false);
                    } catch (RemoteException e4) {
                        e4.printStackTrace();
                    }
                }
            }
            str5 = str;
            str6 = str2;
            startActivityIfNotRunning(context, str5, str6);
        }
    }

    public static void startActivityType(int i, Context context) {
        if (context != null) {
            SysProviderOpt instance = SysProviderOpt.getInstance(context);
            boolean z = false;
            switch (i) {
                case 2:
                    String language = Locale.getDefault().getLanguage();
                    String country = Locale.getDefault().getCountry();
                    if (language.equals("zh") && country.equals("CN")) {
                        z = true;
                    }
                    String str = "com.szchoiceway.navigation";
                    String recordValue = instance.getRecordValue("NAV_PACKAGENAME", str);
                    String str2 = "com.szchoiceway.navigation.MainActivity";
                    String recordValue2 = instance.getRecordValue("NAV_ACTIVITYNAME", str2);
                    if ((!z || !"com.google.android.apps.maps".equals(recordValue)) && getInstallStatus(context, recordValue)) {
                        str = recordValue;
                        str2 = recordValue2;
                    }
                    startActivityIfNotRunning(context, str, str2);
                    return;
                case 3:
                    if (instance.getRecordInteger("KESAIWEI_RECORD_BT_INDEX", 0) == 1) {
                        context.sendBroadcast(new Intent("com.szchoiceway.eventcenter.EventUtils.ACTION_SWITCH_ORIGINACAR"));
                        return;
                    } else {
                        startActivityIfNotRunning(context, "com.szchoiceway.btsuite", "com.szchoiceway.btsuite.BTMainActivity");
                        return;
                    }
                case 4:
                    context.sendBroadcast(new Intent("com.szchoiceway.eventcenter.EventUtils.ACTION_SWITCH_ORIGINACAR"));
                    return;
                case 5:
                    startActivityIfNotRunning(context, "com.szchoiceway.settings", "com.szchoiceway.settings.MainActivity");
                    return;
                case 7:
                    startActivityIfNotRunning(context, "com.txznet.weather", "com.txznet.weather.MainActivity");
                    return;
                case 8:
                    onEnterDvr(context);
                    return;
                case 9:
                    onEnterDashBoard(context);
                    return;
                case 10:
                    startActivityIfNotRunning(context, ESUPER_MODE_PACKAGE_NAME, ESUPER_MODE_CLASS_NAME);
                    return;
                case 11:
                    startActivityIfNotRunning(context, "com.android.chrome", "com.google.android.apps.chrome.Main");
                    return;
                case 12:
                    if (getInstallStatus(context, "com.fibocom.multicamera")) {
                        startActivityIfNotRunning(context, "com.fibocom.multicamera", "");
                        return;
                    } else if (!getInstallStatus(context, ANXIAO_MODE_PACKAGE_NAME)) {
                        Toast.makeText(context, "no devices", 1).show();
                        return;
                    } else {
                        return;
                    }
                case 13:
                    startActivityIfNotRunning(context, APK_INSTALL_MODE_PACKAGE_NAME, APK_INSTALL_MODE_CLASS_NAME);
                    return;
                case 14:
                    startActivityIfNotRunning(context, "com.txznet.weather", "com.txznet.weather.MainActivity");
                    return;
                case 15:
                    startActivityIfNotRunning(context, "com.szchoiceway.equalizer", "com.szchoiceway.equalizer.MainActivity");
                    return;
                default:
                    return;
            }
        }
    }

    public static void startThirdApp(Context context, IEventService iEventService, String str, String str2) {
        if (str.equals(LETTER_CARPLAY_MODE_PACKAGE_NAME)) {
            startActivityType(6, context, iEventService);
        } else if (AppUtil.auxPackageNames.contains(str)) {
            if (iEventService != null) {
                try {
                    if (iEventService.isUpgradeMode()) {
                        return;
                    }
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
            if (!"com.szchoiceway.ksw_dvr".equals(str) || !"com.szchoiceway.ksw_dvr.MainActivity".equals(str2)) {
                startActivityIfNotRunning(context, str, str2);
            } else {
                onEnterDvr(context);
            }
        } else {
            try {
                Log.d(TAG, "sendThirdApp0");
                if (iEventService != null) {
                    iEventService.sendKSW_0x00_0x67_third();
                }
            } catch (RemoteException e2) {
                e2.printStackTrace();
            }
            startActivityIfNotRunning(context, str, str2);
        }
    }

    public static boolean isServiceAlive(Context context, String str) {
        List<ActivityManager.RunningServiceInfo> runningServices = ((ActivityManager) context.getSystemService("activity")).getRunningServices(100);
        if (runningServices.size() <= 0) {
            return false;
        }
        for (int i = 0; i < runningServices.size(); i++) {
            if (str.equals(runningServices.get(i).service.getClassName().toString())) {
                return true;
            }
        }
        return false;
    }

    public static int getStorageType(Context context) {
        DiskInfo disk;
        List<VolumeInfo> volumes = ((StorageManager) context.getSystemService(StorageManager.class)).getVolumes();
        Collections.sort(volumes, VolumeInfo.getDescriptionComparator());
        boolean z = false;
        boolean z2 = false;
        for (VolumeInfo volumeInfo : volumes) {
            if (volumeInfo.getType() == 0 && volumeInfo.isMountedReadable() && (disk = volumeInfo.getDisk()) != null) {
                if (disk.isSd()) {
                    z = true;
                } else if (disk.isUsb()) {
                    z2 = true;
                }
            }
        }
        if (z && z2) {
            return 3;
        }
        if (z2) {
            return 1;
        }
        if (z) {
            return 2;
        }
        return 0;
    }
}
