package com.google.android.gms.common.data;

import com.google.android.gms.common.annotation.KeepForSdk;
import java.util.ArrayList;

@KeepForSdk
public abstract class EntityBuffer<T> extends AbstractDataBuffer<T> {
    private boolean zame = false;
    private ArrayList<Integer> zamf;

    @KeepForSdk
    protected EntityBuffer(DataHolder dataHolder) {
        super(dataHolder);
    }

    /* access modifiers changed from: protected */
    @KeepForSdk
    public String getChildDataMarkerColumn() {
        return null;
    }

    /* access modifiers changed from: protected */
    @KeepForSdk
    public abstract T getEntry(int i, int i2);

    /* access modifiers changed from: protected */
    @KeepForSdk
    public abstract String getPrimaryDataMarkerColumn();

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0064, code lost:
        if (r6.mDataHolder.getString(r4, r7, r3) == null) goto L_0x0068;
     */
    @Override // com.google.android.gms.common.data.DataBuffer, com.google.android.gms.common.data.AbstractDataBuffer
    @com.google.android.gms.common.annotation.KeepForSdk
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final T get(int r7) {
        /*
        // Method dump skipped, instructions count: 109
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.data.EntityBuffer.get(int):java.lang.Object");
    }

    @Override // com.google.android.gms.common.data.DataBuffer, com.google.android.gms.common.data.AbstractDataBuffer
    @KeepForSdk
    public int getCount() {
        zacb();
        return this.zamf.size();
    }

    private final void zacb() {
        synchronized (this) {
            if (!this.zame) {
                int count = this.mDataHolder.getCount();
                this.zamf = new ArrayList<>();
                if (count > 0) {
                    this.zamf.add(0);
                    String primaryDataMarkerColumn = getPrimaryDataMarkerColumn();
                    String string = this.mDataHolder.getString(primaryDataMarkerColumn, 0, this.mDataHolder.getWindowIndex(0));
                    for (int i = 1; i < count; i++) {
                        int windowIndex = this.mDataHolder.getWindowIndex(i);
                        String string2 = this.mDataHolder.getString(primaryDataMarkerColumn, i, windowIndex);
                        if (string2 != null) {
                            if (!string2.equals(string)) {
                                this.zamf.add(Integer.valueOf(i));
                                string = string2;
                            }
                        } else {
                            StringBuilder sb = new StringBuilder(String.valueOf(primaryDataMarkerColumn).length() + 78);
                            sb.append("Missing value for markerColumn: ");
                            sb.append(primaryDataMarkerColumn);
                            sb.append(", at row: ");
                            sb.append(i);
                            sb.append(", for window: ");
                            sb.append(windowIndex);
                            throw new NullPointerException(sb.toString());
                        }
                    }
                }
                this.zame = true;
            }
        }
    }

    private final int zah(int i) {
        if (i >= 0 && i < this.zamf.size()) {
            return this.zamf.get(i).intValue();
        }
        StringBuilder sb = new StringBuilder(53);
        sb.append("Position ");
        sb.append(i);
        sb.append(" is out of bounds for this buffer");
        throw new IllegalArgumentException(sb.toString());
    }
}
