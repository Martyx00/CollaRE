package com.facebook.soloader;

import android.content.Context;
import com.facebook.soloader.m;
import java.io.File;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/* compiled from: ExtractFromZipSoSource */
public class f extends m {

    /* renamed from: c  reason: collision with root package name */
    protected final File f3624c;

    /* renamed from: d  reason: collision with root package name */
    protected final String f3625d;

    public f(Context context, String str, File file, String str2) {
        super(context, str);
        this.f3624c = file;
        this.f3625d = str2;
    }

    /* access modifiers changed from: protected */
    @Override // com.facebook.soloader.m
    public m.e a() {
        return new b(this);
    }

    /* access modifiers changed from: protected */
    /* compiled from: ExtractFromZipSoSource */
    public class b extends m.e {

        /* renamed from: a  reason: collision with root package name */
        private a[] f3628a;

        /* renamed from: c  reason: collision with root package name */
        private final ZipFile f3630c;

        /* renamed from: d  reason: collision with root package name */
        private final m f3631d;

        /* access modifiers changed from: protected */
        public boolean a(ZipEntry zipEntry, String str) {
            return true;
        }

        b(m mVar) {
            this.f3630c = new ZipFile(f.this.f3624c);
            this.f3631d = mVar;
        }

        /* access modifiers changed from: package-private */
        public final a[] c() {
            if (this.f3628a == null) {
                LinkedHashSet linkedHashSet = new LinkedHashSet();
                HashMap hashMap = new HashMap();
                Pattern compile = Pattern.compile(f.this.f3625d);
                String[] a2 = SysUtil.a();
                Enumeration<? extends ZipEntry> entries = this.f3630c.entries();
                while (entries.hasMoreElements()) {
                    ZipEntry zipEntry = (ZipEntry) entries.nextElement();
                    Matcher matcher = compile.matcher(zipEntry.getName());
                    if (matcher.matches()) {
                        String group = matcher.group(1);
                        String group2 = matcher.group(2);
                        int a3 = SysUtil.a(a2, group);
                        if (a3 >= 0) {
                            linkedHashSet.add(group);
                            a aVar = (a) hashMap.get(group2);
                            if (aVar == null || a3 < aVar.f3627b) {
                                hashMap.put(group2, new a(group2, zipEntry, a3));
                            }
                        }
                    }
                }
                this.f3631d.a((String[]) linkedHashSet.toArray(new String[linkedHashSet.size()]));
                a[] aVarArr = (a[]) hashMap.values().toArray(new a[hashMap.size()]);
                Arrays.sort(aVarArr);
                int i = 0;
                for (int i2 = 0; i2 < aVarArr.length; i2++) {
                    a aVar2 = aVarArr[i2];
                    if (a(aVar2.f3626a, aVar2.f3642c)) {
                        i++;
                    } else {
                        aVarArr[i2] = null;
                    }
                }
                a[] aVarArr2 = new a[i];
                int i3 = 0;
                for (a aVar3 : aVarArr) {
                    if (aVar3 != null) {
                        aVarArr2[i3] = aVar3;
                        i3++;
                    }
                }
                this.f3628a = aVarArr2;
            }
            return this.f3628a;
        }

        @Override // java.io.Closeable, java.lang.AutoCloseable, com.facebook.soloader.m.e
        public void close() {
            this.f3630c.close();
        }

        /* access modifiers changed from: protected */
        @Override // com.facebook.soloader.m.e
        public final m.b a() {
            return new m.b(c());
        }

        /* access modifiers changed from: protected */
        @Override // com.facebook.soloader.m.e
        public final m.d b() {
            return new a();
        }

        /* compiled from: ExtractFromZipSoSource */
        private final class a extends m.d {

            /* renamed from: b  reason: collision with root package name */
            private int f3633b;

            private a() {
            }

            @Override // com.facebook.soloader.m.d
            public boolean a() {
                b.this.c();
                return this.f3633b < b.this.f3628a.length;
            }

            @Override // com.facebook.soloader.m.d
            public m.c b() {
                b.this.c();
                a[] aVarArr = b.this.f3628a;
                int i = this.f3633b;
                this.f3633b = i + 1;
                a aVar = aVarArr[i];
                InputStream inputStream = b.this.f3630c.getInputStream(aVar.f3626a);
                try {
                    return new m.c(aVar, inputStream);
                } catch (Throwable th) {
                    if (inputStream != null) {
                        inputStream.close();
                    }
                    throw th;
                }
            }
        }
    }

    /* access modifiers changed from: private */
    /* compiled from: ExtractFromZipSoSource */
    public static final class a extends m.a implements Comparable {

        /* renamed from: a  reason: collision with root package name */
        final ZipEntry f3626a;

        /* renamed from: b  reason: collision with root package name */
        final int f3627b;

        a(String str, ZipEntry zipEntry, int i) {
            super(str, a(zipEntry));
            this.f3626a = zipEntry;
            this.f3627b = i;
        }

        private static String a(ZipEntry zipEntry) {
            return String.format("pseudo-zip-hash-1-%s-%s-%s-%s", zipEntry.getName(), Long.valueOf(zipEntry.getSize()), Long.valueOf(zipEntry.getCompressedSize()), Long.valueOf(zipEntry.getCrc()));
        }

        @Override // java.lang.Comparable
        public int compareTo(Object obj) {
            return this.f3642c.compareTo(((a) obj).f3642c);
        }
    }
}
