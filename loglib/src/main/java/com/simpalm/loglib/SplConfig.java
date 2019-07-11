package com.simpalm.loglib;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import java.util.ArrayList;
import java.util.List;

public class SplConfig {
    private static SplConfig splConfig;

    public static void setSplConfig(SplConfig splConfig) {
        SplConfig.splConfig = splConfig;
    }

    private boolean enableLogOutput = true;
    private boolean enableVerboseLogs = true;
    private boolean enableDebugLogs = true;
    private boolean enableInfoLogs = true;
    private boolean enableWarningLogs = true;
    private boolean enableErrorLogs = true;
    private String grantedPermissions = "";
    private String connectedNetworkType = "";
    private FileLogger fileLogger = null;

    private SplConfig() {
    }

    private SplConfig(Builder builder) {
        this.enableLogOutput = builder.enableLogOutput;
        this.enableVerboseLogs = builder.enableVerboseLogs;
        this.enableDebugLogs = builder.enableDebugLogs;
        this.enableInfoLogs = builder.enableInfoLogs;
        this.enableWarningLogs = builder.enableWarningLogs;
        this.enableErrorLogs = builder.enableErrorLogs;
        this.grantedPermissions = builder.grantedPermissions;
        this.connectedNetworkType = builder.connectedNetworkType;
        this.fileLogger = builder.fileLogger;

        final Thread.UncaughtExceptionHandler handler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                appendLog("UncaughtExceptionHandler", FileLogger.LogType.EX, Spl.toStackTrace(e));
                if (handler != null) {
                    handler.uncaughtException(t, e);
                }
            }
        });
    }

    public static SplConfig get() {
        return splConfig != null ? splConfig : new SplConfig();
    }

    public void setConnectedNetworkType(String connectedNetworkType) {
        this.connectedNetworkType = connectedNetworkType;
    }

    public boolean isEnableLogOutput() {
        return enableLogOutput;
    }

    public boolean isEnableVerboseLogs() {
        return enableVerboseLogs;
    }

    public boolean isEnableDebugLogs() {
        return enableDebugLogs;
    }

    public boolean isEnableInfoLogs() {
        return enableInfoLogs;
    }

    public boolean isEnableWarningLogs() {
        return enableWarningLogs;
    }

    public boolean isEnableErrorLogs() {
        return enableErrorLogs;
    }

    public String getGrantedPermissions() {
        return grantedPermissions;
    }

    public String getConnectedNetworkType() {
        return connectedNetworkType;
    }

    public void clearLogs() {
        if (fileLogger != null) {
            fileLogger.clearLogs();
        }
    }

    public void appendLog(String tag, FileLogger.LogType logType, String message) {
        if (fileLogger != null) {
            fileLogger.appendLog(tag, logType, message);
        }
    }

    public String getAllLogs() {
        if (fileLogger != null) {
            return fileLogger.getAllLogs();
        }
        return "";
    }

    public static class Builder {
        private boolean enableLogOutput = true;
        private boolean enableVerboseLogs = true;
        private boolean enableDebugLogs = true;
        private boolean enableInfoLogs = true;
        private boolean enableWarningLogs = true;
        private boolean enableErrorLogs = true;
        private String grantedPermissions = "";
        private String connectedNetworkType = "";
        private FileLogger fileLogger = null;

        public Builder(Context context) {
            List<String> permissions = getGrantedPermissions(context.getApplicationContext(), context.getApplicationContext().getPackageName());
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < permissions.size(); i++) {
                builder.append(permissions.get(i));
                if (i < permissions.size() - 1) {
                    builder.append(", ");
                }
            }
            grantedPermissions = builder.toString();
            connectedNetworkType = NetworkChangeReceiver.getConnectedNetwork(context);
            fileLogger = new FileLogger(context.getApplicationContext());
        }

        public Builder setEnableLogOutput(boolean enableLogOutput) {
            this.enableLogOutput = enableLogOutput;
            return this;
        }

        public Builder setEnableVerboseLogs(boolean enableVerboseLogs) {
            this.enableVerboseLogs = enableVerboseLogs;
            return this;
        }

        public Builder setEnableDebugLogs(boolean enableDebugLogs) {
            this.enableDebugLogs = enableDebugLogs;
            return this;
        }

        public Builder setEnableInfoLogs(boolean enableInfoLogs) {
            this.enableInfoLogs = enableInfoLogs;
            return this;
        }

        public Builder setEnableWarningLogs(boolean enableWarningLogs) {
            this.enableWarningLogs = enableWarningLogs;
            return this;
        }

        public Builder setEnableErrorLogs(boolean enableErrorLogs) {
            this.enableErrorLogs = enableErrorLogs;
            return this;
        }

        public SplConfig build() {
            return new SplConfig(this);
        }

        private List<String> getGrantedPermissions(Context applicationContext, final String appPackage) {
            List<String> granted = new ArrayList<String>();
            try {
                PackageInfo pi = applicationContext.getPackageManager().getPackageInfo(appPackage, PackageManager.GET_PERMISSIONS);
                for (int i = 0; i < pi.requestedPermissions.length; i++) {
                    if ((pi.requestedPermissionsFlags[i] & PackageInfo.REQUESTED_PERMISSION_GRANTED) != 0) {
                        granted.add(pi.requestedPermissions[i]);
                    }
                }
            } catch (Exception e) {
            }
            return granted;
        }
    }

    @Override
    public String toString() {
        return "SplConfig{" +
                "enableLogOutput=" + enableLogOutput +
                ", enableVerboseLogs=" + enableVerboseLogs +
                ", enableDebugLogs=" + enableDebugLogs +
                ", enableInfoLogs=" + enableInfoLogs +
                ", enableWarningLogs=" + enableWarningLogs +
                ", enableErrorLogs=" + enableErrorLogs +
                '}';
    }
}