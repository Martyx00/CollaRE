package org.spongycastle.jcajce.util;

import java.security.AlgorithmParameterGenerator;
import java.security.AlgorithmParameters;
import java.security.KeyFactory;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.cert.CertificateFactory;
import javax.crypto.Cipher;
import javax.crypto.KeyAgreement;
import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKeyFactory;

public interface JcaJceHelper {
    AlgorithmParameterGenerator createAlgorithmParameterGenerator(String str);

    AlgorithmParameters createAlgorithmParameters(String str);

    CertificateFactory createCertificateFactory(String str);

    Cipher createCipher(String str);

    MessageDigest createDigest(String str);

    KeyAgreement createKeyAgreement(String str);

    KeyFactory createKeyFactory(String str);

    KeyGenerator createKeyGenerator(String str);

    KeyPairGenerator createKeyPairGenerator(String str);

    Mac createMac(String str);

    SecretKeyFactory createSecretKeyFactory(String str);

    SecureRandom createSecureRandom(String str);

    Signature createSignature(String str);
}
