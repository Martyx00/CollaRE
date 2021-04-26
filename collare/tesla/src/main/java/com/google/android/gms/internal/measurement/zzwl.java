package com.google.android.gms.internal.measurement;

import java.util.ArrayList;
import java.util.List;

public final class zzwl {
    private final List<zzwg> zzbmx;
    private final List<zzwg> zzbmy;
    private final List<zzwg> zzbmz;
    private final List<zzwg> zzbna;
    private final List<zzwg> zzbod;
    private final List<zzwg> zzboe;
    private final List<String> zzbof;
    private final List<String> zzbog;
    private final List<String> zzboh;
    private final List<String> zzboi;

    private zzwl() {
        this.zzbmx = new ArrayList();
        this.zzbmy = new ArrayList();
        this.zzbmz = new ArrayList();
        this.zzbna = new ArrayList();
        this.zzbod = new ArrayList();
        this.zzboe = new ArrayList();
        this.zzbof = new ArrayList();
        this.zzbog = new ArrayList();
        this.zzboh = new ArrayList();
        this.zzboi = new ArrayList();
    }

    public final zzwl zzd(zzwg zzwg) {
        this.zzbmx.add(zzwg);
        return this;
    }

    public final zzwl zze(zzwg zzwg) {
        this.zzbmy.add(zzwg);
        return this;
    }

    public final zzwl zzew(String str) {
        this.zzboh.add(str);
        return this;
    }

    public final zzwl zzex(String str) {
        this.zzboi.add(str);
        return this;
    }

    public final zzwl zzey(String str) {
        this.zzbof.add(str);
        return this;
    }

    public final zzwl zzez(String str) {
        this.zzbog.add(str);
        return this;
    }

    public final zzwl zzf(zzwg zzwg) {
        this.zzbmz.add(zzwg);
        return this;
    }

    public final zzwl zzg(zzwg zzwg) {
        this.zzbna.add(zzwg);
        return this;
    }

    public final zzwl zzh(zzwg zzwg) {
        this.zzbod.add(zzwg);
        return this;
    }

    public final zzwl zzi(zzwg zzwg) {
        this.zzboe.add(zzwg);
        return this;
    }

    public final zzwk zzse() {
        return new zzwk(this.zzbmx, this.zzbmy, this.zzbmz, this.zzbna, this.zzbod, this.zzboe, this.zzbof, this.zzbog, this.zzboh, this.zzboi);
    }
}
