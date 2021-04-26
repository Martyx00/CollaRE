package com.google.firebase.iid;

import com.google.android.gms.tasks.Task;

/* access modifiers changed from: package-private */
public final /* synthetic */ class ar implements v {

    /* renamed from: a  reason: collision with root package name */
    private final FirebaseInstanceId f3898a;

    /* renamed from: b  reason: collision with root package name */
    private final String f3899b;

    /* renamed from: c  reason: collision with root package name */
    private final String f3900c;

    /* renamed from: d  reason: collision with root package name */
    private final String f3901d;
    private final String e;

    ar(FirebaseInstanceId firebaseInstanceId, String str, String str2, String str3, String str4) {
        this.f3898a = firebaseInstanceId;
        this.f3899b = str;
        this.f3900c = str2;
        this.f3901d = str3;
        this.e = str4;
    }

    @Override // com.google.firebase.iid.v
    public final Task a() {
        return this.f3898a.a(this.f3899b, this.f3900c, this.f3901d, this.e);
    }
}
