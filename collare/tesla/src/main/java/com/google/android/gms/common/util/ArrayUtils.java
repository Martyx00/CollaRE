package com.google.android.gms.common.util;

import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Objects;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

@VisibleForTesting
@KeepForSdk
public final class ArrayUtils {
    @KeepForSdk
    public static <T> boolean contains(T[] tArr, T t) {
        int length = tArr != null ? tArr.length : 0;
        int i = 0;
        while (true) {
            if (i >= length) {
                i = -1;
                break;
            } else if (Objects.equal(tArr[i], t)) {
                break;
            } else {
                i++;
            }
        }
        if (i >= 0) {
            return true;
        }
        return false;
    }

    @KeepForSdk
    public static boolean contains(int[] iArr, int i) {
        if (iArr == null) {
            return false;
        }
        for (int i2 : iArr) {
            if (i2 == i) {
                return true;
            }
        }
        return false;
    }

    @KeepForSdk
    public static Integer[] toWrapperArray(int[] iArr) {
        if (iArr == null) {
            return null;
        }
        int length = iArr.length;
        Integer[] numArr = new Integer[length];
        for (int i = 0; i < length; i++) {
            numArr[i] = Integer.valueOf(iArr[i]);
        }
        return numArr;
    }

    private ArrayUtils() {
    }

    @KeepForSdk
    public static <T> void writeArray(StringBuilder sb, T[] tArr) {
        int length = tArr.length;
        for (int i = 0; i < length; i++) {
            if (i != 0) {
                sb.append(",");
            }
            sb.append(tArr[i].toString());
        }
    }

    @KeepForSdk
    public static void writeArray(StringBuilder sb, int[] iArr) {
        int length = iArr.length;
        for (int i = 0; i < length; i++) {
            if (i != 0) {
                sb.append(",");
            }
            sb.append(Integer.toString(iArr[i]));
        }
    }

    @KeepForSdk
    public static void writeArray(StringBuilder sb, long[] jArr) {
        int length = jArr.length;
        for (int i = 0; i < length; i++) {
            if (i != 0) {
                sb.append(",");
            }
            sb.append(Long.toString(jArr[i]));
        }
    }

    @KeepForSdk
    public static void writeArray(StringBuilder sb, float[] fArr) {
        int length = fArr.length;
        for (int i = 0; i < length; i++) {
            if (i != 0) {
                sb.append(",");
            }
            sb.append(Float.toString(fArr[i]));
        }
    }

    @KeepForSdk
    public static void writeArray(StringBuilder sb, double[] dArr) {
        int length = dArr.length;
        for (int i = 0; i < length; i++) {
            if (i != 0) {
                sb.append(",");
            }
            sb.append(Double.toString(dArr[i]));
        }
    }

    @KeepForSdk
    public static void writeArray(StringBuilder sb, boolean[] zArr) {
        int length = zArr.length;
        for (int i = 0; i < length; i++) {
            if (i != 0) {
                sb.append(",");
            }
            sb.append(Boolean.toString(zArr[i]));
        }
    }

    @KeepForSdk
    public static void writeStringArray(StringBuilder sb, String[] strArr) {
        int length = strArr.length;
        for (int i = 0; i < length; i++) {
            if (i != 0) {
                sb.append(",");
            }
            sb.append("\"");
            sb.append(strArr[i]);
            sb.append("\"");
        }
    }

    @KeepForSdk
    public static <T> T[] concat(T[]... tArr) {
        if (tArr.length == 0) {
            return (T[]) ((Object[]) Array.newInstance(tArr.getClass(), 0));
        }
        int i = 0;
        for (T[] tArr2 : tArr) {
            i += tArr2.length;
        }
        T[] tArr3 = (T[]) Arrays.copyOf(tArr[0], i);
        int length = tArr[0].length;
        for (int i2 = 1; i2 < tArr.length; i2++) {
            T[] tArr4 = tArr[i2];
            System.arraycopy(tArr4, 0, tArr3, length, tArr4.length);
            length += tArr4.length;
        }
        return tArr3;
    }

    @KeepForSdk
    public static byte[] concatByteArrays(byte[]... bArr) {
        if (bArr.length == 0) {
            return new byte[0];
        }
        int i = 0;
        for (byte[] bArr2 : bArr) {
            i += bArr2.length;
        }
        byte[] copyOf = Arrays.copyOf(bArr[0], i);
        int length = bArr[0].length;
        for (int i2 = 1; i2 < bArr.length; i2++) {
            byte[] bArr3 = bArr[i2];
            System.arraycopy(bArr3, 0, copyOf, length, bArr3.length);
            length += bArr3.length;
        }
        return copyOf;
    }

    @KeepForSdk
    public static <T> T[] appendToArray(T[] tArr, T t) {
        if (tArr == null && t == null) {
            throw new IllegalArgumentException("Cannot generate array of generic type w/o class info");
        }
        T[] tArr2 = tArr == null ? (T[]) ((Object[]) Array.newInstance(t.getClass(), 1)) : (T[]) Arrays.copyOf(tArr, tArr.length + 1);
        tArr2[tArr2.length - 1] = t;
        return tArr2;
    }

    @KeepForSdk
    public static <T> T[] removeAll(T[] tArr, T... tArr2) {
        int i;
        if (tArr == null) {
            return null;
        }
        if (tArr2 == null || tArr2.length == 0) {
            return (T[]) Arrays.copyOf(tArr, tArr.length);
        }
        T[] tArr3 = (T[]) ((Object[]) Array.newInstance(tArr2.getClass().getComponentType(), tArr.length));
        if (tArr2.length == 1) {
            i = 0;
            for (T t : tArr) {
                if (!Objects.equal(tArr2[0], t)) {
                    tArr3[i] = t;
                    i++;
                }
            }
        } else {
            i = 0;
            for (T t2 : tArr) {
                if (!contains(tArr2, t2)) {
                    tArr3[i] = t2;
                    i++;
                }
            }
        }
        if (tArr3 == null) {
            return null;
        }
        return i != tArr3.length ? (T[]) Arrays.copyOf(tArr3, i) : tArr3;
    }

    @KeepForSdk
    public static <T> ArrayList<T> newArrayList() {
        return new ArrayList<>();
    }

    @KeepForSdk
    public static <T> ArrayList<T> toArrayList(T[] tArr) {
        int length = tArr.length;
        ArrayList<T> arrayList = new ArrayList<>(length);
        for (T t : tArr) {
            arrayList.add(t);
        }
        return arrayList;
    }

    @KeepForSdk
    public static int[] toPrimitiveArray(Collection<Integer> collection) {
        int i = 0;
        if (collection == null || collection.size() == 0) {
            return new int[0];
        }
        int[] iArr = new int[collection.size()];
        for (Integer num : collection) {
            iArr[i] = num.intValue();
            i++;
        }
        return iArr;
    }
}
