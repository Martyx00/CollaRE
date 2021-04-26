package ezvcard.util;

/* compiled from: ClearableStringBuilder */
public class c {

    /* renamed from: a  reason: collision with root package name */
    private final StringBuilder f5843a = new StringBuilder();

    public c a() {
        this.f5843a.setLength(0);
        return this;
    }

    public String b() {
        return this.f5843a.toString();
    }

    public String c() {
        String b2 = b();
        a();
        return b2;
    }

    public c a(char c2) {
        this.f5843a.append(c2);
        return this;
    }
}
