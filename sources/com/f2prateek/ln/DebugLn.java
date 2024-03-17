package com.f2prateek.ln;

import android.content.Context;
import android.content.pm.PackageManager;
import android.util.Log;

public class DebugLn implements LnInterface {
    private static final String MSG_FORMAT = "[%s] %s";
    private static final String TAG_FORMAT = "%s/%s:%s";
    protected int minimumLogLevel;
    protected String packageName;

    public DebugLn(String str, int i) {
        this.packageName = str;
        this.minimumLogLevel = i;
    }

    public static DebugLn from(Context context) {
        String packageName2 = context.getPackageName();
        int i = 4;
        try {
            if ((context.getPackageManager().getApplicationInfo(packageName2, 0).flags & 2) != 0) {
                i = 2;
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return new DebugLn(packageName2, i);
    }

    public void v(Throwable th) {
        if (getLoggingLevel() <= 2) {
            println(2, Log.getStackTraceString(th));
        }
    }

    public void v(Object obj, Object... objArr) {
        if (getLoggingLevel() <= 2) {
            String strings = Strings.toString(obj);
            if (objArr.length > 0) {
                strings = String.format(strings, objArr);
            }
            println(2, strings);
        }
    }

    public void v(Throwable th, Object obj, Object... objArr) {
        if (getLoggingLevel() <= 2) {
            String strings = Strings.toString(obj);
            StringBuilder sb = new StringBuilder();
            if (objArr.length > 0) {
                strings = String.format(strings, objArr);
            }
            println(2, sb.append(strings).append(10).append(Log.getStackTraceString(th)).toString());
        }
    }

    public void d(Throwable th) {
        if (getLoggingLevel() <= 3) {
            println(3, Log.getStackTraceString(th));
        }
    }

    public void d(Object obj, Object... objArr) {
        if (getLoggingLevel() <= 3) {
            String strings = Strings.toString(obj);
            if (objArr.length > 0) {
                strings = String.format(strings, objArr);
            }
            println(3, strings);
        }
    }

    public void d(Throwable th, Object obj, Object... objArr) {
        if (getLoggingLevel() <= 3) {
            String strings = Strings.toString(obj);
            StringBuilder sb = new StringBuilder();
            if (objArr.length > 0) {
                strings = String.format(strings, objArr);
            }
            println(3, sb.append(strings).append(10).append(Log.getStackTraceString(th)).toString());
        }
    }

    public void i(Throwable th) {
        if (getLoggingLevel() <= 4) {
            println(4, Log.getStackTraceString(th));
        }
    }

    public void i(Throwable th, Object obj, Object... objArr) {
        if (getLoggingLevel() <= 4) {
            String strings = Strings.toString(obj);
            StringBuilder sb = new StringBuilder();
            if (objArr.length > 0) {
                strings = String.format(strings, objArr);
            }
            println(4, sb.append(strings).append(10).append(Log.getStackTraceString(th)).toString());
        }
    }

    public void i(Object obj, Object... objArr) {
        if (getLoggingLevel() <= 4) {
            String strings = Strings.toString(obj);
            if (objArr.length > 0) {
                strings = String.format(strings, objArr);
            }
            println(4, strings);
        }
    }

    public void w(Throwable th) {
        if (getLoggingLevel() <= 5) {
            println(5, Log.getStackTraceString(th));
        }
    }

    public void w(Throwable th, Object obj, Object... objArr) {
        if (getLoggingLevel() <= 5) {
            String strings = Strings.toString(obj);
            StringBuilder sb = new StringBuilder();
            if (objArr.length > 0) {
                strings = String.format(strings, objArr);
            }
            println(5, sb.append(strings).append(10).append(Log.getStackTraceString(th)).toString());
        }
    }

    public void w(Object obj, Object... objArr) {
        if (getLoggingLevel() <= 5) {
            String strings = Strings.toString(obj);
            if (objArr.length > 0) {
                strings = String.format(strings, objArr);
            }
            println(5, strings);
        }
    }

    public void e(Throwable th) {
        if (getLoggingLevel() <= 6) {
            println(6, Log.getStackTraceString(th));
        }
    }

    public void e(Throwable th, Object obj, Object... objArr) {
        if (getLoggingLevel() <= 6) {
            String strings = Strings.toString(obj);
            StringBuilder sb = new StringBuilder();
            if (objArr.length > 0) {
                strings = String.format(strings, objArr);
            }
            println(6, sb.append(strings).append(10).append(Log.getStackTraceString(th)).toString());
        }
    }

    public void e(Object obj, Object... objArr) {
        if (getLoggingLevel() <= 6) {
            String strings = Strings.toString(obj);
            if (objArr.length > 0) {
                strings = String.format(strings, objArr);
            }
            println(6, strings);
        }
    }

    public int getLoggingLevel() {
        return this.minimumLogLevel;
    }

    public void setLoggingLevel(int i) {
        this.minimumLogLevel = i;
    }

    /* access modifiers changed from: protected */
    public void println(int i, String str) {
        Log.println(i, processTag(this.packageName), processMessage(str));
    }

    /* access modifiers changed from: protected */
    public String processMessage(String str) {
        return String.format(MSG_FORMAT, new Object[]{Thread.currentThread().getName(), str});
    }

    /* access modifiers changed from: protected */
    public String processTag(String str) {
        StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[6];
        return String.format(TAG_FORMAT, new Object[]{str, stackTraceElement.getFileName(), Integer.valueOf(stackTraceElement.getLineNumber())});
    }
}
