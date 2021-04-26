package com.facebook.g;

import com.facebook.common.d.a;
import com.facebook.common.d.i;
import com.facebook.common.d.n;
import com.facebook.g.c;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/* compiled from: ImageFormatChecker */
public class d {

    /* renamed from: a  reason: collision with root package name */
    private static d f1994a;

    /* renamed from: b  reason: collision with root package name */
    private int f1995b;

    /* renamed from: c  reason: collision with root package name */
    private List<c.a> f1996c;

    /* renamed from: d  reason: collision with root package name */
    private final c.a f1997d = new a();

    private d() {
        b();
    }

    public void a(List<c.a> list) {
        this.f1996c = list;
        b();
    }

    public c a(InputStream inputStream) {
        i.a(inputStream);
        int i = this.f1995b;
        byte[] bArr = new byte[i];
        int a2 = a(i, inputStream, bArr);
        c a3 = this.f1997d.a(bArr, a2);
        if (!(a3 == null || a3 == c.f1991a)) {
            return a3;
        }
        List<c.a> list = this.f1996c;
        if (list != null) {
            for (c.a aVar : list) {
                c a4 = aVar.a(bArr, a2);
                if (!(a4 == null || a4 == c.f1991a)) {
                    return a4;
                }
            }
        }
        return c.f1991a;
    }

    private void b() {
        this.f1995b = this.f1997d.a();
        List<c.a> list = this.f1996c;
        if (list != null) {
            for (c.a aVar : list) {
                this.f1995b = Math.max(this.f1995b, aVar.a());
            }
        }
    }

    private static int a(int i, InputStream inputStream, byte[] bArr) {
        i.a(inputStream);
        i.a(bArr);
        i.a(bArr.length >= i);
        if (!inputStream.markSupported()) {
            return a.a(inputStream, bArr, 0, i);
        }
        try {
            inputStream.mark(i);
            return a.a(inputStream, bArr, 0, i);
        } finally {
            inputStream.reset();
        }
    }

    public static synchronized d a() {
        d dVar;
        synchronized (d.class) {
            if (f1994a == null) {
                f1994a = new d();
            }
            dVar = f1994a;
        }
        return dVar;
    }

    public static c b(InputStream inputStream) {
        return a().a(inputStream);
    }

    public static c c(InputStream inputStream) {
        try {
            return b(inputStream);
        } catch (IOException e) {
            throw n.b(e);
        }
    }
}
