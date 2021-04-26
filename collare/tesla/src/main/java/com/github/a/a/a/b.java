package com.github.a.a.a;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.util.BitSet;

/* compiled from: QuotedPrintableCodec */
public class b {

    /* renamed from: a  reason: collision with root package name */
    private static final BitSet f3731a = new BitSet(256);

    /* renamed from: b  reason: collision with root package name */
    private final String f3732b;

    static {
        for (int i = 33; i <= 60; i++) {
            f3731a.set(i);
        }
        for (int i2 = 62; i2 <= 126; i2++) {
            f3731a.set(i2);
        }
        f3731a.set(9);
        f3731a.set(32);
    }

    public b(String str) {
        this.f3732b = str;
    }

    public String a(String str) {
        try {
            byte[] bytes = str.getBytes("US-ASCII");
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            int i = 0;
            while (i < bytes.length) {
                byte b2 = bytes[i];
                if (b2 == 61) {
                    int i2 = i + 1;
                    try {
                        int a2 = a(bytes[i2]);
                        i = i2 + 1;
                        byteArrayOutputStream.write((char) ((a2 << 4) + a(bytes[i])));
                    } catch (ArrayIndexOutOfBoundsException e) {
                        throw new a("Invalid quoted-printable encoding", e);
                    }
                } else {
                    byteArrayOutputStream.write(b2);
                }
                i++;
            }
            try {
                return new String(byteArrayOutputStream.toByteArray(), this.f3732b);
            } catch (UnsupportedEncodingException e2) {
                throw new a(e2);
            }
        } catch (UnsupportedEncodingException e3) {
            throw new a(e3);
        }
    }

    private static int a(byte b2) {
        int digit = Character.digit((char) b2, 16);
        if (digit != -1) {
            return digit;
        }
        throw new a("Invalid URL encoding: not a valid digit (radix 16): " + ((int) b2));
    }
}
