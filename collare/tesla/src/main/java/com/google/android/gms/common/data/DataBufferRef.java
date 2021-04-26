package com.google.android.gms.common.data;

import android.database.CharArrayBuffer;
import android.net.Uri;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;

@KeepForSdk
public class DataBufferRef {
    @KeepForSdk
    protected final DataHolder mDataHolder;
    @KeepForSdk
    protected int mDataRow;
    private int zaln;

    @KeepForSdk
    public DataBufferRef(DataHolder dataHolder, int i) {
        this.mDataHolder = (DataHolder) Preconditions.checkNotNull(dataHolder);
        zag(i);
    }

    /* access modifiers changed from: protected */
    @KeepForSdk
    public int getDataRow() {
        return this.mDataRow;
    }

    /* access modifiers changed from: protected */
    public final void zag(int i) {
        Preconditions.checkState(i >= 0 && i < this.mDataHolder.getCount());
        this.mDataRow = i;
        this.zaln = this.mDataHolder.getWindowIndex(this.mDataRow);
    }

    @KeepForSdk
    public boolean isDataValid() {
        return !this.mDataHolder.isClosed();
    }

    @KeepForSdk
    public boolean hasColumn(String str) {
        return this.mDataHolder.hasColumn(str);
    }

    /* access modifiers changed from: protected */
    @KeepForSdk
    public long getLong(String str) {
        return this.mDataHolder.getLong(str, this.mDataRow, this.zaln);
    }

    /* access modifiers changed from: protected */
    @KeepForSdk
    public int getInteger(String str) {
        return this.mDataHolder.getInteger(str, this.mDataRow, this.zaln);
    }

    /* access modifiers changed from: protected */
    @KeepForSdk
    public boolean getBoolean(String str) {
        return this.mDataHolder.getBoolean(str, this.mDataRow, this.zaln);
    }

    /* access modifiers changed from: protected */
    @KeepForSdk
    public String getString(String str) {
        return this.mDataHolder.getString(str, this.mDataRow, this.zaln);
    }

    /* access modifiers changed from: protected */
    @KeepForSdk
    public float getFloat(String str) {
        return this.mDataHolder.zaa(str, this.mDataRow, this.zaln);
    }

    /* access modifiers changed from: protected */
    @KeepForSdk
    public double getDouble(String str) {
        return this.mDataHolder.zab(str, this.mDataRow, this.zaln);
    }

    /* access modifiers changed from: protected */
    @KeepForSdk
    public byte[] getByteArray(String str) {
        return this.mDataHolder.getByteArray(str, this.mDataRow, this.zaln);
    }

    /* access modifiers changed from: protected */
    @KeepForSdk
    public Uri parseUri(String str) {
        String string = this.mDataHolder.getString(str, this.mDataRow, this.zaln);
        if (string == null) {
            return null;
        }
        return Uri.parse(string);
    }

    /* access modifiers changed from: protected */
    @KeepForSdk
    public void copyToBuffer(String str, CharArrayBuffer charArrayBuffer) {
        this.mDataHolder.zaa(str, this.mDataRow, this.zaln, charArrayBuffer);
    }

    /* access modifiers changed from: protected */
    @KeepForSdk
    public boolean hasNull(String str) {
        return this.mDataHolder.hasNull(str, this.mDataRow, this.zaln);
    }

    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(this.mDataRow), Integer.valueOf(this.zaln), this.mDataHolder);
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof DataBufferRef)) {
            return false;
        }
        DataBufferRef dataBufferRef = (DataBufferRef) obj;
        if (!Objects.equal(Integer.valueOf(dataBufferRef.mDataRow), Integer.valueOf(this.mDataRow)) || !Objects.equal(Integer.valueOf(dataBufferRef.zaln), Integer.valueOf(this.zaln)) || dataBufferRef.mDataHolder != this.mDataHolder) {
            return false;
        }
        return true;
    }
}
