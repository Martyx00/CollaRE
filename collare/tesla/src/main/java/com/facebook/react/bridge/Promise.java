package com.facebook.react.bridge;

public interface Promise {
    @Deprecated
    void reject(String str);

    void reject(String str, WritableMap writableMap);

    void reject(String str, String str2);

    void reject(String str, String str2, WritableMap writableMap);

    void reject(String str, String str2, Throwable th);

    void reject(String str, String str2, Throwable th, WritableMap writableMap);

    void reject(String str, Throwable th);

    void reject(String str, Throwable th, WritableMap writableMap);

    void reject(Throwable th);

    void reject(Throwable th, WritableMap writableMap);

    void resolve(Object obj);
}
