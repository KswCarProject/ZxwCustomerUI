package okhttp3.internal.connection;

import java.io.IOException;
import java.lang.ref.Reference;
import java.net.ConnectException;
import java.net.ProtocolException;
import java.net.Proxy;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;
import javax.net.ssl.SSLPeerUnverifiedException;
import kotlin.jvm.internal.LongCompanionObject;
import okhttp3.Address;
import okhttp3.Connection;
import okhttp3.ConnectionPool;
import okhttp3.Handshake;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Route;
import okhttp3.internal.Internal;
import okhttp3.internal.Util;
import okhttp3.internal.Version;
import okhttp3.internal.http.HttpCodec;
import okhttp3.internal.http.HttpHeaders;
import okhttp3.internal.http1.Http1Codec;
import okhttp3.internal.http2.ErrorCode;
import okhttp3.internal.http2.Http2Codec;
import okhttp3.internal.http2.Http2Connection;
import okhttp3.internal.http2.Http2Stream;
import okhttp3.internal.platform.Platform;
import okhttp3.internal.tls.OkHostnameVerifier;
import okhttp3.internal.ws.RealWebSocket;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.Okio;
import okio.Source;

public final class RealConnection extends Http2Connection.Listener implements Connection {
    private static final String NPE_THROW_WITH_NULL = "throw with null exception";
    public int allocationLimit = 1;
    public final List<Reference<StreamAllocation>> allocations = new ArrayList();
    private final ConnectionPool connectionPool;
    private Handshake handshake;
    private Http2Connection http2Connection;
    public long idleAtNanos = LongCompanionObject.MAX_VALUE;
    public boolean noNewStreams;
    private Protocol protocol;
    private Socket rawSocket;
    private final Route route;
    private BufferedSink sink;
    private Socket socket;
    private BufferedSource source;
    public int successCount;

    public RealConnection(ConnectionPool connectionPool2, Route route2) {
        this.connectionPool = connectionPool2;
        this.route = route2;
    }

    public static RealConnection testConnection(ConnectionPool connectionPool2, Route route2, Socket socket2, long j) {
        RealConnection realConnection = new RealConnection(connectionPool2, route2);
        realConnection.socket = socket2;
        realConnection.idleAtNanos = j;
        return realConnection;
    }

    /* JADX WARNING: Removed duplicated region for block: B:16:0x007b A[Catch:{ IOException -> 0x009a }] */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x007f A[Catch:{ IOException -> 0x009a }] */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0089  */
    /* JADX WARNING: Removed duplicated region for block: B:43:? A[ORIG_RETURN, RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void connect(int r6, int r7, int r8, boolean r9) {
        /*
            r5 = this;
            okhttp3.Protocol r0 = r5.protocol
            if (r0 != 0) goto L_0x00c8
            okhttp3.Route r0 = r5.route
            okhttp3.Address r0 = r0.address()
            java.util.List r0 = r0.connectionSpecs()
            okhttp3.internal.connection.ConnectionSpecSelector r1 = new okhttp3.internal.connection.ConnectionSpecSelector
            r1.<init>(r0)
            okhttp3.Route r2 = r5.route
            okhttp3.Address r2 = r2.address()
            javax.net.ssl.SSLSocketFactory r2 = r2.sslSocketFactory()
            if (r2 != 0) goto L_0x0071
            okhttp3.ConnectionSpec r2 = okhttp3.ConnectionSpec.CLEARTEXT
            boolean r0 = r0.contains(r2)
            if (r0 == 0) goto L_0x0064
            okhttp3.Route r0 = r5.route
            okhttp3.Address r0 = r0.address()
            okhttp3.HttpUrl r0 = r0.url()
            java.lang.String r0 = r0.host()
            okhttp3.internal.platform.Platform r2 = okhttp3.internal.platform.Platform.get()
            boolean r2 = r2.isCleartextTrafficPermitted(r0)
            if (r2 == 0) goto L_0x0040
            goto L_0x0071
        L_0x0040:
            okhttp3.internal.connection.RouteException r5 = new okhttp3.internal.connection.RouteException
            java.net.UnknownServiceException r6 = new java.net.UnknownServiceException
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>()
            java.lang.String r8 = "CLEARTEXT communication to "
            java.lang.StringBuilder r7 = r7.append(r8)
            java.lang.StringBuilder r7 = r7.append(r0)
            java.lang.String r8 = " not permitted by network security policy"
            java.lang.StringBuilder r7 = r7.append(r8)
            java.lang.String r7 = r7.toString()
            r6.<init>(r7)
            r5.<init>(r6)
            throw r5
        L_0x0064:
            okhttp3.internal.connection.RouteException r5 = new okhttp3.internal.connection.RouteException
            java.net.UnknownServiceException r6 = new java.net.UnknownServiceException
            java.lang.String r7 = "CLEARTEXT communication not enabled for client"
            r6.<init>(r7)
            r5.<init>(r6)
            throw r5
        L_0x0071:
            r0 = 0
            r2 = r0
        L_0x0073:
            okhttp3.Route r3 = r5.route     // Catch:{ IOException -> 0x009a }
            boolean r3 = r3.requiresTunnel()     // Catch:{ IOException -> 0x009a }
            if (r3 == 0) goto L_0x007f
            r5.connectTunnel(r6, r7, r8)     // Catch:{ IOException -> 0x009a }
            goto L_0x0082
        L_0x007f:
            r5.connectSocket(r6, r7)     // Catch:{ IOException -> 0x009a }
        L_0x0082:
            r5.establishProtocol(r1)     // Catch:{ IOException -> 0x009a }
            okhttp3.internal.http2.Http2Connection r6 = r5.http2Connection
            if (r6 == 0) goto L_0x0099
            okhttp3.ConnectionPool r6 = r5.connectionPool
            monitor-enter(r6)
            okhttp3.internal.http2.Http2Connection r7 = r5.http2Connection     // Catch:{ all -> 0x0096 }
            int r7 = r7.maxConcurrentStreams()     // Catch:{ all -> 0x0096 }
            r5.allocationLimit = r7     // Catch:{ all -> 0x0096 }
            monitor-exit(r6)     // Catch:{ all -> 0x0096 }
            goto L_0x0099
        L_0x0096:
            r5 = move-exception
            monitor-exit(r6)     // Catch:{ all -> 0x0096 }
            throw r5
        L_0x0099:
            return
        L_0x009a:
            r3 = move-exception
            java.net.Socket r4 = r5.socket
            okhttp3.internal.Util.closeQuietly((java.net.Socket) r4)
            java.net.Socket r4 = r5.rawSocket
            okhttp3.internal.Util.closeQuietly((java.net.Socket) r4)
            r5.socket = r0
            r5.rawSocket = r0
            r5.source = r0
            r5.sink = r0
            r5.handshake = r0
            r5.protocol = r0
            r5.http2Connection = r0
            if (r2 != 0) goto L_0x00bb
            okhttp3.internal.connection.RouteException r2 = new okhttp3.internal.connection.RouteException
            r2.<init>(r3)
            goto L_0x00be
        L_0x00bb:
            r2.addConnectException(r3)
        L_0x00be:
            if (r9 == 0) goto L_0x00c7
            boolean r3 = r1.connectionFailed(r3)
            if (r3 == 0) goto L_0x00c7
            goto L_0x0073
        L_0x00c7:
            throw r2
        L_0x00c8:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "already connected"
            r5.<init>(r6)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.connection.RealConnection.connect(int, int, int, boolean):void");
    }

    private void connectTunnel(int i, int i2, int i3) throws IOException {
        Request createTunnelRequest = createTunnelRequest();
        HttpUrl url = createTunnelRequest.url();
        int i4 = 0;
        while (true) {
            i4++;
            if (i4 <= 21) {
                connectSocket(i, i2);
                createTunnelRequest = createTunnel(i2, i3, createTunnelRequest, url);
                if (createTunnelRequest != null) {
                    Util.closeQuietly(this.rawSocket);
                    this.rawSocket = null;
                    this.sink = null;
                    this.source = null;
                } else {
                    return;
                }
            } else {
                throw new ProtocolException("Too many tunnel connections attempted: " + 21);
            }
        }
    }

    private void connectSocket(int i, int i2) throws IOException {
        Socket socket2;
        Proxy proxy = this.route.proxy();
        Address address = this.route.address();
        if (proxy.type() == Proxy.Type.DIRECT || proxy.type() == Proxy.Type.HTTP) {
            socket2 = address.socketFactory().createSocket();
        } else {
            socket2 = new Socket(proxy);
        }
        this.rawSocket = socket2;
        socket2.setSoTimeout(i2);
        try {
            Platform.get().connectSocket(this.rawSocket, this.route.socketAddress(), i);
            try {
                this.source = Okio.buffer(Okio.source(this.rawSocket));
                this.sink = Okio.buffer(Okio.sink(this.rawSocket));
            } catch (NullPointerException e) {
                if (NPE_THROW_WITH_NULL.equals(e.getMessage())) {
                    throw new IOException(e);
                }
            }
        } catch (ConnectException e2) {
            ConnectException connectException = new ConnectException("Failed to connect to " + this.route.socketAddress());
            connectException.initCause(e2);
            throw connectException;
        }
    }

    private void establishProtocol(ConnectionSpecSelector connectionSpecSelector) throws IOException {
        if (this.route.address().sslSocketFactory() == null) {
            this.protocol = Protocol.HTTP_1_1;
            this.socket = this.rawSocket;
            return;
        }
        connectTls(connectionSpecSelector);
        if (this.protocol == Protocol.HTTP_2) {
            this.socket.setSoTimeout(0);
            Http2Connection build = new Http2Connection.Builder(true).socket(this.socket, this.route.address().url().host(), this.source, this.sink).listener(this).build();
            this.http2Connection = build;
            build.start();
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v0, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v1, resolved type: javax.net.ssl.SSLSocket} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v2, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v2, resolved type: javax.net.ssl.SSLSocket} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v3, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v4, resolved type: javax.net.ssl.SSLSocket} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v13, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v15, resolved type: javax.net.ssl.SSLSocket} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v16, resolved type: java.lang.String} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x011b A[Catch:{ all -> 0x0112 }] */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x0121 A[Catch:{ all -> 0x0112 }] */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x0124  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void connectTls(okhttp3.internal.connection.ConnectionSpecSelector r8) throws java.io.IOException {
        /*
            r7 = this;
            okhttp3.Route r0 = r7.route
            okhttp3.Address r0 = r0.address()
            javax.net.ssl.SSLSocketFactory r1 = r0.sslSocketFactory()
            r2 = 0
            java.net.Socket r3 = r7.rawSocket     // Catch:{ AssertionError -> 0x0114 }
            okhttp3.HttpUrl r4 = r0.url()     // Catch:{ AssertionError -> 0x0114 }
            java.lang.String r4 = r4.host()     // Catch:{ AssertionError -> 0x0114 }
            okhttp3.HttpUrl r5 = r0.url()     // Catch:{ AssertionError -> 0x0114 }
            int r5 = r5.port()     // Catch:{ AssertionError -> 0x0114 }
            r6 = 1
            java.net.Socket r1 = r1.createSocket(r3, r4, r5, r6)     // Catch:{ AssertionError -> 0x0114 }
            javax.net.ssl.SSLSocket r1 = (javax.net.ssl.SSLSocket) r1     // Catch:{ AssertionError -> 0x0114 }
            okhttp3.ConnectionSpec r8 = r8.configureSecureSocket(r1)     // Catch:{ AssertionError -> 0x010f, all -> 0x010c }
            boolean r3 = r8.supportsTlsExtensions()     // Catch:{ AssertionError -> 0x010f, all -> 0x010c }
            if (r3 == 0) goto L_0x0041
            okhttp3.internal.platform.Platform r3 = okhttp3.internal.platform.Platform.get()     // Catch:{ AssertionError -> 0x010f, all -> 0x010c }
            okhttp3.HttpUrl r4 = r0.url()     // Catch:{ AssertionError -> 0x010f, all -> 0x010c }
            java.lang.String r4 = r4.host()     // Catch:{ AssertionError -> 0x010f, all -> 0x010c }
            java.util.List r5 = r0.protocols()     // Catch:{ AssertionError -> 0x010f, all -> 0x010c }
            r3.configureTlsExtensions(r1, r4, r5)     // Catch:{ AssertionError -> 0x010f, all -> 0x010c }
        L_0x0041:
            r1.startHandshake()     // Catch:{ AssertionError -> 0x010f, all -> 0x010c }
            javax.net.ssl.SSLSession r3 = r1.getSession()     // Catch:{ AssertionError -> 0x010f, all -> 0x010c }
            okhttp3.Handshake r3 = okhttp3.Handshake.get(r3)     // Catch:{ AssertionError -> 0x010f, all -> 0x010c }
            javax.net.ssl.HostnameVerifier r4 = r0.hostnameVerifier()     // Catch:{ AssertionError -> 0x010f, all -> 0x010c }
            okhttp3.HttpUrl r5 = r0.url()     // Catch:{ AssertionError -> 0x010f, all -> 0x010c }
            java.lang.String r5 = r5.host()     // Catch:{ AssertionError -> 0x010f, all -> 0x010c }
            javax.net.ssl.SSLSession r6 = r1.getSession()     // Catch:{ AssertionError -> 0x010f, all -> 0x010c }
            boolean r4 = r4.verify(r5, r6)     // Catch:{ AssertionError -> 0x010f, all -> 0x010c }
            if (r4 == 0) goto L_0x00b2
            okhttp3.CertificatePinner r4 = r0.certificatePinner()     // Catch:{ AssertionError -> 0x010f, all -> 0x010c }
            okhttp3.HttpUrl r0 = r0.url()     // Catch:{ AssertionError -> 0x010f, all -> 0x010c }
            java.lang.String r0 = r0.host()     // Catch:{ AssertionError -> 0x010f, all -> 0x010c }
            java.util.List r5 = r3.peerCertificates()     // Catch:{ AssertionError -> 0x010f, all -> 0x010c }
            r4.check((java.lang.String) r0, (java.util.List<java.security.cert.Certificate>) r5)     // Catch:{ AssertionError -> 0x010f, all -> 0x010c }
            boolean r8 = r8.supportsTlsExtensions()     // Catch:{ AssertionError -> 0x010f, all -> 0x010c }
            if (r8 == 0) goto L_0x0083
            okhttp3.internal.platform.Platform r8 = okhttp3.internal.platform.Platform.get()     // Catch:{ AssertionError -> 0x010f, all -> 0x010c }
            java.lang.String r2 = r8.getSelectedProtocol(r1)     // Catch:{ AssertionError -> 0x010f, all -> 0x010c }
        L_0x0083:
            r7.socket = r1     // Catch:{ AssertionError -> 0x010f, all -> 0x010c }
            okio.Source r8 = okio.Okio.source((java.net.Socket) r1)     // Catch:{ AssertionError -> 0x010f, all -> 0x010c }
            okio.BufferedSource r8 = okio.Okio.buffer((okio.Source) r8)     // Catch:{ AssertionError -> 0x010f, all -> 0x010c }
            r7.source = r8     // Catch:{ AssertionError -> 0x010f, all -> 0x010c }
            java.net.Socket r8 = r7.socket     // Catch:{ AssertionError -> 0x010f, all -> 0x010c }
            okio.Sink r8 = okio.Okio.sink((java.net.Socket) r8)     // Catch:{ AssertionError -> 0x010f, all -> 0x010c }
            okio.BufferedSink r8 = okio.Okio.buffer((okio.Sink) r8)     // Catch:{ AssertionError -> 0x010f, all -> 0x010c }
            r7.sink = r8     // Catch:{ AssertionError -> 0x010f, all -> 0x010c }
            r7.handshake = r3     // Catch:{ AssertionError -> 0x010f, all -> 0x010c }
            if (r2 == 0) goto L_0x00a4
            okhttp3.Protocol r8 = okhttp3.Protocol.get(r2)     // Catch:{ AssertionError -> 0x010f, all -> 0x010c }
            goto L_0x00a6
        L_0x00a4:
            okhttp3.Protocol r8 = okhttp3.Protocol.HTTP_1_1     // Catch:{ AssertionError -> 0x010f, all -> 0x010c }
        L_0x00a6:
            r7.protocol = r8     // Catch:{ AssertionError -> 0x010f, all -> 0x010c }
            if (r1 == 0) goto L_0x00b1
            okhttp3.internal.platform.Platform r7 = okhttp3.internal.platform.Platform.get()
            r7.afterHandshake(r1)
        L_0x00b1:
            return
        L_0x00b2:
            java.util.List r7 = r3.peerCertificates()     // Catch:{ AssertionError -> 0x010f, all -> 0x010c }
            r8 = 0
            java.lang.Object r7 = r7.get(r8)     // Catch:{ AssertionError -> 0x010f, all -> 0x010c }
            java.security.cert.X509Certificate r7 = (java.security.cert.X509Certificate) r7     // Catch:{ AssertionError -> 0x010f, all -> 0x010c }
            javax.net.ssl.SSLPeerUnverifiedException r8 = new javax.net.ssl.SSLPeerUnverifiedException     // Catch:{ AssertionError -> 0x010f, all -> 0x010c }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ AssertionError -> 0x010f, all -> 0x010c }
            r2.<init>()     // Catch:{ AssertionError -> 0x010f, all -> 0x010c }
            java.lang.String r3 = "Hostname "
            java.lang.StringBuilder r2 = r2.append(r3)     // Catch:{ AssertionError -> 0x010f, all -> 0x010c }
            okhttp3.HttpUrl r0 = r0.url()     // Catch:{ AssertionError -> 0x010f, all -> 0x010c }
            java.lang.String r0 = r0.host()     // Catch:{ AssertionError -> 0x010f, all -> 0x010c }
            java.lang.StringBuilder r0 = r2.append(r0)     // Catch:{ AssertionError -> 0x010f, all -> 0x010c }
            java.lang.String r2 = " not verified:\n    certificate: "
            java.lang.StringBuilder r0 = r0.append(r2)     // Catch:{ AssertionError -> 0x010f, all -> 0x010c }
            java.lang.String r2 = okhttp3.CertificatePinner.pin(r7)     // Catch:{ AssertionError -> 0x010f, all -> 0x010c }
            java.lang.StringBuilder r0 = r0.append(r2)     // Catch:{ AssertionError -> 0x010f, all -> 0x010c }
            java.lang.String r2 = "\n    DN: "
            java.lang.StringBuilder r0 = r0.append(r2)     // Catch:{ AssertionError -> 0x010f, all -> 0x010c }
            java.security.Principal r2 = r7.getSubjectDN()     // Catch:{ AssertionError -> 0x010f, all -> 0x010c }
            java.lang.String r2 = r2.getName()     // Catch:{ AssertionError -> 0x010f, all -> 0x010c }
            java.lang.StringBuilder r0 = r0.append(r2)     // Catch:{ AssertionError -> 0x010f, all -> 0x010c }
            java.lang.String r2 = "\n    subjectAltNames: "
            java.lang.StringBuilder r0 = r0.append(r2)     // Catch:{ AssertionError -> 0x010f, all -> 0x010c }
            java.util.List r7 = okhttp3.internal.tls.OkHostnameVerifier.allSubjectAltNames(r7)     // Catch:{ AssertionError -> 0x010f, all -> 0x010c }
            java.lang.StringBuilder r7 = r0.append(r7)     // Catch:{ AssertionError -> 0x010f, all -> 0x010c }
            java.lang.String r7 = r7.toString()     // Catch:{ AssertionError -> 0x010f, all -> 0x010c }
            r8.<init>(r7)     // Catch:{ AssertionError -> 0x010f, all -> 0x010c }
            throw r8     // Catch:{ AssertionError -> 0x010f, all -> 0x010c }
        L_0x010c:
            r7 = move-exception
            r2 = r1
            goto L_0x0122
        L_0x010f:
            r7 = move-exception
            r2 = r1
            goto L_0x0115
        L_0x0112:
            r7 = move-exception
            goto L_0x0122
        L_0x0114:
            r7 = move-exception
        L_0x0115:
            boolean r8 = okhttp3.internal.Util.isAndroidGetsocknameError(r7)     // Catch:{ all -> 0x0112 }
            if (r8 == 0) goto L_0x0121
            java.io.IOException r8 = new java.io.IOException     // Catch:{ all -> 0x0112 }
            r8.<init>(r7)     // Catch:{ all -> 0x0112 }
            throw r8     // Catch:{ all -> 0x0112 }
        L_0x0121:
            throw r7     // Catch:{ all -> 0x0112 }
        L_0x0122:
            if (r2 == 0) goto L_0x012b
            okhttp3.internal.platform.Platform r8 = okhttp3.internal.platform.Platform.get()
            r8.afterHandshake(r2)
        L_0x012b:
            okhttp3.internal.Util.closeQuietly((java.net.Socket) r2)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.connection.RealConnection.connectTls(okhttp3.internal.connection.ConnectionSpecSelector):void");
    }

    private Request createTunnel(int i, int i2, Request request, HttpUrl httpUrl) throws IOException {
        String str = "CONNECT " + Util.hostHeader(httpUrl, true) + " HTTP/1.1";
        while (true) {
            Http1Codec http1Codec = new Http1Codec((OkHttpClient) null, (StreamAllocation) null, this.source, this.sink);
            this.source.timeout().timeout((long) i, TimeUnit.MILLISECONDS);
            this.sink.timeout().timeout((long) i2, TimeUnit.MILLISECONDS);
            http1Codec.writeRequest(request.headers(), str);
            http1Codec.finishRequest();
            Response build = http1Codec.readResponseHeaders(false).request(request).build();
            long contentLength = HttpHeaders.contentLength(build);
            if (contentLength == -1) {
                contentLength = 0;
            }
            Source newFixedLengthSource = http1Codec.newFixedLengthSource(contentLength);
            Util.skipAll(newFixedLengthSource, Integer.MAX_VALUE, TimeUnit.MILLISECONDS);
            newFixedLengthSource.close();
            int code = build.code();
            if (code != 200) {
                if (code == 407) {
                    Request authenticate = this.route.address().proxyAuthenticator().authenticate(this.route, build);
                    if (authenticate == null) {
                        throw new IOException("Failed to authenticate with proxy");
                    } else if ("close".equalsIgnoreCase(build.header("Connection"))) {
                        return authenticate;
                    } else {
                        request = authenticate;
                    }
                } else {
                    throw new IOException("Unexpected response code for CONNECT: " + build.code());
                }
            } else if (this.source.buffer().exhausted() && this.sink.buffer().exhausted()) {
                return null;
            } else {
                throw new IOException("TLS tunnel buffered too many bytes!");
            }
        }
    }

    private Request createTunnelRequest() {
        return new Request.Builder().url(this.route.address().url()).header("Host", Util.hostHeader(this.route.address().url(), true)).header("Proxy-Connection", "Keep-Alive").header("User-Agent", Version.userAgent()).build();
    }

    public boolean isEligible(Address address, @Nullable Route route2) {
        if (this.allocations.size() >= this.allocationLimit || this.noNewStreams || !Internal.instance.equalsNonHost(this.route.address(), address)) {
            return false;
        }
        if (address.url().host().equals(route().address().url().host())) {
            return true;
        }
        if (this.http2Connection == null || route2 == null || route2.proxy().type() != Proxy.Type.DIRECT || this.route.proxy().type() != Proxy.Type.DIRECT || !this.route.socketAddress().equals(route2.socketAddress()) || route2.address().hostnameVerifier() != OkHostnameVerifier.INSTANCE || !supportsUrl(address.url())) {
            return false;
        }
        try {
            address.certificatePinner().check(address.url().host(), handshake().peerCertificates());
            return true;
        } catch (SSLPeerUnverifiedException unused) {
            return false;
        }
    }

    public boolean supportsUrl(HttpUrl httpUrl) {
        if (httpUrl.port() != this.route.address().url().port()) {
            return false;
        }
        if (httpUrl.host().equals(this.route.address().url().host())) {
            return true;
        }
        if (this.handshake == null || !OkHostnameVerifier.INSTANCE.verify(httpUrl.host(), (X509Certificate) this.handshake.peerCertificates().get(0))) {
            return false;
        }
        return true;
    }

    public HttpCodec newCodec(OkHttpClient okHttpClient, StreamAllocation streamAllocation) throws SocketException {
        if (this.http2Connection != null) {
            return new Http2Codec(okHttpClient, streamAllocation, this.http2Connection);
        }
        this.socket.setSoTimeout(okHttpClient.readTimeoutMillis());
        this.source.timeout().timeout((long) okHttpClient.readTimeoutMillis(), TimeUnit.MILLISECONDS);
        this.sink.timeout().timeout((long) okHttpClient.writeTimeoutMillis(), TimeUnit.MILLISECONDS);
        return new Http1Codec(okHttpClient, streamAllocation, this.source, this.sink);
    }

    public RealWebSocket.Streams newWebSocketStreams(StreamAllocation streamAllocation) {
        final StreamAllocation streamAllocation2 = streamAllocation;
        return new RealWebSocket.Streams(true, this.source, this.sink) {
            public void close() throws IOException {
                StreamAllocation streamAllocation = streamAllocation2;
                streamAllocation.streamFinished(true, streamAllocation.codec());
            }
        };
    }

    public Route route() {
        return this.route;
    }

    public void cancel() {
        Util.closeQuietly(this.rawSocket);
    }

    public Socket socket() {
        return this.socket;
    }

    public boolean isHealthy(boolean z) {
        int soTimeout;
        if (this.socket.isClosed() || this.socket.isInputShutdown() || this.socket.isOutputShutdown()) {
            return false;
        }
        Http2Connection http2Connection2 = this.http2Connection;
        if (http2Connection2 != null) {
            return !http2Connection2.isShutdown();
        }
        if (z) {
            try {
                soTimeout = this.socket.getSoTimeout();
                this.socket.setSoTimeout(1);
                if (this.source.exhausted()) {
                    this.socket.setSoTimeout(soTimeout);
                    return false;
                }
                this.socket.setSoTimeout(soTimeout);
                return true;
            } catch (SocketTimeoutException unused) {
            } catch (IOException unused2) {
                return false;
            } catch (Throwable th) {
                this.socket.setSoTimeout(soTimeout);
                throw th;
            }
        }
        return true;
    }

    public void onStream(Http2Stream http2Stream) throws IOException {
        http2Stream.close(ErrorCode.REFUSED_STREAM);
    }

    public void onSettings(Http2Connection http2Connection2) {
        synchronized (this.connectionPool) {
            this.allocationLimit = http2Connection2.maxConcurrentStreams();
        }
    }

    public Handshake handshake() {
        return this.handshake;
    }

    public boolean isMultiplexed() {
        return this.http2Connection != null;
    }

    public Protocol protocol() {
        return this.protocol;
    }

    public String toString() {
        StringBuilder append = new StringBuilder().append("Connection{").append(this.route.address().url().host()).append(":").append(this.route.address().url().port()).append(", proxy=").append(this.route.proxy()).append(" hostAddress=").append(this.route.socketAddress()).append(" cipherSuite=");
        Handshake handshake2 = this.handshake;
        return append.append(handshake2 != null ? handshake2.cipherSuite() : "none").append(" protocol=").append(this.protocol).append('}').toString();
    }
}
