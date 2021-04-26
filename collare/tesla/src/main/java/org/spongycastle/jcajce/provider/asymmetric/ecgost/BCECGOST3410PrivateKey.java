package org.spongycastle.jcajce.provider.asymmetric.ecgost;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigInteger;
import java.security.interfaces.ECPrivateKey;
import java.security.spec.ECParameterSpec;
import java.security.spec.ECPoint;
import java.util.Enumeration;
import org.spongycastle.asn1.ASN1Encodable;
import org.spongycastle.asn1.ASN1Encoding;
import org.spongycastle.asn1.ASN1Integer;
import org.spongycastle.asn1.ASN1Null;
import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.ASN1OctetString;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.DERBitString;
import org.spongycastle.asn1.DERNull;
import org.spongycastle.asn1.DEROctetString;
import org.spongycastle.asn1.cryptopro.CryptoProObjectIdentifiers;
import org.spongycastle.asn1.cryptopro.ECGOST3410NamedCurves;
import org.spongycastle.asn1.cryptopro.GOST3410PublicKeyAlgParameters;
import org.spongycastle.asn1.pkcs.PrivateKeyInfo;
import org.spongycastle.asn1.x509.AlgorithmIdentifier;
import org.spongycastle.asn1.x509.SubjectPublicKeyInfo;
import org.spongycastle.asn1.x9.X962Parameters;
import org.spongycastle.asn1.x9.X9ECParameters;
import org.spongycastle.crypto.params.ECDomainParameters;
import org.spongycastle.crypto.params.ECPrivateKeyParameters;
import org.spongycastle.jcajce.provider.asymmetric.util.EC5Util;
import org.spongycastle.jcajce.provider.asymmetric.util.ECUtil;
import org.spongycastle.jcajce.provider.asymmetric.util.PKCS12BagAttributeCarrierImpl;
import org.spongycastle.jce.ECGOST3410NamedCurveTable;
import org.spongycastle.jce.interfaces.ECPointEncoder;
import org.spongycastle.jce.interfaces.PKCS12BagAttributeCarrier;
import org.spongycastle.jce.provider.BouncyCastleProvider;
import org.spongycastle.jce.spec.ECNamedCurveParameterSpec;
import org.spongycastle.jce.spec.ECNamedCurveSpec;
import org.spongycastle.jce.spec.ECPrivateKeySpec;
import org.spongycastle.math.ec.ECCurve;
import org.spongycastle.util.Strings;

public class BCECGOST3410PrivateKey implements ECPrivateKey, ECPointEncoder, org.spongycastle.jce.interfaces.ECPrivateKey, PKCS12BagAttributeCarrier {
    static final long serialVersionUID = 7245981689601667138L;
    private String algorithm = "ECGOST3410";
    private transient PKCS12BagAttributeCarrierImpl attrCarrier = new PKCS12BagAttributeCarrierImpl();

    /* renamed from: d  reason: collision with root package name */
    private transient BigInteger f6413d;
    private transient ECParameterSpec ecSpec;
    private transient GOST3410PublicKeyAlgParameters gostParams;
    private transient DERBitString publicKey;
    private boolean withCompression;

    public String getFormat() {
        return "PKCS#8";
    }

    protected BCECGOST3410PrivateKey() {
    }

    public BCECGOST3410PrivateKey(ECPrivateKey eCPrivateKey) {
        this.f6413d = eCPrivateKey.getS();
        this.algorithm = eCPrivateKey.getAlgorithm();
        this.ecSpec = eCPrivateKey.getParams();
    }

    public BCECGOST3410PrivateKey(ECPrivateKeySpec eCPrivateKeySpec) {
        this.f6413d = eCPrivateKeySpec.getD();
        if (eCPrivateKeySpec.getParams() != null) {
            this.ecSpec = EC5Util.convertSpec(EC5Util.convertCurve(eCPrivateKeySpec.getParams().getCurve(), eCPrivateKeySpec.getParams().getSeed()), eCPrivateKeySpec.getParams());
        } else {
            this.ecSpec = null;
        }
    }

    public BCECGOST3410PrivateKey(java.security.spec.ECPrivateKeySpec eCPrivateKeySpec) {
        this.f6413d = eCPrivateKeySpec.getS();
        this.ecSpec = eCPrivateKeySpec.getParams();
    }

    public BCECGOST3410PrivateKey(BCECGOST3410PrivateKey bCECGOST3410PrivateKey) {
        this.f6413d = bCECGOST3410PrivateKey.f6413d;
        this.ecSpec = bCECGOST3410PrivateKey.ecSpec;
        this.withCompression = bCECGOST3410PrivateKey.withCompression;
        this.attrCarrier = bCECGOST3410PrivateKey.attrCarrier;
        this.publicKey = bCECGOST3410PrivateKey.publicKey;
        this.gostParams = bCECGOST3410PrivateKey.gostParams;
    }

    public BCECGOST3410PrivateKey(String str, ECPrivateKeyParameters eCPrivateKeyParameters, BCECGOST3410PublicKey bCECGOST3410PublicKey, ECParameterSpec eCParameterSpec) {
        ECDomainParameters parameters = eCPrivateKeyParameters.getParameters();
        this.algorithm = str;
        this.f6413d = eCPrivateKeyParameters.getD();
        if (eCParameterSpec == null) {
            this.ecSpec = new ECParameterSpec(EC5Util.convertCurve(parameters.getCurve(), parameters.getSeed()), new ECPoint(parameters.getG().getAffineXCoord().toBigInteger(), parameters.getG().getAffineYCoord().toBigInteger()), parameters.getN(), parameters.getH().intValue());
        } else {
            this.ecSpec = eCParameterSpec;
        }
        this.gostParams = bCECGOST3410PublicKey.getGostParams();
        this.publicKey = getPublicKeyDetails(bCECGOST3410PublicKey);
    }

    public BCECGOST3410PrivateKey(String str, ECPrivateKeyParameters eCPrivateKeyParameters, BCECGOST3410PublicKey bCECGOST3410PublicKey, org.spongycastle.jce.spec.ECParameterSpec eCParameterSpec) {
        ECDomainParameters parameters = eCPrivateKeyParameters.getParameters();
        this.algorithm = str;
        this.f6413d = eCPrivateKeyParameters.getD();
        if (eCParameterSpec == null) {
            this.ecSpec = new ECParameterSpec(EC5Util.convertCurve(parameters.getCurve(), parameters.getSeed()), new ECPoint(parameters.getG().getAffineXCoord().toBigInteger(), parameters.getG().getAffineYCoord().toBigInteger()), parameters.getN(), parameters.getH().intValue());
        } else {
            this.ecSpec = new ECParameterSpec(EC5Util.convertCurve(eCParameterSpec.getCurve(), eCParameterSpec.getSeed()), new ECPoint(eCParameterSpec.getG().getAffineXCoord().toBigInteger(), eCParameterSpec.getG().getAffineYCoord().toBigInteger()), eCParameterSpec.getN(), eCParameterSpec.getH().intValue());
        }
        this.gostParams = bCECGOST3410PublicKey.getGostParams();
        this.publicKey = getPublicKeyDetails(bCECGOST3410PublicKey);
    }

    public BCECGOST3410PrivateKey(String str, ECPrivateKeyParameters eCPrivateKeyParameters) {
        this.algorithm = str;
        this.f6413d = eCPrivateKeyParameters.getD();
        this.ecSpec = null;
    }

    BCECGOST3410PrivateKey(PrivateKeyInfo privateKeyInfo) {
        populateFromPrivKeyInfo(privateKeyInfo);
    }

    private void populateFromPrivKeyInfo(PrivateKeyInfo privateKeyInfo) {
        ASN1Primitive aSN1Primitive = privateKeyInfo.getPrivateKeyAlgorithm().getParameters().toASN1Primitive();
        if (!(aSN1Primitive instanceof ASN1Sequence) || !(ASN1Sequence.getInstance(aSN1Primitive).size() == 2 || ASN1Sequence.getInstance(aSN1Primitive).size() == 3)) {
            X962Parameters instance = X962Parameters.getInstance(privateKeyInfo.getPrivateKeyAlgorithm().getParameters());
            if (instance.isNamedCurve()) {
                ASN1ObjectIdentifier instance2 = ASN1ObjectIdentifier.getInstance(instance.getParameters());
                X9ECParameters namedCurveByOid = ECUtil.getNamedCurveByOid(instance2);
                if (namedCurveByOid == null) {
                    ECDomainParameters byOID = ECGOST3410NamedCurves.getByOID(instance2);
                    this.ecSpec = new ECNamedCurveSpec(ECGOST3410NamedCurves.getName(instance2), EC5Util.convertCurve(byOID.getCurve(), byOID.getSeed()), new ECPoint(byOID.getG().getAffineXCoord().toBigInteger(), byOID.getG().getAffineYCoord().toBigInteger()), byOID.getN(), byOID.getH());
                } else {
                    this.ecSpec = new ECNamedCurveSpec(ECUtil.getCurveName(instance2), EC5Util.convertCurve(namedCurveByOid.getCurve(), namedCurveByOid.getSeed()), new ECPoint(namedCurveByOid.getG().getAffineXCoord().toBigInteger(), namedCurveByOid.getG().getAffineYCoord().toBigInteger()), namedCurveByOid.getN(), namedCurveByOid.getH());
                }
            } else if (instance.isImplicitlyCA()) {
                this.ecSpec = null;
            } else {
                X9ECParameters instance3 = X9ECParameters.getInstance(instance.getParameters());
                this.ecSpec = new ECParameterSpec(EC5Util.convertCurve(instance3.getCurve(), instance3.getSeed()), new ECPoint(instance3.getG().getAffineXCoord().toBigInteger(), instance3.getG().getAffineYCoord().toBigInteger()), instance3.getN(), instance3.getH().intValue());
            }
            ASN1Encodable parsePrivateKey = privateKeyInfo.parsePrivateKey();
            if (parsePrivateKey instanceof ASN1Integer) {
                this.f6413d = ASN1Integer.getInstance(parsePrivateKey).getValue();
                return;
            }
            org.spongycastle.asn1.sec.ECPrivateKey instance4 = org.spongycastle.asn1.sec.ECPrivateKey.getInstance(parsePrivateKey);
            this.f6413d = instance4.getKey();
            this.publicKey = instance4.getPublicKey();
            return;
        }
        this.gostParams = GOST3410PublicKeyAlgParameters.getInstance(privateKeyInfo.getPrivateKeyAlgorithm().getParameters());
        ECNamedCurveParameterSpec parameterSpec = ECGOST3410NamedCurveTable.getParameterSpec(ECGOST3410NamedCurves.getName(this.gostParams.getPublicKeyParamSet()));
        this.ecSpec = new ECNamedCurveSpec(ECGOST3410NamedCurves.getName(this.gostParams.getPublicKeyParamSet()), EC5Util.convertCurve(parameterSpec.getCurve(), parameterSpec.getSeed()), new ECPoint(parameterSpec.getG().getAffineXCoord().toBigInteger(), parameterSpec.getG().getAffineYCoord().toBigInteger()), parameterSpec.getN(), parameterSpec.getH());
        ASN1Encodable parsePrivateKey2 = privateKeyInfo.parsePrivateKey();
        if (parsePrivateKey2 instanceof ASN1Integer) {
            this.f6413d = ASN1Integer.getInstance(parsePrivateKey2).getPositiveValue();
            return;
        }
        byte[] octets = ASN1OctetString.getInstance(parsePrivateKey2).getOctets();
        byte[] bArr = new byte[octets.length];
        for (int i = 0; i != octets.length; i++) {
            bArr[i] = octets[(octets.length - 1) - i];
        }
        this.f6413d = new BigInteger(1, bArr);
    }

    public String getAlgorithm() {
        return this.algorithm;
    }

    public byte[] getEncoded() {
        X962Parameters x962Parameters;
        int i;
        org.spongycastle.asn1.sec.ECPrivateKey eCPrivateKey;
        if (this.gostParams != null) {
            byte[] bArr = new byte[32];
            extractBytes(bArr, 0, getS());
            try {
                return new PrivateKeyInfo(new AlgorithmIdentifier(CryptoProObjectIdentifiers.gostR3410_2001, this.gostParams), new DEROctetString(bArr)).getEncoded(ASN1Encoding.DER);
            } catch (IOException unused) {
                return null;
            }
        } else {
            ECParameterSpec eCParameterSpec = this.ecSpec;
            if (eCParameterSpec instanceof ECNamedCurveSpec) {
                ASN1ObjectIdentifier namedCurveOid = ECUtil.getNamedCurveOid(((ECNamedCurveSpec) eCParameterSpec).getName());
                if (namedCurveOid == null) {
                    namedCurveOid = new ASN1ObjectIdentifier(((ECNamedCurveSpec) this.ecSpec).getName());
                }
                x962Parameters = new X962Parameters(namedCurveOid);
                i = ECUtil.getOrderBitLength(BouncyCastleProvider.CONFIGURATION, this.ecSpec.getOrder(), getS());
            } else if (eCParameterSpec == null) {
                x962Parameters = new X962Parameters((ASN1Null) DERNull.INSTANCE);
                i = ECUtil.getOrderBitLength(BouncyCastleProvider.CONFIGURATION, null, getS());
            } else {
                ECCurve convertCurve = EC5Util.convertCurve(eCParameterSpec.getCurve());
                x962Parameters = new X962Parameters(new X9ECParameters(convertCurve, EC5Util.convertPoint(convertCurve, this.ecSpec.getGenerator(), this.withCompression), this.ecSpec.getOrder(), BigInteger.valueOf((long) this.ecSpec.getCofactor()), this.ecSpec.getCurve().getSeed()));
                i = ECUtil.getOrderBitLength(BouncyCastleProvider.CONFIGURATION, this.ecSpec.getOrder(), getS());
            }
            if (this.publicKey != null) {
                eCPrivateKey = new org.spongycastle.asn1.sec.ECPrivateKey(i, getS(), this.publicKey, x962Parameters);
            } else {
                eCPrivateKey = new org.spongycastle.asn1.sec.ECPrivateKey(i, getS(), x962Parameters);
            }
            try {
                return new PrivateKeyInfo(new AlgorithmIdentifier(CryptoProObjectIdentifiers.gostR3410_2001, x962Parameters.toASN1Primitive()), eCPrivateKey.toASN1Primitive()).getEncoded(ASN1Encoding.DER);
            } catch (IOException unused2) {
                return null;
            }
        }
    }

    private void extractBytes(byte[] bArr, int i, BigInteger bigInteger) {
        byte[] byteArray = bigInteger.toByteArray();
        if (byteArray.length < 32) {
            byte[] bArr2 = new byte[32];
            System.arraycopy(byteArray, 0, bArr2, bArr2.length - byteArray.length, byteArray.length);
            byteArray = bArr2;
        }
        for (int i2 = 0; i2 != 32; i2++) {
            bArr[i + i2] = byteArray[(byteArray.length - 1) - i2];
        }
    }

    public ECParameterSpec getParams() {
        return this.ecSpec;
    }

    @Override // org.spongycastle.jce.interfaces.ECKey
    public org.spongycastle.jce.spec.ECParameterSpec getParameters() {
        ECParameterSpec eCParameterSpec = this.ecSpec;
        if (eCParameterSpec == null) {
            return null;
        }
        return EC5Util.convertSpec(eCParameterSpec, this.withCompression);
    }

    /* access modifiers changed from: package-private */
    public org.spongycastle.jce.spec.ECParameterSpec engineGetSpec() {
        ECParameterSpec eCParameterSpec = this.ecSpec;
        if (eCParameterSpec != null) {
            return EC5Util.convertSpec(eCParameterSpec, this.withCompression);
        }
        return BouncyCastleProvider.CONFIGURATION.getEcImplicitlyCa();
    }

    public BigInteger getS() {
        return this.f6413d;
    }

    @Override // org.spongycastle.jce.interfaces.ECPrivateKey
    public BigInteger getD() {
        return this.f6413d;
    }

    @Override // org.spongycastle.jce.interfaces.PKCS12BagAttributeCarrier
    public void setBagAttribute(ASN1ObjectIdentifier aSN1ObjectIdentifier, ASN1Encodable aSN1Encodable) {
        this.attrCarrier.setBagAttribute(aSN1ObjectIdentifier, aSN1Encodable);
    }

    @Override // org.spongycastle.jce.interfaces.PKCS12BagAttributeCarrier
    public ASN1Encodable getBagAttribute(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        return this.attrCarrier.getBagAttribute(aSN1ObjectIdentifier);
    }

    @Override // org.spongycastle.jce.interfaces.PKCS12BagAttributeCarrier
    public Enumeration getBagAttributeKeys() {
        return this.attrCarrier.getBagAttributeKeys();
    }

    @Override // org.spongycastle.jce.interfaces.ECPointEncoder
    public void setPointFormat(String str) {
        this.withCompression = !"UNCOMPRESSED".equalsIgnoreCase(str);
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof BCECGOST3410PrivateKey)) {
            return false;
        }
        BCECGOST3410PrivateKey bCECGOST3410PrivateKey = (BCECGOST3410PrivateKey) obj;
        if (!getD().equals(bCECGOST3410PrivateKey.getD()) || !engineGetSpec().equals(bCECGOST3410PrivateKey.engineGetSpec())) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return getD().hashCode() ^ engineGetSpec().hashCode();
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        String lineSeparator = Strings.lineSeparator();
        stringBuffer.append("EC Private Key");
        stringBuffer.append(lineSeparator);
        stringBuffer.append("             S: ");
        stringBuffer.append(this.f6413d.toString(16));
        stringBuffer.append(lineSeparator);
        return stringBuffer.toString();
    }

    private DERBitString getPublicKeyDetails(BCECGOST3410PublicKey bCECGOST3410PublicKey) {
        try {
            return SubjectPublicKeyInfo.getInstance(ASN1Primitive.fromByteArray(bCECGOST3410PublicKey.getEncoded())).getPublicKeyData();
        } catch (IOException unused) {
            return null;
        }
    }

    private void readObject(ObjectInputStream objectInputStream) {
        objectInputStream.defaultReadObject();
        populateFromPrivKeyInfo(PrivateKeyInfo.getInstance(ASN1Primitive.fromByteArray((byte[]) objectInputStream.readObject())));
        this.attrCarrier = new PKCS12BagAttributeCarrierImpl();
    }

    private void writeObject(ObjectOutputStream objectOutputStream) {
        objectOutputStream.defaultWriteObject();
        objectOutputStream.writeObject(getEncoded());
    }
}
