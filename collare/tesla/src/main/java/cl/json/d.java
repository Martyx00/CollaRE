package cl.json;

import android.net.Uri;
import android.os.Environment;
import android.util.Base64;
import android.webkit.MimeTypeMap;
import com.facebook.react.bridge.ReactApplicationContext;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/* compiled from: ShareFile */
public class d {

    /* renamed from: a  reason: collision with root package name */
    private final ReactApplicationContext f1385a;

    /* renamed from: b  reason: collision with root package name */
    private String f1386b;

    /* renamed from: c  reason: collision with root package name */
    private Uri f1387c;

    /* renamed from: d  reason: collision with root package name */
    private String f1388d;

    public d(String str, String str2, ReactApplicationContext reactApplicationContext) {
        this(str, reactApplicationContext);
        this.f1388d = str2;
    }

    public d(String str, ReactApplicationContext reactApplicationContext) {
        this.f1386b = str;
        this.f1387c = Uri.parse(this.f1386b);
        this.f1385a = reactApplicationContext;
    }

    private String b(String str) {
        String fileExtensionFromUrl = MimeTypeMap.getFileExtensionFromUrl(str);
        if (fileExtensionFromUrl != null) {
            return MimeTypeMap.getSingleton().getMimeTypeFromExtension(fileExtensionFromUrl);
        }
        return null;
    }

    public boolean a() {
        return c() || d();
    }

    private boolean c() {
        if (this.f1387c.getScheme() == null || !this.f1387c.getScheme().equals("data")) {
            return false;
        }
        this.f1388d = this.f1387c.getSchemeSpecificPart().substring(0, this.f1387c.getSchemeSpecificPart().indexOf(";"));
        return true;
    }

    private boolean d() {
        if (this.f1387c.getScheme() == null || (!this.f1387c.getScheme().equals(FirebaseAnalytics.b.CONTENT) && !this.f1387c.getScheme().equals("file"))) {
            return false;
        }
        if (this.f1388d != null) {
            return true;
        }
        this.f1388d = b(this.f1387c.toString());
        if (this.f1388d == null) {
            String a2 = a(this.f1387c);
            if (a2 == null) {
                return false;
            }
            this.f1388d = b(a2);
        }
        if (this.f1388d == null) {
            this.f1388d = "*/*";
        }
        return true;
    }

    public String b() {
        String str = this.f1388d;
        return str == null ? "*/*" : str;
    }

    private String a(Uri uri) {
        return b.a(this.f1385a, uri);
    }

    public Uri a(String str) {
        String extensionFromMimeType = MimeTypeMap.getSingleton().getExtensionFromMimeType(b());
        if (c()) {
            String substring = this.f1387c.getSchemeSpecificPart().substring(this.f1387c.getSchemeSpecificPart().indexOf(";base64,") + 8);
            try {
                String valueOf = String.valueOf(System.nanoTime());
                if (str == null || str.isEmpty()) {
                    str = valueOf;
                }
                File file = new File(Environment.getExternalStorageDirectory(), Environment.DIRECTORY_DOWNLOADS);
                if (!file.exists()) {
                    if (!file.mkdirs()) {
                        throw new IOException("mkdirs failed on " + file.getAbsolutePath());
                    }
                }
                File file2 = new File(file, str + "." + extensionFromMimeType);
                FileOutputStream fileOutputStream = new FileOutputStream(file2);
                fileOutputStream.write(Base64.decode(substring, 0));
                fileOutputStream.flush();
                fileOutputStream.close();
                return b.a(this.f1385a, file2);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            if (d()) {
                Uri parse = Uri.parse(this.f1386b);
                if (parse.getPath() == null) {
                    return null;
                }
                return b.a(this.f1385a, new File(parse.getPath()));
            }
            return null;
        }
    }
}
