package com.google.android.gms.internal.measurement;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Parcel;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.VisibleForTesting;

public final class zzfe extends zzdz {
    private final zzff zzakf = new zzff(this, getContext(), "google_app_measurement_local.db");
    private boolean zzakg;

    zzfe(zzgn zzgn) {
        super(zzgn);
    }

    @VisibleForTesting
    private final SQLiteDatabase getWritableDatabase() {
        if (this.zzakg) {
            return null;
        }
        SQLiteDatabase writableDatabase = this.zzakf.getWritableDatabase();
        if (writableDatabase != null) {
            return writableDatabase;
        }
        this.zzakg = true;
        return null;
    }

    /* JADX WARNING: Removed duplicated region for block: B:48:0x00c6 A[SYNTHETIC, Splitter:B:48:0x00c6] */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x00e0  */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x00e5  */
    /* JADX WARNING: Removed duplicated region for block: B:67:0x00f5  */
    /* JADX WARNING: Removed duplicated region for block: B:74:0x0111  */
    /* JADX WARNING: Removed duplicated region for block: B:80:0x0121  */
    /* JADX WARNING: Removed duplicated region for block: B:82:0x0126  */
    /* JADX WARNING: Removed duplicated region for block: B:89:0x0117 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:91:0x0117 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:93:0x0117 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final boolean zza(int r17, byte[] r18) {
        /*
        // Method dump skipped, instructions count: 313
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzfe.zza(int, byte[]):boolean");
    }

    @Override // com.google.android.gms.internal.measurement.zzhk, com.google.android.gms.internal.measurement.zzhi
    public final /* bridge */ /* synthetic */ Context getContext() {
        return super.getContext();
    }

    public final void resetAnalyticsData() {
        zzfv();
        zzab();
        try {
            int delete = getWritableDatabase().delete("messages", null, null) + 0;
            if (delete > 0) {
                zzgi().zzjc().zzg("Reset local analytics data. records", Integer.valueOf(delete));
            }
        } catch (SQLiteException e) {
            zzgi().zziv().zzg("Error resetting local analytics data. error", e);
        }
    }

    public final boolean zza(zzex zzex) {
        Parcel obtain = Parcel.obtain();
        zzex.writeToParcel(obtain, 0);
        byte[] marshall = obtain.marshall();
        obtain.recycle();
        if (marshall.length <= 131072) {
            return zza(0, marshall);
        }
        zzgi().zziy().log("Event is too long for local database. Sending event directly to service");
        return false;
    }

    public final boolean zza(zzka zzka) {
        Parcel obtain = Parcel.obtain();
        zzka.writeToParcel(obtain, 0);
        byte[] marshall = obtain.marshall();
        obtain.recycle();
        if (marshall.length <= 131072) {
            return zza(1, marshall);
        }
        zzgi().zziy().log("User property too long for local database. Sending directly to service");
        return false;
    }

    @Override // com.google.android.gms.internal.measurement.zzhi, com.google.android.gms.internal.measurement.zzdy
    public final /* bridge */ /* synthetic */ void zzab() {
        super.zzab();
    }

    @Override // com.google.android.gms.internal.measurement.zzhk, com.google.android.gms.internal.measurement.zzhi
    public final /* bridge */ /* synthetic */ Clock zzbt() {
        return super.zzbt();
    }

    public final boolean zzc(zzef zzef) {
        zzgg();
        byte[] zza = zzkd.zza(zzef);
        if (zza.length <= 131072) {
            return zza(2, zza);
        }
        zzgi().zziy().log("Conditional user property too long for local database. Sending directly to service");
        return false;
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

    /* JADX INFO: finally extract failed */
    /* JADX WARNING: Can't wrap try/catch for region: R(3:64|65|66) */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0039, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x003a, code lost:
        r9 = null;
        r4 = r15;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x003e, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x003f, code lost:
        r9 = null;
        r4 = r15;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0043, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0044, code lost:
        r9 = null;
        r4 = r15;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x009d, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:?, code lost:
        zzgi().zziv().log("Failed to load event from local database");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x00b0, code lost:
        r12.recycle();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x00b3, code lost:
        throw r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x00cd, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:?, code lost:
        zzgi().zziv().log("Failed to load user property from local database");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x00dc, code lost:
        r12.recycle();
        r0 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x00e3, code lost:
        r12.recycle();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:0x00e6, code lost:
        throw r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:63:0x0100, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:65:?, code lost:
        zzgi().zziv().log("Failed to load user property from local database");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:66:0x010f, code lost:
        r12.recycle();
        r0 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:68:0x0116, code lost:
        r12.recycle();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:69:0x0119, code lost:
        throw r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:91:0x0171, code lost:
        r4 = r15;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:41:0x009f */
    /* JADX WARNING: Missing exception handler attribute for start block: B:52:0x00cf */
    /* JADX WARNING: Missing exception handler attribute for start block: B:64:0x0102 */
    /* JADX WARNING: Removed duplicated region for block: B:103:0x0181 A[SYNTHETIC, Splitter:B:103:0x0181] */
    /* JADX WARNING: Removed duplicated region for block: B:109:0x019b  */
    /* JADX WARNING: Removed duplicated region for block: B:116:0x01ab  */
    /* JADX WARNING: Removed duplicated region for block: B:123:0x01c8  */
    /* JADX WARNING: Removed duplicated region for block: B:128:0x01d6  */
    /* JADX WARNING: Removed duplicated region for block: B:130:0x01db  */
    /* JADX WARNING: Removed duplicated region for block: B:137:0x01ce A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:138:0x01ce A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:140:0x01ce A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:92:? A[ExcHandler: SQLiteDatabaseLockedException (unused android.database.sqlite.SQLiteDatabaseLockedException), SYNTHETIC, Splitter:B:12:0x0031] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.util.List<com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable> zzp(int r19) {
        /*
        // Method dump skipped, instructions count: 493
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzfe.zzp(int):java.util.List");
    }
}
