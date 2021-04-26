package com.facebook.common.k;

/* compiled from: ExceptionWithNoStacktrace */
public class a extends Exception {
    public synchronized Throwable fillInStackTrace() {
        return this;
    }

    public a(String str) {
        super(str);
    }
}
