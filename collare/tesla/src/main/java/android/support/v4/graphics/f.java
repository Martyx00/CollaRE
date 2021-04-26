package android.support.v4.graphics;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.graphics.fonts.FontVariationAxis;
import android.support.v4.content.a.c;
import android.util.Log;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;

/* compiled from: TypefaceCompatApi26Impl */
public class f extends d {

    /* renamed from: a  reason: collision with root package name */
    protected final Class f572a;

    /* renamed from: b  reason: collision with root package name */
    protected final Constructor f573b;

    /* renamed from: c  reason: collision with root package name */
    protected final Method f574c;

    /* renamed from: d  reason: collision with root package name */
    protected final Method f575d;
    protected final Method e;
    protected final Method f;
    protected final Method g;

    public f() {
        Method method;
        Method method2;
        Method method3;
        Method method4;
        Constructor constructor;
        Method method5;
        Class cls = null;
        try {
            Class a2 = a();
            constructor = a(a2);
            method4 = b(a2);
            method3 = c(a2);
            method2 = d(a2);
            method = e(a2);
            method5 = f(a2);
            cls = a2;
        } catch (ClassNotFoundException | NoSuchMethodException e2) {
            Log.e("TypefaceCompatApi26Impl", "Unable to collect necessary methods for class " + e2.getClass().getName(), e2);
            method5 = null;
            constructor = null;
            method4 = null;
            method3 = null;
            method2 = null;
            method = null;
        }
        this.f572a = cls;
        this.f573b = constructor;
        this.f574c = method4;
        this.f575d = method3;
        this.e = method2;
        this.f = method;
        this.g = method5;
    }

    private boolean b() {
        if (this.f574c == null) {
            Log.w("TypefaceCompatApi26Impl", "Unable to collect necessary private methods. Fallback to legacy implementation.");
        }
        return this.f574c != null;
    }

    private Object c() {
        try {
            return this.f573b.newInstance(new Object[0]);
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException e2) {
            throw new RuntimeException(e2);
        }
    }

    private boolean a(Context context, Object obj, String str, int i, int i2, int i3, FontVariationAxis[] fontVariationAxisArr) {
        try {
            return ((Boolean) this.f574c.invoke(obj, context.getAssets(), str, 0, false, Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3), fontVariationAxisArr)).booleanValue();
        } catch (IllegalAccessException | InvocationTargetException e2) {
            throw new RuntimeException(e2);
        }
    }

    private boolean a(Object obj, ByteBuffer byteBuffer, int i, int i2, int i3) {
        try {
            return ((Boolean) this.f575d.invoke(obj, byteBuffer, Integer.valueOf(i), null, Integer.valueOf(i2), Integer.valueOf(i3))).booleanValue();
        } catch (IllegalAccessException | InvocationTargetException e2) {
            throw new RuntimeException(e2);
        }
    }

    /* access modifiers changed from: protected */
    public Typeface a(Object obj) {
        try {
            Object newInstance = Array.newInstance(this.f572a, 1);
            Array.set(newInstance, 0, obj);
            return (Typeface) this.g.invoke(null, newInstance, -1, -1);
        } catch (IllegalAccessException | InvocationTargetException e2) {
            throw new RuntimeException(e2);
        }
    }

    private boolean b(Object obj) {
        try {
            return ((Boolean) this.e.invoke(obj, new Object[0])).booleanValue();
        } catch (IllegalAccessException | InvocationTargetException e2) {
            throw new RuntimeException(e2);
        }
    }

    private void c(Object obj) {
        try {
            this.f.invoke(obj, new Object[0]);
        } catch (IllegalAccessException | InvocationTargetException e2) {
            throw new RuntimeException(e2);
        }
    }

    @Override // android.support.v4.graphics.h
    public Typeface a(Context context, c.b bVar, Resources resources, int i) {
        if (!b()) {
            return super.a(context, bVar, resources, i);
        }
        Object c2 = c();
        c.C0006c[] a2 = bVar.a();
        for (c.C0006c cVar : a2) {
            if (!a(context, c2, cVar.a(), cVar.e(), cVar.b(), cVar.c() ? 1 : 0, FontVariationAxis.fromFontVariationSettings(cVar.d()))) {
                c(c2);
                return null;
            }
        }
        if (!b(c2)) {
            return null;
        }
        return a(c2);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0049, code lost:
        r13 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x004a, code lost:
        r14 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x004e, code lost:
        r14 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x004f, code lost:
        r14 = r13;
        r13 = r14;
     */
    @Override // android.support.v4.graphics.h, android.support.v4.graphics.d
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public android.graphics.Typeface a(android.content.Context r12, android.os.CancellationSignal r13, android.support.v4.e.b.C0008b[] r14, int r15) {
        /*
        // Method dump skipped, instructions count: 178
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.graphics.f.a(android.content.Context, android.os.CancellationSignal, android.support.v4.e.b$b[], int):android.graphics.Typeface");
    }

    @Override // android.support.v4.graphics.h
    public Typeface a(Context context, Resources resources, int i, String str, int i2) {
        if (!b()) {
            return super.a(context, resources, i, str, i2);
        }
        Object c2 = c();
        if (!a(context, c2, str, 0, -1, -1, null)) {
            c(c2);
            return null;
        } else if (!b(c2)) {
            return null;
        } else {
            return a(c2);
        }
    }

    /* access modifiers changed from: protected */
    public Class a() {
        return Class.forName("android.graphics.FontFamily");
    }

    /* access modifiers changed from: protected */
    public Constructor a(Class cls) {
        return cls.getConstructor(new Class[0]);
    }

    /* access modifiers changed from: protected */
    public Method b(Class cls) {
        return cls.getMethod("addFontFromAssetManager", AssetManager.class, String.class, Integer.TYPE, Boolean.TYPE, Integer.TYPE, Integer.TYPE, Integer.TYPE, FontVariationAxis[].class);
    }

    /* access modifiers changed from: protected */
    public Method c(Class cls) {
        return cls.getMethod("addFontFromBuffer", ByteBuffer.class, Integer.TYPE, FontVariationAxis[].class, Integer.TYPE, Integer.TYPE);
    }

    /* access modifiers changed from: protected */
    public Method d(Class cls) {
        return cls.getMethod("freeze", new Class[0]);
    }

    /* access modifiers changed from: protected */
    public Method e(Class cls) {
        return cls.getMethod("abortCreation", new Class[0]);
    }

    /* access modifiers changed from: protected */
    public Method f(Class cls) {
        Method declaredMethod = Typeface.class.getDeclaredMethod("createFromFamiliesWithDefault", Array.newInstance(cls, 1).getClass(), Integer.TYPE, Integer.TYPE);
        declaredMethod.setAccessible(true);
        return declaredMethod;
    }
}
