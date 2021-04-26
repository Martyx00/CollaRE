package com.github.a.a.b;

import com.github.a.a.a.b;
import com.github.a.a.d;
import java.io.Closeable;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.nio.charset.IllegalCharsetNameException;
import java.nio.charset.UnsupportedCharsetException;
import java.util.ArrayList;
import java.util.List;

/* compiled from: VObjectReader */
public class f implements Closeable {

    /* renamed from: a  reason: collision with root package name */
    private final String f3743a = System.getProperty("line.separator");

    /* renamed from: b  reason: collision with root package name */
    private final Reader f3744b;

    /* renamed from: c  reason: collision with root package name */
    private final c f3745c;

    /* renamed from: d  reason: collision with root package name */
    private boolean f3746d = true;
    private Charset e;
    private final a f;
    private final a g = new a();
    private final b h;
    private int i = -1;
    private int j = 1;
    private boolean k = false;

    private static boolean a(char c2) {
        return c2 == '\n' || c2 == '\r';
    }

    private static boolean b(char c2) {
        return c2 == ' ' || c2 == '\t';
    }

    public f(Reader reader, c cVar) {
        this.f3744b = reader;
        this.f3745c = cVar;
        this.f = new a(cVar.a());
        this.h = new b(this.f.f3748a);
        if (reader instanceof InputStreamReader) {
            this.e = Charset.forName(((InputStreamReader) reader).getEncoding());
        } else {
            this.e = Charset.defaultCharset();
        }
    }

    public Charset a() {
        return this.e;
    }

    public void a(Charset charset) {
        this.e = charset;
    }

    public boolean b() {
        return this.f3746d;
    }

    public void a(boolean z) {
        this.f3746d = z;
    }

    public void a(d dVar) {
        this.h.f3737d = false;
        while (!this.k && !this.h.f3737d) {
            this.h.f3736c = this.j;
            this.g.a();
            this.h.f3735b.a();
            d b2 = b(dVar);
            if (this.h.f3735b.e() != 0) {
                if (b2 == null) {
                    dVar.a(g.MALFORMED_LINE, null, null, this.h);
                } else if ("BEGIN".equalsIgnoreCase(b2.b().trim())) {
                    String upperCase = b2.d().trim().toUpperCase();
                    if (upperCase.length() == 0) {
                        dVar.a(g.EMPTY_BEGIN, null, null, this.h);
                    } else {
                        dVar.a(upperCase, this.h);
                        this.f.a(upperCase);
                    }
                } else if ("END".equalsIgnoreCase(b2.b().trim())) {
                    String upperCase2 = b2.d().trim().toUpperCase();
                    if (upperCase2.length() == 0) {
                        dVar.a(g.EMPTY_END, null, null, this.h);
                    } else {
                        int b3 = this.f.b(upperCase2);
                        if (b3 == 0) {
                            dVar.a(g.UNMATCHED_END, null, null, this.h);
                        } else {
                            while (b3 > 0) {
                                dVar.b(this.f.a(), this.h);
                                b3--;
                            }
                        }
                    }
                } else {
                    if ("VERSION".equalsIgnoreCase(b2.b())) {
                        String b4 = this.f.b();
                        if (this.f3745c.a(b4)) {
                            com.github.a.a.a a2 = this.f3745c.a(b4, b2.d());
                            if (a2 == null) {
                                dVar.a(g.UNKNOWN_VERSION, b2, null, this.h);
                            } else {
                                dVar.c(b2.d(), this.h);
                                this.f.a(a2);
                            }
                        }
                    }
                    dVar.a(b2, this.h);
                }
            } else {
                return;
            }
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARNING: Code restructure failed: missing block: B:68:0x010d, code lost:
        if (r18.f3746d != false) goto L_0x0112;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:69:0x0110, code lost:
        if (r13 == '\\') goto L_0x0112;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:70:0x0112, code lost:
        r6 = r13;
        r9 = r6;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private com.github.a.a.d b(com.github.a.a.b.d r19) {
        /*
        // Method dump skipped, instructions count: 460
        */
        throw new UnsupportedOperationException("Method not decompiled: com.github.a.a.b.f.b(com.github.a.a.b.d):com.github.a.a.d");
    }

    private void a(d dVar, d dVar2) {
        Charset b2 = b(dVar, dVar2);
        if (b2 == null) {
            b2 = this.e;
        }
        try {
            dVar.c(new b(b2.name()).a(dVar.d()));
        } catch (com.github.a.a.a.a e2) {
            dVar2.a(g.QUOTED_PRINTABLE_ERROR, dVar, e2, this.h);
        }
    }

    private Charset b(d dVar, d dVar2) {
        try {
            return dVar.c().b();
        } catch (IllegalCharsetNameException | UnsupportedCharsetException e2) {
            dVar2.a(g.UNKNOWN_CHARSET, dVar, e2, this.h);
            return null;
        }
    }

    private int c() {
        int i2 = this.i;
        if (i2 < 0) {
            return this.f3744b.read();
        }
        this.i = -1;
        return i2;
    }

    /* access modifiers changed from: private */
    /* compiled from: VObjectReader */
    public static class a {

        /* renamed from: a  reason: collision with root package name */
        private final List<String> f3748a = new ArrayList();

        /* renamed from: b  reason: collision with root package name */
        private final List<com.github.a.a.a> f3749b = new ArrayList();

        public a(com.github.a.a.a aVar) {
            this.f3749b.add(aVar);
        }

        public void a(String str) {
            this.f3748a.add(str);
            this.f3749b.add(c());
        }

        public String a() {
            List<com.github.a.a.a> list = this.f3749b;
            list.remove(list.size() - 1);
            List<String> list2 = this.f3748a;
            return list2.remove(list2.size() - 1);
        }

        public int b(String str) {
            int lastIndexOf = this.f3748a.lastIndexOf(str);
            if (lastIndexOf < 0) {
                return 0;
            }
            return this.f3748a.size() - lastIndexOf;
        }

        public String b() {
            if (this.f3748a.isEmpty()) {
                return null;
            }
            List<String> list = this.f3748a;
            return list.get(list.size() - 1);
        }

        public com.github.a.a.a c() {
            if (this.f3749b.isEmpty()) {
                return null;
            }
            List<com.github.a.a.a> list = this.f3749b;
            return list.get(list.size() - 1);
        }

        public void a(com.github.a.a.a aVar) {
            List<com.github.a.a.a> list = this.f3749b;
            list.set(list.size() - 1, aVar);
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.f3744b.close();
    }
}
