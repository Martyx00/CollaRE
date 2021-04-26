package org.webrtc;

import java.io.UnsupportedEncodingException;
import java.util.Map;
import org.spongycastle.i18n.LocalizedMessage;

class JniHelper {
    JniHelper() {
    }

    @CalledByNative
    static byte[] getStringBytes(String str) {
        try {
            return str.getBytes(LocalizedMessage.DEFAULT_ENCODING);
        } catch (UnsupportedEncodingException unused) {
            throw new RuntimeException("ISO-8859-1 is unsupported");
        }
    }

    @CalledByNative
    static Object getStringClass() {
        return String.class;
    }

    @CalledByNative
    static Object getKey(Map.Entry entry) {
        return entry.getKey();
    }

    @CalledByNative
    static Object getValue(Map.Entry entry) {
        return entry.getValue();
    }
}
