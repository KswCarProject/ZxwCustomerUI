package com.szchoiceway.customerui.utils;

import android.os.Handler;
import android.os.Looper;
import com.szchoiceway.customerui.kt_like.Function0Void;
import java.lang.ref.WeakReference;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DebounceHelper<T> {
    private final Collector<T> collector;
    private T data;
    public long debounceTime = 100;
    private final Handler handler = new Handler(Looper.getMainLooper());
    private boolean isDataChanged = false;
    private boolean isDebounceRunning = false;
    private final DebounceRunnable<T> runnable = new DebounceRunnable<>(this, new Function0Void() {
        public final void invoke() {
            DebounceHelper.this.lambda$new$0$DebounceHelper();
        }
    });
    private final ExecutorService threadPool = Executors.newSingleThreadExecutor();

    public interface Collector<T> {
        void collect(T t);
    }

    public /* synthetic */ void lambda$new$0$DebounceHelper() {
        synchronized (DebounceRunnable.class) {
            if (this.isDataChanged) {
                this.handler.post(new Runnable() {
                    public final void run() {
                        DebounceHelper.this.collect();
                    }
                });
                this.isDataChanged = false;
            }
            this.isDebounceRunning = false;
        }
    }

    public DebounceHelper(Collector<T> collector2) {
        this.collector = collector2;
    }

    public synchronized void emit(T t) {
        this.data = t;
        if (!this.isDebounceRunning) {
            this.isDebounceRunning = true;
            this.isDataChanged = false;
            if (Looper.getMainLooper() != Looper.myLooper()) {
                this.handler.post(new Runnable() {
                    public final void run() {
                        DebounceHelper.this.collect();
                    }
                });
            } else {
                collect();
            }
            this.threadPool.execute(this.runnable);
        } else {
            this.isDataChanged = true;
        }
    }

    /* access modifiers changed from: private */
    public void collect() {
        Collector<T> collector2;
        T t = this.data;
        if (t != null && (collector2 = this.collector) != null) {
            collector2.collect(t);
        }
    }

    private static class DebounceRunnable<T> implements Runnable {
        private final Function0Void action;
        private final WeakReference<DebounceHelper<T>> helperWeak;

        DebounceRunnable(DebounceHelper<T> debounceHelper, Function0Void function0Void) {
            this.helperWeak = new WeakReference<>(debounceHelper);
            this.action = function0Void;
        }

        public void run() {
            long j = this.helperWeak.get() != null ? ((DebounceHelper) this.helperWeak.get()).debounceTime : -1;
            if (j == 0) {
                throw new RuntimeException("debounce time can not be 0.");
            } else if (j != -1) {
                try {
                    Thread.sleep(j);
                    this.action.invoke();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
