package com.facebook.react.views.b;

import com.facebook.imagepipeline.f.g;
import com.facebook.imagepipeline.f.j;
import java.util.List;

/* compiled from: MultiSourceHelper */
public class b {

    /* compiled from: MultiSourceHelper */
    public static class a {

        /* renamed from: a  reason: collision with root package name */
        private final a f3311a;

        /* renamed from: b  reason: collision with root package name */
        private final a f3312b;

        private a(a aVar, a aVar2) {
            this.f3311a = aVar;
            this.f3312b = aVar2;
        }

        public a a() {
            return this.f3311a;
        }

        public a b() {
            return this.f3312b;
        }
    }

    public static a a(int i, int i2, List<a> list) {
        return a(i, i2, list, 1.0d);
    }

    public static a a(int i, int i2, List<a> list, double d2) {
        if (list.isEmpty()) {
            return new a(null, null);
        }
        if (list.size() == 1) {
            return new a(list.get(0), null);
        }
        if (i <= 0 || i2 <= 0) {
            return new a(null, null);
        }
        g h = j.a().h();
        double d3 = ((double) (i * i2)) * d2;
        double d4 = Double.MAX_VALUE;
        double d5 = Double.MAX_VALUE;
        a aVar = null;
        a aVar2 = null;
        for (a aVar3 : list) {
            double abs = Math.abs(1.0d - (aVar3.c() / d3));
            if (abs < d4) {
                aVar2 = aVar3;
                d4 = abs;
            }
            if (abs < d5 && (h.a(aVar3.b()) || h.b(aVar3.b()))) {
                aVar = aVar3;
                d5 = abs;
            }
        }
        if (!(aVar == null || aVar2 == null || !aVar.a().equals(aVar2.a()))) {
            aVar = null;
        }
        return new a(aVar2, aVar);
    }
}
