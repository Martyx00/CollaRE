package com.facebook.react.bridge;

public class JavaScriptContextHolder {
    private long mContext;

    public JavaScriptContextHolder(long j) {
        this.mContext = j;
    }

    public long get() {
        return this.mContext;
    }

    public synchronized void clear() {
        this.mContext = 0;
    }
}
