package android.support.v4.util;

/* compiled from: SparseArrayCompat */
public class m<E> implements Cloneable {

    /* renamed from: a  reason: collision with root package name */
    private static final Object f672a = new Object();

    /* renamed from: b  reason: collision with root package name */
    private boolean f673b;

    /* renamed from: c  reason: collision with root package name */
    private int[] f674c;

    /* renamed from: d  reason: collision with root package name */
    private Object[] f675d;
    private int e;

    public m() {
        this(10);
    }

    public m(int i) {
        this.f673b = false;
        if (i == 0) {
            this.f674c = c.f644a;
            this.f675d = c.f646c;
        } else {
            int a2 = c.a(i);
            this.f674c = new int[a2];
            this.f675d = new Object[a2];
        }
        this.e = 0;
    }

    /* renamed from: a */
    public m<E> clone() {
        try {
            m<E> mVar = (m) super.clone();
            mVar.f674c = (int[]) this.f674c.clone();
            mVar.f675d = (Object[]) this.f675d.clone();
            return mVar;
        } catch (CloneNotSupportedException e2) {
            throw new AssertionError(e2);
        }
    }

    public E a(int i) {
        return a(i, null);
    }

    public E a(int i, E e2) {
        int a2 = c.a(this.f674c, this.e, i);
        if (a2 >= 0) {
            Object[] objArr = this.f675d;
            if (objArr[a2] != f672a) {
                return (E) objArr[a2];
            }
        }
        return e2;
    }

    public void b(int i) {
        Object[] objArr;
        Object obj;
        int a2 = c.a(this.f674c, this.e, i);
        if (a2 >= 0 && (objArr = this.f675d)[a2] != (obj = f672a)) {
            objArr[a2] = obj;
            this.f673b = true;
        }
    }

    public void c(int i) {
        b(i);
    }

    private void d() {
        int i = this.e;
        int[] iArr = this.f674c;
        Object[] objArr = this.f675d;
        int i2 = 0;
        for (int i3 = 0; i3 < i; i3++) {
            Object obj = objArr[i3];
            if (obj != f672a) {
                if (i3 != i2) {
                    iArr[i2] = iArr[i3];
                    objArr[i2] = obj;
                    objArr[i3] = null;
                }
                i2++;
            }
        }
        this.f673b = false;
        this.e = i2;
    }

    public void b(int i, E e2) {
        int a2 = c.a(this.f674c, this.e, i);
        if (a2 >= 0) {
            this.f675d[a2] = e2;
            return;
        }
        int i2 = ~a2;
        if (i2 < this.e) {
            Object[] objArr = this.f675d;
            if (objArr[i2] == f672a) {
                this.f674c[i2] = i;
                objArr[i2] = e2;
                return;
            }
        }
        if (this.f673b && this.e >= this.f674c.length) {
            d();
            i2 = ~c.a(this.f674c, this.e, i);
        }
        int i3 = this.e;
        if (i3 >= this.f674c.length) {
            int a3 = c.a(i3 + 1);
            int[] iArr = new int[a3];
            Object[] objArr2 = new Object[a3];
            int[] iArr2 = this.f674c;
            System.arraycopy(iArr2, 0, iArr, 0, iArr2.length);
            Object[] objArr3 = this.f675d;
            System.arraycopy(objArr3, 0, objArr2, 0, objArr3.length);
            this.f674c = iArr;
            this.f675d = objArr2;
        }
        int i4 = this.e;
        if (i4 - i2 != 0) {
            int[] iArr3 = this.f674c;
            int i5 = i2 + 1;
            System.arraycopy(iArr3, i2, iArr3, i5, i4 - i2);
            Object[] objArr4 = this.f675d;
            System.arraycopy(objArr4, i2, objArr4, i5, this.e - i2);
        }
        this.f674c[i2] = i;
        this.f675d[i2] = e2;
        this.e++;
    }

    public int b() {
        if (this.f673b) {
            d();
        }
        return this.e;
    }

    public int d(int i) {
        if (this.f673b) {
            d();
        }
        return this.f674c[i];
    }

    public E e(int i) {
        if (this.f673b) {
            d();
        }
        return (E) this.f675d[i];
    }

    public int f(int i) {
        if (this.f673b) {
            d();
        }
        return c.a(this.f674c, this.e, i);
    }

    public void c() {
        int i = this.e;
        Object[] objArr = this.f675d;
        for (int i2 = 0; i2 < i; i2++) {
            objArr[i2] = null;
        }
        this.e = 0;
        this.f673b = false;
    }

    public void c(int i, E e2) {
        int i2 = this.e;
        if (i2 == 0 || i > this.f674c[i2 - 1]) {
            if (this.f673b && this.e >= this.f674c.length) {
                d();
            }
            int i3 = this.e;
            if (i3 >= this.f674c.length) {
                int a2 = c.a(i3 + 1);
                int[] iArr = new int[a2];
                Object[] objArr = new Object[a2];
                int[] iArr2 = this.f674c;
                System.arraycopy(iArr2, 0, iArr, 0, iArr2.length);
                Object[] objArr2 = this.f675d;
                System.arraycopy(objArr2, 0, objArr, 0, objArr2.length);
                this.f674c = iArr;
                this.f675d = objArr;
            }
            this.f674c[i3] = i;
            this.f675d[i3] = e2;
            this.e = i3 + 1;
            return;
        }
        b(i, e2);
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
            sb.append(d(i));
            sb.append('=');
            E e2 = e(i);
            if (e2 != this) {
                sb.append((Object) e2);
            } else {
                sb.append("(this Map)");
            }
        }
        sb.append('}');
        return sb.toString();
    }
}
