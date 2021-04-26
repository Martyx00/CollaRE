package ezvcard;

import com.google.android.gms.common.internal.ImagesContract;
import ezvcard.a.a.b;
import ezvcard.util.f;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/* compiled from: Ezvcard */
public final class a {

    /* renamed from: a  reason: collision with root package name */
    public static final String f5693a;

    /* renamed from: b  reason: collision with root package name */
    public static final String f5694b;

    /* renamed from: c  reason: collision with root package name */
    public static final String f5695c;

    /* renamed from: d  reason: collision with root package name */
    public static final String f5696d;

    static {
        Properties properties = new Properties();
        InputStream inputStream = null;
        try {
            inputStream = a.class.getResourceAsStream("ez-vcard.properties");
            properties.load(inputStream);
            f.a(inputStream);
            f5693a = properties.getProperty("version");
            f5694b = properties.getProperty("groupId");
            f5695c = properties.getProperty("artifactId");
            f5696d = properties.getProperty(ImagesContract.URL);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (Throwable th) {
            f.a(inputStream);
            throw th;
        }
    }

    public static b<b<?>> a(InputStream inputStream) {
        return new b<>(inputStream);
    }

    private a() {
    }
}
