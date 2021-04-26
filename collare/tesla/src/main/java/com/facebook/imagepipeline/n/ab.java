package com.facebook.imagepipeline.n;

import com.facebook.common.g.h;
import com.facebook.imagepipeline.j.d;
import com.facebook.imagepipeline.o.b;
import java.io.FileInputStream;
import java.util.concurrent.Executor;

/* compiled from: LocalFileFetchProducer */
public class ab extends aa {
    /* access modifiers changed from: protected */
    @Override // com.facebook.imagepipeline.n.aa
    public String a() {
        return "LocalFileFetchProducer";
    }

    public ab(Executor executor, h hVar) {
        super(executor, hVar);
    }

    /* access modifiers changed from: protected */
    @Override // com.facebook.imagepipeline.n.aa
    public d a(b bVar) {
        return b(new FileInputStream(bVar.p().toString()), (int) bVar.p().length());
    }
}
