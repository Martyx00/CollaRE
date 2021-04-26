package com.google.android.gms.internal.measurement;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import com.google.android.gms.analytics.zzk;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;
import io.a.a.a.a.b.a;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Locale;
import java.util.Map;

/* access modifiers changed from: package-private */
public final class zzco extends zzar {
    private static final byte[] zzaba = "\n".getBytes();
    private final String zzaay = String.format("%s/%s (Linux; U; Android %s; %s; %s Build/%s)", "GoogleAnalytics", zzas.VERSION, Build.VERSION.RELEASE, zzdd.zza(Locale.getDefault()), Build.MODEL, Build.ID);
    private final zzcz zzaaz;

    zzco(zzat zzat) {
        super(zzat);
        this.zzaaz = new zzcz(zzat.zzbt());
    }

    private final int zza(URL url) {
        Preconditions.checkNotNull(url);
        zzb("GET request", url);
        HttpURLConnection httpURLConnection = null;
        try {
            HttpURLConnection zzb = zzb(url);
            zzb.connect();
            zza(zzb);
            int responseCode = zzb.getResponseCode();
            if (responseCode == 200) {
                zzby().zzbr();
            }
            zzb("GET status", Integer.valueOf(responseCode));
            if (zzb != null) {
                zzb.disconnect();
            }
            return responseCode;
        } catch (IOException e) {
            zzd("Network GET connection error", e);
            if (0 == 0) {
                return 0;
            }
            httpURLConnection.disconnect();
            return 0;
        } catch (Throwable th) {
            if (0 != 0) {
                httpURLConnection.disconnect();
            }
            throw th;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:29:0x0079 A[SYNTHETIC, Splitter:B:29:0x0079] */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x0085  */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x008d A[SYNTHETIC, Splitter:B:37:0x008d] */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x0099  */
    /* JADX WARNING: Removed duplicated region for block: B:45:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final int zza(java.net.URL r4, byte[] r5) {
        /*
        // Method dump skipped, instructions count: 157
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzco.zza(java.net.URL, byte[]):int");
    }

    private static void zza(StringBuilder sb, String str, String str2) {
        if (sb.length() != 0) {
            sb.append('&');
        }
        sb.append(URLEncoder.encode(str, "UTF-8"));
        sb.append('=');
        sb.append(URLEncoder.encode(str2, "UTF-8"));
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x0021 A[SYNTHETIC, Splitter:B:17:0x0021] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void zza(java.net.HttpURLConnection r3) {
        /*
            r2 = this;
            java.io.InputStream r3 = r3.getInputStream()     // Catch:{ all -> 0x001d }
            r0 = 1024(0x400, float:1.435E-42)
            byte[] r0 = new byte[r0]     // Catch:{ all -> 0x001b }
        L_0x0008:
            int r1 = r3.read(r0)     // Catch:{ all -> 0x001b }
            if (r1 > 0) goto L_0x0008
            if (r3 == 0) goto L_0x001a
            r3.close()     // Catch:{ IOException -> 0x0014 }
            return
        L_0x0014:
            r3 = move-exception
            java.lang.String r0 = "Error closing http connection input stream"
            r2.zze(r0, r3)
        L_0x001a:
            return
        L_0x001b:
            r0 = move-exception
            goto L_0x001f
        L_0x001d:
            r0 = move-exception
            r3 = 0
        L_0x001f:
            if (r3 == 0) goto L_0x002b
            r3.close()     // Catch:{ IOException -> 0x0025 }
            goto L_0x002b
        L_0x0025:
            r3 = move-exception
            java.lang.String r1 = "Error closing http connection input stream"
            r2.zze(r1, r3)
        L_0x002b:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzco.zza(java.net.HttpURLConnection):void");
    }

    /* JADX WARNING: Removed duplicated region for block: B:41:0x00d8 A[SYNTHETIC, Splitter:B:41:0x00d8] */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x00e4  */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x00ec A[SYNTHETIC, Splitter:B:49:0x00ec] */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x00f8  */
    /* JADX WARNING: Removed duplicated region for block: B:57:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final int zzb(java.net.URL r10, byte[] r11) {
        /*
        // Method dump skipped, instructions count: 252
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzco.zzb(java.net.URL, byte[]):int");
    }

    @VisibleForTesting
    private final HttpURLConnection zzb(URL url) {
        URLConnection openConnection = url.openConnection();
        if (openConnection instanceof HttpURLConnection) {
            HttpURLConnection httpURLConnection = (HttpURLConnection) openConnection;
            httpURLConnection.setDefaultUseCaches(false);
            httpURLConnection.setConnectTimeout(zzcc.zzzn.get().intValue());
            httpURLConnection.setReadTimeout(zzcc.zzzo.get().intValue());
            httpURLConnection.setInstanceFollowRedirects(false);
            httpURLConnection.setRequestProperty(a.HEADER_USER_AGENT, this.zzaay);
            httpURLConnection.setDoInput(true);
            return httpURLConnection;
        }
        throw new IOException("Failed to obtain http connection");
    }

    private final URL zzb(zzch zzch, String str) {
        String str2;
        StringBuilder sb;
        String str3;
        if (zzch.zzep()) {
            str2 = zzbu.zzdz();
            str3 = zzbu.zzeb();
            sb = new StringBuilder(String.valueOf(str2).length() + 1 + String.valueOf(str3).length() + String.valueOf(str).length());
        } else {
            str2 = zzbu.zzea();
            str3 = zzbu.zzeb();
            sb = new StringBuilder(String.valueOf(str2).length() + 1 + String.valueOf(str3).length() + String.valueOf(str).length());
        }
        sb.append(str2);
        sb.append(str3);
        sb.append("?");
        sb.append(str);
        try {
            return new URL(sb.toString());
        } catch (MalformedURLException e) {
            zze("Error trying to parse the hardcoded host url", e);
            return null;
        }
    }

    private final URL zzd(zzch zzch) {
        String str;
        String valueOf;
        String valueOf2;
        String str2;
        if (zzch.zzep()) {
            valueOf = String.valueOf(zzbu.zzdz());
            valueOf2 = String.valueOf(zzbu.zzeb());
            if (valueOf2.length() == 0) {
                str2 = new String(valueOf);
                str = str2;
                return new URL(str);
            }
        } else {
            valueOf = String.valueOf(zzbu.zzea());
            valueOf2 = String.valueOf(zzbu.zzeb());
            if (valueOf2.length() == 0) {
                str2 = new String(valueOf);
                str = str2;
                return new URL(str);
            }
        }
        str = valueOf.concat(valueOf2);
        try {
            return new URL(str);
        } catch (MalformedURLException e) {
            zze("Error trying to parse the hardcoded host url", e);
            return null;
        }
    }

    private final URL zzey() {
        String valueOf = String.valueOf(zzbu.zzdz());
        String valueOf2 = String.valueOf(zzcc.zzzc.get());
        try {
            return new URL(valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf));
        } catch (MalformedURLException e) {
            zze("Error trying to parse the hardcoded host url", e);
            return null;
        }
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public final String zza(zzch zzch, boolean z) {
        Preconditions.checkNotNull(zzch);
        StringBuilder sb = new StringBuilder();
        try {
            for (Map.Entry<String, String> entry : zzch.zzcs().entrySet()) {
                String key = entry.getKey();
                if (!"ht".equals(key) && !"qt".equals(key) && !"AppUID".equals(key) && !"z".equals(key) && !"_gmsv".equals(key)) {
                    zza(sb, key, entry.getValue());
                }
            }
            zza(sb, "ht", String.valueOf(zzch.zzen()));
            zza(sb, "qt", String.valueOf(zzbt().currentTimeMillis() - zzch.zzen()));
            if (z) {
                long zzeq = zzch.zzeq();
                zza(sb, "z", zzeq != 0 ? String.valueOf(zzeq) : String.valueOf(zzch.zzem()));
            }
            return sb.toString();
        } catch (UnsupportedEncodingException e) {
            zze("Failed to encode name or value", e);
            return null;
        }
    }

    /* access modifiers changed from: protected */
    @Override // com.google.android.gms.internal.measurement.zzar
    public final void zzac() {
        zza("Network initialized. User agent", this.zzaay);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:53:0x0159, code lost:
        if (zza(r5) == 200) goto L_0x0135;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:65:0x0192, code lost:
        if (zza(r6, r5) == 200) goto L_0x0135;
     */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x0060  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x0105  */
    /* JADX WARNING: Removed duplicated region for block: B:67:0x0197  */
    /* JADX WARNING: Removed duplicated region for block: B:72:0x01ac A[EDGE_INSN: B:72:0x01ac->B:69:0x01ac ?: BREAK  , SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.util.List<java.lang.Long> zzb(java.util.List<com.google.android.gms.internal.measurement.zzch> r9) {
        /*
        // Method dump skipped, instructions count: 429
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzco.zzb(java.util.List):java.util.List");
    }

    public final boolean zzex() {
        NetworkInfo networkInfo;
        zzk.zzab();
        zzch();
        try {
            networkInfo = ((ConnectivityManager) getContext().getSystemService("connectivity")).getActiveNetworkInfo();
        } catch (SecurityException unused) {
            networkInfo = null;
        }
        if (networkInfo != null && networkInfo.isConnected()) {
            return true;
        }
        zzq("No network connectivity");
        return false;
    }
}
