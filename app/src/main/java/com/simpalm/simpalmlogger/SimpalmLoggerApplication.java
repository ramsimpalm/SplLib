package com.simpalm.simpalmlogger;

import android.app.Application;
import com.simpalm.loglib.SplConfig;

public class SimpalmLoggerApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        SplConfig.setSplConfig(new SplConfig.Builder(this)
                .setEnableLogOutput(true)
                .setEnableVerboseLogs(true)
                .setEnableDebugLogs(true)
                .setEnableInfoLogs(true)
                .setEnableWarningLogs(true)
                .setEnableErrorLogs(true).build());
    }
}
