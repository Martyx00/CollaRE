package com.google.android.gms.tagmanager;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.measurement.zzacj;
import com.google.android.gms.internal.measurement.zzl;
import com.google.android.gms.internal.measurement.zzwe;
import com.google.android.gms.internal.measurement.zzwp;
import com.google.android.gms.internal.measurement.zzwq;
import com.google.android.gms.internal.measurement.zzwr;
import com.google.android.gms.tagmanager.zzeh;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

final class zzer implements Runnable {
    private final String zzaxm;
    private volatile String zzaym;
    private final zzwq zzbcl;
    private final String zzbcm;
    private zzdh<zzl> zzbcn;
    private volatile zzal zzbco;
    private volatile String zzbcp;
    private final Context zzqx;

    @VisibleForTesting
    private zzer(Context context, String str, zzwq zzwq, zzal zzal) {
        this.zzqx = context;
        this.zzbcl = zzwq;
        this.zzaxm = str;
        this.zzbco = zzal;
        String valueOf = String.valueOf("/r?id=");
        String valueOf2 = String.valueOf(str);
        this.zzbcm = valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf);
        this.zzaym = this.zzbcm;
        this.zzbcp = null;
    }

    public zzer(Context context, String str, zzal zzal) {
        this(context, str, new zzwq(), zzal);
    }

    public final void run() {
        boolean z;
        zzdh<zzl> zzdh = this.zzbcn;
        if (zzdh != null) {
            zzdh.zzmw();
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) this.zzqx.getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo == null || !activeNetworkInfo.isConnected()) {
                zzdi.v("...no network connectivity");
                z = false;
            } else {
                z = true;
            }
            if (!z) {
                this.zzbcn.zzq(zzcz.zzbbb);
                return;
            }
            zzdi.v("Start loading resource from network ...");
            String zznd = this.zzbco.zznd();
            String str = this.zzaym;
            StringBuilder sb = new StringBuilder(String.valueOf(zznd).length() + 12 + String.valueOf(str).length());
            sb.append(zznd);
            sb.append(str);
            sb.append("&v=a65833898");
            String sb2 = sb.toString();
            if (this.zzbcp != null && !this.zzbcp.trim().equals("")) {
                String valueOf = String.valueOf(sb2);
                String str2 = this.zzbcp;
                StringBuilder sb3 = new StringBuilder(String.valueOf(valueOf).length() + 4 + String.valueOf(str2).length());
                sb3.append(valueOf);
                sb3.append("&pv=");
                sb3.append(str2);
                sb2 = sb3.toString();
            }
            if (zzeh.zzok().zzol().equals(zzeh.zza.CONTAINER_DEBUG)) {
                String valueOf2 = String.valueOf(sb2);
                String valueOf3 = String.valueOf("&gtm_debug=x");
                sb2 = valueOf3.length() != 0 ? valueOf2.concat(valueOf3) : new String(valueOf2);
            }
            zzwp zzsf = zzwq.zzsf();
            InputStream inputStream = null;
            try {
                inputStream = zzsf.zzem(sb2);
            } catch (FileNotFoundException unused) {
                String str3 = this.zzaxm;
                StringBuilder sb4 = new StringBuilder(String.valueOf(sb2).length() + 79 + String.valueOf(str3).length());
                sb4.append("No data is retrieved from the given url: ");
                sb4.append(sb2);
                sb4.append(". Make sure container_id: ");
                sb4.append(str3);
                sb4.append(" is correct.");
                zzdi.zzab(sb4.toString());
                this.zzbcn.zzq(zzcz.zzbbd);
                zzsf.close();
                return;
            } catch (zzwr unused2) {
                String valueOf4 = String.valueOf(sb2);
                zzdi.zzab(valueOf4.length() != 0 ? "Error when loading resource for url: ".concat(valueOf4) : new String("Error when loading resource for url: "));
                this.zzbcn.zzq(zzcz.zzbbe);
            } catch (IOException e) {
                String message = e.getMessage();
                StringBuilder sb5 = new StringBuilder(String.valueOf(sb2).length() + 40 + String.valueOf(message).length());
                sb5.append("Error when loading resources from url: ");
                sb5.append(sb2);
                sb5.append(" ");
                sb5.append(message);
                zzdi.zzb(sb5.toString(), e);
                this.zzbcn.zzq(zzcz.zzbbc);
                zzsf.close();
                return;
            } catch (Throwable th) {
                zzsf.close();
                throw th;
            }
            try {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                zzwe.zza(inputStream, byteArrayOutputStream);
                zzl zzl = (zzl) zzacj.zza(new zzl(), byteArrayOutputStream.toByteArray());
                String valueOf5 = String.valueOf(zzl);
                StringBuilder sb6 = new StringBuilder(String.valueOf(valueOf5).length() + 43);
                sb6.append("Successfully loaded supplemented resource: ");
                sb6.append(valueOf5);
                zzdi.v(sb6.toString());
                if (zzl.zzpv == null && zzl.zzpu.length == 0) {
                    String valueOf6 = String.valueOf(this.zzaxm);
                    zzdi.v(valueOf6.length() != 0 ? "No change for container: ".concat(valueOf6) : new String("No change for container: "));
                }
                this.zzbcn.onSuccess(zzl);
                zzsf.close();
                zzdi.v("Load resource from network finished.");
            } catch (IOException e2) {
                String message2 = e2.getMessage();
                StringBuilder sb7 = new StringBuilder(String.valueOf(sb2).length() + 51 + String.valueOf(message2).length());
                sb7.append("Error when parsing downloaded resources from url: ");
                sb7.append(sb2);
                sb7.append(" ");
                sb7.append(message2);
                zzdi.zzb(sb7.toString(), e2);
                this.zzbcn.zzq(zzcz.zzbbd);
                zzsf.close();
            }
        } else {
            throw new IllegalStateException("callback must be set before execute");
        }
    }

    /* access modifiers changed from: package-private */
    public final void zza(zzdh<zzl> zzdh) {
        this.zzbcn = zzdh;
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public final void zzct(String str) {
        if (str == null) {
            str = this.zzbcm;
        } else {
            String valueOf = String.valueOf(str);
            zzdi.zzda(valueOf.length() != 0 ? "Setting CTFE URL path: ".concat(valueOf) : new String("Setting CTFE URL path: "));
        }
        this.zzaym = str;
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public final void zzdl(String str) {
        String valueOf = String.valueOf(str);
        zzdi.zzda(valueOf.length() != 0 ? "Setting previous container version: ".concat(valueOf) : new String("Setting previous container version: "));
        this.zzbcp = str;
    }
}
