package android.arch.lifecycle;

import java.util.HashMap;

/* compiled from: ViewModelStore */
public class p {

    /* renamed from: a  reason: collision with root package name */
    private final HashMap<String, n> f97a = new HashMap<>();

    /* access modifiers changed from: package-private */
    public final void a(String str, n nVar) {
        n put = this.f97a.put(str, nVar);
        if (put != null) {
            put.a();
        }
    }

    /* access modifiers changed from: package-private */
    public final n a(String str) {
        return this.f97a.get(str);
    }

    public final void a() {
        for (n nVar : this.f97a.values()) {
            nVar.a();
        }
        this.f97a.clear();
    }
}
