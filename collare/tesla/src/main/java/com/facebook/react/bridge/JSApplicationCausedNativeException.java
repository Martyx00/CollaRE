package com.facebook.react.bridge;

public class JSApplicationCausedNativeException extends RuntimeException {
    public JSApplicationCausedNativeException(String str) {
        super(str);
    }

    public JSApplicationCausedNativeException(String str, Throwable th) {
        super(str, th);
    }
}
