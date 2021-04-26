package org.spongycastle.pqc.jcajce.provider;

import java.security.AccessController;
import java.security.PrivateKey;
import java.security.PrivilegedAction;
import java.security.Provider;
import java.security.PublicKey;
import java.util.HashMap;
import java.util.Map;
import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.pkcs.PrivateKeyInfo;
import org.spongycastle.asn1.x509.SubjectPublicKeyInfo;
import org.spongycastle.jcajce.provider.config.ConfigurableProvider;
import org.spongycastle.jcajce.provider.config.ProviderConfiguration;
import org.spongycastle.jcajce.provider.util.AlgorithmProvider;
import org.spongycastle.jcajce.provider.util.AsymmetricKeyInfoConverter;

public class BouncyCastlePQCProvider extends Provider implements ConfigurableProvider {
    private static final String[] ALGORITHMS = {"Rainbow", "McEliece", "SPHINCS", "NH"};
    private static final String ALGORITHM_PACKAGE = "org.spongycastle.pqc.jcajce.provider.";
    public static final ProviderConfiguration CONFIGURATION = null;
    public static String PROVIDER_NAME = "BCPQC";
    private static String info = "BouncyCastle Post-Quantum Security Provider v1.56";
    private static final Map keyInfoConverters = new HashMap();

    public BouncyCastlePQCProvider() {
        super(PROVIDER_NAME, 1.56d, info);
        AccessController.doPrivileged(new PrivilegedAction() {
            /* class org.spongycastle.pqc.jcajce.provider.BouncyCastlePQCProvider.AnonymousClass1 */

            @Override // java.security.PrivilegedAction
            public Object run() {
                BouncyCastlePQCProvider.this.setup();
                return null;
            }
        });
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setup() {
        loadAlgorithms(ALGORITHM_PACKAGE, ALGORITHMS);
    }

    private void loadAlgorithms(String str, String[] strArr) {
        for (int i = 0; i != strArr.length; i++) {
            Class<?> cls = null;
            try {
                ClassLoader classLoader = getClass().getClassLoader();
                if (classLoader != null) {
                    cls = classLoader.loadClass(str + strArr[i] + "$Mappings");
                } else {
                    cls = Class.forName(str + strArr[i] + "$Mappings");
                }
            } catch (ClassNotFoundException unused) {
            }
            if (cls != null) {
                try {
                    ((AlgorithmProvider) cls.newInstance()).configure(this);
                } catch (Exception e) {
                    throw new InternalError("cannot create instance of " + str + strArr[i] + "$Mappings : " + e);
                }
            }
        }
    }

    @Override // org.spongycastle.jcajce.provider.config.ConfigurableProvider
    public void setParameter(String str, Object obj) {
        synchronized (CONFIGURATION) {
        }
    }

    @Override // org.spongycastle.jcajce.provider.config.ConfigurableProvider
    public boolean hasAlgorithm(String str, String str2) {
        if (!containsKey(str + "." + str2)) {
            StringBuilder sb = new StringBuilder();
            sb.append("Alg.Alias.");
            sb.append(str);
            sb.append(".");
            sb.append(str2);
            return containsKey(sb.toString());
        }
    }

    @Override // org.spongycastle.jcajce.provider.config.ConfigurableProvider
    public void addAlgorithm(String str, String str2) {
        if (!containsKey(str)) {
            put(str, str2);
            return;
        }
        throw new IllegalStateException("duplicate provider key (" + str + ") found");
    }

    @Override // org.spongycastle.jcajce.provider.config.ConfigurableProvider
    public void addAlgorithm(String str, ASN1ObjectIdentifier aSN1ObjectIdentifier, String str2) {
        if (containsKey(str + "." + str2)) {
            addAlgorithm(str + "." + aSN1ObjectIdentifier, str2);
            addAlgorithm(str + ".OID." + aSN1ObjectIdentifier, str2);
            return;
        }
        throw new IllegalStateException("primary key (" + str + "." + str2 + ") not found");
    }

    @Override // org.spongycastle.jcajce.provider.config.ConfigurableProvider
    public void addKeyInfoConverter(ASN1ObjectIdentifier aSN1ObjectIdentifier, AsymmetricKeyInfoConverter asymmetricKeyInfoConverter) {
        synchronized (keyInfoConverters) {
            keyInfoConverters.put(aSN1ObjectIdentifier, asymmetricKeyInfoConverter);
        }
    }

    private static AsymmetricKeyInfoConverter getAsymmetricKeyInfoConverter(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        AsymmetricKeyInfoConverter asymmetricKeyInfoConverter;
        synchronized (keyInfoConverters) {
            asymmetricKeyInfoConverter = (AsymmetricKeyInfoConverter) keyInfoConverters.get(aSN1ObjectIdentifier);
        }
        return asymmetricKeyInfoConverter;
    }

    public static PublicKey getPublicKey(SubjectPublicKeyInfo subjectPublicKeyInfo) {
        AsymmetricKeyInfoConverter asymmetricKeyInfoConverter = getAsymmetricKeyInfoConverter(subjectPublicKeyInfo.getAlgorithm().getAlgorithm());
        if (asymmetricKeyInfoConverter == null) {
            return null;
        }
        return asymmetricKeyInfoConverter.generatePublic(subjectPublicKeyInfo);
    }

    public static PrivateKey getPrivateKey(PrivateKeyInfo privateKeyInfo) {
        AsymmetricKeyInfoConverter asymmetricKeyInfoConverter = getAsymmetricKeyInfoConverter(privateKeyInfo.getPrivateKeyAlgorithm().getAlgorithm());
        if (asymmetricKeyInfoConverter == null) {
            return null;
        }
        return asymmetricKeyInfoConverter.generatePrivate(privateKeyInfo);
    }
}
