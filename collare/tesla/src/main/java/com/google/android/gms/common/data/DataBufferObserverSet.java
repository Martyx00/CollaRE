package com.google.android.gms.common.data;

import com.google.android.gms.common.data.DataBufferObserver;
import java.util.HashSet;
import java.util.Iterator;

public final class DataBufferObserverSet implements DataBufferObserver, DataBufferObserver.Observable {
    private HashSet<DataBufferObserver> zalm = new HashSet<>();

    public final boolean hasObservers() {
        return !this.zalm.isEmpty();
    }

    public final void clear() {
        this.zalm.clear();
    }

    @Override // com.google.android.gms.common.data.DataBufferObserver.Observable
    public final void addObserver(DataBufferObserver dataBufferObserver) {
        this.zalm.add(dataBufferObserver);
    }

    @Override // com.google.android.gms.common.data.DataBufferObserver.Observable
    public final void removeObserver(DataBufferObserver dataBufferObserver) {
        this.zalm.remove(dataBufferObserver);
    }

    @Override // com.google.android.gms.common.data.DataBufferObserver
    public final void onDataChanged() {
        Iterator<DataBufferObserver> it = this.zalm.iterator();
        while (it.hasNext()) {
            it.next().onDataChanged();
        }
    }

    @Override // com.google.android.gms.common.data.DataBufferObserver
    public final void onDataRangeChanged(int i, int i2) {
        Iterator<DataBufferObserver> it = this.zalm.iterator();
        while (it.hasNext()) {
            it.next().onDataRangeChanged(i, i2);
        }
    }

    @Override // com.google.android.gms.common.data.DataBufferObserver
    public final void onDataRangeInserted(int i, int i2) {
        Iterator<DataBufferObserver> it = this.zalm.iterator();
        while (it.hasNext()) {
            it.next().onDataRangeInserted(i, i2);
        }
    }

    @Override // com.google.android.gms.common.data.DataBufferObserver
    public final void onDataRangeRemoved(int i, int i2) {
        Iterator<DataBufferObserver> it = this.zalm.iterator();
        while (it.hasNext()) {
            it.next().onDataRangeRemoved(i, i2);
        }
    }

    @Override // com.google.android.gms.common.data.DataBufferObserver
    public final void onDataRangeMoved(int i, int i2, int i3) {
        Iterator<DataBufferObserver> it = this.zalm.iterator();
        while (it.hasNext()) {
            it.next().onDataRangeMoved(i, i2, i3);
        }
    }
}
