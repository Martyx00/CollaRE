package org.spongycastle.asn1;

import com.google.android.gms.common.api.Api;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.channels.FileChannel;
import org.spongycastle.asn1.eac.CertificateBody;

class StreamUtil {
    private static final long MAX_MEMORY = Runtime.getRuntime().maxMemory();

    static int calculateBodyLength(int i) {
        int i2 = 1;
        if (i > 127) {
            int i3 = 1;
            while (true) {
                i >>>= 8;
                if (i == 0) {
                    break;
                }
                i3++;
            }
            for (int i4 = (i3 - 1) * 8; i4 >= 0; i4 -= 8) {
                i2++;
            }
        }
        return i2;
    }

    StreamUtil() {
    }

    static int findLimit(InputStream inputStream) {
        long j;
        if (inputStream instanceof LimitedInputStream) {
            return ((LimitedInputStream) inputStream).getRemaining();
        }
        if (inputStream instanceof ASN1InputStream) {
            return ((ASN1InputStream) inputStream).getLimit();
        }
        if (inputStream instanceof ByteArrayInputStream) {
            return ((ByteArrayInputStream) inputStream).available();
        }
        if (inputStream instanceof FileInputStream) {
            try {
                FileChannel channel = ((FileInputStream) inputStream).getChannel();
                if (channel != null) {
                    j = channel.size();
                } else {
                    j = 2147483647L;
                }
                if (j < 2147483647L) {
                    return (int) j;
                }
            } catch (IOException unused) {
            }
        }
        long j2 = MAX_MEMORY;
        return j2 > 2147483647L ? Api.BaseClientBuilder.API_PRIORITY_OTHER : (int) j2;
    }

    static int calculateTagLength(int i) {
        if (i < 31) {
            return 1;
        }
        if (i < 128) {
            return 2;
        }
        byte[] bArr = new byte[5];
        int length = bArr.length - 1;
        bArr[length] = (byte) (i & CertificateBody.profileType);
        do {
            i >>= 7;
            length--;
            bArr[length] = (byte) ((i & CertificateBody.profileType) | 128);
        } while (i > 127);
        return 1 + (bArr.length - length);
    }
}
