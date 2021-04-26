package com.facebook.b.a;

import com.facebook.common.k.c;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/* compiled from: CacheKeyUtil */
public final class e {
    public static List<String> a(d dVar) {
        try {
            if (dVar instanceof f) {
                List<d> b2 = ((f) dVar).b();
                ArrayList arrayList = new ArrayList(b2.size());
                for (int i = 0; i < b2.size(); i++) {
                    arrayList.add(c(b2.get(i)));
                }
                return arrayList;
            }
            ArrayList arrayList2 = new ArrayList(1);
            arrayList2.add(c(dVar));
            return arrayList2;
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    public static String b(d dVar) {
        try {
            if (dVar instanceof f) {
                return c(((f) dVar).b().get(0));
            }
            return c(dVar);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    private static String c(d dVar) {
        return c.a(dVar.a().getBytes("UTF-8"));
    }
}
