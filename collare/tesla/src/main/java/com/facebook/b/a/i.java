package com.facebook.b.a;

import android.net.Uri;

/* compiled from: SimpleCacheKey */
public class i implements d {

    /* renamed from: a  reason: collision with root package name */
    final String f1650a;

    public i(String str) {
        this.f1650a = (String) com.facebook.common.d.i.a(str);
    }

    public String toString() {
        return this.f1650a;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof i) {
            return this.f1650a.equals(((i) obj).f1650a);
        }
        return false;
    }

    public int hashCode() {
        return this.f1650a.hashCode();
    }

    @Override // com.facebook.b.a.d
    public boolean a(Uri uri) {
        return this.f1650a.contains(uri.toString());
    }

    @Override // com.facebook.b.a.d
    public String a() {
        return this.f1650a;
    }
}
