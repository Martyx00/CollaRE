package com.facebook.common.k;

/* compiled from: TriState */
public enum e {
    YES,
    NO,
    UNSET;

    public static e a(boolean z) {
        return z ? YES : NO;
    }
}
