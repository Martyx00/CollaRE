package com.google.android.gms.internal.measurement;

import android.annotation.TargetApi;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.PersistableBundle;
import com.google.android.gms.common.util.Clock;

public final class zzjp extends zzjs {
    private final zzep zzarp;
    private final AlarmManager zzyh = ((AlarmManager) getContext().getSystemService("alarm"));
    private Integer zzyi;

    protected zzjp(zzjt zzjt) {
        super(zzjt);
        this.zzarp = new zzjq(this, zzjt.zzlm(), zzjt);
    }

    private final int getJobId() {
        if (this.zzyi == null) {
            String valueOf = String.valueOf(getContext().getPackageName());
            this.zzyi = Integer.valueOf((valueOf.length() != 0 ? "measurement".concat(valueOf) : new String("measurement")).hashCode());
        }
        return this.zzyi.intValue();
    }

    private final PendingIntent zzek() {
        Intent className = new Intent().setClassName(getContext(), "com.google.android.gms.measurement.AppMeasurementReceiver");
        className.setAction("com.google.android.gms.measurement.UPLOAD");
        return PendingIntent.getBroadcast(getContext(), 0, className, 0);
    }

    @TargetApi(24)
    private final void zzkx() {
        zzgi().zzjc().zzg("Cancelling job. JobID", Integer.valueOf(getJobId()));
        ((JobScheduler) getContext().getSystemService("jobscheduler")).cancel(getJobId());
    }

    public final void cancel() {
        zzch();
        this.zzyh.cancel(zzek());
        this.zzarp.cancel();
        if (Build.VERSION.SDK_INT >= 24) {
            zzkx();
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzhk, com.google.android.gms.internal.measurement.zzhi
    public final /* bridge */ /* synthetic */ Context getContext() {
        return super.getContext();
    }

    @Override // com.google.android.gms.internal.measurement.zzhi
    public final /* bridge */ /* synthetic */ void zzab() {
        super.zzab();
    }

    @Override // com.google.android.gms.internal.measurement.zzhk, com.google.android.gms.internal.measurement.zzhi
    public final /* bridge */ /* synthetic */ Clock zzbt() {
        return super.zzbt();
    }

    @Override // com.google.android.gms.internal.measurement.zzhi
    public final /* bridge */ /* synthetic */ void zzfu() {
        super.zzfu();
    }

    @Override // com.google.android.gms.internal.measurement.zzhi
    public final /* bridge */ /* synthetic */ void zzfv() {
        super.zzfv();
    }

    @Override // com.google.android.gms.internal.measurement.zzhi
    public final /* bridge */ /* synthetic */ void zzfw() {
        super.zzfw();
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
    @Override // com.google.android.gms.internal.measurement.zzjs
    public final boolean zzgn() {
        this.zzyh.cancel(zzek());
        if (Build.VERSION.SDK_INT < 24) {
            return false;
        }
        zzkx();
        return false;
    }

    public final void zzh(long j) {
        zzch();
        zzgl();
        if (!zzgd.zza(getContext())) {
            zzgi().zzjb().log("Receiver not registered/enabled");
        }
        zzgl();
        if (!zzkd.zza(getContext(), false)) {
            zzgi().zzjb().log("Service not registered/enabled");
        }
        cancel();
        long elapsedRealtime = zzbt().elapsedRealtime() + j;
        if (j < Math.max(0L, zzez.zzajb.get().longValue()) && !this.zzarp.zzef()) {
            zzgi().zzjc().log("Scheduling upload with DelayedRunnable");
            this.zzarp.zzh(j);
        }
        zzgl();
        if (Build.VERSION.SDK_INT >= 24) {
            zzgi().zzjc().log("Scheduling upload with JobScheduler");
            JobInfo.Builder builder = new JobInfo.Builder(getJobId(), new ComponentName(getContext(), "com.google.android.gms.measurement.AppMeasurementJobService"));
            builder.setMinimumLatency(j);
            builder.setOverrideDeadline(j << 1);
            PersistableBundle persistableBundle = new PersistableBundle();
            persistableBundle.putString("action", "com.google.android.gms.measurement.UPLOAD");
            builder.setExtras(persistableBundle);
            JobInfo build = builder.build();
            zzgi().zzjc().zzg("Scheduling job. JobID", Integer.valueOf(getJobId()));
            ((JobScheduler) getContext().getSystemService("jobscheduler")).schedule(build);
            return;
        }
        zzgi().zzjc().log("Scheduling upload with AlarmManager");
        this.zzyh.setInexactRepeating(2, elapsedRealtime, Math.max(zzez.zzaiw.get().longValue(), j), zzek());
    }

    @Override // com.google.android.gms.internal.measurement.zzjr
    public final /* bridge */ /* synthetic */ zzjz zzjf() {
        return super.zzjf();
    }

    @Override // com.google.android.gms.internal.measurement.zzjr
    public final /* bridge */ /* synthetic */ zzed zzjg() {
        return super.zzjg();
    }

    @Override // com.google.android.gms.internal.measurement.zzjr
    public final /* bridge */ /* synthetic */ zzek zzjh() {
        return super.zzjh();
    }
}
