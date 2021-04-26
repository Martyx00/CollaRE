package com.google.protobuf;

import com.google.firebase.analytics.FirebaseAnalytics;
import java.lang.reflect.Field;
import java.nio.Buffer;
import java.security.AccessController;
import java.security.PrivilegedExceptionAction;
import java.util.logging.Level;
import java.util.logging.Logger;
import sun.misc.Unsafe;

/* access modifiers changed from: package-private */
/* compiled from: UnsafeUtil */
public final class ay {

    /* renamed from: a  reason: collision with root package name */
    private static final Logger f4153a = Logger.getLogger(ay.class.getName());

    /* renamed from: b  reason: collision with root package name */
    private static final Unsafe f4154b = c();

    /* renamed from: c  reason: collision with root package name */
    private static final b f4155c = d();

    /* renamed from: d  reason: collision with root package name */
    private static final boolean f4156d = f();
    private static final boolean e = e();
    private static final long f = ((long) a(byte[].class));
    private static final long g = ((long) a(boolean[].class));
    private static final long h = ((long) b(boolean[].class));
    private static final long i = ((long) a(int[].class));
    private static final long j = ((long) b(int[].class));
    private static final long k = ((long) a(long[].class));
    private static final long l = ((long) b(long[].class));
    private static final long m = ((long) a(float[].class));
    private static final long n = ((long) b(float[].class));
    private static final long o = ((long) a(double[].class));
    private static final long p = ((long) b(double[].class));
    private static final long q = ((long) a(Object[].class));
    private static final long r = ((long) b(Object[].class));
    private static final long s = a(g());
    private static final long t = a(h());

    private ay() {
    }

    static boolean a() {
        return e;
    }

    static boolean b() {
        return f4156d;
    }

    private static int a(Class<?> cls) {
        if (e) {
            return f4155c.a(cls);
        }
        return -1;
    }

    private static int b(Class<?> cls) {
        if (e) {
            return f4155c.b(cls);
        }
        return -1;
    }

    static byte a(byte[] bArr, long j2) {
        return f4155c.a(bArr, f + j2);
    }

    static void a(byte[] bArr, long j2, byte b2) {
        f4155c.a(bArr, f + j2, b2);
    }

    static Unsafe c() {
        try {
            return (Unsafe) AccessController.doPrivileged(new PrivilegedExceptionAction<Unsafe>() {
                /* class com.google.protobuf.ay.AnonymousClass1 */

                /* renamed from: a */
                public Unsafe run() {
                    Field[] declaredFields = Unsafe.class.getDeclaredFields();
                    for (Field field : declaredFields) {
                        field.setAccessible(true);
                        Object obj = field.get(null);
                        if (Unsafe.class.isInstance(obj)) {
                            return (Unsafe) Unsafe.class.cast(obj);
                        }
                    }
                    return null;
                }
            });
        } catch (Throwable unused) {
            return null;
        }
    }

    private static b d() {
        Unsafe unsafe = f4154b;
        if (unsafe == null) {
            return null;
        }
        return new a(unsafe);
    }

    private static boolean e() {
        Unsafe unsafe = f4154b;
        if (unsafe == null) {
            return false;
        }
        try {
            Class<?> cls = unsafe.getClass();
            cls.getMethod("objectFieldOffset", Field.class);
            cls.getMethod("arrayBaseOffset", Class.class);
            cls.getMethod("arrayIndexScale", Class.class);
            cls.getMethod("getInt", Object.class, Long.TYPE);
            cls.getMethod("putInt", Object.class, Long.TYPE, Integer.TYPE);
            cls.getMethod("getLong", Object.class, Long.TYPE);
            cls.getMethod("putLong", Object.class, Long.TYPE, Long.TYPE);
            cls.getMethod("getObject", Object.class, Long.TYPE);
            cls.getMethod("putObject", Object.class, Long.TYPE, Object.class);
            cls.getMethod("getByte", Object.class, Long.TYPE);
            cls.getMethod("putByte", Object.class, Long.TYPE, Byte.TYPE);
            cls.getMethod("getBoolean", Object.class, Long.TYPE);
            cls.getMethod("putBoolean", Object.class, Long.TYPE, Boolean.TYPE);
            cls.getMethod("getFloat", Object.class, Long.TYPE);
            cls.getMethod("putFloat", Object.class, Long.TYPE, Float.TYPE);
            cls.getMethod("getDouble", Object.class, Long.TYPE);
            cls.getMethod("putDouble", Object.class, Long.TYPE, Double.TYPE);
            return true;
        } catch (Throwable th) {
            Logger logger = f4153a;
            Level level = Level.WARNING;
            logger.log(level, "platform method missing - proto runtime falling back to safer methods: " + th);
            return false;
        }
    }

    private static boolean f() {
        Unsafe unsafe = f4154b;
        if (unsafe == null) {
            return false;
        }
        try {
            Class<?> cls = unsafe.getClass();
            cls.getMethod("objectFieldOffset", Field.class);
            cls.getMethod("getLong", Object.class, Long.TYPE);
            if (g() == null) {
                return false;
            }
            cls.getMethod("getByte", Long.TYPE);
            cls.getMethod("putByte", Long.TYPE, Byte.TYPE);
            cls.getMethod("getInt", Long.TYPE);
            cls.getMethod("putInt", Long.TYPE, Integer.TYPE);
            cls.getMethod("getLong", Long.TYPE);
            cls.getMethod("putLong", Long.TYPE, Long.TYPE);
            cls.getMethod("copyMemory", Long.TYPE, Long.TYPE, Long.TYPE);
            cls.getMethod("copyMemory", Object.class, Long.TYPE, Object.class, Long.TYPE, Long.TYPE);
            return true;
        } catch (Throwable th) {
            Logger logger = f4153a;
            Level level = Level.WARNING;
            logger.log(level, "platform method missing - proto runtime falling back to safer methods: " + th);
            return false;
        }
    }

    private static Field g() {
        Field a2 = a(Buffer.class, "address");
        if (a2 == null || a2.getType() != Long.TYPE) {
            return null;
        }
        return a2;
    }

    private static Field h() {
        Field a2 = a(String.class, FirebaseAnalytics.b.VALUE);
        if (a2 == null || a2.getType() != char[].class) {
            return null;
        }
        return a2;
    }

    private static long a(Field field) {
        b bVar;
        if (field == null || (bVar = f4155c) == null) {
            return -1;
        }
        return bVar.a(field);
    }

    private static Field a(Class<?> cls, String str) {
        try {
            Field declaredField = cls.getDeclaredField(str);
            declaredField.setAccessible(true);
            return declaredField;
        } catch (Throwable unused) {
            return null;
        }
    }

    /* access modifiers changed from: private */
    /* compiled from: UnsafeUtil */
    public static abstract class b {

        /* renamed from: a  reason: collision with root package name */
        Unsafe f4157a;

        public abstract byte a(Object obj, long j);

        public abstract void a(Object obj, long j, byte b2);

        b(Unsafe unsafe) {
            this.f4157a = unsafe;
        }

        public final long a(Field field) {
            return this.f4157a.objectFieldOffset(field);
        }

        public final int a(Class<?> cls) {
            return this.f4157a.arrayBaseOffset(cls);
        }

        public final int b(Class<?> cls) {
            return this.f4157a.arrayIndexScale(cls);
        }
    }

    /* access modifiers changed from: private */
    /* compiled from: UnsafeUtil */
    public static final class a extends b {
        a(Unsafe unsafe) {
            super(unsafe);
        }

        @Override // com.google.protobuf.ay.b
        public byte a(Object obj, long j) {
            return this.f4157a.getByte(obj, j);
        }

        @Override // com.google.protobuf.ay.b
        public void a(Object obj, long j, byte b2) {
            this.f4157a.putByte(obj, j, b2);
        }
    }
}
