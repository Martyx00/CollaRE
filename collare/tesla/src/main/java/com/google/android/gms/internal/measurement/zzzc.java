package com.google.android.gms.internal.measurement;

import java.util.Iterator;
import java.util.NoSuchElementException;

final class zzzc implements Iterator {
    private final int limit = this.zzbtg.size();
    private int position = 0;
    private final /* synthetic */ zzzb zzbtg;

    zzzc(zzzb zzzb) {
        this.zzbtg = zzzb;
    }

    private final byte nextByte() {
        try {
            zzzb zzzb = this.zzbtg;
            int i = this.position;
            this.position = i + 1;
            return zzzb.zzae(i);
        } catch (IndexOutOfBoundsException e) {
            throw new NoSuchElementException(e.getMessage());
        }
    }

    public final boolean hasNext() {
        return this.position < this.limit;
    }

    @Override // java.util.Iterator
    public final /* synthetic */ Object next() {
        return Byte.valueOf(nextByte());
    }

    public final void remove() {
        throw new UnsupportedOperationException();
    }
}
