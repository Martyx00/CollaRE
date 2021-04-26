package com.google.android.gms.internal.measurement;

public final class zzacm {
    public static final int[] zzbvp = new int[0];
    private static final int zzbzp = 11;
    private static final int zzbzq = 12;
    private static final int zzbzr = 16;
    private static final int zzbzs = 26;
    public static final long[] zzbzt = new long[0];
    private static final float[] zzbzu = new float[0];
    private static final double[] zzbzv = new double[0];
    private static final boolean[] zzbzw = new boolean[0];
    public static final String[] zzbzx = new String[0];
    private static final byte[][] zzbzy = new byte[0][];
    public static final byte[] zzbzz = new byte[0];

    public static final int zzb(zzaca zzaca, int i) {
        int position = zzaca.getPosition();
        zzaca.zzak(i);
        int i2 = 1;
        while (zzaca.zzvl() == i) {
            zzaca.zzak(i);
            i2++;
        }
        zzaca.zzd(position, i);
        return i2;
    }
}
