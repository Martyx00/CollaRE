package com.google.protobuf;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.AbstractList;
import java.util.List;
import java.util.RandomAccess;
import org.spongycastle.i18n.LocalizedMessage;

/* compiled from: Internal */
public final class u {

    /* renamed from: a  reason: collision with root package name */
    static final Charset f4521a = Charset.forName("UTF-8");

    /* renamed from: b  reason: collision with root package name */
    static final Charset f4522b = Charset.forName(LocalizedMessage.DEFAULT_ENCODING);

    /* renamed from: c  reason: collision with root package name */
    public static final byte[] f4523c = new byte[0];

    /* renamed from: d  reason: collision with root package name */
    public static final ByteBuffer f4524d = ByteBuffer.wrap(f4523c);
    public static final h e = h.a(f4523c);

    /* compiled from: Internal */
    public interface a {
        int a();
    }

    /* compiled from: Internal */
    public interface b<T extends a> {
    }

    public interface d extends List, RandomAccess {
    }

    public static int a(long j) {
        return (int) (j ^ (j >>> 32));
    }

    public static int a(boolean z) {
        return z ? 1231 : 1237;
    }

    static <T> T a(T t) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException();
    }

    static <T> T a(T t, String str) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(str);
    }

    public static boolean a(byte[] bArr) {
        return az.a(bArr);
    }

    public static String b(byte[] bArr) {
        return new String(bArr, f4521a);
    }

    public static int a(a aVar) {
        return aVar.a();
    }

    public static int a(List<? extends a> list) {
        int i = 1;
        for (a aVar : list) {
            i = (i * 31) + a(aVar);
        }
        return i;
    }

    public static int c(byte[] bArr) {
        return a(bArr, 0, bArr.length);
    }

    static int a(byte[] bArr, int i, int i2) {
        int a2 = a(i2, bArr, i, i2);
        if (a2 == 0) {
            return 1;
        }
        return a2;
    }

    static int a(int i, byte[] bArr, int i2, int i3) {
        int i4 = i;
        for (int i5 = i2; i5 < i2 + i3; i5++) {
            i4 = (i4 * 31) + bArr[i5];
        }
        return i4;
    }

    /* compiled from: Internal */
    public static class c<F, T> extends AbstractList<T> {

        /* renamed from: a  reason: collision with root package name */
        private final List<F> f4525a;

        /* renamed from: b  reason: collision with root package name */
        private final a<F, T> f4526b;

        /* compiled from: Internal */
        public interface a<F, T> {
            T a(F f);
        }

        public c(List<F> list, a<F, T> aVar) {
            this.f4525a = list;
            this.f4526b = aVar;
        }

        @Override // java.util.List, java.util.AbstractList
        public T get(int i) {
            return this.f4526b.a(this.f4525a.get(i));
        }

        public int size() {
            return this.f4525a.size();
        }
    }
}
