package okhttp3.internal.connection;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.net.Socket;
import okhttp3.Address;
import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import okhttp3.Route;
import okhttp3.internal.Internal;
import okhttp3.internal.Util;
import okhttp3.internal.http.HttpCodec;
import okhttp3.internal.http2.ConnectionShutdownException;
import okhttp3.internal.http2.ErrorCode;
import okhttp3.internal.http2.StreamResetException;

public final class StreamAllocation {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public final Address address;
    private final Object callStackTrace;
    private boolean canceled;
    private HttpCodec codec;
    private RealConnection connection;
    private final ConnectionPool connectionPool;
    private int refusedStreamCount;
    private boolean released;
    private Route route;
    private final RouteSelector routeSelector;

    public StreamAllocation(ConnectionPool connectionPool2, Address address2, Object obj) {
        this.connectionPool = connectionPool2;
        this.address = address2;
        this.routeSelector = new RouteSelector(address2, routeDatabase());
        this.callStackTrace = obj;
    }

    public HttpCodec newStream(OkHttpClient okHttpClient, boolean z) {
        try {
            HttpCodec newCodec = findHealthyConnection(okHttpClient.connectTimeoutMillis(), okHttpClient.readTimeoutMillis(), okHttpClient.writeTimeoutMillis(), okHttpClient.retryOnConnectionFailure(), z).newCodec(okHttpClient, this);
            synchronized (this.connectionPool) {
                this.codec = newCodec;
            }
            return newCodec;
        } catch (IOException e) {
            throw new RouteException(e);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0018, code lost:
        return r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0012, code lost:
        if (r0.isHealthy(r8) != false) goto L_0x0018;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private okhttp3.internal.connection.RealConnection findHealthyConnection(int r4, int r5, int r6, boolean r7, boolean r8) throws java.io.IOException {
        /*
            r3 = this;
        L_0x0000:
            okhttp3.internal.connection.RealConnection r0 = r3.findConnection(r4, r5, r6, r7)
            okhttp3.ConnectionPool r1 = r3.connectionPool
            monitor-enter(r1)
            int r2 = r0.successCount     // Catch:{ all -> 0x0019 }
            if (r2 != 0) goto L_0x000d
            monitor-exit(r1)     // Catch:{ all -> 0x0019 }
            return r0
        L_0x000d:
            monitor-exit(r1)     // Catch:{ all -> 0x0019 }
            boolean r1 = r0.isHealthy(r8)
            if (r1 != 0) goto L_0x0018
            r3.noNewStreams()
            goto L_0x0000
        L_0x0018:
            return r0
        L_0x0019:
            r3 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x0019 }
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.connection.StreamAllocation.findHealthyConnection(int, int, int, boolean, boolean):okhttp3.internal.connection.RealConnection");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:21:0x002c, code lost:
        if (r1 != null) goto L_0x0034;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x002e, code lost:
        r1 = r6.routeSelector.next();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0034, code lost:
        r2 = r6.connectionPool;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0036, code lost:
        monitor-enter(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0039, code lost:
        if (r6.canceled != false) goto L_0x008c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x003b, code lost:
        okhttp3.internal.Internal.instance.get(r6.connectionPool, r6.address, r6, r1);
        r0 = r6.connection;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0046, code lost:
        if (r0 == null) goto L_0x004a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x0048, code lost:
        monitor-exit(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x0049, code lost:
        return r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x004a, code lost:
        r6.route = r1;
        r6.refusedStreamCount = 0;
        r0 = new okhttp3.internal.connection.RealConnection(r6.connectionPool, r1);
        acquire(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x0059, code lost:
        monitor-exit(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x005a, code lost:
        r0.connect(r7, r8, r9, r10);
        routeDatabase().connected(r0.route());
        r7 = r6.connectionPool;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x006a, code lost:
        monitor-enter(r7);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:?, code lost:
        okhttp3.internal.Internal.instance.put(r6.connectionPool, r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x0076, code lost:
        if (r0.isMultiplexed() == false) goto L_0x0084;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x0078, code lost:
        r4 = okhttp3.internal.Internal.instance.deduplicate(r6.connectionPool, r6.address, r6);
        r0 = r6.connection;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x0084, code lost:
        monitor-exit(r7);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x0085, code lost:
        okhttp3.internal.Util.closeQuietly(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x0088, code lost:
        return r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x0093, code lost:
        throw new java.io.IOException("Canceled");
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private okhttp3.internal.connection.RealConnection findConnection(int r7, int r8, int r9, boolean r10) throws java.io.IOException {
        /*
            r6 = this;
            okhttp3.ConnectionPool r0 = r6.connectionPool
            monitor-enter(r0)
            boolean r1 = r6.released     // Catch:{ all -> 0x00af }
            if (r1 != 0) goto L_0x00a7
            okhttp3.internal.http.HttpCodec r1 = r6.codec     // Catch:{ all -> 0x00af }
            if (r1 != 0) goto L_0x009f
            boolean r1 = r6.canceled     // Catch:{ all -> 0x00af }
            if (r1 != 0) goto L_0x0097
            okhttp3.internal.connection.RealConnection r1 = r6.connection     // Catch:{ all -> 0x00af }
            if (r1 == 0) goto L_0x0019
            boolean r2 = r1.noNewStreams     // Catch:{ all -> 0x00af }
            if (r2 != 0) goto L_0x0019
            monitor-exit(r0)     // Catch:{ all -> 0x00af }
            return r1
        L_0x0019:
            okhttp3.internal.Internal r1 = okhttp3.internal.Internal.instance     // Catch:{ all -> 0x00af }
            okhttp3.ConnectionPool r2 = r6.connectionPool     // Catch:{ all -> 0x00af }
            okhttp3.Address r3 = r6.address     // Catch:{ all -> 0x00af }
            r4 = 0
            r1.get(r2, r3, r6, r4)     // Catch:{ all -> 0x00af }
            okhttp3.internal.connection.RealConnection r1 = r6.connection     // Catch:{ all -> 0x00af }
            if (r1 == 0) goto L_0x0029
            monitor-exit(r0)     // Catch:{ all -> 0x00af }
            return r1
        L_0x0029:
            okhttp3.Route r1 = r6.route     // Catch:{ all -> 0x00af }
            monitor-exit(r0)     // Catch:{ all -> 0x00af }
            if (r1 != 0) goto L_0x0034
            okhttp3.internal.connection.RouteSelector r0 = r6.routeSelector
            okhttp3.Route r1 = r0.next()
        L_0x0034:
            okhttp3.ConnectionPool r2 = r6.connectionPool
            monitor-enter(r2)
            boolean r0 = r6.canceled     // Catch:{ all -> 0x0094 }
            if (r0 != 0) goto L_0x008c
            okhttp3.internal.Internal r0 = okhttp3.internal.Internal.instance     // Catch:{ all -> 0x0094 }
            okhttp3.ConnectionPool r3 = r6.connectionPool     // Catch:{ all -> 0x0094 }
            okhttp3.Address r5 = r6.address     // Catch:{ all -> 0x0094 }
            r0.get(r3, r5, r6, r1)     // Catch:{ all -> 0x0094 }
            okhttp3.internal.connection.RealConnection r0 = r6.connection     // Catch:{ all -> 0x0094 }
            if (r0 == 0) goto L_0x004a
            monitor-exit(r2)     // Catch:{ all -> 0x0094 }
            return r0
        L_0x004a:
            r6.route = r1     // Catch:{ all -> 0x0094 }
            r0 = 0
            r6.refusedStreamCount = r0     // Catch:{ all -> 0x0094 }
            okhttp3.internal.connection.RealConnection r0 = new okhttp3.internal.connection.RealConnection     // Catch:{ all -> 0x0094 }
            okhttp3.ConnectionPool r3 = r6.connectionPool     // Catch:{ all -> 0x0094 }
            r0.<init>(r3, r1)     // Catch:{ all -> 0x0094 }
            r6.acquire(r0)     // Catch:{ all -> 0x0094 }
            monitor-exit(r2)     // Catch:{ all -> 0x0094 }
            r0.connect(r7, r8, r9, r10)
            okhttp3.internal.connection.RouteDatabase r7 = r6.routeDatabase()
            okhttp3.Route r8 = r0.route()
            r7.connected(r8)
            okhttp3.ConnectionPool r7 = r6.connectionPool
            monitor-enter(r7)
            okhttp3.internal.Internal r8 = okhttp3.internal.Internal.instance     // Catch:{ all -> 0x0089 }
            okhttp3.ConnectionPool r9 = r6.connectionPool     // Catch:{ all -> 0x0089 }
            r8.put(r9, r0)     // Catch:{ all -> 0x0089 }
            boolean r8 = r0.isMultiplexed()     // Catch:{ all -> 0x0089 }
            if (r8 == 0) goto L_0x0084
            okhttp3.internal.Internal r8 = okhttp3.internal.Internal.instance     // Catch:{ all -> 0x0089 }
            okhttp3.ConnectionPool r9 = r6.connectionPool     // Catch:{ all -> 0x0089 }
            okhttp3.Address r10 = r6.address     // Catch:{ all -> 0x0089 }
            java.net.Socket r4 = r8.deduplicate(r9, r10, r6)     // Catch:{ all -> 0x0089 }
            okhttp3.internal.connection.RealConnection r0 = r6.connection     // Catch:{ all -> 0x0089 }
        L_0x0084:
            monitor-exit(r7)     // Catch:{ all -> 0x0089 }
            okhttp3.internal.Util.closeQuietly((java.net.Socket) r4)
            return r0
        L_0x0089:
            r6 = move-exception
            monitor-exit(r7)     // Catch:{ all -> 0x0089 }
            throw r6
        L_0x008c:
            java.io.IOException r6 = new java.io.IOException     // Catch:{ all -> 0x0094 }
            java.lang.String r7 = "Canceled"
            r6.<init>(r7)     // Catch:{ all -> 0x0094 }
            throw r6     // Catch:{ all -> 0x0094 }
        L_0x0094:
            r6 = move-exception
            monitor-exit(r2)     // Catch:{ all -> 0x0094 }
            throw r6
        L_0x0097:
            java.io.IOException r6 = new java.io.IOException     // Catch:{ all -> 0x00af }
            java.lang.String r7 = "Canceled"
            r6.<init>(r7)     // Catch:{ all -> 0x00af }
            throw r6     // Catch:{ all -> 0x00af }
        L_0x009f:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException     // Catch:{ all -> 0x00af }
            java.lang.String r7 = "codec != null"
            r6.<init>(r7)     // Catch:{ all -> 0x00af }
            throw r6     // Catch:{ all -> 0x00af }
        L_0x00a7:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException     // Catch:{ all -> 0x00af }
            java.lang.String r7 = "released"
            r6.<init>(r7)     // Catch:{ all -> 0x00af }
            throw r6     // Catch:{ all -> 0x00af }
        L_0x00af:
            r6 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x00af }
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.connection.StreamAllocation.findConnection(int, int, int, boolean):okhttp3.internal.connection.RealConnection");
    }

    public void streamFinished(boolean z, HttpCodec httpCodec) {
        Socket deallocate;
        synchronized (this.connectionPool) {
            if (httpCodec != null) {
                if (httpCodec == this.codec) {
                    if (!z) {
                        this.connection.successCount++;
                    }
                    deallocate = deallocate(z, false, true);
                }
            }
            throw new IllegalStateException("expected " + this.codec + " but was " + httpCodec);
        }
        Util.closeQuietly(deallocate);
    }

    public HttpCodec codec() {
        HttpCodec httpCodec;
        synchronized (this.connectionPool) {
            httpCodec = this.codec;
        }
        return httpCodec;
    }

    private RouteDatabase routeDatabase() {
        return Internal.instance.routeDatabase(this.connectionPool);
    }

    public synchronized RealConnection connection() {
        return this.connection;
    }

    public void release() {
        Socket deallocate;
        synchronized (this.connectionPool) {
            deallocate = deallocate(false, true, false);
        }
        Util.closeQuietly(deallocate);
    }

    public void noNewStreams() {
        Socket deallocate;
        synchronized (this.connectionPool) {
            deallocate = deallocate(true, false, false);
        }
        Util.closeQuietly(deallocate);
    }

    private Socket deallocate(boolean z, boolean z2, boolean z3) {
        Socket socket;
        if (z3) {
            this.codec = null;
        }
        if (z2) {
            this.released = true;
        }
        RealConnection realConnection = this.connection;
        if (realConnection == null) {
            return null;
        }
        if (z) {
            realConnection.noNewStreams = true;
        }
        if (this.codec != null) {
            return null;
        }
        if (!this.released && !this.connection.noNewStreams) {
            return null;
        }
        release(this.connection);
        if (this.connection.allocations.isEmpty()) {
            this.connection.idleAtNanos = System.nanoTime();
            if (Internal.instance.connectionBecameIdle(this.connectionPool, this.connection)) {
                socket = this.connection.socket();
                this.connection = null;
                return socket;
            }
        }
        socket = null;
        this.connection = null;
        return socket;
    }

    public void cancel() {
        HttpCodec httpCodec;
        RealConnection realConnection;
        synchronized (this.connectionPool) {
            this.canceled = true;
            httpCodec = this.codec;
            realConnection = this.connection;
        }
        if (httpCodec != null) {
            httpCodec.cancel();
        } else if (realConnection != null) {
            realConnection.cancel();
        }
    }

    public void streamFailed(IOException iOException) {
        boolean z;
        Socket deallocate;
        synchronized (this.connectionPool) {
            if (iOException instanceof StreamResetException) {
                StreamResetException streamResetException = (StreamResetException) iOException;
                if (streamResetException.errorCode == ErrorCode.REFUSED_STREAM) {
                    this.refusedStreamCount++;
                }
                if (streamResetException.errorCode != ErrorCode.REFUSED_STREAM || this.refusedStreamCount > 1) {
                    this.route = null;
                }
                z = false;
                deallocate = deallocate(z, false, true);
            } else {
                RealConnection realConnection = this.connection;
                if (realConnection != null && (!realConnection.isMultiplexed() || (iOException instanceof ConnectionShutdownException))) {
                    if (this.connection.successCount == 0) {
                        Route route2 = this.route;
                        if (!(route2 == null || iOException == null)) {
                            this.routeSelector.connectFailed(route2, iOException);
                        }
                        this.route = null;
                    }
                }
                z = false;
                deallocate = deallocate(z, false, true);
            }
            z = true;
            deallocate = deallocate(z, false, true);
        }
        Util.closeQuietly(deallocate);
    }

    public void acquire(RealConnection realConnection) {
        if (this.connection == null) {
            this.connection = realConnection;
            realConnection.allocations.add(new StreamAllocationReference(this, this.callStackTrace));
            return;
        }
        throw new IllegalStateException();
    }

    private void release(RealConnection realConnection) {
        int size = realConnection.allocations.size();
        for (int i = 0; i < size; i++) {
            if (realConnection.allocations.get(i).get() == this) {
                realConnection.allocations.remove(i);
                return;
            }
        }
        throw new IllegalStateException();
    }

    public Socket releaseAndAcquire(RealConnection realConnection) {
        if (this.codec == null && this.connection.allocations.size() == 1) {
            Socket deallocate = deallocate(true, false, false);
            this.connection = realConnection;
            realConnection.allocations.add(this.connection.allocations.get(0));
            return deallocate;
        }
        throw new IllegalStateException();
    }

    public boolean hasMoreRoutes() {
        return this.route != null || this.routeSelector.hasNext();
    }

    public String toString() {
        RealConnection connection2 = connection();
        return connection2 != null ? connection2.toString() : this.address.toString();
    }

    public static final class StreamAllocationReference extends WeakReference<StreamAllocation> {
        public final Object callStackTrace;

        StreamAllocationReference(StreamAllocation streamAllocation, Object obj) {
            super(streamAllocation);
            this.callStackTrace = obj;
        }
    }
}
