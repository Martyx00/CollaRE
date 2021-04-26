package ezvcard.parameter;

import ezvcard.VCardVersion;
import ezvcard.c;
import java.util.Collection;

/* compiled from: Encoding */
public class a extends VCardParameter {
    @c(a = {VCardVersion.V2_1})

    /* renamed from: a  reason: collision with root package name */
    public static final a f5798a = new a("QUOTED-PRINTABLE", true);
    @c(a = {VCardVersion.V2_1})

    /* renamed from: b  reason: collision with root package name */
    public static final a f5799b = new a("BASE64", true);
    @c(a = {VCardVersion.V2_1})

    /* renamed from: c  reason: collision with root package name */
    public static final a f5800c = new a("8BIT", true);
    @c(a = {VCardVersion.V2_1})

    /* renamed from: d  reason: collision with root package name */
    public static final a f5801d = new a("7BIT", true);
    @c(a = {VCardVersion.V3_0})
    public static final a e = new a("b");
    private static final d<a> f = new d<>(a.class);

    private a(String str) {
        super(str);
    }

    private a(String str, boolean z) {
        super(str, z);
    }

    public static a a(String str) {
        return (a) f.b(str);
    }

    public static a b(String str) {
        return (a) f.c(str);
    }

    public static Collection<a> a() {
        return f.a();
    }
}
