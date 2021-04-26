package com.crashlytics.android.core;

import io.a.a.a.a.b.i;
import io.a.a.a.a.b.t;
import io.a.a.a.c;
import io.a.a.a.l;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;

/* access modifiers changed from: package-private */
public class QueueFileLogStore implements FileLogStore {
    private t logFile;
    private final int maxLogSize;
    private final File workingFile;

    public class LogBytes {
        public final byte[] bytes;
        public final int offset;

        public LogBytes(byte[] bArr, int i) {
            this.bytes = bArr;
            this.offset = i;
        }
    }

    public QueueFileLogStore(File file, int i) {
        this.workingFile = file;
        this.maxLogSize = i;
    }

    @Override // com.crashlytics.android.core.FileLogStore
    public void writeToLog(long j, String str) {
        openLogFile();
        doWriteToLog(j, str);
    }

    @Override // com.crashlytics.android.core.FileLogStore
    public ByteString getLogAsByteString() {
        LogBytes logBytes = getLogBytes();
        if (logBytes == null) {
            return null;
        }
        return ByteString.copyFrom(logBytes.bytes, 0, logBytes.offset);
    }

    @Override // com.crashlytics.android.core.FileLogStore
    public byte[] getLogAsBytes() {
        LogBytes logBytes = getLogBytes();
        if (logBytes == null) {
            return null;
        }
        return logBytes.bytes;
    }

    private LogBytes getLogBytes() {
        if (!this.workingFile.exists()) {
            return null;
        }
        openLogFile();
        t tVar = this.logFile;
        if (tVar == null) {
            return null;
        }
        final int[] iArr = {0};
        final byte[] bArr = new byte[tVar.a()];
        try {
            this.logFile.a(new t.c() {
                /* class com.crashlytics.android.core.QueueFileLogStore.AnonymousClass1 */

                @Override // io.a.a.a.a.b.t.c
                public void read(InputStream inputStream, int i) {
                    try {
                        inputStream.read(bArr, iArr[0], i);
                        int[] iArr = iArr;
                        iArr[0] = iArr[0] + i;
                    } finally {
                        inputStream.close();
                    }
                }
            });
        } catch (IOException e) {
            c.g().e(CrashlyticsCore.TAG, "A problem occurred while reading the Crashlytics log file.", e);
        }
        return new LogBytes(bArr, iArr[0]);
    }

    @Override // com.crashlytics.android.core.FileLogStore
    public void closeLogFile() {
        i.a(this.logFile, "There was a problem closing the Crashlytics log file.");
        this.logFile = null;
    }

    @Override // com.crashlytics.android.core.FileLogStore
    public void deleteLogFile() {
        closeLogFile();
        this.workingFile.delete();
    }

    private void openLogFile() {
        if (this.logFile == null) {
            try {
                this.logFile = new t(this.workingFile);
            } catch (IOException e) {
                l g = c.g();
                g.e(CrashlyticsCore.TAG, "Could not open log file: " + this.workingFile, e);
            }
        }
    }

    private void doWriteToLog(long j, String str) {
        if (this.logFile != null) {
            if (str == null) {
                str = "null";
            }
            try {
                int i = this.maxLogSize / 4;
                if (str.length() > i) {
                    str = "..." + str.substring(str.length() - i);
                }
                this.logFile.a(String.format(Locale.US, "%d %s%n", Long.valueOf(j), str.replaceAll("\r", " ").replaceAll("\n", " ")).getBytes("UTF-8"));
                while (!this.logFile.b() && this.logFile.a() > this.maxLogSize) {
                    this.logFile.c();
                }
            } catch (IOException e) {
                c.g().e(CrashlyticsCore.TAG, "There was a problem writing to the Crashlytics log.", e);
            }
        }
    }
}
