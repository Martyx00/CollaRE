package com.google.android.gms.tagmanager;

import android.content.Context;
import android.os.Looper;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BasePendingResult;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.DefaultClock;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.measurement.zzi;
import com.google.android.gms.internal.measurement.zzl;
import com.google.android.gms.internal.measurement.zzwc;
import com.google.android.gms.internal.measurement.zzwd;
import com.google.android.gms.internal.measurement.zzwi;
import com.google.android.gms.tagmanager.zzeh;

public final class zzy extends BasePendingResult<ContainerHolder> {
    private final Clock clock;
    private final String zzaxm;
    private long zzaxr;
    private final Looper zzaxu;
    private final TagManager zzaya;
    private final zzaf zzayd;
    private final zzej zzaye;
    private final int zzayf;
    private final zzai zzayg;
    private zzah zzayh;
    private zzwd zzayi;
    private volatile zzv zzayj;
    private volatile boolean zzayk;
    private zzl zzayl;
    private String zzaym;
    private zzag zzayn;
    private zzac zzayo;
    private final Context zzqx;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    @VisibleForTesting
    private zzy(Context context, TagManager tagManager, Looper looper, String str, int i, zzah zzah, zzag zzag, zzwd zzwd, Clock clock2, zzej zzej, zzai zzai) {
        super(looper == null ? Looper.getMainLooper() : looper);
        this.zzqx = context;
        this.zzaya = tagManager;
        this.zzaxu = looper == null ? Looper.getMainLooper() : looper;
        this.zzaxm = str;
        this.zzayf = i;
        this.zzayh = zzah;
        this.zzayn = zzag;
        this.zzayi = zzwd;
        this.zzayd = new zzaf(this, null);
        this.zzayl = new zzl();
        this.clock = clock2;
        this.zzaye = zzej;
        this.zzayg = zzai;
        if (zzmv()) {
            zzcs(zzeh.zzok().zzom());
        }
    }

    public zzy(Context context, TagManager tagManager, Looper looper, String str, int i, zzal zzal) {
        this(context, tagManager, looper, str, i, new zzex(context, str), new zzes(context, str, zzal), new zzwd(context), DefaultClock.getInstance(), new zzdg(1, 5, 900000, 5000, "refreshing", DefaultClock.getInstance()), new zzai(context, str));
        this.zzayi.zzeu(zzal.zznd());
    }

    /* access modifiers changed from: private */
    public final synchronized void zza(zzl zzl) {
        if (this.zzayh != null) {
            zzwc zzwc = new zzwc();
            zzwc.zzboa = this.zzaxr;
            zzwc.zzpv = new zzi();
            zzwc.zzbob = zzl;
            this.zzayh.zza(zzwc);
        }
    }

    /* access modifiers changed from: private */
    public final synchronized void zza(zzl zzl, long j, boolean z) {
        if (z) {
            boolean z2 = this.zzayk;
        }
        if (!isReady() || this.zzayj != null) {
            this.zzayl = zzl;
            this.zzaxr = j;
            long zzmy = this.zzayg.zzmy();
            zzam(Math.max(0L, Math.min(zzmy, (this.zzaxr + zzmy) - this.clock.currentTimeMillis())));
            Container container = new Container(this.zzqx, this.zzaya.getDataLayer(), this.zzaxm, j, zzl);
            if (this.zzayj == null) {
                this.zzayj = new zzv(this.zzaya, this.zzaxu, container, this.zzayd);
            } else {
                this.zzayj.zza(container);
            }
            if (!isReady() && this.zzayo.zzb(container)) {
                setResult(this.zzayj);
            }
        }
    }

    /* access modifiers changed from: private */
    public final synchronized void zzam(long j) {
        if (this.zzayn == null) {
            zzdi.zzab("Refresh requested, but no network load scheduler.");
        } else {
            this.zzayn.zza(j, this.zzayl.zzpw);
        }
    }

    /* access modifiers changed from: private */
    public final boolean zzmv() {
        zzeh zzok = zzeh.zzok();
        return (zzok.zzol() == zzeh.zza.CONTAINER || zzok.zzol() == zzeh.zza.CONTAINER_DEBUG) && this.zzaxm.equals(zzok.getContainerId());
    }

    private final void zzn(boolean z) {
        this.zzayh.zza(new zzad(this, null));
        this.zzayn.zza(new zzae(this, null));
        zzwi zzr = this.zzayh.zzr(this.zzayf);
        if (zzr != null) {
            TagManager tagManager = this.zzaya;
            this.zzayj = new zzv(tagManager, this.zzaxu, new Container(this.zzqx, tagManager.getDataLayer(), this.zzaxm, 0, zzr), this.zzayd);
        }
        this.zzayo = new zzab(this, z);
        if (zzmv()) {
            this.zzayn.zza(0, "");
        } else {
            this.zzayh.zzmx();
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: a_ */
    public final ContainerHolder createFailedResult(Status status) {
        if (this.zzayj != null) {
            return this.zzayj;
        }
        if (status == Status.RESULT_TIMEOUT) {
            zzdi.e("timer expired: setting result to failure");
        }
        return new zzv(status);
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public final synchronized void zzcs(String str) {
        this.zzaym = str;
        if (this.zzayn != null) {
            this.zzayn.zzct(str);
        }
    }

    /* access modifiers changed from: package-private */
    public final synchronized String zzmp() {
        return this.zzaym;
    }

    public final void zzms() {
        zzwi zzr = this.zzayh.zzr(this.zzayf);
        if (zzr != null) {
            setResult(new zzv(this.zzaya, this.zzaxu, new Container(this.zzqx, this.zzaya.getDataLayer(), this.zzaxm, 0, zzr), new zzaa(this)));
        } else {
            zzdi.e("Default was requested, but no default container was found");
            setResult(createFailedResult(new Status(10, "Default was requested, but no default container was found", null)));
        }
        this.zzayn = null;
        this.zzayh = null;
    }

    public final void zzmt() {
        zzn(false);
    }

    public final void zzmu() {
        zzn(true);
    }
}
