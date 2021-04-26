package com.google.android.gms.internal.measurement;

import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import org.spongycastle.asn1.eac.EACTags;
import org.spongycastle.crypto.tls.CipherSuite;

public final class zzi extends zzacd<zzi> {
    public String version = "";
    private String[] zzoh = zzacm.zzbzx;
    public String[] zzoi = zzacm.zzbzx;
    public zzm[] zzoj = zzm.zzg();
    public zzh[] zzok = zzh.zzd();
    public zze[] zzol = zze.zzb();
    public zze[] zzom = zze.zzb();
    public zze[] zzon = zze.zzb();
    public zzj[] zzoo = zzj.zze();
    private String zzop = "";
    private String zzoq = "";
    private String zzor = "0";
    private zzd zzos = null;
    private float zzot = BitmapDescriptorFactory.HUE_RED;
    private boolean zzou = false;
    private String[] zzov = zzacm.zzbzx;
    public int zzow = 0;

    public zzi() {
        this.zzbzd = null;
        this.zzbzo = -1;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzi)) {
            return false;
        }
        zzi zzi = (zzi) obj;
        if (!zzach.equals(this.zzoh, zzi.zzoh) || !zzach.equals(this.zzoi, zzi.zzoi) || !zzach.equals(this.zzoj, zzi.zzoj) || !zzach.equals(this.zzok, zzi.zzok) || !zzach.equals(this.zzol, zzi.zzol) || !zzach.equals(this.zzom, zzi.zzom) || !zzach.equals(this.zzon, zzi.zzon) || !zzach.equals(this.zzoo, zzi.zzoo)) {
            return false;
        }
        String str = this.zzop;
        if (str == null) {
            if (zzi.zzop != null) {
                return false;
            }
        } else if (!str.equals(zzi.zzop)) {
            return false;
        }
        String str2 = this.zzoq;
        if (str2 == null) {
            if (zzi.zzoq != null) {
                return false;
            }
        } else if (!str2.equals(zzi.zzoq)) {
            return false;
        }
        String str3 = this.zzor;
        if (str3 == null) {
            if (zzi.zzor != null) {
                return false;
            }
        } else if (!str3.equals(zzi.zzor)) {
            return false;
        }
        String str4 = this.version;
        if (str4 == null) {
            if (zzi.version != null) {
                return false;
            }
        } else if (!str4.equals(zzi.version)) {
            return false;
        }
        zzd zzd = this.zzos;
        if (zzd == null) {
            if (zzi.zzos != null) {
                return false;
            }
        } else if (!zzd.equals(zzi.zzos)) {
            return false;
        }
        if (Float.floatToIntBits(this.zzot) == Float.floatToIntBits(zzi.zzot) && this.zzou == zzi.zzou && zzach.equals(this.zzov, zzi.zzov) && this.zzow == zzi.zzow) {
            return (this.zzbzd == null || this.zzbzd.isEmpty()) ? zzi.zzbzd == null || zzi.zzbzd.isEmpty() : this.zzbzd.equals(zzi.zzbzd);
        }
        return false;
    }

    public final int hashCode() {
        int hashCode = (((((((((((((((((getClass().getName().hashCode() + 527) * 31) + zzach.hashCode(this.zzoh)) * 31) + zzach.hashCode(this.zzoi)) * 31) + zzach.hashCode(this.zzoj)) * 31) + zzach.hashCode(this.zzok)) * 31) + zzach.hashCode(this.zzol)) * 31) + zzach.hashCode(this.zzom)) * 31) + zzach.hashCode(this.zzon)) * 31) + zzach.hashCode(this.zzoo)) * 31;
        String str = this.zzop;
        int i = 0;
        int hashCode2 = (hashCode + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.zzoq;
        int hashCode3 = (hashCode2 + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.zzor;
        int hashCode4 = (hashCode3 + (str3 == null ? 0 : str3.hashCode())) * 31;
        String str4 = this.version;
        int hashCode5 = hashCode4 + (str4 == null ? 0 : str4.hashCode());
        zzd zzd = this.zzos;
        int hashCode6 = ((((((((((hashCode5 * 31) + (zzd == null ? 0 : zzd.hashCode())) * 31) + Float.floatToIntBits(this.zzot)) * 31) + (this.zzou ? 1231 : 1237)) * 31) + zzach.hashCode(this.zzov)) * 31) + this.zzow) * 31;
        if (this.zzbzd != null && !this.zzbzd.isEmpty()) {
            i = this.zzbzd.hashCode();
        }
        return hashCode6 + i;
    }

    /* access modifiers changed from: protected */
    @Override // com.google.android.gms.internal.measurement.zzacj, com.google.android.gms.internal.measurement.zzacd
    public final int zza() {
        int zza = super.zza();
        String[] strArr = this.zzoi;
        int i = 0;
        if (strArr != null && strArr.length > 0) {
            int i2 = 0;
            int i3 = 0;
            int i4 = 0;
            while (true) {
                String[] strArr2 = this.zzoi;
                if (i2 >= strArr2.length) {
                    break;
                }
                String str = strArr2[i2];
                if (str != null) {
                    i4++;
                    i3 += zzacb.zzfr(str);
                }
                i2++;
            }
            zza = zza + i3 + (i4 * 1);
        }
        zzm[] zzmArr = this.zzoj;
        if (zzmArr != null && zzmArr.length > 0) {
            int i5 = zza;
            int i6 = 0;
            while (true) {
                zzm[] zzmArr2 = this.zzoj;
                if (i6 >= zzmArr2.length) {
                    break;
                }
                zzm zzm = zzmArr2[i6];
                if (zzm != null) {
                    i5 += zzacb.zzb(2, zzm);
                }
                i6++;
            }
            zza = i5;
        }
        zzh[] zzhArr = this.zzok;
        if (zzhArr != null && zzhArr.length > 0) {
            int i7 = zza;
            int i8 = 0;
            while (true) {
                zzh[] zzhArr2 = this.zzok;
                if (i8 >= zzhArr2.length) {
                    break;
                }
                zzh zzh = zzhArr2[i8];
                if (zzh != null) {
                    i7 += zzacb.zzb(3, zzh);
                }
                i8++;
            }
            zza = i7;
        }
        zze[] zzeArr = this.zzol;
        if (zzeArr != null && zzeArr.length > 0) {
            int i9 = zza;
            int i10 = 0;
            while (true) {
                zze[] zzeArr2 = this.zzol;
                if (i10 >= zzeArr2.length) {
                    break;
                }
                zze zze = zzeArr2[i10];
                if (zze != null) {
                    i9 += zzacb.zzb(4, zze);
                }
                i10++;
            }
            zza = i9;
        }
        zze[] zzeArr3 = this.zzom;
        if (zzeArr3 != null && zzeArr3.length > 0) {
            int i11 = zza;
            int i12 = 0;
            while (true) {
                zze[] zzeArr4 = this.zzom;
                if (i12 >= zzeArr4.length) {
                    break;
                }
                zze zze2 = zzeArr4[i12];
                if (zze2 != null) {
                    i11 += zzacb.zzb(5, zze2);
                }
                i12++;
            }
            zza = i11;
        }
        zze[] zzeArr5 = this.zzon;
        if (zzeArr5 != null && zzeArr5.length > 0) {
            int i13 = zza;
            int i14 = 0;
            while (true) {
                zze[] zzeArr6 = this.zzon;
                if (i14 >= zzeArr6.length) {
                    break;
                }
                zze zze3 = zzeArr6[i14];
                if (zze3 != null) {
                    i13 += zzacb.zzb(6, zze3);
                }
                i14++;
            }
            zza = i13;
        }
        zzj[] zzjArr = this.zzoo;
        if (zzjArr != null && zzjArr.length > 0) {
            int i15 = zza;
            int i16 = 0;
            while (true) {
                zzj[] zzjArr2 = this.zzoo;
                if (i16 >= zzjArr2.length) {
                    break;
                }
                zzj zzj = zzjArr2[i16];
                if (zzj != null) {
                    i15 += zzacb.zzb(7, zzj);
                }
                i16++;
            }
            zza = i15;
        }
        String str2 = this.zzop;
        if (str2 != null && !str2.equals("")) {
            zza += zzacb.zzc(9, this.zzop);
        }
        String str3 = this.zzoq;
        if (str3 != null && !str3.equals("")) {
            zza += zzacb.zzc(10, this.zzoq);
        }
        String str4 = this.zzor;
        if (str4 != null && !str4.equals("0")) {
            zza += zzacb.zzc(12, this.zzor);
        }
        String str5 = this.version;
        if (str5 != null && !str5.equals("")) {
            zza += zzacb.zzc(13, this.version);
        }
        zzd zzd = this.zzos;
        if (zzd != null) {
            zza += zzacb.zzb(14, zzd);
        }
        if (Float.floatToIntBits(this.zzot) != Float.floatToIntBits(BitmapDescriptorFactory.HUE_RED)) {
            zza += zzacb.zzaq(15) + 4;
        }
        String[] strArr3 = this.zzov;
        if (strArr3 != null && strArr3.length > 0) {
            int i17 = 0;
            int i18 = 0;
            int i19 = 0;
            while (true) {
                String[] strArr4 = this.zzov;
                if (i17 >= strArr4.length) {
                    break;
                }
                String str6 = strArr4[i17];
                if (str6 != null) {
                    i19++;
                    i18 += zzacb.zzfr(str6);
                }
                i17++;
            }
            zza = zza + i18 + (i19 * 2);
        }
        int i20 = this.zzow;
        if (i20 != 0) {
            zza += zzacb.zzf(17, i20);
        }
        if (this.zzou) {
            zza += zzacb.zzaq(18) + 1;
        }
        String[] strArr5 = this.zzoh;
        if (strArr5 == null || strArr5.length <= 0) {
            return zza;
        }
        int i21 = 0;
        int i22 = 0;
        while (true) {
            String[] strArr6 = this.zzoh;
            if (i >= strArr6.length) {
                return zza + i21 + (i22 * 2);
            }
            String str7 = strArr6[i];
            if (str7 != null) {
                i22++;
                i21 += zzacb.zzfr(str7);
            }
            i++;
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzacj, com.google.android.gms.internal.measurement.zzacd
    public final void zza(zzacb zzacb) {
        String[] strArr = this.zzoi;
        int i = 0;
        if (strArr != null && strArr.length > 0) {
            int i2 = 0;
            while (true) {
                String[] strArr2 = this.zzoi;
                if (i2 >= strArr2.length) {
                    break;
                }
                String str = strArr2[i2];
                if (str != null) {
                    zzacb.zzb(1, str);
                }
                i2++;
            }
        }
        zzm[] zzmArr = this.zzoj;
        if (zzmArr != null && zzmArr.length > 0) {
            int i3 = 0;
            while (true) {
                zzm[] zzmArr2 = this.zzoj;
                if (i3 >= zzmArr2.length) {
                    break;
                }
                zzm zzm = zzmArr2[i3];
                if (zzm != null) {
                    zzacb.zza(2, zzm);
                }
                i3++;
            }
        }
        zzh[] zzhArr = this.zzok;
        if (zzhArr != null && zzhArr.length > 0) {
            int i4 = 0;
            while (true) {
                zzh[] zzhArr2 = this.zzok;
                if (i4 >= zzhArr2.length) {
                    break;
                }
                zzh zzh = zzhArr2[i4];
                if (zzh != null) {
                    zzacb.zza(3, zzh);
                }
                i4++;
            }
        }
        zze[] zzeArr = this.zzol;
        if (zzeArr != null && zzeArr.length > 0) {
            int i5 = 0;
            while (true) {
                zze[] zzeArr2 = this.zzol;
                if (i5 >= zzeArr2.length) {
                    break;
                }
                zze zze = zzeArr2[i5];
                if (zze != null) {
                    zzacb.zza(4, zze);
                }
                i5++;
            }
        }
        zze[] zzeArr3 = this.zzom;
        if (zzeArr3 != null && zzeArr3.length > 0) {
            int i6 = 0;
            while (true) {
                zze[] zzeArr4 = this.zzom;
                if (i6 >= zzeArr4.length) {
                    break;
                }
                zze zze2 = zzeArr4[i6];
                if (zze2 != null) {
                    zzacb.zza(5, zze2);
                }
                i6++;
            }
        }
        zze[] zzeArr5 = this.zzon;
        if (zzeArr5 != null && zzeArr5.length > 0) {
            int i7 = 0;
            while (true) {
                zze[] zzeArr6 = this.zzon;
                if (i7 >= zzeArr6.length) {
                    break;
                }
                zze zze3 = zzeArr6[i7];
                if (zze3 != null) {
                    zzacb.zza(6, zze3);
                }
                i7++;
            }
        }
        zzj[] zzjArr = this.zzoo;
        if (zzjArr != null && zzjArr.length > 0) {
            int i8 = 0;
            while (true) {
                zzj[] zzjArr2 = this.zzoo;
                if (i8 >= zzjArr2.length) {
                    break;
                }
                zzj zzj = zzjArr2[i8];
                if (zzj != null) {
                    zzacb.zza(7, zzj);
                }
                i8++;
            }
        }
        String str2 = this.zzop;
        if (str2 != null && !str2.equals("")) {
            zzacb.zzb(9, this.zzop);
        }
        String str3 = this.zzoq;
        if (str3 != null && !str3.equals("")) {
            zzacb.zzb(10, this.zzoq);
        }
        String str4 = this.zzor;
        if (str4 != null && !str4.equals("0")) {
            zzacb.zzb(12, this.zzor);
        }
        String str5 = this.version;
        if (str5 != null && !str5.equals("")) {
            zzacb.zzb(13, this.version);
        }
        zzd zzd = this.zzos;
        if (zzd != null) {
            zzacb.zza(14, zzd);
        }
        if (Float.floatToIntBits(this.zzot) != Float.floatToIntBits(BitmapDescriptorFactory.HUE_RED)) {
            zzacb.zza(15, this.zzot);
        }
        String[] strArr3 = this.zzov;
        if (strArr3 != null && strArr3.length > 0) {
            int i9 = 0;
            while (true) {
                String[] strArr4 = this.zzov;
                if (i9 >= strArr4.length) {
                    break;
                }
                String str6 = strArr4[i9];
                if (str6 != null) {
                    zzacb.zzb(16, str6);
                }
                i9++;
            }
        }
        int i10 = this.zzow;
        if (i10 != 0) {
            zzacb.zze(17, i10);
        }
        boolean z = this.zzou;
        if (z) {
            zzacb.zza(18, z);
        }
        String[] strArr5 = this.zzoh;
        if (strArr5 != null && strArr5.length > 0) {
            while (true) {
                String[] strArr6 = this.zzoh;
                if (i >= strArr6.length) {
                    break;
                }
                String str7 = strArr6[i];
                if (str7 != null) {
                    zzacb.zzb(19, str7);
                }
                i++;
            }
        }
        super.zza(zzacb);
    }

    @Override // com.google.android.gms.internal.measurement.zzacj
    public final /* synthetic */ zzacj zzb(zzaca zzaca) {
        while (true) {
            int zzvl = zzaca.zzvl();
            switch (zzvl) {
                case 0:
                    return this;
                case 10:
                    int zzb = zzacm.zzb(zzaca, 10);
                    String[] strArr = this.zzoi;
                    int length = strArr == null ? 0 : strArr.length;
                    String[] strArr2 = new String[(zzb + length)];
                    if (length != 0) {
                        System.arraycopy(this.zzoi, 0, strArr2, 0, length);
                    }
                    while (length < strArr2.length - 1) {
                        strArr2[length] = zzaca.readString();
                        zzaca.zzvl();
                        length++;
                    }
                    strArr2[length] = zzaca.readString();
                    this.zzoi = strArr2;
                    break;
                case 18:
                    int zzb2 = zzacm.zzb(zzaca, 18);
                    zzm[] zzmArr = this.zzoj;
                    int length2 = zzmArr == null ? 0 : zzmArr.length;
                    zzm[] zzmArr2 = new zzm[(zzb2 + length2)];
                    if (length2 != 0) {
                        System.arraycopy(this.zzoj, 0, zzmArr2, 0, length2);
                    }
                    while (length2 < zzmArr2.length - 1) {
                        zzmArr2[length2] = new zzm();
                        zzaca.zza(zzmArr2[length2]);
                        zzaca.zzvl();
                        length2++;
                    }
                    zzmArr2[length2] = new zzm();
                    zzaca.zza(zzmArr2[length2]);
                    this.zzoj = zzmArr2;
                    break;
                case 26:
                    int zzb3 = zzacm.zzb(zzaca, 26);
                    zzh[] zzhArr = this.zzok;
                    int length3 = zzhArr == null ? 0 : zzhArr.length;
                    zzh[] zzhArr2 = new zzh[(zzb3 + length3)];
                    if (length3 != 0) {
                        System.arraycopy(this.zzok, 0, zzhArr2, 0, length3);
                    }
                    while (length3 < zzhArr2.length - 1) {
                        zzhArr2[length3] = new zzh();
                        zzaca.zza(zzhArr2[length3]);
                        zzaca.zzvl();
                        length3++;
                    }
                    zzhArr2[length3] = new zzh();
                    zzaca.zza(zzhArr2[length3]);
                    this.zzok = zzhArr2;
                    break;
                case 34:
                    int zzb4 = zzacm.zzb(zzaca, 34);
                    zze[] zzeArr = this.zzol;
                    int length4 = zzeArr == null ? 0 : zzeArr.length;
                    zze[] zzeArr2 = new zze[(zzb4 + length4)];
                    if (length4 != 0) {
                        System.arraycopy(this.zzol, 0, zzeArr2, 0, length4);
                    }
                    while (length4 < zzeArr2.length - 1) {
                        zzeArr2[length4] = new zze();
                        zzaca.zza(zzeArr2[length4]);
                        zzaca.zzvl();
                        length4++;
                    }
                    zzeArr2[length4] = new zze();
                    zzaca.zza(zzeArr2[length4]);
                    this.zzol = zzeArr2;
                    break;
                case 42:
                    int zzb5 = zzacm.zzb(zzaca, 42);
                    zze[] zzeArr3 = this.zzom;
                    int length5 = zzeArr3 == null ? 0 : zzeArr3.length;
                    zze[] zzeArr4 = new zze[(zzb5 + length5)];
                    if (length5 != 0) {
                        System.arraycopy(this.zzom, 0, zzeArr4, 0, length5);
                    }
                    while (length5 < zzeArr4.length - 1) {
                        zzeArr4[length5] = new zze();
                        zzaca.zza(zzeArr4[length5]);
                        zzaca.zzvl();
                        length5++;
                    }
                    zzeArr4[length5] = new zze();
                    zzaca.zza(zzeArr4[length5]);
                    this.zzom = zzeArr4;
                    break;
                case 50:
                    int zzb6 = zzacm.zzb(zzaca, 50);
                    zze[] zzeArr5 = this.zzon;
                    int length6 = zzeArr5 == null ? 0 : zzeArr5.length;
                    zze[] zzeArr6 = new zze[(zzb6 + length6)];
                    if (length6 != 0) {
                        System.arraycopy(this.zzon, 0, zzeArr6, 0, length6);
                    }
                    while (length6 < zzeArr6.length - 1) {
                        zzeArr6[length6] = new zze();
                        zzaca.zza(zzeArr6[length6]);
                        zzaca.zzvl();
                        length6++;
                    }
                    zzeArr6[length6] = new zze();
                    zzaca.zza(zzeArr6[length6]);
                    this.zzon = zzeArr6;
                    break;
                case 58:
                    int zzb7 = zzacm.zzb(zzaca, 58);
                    zzj[] zzjArr = this.zzoo;
                    int length7 = zzjArr == null ? 0 : zzjArr.length;
                    zzj[] zzjArr2 = new zzj[(zzb7 + length7)];
                    if (length7 != 0) {
                        System.arraycopy(this.zzoo, 0, zzjArr2, 0, length7);
                    }
                    while (length7 < zzjArr2.length - 1) {
                        zzjArr2[length7] = new zzj();
                        zzaca.zza(zzjArr2[length7]);
                        zzaca.zzvl();
                        length7++;
                    }
                    zzjArr2[length7] = new zzj();
                    zzaca.zza(zzjArr2[length7]);
                    this.zzoo = zzjArr2;
                    break;
                case 74:
                    this.zzop = zzaca.readString();
                    break;
                case 82:
                    this.zzoq = zzaca.readString();
                    break;
                case 98:
                    this.zzor = zzaca.readString();
                    break;
                case 106:
                    this.version = zzaca.readString();
                    break;
                case 114:
                    if (this.zzos == null) {
                        this.zzos = new zzd();
                    }
                    zzaca.zza(this.zzos);
                    break;
                case EACTags.SECURE_MESSAGING_TEMPLATE /*{ENCODED_INT: 125}*/:
                    this.zzot = Float.intBitsToFloat(zzaca.zzvp());
                    break;
                case 130:
                    int zzb8 = zzacm.zzb(zzaca, 130);
                    String[] strArr3 = this.zzov;
                    int length8 = strArr3 == null ? 0 : strArr3.length;
                    String[] strArr4 = new String[(zzb8 + length8)];
                    if (length8 != 0) {
                        System.arraycopy(this.zzov, 0, strArr4, 0, length8);
                    }
                    while (length8 < strArr4.length - 1) {
                        strArr4[length8] = zzaca.readString();
                        zzaca.zzvl();
                        length8++;
                    }
                    strArr4[length8] = zzaca.readString();
                    this.zzov = strArr4;
                    break;
                case CipherSuite.TLS_DHE_RSA_WITH_CAMELLIA_256_CBC_SHA /*{ENCODED_INT: 136}*/:
                    this.zzow = zzaca.zzvn();
                    break;
                case CipherSuite.TLS_DHE_PSK_WITH_AES_128_CBC_SHA /*{ENCODED_INT: 144}*/:
                    this.zzou = zzaca.zzvm();
                    break;
                case CipherSuite.TLS_DHE_RSA_WITH_SEED_CBC_SHA /*{ENCODED_INT: 154}*/:
                    int zzb9 = zzacm.zzb(zzaca, CipherSuite.TLS_DHE_RSA_WITH_SEED_CBC_SHA);
                    String[] strArr5 = this.zzoh;
                    int length9 = strArr5 == null ? 0 : strArr5.length;
                    String[] strArr6 = new String[(zzb9 + length9)];
                    if (length9 != 0) {
                        System.arraycopy(this.zzoh, 0, strArr6, 0, length9);
                    }
                    while (length9 < strArr6.length - 1) {
                        strArr6[length9] = zzaca.readString();
                        zzaca.zzvl();
                        length9++;
                    }
                    strArr6[length9] = zzaca.readString();
                    this.zzoh = strArr6;
                    break;
                default:
                    if (super.zza(zzaca, zzvl)) {
                        break;
                    } else {
                        return this;
                    }
            }
        }
    }
}
