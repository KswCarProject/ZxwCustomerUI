package com.mapzen.http;

import android.os.Build;
import android.util.Log;
import java.security.KeyStore;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.ConnectionSpec;
import okhttp3.OkHttpClient;
import okhttp3.TlsVersion;

@Metadata(bv = {1, 0, 0}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0004"}, d2 = {"Lcom/mapzen/http/Tls12OkHttpClientFactory;", "", "()V", "Companion", "library_release"}, k = 1, mv = {1, 1, 1})
/* compiled from: Tls12OkHttpClientFactory.kt */
public final class Tls12OkHttpClientFactory {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);

    @Metadata(bv = {1, 0, 0}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004¨\u0006\u0006"}, d2 = {"Lcom/mapzen/http/Tls12OkHttpClientFactory$Companion;", "", "()V", "enableTls12OnPreLollipop", "Lokhttp3/OkHttpClient$Builder;", "client", "library_release"}, k = 1, mv = {1, 1, 1})
    /* compiled from: Tls12OkHttpClientFactory.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final OkHttpClient.Builder enableTls12OnPreLollipop(OkHttpClient.Builder builder) {
            Intrinsics.checkParameterIsNotNull(builder, "client");
            int i = Build.VERSION.SDK_INT;
            if (16 <= i && i <= 21) {
                try {
                    SSLContext instance = SSLContext.getInstance("TLSv1.2");
                    instance.init((KeyManager[]) null, (TrustManager[]) null, (SecureRandom) null);
                    TrustManagerFactory instance2 = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
                    instance2.init((KeyStore) null);
                    TrustManager[] trustManagers = instance2.getTrustManagers();
                    if (((Object[]) trustManagers).length != 1 || !(trustManagers[0] instanceof X509TrustManager)) {
                        throw new IllegalStateException("Unexpected default trust managers:" + Arrays.toString((Object[]) trustManagers));
                    }
                    TrustManager trustManager = trustManagers[0];
                    if (trustManager != null) {
                        SSLSocketFactory socketFactory = instance.getSocketFactory();
                        Intrinsics.checkExpressionValueIsNotNull(socketFactory, "sc.getSocketFactory()");
                        builder.sslSocketFactory(new Tls12SocketFactory(socketFactory), (X509TrustManager) trustManager);
                        ConnectionSpec build = new ConnectionSpec.Builder(ConnectionSpec.MODERN_TLS).tlsVersions(TlsVersion.TLS_1_2).build();
                        ArrayList arrayList = new ArrayList();
                        arrayList.add(build);
                        arrayList.add(ConnectionSpec.COMPATIBLE_TLS);
                        arrayList.add(ConnectionSpec.CLEARTEXT);
                        builder.connectionSpecs(arrayList);
                    } else {
                        throw new TypeCastException("null cannot be cast to non-null type javax.net.ssl.X509TrustManager");
                    }
                } catch (Exception e) {
                    Log.e("OkHttpTLSCompat", "Error while setting TLS 1.2", e);
                }
            }
            return builder;
        }
    }
}
