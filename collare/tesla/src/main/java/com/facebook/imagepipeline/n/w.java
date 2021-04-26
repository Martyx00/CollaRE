package com.facebook.imagepipeline.n;

import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import com.facebook.common.g.h;
import com.facebook.imagepipeline.j.d;
import com.facebook.imagepipeline.o.b;
import java.io.IOException;
import java.util.concurrent.Executor;

/* compiled from: LocalAssetFetchProducer */
public class w extends aa {

    /* renamed from: a  reason: collision with root package name */
    private final AssetManager f2445a;

    /* access modifiers changed from: protected */
    @Override // com.facebook.imagepipeline.n.aa
    public String a() {
        return "LocalAssetFetchProducer";
    }

    public w(Executor executor, h hVar, AssetManager assetManager) {
        super(executor, hVar);
        this.f2445a = assetManager;
    }

    /* access modifiers changed from: protected */
    @Override // com.facebook.imagepipeline.n.aa
    public d a(b bVar) {
        return b(this.f2445a.open(c(bVar), 2), b(bVar));
    }

    private int b(b bVar) {
        AssetFileDescriptor assetFileDescriptor = null;
        try {
            assetFileDescriptor = this.f2445a.openFd(c(bVar));
            int length = (int) assetFileDescriptor.getLength();
            if (assetFileDescriptor != null) {
                try {
                    assetFileDescriptor.close();
                } catch (IOException unused) {
                }
            }
            return length;
        } catch (IOException unused2) {
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

    private static String c(b bVar) {
        return bVar.b().getPath().substring(1);
    }
}
