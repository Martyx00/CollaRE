package com.facebook.common.d;

import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

/* compiled from: Sets */
public final class k {
    public static <E> Set<E> a() {
        return a(new IdentityHashMap());
    }

    public static <E> Set<E> a(Map<E, Boolean> map) {
        return Collections.newSetFromMap(map);
    }

    public static <E> CopyOnWriteArraySet<E> b() {
        return new CopyOnWriteArraySet<>();
    }
}
