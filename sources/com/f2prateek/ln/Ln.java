package com.f2prateek.ln;

public class Ln {
    protected static LnInterface ln = new EmptyLn();

    public static String logLevelToString(int i) {
        switch (i) {
            case 2:
                return "VERBOSE";
            case 3:
                return "DEBUG";
            case 4:
                return "INFO";
            case 5:
                return "WARN";
            case 6:
                return "ERROR";
            case 7:
                return "ASSERT";
            default:
                return "UNKNOWN";
        }
    }

    private Ln() {
    }

    public static void set(LnInterface lnInterface) {
        ln = lnInterface;
    }

    public static void v(Throwable th) {
        ln.v(th);
    }

    public static void v(Object obj, Object... objArr) {
        ln.v(obj, objArr);
    }

    public static void v(Throwable th, Object obj, Object... objArr) {
        ln.v(th, obj, objArr);
    }

    public static void d(Throwable th) {
        ln.d(th);
    }

    public static void d(Object obj, Object... objArr) {
        ln.d(obj, objArr);
    }

    public static void d(Throwable th, Object obj, Object... objArr) {
        ln.d(th, obj, objArr);
    }

    public static void i(Throwable th) {
        ln.i(th);
    }

    public static void i(Object obj, Object... objArr) {
        ln.i(obj, objArr);
    }

    public static void i(Throwable th, Object obj, Object... objArr) {
        ln.i(th, obj, objArr);
    }

    public static void w(Throwable th) {
        ln.w(th);
    }

    public static void w(Object obj, Object... objArr) {
        ln.w(obj, objArr);
    }

    public static void w(Throwable th, Object obj, Object... objArr) {
        ln.w(th, obj, objArr);
    }

    public static void e(Throwable th) {
        ln.e(th);
    }

    public static void e(Object obj, Object... objArr) {
        ln.e(obj, objArr);
    }

    public static void e(Throwable th, Object obj, Object... objArr) {
        ln.e(th, obj, objArr);
    }

    public static int getLoggingLevel() {
        return ln.getLoggingLevel();
    }

    public static void setLoggingLevel(int i) {
        ln.setLoggingLevel(i);
    }
}
