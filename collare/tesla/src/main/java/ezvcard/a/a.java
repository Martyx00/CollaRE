package ezvcard.a;

import ezvcard.b;

/* compiled from: CannotParseException */
public class a extends RuntimeException {

    /* renamed from: a  reason: collision with root package name */
    private final Integer f5697a;

    /* renamed from: b  reason: collision with root package name */
    private final Object[] f5698b;

    public a() {
        this(null);
    }

    public a(int i, Object... objArr) {
        this.f5697a = Integer.valueOf(i);
        this.f5698b = objArr;
    }

    public a(String str) {
        this(25, str);
    }

    public Integer a() {
        return this.f5697a;
    }

    public Object[] b() {
        return this.f5698b;
    }

    public String getMessage() {
        return b.INSTANCE.b(this.f5697a.intValue(), this.f5698b);
    }
}
