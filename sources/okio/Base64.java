package okio;

import com.szchoiceway.customerui.utils.EventUtils;
import java.io.UnsupportedEncodingException;
import kotlin.UByte;

final class Base64 {
    private static final byte[] MAP = {65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, EventUtils.MCU_KEY_TFT_LONG_OPEN, EventUtils.MCU_KEY_TFT_LONG_CLOSE, EventUtils.MCU_KEY_LOUDNESS, EventUtils.MCU_KEY_CLEAR, EventUtils.MCU_KEY_DVD_MENU, 85, EventUtils.MCU_KEY_AB, EventUtils.MCU_KEY_SEARCH, EventUtils.MCU_KEY_DUAL, EventUtils.MCU_KEY_TAB, EventUtils.MCU_KEY_MAX_AC, EventUtils.MCU_KEY_RIGHT_TEMP_SUB, EventUtils.MCU_KEY_FAN_SUB, EventUtils.MCU_KEY_FAN_ADD, EventUtils.MCU_KEY_LEFT_TEMP_ADD, EventUtils.MCU_KEY_LEFT_TEMP_SUB, EventUtils.MCU_KEY_F_CAM, EventUtils.MCU_KEY1_2, EventUtils.MCU_KEY1_3, EventUtils.MCU_KEY1_4, 106, EventUtils.MCU_KEY2_10, EventUtils.MCU_KEY1_5, EventUtils.MCU_KEY1_12, EventUtils.MCU_KEY1_14, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 43, 47};
    private static final byte[] URL_MAP = {65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, EventUtils.MCU_KEY_TFT_LONG_OPEN, EventUtils.MCU_KEY_TFT_LONG_CLOSE, EventUtils.MCU_KEY_LOUDNESS, EventUtils.MCU_KEY_CLEAR, EventUtils.MCU_KEY_DVD_MENU, 85, EventUtils.MCU_KEY_AB, EventUtils.MCU_KEY_SEARCH, EventUtils.MCU_KEY_DUAL, EventUtils.MCU_KEY_TAB, EventUtils.MCU_KEY_MAX_AC, EventUtils.MCU_KEY_RIGHT_TEMP_SUB, EventUtils.MCU_KEY_FAN_SUB, EventUtils.MCU_KEY_FAN_ADD, EventUtils.MCU_KEY_LEFT_TEMP_ADD, EventUtils.MCU_KEY_LEFT_TEMP_SUB, EventUtils.MCU_KEY_F_CAM, EventUtils.MCU_KEY1_2, EventUtils.MCU_KEY1_3, EventUtils.MCU_KEY1_4, 106, EventUtils.MCU_KEY2_10, EventUtils.MCU_KEY1_5, EventUtils.MCU_KEY1_12, EventUtils.MCU_KEY1_14, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 45, EventUtils.MCU_KEY_AIR_SW};

    private Base64() {
    }

    public static byte[] decode(String str) {
        int i;
        int length = str.length();
        while (length > 0 && ((r5 = str.charAt(length - 1)) == '=' || r5 == 10 || r5 == 13 || r5 == ' ' || r5 == 9)) {
            length--;
        }
        int i2 = (int) ((((long) length) * 6) / 8);
        byte[] bArr = new byte[i2];
        int i3 = 0;
        byte b = 0;
        int i4 = 0;
        for (int i5 = 0; i5 < length; i5++) {
            char charAt = str.charAt(i5);
            if (charAt >= 'A' && charAt <= 'Z') {
                i = charAt - 'A';
            } else if (charAt >= 'a' && charAt <= 'z') {
                i = charAt - 'G';
            } else if (charAt >= '0' && charAt <= '9') {
                i = charAt + 4;
            } else if (charAt == '+' || charAt == '-') {
                i = 62;
            } else if (charAt == '/' || charAt == '_') {
                i = 63;
            } else {
                if (!(charAt == 10 || charAt == 13 || charAt == ' ' || charAt == 9)) {
                    return null;
                }
            }
            b = (b << 6) | ((byte) i);
            i3++;
            if (i3 % 4 == 0) {
                int i6 = i4 + 1;
                bArr[i4] = (byte) (b >> 16);
                int i7 = i6 + 1;
                bArr[i6] = (byte) (b >> 8);
                bArr[i7] = (byte) b;
                i4 = i7 + 1;
            }
        }
        int i8 = i3 % 4;
        if (i8 == 1) {
            return null;
        }
        if (i8 == 2) {
            bArr[i4] = (byte) ((b << 12) >> 16);
            i4++;
        } else if (i8 == 3) {
            int i9 = b << 6;
            int i10 = i4 + 1;
            bArr[i4] = (byte) (i9 >> 16);
            i4 = i10 + 1;
            bArr[i10] = (byte) (i9 >> 8);
        }
        if (i4 == i2) {
            return bArr;
        }
        byte[] bArr2 = new byte[i4];
        System.arraycopy(bArr, 0, bArr2, 0, i4);
        return bArr2;
    }

    public static String encode(byte[] bArr) {
        return encode(bArr, MAP);
    }

    public static String encodeUrl(byte[] bArr) {
        return encode(bArr, URL_MAP);
    }

    private static String encode(byte[] bArr, byte[] bArr2) {
        byte[] bArr3 = new byte[(((bArr.length + 2) / 3) * 4)];
        int length = bArr.length - (bArr.length % 3);
        int i = 0;
        for (int i2 = 0; i2 < length; i2 += 3) {
            int i3 = i + 1;
            bArr3[i] = bArr2[(bArr[i2] & UByte.MAX_VALUE) >> 2];
            int i4 = i3 + 1;
            int i5 = i2 + 1;
            bArr3[i3] = bArr2[((bArr[i2] & 3) << 4) | ((bArr[i5] & UByte.MAX_VALUE) >> 4)];
            int i6 = i4 + 1;
            int i7 = i2 + 2;
            bArr3[i4] = bArr2[((bArr[i5] & 15) << 2) | ((bArr[i7] & UByte.MAX_VALUE) >> 6)];
            i = i6 + 1;
            bArr3[i6] = bArr2[bArr[i7] & 63];
        }
        int length2 = bArr.length % 3;
        if (length2 == 1) {
            int i8 = i + 1;
            bArr3[i] = bArr2[(bArr[length] & UByte.MAX_VALUE) >> 2];
            int i9 = i8 + 1;
            bArr3[i8] = bArr2[(bArr[length] & 3) << 4];
            bArr3[i9] = 61;
            bArr3[i9 + 1] = 61;
        } else if (length2 == 2) {
            int i10 = i + 1;
            bArr3[i] = bArr2[(bArr[length] & UByte.MAX_VALUE) >> 2];
            int i11 = i10 + 1;
            int i12 = length + 1;
            bArr3[i10] = bArr2[((bArr[i12] & UByte.MAX_VALUE) >> 4) | ((bArr[length] & 3) << 4)];
            bArr3[i11] = bArr2[(bArr[i12] & 15) << 2];
            bArr3[i11 + 1] = 61;
        }
        try {
            return new String(bArr3, "US-ASCII");
        } catch (UnsupportedEncodingException e) {
            throw new AssertionError(e);
        }
    }
}
