package org.webrtc;

import org.webrtc.Logging;

/* access modifiers changed from: package-private */
public class JNILogging {
    private final Loggable loggable;

    public JNILogging(Loggable loggable2) {
        this.loggable = loggable2;
    }

    @CalledByNative
    public void logToInjectable(String str, Integer num, String str2) {
        this.loggable.onLogMessage(str, Logging.Severity.values()[num.intValue()], str2);
    }
}
