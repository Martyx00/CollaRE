package com.facebook.c;

import com.facebook.common.d.i;

/* compiled from: SimpleDataSource */
public class h<T> extends a<T> {
    private h() {
    }

    public static <T> h<T> j() {
        return new h<>();
    }

    /* JADX DEBUG: Multi-variable search result rejected for r0v0, resolved type: com.facebook.c.h<T> */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.facebook.c.a
    public boolean a(T t, boolean z) {
        return super.a(i.a(t), z);
    }

    @Override // com.facebook.c.a
    public boolean a(Throwable th) {
        return super.a((Throwable) i.a(th));
    }

    @Override // com.facebook.c.a
    public boolean a(float f) {
        return super.a(f);
    }
}
