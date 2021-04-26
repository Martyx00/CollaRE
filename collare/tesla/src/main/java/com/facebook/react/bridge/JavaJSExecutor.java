package com.facebook.react.bridge;

import com.facebook.j.a.a;

@a
public interface JavaJSExecutor {

    public interface Factory {
        JavaJSExecutor create();
    }

    void close();

    @a
    String executeJSCall(String str, String str2);

    @a
    void loadApplicationScript(String str);

    @a
    void setGlobalVariable(String str, String str2);

    public static class ProxyExecutorException extends Exception {
        public ProxyExecutorException(Throwable th) {
            super(th);
        }
    }
}
