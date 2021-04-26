package com.google.android.gms.internal.measurement;

import android.text.TextUtils;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.common.util.VisibleForTesting;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.Locale;

@VisibleForTesting
public final class zzah extends zzar {
    private static boolean zzuy;
    private AdvertisingIdClient.Info zzuz;
    private final zzcz zzva;
    private String zzvb;
    private boolean zzvc = false;
    private final Object zzvd = new Object();

    zzah(zzat zzat) {
        super(zzat);
        this.zzva = new zzcz(zzat.zzbt());
    }

    private final boolean zza(AdvertisingIdClient.Info info, AdvertisingIdClient.Info info2) {
        String str = null;
        String id = info2 == null ? null : info2.getId();
        if (TextUtils.isEmpty(id)) {
            return true;
        }
        String zzdn = zzcc().zzdn();
        synchronized (this.zzvd) {
            if (!this.zzvc) {
                this.zzvb = zzbm();
                this.zzvc = true;
            } else if (TextUtils.isEmpty(this.zzvb)) {
                if (info != null) {
                    str = info.getId();
                }
                if (str == null) {
                    String valueOf = String.valueOf(id);
                    String valueOf2 = String.valueOf(zzdn);
                    return zzp(valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf));
                }
                String valueOf3 = String.valueOf(str);
                String valueOf4 = String.valueOf(zzdn);
                this.zzvb = zzo(valueOf4.length() != 0 ? valueOf3.concat(valueOf4) : new String(valueOf3));
            }
            String valueOf5 = String.valueOf(id);
            String valueOf6 = String.valueOf(zzdn);
            String zzo = zzo(valueOf6.length() != 0 ? valueOf5.concat(valueOf6) : new String(valueOf5));
            if (TextUtils.isEmpty(zzo)) {
                return false;
            }
            if (zzo.equals(this.zzvb)) {
                return true;
            }
            if (!TextUtils.isEmpty(this.zzvb)) {
                zzq("Resetting the client id because Advertising Id changed.");
                zzdn = zzcc().zzdo();
                zza("New client Id", zzdn);
            }
            String valueOf7 = String.valueOf(id);
            String valueOf8 = String.valueOf(zzdn);
            return zzp(valueOf8.length() != 0 ? valueOf7.concat(valueOf8) : new String(valueOf7));
        }
    }

    private final synchronized AdvertisingIdClient.Info zzbk() {
        if (this.zzva.zzj(1000)) {
            this.zzva.start();
            AdvertisingIdClient.Info zzbl = zzbl();
            if (!zza(this.zzuz, zzbl)) {
                zzu("Failed to reset client id on adid change. Not using adid");
                zzbl = new AdvertisingIdClient.Info("", false);
            }
            this.zzuz = zzbl;
        }
        return this.zzuz;
    }

    private final AdvertisingIdClient.Info zzbl() {
        try {
            return AdvertisingIdClient.getAdvertisingIdInfo(getContext());
        } catch (IllegalStateException unused) {
            zzt("IllegalStateException getting Ad Id Info. If you would like to see Audience reports, please ensure that you have added '<meta-data android:name=\"com.google.android.gms.version\" android:value=\"@integer/google_play_services_version\" />' to your application manifest file. See http://goo.gl/naFqQk for details.");
        } catch (Exception e) {
            if (!zzuy) {
                zzuy = true;
                zzd("Error getting advertiser id", e);
            }
        }
        return null;
    }

    private final String zzbm() {
        IOException e;
        String str = null;
        try {
            FileInputStream openFileInput = getContext().openFileInput("gaClientIdData");
            byte[] bArr = new byte[128];
            int read = openFileInput.read(bArr, 0, 128);
            if (openFileInput.available() > 0) {
                zzt("Hash file seems corrupted, deleting it.");
                openFileInput.close();
                getContext().deleteFile("gaClientIdData");
                return null;
            } else if (read <= 0) {
                zzq("Hash file is empty.");
                openFileInput.close();
                return null;
            } else {
                String str2 = new String(bArr, 0, read);
                try {
                    openFileInput.close();
                } catch (FileNotFoundException unused) {
                } catch (IOException e2) {
                    e = e2;
                    str = str2;
                    zzd("Error reading Hash file, deleting it", e);
                    getContext().deleteFile("gaClientIdData");
                    return str;
                }
                return str2;
            }
        } catch (FileNotFoundException unused2) {
            return null;
        } catch (IOException e3) {
            e = e3;
            zzd("Error reading Hash file, deleting it", e);
            getContext().deleteFile("gaClientIdData");
            return str;
        }
    }

    private static String zzo(String str) {
        MessageDigest messageDigest = zzdd.getMessageDigest("MD5");
        if (messageDigest == null) {
            return null;
        }
        return String.format(Locale.US, "%032X", new BigInteger(1, messageDigest.digest(str.getBytes())));
    }

    private final boolean zzp(String str) {
        try {
            String zzo = zzo(str);
            zzq("Storing hashed adid.");
            FileOutputStream openFileOutput = getContext().openFileOutput("gaClientIdData", 0);
            openFileOutput.write(zzo.getBytes());
            openFileOutput.close();
            this.zzvb = zzo;
            return true;
        } catch (IOException e) {
            zze("Error creating hash file", e);
            return false;
        }
    }

    /* access modifiers changed from: protected */
    @Override // com.google.android.gms.internal.measurement.zzar
    public final void zzac() {
    }

    public final boolean zzbc() {
        zzch();
        AdvertisingIdClient.Info zzbk = zzbk();
        return zzbk != null && !zzbk.isLimitAdTrackingEnabled();
    }

    public final String zzbj() {
        zzch();
        AdvertisingIdClient.Info zzbk = zzbk();
        String id = zzbk != null ? zzbk.getId() : null;
        if (TextUtils.isEmpty(id)) {
            return null;
        }
        return id;
    }
}
