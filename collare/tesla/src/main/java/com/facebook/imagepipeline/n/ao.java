package com.facebook.imagepipeline.n;

import android.content.ContentResolver;
import com.facebook.common.g.h;
import com.facebook.imagepipeline.j.d;
import com.facebook.imagepipeline.o.b;
import java.util.concurrent.Executor;

/* compiled from: QualifiedResourceFetchProducer */
public class ao extends aa {

    /* renamed from: a  reason: collision with root package name */
    private final ContentResolver f2306a;

    /* access modifiers changed from: protected */
    @Override // com.facebook.imagepipeline.n.aa
    public String a() {
        return "QualifiedResourceFetchProducer";
    }

    public ao(Executor executor, h hVar, ContentResolver contentResolver) {
        super(executor, hVar);
        this.f2306a = contentResolver;
    }

    /* access modifiers changed from: protected */
    @Override // com.facebook.imagepipeline.n.aa
    public d a(b bVar) {
        return b(this.f2306a.openInputStream(bVar.b()), -1);
    }
}
