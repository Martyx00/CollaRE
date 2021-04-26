package org.spongycastle.asn1.util;

import java.io.FileInputStream;
import org.spongycastle.asn1.ASN1InputStream;
import org.spongycastle.asn1.ASN1Primitive;

public class Dump {
    public static void main(String[] strArr) {
        ASN1InputStream aSN1InputStream = new ASN1InputStream(new FileInputStream(strArr[0]));
        while (true) {
            ASN1Primitive readObject = aSN1InputStream.readObject();
            if (readObject != null) {
                System.out.println(ASN1Dump.dumpAsString(readObject));
            } else {
                return;
            }
        }
    }
}
