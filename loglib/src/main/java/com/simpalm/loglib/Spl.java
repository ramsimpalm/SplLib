package com.simpalm.loglib;

import android.os.Build;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;

public class Spl {
    private static Map<Object, String> map = new WeakHashMap<>();
    private static List<String> queue = new ArrayList<>();
    private static int lastHashCode = 0;

    private static String getTAG(Object o) {
        String tag = map.get(o);
        if (tag == null) {
            setTAG(o);
            tag = map.get(o);
        }
        int lastIndex = queue.size() - 1;
        if (lastIndex == -1 || !tag.equals(queue.get(lastIndex))) {
            queue.add(tag);
        }
        return tag;
    }

    private static void setTAG(Object o) {
        String tag;
        if (o instanceof String) {
            tag = (String) o;
        } else {
            tag = o.getClass().getSimpleName();
        }
        Spl.map.put(o, tag);
    }

    public static void v(Object o, String msg) {
        if (o == null || msg == null || !SplConfig.get().isEnableVerboseLogs()) return;

        if (SplConfig.get().isEnableLogOutput()) {
            Log.v(getTAG(o), msg);
        }
        SplConfig.get().appendLog(getTAG(o), FileLogger.LogType.V, msg);
        logExtraInfo(o, SplConfig.get().isEnableLogOutput());
    }

    public static void v(Object o, String msg, Throwable t) {
        if (o == null || msg == null || !SplConfig.get().isEnableVerboseLogs()) return;

        if (SplConfig.get().isEnableLogOutput()) {
            Log.v(getTAG(o), msg, t);
        }
        SplConfig.get().appendLog(getTAG(o), FileLogger.LogType.V, msg + FileLogger.NEW_LINE + toStackTrace(t));
        logExtraInfo(o, SplConfig.get().isEnableLogOutput());
    }

    public static void v(Object o, Throwable t) {
        if (o == null || t == null || !SplConfig.get().isEnableVerboseLogs()) return;

        if (SplConfig.get().isEnableLogOutput()) {
            Log.v(getTAG(o), toMessage(t));
        }
        SplConfig.get().appendLog(getTAG(o), FileLogger.LogType.EX, toStackTrace(t));
        logExtraInfo(o, SplConfig.get().isEnableLogOutput());
    }

    public static void d(Object o, String msg) {
        if (o == null || msg == null || !SplConfig.get().isEnableDebugLogs()) return;

        if (SplConfig.get().isEnableLogOutput()) {
            Log.d(getTAG(o), msg);
        }
        SplConfig.get().appendLog(getTAG(o), FileLogger.LogType.D, msg);
        logExtraInfo(o, SplConfig.get().isEnableLogOutput());
    }

    public static void d(Object o, String msg, Throwable t) {
        if (o == null || msg == null || !SplConfig.get().isEnableDebugLogs()) return;

        if (SplConfig.get().isEnableLogOutput()) {
            Log.d(getTAG(o), msg, t);
        }
        SplConfig.get().appendLog(getTAG(o), FileLogger.LogType.D, msg + FileLogger.NEW_LINE + toStackTrace(t));
        logExtraInfo(o, SplConfig.get().isEnableLogOutput());
    }

    public static void d(Object o, Throwable t) {
        if (o == null || t == null || !SplConfig.get().isEnableDebugLogs()) return;

        if (SplConfig.get().isEnableLogOutput()) {
            Log.d(getTAG(o), toMessage(t));
        }
        SplConfig.get().appendLog(getTAG(o), FileLogger.LogType.EX, toStackTrace(t));
        logExtraInfo(o, SplConfig.get().isEnableLogOutput());
    }

    public static void i(Object o, String msg) {
        if (o == null || msg == null || !SplConfig.get().isEnableInfoLogs()) return;

        if (SplConfig.get().isEnableLogOutput()) {
            Log.i(getTAG(o), msg);
        }
        SplConfig.get().appendLog(getTAG(o), FileLogger.LogType.I, msg);
        logExtraInfo(o, SplConfig.get().isEnableLogOutput());
    }

    public static void i(Object o, String msg, Throwable t) {
        if (o == null || msg == null || !SplConfig.get().isEnableInfoLogs()) return;

        if (SplConfig.get().isEnableLogOutput()) {
            Log.i(getTAG(o), msg, t);
        }
        SplConfig.get().appendLog(getTAG(o), FileLogger.LogType.I, msg + FileLogger.NEW_LINE + toStackTrace(t));
        logExtraInfo(o, SplConfig.get().isEnableLogOutput());
    }

    public static void i(Object o, Throwable t) {
        if (o == null || t == null || !SplConfig.get().isEnableInfoLogs()) return;

        if (SplConfig.get().isEnableLogOutput()) {
            Log.i(getTAG(o), toMessage(t));
        }
        SplConfig.get().appendLog(getTAG(o), FileLogger.LogType.EX, toStackTrace(t));
        logExtraInfo(o, SplConfig.get().isEnableLogOutput());
    }

    public static void w(Object o, String msg) {
        if (o == null || msg == null || !SplConfig.get().isEnableWarningLogs()) return;

        if (SplConfig.get().isEnableLogOutput()) {
            Log.w(getTAG(o), msg);
        }
        SplConfig.get().appendLog(getTAG(o), FileLogger.LogType.W, msg);
        logExtraInfo(o, SplConfig.get().isEnableLogOutput());
    }

    public static void w(Object o, String msg, Throwable t) {
        if (o == null || msg == null || !SplConfig.get().isEnableWarningLogs()) return;

        if (SplConfig.get().isEnableLogOutput()) {
            Log.w(getTAG(o), msg, t);
        }
        SplConfig.get().appendLog(getTAG(o), FileLogger.LogType.W, msg + FileLogger.NEW_LINE + toStackTrace(t));
        logExtraInfo(o, SplConfig.get().isEnableLogOutput());
    }

    public static void w(Object o, Throwable t) {
        if (o == null || t == null || !SplConfig.get().isEnableWarningLogs()) return;

        if (SplConfig.get().isEnableLogOutput()) {
            Log.w(getTAG(o), toMessage(t));
        }
        SplConfig.get().appendLog(getTAG(o), FileLogger.LogType.EX, toStackTrace(t));
        logExtraInfo(o, SplConfig.get().isEnableLogOutput());
    }

    public static void e(Object o, String msg) {
        if (o == null || msg == null || !SplConfig.get().isEnableErrorLogs()) return;

        if (SplConfig.get().isEnableLogOutput()) {
            Log.e(getTAG(o), msg);
        }
        SplConfig.get().appendLog(getTAG(o), FileLogger.LogType.E, msg);
        logExtraInfo(o, SplConfig.get().isEnableLogOutput());
    }

    public static void e(Object o, String msg, Throwable t) {
        if (o == null || msg == null || !SplConfig.get().isEnableErrorLogs()) return;

        if (SplConfig.get().isEnableLogOutput()) {
            Log.e(getTAG(o), msg, t);
        }
        SplConfig.get().appendLog(getTAG(o), FileLogger.LogType.E, msg + FileLogger.NEW_LINE + toStackTrace(t));
        logExtraInfo(o, SplConfig.get().isEnableLogOutput());
    }

    public static void e(Object o, Throwable t) {
        if (o == null || t == null || !SplConfig.get().isEnableErrorLogs()) return;

        if (SplConfig.get().isEnableLogOutput()) {
            Log.e(getTAG(o), toMessage(t));
        }
        SplConfig.get().appendLog(getTAG(o), FileLogger.LogType.EX, toStackTrace(t));
        logExtraInfo(o, SplConfig.get().isEnableLogOutput());
    }

    private static String toMessage(Throwable t) {
        if (t.getLocalizedMessage() != null) {
            return t.getLocalizedMessage();
        } else if (t.getMessage() != null) {
            return t.getMessage();
        }
        return "";
    }

    public static String toStackTrace(Throwable t) {
        StackTraceElement[] elements = t.getStackTrace();
        if (elements != null) {
            StringBuilder builder = new StringBuilder();
            for (StackTraceElement element : elements) {
                builder.append(element.getClassName()).append(".").append(element.getMethodName()).append("(").append(element.getLineNumber()).append(")").append("\r\n");
            }
            return builder.toString();
        }
        return "";
    }

    private static void logExtraInfo(Object o, boolean logOutputEnabled) {
        SplConfig config = SplConfig.get();
        String info = Build.BRAND + "(" + Build.MODEL + ")" + ", Granted_Permissions=" + config.getGrantedPermissions() + ", Network=" + config.getConnectedNetworkType() + ", Steps=" + getQueueContent();
        if (info.hashCode() != lastHashCode) {
            lastHashCode = info.hashCode();
            if (logOutputEnabled) {
                Log.d(getTAG(o), info);
            }
            SplConfig.get().appendLog(getTAG(o), FileLogger.LogType.EI, info);
        }
    }

    private static String getQueueContent() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < queue.size(); i++) {
            builder.append(queue.get(i));
            if (i < queue.size() - 1) {
                builder.append("->");
            }
        }
        return builder.toString();
    }
}
