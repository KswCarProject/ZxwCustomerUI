package com.f2prateek.ln;

public interface LnInterface {
    void d(Object obj, Object... objArr);

    void d(Throwable th);

    void d(Throwable th, Object obj, Object... objArr);

    void e(Object obj, Object... objArr);

    void e(Throwable th);

    void e(Throwable th, Object obj, Object... objArr);

    int getLoggingLevel();

    void i(Object obj, Object... objArr);

    void i(Throwable th);

    void i(Throwable th, Object obj, Object... objArr);

    void setLoggingLevel(int i);

    void v(Object obj, Object... objArr);

    void v(Throwable th);

    void v(Throwable th, Object obj, Object... objArr);

    void w(Object obj, Object... objArr);

    void w(Throwable th);

    void w(Throwable th, Object obj, Object... objArr);
}
