package com.google.android.gms.common.internal.safeparcel;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.SparseArray;
import android.util.SparseBooleanArray;
import android.util.SparseIntArray;
import android.util.SparseLongArray;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

public class SafeParcelWriter {
    private SafeParcelWriter() {
    }

    private static void zzb(Parcel parcel, int i, int i2) {
        if (i2 >= 65535) {
            parcel.writeInt(i | -65536);
            parcel.writeInt(i2);
            return;
        }
        parcel.writeInt(i | (i2 << 16));
    }

    private static int zza(Parcel parcel, int i) {
        parcel.writeInt(i | -65536);
        parcel.writeInt(0);
        return parcel.dataPosition();
    }

    private static void zzb(Parcel parcel, int i) {
        int dataPosition = parcel.dataPosition();
        parcel.setDataPosition(i - 4);
        parcel.writeInt(dataPosition - i);
        parcel.setDataPosition(dataPosition);
    }

    public static int beginObjectHeader(Parcel parcel) {
        return zza(parcel, 20293);
    }

    public static void finishObjectHeader(Parcel parcel, int i) {
        zzb(parcel, i);
    }

    public static void writeBoolean(Parcel parcel, int i, boolean z) {
        zzb(parcel, i, 4);
        parcel.writeInt(z ? 1 : 0);
    }

    public static void writeBooleanObject(Parcel parcel, int i, Boolean bool, boolean z) {
        if (bool != null) {
            zzb(parcel, i, 4);
            parcel.writeInt(bool.booleanValue() ? 1 : 0);
        } else if (z) {
            zzb(parcel, i, 0);
        }
    }

    public static void writeByte(Parcel parcel, int i, byte b2) {
        zzb(parcel, i, 4);
        parcel.writeInt(b2);
    }

    public static void writeChar(Parcel parcel, int i, char c2) {
        zzb(parcel, i, 4);
        parcel.writeInt(c2);
    }

    public static void writeShort(Parcel parcel, int i, short s) {
        zzb(parcel, i, 4);
        parcel.writeInt(s);
    }

    public static void writeInt(Parcel parcel, int i, int i2) {
        zzb(parcel, i, 4);
        parcel.writeInt(i2);
    }

    public static void writeIntegerObject(Parcel parcel, int i, Integer num, boolean z) {
        if (num != null) {
            zzb(parcel, i, 4);
            parcel.writeInt(num.intValue());
        } else if (z) {
            zzb(parcel, i, 0);
        }
    }

    public static void writeLong(Parcel parcel, int i, long j) {
        zzb(parcel, i, 8);
        parcel.writeLong(j);
    }

    public static void writeLongObject(Parcel parcel, int i, Long l, boolean z) {
        if (l != null) {
            zzb(parcel, i, 8);
            parcel.writeLong(l.longValue());
        } else if (z) {
            zzb(parcel, i, 0);
        }
    }

    public static void writeBigInteger(Parcel parcel, int i, BigInteger bigInteger, boolean z) {
        if (bigInteger != null) {
            int zza = zza(parcel, i);
            parcel.writeByteArray(bigInteger.toByteArray());
            zzb(parcel, zza);
        } else if (z) {
            zzb(parcel, i, 0);
        }
    }

    public static void writeFloat(Parcel parcel, int i, float f) {
        zzb(parcel, i, 4);
        parcel.writeFloat(f);
    }

    public static void writeFloatObject(Parcel parcel, int i, Float f, boolean z) {
        if (f != null) {
            zzb(parcel, i, 4);
            parcel.writeFloat(f.floatValue());
        } else if (z) {
            zzb(parcel, i, 0);
        }
    }

    public static void writeDouble(Parcel parcel, int i, double d2) {
        zzb(parcel, i, 8);
        parcel.writeDouble(d2);
    }

    public static void writeDoubleObject(Parcel parcel, int i, Double d2, boolean z) {
        if (d2 != null) {
            zzb(parcel, i, 8);
            parcel.writeDouble(d2.doubleValue());
        } else if (z) {
            zzb(parcel, i, 0);
        }
    }

    public static void writeBigDecimal(Parcel parcel, int i, BigDecimal bigDecimal, boolean z) {
        if (bigDecimal != null) {
            int zza = zza(parcel, i);
            parcel.writeByteArray(bigDecimal.unscaledValue().toByteArray());
            parcel.writeInt(bigDecimal.scale());
            zzb(parcel, zza);
        } else if (z) {
            zzb(parcel, i, 0);
        }
    }

    public static void writeString(Parcel parcel, int i, String str, boolean z) {
        if (str != null) {
            int zza = zza(parcel, i);
            parcel.writeString(str);
            zzb(parcel, zza);
        } else if (z) {
            zzb(parcel, i, 0);
        }
    }

    public static void writeIBinder(Parcel parcel, int i, IBinder iBinder, boolean z) {
        if (iBinder != null) {
            int zza = zza(parcel, i);
            parcel.writeStrongBinder(iBinder);
            zzb(parcel, zza);
        } else if (z) {
            zzb(parcel, i, 0);
        }
    }

    public static void writeParcelable(Parcel parcel, int i, Parcelable parcelable, int i2, boolean z) {
        if (parcelable != null) {
            int zza = zza(parcel, i);
            parcelable.writeToParcel(parcel, i2);
            zzb(parcel, zza);
        } else if (z) {
            zzb(parcel, i, 0);
        }
    }

    public static void writeBundle(Parcel parcel, int i, Bundle bundle, boolean z) {
        if (bundle != null) {
            int zza = zza(parcel, i);
            parcel.writeBundle(bundle);
            zzb(parcel, zza);
        } else if (z) {
            zzb(parcel, i, 0);
        }
    }

    public static void writeByteArray(Parcel parcel, int i, byte[] bArr, boolean z) {
        if (bArr != null) {
            int zza = zza(parcel, i);
            parcel.writeByteArray(bArr);
            zzb(parcel, zza);
        } else if (z) {
            zzb(parcel, i, 0);
        }
    }

    public static void writeByteArrayArray(Parcel parcel, int i, byte[][] bArr, boolean z) {
        if (bArr != null) {
            int zza = zza(parcel, i);
            int length = bArr.length;
            parcel.writeInt(length);
            for (byte[] bArr2 : bArr) {
                parcel.writeByteArray(bArr2);
            }
            zzb(parcel, zza);
        } else if (z) {
            zzb(parcel, i, 0);
        }
    }

    public static void writeBooleanArray(Parcel parcel, int i, boolean[] zArr, boolean z) {
        if (zArr != null) {
            int zza = zza(parcel, i);
            parcel.writeBooleanArray(zArr);
            zzb(parcel, zza);
        } else if (z) {
            zzb(parcel, i, 0);
        }
    }

    public static void writeCharArray(Parcel parcel, int i, char[] cArr, boolean z) {
        if (cArr != null) {
            int zza = zza(parcel, i);
            parcel.writeCharArray(cArr);
            zzb(parcel, zza);
        } else if (z) {
            zzb(parcel, i, 0);
        }
    }

    public static void writeIntArray(Parcel parcel, int i, int[] iArr, boolean z) {
        if (iArr != null) {
            int zza = zza(parcel, i);
            parcel.writeIntArray(iArr);
            zzb(parcel, zza);
        } else if (z) {
            zzb(parcel, i, 0);
        }
    }

    public static void writeLongArray(Parcel parcel, int i, long[] jArr, boolean z) {
        if (jArr != null) {
            int zza = zza(parcel, i);
            parcel.writeLongArray(jArr);
            zzb(parcel, zza);
        } else if (z) {
            zzb(parcel, i, 0);
        }
    }

    public static void writeBigIntegerArray(Parcel parcel, int i, BigInteger[] bigIntegerArr, boolean z) {
        if (bigIntegerArr != null) {
            int zza = zza(parcel, i);
            int length = bigIntegerArr.length;
            parcel.writeInt(length);
            for (BigInteger bigInteger : bigIntegerArr) {
                parcel.writeByteArray(bigInteger.toByteArray());
            }
            zzb(parcel, zza);
        } else if (z) {
            zzb(parcel, i, 0);
        }
    }

    public static void writeFloatArray(Parcel parcel, int i, float[] fArr, boolean z) {
        if (fArr != null) {
            int zza = zza(parcel, i);
            parcel.writeFloatArray(fArr);
            zzb(parcel, zza);
        } else if (z) {
            zzb(parcel, i, 0);
        }
    }

    public static void writeDoubleArray(Parcel parcel, int i, double[] dArr, boolean z) {
        if (dArr != null) {
            int zza = zza(parcel, i);
            parcel.writeDoubleArray(dArr);
            zzb(parcel, zza);
        } else if (z) {
            zzb(parcel, i, 0);
        }
    }

    public static void writeBigDecimalArray(Parcel parcel, int i, BigDecimal[] bigDecimalArr, boolean z) {
        if (bigDecimalArr != null) {
            int zza = zza(parcel, i);
            int length = bigDecimalArr.length;
            parcel.writeInt(length);
            for (int i2 = 0; i2 < length; i2++) {
                parcel.writeByteArray(bigDecimalArr[i2].unscaledValue().toByteArray());
                parcel.writeInt(bigDecimalArr[i2].scale());
            }
            zzb(parcel, zza);
        } else if (z) {
            zzb(parcel, i, 0);
        }
    }

    public static void writeStringArray(Parcel parcel, int i, String[] strArr, boolean z) {
        if (strArr != null) {
            int zza = zza(parcel, i);
            parcel.writeStringArray(strArr);
            zzb(parcel, zza);
        } else if (z) {
            zzb(parcel, i, 0);
        }
    }

    public static void writeIBinderArray(Parcel parcel, int i, IBinder[] iBinderArr, boolean z) {
        if (iBinderArr != null) {
            int zza = zza(parcel, i);
            parcel.writeBinderArray(iBinderArr);
            zzb(parcel, zza);
        } else if (z) {
            zzb(parcel, i, 0);
        }
    }

    public static void writeBooleanList(Parcel parcel, int i, List<Boolean> list, boolean z) {
        if (list != null) {
            int zza = zza(parcel, i);
            int size = list.size();
            parcel.writeInt(size);
            for (int i2 = 0; i2 < size; i2++) {
                parcel.writeInt(list.get(i2).booleanValue() ? 1 : 0);
            }
            zzb(parcel, zza);
        } else if (z) {
            zzb(parcel, i, 0);
        }
    }

    public static void writeIntegerList(Parcel parcel, int i, List<Integer> list, boolean z) {
        if (list != null) {
            int zza = zza(parcel, i);
            int size = list.size();
            parcel.writeInt(size);
            for (int i2 = 0; i2 < size; i2++) {
                parcel.writeInt(list.get(i2).intValue());
            }
            zzb(parcel, zza);
        } else if (z) {
            zzb(parcel, i, 0);
        }
    }

    public static void writeLongList(Parcel parcel, int i, List<Long> list, boolean z) {
        if (list != null) {
            int zza = zza(parcel, i);
            int size = list.size();
            parcel.writeInt(size);
            for (int i2 = 0; i2 < size; i2++) {
                parcel.writeLong(list.get(i2).longValue());
            }
            zzb(parcel, zza);
        } else if (z) {
            zzb(parcel, i, 0);
        }
    }

    public static void writeFloatList(Parcel parcel, int i, List<Float> list, boolean z) {
        if (list != null) {
            int zza = zza(parcel, i);
            int size = list.size();
            parcel.writeInt(size);
            for (int i2 = 0; i2 < size; i2++) {
                parcel.writeFloat(list.get(i2).floatValue());
            }
            zzb(parcel, zza);
        } else if (z) {
            zzb(parcel, i, 0);
        }
    }

    public static void writeDoubleList(Parcel parcel, int i, List<Double> list, boolean z) {
        if (list != null) {
            int zza = zza(parcel, i);
            int size = list.size();
            parcel.writeInt(size);
            for (int i2 = 0; i2 < size; i2++) {
                parcel.writeDouble(list.get(i2).doubleValue());
            }
            zzb(parcel, zza);
        } else if (z) {
            zzb(parcel, i, 0);
        }
    }

    public static void writeStringList(Parcel parcel, int i, List<String> list, boolean z) {
        if (list != null) {
            int zza = zza(parcel, i);
            parcel.writeStringList(list);
            zzb(parcel, zza);
        } else if (z) {
            zzb(parcel, i, 0);
        }
    }

    public static void writeIBinderList(Parcel parcel, int i, List<IBinder> list, boolean z) {
        if (list != null) {
            int zza = zza(parcel, i);
            parcel.writeBinderList(list);
            zzb(parcel, zza);
        } else if (z) {
            zzb(parcel, i, 0);
        }
    }

    public static <T extends Parcelable> void writeTypedArray(Parcel parcel, int i, T[] tArr, int i2, boolean z) {
        if (tArr != null) {
            int zza = zza(parcel, i);
            int length = tArr.length;
            parcel.writeInt(length);
            for (T t : tArr) {
                if (t == null) {
                    parcel.writeInt(0);
                } else {
                    zza(parcel, t, i2);
                }
            }
            zzb(parcel, zza);
        } else if (z) {
            zzb(parcel, i, 0);
        }
    }

    public static <T extends Parcelable> void writeTypedList(Parcel parcel, int i, List<T> list, boolean z) {
        if (list != null) {
            int zza = zza(parcel, i);
            int size = list.size();
            parcel.writeInt(size);
            for (int i2 = 0; i2 < size; i2++) {
                T t = list.get(i2);
                if (t == null) {
                    parcel.writeInt(0);
                } else {
                    zza(parcel, t, 0);
                }
            }
            zzb(parcel, zza);
        } else if (z) {
            zzb(parcel, i, 0);
        }
    }

    private static <T extends Parcelable> void zza(Parcel parcel, T t, int i) {
        int dataPosition = parcel.dataPosition();
        parcel.writeInt(1);
        int dataPosition2 = parcel.dataPosition();
        t.writeToParcel(parcel, i);
        int dataPosition3 = parcel.dataPosition();
        parcel.setDataPosition(dataPosition);
        parcel.writeInt(dataPosition3 - dataPosition2);
        parcel.setDataPosition(dataPosition3);
    }

    public static void writeParcel(Parcel parcel, int i, Parcel parcel2, boolean z) {
        if (parcel2 != null) {
            int zza = zza(parcel, i);
            parcel.appendFrom(parcel2, 0, parcel2.dataSize());
            zzb(parcel, zza);
        } else if (z) {
            zzb(parcel, i, 0);
        }
    }

    public static void writeParcelArray(Parcel parcel, int i, Parcel[] parcelArr, boolean z) {
        if (parcelArr != null) {
            int zza = zza(parcel, i);
            int length = parcelArr.length;
            parcel.writeInt(length);
            for (Parcel parcel2 : parcelArr) {
                if (parcel2 != null) {
                    parcel.writeInt(parcel2.dataSize());
                    parcel.appendFrom(parcel2, 0, parcel2.dataSize());
                } else {
                    parcel.writeInt(0);
                }
            }
            zzb(parcel, zza);
        } else if (z) {
            zzb(parcel, i, 0);
        }
    }

    public static void writeParcelList(Parcel parcel, int i, List<Parcel> list, boolean z) {
        if (list != null) {
            int zza = zza(parcel, i);
            int size = list.size();
            parcel.writeInt(size);
            for (int i2 = 0; i2 < size; i2++) {
                Parcel parcel2 = list.get(i2);
                if (parcel2 != null) {
                    parcel.writeInt(parcel2.dataSize());
                    parcel.appendFrom(parcel2, 0, parcel2.dataSize());
                } else {
                    parcel.writeInt(0);
                }
            }
            zzb(parcel, zza);
        } else if (z) {
            zzb(parcel, i, 0);
        }
    }

    public static void writeList(Parcel parcel, int i, List list, boolean z) {
        if (list != null) {
            int zza = zza(parcel, i);
            parcel.writeList(list);
            zzb(parcel, zza);
        } else if (z) {
            zzb(parcel, i, 0);
        }
    }

    public static void writeSparseBooleanArray(Parcel parcel, int i, SparseBooleanArray sparseBooleanArray, boolean z) {
        if (sparseBooleanArray != null) {
            int zza = zza(parcel, i);
            parcel.writeSparseBooleanArray(sparseBooleanArray);
            zzb(parcel, zza);
        } else if (z) {
            zzb(parcel, i, 0);
        }
    }

    public static void writeDoubleSparseArray(Parcel parcel, int i, SparseArray<Double> sparseArray, boolean z) {
        if (sparseArray != null) {
            int zza = zza(parcel, i);
            int size = sparseArray.size();
            parcel.writeInt(size);
            for (int i2 = 0; i2 < size; i2++) {
                parcel.writeInt(sparseArray.keyAt(i2));
                parcel.writeDouble(sparseArray.valueAt(i2).doubleValue());
            }
            zzb(parcel, zza);
        } else if (z) {
            zzb(parcel, i, 0);
        }
    }

    public static void writeFloatSparseArray(Parcel parcel, int i, SparseArray<Float> sparseArray, boolean z) {
        if (sparseArray != null) {
            int zza = zza(parcel, i);
            int size = sparseArray.size();
            parcel.writeInt(size);
            for (int i2 = 0; i2 < size; i2++) {
                parcel.writeInt(sparseArray.keyAt(i2));
                parcel.writeFloat(sparseArray.valueAt(i2).floatValue());
            }
            zzb(parcel, zza);
        } else if (z) {
            zzb(parcel, i, 0);
        }
    }

    public static void writeSparseIntArray(Parcel parcel, int i, SparseIntArray sparseIntArray, boolean z) {
        if (sparseIntArray != null) {
            int zza = zza(parcel, i);
            int size = sparseIntArray.size();
            parcel.writeInt(size);
            for (int i2 = 0; i2 < size; i2++) {
                parcel.writeInt(sparseIntArray.keyAt(i2));
                parcel.writeInt(sparseIntArray.valueAt(i2));
            }
            zzb(parcel, zza);
        } else if (z) {
            zzb(parcel, i, 0);
        }
    }

    public static void writeSparseLongArray(Parcel parcel, int i, SparseLongArray sparseLongArray, boolean z) {
        if (sparseLongArray != null) {
            int zza = zza(parcel, i);
            int size = sparseLongArray.size();
            parcel.writeInt(size);
            for (int i2 = 0; i2 < size; i2++) {
                parcel.writeInt(sparseLongArray.keyAt(i2));
                parcel.writeLong(sparseLongArray.valueAt(i2));
            }
            zzb(parcel, zza);
        } else if (z) {
            zzb(parcel, i, 0);
        }
    }

    public static void writeStringSparseArray(Parcel parcel, int i, SparseArray<String> sparseArray, boolean z) {
        if (sparseArray != null) {
            int zza = zza(parcel, i);
            int size = sparseArray.size();
            parcel.writeInt(size);
            for (int i2 = 0; i2 < size; i2++) {
                parcel.writeInt(sparseArray.keyAt(i2));
                parcel.writeString(sparseArray.valueAt(i2));
            }
            zzb(parcel, zza);
        } else if (z) {
            zzb(parcel, i, 0);
        }
    }

    public static void writeParcelSparseArray(Parcel parcel, int i, SparseArray<Parcel> sparseArray, boolean z) {
        if (sparseArray != null) {
            int zza = zza(parcel, i);
            int size = sparseArray.size();
            parcel.writeInt(size);
            for (int i2 = 0; i2 < size; i2++) {
                parcel.writeInt(sparseArray.keyAt(i2));
                Parcel valueAt = sparseArray.valueAt(i2);
                if (valueAt != null) {
                    parcel.writeInt(valueAt.dataSize());
                    parcel.appendFrom(valueAt, 0, valueAt.dataSize());
                } else {
                    parcel.writeInt(0);
                }
            }
            zzb(parcel, zza);
        } else if (z) {
            zzb(parcel, i, 0);
        }
    }

    public static <T extends Parcelable> void writeTypedSparseArray(Parcel parcel, int i, SparseArray<T> sparseArray, boolean z) {
        if (sparseArray != null) {
            int zza = zza(parcel, i);
            int size = sparseArray.size();
            parcel.writeInt(size);
            for (int i2 = 0; i2 < size; i2++) {
                parcel.writeInt(sparseArray.keyAt(i2));
                T valueAt = sparseArray.valueAt(i2);
                if (valueAt == null) {
                    parcel.writeInt(0);
                } else {
                    zza(parcel, valueAt, 0);
                }
            }
            zzb(parcel, zza);
        } else if (z) {
            zzb(parcel, i, 0);
        }
    }

    public static void writeIBinderSparseArray(Parcel parcel, int i, SparseArray<IBinder> sparseArray, boolean z) {
        if (sparseArray != null) {
            int zza = zza(parcel, i);
            int size = sparseArray.size();
            parcel.writeInt(size);
            for (int i2 = 0; i2 < size; i2++) {
                parcel.writeInt(sparseArray.keyAt(i2));
                parcel.writeStrongBinder(sparseArray.valueAt(i2));
            }
            zzb(parcel, zza);
        } else if (z) {
            zzb(parcel, i, 0);
        }
    }

    public static void writeByteArraySparseArray(Parcel parcel, int i, SparseArray<byte[]> sparseArray, boolean z) {
        if (sparseArray != null) {
            int zza = zza(parcel, i);
            int size = sparseArray.size();
            parcel.writeInt(size);
            for (int i2 = 0; i2 < size; i2++) {
                parcel.writeInt(sparseArray.keyAt(i2));
                parcel.writeByteArray(sparseArray.valueAt(i2));
            }
            zzb(parcel, zza);
        } else if (z) {
            zzb(parcel, i, 0);
        }
    }
}
