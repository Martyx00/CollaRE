package com.google.android.gms.internal.measurement;

/* access modifiers changed from: package-private */
public final class zzfj implements Runnable {
    private final /* synthetic */ int zzakw;
    private final /* synthetic */ String zzakx;
    private final /* synthetic */ Object zzaky;
    private final /* synthetic */ Object zzakz;
    private final /* synthetic */ Object zzala;
    private final /* synthetic */ zzfi zzalb;

    zzfj(zzfi zzfi, int i, String str, Object obj, Object obj2, Object obj3) {
        this.zzalb = zzfi;
        this.zzakw = i;
        this.zzakx = str;
        this.zzaky = obj;
        this.zzakz = obj2;
        this.zzala = obj3;
    }

    public final void run() {
        char c2;
        zzfi zzfi;
        zzft zzgj = this.zzalb.zzacv.zzgj();
        if (!zzgj.isInitialized()) {
            this.zzalb.zza(6, "Persisted config not initialized. Not logging error/warn");
            return;
        }
        if (this.zzalb.zzakl == 0) {
            if (this.zzalb.zzgk().zzds()) {
                zzfi = this.zzalb;
                zzfi.zzgl();
                c2 = 'C';
            } else {
                zzfi = this.zzalb;
                zzfi.zzgl();
                c2 = 'c';
            }
            zzfi.zzakl = c2;
        }
        if (this.zzalb.zzafi < 0) {
            zzfi zzfi2 = this.zzalb;
            zzfi2.zzafi = zzfi2.zzgk().zzgw();
        }
        char charAt = "01VDIWEA?".charAt(this.zzakw);
        char c3 = this.zzalb.zzakl;
        long j = this.zzalb.zzafi;
        String zza = zzfi.zza(true, this.zzakx, this.zzaky, this.zzakz, this.zzala);
        StringBuilder sb = new StringBuilder(String.valueOf(zza).length() + 24);
        sb.append("2");
        sb.append(charAt);
        sb.append(c3);
        sb.append(j);
        sb.append(":");
        sb.append(zza);
        String sb2 = sb.toString();
        if (sb2.length() > 1024) {
            sb2 = this.zzakx.substring(0, 1024);
        }
        zzgj.zzals.zzc(sb2, 1);
    }
}
