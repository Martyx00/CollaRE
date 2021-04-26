package com.facebook.imagepipeline.n;

import android.content.res.AssetFileDescriptor;
import android.content.res.Resources;
import com.facebook.common.g.h;
import com.facebook.imagepipeline.j.d;
import com.facebook.imagepipeline.o.b;
import java.io.IOException;
import java.util.concurrent.Executor;

/* compiled from: LocalResourceFetchProducer */
public class ac extends aa {

    /* renamed from: a  reason: collision with root package name */
    private final Resources f2247a;

    /* access modifiers changed from: protected */
    @Override // com.facebook.imagepipeline.n.aa
    public String a() {
        return "LocalResourceFetchProducer";
    }

    public ac(Executor executor, h hVar, Resources resources) {
        super(executor, hVar);
        this.f2247a = resources;
    }

    /* access modifiers changed from: protected */
    @Override // com.facebook.imagepipeline.n.aa
    public d a(b bVar) {
        return b(this.f2247a.openRawResource(c(bVar)), b(bVar));
    }

    private int b(b bVar) {
        AssetFileDescriptor assetFileDescriptor = null;
        try {
            assetFileDescriptor = this.f2247a.openRawResourceFd(c(bVar));
            int length = (int) assetFileDescriptor.getLength();
            if (assetFileDescriptor != null) {
                try {
                    assetFileDescriptor.close();
                } catch (IOException unused) {
                }
            }
            return length;
        } catch (Resources.NotFoundException unused2) {
            if (assetFileDescriptor != null) {
                try {
                    assetFileDescriptor.close();
                } catch (IOException unused3) {
                }
            }
            return -1;
        } catch (Throwable th) {
            if (assetFileDescriptor != null) {
                try {
                    assetFileDescriptor.close();
                } catch (IOException unused4) {
                }
            }
            throw th;
        }
    }

    private static int c(b bVar) {
        return Integer.parseInt(bVar.b().getPath().substring(1));
    }
}
