package android.support.v4.graphics;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.CancellationSignal;
import android.support.v4.content.a.c;
import android.support.v4.e.b;
import android.support.v4.util.l;
import android.util.Log;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.util.List;

/* compiled from: TypefaceCompatApi24Impl */
class e extends h {

    /* renamed from: a  reason: collision with root package name */
    private static final Class f568a;

    /* renamed from: b  reason: collision with root package name */
    private static final Constructor f569b;

    /* renamed from: c  reason: collision with root package name */
    private static final Method f570c;

    /* renamed from: d  reason: collision with root package name */
    private static final Method f571d;

    e() {
    }

    static {
        Method method;
        Method method2;
        Class<?> cls;
        Constructor<?> constructor = null;
        try {
            cls = Class.forName("android.graphics.FontFamily");
            Constructor<?> constructor2 = cls.getConstructor(new Class[0]);
            method = cls.getMethod("addFontWeightStyle", ByteBuffer.class, Integer.TYPE, List.class, Integer.TYPE, Boolean.TYPE);
            method2 = Typeface.class.getMethod("createFromFamiliesWithDefault", Array.newInstance(cls, 1).getClass());
            constructor = constructor2;
        } catch (ClassNotFoundException | NoSuchMethodException e) {
            Log.e("TypefaceCompatApi24Impl", e.getClass().getName(), e);
            cls = null;
            method2 = null;
            method = null;
        }
        f569b = constructor;
        f568a = cls;
        f570c = method;
        f571d = method2;
    }

    public static boolean a() {
        if (f570c == null) {
            Log.w("TypefaceCompatApi24Impl", "Unable to collect necessary private methods.Fallback to legacy implementation.");
        }
        return f570c != null;
    }

    private static Object b() {
        try {
            return f569b.newInstance(new Object[0]);
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    private static boolean a(Object obj, ByteBuffer byteBuffer, int i, int i2, boolean z) {
        try {
            return ((Boolean) f570c.invoke(obj, byteBuffer, Integer.valueOf(i), null, Integer.valueOf(i2), Boolean.valueOf(z))).booleanValue();
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    private static Typeface a(Object obj) {
        try {
            Object newInstance = Array.newInstance(f568a, 1);
            Array.set(newInstance, 0, obj);
            return (Typeface) f571d.invoke(null, newInstance);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    @Override // android.support.v4.graphics.h
    public Typeface a(Context context, CancellationSignal cancellationSignal, b.C0008b[] bVarArr, int i) {
        Object b2 = b();
        l lVar = new l();
        for (b.C0008b bVar : bVarArr) {
            Uri a2 = bVar.a();
            ByteBuffer byteBuffer = (ByteBuffer) lVar.get(a2);
            if (byteBuffer == null) {
                byteBuffer = i.a(context, cancellationSignal, a2);
                lVar.put(a2, byteBuffer);
            }
            if (!a(b2, byteBuffer, bVar.b(), bVar.c(), bVar.d())) {
                return null;
            }
        }
        return Typeface.create(a(b2), i);
    }

    @Override // android.support.v4.graphics.h
    public Typeface a(Context context, c.b bVar, Resources resources, int i) {
        Object b2 = b();
        c.C0006c[] a2 = bVar.a();
        for (c.C0006c cVar : a2) {
            ByteBuffer a3 = i.a(context, resources, cVar.f());
            if (a3 == null || !a(b2, a3, cVar.e(), cVar.b(), cVar.c())) {
                return null;
            }
        }
        return a(b2);
    }
}
