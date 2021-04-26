package com.google.android.gms.internal.measurement;

import java.lang.reflect.Field;
import java.nio.Buffer;
import java.nio.ByteOrder;
import java.security.AccessController;
import java.util.logging.Level;
import java.util.logging.Logger;
import sun.misc.Unsafe;

final class zzabr {
    private static final Logger logger = Logger.getLogger(zzabr.class.getName());
    private static final Class<?> zzbtc = zzza.zztl();
    private static final Unsafe zzbvq = zzvg();
    private static final boolean zzbwu = zzk(Long.TYPE);
    private static final boolean zzbwv = zzk(Integer.TYPE);
    private static final zzd zzbww;
    private static final boolean zzbwx = zzvi();
    private static final boolean zzbwy = zzvh();
    private static final long zzbwz = ((long) zzi(byte[].class));
    private static final long zzbxa = ((long) zzi(boolean[].class));
    private static final long zzbxb = ((long) zzj(boolean[].class));
    private static final long zzbxc = ((long) zzi(int[].class));
    private static final long zzbxd = ((long) zzj(int[].class));
    private static final long zzbxe = ((long) zzi(long[].class));
    private static final long zzbxf = ((long) zzj(long[].class));
    private static final long zzbxg = ((long) zzi(float[].class));
    private static final long zzbxh = ((long) zzj(float[].class));
    private static final long zzbxi = ((long) zzi(double[].class));
    private static final long zzbxj = ((long) zzj(double[].class));
    private static final long zzbxk = ((long) zzi(Object[].class));
    private static final long zzbxl = ((long) zzj(Object[].class));
    private static final long zzbxm = zza(zzvj());
    private static final long zzbxn;
    private static final boolean zzbxo = (ByteOrder.nativeOrder() != ByteOrder.BIG_ENDIAN);

    static final class zza extends zzd {
        zza(Unsafe unsafe) {
            super(unsafe);
        }
    }

    static final class zzb extends zzd {
        zzb(Unsafe unsafe) {
            super(unsafe);
        }
    }

    static final class zzc extends zzd {
        zzc(Unsafe unsafe) {
            super(unsafe);
        }
    }

    /* access modifiers changed from: package-private */
    public static abstract class zzd {
        Unsafe zzbxp;

        zzd(Unsafe unsafe) {
            this.zzbxp = unsafe;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:19:0x0103  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0105  */
    static {
        /*
        // Method dump skipped, instructions count: 265
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzabr.<clinit>():void");
    }

    private zzabr() {
    }

    private static long zza(Field field) {
        zzd zzd2;
        if (field == null || (zzd2 = zzbww) == null) {
            return -1;
        }
        return zzd2.zzbxp.objectFieldOffset(field);
    }

    private static Field zza(Class<?> cls, String str) {
        try {
            Field declaredField = cls.getDeclaredField(str);
            declaredField.setAccessible(true);
            return declaredField;
        } catch (Throwable unused) {
            return null;
        }
    }

    private static int zzi(Class<?> cls) {
        if (zzbwy) {
            return zzbww.zzbxp.arrayBaseOffset(cls);
        }
        return -1;
    }

    private static int zzj(Class<?> cls) {
        if (zzbwy) {
            return zzbww.zzbxp.arrayIndexScale(cls);
        }
        return -1;
    }

    private static boolean zzk(Class<?> cls) {
        if (!zzza.zztk()) {
            return false;
        }
        try {
            Class<?> cls2 = zzbtc;
            cls2.getMethod("peekLong", cls, Boolean.TYPE);
            cls2.getMethod("pokeLong", cls, Long.TYPE, Boolean.TYPE);
            cls2.getMethod("pokeInt", cls, Integer.TYPE, Boolean.TYPE);
            cls2.getMethod("peekInt", cls, Boolean.TYPE);
            cls2.getMethod("pokeByte", cls, Byte.TYPE);
            cls2.getMethod("peekByte", cls);
            cls2.getMethod("pokeByteArray", cls, byte[].class, Integer.TYPE, Integer.TYPE);
            cls2.getMethod("peekByteArray", cls, byte[].class, Integer.TYPE, Integer.TYPE);
            return true;
        } catch (Throwable unused) {
            return false;
        }
    }

    static Unsafe zzvg() {
        try {
            return (Unsafe) AccessController.doPrivileged(new zzabs());
        } catch (Throwable unused) {
            return null;
        }
    }

    private static boolean zzvh() {
        Unsafe unsafe = zzbvq;
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
            if (zzza.zztk()) {
                return true;
            }
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
            Logger logger2 = logger;
            Level level = Level.WARNING;
            String valueOf = String.valueOf(th);
            StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 71);
            sb.append("platform method missing - proto runtime falling back to safer methods: ");
            sb.append(valueOf);
            logger2.logp(level, "com.google.protobuf.UnsafeUtil", "supportsUnsafeArrayOperations", sb.toString());
            return false;
        }
    }

    private static boolean zzvi() {
        Unsafe unsafe = zzbvq;
        if (unsafe == null) {
            return false;
        }
        try {
            Class<?> cls = unsafe.getClass();
            cls.getMethod("objectFieldOffset", Field.class);
            cls.getMethod("getLong", Object.class, Long.TYPE);
            if (zzvj() == null) {
                return false;
            }
            if (zzza.zztk()) {
                return true;
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
            Logger logger2 = logger;
            Level level = Level.WARNING;
            String valueOf = String.valueOf(th);
            StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 71);
            sb.append("platform method missing - proto runtime falling back to safer methods: ");
            sb.append(valueOf);
            logger2.logp(level, "com.google.protobuf.UnsafeUtil", "supportsUnsafeByteBufferOperations", sb.toString());
            return false;
        }
    }

    private static Field zzvj() {
        Field zza2;
        if (zzza.zztk() && (zza2 = zza(Buffer.class, "effectiveDirectAddress")) != null) {
            return zza2;
        }
        Field zza3 = zza(Buffer.class, "address");
        if (zza3 == null || zza3.getType() != Long.TYPE) {
            return null;
        }
        return zza3;
    }
}
