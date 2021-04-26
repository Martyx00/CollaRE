package com.google.android.gms.tagmanager;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import com.google.android.gms.common.util.VisibleForTesting;
import io.a.a.a.a.b.a;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Locale;

/* access modifiers changed from: package-private */
public final class zzfu implements zzbe {
    private final String zzaay;
    private final zzfx zzbei;
    private final zzfw zzbej;
    private final Context zzqx;

    @VisibleForTesting
    zzfu(Context context, zzfw zzfw) {
        this(new zzfv(), context, zzfw);
    }

    @VisibleForTesting
    private zzfu(zzfx zzfx, Context context, zzfw zzfw) {
        this.zzbei = zzfx;
        this.zzqx = context.getApplicationContext();
        this.zzbej = zzfw;
        String str = Build.VERSION.RELEASE;
        Locale locale = Locale.getDefault();
        String str2 = null;
        if (!(locale == null || locale.getLanguage() == null || locale.getLanguage().length() == 0)) {
            StringBuilder sb = new StringBuilder();
            sb.append(locale.getLanguage().toLowerCase());
            if (!(locale.getCountry() == null || locale.getCountry().length() == 0)) {
                sb.append("-");
                sb.append(locale.getCountry().toLowerCase());
            }
            str2 = sb.toString();
        }
        this.zzaay = String.format("%s/%s (Linux; U; Android %s; %s; %s Build/%s)", "GoogleTagManager", "4.00", str, str2, Build.MODEL, Build.ID);
    }

    @VisibleForTesting
    private static URL zzd(zzbw zzbw) {
        try {
            return new URL(zzbw.zznw());
        } catch (MalformedURLException unused) {
            zzdi.e("Error trying to parse the GTM url.");
            return null;
        }
    }

    @Override // com.google.android.gms.tagmanager.zzbe
    public final void zze(List<zzbw> list) {
        int min = Math.min(list.size(), 40);
        boolean z = true;
        for (int i = 0; i < min; i++) {
            zzbw zzbw = list.get(i);
            URL zzd = zzd(zzbw);
            if (zzd == null) {
                zzdi.zzab("No destination: discarding hit.");
                this.zzbej.zzb(zzbw);
            } else {
                InputStream inputStream = null;
                try {
                    HttpURLConnection zzc = this.zzbei.zzc(zzd);
                    if (z) {
                        try {
                            zzdn.zzp(this.zzqx);
                            z = false;
                        } catch (Throwable th) {
                            if (0 != 0) {
                                inputStream.close();
                            }
                            zzc.disconnect();
                            throw th;
                        }
                    }
                    zzc.setRequestProperty(a.HEADER_USER_AGENT, this.zzaay);
                    int responseCode = zzc.getResponseCode();
                    InputStream inputStream2 = zzc.getInputStream();
                    if (responseCode != 200) {
                        StringBuilder sb = new StringBuilder(25);
                        sb.append("Bad response: ");
                        sb.append(responseCode);
                        zzdi.zzab(sb.toString());
                        this.zzbej.zzc(zzbw);
                    } else {
                        this.zzbej.zza(zzbw);
                    }
                    if (inputStream2 != null) {
                        inputStream2.close();
                    }
                    zzc.disconnect();
                } catch (IOException e) {
                    String valueOf = String.valueOf(e.getClass().getSimpleName());
                    zzdi.zzab(valueOf.length() != 0 ? "Exception sending hit: ".concat(valueOf) : new String("Exception sending hit: "));
                    zzdi.zzab(e.getMessage());
                    this.zzbej.zzc(zzbw);
                }
            }
        }
    }

    @Override // com.google.android.gms.tagmanager.zzbe
    public final boolean zznl() {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) this.zzqx.getSystemService("connectivity")).getActiveNetworkInfo();
        if (activeNetworkInfo != null && activeNetworkInfo.isConnected()) {
            return true;
        }
        zzdi.v("...no network connectivity");
        return false;
    }
}
