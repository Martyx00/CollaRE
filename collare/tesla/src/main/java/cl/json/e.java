package cl.json;

import android.net.Uri;
import android.os.Environment;
import android.util.Base64;
import android.webkit.MimeTypeMap;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReadableArray;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

/* compiled from: ShareFiles */
public class e {

    /* renamed from: a  reason: collision with root package name */
    private final ReactApplicationContext f1389a;

    /* renamed from: b  reason: collision with root package name */
    private ArrayList<Uri> f1390b;

    /* renamed from: c  reason: collision with root package name */
    private String f1391c;

    public e(ReadableArray readableArray, String str, ReactApplicationContext reactApplicationContext) {
        this(readableArray, reactApplicationContext);
        this.f1391c = str;
    }

    public e(ReadableArray readableArray, ReactApplicationContext reactApplicationContext) {
        this.f1390b = new ArrayList<>();
        for (int i = 0; i < readableArray.size(); i++) {
            String string = readableArray.getString(i);
            if (string != null) {
                this.f1390b.add(Uri.parse(string));
            }
        }
        this.f1389a = reactApplicationContext;
    }

    private String a(String str) {
        String fileExtensionFromUrl = MimeTypeMap.getFileExtensionFromUrl(str);
        if (fileExtensionFromUrl != null) {
            return MimeTypeMap.getSingleton().getMimeTypeFromExtension(fileExtensionFromUrl);
        }
        return null;
    }

    public boolean a() {
        Iterator<Uri> it = this.f1390b.iterator();
        boolean z = true;
        while (it.hasNext()) {
            Uri next = it.next();
            if (a(next) || b(next)) {
                z = true;
                continue;
            } else {
                z = false;
                continue;
            }
            if (!z) {
                break;
            }
        }
        return z;
    }

    private boolean a(Uri uri) {
        if (uri.getScheme() == null || !uri.getScheme().equals("data")) {
            return false;
        }
        String substring = uri.getSchemeSpecificPart().substring(0, uri.getSchemeSpecificPart().indexOf(";"));
        String str = this.f1391c;
        if (str == null) {
            this.f1391c = substring;
            return true;
        } else if (!str.equalsIgnoreCase(substring) && this.f1391c.split("/")[0].equalsIgnoreCase(substring.split("/")[0])) {
            this.f1391c = this.f1391c.split("/")[0].concat("/*");
            return true;
        } else if (this.f1391c.equalsIgnoreCase(substring)) {
            return true;
        } else {
            this.f1391c = "*/*";
            return true;
        }
    }

    private boolean b(Uri uri) {
        if ((uri.getScheme() == null || !uri.getScheme().equals(FirebaseAnalytics.b.CONTENT)) && !"file".equals(uri.getScheme())) {
            return false;
        }
        String a2 = a(uri.toString());
        if (a2 == null) {
            a2 = a(c(uri));
        }
        if (a2 == null) {
            a2 = "*/*";
        }
        String str = this.f1391c;
        if (str == null) {
            this.f1391c = a2;
            return true;
        } else if (!str.equalsIgnoreCase(a2) && this.f1391c.split("/")[0].equalsIgnoreCase(a2.split("/")[0])) {
            this.f1391c = this.f1391c.split("/")[0].concat("/*");
            return true;
        } else if (this.f1391c.equalsIgnoreCase(a2)) {
            return true;
        } else {
            this.f1391c = "*/*";
            return true;
        }
    }

    public String b() {
        String str = this.f1391c;
        return str == null ? "*/*" : str;
    }

    private String c(Uri uri) {
        return b.a(this.f1389a, uri);
    }

    public ArrayList<Uri> c() {
        MimeTypeMap singleton = MimeTypeMap.getSingleton();
        ArrayList<Uri> arrayList = new ArrayList<>();
        Iterator<Uri> it = this.f1390b.iterator();
        while (it.hasNext()) {
            Uri next = it.next();
            if (a(next)) {
                String extensionFromMimeType = singleton.getExtensionFromMimeType(next.getSchemeSpecificPart().substring(0, next.getSchemeSpecificPart().indexOf(";")));
                String substring = next.getSchemeSpecificPart().substring(next.getSchemeSpecificPart().indexOf(";base64,") + 8);
                try {
                    File file = new File(Environment.getExternalStorageDirectory(), Environment.DIRECTORY_DOWNLOADS);
                    if (!file.exists()) {
                        if (!file.mkdirs()) {
                            throw new IOException("mkdirs failed on " + file.getAbsolutePath());
                        }
                    }
                    File file2 = new File(file, System.currentTimeMillis() + "." + extensionFromMimeType);
                    FileOutputStream fileOutputStream = new FileOutputStream(file2);
                    fileOutputStream.write(Base64.decode(substring, 0));
                    fileOutputStream.flush();
                    fileOutputStream.close();
                    arrayList.add(b.a(this.f1389a, file2));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if (b(next) && next.getPath() != null) {
                arrayList.add(b.a(this.f1389a, new File(next.getPath())));
            }
        }
        return arrayList;
    }
}
