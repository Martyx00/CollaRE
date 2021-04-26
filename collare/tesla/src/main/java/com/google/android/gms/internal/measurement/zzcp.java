package com.google.android.gms.internal.measurement;

import com.google.android.gms.common.internal.Preconditions;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/* access modifiers changed from: package-private */
public final class zzcp {
    private int zzabb;
    private ByteArrayOutputStream zzabc = new ByteArrayOutputStream();
    private final /* synthetic */ zzco zzabd;

    public zzcp(zzco zzco) {
        this.zzabd = zzco;
    }

    public final byte[] getPayload() {
        return this.zzabc.toByteArray();
    }

    public final boolean zze(zzch zzch) {
        Preconditions.checkNotNull(zzch);
        if (this.zzabb + 1 > zzbu.zzdy()) {
            return false;
        }
        String zza = this.zzabd.zza(zzch, false);
        if (zza == null) {
            this.zzabd.zzbu().zza(zzch, "Error formatting hit");
            return true;
        }
        byte[] bytes = zza.getBytes();
        int length = bytes.length;
        if (length > zzbu.zzdu()) {
            this.zzabd.zzbu().zza(zzch, "Hit size exceeds the maximum size limit");
            return true;
        }
        if (this.zzabc.size() > 0) {
            length++;
        }
        if (this.zzabc.size() + length > zzcc.zzzj.get().intValue()) {
            return false;
        }
        try {
            if (this.zzabc.size() > 0) {
                this.zzabc.write(zzco.zzaba);
            }
            this.zzabc.write(bytes);
            this.zzabb++;
            return true;
        } catch (IOException e) {
            this.zzabd.zze("Failed to write payload when batching hits", e);
            return true;
        }
    }

    public final int zzfa() {
        return this.zzabb;
    }
}
