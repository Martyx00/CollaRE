package io.a.a.a.a.g;

import io.a.a.a.a.f.b;
import io.a.a.a.c;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStream;
import org.json.JSONObject;

/* access modifiers changed from: package-private */
/* compiled from: DefaultCachedSettingsIo */
public class i implements g {

    /* renamed from: a  reason: collision with root package name */
    private final io.a.a.a.i f6039a;

    public i(io.a.a.a.i iVar) {
        this.f6039a = iVar;
    }

    @Override // io.a.a.a.a.g.g
    public JSONObject a() {
        Throwable th;
        FileInputStream fileInputStream;
        Exception e;
        JSONObject jSONObject;
        c.g().a("Fabric", "Reading cached settings...");
        FileInputStream fileInputStream2 = null;
        try {
            File file = new File(new b(this.f6039a).a(), "com.crashlytics.settings.json");
            if (file.exists()) {
                fileInputStream = new FileInputStream(file);
                try {
                    jSONObject = new JSONObject(io.a.a.a.a.b.i.a((InputStream) fileInputStream));
                    fileInputStream2 = fileInputStream;
                } catch (Exception e2) {
                    e = e2;
                    try {
                        c.g().e("Fabric", "Failed to fetch cached settings", e);
                        io.a.a.a.a.b.i.a((Closeable) fileInputStream, "Error while closing settings cache file.");
                        return null;
                    } catch (Throwable th2) {
                        th = th2;
                        fileInputStream2 = fileInputStream;
                        io.a.a.a.a.b.i.a((Closeable) fileInputStream2, "Error while closing settings cache file.");
                        throw th;
                    }
                }
            } else {
                c.g().a("Fabric", "No cached settings found.");
                jSONObject = null;
            }
            io.a.a.a.a.b.i.a((Closeable) fileInputStream2, "Error while closing settings cache file.");
            return jSONObject;
        } catch (Exception e3) {
            e = e3;
            fileInputStream = null;
            c.g().e("Fabric", "Failed to fetch cached settings", e);
            io.a.a.a.a.b.i.a((Closeable) fileInputStream, "Error while closing settings cache file.");
            return null;
        } catch (Throwable th3) {
            th = th3;
            io.a.a.a.a.b.i.a((Closeable) fileInputStream2, "Error while closing settings cache file.");
            throw th;
        }
    }

    @Override // io.a.a.a.a.g.g
    public void a(long j, JSONObject jSONObject) {
        Throwable th;
        Exception e;
        c.g().a("Fabric", "Writing settings to cache file...");
        if (jSONObject != null) {
            FileWriter fileWriter = null;
            try {
                jSONObject.put("expires_at", j);
                FileWriter fileWriter2 = new FileWriter(new File(new b(this.f6039a).a(), "com.crashlytics.settings.json"));
                try {
                    fileWriter2.write(jSONObject.toString());
                    fileWriter2.flush();
                    io.a.a.a.a.b.i.a((Closeable) fileWriter2, "Failed to close settings writer.");
                } catch (Exception e2) {
                    e = e2;
                    fileWriter = fileWriter2;
                    try {
                        c.g().e("Fabric", "Failed to cache settings", e);
                        io.a.a.a.a.b.i.a((Closeable) fileWriter, "Failed to close settings writer.");
                    } catch (Throwable th2) {
                        th = th2;
                        io.a.a.a.a.b.i.a((Closeable) fileWriter, "Failed to close settings writer.");
                        throw th;
                    }
                } catch (Throwable th3) {
                    th = th3;
                    fileWriter = fileWriter2;
                    io.a.a.a.a.b.i.a((Closeable) fileWriter, "Failed to close settings writer.");
                    throw th;
                }
            } catch (Exception e3) {
                e = e3;
                c.g().e("Fabric", "Failed to cache settings", e);
                io.a.a.a.a.b.i.a((Closeable) fileWriter, "Failed to close settings writer.");
            }
        }
    }
}
