package com.google.android.gms.internal.measurement;

import android.content.ComponentName;
import android.content.Context;
import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.stats.ConnectionTracker;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@VisibleForTesting
public final class zzik extends zzdz {
    private final zziy zzaqo;
    private zzfa zzaqp;
    private volatile Boolean zzaqq;
    private final zzep zzaqr;
    private final zzjo zzaqs;
    private final List<Runnable> zzaqt = new ArrayList();
    private final zzep zzaqu;

    protected zzik(zzgn zzgn) {
        super(zzgn);
        this.zzaqs = new zzjo(zzgn.zzbt());
        this.zzaqo = new zziy(this);
        this.zzaqr = new zzil(this, zzgn);
        this.zzaqu = new zziq(this, zzgn);
    }

    /* access modifiers changed from: private */
    public final void onServiceDisconnected(ComponentName componentName) {
        zzab();
        if (this.zzaqp != null) {
            this.zzaqp = null;
            zzgi().zzjc().zzg("Disconnected from device MeasurementService", componentName);
            zzab();
            zzdf();
        }
    }

    /* access modifiers changed from: private */
    public final void zzcu() {
        zzab();
        this.zzaqs.start();
        this.zzaqr.zzh(zzez.zzajk.get().longValue());
    }

    /* access modifiers changed from: private */
    public final void zzcv() {
        zzab();
        if (isConnected()) {
            zzgi().zzjc().log("Inactivity, disconnecting from the service");
            disconnect();
        }
    }

    private final void zzf(Runnable runnable) {
        zzab();
        if (isConnected()) {
            runnable.run();
        } else if (((long) this.zzaqt.size()) >= 1000) {
            zzgi().zziv().log("Discarding data. Max runnable queue size reached");
        } else {
            this.zzaqt.add(runnable);
            this.zzaqu.zzh(60000);
            zzdf();
        }
    }

    private final zzeb zzk(boolean z) {
        zzgl();
        return zzfz().zzbl(z ? zzgi().zzje() : null);
    }

    private final boolean zzkq() {
        zzgl();
        return true;
    }

    /* access modifiers changed from: private */
    public final void zzks() {
        zzab();
        zzgi().zzjc().zzg("Processing queued up service tasks", Integer.valueOf(this.zzaqt.size()));
        for (Runnable runnable : this.zzaqt) {
            try {
                runnable.run();
            } catch (Exception e) {
                zzgi().zziv().zzg("Task exception while flushing queue", e);
            }
        }
        this.zzaqt.clear();
        this.zzaqu.cancel();
    }

    public final void disconnect() {
        zzab();
        zzch();
        try {
            ConnectionTracker.getInstance().unbindService(getContext(), this.zzaqo);
        } catch (IllegalArgumentException | IllegalStateException unused) {
        }
        this.zzaqp = null;
    }

    @Override // com.google.android.gms.internal.measurement.zzhk, com.google.android.gms.internal.measurement.zzhi
    public final /* bridge */ /* synthetic */ Context getContext() {
        return super.getContext();
    }

    public final boolean isConnected() {
        zzab();
        zzch();
        return this.zzaqp != null;
    }

    /* access modifiers changed from: protected */
    public final void resetAnalyticsData() {
        zzab();
        zzfv();
        zzch();
        zzeb zzk = zzk(false);
        if (zzkq()) {
            zzgc().resetAnalyticsData();
        }
        zzf(new zzim(this, zzk));
    }

    /* access modifiers changed from: protected */
    @VisibleForTesting
    public final void zza(zzfa zzfa) {
        zzab();
        Preconditions.checkNotNull(zzfa);
        this.zzaqp = zzfa;
        zzcu();
        zzks();
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public final void zza(zzfa zzfa, AbstractSafeParcelable abstractSafeParcelable, zzeb zzeb) {
        int i;
        zzfk zziv;
        String str;
        List<AbstractSafeParcelable> zzp;
        zzab();
        zzfv();
        zzch();
        boolean zzkq = zzkq();
        int i2 = 0;
        int i3 = 100;
        while (i2 < 1001 && i3 == 100) {
            ArrayList arrayList = new ArrayList();
            if (!zzkq || (zzp = zzgc().zzp(100)) == null) {
                i = 0;
            } else {
                arrayList.addAll(zzp);
                i = zzp.size();
            }
            if (abstractSafeParcelable != null && i < 100) {
                arrayList.add(abstractSafeParcelable);
            }
            ArrayList arrayList2 = arrayList;
            int size = arrayList2.size();
            int i4 = 0;
            while (i4 < size) {
                Object obj = arrayList2.get(i4);
                i4++;
                AbstractSafeParcelable abstractSafeParcelable2 = (AbstractSafeParcelable) obj;
                if (abstractSafeParcelable2 instanceof zzex) {
                    try {
                        zzfa.zza((zzex) abstractSafeParcelable2, zzeb);
                    } catch (RemoteException e) {
                        e = e;
                        zziv = zzgi().zziv();
                        str = "Failed to send event to the service";
                    }
                } else if (abstractSafeParcelable2 instanceof zzka) {
                    try {
                        zzfa.zza((zzka) abstractSafeParcelable2, zzeb);
                    } catch (RemoteException e2) {
                        e = e2;
                        zziv = zzgi().zziv();
                        str = "Failed to send attribute to the service";
                    }
                } else if (abstractSafeParcelable2 instanceof zzef) {
                    try {
                        zzfa.zza((zzef) abstractSafeParcelable2, zzeb);
                    } catch (RemoteException e3) {
                        e = e3;
                        zziv = zzgi().zziv();
                        str = "Failed to send conditional property to the service";
                    }
                } else {
                    zzgi().zziv().log("Discarding data. Unrecognized parcel type.");
                }
            }
            i2++;
            i3 = i;
        }
        return;
        zziv.zzg(str, e);
    }

    public final void zza(AtomicReference<String> atomicReference) {
        zzab();
        zzch();
        zzf(new zzin(this, atomicReference, zzk(false)));
    }

    /* access modifiers changed from: protected */
    public final void zza(AtomicReference<List<zzef>> atomicReference, String str, String str2, String str3) {
        zzab();
        zzch();
        zzf(new zziu(this, atomicReference, str, str2, str3, zzk(false)));
    }

    /* access modifiers changed from: protected */
    public final void zza(AtomicReference<List<zzka>> atomicReference, String str, String str2, String str3, boolean z) {
        zzab();
        zzch();
        zzf(new zziv(this, atomicReference, str, str2, str3, z, zzk(false)));
    }

    /* access modifiers changed from: protected */
    public final void zza(AtomicReference<List<zzka>> atomicReference, boolean z) {
        zzab();
        zzch();
        zzf(new zzix(this, atomicReference, zzk(false), z));
    }

    @Override // com.google.android.gms.internal.measurement.zzhi, com.google.android.gms.internal.measurement.zzdy
    public final /* bridge */ /* synthetic */ void zzab() {
        super.zzab();
    }

    /* access modifiers changed from: protected */
    public final void zzb(zzex zzex, String str) {
        Preconditions.checkNotNull(zzex);
        zzab();
        zzch();
        boolean zzkq = zzkq();
        zzf(new zzis(this, zzkq, zzkq && zzgc().zza(zzex), zzex, zzk(true), str));
    }

    /* access modifiers changed from: protected */
    public final void zzb(zzig zzig) {
        zzab();
        zzch();
        zzf(new zzip(this, zzig));
    }

    /* access modifiers changed from: protected */
    public final void zzb(zzka zzka) {
        zzab();
        zzch();
        zzf(new zziw(this, zzkq() && zzgc().zza(zzka), zzka, zzk(true)));
    }

    @Override // com.google.android.gms.internal.measurement.zzhk, com.google.android.gms.internal.measurement.zzhi
    public final /* bridge */ /* synthetic */ Clock zzbt() {
        return super.zzbt();
    }

    /* access modifiers changed from: protected */
    public final void zzd(zzef zzef) {
        Preconditions.checkNotNull(zzef);
        zzab();
        zzch();
        zzgl();
        zzf(new zzit(this, true, zzgc().zzc(zzef), new zzef(zzef), zzk(true), zzef));
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x0108  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zzdf() {
        /*
        // Method dump skipped, instructions count: 400
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzik.zzdf():void");
    }

    @Override // com.google.android.gms.internal.measurement.zzhi, com.google.android.gms.internal.measurement.zzdy
    public final /* bridge */ /* synthetic */ void zzfu() {
        super.zzfu();
    }

    @Override // com.google.android.gms.internal.measurement.zzhi, com.google.android.gms.internal.measurement.zzdy
    public final /* bridge */ /* synthetic */ void zzfv() {
        super.zzfv();
    }

    @Override // com.google.android.gms.internal.measurement.zzhi, com.google.android.gms.internal.measurement.zzdy
    public final /* bridge */ /* synthetic */ void zzfw() {
        super.zzfw();
    }

    @Override // com.google.android.gms.internal.measurement.zzdy
    public final /* bridge */ /* synthetic */ zzdu zzfx() {
        return super.zzfx();
    }

    @Override // com.google.android.gms.internal.measurement.zzdy
    public final /* bridge */ /* synthetic */ zzhm zzfy() {
        return super.zzfy();
    }

    @Override // com.google.android.gms.internal.measurement.zzdy
    public final /* bridge */ /* synthetic */ zzfd zzfz() {
        return super.zzfz();
    }

    @Override // com.google.android.gms.internal.measurement.zzdy
    public final /* bridge */ /* synthetic */ zzik zzga() {
        return super.zzga();
    }

    @Override // com.google.android.gms.internal.measurement.zzdy
    public final /* bridge */ /* synthetic */ zzih zzgb() {
        return super.zzgb();
    }

    @Override // com.google.android.gms.internal.measurement.zzdy
    public final /* bridge */ /* synthetic */ zzfe zzgc() {
        return super.zzgc();
    }

    @Override // com.google.android.gms.internal.measurement.zzdy
    public final /* bridge */ /* synthetic */ zzjj zzgd() {
        return super.zzgd();
    }

    @Override // com.google.android.gms.internal.measurement.zzhi
    public final /* bridge */ /* synthetic */ zzer zzge() {
        return super.zzge();
    }

    @Override // com.google.android.gms.internal.measurement.zzhi
    public final /* bridge */ /* synthetic */ zzfg zzgf() {
        return super.zzgf();
    }

    @Override // com.google.android.gms.internal.measurement.zzhi
    public final /* bridge */ /* synthetic */ zzkd zzgg() {
        return super.zzgg();
    }

    @Override // com.google.android.gms.internal.measurement.zzhk, com.google.android.gms.internal.measurement.zzhi
    public final /* bridge */ /* synthetic */ zzgi zzgh() {
        return super.zzgh();
    }

    @Override // com.google.android.gms.internal.measurement.zzhk, com.google.android.gms.internal.measurement.zzhi
    public final /* bridge */ /* synthetic */ zzfi zzgi() {
        return super.zzgi();
    }

    @Override // com.google.android.gms.internal.measurement.zzhi
    public final /* bridge */ /* synthetic */ zzft zzgj() {
        return super.zzgj();
    }

    @Override // com.google.android.gms.internal.measurement.zzhi
    public final /* bridge */ /* synthetic */ zzeh zzgk() {
        return super.zzgk();
    }

    @Override // com.google.android.gms.internal.measurement.zzhk, com.google.android.gms.internal.measurement.zzhi
    public final /* bridge */ /* synthetic */ zzee zzgl() {
        return super.zzgl();
    }

    /* access modifiers changed from: protected */
    @Override // com.google.android.gms.internal.measurement.zzdz
    public final boolean zzgn() {
        return false;
    }

    /* access modifiers changed from: protected */
    public final void zzkm() {
        zzab();
        zzch();
        zzf(new zzio(this, zzk(true)));
    }

    /* access modifiers changed from: protected */
    public final void zzkp() {
        zzab();
        zzch();
        zzf(new zzir(this, zzk(true)));
    }

    /* access modifiers changed from: package-private */
    public final Boolean zzkr() {
        return this.zzaqq;
    }
}
