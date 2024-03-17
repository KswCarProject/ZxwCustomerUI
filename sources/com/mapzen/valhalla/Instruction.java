package com.mapzen.valhalla;

import com.mapzen.helpers.DistanceFormatter;
import com.mapzen.model.ValhallaLocation;
import com.mapzen.valhalla.Router;
import java.util.Locale;
import kotlin.ExceptionsKt;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONException;
import org.json.JSONObject;

@Metadata(bv = {1, 0, 0}, d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u0007\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0016\u0018\u0000 >2\u00020\u0001:\u0001>B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B\u0017\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\u0013\u0010 \u001a\u00020!2\b\u0010\"\u001a\u0004\u0018\u00010\u0001H\u0002J\u0012\u0010#\u001a\u0004\u0018\u00010$2\u0006\u0010%\u001a\u00020$H\u0002J\u0006\u0010&\u001a\u00020\tJ\u0006\u0010'\u001a\u00020$J\u0006\u0010(\u001a\u00020$J\u0006\u0010)\u001a\u00020*J\u0006\u0010+\u001a\u00020\tJ\u0006\u0010,\u001a\u00020$J\b\u0010-\u001a\u0004\u0018\u00010$J\u0006\u0010.\u001a\u00020\tJ\u0006\u0010/\u001a\u00020$J\u0006\u00100\u001a\u00020\tJ\u0006\u00101\u001a\u00020\tJ\b\u00102\u001a\u0004\u0018\u000103J\b\u00104\u001a\u0004\u0018\u00010$J\u0006\u00105\u001a\u000206J\u0006\u00107\u001a\u000208J\u0006\u00109\u001a\u00020$J\u0006\u0010:\u001a\u00020$J\u0006\u0010;\u001a\u00020$J\u0010\u0010<\u001a\u00020\t2\u0006\u0010\u0002\u001a\u00020\u0003H\u0002J\b\u0010=\u001a\u00020$H\u0016R\u001a\u0010\b\u001a\u00020\tX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u001a\u0010\u000e\u001a\u00020\tX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u000b\"\u0004\b\u0010\u0010\rR\u001a\u0010\u0002\u001a\u00020\u0003X.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0004R\u001a\u0010\u0014\u001a\u00020\tX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u000b\"\u0004\b\u0016\u0010\rR\u001a\u0010\u0017\u001a\u00020\u0018X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001cR\u001a\u0010\u001d\u001a\u00020\tX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u000b\"\u0004\b\u001f\u0010\r¨\u0006?"}, d2 = {"Lcom/mapzen/valhalla/Instruction;", "", "json", "Lorg/json/JSONObject;", "(Lorg/json/JSONObject;)V", "units", "Lcom/mapzen/valhalla/Router$DistanceUnits;", "(Lorg/json/JSONObject;Lcom/mapzen/valhalla/Router$DistanceUnits;)V", "bearing", "", "getBearing", "()I", "setBearing", "(I)V", "distance", "getDistance", "setDistance", "getJson", "()Lorg/json/JSONObject;", "setJson", "liveDistanceToNext", "getLiveDistanceToNext", "setLiveDistanceToNext", "location", "Lcom/mapzen/model/ValhallaLocation;", "getLocation", "()Lcom/mapzen/model/ValhallaLocation;", "setLocation", "(Lcom/mapzen/model/ValhallaLocation;)V", "turnInstruction", "getTurnInstruction", "setTurnInstruction", "equals", "", "obj", "formatColorString", "", "color", "getBeginPolygonIndex", "getBeginStreetNames", "getDirection", "getDirectionAngle", "", "getEndPolygonIndex", "getFormattedDistance", "getHumanTurnInstruction", "getIntegerInstruction", "getName", "getRotationBearing", "getTime", "getTransitInfo", "Lcom/mapzen/valhalla/TransitInfo;", "getTransitInfoColorHex", "getTravelMode", "Lcom/mapzen/valhalla/TravelMode;", "getTravelType", "Lcom/mapzen/valhalla/TravelType;", "getVerbalPostTransitionInstruction", "getVerbalPreTransitionInstruction", "getVerbalTransitionAlertInstruction", "parseTurnInstruction", "toString", "Companion", "library_release"}, k = 1, mv = {1, 1, 1})
/* compiled from: Instruction.kt */
public class Instruction {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final String KEY_BEGIN_SHAPE_INDEX = "begin_shape_index";
    public static final String KEY_BEGIN_STREET_NAMES = "begin_street_names";
    public static final String KEY_END_SHAPE_INDEX = "end_shape_index";
    public static final String KEY_INSTRUCTION = "instruction";
    public static final String KEY_LENGTH = "length";
    public static final String KEY_STREET_NAMES = "street_names";
    public static final String KEY_TIME = "time";
    public static final String KEY_TRANSIT_INFO = "transit_info";
    public static final String KEY_TRAVEL_MODE = "travel_mode";
    public static final String KEY_TRAVEL_TYPE = "travel_type";
    public static final String KEY_TYPE = "type";
    public static final String KEY_VERBAL_POST_TRANSITION_INSTRUCTION = "verbal_post_transition_instruction";
    public static final String KEY_VERBAL_PRE_TRANSITION_INSTRUCTION = "verbal_pre_transition_instruction";
    public static final String KEY_VERBAL_TRANSITION_ALERT_INSTRUCTION = "verbal_transition_alert_instruction";
    public static final int KM_TO_METERS = 1000;
    public static final int MANEUVER_TYPE_DESTINATION = 4;
    public static final double MI_TO_METERS = 1609.344d;
    private int bearing;
    private int distance;
    public JSONObject json;
    private int liveDistanceToNext;
    private ValhallaLocation location;
    private int turnInstruction;

    @Metadata(bv = {1, 0, 0}, k = 3, mv = {1, 1, 1})
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[Router.DistanceUnits.values().length];
            $EnumSwitchMapping$0 = iArr;
            iArr[Router.DistanceUnits.KILOMETERS.ordinal()] = 1;
            iArr[Router.DistanceUnits.MILES.ordinal()] = 2;
        }
    }

    @Metadata(bv = {1, 0, 0}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u000e\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0013XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0016XT¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Lcom/mapzen/valhalla/Instruction$Companion;", "", "()V", "KEY_BEGIN_SHAPE_INDEX", "", "KEY_BEGIN_STREET_NAMES", "KEY_END_SHAPE_INDEX", "KEY_INSTRUCTION", "KEY_LENGTH", "KEY_STREET_NAMES", "KEY_TIME", "KEY_TRANSIT_INFO", "KEY_TRAVEL_MODE", "KEY_TRAVEL_TYPE", "KEY_TYPE", "KEY_VERBAL_POST_TRANSITION_INSTRUCTION", "KEY_VERBAL_PRE_TRANSITION_INSTRUCTION", "KEY_VERBAL_TRANSITION_ALERT_INSTRUCTION", "KM_TO_METERS", "", "MANEUVER_TYPE_DESTINATION", "MI_TO_METERS", "", "library_release"}, k = 1, mv = {1, 1, 1})
    /* compiled from: Instruction.kt */
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

    public final int getTurnInstruction() {
        return this.turnInstruction;
    }

    public final void setTurnInstruction(int i) {
        this.turnInstruction = i;
    }

    public final int getDistance() {
        return this.distance;
    }

    public final void setDistance(int i) {
        this.distance = i;
    }

    public final ValhallaLocation getLocation() {
        return this.location;
    }

    public final void setLocation(ValhallaLocation valhallaLocation) {
        Intrinsics.checkParameterIsNotNull(valhallaLocation, "<set-?>");
        this.location = valhallaLocation;
    }

    public final int getLiveDistanceToNext() {
        return this.liveDistanceToNext;
    }

    public final void setLiveDistanceToNext(int i) {
        this.liveDistanceToNext = i;
    }

    public final int getBearing() {
        return this.bearing;
    }

    public final void setBearing(int i) {
        this.bearing = i;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public Instruction(JSONObject jSONObject) {
        this(jSONObject, Router.DistanceUnits.KILOMETERS);
        Intrinsics.checkParameterIsNotNull(jSONObject, "json");
    }

    public Instruction(JSONObject jSONObject, Router.DistanceUnits distanceUnits) {
        Intrinsics.checkParameterIsNotNull(jSONObject, "json");
        Intrinsics.checkParameterIsNotNull(distanceUnits, Route.KEY_UNITS);
        this.location = new ValhallaLocation();
        this.liveDistanceToNext = -1;
        if (jSONObject.length() >= 6) {
            this.json = jSONObject;
            this.turnInstruction = parseTurnInstruction(jSONObject);
            double d = jSONObject.getDouble(KEY_LENGTH);
            int i = WhenMappings.$EnumSwitchMapping$0[distanceUnits.ordinal()];
            if (i == 1) {
                this.distance = (int) Math.round(d * ((double) KM_TO_METERS));
            } else if (i == 2) {
                this.distance = (int) Math.round(d * MI_TO_METERS);
            }
        } else {
            throw new JSONException("too few arguments");
        }
    }

    public final int getIntegerInstruction() {
        return this.turnInstruction;
    }

    public final String getHumanTurnInstruction() {
        JSONObject jSONObject = this.json;
        if (jSONObject == null) {
            Intrinsics.throwUninitializedPropertyAccessException("json");
        }
        return jSONObject.getString(KEY_INSTRUCTION);
    }

    public final String getBeginStreetNames() {
        JSONObject jSONObject = this.json;
        if (jSONObject == null) {
            Intrinsics.throwUninitializedPropertyAccessException("json");
        }
        String str = KEY_BEGIN_STREET_NAMES;
        String str2 = "";
        if (jSONObject.has(str)) {
            JSONObject jSONObject2 = this.json;
            if (jSONObject2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("json");
            }
            int length = jSONObject2.getJSONArray(str).length();
            int i = 0;
            int i2 = length - 1;
            if (i2 >= 0) {
                while (true) {
                    StringBuilder append = new StringBuilder().append(str2);
                    JSONObject jSONObject3 = this.json;
                    if (jSONObject3 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("json");
                    }
                    str2 = append.append(jSONObject3.getJSONArray(KEY_BEGIN_STREET_NAMES).get(i)).toString();
                    if (length > 1 && i < i2) {
                        str2 = str2 + "/";
                    }
                    if (i == i2) {
                        break;
                    }
                    i++;
                }
            }
        }
        return str2;
    }

    public final String getName() {
        JSONObject jSONObject = this.json;
        if (jSONObject == null) {
            Intrinsics.throwUninitializedPropertyAccessException("json");
        }
        String str = KEY_STREET_NAMES;
        String str2 = "";
        if (jSONObject.has(str)) {
            JSONObject jSONObject2 = this.json;
            if (jSONObject2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("json");
            }
            int length = jSONObject2.getJSONArray(str).length();
            int i = 0;
            int i2 = length - 1;
            if (i2 >= 0) {
                while (true) {
                    StringBuilder append = new StringBuilder().append(str2);
                    JSONObject jSONObject3 = this.json;
                    if (jSONObject3 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("json");
                    }
                    str2 = append.append(jSONObject3.getJSONArray(KEY_STREET_NAMES).get(i)).toString();
                    if (length > 1 && i < i2) {
                        str2 = str2 + "/";
                    }
                    if (i == i2) {
                        break;
                    }
                    i++;
                }
            }
            return str2;
        }
        JSONObject jSONObject4 = this.json;
        if (jSONObject4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("json");
        }
        String string = jSONObject4.getString(KEY_INSTRUCTION);
        return string != null ? string : str2;
    }

    public final String getFormattedDistance() {
        String format = DistanceFormatter.format(this.distance);
        Intrinsics.checkExpressionValueIsNotNull(format, "DistanceFormatter.format(distance.toInt())");
        return format;
    }

    public final int getTime() {
        JSONObject jSONObject = this.json;
        if (jSONObject == null) {
            Intrinsics.throwUninitializedPropertyAccessException("json");
        }
        return jSONObject.getInt(KEY_TIME);
    }

    public final int getBeginPolygonIndex() {
        JSONObject jSONObject = this.json;
        if (jSONObject == null) {
            Intrinsics.throwUninitializedPropertyAccessException("json");
        }
        return jSONObject.getInt(KEY_BEGIN_SHAPE_INDEX);
    }

    public final int getEndPolygonIndex() {
        JSONObject jSONObject = this.json;
        if (jSONObject == null) {
            Intrinsics.throwUninitializedPropertyAccessException("json");
        }
        return jSONObject.getInt(KEY_END_SHAPE_INDEX);
    }

    public final float getDirectionAngle() {
        double d = (double) this.bearing;
        double d2 = 315.0d;
        if (d < 315.0d || d > 360.0d) {
            double d3 = 270.0d;
            if (d < 270.0d || d >= 315.0d) {
                d2 = 225.0d;
                if (d < 225.0d || d >= 270.0d) {
                    d3 = 180.0d;
                    if (d < 180.0d || d >= 225.0d) {
                        d2 = 135.0d;
                        if (d < 135.0d || d >= 180.0d) {
                            d3 = 90.0d;
                            if (d < 90.0d || d >= 135.0d) {
                                d2 = 45.0d;
                                if (d < 45.0d || d >= 90.0d) {
                                    d3 = 0.0d;
                                    if (d < 0.0d || d >= 45.0d) {
                                        return 0.0f;
                                    }
                                }
                            }
                        }
                    }
                }
            }
            return (float) d3;
        }
        return (float) d2;
    }

    public final String getDirection() {
        int i = this.bearing;
        if (((double) i) >= 315.0d && ((double) i) >= 360.0d) {
            return "NE";
        }
        if (((double) i) >= 270.0d && ((double) i) < 315.0d) {
            return "E";
        }
        if (((double) i) >= 225.0d && ((double) i) < 270.0d) {
            return "SE";
        }
        if (((double) i) >= 180.0d && ((double) i) < 225.0d) {
            return "S";
        }
        if (((double) i) >= 135.0d && ((double) i) < 180.0d) {
            return "SW";
        }
        if (((double) i) >= 90.0d && ((double) i) < 135.0d) {
            return "W";
        }
        if (((double) i) < 45.0d || ((double) i) >= 90.0d) {
            return (((double) i) < 0.0d || ((double) i) >= 45.0d) ? "" : "N";
        }
        return "NW";
    }

    public final int getRotationBearing() {
        return 360 - this.bearing;
    }

    public String toString() {
        String str;
        try {
            str = getName();
        } catch (JSONException e) {
            System.out.println("Json exception unable to get name" + ExceptionsKt.getStackTrace(e));
            str = "";
        }
        String format = String.format(Locale.US, "Instruction: (%.5f, %.5f) %s %sLiveDistanceTo: %d", new Object[]{Double.valueOf(this.location.getLatitude()), Double.valueOf(this.location.getLongitude()), Integer.valueOf(this.turnInstruction), str, Integer.valueOf(this.liveDistanceToNext)});
        Intrinsics.checkExpressionValueIsNotNull(format, "java.lang.String.format(…name, liveDistanceToNext)");
        return format;
    }

    public boolean equals(Object obj) {
        if (obj == null || (!Intrinsics.areEqual((Object) getClass(), (Object) obj.getClass()))) {
            return false;
        }
        if (obj != null) {
            Instruction instruction = (Instruction) obj;
            if (this.turnInstruction == instruction.turnInstruction && this.bearing == instruction.bearing && this.location.getLatitude() == instruction.location.getLatitude() && this.location.getLongitude() == instruction.location.getLongitude()) {
                return true;
            }
            return false;
        }
        throw new TypeCastException("null cannot be cast to non-null type com.mapzen.valhalla.Instruction");
    }

    private final int parseTurnInstruction(JSONObject jSONObject) {
        return jSONObject.getInt(KEY_TYPE);
    }

    public final String getVerbalPreTransitionInstruction() {
        JSONObject jSONObject = this.json;
        if (jSONObject == null) {
            Intrinsics.throwUninitializedPropertyAccessException("json");
        }
        String str = KEY_VERBAL_PRE_TRANSITION_INSTRUCTION;
        if (!jSONObject.has(str)) {
            return "";
        }
        JSONObject jSONObject2 = this.json;
        if (jSONObject2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("json");
        }
        String string = jSONObject2.getString(str);
        if (string != null) {
            return string;
        }
        return "";
    }

    public final String getVerbalTransitionAlertInstruction() {
        JSONObject jSONObject = this.json;
        if (jSONObject == null) {
            Intrinsics.throwUninitializedPropertyAccessException("json");
        }
        String str = KEY_VERBAL_TRANSITION_ALERT_INSTRUCTION;
        if (!jSONObject.has(str)) {
            return "";
        }
        JSONObject jSONObject2 = this.json;
        if (jSONObject2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("json");
        }
        String string = jSONObject2.getString(str);
        if (string != null) {
            return string;
        }
        return "";
    }

    public final String getVerbalPostTransitionInstruction() {
        JSONObject jSONObject = this.json;
        if (jSONObject == null) {
            Intrinsics.throwUninitializedPropertyAccessException("json");
        }
        String str = KEY_VERBAL_POST_TRANSITION_INSTRUCTION;
        if (!jSONObject.has(str)) {
            return "";
        }
        JSONObject jSONObject2 = this.json;
        if (jSONObject2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("json");
        }
        String string = jSONObject2.getString(str);
        if (string != null) {
            return string;
        }
        return "";
    }

    public final TravelMode getTravelMode() {
        JSONObject jSONObject = this.json;
        if (jSONObject == null) {
            Intrinsics.throwUninitializedPropertyAccessException("json");
        }
        String string = jSONObject.getString(KEY_TRAVEL_MODE);
        if (Intrinsics.areEqual((Object) string, (Object) TravelMode.DRIVE.toString())) {
            return TravelMode.DRIVE;
        }
        if (Intrinsics.areEqual((Object) string, (Object) TravelMode.PEDESTRIAN.toString())) {
            return TravelMode.PEDESTRIAN;
        }
        if (Intrinsics.areEqual((Object) string, (Object) TravelMode.BICYCLE.toString())) {
            return TravelMode.BICYCLE;
        }
        if (Intrinsics.areEqual((Object) string, (Object) TravelMode.TRANSIT.toString())) {
            return TravelMode.TRANSIT;
        }
        return TravelMode.DRIVE;
    }

    public final TravelType getTravelType() {
        JSONObject jSONObject = this.json;
        if (jSONObject == null) {
            Intrinsics.throwUninitializedPropertyAccessException("json");
        }
        String string = jSONObject.getString(KEY_TRAVEL_TYPE);
        if (Intrinsics.areEqual((Object) string, (Object) TravelType.CAR.toString())) {
            return TravelType.CAR;
        }
        if (Intrinsics.areEqual((Object) string, (Object) TravelType.FOOT.toString())) {
            return TravelType.FOOT;
        }
        if (Intrinsics.areEqual((Object) string, (Object) TravelType.ROAD.toString())) {
            return TravelType.ROAD;
        }
        if (Intrinsics.areEqual((Object) string, (Object) TravelType.TRAM.toString())) {
            return TravelType.TRAM;
        }
        if (Intrinsics.areEqual((Object) string, (Object) TravelType.METRO.toString())) {
            return TravelType.METRO;
        }
        if (Intrinsics.areEqual((Object) string, (Object) TravelType.RAIL.toString())) {
            return TravelType.RAIL;
        }
        if (Intrinsics.areEqual((Object) string, (Object) TravelType.BUS.toString())) {
            return TravelType.BUS;
        }
        if (Intrinsics.areEqual((Object) string, (Object) TravelType.FERRY.toString())) {
            return TravelType.FERRY;
        }
        if (Intrinsics.areEqual((Object) string, (Object) TravelType.CABLE_CAR.toString())) {
            return TravelType.CABLE_CAR;
        }
        if (Intrinsics.areEqual((Object) string, (Object) TravelType.GONDOLA.toString())) {
            return TravelType.GONDOLA;
        }
        if (Intrinsics.areEqual((Object) string, (Object) TravelType.FUNICULAR.toString())) {
            return TravelType.FUNICULAR;
        }
        return TravelType.CAR;
    }

    public final TransitInfo getTransitInfo() {
        JSONObject jSONObject = this.json;
        if (jSONObject == null) {
            Intrinsics.throwUninitializedPropertyAccessException("json");
        }
        JSONObject optJSONObject = jSONObject.optJSONObject(KEY_TRANSIT_INFO);
        if (optJSONObject != null) {
            return new TransitInfo(optJSONObject);
        }
        return null;
    }

    public final String getTransitInfoColorHex() {
        Integer color;
        if (getTransitInfo() == null) {
            return null;
        }
        TransitInfo transitInfo = getTransitInfo();
        if (transitInfo == null || (color = transitInfo.getColor()) == null) {
            return null;
        }
        String hexString = Integer.toHexString(color.intValue());
        Intrinsics.checkExpressionValueIsNotNull(hexString, "Integer.toHexString(color)");
        return formatColorString(hexString);
    }

    private final String formatColorString(String str) {
        if (str.length() > 6) {
            return null;
        }
        if (str.length() == 6) {
            return "#" + str;
        }
        return formatColorString("0" + str);
    }
}
