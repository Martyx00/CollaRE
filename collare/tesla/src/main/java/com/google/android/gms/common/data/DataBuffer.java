package com.google.android.gms.common.data;

import android.os.Bundle;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.Releasable;
import java.util.Iterator;

public interface DataBuffer<T> extends Releasable, Iterable<T> {
    @Deprecated
    void close();

    T get(int i);

    int getCount();

    @KeepForSdk
    Bundle getMetadata();

    @Deprecated
    boolean isClosed();

    @Override // java.lang.Iterable
    Iterator<T> iterator();

    @Override // com.google.android.gms.common.api.Releasable
    void release();

    Iterator<T> singleRefIterator();
}
