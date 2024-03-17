package com.mapzen.http;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 0}, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\u0005\u0018\u0000 \u00192\u00020\u0001:\u0001\u0019B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0001¢\u0006\u0002\u0010\u0003J\u0018\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0017J(\u0010\u0006\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\t2\u0006\u0010\u000e\u001a\u00020\u000bH\u0017J(\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u000f\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00102\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0011\u001a\u00020\u0012H\u0017J\u0018\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00102\u0006\u0010\n\u001a\u00020\u000bH\u0017J(\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00102\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0013\u001a\u00020\t2\u0006\u0010\u000e\u001a\u00020\u000bH\u0017J\u0013\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00100\u0015H\u0016¢\u0006\u0002\u0010\u0016J\u0013\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00100\u0015H\u0016¢\u0006\u0002\u0010\u0016J\u0010\u0010\u0018\u001a\u00020\u00072\u0006\u0010\u000f\u001a\u00020\u0007H\u0002R\u0014\u0010\u0002\u001a\u00020\u0001X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u001a"}, d2 = {"Lcom/mapzen/http/Tls12SocketFactory;", "Ljavax/net/ssl/SSLSocketFactory;", "delegate", "(Ljavax/net/ssl/SSLSocketFactory;)V", "getDelegate$library_release", "()Ljavax/net/ssl/SSLSocketFactory;", "createSocket", "Ljava/net/Socket;", "host", "Ljava/net/InetAddress;", "port", "", "address", "localAddress", "localPort", "s", "", "autoClose", "", "localHost", "getDefaultCipherSuites", "", "()[Ljava/lang/String;", "getSupportedCipherSuites", "patch", "Companion", "library_release"}, k = 1, mv = {1, 1, 1})
/* compiled from: Tls12SocketFactory.kt */
public final class Tls12SocketFactory extends SSLSocketFactory {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    /* access modifiers changed from: private */
    public static final String[] TLS_V12_ONLY = ((String[]) ((Object[]) new String[]{"TLSv1.2"}));
    private final SSLSocketFactory delegate;

    public Tls12SocketFactory(SSLSocketFactory sSLSocketFactory) {
        Intrinsics.checkParameterIsNotNull(sSLSocketFactory, "delegate");
        this.delegate = sSLSocketFactory;
    }

    public final SSLSocketFactory getDelegate$library_release() {
        return this.delegate;
    }

    public String[] getDefaultCipherSuites() {
        String[] defaultCipherSuites = this.delegate.getDefaultCipherSuites();
        Intrinsics.checkExpressionValueIsNotNull(defaultCipherSuites, "delegate.defaultCipherSuites");
        return defaultCipherSuites;
    }

    public String[] getSupportedCipherSuites() {
        String[] supportedCipherSuites = this.delegate.getSupportedCipherSuites();
        Intrinsics.checkExpressionValueIsNotNull(supportedCipherSuites, "delegate.supportedCipherSuites");
        return supportedCipherSuites;
    }

    public Socket createSocket(Socket socket, String str, int i, boolean z) throws IOException {
        Intrinsics.checkParameterIsNotNull(socket, "s");
        Intrinsics.checkParameterIsNotNull(str, "host");
        Socket createSocket = this.delegate.createSocket(socket, str, i, z);
        Intrinsics.checkExpressionValueIsNotNull(createSocket, "delegate.createSocket(s, host, port, autoClose)");
        return patch(createSocket);
    }

    public Socket createSocket(String str, int i) throws IOException {
        Intrinsics.checkParameterIsNotNull(str, "host");
        Socket createSocket = this.delegate.createSocket(str, i);
        Intrinsics.checkExpressionValueIsNotNull(createSocket, "delegate.createSocket(host, port)");
        return patch(createSocket);
    }

    public Socket createSocket(String str, int i, InetAddress inetAddress, int i2) throws IOException {
        Intrinsics.checkParameterIsNotNull(str, "host");
        Intrinsics.checkParameterIsNotNull(inetAddress, "localHost");
        Socket createSocket = this.delegate.createSocket(str, i, inetAddress, i2);
        Intrinsics.checkExpressionValueIsNotNull(createSocket, "delegate.createSocket(ho…rt, localHost, localPort)");
        return patch(createSocket);
    }

    public Socket createSocket(InetAddress inetAddress, int i) throws IOException {
        Intrinsics.checkParameterIsNotNull(inetAddress, "host");
        Socket createSocket = this.delegate.createSocket(inetAddress, i);
        Intrinsics.checkExpressionValueIsNotNull(createSocket, "delegate.createSocket(host, port)");
        return patch(createSocket);
    }

    public Socket createSocket(InetAddress inetAddress, int i, InetAddress inetAddress2, int i2) throws IOException {
        Intrinsics.checkParameterIsNotNull(inetAddress, "address");
        Intrinsics.checkParameterIsNotNull(inetAddress2, "localAddress");
        Socket createSocket = this.delegate.createSocket(inetAddress, i, inetAddress2, i2);
        Intrinsics.checkExpressionValueIsNotNull(createSocket, "delegate.createSocket(ad… localAddress, localPort)");
        return patch(createSocket);
    }

    private final Socket patch(Socket socket) {
        if (socket instanceof SSLSocket) {
            ((SSLSocket) socket).setEnabledProtocols(Companion.getTLS_V12_ONLY());
        }
        return socket;
    }

    @Metadata(bv = {1, 0, 0}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001c\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0004¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0006\u0010\u0007¨\u0006\t"}, d2 = {"Lcom/mapzen/http/Tls12SocketFactory$Companion;", "", "()V", "TLS_V12_ONLY", "", "", "getTLS_V12_ONLY", "()[Ljava/lang/String;", "[Ljava/lang/String;", "library_release"}, k = 1, mv = {1, 1, 1})
    /* compiled from: Tls12SocketFactory.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* access modifiers changed from: private */
        public final String[] getTLS_V12_ONLY() {
            return Tls12SocketFactory.TLS_V12_ONLY;
        }
    }
}
