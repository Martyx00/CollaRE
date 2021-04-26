package io.realm.log;

import io.realm.internal.Keep;

@Keep
public interface RealmLogger {
    void log(int i, String str, Throwable th, String str2);
}
