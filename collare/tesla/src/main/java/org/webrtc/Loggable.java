package org.webrtc;

import org.webrtc.Logging;

public interface Loggable {
    void onLogMessage(String str, Logging.Severity severity, String str2);
}
