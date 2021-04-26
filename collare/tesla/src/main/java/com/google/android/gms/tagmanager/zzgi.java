package com.google.android.gms.tagmanager;

/* access modifiers changed from: package-private */
public final class zzgi extends Number implements Comparable<zzgi> {
    private double zzbes;
    private long zzbet;
    private boolean zzbeu = false;

    private zzgi(double d2) {
        this.zzbes = d2;
    }

    private zzgi(long j) {
        this.zzbet = j;
    }

    public static zzgi zza(Double d2) {
        return new zzgi(d2.doubleValue());
    }

    public static zzgi zzao(long j) {
        return new zzgi(j);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:5:0x0013, code lost:
        return new com.google.android.gms.tagmanager.zzgi(java.lang.Double.parseDouble(r3));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0023, code lost:
        throw new java.lang.NumberFormatException(java.lang.String.valueOf(r3).concat(" is not a valid TypedNumber"));
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x000a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.google.android.gms.tagmanager.zzgi zzdr(java.lang.String r3) {
        /*
            com.google.android.gms.tagmanager.zzgi r0 = new com.google.android.gms.tagmanager.zzgi     // Catch:{ NumberFormatException -> 0x000a }
            long r1 = java.lang.Long.parseLong(r3)     // Catch:{ NumberFormatException -> 0x000a }
            r0.<init>(r1)     // Catch:{ NumberFormatException -> 0x000a }
            return r0
        L_0x000a:
            com.google.android.gms.tagmanager.zzgi r0 = new com.google.android.gms.tagmanager.zzgi     // Catch:{ NumberFormatException -> 0x0014 }
            double r1 = java.lang.Double.parseDouble(r3)     // Catch:{ NumberFormatException -> 0x0014 }
            r0.<init>(r1)     // Catch:{ NumberFormatException -> 0x0014 }
            return r0
        L_0x0014:
            java.lang.NumberFormatException r0 = new java.lang.NumberFormatException
            java.lang.String r3 = java.lang.String.valueOf(r3)
            java.lang.String r1 = " is not a valid TypedNumber"
            java.lang.String r3 = r3.concat(r1)
            r0.<init>(r3)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.tagmanager.zzgi.zzdr(java.lang.String):com.google.android.gms.tagmanager.zzgi");
    }

    public final byte byteValue() {
        return (byte) ((int) longValue());
    }

    public final double doubleValue() {
        return this.zzbeu ? (double) this.zzbet : this.zzbes;
    }

    public final boolean equals(Object obj) {
        return (obj instanceof zzgi) && compareTo((zzgi) obj) == 0;
    }

    public final float floatValue() {
        return (float) doubleValue();
    }

    public final int hashCode() {
        return new Long(longValue()).hashCode();
    }

    public final int intValue() {
        return (int) longValue();
    }

    public final long longValue() {
        return this.zzbeu ? this.zzbet : (long) this.zzbes;
    }

    public final short shortValue() {
        return (short) ((int) longValue());
    }

    public final String toString() {
        return this.zzbeu ? Long.toString(this.zzbet) : Double.toString(this.zzbes);
    }

    /* renamed from: zza */
    public final int compareTo(zzgi zzgi) {
        return (!this.zzbeu || !zzgi.zzbeu) ? Double.compare(doubleValue(), zzgi.doubleValue()) : new Long(this.zzbet).compareTo(Long.valueOf(zzgi.zzbet));
    }

    public final boolean zzpg() {
        return !this.zzbeu;
    }

    public final boolean zzph() {
        return this.zzbeu;
    }
}
