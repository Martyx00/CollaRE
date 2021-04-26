package io.realm.exceptions;

import io.realm.internal.Keep;

@Keep
public final class RealmPrimaryKeyConstraintException extends RuntimeException {
    public RealmPrimaryKeyConstraintException(String str) {
        super(str);
    }
}
