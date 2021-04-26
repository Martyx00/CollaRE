package com.facebook.common.d;

/* compiled from: Suppliers */
public class m {

    /* renamed from: a  reason: collision with root package name */
    public static final l<Boolean> f1746a = new l<Boolean>() {
        /* class com.facebook.common.d.m.AnonymousClass2 */

        /* renamed from: a */
        public Boolean b() {
            return true;
        }
    };

    /* renamed from: b  reason: collision with root package name */
    public static final l<Boolean> f1747b = new l<Boolean>() {
        /* class com.facebook.common.d.m.AnonymousClass3 */

        /* renamed from: a */
        public Boolean b() {
            return false;
        }
    };

    public static <T> l<T> a(final T t) {
        return new l<T>() {
            /* class com.facebook.common.d.m.AnonymousClass1 */

            @Override // com.facebook.common.d.l
            public T b() {
                return (T) t;
            }
        };
    }
}
