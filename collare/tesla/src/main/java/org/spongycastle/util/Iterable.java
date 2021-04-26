package org.spongycastle.util;

import java.util.Iterator;

public interface Iterable<T> extends Iterable<T> {
    @Override // java.lang.Iterable
    Iterator<T> iterator();
}
