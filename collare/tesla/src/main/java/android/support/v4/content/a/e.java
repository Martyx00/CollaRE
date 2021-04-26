package android.support.v4.content.a;

/* compiled from: GrowingArrayUtils */
final class e {

    /* renamed from: a  reason: collision with root package name */
    static final /* synthetic */ boolean f358a = (!e.class.desiredAssertionStatus());

    public static int a(int i) {
        if (i <= 4) {
            return 8;
        }
        return i * 2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v5, types: [java.lang.Object[], java.lang.Object] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static <T> T[] a(T[] r2, int r3, T r4) {
        /*
            boolean r0 = android.support.v4.content.a.e.f358a
            if (r0 != 0) goto L_0x000e
            int r0 = r2.length
            if (r3 > r0) goto L_0x0008
            goto L_0x000e
        L_0x0008:
            java.lang.AssertionError r2 = new java.lang.AssertionError
            r2.<init>()
            throw r2
        L_0x000e:
            int r0 = r3 + 1
            int r1 = r2.length
            if (r0 <= r1) goto L_0x002a
            java.lang.Class r0 = r2.getClass()
            java.lang.Class r0 = r0.getComponentType()
            int r1 = a(r3)
            java.lang.Object r0 = java.lang.reflect.Array.newInstance(r0, r1)
            java.lang.Object[] r0 = (java.lang.Object[]) r0
            r1 = 0
            java.lang.System.arraycopy(r2, r1, r0, r1, r3)
            r2 = r0
        L_0x002a:
            r2[r3] = r4
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.content.a.e.a(java.lang.Object[], int, java.lang.Object):java.lang.Object[]");
    }

    public static int[] a(int[] iArr, int i, int i2) {
        if (f358a || i <= iArr.length) {
            if (i + 1 > iArr.length) {
                int[] iArr2 = new int[a(i)];
                System.arraycopy(iArr, 0, iArr2, 0, i);
                iArr = iArr2;
            }
            iArr[i] = i2;
            return iArr;
        }
        throw new AssertionError();
    }

    private e() {
    }
}
