package ezvcard.parameter;

public class ExpertiseLevel extends VCardParameter {

    /* renamed from: a  reason: collision with root package name */
    public static final ExpertiseLevel f5762a = new ExpertiseLevel("beginner");

    /* renamed from: b  reason: collision with root package name */
    public static final ExpertiseLevel f5763b = new ExpertiseLevel("average");

    /* renamed from: c  reason: collision with root package name */
    public static final ExpertiseLevel f5764c = new ExpertiseLevel("expert");

    /* renamed from: d  reason: collision with root package name */
    private static final d<ExpertiseLevel> f5765d = new d<>(ExpertiseLevel.class);

    private ExpertiseLevel(String str) {
        super(str);
    }

    public static ExpertiseLevel a(String str) {
        return (ExpertiseLevel) f5765d.c(str);
    }
}
