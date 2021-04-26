package org.spongycastle.crypto.tls;

import java.io.InputStream;
import java.io.OutputStream;
import org.spongycastle.asn1.ASN1Encoding;
import org.spongycastle.asn1.ocsp.OCSPResponse;

public class CertificateStatus {
    protected Object response;
    protected short statusType;

    public CertificateStatus(short s, Object obj) {
        if (isCorrectType(s, obj)) {
            this.statusType = s;
            this.response = obj;
            return;
        }
        throw new IllegalArgumentException("'response' is not an instance of the correct type");
    }

    public short getStatusType() {
        return this.statusType;
    }

    public Object getResponse() {
        return this.response;
    }

    public OCSPResponse getOCSPResponse() {
        if (isCorrectType(1, this.response)) {
            return (OCSPResponse) this.response;
        }
        throw new IllegalStateException("'response' is not an OCSPResponse");
    }

    public void encode(OutputStream outputStream) {
        TlsUtils.writeUint8(this.statusType, outputStream);
        if (this.statusType == 1) {
            TlsUtils.writeOpaque24(((OCSPResponse) this.response).getEncoded(ASN1Encoding.DER), outputStream);
            return;
        }
        throw new TlsFatalAlert(80);
    }

    public static CertificateStatus parse(InputStream inputStream) {
        short readUint8 = TlsUtils.readUint8(inputStream);
        if (readUint8 == 1) {
            return new CertificateStatus(readUint8, OCSPResponse.getInstance(TlsUtils.readDERObject(TlsUtils.readOpaque24(inputStream))));
        }
        throw new TlsFatalAlert(50);
    }

    protected static boolean isCorrectType(short s, Object obj) {
        if (s == 1) {
            return obj instanceof OCSPResponse;
        }
        throw new IllegalArgumentException("'statusType' is an unsupported value");
    }
}
