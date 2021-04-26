package android.support.v4.e;

import android.content.ContentUris;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.content.pm.Signature;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Build;
import android.os.CancellationSignal;
import android.os.Handler;
import android.support.v4.content.a.f;
import android.support.v4.e.c;
import android.support.v4.graphics.i;
import android.support.v4.util.g;
import android.support.v4.util.k;
import android.support.v4.util.l;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

/* compiled from: FontsContractCompat */
public class b {

    /* renamed from: a  reason: collision with root package name */
    static final g<String, Typeface> f408a = new g<>(16);

    /* renamed from: b  reason: collision with root package name */
    static final Object f409b = new Object();

    /* renamed from: c  reason: collision with root package name */
    static final l<String, ArrayList<c.a<c>>> f410c = new l<>();

    /* renamed from: d  reason: collision with root package name */
    private static final c f411d = new c("fonts", 10, io.a.a.a.a.b.a.DEFAULT_TIMEOUT);
    private static final Comparator<byte[]> e = new Comparator<byte[]>() {
        /* class android.support.v4.e.b.AnonymousClass4 */

        /* renamed from: a */
        public int compare(byte[] bArr, byte[] bArr2) {
            if (bArr.length != bArr2.length) {
                return bArr.length - bArr2.length;
            }
            for (int i = 0; i < bArr.length; i++) {
                if (bArr[i] != bArr2[i]) {
                    return bArr[i] - bArr2[i];
                }
            }
            return 0;
        }
    };

    static c a(Context context, a aVar, int i) {
        try {
            a a2 = a(context, (CancellationSignal) null, aVar);
            int i2 = -3;
            if (a2.a() == 0) {
                Typeface a3 = android.support.v4.graphics.c.a(context, null, a2.b(), i);
                if (a3 != null) {
                    i2 = 0;
                }
                return new c(a3, i2);
            }
            if (a2.a() == 1) {
                i2 = -2;
            }
            return new c(null, i2);
        } catch (PackageManager.NameNotFoundException unused) {
            return new c(null, -1);
        }
    }

    /* access modifiers changed from: private */
    /* compiled from: FontsContractCompat */
    public static final class c {

        /* renamed from: a  reason: collision with root package name */
        final Typeface f425a;

        /* renamed from: b  reason: collision with root package name */
        final int f426b;

        c(Typeface typeface, int i) {
            this.f425a = typeface;
            this.f426b = i;
        }
    }

    public static Typeface a(final Context context, final a aVar, final f.a aVar2, final Handler handler, boolean z, int i, final int i2) {
        AnonymousClass2 r3;
        final String str = aVar.f() + "-" + i2;
        Typeface typeface = f408a.get(str);
        if (typeface != null) {
            if (aVar2 != null) {
                aVar2.a(typeface);
            }
            return typeface;
        } else if (!z || i != -1) {
            AnonymousClass1 r1 = new Callable<c>() {
                /* class android.support.v4.e.b.AnonymousClass1 */

                /* renamed from: a */
                public c call() {
                    c a2 = b.a(context, aVar, i2);
                    if (a2.f425a != null) {
                        b.f408a.put(str, a2.f425a);
                    }
                    return a2;
                }
            };
            if (z) {
                try {
                    return ((c) f411d.a(r1, i)).f425a;
                } catch (InterruptedException unused) {
                    return null;
                }
            } else {
                if (aVar2 == null) {
                    r3 = null;
                } else {
                    r3 = new c.a<c>() {
                        /* class android.support.v4.e.b.AnonymousClass2 */

                        public void a(c cVar) {
                            if (cVar == null) {
                                aVar2.a(1, handler);
                            } else if (cVar.f426b == 0) {
                                aVar2.a(cVar.f425a, handler);
                            } else {
                                aVar2.a(cVar.f426b, handler);
                            }
                        }
                    };
                }
                synchronized (f409b) {
                    if (f410c.containsKey(str)) {
                        if (r3 != null) {
                            f410c.get(str).add(r3);
                        }
                        return null;
                    }
                    if (r3 != null) {
                        ArrayList<c.a<c>> arrayList = new ArrayList<>();
                        arrayList.add(r3);
                        f410c.put(str, arrayList);
                    }
                    f411d.a(r1, new c.a<c>() {
                        /* class android.support.v4.e.b.AnonymousClass3 */

                        /* JADX WARNING: Code restructure failed: missing block: B:11:0x001e, code lost:
                            if (r0 >= r1.size()) goto L_0x002c;
                         */
                        /* JADX WARNING: Code restructure failed: missing block: B:12:0x0020, code lost:
                            r1.get(r0).a(r5);
                            r0 = r0 + 1;
                         */
                        /* JADX WARNING: Code restructure failed: missing block: B:13:0x002c, code lost:
                            return;
                         */
                        /* JADX WARNING: Code restructure failed: missing block: B:9:0x0019, code lost:
                            r0 = 0;
                         */
                        /* Code decompiled incorrectly, please refer to instructions dump. */
                        public void a(android.support.v4.e.b.c r5) {
                            /*
                                r4 = this;
                                java.lang.Object r0 = android.support.v4.e.b.f409b
                                monitor-enter(r0)
                                android.support.v4.util.l<java.lang.String, java.util.ArrayList<android.support.v4.e.c$a<android.support.v4.e.b$c>>> r1 = android.support.v4.e.b.f410c     // Catch:{ all -> 0x002d }
                                java.lang.String r2 = r0     // Catch:{ all -> 0x002d }
                                java.lang.Object r1 = r1.get(r2)     // Catch:{ all -> 0x002d }
                                java.util.ArrayList r1 = (java.util.ArrayList) r1     // Catch:{ all -> 0x002d }
                                if (r1 != 0) goto L_0x0011
                                monitor-exit(r0)     // Catch:{ all -> 0x002d }
                                return
                            L_0x0011:
                                android.support.v4.util.l<java.lang.String, java.util.ArrayList<android.support.v4.e.c$a<android.support.v4.e.b$c>>> r2 = android.support.v4.e.b.f410c     // Catch:{ all -> 0x002d }
                                java.lang.String r3 = r0     // Catch:{ all -> 0x002d }
                                r2.remove(r3)     // Catch:{ all -> 0x002d }
                                monitor-exit(r0)     // Catch:{ all -> 0x002d }
                                r0 = 0
                            L_0x001a:
                                int r2 = r1.size()
                                if (r0 >= r2) goto L_0x002c
                                java.lang.Object r2 = r1.get(r0)
                                android.support.v4.e.c$a r2 = (android.support.v4.e.c.a) r2
                                r2.a(r5)
                                int r0 = r0 + 1
                                goto L_0x001a
                            L_0x002c:
                                return
                            L_0x002d:
                                r5 = move-exception
                                monitor-exit(r0)
                                throw r5
                            */
                            throw new UnsupportedOperationException("Method not decompiled: android.support.v4.e.b.AnonymousClass3.a(android.support.v4.e.b$c):void");
                        }
                    });
                    return null;
                }
            }
        } else {
            c a2 = a(context, aVar, i2);
            if (aVar2 != null) {
                if (a2.f426b == 0) {
                    aVar2.a(a2.f425a, handler);
                } else {
                    aVar2.a(a2.f426b, handler);
                }
            }
            return a2.f425a;
        }
    }

    /* renamed from: android.support.v4.e.b$b  reason: collision with other inner class name */
    /* compiled from: FontsContractCompat */
    public static class C0008b {

        /* renamed from: a  reason: collision with root package name */
        private final Uri f421a;

        /* renamed from: b  reason: collision with root package name */
        private final int f422b;

        /* renamed from: c  reason: collision with root package name */
        private final int f423c;

        /* renamed from: d  reason: collision with root package name */
        private final boolean f424d;
        private final int e;

        public C0008b(Uri uri, int i, int i2, boolean z, int i3) {
            this.f421a = (Uri) k.a(uri);
            this.f422b = i;
            this.f423c = i2;
            this.f424d = z;
            this.e = i3;
        }

        public Uri a() {
            return this.f421a;
        }

        public int b() {
            return this.f422b;
        }

        public int c() {
            return this.f423c;
        }

        public boolean d() {
            return this.f424d;
        }

        public int e() {
            return this.e;
        }
    }

    /* compiled from: FontsContractCompat */
    public static class a {

        /* renamed from: a  reason: collision with root package name */
        private final int f419a;

        /* renamed from: b  reason: collision with root package name */
        private final C0008b[] f420b;

        public a(int i, C0008b[] bVarArr) {
            this.f419a = i;
            this.f420b = bVarArr;
        }

        public int a() {
            return this.f419a;
        }

        public C0008b[] b() {
            return this.f420b;
        }
    }

    public static Map<Uri, ByteBuffer> a(Context context, C0008b[] bVarArr, CancellationSignal cancellationSignal) {
        HashMap hashMap = new HashMap();
        for (C0008b bVar : bVarArr) {
            if (bVar.e() == 0) {
                Uri a2 = bVar.a();
                if (!hashMap.containsKey(a2)) {
                    hashMap.put(a2, i.a(context, cancellationSignal, a2));
                }
            }
        }
        return Collections.unmodifiableMap(hashMap);
    }

    public static a a(Context context, CancellationSignal cancellationSignal, a aVar) {
        ProviderInfo a2 = a(context.getPackageManager(), aVar, context.getResources());
        if (a2 == null) {
            return new a(1, null);
        }
        return new a(0, a(context, aVar, a2.authority, cancellationSignal));
    }

    public static ProviderInfo a(PackageManager packageManager, a aVar, Resources resources) {
        String a2 = aVar.a();
        ProviderInfo resolveContentProvider = packageManager.resolveContentProvider(a2, 0);
        if (resolveContentProvider == null) {
            throw new PackageManager.NameNotFoundException("No package found for authority: " + a2);
        } else if (resolveContentProvider.packageName.equals(aVar.b())) {
            List<byte[]> a3 = a(packageManager.getPackageInfo(resolveContentProvider.packageName, 64).signatures);
            Collections.sort(a3, e);
            List<List<byte[]>> a4 = a(aVar, resources);
            for (int i = 0; i < a4.size(); i++) {
                ArrayList arrayList = new ArrayList(a4.get(i));
                Collections.sort(arrayList, e);
                if (a(a3, arrayList)) {
                    return resolveContentProvider;
                }
            }
            return null;
        } else {
            throw new PackageManager.NameNotFoundException("Found content provider " + a2 + ", but package was not " + aVar.b());
        }
    }

    private static List<List<byte[]>> a(a aVar, Resources resources) {
        if (aVar.d() != null) {
            return aVar.d();
        }
        return android.support.v4.content.a.c.a(resources, aVar.e());
    }

    private static boolean a(List<byte[]> list, List<byte[]> list2) {
        if (list.size() != list2.size()) {
            return false;
        }
        for (int i = 0; i < list.size(); i++) {
            if (!Arrays.equals(list.get(i), list2.get(i))) {
                return false;
            }
        }
        return true;
    }

    private static List<byte[]> a(Signature[] signatureArr) {
        ArrayList arrayList = new ArrayList();
        for (Signature signature : signatureArr) {
            arrayList.add(signature.toByteArray());
        }
        return arrayList;
    }

    static C0008b[] a(Context context, a aVar, String str, CancellationSignal cancellationSignal) {
        Uri uri;
        Cursor query;
        ArrayList arrayList = new ArrayList();
        Uri build = new Uri.Builder().scheme(FirebaseAnalytics.b.CONTENT).authority(str).build();
        Uri build2 = new Uri.Builder().scheme(FirebaseAnalytics.b.CONTENT).authority(str).appendPath("file").build();
        Cursor cursor = null;
        try {
            if (Build.VERSION.SDK_INT > 16) {
                query = context.getContentResolver().query(build, new String[]{"_id", "file_id", "font_ttc_index", "font_variation_settings", "font_weight", "font_italic", "result_code"}, "query = ?", new String[]{aVar.c()}, null, cancellationSignal);
            } else {
                query = context.getContentResolver().query(build, new String[]{"_id", "file_id", "font_ttc_index", "font_variation_settings", "font_weight", "font_italic", "result_code"}, "query = ?", new String[]{aVar.c()}, null);
            }
            if (cursor != null && cursor.getCount() > 0) {
                int columnIndex = cursor.getColumnIndex("result_code");
                ArrayList arrayList2 = new ArrayList();
                int columnIndex2 = cursor.getColumnIndex("_id");
                int columnIndex3 = cursor.getColumnIndex("file_id");
                int columnIndex4 = cursor.getColumnIndex("font_ttc_index");
                int columnIndex5 = cursor.getColumnIndex("font_weight");
                int columnIndex6 = cursor.getColumnIndex("font_italic");
                while (cursor.moveToNext()) {
                    int i = columnIndex != -1 ? cursor.getInt(columnIndex) : 0;
                    int i2 = columnIndex4 != -1 ? cursor.getInt(columnIndex4) : 0;
                    if (columnIndex3 == -1) {
                        uri = ContentUris.withAppendedId(build, cursor.getLong(columnIndex2));
                    } else {
                        uri = ContentUris.withAppendedId(build2, cursor.getLong(columnIndex3));
                    }
                    arrayList2.add(new C0008b(uri, i2, columnIndex5 != -1 ? cursor.getInt(columnIndex5) : 400, columnIndex6 != -1 && cursor.getInt(columnIndex6) == 1, i));
                }
                arrayList = arrayList2;
            }
            return (C0008b[]) arrayList.toArray(new C0008b[0]);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }
}
