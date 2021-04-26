package com.facebook.h;

import android.util.Pair;
import java.io.IOException;
import java.io.InputStream;
import org.spongycastle.crypto.tls.CipherSuite;
import org.spongycastle.math.ec.Tnaf;

/* compiled from: WebpUtil */
public class e {
    public static Pair<Integer, Integer> a(InputStream inputStream) {
        byte[] bArr = new byte[4];
        try {
            inputStream.read(bArr);
            if (!a(bArr, "RIFF")) {
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                return null;
            }
            f(inputStream);
            inputStream.read(bArr);
            if (!a(bArr, "WEBP")) {
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException e2) {
                        e2.printStackTrace();
                    }
                }
                return null;
            }
            inputStream.read(bArr);
            String a2 = a(bArr);
            if ("VP8 ".equals(a2)) {
                Pair<Integer, Integer> c2 = c(inputStream);
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException e3) {
                        e3.printStackTrace();
                    }
                }
                return c2;
            } else if ("VP8L".equals(a2)) {
                Pair<Integer, Integer> d2 = d(inputStream);
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException e4) {
                        e4.printStackTrace();
                    }
                }
                return d2;
            } else if ("VP8X".equals(a2)) {
                Pair<Integer, Integer> e5 = e(inputStream);
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException e6) {
                        e6.printStackTrace();
                    }
                }
                return e5;
            } else {
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException e7) {
                        e7.printStackTrace();
                    }
                }
                return null;
            }
        } catch (IOException e8) {
            e8.printStackTrace();
            if (inputStream != null) {
                inputStream.close();
            }
        } catch (Throwable th) {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e9) {
                    e9.printStackTrace();
                }
            }
            throw th;
        }
    }

    private static Pair<Integer, Integer> c(InputStream inputStream) {
        inputStream.skip(7);
        short h = h(inputStream);
        short h2 = h(inputStream);
        short h3 = h(inputStream);
        if (h == 157 && h2 == 1 && h3 == 42) {
            return new Pair<>(Integer.valueOf(b(inputStream)), Integer.valueOf(b(inputStream)));
        }
        return null;
    }

    private static Pair<Integer, Integer> d(InputStream inputStream) {
        f(inputStream);
        if (i(inputStream) != 47) {
            return null;
        }
        int read = ((byte) inputStream.read()) & 255;
        return new Pair<>(Integer.valueOf(((((byte) inputStream.read()) & 255) | ((read & 63) << 8)) + 1), Integer.valueOf(((((((byte) inputStream.read()) & 255) & 15) << 10) | ((((byte) inputStream.read()) & 255) << 2) | ((read & 192) >> 6)) + 1));
    }

    private static Pair<Integer, Integer> e(InputStream inputStream) {
        inputStream.skip(8);
        return new Pair<>(Integer.valueOf(g(inputStream) + 1), Integer.valueOf(g(inputStream) + 1));
    }

    private static boolean a(byte[] bArr, String str) {
        if (bArr.length != str.length()) {
            return false;
        }
        for (int i = 0; i < bArr.length; i++) {
            if (str.charAt(i) != bArr[i]) {
                return false;
            }
        }
        return true;
    }

    private static String a(byte[] bArr) {
        StringBuilder sb = new StringBuilder();
        for (byte b2 : bArr) {
            sb.append((char) b2);
        }
        return sb.toString();
    }

    private static int f(InputStream inputStream) {
        return ((((byte) inputStream.read()) << 24) & -16777216) | ((((byte) inputStream.read()) << Tnaf.POW_2_WIDTH) & 16711680) | ((((byte) inputStream.read()) << 8) & CipherSuite.DRAFT_TLS_DHE_RSA_WITH_AES_128_OCB) | (((byte) inputStream.read()) & 255);
    }

    public static int b(InputStream inputStream) {
        return ((((byte) inputStream.read()) << 8) & CipherSuite.DRAFT_TLS_DHE_RSA_WITH_AES_128_OCB) | (((byte) inputStream.read()) & 255);
    }

    private static int g(InputStream inputStream) {
        byte i = i(inputStream);
        return ((i(inputStream) << Tnaf.POW_2_WIDTH) & 16711680) | ((i(inputStream) << 8) & CipherSuite.DRAFT_TLS_DHE_RSA_WITH_AES_128_OCB) | (i & 255);
    }

    private static short h(InputStream inputStream) {
        return (short) (inputStream.read() & 255);
    }

    private static byte i(InputStream inputStream) {
        return (byte) (inputStream.read() & 255);
    }
}
