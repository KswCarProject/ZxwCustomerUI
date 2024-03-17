package com.mapzen.valhalla;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONObject;

@Metadata(bv = {1, 0, 0}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0002\b\u0006\u0018\u0000 \u00142\u00020\u0001:\u0001\u0014B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0006\u0010\b\u001a\u00020\tJ\u0006\u0010\n\u001a\u00020\u000bJ\u0006\u0010\f\u001a\u00020\tJ\u0006\u0010\r\u001a\u00020\u000bJ\u0006\u0010\u000e\u001a\u00020\u000fJ\u0006\u0010\u0010\u001a\u00020\u000fJ\u0006\u0010\u0011\u001a\u00020\tJ\u0006\u0010\u0012\u001a\u00020\tJ\u0006\u0010\u0013\u001a\u00020\tR\u001a\u0010\u0002\u001a\u00020\u0003X.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\u0004¨\u0006\u0015"}, d2 = {"Lcom/mapzen/valhalla/TransitStop;", "", "json", "Lorg/json/JSONObject;", "(Lorg/json/JSONObject;)V", "getJson", "()Lorg/json/JSONObject;", "setJson", "getArrivalDateTime", "", "getAssumedSchedule", "", "getDepartureDateTime", "getIsParentStop", "getLat", "", "getLon", "getName", "getOnestopId", "getType", "Companion", "library_release"}, k = 1, mv = {1, 1, 1})
/* compiled from: TransitStop.kt */
public final class TransitStop {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final String KEY_ARRIVAL_DATE_TIME = "arrival_date_time";
    public static final String KEY_ASSUMED_SCHEDULE = "assumed_schedule";
    public static final String KEY_DEPARTURE_DATE_TIME = "departure_date_time";
    public static final String KEY_IS_PARENT_STOP = "is_parent_stop";
    public static final String KEY_LAT = "lat";
    public static final String KEY_LON = "lon";
    public static final String KEY_NAME = "name";
    public static final String KEY_ONESTOP_ID = "onestop_id";
    public static final String KEY_TYPE = "type";
    public JSONObject json;

    @Metadata(bv = {1, 0, 0}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\t\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lcom/mapzen/valhalla/TransitStop$Companion;", "", "()V", "KEY_ARRIVAL_DATE_TIME", "", "KEY_ASSUMED_SCHEDULE", "KEY_DEPARTURE_DATE_TIME", "KEY_IS_PARENT_STOP", "KEY_LAT", "KEY_LON", "KEY_NAME", "KEY_ONESTOP_ID", "KEY_TYPE", "library_release"}, k = 1, mv = {1, 1, 1})
    /* compiled from: TransitStop.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public final JSONObject getJson() {
        JSONObject jSONObject = this.json;
        if (jSONObject == null) {
            Intrinsics.throwUninitializedPropertyAccessException("json");
        }
        return jSONObject;
    }

    public final void setJson(JSONObject jSONObject) {
        Intrinsics.checkParameterIsNotNull(jSONObject, "<set-?>");
        this.json = jSONObject;
    }

    public TransitStop(JSONObject jSONObject) {
        Intrinsics.checkParameterIsNotNull(jSONObject, "json");
        this.json = jSONObject;
    }

    public final String getType() {
        JSONObject jSONObject = this.json;
        if (jSONObject == null) {
            Intrinsics.throwUninitializedPropertyAccessException("json");
        }
        String optString = jSONObject.optString(KEY_TYPE);
        Intrinsics.checkExpressionValueIsNotNull(optString, "json.optString(KEY_TYPE)");
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

    public final String getName() {
        JSONObject jSONObject = this.json;
        if (jSONObject == null) {
            Intrinsics.throwUninitializedPropertyAccessException("json");
        }
        String optString = jSONObject.optString(KEY_NAME);
        Intrinsics.checkExpressionValueIsNotNull(optString, "json.optString(KEY_NAME)");
        return optString;
    }

    public final String getDepartureDateTime() {
        JSONObject jSONObject = this.json;
        if (jSONObject == null) {
            Intrinsics.throwUninitializedPropertyAccessException("json");
        }
        String optString = jSONObject.optString(KEY_DEPARTURE_DATE_TIME);
        Intrinsics.checkExpressionValueIsNotNull(optString, "json.optString(KEY_DEPARTURE_DATE_TIME)");
        return optString;
    }

    public final boolean getIsParentStop() {
        JSONObject jSONObject = this.json;
        if (jSONObject == null) {
            Intrinsics.throwUninitializedPropertyAccessException("json");
        }
        return jSONObject.optBoolean(KEY_IS_PARENT_STOP);
    }

    public final boolean getAssumedSchedule() {
        JSONObject jSONObject = this.json;
        if (jSONObject == null) {
            Intrinsics.throwUninitializedPropertyAccessException("json");
        }
        return jSONObject.optBoolean(KEY_ASSUMED_SCHEDULE);
    }

    public final String getArrivalDateTime() {
        JSONObject jSONObject = this.json;
        if (jSONObject == null) {
            Intrinsics.throwUninitializedPropertyAccessException("json");
        }
        String optString = jSONObject.optString(KEY_ARRIVAL_DATE_TIME);
        Intrinsics.checkExpressionValueIsNotNull(optString, "json.optString(KEY_ARRIVAL_DATE_TIME)");
        return optString;
    }

    public final double getLon() {
        JSONObject jSONObject = this.json;
        if (jSONObject == null) {
            Intrinsics.throwUninitializedPropertyAccessException("json");
        }
        return jSONObject.optDouble(KEY_LON);
    }

    public final double getLat() {
        JSONObject jSONObject = this.json;
        if (jSONObject == null) {
            Intrinsics.throwUninitializedPropertyAccessException("json");
        }
        return jSONObject.optDouble(KEY_LAT);
    }
}
