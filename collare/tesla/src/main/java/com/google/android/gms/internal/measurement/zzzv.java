package com.google.android.gms.internal.measurement;

import com.google.android.gms.internal.measurement.zzzv;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public abstract class zzzv<MessageType extends zzzv<MessageType, BuilderType>, BuilderType> extends zzyz<MessageType, BuilderType> {
    private static Map<Object, zzzv<?, ?>> zzbuh = new ConcurrentHashMap();
    protected zzabp zzbuf = zzabp.zzvf();
    private int zzbug = -1;

    public static abstract class zza<MessageType extends zza<MessageType, BuilderType>, BuilderType> extends zzzv<MessageType, BuilderType> implements zzaar {
        protected zzzr<Object> zzbui = zzzr.zztx();
    }

    /* JADX INFO: Failed to restore enum class, 'enum' modifier removed */
    public static final class zzb {
        private static final int zzbuj = 1;
        private static final int zzbuk = 2;
        public static final int zzbul = 3;
        private static final int zzbum = 4;
        private static final int zzbun = 5;
        public static final int zzbuo = 6;
        public static final int zzbup = 7;
        private static final /* synthetic */ int[] zzbuq = {1, 2, 3, 4, 5, 6, 7};
        public static final int zzbur = 1;
        private static final int zzbus = 2;
        private static final /* synthetic */ int[] zzbut = {1, 2};
        private static final int zzbuu = 1;
        private static final int zzbuv = 2;
        private static final /* synthetic */ int[] zzbuw = {1, 2};
    }

    static Object zza(Method method, Object obj, Object... objArr) {
        try {
            return method.invoke(obj, objArr);
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Couldn't use Java reflection to implement protocol message reflection.", e);
        } catch (InvocationTargetException e2) {
            Throwable cause = e2.getCause();
            if (cause instanceof RuntimeException) {
                throw ((RuntimeException) cause);
            } else if (cause instanceof Error) {
                throw ((Error) cause);
            } else {
                throw new RuntimeException("Unexpected exception thrown by generated accessor method.", cause);
            }
        }
    }

    static <T extends zzzv<?, ?>> T zzf(Class<T> cls) {
        T t = (T) zzbuh.get(cls);
        if (t == null) {
            try {
                Class.forName(cls.getName(), true, cls.getClassLoader());
                t = (T) zzbuh.get(cls);
            } catch (ClassNotFoundException e) {
                throw new IllegalStateException("Class initialization cannot fail.", e);
            }
        }
        if (t != null) {
            return t;
        }
        String valueOf = String.valueOf(cls.getName());
        throw new IllegalStateException(valueOf.length() != 0 ? "Unable to get default instance for: ".concat(valueOf) : new String("Unable to get default instance for: "));
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!((zzzv) zza(6, (Object) null, (Object) null)).getClass().isInstance(obj)) {
            return false;
        }
        return zzaay.zzus().zzt(this).equals(this, (zzzv) obj);
    }

    public int hashCode() {
        if (this.zzbta != 0) {
            return this.zzbta;
        }
        this.zzbta = zzaay.zzus().zzt(this).hashCode(this);
        return this.zzbta;
    }

    public String toString() {
        return zzaas.zza(this, super.toString());
    }

    /* access modifiers changed from: protected */
    public abstract Object zza(int i, Object obj, Object obj2);
}
