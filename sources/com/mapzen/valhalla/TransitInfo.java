package com.mapzen.valhalla;

import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONArray;
import org.json.JSONObject;

@Metadata(bv = {1, 0, 0}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u00152\u00020\u0001:\u0001\u0015B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\r\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u0007J\u0006\u0010\b\u001a\u00020\tJ\u0006\u0010\n\u001a\u00020\tJ\u0006\u0010\u000b\u001a\u00020\tJ\u0006\u0010\f\u001a\u00020\tJ\u0006\u0010\r\u001a\u00020\tJ\u0006\u0010\u000e\u001a\u00020\tJ\u0006\u0010\u000f\u001a\u00020\tJ\u0006\u0010\u0010\u001a\u00020\tJ\u0006\u0010\u0011\u001a\u00020\u0006J\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00140\u0013R\u000e\u0010\u0002\u001a\u00020\u0003X.¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Lcom/mapzen/valhalla/TransitInfo;", "", "json", "Lorg/json/JSONObject;", "(Lorg/json/JSONObject;)V", "getColor", "", "()Ljava/lang/Integer;", "getDescription", "", "getHeadsign", "getLongName", "getOnestopId", "getOperatorName", "getOperatorOnestopId", "getOperatorUrl", "getShortName", "getTextColor", "getTransitStops", "Ljava/util/ArrayList;", "Lcom/mapzen/valhalla/TransitStop;", "Companion", "library_release"}, k = 1, mv = {1, 1, 1})
/* compiled from: TransitInfo.kt */
public final class TransitInfo {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final String KEY_COLOR = "color";
    public static final String KEY_DESCRIPTION = "description";
    public static final String KEY_HEADSIGN = "headsign";
    public static final String KEY_LONG_NAME = "long_name";
    public static final String KEY_ONESTOP_ID = "onestop_id";
    public static final String KEY_OPERATOR_NAME = "operator_name";
    public static final String KEY_OPERATOR_ONESTOP_ID = "operator_onestop_id";
    public static final String KEY_OPERATOR_URL = "operator_url";
    public static final String KEY_SHORT_NAME = "short_name";
    public static final String KEY_TEXT_COLOR = "text_color";
    public static final String KEY_TRANSIT_STOPS = "transit_stops";
    private JSONObject json;

    @Metadata(bv = {1, 0, 0}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u000b\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lcom/mapzen/valhalla/TransitInfo$Companion;", "", "()V", "KEY_COLOR", "", "KEY_DESCRIPTION", "KEY_HEADSIGN", "KEY_LONG_NAME", "KEY_ONESTOP_ID", "KEY_OPERATOR_NAME", "KEY_OPERATOR_ONESTOP_ID", "KEY_OPERATOR_URL", "KEY_SHORT_NAME", "KEY_TEXT_COLOR", "KEY_TRANSIT_STOPS", "library_release"}, k = 1, mv = {1, 1, 1})
    /* compiled from: TransitInfo.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public TransitInfo(JSONObject jSONObject) {
        Intrinsics.checkParameterIsNotNull(jSONObject, "json");
        this.json = jSONObject;
    }

    public final ArrayList<TransitStop> getTransitStops() {
        JSONObject jSONObject = this.json;
        if (jSONObject == null) {
            Intrinsics.throwUninitializedPropertyAccessException("json");
        }
        JSONArray jSONArray = jSONObject.getJSONArray(KEY_TRANSIT_STOPS);
        ArrayList<TransitStop> arrayList = new ArrayList<>();
        int i = 0;
        int length = jSONArray.length() - 1;
        if (length >= 0) {
            while (true) {
                JSONObject jSONObject2 = jSONArray.getJSONObject(i);
                Intrinsics.checkExpressionValueIsNotNull(jSONObject2, "jsonArray.getJSONObject(i)");
                arrayList.add(new TransitStop(jSONObject2));
                if (i == length) {
                    break;
                }
                i++;
            }
        }
        return arrayList;
    }

    public final String getHeadsign() {
        JSONObject jSONObject = this.json;
        if (jSONObject == null) {
            Intrinsics.throwUninitializedPropertyAccessException("json");
        }
        String optString = jSONObject.optString(KEY_HEADSIGN);
        Intrinsics.checkExpressionValueIsNotNull(optString, "json.optString(KEY_HEADSIGN)");
        return optString;
    }

    public final String getLongName() {
        JSONObject jSONObject = this.json;
        if (jSONObject == null) {
            Intrinsics.throwUninitializedPropertyAccessException("json");
        }
        String optString = jSONObject.optString(KEY_LONG_NAME);
        Intrinsics.checkExpressionValueIsNotNull(optString, "json.optString(KEY_LONG_NAME)");
        return optString;
    }

    public final String getOperatorUrl() {
        JSONObject jSONObject = this.json;
        if (jSONObject == null) {
            Intrinsics.throwUninitializedPropertyAccessException("json");
        }
        String optString = jSONObject.optString(KEY_OPERATOR_URL);
        Intrinsics.checkExpressionValueIsNotNull(optString, "json.optString(KEY_OPERATOR_URL)");
        return optString;
    }

    public final String getOnestopId() {
        JSONObject jSONObject = this.json;
        if (jSONObject == null) {
            Intrinsics.throwUninitializedPropertyAccessException("json");
        }
        String optString = jSONObject.optString(KEY_ONESTOP_ID);
        Intrinsics.checkExpressionValueIsNotNull(optString, "json.optString(KEY_ONESTOP_ID)");
        return optString;
    }

    public final String getShortName() {
        JSONObject jSONObject = this.json;
        if (jSONObject == null) {
            Intrinsics.throwUninitializedPropertyAccessException("json");
        }
        String optString = jSONObject.optString(KEY_SHORT_NAME);
        Intrinsics.checkExpressionValueIsNotNull(optString, "json.optString(KEY_SHORT_NAME)");
        return optString;
    }

    public final Integer getColor() {
        JSONObject jSONObject = this.json;
        if (jSONObject == null) {
            Intrinsics.throwUninitializedPropertyAccessException("json");
        }
        String str = KEY_COLOR;
        if (!jSONObject.has(str)) {
            return null;
        }
        JSONObject jSONObject2 = this.json;
        if (jSONObject2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("json");
        }
        return Integer.valueOf(jSONObject2.getInt(str));
    }

    public final String getDescription() {
        JSONObject jSONObject = this.json;
        if (jSONObject == null) {
            Intrinsics.throwUninitializedPropertyAccessException("json");
        }
        String optString = jSONObject.optString(KEY_DESCRIPTION);
        Intrinsics.checkExpressionValueIsNotNull(optString, "json.optString(KEY_DESCRIPTION)");
        return optString;
    }

    public final int getTextColor() {
        JSONObject jSONObject = this.json;
        if (jSONObject == null) {
            Intrinsics.throwUninitializedPropertyAccessException("json");
        }
        return jSONObject.getInt(KEY_TEXT_COLOR);
    }

    public final String getOperatorOnestopId() {
        JSONObject jSONObject = this.json;
        if (jSONObject == null) {
            Intrinsics.throwUninitializedPropertyAccessException("json");
        }
        String optString = jSONObject.optString(KEY_OPERATOR_ONESTOP_ID);
        Intrinsics.checkExpressionValueIsNotNull(optString, "json.optString(KEY_OPERATOR_ONESTOP_ID)");
        return optString;
    }

    public final String getOperatorName() {
        JSONObject jSONObject = this.json;
        if (jSONObject == null) {
            Intrinsics.throwUninitializedPropertyAccessException("json");
        }
        String optString = jSONObject.optString(KEY_OPERATOR_NAME);
        Intrinsics.checkExpressionValueIsNotNull(optString, "json.optString(KEY_OPERATOR_NAME)");
        return optString;
    }
}
