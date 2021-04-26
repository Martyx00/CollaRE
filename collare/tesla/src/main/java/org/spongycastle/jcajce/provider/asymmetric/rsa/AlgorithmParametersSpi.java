package org.spongycastle.jcajce.provider.asymmetric.rsa;

import java.io.IOException;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.InvalidParameterSpecException;
import java.security.spec.MGF1ParameterSpec;
import java.security.spec.PSSParameterSpec;
import javax.crypto.spec.OAEPParameterSpec;
import javax.crypto.spec.PSource;
import org.spongycastle.asn1.ASN1Encoding;
import org.spongycastle.asn1.ASN1Integer;
import org.spongycastle.asn1.ASN1OctetString;
import org.spongycastle.asn1.DERNull;
import org.spongycastle.asn1.DEROctetString;
import org.spongycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.spongycastle.asn1.pkcs.RSAESOAEPparams;
import org.spongycastle.asn1.pkcs.RSASSAPSSparams;
import org.spongycastle.asn1.x509.AlgorithmIdentifier;
import org.spongycastle.jcajce.provider.util.DigestFactory;
import org.spongycastle.jcajce.util.MessageDigestUtils;

public abstract class AlgorithmParametersSpi extends java.security.AlgorithmParametersSpi {
    /* access modifiers changed from: protected */
    public abstract AlgorithmParameterSpec localEngineGetParameterSpec(Class cls);

    /* access modifiers changed from: protected */
    public boolean isASN1FormatString(String str) {
        return str == null || str.equals("ASN.1");
    }

    /* access modifiers changed from: protected */
    @Override // java.security.AlgorithmParametersSpi
    public AlgorithmParameterSpec engineGetParameterSpec(Class cls) {
        if (cls != null) {
            return localEngineGetParameterSpec(cls);
        }
        throw new NullPointerException("argument to getParameterSpec must not be null");
    }

    public static class OAEP extends AlgorithmParametersSpi {
        OAEPParameterSpec currentSpec;

        /* access modifiers changed from: protected */
        public String engineToString() {
            return "OAEP Parameters";
        }

        /* access modifiers changed from: protected */
        @Override // java.security.AlgorithmParametersSpi
        public byte[] engineGetEncoded() {
            try {
                return new RSAESOAEPparams(new AlgorithmIdentifier(DigestFactory.getOID(this.currentSpec.getDigestAlgorithm()), DERNull.INSTANCE), new AlgorithmIdentifier(PKCSObjectIdentifiers.id_mgf1, new AlgorithmIdentifier(DigestFactory.getOID(((MGF1ParameterSpec) this.currentSpec.getMGFParameters()).getDigestAlgorithm()), DERNull.INSTANCE)), new AlgorithmIdentifier(PKCSObjectIdentifiers.id_pSpecified, new DEROctetString(((PSource.PSpecified) this.currentSpec.getPSource()).getValue()))).getEncoded(ASN1Encoding.DER);
            } catch (IOException unused) {
                throw new RuntimeException("Error encoding OAEPParameters");
            }
        }

        /* access modifiers changed from: protected */
        @Override // java.security.AlgorithmParametersSpi
        public byte[] engineGetEncoded(String str) {
            if (isASN1FormatString(str) || str.equalsIgnoreCase("X.509")) {
                return engineGetEncoded();
            }
            return null;
        }

        /* access modifiers changed from: protected */
        @Override // org.spongycastle.jcajce.provider.asymmetric.rsa.AlgorithmParametersSpi
        public AlgorithmParameterSpec localEngineGetParameterSpec(Class cls) {
            if (cls == OAEPParameterSpec.class || cls == AlgorithmParameterSpec.class) {
                return this.currentSpec;
            }
            throw new InvalidParameterSpecException("unknown parameter spec passed to OAEP parameters object.");
        }

        /* access modifiers changed from: protected */
        @Override // java.security.AlgorithmParametersSpi
        public void engineInit(AlgorithmParameterSpec algorithmParameterSpec) {
            if (algorithmParameterSpec instanceof OAEPParameterSpec) {
                this.currentSpec = (OAEPParameterSpec) algorithmParameterSpec;
                return;
            }
            throw new InvalidParameterSpecException("OAEPParameterSpec required to initialise an OAEP algorithm parameters object");
        }

        /* access modifiers changed from: protected */
        @Override // java.security.AlgorithmParametersSpi
        public void engineInit(byte[] bArr) {
            try {
                RSAESOAEPparams instance = RSAESOAEPparams.getInstance(bArr);
                if (instance.getMaskGenAlgorithm().getAlgorithm().equals(PKCSObjectIdentifiers.id_mgf1)) {
                    this.currentSpec = new OAEPParameterSpec(MessageDigestUtils.getDigestName(instance.getHashAlgorithm().getAlgorithm()), OAEPParameterSpec.DEFAULT.getMGFAlgorithm(), new MGF1ParameterSpec(MessageDigestUtils.getDigestName(AlgorithmIdentifier.getInstance(instance.getMaskGenAlgorithm().getParameters()).getAlgorithm())), new PSource.PSpecified(ASN1OctetString.getInstance(instance.getPSourceAlgorithm().getParameters()).getOctets()));
                    return;
                }
                throw new IOException("unknown mask generation function: " + instance.getMaskGenAlgorithm().getAlgorithm());
            } catch (ClassCastException unused) {
                throw new IOException("Not a valid OAEP Parameter encoding.");
            } catch (ArrayIndexOutOfBoundsException unused2) {
                throw new IOException("Not a valid OAEP Parameter encoding.");
            }
        }

        /* access modifiers changed from: protected */
        @Override // java.security.AlgorithmParametersSpi
        public void engineInit(byte[] bArr, String str) {
            if (str.equalsIgnoreCase("X.509") || str.equalsIgnoreCase("ASN.1")) {
                engineInit(bArr);
                return;
            }
            throw new IOException("Unknown parameter format " + str);
        }
    }

    public static class PSS extends AlgorithmParametersSpi {
        PSSParameterSpec currentSpec;

        /* access modifiers changed from: protected */
        public String engineToString() {
            return "PSS Parameters";
        }

        /* access modifiers changed from: protected */
        @Override // java.security.AlgorithmParametersSpi
        public byte[] engineGetEncoded() {
            PSSParameterSpec pSSParameterSpec = this.currentSpec;
            return new RSASSAPSSparams(new AlgorithmIdentifier(DigestFactory.getOID(pSSParameterSpec.getDigestAlgorithm()), DERNull.INSTANCE), new AlgorithmIdentifier(PKCSObjectIdentifiers.id_mgf1, new AlgorithmIdentifier(DigestFactory.getOID(((MGF1ParameterSpec) pSSParameterSpec.getMGFParameters()).getDigestAlgorithm()), DERNull.INSTANCE)), new ASN1Integer((long) pSSParameterSpec.getSaltLength()), new ASN1Integer((long) pSSParameterSpec.getTrailerField())).getEncoded(ASN1Encoding.DER);
        }

        /* access modifiers changed from: protected */
        @Override // java.security.AlgorithmParametersSpi
        public byte[] engineGetEncoded(String str) {
            if (str.equalsIgnoreCase("X.509") || str.equalsIgnoreCase("ASN.1")) {
                return engineGetEncoded();
            }
            return null;
        }

        /* access modifiers changed from: protected */
        @Override // org.spongycastle.jcajce.provider.asymmetric.rsa.AlgorithmParametersSpi
        public AlgorithmParameterSpec localEngineGetParameterSpec(Class cls) {
            PSSParameterSpec pSSParameterSpec;
            if (cls == PSSParameterSpec.class && (pSSParameterSpec = this.currentSpec) != null) {
                return pSSParameterSpec;
            }
            throw new InvalidParameterSpecException("unknown parameter spec passed to PSS parameters object.");
        }

        /* access modifiers changed from: protected */
        @Override // java.security.AlgorithmParametersSpi
        public void engineInit(AlgorithmParameterSpec algorithmParameterSpec) {
            if (algorithmParameterSpec instanceof PSSParameterSpec) {
                this.currentSpec = (PSSParameterSpec) algorithmParameterSpec;
                return;
            }
            throw new InvalidParameterSpecException("PSSParameterSpec required to initialise an PSS algorithm parameters object");
        }

        /* access modifiers changed from: protected */
        @Override // java.security.AlgorithmParametersSpi
        public void engineInit(byte[] bArr) {
            try {
                RSASSAPSSparams instance = RSASSAPSSparams.getInstance(bArr);
                if (instance.getMaskGenAlgorithm().getAlgorithm().equals(PKCSObjectIdentifiers.id_mgf1)) {
                    this.currentSpec = new PSSParameterSpec(MessageDigestUtils.getDigestName(instance.getHashAlgorithm().getAlgorithm()), PSSParameterSpec.DEFAULT.getMGFAlgorithm(), new MGF1ParameterSpec(MessageDigestUtils.getDigestName(AlgorithmIdentifier.getInstance(instance.getMaskGenAlgorithm().getParameters()).getAlgorithm())), instance.getSaltLength().intValue(), instance.getTrailerField().intValue());
                    return;
                }
                throw new IOException("unknown mask generation function: " + instance.getMaskGenAlgorithm().getAlgorithm());
            } catch (ClassCastException unused) {
                throw new IOException("Not a valid PSS Parameter encoding.");
            } catch (ArrayIndexOutOfBoundsException unused2) {
                throw new IOException("Not a valid PSS Parameter encoding.");
            }
        }

        /* access modifiers changed from: protected */
        @Override // java.security.AlgorithmParametersSpi
        public void engineInit(byte[] bArr, String str) {
            if (isASN1FormatString(str) || str.equalsIgnoreCase("X.509")) {
                engineInit(bArr);
                return;
            }
            throw new IOException("Unknown parameter format " + str);
        }
    }
}
