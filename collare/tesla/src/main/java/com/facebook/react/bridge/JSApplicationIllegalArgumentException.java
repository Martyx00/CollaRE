package com.facebook.react.bridge;

public class JSApplicationIllegalArgumentException extends JSApplicationCausedNativeException {
    public JSApplicationIllegalArgumentException(String str) {
        super(str);
    }

    public JSApplicationIllegalArgumentException(String str, Throwable th) {
        super(str, th);
    }
}
