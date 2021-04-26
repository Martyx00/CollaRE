package io.realm.internal;

import android.content.Context;
import io.realm.exceptions.RealmException;
import io.realm.o;
import io.realm.r;
import java.lang.reflect.InvocationTargetException;

/* compiled from: ObjectServerFacade */
public class j {

    /* renamed from: a  reason: collision with root package name */
    private static final j f6284a = new j();

    /* renamed from: b  reason: collision with root package name */
    private static j f6285b;

    public void a(Context context, String str) {
    }

    public void a(OsRealmConfig osRealmConfig) {
    }

    public void a(o oVar) {
    }

    public void a(r rVar) {
    }

    public String c(r rVar) {
        return null;
    }

    public String d(r rVar) {
        return null;
    }

    public void e(r rVar) {
    }

    static {
        f6285b = null;
        try {
            f6285b = (j) Class.forName("io.realm.internal.SyncObjectServerFacade").getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (ClassNotFoundException unused) {
        } catch (InstantiationException e) {
            throw new RealmException("Failed to init SyncObjectServerFacade", e);
        } catch (IllegalAccessException e2) {
            throw new RealmException("Failed to init SyncObjectServerFacade", e2);
        } catch (NoSuchMethodException e3) {
            throw new RealmException("Failed to init SyncObjectServerFacade", e3);
        } catch (InvocationTargetException e4) {
            throw new RealmException("Failed to init SyncObjectServerFacade", e4.getTargetException());
        }
    }

    public Object[] b(r rVar) {
        return new Object[11];
    }

    public static j a(boolean z) {
        if (z) {
            return f6285b;
        }
        return f6284a;
    }

    public static j a() {
        j jVar = f6285b;
        if (jVar != null) {
            return jVar;
        }
        return f6284a;
    }
}
