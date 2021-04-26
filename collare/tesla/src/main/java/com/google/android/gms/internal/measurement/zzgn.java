package com.google.android.gms.internal.measurement;

import android.app.Application;
import android.content.Context;
import android.text.TextUtils;
import com.google.android.gms.common.api.internal.GoogleServices;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.DefaultClock;
import com.google.android.gms.common.wrappers.Wrappers;
import com.google.android.gms.measurement.AppMeasurement;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.concurrent.atomic.AtomicReference;

public class zzgn implements zzhk {
    private static volatile zzgn zzaoc;
    private final Clock clock;
    private final long zzaga;
    private final zzee zzahs;
    private final String zzaod;
    private final zzeh zzaoe;
    private final zzft zzaof;
    private final zzfi zzaog;
    private final zzgi zzaoh;
    private final zzjj zzaoi;
    private final AppMeasurement zzaoj;
    private final FirebaseAnalytics zzaok;
    private final zzkd zzaol;
    private final zzfg zzaom;
    private final zzih zzaon;
    private final zzhm zzaoo;
    private final zzdu zzaop;
    private zzfe zzaoq;
    private zzik zzaor;
    private zzer zzaos;
    private zzfd zzaot;
    private zzfz zzaou;
    private Boolean zzaov;
    private long zzaow;
    private int zzaox;
    private int zzaoy;
    private final Context zzqx;
    private boolean zzvn = false;

    private zzgn(zzhl zzhl) {
        String str;
        zzfk zzfk;
        Preconditions.checkNotNull(zzhl);
        this.zzahs = new zzee(zzhl.zzqx);
        zzez.zza(this.zzahs);
        this.zzqx = zzhl.zzqx;
        this.zzaod = zzhl.zzaod;
        zzwx.init(this.zzqx);
        this.clock = DefaultClock.getInstance();
        this.zzaga = this.clock.currentTimeMillis();
        this.zzaoe = new zzeh(this);
        zzft zzft = new zzft(this);
        zzft.zzm();
        this.zzaof = zzft;
        zzfi zzfi = new zzfi(this);
        zzfi.zzm();
        this.zzaog = zzfi;
        zzkd zzkd = new zzkd(this);
        zzkd.zzm();
        this.zzaol = zzkd;
        zzfg zzfg = new zzfg(this);
        zzfg.zzm();
        this.zzaom = zzfg;
        this.zzaop = new zzdu(this);
        zzih zzih = new zzih(this);
        zzih.zzm();
        this.zzaon = zzih;
        zzhm zzhm = new zzhm(this);
        zzhm.zzm();
        this.zzaoo = zzhm;
        this.zzaoj = new AppMeasurement(this);
        this.zzaok = new FirebaseAnalytics(this);
        zzjj zzjj = new zzjj(this);
        zzjj.zzm();
        this.zzaoi = zzjj;
        zzgi zzgi = new zzgi(this);
        zzgi.zzm();
        this.zzaoh = zzgi;
        zzee zzee = this.zzahs;
        if (this.zzqx.getApplicationContext() instanceof Application) {
            zzhm zzfy = zzfy();
            if (zzfy.getContext().getApplicationContext() instanceof Application) {
                Application application = (Application) zzfy.getContext().getApplicationContext();
                if (zzfy.zzapl == null) {
                    zzfy.zzapl = new zzif(zzfy, null);
                }
                application.unregisterActivityLifecycleCallbacks(zzfy.zzapl);
                application.registerActivityLifecycleCallbacks(zzfy.zzapl);
                zzfk = zzfy.zzgi().zzjc();
                str = "Registered activity lifecycle callback";
            }
            this.zzaoh.zzc(new zzgo(this, zzhl));
        }
        zzfk = zzgi().zziy();
        str = "Application context is not an Application";
        zzfk.log(str);
        this.zzaoh.zzc(new zzgo(this, zzhl));
    }

    public static zzgn zza(Context context, String str, String str2) {
        Preconditions.checkNotNull(context);
        Preconditions.checkNotNull(context.getApplicationContext());
        if (zzaoc == null) {
            synchronized (zzgn.class) {
                if (zzaoc == null) {
                    zzaoc = new zzgn(new zzhl(context, null));
                }
            }
        }
        return zzaoc;
    }

    private static void zza(zzdz zzdz) {
        if (zzdz == null) {
            throw new IllegalStateException("Component not created");
        } else if (!zzdz.isInitialized()) {
            String valueOf = String.valueOf(zzdz.getClass());
            StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 27);
            sb.append("Component not initialized: ");
            sb.append(valueOf);
            throw new IllegalStateException(sb.toString());
        }
    }

    private static void zza(zzhi zzhi) {
        if (zzhi == null) {
            throw new IllegalStateException("Component not created");
        }
    }

    private static void zza(zzhj zzhj) {
        if (zzhj == null) {
            throw new IllegalStateException("Component not created");
        } else if (!zzhj.isInitialized()) {
            String valueOf = String.valueOf(zzhj.getClass());
            StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 27);
            sb.append("Component not initialized: ");
            sb.append(valueOf);
            throw new IllegalStateException(sb.toString());
        }
    }

    /* access modifiers changed from: private */
    public final void zza(zzhl zzhl) {
        String str;
        zzfk zzfk;
        zzgh().zzab();
        zzeh.zzhn();
        zzer zzer = new zzer(this);
        zzer.zzm();
        this.zzaos = zzer;
        zzfd zzfd = new zzfd(this);
        zzfd.zzm();
        this.zzaot = zzfd;
        zzfe zzfe = new zzfe(this);
        zzfe.zzm();
        this.zzaoq = zzfe;
        zzik zzik = new zzik(this);
        zzik.zzm();
        this.zzaor = zzik;
        this.zzaol.zzgm();
        this.zzaof.zzgm();
        this.zzaou = new zzfz(this);
        this.zzaot.zzgm();
        zzgi().zzja().zzg("App measurement is starting up, version", Long.valueOf(this.zzaoe.zzgw()));
        zzee zzee = this.zzahs;
        zzgi().zzja().log("To enable debug logging run: adb shell setprop log.tag.FA VERBOSE");
        zzee zzee2 = this.zzahs;
        String zzah = zzfd.zzah();
        if (zzgg().zzcn(zzah)) {
            zzfk = zzgi().zzja();
            str = "Faster debug mode event logging enabled. To disable, run:\n  adb shell setprop debug.firebase.analytics.app .none.";
        } else {
            zzfk = zzgi().zzja();
            String valueOf = String.valueOf(zzah);
            str = valueOf.length() != 0 ? "To enable faster debug mode event logging run:\n  adb shell setprop debug.firebase.analytics.app ".concat(valueOf) : new String("To enable faster debug mode event logging run:\n  adb shell setprop debug.firebase.analytics.app ");
        }
        zzfk.log(str);
        zzgi().zzjb().log("Debug-level message logging enabled");
        if (this.zzaox != this.zzaoy) {
            zzgi().zziv().zze("Not all components initialized", Integer.valueOf(this.zzaox), Integer.valueOf(this.zzaoy));
        }
        this.zzvn = true;
    }

    private final void zzch() {
        if (!this.zzvn) {
            throw new IllegalStateException("AppMeasurement is not initialized");
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzhk
    public final Context getContext() {
        return this.zzqx;
    }

    public final boolean isEnabled() {
        zzgh().zzab();
        zzch();
        boolean z = false;
        if (this.zzaoe.zzho()) {
            return false;
        }
        Boolean zzhp = this.zzaoe.zzhp();
        if (zzhp != null) {
            z = zzhp.booleanValue();
        } else if (!GoogleServices.isMeasurementExplicitlyDisabled()) {
            z = true;
        }
        return zzgj().zzg(z);
    }

    /* access modifiers changed from: protected */
    public final void start() {
        zzgh().zzab();
        if (zzgj().zzalt.get() == 0) {
            zzgj().zzalt.set(this.clock.currentTimeMillis());
        }
        if (Long.valueOf(zzgj().zzaly.get()).longValue() == 0) {
            zzgi().zzjc().zzg("Persisting first open", Long.valueOf(this.zzaga));
            zzgj().zzaly.set(this.zzaga);
        }
        if (zzkg()) {
            zzee zzee = this.zzahs;
            if (!TextUtils.isEmpty(zzfz().getGmpAppId())) {
                String zzjj = zzgj().zzjj();
                if (zzjj == null) {
                    zzgj().zzbt(zzfz().getGmpAppId());
                } else if (!zzjj.equals(zzfz().getGmpAppId())) {
                    zzgi().zzja().log("Rechecking which service to use due to a GMP App Id change");
                    zzgj().zzjm();
                    this.zzaor.disconnect();
                    this.zzaor.zzdf();
                    zzgj().zzbt(zzfz().getGmpAppId());
                    zzgj().zzaly.set(this.zzaga);
                    zzgj().zzama.zzbv(null);
                }
            }
            zzfy().zzbu(zzgj().zzama.zzjq());
            zzee zzee2 = this.zzahs;
            if (!TextUtils.isEmpty(zzfz().getGmpAppId())) {
                boolean isEnabled = isEnabled();
                if (!zzgj().zzjp() && !this.zzaoe.zzho()) {
                    zzgj().zzh(!isEnabled);
                }
                if (!this.zzaoe.zzbc(zzfz().zzah()) || isEnabled) {
                    zzfy().zzkm();
                }
                zzga().zza(new AtomicReference<>());
            }
        } else if (isEnabled()) {
            if (!zzgg().zzx("android.permission.INTERNET")) {
                zzgi().zziv().log("App is missing INTERNET permission");
            }
            if (!zzgg().zzx("android.permission.ACCESS_NETWORK_STATE")) {
                zzgi().zziv().log("App is missing ACCESS_NETWORK_STATE permission");
            }
            zzee zzee3 = this.zzahs;
            if (!Wrappers.packageManager(this.zzqx).isCallerInstantApp() && !this.zzaoe.zzhu()) {
                if (!zzgd.zza(this.zzqx)) {
                    zzgi().zziv().log("AppMeasurementReceiver not registered/enabled");
                }
                if (!zzkd.zza(this.zzqx, false)) {
                    zzgi().zziv().log("AppMeasurementService not registered/enabled");
                }
            }
            zzgi().zziv().log("Uploading is not possible. App measurement disabled");
        }
    }

    /* access modifiers changed from: package-private */
    public final void zzb(zzdz zzdz) {
        this.zzaox++;
    }

    /* access modifiers changed from: package-private */
    public final void zzb(zzhj zzhj) {
        this.zzaox++;
    }

    @Override // com.google.android.gms.internal.measurement.zzhk
    public final Clock zzbt() {
        return this.clock;
    }

    /* access modifiers changed from: package-private */
    public final void zzfu() {
        zzee zzee = this.zzahs;
        throw new IllegalStateException("Unexpected call on client side");
    }

    /* access modifiers changed from: package-private */
    public final void zzfv() {
        zzee zzee = this.zzahs;
    }

    public final zzdu zzfx() {
        zzdu zzdu = this.zzaop;
        if (zzdu != null) {
            return zzdu;
        }
        throw new IllegalStateException("Component not created");
    }

    public final zzhm zzfy() {
        zza((zzdz) this.zzaoo);
        return this.zzaoo;
    }

    public final zzfd zzfz() {
        zza((zzdz) this.zzaot);
        return this.zzaot;
    }

    public final zzik zzga() {
        zza((zzdz) this.zzaor);
        return this.zzaor;
    }

    public final zzih zzgb() {
        zza((zzdz) this.zzaon);
        return this.zzaon;
    }

    public final zzfe zzgc() {
        zza((zzdz) this.zzaoq);
        return this.zzaoq;
    }

    public final zzjj zzgd() {
        zza((zzdz) this.zzaoi);
        return this.zzaoi;
    }

    public final zzer zzge() {
        zza((zzhj) this.zzaos);
        return this.zzaos;
    }

    public final zzfg zzgf() {
        zza((zzhi) this.zzaom);
        return this.zzaom;
    }

    public final zzkd zzgg() {
        zza((zzhi) this.zzaol);
        return this.zzaol;
    }

    @Override // com.google.android.gms.internal.measurement.zzhk
    public final zzgi zzgh() {
        zza((zzhj) this.zzaoh);
        return this.zzaoh;
    }

    @Override // com.google.android.gms.internal.measurement.zzhk
    public final zzfi zzgi() {
        zza((zzhj) this.zzaog);
        return this.zzaog;
    }

    public final zzft zzgj() {
        zza((zzhi) this.zzaof);
        return this.zzaof;
    }

    public final zzeh zzgk() {
        return this.zzaoe;
    }

    @Override // com.google.android.gms.internal.measurement.zzhk
    public final zzee zzgl() {
        return this.zzahs;
    }

    public final zzfi zzjy() {
        zzfi zzfi = this.zzaog;
        if (zzfi == null || !zzfi.isInitialized()) {
            return null;
        }
        return this.zzaog;
    }

    public final zzfz zzjz() {
        return this.zzaou;
    }

    /* access modifiers changed from: package-private */
    public final zzgi zzka() {
        return this.zzaoh;
    }

    public final AppMeasurement zzkb() {
        return this.zzaoj;
    }

    public final FirebaseAnalytics zzkc() {
        return this.zzaok;
    }

    public final String zzkd() {
        return this.zzaod;
    }

    /* access modifiers changed from: package-private */
    public final long zzke() {
        Long valueOf = Long.valueOf(zzgj().zzaly.get());
        return valueOf.longValue() == 0 ? this.zzaga : Math.min(this.zzaga, valueOf.longValue());
    }

    /* access modifiers changed from: package-private */
    public final void zzkf() {
        this.zzaoy++;
    }

    /* access modifiers changed from: protected */
    public final boolean zzkg() {
        zzch();
        zzgh().zzab();
        Boolean bool = this.zzaov;
        if (bool == null || this.zzaow == 0 || (bool != null && !bool.booleanValue() && Math.abs(this.clock.elapsedRealtime() - this.zzaow) > 1000)) {
            this.zzaow = this.clock.elapsedRealtime();
            zzee zzee = this.zzahs;
            boolean z = false;
            if (zzgg().zzx("android.permission.INTERNET") && zzgg().zzx("android.permission.ACCESS_NETWORK_STATE") && (Wrappers.packageManager(this.zzqx).isCallerInstantApp() || this.zzaoe.zzhu() || (zzgd.zza(this.zzqx) && zzkd.zza(this.zzqx, false)))) {
                z = true;
            }
            this.zzaov = Boolean.valueOf(z);
            if (this.zzaov.booleanValue()) {
                this.zzaov = Boolean.valueOf(zzgg().zzck(zzfz().getGmpAppId()));
            }
        }
        return this.zzaov.booleanValue();
    }
}
