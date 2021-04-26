package org.spongycastle.jcajce.provider.asymmetric.ec;

import java.math.BigInteger;
import java.security.PublicKey;
import java.security.spec.ECGenParameterSpec;
import java.security.spec.ECParameterSpec;
import org.spongycastle.asn1.ASN1Null;
import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.DERNull;
import org.spongycastle.asn1.x9.X962Parameters;
import org.spongycastle.asn1.x9.X9ECParameters;
import org.spongycastle.crypto.params.AsymmetricKeyParameter;
import org.spongycastle.jcajce.provider.asymmetric.util.EC5Util;
import org.spongycastle.jcajce.provider.asymmetric.util.ECUtil;
import org.spongycastle.jce.spec.ECNamedCurveSpec;
import org.spongycastle.math.ec.ECCurve;

class ECUtils {
    ECUtils() {
    }

    static AsymmetricKeyParameter generatePublicKeyParameter(PublicKey publicKey) {
        return publicKey instanceof BCECPublicKey ? ((BCECPublicKey) publicKey).engineGetKeyParameters() : ECUtil.generatePublicKeyParameter(publicKey);
    }

    static X9ECParameters getDomainParametersFromGenSpec(ECGenParameterSpec eCGenParameterSpec) {
        return getDomainParametersFromName(eCGenParameterSpec.getName());
    }

    static X9ECParameters getDomainParametersFromName(String str) {
        try {
            if (str.charAt(0) >= '0' && str.charAt(0) <= '2') {
                return ECUtil.getNamedCurveByOid(new ASN1ObjectIdentifier(str));
            }
            if (str.indexOf(32) > 0) {
                return ECUtil.getNamedCurveByName(str.substring(str.indexOf(32) + 1));
            }
            return ECUtil.getNamedCurveByName(str);
        } catch (IllegalArgumentException unused) {
            return ECUtil.getNamedCurveByName(str);
        }
    }

    static X962Parameters getDomainParametersFromName(ECParameterSpec eCParameterSpec, boolean z) {
        if (eCParameterSpec instanceof ECNamedCurveSpec) {
            ECNamedCurveSpec eCNamedCurveSpec = (ECNamedCurveSpec) eCParameterSpec;
            ASN1ObjectIdentifier namedCurveOid = ECUtil.getNamedCurveOid(eCNamedCurveSpec.getName());
            if (namedCurveOid == null) {
                namedCurveOid = new ASN1ObjectIdentifier(eCNamedCurveSpec.getName());
            }
            return new X962Parameters(namedCurveOid);
        } else if (eCParameterSpec == null) {
            return new X962Parameters((ASN1Null) DERNull.INSTANCE);
        } else {
            ECCurve convertCurve = EC5Util.convertCurve(eCParameterSpec.getCurve());
            return new X962Parameters(new X9ECParameters(convertCurve, EC5Util.convertPoint(convertCurve, eCParameterSpec.getGenerator(), z), eCParameterSpec.getOrder(), BigInteger.valueOf((long) eCParameterSpec.getCofactor()), eCParameterSpec.getCurve().getSeed()));
        }
    }
}
