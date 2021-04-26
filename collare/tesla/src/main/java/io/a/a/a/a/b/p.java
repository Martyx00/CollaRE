package io.a.a.a.a.b;

import android.content.Context;
import io.a.a.a.c;
import io.a.a.a.l;
import java.lang.reflect.Method;

/* access modifiers changed from: package-private */
/* compiled from: FirebaseAppImpl */
public final class p implements o {

    /* renamed from: a  reason: collision with root package name */
    private final Method f5911a;

    /* renamed from: b  reason: collision with root package name */
    private final Object f5912b;

    public static o a(Context context) {
        try {
            Class<?> loadClass = context.getClassLoader().loadClass("com.google.firebase.FirebaseApp");
            return new p(loadClass, loadClass.getDeclaredMethod("getInstance", new Class[0]).invoke(loadClass, new Object[0]));
        } catch (ClassNotFoundException unused) {
            c.g().a("Fabric", "Could not find class: com.google.firebase.FirebaseApp");
            return null;
        } catch (NoSuchMethodException e) {
            l g = c.g();
            g.a("Fabric", "Could not find method: " + e.getMessage());
            return null;
        } catch (Exception e2) {
            c.g().a("Fabric", "Unexpected error loading FirebaseApp instance.", e2);
            return null;
        }
    }

    private p(Class cls, Object obj) {
        this.f5912b = obj;
        this.f5911a = cls.getDeclaredMethod("isDataCollectionDefaultEnabled", new Class[0]);
    }

    @Override // io.a.a.a.a.b.o
    public boolean a() {
        try {
            return ((Boolean) this.f5911a.invoke(this.f5912b, new Object[0])).booleanValue();
        } catch (Exception e) {
            c.g().a("Fabric", "Cannot check isDataCollectionDefaultEnabled on FirebaseApp.", e);
            return false;
        }
    }
}
