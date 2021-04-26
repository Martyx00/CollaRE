package ezvcard.parameter;

import ezvcard.parameter.VCardParameter;
import ezvcard.util.a;

/* compiled from: VCardParameterCaseClasses */
public class d<T extends VCardParameter> extends a<T, String> {
    public d(Class<T> cls) {
        super(cls);
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Can't wrap try/catch for region: R(3:4|5|6) */
    /* JADX WARNING: Code restructure failed: missing block: B:5:?, code lost:
        r2 = r6.f5829a.getDeclaredConstructor(java.lang.String.class, ezvcard.VCardVersion[].class);
        r2.setAccessible(true);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:6:0x003e, code lost:
        return (T) ((ezvcard.parameter.VCardParameter) r2.newInstance(r7, new ezvcard.VCardVersion[0]));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x003f, code lost:
        r7 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0045, code lost:
        throw new java.lang.RuntimeException(r7);
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:4:0x001c */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public T a(java.lang.String r7) {
        /*
            r6 = this;
            r0 = 0
            r1 = 1
            java.lang.Class r2 = r6.f5829a     // Catch:{ Exception -> 0x001c }
            java.lang.Class[] r3 = new java.lang.Class[r1]     // Catch:{ Exception -> 0x001c }
            java.lang.Class<java.lang.String> r4 = java.lang.String.class
            r3[r0] = r4     // Catch:{ Exception -> 0x001c }
            java.lang.reflect.Constructor r2 = r2.getDeclaredConstructor(r3)     // Catch:{ Exception -> 0x001c }
            r2.setAccessible(r1)     // Catch:{ Exception -> 0x001c }
            java.lang.Object[] r3 = new java.lang.Object[r1]     // Catch:{ Exception -> 0x001c }
            r3[r0] = r7     // Catch:{ Exception -> 0x001c }
            java.lang.Object r2 = r2.newInstance(r3)     // Catch:{ Exception -> 0x001c }
            ezvcard.parameter.VCardParameter r2 = (ezvcard.parameter.VCardParameter) r2     // Catch:{ Exception -> 0x001c }
            return r2
        L_0x001c:
            java.lang.Class r2 = r6.f5829a     // Catch:{ Exception -> 0x003f }
            r3 = 2
            java.lang.Class[] r4 = new java.lang.Class[r3]     // Catch:{ Exception -> 0x003f }
            java.lang.Class<java.lang.String> r5 = java.lang.String.class
            r4[r0] = r5     // Catch:{ Exception -> 0x003f }
            java.lang.Class<ezvcard.VCardVersion[]> r5 = ezvcard.VCardVersion[].class
            r4[r1] = r5     // Catch:{ Exception -> 0x003f }
            java.lang.reflect.Constructor r2 = r2.getDeclaredConstructor(r4)     // Catch:{ Exception -> 0x003f }
            r2.setAccessible(r1)     // Catch:{ Exception -> 0x003f }
            java.lang.Object[] r3 = new java.lang.Object[r3]     // Catch:{ Exception -> 0x003f }
            r3[r0] = r7     // Catch:{ Exception -> 0x003f }
            ezvcard.VCardVersion[] r7 = new ezvcard.VCardVersion[r0]     // Catch:{ Exception -> 0x003f }
            r3[r1] = r7     // Catch:{ Exception -> 0x003f }
            java.lang.Object r7 = r2.newInstance(r3)     // Catch:{ Exception -> 0x003f }
            ezvcard.parameter.VCardParameter r7 = (ezvcard.parameter.VCardParameter) r7     // Catch:{ Exception -> 0x003f }
            return r7
        L_0x003f:
            r7 = move-exception
            java.lang.RuntimeException r0 = new java.lang.RuntimeException
            r0.<init>(r7)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: ezvcard.parameter.d.a(java.lang.String):ezvcard.parameter.VCardParameter");
    }

    /* access modifiers changed from: protected */
    public boolean a(T t, String str) {
        return t.c().equalsIgnoreCase(str);
    }
}
