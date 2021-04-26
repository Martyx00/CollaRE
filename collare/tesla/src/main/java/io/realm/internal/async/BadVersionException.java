package io.realm.internal.async;

import io.realm.internal.Keep;

@Keep
public class BadVersionException extends Exception {
    public BadVersionException(String str) {
        super(str);
    }

    public BadVersionException(String str, Throwable th) {
        super(str, th);
    }
}
