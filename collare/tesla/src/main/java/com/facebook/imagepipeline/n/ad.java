package com.facebook.imagepipeline.n;

import android.content.ContentResolver;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Build;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import com.facebook.common.h.a;
import com.facebook.imagepipeline.c.g;
import com.facebook.imagepipeline.j.b;
import com.facebook.imagepipeline.j.c;
import com.facebook.imagepipeline.j.f;
import java.util.Map;
import java.util.concurrent.Executor;

/* compiled from: LocalVideoThumbnailProducer */
public class ad implements ak<a<b>> {

    /* renamed from: a  reason: collision with root package name */
    private final Executor f2248a;

    /* renamed from: b  reason: collision with root package name */
    private final ContentResolver f2249b;

    public ad(Executor executor, ContentResolver contentResolver) {
        this.f2248a = executor;
        this.f2249b = contentResolver;
    }

    @Override // com.facebook.imagepipeline.n.ak
    public void a(k<a<b>> kVar, al alVar) {
        final an c2 = alVar.c();
        final String b2 = alVar.b();
        final com.facebook.imagepipeline.o.b a2 = alVar.a();
        final AnonymousClass1 r9 = new ar<a<b>>(kVar, "VideoThumbnailProducer", c2, b2) {
            /* class com.facebook.imagepipeline.n.ad.AnonymousClass1 */

            /* access modifiers changed from: protected */
            public void a(a<b> aVar) {
                super.a((Object) aVar);
                c2.a(b2, "VideoThumbnailProducer", aVar != null);
            }

            /* access modifiers changed from: protected */
            @Override // com.facebook.common.b.e, com.facebook.imagepipeline.n.ar
            public void a(Exception exc) {
                super.a(exc);
                c2.a(b2, "VideoThumbnailProducer", false);
            }

            /* access modifiers changed from: protected */
            /* renamed from: d */
            public a<b> c() {
                Bitmap createVideoThumbnail;
                String c2 = ad.this.c(a2);
                if (c2 == null || (createVideoThumbnail = ThumbnailUtils.createVideoThumbnail(c2, ad.b(a2))) == null) {
                    return null;
                }
                return a.a(new c(createVideoThumbnail, g.a(), f.f2171a, 0));
            }

            /* access modifiers changed from: protected */
            /* renamed from: b */
            public Map<String, String> c(a<b> aVar) {
                return com.facebook.common.d.f.a("createdThumbnail", String.valueOf(aVar != null));
            }

            /* access modifiers changed from: protected */
            /* renamed from: c */
            public void b(a<b> aVar) {
                a.c(aVar);
            }
        };
        alVar.a(new e() {
            /* class com.facebook.imagepipeline.n.ad.AnonymousClass2 */

            @Override // com.facebook.imagepipeline.n.am, com.facebook.imagepipeline.n.e
            public void a() {
                r9.a();
            }
        });
        this.f2248a.execute(r9);
    }

    /* access modifiers changed from: private */
    public static int b(com.facebook.imagepipeline.o.b bVar) {
        return (bVar.d() > 96 || bVar.e() > 96) ? 1 : 3;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private String c(com.facebook.imagepipeline.o.b bVar) {
        String[] strArr;
        String str;
        Uri uri;
        Uri b2 = bVar.b();
        if (com.facebook.common.k.f.c(b2)) {
            return bVar.p().getPath();
        }
        if (com.facebook.common.k.f.d(b2)) {
            if (Build.VERSION.SDK_INT < 19 || !"com.android.providers.media.documents".equals(b2.getAuthority())) {
                uri = b2;
                str = null;
                strArr = null;
            } else {
                String documentId = DocumentsContract.getDocumentId(b2);
                uri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                str = "_id=?";
                strArr = new String[]{documentId.split(":")[1]};
            }
            Cursor query = this.f2249b.query(uri, new String[]{"_data"}, str, strArr, null);
            if (query != null) {
                try {
                    if (query.moveToFirst()) {
                        return query.getString(query.getColumnIndexOrThrow("_data"));
                    }
                } finally {
                    if (query != null) {
                        query.close();
                    }
                }
            }
            if (query != null) {
                query.close();
            }
        }
        return null;
    }
}
