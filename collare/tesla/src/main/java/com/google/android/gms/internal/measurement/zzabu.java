package com.google.android.gms.internal.measurement;

/* JADX INFO: Failed to restore enum class, 'enum' modifier removed */
public class zzabu extends Enum<zzabu> {
    public static final zzabu zzbxq = new zzabu("DOUBLE", 0, zzabz.DOUBLE, 1);
    public static final zzabu zzbxr = new zzabu("FLOAT", 1, zzabz.FLOAT, 5);
    public static final zzabu zzbxs = new zzabu("INT64", 2, zzabz.LONG, 0);
    public static final zzabu zzbxt = new zzabu("UINT64", 3, zzabz.LONG, 0);
    public static final zzabu zzbxu = new zzabu("INT32", 4, zzabz.INT, 0);
    public static final zzabu zzbxv = new zzabu("FIXED64", 5, zzabz.LONG, 1);
    public static final zzabu zzbxw = new zzabu("FIXED32", 6, zzabz.INT, 5);
    public static final zzabu zzbxx = new zzabu("BOOL", 7, zzabz.BOOLEAN, 0);
    public static final zzabu zzbxy = new zzabv("STRING", 8, zzabz.STRING, 2);
    public static final zzabu zzbxz = new zzabw("GROUP", 9, zzabz.MESSAGE, 3);
    public static final zzabu zzbya = new zzabx("MESSAGE", 10, zzabz.MESSAGE, 2);
    public static final zzabu zzbyb = new zzaby("BYTES", 11, zzabz.BYTE_STRING, 2);
    public static final zzabu zzbyc = new zzabu("UINT32", 12, zzabz.INT, 0);
    public static final zzabu zzbyd = new zzabu("ENUM", 13, zzabz.ENUM, 0);
    public static final zzabu zzbye = new zzabu("SFIXED32", 14, zzabz.INT, 5);
    public static final zzabu zzbyf = new zzabu("SFIXED64", 15, zzabz.LONG, 1);
    public static final zzabu zzbyg = new zzabu("SINT32", 16, zzabz.INT, 0);
    public static final zzabu zzbyh = new zzabu("SINT64", 17, zzabz.LONG, 0);
    private static final /* synthetic */ zzabu[] zzbyk = {zzbxq, zzbxr, zzbxs, zzbxt, zzbxu, zzbxv, zzbxw, zzbxx, zzbxy, zzbxz, zzbya, zzbyb, zzbyc, zzbyd, zzbye, zzbyf, zzbyg, zzbyh};
    private final zzabz zzbyi;
    private final int zzbyj;

    private zzabu(String str, int i, zzabz zzabz, int i2) {
        this.zzbyi = zzabz;
        this.zzbyj = i2;
    }

    /* synthetic */ zzabu(String str, int i, zzabz zzabz, int i2, zzabt zzabt) {
        this(str, i, zzabz, i2);
    }

    public static zzabu[] values() {
        return (zzabu[]) zzbyk.clone();
    }

    public final zzabz zzvk() {
        return this.zzbyi;
    }
}
