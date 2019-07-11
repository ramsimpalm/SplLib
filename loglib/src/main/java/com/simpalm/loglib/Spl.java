package com.simpalm.loglib;

import android.graphics.Bitmap;
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

    /**
     * Same as Log.v
     *
     * @param o   the object
     * @param msg the message
     */
    public static void v(Object o, String msg) {
        if (o == null || msg == null || !SplConfig.get().isEnableVerboseLogs()) return;

        if (SplConfig.get().isEnableLogOutput()) {
            Log.v(getTAG(o), msg);
        }
        SplConfig.get().appendLog(getTAG(o), FileLogger.LogType.V, msg);
        logExtraInfo(o, SplConfig.get().isEnableLogOutput());
    }

    /**
     * Same as Log.v
     *
     * @param o   the object
     * @param msg the message
     * @param t   the exception
     */
    public static void v(Object o, String msg, Throwable t) {
        if (o == null || msg == null || !SplConfig.get().isEnableVerboseLogs()) return;

        if (SplConfig.get().isEnableLogOutput()) {
            Log.v(getTAG(o), msg, t);
        }
        SplConfig.get().appendLog(getTAG(o), FileLogger.LogType.V, msg + FileLogger.NEW_LINE + toStackTrace(t));
        logExtraInfo(o, SplConfig.get().isEnableLogOutput());
    }

    /**
     * Same as Log.v
     *
     * @param o the object
     * @param t the exception
     */
    public static void v(Object o, Throwable t) {
        if (o == null || t == null || !SplConfig.get().isEnableVerboseLogs()) return;

        if (SplConfig.get().isEnableLogOutput()) {
            Log.v(getTAG(o), toMessage(t));
        }
        SplConfig.get().appendLog(getTAG(o), FileLogger.LogType.EX, toStackTrace(t));
        logExtraInfo(o, SplConfig.get().isEnableLogOutput());
    }

    /**
     * Same as Log.d
     *
     * @param o   the object
     * @param msg the message
     */
    public static void d(Object o, String msg) {
        if (o == null || msg == null || !SplConfig.get().isEnableDebugLogs()) return;

        if (SplConfig.get().isEnableLogOutput()) {
            Log.d(getTAG(o), msg);
        }
        SplConfig.get().appendLog(getTAG(o), FileLogger.LogType.D, msg);
        logExtraInfo(o, SplConfig.get().isEnableLogOutput());
    }

    /**
     * Same as Log.d
     *
     * @param o   the object
     * @param msg the message
     * @param t   the exception
     */
    public static void d(Object o, String msg, Throwable t) {
        if (o == null || msg == null || !SplConfig.get().isEnableDebugLogs()) return;

        if (SplConfig.get().isEnableLogOutput()) {
            Log.d(getTAG(o), msg, t);
        }
        SplConfig.get().appendLog(getTAG(o), FileLogger.LogType.D, msg + FileLogger.NEW_LINE + toStackTrace(t));
        logExtraInfo(o, SplConfig.get().isEnableLogOutput());
    }

    /**
     * Same as Log.d
     *
     * @param o the object
     * @param t the exception
     */
    public static void d(Object o, Throwable t) {
        if (o == null || t == null || !SplConfig.get().isEnableDebugLogs()) return;

        if (SplConfig.get().isEnableLogOutput()) {
            Log.d(getTAG(o), toMessage(t));
        }
        SplConfig.get().appendLog(getTAG(o), FileLogger.LogType.EX, toStackTrace(t));
        logExtraInfo(o, SplConfig.get().isEnableLogOutput());
    }

    /**
     * Same as Log.i
     *
     * @param o   the object
     * @param msg the message
     */
    public static void i(Object o, String msg) {
        if (o == null || msg == null || !SplConfig.get().isEnableInfoLogs()) return;

        if (SplConfig.get().isEnableLogOutput()) {
            Log.i(getTAG(o), msg);
        }
        SplConfig.get().appendLog(getTAG(o), FileLogger.LogType.I, msg);
        logExtraInfo(o, SplConfig.get().isEnableLogOutput());
    }

    /**
     * Same as Log.i
     *
     * @param o   the object
     * @param msg the message
     * @param t   the exception
     */
    public static void i(Object o, String msg, Throwable t) {
        if (o == null || msg == null || !SplConfig.get().isEnableInfoLogs()) return;

        if (SplConfig.get().isEnableLogOutput()) {
            Log.i(getTAG(o), msg, t);
        }
        SplConfig.get().appendLog(getTAG(o), FileLogger.LogType.I, msg + FileLogger.NEW_LINE + toStackTrace(t));
        logExtraInfo(o, SplConfig.get().isEnableLogOutput());
    }

    /**
     * Same as Log.i
     *
     * @param o the object
     * @param t the exception
     */
    public static void i(Object o, Throwable t) {
        if (o == null || t == null || !SplConfig.get().isEnableInfoLogs()) return;

        if (SplConfig.get().isEnableLogOutput()) {
            Log.i(getTAG(o), toMessage(t));
        }
        SplConfig.get().appendLog(getTAG(o), FileLogger.LogType.EX, toStackTrace(t));
        logExtraInfo(o, SplConfig.get().isEnableLogOutput());
    }

    /**
     * Same as Log.w
     *
     * @param o   the object
     * @param msg the message
     */
    public static void w(Object o, String msg) {
        if (o == null || msg == null || !SplConfig.get().isEnableWarningLogs()) return;

        if (SplConfig.get().isEnableLogOutput()) {
            Log.w(getTAG(o), msg);
        }
        SplConfig.get().appendLog(getTAG(o), FileLogger.LogType.W, msg);
        logExtraInfo(o, SplConfig.get().isEnableLogOutput());
    }

    /**
     * Same as Log.w
     *
     * @param o   the object
     * @param msg the message
     * @param t   the exception
     */
    public static void w(Object o, String msg, Throwable t) {
        if (o == null || msg == null || !SplConfig.get().isEnableWarningLogs()) return;

        if (SplConfig.get().isEnableLogOutput()) {
            Log.w(getTAG(o), msg, t);
        }
        SplConfig.get().appendLog(getTAG(o), FileLogger.LogType.W, msg + FileLogger.NEW_LINE + toStackTrace(t));
        logExtraInfo(o, SplConfig.get().isEnableLogOutput());
    }

    /**
     * Same as Log.w
     *
     * @param o the object
     * @param t the exception
     */
    public static void w(Object o, Throwable t) {
        if (o == null || t == null || !SplConfig.get().isEnableWarningLogs()) return;

        if (SplConfig.get().isEnableLogOutput()) {
            Log.w(getTAG(o), toMessage(t));
        }
        SplConfig.get().appendLog(getTAG(o), FileLogger.LogType.EX, toStackTrace(t));
        logExtraInfo(o, SplConfig.get().isEnableLogOutput());
    }

    /**
     * Same as Log.e
     *
     * @param o   the object
     * @param msg the message
     */
    public static void e(Object o, String msg) {
        if (o == null || msg == null || !SplConfig.get().isEnableErrorLogs()) return;

        if (SplConfig.get().isEnableLogOutput()) {
            Log.e(getTAG(o), msg);
        }
        SplConfig.get().appendLog(getTAG(o), FileLogger.LogType.E, msg);
        logExtraInfo(o, SplConfig.get().isEnableLogOutput());
    }

    /**
     * Same as Log.e
     *
     * @param o   the object
     * @param msg the message
     * @param t   the exception
     */
    public static void e(Object o, String msg, Throwable t) {
        if (o == null || msg == null || !SplConfig.get().isEnableErrorLogs()) return;

        if (SplConfig.get().isEnableLogOutput()) {
            Log.e(getTAG(o), msg, t);
        }
        SplConfig.get().appendLog(getTAG(o), FileLogger.LogType.E, msg + FileLogger.NEW_LINE + toStackTrace(t));
        logExtraInfo(o, SplConfig.get().isEnableLogOutput());
    }

    /**
     * Same as Log.e
     *
     * @param o the object
     * @param t the exception
     */
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

    /**
     * Gets the exception printable text
     *
     * @param t the exception
     * @return the text
     */
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

    /**
     * Logs the bitmap info and its effect is same as Log.d
     *
     * @param o      the tag
     * @param bitmap the bitmap
     */
    public static void logBitmapInfo(Object o, Bitmap bitmap) {
        SplConfig config = SplConfig.get();
        String info;
        if (bitmap == null) {
            info = "Bitmap is null";
        } else {
            info = "Bitmap dimension=" + bitmap.getWidth() + "x" + bitmap.getHeight() + ", size=" + bitmap.getByteCount() + " bytes";
        }
        if (config.isEnableLogOutput()) {
            Log.d(getTAG(o), info);
        }
        SplConfig.get().appendLog(getTAG(o), FileLogger.LogType.EI, info);
    }

    /**
     * Logs the user info and its effect is same as Log.d
     *
     * @param o    the tag
     * @param info the use info
     */
    public static void logUserInfo(Object o, String info) {
        SplConfig config = SplConfig.get();
        if (config.isEnableLogOutput()) {
            Log.d(getTAG(o), "User=>" + info);
        }
        SplConfig.get().appendLog(getTAG(o), FileLogger.LogType.EI, info);
    }

    /**
     * Logs the method info and its effect is same as Log.d
     *
     * @param o              the tag
     * @param name           the method name
     * @param nameValuePairs name value pair of method arguments<br/>
     *                       for example logUserInfo(Object o, String info) method should be logged like this:<br/>
     *                       Spl.logMethod(this, "logUserInfo", "o", o, "info", info);
     */
    public static void logMethod(Object o, String name, Object... nameValuePairs) {
        StringBuilder builder = new StringBuilder();
        builder.append(name).append("=>");
        if (nameValuePairs != null) {
            for (int i = 0; i < nameValuePairs.length / 2; i++) {
                builder.append(nameValuePairs[i]).append("=").append(nameValuePairs[i + 1]);
            }
        }
        SplConfig config = SplConfig.get();
        if (config.isEnableLogOutput()) {
            Log.d(getTAG(o), builder.toString());
        }
        SplConfig.get().appendLog(getTAG(o), FileLogger.LogType.EI, builder.toString());
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
