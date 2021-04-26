package com.google.android.gms.common.data;

import java.util.ArrayList;

public final class FreezableUtils {
    /* JADX DEBUG: Multi-variable search result rejected for r0v0, resolved type: java.util.ArrayList<T> */
    /* JADX WARN: Multi-variable type inference failed */
    public static <T, E extends Freezable<T>> ArrayList<T> freeze(ArrayList<E> arrayList) {
        ArrayList<T> arrayList2 = (ArrayList<T>) new ArrayList(arrayList.size());
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            arrayList2.add(arrayList.get(i).freeze());
        }
        return arrayList2;
    }

    /* JADX DEBUG: Multi-variable search result rejected for r0v0, resolved type: java.util.ArrayList<T> */
    /* JADX WARN: Multi-variable type inference failed */
    public static <T, E extends Freezable<T>> ArrayList<T> freeze(E[] eArr) {
        ArrayList<T> arrayList = (ArrayList<T>) new ArrayList(eArr.length);
        for (E e : eArr) {
            arrayList.add(e.freeze());
        }
        return arrayList;
    }

    /* JADX DEBUG: Multi-variable search result rejected for r0v0, resolved type: java.util.ArrayList<T> */
    /* JADX WARN: Multi-variable type inference failed */
    public static <T, E extends Freezable<T>> ArrayList<T> freezeIterable(Iterable<E> iterable) {
        ArrayList<T> arrayList = (ArrayList<T>) new ArrayList();
        for (E e : iterable) {
            arrayList.add(e.freeze());
        }
        return arrayList;
    }
}
