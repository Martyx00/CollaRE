package ezvcard.parameter;

import java.util.Collection;

public class Calscale extends VCardParameter {

    /* renamed from: a  reason: collision with root package name */
    public static final Calscale f5756a = new Calscale("gregorian");

    /* renamed from: b  reason: collision with root package name */
    private static final d<Calscale> f5757b = new d<>(Calscale.class);

    private Calscale(String str) {
        super(str);
    }

    public static Calscale a(String str) {
        return (Calscale) f5757b.b(str);
    }

    public static Calscale b(String str) {
        return (Calscale) f5757b.c(str);
    }

    public static Collection<Calscale> a() {
        return f5757b.a();
    }
}
