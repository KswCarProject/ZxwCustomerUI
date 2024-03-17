package com.f2prateek.ln;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class LnFacade implements LnInterface {
    private ArrayList<LnInterface> endpoints;
    protected int minimumLogLevel;

    public LnFacade(LnInterface lnInterface, LnInterface... lnInterfaceArr) {
        this(4, lnInterface, lnInterfaceArr);
    }

    public LnFacade(int i, LnInterface lnInterface, LnInterface... lnInterfaceArr) {
        ArrayList<LnInterface> arrayList = new ArrayList<>(lnInterfaceArr.length + 1);
        this.endpoints = arrayList;
        Collections.addAll(arrayList, new LnInterface[]{lnInterface});
        if (lnInterfaceArr.length > 0) {
            Collections.addAll(this.endpoints, lnInterfaceArr);
        }
        setLoggingLevel(i);
    }

    public void addLoggingEndpoint(LnInterface lnInterface) {
        this.endpoints.add(lnInterface);
    }

    public void v(Throwable th) {
        Iterator<LnInterface> it = this.endpoints.iterator();
        while (it.hasNext()) {
            it.next().v(th);
        }
    }

    public void v(Object obj, Object... objArr) {
        Iterator<LnInterface> it = this.endpoints.iterator();
        while (it.hasNext()) {
            it.next().v(obj, objArr);
        }
    }

    public void v(Throwable th, Object obj, Object... objArr) {
        Iterator<LnInterface> it = this.endpoints.iterator();
        while (it.hasNext()) {
            it.next().v(th, obj, objArr);
        }
    }

    public void d(Throwable th) {
        Iterator<LnInterface> it = this.endpoints.iterator();
        while (it.hasNext()) {
            it.next().d(th);
        }
    }

    public void d(Object obj, Object... objArr) {
        Iterator<LnInterface> it = this.endpoints.iterator();
        while (it.hasNext()) {
            it.next().d(obj, objArr);
        }
    }

    public void d(Throwable th, Object obj, Object... objArr) {
        Iterator<LnInterface> it = this.endpoints.iterator();
        while (it.hasNext()) {
            it.next().d(th, obj, objArr);
        }
    }

    public void i(Throwable th) {
        Iterator<LnInterface> it = this.endpoints.iterator();
        while (it.hasNext()) {
            it.next().i(th);
        }
    }

    public void i(Throwable th, Object obj, Object... objArr) {
        Iterator<LnInterface> it = this.endpoints.iterator();
        while (it.hasNext()) {
            it.next().i(th, obj, objArr);
        }
    }

    public void i(Object obj, Object... objArr) {
        Iterator<LnInterface> it = this.endpoints.iterator();
        while (it.hasNext()) {
            it.next().i(obj, objArr);
        }
    }

    public void w(Throwable th) {
        Iterator<LnInterface> it = this.endpoints.iterator();
        while (it.hasNext()) {
            it.next().w(th);
        }
    }

    public void w(Throwable th, Object obj, Object... objArr) {
        Iterator<LnInterface> it = this.endpoints.iterator();
        while (it.hasNext()) {
            it.next().w(th, obj, objArr);
        }
    }

    public void w(Object obj, Object... objArr) {
        Iterator<LnInterface> it = this.endpoints.iterator();
        while (it.hasNext()) {
            it.next().w(obj, objArr);
        }
    }

    public void e(Throwable th) {
        Iterator<LnInterface> it = this.endpoints.iterator();
        while (it.hasNext()) {
            it.next().e(th);
        }
    }

    public void e(Throwable th, Object obj, Object... objArr) {
        Iterator<LnInterface> it = this.endpoints.iterator();
        while (it.hasNext()) {
            it.next().e(th, obj, objArr);
        }
    }

    public void e(Object obj, Object... objArr) {
        Iterator<LnInterface> it = this.endpoints.iterator();
        while (it.hasNext()) {
            it.next().e(obj, objArr);
        }
    }

    public int getLoggingLevel() {
        return this.minimumLogLevel;
    }

    public void setLoggingLevel(int i) {
        Iterator<LnInterface> it = this.endpoints.iterator();
        while (it.hasNext()) {
            it.next().setLoggingLevel(i);
        }
    }
}
