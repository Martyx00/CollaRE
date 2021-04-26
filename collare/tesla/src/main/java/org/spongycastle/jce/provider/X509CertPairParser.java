package org.spongycastle.jce.provider;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import org.spongycastle.asn1.ASN1InputStream;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.x509.CertificatePair;
import org.spongycastle.x509.X509CertificatePair;
import org.spongycastle.x509.X509StreamParserSpi;
import org.spongycastle.x509.util.StreamParsingException;

public class X509CertPairParser extends X509StreamParserSpi {
    private InputStream currentStream = null;

    private X509CertificatePair readDERCrossCertificatePair(InputStream inputStream) {
        return new X509CertificatePair(CertificatePair.getInstance((ASN1Sequence) new ASN1InputStream(inputStream).readObject()));
    }

    @Override // org.spongycastle.x509.X509StreamParserSpi
    public void engineInit(InputStream inputStream) {
        this.currentStream = inputStream;
        if (!this.currentStream.markSupported()) {
            this.currentStream = new BufferedInputStream(this.currentStream);
        }
    }

    @Override // org.spongycastle.x509.X509StreamParserSpi
    public Object engineRead() {
        try {
            this.currentStream.mark(10);
            if (this.currentStream.read() == -1) {
                return null;
            }
            this.currentStream.reset();
            return readDERCrossCertificatePair(this.currentStream);
        } catch (Exception e) {
            throw new StreamParsingException(e.toString(), e);
        }
    }

    @Override // org.spongycastle.x509.X509StreamParserSpi
    public Collection engineReadAll() {
        ArrayList arrayList = new ArrayList();
        while (true) {
            X509CertificatePair x509CertificatePair = (X509CertificatePair) engineRead();
            if (x509CertificatePair == null) {
                return arrayList;
            }
            arrayList.add(x509CertificatePair);
        }
    }
}
