package com.szchoiceway.customerui.utils;

import android.util.Log;

public class LogHelps {
    private static boolean OpenLowLog = false;
    static String className = "LogHelps";
    static String fileName;
    static int lineNumber;
    static String methodName;

    public static boolean isDebug() {
        return true;
    }

    public static boolean isOpenLowLog() {
        return OpenLowLog;
    }

    public static void setOpenLowLog(boolean z) {
        OpenLowLog = z;
    }

    private static String createLog(String str) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("(").append(fileName).append(":").append(lineNumber).append(") ");
        stringBuffer.append(methodName);
        stringBuffer.append(": ");
        stringBuffer.append(str);
        return stringBuffer.toString();
    }

    private static void getMethodNames(StackTraceElement[] stackTraceElementArr) {
        methodName = stackTraceElementArr[1].getMethodName();
        fileName = stackTraceElementArr[1].getFileName();
        lineNumber = stackTraceElementArr[1].getLineNumber();
    }

    public static void v(String... strArr) {
        if (isDebug()) {
            getMethodNames(new Throwable().getStackTrace());
            Log.v(className, createLog(strArr));
        }
    }

    public static void d(String... strArr) {
        if (isDebug()) {
            getMethodNames(new Throwable().getStackTrace());
            Log.d(className, createLog(strArr));
        }
    }

    public static void i(String... strArr) {
        if (isDebug()) {
            getMethodNames(new Throwable().getStackTrace());
            Log.i(className, createLog(strArr));
        }
    }

    public static void w(String... strArr) {
        if (isDebug()) {
            getMethodNames(new Throwable().getStackTrace());
            Log.w(className, createLog(strArr));
        }
    }

    public static void e(String... strArr) {
        if (isDebug()) {
            getMethodNames(new Throwable().getStackTrace());
            Log.e(className, createLog(strArr));
        }
    }

    public static void wLow(String... strArr) {
        if (isDebug() && OpenLowLog) {
            getMethodNames(new Throwable().getStackTrace());
            Log.w(className, createLog(strArr));
        }
    }

    public static void iLow(String... strArr) {
        if (isDebug() && OpenLowLog) {
            getMethodNames(new Throwable().getStackTrace());
            Log.i(className, createLog(strArr));
        }
    }

    private static String createLog(String... strArr) {
        StringBuffer append = new StringBuffer().append("(").append(fileName).append(":").append(lineNumber).append(")").append(" ").append(methodName).append(" -> ");
        if (strArr == null) {
            return append.toString();
        }
        for (int i = 0; i < strArr.length; i++) {
            append.append(strArr[i]);
            if (i != strArr.length - 1) {
                append.append(" & ");
            }
        }
        return append.toString();
    }

    private static String generateBlank(int i) {
        StringBuilder sb = new StringBuilder();
        for (int i2 = 0; i2 < i; i2++) {
            sb.append(" ");
        }
        return sb.toString();
    }
}
