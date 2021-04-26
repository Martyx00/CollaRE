package android.support.v4.util;

public final class Pools {

    public static class SimplePool<T> {

        /* renamed from: a  reason: collision with root package name */
        private final Object[] f634a;

        /* renamed from: b  reason: collision with root package name */
        private int f635b;

        public SimplePool(int i) {
            if (i > 0) {
                this.f634a = new Object[i];
                return;
            }
            throw new IllegalArgumentException("The max pool size must be > 0");
        }

        public T a() {
            int i = this.f635b;
            if (i <= 0) {
                return null;
            }
            int i2 = i - 1;
            Object[] objArr = this.f634a;
            T t = (T) objArr[i2];
            objArr[i2] = null;
            this.f635b = i - 1;
            return t;
        }

        public boolean a(T t) {
            if (!b(t)) {
                int i = this.f635b;
                Object[] objArr = this.f634a;
                if (i >= objArr.length) {
                    return false;
                }
                objArr[i] = t;
                this.f635b = i + 1;
                return true;
            }
            throw new IllegalStateException("Already in the pool!");
        }

        private boolean b(T t) {
            for (int i = 0; i < this.f635b; i++) {
                if (this.f634a[i] == t) {
                    return true;
                }
            }
            return false;
        }
    }

    public static class a<T> extends SimplePool<T> {

        /* renamed from: a  reason: collision with root package name */
        private final Object f636a = new Object();

        public a(int i) {
            super(i);
        }

        @Override // android.support.v4.util.Pools.SimplePool
        public T a() {
            T t;
            synchronized (this.f636a) {
                t = (T) super.a();
            }
            return t;
        }

        @Override // android.support.v4.util.Pools.SimplePool
        public boolean a(T t) {
            boolean a2;
            synchronized (this.f636a) {
                a2 = super.a(t);
            }
            return a2;
        }
    }
}
