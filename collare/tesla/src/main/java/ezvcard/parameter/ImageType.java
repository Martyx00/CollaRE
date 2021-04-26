package ezvcard.parameter;

public class ImageType extends MediaTypeParameter {

    /* renamed from: a  reason: collision with root package name */
    public static final ImageType f5770a = new ImageType("GIF", "image/gif", "gif");

    /* renamed from: b  reason: collision with root package name */
    public static final ImageType f5771b = new ImageType("JPEG", "image/jpeg", "jpg");

    /* renamed from: c  reason: collision with root package name */
    public static final ImageType f5772c = new ImageType("PNG", "image/png", "png");
    private static final b<ImageType> f = new b<>(ImageType.class);

    private ImageType(String str, String str2, String str3) {
        super(str, str2, str3);
    }

    public static ImageType a(String str, String str2, String str3) {
        return (ImageType) f.b(new String[]{str, str2, str3});
    }

    public static ImageType b(String str, String str2, String str3) {
        return (ImageType) f.c(new String[]{str, str2, str3});
    }
}
