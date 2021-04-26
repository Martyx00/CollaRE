package com.facebook.common.d;

import java.util.Arrays;

/* compiled from: Objects */
public final class h {
    public static boolean a(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    public static int a(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    public static a a(Object obj) {
        return new a(a(obj.getClass()));
    }

    private static String a(Class<?> cls) {
        String replaceAll = cls.getName().replaceAll("\\$[0-9]+", "\\$");
        int lastIndexOf = replaceAll.lastIndexOf(36);
        if (lastIndexOf == -1) {
            lastIndexOf = replaceAll.lastIndexOf(46);
        }
        return replaceAll.substring(lastIndexOf + 1);
    }

    /* compiled from: Objects */
    public static final class a {

        /* renamed from: a  reason: collision with root package name */
        private final String f1739a;

        /* renamed from: b  reason: collision with root package name */
        private C0042a f1740b;

        /* renamed from: c  reason: collision with root package name */
        private C0042a f1741c;

        /* renamed from: d  reason: collision with root package name */
        private boolean f1742d;

        private a(String str) {
            this.f1740b = new C0042a();
            this.f1741c = this.f1740b;
            this.f1742d = false;
            this.f1739a = (String) i.a(str);
        }

        public a a(String str, Object obj) {
            return b(str, obj);
        }

        public a a(String str, boolean z) {
            return b(str, String.valueOf(z));
        }

        public a a(String str, int i) {
            return b(str, String.valueOf(i));
        }

        public String toString() {
            boolean z = this.f1742d;
            String str = "";
            StringBuilder sb = new StringBuilder(32);
            sb.append(this.f1739a);
            sb.append('{');
            for (C0042a aVar = this.f1740b.f1745c; aVar != null; aVar = aVar.f1745c) {
                if (!z || aVar.f1744b != null) {
                    sb.append(str);
                    str = ", ";
                    if (aVar.f1743a != null) {
                        sb.append(aVar.f1743a);
                        sb.append('=');
                    }
                    sb.append(aVar.f1744b);
                }
            }
            sb.append('}');
            return sb.toString();
        }

        private C0042a a() {
            C0042a aVar = new C0042a();
            this.f1741c.f1745c = aVar;
            this.f1741c = aVar;
            return aVar;
        }

        private a b(String str, Object obj) {
            C0042a a2 = a();
            a2.f1744b = obj;
            a2.f1743a = (String) i.a(str);
            return this;
        }

        /* access modifiers changed from: private */
        /* renamed from: com.facebook.common.d.h$a$a  reason: collision with other inner class name */
        /* compiled from: Objects */
        public static final class C0042a {

            /* renamed from: a  reason: collision with root package name */
            String f1743a;

            /* renamed from: b  reason: collision with root package name */
            Object f1744b;

            /* renamed from: c  reason: collision with root package name */
            C0042a f1745c;

            private C0042a() {
            }
        }
    }
}
