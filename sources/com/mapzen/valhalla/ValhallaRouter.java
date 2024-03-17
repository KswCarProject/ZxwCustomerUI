package com.mapzen.valhalla;

import com.mapzen.valhalla.JSON;
import com.mapzen.valhalla.Router;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import retrofit2.Call;

@Metadata(bv = {1, 0, 0}, d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0013\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\b\u0016\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0010\u001a\u00020\u0001H\u0016J\u0010\u0010\u0011\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0012H\u0016J\b\u0010\u0013\u001a\u0004\u0018\u00010\bJ\b\u0010\u0014\u001a\u00020\u0015H\u0016J\b\u0010\u0016\u001a\u00020\u0001H\u0016J\u0010\u0010\u0017\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0004H\u0016J\u0010\u0010\u0018\u001a\u00020\u00012\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J\b\u0010\u0019\u001a\u00020\u0001H\u0016J\u0010\u0010\u001a\u001a\u00020\u00012\u0006\u0010\u001b\u001a\u00020\u0006H\u0016J\u0010\u0010\u001c\u001a\u00020\u00012\u0006\u0010\u0007\u001a\u00020\u001dH\u0016J\u0010\u0010\u001e\u001a\u00020\u00012\u0006\u0010\u001f\u001a\u00020 H\u0016J\u0018\u0010\u001e\u001a\u00020\u00012\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\"H\u0016J8\u0010\u001e\u001a\u00020\u00012\u0006\u0010\u001f\u001a\u00020 2\b\u0010#\u001a\u0004\u0018\u00010\b2\b\u0010$\u001a\u0004\u0018\u00010\b2\b\u0010%\u001a\u0004\u0018\u00010\b2\b\u0010&\u001a\u0004\u0018\u00010\bH\u0016J\b\u0010'\u001a\u00020\u0001H\u0016J\b\u0010(\u001a\u00020\u0001H\u0016R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u000e¢\u0006\u0002\n\u0000¨\u0006)"}, d2 = {"Lcom/mapzen/valhalla/ValhallaRouter;", "Lcom/mapzen/valhalla/Router;", "()V", "callback", "Lcom/mapzen/valhalla/RouteCallback;", "httpHandler", "Lcom/mapzen/valhalla/HttpHandler;", "language", "", "locations", "Ljava/util/ArrayList;", "Lcom/mapzen/valhalla/JSON$Location;", "type", "Lcom/mapzen/valhalla/Router$Type;", "units", "Lcom/mapzen/valhalla/Router$DistanceUnits;", "clearLocations", "fetch", "Lretrofit2/Call;", "getDefaultLanguage", "getJSONRequest", "Lcom/mapzen/valhalla/JSON;", "setBiking", "setCallback", "setDistanceUnits", "setDriving", "setHttpHandler", "handler", "setLanguage", "Lcom/mapzen/valhalla/Router$Language;", "setLocation", "point", "", "heading", "", "name", "street", "city", "state", "setMultimodal", "setWalking", "library_release"}, k = 1, mv = {1, 1, 1})
/* compiled from: ValhallaRouter.kt */
public class ValhallaRouter implements Router {
    /* access modifiers changed from: private */
    public RouteCallback callback;
    private HttpHandler httpHandler;
    private String language;
    private final ArrayList<JSON.Location> locations = new ArrayList<>();
    private Router.Type type = Router.Type.DRIVING;
    private Router.DistanceUnits units = Router.DistanceUnits.KILOMETERS;

    public Router setHttpHandler(HttpHandler httpHandler2) {
        Intrinsics.checkParameterIsNotNull(httpHandler2, "handler");
        this.httpHandler = httpHandler2;
        return this;
    }

    public Router setLanguage(Router.Language language2) {
        Intrinsics.checkParameterIsNotNull(language2, "language");
        this.language = language2.toString();
        return this;
    }

    public Router setWalking() {
        this.type = Router.Type.WALKING;
        return this;
    }

    public Router setDriving() {
        this.type = Router.Type.DRIVING;
        return this;
    }

    public Router setBiking() {
        this.type = Router.Type.BIKING;
        return this;
    }

    public Router setMultimodal() {
        this.type = Router.Type.MULTIMODAL;
        return this;
    }

    public Router setLocation(double[] dArr) {
        Intrinsics.checkParameterIsNotNull(dArr, "point");
        this.locations.add(new JSON.Location(dArr[0], dArr[1]));
        return this;
    }

    public Router setLocation(double[] dArr, int i) {
        Intrinsics.checkParameterIsNotNull(dArr, "point");
        this.locations.add(new JSON.Location(dArr[0], dArr[1], i));
        return this;
    }

    public Router setLocation(double[] dArr, String str, String str2, String str3, String str4) {
        double[] dArr2 = dArr;
        Intrinsics.checkParameterIsNotNull(dArr, "point");
        this.locations.add(new JSON.Location(dArr2[0], dArr2[1], str, str2, str3, str4));
        return this;
    }

    public Router setDistanceUnits(Router.DistanceUnits distanceUnits) {
        Intrinsics.checkParameterIsNotNull(distanceUnits, Route.KEY_UNITS);
        this.units = distanceUnits;
        return this;
    }

    public Router clearLocations() {
        this.locations.clear();
        return this;
    }

    public Router setCallback(RouteCallback routeCallback) {
        Intrinsics.checkParameterIsNotNull(routeCallback, "callback");
        this.callback = routeCallback;
        return this;
    }

    public Call<String> fetch() {
        HttpHandler httpHandler2 = this.httpHandler;
        if (httpHandler2 != null) {
            return httpHandler2.requestRoute(getJSONRequest(), new ValhallaRouter$fetch$1(this));
        }
        return null;
    }

    public JSON getJSONRequest() {
        if (this.locations.size() >= 2) {
            JSON json = new JSON();
            int i = 0;
            int size = this.locations.size() - 1;
            if (size >= 0) {
                while (true) {
                    json.locations.add(this.locations.get(i));
                    if (i == size) {
                        break;
                    }
                    i++;
                }
            }
            if (this.language == null) {
                this.language = getDefaultLanguage();
            }
            json.costing = this.type.toString();
            json.directionsOptions.language = this.language;
            json.directionsOptions.units = this.units.toString();
            return json;
        }
        throw new MalformedURLException();
    }

    public final String getDefaultLanguage() {
        String str = Locale.getDefault().getLanguage() + "-" + Locale.getDefault().getCountry();
        Object[] objArr = (Object[]) Router.Language.values();
        boolean z = false;
        int i = 0;
        while (true) {
            if (i >= objArr.length) {
                break;
            } else if (Intrinsics.areEqual((Object) ((Router.Language) objArr[i]).toString(), (Object) str)) {
                z = true;
                break;
            } else {
                i++;
            }
        }
        return z ? str : Locale.getDefault().getLanguage();
    }
}
