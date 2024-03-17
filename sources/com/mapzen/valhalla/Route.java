package com.mapzen.valhalla;

import com.mapzen.helpers.GeometryHelper;
import com.mapzen.model.ValhallaLocation;
import com.mapzen.valhalla.Router;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONArray;
import org.json.JSONObject;

@Metadata(bv = {1, 0, 0}, d1 = {"\u0000p\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0014\n\u0002\u0010\"\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0013\b\u0016\u0018\u0000 \\2\u00020\u0001:\u0001\\B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B\u000f\b\u0016\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\u0010\u0010-\u001a\u00020.2\u0006\u0010/\u001a\u00020\u0014H\u0016J\u0010\u00100\u001a\u00020\u00182\u0006\u00101\u001a\u00020\u0016H\u0002J\u0018\u00102\u001a\u00020\u00182\u0006\u00101\u001a\u00020\u00162\u0006\u00103\u001a\u00020\"H\u0002J\b\u00104\u001a\u00020\u0018H\u0016J\u0018\u00105\u001a\u00020\u00182\u0006\u00106\u001a\u00020\u00162\u0006\u00107\u001a\u00020\u0016H\u0002J\b\u00108\u001a\u00020\u0016H\u0016J\b\u00109\u001a\u00020\u0014H\u0016J\b\u0010:\u001a\u00020\"H\u0016J\b\u0010;\u001a\u00020\tH\u0016J\u000e\u0010<\u001a\b\u0012\u0004\u0012\u00020\u00160\u0012H\u0016J\n\u0010=\u001a\u0004\u0018\u00010\u0014H\u0016J\u000f\u0010>\u001a\u0004\u0018\u00010\tH\u0016¢\u0006\u0002\u0010?J\b\u0010@\u001a\u00020\tH\u0016J\u0010\u0010A\u001a\n\u0012\u0004\u0012\u00020\u0014\u0018\u00010\u0012H\u0016J\u000e\u0010B\u001a\b\u0012\u0004\u0012\u00020\u00140CH\u0016J\b\u0010D\u001a\u00020\u0016H\u0016J\u000f\u0010E\u001a\u0004\u0018\u00010\tH\u0016¢\u0006\u0002\u0010?J\b\u0010F\u001a\u00020\u0006H\u0002J\b\u0010G\u001a\u00020\tH\u0016J\b\u0010H\u001a\u00020\tH\u0016J\b\u0010I\u001a\u00020JH\u0002J\u0010\u0010K\u001a\u00020.2\u0006\u0010\u0005\u001a\u00020\u0006H\u0002J\u001e\u0010L\u001a\b\u0012\u0004\u0012\u00020\u001a0\u00122\u000e\u0010M\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0012H\u0002J\u0010\u0010N\u001a\u00020.2\u0006\u0010\u0013\u001a\u00020JH\u0002J\b\u0010O\u001a\u00020\u0018H\u0016J\b\u0010P\u001a\u00020\u0018H\u0002J\b\u0010Q\u001a\u00020.H\u0016J\u000e\u0010R\u001a\u00020.2\u0006\u0010\u0005\u001a\u00020\u0006J\u0018\u0010S\u001a\u00020\u00162\u0006\u0010T\u001a\u00020\u001a2\u0006\u00101\u001a\u00020\u0016H\u0002J\"\u0010S\u001a\u0004\u0018\u00010\u00162\u0006\u0010T\u001a\u00020\u001a2\u0006\u00101\u001a\u00020\u00162\u0006\u0010U\u001a\u00020\"H\u0002J\u0012\u0010V\u001a\u0004\u0018\u00010\u00162\u0006\u0010W\u001a\u00020\u0016H\u0016J\b\u0010X\u001a\u00020.H\u0016J\b\u0010Y\u001a\u00020.H\u0002J\u0010\u0010Z\u001a\u00020.2\u0006\u0010[\u001a\u00020\u001aH\u0002R\u0012\u0010\b\u001a\u0004\u0018\u00010\tX\u000e¢\u0006\u0004\n\u0002\u0010\nR\u000e\u0010\u000b\u001a\u00020\tX\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\f\u001a\u00020\tX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u0016\u0010\u0011\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0012X\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u0013\u001a\n\u0012\u0004\u0012\u00020\u0014\u0018\u00010\u0012X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u0016X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0018X\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u0019\u001a\n\u0012\u0004\u0012\u00020\u001a\u0018\u00010\u0012X\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u001b\u001a\u00020\u0006X.¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u0007R\u0014\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u00140 X\u0004¢\u0006\u0002\n\u0000R\u001a\u0010!\u001a\u00020\"X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b#\u0010$\"\u0004\b%\u0010&R\u001a\u0010'\u001a\u00020(X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b)\u0010*\"\u0004\b+\u0010,¨\u0006]"}, d2 = {"Lcom/mapzen/valhalla/Route;", "", "jsonString", "", "(Ljava/lang/String;)V", "jsonObject", "Lorg/json/JSONObject;", "(Lorg/json/JSONObject;)V", "beginningRouteLostThresholdMeters", "", "Ljava/lang/Integer;", "currentInstructionIndex", "currentLeg", "getCurrentLeg", "()I", "setCurrentLeg", "(I)V", "fullString", "Ljava/util/ArrayList;", "instructions", "Lcom/mapzen/valhalla/Instruction;", "lastFixedLocation", "Lcom/mapzen/model/ValhallaLocation;", "lost", "", "poly", "Lcom/mapzen/valhalla/Node;", "rawRoute", "getRawRoute", "()Lorg/json/JSONObject;", "setRawRoute", "seenInstructions", "Ljava/util/HashSet;", "totalDistanceTravelled", "", "getTotalDistanceTravelled", "()D", "setTotalDistanceTravelled", "(D)V", "units", "Lcom/mapzen/valhalla/Router$DistanceUnits;", "getUnits", "()Lcom/mapzen/valhalla/Router$DistanceUnits;", "setUnits", "(Lcom/mapzen/valhalla/Router$DistanceUnits;)V", "addSeenInstruction", "", "instruction", "closeToDestination", "location", "closeToNextLeg", "legDistance", "foundRoute", "fuzzyEqual", "l1", "l2", "getAccurateStartPoint", "getCurrentInstruction", "getCurrentRotationBearing", "getDistanceToNextInstruction", "getGeometry", "getNextInstruction", "getNextInstructionIndex", "()Ljava/lang/Integer;", "getRemainingDistanceToDestination", "getRouteInstructions", "getSeenInstructions", "", "getStartCoordinates", "getStatus", "getSummary", "getTotalDistance", "getTotalTime", "getViaPoints", "Lorg/json/JSONArray;", "initializeDistanceUnits", "initializePolyline", "encodedFull", "initializeTurnByTurn", "isLost", "pastEndOfPoly", "rewind", "setJsonObject", "snapTo", "node", "degreeOffset", "snapToRoute", "currentLocation", "updateAllInstructions", "updateCurrentInstructionIndex", "updateDistanceTravelled", "current", "Companion", "library_release"}, k = 1, mv = {1, 1, 1})
/* compiled from: Route.kt */
public class Route {
    public static final double CLOCKWISE_DEGREES = 90.0d;
    public static final int CLOSE_TO_DESTINATION_THRESHOLD_METERS = 20;
    public static final int CLOSE_TO_NEXT_LEG_THRESHOLD_METERS = 5;
    public static final int CORRECTION_THRESHOLD_METERS = 1000;
    public static final double COUNTERCLOCKWISE_DEGREES = -90.0d;
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final String KEY_LEGS = "legs";
    public static final String KEY_LENGTH = "length";
    public static final String KEY_LOCATIONS = "locations";
    public static final String KEY_MANEUVERS = "maneuvers";
    public static final String KEY_SHAPE = "shape";
    public static final String KEY_STATUS = "status";
    public static final String KEY_SUMMARY = "summary";
    public static final String KEY_TIME = "time";
    public static final String KEY_TRIP = "trip";
    public static final String KEY_UNITS = "units";
    public static final double LOCATION_FUZZY_EQUAL_THRESHOLD_DEGREES = 1.0E-5d;
    public static final int LOST_THRESHOLD_METERS = 50;
    public static final int REVERSE_DEGREES = 180;
    private Integer beginningRouteLostThresholdMeters;
    private int currentInstructionIndex;
    private int currentLeg;
    private ArrayList<String> fullString;
    private ArrayList<Instruction> instructions;
    private ValhallaLocation lastFixedLocation;
    private boolean lost;
    private ArrayList<Node> poly;
    public JSONObject rawRoute;
    private final HashSet<Instruction> seenInstructions = new HashSet<>();
    private double totalDistanceTravelled;
    private Router.DistanceUnits units = Router.DistanceUnits.KILOMETERS;

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

    @Metadata(bv = {1, 0, 0}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\r\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006XT¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0006XT¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bXT¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u000bXT¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000bXT¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000bXT¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u000bXT¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u000bXT¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u000bXT¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u000bXT¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u000bXT¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u000bXT¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0006XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0006XT¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Lcom/mapzen/valhalla/Route$Companion;", "", "()V", "CLOCKWISE_DEGREES", "", "CLOSE_TO_DESTINATION_THRESHOLD_METERS", "", "CLOSE_TO_NEXT_LEG_THRESHOLD_METERS", "CORRECTION_THRESHOLD_METERS", "COUNTERCLOCKWISE_DEGREES", "KEY_LEGS", "", "KEY_LENGTH", "KEY_LOCATIONS", "KEY_MANEUVERS", "KEY_SHAPE", "KEY_STATUS", "KEY_SUMMARY", "KEY_TIME", "KEY_TRIP", "KEY_UNITS", "LOCATION_FUZZY_EQUAL_THRESHOLD_DEGREES", "LOST_THRESHOLD_METERS", "REVERSE_DEGREES", "library_release"}, k = 1, mv = {1, 1, 1})
    /* compiled from: Route.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public final JSONObject getRawRoute() {
        JSONObject jSONObject = this.rawRoute;
        if (jSONObject == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rawRoute");
        }
        return jSONObject;
    }

    public final void setRawRoute(JSONObject jSONObject) {
        Intrinsics.checkParameterIsNotNull(jSONObject, "<set-?>");
        this.rawRoute = jSONObject;
    }

    public final Router.DistanceUnits getUnits() {
        return this.units;
    }

    public final void setUnits(Router.DistanceUnits distanceUnits) {
        Intrinsics.checkParameterIsNotNull(distanceUnits, "<set-?>");
        this.units = distanceUnits;
    }

    public final int getCurrentLeg() {
        return this.currentLeg;
    }

    public final void setCurrentLeg(int i) {
        this.currentLeg = i;
    }

    public final double getTotalDistanceTravelled() {
        return this.totalDistanceTravelled;
    }

    public final void setTotalDistanceTravelled(double d) {
        this.totalDistanceTravelled = d;
    }

    public Route(String str) {
        Intrinsics.checkParameterIsNotNull(str, "jsonString");
        setJsonObject(new JSONObject(str));
    }

    public Route(JSONObject jSONObject) {
        Intrinsics.checkParameterIsNotNull(jSONObject, "jsonObject");
        setJsonObject(jSONObject);
    }

    public final void setJsonObject(JSONObject jSONObject) {
        Intrinsics.checkParameterIsNotNull(jSONObject, "jsonObject");
        this.rawRoute = jSONObject;
        if (foundRoute()) {
            initializeDistanceUnits(jSONObject);
            this.fullString = new ArrayList<>();
            int length = jSONObject.getJSONObject(KEY_TRIP).getJSONArray(KEY_LEGS).length() - 1;
            if (length >= 0) {
                int i = 0;
                while (true) {
                    ArrayList<String> arrayList = this.fullString;
                    if (arrayList == null) {
                        Intrinsics.throwNpe();
                    }
                    arrayList.add(jSONObject.getJSONObject(KEY_TRIP).getJSONArray(KEY_LEGS).getJSONObject(i).getString(KEY_SHAPE));
                    if (i == length) {
                        break;
                    }
                    i++;
                }
            }
            initializePolyline(this.fullString);
            JSONArray jSONArray = jSONObject.getJSONObject(KEY_TRIP).getJSONArray(KEY_LEGS).getJSONObject(0).getJSONArray(KEY_MANEUVERS);
            Intrinsics.checkExpressionValueIsNotNull(jSONArray, "jsonObject.getJSONObject…tJSONArray(KEY_MANEUVERS)");
            initializeTurnByTurn(jSONArray);
        }
    }

    private final void initializeDistanceUnits(JSONObject jSONObject) {
        String string = jSONObject.getJSONObject(KEY_TRIP).getString(KEY_UNITS);
        if (Intrinsics.areEqual((Object) string, (Object) Router.DistanceUnits.KILOMETERS.toString())) {
            this.units = Router.DistanceUnits.KILOMETERS;
        } else if (Intrinsics.areEqual((Object) string, (Object) Router.DistanceUnits.MILES.toString())) {
            this.units = Router.DistanceUnits.MILES;
        }
    }

    private final ArrayList<Node> initializePolyline(ArrayList<String> arrayList) {
        int i;
        int i2;
        ArrayList<String> arrayList2 = arrayList;
        ArrayList<Node> arrayList3 = new ArrayList<>();
        if (arrayList2 == null) {
            Intrinsics.throwNpe();
        }
        int size = arrayList.size() - 1;
        if (size >= 0) {
            int i3 = 0;
            while (true) {
                String str = arrayList2.get(i3);
                this.poly = new ArrayList<>();
                Node node = null;
                int length = str.length();
                int i4 = 0;
                int i5 = 0;
                int i6 = 0;
                while (i4 < length) {
                    int i7 = 0;
                    int i8 = 0;
                    while (true) {
                        i = i4 + 1;
                        int charAt = str.charAt(i4) - '?';
                        i7 |= (charAt & 31) << i8;
                        i8 += 5;
                        if (charAt < 32) {
                            break;
                        }
                        i4 = i;
                    }
                    int i9 = ((i7 & 1) != 0 ? ~(i7 >> 1) : i7 >> 1) + i5;
                    int i10 = 0;
                    int i11 = 0;
                    while (true) {
                        i2 = i + 1;
                        int charAt2 = str.charAt(i) - '?';
                        i10 |= (charAt2 & 31) << i11;
                        i11 += 5;
                        if (charAt2 < 32) {
                            break;
                        }
                        i = i2;
                    }
                    int i12 = i10 & 1;
                    int i13 = i10 >> 1;
                    if (i12 != 0) {
                        i13 = ~i13;
                    }
                    i6 += i13;
                    int i14 = i3;
                    Node node2 = new Node(((double) i9) / 1000000.0d, ((double) i6) / 1000000.0d);
                    ArrayList<Node> arrayList4 = this.poly;
                    if (arrayList4 == null) {
                        Intrinsics.throwNpe();
                    }
                    if (!arrayList4.isEmpty()) {
                        ArrayList<Node> arrayList5 = this.poly;
                        if (arrayList5 == null) {
                            Intrinsics.throwNpe();
                        }
                        ArrayList<Node> arrayList6 = this.poly;
                        if (arrayList6 == null) {
                            Intrinsics.throwNpe();
                        }
                        Node node3 = arrayList5.get(arrayList6.size() - 1);
                        double distanceTo = (double) node2.getLocation().distanceTo(node3.getLocation());
                        node2.setTotalDistance(node3.getTotalDistance() + distanceTo);
                        if (node != null) {
                            node.setBearing(GeometryHelper.getBearing(node.getLocation(), node2.getLocation()));
                        }
                        if (node == null) {
                            Intrinsics.throwNpe();
                        }
                        node.setLegDistance(distanceTo);
                    }
                    ArrayList<Node> arrayList7 = this.poly;
                    if (arrayList7 == null) {
                        Intrinsics.throwNpe();
                    }
                    arrayList7.add(node2);
                    arrayList3.add(node2);
                    node = node2;
                    i3 = i14;
                    i5 = i9;
                    i4 = i2;
                }
                int i15 = i3;
                if (i15 == size) {
                    break;
                }
                i3 = i15 + 1;
            }
        }
        this.poly = arrayList3;
        return arrayList3;
    }

    private final void initializeTurnByTurn(JSONArray jSONArray) {
        this.instructions = new ArrayList<>();
        int length = jSONArray.length() - 1;
        if (length >= 0) {
            int i = 0;
            while (true) {
                JSONObject jSONObject = jSONArray.getJSONObject(i);
                Intrinsics.checkExpressionValueIsNotNull(jSONObject, "instructions.getJSONObject(i)");
                Instruction instruction = new Instruction(jSONObject, this.units);
                ArrayList<Node> arrayList = this.poly;
                if (arrayList == null) {
                    Intrinsics.throwNpe();
                }
                instruction.setBearing((int) Math.ceil(arrayList.get(instruction.getBeginPolygonIndex()).getBearing()));
                instruction.setDistance(instruction.getDistance() + 0);
                ArrayList<Instruction> arrayList2 = this.instructions;
                if (arrayList2 == null) {
                    Intrinsics.throwNpe();
                }
                arrayList2.add(instruction);
                if (i != length) {
                    i++;
                } else {
                    return;
                }
            }
        }
    }

    public int getTotalDistance() {
        double d;
        double d2 = getSummary().getDouble(KEY_LENGTH);
        int i = WhenMappings.$EnumSwitchMapping$0[this.units.ordinal()];
        if (i != 1) {
            if (i == 2) {
                d = Instruction.MI_TO_METERS;
            }
            return (int) Math.round(d2);
        }
        d = (double) Instruction.KM_TO_METERS;
        d2 *= d;
        return (int) Math.round(d2);
    }

    public Integer getStatus() {
        JSONObject jSONObject = this.rawRoute;
        if (jSONObject == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rawRoute");
        }
        String str = KEY_TRIP;
        if (jSONObject.optJSONObject(str) == null) {
            return -1;
        }
        JSONObject jSONObject2 = this.rawRoute;
        if (jSONObject2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rawRoute");
        }
        return Integer.valueOf(jSONObject2.optJSONObject(str).getInt(KEY_STATUS));
    }

    public boolean foundRoute() {
        return Intrinsics.areEqual((Object) getStatus(), (Object) 0);
    }

    public int getTotalTime() {
        return getSummary().getInt(KEY_TIME);
    }

    public int getDistanceToNextInstruction() {
        return getCurrentInstruction().getLiveDistanceToNext();
    }

    public int getRemainingDistanceToDestination() {
        ArrayList<Instruction> arrayList = this.instructions;
        if (arrayList == null) {
            Intrinsics.throwNpe();
        }
        ArrayList<Instruction> arrayList2 = this.instructions;
        if (arrayList2 == null) {
            Intrinsics.throwNpe();
        }
        return arrayList.get(arrayList2.size() - 1).getLiveDistanceToNext();
    }

    public ArrayList<Instruction> getRouteInstructions() {
        ArrayList<Instruction> arrayList = this.instructions;
        if (arrayList == null) {
            return null;
        }
        int i = 0;
        if (arrayList == null) {
            Intrinsics.throwNpe();
        }
        Iterator<Instruction> it = arrayList.iterator();
        while (it.hasNext()) {
            Instruction next = it.next();
            ArrayList<Node> arrayList2 = this.poly;
            if (arrayList2 == null) {
                Intrinsics.throwNpe();
            }
            next.setLocation(arrayList2.get(next.getBeginPolygonIndex()).getLocation());
            if (next.getLiveDistanceToNext() < 0) {
                i += next.getDistance();
                next.setLiveDistanceToNext(i);
            }
        }
        return this.instructions;
    }

    public ArrayList<ValhallaLocation> getGeometry() {
        ArrayList<ValhallaLocation> arrayList = new ArrayList<>();
        ArrayList<Node> arrayList2 = this.poly;
        if (arrayList2 instanceof ArrayList) {
            Iterator<Node> it = arrayList2.iterator();
            while (it.hasNext()) {
                arrayList.add(it.next().getLocation());
            }
        }
        return arrayList;
    }

    public ValhallaLocation getStartCoordinates() {
        ValhallaLocation valhallaLocation = new ValhallaLocation();
        ArrayList<Node> arrayList = this.poly;
        if (arrayList == null) {
            Intrinsics.throwNpe();
        }
        valhallaLocation.setLatitude(arrayList.get(0).getLat());
        ArrayList<Node> arrayList2 = this.poly;
        if (arrayList2 == null) {
            Intrinsics.throwNpe();
        }
        valhallaLocation.setLongitude(arrayList2.get(0).getLng());
        return valhallaLocation;
    }

    public boolean isLost() {
        return this.lost;
    }

    private final JSONArray getViaPoints() {
        JSONObject jSONObject = this.rawRoute;
        if (jSONObject == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rawRoute");
        }
        JSONArray jSONArray = jSONObject.getJSONObject(KEY_TRIP).getJSONArray(KEY_LOCATIONS);
        Intrinsics.checkExpressionValueIsNotNull(jSONArray, "rawRoute.getJSONObject(K…tJSONArray(KEY_LOCATIONS)");
        return jSONArray;
    }

    private final JSONObject getSummary() {
        JSONObject jSONObject = this.rawRoute;
        if (jSONObject == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rawRoute");
        }
        JSONObject jSONObject2 = jSONObject.getJSONObject(KEY_TRIP).getJSONObject(KEY_SUMMARY);
        Intrinsics.checkExpressionValueIsNotNull(jSONObject2, "rawRoute.getJSONObject(K…etJSONObject(KEY_SUMMARY)");
        return jSONObject2;
    }

    public double getCurrentRotationBearing() {
        double d = (double) 360;
        ArrayList<Node> arrayList = this.poly;
        if (arrayList == null) {
            Intrinsics.throwNpe();
        }
        return d - arrayList.get(this.currentLeg).getBearing();
    }

    public void rewind() {
        this.currentLeg = 0;
    }

    public ValhallaLocation snapToRoute(ValhallaLocation valhallaLocation) {
        Intrinsics.checkParameterIsNotNull(valhallaLocation, "currentLocation");
        ArrayList<Node> arrayList = this.poly;
        if (arrayList == null) {
            Intrinsics.throwNpe();
        }
        int size = arrayList.size();
        if (pastEndOfPoly()) {
            this.lost = true;
            return null;
        } else if (closeToDestination(valhallaLocation)) {
            ArrayList<Node> arrayList2 = this.poly;
            if (arrayList2 == null) {
                Intrinsics.throwNpe();
            }
            Node node = arrayList2.get(size - 1);
            Intrinsics.checkExpressionValueIsNotNull(node, "destination");
            updateDistanceTravelled(node);
            return node.getLocation();
        } else {
            ArrayList<Node> arrayList3 = this.poly;
            if (arrayList3 == null) {
                Intrinsics.throwNpe();
            }
            Node node2 = arrayList3.get(this.currentLeg);
            Intrinsics.checkExpressionValueIsNotNull(node2, "currentNode");
            ValhallaLocation snapTo = snapTo(node2, valhallaLocation);
            this.lastFixedLocation = snapTo;
            if (snapTo == null) {
                this.lastFixedLocation = node2.getLocation();
            } else if (closeToNextLeg(node2.getLocation(), node2.getLegDistance())) {
                this.currentLeg++;
                updateCurrentInstructionIndex();
                return snapToRoute(valhallaLocation);
            }
            if (this.beginningRouteLostThresholdMeters == null) {
                ArrayList<Node> arrayList4 = this.poly;
                if (arrayList4 == null) {
                    Intrinsics.throwNpe();
                }
                this.beginningRouteLostThresholdMeters = Integer.valueOf(((int) valhallaLocation.distanceTo(arrayList4.get(0).getLocation())) + LOST_THRESHOLD_METERS);
            }
            double distanceTo = (double) valhallaLocation.distanceTo(this.lastFixedLocation);
            if (distanceTo < ((double) LOST_THRESHOLD_METERS)) {
                Intrinsics.checkExpressionValueIsNotNull(node2, "currentNode");
                updateDistanceTravelled(node2);
                return this.lastFixedLocation;
            }
            if (this.totalDistanceTravelled == 0.0d && this.currentLeg == 0) {
                Integer num = this.beginningRouteLostThresholdMeters;
                if (num == null) {
                    Intrinsics.throwNpe();
                }
                if (distanceTo < ((double) num.intValue())) {
                    return valhallaLocation;
                }
            }
            this.lost = true;
            return null;
        }
    }

    private final boolean pastEndOfPoly() {
        int i = this.currentLeg;
        ArrayList<Node> arrayList = this.poly;
        if (arrayList == null) {
            Intrinsics.throwNpe();
        }
        return i >= arrayList.size();
    }

    private final boolean closeToDestination(ValhallaLocation valhallaLocation) {
        ArrayList<Node> arrayList = this.poly;
        if (arrayList == null) {
            Intrinsics.throwNpe();
        }
        ArrayList<Node> arrayList2 = this.poly;
        if (arrayList2 == null) {
            Intrinsics.throwNpe();
        }
        if (Math.floor((double) arrayList.get(arrayList2.size() - 1).getLocation().distanceTo(valhallaLocation)) < ((double) CLOSE_TO_DESTINATION_THRESHOLD_METERS)) {
            return true;
        }
        return false;
    }

    private final boolean closeToNextLeg(ValhallaLocation valhallaLocation, double d) {
        return ((double) valhallaLocation.distanceTo(this.lastFixedLocation)) > d - ((double) CLOSE_TO_NEXT_LEG_THRESHOLD_METERS);
    }

    private final void updateDistanceTravelled(Node node) {
        double d = 0.0d;
        this.totalDistanceTravelled = 0.0d;
        int i = this.currentLeg - 1;
        if (i >= 0) {
            int i2 = 0;
            while (true) {
                ArrayList<Node> arrayList = this.poly;
                if (arrayList == null) {
                    Intrinsics.throwNpe();
                }
                d += arrayList.get(i2).getLegDistance();
                if (i2 == i) {
                    break;
                }
                i2++;
            }
        }
        if (this.lastFixedLocation != null) {
            this.totalDistanceTravelled = Math.ceil(d + ((double) node.getLocation().distanceTo(this.lastFixedLocation)));
        }
        updateAllInstructions();
    }

    public void updateAllInstructions() {
        ArrayList<Instruction> arrayList = this.instructions;
        if (arrayList == null) {
            Intrinsics.throwNpe();
        }
        Iterator<Instruction> it = arrayList.iterator();
        int i = 0;
        while (it.hasNext()) {
            Instruction next = it.next();
            i += next.getDistance();
            next.setLiveDistanceToNext(i - ((int) Math.ceil(this.totalDistanceTravelled)));
        }
    }

    private final ValhallaLocation snapTo(Node node, ValhallaLocation valhallaLocation) {
        if (fuzzyEqual(node.getLocation(), valhallaLocation)) {
            updateDistanceTravelled(node);
            valhallaLocation.setBearing((float) node.getBearing());
            return valhallaLocation;
        }
        double d = CLOCKWISE_DEGREES;
        ValhallaLocation snapTo = snapTo(node, valhallaLocation, d);
        if (snapTo == null) {
            snapTo = snapTo(node, valhallaLocation, COUNTERCLOCKWISE_DEGREES);
        }
        if (snapTo != null && Math.round((double) snapTo.distanceTo(valhallaLocation)) > ((long) CORRECTION_THRESHOLD_METERS)) {
            Node node2 = new Node(node.getLat(), node.getLng());
            node2.setBearing(node.getBearing() - ((double) REVERSE_DEGREES));
            ValhallaLocation snapTo2 = snapTo(node2, valhallaLocation, d);
            snapTo = snapTo2 == null ? snapTo(node2, valhallaLocation, COUNTERCLOCKWISE_DEGREES) : snapTo2;
        }
        double bearing = node.getBearing() - ((double) node.getLocation().bearingTo(snapTo));
        if (Math.abs(bearing) > ((double) 10) && Math.abs(bearing) < ((double) 350)) {
            snapTo = node.getLocation();
        }
        if (snapTo != null) {
            snapTo.setBearing(node.getLocation().getBearing());
        }
        if (snapTo == null) {
            Intrinsics.throwNpe();
        }
        return snapTo;
    }

    private final ValhallaLocation snapTo(Node node, ValhallaLocation valhallaLocation, double d) {
        double radians = Math.toRadians(node.getLat());
        double radians2 = Math.toRadians(node.getLng());
        double radians3 = Math.toRadians(valhallaLocation.getLatitude());
        double radians4 = Math.toRadians(valhallaLocation.getLongitude());
        double radians5 = Math.toRadians(node.getBearing());
        double radians6 = Math.toRadians(node.getBearing() + d);
        double d2 = radians4 - radians2;
        double d3 = (double) 2;
        double d4 = (radians3 - radians) / d3;
        double d5 = (d2 == 0.0d ? 0.001d : d2) / d3;
        double asin = d3 * Math.asin(Math.sqrt((Math.sin(d4) * Math.sin(d4)) + (Math.cos(radians) * Math.cos(radians3) * Math.sin(d5) * Math.sin(d5))));
        if (asin == 0.0d) {
            return null;
        }
        double acos = Math.acos((Math.sin(radians3) - (Math.sin(radians) * Math.cos(asin))) / (Math.sin(asin) * Math.cos(radians)));
        double acos2 = Math.acos((Math.sin(radians) - (Math.sin(radians3) * Math.cos(asin))) / (Math.sin(asin) * Math.cos(radians3)));
        double d6 = (double) 0;
        if (Math.sin(d2) > d6) {
            acos2 = 6.283185307179586d - acos2;
        } else {
            acos = 6.283185307179586d - acos;
        }
        double d7 = (((radians5 - acos) + 3.141592653589793d) % 6.283185307179586d) - 3.141592653589793d;
        double d8 = (((acos2 - radians6) + 3.141592653589793d) % 6.283185307179586d) - 3.141592653589793d;
        if (Math.sin(d7) == 0.0d && Math.sin(d8) == 0.0d) {
            return null;
        }
        if (Math.sin(d7) * Math.sin(d8) < d6) {
            return null;
        }
        double atan2 = Math.atan2(Math.sin(asin) * Math.sin(d7) * Math.sin(d8), Math.cos(d8) + (Math.cos(d7) * Math.cos(Math.acos(((-Math.cos(d7)) * Math.cos(d8)) + (Math.sin(d7) * Math.sin(d8) * Math.cos(asin))))));
        double asin2 = Math.asin((Math.sin(radians) * Math.cos(atan2)) + (Math.cos(radians) * Math.sin(atan2) * Math.cos(radians5)));
        ValhallaLocation valhallaLocation2 = new ValhallaLocation();
        valhallaLocation2.setLatitude(Math.toDegrees(asin2));
        valhallaLocation2.setLongitude(Math.toDegrees((((radians2 + Math.atan2((Math.sin(radians5) * Math.sin(atan2)) * Math.cos(radians), Math.cos(atan2) - (Math.sin(radians) * Math.sin(asin2)))) + 9.42477796076938d) % 6.283185307179586d) - 3.141592653589793d));
        return valhallaLocation2;
    }

    private final boolean fuzzyEqual(ValhallaLocation valhallaLocation, ValhallaLocation valhallaLocation2) {
        double abs = Math.abs(valhallaLocation.getLatitude() - valhallaLocation2.getLatitude());
        double abs2 = Math.abs(valhallaLocation.getLongitude() - valhallaLocation2.getLongitude());
        double d = LOCATION_FUZZY_EQUAL_THRESHOLD_DEGREES;
        return abs <= d && abs2 <= d;
    }

    public Set<Instruction> getSeenInstructions() {
        return this.seenInstructions;
    }

    public void addSeenInstruction(Instruction instruction) {
        Intrinsics.checkParameterIsNotNull(instruction, Instruction.KEY_INSTRUCTION);
        this.seenInstructions.add(instruction);
    }

    public Instruction getNextInstruction() {
        int i = this.currentInstructionIndex + 1;
        ArrayList<Instruction> arrayList = this.instructions;
        if (arrayList == null) {
            Intrinsics.throwNpe();
        }
        if (i >= arrayList.size()) {
            return null;
        }
        ArrayList<Instruction> arrayList2 = this.instructions;
        if (arrayList2 == null) {
            Intrinsics.throwNpe();
        }
        return arrayList2.get(i);
    }

    public Integer getNextInstructionIndex() {
        ArrayList<Instruction> arrayList = this.instructions;
        if (arrayList != null) {
            return Integer.valueOf(CollectionsKt.indexOf(arrayList, getNextInstruction()));
        }
        return null;
    }

    public Instruction getCurrentInstruction() {
        ArrayList<Instruction> arrayList = this.instructions;
        if (arrayList == null) {
            Intrinsics.throwNpe();
        }
        Instruction instruction = arrayList.get(this.currentInstructionIndex);
        Intrinsics.checkExpressionValueIsNotNull(instruction, "instructions!![currentInstructionIndex]");
        return instruction;
    }

    private final void updateCurrentInstructionIndex() {
        Instruction nextInstruction = getNextInstruction();
        if (nextInstruction != null && this.currentLeg >= nextInstruction.getBeginPolygonIndex()) {
            this.currentInstructionIndex++;
        }
    }

    public ValhallaLocation getAccurateStartPoint() {
        ArrayList<Node> arrayList = this.poly;
        if (arrayList == null) {
            Intrinsics.throwNpe();
        }
        return arrayList.get(0).getLocation();
    }
}
