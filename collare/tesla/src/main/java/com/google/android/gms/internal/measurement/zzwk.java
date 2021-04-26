package com.google.android.gms.internal.measurement;

import java.util.Collections;
import java.util.List;

public final class zzwk {
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

    private zzwk(List<zzwg> list, List<zzwg> list2, List<zzwg> list3, List<zzwg> list4, List<zzwg> list5, List<zzwg> list6, List<String> list7, List<String> list8, List<String> list9, List<String> list10) {
        this.zzbmx = Collections.unmodifiableList(list);
        this.zzbmy = Collections.unmodifiableList(list2);
        this.zzbmz = Collections.unmodifiableList(list3);
        this.zzbna = Collections.unmodifiableList(list4);
        this.zzbod = Collections.unmodifiableList(list5);
        this.zzboe = Collections.unmodifiableList(list6);
        this.zzbof = Collections.unmodifiableList(list7);
        this.zzbog = Collections.unmodifiableList(list8);
        this.zzboh = Collections.unmodifiableList(list9);
        this.zzboi = Collections.unmodifiableList(list10);
    }

    public final String toString() {
        String valueOf = String.valueOf(this.zzbmx);
        String valueOf2 = String.valueOf(this.zzbmy);
        String valueOf3 = String.valueOf(this.zzbmz);
        String valueOf4 = String.valueOf(this.zzbna);
        String valueOf5 = String.valueOf(this.zzbod);
        String valueOf6 = String.valueOf(this.zzboe);
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 102 + String.valueOf(valueOf2).length() + String.valueOf(valueOf3).length() + String.valueOf(valueOf4).length() + String.valueOf(valueOf5).length() + String.valueOf(valueOf6).length());
        sb.append("Positive predicates: ");
        sb.append(valueOf);
        sb.append("  Negative predicates: ");
        sb.append(valueOf2);
        sb.append("  Add tags: ");
        sb.append(valueOf3);
        sb.append("  Remove tags: ");
        sb.append(valueOf4);
        sb.append("  Add macros: ");
        sb.append(valueOf5);
        sb.append("  Remove macros: ");
        sb.append(valueOf6);
        return sb.toString();
    }

    public final List<zzwg> zzri() {
        return this.zzbmx;
    }

    public final List<zzwg> zzrj() {
        return this.zzbmy;
    }

    public final List<zzwg> zzrk() {
        return this.zzbmz;
    }

    public final List<zzwg> zzrl() {
        return this.zzbna;
    }

    public final List<zzwg> zzsc() {
        return this.zzbod;
    }

    public final List<zzwg> zzsd() {
        return this.zzboe;
    }
}
