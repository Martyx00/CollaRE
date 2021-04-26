package org.spongycastle.pqc.math.ntru.util;

import java.io.IOException;
import java.io.InputStream;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import org.spongycastle.pqc.math.ntru.euclid.IntEuclidean;
import org.spongycastle.pqc.math.ntru.polynomial.DenseTernaryPolynomial;
import org.spongycastle.pqc.math.ntru.polynomial.SparseTernaryPolynomial;
import org.spongycastle.pqc.math.ntru.polynomial.TernaryPolynomial;
import org.spongycastle.util.Integers;

public class Util {
    private static volatile boolean IS_64_BITNESS_KNOWN;
    private static volatile boolean IS_64_BIT_JVM;

    public static int invert(int i, int i2) {
        int i3 = i % i2;
        if (i3 < 0) {
            i3 += i2;
        }
        return IntEuclidean.calculate(i3, i2).x;
    }

    public static int pow(int i, int i2, int i3) {
        int i4 = 1;
        for (int i5 = 0; i5 < i2; i5++) {
            i4 = (i4 * i) % i3;
        }
        return i4;
    }

    public static long pow(long j, int i, long j2) {
        long j3 = 1;
        for (int i2 = 0; i2 < i; i2++) {
            j3 = (j3 * j) % j2;
        }
        return j3;
    }

    public static TernaryPolynomial generateRandomTernary(int i, int i2, int i3, boolean z, SecureRandom secureRandom) {
        if (z) {
            return SparseTernaryPolynomial.generateRandom(i, i2, i3, secureRandom);
        }
        return DenseTernaryPolynomial.generateRandom(i, i2, i3, secureRandom);
    }

    public static int[] generateRandomTernary(int i, int i2, int i3, SecureRandom secureRandom) {
        Integer valueOf = Integers.valueOf(1);
        Integer valueOf2 = Integers.valueOf(-1);
        Integer valueOf3 = Integers.valueOf(0);
        ArrayList arrayList = new ArrayList();
        for (int i4 = 0; i4 < i2; i4++) {
            arrayList.add(valueOf);
        }
        for (int i5 = 0; i5 < i3; i5++) {
            arrayList.add(valueOf2);
        }
        while (arrayList.size() < i) {
            arrayList.add(valueOf3);
        }
        Collections.shuffle(arrayList, secureRandom);
        int[] iArr = new int[i];
        for (int i6 = 0; i6 < i; i6++) {
            iArr[i6] = ((Integer) arrayList.get(i6)).intValue();
        }
        return iArr;
    }

    public static boolean is64BitJVM() {
        if (!IS_64_BITNESS_KNOWN) {
            String property = System.getProperty("os.arch");
            IS_64_BIT_JVM = "amd64".equals(property) || "x86_64".equals(property) || "ppc64".equals(property) || "64".equals(System.getProperty("sun.arch.data.model"));
            IS_64_BITNESS_KNOWN = true;
        }
        return IS_64_BIT_JVM;
    }

    public static byte[] readFullLength(InputStream inputStream, int i) {
        byte[] bArr = new byte[i];
        if (inputStream.read(bArr) == bArr.length) {
            return bArr;
        }
        throw new IOException("Not enough bytes to read.");
    }
}
