package com.facebook.common.d;

import java.util.ArrayList;
import java.util.Collections;

/* compiled from: ImmutableList */
public class e<E> extends ArrayList<E> {
    private e(int i) {
        super(i);
    }

    public static <E> e<E> a(E... eArr) {
        e<E> eVar = new e<>(eArr.length);
        Collections.addAll(eVar, eArr);
        return eVar;
    }
}
