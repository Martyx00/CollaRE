package com.facebook.b.b;

import android.os.Environment;
import com.facebook.b.a.a;
import com.facebook.b.a.j;
import com.facebook.b.b.d;
import com.facebook.common.c.c;
import com.facebook.common.d.i;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

/* compiled from: DefaultDiskStorage */
public class a implements d {

    /* renamed from: a  reason: collision with root package name */
    static final long f1651a = TimeUnit.MINUTES.toMillis(30);

    /* renamed from: b  reason: collision with root package name */
    private static final Class<?> f1652b = a.class;

    /* renamed from: c  reason: collision with root package name */
    private final File f1653c;

    /* renamed from: d  reason: collision with root package name */
    private final boolean f1654d;
    private final File e;
    private final com.facebook.b.a.a f;
    private final com.facebook.common.time.a g = com.facebook.common.time.c.b();

    public a(File file, int i, com.facebook.b.a.a aVar) {
        i.a(file);
        this.f1653c = file;
        this.f1654d = a(file, aVar);
        this.e = new File(this.f1653c, a(i));
        this.f = aVar;
        g();
    }

    private static boolean a(File file, com.facebook.b.a.a aVar) {
        IOException e2;
        try {
            File externalStorageDirectory = Environment.getExternalStorageDirectory();
            if (externalStorageDirectory == null) {
                return false;
            }
            try {
                try {
                    return file.getCanonicalPath().contains(externalStorageDirectory.toString());
                } catch (IOException e3) {
                    e2 = e3;
                    a.EnumC0036a aVar2 = a.EnumC0036a.OTHER;
                    Class<?> cls = f1652b;
                    aVar.a(aVar2, cls, "failed to read folder to check if external: " + ((String) null), e2);
                    return false;
                }
            } catch (IOException e4) {
                e2 = e4;
                a.EnumC0036a aVar22 = a.EnumC0036a.OTHER;
                Class<?> cls2 = f1652b;
                aVar.a(aVar22, cls2, "failed to read folder to check if external: " + ((String) null), e2);
                return false;
            }
        } catch (Exception e5) {
            aVar.a(a.EnumC0036a.OTHER, f1652b, "failed to get the external storage directory!", e5);
            return false;
        }
    }

    static String a(int i) {
        return String.format(null, "%s.ols%d.%d", "v2", 100, Integer.valueOf(i));
    }

    @Override // com.facebook.b.b.d
    public boolean a() {
        return this.f1654d;
    }

    private void g() {
        boolean z = true;
        if (this.f1653c.exists()) {
            if (!this.e.exists()) {
                com.facebook.common.c.a.b(this.f1653c);
            } else {
                z = false;
            }
        }
        if (z) {
            try {
                com.facebook.common.c.c.a(this.e);
            } catch (c.a unused) {
                com.facebook.b.a.a aVar = this.f;
                a.EnumC0036a aVar2 = a.EnumC0036a.WRITE_CREATE_DIR;
                Class<?> cls = f1652b;
                aVar.a(aVar2, cls, "version directory could not be created: " + this.e, null);
            }
        }
    }

    /* compiled from: DefaultDiskStorage */
    private static class d extends IOException {

        /* renamed from: a  reason: collision with root package name */
        public final long f1663a;

        /* renamed from: b  reason: collision with root package name */
        public final long f1664b;

        public d(long j, long j2) {
            super("File was not written completely. Expected: " + j + ", found: " + j2);
            this.f1663a = j;
            this.f1664b = j2;
        }
    }

    /* access modifiers changed from: package-private */
    public File a(String str) {
        return new File(f(str));
    }

    private String d(String str) {
        String valueOf = String.valueOf(Math.abs(str.hashCode() % 100));
        return this.e + File.separator + valueOf;
    }

    private File e(String str) {
        return new File(d(str));
    }

    /* access modifiers changed from: private */
    /* renamed from: com.facebook.b.b.a$a  reason: collision with other inner class name */
    /* compiled from: DefaultDiskStorage */
    public class C0037a implements com.facebook.common.c.b {

        /* renamed from: b  reason: collision with root package name */
        private final List<d.a> f1656b;

        @Override // com.facebook.common.c.b
        public void a(File file) {
        }

        @Override // com.facebook.common.c.b
        public void c(File file) {
        }

        private C0037a() {
            this.f1656b = new ArrayList();
        }

        @Override // com.facebook.common.c.b
        public void b(File file) {
            c b2 = a.this.b((a) file);
            if (b2 != null && b2.f1661a == ".cnt") {
                this.f1656b.add(new b(b2.f1662b, file));
            }
        }

        public List<d.a> a() {
            return Collections.unmodifiableList(this.f1656b);
        }
    }

    /* compiled from: DefaultDiskStorage */
    private class f implements com.facebook.common.c.b {

        /* renamed from: b  reason: collision with root package name */
        private boolean f1669b;

        private f() {
        }

        @Override // com.facebook.common.c.b
        public void a(File file) {
            if (!this.f1669b && file.equals(a.this.e)) {
                this.f1669b = true;
            }
        }

        @Override // com.facebook.common.c.b
        public void b(File file) {
            if (!this.f1669b || !d(file)) {
                file.delete();
            }
        }

        @Override // com.facebook.common.c.b
        public void c(File file) {
            if (!a.this.f1653c.equals(file) && !this.f1669b) {
                file.delete();
            }
            if (this.f1669b && file.equals(a.this.e)) {
                this.f1669b = false;
            }
        }

        private boolean d(File file) {
            c b2 = a.this.b((a) file);
            boolean z = false;
            if (b2 == null) {
                return false;
            }
            if (b2.f1661a == ".tmp") {
                return e(file);
            }
            if (b2.f1661a == ".cnt") {
                z = true;
            }
            i.b(z);
            return true;
        }

        private boolean e(File file) {
            return file.lastModified() > a.this.g.a() - a.f1651a;
        }
    }

    @Override // com.facebook.b.b.d
    public void b() {
        com.facebook.common.c.a.a(this.f1653c, new f());
    }

    private void a(File file, String str) {
        try {
            com.facebook.common.c.c.a(file);
        } catch (c.a e2) {
            this.f.a(a.EnumC0036a.WRITE_CREATE_DIR, f1652b, str, e2);
            throw e2;
        }
    }

    @Override // com.facebook.b.b.d
    public d.b a(String str, Object obj) {
        c cVar = new c(".tmp", str);
        File e2 = e(cVar.f1662b);
        if (!e2.exists()) {
            a(e2, "insert");
        }
        try {
            return new e(str, cVar.a(e2));
        } catch (IOException e3) {
            this.f.a(a.EnumC0036a.WRITE_CREATE_TEMPFILE, f1652b, "insert", e3);
            throw e3;
        }
    }

    @Override // com.facebook.b.b.d
    public com.facebook.a.a b(String str, Object obj) {
        File a2 = a(str);
        if (!a2.exists()) {
            return null;
        }
        a2.setLastModified(this.g.a());
        return com.facebook.a.b.a(a2);
    }

    private String f(String str) {
        c cVar = new c(".cnt", str);
        return cVar.a(d(cVar.f1662b));
    }

    @Override // com.facebook.b.b.d
    public boolean c(String str, Object obj) {
        return a(str, false);
    }

    private boolean a(String str, boolean z) {
        File a2 = a(str);
        boolean exists = a2.exists();
        if (z && exists) {
            a2.setLastModified(this.g.a());
        }
        return exists;
    }

    @Override // com.facebook.b.b.d
    public long a(d.a aVar) {
        return a(((b) aVar).c().c());
    }

    @Override // com.facebook.b.b.d
    public long b(String str) {
        return a(a(str));
    }

    private long a(File file) {
        if (!file.exists()) {
            return 0;
        }
        long length = file.length();
        if (file.delete()) {
            return length;
        }
        return -1;
    }

    @Override // com.facebook.b.b.d
    public void c() {
        com.facebook.common.c.a.a(this.f1653c);
    }

    /* renamed from: d */
    public List<d.a> e() {
        C0037a aVar = new C0037a();
        com.facebook.common.c.a.a(this.e, aVar);
        return aVar.a();
    }

    /* compiled from: DefaultDiskStorage */
    static class b implements d.a {

        /* renamed from: a  reason: collision with root package name */
        private final String f1657a;

        /* renamed from: b  reason: collision with root package name */
        private final com.facebook.a.b f1658b;

        /* renamed from: c  reason: collision with root package name */
        private long f1659c;

        /* renamed from: d  reason: collision with root package name */
        private long f1660d;

        private b(String str, File file) {
            i.a(file);
            this.f1657a = (String) i.a(str);
            this.f1658b = com.facebook.a.b.a(file);
            this.f1659c = -1;
            this.f1660d = -1;
        }

        @Override // com.facebook.b.b.d.a
        public String a() {
            return this.f1657a;
        }

        @Override // com.facebook.b.b.d.a
        public long b() {
            if (this.f1660d < 0) {
                this.f1660d = this.f1658b.c().lastModified();
            }
            return this.f1660d;
        }

        public com.facebook.a.b c() {
            return this.f1658b;
        }

        @Override // com.facebook.b.b.d.a
        public long d() {
            if (this.f1659c < 0) {
                this.f1659c = this.f1658b.b();
            }
            return this.f1659c;
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private c b(File file) {
        c b2 = c.b(file);
        if (b2 == null) {
            return null;
        }
        if (e(b2.f1662b).equals(file.getParentFile())) {
            return b2;
        }
        return null;
    }

    /* access modifiers changed from: private */
    public static String g(String str) {
        if (".cnt".equals(str)) {
            return ".cnt";
        }
        if (".tmp".equals(str)) {
            return ".tmp";
        }
        return null;
    }

    /* access modifiers changed from: private */
    /* compiled from: DefaultDiskStorage */
    public static class c {

        /* renamed from: a  reason: collision with root package name */
        public final String f1661a;

        /* renamed from: b  reason: collision with root package name */
        public final String f1662b;

        private c(String str, String str2) {
            this.f1661a = str;
            this.f1662b = str2;
        }

        public String toString() {
            return this.f1661a + "(" + this.f1662b + ")";
        }

        public String a(String str) {
            return str + File.separator + this.f1662b + this.f1661a;
        }

        public File a(File file) {
            return File.createTempFile(this.f1662b + ".", ".tmp", file);
        }

        public static c b(File file) {
            String g;
            String name = file.getName();
            int lastIndexOf = name.lastIndexOf(46);
            if (lastIndexOf <= 0 || (g = a.g(name.substring(lastIndexOf))) == null) {
                return null;
            }
            String substring = name.substring(0, lastIndexOf);
            if (g.equals(".tmp")) {
                int lastIndexOf2 = substring.lastIndexOf(46);
                if (lastIndexOf2 <= 0) {
                    return null;
                }
                substring = substring.substring(0, lastIndexOf2);
            }
            return new c(g, substring);
        }
    }

    /* compiled from: DefaultDiskStorage */
    class e implements d.b {

        /* renamed from: a  reason: collision with root package name */
        final File f1665a;

        /* renamed from: c  reason: collision with root package name */
        private final String f1667c;

        public e(String str, File file) {
            this.f1667c = str;
            this.f1665a = file;
        }

        /* JADX INFO: finally extract failed */
        @Override // com.facebook.b.b.d.b
        public void a(j jVar, Object obj) {
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(this.f1665a);
                try {
                    com.facebook.common.d.c cVar = new com.facebook.common.d.c(fileOutputStream);
                    jVar.a(cVar);
                    cVar.flush();
                    long a2 = cVar.a();
                    fileOutputStream.close();
                    if (this.f1665a.length() != a2) {
                        throw new d(a2, this.f1665a.length());
                    }
                } catch (Throwable th) {
                    fileOutputStream.close();
                    throw th;
                }
            } catch (FileNotFoundException e) {
                a.this.f.a(a.EnumC0036a.WRITE_UPDATE_FILE_NOT_FOUND, a.f1652b, "updateResource", e);
                throw e;
            }
        }

        @Override // com.facebook.b.b.d.b
        public com.facebook.a.a a(Object obj) {
            a.EnumC0036a aVar;
            File a2 = a.this.a(this.f1667c);
            try {
                com.facebook.common.c.c.a(this.f1665a, a2);
                if (a2.exists()) {
                    a2.setLastModified(a.this.g.a());
                }
                return com.facebook.a.b.a(a2);
            } catch (c.d e) {
                Throwable cause = e.getCause();
                if (cause == null) {
                    aVar = a.EnumC0036a.WRITE_RENAME_FILE_OTHER;
                } else if (cause instanceof c.C0041c) {
                    aVar = a.EnumC0036a.WRITE_RENAME_FILE_TEMPFILE_PARENT_NOT_FOUND;
                } else if (cause instanceof FileNotFoundException) {
                    aVar = a.EnumC0036a.WRITE_RENAME_FILE_TEMPFILE_NOT_FOUND;
                } else {
                    aVar = a.EnumC0036a.WRITE_RENAME_FILE_OTHER;
                }
                a.this.f.a(aVar, a.f1652b, "commit", e);
                throw e;
            }
        }

        @Override // com.facebook.b.b.d.b
        public boolean a() {
            return !this.f1665a.exists() || this.f1665a.delete();
        }
    }
}
