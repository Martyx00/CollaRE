package org.spongycastle.jce;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;
import org.spongycastle.asn1.ASN1InputStream;
import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.ASN1OctetString;
import org.spongycastle.asn1.DERNull;
import org.spongycastle.asn1.DEROctetString;
import org.spongycastle.asn1.DEROutputStream;
import org.spongycastle.asn1.pkcs.ContentInfo;
import org.spongycastle.asn1.pkcs.MacData;
import org.spongycastle.asn1.pkcs.Pfx;
import org.spongycastle.asn1.x509.AlgorithmIdentifier;
import org.spongycastle.asn1.x509.DigestInfo;

public class PKCS12Util {
    public static byte[] convertToDefiniteLength(byte[] bArr) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        DEROutputStream dEROutputStream = new DEROutputStream(byteArrayOutputStream);
        Pfx instance = Pfx.getInstance(bArr);
        byteArrayOutputStream.reset();
        dEROutputStream.writeObject(instance);
        return byteArrayOutputStream.toByteArray();
    }

    public static byte[] convertToDefiniteLength(byte[] bArr, char[] cArr, String str) {
        Pfx instance = Pfx.getInstance(bArr);
        ContentInfo authSafe = instance.getAuthSafe();
        ASN1OctetString instance2 = ASN1OctetString.getInstance(authSafe.getContent());
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        DEROutputStream dEROutputStream = new DEROutputStream(byteArrayOutputStream);
        dEROutputStream.writeObject(new ASN1InputStream(instance2.getOctets()).readObject());
        ContentInfo contentInfo = new ContentInfo(authSafe.getContentType(), new DEROctetString(byteArrayOutputStream.toByteArray()));
        MacData macData = instance.getMacData();
        try {
            int intValue = macData.getIterationCount().intValue();
            Pfx pfx = new Pfx(contentInfo, new MacData(new DigestInfo(new AlgorithmIdentifier(macData.getMac().getAlgorithmId().getAlgorithm(), DERNull.INSTANCE), calculatePbeMac(macData.getMac().getAlgorithmId().getAlgorithm(), macData.getSalt(), intValue, cArr, ASN1OctetString.getInstance(contentInfo.getContent()).getOctets(), str)), macData.getSalt(), intValue));
            byteArrayOutputStream.reset();
            dEROutputStream.writeObject(pfx);
            return byteArrayOutputStream.toByteArray();
        } catch (Exception e) {
            throw new IOException("error constructing MAC: " + e.toString());
        }
    }

    private static byte[] calculatePbeMac(ASN1ObjectIdentifier aSN1ObjectIdentifier, byte[] bArr, int i, char[] cArr, byte[] bArr2, String str) {
        SecretKeyFactory instance = SecretKeyFactory.getInstance(aSN1ObjectIdentifier.getId(), str);
        PBEParameterSpec pBEParameterSpec = new PBEParameterSpec(bArr, i);
        SecretKey generateSecret = instance.generateSecret(new PBEKeySpec(cArr));
        Mac instance2 = Mac.getInstance(aSN1ObjectIdentifier.getId(), str);
        instance2.init(generateSecret, pBEParameterSpec);
        instance2.update(bArr2);
        return instance2.doFinal();
    }
}
