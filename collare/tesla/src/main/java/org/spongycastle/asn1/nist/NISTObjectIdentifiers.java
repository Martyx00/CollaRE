package org.spongycastle.asn1.nist;

import org.spongycastle.asn1.ASN1ObjectIdentifier;

public interface NISTObjectIdentifiers {
    public static final ASN1ObjectIdentifier aes = nistAlgorithm.branch("1");
    public static final ASN1ObjectIdentifier dsa_with_sha224;
    public static final ASN1ObjectIdentifier dsa_with_sha256 = sigAlgs.branch("2");
    public static final ASN1ObjectIdentifier dsa_with_sha384 = sigAlgs.branch("3");
    public static final ASN1ObjectIdentifier dsa_with_sha512 = sigAlgs.branch("4");
    public static final ASN1ObjectIdentifier hashAlgs = nistAlgorithm.branch("2");
    public static final ASN1ObjectIdentifier id_aes128_CBC = aes.branch("2");
    public static final ASN1ObjectIdentifier id_aes128_CCM = aes.branch("7");
    public static final ASN1ObjectIdentifier id_aes128_CFB = aes.branch("4");
    public static final ASN1ObjectIdentifier id_aes128_ECB = aes.branch("1");
    public static final ASN1ObjectIdentifier id_aes128_GCM = aes.branch("6");
    public static final ASN1ObjectIdentifier id_aes128_OFB = aes.branch("3");
    public static final ASN1ObjectIdentifier id_aes128_wrap = aes.branch("5");
    public static final ASN1ObjectIdentifier id_aes128_wrap_pad = aes.branch("8");
    public static final ASN1ObjectIdentifier id_aes192_CBC = aes.branch("22");
    public static final ASN1ObjectIdentifier id_aes192_CCM = aes.branch("27");
    public static final ASN1ObjectIdentifier id_aes192_CFB = aes.branch("24");
    public static final ASN1ObjectIdentifier id_aes192_ECB = aes.branch("21");
    public static final ASN1ObjectIdentifier id_aes192_GCM = aes.branch("26");
    public static final ASN1ObjectIdentifier id_aes192_OFB = aes.branch("23");
    public static final ASN1ObjectIdentifier id_aes192_wrap = aes.branch("25");
    public static final ASN1ObjectIdentifier id_aes192_wrap_pad = aes.branch("28");
    public static final ASN1ObjectIdentifier id_aes256_CBC = aes.branch("42");
    public static final ASN1ObjectIdentifier id_aes256_CCM = aes.branch("47");
    public static final ASN1ObjectIdentifier id_aes256_CFB = aes.branch("44");
    public static final ASN1ObjectIdentifier id_aes256_ECB = aes.branch("41");
    public static final ASN1ObjectIdentifier id_aes256_GCM = aes.branch("46");
    public static final ASN1ObjectIdentifier id_aes256_OFB = aes.branch("43");
    public static final ASN1ObjectIdentifier id_aes256_wrap = aes.branch("45");
    public static final ASN1ObjectIdentifier id_aes256_wrap_pad = aes.branch("48");
    public static final ASN1ObjectIdentifier id_dsa_with_sha2;
    public static final ASN1ObjectIdentifier id_dsa_with_sha3_224 = sigAlgs.branch("5");
    public static final ASN1ObjectIdentifier id_dsa_with_sha3_256 = sigAlgs.branch("6");
    public static final ASN1ObjectIdentifier id_dsa_with_sha3_384 = sigAlgs.branch("7");
    public static final ASN1ObjectIdentifier id_dsa_with_sha3_512 = sigAlgs.branch("8");
    public static final ASN1ObjectIdentifier id_ecdsa_with_sha3_224 = sigAlgs.branch("9");
    public static final ASN1ObjectIdentifier id_ecdsa_with_sha3_256 = sigAlgs.branch("10");
    public static final ASN1ObjectIdentifier id_ecdsa_with_sha3_384 = sigAlgs.branch("11");
    public static final ASN1ObjectIdentifier id_ecdsa_with_sha3_512 = sigAlgs.branch("12");
    public static final ASN1ObjectIdentifier id_hmacWithSHA3_224 = hashAlgs.branch("13");
    public static final ASN1ObjectIdentifier id_hmacWithSHA3_256 = hashAlgs.branch("14");
    public static final ASN1ObjectIdentifier id_hmacWithSHA3_384 = hashAlgs.branch("15");
    public static final ASN1ObjectIdentifier id_hmacWithSHA3_512 = hashAlgs.branch("16");
    public static final ASN1ObjectIdentifier id_rsassa_pkcs1_v1_5_with_sha3_224 = sigAlgs.branch("13");
    public static final ASN1ObjectIdentifier id_rsassa_pkcs1_v1_5_with_sha3_256 = sigAlgs.branch("14");
    public static final ASN1ObjectIdentifier id_rsassa_pkcs1_v1_5_with_sha3_384 = sigAlgs.branch("15");
    public static final ASN1ObjectIdentifier id_rsassa_pkcs1_v1_5_with_sha3_512 = sigAlgs.branch("16");
    public static final ASN1ObjectIdentifier id_sha224 = hashAlgs.branch("4");
    public static final ASN1ObjectIdentifier id_sha256 = hashAlgs.branch("1");
    public static final ASN1ObjectIdentifier id_sha384 = hashAlgs.branch("2");
    public static final ASN1ObjectIdentifier id_sha3_224 = hashAlgs.branch("7");
    public static final ASN1ObjectIdentifier id_sha3_256 = hashAlgs.branch("8");
    public static final ASN1ObjectIdentifier id_sha3_384 = hashAlgs.branch("9");
    public static final ASN1ObjectIdentifier id_sha3_512 = hashAlgs.branch("10");
    public static final ASN1ObjectIdentifier id_sha512 = hashAlgs.branch("3");
    public static final ASN1ObjectIdentifier id_sha512_224 = hashAlgs.branch("5");
    public static final ASN1ObjectIdentifier id_sha512_256 = hashAlgs.branch("6");
    public static final ASN1ObjectIdentifier id_shake128 = hashAlgs.branch("11");
    public static final ASN1ObjectIdentifier id_shake256 = hashAlgs.branch("12");
    public static final ASN1ObjectIdentifier nistAlgorithm = new ASN1ObjectIdentifier("2.16.840.1.101.3.4");
    public static final ASN1ObjectIdentifier sigAlgs = nistAlgorithm.branch("3");

    static {
        ASN1ObjectIdentifier aSN1ObjectIdentifier = sigAlgs;
        id_dsa_with_sha2 = aSN1ObjectIdentifier;
        dsa_with_sha224 = aSN1ObjectIdentifier.branch("1");
    }
}
