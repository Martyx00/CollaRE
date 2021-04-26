package com.crashlytics.android.core;

/* access modifiers changed from: package-private */
public interface FileLogStore {
    void closeLogFile();

    void deleteLogFile();

    ByteString getLogAsByteString();

    byte[] getLogAsBytes();

    void writeToLog(long j, String str);
}
