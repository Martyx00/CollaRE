package org.spongycastle.jce;

import java.io.UnsupportedEncodingException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.Provider;
import java.security.PublicKey;
import java.security.Security;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.cryptopro.CryptoProObjectIdentifiers;
import org.spongycastle.asn1.pkcs.PrivateKeyInfo;
import org.spongycastle.asn1.x509.AlgorithmIdentifier;
import org.spongycastle.asn1.x509.SubjectPublicKeyInfo;
import org.spongycastle.asn1.x9.X962Parameters;
import org.spongycastle.asn1.x9.X9ECParameters;
import org.spongycastle.asn1.x9.X9ObjectIdentifiers;
import org.spongycastle.jcajce.provider.asymmetric.util.ECUtil;
import org.spongycastle.jce.provider.BouncyCastleProvider;

public class ECKeyUtil {
    public static PublicKey publicToExplicitParameters(PublicKey publicKey, String str) {
        Provider provider = Security.getProvider(str);
        if (provider != null) {
            return publicToExplicitParameters(publicKey, provider);
        }
        throw new NoSuchProviderException("cannot find provider: " + str);
    }

    public static PublicKey publicToExplicitParameters(PublicKey publicKey, Provider provider) {
        X9ECParameters x9ECParameters;
        try {
            SubjectPublicKeyInfo instance = SubjectPublicKeyInfo.getInstance(ASN1Primitive.fromByteArray(publicKey.getEncoded()));
            if (!instance.getAlgorithmId().getAlgorithm().equals(CryptoProObjectIdentifiers.gostR3410_2001)) {
                X962Parameters instance2 = X962Parameters.getInstance(instance.getAlgorithmId().getParameters());
                if (instance2.isNamedCurve()) {
                    X9ECParameters namedCurveByOid = ECUtil.getNamedCurveByOid(ASN1ObjectIdentifier.getInstance(instance2.getParameters()));
                    x9ECParameters = new X9ECParameters(namedCurveByOid.getCurve(), namedCurveByOid.getG(), namedCurveByOid.getN(), namedCurveByOid.getH());
                } else if (!instance2.isImplicitlyCA()) {
                    return publicKey;
                } else {
                    x9ECParameters = new X9ECParameters(BouncyCastleProvider.CONFIGURATION.getEcImplicitlyCa().getCurve(), BouncyCastleProvider.CONFIGURATION.getEcImplicitlyCa().getG(), BouncyCastleProvider.CONFIGURATION.getEcImplicitlyCa().getN(), BouncyCastleProvider.CONFIGURATION.getEcImplicitlyCa().getH());
                }
                return KeyFactory.getInstance(publicKey.getAlgorithm(), provider).generatePublic(new X509EncodedKeySpec(new SubjectPublicKeyInfo(new AlgorithmIdentifier(X9ObjectIdentifiers.id_ecPublicKey, new X962Parameters(x9ECParameters)), instance.getPublicKeyData().getBytes()).getEncoded()));
            }
            throw new IllegalArgumentException("cannot convert GOST key to explicit parameters.");
        } catch (IllegalArgumentException e) {
            throw e;
        } catch (NoSuchAlgorithmException e2) {
            throw e2;
        } catch (Exception e3) {
            throw new UnexpectedException(e3);
        }
    }

    public static PrivateKey privateToExplicitParameters(PrivateKey privateKey, String str) {
        Provider provider = Security.getProvider(str);
        if (provider != null) {
            return privateToExplicitParameters(privateKey, provider);
        }
        throw new NoSuchProviderException("cannot find provider: " + str);
    }

    public static PrivateKey privateToExplicitParameters(PrivateKey privateKey, Provider provider) {
        X9ECParameters x9ECParameters;
        try {
            PrivateKeyInfo instance = PrivateKeyInfo.getInstance(ASN1Primitive.fromByteArray(privateKey.getEncoded()));
            if (!instance.getAlgorithmId().getAlgorithm().equals(CryptoProObjectIdentifiers.gostR3410_2001)) {
                X962Parameters instance2 = X962Parameters.getInstance(instance.getAlgorithmId().getParameters());
                if (instance2.isNamedCurve()) {
                    X9ECParameters namedCurveByOid = ECUtil.getNamedCurveByOid(ASN1ObjectIdentifier.getInstance(instance2.getParameters()));
                    x9ECParameters = new X9ECParameters(namedCurveByOid.getCurve(), namedCurveByOid.getG(), namedCurveByOid.getN(), namedCurveByOid.getH());
                } else if (!instance2.isImplicitlyCA()) {
                    return privateKey;
                } else {
                    x9ECParameters = new X9ECParameters(BouncyCastleProvider.CONFIGURATION.getEcImplicitlyCa().getCurve(), BouncyCastleProvider.CONFIGURATION.getEcImplicitlyCa().getG(), BouncyCastleProvider.CONFIGURATION.getEcImplicitlyCa().getN(), BouncyCastleProvider.CONFIGURATION.getEcImplicitlyCa().getH());
                }
                return KeyFactory.getInstance(privateKey.getAlgorithm(), provider).generatePrivate(new PKCS8EncodedKeySpec(new PrivateKeyInfo(new AlgorithmIdentifier(X9ObjectIdentifiers.id_ecPublicKey, new X962Parameters(x9ECParameters)), instance.parsePrivateKey()).getEncoded()));
            }
            throw new UnsupportedEncodingException("cannot convert GOST key to explicit parameters.");
        } catch (IllegalArgumentException e) {
            throw e;
        } catch (NoSuchAlgorithmException e2) {
            throw e2;
        } catch (Exception e3) {
            throw new UnexpectedException(e3);
        }
    }

    /* access modifiers changed from: private */
    public static class UnexpectedException extends RuntimeException {
        private Throwable cause;

        UnexpectedException(Throwable th) {
            super(th.toString());
            this.cause = th;
        }

        public Throwable getCause() {
            return this.cause;
        }
    }
}
