package com.google.firebase;

import android.content.Context;
import android.text.TextUtils;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.StringResourceValueReader;
import com.google.android.gms.common.util.Strings;

/* compiled from: com.google.firebase:firebase-common@@16.0.2 */
public final class b {

    /* renamed from: a  reason: collision with root package name */
    private final String f3804a;

    /* renamed from: b  reason: collision with root package name */
    private final String f3805b;

    /* renamed from: c  reason: collision with root package name */
    private final String f3806c;

    /* renamed from: d  reason: collision with root package name */
    private final String f3807d;
    private final String e;
    private final String f;
    private final String g;

    private b(String str, String str2, String str3, String str4, String str5, String str6, String str7) {
        Preconditions.checkState(!Strings.isEmptyOrWhitespace(str), "ApplicationId must be set.");
        this.f3805b = str;
        this.f3804a = str2;
        this.f3806c = str3;
        this.f3807d = str4;
        this.e = str5;
        this.f = str6;
        this.g = str7;
    }

    public static b a(Context context) {
        StringResourceValueReader stringResourceValueReader = new StringResourceValueReader(context);
        String string = stringResourceValueReader.getString("google_app_id");
        if (TextUtils.isEmpty(string)) {
            return null;
        }
        return new b(string, stringResourceValueReader.getString("google_api_key"), stringResourceValueReader.getString("firebase_database_url"), stringResourceValueReader.getString("ga_trackingId"), stringResourceValueReader.getString("gcm_defaultSenderId"), stringResourceValueReader.getString("google_storage_bucket"), stringResourceValueReader.getString("project_id"));
    }

    public final String a() {
        return this.f3805b;
    }

    public final String b() {
        return this.e;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof b)) {
            return false;
        }
        b bVar = (b) obj;
        if (!Objects.equal(this.f3805b, bVar.f3805b) || !Objects.equal(this.f3804a, bVar.f3804a) || !Objects.equal(this.f3806c, bVar.f3806c) || !Objects.equal(this.f3807d, bVar.f3807d) || !Objects.equal(this.e, bVar.e) || !Objects.equal(this.f, bVar.f) || !Objects.equal(this.g, bVar.g)) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        return Objects.hashCode(this.f3805b, this.f3804a, this.f3806c, this.f3807d, this.e, this.f, this.g);
    }

    public final String toString() {
        return Objects.toStringHelper(this).add("applicationId", this.f3805b).add("apiKey", this.f3804a).add("databaseUrl", this.f3806c).add("gcmSenderId", this.e).add("storageBucket", this.f).add("projectId", this.g).toString();
    }
}
