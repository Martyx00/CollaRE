package com.google.android.gms.tagmanager;

import android.net.Uri;
import com.google.android.gms.common.util.VisibleForTesting;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/* access modifiers changed from: package-private */
@VisibleForTesting
public class zzeh {
    private static zzeh zzbbx;
    private volatile String zzaxm = null;
    private volatile zza zzbby = zza.NONE;
    private volatile String zzbbz = null;
    private volatile String zzbca = null;

    /* access modifiers changed from: package-private */
    public enum zza {
        NONE,
        CONTAINER,
        CONTAINER_DEBUG
    }

    zzeh() {
    }

    private static String zzdk(String str) {
        return str.split("&")[0].split("=")[1];
    }

    static zzeh zzok() {
        zzeh zzeh;
        synchronized (zzeh.class) {
            if (zzbbx == null) {
                zzbbx = new zzeh();
            }
            zzeh = zzbbx;
        }
        return zzeh;
    }

    /* access modifiers changed from: package-private */
    public final String getContainerId() {
        return this.zzaxm;
    }

    /* access modifiers changed from: package-private */
    public final synchronized boolean zzb(Uri uri) {
        try {
            String decode = URLDecoder.decode(uri.toString(), "UTF-8");
            if (decode.matches("^tagmanager.c.\\S+:\\/\\/preview\\/p\\?id=\\S+&gtm_auth=\\S+&gtm_preview=\\d+(&gtm_debug=x)?$")) {
                String valueOf = String.valueOf(decode);
                zzdi.v(valueOf.length() != 0 ? "Container preview url: ".concat(valueOf) : new String("Container preview url: "));
                this.zzbby = decode.matches(".*?&gtm_debug=x$") ? zza.CONTAINER_DEBUG : zza.CONTAINER;
                this.zzbca = uri.getQuery().replace("&gtm_debug=x", "");
                if (this.zzbby == zza.CONTAINER || this.zzbby == zza.CONTAINER_DEBUG) {
                    String valueOf2 = String.valueOf("/r?");
                    String valueOf3 = String.valueOf(this.zzbca);
                    this.zzbbz = valueOf3.length() != 0 ? valueOf2.concat(valueOf3) : new String(valueOf2);
                }
                this.zzaxm = zzdk(this.zzbca);
                return true;
            } else if (!decode.matches("^tagmanager.c.\\S+:\\/\\/preview\\/p\\?id=\\S+&gtm_preview=$")) {
                String valueOf4 = String.valueOf(decode);
                zzdi.zzab(valueOf4.length() != 0 ? "Invalid preview uri: ".concat(valueOf4) : new String("Invalid preview uri: "));
                return false;
            } else if (!zzdk(uri.getQuery()).equals(this.zzaxm)) {
                return false;
            } else {
                String valueOf5 = String.valueOf(this.zzaxm);
                zzdi.v(valueOf5.length() != 0 ? "Exit preview mode for container: ".concat(valueOf5) : new String("Exit preview mode for container: "));
                this.zzbby = zza.NONE;
                this.zzbbz = null;
                return true;
            }
        } catch (UnsupportedEncodingException unused) {
            return false;
        }
    }

    /* access modifiers changed from: package-private */
    public final zza zzol() {
        return this.zzbby;
    }

    /* access modifiers changed from: package-private */
    public final String zzom() {
        return this.zzbbz;
    }
}
