package android.support.v4.util;

/* compiled from: LongSparseArray */
public class f<E> implements Cloneable {

    /* renamed from: a  reason: collision with root package name */
    private static final Object f649a = new Object();

    /* renamed from: b  reason: collision with root package name */
    private boolean f650b;

    /* renamed from: c  reason: collision with root package name */
    private long[] f651c;

    /* renamed from: d  reason: collision with root package name */
    private Object[] f652d;
    private int e;

    public f() {
        this(10);
    }

    public f(int i) {
        this.f650b = false;
        if (i == 0) {
            this.f651c = c.f645b;
            this.f652d = c.f646c;
        } else {
            int b2 = c.b(i);
            this.f651c = new long[b2];
            this.f652d = new Object[b2];
        }
        this.e = 0;
    }

    /* renamed from: a */
    public f<E> clone() {
        try {
            f<E> fVar = (f) super.clone();
            fVar.f651c = (long[]) this.f651c.clone();
            fVar.f652d = (Object[]) this.f652d.clone();
            return fVar;
        } catch (CloneNotSupportedException e2) {
            throw new AssertionError(e2);
        }
    }

    public E a(long j) {
        return a(j, null);
    }

    public E a(long j, E e2) {
        int a2 = c.a(this.f651c, this.e, j);
        if (a2 >= 0) {
            Object[] objArr = this.f652d;
            if (objArr[a2] != f649a) {
                return (E) objArr[a2];
            }
        }
        return e2;
    }

    public void b(long j) {
        Object[] objArr;
        Object obj;
        int a2 = c.a(this.f651c, this.e, j);
        if (a2 >= 0 && (objArr = this.f652d)[a2] != (obj = f649a)) {
            objArr[a2] = obj;
            this.f650b = true;
        }
    }

    private void d() {
        int i = this.e;
        long[] jArr = this.f651c;
        Object[] objArr = this.f652d;
        int i2 = 0;
        for (int i3 = 0; i3 < i; i3++) {
            Object obj = objArr[i3];
            if (obj != f649a) {
                if (i3 != i2) {
                    jArr[i2] = jArr[i3];
                    objArr[i2] = obj;
                    objArr[i3] = null;
                }
                i2++;
            }
        }
        this.f650b = false;
        this.e = i2;
    }

    public void b(long j, E e2) {
        int a2 = c.a(this.f651c, this.e, j);
        if (a2 >= 0) {
            this.f652d[a2] = e2;
            return;
        }
        int i = ~a2;
        if (i < this.e) {
            Object[] objArr = this.f652d;
            if (objArr[i] == f649a) {
                this.f651c[i] = j;
                objArr[i] = e2;
                return;
            }
        }
        if (this.f650b && this.e >= this.f651c.length) {
            d();
            i = ~c.a(this.f651c, this.e, j);
        }
        int i2 = this.e;
        if (i2 >= this.f651c.length) {
            int b2 = c.b(i2 + 1);
            long[] jArr = new long[b2];
            Object[] objArr2 = new Object[b2];
            long[] jArr2 = this.f651c;
            System.arraycopy(jArr2, 0, jArr, 0, jArr2.length);
            Object[] objArr3 = this.f652d;
            System.arraycopy(objArr3, 0, objArr2, 0, objArr3.length);
            this.f651c = jArr;
            this.f652d = objArr2;
        }
        int i3 = this.e;
        if (i3 - i != 0) {
            long[] jArr3 = this.f651c;
            int i4 = i + 1;
            System.arraycopy(jArr3, i, jArr3, i4, i3 - i);
            Object[] objArr4 = this.f652d;
            System.arraycopy(objArr4, i, objArr4, i4, this.e - i);
        }
        this.f651c[i] = j;
        this.f652d[i] = e2;
        this.e++;
    }

    public int b() {
        if (this.f650b) {
            d();
        }
        return this.e;
    }

    public long a(int i) {
        if (this.f650b) {
            d();
        }
        return this.f651c[i];
    }

    public E b(int i) {
        if (this.f650b) {
            d();
        }
        return (E) this.f652d[i];
    }

    public void c() {
        int i = this.e;
        Object[] objArr = this.f652d;
        for (int i2 = 0; i2 < i; i2++) {
            objArr[i2] = null;
        }
        this.e = 0;
        this.f650b = false;
    }

    public void c(long j, E e2) {
        int i = this.e;
        if (i == 0 || j > this.f651c[i - 1]) {
            if (this.f650b && this.e >= this.f651c.length) {
                d();
            }
            int i2 = this.e;
            if (i2 >= this.f651c.length) {
                int b2 = c.b(i2 + 1);
                long[] jArr = new long[b2];
                Object[] objArr = new Object[b2];
                long[] jArr2 = this.f651c;
                System.arraycopy(jArr2, 0, jArr, 0, jArr2.length);
                Object[] objArr2 = this.f652d;
                System.arraycopy(objArr2, 0, objArr, 0, objArr2.length);
                this.f651c = jArr;
                this.f652d = objArr;
            }
            this.f651c[i2] = j;
            this.f652d[i2] = e2;
            this.e = i2 + 1;
            return;
        }
        b(j, e2);
    }

    public String toString() {
        if (b() <= 0) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder(this.e * 28);
        sb.append('{');
        for (int i = 0; i < this.e; i++) {
            if (i > 0) {
                sb.append(", ");
            }
            sb.append(a(i));
            sb.append('=');
            E b2 = b(i);
            if (b2 != this) {
                sb.append((Object) b2);
            } else {
                sb.append("(this Map)");
            }
        }
        sb.append('}');
        return sb.toString();
    }
}
