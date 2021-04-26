package ezvcard.parameter;

public class ImppType extends VCardParameter {

    /* renamed from: a  reason: collision with root package name */
    public static final ImppType f5773a = new ImppType("personal");

    /* renamed from: b  reason: collision with root package name */
    public static final ImppType f5774b = new ImppType("business");

    /* renamed from: c  reason: collision with root package name */
    public static final ImppType f5775c = new ImppType("home");

    /* renamed from: d  reason: collision with root package name */
    public static final ImppType f5776d = new ImppType("work");
    public static final ImppType e = new ImppType("mobile");
    public static final ImppType f = new ImppType("pref");
    private static final d<ImppType> g = new d<>(ImppType.class);

    private ImppType(String str) {
        super(str);
    }

    public static ImppType a(String str) {
        return (ImppType) g.c(str);
    }
}
