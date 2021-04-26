package com.facebook.f.a.a.a;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* compiled from: ForwardingImageOriginListener */
public class a implements b {

    /* renamed from: a  reason: collision with root package name */
    private final List<b> f1810a;

    public a(b... bVarArr) {
        this.f1810a = new ArrayList(bVarArr.length);
        Collections.addAll(this.f1810a, bVarArr);
    }

    public synchronized void a(b bVar) {
        this.f1810a.add(bVar);
    }

    public synchronized void b(b bVar) {
        this.f1810a.remove(bVar);
    }

    @Override // com.facebook.f.a.a.a.b
    public synchronized void a(String str, int i, boolean z) {
        int size = this.f1810a.size();
        for (int i2 = 0; i2 < size; i2++) {
            try {
                this.f1810a.get(i2).a(str, i, z);
            } catch (Exception e) {
                com.facebook.common.e.a.c("ForwardingImageOriginListener", "InternalListener exception in onImageLoaded", e);
            }
        }
    }
}
