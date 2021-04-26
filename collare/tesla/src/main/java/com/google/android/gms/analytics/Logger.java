package com.google.android.gms.analytics;

import com.google.android.gms.common.util.VisibleForTesting;

@VisibleForTesting
@Deprecated
public interface Logger {

    @Deprecated
    public static class LogLevel {
        public static final int ERROR = 3;
        public static final int INFO = 1;
        public static final int VERBOSE = 0;
        public static final int WARNING = 2;
    }

    @Deprecated
    void error(Exception exc);

    @Deprecated
    void error(String str);

    @Deprecated
    int getLogLevel();

    @Deprecated
    void info(String str);

    @Deprecated
    void setLogLevel(int i);

    @Deprecated
    void verbose(String str);

    @Deprecated
    void warn(String str);
}
