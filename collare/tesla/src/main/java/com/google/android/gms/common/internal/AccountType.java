package com.google.android.gms.common.internal;

import com.google.android.gms.common.annotation.KeepForSdk;

@KeepForSdk
public final class AccountType {
    @KeepForSdk
    public static final String GOOGLE = "com.google";
    private static final String[] zzbs = {GOOGLE, "com.google.work", "cn.google"};

    private AccountType() {
    }
}
