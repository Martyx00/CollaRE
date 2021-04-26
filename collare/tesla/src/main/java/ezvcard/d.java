package ezvcard;

/* compiled from: ValidationWarning */
public class d {

    /* renamed from: a  reason: collision with root package name */
    private final Integer f5749a;

    /* renamed from: b  reason: collision with root package name */
    private final String f5750b;

    public d(int i, Object... objArr) {
        this.f5749a = Integer.valueOf(i);
        this.f5750b = b.INSTANCE.a(i, objArr);
    }

    public Integer a() {
        return this.f5749a;
    }

    public String b() {
        return this.f5750b;
    }

    public String toString() {
        if (this.f5749a == null) {
            return this.f5750b;
        }
        return "(" + this.f5749a + ") " + this.f5750b;
    }
}
