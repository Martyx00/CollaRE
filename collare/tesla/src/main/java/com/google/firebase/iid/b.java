package com.google.firebase.iid;

import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.tasks.Task;

@KeepForSdk
public interface b {
    @KeepForSdk
    Task<Void> a(String str);

    @KeepForSdk
    Task<Void> a(String str, String str2);

    @KeepForSdk
    Task<Void> a(String str, String str2, String str3);

    @KeepForSdk
    Task<String> a(String str, String str2, String str3, String str4);

    @KeepForSdk
    boolean a();

    @KeepForSdk
    Task<Void> b(String str, String str2, String str3);

    @KeepForSdk
    boolean b();
}
