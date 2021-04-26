package io.a.a.a.a.b;

/* access modifiers changed from: package-private */
/* compiled from: AdvertisingInfo */
public class b {

    /* renamed from: a  reason: collision with root package name */
    public final String f5878a;

    /* renamed from: b  reason: collision with root package name */
    public final boolean f5879b;

    b(String str, boolean z) {
        this.f5878a = str;
        this.f5879b = z;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        b bVar = (b) obj;
        if (this.f5879b != bVar.f5879b) {
            return false;
        }
        String str = this.f5878a;
        return str == null ? bVar.f5878a == null : str.equals(bVar.f5878a);
    }

    public int hashCode() {
        String str = this.f5878a;
        return ((str != null ? str.hashCode() : 0) * 31) + (this.f5879b ? 1 : 0);
    }
}
