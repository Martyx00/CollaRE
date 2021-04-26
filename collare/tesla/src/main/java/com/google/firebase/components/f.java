package com.google.firebase.components;

import com.google.android.gms.common.annotation.KeepForSdk;
import java.util.Arrays;
import java.util.List;

@KeepForSdk
/* compiled from: com.google.firebase:firebase-common@@16.0.2 */
public class f extends g {

    /* renamed from: a  reason: collision with root package name */
    private final List<a<?>> f3821a;

    @KeepForSdk
    public f(List<a<?>> list) {
        super("Dependency cycle detected: " + Arrays.toString(list.toArray()));
        this.f3821a = list;
    }
}
