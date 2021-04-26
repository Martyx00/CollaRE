package com.google.android.gms.internal.measurement;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.util.a;
import android.text.TextUtils;
import android.util.Pair;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.common.wrappers.Wrappers;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import org.spongycastle.asn1.cmp.PKIFailureInfo;

public class zzjt implements zzhk {
    private static volatile zzjt zzarr;
    private final zzgn zzacv;
    private zzgh zzars;
    private zzfm zzart;
    private zzek zzaru;
    private zzfr zzarv;
    private zzjp zzarw;
    private zzed zzarx;
    private final zzjz zzary;
    private boolean zzarz;
    @VisibleForTesting
    private long zzasa;
    private List<Runnable> zzasb;
    private int zzasc;
    private int zzasd;
    private boolean zzase;
    private boolean zzasf;
    private boolean zzasg;
    private FileLock zzash;
    private FileChannel zzasi;
    private List<Long> zzasj;
    private List<Long> zzask;
    private long zzasl;
    private boolean zzvn;

    /* access modifiers changed from: package-private */
    public class zza implements zzem {
        zzku zzasp;
        List<Long> zzasq;
        List<zzkr> zzasr;
        private long zzass;

        private zza() {
        }

        /* synthetic */ zza(zzjt zzjt, zzju zzju) {
            this();
        }

        private static long zza(zzkr zzkr) {
            return ((zzkr.zzavb.longValue() / 1000) / 60) / 60;
        }

        @Override // com.google.android.gms.internal.measurement.zzem
        public final boolean zza(long j, zzkr zzkr) {
            Preconditions.checkNotNull(zzkr);
            if (this.zzasr == null) {
                this.zzasr = new ArrayList();
            }
            if (this.zzasq == null) {
                this.zzasq = new ArrayList();
            }
            if (this.zzasr.size() > 0 && zza(this.zzasr.get(0)) != zza(zzkr)) {
                return false;
            }
            long zzwb = this.zzass + ((long) zzkr.zzwb());
            if (zzwb >= ((long) Math.max(0, zzez.zzaim.get().intValue()))) {
                return false;
            }
            this.zzass = zzwb;
            this.zzasr.add(zzkr);
            this.zzasq.add(Long.valueOf(j));
            return this.zzasr.size() < Math.max(1, zzez.zzain.get().intValue());
        }

        @Override // com.google.android.gms.internal.measurement.zzem
        public final void zzb(zzku zzku) {
            Preconditions.checkNotNull(zzku);
            this.zzasp = zzku;
        }
    }

    private zzjt(zzjy zzjy) {
        this(zzjy, null);
    }

    private zzjt(zzjy zzjy, zzgn zzgn) {
        this.zzvn = false;
        Preconditions.checkNotNull(zzjy);
        this.zzacv = zzgn.zza(zzjy.zzqx, null, null);
        this.zzasl = -1;
        zzjz zzjz = new zzjz(this);
        zzjz.zzm();
        this.zzary = zzjz;
        zzfm zzfm = new zzfm(this);
        zzfm.zzm();
        this.zzart = zzfm;
        zzgh zzgh = new zzgh(this);
        zzgh.zzm();
        this.zzars = zzgh;
        this.zzacv.zzgh().zzc(new zzju(this, zzjy));
    }

    @VisibleForTesting
    private final int zza(FileChannel fileChannel) {
        zzab();
        if (fileChannel == null || !fileChannel.isOpen()) {
            this.zzacv.zzgi().zziv().log("Bad channel to read from");
            return 0;
        }
        ByteBuffer allocate = ByteBuffer.allocate(4);
        try {
            fileChannel.position(0L);
            int read = fileChannel.read(allocate);
            if (read != 4) {
                if (read != -1) {
                    this.zzacv.zzgi().zziy().zzg("Unexpected data length. Bytes read", Integer.valueOf(read));
                }
                return 0;
            }
            allocate.flip();
            return allocate.getInt();
        } catch (IOException e) {
            this.zzacv.zzgi().zziv().zzg("Failed to read from channel", e);
            return 0;
        }
    }

    private final zzeb zza(Context context, String str, String str2, boolean z, boolean z2, boolean z3, long j) {
        String str3;
        int i;
        String str4 = "Unknown";
        PackageManager packageManager = context.getPackageManager();
        if (packageManager == null) {
            this.zzacv.zzgi().zziv().log("PackageManager is null, can not log app install information");
            return null;
        }
        try {
            str4 = packageManager.getInstallerPackageName(str);
        } catch (IllegalArgumentException unused) {
            this.zzacv.zzgi().zziv().zzg("Error retrieving installer package name. appId", zzfi.zzbp(str));
        }
        if (str4 == null) {
            str4 = "manual_install";
        } else if ("com.android.vending".equals(str4)) {
            str4 = "";
        }
        try {
            PackageInfo packageInfo = Wrappers.packageManager(context).getPackageInfo(str, 0);
            if (packageInfo != null) {
                CharSequence applicationLabel = Wrappers.packageManager(context).getApplicationLabel(str);
                if (!TextUtils.isEmpty(applicationLabel)) {
                    applicationLabel.toString();
                }
                str3 = packageInfo.versionName;
                i = packageInfo.versionCode;
            } else {
                str3 = "Unknown";
                i = PKIFailureInfo.systemUnavail;
            }
            this.zzacv.zzgl();
            return new zzeb(str, str2, str3, (long) i, str4, this.zzacv.zzgk().zzgw(), this.zzacv.zzgg().zzd(context, str), (String) null, z, false, "", 0L, this.zzacv.zzgk().zzbd(str) ? j : 0, 0, z2, z3, false);
        } catch (PackageManager.NameNotFoundException unused2) {
            this.zzacv.zzgi().zziv().zze("Error retrieving newly installed package info. appId, appName", zzfi.zzbp(str), "Unknown");
            return null;
        }
    }

    private static void zza(zzjs zzjs) {
        if (zzjs == null) {
            throw new IllegalStateException("Upload Component not created");
        } else if (!zzjs.isInitialized()) {
            String valueOf = String.valueOf(zzjs.getClass());
            StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 27);
            sb.append("Component not initialized: ");
            sb.append(valueOf);
            throw new IllegalStateException(sb.toString());
        }
    }

    /* access modifiers changed from: private */
    public final void zza(zzjy zzjy) {
        this.zzacv.zzgh().zzab();
        zzek zzek = new zzek(this);
        zzek.zzm();
        this.zzaru = zzek;
        this.zzacv.zzgk().zza(this.zzars);
        zzed zzed = new zzed(this);
        zzed.zzm();
        this.zzarx = zzed;
        zzjp zzjp = new zzjp(this);
        zzjp.zzm();
        this.zzarw = zzjp;
        this.zzarv = new zzfr(this);
        if (this.zzasc != this.zzasd) {
            this.zzacv.zzgi().zziv().zze("Not all upload components initialized", Integer.valueOf(this.zzasc), Integer.valueOf(this.zzasd));
        }
        this.zzvn = true;
    }

    @VisibleForTesting
    private final boolean zza(int i, FileChannel fileChannel) {
        zzab();
        if (fileChannel == null || !fileChannel.isOpen()) {
            this.zzacv.zzgi().zziv().log("Bad channel to read from");
            return false;
        }
        ByteBuffer allocate = ByteBuffer.allocate(4);
        allocate.putInt(i);
        allocate.flip();
        try {
            fileChannel.truncate(0L);
            fileChannel.write(allocate);
            fileChannel.force(true);
            if (fileChannel.size() != 4) {
                this.zzacv.zzgi().zziv().zzg("Error writing to channel. Bytes written", Long.valueOf(fileChannel.size()));
            }
            return true;
        } catch (IOException e) {
            this.zzacv.zzgi().zziv().zzg("Failed to write to channel", e);
            return false;
        }
    }

    private final boolean zza(String str, zzex zzex) {
        long j;
        zzkc zzkc;
        String string = zzex.zzahg.getString(FirebaseAnalytics.b.CURRENCY);
        if (FirebaseAnalytics.a.ECOMMERCE_PURCHASE.equals(zzex.name)) {
            double doubleValue = zzex.zzahg.zzbk(FirebaseAnalytics.b.VALUE).doubleValue() * 1000000.0d;
            if (doubleValue == 0.0d) {
                doubleValue = ((double) zzex.zzahg.getLong(FirebaseAnalytics.b.VALUE).longValue()) * 1000000.0d;
            }
            if (doubleValue > 9.223372036854776E18d || doubleValue < -9.223372036854776E18d) {
                this.zzacv.zzgi().zziy().zze("Data lost. Currency value is too big. appId", zzfi.zzbp(str), Double.valueOf(doubleValue));
                return false;
            }
            j = Math.round(doubleValue);
        } else {
            j = zzex.zzahg.getLong(FirebaseAnalytics.b.VALUE).longValue();
        }
        if (!TextUtils.isEmpty(string)) {
            String upperCase = string.toUpperCase(Locale.US);
            if (upperCase.matches("[A-Z]{3}")) {
                String valueOf = String.valueOf("_ltv_");
                String valueOf2 = String.valueOf(upperCase);
                String concat = valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf);
                zzkc zzh = zzjh().zzh(str, concat);
                if (zzh == null || !(zzh.value instanceof Long)) {
                    zzek zzjh = zzjh();
                    int zzb = this.zzacv.zzgk().zzb(str, zzez.zzaji) - 1;
                    Preconditions.checkNotEmpty(str);
                    zzjh.zzab();
                    zzjh.zzch();
                    try {
                        zzjh.getWritableDatabase().execSQL("delete from user_attributes where app_id=? and name in (select name from user_attributes where app_id=? and name like '_ltv_%' order by set_timestamp desc limit ?,10);", new String[]{str, str, String.valueOf(zzb)});
                    } catch (SQLiteException e) {
                        zzjh.zzgi().zziv().zze("Error pruning currencies. appId", zzfi.zzbp(str), e);
                    }
                    zzkc = new zzkc(str, zzex.origin, concat, this.zzacv.zzbt().currentTimeMillis(), Long.valueOf(j));
                } else {
                    zzkc = new zzkc(str, zzex.origin, concat, this.zzacv.zzbt().currentTimeMillis(), Long.valueOf(((Long) zzh.value).longValue() + j));
                }
                if (!zzjh().zza(zzkc)) {
                    this.zzacv.zzgi().zziv().zzd("Too many unique user properties are set. Ignoring user property. appId", zzfi.zzbp(str), this.zzacv.zzgf().zzbo(zzkc.name), zzkc.value);
                    this.zzacv.zzgg().zza(str, 9, (String) null, (String) null, 0);
                }
            }
        }
        return true;
    }

    private final zzkp[] zza(String str, zzkx[] zzkxArr, zzkr[] zzkrArr) {
        Preconditions.checkNotEmpty(str);
        return zzjg().zza(str, zzkrArr, zzkxArr);
    }

    private final void zzab() {
        this.zzacv.zzgh().zzab();
    }

    private final void zzb(zzea zzea) {
        a aVar;
        zzab();
        if (TextUtils.isEmpty(zzea.getGmpAppId())) {
            zzb(zzea.zzah(), 204, null, null, null);
            return;
        }
        zzeh zzgk = this.zzacv.zzgk();
        String gmpAppId = zzea.getGmpAppId();
        String appInstanceId = zzea.getAppInstanceId();
        Uri.Builder builder = new Uri.Builder();
        Uri.Builder encodedAuthority = builder.scheme(zzez.zzaii.get()).encodedAuthority(zzez.zzaij.get());
        String valueOf = String.valueOf(gmpAppId);
        encodedAuthority.path(valueOf.length() != 0 ? "config/app/".concat(valueOf) : new String("config/app/")).appendQueryParameter("app_instance_id", appInstanceId).appendQueryParameter("platform", io.a.a.a.a.b.a.ANDROID_CLIENT_TYPE).appendQueryParameter("gmp_version", String.valueOf(zzgk.zzgw()));
        String uri = builder.build().toString();
        try {
            URL url = new URL(uri);
            this.zzacv.zzgi().zzjc().zzg("Fetching remote configuration", zzea.zzah());
            zzkn zzbx = zzky().zzbx(zzea.zzah());
            String zzby = zzky().zzby(zzea.zzah());
            if (zzbx == null || TextUtils.isEmpty(zzby)) {
                aVar = null;
            } else {
                a aVar2 = new a();
                aVar2.put("If-Modified-Since", zzby);
                aVar = aVar2;
            }
            this.zzase = true;
            zzfm zzkz = zzkz();
            String zzah = zzea.zzah();
            zzjw zzjw = new zzjw(this);
            zzkz.zzab();
            zzkz.zzch();
            Preconditions.checkNotNull(url);
            Preconditions.checkNotNull(zzjw);
            zzkz.zzgh().zzd(new zzfq(zzkz, zzah, url, null, aVar, zzjw));
        } catch (MalformedURLException unused) {
            this.zzacv.zzgi().zziv().zze("Failed to parse config URL. Not fetching. appId", zzfi.zzbp(zzea.zzah()), uri);
        }
    }

    private final Boolean zzc(zzea zzea) {
        try {
            if (zzea.zzgu() != -2147483648L) {
                if (zzea.zzgu() == ((long) Wrappers.packageManager(this.zzacv.getContext()).getPackageInfo(zzea.zzah(), 0).versionCode)) {
                    return true;
                }
            } else {
                String str = Wrappers.packageManager(this.zzacv.getContext()).getPackageInfo(zzea.zzah(), 0).versionName;
                if (zzea.zzag() != null && zzea.zzag().equals(str)) {
                    return true;
                }
            }
            return false;
        } catch (PackageManager.NameNotFoundException unused) {
            return null;
        }
    }

    private final void zzc(zzex zzex, zzeb zzeb) {
        zzet zzet;
        zzea zzbf;
        Preconditions.checkNotNull(zzeb);
        Preconditions.checkNotEmpty(zzeb.packageName);
        long nanoTime = System.nanoTime();
        zzab();
        zzlc();
        String str = zzeb.packageName;
        if (zzjf().zzd(zzex, zzeb)) {
            if (!zzeb.zzafk) {
                zzg(zzeb);
            } else if (zzky().zzn(str, zzex.name)) {
                this.zzacv.zzgi().zziy().zze("Dropping blacklisted event. appId", zzfi.zzbp(str), this.zzacv.zzgf().zzbm(zzex.name));
                boolean z = zzky().zzcb(str) || zzky().zzcc(str);
                if (!z && !"_err".equals(zzex.name)) {
                    this.zzacv.zzgg().zza(str, 11, "_ev", zzex.name, 0);
                }
                if (z && (zzbf = zzjh().zzbf(str)) != null) {
                    if (Math.abs(this.zzacv.zzbt().currentTimeMillis() - Math.max(zzbf.zzha(), zzbf.zzgz())) > zzez.zzajd.get().longValue()) {
                        this.zzacv.zzgi().zzjb().log("Fetching config for blacklisted app");
                        zzb(zzbf);
                    }
                }
            } else {
                if (this.zzacv.zzgi().isLoggable(2)) {
                    this.zzacv.zzgi().zzjc().zzg("Logging event", this.zzacv.zzgf().zzb(zzex));
                }
                zzjh().beginTransaction();
                try {
                    zzg(zzeb);
                    if (("_iap".equals(zzex.name) || FirebaseAnalytics.a.ECOMMERCE_PURCHASE.equals(zzex.name)) && !zza(str, zzex)) {
                        zzjh().setTransactionSuccessful();
                        return;
                    }
                    boolean zzcg = zzkd.zzcg(zzex.name);
                    boolean equals = "_err".equals(zzex.name);
                    zzel zza2 = zzjh().zza(zzld(), str, true, zzcg, false, equals, false);
                    long intValue = zza2.zzagu - ((long) zzez.zzaio.get().intValue());
                    if (intValue > 0) {
                        if (intValue % 1000 == 1) {
                            this.zzacv.zzgi().zziv().zze("Data loss. Too many events logged. appId, count", zzfi.zzbp(str), Long.valueOf(zza2.zzagu));
                        }
                        zzjh().setTransactionSuccessful();
                        zzjh().endTransaction();
                        return;
                    }
                    if (zzcg) {
                        long intValue2 = zza2.zzagt - ((long) zzez.zzaiq.get().intValue());
                        if (intValue2 > 0) {
                            if (intValue2 % 1000 == 1) {
                                this.zzacv.zzgi().zziv().zze("Data loss. Too many public events logged. appId, count", zzfi.zzbp(str), Long.valueOf(zza2.zzagt));
                            }
                            this.zzacv.zzgg().zza(str, 16, "_ev", zzex.name, 0);
                            zzjh().setTransactionSuccessful();
                            zzjh().endTransaction();
                            return;
                        }
                    }
                    if (equals) {
                        long max = zza2.zzagw - ((long) Math.max(0, Math.min(1000000, this.zzacv.zzgk().zzb(zzeb.packageName, zzez.zzaip))));
                        if (max > 0) {
                            if (max == 1) {
                                this.zzacv.zzgi().zziv().zze("Too many error events logged. appId, count", zzfi.zzbp(str), Long.valueOf(zza2.zzagw));
                            }
                            zzjh().setTransactionSuccessful();
                            zzjh().endTransaction();
                            return;
                        }
                    }
                    Bundle zzin = zzex.zzahg.zzin();
                    this.zzacv.zzgg().zza(zzin, "_o", zzex.origin);
                    if (this.zzacv.zzgg().zzcn(str)) {
                        this.zzacv.zzgg().zza(zzin, "_dbg", (Object) 1L);
                        this.zzacv.zzgg().zza(zzin, "_r", (Object) 1L);
                    }
                    long zzbg = zzjh().zzbg(str);
                    if (zzbg > 0) {
                        this.zzacv.zzgi().zziy().zze("Data lost. Too many events stored on disk, deleted. appId", zzfi.zzbp(str), Long.valueOf(zzbg));
                    }
                    zzes zzes = r11;
                    boolean z2 = false;
                    zzes zzes2 = new zzes(this.zzacv, zzex.origin, str, zzex.name, zzex.zzahr, 0, zzin);
                    zzet zzf = zzjh().zzf(str, zzes.name);
                    if (zzf != null) {
                        zzes zza3 = zzes.zza(this.zzacv, zzf.zzahj);
                        zzet = zzf.zzah(zza3.timestamp);
                        zzes = zza3;
                    } else if (zzjh().zzbj(str) < 500 || !zzcg) {
                        zzet = new zzet(str, zzes.name, 0, 0, zzes.timestamp, 0, null, null, null);
                    } else {
                        this.zzacv.zzgi().zziv().zzd("Too many event names used, ignoring event. appId, name, supported count", zzfi.zzbp(str), this.zzacv.zzgf().zzbm(zzes.name), 500);
                        this.zzacv.zzgg().zza(str, 8, (String) null, (String) null, 0);
                        zzjh().endTransaction();
                        return;
                    }
                    zzjh().zza(zzet);
                    zzab();
                    zzlc();
                    Preconditions.checkNotNull(zzes);
                    Preconditions.checkNotNull(zzeb);
                    Preconditions.checkNotEmpty(zzes.zzth);
                    Preconditions.checkArgument(zzes.zzth.equals(zzeb.packageName));
                    zzku zzku = new zzku();
                    zzku.zzavh = 1;
                    zzku.zzavp = io.a.a.a.a.b.a.ANDROID_CLIENT_TYPE;
                    zzku.zzth = zzeb.packageName;
                    zzku.zzafh = zzeb.zzafh;
                    zzku.zztg = zzeb.zztg;
                    zzku.zzawb = zzeb.zzafg == -2147483648L ? null : Integer.valueOf((int) zzeb.zzafg);
                    zzku.zzavt = Long.valueOf(zzeb.zzafi);
                    zzku.zzafa = zzeb.zzafa;
                    zzku.zzavx = zzeb.zzafj == 0 ? null : Long.valueOf(zzeb.zzafj);
                    Pair<String, Boolean> zzbr = this.zzacv.zzgj().zzbr(zzeb.packageName);
                    if (zzbr == null || TextUtils.isEmpty((CharSequence) zzbr.first)) {
                        if (!this.zzacv.zzge().zzf(this.zzacv.getContext()) && zzeb.zzafn) {
                            String string = Settings.Secure.getString(this.zzacv.getContext().getContentResolver(), "android_id");
                            if (string == null) {
                                this.zzacv.zzgi().zziy().zzg("null secure ID. appId", zzfi.zzbp(zzku.zzth));
                                string = "null";
                            } else if (string.isEmpty()) {
                                this.zzacv.zzgi().zziy().zzg("empty secure ID. appId", zzfi.zzbp(zzku.zzth));
                            }
                            zzku.zzawe = string;
                        }
                    } else if (zzeb.zzafm) {
                        zzku.zzavv = (String) zzbr.first;
                        zzku.zzavw = (Boolean) zzbr.second;
                    }
                    this.zzacv.zzge().zzch();
                    zzku.zzavr = Build.MODEL;
                    this.zzacv.zzge().zzch();
                    zzku.zzavq = Build.VERSION.RELEASE;
                    zzku.zzavs = Integer.valueOf((int) this.zzacv.zzge().zzik());
                    zzku.zzahd = this.zzacv.zzge().zzil();
                    zzku.zzavu = null;
                    zzku.zzavk = null;
                    zzku.zzavl = null;
                    zzku.zzavm = null;
                    zzku.zzawg = Long.valueOf(zzeb.zzafl);
                    if (this.zzacv.isEnabled() && zzeh.zzht()) {
                        zzku.zzawh = null;
                    }
                    zzea zzbf2 = zzjh().zzbf(zzeb.packageName);
                    if (zzbf2 == null) {
                        zzbf2 = new zzea(this.zzacv, zzeb.packageName);
                        zzbf2.zzam(this.zzacv.zzfz().zzir());
                        zzbf2.zzap(zzeb.zzafc);
                        zzbf2.zzan(zzeb.zzafa);
                        zzbf2.zzao(this.zzacv.zzgj().zzbs(zzeb.packageName));
                        zzbf2.zzw(0);
                        zzbf2.zzr(0);
                        zzbf2.zzs(0);
                        zzbf2.setAppVersion(zzeb.zztg);
                        zzbf2.zzt(zzeb.zzafg);
                        zzbf2.zzaq(zzeb.zzafh);
                        zzbf2.zzu(zzeb.zzafi);
                        zzbf2.zzv(zzeb.zzafj);
                        zzbf2.setMeasurementEnabled(zzeb.zzafk);
                        zzbf2.zzaf(zzeb.zzafl);
                        zzjh().zza(zzbf2);
                    }
                    zzku.zzaez = zzbf2.getAppInstanceId();
                    zzku.zzafc = zzbf2.zzgr();
                    List<zzkc> zzbe = zzjh().zzbe(zzeb.packageName);
                    zzku.zzavj = new zzkx[zzbe.size()];
                    for (int i = 0; i < zzbe.size(); i++) {
                        zzkx zzkx = new zzkx();
                        zzku.zzavj[i] = zzkx;
                        zzkx.name = zzbe.get(i).name;
                        zzkx.zzaws = Long.valueOf(zzbe.get(i).zzast);
                        zzjf().zza(zzkx, zzbe.get(i).value);
                    }
                    try {
                        long zza4 = zzjh().zza(zzku);
                        zzek zzjh = zzjh();
                        if (zzes.zzahg != null) {
                            Iterator<String> it = zzes.zzahg.iterator();
                            while (true) {
                                if (it.hasNext()) {
                                    if ("_r".equals(it.next())) {
                                        break;
                                    }
                                } else {
                                    boolean zzo = zzky().zzo(zzes.zzth, zzes.name);
                                    zzel zza5 = zzjh().zza(zzld(), zzes.zzth, false, false, false, false, false);
                                    if (zzo && zza5.zzagx < ((long) this.zzacv.zzgk().zzas(zzes.zzth))) {
                                        z2 = true;
                                    }
                                }
                            }
                        }
                        if (zzjh.zza(zzes, zza4, z2)) {
                            this.zzasa = 0;
                        }
                    } catch (IOException e) {
                        this.zzacv.zzgi().zziv().zze("Data loss. Failed to insert raw event metadata. appId", zzfi.zzbp(zzku.zzth), e);
                    }
                    zzjh().setTransactionSuccessful();
                    if (this.zzacv.zzgi().isLoggable(2)) {
                        this.zzacv.zzgi().zzjc().zzg("Event recorded", this.zzacv.zzgf().zza(zzes));
                    }
                    zzjh().endTransaction();
                    zzlg();
                    this.zzacv.zzgi().zzjc().zzg("Background event processing time, ms", Long.valueOf(((System.nanoTime() - nanoTime) + 500000) / 1000000));
                } finally {
                    zzjh().endTransaction();
                }
            }
        }
    }

    private final zzeb zzce(String str) {
        zzfk zzjb;
        String str2;
        Object obj;
        String str3 = str;
        zzea zzbf = zzjh().zzbf(str3);
        if (zzbf == null || TextUtils.isEmpty(zzbf.zzag())) {
            zzjb = this.zzacv.zzgi().zzjb();
            str2 = "No app data available; dropping";
            obj = str3;
        } else {
            Boolean zzc = zzc(zzbf);
            if (zzc == null || zzc.booleanValue()) {
                return new zzeb(str, zzbf.getGmpAppId(), zzbf.zzag(), zzbf.zzgu(), zzbf.zzgv(), zzbf.zzgw(), zzbf.zzgx(), (String) null, zzbf.isMeasurementEnabled(), false, zzbf.zzgr(), zzbf.zzhk(), 0L, 0, zzbf.zzhl(), zzbf.zzhm(), false);
            }
            zzjb = this.zzacv.zzgi().zziv();
            str2 = "App version does not match; dropping. appId";
            obj = zzfi.zzbp(str);
        }
        zzjb.zzg(str2, obj);
        return null;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:114:0x0273, code lost:
        if (r6 != null) goto L_0x0239;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0040, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0041, code lost:
        r6 = r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0045, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0046, code lost:
        r6 = null;
        r12 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0084, code lost:
        if (r3 != null) goto L_0x0086;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x009c, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x009d, code lost:
        r6 = r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x00e6, code lost:
        if (r3 != null) goto L_0x0086;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:425:0x0b7a, code lost:
        r6.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:74:0x01c8, code lost:
        if (r5 != null) goto L_0x01ca;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:81:0x01fb, code lost:
        if (r5 != null) goto L_0x01ca;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:86:0x0217, code lost:
        if (r5 != null) goto L_0x01ca;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:93:0x0237, code lost:
        if (r6 == null) goto L_0x0276;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:94:0x0239, code lost:
        r6.close();
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:117:0x027a  */
    /* JADX WARNING: Removed duplicated region for block: B:11:0x0040 A[ExcHandler: all (th java.lang.Throwable), PHI: r3 
      PHI: (r3v30 android.database.Cursor) = (r3v28 android.database.Cursor), (r3v28 android.database.Cursor), (r3v28 android.database.Cursor), (r3v28 android.database.Cursor), (r3v0 android.database.Cursor), (r3v0 android.database.Cursor) binds: [B:21:0x007e, B:22:?, B:27:0x008f, B:28:?, B:9:0x0031, B:10:?] A[DONT_GENERATE, DONT_INLINE], Splitter:B:9:0x0031] */
    /* JADX WARNING: Removed duplicated region for block: B:122:0x0288  */
    /* JADX WARNING: Removed duplicated region for block: B:157:0x0382  */
    /* JADX WARNING: Removed duplicated region for block: B:158:0x0384  */
    /* JADX WARNING: Removed duplicated region for block: B:160:0x0388  */
    /* JADX WARNING: Removed duplicated region for block: B:224:0x05a1  */
    /* JADX WARNING: Removed duplicated region for block: B:420:0x0b63  */
    /* JADX WARNING: Removed duplicated region for block: B:425:0x0b7a  */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x0120 A[SYNTHETIC, Splitter:B:49:0x0120] */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x0142  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final boolean zzd(java.lang.String r40, long r41) {
        /*
        // Method dump skipped, instructions count: 2966
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzjt.zzd(java.lang.String, long):boolean");
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x00ce  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x00f4  */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x0102  */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x012c  */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x013a  */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x0148  */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x0150  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.android.gms.internal.measurement.zzea zzg(com.google.android.gms.internal.measurement.zzeb r9) {
        /*
        // Method dump skipped, instructions count: 344
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzjt.zzg(com.google.android.gms.internal.measurement.zzeb):com.google.android.gms.internal.measurement.zzea");
    }

    public static zzjt zzg(Context context) {
        Preconditions.checkNotNull(context);
        Preconditions.checkNotNull(context.getApplicationContext());
        if (zzarr == null) {
            synchronized (zzjt.class) {
                if (zzarr == null) {
                    zzarr = new zzjt(new zzjy(context));
                }
            }
        }
        return zzarr;
    }

    private final zzgh zzky() {
        zza(this.zzars);
        return this.zzars;
    }

    private final zzfr zzla() {
        zzfr zzfr = this.zzarv;
        if (zzfr != null) {
            return zzfr;
        }
        throw new IllegalStateException("Network broadcast receiver not created");
    }

    private final zzjp zzlb() {
        zza(this.zzarw);
        return this.zzarw;
    }

    private final long zzld() {
        long currentTimeMillis = this.zzacv.zzbt().currentTimeMillis();
        zzft zzgj = this.zzacv.zzgj();
        zzgj.zzch();
        zzgj.zzab();
        long j = zzgj.zzalx.get();
        if (j == 0) {
            j = 1 + ((long) zzgj.zzgg().zzlo().nextInt(86400000));
            zzgj.zzalx.set(j);
        }
        return ((((currentTimeMillis + j) / 1000) / 60) / 60) / 24;
    }

    private final boolean zzlf() {
        zzab();
        zzlc();
        return zzjh().zzia() || !TextUtils.isEmpty(zzjh().zzhv());
    }

    /* JADX WARNING: Removed duplicated region for block: B:52:0x0176  */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x0194  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void zzlg() {
        /*
        // Method dump skipped, instructions count: 603
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzjt.zzlg():void");
    }

    private final void zzlh() {
        zzab();
        if (this.zzase || this.zzasf || this.zzasg) {
            this.zzacv.zzgi().zzjc().zzd("Not stopping services. fetch, network, upload", Boolean.valueOf(this.zzase), Boolean.valueOf(this.zzasf), Boolean.valueOf(this.zzasg));
            return;
        }
        this.zzacv.zzgi().zzjc().log("Stopping uploading service(s)");
        List<Runnable> list = this.zzasb;
        if (list != null) {
            for (Runnable runnable : list) {
                runnable.run();
            }
            this.zzasb.clear();
        }
    }

    @VisibleForTesting
    private final boolean zzli() {
        String str;
        zzfk zzfk;
        Object e;
        zzab();
        try {
            this.zzasi = new RandomAccessFile(new File(this.zzacv.getContext().getFilesDir(), "google_app_measurement.db"), "rw").getChannel();
            this.zzash = this.zzasi.tryLock();
            if (this.zzash != null) {
                this.zzacv.zzgi().zzjc().log("Storage concurrent access okay");
                return true;
            }
            this.zzacv.zzgi().zziv().log("Storage concurrent data access panic");
            return false;
        } catch (FileNotFoundException e2) {
            e = e2;
            zzfk = this.zzacv.zzgi().zziv();
            str = "Failed to acquire storage lock";
            zzfk.zzg(str, e);
            return false;
        } catch (IOException e3) {
            e = e3;
            zzfk = this.zzacv.zzgi().zziv();
            str = "Failed to access storage lock file";
            zzfk.zzg(str, e);
            return false;
        }
    }

    private final boolean zzlk() {
        zzab();
        zzlc();
        return this.zzarz;
    }

    @Override // com.google.android.gms.internal.measurement.zzhk
    public final Context getContext() {
        return this.zzacv.getContext();
    }

    /* access modifiers changed from: protected */
    public final void start() {
        this.zzacv.zzgh().zzab();
        zzjh().zzhx();
        if (this.zzacv.zzgj().zzalt.get() == 0) {
            this.zzacv.zzgj().zzalt.set(this.zzacv.zzbt().currentTimeMillis());
        }
        zzlg();
    }

    /* JADX INFO: finally extract failed */
    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public final void zza(int i, Throwable th, byte[] bArr, String str) {
        zzab();
        zzlc();
        if (bArr == null) {
            try {
                bArr = new byte[0];
            } catch (Throwable th2) {
                this.zzasf = false;
                zzlh();
                throw th2;
            }
        }
        List<Long> list = this.zzasj;
        this.zzasj = null;
        boolean z = true;
        if ((i == 200 || i == 204) && th == null) {
            try {
                this.zzacv.zzgj().zzalt.set(this.zzacv.zzbt().currentTimeMillis());
                this.zzacv.zzgj().zzalu.set(0);
                zzlg();
                this.zzacv.zzgi().zzjc().zze("Successful upload. Got network response. code, size", Integer.valueOf(i), Integer.valueOf(bArr.length));
                zzjh().beginTransaction();
                try {
                    for (Long l : list) {
                        try {
                            zzek zzjh = zzjh();
                            long longValue = l.longValue();
                            zzjh.zzab();
                            zzjh.zzch();
                            try {
                                if (zzjh.getWritableDatabase().delete("queue", "rowid=?", new String[]{String.valueOf(longValue)}) != 1) {
                                    throw new SQLiteException("Deleted fewer rows from queue than expected");
                                }
                            } catch (SQLiteException e) {
                                zzjh.zzgi().zziv().zzg("Failed to delete a bundle in a queue table", e);
                                throw e;
                            }
                        } catch (SQLiteException e2) {
                            if (this.zzask == null || !this.zzask.contains(l)) {
                                throw e2;
                            }
                        }
                    }
                    zzjh().setTransactionSuccessful();
                    zzjh().endTransaction();
                    this.zzask = null;
                    if (!zzkz().zzex() || !zzlf()) {
                        this.zzasl = -1;
                        zzlg();
                    } else {
                        zzle();
                    }
                    this.zzasa = 0;
                } catch (Throwable th3) {
                    zzjh().endTransaction();
                    throw th3;
                }
            } catch (SQLiteException e3) {
                this.zzacv.zzgi().zziv().zzg("Database error while trying to delete uploaded bundles", e3);
                this.zzasa = this.zzacv.zzbt().elapsedRealtime();
                this.zzacv.zzgi().zzjc().zzg("Disable upload, time", Long.valueOf(this.zzasa));
            }
        } else {
            this.zzacv.zzgi().zzjc().zze("Network upload failed. Will retry later. code, error", Integer.valueOf(i), th);
            this.zzacv.zzgj().zzalu.set(this.zzacv.zzbt().currentTimeMillis());
            if (!(i == 503 || i == 429)) {
                z = false;
            }
            if (z) {
                this.zzacv.zzgj().zzalv.set(this.zzacv.zzbt().currentTimeMillis());
            }
            if (this.zzacv.zzgk().zzay(str)) {
                zzjh().zzc(list);
            }
            zzlg();
        }
        this.zzasf = false;
        zzlh();
    }

    /* JADX DEBUG: Multi-variable search result rejected for r10v2, resolved type: java.lang.Long */
    /* JADX DEBUG: Multi-variable search result rejected for r10v3, resolved type: java.lang.Long */
    /* JADX DEBUG: Multi-variable search result rejected for r10v4, resolved type: java.lang.Long */
    /* JADX DEBUG: Multi-variable search result rejected for r10v6, resolved type: java.lang.Long */
    /* JADX WARN: Multi-variable type inference failed */
    public final byte[] zza(zzex zzex, String str) {
        zzkc zzkc;
        zzku zzku;
        zzkt zzkt;
        zzea zzea;
        byte[] bArr;
        Bundle bundle;
        long j;
        zzfk zziy;
        String str2;
        Object zzbp;
        zzlc();
        zzab();
        this.zzacv.zzfu();
        Preconditions.checkNotNull(zzex);
        Preconditions.checkNotEmpty(str);
        zzkt zzkt2 = new zzkt();
        zzjh().beginTransaction();
        try {
            zzea zzbf = zzjh().zzbf(str);
            if (zzbf == null) {
                this.zzacv.zzgi().zzjb().zzg("Log and bundle not available. package_name", str);
            } else if (!zzbf.isMeasurementEnabled()) {
                this.zzacv.zzgi().zzjb().zzg("Log and bundle disabled. package_name", str);
            } else {
                if (("_iap".equals(zzex.name) || FirebaseAnalytics.a.ECOMMERCE_PURCHASE.equals(zzex.name)) && !zza(str, zzex)) {
                    this.zzacv.zzgi().zziy().zzg("Failed to handle purchase event at single event bundle creation. appId", zzfi.zzbp(str));
                }
                boolean zzaw = this.zzacv.zzgk().zzaw(str);
                Long l = 0L;
                if (zzaw && "_e".equals(zzex.name)) {
                    if (zzex.zzahg == null || zzex.zzahg.size() == 0) {
                        zziy = this.zzacv.zzgi().zziy();
                        str2 = "The engagement event does not contain any parameters. appId";
                        zzbp = zzfi.zzbp(str);
                    } else if (zzex.zzahg.getLong("_et") == null) {
                        zziy = this.zzacv.zzgi().zziy();
                        str2 = "The engagement event does not include duration. appId";
                        zzbp = zzfi.zzbp(str);
                    } else {
                        l = zzex.zzahg.getLong("_et");
                    }
                    zziy.zzg(str2, zzbp);
                }
                zzku zzku2 = new zzku();
                zzkt2.zzavf = new zzku[]{zzku2};
                zzku2.zzavh = 1;
                zzku2.zzavp = io.a.a.a.a.b.a.ANDROID_CLIENT_TYPE;
                zzku2.zzth = zzbf.zzah();
                zzku2.zzafh = zzbf.zzgv();
                zzku2.zztg = zzbf.zzag();
                long zzgu = zzbf.zzgu();
                zzku2.zzawb = zzgu == -2147483648L ? null : Integer.valueOf((int) zzgu);
                zzku2.zzavt = Long.valueOf(zzbf.zzgw());
                zzku2.zzafa = zzbf.getGmpAppId();
                zzku2.zzavx = Long.valueOf(zzbf.zzgx());
                if (this.zzacv.isEnabled() && zzeh.zzht() && this.zzacv.zzgk().zzau(zzku2.zzth)) {
                    zzku2.zzawh = null;
                }
                Pair<String, Boolean> zzbr = this.zzacv.zzgj().zzbr(zzbf.zzah());
                if (zzbf.zzhl() && zzbr != null && !TextUtils.isEmpty((CharSequence) zzbr.first)) {
                    zzku2.zzavv = (String) zzbr.first;
                    zzku2.zzavw = (Boolean) zzbr.second;
                }
                this.zzacv.zzge().zzch();
                zzku2.zzavr = Build.MODEL;
                this.zzacv.zzge().zzch();
                zzku2.zzavq = Build.VERSION.RELEASE;
                zzku2.zzavs = Integer.valueOf((int) this.zzacv.zzge().zzik());
                zzku2.zzahd = this.zzacv.zzge().zzil();
                zzku2.zzaez = zzbf.getAppInstanceId();
                zzku2.zzafc = zzbf.zzgr();
                List<zzkc> zzbe = zzjh().zzbe(zzbf.zzah());
                zzku2.zzavj = new zzkx[zzbe.size()];
                if (zzaw) {
                    zzkc = zzjh().zzh(zzku2.zzth, "_lte");
                    if (zzkc == null || zzkc.value == null) {
                        zzkc = new zzkc(zzku2.zzth, "auto", "_lte", this.zzacv.zzbt().currentTimeMillis(), l);
                    } else if (l.longValue() > 0) {
                        zzkc = new zzkc(zzku2.zzth, "auto", "_lte", this.zzacv.zzbt().currentTimeMillis(), Long.valueOf(((Long) zzkc.value).longValue() + l.longValue()));
                    }
                } else {
                    zzkc = null;
                }
                zzkx zzkx = null;
                for (int i = 0; i < zzbe.size(); i++) {
                    zzkx zzkx2 = new zzkx();
                    zzku2.zzavj[i] = zzkx2;
                    zzkx2.name = zzbe.get(i).name;
                    zzkx2.zzaws = Long.valueOf(zzbe.get(i).zzast);
                    zzjf().zza(zzkx2, zzbe.get(i).value);
                    if (zzaw && "_lte".equals(zzkx2.name)) {
                        zzkx2.zzave = (Long) zzkc.value;
                        zzkx2.zzaws = Long.valueOf(this.zzacv.zzbt().currentTimeMillis());
                        zzkx = zzkx2;
                    }
                }
                if (zzaw && zzkx == null) {
                    zzkx zzkx3 = new zzkx();
                    zzkx3.name = "_lte";
                    zzkx3.zzaws = Long.valueOf(this.zzacv.zzbt().currentTimeMillis());
                    zzkx3.zzave = (Long) zzkc.value;
                    zzku2.zzavj = (zzkx[]) Arrays.copyOf(zzku2.zzavj, zzku2.zzavj.length + 1);
                    zzku2.zzavj[zzku2.zzavj.length - 1] = zzkx3;
                }
                if (l.longValue() > 0) {
                    zzjh().zza(zzkc);
                }
                Bundle zzin = zzex.zzahg.zzin();
                if ("_iap".equals(zzex.name)) {
                    zzin.putLong("_c", 1);
                    this.zzacv.zzgi().zzjb().log("Marking in-app purchase as real-time");
                    zzin.putLong("_r", 1);
                }
                zzin.putString("_o", zzex.origin);
                if (this.zzacv.zzgg().zzcn(zzku2.zzth)) {
                    this.zzacv.zzgg().zza(zzin, "_dbg", (Object) 1L);
                    this.zzacv.zzgg().zza(zzin, "_r", (Object) 1L);
                }
                zzet zzf = zzjh().zzf(str, zzex.name);
                if (zzf == null) {
                    bundle = zzin;
                    bArr = null;
                    zzku = zzku2;
                    zzea = zzbf;
                    zzkt = zzkt2;
                    zzjh().zza(new zzet(str, zzex.name, 1, 0, zzex.zzahr, 0, null, null, null));
                    j = 0;
                } else {
                    bundle = zzin;
                    zzku = zzku2;
                    zzea = zzbf;
                    zzkt = zzkt2;
                    bArr = null;
                    long j2 = zzf.zzahj;
                    zzjh().zza(zzf.zzah(zzex.zzahr).zzim());
                    j = j2;
                }
                zzes zzes = new zzes(this.zzacv, zzex.origin, str, zzex.name, zzex.zzahr, j, bundle);
                zzkr zzkr = new zzkr();
                zzku.zzavi = new zzkr[]{zzkr};
                zzkr.zzavb = Long.valueOf(zzes.timestamp);
                zzkr.name = zzes.name;
                zzkr.zzavc = Long.valueOf(zzes.zzahf);
                zzkr.zzava = new zzks[zzes.zzahg.size()];
                Iterator<String> it = zzes.zzahg.iterator();
                int i2 = 0;
                while (it.hasNext()) {
                    String next = it.next();
                    zzks zzks = new zzks();
                    zzkr.zzava[i2] = zzks;
                    zzks.name = next;
                    zzjf().zza(zzks, zzes.zzahg.get(next));
                    i2++;
                }
                zzku.zzawa = zza(zzea.zzah(), zzku.zzavj, zzku.zzavi);
                zzku.zzavl = zzkr.zzavb;
                zzku.zzavm = zzkr.zzavb;
                long zzgt = zzea.zzgt();
                zzku.zzavo = zzgt != 0 ? Long.valueOf(zzgt) : bArr;
                long zzgs = zzea.zzgs();
                if (zzgs != 0) {
                    zzgt = zzgs;
                }
                zzku.zzavn = zzgt != 0 ? Long.valueOf(zzgt) : bArr;
                zzea.zzhb();
                zzku.zzavy = Integer.valueOf((int) zzea.zzgy());
                zzku.zzavu = Long.valueOf(this.zzacv.zzgk().zzgw());
                zzku.zzavk = Long.valueOf(this.zzacv.zzbt().currentTimeMillis());
                zzku.zzavz = Boolean.TRUE;
                zzea.zzr(zzku.zzavl.longValue());
                zzea.zzs(zzku.zzavm.longValue());
                zzjh().zza(zzea);
                zzjh().setTransactionSuccessful();
                zzjh().endTransaction();
                try {
                    byte[] bArr2 = new byte[zzkt.zzwb()];
                    zzacb zzb = zzacb.zzb(bArr2, 0, bArr2.length);
                    zzkt.zza(zzb);
                    zzb.zzvt();
                    return zzjf().zzb(bArr2);
                } catch (IOException e) {
                    this.zzacv.zzgi().zziv().zze("Data loss. Failed to bundle and serialize. appId", zzfi.zzbp(str), e);
                    return bArr;
                }
            }
            return new byte[0];
        } finally {
            zzjh().endTransaction();
        }
    }

    /* access modifiers changed from: package-private */
    public final void zzb(zzef zzef, zzeb zzeb) {
        zzfk zziv;
        String str;
        Object zzbp;
        String zzbo;
        Object value;
        zzfk zziv2;
        String str2;
        Object zzbp2;
        String zzbo2;
        Object obj;
        Preconditions.checkNotNull(zzef);
        Preconditions.checkNotEmpty(zzef.packageName);
        Preconditions.checkNotNull(zzef.origin);
        Preconditions.checkNotNull(zzef.zzage);
        Preconditions.checkNotEmpty(zzef.zzage.name);
        zzab();
        zzlc();
        if (!TextUtils.isEmpty(zzeb.zzafa)) {
            if (!zzeb.zzafk) {
                zzg(zzeb);
                return;
            }
            zzef zzef2 = new zzef(zzef);
            boolean z = false;
            zzef2.active = false;
            zzjh().beginTransaction();
            try {
                zzef zzi = zzjh().zzi(zzef2.packageName, zzef2.zzage.name);
                if (zzi != null && !zzi.origin.equals(zzef2.origin)) {
                    this.zzacv.zzgi().zziy().zzd("Updating a conditional user property with different origin. name, origin, origin (from DB)", this.zzacv.zzgf().zzbo(zzef2.zzage.name), zzef2.origin, zzi.origin);
                }
                if (zzi != null && zzi.active) {
                    zzef2.origin = zzi.origin;
                    zzef2.creationTimestamp = zzi.creationTimestamp;
                    zzef2.triggerTimeout = zzi.triggerTimeout;
                    zzef2.triggerEventName = zzi.triggerEventName;
                    zzef2.zzagg = zzi.zzagg;
                    zzef2.active = zzi.active;
                    zzef2.zzage = new zzka(zzef2.zzage.name, zzi.zzage.zzast, zzef2.zzage.getValue(), zzi.zzage.origin);
                } else if (TextUtils.isEmpty(zzef2.triggerEventName)) {
                    zzef2.zzage = new zzka(zzef2.zzage.name, zzef2.creationTimestamp, zzef2.zzage.getValue(), zzef2.zzage.origin);
                    zzef2.active = true;
                    z = true;
                }
                if (zzef2.active) {
                    zzka zzka = zzef2.zzage;
                    zzkc zzkc = new zzkc(zzef2.packageName, zzef2.origin, zzka.name, zzka.zzast, zzka.getValue());
                    if (zzjh().zza(zzkc)) {
                        zziv2 = this.zzacv.zzgi().zzjb();
                        str2 = "User property updated immediately";
                        zzbp2 = zzef2.packageName;
                        zzbo2 = this.zzacv.zzgf().zzbo(zzkc.name);
                        obj = zzkc.value;
                    } else {
                        zziv2 = this.zzacv.zzgi().zziv();
                        str2 = "(2)Too many active user properties, ignoring";
                        zzbp2 = zzfi.zzbp(zzef2.packageName);
                        zzbo2 = this.zzacv.zzgf().zzbo(zzkc.name);
                        obj = zzkc.value;
                    }
                    zziv2.zzd(str2, zzbp2, zzbo2, obj);
                    if (z && zzef2.zzagg != null) {
                        zzc(new zzex(zzef2.zzagg, zzef2.creationTimestamp), zzeb);
                    }
                }
                if (zzjh().zza(zzef2)) {
                    zziv = this.zzacv.zzgi().zzjb();
                    str = "Conditional property added";
                    zzbp = zzef2.packageName;
                    zzbo = this.zzacv.zzgf().zzbo(zzef2.zzage.name);
                    value = zzef2.zzage.getValue();
                } else {
                    zziv = this.zzacv.zzgi().zziv();
                    str = "Too many conditional properties, ignoring";
                    zzbp = zzfi.zzbp(zzef2.packageName);
                    zzbo = this.zzacv.zzgf().zzbo(zzef2.zzage.name);
                    value = zzef2.zzage.getValue();
                }
                zziv.zzd(str, zzbp, zzbo, value);
                zzjh().setTransactionSuccessful();
            } finally {
                zzjh().endTransaction();
            }
        }
    }

    /* access modifiers changed from: package-private */
    public final void zzb(zzex zzex, zzeb zzeb) {
        List<zzef> list;
        List<zzef> list2;
        List<zzef> list3;
        zzfk zziv;
        String str;
        Object zzbp;
        String zzbo;
        Object obj;
        Preconditions.checkNotNull(zzeb);
        Preconditions.checkNotEmpty(zzeb.packageName);
        zzab();
        zzlc();
        String str2 = zzeb.packageName;
        long j = zzex.zzahr;
        if (zzjf().zzd(zzex, zzeb)) {
            if (!zzeb.zzafk) {
                zzg(zzeb);
                return;
            }
            zzjh().beginTransaction();
            try {
                zzek zzjh = zzjh();
                Preconditions.checkNotEmpty(str2);
                zzjh.zzab();
                zzjh.zzch();
                int i = (j > 0 ? 1 : (j == 0 ? 0 : -1));
                if (i < 0) {
                    zzjh.zzgi().zziy().zze("Invalid time querying timed out conditional properties", zzfi.zzbp(str2), Long.valueOf(j));
                    list = Collections.emptyList();
                } else {
                    list = zzjh.zzb("active=0 and app_id=? and abs(? - creation_timestamp) > trigger_timeout", new String[]{str2, String.valueOf(j)});
                }
                for (zzef zzef : list) {
                    if (zzef != null) {
                        this.zzacv.zzgi().zzjb().zzd("User property timed out", zzef.packageName, this.zzacv.zzgf().zzbo(zzef.zzage.name), zzef.zzage.getValue());
                        if (zzef.zzagf != null) {
                            zzc(new zzex(zzef.zzagf, j), zzeb);
                        }
                        zzjh().zzj(str2, zzef.zzage.name);
                    }
                }
                zzek zzjh2 = zzjh();
                Preconditions.checkNotEmpty(str2);
                zzjh2.zzab();
                zzjh2.zzch();
                if (i < 0) {
                    zzjh2.zzgi().zziy().zze("Invalid time querying expired conditional properties", zzfi.zzbp(str2), Long.valueOf(j));
                    list2 = Collections.emptyList();
                } else {
                    list2 = zzjh2.zzb("active<>0 and app_id=? and abs(? - triggered_timestamp) > time_to_live", new String[]{str2, String.valueOf(j)});
                }
                ArrayList arrayList = new ArrayList(list2.size());
                for (zzef zzef2 : list2) {
                    if (zzef2 != null) {
                        this.zzacv.zzgi().zzjb().zzd("User property expired", zzef2.packageName, this.zzacv.zzgf().zzbo(zzef2.zzage.name), zzef2.zzage.getValue());
                        zzjh().zzg(str2, zzef2.zzage.name);
                        if (zzef2.zzagh != null) {
                            arrayList.add(zzef2.zzagh);
                        }
                        zzjh().zzj(str2, zzef2.zzage.name);
                    }
                }
                ArrayList arrayList2 = arrayList;
                int size = arrayList2.size();
                int i2 = 0;
                while (i2 < size) {
                    Object obj2 = arrayList2.get(i2);
                    i2++;
                    zzc(new zzex((zzex) obj2, j), zzeb);
                }
                zzek zzjh3 = zzjh();
                String str3 = zzex.name;
                Preconditions.checkNotEmpty(str2);
                Preconditions.checkNotEmpty(str3);
                zzjh3.zzab();
                zzjh3.zzch();
                if (i < 0) {
                    zzjh3.zzgi().zziy().zzd("Invalid time querying triggered conditional properties", zzfi.zzbp(str2), zzjh3.zzgf().zzbm(str3), Long.valueOf(j));
                    list3 = Collections.emptyList();
                } else {
                    list3 = zzjh3.zzb("active=0 and app_id=? and trigger_event_name=? and abs(? - creation_timestamp) <= trigger_timeout", new String[]{str2, str3, String.valueOf(j)});
                }
                ArrayList arrayList3 = new ArrayList(list3.size());
                for (zzef zzef3 : list3) {
                    if (zzef3 != null) {
                        zzka zzka = zzef3.zzage;
                        zzkc zzkc = new zzkc(zzef3.packageName, zzef3.origin, zzka.name, j, zzka.getValue());
                        if (zzjh().zza(zzkc)) {
                            zziv = this.zzacv.zzgi().zzjb();
                            str = "User property triggered";
                            zzbp = zzef3.packageName;
                            zzbo = this.zzacv.zzgf().zzbo(zzkc.name);
                            obj = zzkc.value;
                        } else {
                            zziv = this.zzacv.zzgi().zziv();
                            str = "Too many active user properties, ignoring";
                            zzbp = zzfi.zzbp(zzef3.packageName);
                            zzbo = this.zzacv.zzgf().zzbo(zzkc.name);
                            obj = zzkc.value;
                        }
                        zziv.zzd(str, zzbp, zzbo, obj);
                        if (zzef3.zzagg != null) {
                            arrayList3.add(zzef3.zzagg);
                        }
                        zzef3.zzage = new zzka(zzkc);
                        zzef3.active = true;
                        zzjh().zza(zzef3);
                    }
                }
                zzc(zzex, zzeb);
                ArrayList arrayList4 = arrayList3;
                int size2 = arrayList4.size();
                int i3 = 0;
                while (i3 < size2) {
                    Object obj3 = arrayList4.get(i3);
                    i3++;
                    zzc(new zzex((zzex) obj3, j), zzeb);
                }
                zzjh().setTransactionSuccessful();
            } finally {
                zzjh().endTransaction();
            }
        }
    }

    /* access modifiers changed from: package-private */
    public final void zzb(zzjs zzjs) {
        this.zzasc++;
    }

    /* access modifiers changed from: package-private */
    public final void zzb(zzka zzka, zzeb zzeb) {
        zzab();
        zzlc();
        if (!TextUtils.isEmpty(zzeb.zzafa)) {
            if (!zzeb.zzafk) {
                zzg(zzeb);
                return;
            }
            int zzcj = this.zzacv.zzgg().zzcj(zzka.name);
            if (zzcj != 0) {
                this.zzacv.zzgg();
                this.zzacv.zzgg().zza(zzeb.packageName, zzcj, "_ev", zzkd.zza(zzka.name, 24, true), zzka.name != null ? zzka.name.length() : 0);
                return;
            }
            int zzi = this.zzacv.zzgg().zzi(zzka.name, zzka.getValue());
            if (zzi != 0) {
                this.zzacv.zzgg();
                String zza2 = zzkd.zza(zzka.name, 24, true);
                Object value = zzka.getValue();
                this.zzacv.zzgg().zza(zzeb.packageName, zzi, "_ev", zza2, (value == null || (!(value instanceof String) && !(value instanceof CharSequence))) ? 0 : String.valueOf(value).length());
                return;
            }
            Object zzj = this.zzacv.zzgg().zzj(zzka.name, zzka.getValue());
            if (zzj != null) {
                zzkc zzkc = new zzkc(zzeb.packageName, zzka.origin, zzka.name, zzka.zzast, zzj);
                this.zzacv.zzgi().zzjb().zze("Setting user property", this.zzacv.zzgf().zzbo(zzkc.name), zzj);
                zzjh().beginTransaction();
                try {
                    zzg(zzeb);
                    boolean zza3 = zzjh().zza(zzkc);
                    zzjh().setTransactionSuccessful();
                    if (zza3) {
                        this.zzacv.zzgi().zzjb().zze("User property set", this.zzacv.zzgf().zzbo(zzkc.name), zzkc.value);
                    } else {
                        this.zzacv.zzgi().zziv().zze("Too many unique user properties are set. Ignoring user property", this.zzacv.zzgf().zzbo(zzkc.name), zzkc.value);
                        this.zzacv.zzgg().zza(zzeb.packageName, 9, (String) null, (String) null, 0);
                    }
                } finally {
                    zzjh().endTransaction();
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:53:0x0132  */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x0142  */
    @com.google.android.gms.common.util.VisibleForTesting
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zzb(java.lang.String r7, int r8, java.lang.Throwable r9, byte[] r10, java.util.Map<java.lang.String, java.util.List<java.lang.String>> r11) {
        /*
        // Method dump skipped, instructions count: 393
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzjt.zzb(java.lang.String, int, java.lang.Throwable, byte[], java.util.Map):void");
    }

    @Override // com.google.android.gms.internal.measurement.zzhk
    public final Clock zzbt() {
        return this.zzacv.zzbt();
    }

    /* access modifiers changed from: package-private */
    public final void zzc(zzef zzef, zzeb zzeb) {
        Preconditions.checkNotNull(zzef);
        Preconditions.checkNotEmpty(zzef.packageName);
        Preconditions.checkNotNull(zzef.zzage);
        Preconditions.checkNotEmpty(zzef.zzage.name);
        zzab();
        zzlc();
        if (!TextUtils.isEmpty(zzeb.zzafa)) {
            if (!zzeb.zzafk) {
                zzg(zzeb);
                return;
            }
            zzjh().beginTransaction();
            try {
                zzg(zzeb);
                zzef zzi = zzjh().zzi(zzef.packageName, zzef.zzage.name);
                if (zzi != null) {
                    this.zzacv.zzgi().zzjb().zze("Removing conditional user property", zzef.packageName, this.zzacv.zzgf().zzbo(zzef.zzage.name));
                    zzjh().zzj(zzef.packageName, zzef.zzage.name);
                    if (zzi.active) {
                        zzjh().zzg(zzef.packageName, zzef.zzage.name);
                    }
                    if (zzef.zzagh != null) {
                        Bundle bundle = null;
                        if (zzef.zzagh.zzahg != null) {
                            bundle = zzef.zzagh.zzahg.zzin();
                        }
                        zzc(this.zzacv.zzgg().zza(zzef.packageName, zzef.zzagh.name, bundle, zzi.origin, zzef.zzagh.zzahr, true, false), zzeb);
                    }
                } else {
                    this.zzacv.zzgi().zziy().zze("Conditional user property doesn't exist", zzfi.zzbp(zzef.packageName), this.zzacv.zzgf().zzbo(zzef.zzage.name));
                }
                zzjh().setTransactionSuccessful();
            } finally {
                zzjh().endTransaction();
            }
        }
    }

    /* access modifiers changed from: package-private */
    public final void zzc(zzex zzex, String str) {
        zzea zzbf = zzjh().zzbf(str);
        if (zzbf == null || TextUtils.isEmpty(zzbf.zzag())) {
            this.zzacv.zzgi().zzjb().zzg("No app data available; dropping event", str);
            return;
        }
        Boolean zzc = zzc(zzbf);
        if (zzc == null) {
            if (!"_ui".equals(zzex.name)) {
                this.zzacv.zzgi().zziy().zzg("Could not find package. appId", zzfi.zzbp(str));
            }
        } else if (!zzc.booleanValue()) {
            this.zzacv.zzgi().zziv().zzg("App version does not match; dropping event. appId", zzfi.zzbp(str));
            return;
        }
        zzb(zzex, new zzeb(str, zzbf.getGmpAppId(), zzbf.zzag(), zzbf.zzgu(), zzbf.zzgv(), zzbf.zzgw(), zzbf.zzgx(), (String) null, zzbf.isMeasurementEnabled(), false, zzbf.zzgr(), zzbf.zzhk(), 0L, 0, zzbf.zzhl(), zzbf.zzhm(), false));
    }

    /* access modifiers changed from: package-private */
    public final void zzc(zzka zzka, zzeb zzeb) {
        zzab();
        zzlc();
        if (!TextUtils.isEmpty(zzeb.zzafa)) {
            if (!zzeb.zzafk) {
                zzg(zzeb);
                return;
            }
            this.zzacv.zzgi().zzjb().zzg("Removing user property", this.zzacv.zzgf().zzbo(zzka.name));
            zzjh().beginTransaction();
            try {
                zzg(zzeb);
                zzjh().zzg(zzeb.packageName, zzka.name);
                zzjh().setTransactionSuccessful();
                this.zzacv.zzgi().zzjb().zzg("User property removed", this.zzacv.zzgf().zzbo(zzka.name));
            } finally {
                zzjh().endTransaction();
            }
        }
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public final void zzd(zzeb zzeb) {
        if (this.zzasj != null) {
            this.zzask = new ArrayList();
            this.zzask.addAll(this.zzasj);
        }
        zzek zzjh = zzjh();
        String str = zzeb.packageName;
        Preconditions.checkNotEmpty(str);
        zzjh.zzab();
        zzjh.zzch();
        try {
            SQLiteDatabase writableDatabase = zzjh.getWritableDatabase();
            String[] strArr = {str};
            int delete = writableDatabase.delete("apps", "app_id=?", strArr) + 0 + writableDatabase.delete("events", "app_id=?", strArr) + writableDatabase.delete("user_attributes", "app_id=?", strArr) + writableDatabase.delete("conditional_properties", "app_id=?", strArr) + writableDatabase.delete("raw_events", "app_id=?", strArr) + writableDatabase.delete("raw_events_metadata", "app_id=?", strArr) + writableDatabase.delete("queue", "app_id=?", strArr) + writableDatabase.delete("audience_filter_values", "app_id=?", strArr) + writableDatabase.delete("main_event_params", "app_id=?", strArr);
            if (delete > 0) {
                zzjh.zzgi().zzjc().zze("Reset analytics data. app, records", str, Integer.valueOf(delete));
            }
        } catch (SQLiteException e) {
            zzjh.zzgi().zziv().zze("Error resetting analytics data. appId, error", zzfi.zzbp(str), e);
        }
        zzeb zza2 = zza(this.zzacv.getContext(), zzeb.packageName, zzeb.zzafa, zzeb.zzafk, zzeb.zzafm, zzeb.zzafn, zzeb.zzaga);
        if (!this.zzacv.zzgk().zzbc(zzeb.packageName) || zzeb.zzafk) {
            zzf(zza2);
        }
    }

    /* access modifiers changed from: package-private */
    public final void zze(zzeb zzeb) {
        zzab();
        zzlc();
        Preconditions.checkNotEmpty(zzeb.packageName);
        zzg(zzeb);
    }

    /* access modifiers changed from: package-private */
    public final void zze(zzef zzef) {
        zzeb zzce = zzce(zzef.packageName);
        if (zzce != null) {
            zzb(zzef, zzce);
        }
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:110:0x03af  */
    /* JADX WARNING: Removed duplicated region for block: B:58:0x01e5  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zzf(com.google.android.gms.internal.measurement.zzeb r19) {
        /*
        // Method dump skipped, instructions count: 993
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzjt.zzf(com.google.android.gms.internal.measurement.zzeb):void");
    }

    /* access modifiers changed from: package-private */
    public final void zzf(zzef zzef) {
        zzeb zzce = zzce(zzef.packageName);
        if (zzce != null) {
            zzc(zzef, zzce);
        }
    }

    /* access modifiers changed from: package-private */
    public final void zzg(Runnable runnable) {
        zzab();
        if (this.zzasb == null) {
            this.zzasb = new ArrayList();
        }
        this.zzasb.add(runnable);
    }

    public final zzfg zzgf() {
        return this.zzacv.zzgf();
    }

    public final zzkd zzgg() {
        return this.zzacv.zzgg();
    }

    @Override // com.google.android.gms.internal.measurement.zzhk
    public final zzgi zzgh() {
        return this.zzacv.zzgh();
    }

    @Override // com.google.android.gms.internal.measurement.zzhk
    public final zzfi zzgi() {
        return this.zzacv.zzgi();
    }

    public final zzeh zzgk() {
        return this.zzacv.zzgk();
    }

    @Override // com.google.android.gms.internal.measurement.zzhk
    public final zzee zzgl() {
        return this.zzacv.zzgl();
    }

    /* access modifiers changed from: package-private */
    public final String zzh(zzeb zzeb) {
        try {
            return (String) this.zzacv.zzgh().zzb(new zzjx(this, zzeb)).get(30000, TimeUnit.MILLISECONDS);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            this.zzacv.zzgi().zziv().zze("Failed to get app instance id. appId", zzfi.zzbp(zzeb.packageName), e);
            return null;
        }
    }

    public final zzjz zzjf() {
        zza(this.zzary);
        return this.zzary;
    }

    public final zzed zzjg() {
        zza(this.zzarx);
        return this.zzarx;
    }

    public final zzek zzjh() {
        zza(this.zzaru);
        return this.zzaru;
    }

    public final zzfm zzkz() {
        zza(this.zzart);
        return this.zzart;
    }

    /* access modifiers changed from: package-private */
    public final void zzlc() {
        if (!this.zzvn) {
            throw new IllegalStateException("UploadController is not initialized");
        }
    }

    /* access modifiers changed from: package-private */
    public final void zzle() {
        zzea zzbf;
        String str;
        zzfk zzjc;
        String str2;
        zzab();
        zzlc();
        this.zzasg = true;
        try {
            this.zzacv.zzgl();
            Boolean zzkr = this.zzacv.zzga().zzkr();
            if (zzkr == null) {
                zzjc = this.zzacv.zzgi().zziy();
                str2 = "Upload data called on the client side before use of service was decided";
            } else if (zzkr.booleanValue()) {
                zzjc = this.zzacv.zzgi().zziv();
                str2 = "Upload called in the client side when service should be used";
            } else {
                if (this.zzasa <= 0) {
                    zzab();
                    if (this.zzasj != null) {
                        zzjc = this.zzacv.zzgi().zzjc();
                        str2 = "Uploading requested multiple times";
                    } else if (!zzkz().zzex()) {
                        this.zzacv.zzgi().zzjc().log("Network not connected, ignoring upload request");
                    } else {
                        long currentTimeMillis = this.zzacv.zzbt().currentTimeMillis();
                        String str3 = null;
                        zzd(null, currentTimeMillis - zzeh.zzhr());
                        long j = this.zzacv.zzgj().zzalt.get();
                        if (j != 0) {
                            this.zzacv.zzgi().zzjb().zzg("Uploading events. Elapsed time since last upload attempt (ms)", Long.valueOf(Math.abs(currentTimeMillis - j)));
                        }
                        String zzhv = zzjh().zzhv();
                        if (!TextUtils.isEmpty(zzhv)) {
                            if (this.zzasl == -1) {
                                this.zzasl = zzjh().zzic();
                            }
                            List<Pair<zzku, Long>> zzb = zzjh().zzb(zzhv, this.zzacv.zzgk().zzb(zzhv, zzez.zzaik), Math.max(0, this.zzacv.zzgk().zzb(zzhv, zzez.zzail)));
                            if (!zzb.isEmpty()) {
                                Iterator<Pair<zzku, Long>> it = zzb.iterator();
                                while (true) {
                                    if (!it.hasNext()) {
                                        str = null;
                                        break;
                                    }
                                    zzku zzku = (zzku) it.next().first;
                                    if (!TextUtils.isEmpty(zzku.zzavv)) {
                                        str = zzku.zzavv;
                                        break;
                                    }
                                }
                                if (str != null) {
                                    int i = 0;
                                    while (true) {
                                        if (i >= zzb.size()) {
                                            break;
                                        }
                                        zzku zzku2 = (zzku) zzb.get(i).first;
                                        if (!(TextUtils.isEmpty(zzku2.zzavv) || zzku2.zzavv.equals(str))) {
                                            zzb = zzb.subList(0, i);
                                            break;
                                        }
                                        i++;
                                    }
                                }
                                zzkt zzkt = new zzkt();
                                zzkt.zzavf = new zzku[zzb.size()];
                                ArrayList arrayList = new ArrayList(zzb.size());
                                boolean z = zzeh.zzht() && this.zzacv.zzgk().zzau(zzhv);
                                for (int i2 = 0; i2 < zzkt.zzavf.length; i2++) {
                                    zzkt.zzavf[i2] = (zzku) zzb.get(i2).first;
                                    arrayList.add((Long) zzb.get(i2).second);
                                    zzkt.zzavf[i2].zzavu = Long.valueOf(this.zzacv.zzgk().zzgw());
                                    zzkt.zzavf[i2].zzavk = Long.valueOf(currentTimeMillis);
                                    zzku zzku3 = zzkt.zzavf[i2];
                                    this.zzacv.zzgl();
                                    zzku3.zzavz = false;
                                    if (!z) {
                                        zzkt.zzavf[i2].zzawh = null;
                                    }
                                }
                                if (this.zzacv.zzgi().isLoggable(2)) {
                                    str3 = zzjf().zzb(zzkt);
                                }
                                byte[] zza2 = zzjf().zza(zzkt);
                                String str4 = zzez.zzaiu.get();
                                try {
                                    URL url = new URL(str4);
                                    Preconditions.checkArgument(!arrayList.isEmpty());
                                    if (this.zzasj != null) {
                                        this.zzacv.zzgi().zziv().log("Set uploading progress before finishing the previous upload");
                                    } else {
                                        this.zzasj = new ArrayList(arrayList);
                                    }
                                    this.zzacv.zzgj().zzalu.set(currentTimeMillis);
                                    String str5 = "?";
                                    if (zzkt.zzavf.length > 0) {
                                        str5 = zzkt.zzavf[0].zzth;
                                    }
                                    this.zzacv.zzgi().zzjc().zzd("Uploading data. app, uncompressed size, data", str5, Integer.valueOf(zza2.length), str3);
                                    this.zzasf = true;
                                    zzfm zzkz = zzkz();
                                    zzjv zzjv = new zzjv(this, zzhv);
                                    zzkz.zzab();
                                    zzkz.zzch();
                                    Preconditions.checkNotNull(url);
                                    Preconditions.checkNotNull(zza2);
                                    Preconditions.checkNotNull(zzjv);
                                    zzkz.zzgh().zzd(new zzfq(zzkz, zzhv, url, zza2, null, zzjv));
                                } catch (MalformedURLException unused) {
                                    this.zzacv.zzgi().zziv().zze("Failed to parse upload URL. Not uploading. appId", zzfi.zzbp(zzhv), str4);
                                }
                            }
                        } else {
                            this.zzasl = -1;
                            String zzag = zzjh().zzag(currentTimeMillis - zzeh.zzhr());
                            if (!TextUtils.isEmpty(zzag) && (zzbf = zzjh().zzbf(zzag)) != null) {
                                zzb(zzbf);
                            }
                        }
                    }
                }
                zzlg();
            }
            zzjc.log(str2);
        } finally {
            this.zzasg = false;
            zzlh();
        }
    }

    /* access modifiers changed from: package-private */
    public final void zzlj() {
        zzfk zziv;
        String str;
        zzab();
        zzlc();
        if (!this.zzarz) {
            this.zzacv.zzgi().zzja().log("This instance being marked as an uploader");
            zzab();
            zzlc();
            if (zzlk() && zzli()) {
                int zza2 = zza(this.zzasi);
                int zzis = this.zzacv.zzfz().zzis();
                zzab();
                if (zza2 > zzis) {
                    zziv = this.zzacv.zzgi().zziv();
                    str = "Panic: can't downgrade version. Previous, current version";
                } else if (zza2 < zzis) {
                    if (zza(zzis, this.zzasi)) {
                        zziv = this.zzacv.zzgi().zzjc();
                        str = "Storage version upgraded. Previous, current version";
                    } else {
                        zziv = this.zzacv.zzgi().zziv();
                        str = "Storage version upgrade failed. Previous, current version";
                    }
                }
                zziv.zze(str, Integer.valueOf(zza2), Integer.valueOf(zzis));
            }
            this.zzarz = true;
            zzlg();
        }
    }

    /* access modifiers changed from: package-private */
    public final void zzll() {
        this.zzasd++;
    }

    /* access modifiers changed from: package-private */
    public final zzgn zzlm() {
        return this.zzacv;
    }

    /* access modifiers changed from: package-private */
    public final void zzm(boolean z) {
        zzlg();
    }
}
