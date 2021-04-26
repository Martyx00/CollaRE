package com.facebook.c;

import com.facebook.common.d.l;

/* compiled from: DataSources */
public class d {
    public static <T> c<T> a(Throwable th) {
        h j = h.j();
        j.a(th);
        return j;
    }

    public static <T> l<c<T>> b(final Throwable th) {
        return new l<c<T>>() {
            /* class com.facebook.c.d.AnonymousClass1 */

            /* renamed from: a */
            public c<T> b() {
                return d.a(th);
            }
        };
    }
}
