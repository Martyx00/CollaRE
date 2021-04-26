package com.facebook.react.modules.network;

import com.facebook.common.e.a;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

/* compiled from: ProgressiveStringDecoder */
public class l {

    /* renamed from: a  reason: collision with root package name */
    private final CharsetDecoder f2958a;

    /* renamed from: b  reason: collision with root package name */
    private byte[] f2959b = null;

    public l(Charset charset) {
        this.f2958a = charset.newDecoder();
    }

    public String a(byte[] bArr, int i) {
        byte[] bArr2 = this.f2959b;
        if (bArr2 != null) {
            byte[] bArr3 = new byte[(bArr2.length + i)];
            System.arraycopy(bArr2, 0, bArr3, 0, bArr2.length);
            System.arraycopy(bArr, 0, bArr3, this.f2959b.length, i);
            i += this.f2959b.length;
            bArr = bArr3;
        }
        boolean z = true;
        ByteBuffer wrap = ByteBuffer.wrap(bArr, 0, i);
        CharBuffer charBuffer = null;
        boolean z2 = false;
        int i2 = 0;
        while (!z2 && i2 < 4) {
            try {
                charBuffer = this.f2958a.decode(wrap);
                z2 = true;
            } catch (CharacterCodingException unused) {
                i2++;
                wrap = ByteBuffer.wrap(bArr, 0, i - i2);
            }
        }
        if (!z2 || i2 <= 0) {
            z = false;
        }
        if (z) {
            this.f2959b = new byte[i2];
            System.arraycopy(bArr, i - i2, this.f2959b, 0, i2);
        } else {
            this.f2959b = null;
        }
        if (z2) {
            return new String(charBuffer.array(), 0, charBuffer.length());
        }
        a.c("ReactNative", "failed to decode string from byte array");
        return "";
    }
}
