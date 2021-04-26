package com.google.android.gms.internal.measurement;

import com.google.android.gms.common.api.Api;

/* access modifiers changed from: package-private */
public final class zzzl extends zzzj {
    private final byte[] buffer;
    private int limit;
    private int pos;
    private final boolean zzbto;
    private int zzbtp;
    private int zzbtq;
    private int zzbtr;

    private zzzl(byte[] bArr, int i, int i2, boolean z) {
        super();
        this.zzbtr = Api.BaseClientBuilder.API_PRIORITY_OTHER;
        this.buffer = bArr;
        this.limit = i2 + i;
        this.pos = i;
        this.zzbtq = this.pos;
        this.zzbto = z;
    }

    private final void zztp() {
        this.limit += this.zzbtp;
        int i = this.limit;
        int i2 = i - this.zzbtq;
        int i3 = this.zzbtr;
        if (i2 > i3) {
            this.zzbtp = i2 - i3;
            this.limit = i - this.zzbtp;
            return;
        }
        this.zzbtp = 0;
    }

    public final int zzaf(int i) {
        if (i >= 0) {
            int zzto = i + zzto();
            int i2 = this.zzbtr;
            if (zzto <= i2) {
                this.zzbtr = zzto;
                zztp();
                return i2;
            }
            throw zzzy.zzub();
        }
        throw zzzy.zzuc();
    }

    @Override // com.google.android.gms.internal.measurement.zzzj
    public final int zzto() {
        return this.pos - this.zzbtq;
    }
}
