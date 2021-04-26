package com.google.android.gms.tagmanager;

import android.content.Context;
import android.content.res.Resources;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.measurement.zzaci;
import com.google.android.gms.internal.measurement.zzacj;
import com.google.android.gms.internal.measurement.zzi;
import com.google.android.gms.internal.measurement.zzwc;
import com.google.android.gms.internal.measurement.zzwe;
import com.google.android.gms.internal.measurement.zzwi;
import com.google.android.gms.internal.measurement.zzwm;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.json.JSONException;

/* access modifiers changed from: package-private */
public final class zzex implements zzah {
    private final ExecutorService executor = Executors.newSingleThreadExecutor();
    private final String zzaxm;
    private zzdh<zzwc> zzbcn;
    private final Context zzqx;

    zzex(Context context, String str) {
        this.zzqx = context;
        this.zzaxm = str;
    }

    private static zzwi zza(ByteArrayOutputStream byteArrayOutputStream) {
        try {
            return zzda.zzdi(byteArrayOutputStream.toString("UTF-8"));
        } catch (UnsupportedEncodingException unused) {
            zzdi.zzda("Failed to convert binary resource to string for JSON parsing; the file format is not UTF-8 format.");
            return null;
        } catch (JSONException unused2) {
            zzdi.zzab("Failed to extract the container from the resource file. Resource is a UTF-8 encoded string but doesn't contain a JSON container");
            return null;
        }
    }

    private static zzwi zze(byte[] bArr) {
        try {
            zzwi zza = zzwe.zza((zzi) zzacj.zza(new zzi(), bArr));
            if (zza != null) {
                zzdi.v("The container was successfully loaded from the resource (using binary file)");
            }
            return zza;
        } catch (zzaci unused) {
            zzdi.e("The resource file is corrupted. The container cannot be extracted from the binary file");
            return null;
        } catch (zzwm unused2) {
            zzdi.zzab("The resource file is invalid. The container from the binary file is invalid");
            return null;
        }
    }

    @VisibleForTesting
    private final File zzoq() {
        String valueOf = String.valueOf("resource_");
        String valueOf2 = String.valueOf(this.zzaxm);
        return new File(this.zzqx.getDir("google_tagmanager", 0), valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf));
    }

    @Override // com.google.android.gms.common.api.Releasable
    public final synchronized void release() {
        this.executor.shutdown();
    }

    @Override // com.google.android.gms.tagmanager.zzah
    public final void zza(zzwc zzwc) {
        this.executor.execute(new zzez(this, zzwc));
    }

    @Override // com.google.android.gms.tagmanager.zzah
    public final void zza(zzdh<zzwc> zzdh) {
        this.zzbcn = zzdh;
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public final boolean zzb(zzwc zzwc) {
        File zzoq = zzoq();
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(zzoq);
            try {
                byte[] bArr = new byte[zzwc.zzwb()];
                zzacj.zza(zzwc, bArr, 0, bArr.length);
                fileOutputStream.write(bArr);
                try {
                    fileOutputStream.close();
                    return true;
                } catch (IOException unused) {
                    zzdi.zzab("error closing stream for writing resource to disk");
                    return true;
                }
            } catch (IOException unused2) {
                zzdi.zzab("Error writing resource to disk. Removing resource from disk.");
                zzoq.delete();
                try {
                    fileOutputStream.close();
                } catch (IOException unused3) {
                    zzdi.zzab("error closing stream for writing resource to disk");
                }
                return false;
            } catch (Throwable th) {
                try {
                    fileOutputStream.close();
                } catch (IOException unused4) {
                    zzdi.zzab("error closing stream for writing resource to disk");
                }
                throw th;
            }
        } catch (FileNotFoundException unused5) {
            zzdi.e("Error opening resource file for writing");
            return false;
        }
    }

    @Override // com.google.android.gms.tagmanager.zzah
    public final void zzmx() {
        this.executor.execute(new zzey(this));
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Missing exception handler attribute for start block: B:22:0x0075 */
    @com.google.android.gms.common.util.VisibleForTesting
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zzop() {
        /*
        // Method dump skipped, instructions count: 188
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.tagmanager.zzex.zzop():void");
    }

    @Override // com.google.android.gms.tagmanager.zzah
    public final zzwi zzr(int i) {
        String sb;
        try {
            InputStream openRawResource = this.zzqx.getResources().openRawResource(i);
            String resourceName = this.zzqx.getResources().getResourceName(i);
            StringBuilder sb2 = new StringBuilder(String.valueOf(resourceName).length() + 66);
            sb2.append("Attempting to load a container from the resource ID ");
            sb2.append(i);
            sb2.append(" (");
            sb2.append(resourceName);
            sb2.append(")");
            zzdi.v(sb2.toString());
            try {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                zzwe.zza(openRawResource, byteArrayOutputStream);
                zzwi zza = zza(byteArrayOutputStream);
                if (zza == null) {
                    return zze(byteArrayOutputStream.toByteArray());
                }
                zzdi.v("The container was successfully loaded from the resource (using JSON file format)");
                return zza;
            } catch (IOException unused) {
                String resourceName2 = this.zzqx.getResources().getResourceName(i);
                StringBuilder sb3 = new StringBuilder(String.valueOf(resourceName2).length() + 67);
                sb3.append("Error reading the default container with resource ID ");
                sb3.append(i);
                sb3.append(" (");
                sb3.append(resourceName2);
                sb3.append(")");
                sb = sb3.toString();
                zzdi.zzab(sb);
                return null;
            }
        } catch (Resources.NotFoundException unused2) {
            StringBuilder sb4 = new StringBuilder(98);
            sb4.append("Failed to load the container. No default container resource found with the resource ID ");
            sb4.append(i);
            sb = sb4.toString();
            zzdi.zzab(sb);
            return null;
        }
    }
}
