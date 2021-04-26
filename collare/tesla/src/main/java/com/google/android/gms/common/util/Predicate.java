package com.google.android.gms.common.util;

import com.google.android.gms.common.annotation.KeepForSdk;

@KeepForSdk
public interface Predicate<T> {
    @KeepForSdk
    boolean apply(T t);
}
