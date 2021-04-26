package io.realm;

import io.realm.internal.n;
import io.realm.internal.p;

/* compiled from: RealmObject */
public abstract class v implements u {
    public static <E extends u> boolean a(E e) {
        if (e instanceof n) {
            p b2 = ((n) e).c_().b();
            if (b2 == null || !b2.d()) {
                return false;
            }
            return true;
        } else if (e != null) {
            return true;
        } else {
            return false;
        }
    }

    public static <E extends u> boolean b(E e) {
        return e instanceof n;
    }
}
