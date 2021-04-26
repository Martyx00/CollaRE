package com.google.protobuf;

/* access modifiers changed from: package-private */
/* compiled from: Utf8 */
public final class az {

    /* renamed from: a  reason: collision with root package name */
    private static final a f4158a = (d.a() ? new d() : new b());

    /* access modifiers changed from: private */
    public static int b(int i) {
        if (i > -12) {
            return -1;
        }
        return i;
    }

    /* access modifiers changed from: private */
    public static int b(int i, int i2) {
        if (i > -12 || i2 > -65) {
            return -1;
        }
        return i ^ (i2 << 8);
    }

    /* access modifiers changed from: private */
    public static int b(int i, int i2, int i3) {
        if (i > -12 || i2 > -65 || i3 > -65) {
            return -1;
        }
        return (i ^ (i2 << 8)) ^ (i3 << 16);
    }

    public static boolean a(byte[] bArr) {
        return f4158a.a(bArr, 0, bArr.length);
    }

    public static boolean a(byte[] bArr, int i, int i2) {
        return f4158a.a(bArr, i, i2);
    }

    public static int a(int i, byte[] bArr, int i2, int i3) {
        return f4158a.a(i, bArr, i2, i3);
    }

    /* access modifiers changed from: private */
    public static int c(byte[] bArr, int i, int i2) {
        byte b2 = bArr[i - 1];
        switch (i2 - i) {
            case 0:
                return b(b2);
            case 1:
                return b(b2, bArr[i]);
            case 2:
                return b(b2, bArr[i], bArr[i + 1]);
            default:
                throw new AssertionError();
        }
    }

    /* access modifiers changed from: package-private */
    /* compiled from: Utf8 */
    public static class c extends IllegalArgumentException {
        c(int i, int i2) {
            super("Unpaired surrogate at index " + i + " of " + i2);
        }
    }

    static int a(CharSequence charSequence) {
        int length = charSequence.length();
        int i = 0;
        while (i < length && charSequence.charAt(i) < 128) {
            i++;
        }
        int i2 = length;
        while (true) {
            if (i < length) {
                char charAt = charSequence.charAt(i);
                if (charAt >= 2048) {
                    i2 += a(charSequence, i);
                    break;
                }
                i2 += (127 - charAt) >>> 31;
                i++;
            } else {
                break;
            }
        }
        if (i2 >= length) {
            return i2;
        }
        throw new IllegalArgumentException("UTF-8 length does not fit in int: " + (((long) i2) + 4294967296L));
    }

    private static int a(CharSequence charSequence, int i) {
        int length = charSequence.length();
        int i2 = 0;
        while (i < length) {
            char charAt = charSequence.charAt(i);
            if (charAt < 2048) {
                i2 += (127 - charAt) >>> 31;
            } else {
                i2 += 2;
                if (55296 <= charAt && charAt <= 57343) {
                    if (Character.codePointAt(charSequence, i) >= 65536) {
                        i++;
                    } else {
                        throw new c(i, length);
                    }
                }
            }
            i++;
        }
        return i2;
    }

    static int a(CharSequence charSequence, byte[] bArr, int i, int i2) {
        return f4158a.a(charSequence, bArr, i, i2);
    }

    /* access modifiers changed from: package-private */
    /* compiled from: Utf8 */
    public static abstract class a {
        /* access modifiers changed from: package-private */
        public abstract int a(int i, byte[] bArr, int i2, int i3);

        /* access modifiers changed from: package-private */
        public abstract int a(CharSequence charSequence, byte[] bArr, int i, int i2);

        a() {
        }

        /* access modifiers changed from: package-private */
        public final boolean a(byte[] bArr, int i, int i2) {
            return a(0, bArr, i, i2) == 0;
        }
    }

    /* compiled from: Utf8 */
    static final class b extends a {
        b() {
        }

        /* access modifiers changed from: package-private */
        /* JADX WARNING: Code restructure failed: missing block: B:27:0x0042, code lost:
            if (r8[r9] > -65) goto L_0x0044;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:8:0x0015, code lost:
            if (r8[r9] > -65) goto L_0x0017;
         */
        @Override // com.google.protobuf.az.a
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public int a(int r7, byte[] r8, int r9, int r10) {
            /*
            // Method dump skipped, instructions count: 134
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.az.b.a(int, byte[], int, int):int");
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.protobuf.az.a
        public int a(CharSequence charSequence, byte[] bArr, int i, int i2) {
            int i3;
            int i4;
            char charAt;
            int length = charSequence.length();
            int i5 = i2 + i;
            int i6 = 0;
            while (i6 < length && (i4 = i6 + i) < i5 && (charAt = charSequence.charAt(i6)) < 128) {
                bArr[i4] = (byte) charAt;
                i6++;
            }
            if (i6 == length) {
                return i + length;
            }
            int i7 = i + i6;
            while (i6 < length) {
                char charAt2 = charSequence.charAt(i6);
                if (charAt2 < 128 && i7 < i5) {
                    bArr[i7] = (byte) charAt2;
                    i7++;
                } else if (charAt2 < 2048 && i7 <= i5 - 2) {
                    int i8 = i7 + 1;
                    bArr[i7] = (byte) ((charAt2 >>> 6) | 960);
                    i7 = i8 + 1;
                    bArr[i8] = (byte) ((charAt2 & '?') | 128);
                } else if ((charAt2 < 55296 || 57343 < charAt2) && i7 <= i5 - 3) {
                    int i9 = i7 + 1;
                    bArr[i7] = (byte) ((charAt2 >>> '\f') | 480);
                    int i10 = i9 + 1;
                    bArr[i9] = (byte) (((charAt2 >>> 6) & 63) | 128);
                    bArr[i10] = (byte) ((charAt2 & '?') | 128);
                    i7 = i10 + 1;
                } else if (i7 <= i5 - 4) {
                    int i11 = i6 + 1;
                    if (i11 != charSequence.length()) {
                        char charAt3 = charSequence.charAt(i11);
                        if (Character.isSurrogatePair(charAt2, charAt3)) {
                            int codePoint = Character.toCodePoint(charAt2, charAt3);
                            int i12 = i7 + 1;
                            bArr[i7] = (byte) ((codePoint >>> 18) | 240);
                            int i13 = i12 + 1;
                            bArr[i12] = (byte) (((codePoint >>> 12) & 63) | 128);
                            int i14 = i13 + 1;
                            bArr[i13] = (byte) (((codePoint >>> 6) & 63) | 128);
                            i7 = i14 + 1;
                            bArr[i14] = (byte) ((codePoint & 63) | 128);
                            i6 = i11;
                        } else {
                            i6 = i11;
                        }
                    }
                    throw new c(i6 - 1, length);
                } else if (55296 > charAt2 || charAt2 > 57343 || ((i3 = i6 + 1) != charSequence.length() && Character.isSurrogatePair(charAt2, charSequence.charAt(i3)))) {
                    throw new ArrayIndexOutOfBoundsException("Failed writing " + charAt2 + " at index " + i7);
                } else {
                    throw new c(i6, length);
                }
                i6++;
            }
            return i7;
        }

        private static int b(byte[] bArr, int i, int i2) {
            while (i < i2 && bArr[i] >= 0) {
                i++;
            }
            if (i >= i2) {
                return 0;
            }
            return c(bArr, i, i2);
        }

        private static int c(byte[] bArr, int i, int i2) {
            while (i < i2) {
                int i3 = i + 1;
                byte b2 = bArr[i];
                if (b2 >= 0) {
                    i = i3;
                } else if (b2 < -32) {
                    if (i3 >= i2) {
                        return b2;
                    }
                    if (b2 >= -62) {
                        i = i3 + 1;
                        if (bArr[i3] > -65) {
                        }
                    }
                    return -1;
                } else if (b2 < -16) {
                    if (i3 >= i2 - 1) {
                        return az.c(bArr, i3, i2);
                    }
                    int i4 = i3 + 1;
                    byte b3 = bArr[i3];
                    if (b3 <= -65 && ((b2 != -32 || b3 >= -96) && (b2 != -19 || b3 < -96))) {
                        i = i4 + 1;
                        if (bArr[i4] > -65) {
                        }
                    }
                    return -1;
                } else if (i3 >= i2 - 2) {
                    return az.c(bArr, i3, i2);
                } else {
                    int i5 = i3 + 1;
                    byte b4 = bArr[i3];
                    if (b4 <= -65 && (((b2 << 28) + (b4 + 112)) >> 30) == 0) {
                        int i6 = i5 + 1;
                        if (bArr[i5] <= -65) {
                            int i7 = i6 + 1;
                            if (bArr[i6] <= -65) {
                                i = i7;
                            }
                        }
                    }
                    return -1;
                }
            }
            return 0;
        }
    }

    /* compiled from: Utf8 */
    static final class d extends a {
        d() {
        }

        static boolean a() {
            return ay.a() && ay.b();
        }

        /* access modifiers changed from: package-private */
        /* JADX WARNING: Code restructure failed: missing block: B:12:0x0025, code lost:
            if (com.google.protobuf.ay.a(r13, r2) > -65) goto L_0x0027;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:31:0x0056, code lost:
            if (com.google.protobuf.ay.a(r13, r2) > -65) goto L_0x0058;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:52:0x0098, code lost:
            if (com.google.protobuf.ay.a(r13, r2) > -65) goto L_0x009a;
         */
        @Override // com.google.protobuf.az.a
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public int a(int r12, byte[] r13, int r14, int r15) {
            /*
            // Method dump skipped, instructions count: 199
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.az.d.a(int, byte[], int, int):int");
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.protobuf.az.a
        public int a(CharSequence charSequence, byte[] bArr, int i, int i2) {
            int i3;
            char charAt;
            long j = (long) i;
            long j2 = ((long) i2) + j;
            int length = charSequence.length();
            if (length > i2 || bArr.length - i2 < i) {
                throw new ArrayIndexOutOfBoundsException("Failed writing " + charSequence.charAt(length - 1) + " at index " + (i + i2));
            }
            int i4 = 0;
            while (i4 < length && (charAt = charSequence.charAt(i4)) < 128) {
                ay.a(bArr, j, (byte) charAt);
                i4++;
                j = 1 + j;
            }
            if (i4 == length) {
                return (int) j;
            }
            while (i4 < length) {
                char charAt2 = charSequence.charAt(i4);
                if (charAt2 < 128 && j < j2) {
                    ay.a(bArr, j, (byte) charAt2);
                    j++;
                } else if (charAt2 < 2048 && j <= j2 - 2) {
                    long j3 = j + 1;
                    ay.a(bArr, j, (byte) ((charAt2 >>> 6) | 960));
                    j = j3 + 1;
                    ay.a(bArr, j3, (byte) ((charAt2 & '?') | 128));
                } else if ((charAt2 < 55296 || 57343 < charAt2) && j <= j2 - 3) {
                    long j4 = j + 1;
                    ay.a(bArr, j, (byte) ((charAt2 >>> '\f') | 480));
                    long j5 = j4 + 1;
                    ay.a(bArr, j4, (byte) (((charAt2 >>> 6) & 63) | 128));
                    ay.a(bArr, j5, (byte) ((charAt2 & '?') | 128));
                    j = j5 + 1;
                } else if (j <= j2 - 4) {
                    int i5 = i4 + 1;
                    if (i5 != length) {
                        char charAt3 = charSequence.charAt(i5);
                        if (Character.isSurrogatePair(charAt2, charAt3)) {
                            int codePoint = Character.toCodePoint(charAt2, charAt3);
                            long j6 = j + 1;
                            ay.a(bArr, j, (byte) ((codePoint >>> 18) | 240));
                            long j7 = j6 + 1;
                            ay.a(bArr, j6, (byte) (((codePoint >>> 12) & 63) | 128));
                            long j8 = j7 + 1;
                            ay.a(bArr, j7, (byte) (((codePoint >>> 6) & 63) | 128));
                            j = j8 + 1;
                            ay.a(bArr, j8, (byte) ((codePoint & 63) | 128));
                            i4 = i5;
                        }
                    } else {
                        i5 = i4;
                    }
                    throw new c(i5 - 1, length);
                } else if (55296 > charAt2 || charAt2 > 57343 || ((i3 = i4 + 1) != length && Character.isSurrogatePair(charAt2, charSequence.charAt(i3)))) {
                    throw new ArrayIndexOutOfBoundsException("Failed writing " + charAt2 + " at index " + j);
                } else {
                    throw new c(i4, length);
                }
                i4++;
            }
            return (int) j;
        }

        private static int a(byte[] bArr, long j, int i) {
            int i2 = 0;
            if (i < 16) {
                return 0;
            }
            while (i2 < i) {
                long j2 = 1 + j;
                if (ay.a(bArr, j) < 0) {
                    return i2;
                }
                i2++;
                j = j2;
            }
            return i;
        }

        /* JADX WARNING: Code restructure failed: missing block: B:19:0x0039, code lost:
            return -1;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:37:0x0066, code lost:
            return -1;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private static int b(byte[] r8, long r9, int r11) {
            /*
            // Method dump skipped, instructions count: 150
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.az.d.b(byte[], long, int):int");
        }

        private static int a(byte[] bArr, int i, long j, int i2) {
            switch (i2) {
                case 0:
                    return az.b(i);
                case 1:
                    return az.b(i, ay.a(bArr, j));
                case 2:
                    return az.b(i, ay.a(bArr, j), ay.a(bArr, j + 1));
                default:
                    throw new AssertionError();
            }
        }
    }
}
