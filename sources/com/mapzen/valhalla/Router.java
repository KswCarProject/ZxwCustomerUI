package com.mapzen.valhalla;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.DebugKt;
import retrofit2.Call;

@Metadata(bv = {1, 0, 0}, d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0013\n\u0000\n\u0002\u0010\b\n\u0002\b\n\bf\u0018\u00002\u00020\u0001:\u0003!\"#J\b\u0010\u0002\u001a\u00020\u0000H&J\u0010\u0010\u0003\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0004H&J\b\u0010\u0006\u001a\u00020\u0007H&J\b\u0010\b\u001a\u00020\u0000H&J\u0010\u0010\t\u001a\u00020\u00002\u0006\u0010\n\u001a\u00020\u000bH&J\u0010\u0010\f\u001a\u00020\u00002\u0006\u0010\r\u001a\u00020\u000eH&J\b\u0010\u000f\u001a\u00020\u0000H&J\u0010\u0010\u0010\u001a\u00020\u00002\u0006\u0010\u0011\u001a\u00020\u0012H&J\u0010\u0010\u0013\u001a\u00020\u00002\u0006\u0010\u0014\u001a\u00020\u0015H&J\u0010\u0010\u0016\u001a\u00020\u00002\u0006\u0010\u0017\u001a\u00020\u0018H&J\u0018\u0010\u0016\u001a\u00020\u00002\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001aH&J@\u0010\u0016\u001a\u00020\u00002\u0006\u0010\u0017\u001a\u00020\u00182\n\b\u0002\u0010\u001b\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u001c\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u001d\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u0005H&J\b\u0010\u001f\u001a\u00020\u0000H&J\b\u0010 \u001a\u00020\u0000H&¨\u0006$"}, d2 = {"Lcom/mapzen/valhalla/Router;", "", "clearLocations", "fetch", "Lretrofit2/Call;", "", "getJSONRequest", "Lcom/mapzen/valhalla/JSON;", "setBiking", "setCallback", "callback", "Lcom/mapzen/valhalla/RouteCallback;", "setDistanceUnits", "units", "Lcom/mapzen/valhalla/Router$DistanceUnits;", "setDriving", "setHttpHandler", "handler", "Lcom/mapzen/valhalla/HttpHandler;", "setLanguage", "language", "Lcom/mapzen/valhalla/Router$Language;", "setLocation", "point", "", "heading", "", "name", "street", "city", "state", "setMultimodal", "setWalking", "DistanceUnits", "Language", "Type", "library_release"}, k = 1, mv = {1, 1, 1})
/* compiled from: Router.kt */
public interface Router {
    Router clearLocations();

    Call<String> fetch();

    JSON getJSONRequest();

    Router setBiking();

    Router setCallback(RouteCallback routeCallback);

    Router setDistanceUnits(DistanceUnits distanceUnits);

    Router setDriving();

    Router setHttpHandler(HttpHandler httpHandler);

    Router setLanguage(Language language);

    Router setLocation(double[] dArr);

    Router setLocation(double[] dArr, int i);

    Router setLocation(double[] dArr, String str, String str2, String str3, String str4);

    Router setMultimodal();

    Router setWalking();

    @Metadata(bv = {1, 0, 0}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000e\b\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0005\u001a\u00020\u0003H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010¨\u0006\u0011"}, d2 = {"Lcom/mapzen/valhalla/Router$Language;", "", "languageTag", "", "(Ljava/lang/String;ILjava/lang/String;)V", "toString", "CA_ES", "CS_CZ", "DE_DE", "EN_US", "PIRATE", "ES_ES", "FR_FR", "HI_IN", "IT_IT", "RU_RU", "SL_SI", "library_release"}, k = 1, mv = {1, 1, 1})
    /* compiled from: Router.kt */
    public enum Language {
        CA_ES("ca-ES"),
        CS_CZ("cs-CZ"),
        DE_DE("de-DE"),
        EN_US("en-US"),
        PIRATE("en-US-x-pirate"),
        ES_ES("es-ES"),
        FR_FR("fr-FR"),
        HI_IN("hi-IN"),
        IT_IT("it-IT"),
        RU_RU("ru-RU"),
        SL_SI("sl-SI");
        
        private final String languageTag;

        protected Language(String str) {
            Intrinsics.checkParameterIsNotNull(str, "languageTag");
            this.languageTag = str;
        }

        public String toString() {
            return this.languageTag;
        }
    }

    @Metadata(bv = {1, 0, 0}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\b\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0005\u001a\u00020\u0003H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\t¨\u0006\n"}, d2 = {"Lcom/mapzen/valhalla/Router$Type;", "", "type", "", "(Ljava/lang/String;ILjava/lang/String;)V", "toString", "WALKING", "BIKING", "DRIVING", "MULTIMODAL", "library_release"}, k = 1, mv = {1, 1, 1})
    /* compiled from: Router.kt */
    public enum Type {
        WALKING("pedestrian"),
        BIKING("bicycle"),
        DRIVING(DebugKt.DEBUG_PROPERTY_VALUE_AUTO),
        MULTIMODAL("multimodal");
        
        private final String type;

        protected Type(String str) {
            Intrinsics.checkParameterIsNotNull(str, "type");
            this.type = str;
        }

        public String toString() {
            return this.type;
        }
    }

    @Metadata(bv = {1, 0, 0}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\b\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0005\u001a\u00020\u0003H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000j\u0002\b\u0006j\u0002\b\u0007¨\u0006\b"}, d2 = {"Lcom/mapzen/valhalla/Router$DistanceUnits;", "", "units", "", "(Ljava/lang/String;ILjava/lang/String;)V", "toString", "MILES", "KILOMETERS", "library_release"}, k = 1, mv = {1, 1, 1})
    /* compiled from: Router.kt */
    public enum DistanceUnits {
        MILES("miles"),
        KILOMETERS("kilometers");
        
        private final String units;

        protected DistanceUnits(String str) {
            Intrinsics.checkParameterIsNotNull(str, Route.KEY_UNITS);
            this.units = str;
        }

        public String toString() {
            return this.units;
        }
    }

    @Metadata(bv = {1, 0, 0}, k = 3, mv = {1, 1, 1})
    /* compiled from: Router.kt */
    public static final class DefaultImpls {
        public static /* bridge */ /* synthetic */ Router setLocation$default(Router router, double[] dArr, String str, String str2, String str3, String str4, int i, Object obj) {
            if (obj == null) {
                if ((i & 2) != 0) {
                    str = null;
                }
                String str5 = str;
                if ((i & 4) != 0) {
                    str2 = null;
                }
                String str6 = str2;
                if ((i & 8) != 0) {
                    str3 = null;
                }
                String str7 = str3;
                if ((i & 16) != 0) {
                    str4 = null;
                }
                return router.setLocation(dArr, str5, str6, str7, str4);
            }
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: setLocation");
        }
    }
}
