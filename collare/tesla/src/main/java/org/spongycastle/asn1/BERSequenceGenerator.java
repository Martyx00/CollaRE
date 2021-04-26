package org.spongycastle.asn1;

import java.io.OutputStream;

public class BERSequenceGenerator extends BERGenerator {
    public BERSequenceGenerator(OutputStream outputStream) {
        super(outputStream);
        writeBERHeader(48);
    }

    public BERSequenceGenerator(OutputStream outputStream, int i, boolean z) {
        super(outputStream, i, z);
        writeBERHeader(48);
    }

    public void addObject(ASN1Encodable aSN1Encodable) {
        aSN1Encodable.toASN1Primitive().encode(new BEROutputStream(this._out));
    }

    public void close() {
        writeBEREnd();
    }
}
