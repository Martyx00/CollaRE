package org.spongycastle.jce.netscape;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Encoding;
import org.spongycastle.asn1.ASN1InputStream;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.DERBitString;
import org.spongycastle.asn1.DERIA5String;
import org.spongycastle.asn1.DERSequence;
import org.spongycastle.asn1.x509.AlgorithmIdentifier;
import org.spongycastle.asn1.x509.SubjectPublicKeyInfo;
import org.spongycastle.jce.provider.BouncyCastleProvider;

public class NetscapeCertRequest extends ASN1Object {
    String challenge;
    DERBitString content;
    AlgorithmIdentifier keyAlg;
    PublicKey pubkey;
    AlgorithmIdentifier sigAlg;
    byte[] sigBits;

    private static ASN1Sequence getReq(byte[] bArr) {
        return ASN1Sequence.getInstance(new ASN1InputStream(new ByteArrayInputStream(bArr)).readObject());
    }

    public NetscapeCertRequest(byte[] bArr) {
        this(getReq(bArr));
    }

    public NetscapeCertRequest(ASN1Sequence aSN1Sequence) {
        try {
            if (aSN1Sequence.size() == 3) {
                this.sigAlg = AlgorithmIdentifier.getInstance(aSN1Sequence.getObjectAt(1));
                this.sigBits = ((DERBitString) aSN1Sequence.getObjectAt(2)).getOctets();
                ASN1Sequence aSN1Sequence2 = (ASN1Sequence) aSN1Sequence.getObjectAt(0);
                if (aSN1Sequence2.size() == 2) {
                    this.challenge = ((DERIA5String) aSN1Sequence2.getObjectAt(1)).getString();
                    this.content = new DERBitString(aSN1Sequence2);
                    SubjectPublicKeyInfo instance = SubjectPublicKeyInfo.getInstance(aSN1Sequence2.getObjectAt(0));
                    X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(new DERBitString(instance).getBytes());
                    this.keyAlg = instance.getAlgorithm();
                    this.pubkey = KeyFactory.getInstance(this.keyAlg.getAlgorithm().getId(), BouncyCastleProvider.PROVIDER_NAME).generatePublic(x509EncodedKeySpec);
                    return;
                }
                throw new IllegalArgumentException("invalid PKAC (len): " + aSN1Sequence2.size());
            }
            throw new IllegalArgumentException("invalid SPKAC (size):" + aSN1Sequence.size());
        } catch (Exception e) {
            throw new IllegalArgumentException(e.toString());
        }
    }

    public NetscapeCertRequest(String str, AlgorithmIdentifier algorithmIdentifier, PublicKey publicKey) {
        this.challenge = str;
        this.sigAlg = algorithmIdentifier;
        this.pubkey = publicKey;
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(getKeySpec());
        aSN1EncodableVector.add(new DERIA5String(str));
        try {
            this.content = new DERBitString(new DERSequence(aSN1EncodableVector));
        } catch (IOException e) {
            throw new InvalidKeySpecException("exception encoding key: " + e.toString());
        }
    }

    public String getChallenge() {
        return this.challenge;
    }

    public void setChallenge(String str) {
        this.challenge = str;
    }

    public AlgorithmIdentifier getSigningAlgorithm() {
        return this.sigAlg;
    }

    public void setSigningAlgorithm(AlgorithmIdentifier algorithmIdentifier) {
        this.sigAlg = algorithmIdentifier;
    }

    public AlgorithmIdentifier getKeyAlgorithm() {
        return this.keyAlg;
    }

    public void setKeyAlgorithm(AlgorithmIdentifier algorithmIdentifier) {
        this.keyAlg = algorithmIdentifier;
    }

    public PublicKey getPublicKey() {
        return this.pubkey;
    }

    public void setPublicKey(PublicKey publicKey) {
        this.pubkey = publicKey;
    }

    public boolean verify(String str) {
        if (!str.equals(this.challenge)) {
            return false;
        }
        Signature instance = Signature.getInstance(this.sigAlg.getAlgorithm().getId(), BouncyCastleProvider.PROVIDER_NAME);
        instance.initVerify(this.pubkey);
        instance.update(this.content.getBytes());
        return instance.verify(this.sigBits);
    }

    public void sign(PrivateKey privateKey) {
        sign(privateKey, null);
    }

    public void sign(PrivateKey privateKey, SecureRandom secureRandom) {
        Signature instance = Signature.getInstance(this.sigAlg.getAlgorithm().getId(), BouncyCastleProvider.PROVIDER_NAME);
        if (secureRandom != null) {
            instance.initSign(privateKey, secureRandom);
        } else {
            instance.initSign(privateKey);
        }
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(getKeySpec());
        aSN1EncodableVector.add(new DERIA5String(this.challenge));
        try {
            instance.update(new DERSequence(aSN1EncodableVector).getEncoded(ASN1Encoding.DER));
            this.sigBits = instance.sign();
        } catch (IOException e) {
            throw new SignatureException(e.getMessage());
        }
    }

    private ASN1Primitive getKeySpec() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            byteArrayOutputStream.write(this.pubkey.getEncoded());
            byteArrayOutputStream.close();
            return new ASN1InputStream(new ByteArrayInputStream(byteArrayOutputStream.toByteArray())).readObject();
        } catch (IOException e) {
            throw new InvalidKeySpecException(e.getMessage());
        }
    }

    @Override // org.spongycastle.asn1.ASN1Encodable, org.spongycastle.asn1.ASN1Object
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        ASN1EncodableVector aSN1EncodableVector2 = new ASN1EncodableVector();
        try {
            aSN1EncodableVector2.add(getKeySpec());
        } catch (Exception unused) {
        }
        aSN1EncodableVector2.add(new DERIA5String(this.challenge));
        aSN1EncodableVector.add(new DERSequence(aSN1EncodableVector2));
        aSN1EncodableVector.add(this.sigAlg);
        aSN1EncodableVector.add(new DERBitString(this.sigBits));
        return new DERSequence(aSN1EncodableVector);
    }
}
