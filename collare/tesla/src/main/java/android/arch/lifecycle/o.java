package android.arch.lifecycle;

/* compiled from: ViewModelProvider */
public class o {

    /* renamed from: a  reason: collision with root package name */
    private final a f95a;

    /* renamed from: b  reason: collision with root package name */
    private final p f96b;

    /* compiled from: ViewModelProvider */
    public interface a {
        <T extends n> T a(Class<T> cls);
    }

    public o(p pVar, a aVar) {
        this.f95a = aVar;
        this.f96b = pVar;
    }

    public <T extends n> T a(Class<T> cls) {
        String canonicalName = cls.getCanonicalName();
        if (canonicalName != null) {
            return (T) a("android.arch.lifecycle.ViewModelProvider.DefaultKey:" + canonicalName, cls);
        }
        throw new IllegalArgumentException("Local and anonymous classes can not be ViewModels");
    }

    public <T extends n> T a(String str, Class<T> cls) {
        T t = (T) this.f96b.a(str);
        if (cls.isInstance(t)) {
            return t;
        }
        T t2 = (T) this.f95a.a(cls);
        this.f96b.a(str, t2);
        return t2;
    }
}
