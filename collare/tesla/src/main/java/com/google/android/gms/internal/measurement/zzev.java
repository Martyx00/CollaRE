package com.google.android.gms.internal.measurement;

import java.util.Iterator;

/* access modifiers changed from: package-private */
public final class zzev implements Iterator<String> {
    private Iterator<String> zzahp = this.zzahq.zzaho.keySet().iterator();
    private final /* synthetic */ zzeu zzahq;

    zzev(zzeu zzeu) {
        this.zzahq = zzeu;
    }

    public final boolean hasNext() {
        return this.zzahp.hasNext();
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // java.util.Iterator
    public final /* synthetic */ String next() {
        return this.zzahp.next();
    }

    public final void remove() {
        throw new UnsupportedOperationException("Remove not supported");
    }
}
