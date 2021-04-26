package com.facebook.imagepipeline.l;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.os.MemoryFile;
import com.facebook.common.d.b;
import com.facebook.common.d.n;
import com.facebook.common.g.g;
import com.facebook.common.g.i;
import com.facebook.common.h.a;
import com.facebook.imagepipeline.j.d;
import java.io.Closeable;
import java.io.FileDescriptor;
import java.io.OutputStream;
import java.lang.reflect.Method;

/* compiled from: GingerbreadPurgeableDecoder */
public class c extends b {

    /* renamed from: b  reason: collision with root package name */
    private static Method f2182b;

    @Override // com.facebook.imagepipeline.l.b
    public /* bridge */ /* synthetic */ a a(Bitmap bitmap) {
        return super.a(bitmap);
    }

    @Override // com.facebook.imagepipeline.l.b, com.facebook.imagepipeline.l.e
    public /* bridge */ /* synthetic */ a a(d dVar, Bitmap.Config config, Rect rect) {
        return super.a(dVar, config, rect);
    }

    @Override // com.facebook.imagepipeline.l.b, com.facebook.imagepipeline.l.e
    public /* bridge */ /* synthetic */ a a(d dVar, Bitmap.Config config, Rect rect, int i) {
        return super.a(dVar, config, rect, i);
    }

    /* access modifiers changed from: protected */
    @Override // com.facebook.imagepipeline.l.b
    public Bitmap a(a<g> aVar, BitmapFactory.Options options) {
        return a(aVar, aVar.a().a(), (byte[]) null, options);
    }

    /* access modifiers changed from: protected */
    @Override // com.facebook.imagepipeline.l.b
    public Bitmap a(a<g> aVar, int i, BitmapFactory.Options options) {
        return a(aVar, i, a(aVar, i) ? null : f2180a, options);
    }

    private static MemoryFile a(a<g> aVar, int i, byte[] bArr) {
        Closeable closeable;
        Throwable th;
        i iVar;
        com.facebook.common.j.a aVar2 = null;
        MemoryFile memoryFile = new MemoryFile(null, (bArr == null ? 0 : bArr.length) + i);
        memoryFile.allowPurging(false);
        try {
            iVar = new i(aVar.a());
            try {
                com.facebook.common.j.a aVar3 = new com.facebook.common.j.a(iVar, i);
                try {
                    OutputStream outputStream = memoryFile.getOutputStream();
                    com.facebook.common.d.a.a(aVar3, outputStream);
                    if (bArr != null) {
                        memoryFile.writeBytes(bArr, 0, i, bArr.length);
                    }
                    a.c(aVar);
                    b.a(iVar);
                    b.a(aVar3);
                    b.a(outputStream, true);
                    return memoryFile;
                } catch (Throwable th2) {
                    th = th2;
                    closeable = null;
                    aVar2 = aVar3;
                    a.c(aVar);
                    b.a(iVar);
                    b.a(aVar2);
                    b.a(closeable, true);
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                closeable = null;
                a.c(aVar);
                b.a(iVar);
                b.a(aVar2);
                b.a(closeable, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            closeable = null;
            iVar = null;
            a.c(aVar);
            b.a(iVar);
            b.a(aVar2);
            b.a(closeable, true);
            throw th;
        }
    }

    private synchronized Method a() {
        if (f2182b == null) {
            try {
                f2182b = MemoryFile.class.getDeclaredMethod("getFileDescriptor", new Class[0]);
            } catch (Exception e) {
                throw n.b(e);
            }
        }
        return f2182b;
    }

    private FileDescriptor a(MemoryFile memoryFile) {
        try {
            return (FileDescriptor) a().invoke(memoryFile, new Object[0]);
        } catch (Exception e) {
            throw n.b(e);
        }
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x002d  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public android.graphics.Bitmap a(com.facebook.common.h.a<com.facebook.common.g.g> r2, int r3, byte[] r4, android.graphics.BitmapFactory.Options r5) {
        /*
            r1 = this;
            r0 = 0
            android.os.MemoryFile r2 = a(r2, r3, r4)     // Catch:{ IOException -> 0x0025 }
            java.io.FileDescriptor r3 = r1.a(r2)     // Catch:{ IOException -> 0x001f, all -> 0x001d }
            com.facebook.common.l.b r4 = com.facebook.common.l.c.f1796d     // Catch:{ IOException -> 0x001f, all -> 0x001d }
            android.graphics.Bitmap r3 = r4.a(r3, r0, r5)     // Catch:{ IOException -> 0x001f, all -> 0x001d }
            java.lang.String r4 = "BitmapFactory returned null"
            java.lang.Object r3 = com.facebook.common.d.i.a(r3, r4)     // Catch:{ IOException -> 0x001f, all -> 0x001d }
            android.graphics.Bitmap r3 = (android.graphics.Bitmap) r3     // Catch:{ IOException -> 0x001f, all -> 0x001d }
            if (r2 == 0) goto L_0x001c
            r2.close()
        L_0x001c:
            return r3
        L_0x001d:
            r3 = move-exception
            goto L_0x002b
        L_0x001f:
            r3 = move-exception
            r0 = r2
            goto L_0x0026
        L_0x0022:
            r3 = move-exception
            r2 = r0
            goto L_0x002b
        L_0x0025:
            r3 = move-exception
        L_0x0026:
            java.lang.RuntimeException r2 = com.facebook.common.d.n.b(r3)     // Catch:{ all -> 0x0022 }
            throw r2     // Catch:{ all -> 0x0022 }
        L_0x002b:
            if (r2 == 0) goto L_0x0030
            r2.close()
        L_0x0030:
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.imagepipeline.l.c.a(com.facebook.common.h.a, int, byte[], android.graphics.BitmapFactory$Options):android.graphics.Bitmap");
    }
}
