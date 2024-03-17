package com.szchoiceway.customerui.bmw.weather;

import com.szchoiceway.customerui.R;

public class WeatherIconTXZ extends WeatherIconBase {
    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:144:0x0278, code lost:
        if (r3 != false) goto L_0x01be;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:184:?, code lost:
        return com.szchoiceway.customerui.R.drawable.id8_main_edit_icon_weather_sunny;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:185:?, code lost:
        return com.szchoiceway.customerui.R.drawable.id8_main_icon_weather_sunny;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x0095, code lost:
        if (r2.equals("weather_zhenyu.png") == false) goto L_0x001b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:96:0x01bc, code lost:
        if (r3 != false) goto L_0x01be;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int getWeatherIconId(java.lang.String r2, boolean r3, int r4) {
        /*
            r1 = this;
            r1 = 20
            if (r4 != r1) goto L_0x0010
            if (r3 == 0) goto L_0x000b
            r1 = 1879575484(0x70080bbc, float:1.6841659E29)
            goto L_0x0305
        L_0x000b:
            r1 = 1879575577(0x70080c19, float:1.6841835E29)
            goto L_0x0305
        L_0x0010:
            r2.hashCode()
            r4 = -1
            int r0 = r2.hashCode()
            switch(r0) {
                case -2007006149: goto L_0x01a7;
                case -1964169151: goto L_0x019a;
                case -1893213074: goto L_0x018d;
                case -1762304422: goto L_0x0180;
                case -1570070136: goto L_0x0173;
                case -1522097648: goto L_0x0166;
                case -1512778085: goto L_0x0159;
                case -1461142052: goto L_0x014c;
                case -1024703373: goto L_0x013d;
                case -637245050: goto L_0x012e;
                case -613858791: goto L_0x011f;
                case -390972850: goto L_0x0110;
                case -337726012: goto L_0x0101;
                case -242160521: goto L_0x00f2;
                case -175999878: goto L_0x00e3;
                case 14618050: goto L_0x00d4;
                case 264624306: goto L_0x00c5;
                case 365061948: goto L_0x00b6;
                case 393896703: goto L_0x00a7;
                case 514120659: goto L_0x0098;
                case 517883033: goto L_0x008e;
                case 881565940: goto L_0x0080;
                case 967042239: goto L_0x0072;
                case 1110562654: goto L_0x0064;
                case 1457102115: goto L_0x0056;
                case 1609652513: goto L_0x0048;
                case 1610234053: goto L_0x003a;
                case 1626325763: goto L_0x002c;
                case 1815913657: goto L_0x001e;
                default: goto L_0x001b;
            }
        L_0x001b:
            r1 = r4
            goto L_0x01b3
        L_0x001e:
            java.lang.String r1 = "weather_xiaoxue.png"
            boolean r1 = r2.equals(r1)
            if (r1 != 0) goto L_0x0028
            goto L_0x001b
        L_0x0028:
            r1 = 28
            goto L_0x01b3
        L_0x002c:
            java.lang.String r1 = "weather_fuchen.png"
            boolean r1 = r2.equals(r1)
            if (r1 != 0) goto L_0x0036
            goto L_0x001b
        L_0x0036:
            r1 = 27
            goto L_0x01b3
        L_0x003a:
            java.lang.String r1 = "weather_mai.png"
            boolean r1 = r2.equals(r1)
            if (r1 != 0) goto L_0x0044
            goto L_0x001b
        L_0x0044:
            r1 = 26
            goto L_0x01b3
        L_0x0048:
            java.lang.String r1 = "weather_xiaoyu.png"
            boolean r1 = r2.equals(r1)
            if (r1 != 0) goto L_0x0052
            goto L_0x001b
        L_0x0052:
            r1 = 25
            goto L_0x01b3
        L_0x0056:
            java.lang.String r1 = "weather_leizhenyubanyoubingbao.png"
            boolean r1 = r2.equals(r1)
            if (r1 != 0) goto L_0x0060
            goto L_0x001b
        L_0x0060:
            r1 = 24
            goto L_0x01b3
        L_0x0064:
            java.lang.String r1 = "weather_baoxue.png"
            boolean r1 = r2.equals(r1)
            if (r1 != 0) goto L_0x006e
            goto L_0x001b
        L_0x006e:
            r1 = 23
            goto L_0x01b3
        L_0x0072:
            java.lang.String r1 = "weather_dabaoyu.png"
            boolean r1 = r2.equals(r1)
            if (r1 != 0) goto L_0x007c
            goto L_0x001b
        L_0x007c:
            r1 = 22
            goto L_0x01b3
        L_0x0080:
            java.lang.String r1 = "weather_zhongxue.png"
            boolean r1 = r2.equals(r1)
            if (r1 != 0) goto L_0x008a
            goto L_0x001b
        L_0x008a:
            r1 = 21
            goto L_0x01b3
        L_0x008e:
            java.lang.String r0 = "weather_zhenyu.png"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x01b3
            goto L_0x001b
        L_0x0098:
            java.lang.String r1 = "weather_duoyun_night.png"
            boolean r1 = r2.equals(r1)
            if (r1 != 0) goto L_0x00a3
            goto L_0x001b
        L_0x00a3:
            r1 = 19
            goto L_0x01b3
        L_0x00a7:
            java.lang.String r1 = "weather_dayu.png"
            boolean r1 = r2.equals(r1)
            if (r1 != 0) goto L_0x00b2
            goto L_0x001b
        L_0x00b2:
            r1 = 18
            goto L_0x01b3
        L_0x00b6:
            java.lang.String r1 = "weather_shachenbao.png"
            boolean r1 = r2.equals(r1)
            if (r1 != 0) goto L_0x00c1
            goto L_0x001b
        L_0x00c1:
            r1 = 17
            goto L_0x01b3
        L_0x00c5:
            java.lang.String r1 = "weather_zhenyu_night.png"
            boolean r1 = r2.equals(r1)
            if (r1 != 0) goto L_0x00d0
            goto L_0x001b
        L_0x00d0:
            r1 = 16
            goto L_0x01b3
        L_0x00d4:
            java.lang.String r1 = "weather_bingyu.png"
            boolean r1 = r2.equals(r1)
            if (r1 != 0) goto L_0x00df
            goto L_0x001b
        L_0x00df:
            r1 = 15
            goto L_0x01b3
        L_0x00e3:
            java.lang.String r1 = "weather_duoyun.png"
            boolean r1 = r2.equals(r1)
            if (r1 != 0) goto L_0x00ee
            goto L_0x001b
        L_0x00ee:
            r1 = 14
            goto L_0x01b3
        L_0x00f2:
            java.lang.String r1 = "weather_qing.png"
            boolean r1 = r2.equals(r1)
            if (r1 != 0) goto L_0x00fd
            goto L_0x001b
        L_0x00fd:
            r1 = 13
            goto L_0x01b3
        L_0x0101:
            java.lang.String r1 = "weather_wu.png"
            boolean r1 = r2.equals(r1)
            if (r1 != 0) goto L_0x010c
            goto L_0x001b
        L_0x010c:
            r1 = 12
            goto L_0x01b3
        L_0x0110:
            java.lang.String r1 = "weather_yin.png"
            boolean r1 = r2.equals(r1)
            if (r1 != 0) goto L_0x011b
            goto L_0x001b
        L_0x011b:
            r1 = 11
            goto L_0x01b3
        L_0x011f:
            java.lang.String r1 = "weather_na.png"
            boolean r1 = r2.equals(r1)
            if (r1 != 0) goto L_0x012a
            goto L_0x001b
        L_0x012a:
            r1 = 10
            goto L_0x01b3
        L_0x012e:
            java.lang.String r1 = "weather_zhongyu.png"
            boolean r1 = r2.equals(r1)
            if (r1 != 0) goto L_0x0139
            goto L_0x001b
        L_0x0139:
            r1 = 9
            goto L_0x01b3
        L_0x013d:
            java.lang.String r1 = "weather_leizhenyu.png"
            boolean r1 = r2.equals(r1)
            if (r1 != 0) goto L_0x0148
            goto L_0x001b
        L_0x0148:
            r1 = 8
            goto L_0x01b3
        L_0x014c:
            java.lang.String r1 = "weather_baoyu.png"
            boolean r1 = r2.equals(r1)
            if (r1 != 0) goto L_0x0157
            goto L_0x001b
        L_0x0157:
            r1 = 7
            goto L_0x01b3
        L_0x0159:
            java.lang.String r1 = "weather_daxue.png"
            boolean r1 = r2.equals(r1)
            if (r1 != 0) goto L_0x0164
            goto L_0x001b
        L_0x0164:
            r1 = 6
            goto L_0x01b3
        L_0x0166:
            java.lang.String r1 = "weather_qing_night.png"
            boolean r1 = r2.equals(r1)
            if (r1 != 0) goto L_0x0171
            goto L_0x001b
        L_0x0171:
            r1 = 5
            goto L_0x01b3
        L_0x0173:
            java.lang.String r1 = "weather_yujiaxue.png"
            boolean r1 = r2.equals(r1)
            if (r1 != 0) goto L_0x017e
            goto L_0x001b
        L_0x017e:
            r1 = 4
            goto L_0x01b3
        L_0x0180:
            java.lang.String r1 = "weather_zhenxue_night.png"
            boolean r1 = r2.equals(r1)
            if (r1 != 0) goto L_0x018b
            goto L_0x001b
        L_0x018b:
            r1 = 3
            goto L_0x01b3
        L_0x018d:
            java.lang.String r1 = "weather_tedabaoyu.png"
            boolean r1 = r2.equals(r1)
            if (r1 != 0) goto L_0x0198
            goto L_0x001b
        L_0x0198:
            r1 = 2
            goto L_0x01b3
        L_0x019a:
            java.lang.String r1 = "weather_zhenxue.png"
            boolean r1 = r2.equals(r1)
            if (r1 != 0) goto L_0x01a5
            goto L_0x001b
        L_0x01a5:
            r1 = 1
            goto L_0x01b3
        L_0x01a7:
            java.lang.String r1 = "weather_yangsha.png"
            boolean r1 = r2.equals(r1)
            if (r1 != 0) goto L_0x01b2
            goto L_0x001b
        L_0x01b2:
            r1 = 0
        L_0x01b3:
            r2 = 1879573912(0x70080598, float:1.683869E29)
            r4 = 1879574079(0x7008063f, float:1.6839005E29)
            switch(r1) {
                case 0: goto L_0x02fc;
                case 1: goto L_0x02f2;
                case 2: goto L_0x02e8;
                case 3: goto L_0x02de;
                case 4: goto L_0x02d4;
                case 5: goto L_0x02ca;
                case 6: goto L_0x02c0;
                case 7: goto L_0x02b6;
                case 8: goto L_0x02ac;
                case 9: goto L_0x02a0;
                case 10: goto L_0x0294;
                case 11: goto L_0x0288;
                case 12: goto L_0x027c;
                case 13: goto L_0x0278;
                case 14: goto L_0x026c;
                case 15: goto L_0x0260;
                case 16: goto L_0x0254;
                case 17: goto L_0x0248;
                case 18: goto L_0x023c;
                case 19: goto L_0x0230;
                case 20: goto L_0x0224;
                case 21: goto L_0x0218;
                case 22: goto L_0x020c;
                case 23: goto L_0x0200;
                case 24: goto L_0x01f4;
                case 25: goto L_0x01e8;
                case 26: goto L_0x01dc;
                case 27: goto L_0x01d0;
                case 28: goto L_0x01c4;
                default: goto L_0x01bc;
            }
        L_0x01bc:
            if (r3 == 0) goto L_0x01c1
        L_0x01be:
            r1 = r2
            goto L_0x0305
        L_0x01c1:
            r1 = r4
            goto L_0x0305
        L_0x01c4:
            if (r3 == 0) goto L_0x01cb
            r1 = 1879573860(0x70080564, float:1.6838591E29)
            goto L_0x0305
        L_0x01cb:
            r1 = 1879574025(0x70080609, float:1.6838903E29)
            goto L_0x0305
        L_0x01d0:
            if (r3 == 0) goto L_0x01d7
            r1 = 1879573838(0x7008054e, float:1.683855E29)
            goto L_0x0305
        L_0x01d7:
            r1 = 1879574001(0x700805f1, float:1.6838858E29)
            goto L_0x0305
        L_0x01dc:
            if (r3 == 0) goto L_0x01e3
            r1 = 1879573850(0x7008055a, float:1.6838572E29)
            goto L_0x0305
        L_0x01e3:
            r1 = 1879574015(0x700805ff, float:1.6838884E29)
            goto L_0x0305
        L_0x01e8:
            if (r3 == 0) goto L_0x01ef
            r1 = 1879573835(0x7008054b, float:1.6838544E29)
            goto L_0x0305
        L_0x01ef:
            r1 = 1879573998(0x700805ee, float:1.6838852E29)
            goto L_0x0305
        L_0x01f4:
            if (r3 == 0) goto L_0x01fb
            r1 = 1879573918(0x7008059e, float:1.68387E29)
            goto L_0x0305
        L_0x01fb:
            r1 = 1879574085(0x70080645, float:1.6839016E29)
            goto L_0x0305
        L_0x0200:
            if (r3 == 0) goto L_0x0207
            r1 = 1879573823(0x7008053f, float:1.6838521E29)
            goto L_0x0305
        L_0x0207:
            r1 = 1879573986(0x700805e2, float:1.683883E29)
            goto L_0x0305
        L_0x020c:
            if (r3 == 0) goto L_0x0213
            r1 = 1879573887(0x7008057f, float:1.6838642E29)
            goto L_0x0305
        L_0x0213:
            r1 = 1879574054(0x70080626, float:1.6838958E29)
            goto L_0x0305
        L_0x0218:
            if (r3 == 0) goto L_0x021f
            r1 = 1879573866(0x7008056a, float:1.6838603E29)
            goto L_0x0305
        L_0x021f:
            r1 = 1879574033(0x70080611, float:1.6838918E29)
            goto L_0x0305
        L_0x0224:
            if (r3 == 0) goto L_0x022b
            r1 = 1879573899(0x7008058b, float:1.6838665E29)
            goto L_0x0305
        L_0x022b:
            r1 = 1879574066(0x70080632, float:1.683898E29)
            goto L_0x0305
        L_0x0230:
            if (r3 == 0) goto L_0x0237
            r1 = 1879573832(0x70080548, float:1.6838538E29)
            goto L_0x0305
        L_0x0237:
            r1 = 1879573995(0x700805eb, float:1.6838846E29)
            goto L_0x0305
        L_0x023c:
            if (r3 == 0) goto L_0x0243
            r1 = 1879573853(0x7008055d, float:1.6838578E29)
            goto L_0x0305
        L_0x0243:
            r1 = 1879574018(0x70080602, float:1.683889E29)
            goto L_0x0305
        L_0x0248:
            if (r3 == 0) goto L_0x024f
            r1 = 1879573893(0x70080585, float:1.6838654E29)
            goto L_0x0305
        L_0x024f:
            r1 = 1879574060(0x7008062c, float:1.683897E29)
            goto L_0x0305
        L_0x0254:
            if (r3 == 0) goto L_0x025b
            r1 = 1879573872(0x70080570, float:1.6838614E29)
            goto L_0x0305
        L_0x025b:
            r1 = 1879574039(0x70080617, float:1.683893E29)
            goto L_0x0305
        L_0x0260:
            if (r3 == 0) goto L_0x0267
            r1 = 1879573844(0x70080554, float:1.6838561E29)
            goto L_0x0305
        L_0x0267:
            r1 = 1879574009(0x700805f9, float:1.6838873E29)
            goto L_0x0305
        L_0x026c:
            if (r3 == 0) goto L_0x0273
            r1 = 1879573829(0x70080545, float:1.6838533E29)
            goto L_0x0305
        L_0x0273:
            r1 = 1879573992(0x700805e8, float:1.683884E29)
            goto L_0x0305
        L_0x0278:
            if (r3 == 0) goto L_0x01c1
            goto L_0x01be
        L_0x027c:
            if (r3 == 0) goto L_0x0283
            r1 = 1879573841(0x70080551, float:1.6838555E29)
            goto L_0x0305
        L_0x0283:
            r1 = 1879574006(0x700805f6, float:1.6838867E29)
            goto L_0x0305
        L_0x0288:
            if (r3 == 0) goto L_0x028f
            r1 = 1879573878(0x70080576, float:1.6838625E29)
            goto L_0x0305
        L_0x028f:
            r1 = 1879574045(0x7008061d, float:1.683894E29)
            goto L_0x0305
        L_0x0294:
            if (r3 == 0) goto L_0x029b
            r1 = 1879573921(0x700805a1, float:1.6838707E29)
            goto L_0x0305
        L_0x029b:
            r1 = 1879574088(0x70080648, float:1.6839022E29)
            goto L_0x0305
        L_0x02a0:
            if (r3 == 0) goto L_0x02a7
            r1 = 1879573869(0x7008056d, float:1.6838608E29)
            goto L_0x0305
        L_0x02a7:
            r1 = 1879574036(0x70080614, float:1.6838924E29)
            goto L_0x0305
        L_0x02ac:
            if (r3 == 0) goto L_0x02b2
            r1 = 1879573915(0x7008059b, float:1.6838695E29)
            goto L_0x0305
        L_0x02b2:
            r1 = 1879574082(0x70080642, float:1.683901E29)
            goto L_0x0305
        L_0x02b6:
            if (r3 == 0) goto L_0x02bc
            r1 = 1879573884(0x7008057c, float:1.6838637E29)
            goto L_0x0305
        L_0x02bc:
            r1 = 1879574051(0x70080623, float:1.6838952E29)
            goto L_0x0305
        L_0x02c0:
            if (r3 == 0) goto L_0x02c6
            r1 = 1879573856(0x70080560, float:1.6838584E29)
            goto L_0x0305
        L_0x02c6:
            r1 = 1879574021(0x70080605, float:1.6838895E29)
            goto L_0x0305
        L_0x02ca:
            if (r3 == 0) goto L_0x02d0
            r1 = 1879573875(0x70080573, float:1.683862E29)
            goto L_0x0305
        L_0x02d0:
            r1 = 1879574042(0x7008061a, float:1.6838935E29)
            goto L_0x0305
        L_0x02d4:
            if (r3 == 0) goto L_0x02da
            r1 = 1879573902(0x7008058e, float:1.683867E29)
            goto L_0x0305
        L_0x02da:
            r1 = 1879574069(0x70080635, float:1.6838986E29)
            goto L_0x0305
        L_0x02de:
            if (r3 == 0) goto L_0x02e4
            r1 = 1879573909(0x70080595, float:1.6838684E29)
            goto L_0x0305
        L_0x02e4:
            r1 = 1879574076(0x7008063c, float:1.6839E29)
            goto L_0x0305
        L_0x02e8:
            if (r3 == 0) goto L_0x02ee
            r1 = 1879573890(0x70080582, float:1.6838648E29)
            goto L_0x0305
        L_0x02ee:
            r1 = 1879574057(0x70080629, float:1.6838963E29)
            goto L_0x0305
        L_0x02f2:
            if (r3 == 0) goto L_0x02f8
            r1 = 1879573906(0x70080592, float:1.6838678E29)
            goto L_0x0305
        L_0x02f8:
            r1 = 1879574073(0x70080639, float:1.6838994E29)
            goto L_0x0305
        L_0x02fc:
            if (r3 == 0) goto L_0x0302
            r1 = 1879573826(0x70080542, float:1.6838527E29)
            goto L_0x0305
        L_0x0302:
            r1 = 1879573989(0x700805e5, float:1.6838835E29)
        L_0x0305:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.szchoiceway.customerui.bmw.weather.WeatherIconTXZ.getWeatherIconId(java.lang.String, boolean, int):int");
    }

    public int getWeatherIconIdMini(String str, boolean z) {
        str.hashCode();
        char c = 65535;
        switch (str.hashCode()) {
            case -2007006149:
                if (str.equals("weather_yangsha.png")) {
                    c = 0;
                    break;
                }
                break;
            case -1964169151:
                if (str.equals("weather_zhenxue.png")) {
                    c = 1;
                    break;
                }
                break;
            case -1893213074:
                if (str.equals("weather_tedabaoyu.png")) {
                    c = 2;
                    break;
                }
                break;
            case -1762304422:
                if (str.equals("weather_zhenxue_night.png")) {
                    c = 3;
                    break;
                }
                break;
            case -1570070136:
                if (str.equals("weather_yujiaxue.png")) {
                    c = 4;
                    break;
                }
                break;
            case -1522097648:
                if (str.equals("weather_qing_night.png")) {
                    c = 5;
                    break;
                }
                break;
            case -1512778085:
                if (str.equals("weather_daxue.png")) {
                    c = 6;
                    break;
                }
                break;
            case -1461142052:
                if (str.equals("weather_baoyu.png")) {
                    c = 7;
                    break;
                }
                break;
            case -1024703373:
                if (str.equals("weather_leizhenyu.png")) {
                    c = 8;
                    break;
                }
                break;
            case -637245050:
                if (str.equals("weather_zhongyu.png")) {
                    c = 9;
                    break;
                }
                break;
            case -613858791:
                if (str.equals("weather_na.png")) {
                    c = 10;
                    break;
                }
                break;
            case -390972850:
                if (str.equals("weather_yin.png")) {
                    c = 11;
                    break;
                }
                break;
            case -337726012:
                if (str.equals("weather_wu.png")) {
                    c = 12;
                    break;
                }
                break;
            case -242160521:
                if (str.equals("weather_qing.png")) {
                    c = 13;
                    break;
                }
                break;
            case -175999878:
                if (str.equals("weather_duoyun.png")) {
                    c = 14;
                    break;
                }
                break;
            case 14618050:
                if (str.equals("weather_bingyu.png")) {
                    c = 15;
                    break;
                }
                break;
            case 264624306:
                if (str.equals("weather_zhenyu_night.png")) {
                    c = 16;
                    break;
                }
                break;
            case 365061948:
                if (str.equals("weather_shachenbao.png")) {
                    c = 17;
                    break;
                }
                break;
            case 393896703:
                if (str.equals("weather_dayu.png")) {
                    c = 18;
                    break;
                }
                break;
            case 514120659:
                if (str.equals("weather_duoyun_night.png")) {
                    c = 19;
                    break;
                }
                break;
            case 517883033:
                if (str.equals("weather_zhenyu.png")) {
                    c = 20;
                    break;
                }
                break;
            case 881565940:
                if (str.equals("weather_zhongxue.png")) {
                    c = 21;
                    break;
                }
                break;
            case 967042239:
                if (str.equals("weather_dabaoyu.png")) {
                    c = 22;
                    break;
                }
                break;
            case 1110562654:
                if (str.equals("weather_baoxue.png")) {
                    c = 23;
                    break;
                }
                break;
            case 1457102115:
                if (str.equals("weather_leizhenyubanyoubingbao.png")) {
                    c = 24;
                    break;
                }
                break;
            case 1609652513:
                if (str.equals("weather_xiaoyu.png")) {
                    c = 25;
                    break;
                }
                break;
            case 1610234053:
                if (str.equals("weather_mai.png")) {
                    c = 26;
                    break;
                }
                break;
            case 1626325763:
                if (str.equals("weather_fuchen.png")) {
                    c = 27;
                    break;
                }
                break;
            case 1815913657:
                if (str.equals("weather_xiaoxue.png")) {
                    c = 28;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                return z ? R.drawable.id8_main_edit_icon_weather_blowing_sand2 : R.drawable.id8_main_icon_weather_blowing_sand2;
            case 1:
                return z ? R.drawable.id8_main_edit_icon_weather_snow_showers2 : R.drawable.id8_main_icon_weather_snow_showers2;
            case 2:
                return z ? R.drawable.id8_main_edit_icon_weather_rainstorm_xl2 : R.drawable.id8_main_icon_weather_rainstorm_xl2;
            case 3:
                return z ? R.drawable.id8_main_edit_icon_weather_snow_showers_at_night2 : R.drawable.id8_main_icon_weather_snow_showers_at_night2;
            case 4:
                return z ? R.drawable.id8_main_edit_icon_weather_sleet2 : R.drawable.id8_main_icon_weather_sleet2;
            case 5:
                return z ? R.drawable.id8_main_edit_icon_weather_night_sunny2 : R.drawable.id8_main_icon_weather_night_sunny2;
            case 6:
                return z ? R.drawable.id8_main_edit_icon_weather_heavy_snow2 : R.drawable.id8_main_icon_weather_heavy_snow2;
            case 7:
                return z ? R.drawable.id8_main_edit_icon_weather_rainstorm_2 : R.drawable.id8_main_icon_weather_rainstorm_2;
            case 8:
                return z ? R.drawable.id8_main_edit_icon_weather_thunderstorms2 : R.drawable.id8_main_icon_weather_thunderstorms2;
            case 9:
                return z ? R.drawable.id8_main_edit_icon_weather_moderate_rain2 : R.drawable.id8_main_icon_weather_moderate_rain2;
            case 10:
                return z ? R.drawable.id8_main_edit_icon_weather_unknown2 : R.drawable.id8_main_icon_weather_unknown2;
            case 11:
                return z ? R.drawable.id8_main_edit_icon_weather_overcast2 : R.drawable.id8_main_icon_weather_overcast2;
            case 12:
                return z ? R.drawable.id8_main_edit_icon_weather_fog2 : R.drawable.id8_main_icon_weather_fog2;
            case 13:
                if (z) {
                    return R.drawable.id8_main_edit_icon_weather_sunny2;
                }
                break;
            case 14:
                return z ? R.drawable.id8_main_edit_icon_weather_cloudy2 : R.drawable.id8_main_icon_weather_cloudy2;
            case 15:
                return z ? R.drawable.id8_main_edit_icon_weather_freezing_rain2 : R.drawable.id8_main_icon_weather_freezing_rain2;
            case 16:
                return z ? R.drawable.id8_main_edit_icon_weather_night_showers2 : R.drawable.id8_main_icon_weather_night_showers2;
            case 17:
                return z ? R.drawable.id8_main_edit_icon_weather_sandstorm_2 : R.drawable.id8_main_icon_weather_sandstorm_2;
            case 18:
                return z ? R.drawable.id8_main_edit_icon_weather_heavy_rain2 : R.drawable.id8_main_icon_weather_heavy_rain2;
            case 19:
                return z ? R.drawable.id8_main_edit_icon_weather_cloudy_at_night2 : R.drawable.id8_main_icon_weather_cloudy_at_night2;
            case 20:
                return z ? R.drawable.id8_main_edit_icon_weather_showers2 : R.drawable.id8_main_icon_weather_showers2;
            case 21:
                return z ? R.drawable.id8_main_edit_icon_weather_medium_snow2 : R.drawable.id8_main_icon_weather_medium_snow2;
            case 22:
                return z ? R.drawable.id8_main_edit_icon_weather_rainstorm_l2 : R.drawable.id8_main_icon_weather_rainstorm_l2;
            case 23:
                return z ? R.drawable.id8_main_edit_icon_weather_blizzard2 : R.drawable.id8_main_icon_weather_blizzard2;
            case 24:
                return z ? R.drawable.id8_main_edit_icon_weather_thunderstorms_with_hail2 : R.drawable.id8_main_icon_weather_thunderstorms_with_hail2;
            case 25:
                return z ? R.drawable.id8_main_edit_icon_weather_drizzle2 : R.drawable.id8_main_icon_weather_drizzle2;
            case 26:
                return z ? R.drawable.id8_main_edit_icon_weather_haze2 : R.drawable.id8_main_icon_weather_haze2;
            case 27:
                return z ? R.drawable.id8_main_edit_icon_weather_dusty2 : R.drawable.id8_main_icon_weather_dusty2;
            case 28:
                return z ? R.drawable.id8_main_edit_icon_weather_light_snow2 : R.drawable.id8_main_icon_weather_light_snow2;
            default:
                if (z) {
                    return R.drawable.id8_main_edit_icon_weather_sunny2;
                }
                break;
        }
        return R.drawable.id8_main_icon_weather_sunny2;
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:274:0x04ae, code lost:
        return com.szchoiceway.customerui.R.drawable.id8_main_icon_weather_sunny1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:95:0x01c2, code lost:
        return com.szchoiceway.customerui.R.drawable.pemp_id8_main_icon_weather_sunny;
     */
    /* JADX WARNING: Removed duplicated region for block: B:273:0x04a9 A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:94:0x01bd A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int getWeatherIconIdBig(java.lang.String r20, boolean r21, int r22) {
        /*
            r19 = this;
            r0 = r20
            java.lang.String r2 = "weather_zhongyu.png"
            java.lang.String r4 = "weather_leizhenyu.png"
            java.lang.String r6 = "weather_baoyu.png"
            java.lang.String r8 = "weather_daxue.png"
            java.lang.String r10 = "weather_qing_night.png"
            r11 = 4
            java.lang.String r12 = "weather_yujiaxue.png"
            r13 = 3
            java.lang.String r14 = "weather_zhenxue_night.png"
            r15 = 2
            java.lang.String r1 = "weather_tedabaoyu.png"
            r16 = 1
            java.lang.String r3 = "weather_zhenxue.png"
            r17 = 0
            java.lang.String r5 = "weather_yangsha.png"
            r18 = -1
            r7 = 20
            r9 = r22
            if (r9 != r7) goto L_0x031b
            r20.hashCode()
            int r9 = r20.hashCode()
            switch(r9) {
                case -2007006149: goto L_0x01ae;
                case -1964169151: goto L_0x01a3;
                case -1893213074: goto L_0x0199;
                case -1762304422: goto L_0x018f;
                case -1570070136: goto L_0x0185;
                case -1522097648: goto L_0x017b;
                case -1512778085: goto L_0x0171;
                case -1461142052: goto L_0x0167;
                case -1024703373: goto L_0x015c;
                case -637245050: goto L_0x0150;
                case -613858791: goto L_0x0141;
                case -390972850: goto L_0x0132;
                case -337726012: goto L_0x0123;
                case -242160521: goto L_0x0114;
                case -175999878: goto L_0x0105;
                case 14618050: goto L_0x00f6;
                case 264624306: goto L_0x00e7;
                case 365061948: goto L_0x00d8;
                case 393896703: goto L_0x00c9;
                case 514120659: goto L_0x00ba;
                case 517883033: goto L_0x00ad;
                case 881565940: goto L_0x009f;
                case 967042239: goto L_0x0091;
                case 1110562654: goto L_0x0083;
                case 1457102115: goto L_0x0075;
                case 1609652513: goto L_0x0067;
                case 1610234053: goto L_0x0059;
                case 1626325763: goto L_0x004b;
                case 1815913657: goto L_0x003d;
                default: goto L_0x0039;
            }
        L_0x0039:
            r1 = r18
            goto L_0x01b8
        L_0x003d:
            java.lang.String r1 = "weather_xiaoxue.png"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x0047
            goto L_0x0039
        L_0x0047:
            r1 = 28
            goto L_0x01b8
        L_0x004b:
            java.lang.String r1 = "weather_fuchen.png"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x0055
            goto L_0x0039
        L_0x0055:
            r1 = 27
            goto L_0x01b8
        L_0x0059:
            java.lang.String r1 = "weather_mai.png"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x0063
            goto L_0x0039
        L_0x0063:
            r1 = 26
            goto L_0x01b8
        L_0x0067:
            java.lang.String r1 = "weather_xiaoyu.png"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x0071
            goto L_0x0039
        L_0x0071:
            r1 = 25
            goto L_0x01b8
        L_0x0075:
            java.lang.String r1 = "weather_leizhenyubanyoubingbao.png"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x007f
            goto L_0x0039
        L_0x007f:
            r1 = 24
            goto L_0x01b8
        L_0x0083:
            java.lang.String r1 = "weather_baoxue.png"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x008d
            goto L_0x0039
        L_0x008d:
            r1 = 23
            goto L_0x01b8
        L_0x0091:
            java.lang.String r1 = "weather_dabaoyu.png"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x009b
            goto L_0x0039
        L_0x009b:
            r1 = 22
            goto L_0x01b8
        L_0x009f:
            java.lang.String r1 = "weather_zhongxue.png"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x00a9
            goto L_0x0039
        L_0x00a9:
            r1 = 21
            goto L_0x01b8
        L_0x00ad:
            java.lang.String r1 = "weather_zhenyu.png"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x00b7
            goto L_0x0039
        L_0x00b7:
            r1 = r7
            goto L_0x01b8
        L_0x00ba:
            java.lang.String r1 = "weather_duoyun_night.png"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x00c5
            goto L_0x0039
        L_0x00c5:
            r1 = 19
            goto L_0x01b8
        L_0x00c9:
            java.lang.String r1 = "weather_dayu.png"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x00d4
            goto L_0x0039
        L_0x00d4:
            r1 = 18
            goto L_0x01b8
        L_0x00d8:
            java.lang.String r1 = "weather_shachenbao.png"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x00e3
            goto L_0x0039
        L_0x00e3:
            r1 = 17
            goto L_0x01b8
        L_0x00e7:
            java.lang.String r1 = "weather_zhenyu_night.png"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x00f2
            goto L_0x0039
        L_0x00f2:
            r1 = 16
            goto L_0x01b8
        L_0x00f6:
            java.lang.String r1 = "weather_bingyu.png"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x0101
            goto L_0x0039
        L_0x0101:
            r1 = 15
            goto L_0x01b8
        L_0x0105:
            java.lang.String r1 = "weather_duoyun.png"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x0110
            goto L_0x0039
        L_0x0110:
            r1 = 14
            goto L_0x01b8
        L_0x0114:
            java.lang.String r1 = "weather_qing.png"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x011f
            goto L_0x0039
        L_0x011f:
            r1 = 13
            goto L_0x01b8
        L_0x0123:
            java.lang.String r1 = "weather_wu.png"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x012e
            goto L_0x0039
        L_0x012e:
            r1 = 12
            goto L_0x01b8
        L_0x0132:
            java.lang.String r1 = "weather_yin.png"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x013d
            goto L_0x0039
        L_0x013d:
            r1 = 11
            goto L_0x01b8
        L_0x0141:
            java.lang.String r1 = "weather_na.png"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x014c
            goto L_0x0039
        L_0x014c:
            r1 = 10
            goto L_0x01b8
        L_0x0150:
            boolean r0 = r0.equals(r2)
            if (r0 != 0) goto L_0x0158
            goto L_0x0039
        L_0x0158:
            r1 = 9
            goto L_0x01b8
        L_0x015c:
            boolean r0 = r0.equals(r4)
            if (r0 != 0) goto L_0x0164
            goto L_0x0039
        L_0x0164:
            r1 = 8
            goto L_0x01b8
        L_0x0167:
            boolean r0 = r0.equals(r6)
            if (r0 != 0) goto L_0x016f
            goto L_0x0039
        L_0x016f:
            r1 = 7
            goto L_0x01b8
        L_0x0171:
            boolean r0 = r0.equals(r8)
            if (r0 != 0) goto L_0x0179
            goto L_0x0039
        L_0x0179:
            r1 = 6
            goto L_0x01b8
        L_0x017b:
            boolean r0 = r0.equals(r10)
            if (r0 != 0) goto L_0x0183
            goto L_0x0039
        L_0x0183:
            r1 = 5
            goto L_0x01b8
        L_0x0185:
            boolean r0 = r0.equals(r12)
            if (r0 != 0) goto L_0x018d
            goto L_0x0039
        L_0x018d:
            r1 = r11
            goto L_0x01b8
        L_0x018f:
            boolean r0 = r0.equals(r14)
            if (r0 != 0) goto L_0x0197
            goto L_0x0039
        L_0x0197:
            r1 = r13
            goto L_0x01b8
        L_0x0199:
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x01a1
            goto L_0x0039
        L_0x01a1:
            r1 = r15
            goto L_0x01b8
        L_0x01a3:
            boolean r0 = r0.equals(r3)
            if (r0 != 0) goto L_0x01ab
            goto L_0x0039
        L_0x01ab:
            r1 = r16
            goto L_0x01b8
        L_0x01ae:
            boolean r0 = r0.equals(r5)
            if (r0 != 0) goto L_0x01b6
            goto L_0x0039
        L_0x01b6:
            r1 = r17
        L_0x01b8:
            switch(r1) {
                case 0: goto L_0x030f;
                case 1: goto L_0x0303;
                case 2: goto L_0x02f7;
                case 3: goto L_0x02eb;
                case 4: goto L_0x02df;
                case 5: goto L_0x02d3;
                case 6: goto L_0x02c7;
                case 7: goto L_0x02bb;
                case 8: goto L_0x02af;
                case 9: goto L_0x02a3;
                case 10: goto L_0x0297;
                case 11: goto L_0x028b;
                case 12: goto L_0x027f;
                case 13: goto L_0x027b;
                case 14: goto L_0x026f;
                case 15: goto L_0x0263;
                case 16: goto L_0x0257;
                case 17: goto L_0x024b;
                case 18: goto L_0x023f;
                case 19: goto L_0x0233;
                case 20: goto L_0x0227;
                case 21: goto L_0x021b;
                case 22: goto L_0x020f;
                case 23: goto L_0x0203;
                case 24: goto L_0x01f7;
                case 25: goto L_0x01eb;
                case 26: goto L_0x01df;
                case 27: goto L_0x01d3;
                case 28: goto L_0x01c7;
                default: goto L_0x01bb;
            }
        L_0x01bb:
            if (r21 == 0) goto L_0x01c2
        L_0x01bd:
            r0 = 1879575519(0x70080bdf, float:1.6841725E29)
            goto L_0x05f4
        L_0x01c2:
            r0 = 1879575614(0x70080c3e, float:1.6841904E29)
            goto L_0x05f4
        L_0x01c7:
            if (r21 == 0) goto L_0x01ce
            r0 = 1879575499(0x70080bcb, float:1.6841687E29)
            goto L_0x05f4
        L_0x01ce:
            r0 = 1879575593(0x70080c29, float:1.6841865E29)
            goto L_0x05f4
        L_0x01d3:
            if (r21 == 0) goto L_0x01da
            r0 = 1879575492(0x70080bc4, float:1.6841674E29)
            goto L_0x05f4
        L_0x01da:
            r0 = 1879575585(0x70080c21, float:1.684185E29)
            goto L_0x05f4
        L_0x01df:
            if (r21 == 0) goto L_0x01e6
            r0 = 1879575496(0x70080bc8, float:1.6841682E29)
            goto L_0x05f4
        L_0x01e6:
            r0 = 1879575590(0x70080c26, float:1.684186E29)
            goto L_0x05f4
        L_0x01eb:
            if (r21 == 0) goto L_0x01f2
            r0 = 1879575491(0x70080bc3, float:1.6841672E29)
            goto L_0x05f4
        L_0x01f2:
            r0 = 1879575584(0x70080c20, float:1.6841848E29)
            goto L_0x05f4
        L_0x01f7:
            if (r21 == 0) goto L_0x01fe
            r0 = 1879575521(0x70080be1, float:1.6841729E29)
            goto L_0x05f4
        L_0x01fe:
            r0 = 1879575616(0x70080c40, float:1.6841908E29)
            goto L_0x05f4
        L_0x0203:
            if (r21 == 0) goto L_0x020a
            r0 = 1879575487(0x70080bbf, float:1.6841665E29)
            goto L_0x05f4
        L_0x020a:
            r0 = 1879575580(0x70080c1c, float:1.684184E29)
            goto L_0x05f4
        L_0x020f:
            if (r21 == 0) goto L_0x0216
            r0 = 1879575511(0x70080bd7, float:1.684171E29)
            goto L_0x05f4
        L_0x0216:
            r0 = 1879575606(0x70080c36, float:1.684189E29)
            goto L_0x05f4
        L_0x021b:
            if (r21 == 0) goto L_0x0222
            r0 = 1879575503(0x70080bcf, float:1.6841695E29)
            goto L_0x05f4
        L_0x0222:
            r0 = 1879575598(0x70080c2e, float:1.6841874E29)
            goto L_0x05f4
        L_0x0227:
            if (r21 == 0) goto L_0x022e
            r0 = 1879575515(0x70080bdb, float:1.6841717E29)
            goto L_0x05f4
        L_0x022e:
            r0 = 1879575610(0x70080c3a, float:1.6841897E29)
            goto L_0x05f4
        L_0x0233:
            if (r21 == 0) goto L_0x023a
            r0 = 1879575490(0x70080bc2, float:1.684167E29)
            goto L_0x05f4
        L_0x023a:
            r0 = 1879575583(0x70080c1f, float:1.6841846E29)
            goto L_0x05f4
        L_0x023f:
            if (r21 == 0) goto L_0x0246
            r0 = 1879575497(0x70080bc9, float:1.6841683E29)
            goto L_0x05f4
        L_0x0246:
            r0 = 1879575591(0x70080c27, float:1.6841861E29)
            goto L_0x05f4
        L_0x024b:
            if (r21 == 0) goto L_0x0252
            r0 = 1879575513(0x70080bd9, float:1.6841714E29)
            goto L_0x05f4
        L_0x0252:
            r0 = 1879575608(0x70080c38, float:1.6841893E29)
            goto L_0x05f4
        L_0x0257:
            if (r21 == 0) goto L_0x025e
            r0 = 1879575505(0x70080bd1, float:1.6841699E29)
            goto L_0x05f4
        L_0x025e:
            r0 = 1879575600(0x70080c30, float:1.6841878E29)
            goto L_0x05f4
        L_0x0263:
            if (r21 == 0) goto L_0x026a
            r0 = 1879575494(0x70080bc6, float:1.6841678E29)
            goto L_0x05f4
        L_0x026a:
            r0 = 1879575588(0x70080c24, float:1.6841855E29)
            goto L_0x05f4
        L_0x026f:
            if (r21 == 0) goto L_0x0276
            r0 = 1879575489(0x70080bc1, float:1.6841668E29)
            goto L_0x05f4
        L_0x0276:
            r0 = 1879575582(0x70080c1e, float:1.6841844E29)
            goto L_0x05f4
        L_0x027b:
            if (r21 == 0) goto L_0x01c2
            goto L_0x01bd
        L_0x027f:
            if (r21 == 0) goto L_0x0286
            r0 = 1879575493(0x70080bc5, float:1.6841676E29)
            goto L_0x05f4
        L_0x0286:
            r0 = 1879575587(0x70080c23, float:1.6841853E29)
            goto L_0x05f4
        L_0x028b:
            if (r21 == 0) goto L_0x0292
            r0 = 1879575507(0x70080bd3, float:1.6841702E29)
            goto L_0x05f4
        L_0x0292:
            r0 = 1879575602(0x70080c32, float:1.6841882E29)
            goto L_0x05f4
        L_0x0297:
            if (r21 == 0) goto L_0x029e
            r0 = 1879575522(0x70080be2, float:1.684173E29)
            goto L_0x05f4
        L_0x029e:
            r0 = 1879575617(0x70080c41, float:1.684191E29)
            goto L_0x05f4
        L_0x02a3:
            if (r21 == 0) goto L_0x02aa
            r0 = 1879575504(0x70080bd0, float:1.6841697E29)
            goto L_0x05f4
        L_0x02aa:
            r0 = 1879575599(0x70080c2f, float:1.6841876E29)
            goto L_0x05f4
        L_0x02af:
            if (r21 == 0) goto L_0x02b6
            r0 = 1879575520(0x70080be0, float:1.6841727E29)
            goto L_0x05f4
        L_0x02b6:
            r0 = 1879575615(0x70080c3f, float:1.6841906E29)
            goto L_0x05f4
        L_0x02bb:
            if (r21 == 0) goto L_0x02c2
            r0 = 1879575510(0x70080bd6, float:1.6841708E29)
            goto L_0x05f4
        L_0x02c2:
            r0 = 1879575605(0x70080c35, float:1.6841887E29)
            goto L_0x05f4
        L_0x02c7:
            if (r21 == 0) goto L_0x02ce
            r0 = 1879575498(0x70080bca, float:1.6841685E29)
            goto L_0x05f4
        L_0x02ce:
            r0 = 1879575592(0x70080c28, float:1.6841863E29)
            goto L_0x05f4
        L_0x02d3:
            if (r21 == 0) goto L_0x02da
            r0 = 1879575506(0x70080bd2, float:1.68417E29)
            goto L_0x05f4
        L_0x02da:
            r0 = 1879575601(0x70080c31, float:1.684188E29)
            goto L_0x05f4
        L_0x02df:
            if (r21 == 0) goto L_0x02e6
            r0 = 1879575516(0x70080bdc, float:1.684172E29)
            goto L_0x05f4
        L_0x02e6:
            r0 = 1879575611(0x70080c3b, float:1.6841899E29)
            goto L_0x05f4
        L_0x02eb:
            if (r21 == 0) goto L_0x02f2
            r0 = 1879575518(0x70080bde, float:1.6841723E29)
            goto L_0x05f4
        L_0x02f2:
            r0 = 1879575613(0x70080c3d, float:1.6841903E29)
            goto L_0x05f4
        L_0x02f7:
            if (r21 == 0) goto L_0x02fe
            r0 = 1879575512(0x70080bd8, float:1.6841712E29)
            goto L_0x05f4
        L_0x02fe:
            r0 = 1879575607(0x70080c37, float:1.6841891E29)
            goto L_0x05f4
        L_0x0303:
            if (r21 == 0) goto L_0x030a
            r0 = 1879575517(0x70080bdd, float:1.6841721E29)
            goto L_0x05f4
        L_0x030a:
            r0 = 1879575612(0x70080c3c, float:1.68419E29)
            goto L_0x05f4
        L_0x030f:
            if (r21 == 0) goto L_0x0316
            r0 = 1879575488(0x70080bc0, float:1.6841666E29)
            goto L_0x05f4
        L_0x0316:
            r0 = 1879575581(0x70080c1d, float:1.6841842E29)
            goto L_0x05f4
        L_0x031b:
            r20.hashCode()
            int r9 = r20.hashCode()
            switch(r9) {
                case -2007006149: goto L_0x049a;
                case -1964169151: goto L_0x048f;
                case -1893213074: goto L_0x0485;
                case -1762304422: goto L_0x047b;
                case -1570070136: goto L_0x0471;
                case -1522097648: goto L_0x0467;
                case -1512778085: goto L_0x045d;
                case -1461142052: goto L_0x0453;
                case -1024703373: goto L_0x0448;
                case -637245050: goto L_0x043c;
                case -613858791: goto L_0x042d;
                case -390972850: goto L_0x041e;
                case -337726012: goto L_0x040f;
                case -242160521: goto L_0x0400;
                case -175999878: goto L_0x03f1;
                case 14618050: goto L_0x03e2;
                case 264624306: goto L_0x03d3;
                case 365061948: goto L_0x03c4;
                case 393896703: goto L_0x03b5;
                case 514120659: goto L_0x03a6;
                case 517883033: goto L_0x0399;
                case 881565940: goto L_0x038b;
                case 967042239: goto L_0x037d;
                case 1110562654: goto L_0x036f;
                case 1457102115: goto L_0x0361;
                case 1609652513: goto L_0x0353;
                case 1610234053: goto L_0x0345;
                case 1626325763: goto L_0x0337;
                case 1815913657: goto L_0x0329;
                default: goto L_0x0325;
            }
        L_0x0325:
            r1 = r18
            goto L_0x04a4
        L_0x0329:
            java.lang.String r1 = "weather_xiaoxue.png"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x0333
            goto L_0x0325
        L_0x0333:
            r1 = 28
            goto L_0x04a4
        L_0x0337:
            java.lang.String r1 = "weather_fuchen.png"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x0341
            goto L_0x0325
        L_0x0341:
            r1 = 27
            goto L_0x04a4
        L_0x0345:
            java.lang.String r1 = "weather_mai.png"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x034f
            goto L_0x0325
        L_0x034f:
            r1 = 26
            goto L_0x04a4
        L_0x0353:
            java.lang.String r1 = "weather_xiaoyu.png"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x035d
            goto L_0x0325
        L_0x035d:
            r1 = 25
            goto L_0x04a4
        L_0x0361:
            java.lang.String r1 = "weather_leizhenyubanyoubingbao.png"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x036b
            goto L_0x0325
        L_0x036b:
            r1 = 24
            goto L_0x04a4
        L_0x036f:
            java.lang.String r1 = "weather_baoxue.png"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x0379
            goto L_0x0325
        L_0x0379:
            r1 = 23
            goto L_0x04a4
        L_0x037d:
            java.lang.String r1 = "weather_dabaoyu.png"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x0387
            goto L_0x0325
        L_0x0387:
            r1 = 22
            goto L_0x04a4
        L_0x038b:
            java.lang.String r1 = "weather_zhongxue.png"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x0395
            goto L_0x0325
        L_0x0395:
            r1 = 21
            goto L_0x04a4
        L_0x0399:
            java.lang.String r1 = "weather_zhenyu.png"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x03a3
            goto L_0x0325
        L_0x03a3:
            r1 = r7
            goto L_0x04a4
        L_0x03a6:
            java.lang.String r1 = "weather_duoyun_night.png"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x03b1
            goto L_0x0325
        L_0x03b1:
            r1 = 19
            goto L_0x04a4
        L_0x03b5:
            java.lang.String r1 = "weather_dayu.png"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x03c0
            goto L_0x0325
        L_0x03c0:
            r1 = 18
            goto L_0x04a4
        L_0x03c4:
            java.lang.String r1 = "weather_shachenbao.png"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x03cf
            goto L_0x0325
        L_0x03cf:
            r1 = 17
            goto L_0x04a4
        L_0x03d3:
            java.lang.String r1 = "weather_zhenyu_night.png"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x03de
            goto L_0x0325
        L_0x03de:
            r1 = 16
            goto L_0x04a4
        L_0x03e2:
            java.lang.String r1 = "weather_bingyu.png"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x03ed
            goto L_0x0325
        L_0x03ed:
            r1 = 15
            goto L_0x04a4
        L_0x03f1:
            java.lang.String r1 = "weather_duoyun.png"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x03fc
            goto L_0x0325
        L_0x03fc:
            r1 = 14
            goto L_0x04a4
        L_0x0400:
            java.lang.String r1 = "weather_qing.png"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x040b
            goto L_0x0325
        L_0x040b:
            r1 = 13
            goto L_0x04a4
        L_0x040f:
            java.lang.String r1 = "weather_wu.png"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x041a
            goto L_0x0325
        L_0x041a:
            r1 = 12
            goto L_0x04a4
        L_0x041e:
            java.lang.String r1 = "weather_yin.png"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x0429
            goto L_0x0325
        L_0x0429:
            r1 = 11
            goto L_0x04a4
        L_0x042d:
            java.lang.String r1 = "weather_na.png"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x0438
            goto L_0x0325
        L_0x0438:
            r1 = 10
            goto L_0x04a4
        L_0x043c:
            boolean r0 = r0.equals(r2)
            if (r0 != 0) goto L_0x0444
            goto L_0x0325
        L_0x0444:
            r1 = 9
            goto L_0x04a4
        L_0x0448:
            boolean r0 = r0.equals(r4)
            if (r0 != 0) goto L_0x0450
            goto L_0x0325
        L_0x0450:
            r1 = 8
            goto L_0x04a4
        L_0x0453:
            boolean r0 = r0.equals(r6)
            if (r0 != 0) goto L_0x045b
            goto L_0x0325
        L_0x045b:
            r1 = 7
            goto L_0x04a4
        L_0x045d:
            boolean r0 = r0.equals(r8)
            if (r0 != 0) goto L_0x0465
            goto L_0x0325
        L_0x0465:
            r1 = 6
            goto L_0x04a4
        L_0x0467:
            boolean r0 = r0.equals(r10)
            if (r0 != 0) goto L_0x046f
            goto L_0x0325
        L_0x046f:
            r1 = 5
            goto L_0x04a4
        L_0x0471:
            boolean r0 = r0.equals(r12)
            if (r0 != 0) goto L_0x0479
            goto L_0x0325
        L_0x0479:
            r1 = r11
            goto L_0x04a4
        L_0x047b:
            boolean r0 = r0.equals(r14)
            if (r0 != 0) goto L_0x0483
            goto L_0x0325
        L_0x0483:
            r1 = r13
            goto L_0x04a4
        L_0x0485:
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x048d
            goto L_0x0325
        L_0x048d:
            r1 = r15
            goto L_0x04a4
        L_0x048f:
            boolean r0 = r0.equals(r3)
            if (r0 != 0) goto L_0x0497
            goto L_0x0325
        L_0x0497:
            r1 = r16
            goto L_0x04a4
        L_0x049a:
            boolean r0 = r0.equals(r5)
            if (r0 != 0) goto L_0x04a2
            goto L_0x0325
        L_0x04a2:
            r1 = r17
        L_0x04a4:
            switch(r1) {
                case 0: goto L_0x05eb;
                case 1: goto L_0x05e1;
                case 2: goto L_0x05d7;
                case 3: goto L_0x05cd;
                case 4: goto L_0x05c3;
                case 5: goto L_0x05b9;
                case 6: goto L_0x05af;
                case 7: goto L_0x05a5;
                case 8: goto L_0x059b;
                case 9: goto L_0x058f;
                case 10: goto L_0x0583;
                case 11: goto L_0x0577;
                case 12: goto L_0x056b;
                case 13: goto L_0x0567;
                case 14: goto L_0x055b;
                case 15: goto L_0x054f;
                case 16: goto L_0x0543;
                case 17: goto L_0x0537;
                case 18: goto L_0x052b;
                case 19: goto L_0x051f;
                case 20: goto L_0x0513;
                case 21: goto L_0x0507;
                case 22: goto L_0x04fb;
                case 23: goto L_0x04ef;
                case 24: goto L_0x04e3;
                case 25: goto L_0x04d7;
                case 26: goto L_0x04cb;
                case 27: goto L_0x04bf;
                case 28: goto L_0x04b3;
                default: goto L_0x04a7;
            }
        L_0x04a7:
            if (r21 == 0) goto L_0x04ae
        L_0x04a9:
            r0 = 1879573913(0x70080599, float:1.6838691E29)
            goto L_0x05f4
        L_0x04ae:
            r0 = 1879574080(0x70080640, float:1.6839007E29)
            goto L_0x05f4
        L_0x04b3:
            if (r21 == 0) goto L_0x04ba
            r0 = 1879573861(0x70080565, float:1.6838593E29)
            goto L_0x05f4
        L_0x04ba:
            r0 = 1879574026(0x7008060a, float:1.6838905E29)
            goto L_0x05f4
        L_0x04bf:
            if (r21 == 0) goto L_0x04c6
            r0 = 1879573839(0x7008054f, float:1.6838552E29)
            goto L_0x05f4
        L_0x04c6:
            r0 = 1879574002(0x700805f2, float:1.683886E29)
            goto L_0x05f4
        L_0x04cb:
            if (r21 == 0) goto L_0x04d2
            r0 = 1879573851(0x7008055b, float:1.6838574E29)
            goto L_0x05f4
        L_0x04d2:
            r0 = 1879574016(0x70080600, float:1.6838886E29)
            goto L_0x05f4
        L_0x04d7:
            if (r21 == 0) goto L_0x04de
            r0 = 1879573836(0x7008054c, float:1.6838546E29)
            goto L_0x05f4
        L_0x04de:
            r0 = 1879573999(0x700805ef, float:1.6838854E29)
            goto L_0x05f4
        L_0x04e3:
            if (r21 == 0) goto L_0x04ea
            r0 = 1879573919(0x7008059f, float:1.6838703E29)
            goto L_0x05f4
        L_0x04ea:
            r0 = 1879574086(0x70080646, float:1.6839018E29)
            goto L_0x05f4
        L_0x04ef:
            if (r21 == 0) goto L_0x04f6
            r0 = 1879573824(0x70080540, float:1.6838523E29)
            goto L_0x05f4
        L_0x04f6:
            r0 = 1879573987(0x700805e3, float:1.6838831E29)
            goto L_0x05f4
        L_0x04fb:
            if (r21 == 0) goto L_0x0502
            r0 = 1879573888(0x70080580, float:1.6838644E29)
            goto L_0x05f4
        L_0x0502:
            r0 = 1879574055(0x70080627, float:1.683896E29)
            goto L_0x05f4
        L_0x0507:
            if (r21 == 0) goto L_0x050e
            r0 = 1879573867(0x7008056b, float:1.6838605E29)
            goto L_0x05f4
        L_0x050e:
            r0 = 1879574034(0x70080612, float:1.683892E29)
            goto L_0x05f4
        L_0x0513:
            if (r21 == 0) goto L_0x051a
            r0 = 1879573900(0x7008058c, float:1.6838667E29)
            goto L_0x05f4
        L_0x051a:
            r0 = 1879574067(0x70080633, float:1.6838982E29)
            goto L_0x05f4
        L_0x051f:
            if (r21 == 0) goto L_0x0526
            r0 = 1879573833(0x70080549, float:1.683854E29)
            goto L_0x05f4
        L_0x0526:
            r0 = 1879573996(0x700805ec, float:1.6838848E29)
            goto L_0x05f4
        L_0x052b:
            if (r21 == 0) goto L_0x0532
            r0 = 1879573854(0x7008055e, float:1.683858E29)
            goto L_0x05f4
        L_0x0532:
            r0 = 1879574019(0x70080603, float:1.6838892E29)
            goto L_0x05f4
        L_0x0537:
            if (r21 == 0) goto L_0x053e
            r0 = 1879573894(0x70080586, float:1.6838656E29)
            goto L_0x05f4
        L_0x053e:
            r0 = 1879574061(0x7008062d, float:1.6838971E29)
            goto L_0x05f4
        L_0x0543:
            if (r21 == 0) goto L_0x054a
            r0 = 1879573873(0x70080571, float:1.6838616E29)
            goto L_0x05f4
        L_0x054a:
            r0 = 1879574040(0x70080618, float:1.6838931E29)
            goto L_0x05f4
        L_0x054f:
            if (r21 == 0) goto L_0x0556
            r0 = 1879573845(0x70080555, float:1.6838563E29)
            goto L_0x05f4
        L_0x0556:
            r0 = 1879574010(0x700805fa, float:1.6838875E29)
            goto L_0x05f4
        L_0x055b:
            if (r21 == 0) goto L_0x0562
            r0 = 1879573830(0x70080546, float:1.6838535E29)
            goto L_0x05f4
        L_0x0562:
            r0 = 1879573993(0x700805e9, float:1.6838843E29)
            goto L_0x05f4
        L_0x0567:
            if (r21 == 0) goto L_0x04ae
            goto L_0x04a9
        L_0x056b:
            if (r21 == 0) goto L_0x0572
            r0 = 1879573842(0x70080552, float:1.6838557E29)
            goto L_0x05f4
        L_0x0572:
            r0 = 1879574007(0x700805f7, float:1.6838869E29)
            goto L_0x05f4
        L_0x0577:
            if (r21 == 0) goto L_0x057e
            r0 = 1879573879(0x70080577, float:1.6838627E29)
            goto L_0x05f4
        L_0x057e:
            r0 = 1879574046(0x7008061e, float:1.6838943E29)
            goto L_0x05f4
        L_0x0583:
            if (r21 == 0) goto L_0x058a
            r0 = 1879573922(0x700805a2, float:1.6838708E29)
            goto L_0x05f4
        L_0x058a:
            r0 = 1879574089(0x70080649, float:1.6839024E29)
            goto L_0x05f4
        L_0x058f:
            if (r21 == 0) goto L_0x0596
            r0 = 1879573870(0x7008056e, float:1.683861E29)
            goto L_0x05f4
        L_0x0596:
            r0 = 1879574037(0x70080615, float:1.6838926E29)
            goto L_0x05f4
        L_0x059b:
            if (r21 == 0) goto L_0x05a1
            r0 = 1879573916(0x7008059c, float:1.6838697E29)
            goto L_0x05f4
        L_0x05a1:
            r0 = 1879574083(0x70080643, float:1.6839013E29)
            goto L_0x05f4
        L_0x05a5:
            if (r21 == 0) goto L_0x05ab
            r0 = 1879573885(0x7008057d, float:1.6838639E29)
            goto L_0x05f4
        L_0x05ab:
            r0 = 1879574052(0x70080624, float:1.6838954E29)
            goto L_0x05f4
        L_0x05af:
            if (r21 == 0) goto L_0x05b5
            r0 = 1879573857(0x70080561, float:1.6838586E29)
            goto L_0x05f4
        L_0x05b5:
            r0 = 1879574022(0x70080606, float:1.6838897E29)
            goto L_0x05f4
        L_0x05b9:
            if (r21 == 0) goto L_0x05bf
            r0 = 1879573876(0x70080574, float:1.6838622E29)
            goto L_0x05f4
        L_0x05bf:
            r0 = 1879574043(0x7008061b, float:1.6838937E29)
            goto L_0x05f4
        L_0x05c3:
            if (r21 == 0) goto L_0x05c9
            r0 = 1879573903(0x7008058f, float:1.6838673E29)
            goto L_0x05f4
        L_0x05c9:
            r0 = 1879574070(0x70080636, float:1.6838988E29)
            goto L_0x05f4
        L_0x05cd:
            if (r21 == 0) goto L_0x05d3
            r0 = 1879573910(0x70080596, float:1.6838686E29)
            goto L_0x05f4
        L_0x05d3:
            r0 = 1879574077(0x7008063d, float:1.6839001E29)
            goto L_0x05f4
        L_0x05d7:
            if (r21 == 0) goto L_0x05dd
            r0 = 1879573891(0x70080583, float:1.683865E29)
            goto L_0x05f4
        L_0x05dd:
            r0 = 1879574058(0x7008062a, float:1.6838965E29)
            goto L_0x05f4
        L_0x05e1:
            if (r21 == 0) goto L_0x05e7
            r0 = 1879573907(0x70080593, float:1.683868E29)
            goto L_0x05f4
        L_0x05e7:
            r0 = 1879574074(0x7008063a, float:1.6838996E29)
            goto L_0x05f4
        L_0x05eb:
            if (r21 == 0) goto L_0x05f1
            r0 = 1879573827(0x70080543, float:1.6838529E29)
            goto L_0x05f4
        L_0x05f1:
            r0 = 1879573990(0x700805e6, float:1.6838837E29)
        L_0x05f4:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.szchoiceway.customerui.bmw.weather.WeatherIconTXZ.getWeatherIconIdBig(java.lang.String, boolean, int):int");
    }
}
