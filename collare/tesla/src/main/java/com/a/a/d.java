package com.a.a;

import android.content.Context;
import android.util.Log;
import com.a.a.a.f;
import com.a.a.c;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

/* compiled from: ReLinkerInstance */
public class d {

    /* renamed from: a  reason: collision with root package name */
    protected final Set<String> f1514a;

    /* renamed from: b  reason: collision with root package name */
    protected final c.b f1515b;

    /* renamed from: c  reason: collision with root package name */
    protected final c.a f1516c;

    /* renamed from: d  reason: collision with root package name */
    protected boolean f1517d;
    protected boolean e;
    protected c.d f;

    protected d() {
        this(new e(), new a());
    }

    protected d(c.b bVar, c.a aVar) {
        this.f1514a = new HashSet();
        if (bVar == null) {
            throw new IllegalArgumentException("Cannot pass null library loader");
        } else if (aVar != null) {
            this.f1515b = bVar;
            this.f1516c = aVar;
        } else {
            throw new IllegalArgumentException("Cannot pass null library installer");
        }
    }

    public void a(Context context, String str) {
        a(context, str, (String) null, (c.AbstractC0035c) null);
    }

    public void a(final Context context, final String str, final String str2, final c.AbstractC0035c cVar) {
        if (context == null) {
            throw new IllegalArgumentException("Given context is null");
        } else if (!f.a(str)) {
            a("Beginning load of %s...", str);
            if (cVar == null) {
                c(context, str, str2);
            } else {
                new Thread(new Runnable() {
                    /* class com.a.a.d.AnonymousClass1 */

                    public void run() {
                        try {
                            d.this.c(context, str, str2);
                            cVar.a();
                        } catch (UnsatisfiedLinkError e2) {
                            cVar.a(e2);
                        } catch (b e3) {
                            cVar.a(e3);
                        }
                    }
                }).start();
            }
        } else {
            throw new IllegalArgumentException("Given library is either null or empty");
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void c(Context context, String str, String str2) {
        f fVar;
        Throwable th;
        f fVar2;
        if (!this.f1514a.contains(str) || this.f1517d) {
            try {
                this.f1515b.a(str);
                this.f1514a.add(str);
                a("%s (%s) was loaded normally!", str, str2);
            } catch (UnsatisfiedLinkError e2) {
                a("Loading the library normally failed: %s", Log.getStackTraceString(e2));
                a("%s (%s) was not loaded normally, re-linking...", str, str2);
                File a2 = a(context, str, str2);
                if (!a2.exists() || this.f1517d) {
                    if (this.f1517d) {
                        a("Forcing a re-link of %s (%s)...", str, str2);
                    }
                    b(context, str, str2);
                    this.f1516c.a(context, this.f1515b.a(), this.f1515b.c(str), a2, this);
                }
                try {
                    if (this.e) {
                        fVar = null;
                        fVar2 = new f(a2);
                        List<String> b2 = fVar2.b();
                        fVar2.close();
                        for (String str3 : b2) {
                            a(context, this.f1515b.d(str3));
                        }
                    }
                } catch (IOException unused) {
                }
                this.f1515b.b(a2.getAbsolutePath());
                this.f1514a.add(str);
                a("%s (%s) was re-linked!", str, str2);
            } catch (Throwable th2) {
                th = th2;
                fVar = fVar2;
                fVar.close();
                throw th;
            }
        } else {
            a("%s already loaded previously!", str);
        }
    }

    /* access modifiers changed from: protected */
    public File a(Context context) {
        return context.getDir("lib", 0);
    }

    /* access modifiers changed from: protected */
    public File a(Context context, String str, String str2) {
        String c2 = this.f1515b.c(str);
        if (f.a(str2)) {
            return new File(a(context), c2);
        }
        File a2 = a(context);
        return new File(a2, c2 + "." + str2);
    }

    /* access modifiers changed from: protected */
    public void b(Context context, String str, String str2) {
        File a2 = a(context);
        File a3 = a(context, str, str2);
        final String c2 = this.f1515b.c(str);
        File[] listFiles = a2.listFiles(new FilenameFilter() {
            /* class com.a.a.d.AnonymousClass2 */

            public boolean accept(File file, String str) {
                return str.startsWith(c2);
            }
        });
        if (listFiles != null) {
            for (File file : listFiles) {
                if (this.f1517d || !file.getAbsolutePath().equals(a3.getAbsolutePath())) {
                    file.delete();
                }
            }
        }
    }

    public void a(String str, Object... objArr) {
        a(String.format(Locale.US, str, objArr));
    }

    public void a(String str) {
        c.d dVar = this.f;
        if (dVar != null) {
            dVar.a(str);
        }
    }
}
