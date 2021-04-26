package ezvcard.parameter;

public class KeyType extends MediaTypeParameter {

    /* renamed from: a  reason: collision with root package name */
    public static final KeyType f5781a = new KeyType("PGP", "application/pgp-keys", "pgp");

    /* renamed from: b  reason: collision with root package name */
    public static final KeyType f5782b = new KeyType("GPG", "application/gpg", "gpg");

    /* renamed from: c  reason: collision with root package name */
    public static final KeyType f5783c = new KeyType("X509", "application/x509", null);
    private static final b<KeyType> f = new b<>(KeyType.class);

    private KeyType(String str, String str2, String str3) {
        super(str, str2, str3);
    }

    public static KeyType a(String str, String str2, String str3) {
        return (KeyType) f.b(new String[]{str, str2, str3});
    }

    public static KeyType b(String str, String str2, String str3) {
        return (KeyType) f.c(new String[]{str, str2, str3});
    }
}
