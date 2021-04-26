package com.facebook.b.a;

import android.net.Uri;
import com.facebook.common.d.i;
import java.util.List;

/* compiled from: MultiCacheKey */
public class f implements d {

    /* renamed from: a  reason: collision with root package name */
    final List<d> f1647a;

    public f(List<d> list) {
        this.f1647a = (List) i.a(list);
    }

    public List<d> b() {
        return this.f1647a;
    }

    public String toString() {
        return "MultiCacheKey:" + this.f1647a.toString();
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof f) {
            return this.f1647a.equals(((f) obj).f1647a);
        }
        return false;
    }

    public int hashCode() {
        return this.f1647a.hashCode();
    }

    @Override // com.facebook.b.a.d
    public boolean a(Uri uri) {
        for (int i = 0; i < this.f1647a.size(); i++) {
            if (this.f1647a.get(i).a(uri)) {
                return true;
            }
        }
        return false;
    }

    @Override // com.facebook.b.a.d
    public String a() {
        return this.f1647a.get(0).a();
    }
}
