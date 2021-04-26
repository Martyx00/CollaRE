package com.facebook.imagepipeline.n;

import android.content.ContentResolver;
import android.database.Cursor;
import android.graphics.Rect;
import android.media.ExifInterface;
import android.net.Uri;
import com.facebook.common.e.a;
import com.facebook.common.g.h;
import com.facebook.common.k.f;
import com.facebook.imagepipeline.e.e;
import com.facebook.imagepipeline.j.d;
import com.facebook.imagepipeline.o.b;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.Executor;

/* compiled from: LocalContentUriThumbnailFetchProducer */
public class y extends aa implements ax<d> {

    /* renamed from: a  reason: collision with root package name */
    private static final Class<?> f2448a = y.class;

    /* renamed from: b  reason: collision with root package name */
    private static final String[] f2449b = {"_id", "_data"};

    /* renamed from: c  reason: collision with root package name */
    private static final String[] f2450c = {"_data"};

    /* renamed from: d  reason: collision with root package name */
    private static final Rect f2451d = new Rect(0, 0, 512, 384);
    private static final Rect e = new Rect(0, 0, 96, 96);
    private final ContentResolver f;

    /* access modifiers changed from: protected */
    @Override // com.facebook.imagepipeline.n.aa
    public String a() {
        return "LocalContentUriThumbnailFetchProducer";
    }

    public y(Executor executor, h hVar, ContentResolver contentResolver) {
        super(executor, hVar);
        this.f = contentResolver;
    }

    @Override // com.facebook.imagepipeline.n.ax
    public boolean a(e eVar) {
        return ay.a(f2451d.width(), f2451d.height(), eVar);
    }

    /* access modifiers changed from: protected */
    @Override // com.facebook.imagepipeline.n.aa
    public d a(b bVar) {
        d a2;
        Uri b2 = bVar.b();
        if (!f.f(b2) || (a2 = a(b2, bVar.f())) == null) {
            return null;
        }
        return a2;
    }

    private d a(Uri uri, e eVar) {
        d a2;
        Cursor query = this.f.query(uri, f2449b, null, null, null);
        if (query == null) {
            return null;
        }
        try {
            if (query.getCount() == 0) {
                return null;
            }
            query.moveToFirst();
            String string = query.getString(query.getColumnIndex("_data"));
            if (eVar == null || (a2 = a(eVar, query.getInt(query.getColumnIndex("_id")))) == null) {
                query.close();
                return null;
            }
            a2.c(b(string));
            query.close();
            return a2;
        } finally {
            query.close();
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:25:0x0056  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private com.facebook.imagepipeline.j.d a(com.facebook.imagepipeline.e.e r5, int r6) {
        /*
            r4 = this;
            int r5 = b(r5)
            r0 = 0
            if (r5 != 0) goto L_0x0008
            return r0
        L_0x0008:
            android.content.ContentResolver r1 = r4.f     // Catch:{ all -> 0x0052 }
            long r2 = (long) r6     // Catch:{ all -> 0x0052 }
            java.lang.String[] r6 = com.facebook.imagepipeline.n.y.f2450c     // Catch:{ all -> 0x0052 }
            android.database.Cursor r5 = android.provider.MediaStore.Images.Thumbnails.queryMiniThumbnail(r1, r2, r5, r6)     // Catch:{ all -> 0x0052 }
            if (r5 != 0) goto L_0x0019
            if (r5 == 0) goto L_0x0018
            r5.close()
        L_0x0018:
            return r0
        L_0x0019:
            r5.moveToFirst()     // Catch:{ all -> 0x0050 }
            int r6 = r5.getCount()     // Catch:{ all -> 0x0050 }
            if (r6 <= 0) goto L_0x004a
            java.lang.String r6 = "_data"
            int r6 = r5.getColumnIndex(r6)     // Catch:{ all -> 0x0050 }
            java.lang.String r6 = r5.getString(r6)     // Catch:{ all -> 0x0050 }
            java.io.File r1 = new java.io.File     // Catch:{ all -> 0x0050 }
            r1.<init>(r6)     // Catch:{ all -> 0x0050 }
            boolean r1 = r1.exists()     // Catch:{ all -> 0x0050 }
            if (r1 == 0) goto L_0x004a
            java.io.FileInputStream r0 = new java.io.FileInputStream     // Catch:{ all -> 0x0050 }
            r0.<init>(r6)     // Catch:{ all -> 0x0050 }
            int r6 = a(r6)     // Catch:{ all -> 0x0050 }
            com.facebook.imagepipeline.j.d r6 = r4.b(r0, r6)     // Catch:{ all -> 0x0050 }
            if (r5 == 0) goto L_0x0049
            r5.close()
        L_0x0049:
            return r6
        L_0x004a:
            if (r5 == 0) goto L_0x004f
            r5.close()
        L_0x004f:
            return r0
        L_0x0050:
            r6 = move-exception
            goto L_0x0054
        L_0x0052:
            r6 = move-exception
            r5 = r0
        L_0x0054:
            if (r5 == 0) goto L_0x0059
            r5.close()
        L_0x0059:
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.imagepipeline.n.y.a(com.facebook.imagepipeline.e.e, int):com.facebook.imagepipeline.j.d");
    }

    private static int b(e eVar) {
        if (ay.a(e.width(), e.height(), eVar)) {
            return 3;
        }
        return ay.a(f2451d.width(), f2451d.height(), eVar) ? 1 : 0;
    }

    private static int a(String str) {
        if (str == null) {
            return -1;
        }
        return (int) new File(str).length();
    }

    private static int b(String str) {
        if (str != null) {
            try {
                return com.facebook.h.b.a(new ExifInterface(str).getAttributeInt("Orientation", 1));
            } catch (IOException e2) {
                a.b(f2448a, e2, "Unable to retrieve thumbnail rotation for %s", str);
            }
        }
        return 0;
    }
}
