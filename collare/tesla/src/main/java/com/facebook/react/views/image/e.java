package com.facebook.react.views.image;

import android.graphics.Bitmap;
import com.facebook.b.a.f;
import com.facebook.common.h.a;
import com.facebook.imagepipeline.o.d;
import java.util.LinkedList;
import java.util.List;

/* compiled from: MultiPostprocessor */
public class e implements d {

    /* renamed from: a  reason: collision with root package name */
    private final List<d> f3331a;

    public static d a(List<d> list) {
        switch (list.size()) {
            case 0:
                return null;
            case 1:
                return list.get(0);
            default:
                return new e(list);
        }
    }

    private e(List<d> list) {
        this.f3331a = new LinkedList(list);
    }

    @Override // com.facebook.imagepipeline.o.d
    public String b() {
        StringBuilder sb = new StringBuilder();
        for (d dVar : this.f3331a) {
            if (sb.length() > 0) {
                sb.append(",");
            }
            sb.append(dVar.b());
        }
        sb.insert(0, "MultiPostProcessor (");
        sb.append(")");
        return sb.toString();
    }

    @Override // com.facebook.imagepipeline.o.d
    public com.facebook.b.a.d a() {
        LinkedList linkedList = new LinkedList();
        for (d dVar : this.f3331a) {
            linkedList.push(dVar.a());
        }
        return new f(linkedList);
    }

    /* JADX INFO: finally extract failed */
    @Override // com.facebook.imagepipeline.o.d
    public a<Bitmap> a(Bitmap bitmap, com.facebook.imagepipeline.c.f fVar) {
        a<Bitmap> aVar = null;
        try {
            a<Bitmap> aVar2 = null;
            for (d dVar : this.f3331a) {
                aVar = dVar.a(aVar2 != null ? aVar2.a() : bitmap, fVar);
                a.c(aVar2);
                aVar2 = aVar.clone();
            }
            a<Bitmap> b2 = aVar.clone();
            a.c(aVar);
            return b2;
        } catch (Throwable th) {
            a.c(null);
            throw th;
        }
    }
}
