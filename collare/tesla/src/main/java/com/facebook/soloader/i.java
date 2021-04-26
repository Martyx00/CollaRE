package com.facebook.soloader;

import java.io.File;
import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.FileChannel;

/* compiled from: MinElf */
public final class i {
    public static String[] a(File file) {
        FileInputStream fileInputStream = new FileInputStream(file);
        try {
            return a(fileInputStream.getChannel());
        } finally {
            fileInputStream.close();
        }
    }

    public static String[] a(FileChannel fileChannel) {
        long j;
        long j2;
        int i;
        long j3;
        long j4;
        boolean z;
        long j5;
        long j6;
        long j7;
        long j8;
        long j9;
        long j10;
        long j11;
        int i2;
        long j12;
        long j13;
        long j14;
        long j15;
        long j16;
        long j17;
        ByteBuffer allocate = ByteBuffer.allocate(8);
        allocate.order(ByteOrder.LITTLE_ENDIAN);
        if (c(fileChannel, allocate, 0) == 1179403647) {
            boolean z2 = true;
            if (e(fileChannel, allocate, 4) != 1) {
                z2 = false;
            }
            if (e(fileChannel, allocate, 5) == 2) {
                allocate.order(ByteOrder.BIG_ENDIAN);
            }
            if (z2) {
                j = c(fileChannel, allocate, 28);
            } else {
                j = b(fileChannel, allocate, 32);
            }
            if (z2) {
                j2 = (long) d(fileChannel, allocate, 44);
            } else {
                j2 = (long) d(fileChannel, allocate, 56);
            }
            if (z2) {
                i = d(fileChannel, allocate, 42);
            } else {
                i = d(fileChannel, allocate, 54);
            }
            if (j2 == 65535) {
                if (z2) {
                    j17 = c(fileChannel, allocate, 32);
                } else {
                    j17 = b(fileChannel, allocate, 40);
                }
                if (z2) {
                    j2 = c(fileChannel, allocate, j17 + 28);
                } else {
                    j2 = c(fileChannel, allocate, j17 + 44);
                }
            }
            long j18 = j;
            long j19 = 0;
            while (true) {
                if (j19 >= j2) {
                    j3 = 0;
                    j4 = 0;
                    break;
                }
                if (z2) {
                    j16 = c(fileChannel, allocate, j18 + 0);
                } else {
                    j16 = c(fileChannel, allocate, j18 + 0);
                }
                if (j16 == 2) {
                    if (z2) {
                        j3 = c(fileChannel, allocate, j18 + 4);
                    } else {
                        j3 = b(fileChannel, allocate, j18 + 8);
                    }
                    j4 = 0;
                } else {
                    j18 += (long) i;
                    j19++;
                }
            }
            if (j3 != j4) {
                long j20 = j3;
                long j21 = j4;
                int i3 = 0;
                while (true) {
                    if (z2) {
                        z = z2;
                        j5 = c(fileChannel, allocate, j20 + j4);
                    } else {
                        z = z2;
                        j5 = b(fileChannel, allocate, j20 + j4);
                    }
                    if (j5 == 1) {
                        if (i3 != Integer.MAX_VALUE) {
                            i3++;
                            j6 = j5;
                        } else {
                            throw new a("malformed DT_NEEDED section");
                        }
                    } else if (j5 == 5) {
                        if (z) {
                            j6 = j5;
                            j15 = c(fileChannel, allocate, j20 + 4);
                        } else {
                            j6 = j5;
                            j15 = b(fileChannel, allocate, j20 + 8);
                        }
                        j21 = j15;
                    } else {
                        j6 = j5;
                    }
                    long j22 = 16;
                    j20 += z ? 8 : 16;
                    j4 = 0;
                    if (j6 != 0) {
                        z2 = z;
                    } else if (j21 != 0) {
                        long j23 = j3;
                        int i4 = 0;
                        while (true) {
                            if (((long) i4) >= j2) {
                                j7 = 0;
                                j8 = 0;
                                break;
                            }
                            if (z) {
                                j11 = c(fileChannel, allocate, j + j4);
                            } else {
                                j11 = c(fileChannel, allocate, j + j4);
                            }
                            if (j11 == 1) {
                                if (z) {
                                    j12 = c(fileChannel, allocate, j + 8);
                                } else {
                                    j12 = b(fileChannel, allocate, j + j22);
                                }
                                if (z) {
                                    i2 = i4;
                                    j13 = c(fileChannel, allocate, j + 20);
                                } else {
                                    i2 = i4;
                                    j13 = b(fileChannel, allocate, j + 40);
                                }
                                if (j12 <= j21 && j21 < j13 + j12) {
                                    if (z) {
                                        j14 = c(fileChannel, allocate, j + 4);
                                    } else {
                                        j14 = b(fileChannel, allocate, j + 8);
                                    }
                                    j7 = j14 + (j21 - j12);
                                    j8 = 0;
                                }
                            } else {
                                i2 = i4;
                            }
                            j += (long) i;
                            i4 = i2 + 1;
                            j22 = 16;
                            j4 = 0;
                        }
                        if (j7 != j8) {
                            String[] strArr = new String[i3];
                            int i5 = 0;
                            while (true) {
                                if (z) {
                                    j9 = c(fileChannel, allocate, j23 + j8);
                                } else {
                                    j9 = b(fileChannel, allocate, j23 + j8);
                                }
                                if (j9 == 1) {
                                    if (z) {
                                        j10 = c(fileChannel, allocate, j23 + 4);
                                    } else {
                                        j10 = b(fileChannel, allocate, j23 + 8);
                                    }
                                    strArr[i5] = a(fileChannel, allocate, j10 + j7);
                                    if (i5 != Integer.MAX_VALUE) {
                                        i5++;
                                    } else {
                                        throw new a("malformed DT_NEEDED section");
                                    }
                                }
                                j23 += z ? 8 : 16;
                                if (j9 != 0) {
                                    j8 = 0;
                                } else if (i5 == strArr.length) {
                                    return strArr;
                                } else {
                                    throw new a("malformed DT_NEEDED section");
                                }
                            }
                        } else {
                            throw new a("did not find file offset of DT_STRTAB table");
                        }
                    } else {
                        throw new a("Dynamic section string-table not found");
                    }
                }
            } else {
                throw new a("ELF file does not contain dynamic linking information");
            }
        } else {
            throw new a("file is not ELF");
        }
    }

    private static String a(FileChannel fileChannel, ByteBuffer byteBuffer, long j) {
        StringBuilder sb = new StringBuilder();
        while (true) {
            long j2 = 1 + j;
            short e = e(fileChannel, byteBuffer, j);
            if (e == 0) {
                return sb.toString();
            }
            sb.append((char) e);
            j = j2;
        }
    }

    private static void a(FileChannel fileChannel, ByteBuffer byteBuffer, int i, long j) {
        int read;
        byteBuffer.position(0);
        byteBuffer.limit(i);
        while (byteBuffer.remaining() > 0 && (read = fileChannel.read(byteBuffer, j)) != -1) {
            j += (long) read;
        }
        if (byteBuffer.remaining() <= 0) {
            byteBuffer.position(0);
            return;
        }
        throw new a("ELF file truncated");
    }

    private static long b(FileChannel fileChannel, ByteBuffer byteBuffer, long j) {
        a(fileChannel, byteBuffer, 8, j);
        return byteBuffer.getLong();
    }

    private static long c(FileChannel fileChannel, ByteBuffer byteBuffer, long j) {
        a(fileChannel, byteBuffer, 4, j);
        return ((long) byteBuffer.getInt()) & 4294967295L;
    }

    private static int d(FileChannel fileChannel, ByteBuffer byteBuffer, long j) {
        a(fileChannel, byteBuffer, 2, j);
        return byteBuffer.getShort() & 65535;
    }

    private static short e(FileChannel fileChannel, ByteBuffer byteBuffer, long j) {
        a(fileChannel, byteBuffer, 1, j);
        return (short) (byteBuffer.get() & 255);
    }

    /* access modifiers changed from: private */
    /* compiled from: MinElf */
    public static class a extends RuntimeException {
        a(String str) {
            super(str);
        }
    }
}
