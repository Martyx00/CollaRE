package com.google.android.gms.common.data;

import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Preconditions;
import java.util.Iterator;
import java.util.NoSuchElementException;

@KeepForSdk
public class DataBufferIterator<T> implements Iterator<T> {
    protected final DataBuffer<T> zalk;
    protected int zall = -1;

    public DataBufferIterator(DataBuffer<T> dataBuffer) {
        this.zalk = (DataBuffer) Preconditions.checkNotNull(dataBuffer);
    }

    public boolean hasNext() {
        return this.zall < this.zalk.getCount() - 1;
    }

    @Override // java.util.Iterator
    public T next() {
        if (hasNext()) {
            DataBuffer<T> dataBuffer = this.zalk;
            int i = this.zall + 1;
            this.zall = i;
            return dataBuffer.get(i);
        }
        int i2 = this.zall;
        StringBuilder sb = new StringBuilder(46);
        sb.append("Cannot advance the iterator beyond ");
        sb.append(i2);
        throw new NoSuchElementException(sb.toString());
    }

    public void remove() {
        throw new UnsupportedOperationException("Cannot remove elements from a DataBufferIterator");
    }
}
