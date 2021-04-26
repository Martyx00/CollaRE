package com.google.android.gms.common.api;

import android.os.Bundle;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.data.AbstractDataBuffer;
import com.google.android.gms.common.data.DataBuffer;
import java.util.Iterator;

@KeepForSdk
public class DataBufferResponse<T, R extends AbstractDataBuffer<T> & Result> extends Response<R> implements DataBuffer<T> {
    @KeepForSdk
    public DataBufferResponse() {
    }

    @KeepForSdk
    public DataBufferResponse(R r) {
        super(r);
    }

    @Override // com.google.android.gms.common.data.DataBuffer
    public int getCount() {
        return ((AbstractDataBuffer) getResult()).getCount();
    }

    @Override // com.google.android.gms.common.data.DataBuffer
    public T get(int i) {
        return (T) ((AbstractDataBuffer) getResult()).get(i);
    }

    @Override // com.google.android.gms.common.data.DataBuffer
    public Bundle getMetadata() {
        return ((AbstractDataBuffer) getResult()).getMetadata();
    }

    @Override // com.google.android.gms.common.data.DataBuffer
    public void close() {
        ((AbstractDataBuffer) getResult()).close();
    }

    @Override // com.google.android.gms.common.data.DataBuffer
    public boolean isClosed() {
        return ((AbstractDataBuffer) getResult()).isClosed();
    }

    @Override // com.google.android.gms.common.data.DataBuffer, java.lang.Iterable
    public Iterator<T> iterator() {
        return ((AbstractDataBuffer) getResult()).iterator();
    }

    @Override // com.google.android.gms.common.data.DataBuffer
    public Iterator<T> singleRefIterator() {
        return ((AbstractDataBuffer) getResult()).singleRefIterator();
    }

    @Override // com.google.android.gms.common.data.DataBuffer, com.google.android.gms.common.api.Releasable
    public void release() {
        ((AbstractDataBuffer) getResult()).release();
    }
}
