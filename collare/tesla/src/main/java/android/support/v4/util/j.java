package android.support.v4.util;

/* compiled from: Pair */
public class j<F, S> {

    /* renamed from: a  reason: collision with root package name */
    public final F f667a;

    /* renamed from: b  reason: collision with root package name */
    public final S f668b;

    public j(F f, S s) {
        this.f667a = f;
        this.f668b = s;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof j)) {
            return false;
        }
        j jVar = (j) obj;
        if (!i.a(jVar.f667a, this.f667a) || !i.a(jVar.f668b, this.f668b)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        F f = this.f667a;
        int i = 0;
        int hashCode = f == null ? 0 : f.hashCode();
        S s = this.f668b;
        if (s != null) {
            i = s.hashCode();
        }
        return hashCode ^ i;
    }

    public String toString() {
        return "Pair{" + String.valueOf(this.f667a) + " " + String.valueOf(this.f668b) + "}";
    }
}
