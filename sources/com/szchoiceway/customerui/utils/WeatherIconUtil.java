package com.szchoiceway.customerui.utils;

public class WeatherIconUtil {
    /* JADX WARNING: Code restructure failed: missing block: B:251:0x0412, code lost:
        if (r26 != 0) goto L_0x01f2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:253:0x0418, code lost:
        if (r1 == 38) goto L_0x041a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:254:0x041a, code lost:
        return com.szchoiceway.customerui.R.drawable.audi_right_weather_sunny1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:395:?, code lost:
        return com.szchoiceway.customerui.R.drawable.pemp_id7_weather_qin;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:396:?, code lost:
        return com.szchoiceway.customerui.R.drawable.landrover_main_icon_weather_sunny1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:397:?, code lost:
        return com.szchoiceway.customerui.R.drawable.weather_qing;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:94:0x01ee, code lost:
        if (r26 != 0) goto L_0x01f2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:95:0x01f2, code lost:
        return com.szchoiceway.customerui.R.drawable.landrover_main_icon_weather_sunny1_white;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:96:0x01f7, code lost:
        if (r1 == 38) goto L_0x041a;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static int getWeatherPicID(java.lang.String r23, int r24, int r25, int r26) {
        /*
            r0 = r23
            r1 = r24
            r2 = r25
            r23.hashCode()
            int r3 = r23.hashCode()
            r4 = 19
            r5 = 5
            r6 = -1
            switch(r3) {
                case -2007006149: goto L_0x01a4;
                case -1964169151: goto L_0x0198;
                case -1893213074: goto L_0x018c;
                case -1762304422: goto L_0x0180;
                case -1570070136: goto L_0x0174;
                case -1522097648: goto L_0x0168;
                case -1512778085: goto L_0x015c;
                case -1461142052: goto L_0x0150;
                case -1024703373: goto L_0x0141;
                case -637245050: goto L_0x0132;
                case -613858791: goto L_0x0123;
                case -390972850: goto L_0x0114;
                case -337726012: goto L_0x0105;
                case -242160521: goto L_0x00f6;
                case -175999878: goto L_0x00e7;
                case 14618050: goto L_0x00d8;
                case 264624306: goto L_0x00c9;
                case 365061948: goto L_0x00ba;
                case 393896703: goto L_0x00ab;
                case 514120659: goto L_0x009d;
                case 517883033: goto L_0x008e;
                case 881565940: goto L_0x007f;
                case 967042239: goto L_0x0070;
                case 1110562654: goto L_0x0061;
                case 1457102115: goto L_0x0052;
                case 1609652513: goto L_0x0043;
                case 1610234053: goto L_0x0034;
                case 1626325763: goto L_0x0025;
                case 1815913657: goto L_0x0016;
                default: goto L_0x0014;
            }
        L_0x0014:
            goto L_0x01af
        L_0x0016:
            java.lang.String r3 = "weather_xiaoxue.png"
            boolean r0 = r0.equals(r3)
            if (r0 != 0) goto L_0x0021
            goto L_0x01af
        L_0x0021:
            r6 = 28
            goto L_0x01af
        L_0x0025:
            java.lang.String r3 = "weather_fuchen.png"
            boolean r0 = r0.equals(r3)
            if (r0 != 0) goto L_0x0030
            goto L_0x01af
        L_0x0030:
            r6 = 27
            goto L_0x01af
        L_0x0034:
            java.lang.String r3 = "weather_mai.png"
            boolean r0 = r0.equals(r3)
            if (r0 != 0) goto L_0x003f
            goto L_0x01af
        L_0x003f:
            r6 = 26
            goto L_0x01af
        L_0x0043:
            java.lang.String r3 = "weather_xiaoyu.png"
            boolean r0 = r0.equals(r3)
            if (r0 != 0) goto L_0x004e
            goto L_0x01af
        L_0x004e:
            r6 = 25
            goto L_0x01af
        L_0x0052:
            java.lang.String r3 = "weather_leizhenyubanyoubingbao.png"
            boolean r0 = r0.equals(r3)
            if (r0 != 0) goto L_0x005d
            goto L_0x01af
        L_0x005d:
            r6 = 24
            goto L_0x01af
        L_0x0061:
            java.lang.String r3 = "weather_baoxue.png"
            boolean r0 = r0.equals(r3)
            if (r0 != 0) goto L_0x006c
            goto L_0x01af
        L_0x006c:
            r6 = 23
            goto L_0x01af
        L_0x0070:
            java.lang.String r3 = "weather_dabaoyu.png"
            boolean r0 = r0.equals(r3)
            if (r0 != 0) goto L_0x007b
            goto L_0x01af
        L_0x007b:
            r6 = 22
            goto L_0x01af
        L_0x007f:
            java.lang.String r3 = "weather_zhongxue.png"
            boolean r0 = r0.equals(r3)
            if (r0 != 0) goto L_0x008a
            goto L_0x01af
        L_0x008a:
            r6 = 21
            goto L_0x01af
        L_0x008e:
            java.lang.String r3 = "weather_zhenyu.png"
            boolean r0 = r0.equals(r3)
            if (r0 != 0) goto L_0x0099
            goto L_0x01af
        L_0x0099:
            r6 = 20
            goto L_0x01af
        L_0x009d:
            java.lang.String r3 = "weather_duoyun_night.png"
            boolean r0 = r0.equals(r3)
            if (r0 != 0) goto L_0x00a8
            goto L_0x01af
        L_0x00a8:
            r6 = r4
            goto L_0x01af
        L_0x00ab:
            java.lang.String r3 = "weather_dayu.png"
            boolean r0 = r0.equals(r3)
            if (r0 != 0) goto L_0x00b6
            goto L_0x01af
        L_0x00b6:
            r6 = 18
            goto L_0x01af
        L_0x00ba:
            java.lang.String r3 = "weather_shachenbao.png"
            boolean r0 = r0.equals(r3)
            if (r0 != 0) goto L_0x00c5
            goto L_0x01af
        L_0x00c5:
            r6 = 17
            goto L_0x01af
        L_0x00c9:
            java.lang.String r3 = "weather_zhenyu_night.png"
            boolean r0 = r0.equals(r3)
            if (r0 != 0) goto L_0x00d4
            goto L_0x01af
        L_0x00d4:
            r6 = 16
            goto L_0x01af
        L_0x00d8:
            java.lang.String r3 = "weather_bingyu.png"
            boolean r0 = r0.equals(r3)
            if (r0 != 0) goto L_0x00e3
            goto L_0x01af
        L_0x00e3:
            r6 = 15
            goto L_0x01af
        L_0x00e7:
            java.lang.String r3 = "weather_duoyun.png"
            boolean r0 = r0.equals(r3)
            if (r0 != 0) goto L_0x00f2
            goto L_0x01af
        L_0x00f2:
            r6 = 14
            goto L_0x01af
        L_0x00f6:
            java.lang.String r3 = "weather_qing.png"
            boolean r0 = r0.equals(r3)
            if (r0 != 0) goto L_0x0101
            goto L_0x01af
        L_0x0101:
            r6 = 13
            goto L_0x01af
        L_0x0105:
            java.lang.String r3 = "weather_wu.png"
            boolean r0 = r0.equals(r3)
            if (r0 != 0) goto L_0x0110
            goto L_0x01af
        L_0x0110:
            r6 = 12
            goto L_0x01af
        L_0x0114:
            java.lang.String r3 = "weather_yin.png"
            boolean r0 = r0.equals(r3)
            if (r0 != 0) goto L_0x011f
            goto L_0x01af
        L_0x011f:
            r6 = 11
            goto L_0x01af
        L_0x0123:
            java.lang.String r3 = "weather_na.png"
            boolean r0 = r0.equals(r3)
            if (r0 != 0) goto L_0x012e
            goto L_0x01af
        L_0x012e:
            r6 = 10
            goto L_0x01af
        L_0x0132:
            java.lang.String r3 = "weather_zhongyu.png"
            boolean r0 = r0.equals(r3)
            if (r0 != 0) goto L_0x013d
            goto L_0x01af
        L_0x013d:
            r6 = 9
            goto L_0x01af
        L_0x0141:
            java.lang.String r3 = "weather_leizhenyu.png"
            boolean r0 = r0.equals(r3)
            if (r0 != 0) goto L_0x014c
            goto L_0x01af
        L_0x014c:
            r6 = 8
            goto L_0x01af
        L_0x0150:
            java.lang.String r3 = "weather_baoyu.png"
            boolean r0 = r0.equals(r3)
            if (r0 != 0) goto L_0x015a
            goto L_0x01af
        L_0x015a:
            r6 = 7
            goto L_0x01af
        L_0x015c:
            java.lang.String r3 = "weather_daxue.png"
            boolean r0 = r0.equals(r3)
            if (r0 != 0) goto L_0x0166
            goto L_0x01af
        L_0x0166:
            r6 = 6
            goto L_0x01af
        L_0x0168:
            java.lang.String r3 = "weather_qing_night.png"
            boolean r0 = r0.equals(r3)
            if (r0 != 0) goto L_0x0172
            goto L_0x01af
        L_0x0172:
            r6 = r5
            goto L_0x01af
        L_0x0174:
            java.lang.String r3 = "weather_yujiaxue.png"
            boolean r0 = r0.equals(r3)
            if (r0 != 0) goto L_0x017e
            goto L_0x01af
        L_0x017e:
            r6 = 4
            goto L_0x01af
        L_0x0180:
            java.lang.String r3 = "weather_zhenxue_night.png"
            boolean r0 = r0.equals(r3)
            if (r0 != 0) goto L_0x018a
            goto L_0x01af
        L_0x018a:
            r6 = 3
            goto L_0x01af
        L_0x018c:
            java.lang.String r3 = "weather_tedabaoyu.png"
            boolean r0 = r0.equals(r3)
            if (r0 != 0) goto L_0x0196
            goto L_0x01af
        L_0x0196:
            r6 = 2
            goto L_0x01af
        L_0x0198:
            java.lang.String r3 = "weather_zhenxue.png"
            boolean r0 = r0.equals(r3)
            if (r0 != 0) goto L_0x01a2
            goto L_0x01af
        L_0x01a2:
            r6 = 1
            goto L_0x01af
        L_0x01a4:
            java.lang.String r3 = "weather_yangsha.png"
            boolean r0 = r0.equals(r3)
            if (r0 != 0) goto L_0x01ae
            goto L_0x01af
        L_0x01ae:
            r6 = 0
        L_0x01af:
            r7 = 1879576116(0x70080e34, float:1.6842853E29)
            r8 = 1879575004(0x700809dc, float:1.6840752E29)
            r9 = 1879574963(0x700809b3, float:1.6840675E29)
            r10 = 1879574969(0x700809b9, float:1.6840686E29)
            r11 = 1879575002(0x700809da, float:1.6840748E29)
            r12 = 1879574956(0x700809ac, float:1.6840662E29)
            r13 = 1879574952(0x700809a8, float:1.6840654E29)
            r14 = 1879574950(0x700809a6, float:1.684065E29)
            r15 = 1879574965(0x700809b5, float:1.6840679E29)
            r16 = 1879574967(0x700809b7, float:1.6840682E29)
            r17 = 1879574985(0x700809c9, float:1.6840716E29)
            r18 = 1879574960(0x700809b0, float:1.684067E29)
            r19 = 1879574983(0x700809c7, float:1.6840713E29)
            r20 = 1879574946(0x700809a2, float:1.6840643E29)
            r21 = 1879575402(0x70080b6a, float:1.6841504E29)
            r22 = 1879575000(0x700809d8, float:1.6840745E29)
            r0 = 32
            r3 = 38
            switch(r6) {
                case 0: goto L_0x05c3;
                case 1: goto L_0x05a4;
                case 2: goto L_0x0584;
                case 3: goto L_0x0560;
                case 4: goto L_0x053c;
                case 5: goto L_0x0518;
                case 6: goto L_0x04f5;
                case 7: goto L_0x04d2;
                case 8: goto L_0x04b0;
                case 9: goto L_0x048c;
                case 10: goto L_0x046a;
                case 11: goto L_0x0446;
                case 12: goto L_0x0422;
                case 13: goto L_0x0408;
                case 14: goto L_0x03e6;
                case 15: goto L_0x03c3;
                case 16: goto L_0x039f;
                case 17: goto L_0x037b;
                case 18: goto L_0x0359;
                case 19: goto L_0x0337;
                case 20: goto L_0x0313;
                case 21: goto L_0x02ef;
                case 22: goto L_0x02cc;
                case 23: goto L_0x02a9;
                case 24: goto L_0x0287;
                case 25: goto L_0x0263;
                case 26: goto L_0x0241;
                case 27: goto L_0x021f;
                case 28: goto L_0x01fb;
                default: goto L_0x01e6;
            }
        L_0x01e6:
            if (r1 != r4) goto L_0x01ea
            goto L_0x040a
        L_0x01ea:
            if (r2 != r5) goto L_0x01f7
            if (r1 != r0) goto L_0x0414
            if (r26 != 0) goto L_0x01f2
            goto L_0x0414
        L_0x01f2:
            r0 = 1879575001(0x700809d9, float:1.6840747E29)
            goto L_0x05e1
        L_0x01f7:
            if (r1 != r3) goto L_0x041f
            goto L_0x041a
        L_0x01fb:
            if (r1 != r4) goto L_0x0202
            r0 = 1879575407(0x70080b6f, float:1.6841513E29)
            goto L_0x05e1
        L_0x0202:
            if (r2 != r5) goto L_0x0213
            if (r1 != r0) goto L_0x020e
            if (r26 != 0) goto L_0x0209
            goto L_0x020e
        L_0x0209:
            r0 = 1879574972(0x700809bc, float:1.6840692E29)
            goto L_0x05e1
        L_0x020e:
            r0 = 1879574971(0x700809bb, float:1.684069E29)
            goto L_0x05e1
        L_0x0213:
            if (r1 != r3) goto L_0x021a
            r0 = 1879573297(0x70080331, float:1.6837528E29)
            goto L_0x05e1
        L_0x021a:
            r0 = 1879576121(0x70080e39, float:1.6842862E29)
            goto L_0x05e1
        L_0x021f:
            if (r1 != r4) goto L_0x0226
            r0 = 1879575397(0x70080b65, float:1.6841495E29)
            goto L_0x05e1
        L_0x0226:
            if (r2 != r5) goto L_0x0235
            if (r1 != r0) goto L_0x0232
            if (r26 != 0) goto L_0x022d
            goto L_0x0232
        L_0x022d:
            r0 = 1879574957(0x700809ad, float:1.6840663E29)
            goto L_0x05e1
        L_0x0232:
            r0 = r12
            goto L_0x05e1
        L_0x0235:
            if (r1 != r3) goto L_0x023c
            r0 = 1879573290(0x7008032a, float:1.6837515E29)
            goto L_0x05e1
        L_0x023c:
            r0 = 1879576111(0x70080e2f, float:1.6842843E29)
            goto L_0x05e1
        L_0x0241:
            if (r1 != r4) goto L_0x0248
            r0 = 1879575400(0x70080b68, float:1.68415E29)
            goto L_0x05e1
        L_0x0248:
            if (r2 != r5) goto L_0x0257
            if (r1 != r0) goto L_0x0254
            if (r26 != 0) goto L_0x024f
            goto L_0x0254
        L_0x024f:
            r0 = 1879574964(0x700809b4, float:1.6840677E29)
            goto L_0x05e1
        L_0x0254:
            r0 = r9
            goto L_0x05e1
        L_0x0257:
            if (r1 != r3) goto L_0x025e
            r0 = 1879573293(0x7008032d, float:1.683752E29)
            goto L_0x05e1
        L_0x025e:
            r0 = 1879576114(0x70080e32, float:1.6842849E29)
            goto L_0x05e1
        L_0x0263:
            if (r1 != r4) goto L_0x026a
            r0 = 1879575408(0x70080b70, float:1.6841515E29)
            goto L_0x05e1
        L_0x026a:
            if (r2 != r5) goto L_0x027b
            if (r1 != r0) goto L_0x0276
            if (r26 != 0) goto L_0x0271
            goto L_0x0276
        L_0x0271:
            r0 = 1879574955(0x700809ab, float:1.684066E29)
            goto L_0x05e1
        L_0x0276:
            r0 = 1879574954(0x700809aa, float:1.6840658E29)
            goto L_0x05e1
        L_0x027b:
            if (r1 != r3) goto L_0x0282
            r0 = 1879573289(0x70080329, float:1.6837513E29)
            goto L_0x05e1
        L_0x0282:
            r0 = 1879576122(0x70080e3a, float:1.6842864E29)
            goto L_0x05e1
        L_0x0287:
            if (r1 != r4) goto L_0x028e
            r0 = 1879575399(0x70080b67, float:1.6841498E29)
            goto L_0x05e1
        L_0x028e:
            if (r2 != r5) goto L_0x029d
            if (r1 != r0) goto L_0x029a
            if (r26 != 0) goto L_0x0295
            goto L_0x029a
        L_0x0295:
            r0 = 1879574970(0x700809ba, float:1.6840688E29)
            goto L_0x05e1
        L_0x029a:
            r0 = r10
            goto L_0x05e1
        L_0x029d:
            if (r1 != r3) goto L_0x02a4
            r0 = 1879573316(0x70080344, float:1.6837564E29)
            goto L_0x05e1
        L_0x02a4:
            r0 = 1879576113(0x70080e31, float:1.6842847E29)
            goto L_0x05e1
        L_0x02a9:
            if (r1 != r4) goto L_0x02b0
            r0 = 1879575389(0x70080b5d, float:1.684148E29)
            goto L_0x05e1
        L_0x02b0:
            if (r2 != r5) goto L_0x02c0
            if (r1 != r0) goto L_0x02bc
            if (r26 != 0) goto L_0x02b7
            goto L_0x02bc
        L_0x02b7:
            r0 = 1879574947(0x700809a3, float:1.6840645E29)
            goto L_0x05e1
        L_0x02bc:
            r0 = r20
            goto L_0x05e1
        L_0x02c0:
            if (r1 != r3) goto L_0x02c7
            r0 = 1879573285(0x70080325, float:1.6837505E29)
            goto L_0x05e1
        L_0x02c7:
            r0 = 1879576103(0x70080e27, float:1.6842828E29)
            goto L_0x05e1
        L_0x02cc:
            if (r1 != r4) goto L_0x02d3
            r0 = 1879575392(0x70080b60, float:1.6841485E29)
            goto L_0x05e1
        L_0x02d3:
            if (r2 != r5) goto L_0x02e3
            if (r1 != r0) goto L_0x02df
            if (r26 != 0) goto L_0x02da
            goto L_0x02df
        L_0x02da:
            r0 = 1879574986(0x700809ca, float:1.6840718E29)
            goto L_0x05e1
        L_0x02df:
            r0 = r17
            goto L_0x05e1
        L_0x02e3:
            if (r1 != r3) goto L_0x02ea
            r0 = 1879573305(0x70080339, float:1.6837543E29)
            goto L_0x05e1
        L_0x02ea:
            r0 = 1879576106(0x70080e2a, float:1.6842834E29)
            goto L_0x05e1
        L_0x02ef:
            if (r1 != r4) goto L_0x02f6
            r0 = 1879575418(0x70080b7a, float:1.6841534E29)
            goto L_0x05e1
        L_0x02f6:
            if (r2 != r5) goto L_0x0307
            if (r1 != r0) goto L_0x0302
            if (r26 != 0) goto L_0x02fd
            goto L_0x0302
        L_0x02fd:
            r0 = 1879574974(0x700809be, float:1.6840696E29)
            goto L_0x05e1
        L_0x0302:
            r0 = 1879574973(0x700809bd, float:1.6840694E29)
            goto L_0x05e1
        L_0x0307:
            if (r1 != r3) goto L_0x030e
            r0 = 1879573298(0x70080332, float:1.683753E29)
            goto L_0x05e1
        L_0x030e:
            r0 = 1879576130(0x70080e42, float:1.684288E29)
            goto L_0x05e1
        L_0x0313:
            if (r1 != r4) goto L_0x031a
            r0 = 1879575417(0x70080b79, float:1.6841532E29)
            goto L_0x05e1
        L_0x031a:
            if (r2 != r5) goto L_0x032b
            if (r1 != r0) goto L_0x0326
            if (r26 != 0) goto L_0x0321
            goto L_0x0326
        L_0x0321:
            r0 = 1879574993(0x700809d1, float:1.6840731E29)
            goto L_0x05e1
        L_0x0326:
            r0 = 1879574992(0x700809d0, float:1.684073E29)
            goto L_0x05e1
        L_0x032b:
            if (r1 != r3) goto L_0x0332
            r0 = 1879573309(0x7008033d, float:1.683755E29)
            goto L_0x05e1
        L_0x0332:
            r0 = 1879576128(0x70080e40, float:1.6842875E29)
            goto L_0x05e1
        L_0x0337:
            if (r1 != r4) goto L_0x033e
            r0 = 1879575410(0x70080b72, float:1.684152E29)
            goto L_0x05e1
        L_0x033e:
            if (r2 != r5) goto L_0x034d
            if (r1 != r0) goto L_0x034a
            if (r26 != 0) goto L_0x0345
            goto L_0x034a
        L_0x0345:
            r0 = 1879574953(0x700809a9, float:1.6840656E29)
            goto L_0x05e1
        L_0x034a:
            r0 = r13
            goto L_0x05e1
        L_0x034d:
            if (r1 != r3) goto L_0x0354
            r0 = 1879573288(0x70080328, float:1.683751E29)
            goto L_0x05e1
        L_0x0354:
            r0 = 1879576110(0x70080e2e, float:1.6842841E29)
            goto L_0x05e1
        L_0x0359:
            if (r1 != r4) goto L_0x0360
            r0 = 1879575394(0x70080b62, float:1.6841489E29)
            goto L_0x05e1
        L_0x0360:
            if (r2 != r5) goto L_0x036f
            if (r1 != r0) goto L_0x036c
            if (r26 != 0) goto L_0x0367
            goto L_0x036c
        L_0x0367:
            r0 = 1879574966(0x700809b6, float:1.684068E29)
            goto L_0x05e1
        L_0x036c:
            r0 = r15
            goto L_0x05e1
        L_0x036f:
            if (r1 != r3) goto L_0x0376
            r0 = 1879573294(0x7008032e, float:1.6837522E29)
            goto L_0x05e1
        L_0x0376:
            r0 = 1879576108(0x70080e2c, float:1.6842838E29)
            goto L_0x05e1
        L_0x037b:
            if (r1 != r4) goto L_0x0382
            r0 = 1879575403(0x70080b6b, float:1.6841506E29)
            goto L_0x05e1
        L_0x0382:
            if (r2 != r5) goto L_0x0393
            if (r1 != r0) goto L_0x038e
            if (r26 != 0) goto L_0x0389
            goto L_0x038e
        L_0x0389:
            r0 = 1879574990(0x700809ce, float:1.6840726E29)
            goto L_0x05e1
        L_0x038e:
            r0 = 1879574989(0x700809cd, float:1.6840724E29)
            goto L_0x05e1
        L_0x0393:
            if (r1 != r3) goto L_0x039a
            r0 = 1879573307(0x7008033b, float:1.6837547E29)
            goto L_0x05e1
        L_0x039a:
            r0 = 1879576118(0x70080e36, float:1.6842857E29)
            goto L_0x05e1
        L_0x039f:
            if (r1 != r4) goto L_0x03a6
            r0 = 1879575413(0x70080b75, float:1.6841525E29)
            goto L_0x05e1
        L_0x03a6:
            if (r2 != r5) goto L_0x03b7
            if (r1 != r0) goto L_0x03b2
            if (r26 != 0) goto L_0x03ad
            goto L_0x03b2
        L_0x03ad:
            r0 = 1879574978(0x700809c2, float:1.6840703E29)
            goto L_0x05e1
        L_0x03b2:
            r0 = 1879574977(0x700809c1, float:1.6840701E29)
            goto L_0x05e1
        L_0x03b7:
            if (r1 != r3) goto L_0x03be
            r0 = 1879573300(0x70080334, float:1.6837533E29)
            goto L_0x05e1
        L_0x03be:
            r0 = 1879576129(0x70080e41, float:1.6842877E29)
            goto L_0x05e1
        L_0x03c3:
            if (r1 != r4) goto L_0x03ca
            r0 = 1879575391(0x70080b5f, float:1.6841483E29)
            goto L_0x05e1
        L_0x03ca:
            if (r2 != r5) goto L_0x03da
            if (r1 != r0) goto L_0x03d6
            if (r26 != 0) goto L_0x03d1
            goto L_0x03d6
        L_0x03d1:
            r0 = 1879574961(0x700809b1, float:1.6840671E29)
            goto L_0x05e1
        L_0x03d6:
            r0 = r18
            goto L_0x05e1
        L_0x03da:
            if (r1 != r3) goto L_0x03e1
            r0 = 1879573292(0x7008032c, float:1.6837518E29)
            goto L_0x05e1
        L_0x03e1:
            r0 = 1879576105(0x70080e29, float:1.6842832E29)
            goto L_0x05e1
        L_0x03e6:
            if (r1 != r4) goto L_0x03ed
            r0 = 1879575396(0x70080b64, float:1.6841493E29)
            goto L_0x05e1
        L_0x03ed:
            if (r2 != r5) goto L_0x03fc
            if (r1 != r0) goto L_0x03f9
            if (r26 != 0) goto L_0x03f4
            goto L_0x03f9
        L_0x03f4:
            r0 = 1879574951(0x700809a7, float:1.6840652E29)
            goto L_0x05e1
        L_0x03f9:
            r0 = r14
            goto L_0x05e1
        L_0x03fc:
            if (r1 != r3) goto L_0x0403
            r0 = 1879573287(0x70080327, float:1.6837509E29)
            goto L_0x05e1
        L_0x0403:
            r0 = 1879576109(0x70080e2d, float:1.684284E29)
            goto L_0x05e1
        L_0x0408:
            if (r1 != r4) goto L_0x040e
        L_0x040a:
            r0 = r21
            goto L_0x05e1
        L_0x040e:
            if (r2 != r5) goto L_0x0418
            if (r1 != r0) goto L_0x0414
            if (r26 != 0) goto L_0x01f2
        L_0x0414:
            r0 = r22
            goto L_0x05e1
        L_0x0418:
            if (r1 != r3) goto L_0x041f
        L_0x041a:
            r0 = 1879573314(0x70080342, float:1.683756E29)
            goto L_0x05e1
        L_0x041f:
            r0 = r7
            goto L_0x05e1
        L_0x0422:
            if (r1 != r4) goto L_0x0429
            r0 = 1879575406(0x70080b6e, float:1.6841512E29)
            goto L_0x05e1
        L_0x0429:
            if (r2 != r5) goto L_0x043a
            if (r1 != r0) goto L_0x0435
            if (r26 != 0) goto L_0x0430
            goto L_0x0435
        L_0x0430:
            r0 = 1879574959(0x700809af, float:1.6840667E29)
            goto L_0x05e1
        L_0x0435:
            r0 = 1879574958(0x700809ae, float:1.6840665E29)
            goto L_0x05e1
        L_0x043a:
            if (r1 != r3) goto L_0x0441
            r0 = 1879573291(0x7008032b, float:1.6837516E29)
            goto L_0x05e1
        L_0x0441:
            r0 = 1879576120(0x70080e38, float:1.684286E29)
            goto L_0x05e1
        L_0x0446:
            if (r1 != r4) goto L_0x044d
            r0 = 1879575414(0x70080b76, float:1.6841527E29)
            goto L_0x05e1
        L_0x044d:
            if (r2 != r5) goto L_0x045e
            if (r1 != r0) goto L_0x0459
            if (r26 != 0) goto L_0x0454
            goto L_0x0459
        L_0x0454:
            r0 = 1879574982(0x700809c6, float:1.684071E29)
            goto L_0x05e1
        L_0x0459:
            r0 = 1879574981(0x700809c5, float:1.6840709E29)
            goto L_0x05e1
        L_0x045e:
            if (r1 != r3) goto L_0x0465
            r0 = 1879573302(0x70080336, float:1.6837537E29)
            goto L_0x05e1
        L_0x0465:
            r0 = 1879576124(0x70080e3c, float:1.6842868E29)
            goto L_0x05e1
        L_0x046a:
            if (r1 != r4) goto L_0x0471
            r0 = 1879575405(0x70080b6d, float:1.684151E29)
            goto L_0x05e1
        L_0x0471:
            if (r2 != r5) goto L_0x0480
            if (r1 != r0) goto L_0x047d
            if (r26 != 0) goto L_0x0478
            goto L_0x047d
        L_0x0478:
            r0 = 1879575005(0x700809dd, float:1.6840754E29)
            goto L_0x05e1
        L_0x047d:
            r0 = r8
            goto L_0x05e1
        L_0x0480:
            if (r1 != r3) goto L_0x0487
            r0 = 1879573317(0x70080345, float:1.6837566E29)
            goto L_0x05e1
        L_0x0487:
            r0 = 1879576115(0x70080e33, float:1.684285E29)
            goto L_0x05e1
        L_0x048c:
            if (r1 != r4) goto L_0x0493
            r0 = 1879575419(0x70080b7b, float:1.6841536E29)
            goto L_0x05e1
        L_0x0493:
            if (r2 != r5) goto L_0x04a4
            if (r1 != r0) goto L_0x049f
            if (r26 != 0) goto L_0x049a
            goto L_0x049f
        L_0x049a:
            r0 = 1879574976(0x700809c0, float:1.68407E29)
            goto L_0x05e1
        L_0x049f:
            r0 = 1879574975(0x700809bf, float:1.6840697E29)
            goto L_0x05e1
        L_0x04a4:
            if (r1 != r3) goto L_0x04ab
            r0 = 1879573299(0x70080333, float:1.6837532E29)
            goto L_0x05e1
        L_0x04ab:
            r0 = 1879576131(0x70080e43, float:1.6842881E29)
            goto L_0x05e1
        L_0x04b0:
            if (r1 != r4) goto L_0x04b7
            r0 = 1879575398(0x70080b66, float:1.6841496E29)
            goto L_0x05e1
        L_0x04b7:
            if (r2 != r5) goto L_0x04c6
            if (r1 != r0) goto L_0x04c3
            if (r26 != 0) goto L_0x04be
            goto L_0x04c3
        L_0x04be:
            r0 = 1879575003(0x700809db, float:1.684075E29)
            goto L_0x05e1
        L_0x04c3:
            r0 = r11
            goto L_0x05e1
        L_0x04c6:
            if (r1 != r3) goto L_0x04cd
            r0 = 1879573315(0x70080343, float:1.6837562E29)
            goto L_0x05e1
        L_0x04cd:
            r0 = 1879576112(0x70080e30, float:1.6842845E29)
            goto L_0x05e1
        L_0x04d2:
            if (r1 != r4) goto L_0x04d9
            r0 = 1879575390(0x70080b5e, float:1.6841481E29)
            goto L_0x05e1
        L_0x04d9:
            if (r2 != r5) goto L_0x04e9
            if (r1 != r0) goto L_0x04e5
            if (r26 != 0) goto L_0x04e0
            goto L_0x04e5
        L_0x04e0:
            r0 = 1879574984(0x700809c8, float:1.6840714E29)
            goto L_0x05e1
        L_0x04e5:
            r0 = r19
            goto L_0x05e1
        L_0x04e9:
            if (r1 != r3) goto L_0x04f0
            r0 = 1879573304(0x70080338, float:1.6837541E29)
            goto L_0x05e1
        L_0x04f0:
            r0 = 1879576104(0x70080e28, float:1.684283E29)
            goto L_0x05e1
        L_0x04f5:
            if (r1 != r4) goto L_0x04fc
            r0 = 1879575393(0x70080b61, float:1.6841487E29)
            goto L_0x05e1
        L_0x04fc:
            if (r2 != r5) goto L_0x050c
            if (r1 != r0) goto L_0x0508
            if (r26 != 0) goto L_0x0503
            goto L_0x0508
        L_0x0503:
            r0 = 1879574968(0x700809b8, float:1.6840684E29)
            goto L_0x05e1
        L_0x0508:
            r0 = r16
            goto L_0x05e1
        L_0x050c:
            if (r1 != r3) goto L_0x0513
            r0 = 1879573295(0x7008032f, float:1.6837524E29)
            goto L_0x05e1
        L_0x0513:
            r0 = 1879576107(0x70080e2b, float:1.6842836E29)
            goto L_0x05e1
        L_0x0518:
            if (r1 != r4) goto L_0x051f
            r0 = 1879575411(0x70080b73, float:1.6841521E29)
            goto L_0x05e1
        L_0x051f:
            if (r2 != r5) goto L_0x0530
            if (r1 != r0) goto L_0x052b
            if (r26 != 0) goto L_0x0526
            goto L_0x052b
        L_0x0526:
            r0 = 1879574980(0x700809c4, float:1.6840707E29)
            goto L_0x05e1
        L_0x052b:
            r0 = 1879574979(0x700809c3, float:1.6840705E29)
            goto L_0x05e1
        L_0x0530:
            if (r1 != r3) goto L_0x0537
            r0 = 1879573301(0x70080335, float:1.6837535E29)
            goto L_0x05e1
        L_0x0537:
            r0 = 1879576117(0x70080e35, float:1.6842855E29)
            goto L_0x05e1
        L_0x053c:
            if (r1 != r4) goto L_0x0543
            r0 = 1879575415(0x70080b77, float:1.6841529E29)
            goto L_0x05e1
        L_0x0543:
            if (r2 != r5) goto L_0x0554
            if (r1 != r0) goto L_0x054f
            if (r26 != 0) goto L_0x054a
            goto L_0x054f
        L_0x054a:
            r0 = 1879574995(0x700809d3, float:1.6840735E29)
            goto L_0x05e1
        L_0x054f:
            r0 = 1879574994(0x700809d2, float:1.6840733E29)
            goto L_0x05e1
        L_0x0554:
            if (r1 != r3) goto L_0x055b
            r0 = 1879573310(0x7008033e, float:1.6837552E29)
            goto L_0x05e1
        L_0x055b:
            r0 = 1879576125(0x70080e3d, float:1.684287E29)
            goto L_0x05e1
        L_0x0560:
            if (r1 != r4) goto L_0x0567
            r0 = 1879575412(0x70080b74, float:1.6841523E29)
            goto L_0x05e1
        L_0x0567:
            if (r2 != r5) goto L_0x0578
            if (r1 != r0) goto L_0x0573
            if (r26 != 0) goto L_0x056e
            goto L_0x0573
        L_0x056e:
            r0 = 1879574999(0x700809d7, float:1.6840743E29)
            goto L_0x05e1
        L_0x0573:
            r0 = 1879574998(0x700809d6, float:1.684074E29)
            goto L_0x05e1
        L_0x0578:
            if (r1 != r3) goto L_0x057f
            r0 = 1879573312(0x70080340, float:1.6837556E29)
            goto L_0x05e1
        L_0x057f:
            r0 = 1879576127(0x70080e3f, float:1.6842874E29)
            goto L_0x05e1
        L_0x0584:
            if (r1 != r4) goto L_0x058b
            r0 = 1879575404(0x70080b6c, float:1.6841508E29)
            goto L_0x05e1
        L_0x058b:
            if (r2 != r5) goto L_0x059a
            if (r1 != r0) goto L_0x0596
            if (r26 != 0) goto L_0x0592
            goto L_0x0596
        L_0x0592:
            r0 = 1879574988(0x700809cc, float:1.6840722E29)
            goto L_0x05e1
        L_0x0596:
            r0 = 1879574987(0x700809cb, float:1.684072E29)
            goto L_0x05e1
        L_0x059a:
            if (r1 != r3) goto L_0x05a0
            r0 = 1879573306(0x7008033a, float:1.6837545E29)
            goto L_0x05e1
        L_0x05a0:
            r0 = 1879576119(0x70080e37, float:1.6842858E29)
            goto L_0x05e1
        L_0x05a4:
            if (r1 != r4) goto L_0x05aa
            r0 = 1879575416(0x70080b78, float:1.684153E29)
            goto L_0x05e1
        L_0x05aa:
            if (r2 != r5) goto L_0x05b9
            if (r1 != r0) goto L_0x05b5
            if (r26 != 0) goto L_0x05b1
            goto L_0x05b5
        L_0x05b1:
            r0 = 1879574997(0x700809d5, float:1.6840739E29)
            goto L_0x05e1
        L_0x05b5:
            r0 = 1879574996(0x700809d4, float:1.6840737E29)
            goto L_0x05e1
        L_0x05b9:
            if (r1 != r3) goto L_0x05bf
            r0 = 1879573313(0x70080341, float:1.6837558E29)
            goto L_0x05e1
        L_0x05bf:
            r0 = 1879576126(0x70080e3e, float:1.6842872E29)
            goto L_0x05e1
        L_0x05c3:
            if (r1 != r4) goto L_0x05c9
            r0 = 1879575409(0x70080b71, float:1.6841517E29)
            goto L_0x05e1
        L_0x05c9:
            if (r2 != r5) goto L_0x05d8
            if (r1 != r0) goto L_0x05d4
            if (r26 != 0) goto L_0x05d0
            goto L_0x05d4
        L_0x05d0:
            r0 = 1879574949(0x700809a5, float:1.6840648E29)
            goto L_0x05e1
        L_0x05d4:
            r0 = 1879574948(0x700809a4, float:1.6840646E29)
            goto L_0x05e1
        L_0x05d8:
            if (r1 != r3) goto L_0x05de
            r0 = 1879573286(0x70080326, float:1.6837507E29)
            goto L_0x05e1
        L_0x05de:
            r0 = 1879576123(0x70080e3b, float:1.6842866E29)
        L_0x05e1:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.szchoiceway.customerui.utils.WeatherIconUtil.getWeatherPicID(java.lang.String, int, int, int):int");
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x00ea, code lost:
        if (r1.equals("3-snow") == false) goto L_0x00e4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:6:0x0037, code lost:
        if (r1.equals("3-snow") == false) goto L_0x0031;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:72:?, code lost:
        return r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:73:?, code lost:
        return r3;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static android.graphics.drawable.Drawable getWeatherCardDetailIcon(android.content.Context r18, java.lang.String r19, boolean r20) {
        /*
            r0 = r18
            r1 = r19
            com.szchoiceway.customerui.utils.SysProviderOpt r2 = com.szchoiceway.customerui.utils.SysProviderOpt.getInstance(r18)
            java.lang.String r3 = "KESAIWEI_SYS_MODE_SELECTION"
            r4 = 16
            int r2 = r2.getRecordInteger(r3, r4)
            r3 = 5
            java.lang.String r4 = "3-snow"
            r5 = 4
            java.lang.String r6 = "3-rain"
            r7 = 3
            java.lang.String r8 = "4"
            r9 = 2
            java.lang.String r10 = "2"
            r11 = 1
            java.lang.String r12 = "1"
            r13 = 0
            java.lang.String r14 = "0"
            r15 = -1
            r16 = 0
            if (r20 == 0) goto L_0x00da
            r19.hashCode()
            int r17 = r19.hashCode()
            switch(r17) {
                case 48: goto L_0x005e;
                case 49: goto L_0x0055;
                case 50: goto L_0x004c;
                case 52: goto L_0x0043;
                case 1505137902: goto L_0x003a;
                case 1505180381: goto L_0x0033;
                default: goto L_0x0031;
            }
        L_0x0031:
            r3 = r15
            goto L_0x0066
        L_0x0033:
            boolean r1 = r1.equals(r4)
            if (r1 != 0) goto L_0x0066
            goto L_0x0031
        L_0x003a:
            boolean r1 = r1.equals(r6)
            if (r1 != 0) goto L_0x0041
            goto L_0x0031
        L_0x0041:
            r3 = r5
            goto L_0x0066
        L_0x0043:
            boolean r1 = r1.equals(r8)
            if (r1 != 0) goto L_0x004a
            goto L_0x0031
        L_0x004a:
            r3 = r7
            goto L_0x0066
        L_0x004c:
            boolean r1 = r1.equals(r10)
            if (r1 != 0) goto L_0x0053
            goto L_0x0031
        L_0x0053:
            r3 = r9
            goto L_0x0066
        L_0x0055:
            boolean r1 = r1.equals(r12)
            if (r1 != 0) goto L_0x005c
            goto L_0x0031
        L_0x005c:
            r3 = r11
            goto L_0x0066
        L_0x005e:
            boolean r1 = r1.equals(r14)
            if (r1 != 0) goto L_0x0065
            goto L_0x0031
        L_0x0065:
            r3 = r13
        L_0x0066:
            r1 = 38
            switch(r3) {
                case 0: goto L_0x00c2;
                case 1: goto L_0x00b1;
                case 2: goto L_0x00a0;
                case 3: goto L_0x008f;
                case 4: goto L_0x007e;
                case 5: goto L_0x006d;
                default: goto L_0x006b;
            }
        L_0x006b:
            goto L_0x014c
        L_0x006d:
            r3 = 1879573905(0x70080591, float:1.6838676E29)
            android.graphics.drawable.Drawable r3 = r0.getDrawable(r3)
            if (r2 != r1) goto L_0x00d6
            r1 = 1879573311(0x7008033f, float:1.6837554E29)
            android.graphics.drawable.Drawable r0 = r0.getDrawable(r1)
            goto L_0x00d2
        L_0x007e:
            r3 = 1879573883(0x7008057b, float:1.6838635E29)
            android.graphics.drawable.Drawable r3 = r0.getDrawable(r3)
            if (r2 != r1) goto L_0x00d6
            r1 = 1879573303(0x70080337, float:1.683754E29)
            android.graphics.drawable.Drawable r0 = r0.getDrawable(r1)
            goto L_0x00d2
        L_0x008f:
            r3 = 1879573924(0x700805a4, float:1.6838712E29)
            android.graphics.drawable.Drawable r3 = r0.getDrawable(r3)
            if (r2 != r1) goto L_0x00d6
            r1 = 1879573318(0x70080346, float:1.6837567E29)
            android.graphics.drawable.Drawable r0 = r0.getDrawable(r1)
            goto L_0x00d2
        L_0x00a0:
            r3 = 1879573925(0x700805a5, float:1.6838714E29)
            android.graphics.drawable.Drawable r3 = r0.getDrawable(r3)
            if (r2 != r1) goto L_0x00d6
            r1 = 1879573319(0x70080347, float:1.683757E29)
            android.graphics.drawable.Drawable r0 = r0.getDrawable(r1)
            goto L_0x00d2
        L_0x00b1:
            r3 = 1879573859(0x70080563, float:1.683859E29)
            android.graphics.drawable.Drawable r3 = r0.getDrawable(r3)
            if (r2 != r1) goto L_0x00d6
            r1 = 1879573296(0x70080330, float:1.6837526E29)
            android.graphics.drawable.Drawable r0 = r0.getDrawable(r1)
            goto L_0x00d2
        L_0x00c2:
            r3 = 1879573926(0x700805a6, float:1.6838716E29)
            android.graphics.drawable.Drawable r3 = r0.getDrawable(r3)
            if (r2 != r1) goto L_0x00d6
            r1 = 1879573320(0x70080348, float:1.6837571E29)
            android.graphics.drawable.Drawable r0 = r0.getDrawable(r1)
        L_0x00d2:
            r16 = r0
            goto L_0x014c
        L_0x00d6:
            r16 = r3
            goto L_0x014c
        L_0x00da:
            r19.hashCode()
            int r2 = r19.hashCode()
            switch(r2) {
                case 48: goto L_0x0111;
                case 49: goto L_0x0108;
                case 50: goto L_0x00ff;
                case 52: goto L_0x00f6;
                case 1505137902: goto L_0x00ed;
                case 1505180381: goto L_0x00e6;
                default: goto L_0x00e4;
            }
        L_0x00e4:
            r3 = r15
            goto L_0x0119
        L_0x00e6:
            boolean r1 = r1.equals(r4)
            if (r1 != 0) goto L_0x0119
            goto L_0x00e4
        L_0x00ed:
            boolean r1 = r1.equals(r6)
            if (r1 != 0) goto L_0x00f4
            goto L_0x00e4
        L_0x00f4:
            r3 = r5
            goto L_0x0119
        L_0x00f6:
            boolean r1 = r1.equals(r8)
            if (r1 != 0) goto L_0x00fd
            goto L_0x00e4
        L_0x00fd:
            r3 = r7
            goto L_0x0119
        L_0x00ff:
            boolean r1 = r1.equals(r10)
            if (r1 != 0) goto L_0x0106
            goto L_0x00e4
        L_0x0106:
            r3 = r9
            goto L_0x0119
        L_0x0108:
            boolean r1 = r1.equals(r12)
            if (r1 != 0) goto L_0x010f
            goto L_0x00e4
        L_0x010f:
            r3 = r11
            goto L_0x0119
        L_0x0111:
            boolean r1 = r1.equals(r14)
            if (r1 != 0) goto L_0x0118
            goto L_0x00e4
        L_0x0118:
            r3 = r13
        L_0x0119:
            switch(r3) {
                case 0: goto L_0x0145;
                case 1: goto L_0x013d;
                case 2: goto L_0x0135;
                case 3: goto L_0x012d;
                case 4: goto L_0x0125;
                case 5: goto L_0x011d;
                default: goto L_0x011c;
            }
        L_0x011c:
            goto L_0x014c
        L_0x011d:
            r1 = 1879574072(0x70080638, float:1.6838992E29)
            android.graphics.drawable.Drawable r16 = r0.getDrawable(r1)
            goto L_0x014c
        L_0x0125:
            r1 = 1879574050(0x70080622, float:1.683895E29)
            android.graphics.drawable.Drawable r16 = r0.getDrawable(r1)
            goto L_0x014c
        L_0x012d:
            r1 = 1879574091(0x7008064b, float:1.6839028E29)
            android.graphics.drawable.Drawable r16 = r0.getDrawable(r1)
            goto L_0x014c
        L_0x0135:
            r1 = 1879574092(0x7008064c, float:1.683903E29)
            android.graphics.drawable.Drawable r16 = r0.getDrawable(r1)
            goto L_0x014c
        L_0x013d:
            r1 = 1879574024(0x70080608, float:1.6838901E29)
            android.graphics.drawable.Drawable r16 = r0.getDrawable(r1)
            goto L_0x014c
        L_0x0145:
            r1 = 1879574093(0x7008064d, float:1.6839031E29)
            android.graphics.drawable.Drawable r16 = r0.getDrawable(r1)
        L_0x014c:
            return r16
        */
        throw new UnsupportedOperationException("Method not decompiled: com.szchoiceway.customerui.utils.WeatherIconUtil.getWeatherCardDetailIcon(android.content.Context, java.lang.String, boolean):android.graphics.drawable.Drawable");
    }
}
