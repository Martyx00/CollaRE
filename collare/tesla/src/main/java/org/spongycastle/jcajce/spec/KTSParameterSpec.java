package org.spongycastle.jcajce.spec;

import java.security.spec.AlgorithmParameterSpec;
import org.spongycastle.asn1.nist.NISTObjectIdentifiers;
import org.spongycastle.asn1.x509.AlgorithmIdentifier;
import org.spongycastle.asn1.x9.X9ObjectIdentifiers;
import org.spongycastle.util.Arrays;

public class KTSParameterSpec implements AlgorithmParameterSpec {
    private final AlgorithmIdentifier kdfAlgorithm;
    private final int keySizeInBits;
    private byte[] otherInfo;
    private final AlgorithmParameterSpec parameterSpec;
    private final String wrappingKeyAlgorithm;

    public static final class Builder {
        private final String algorithmName;
        private AlgorithmIdentifier kdfAlgorithm;
        private final int keySizeInBits;
        private byte[] otherInfo;
        private AlgorithmParameterSpec parameterSpec;

        public Builder(String str, int i) {
            this(str, i, null);
        }

        public Builder(String str, int i, byte[] bArr) {
            this.algorithmName = str;
            this.keySizeInBits = i;
            this.kdfAlgorithm = new AlgorithmIdentifier(X9ObjectIdentifiers.id_kdf_kdf3, new AlgorithmIdentifier(NISTObjectIdentifiers.id_sha256));
            this.otherInfo = bArr == null ? new byte[0] : Arrays.clone(bArr);
        }

        public Builder withParameterSpec(AlgorithmParameterSpec algorithmParameterSpec) {
            this.parameterSpec = algorithmParameterSpec;
            return this;
        }

        public Builder withKdfAlgorithm(AlgorithmIdentifier algorithmIdentifier) {
            this.kdfAlgorithm = algorithmIdentifier;
            return this;
        }

        public KTSParameterSpec build() {
            return new KTSParameterSpec(this.algorithmName, this.keySizeInBits, this.parameterSpec, this.kdfAlgorithm, this.otherInfo);
        }
    }

    private KTSParameterSpec(String str, int i, AlgorithmParameterSpec algorithmParameterSpec, AlgorithmIdentifier algorithmIdentifier, byte[] bArr) {
        this.wrappingKeyAlgorithm = str;
        this.keySizeInBits = i;
        this.parameterSpec = algorithmParameterSpec;
        this.kdfAlgorithm = algorithmIdentifier;
        this.otherInfo = bArr;
    }

    public String getKeyAlgorithmName() {
        return this.wrappingKeyAlgorithm;
    }

    public int getKeySize() {
        return this.keySizeInBits;
    }

    public AlgorithmParameterSpec getParameterSpec() {
        return this.parameterSpec;
    }

    public AlgorithmIdentifier getKdfAlgorithm() {
        return this.kdfAlgorithm;
    }

    public byte[] getOtherInfo() {
        return Arrays.clone(this.otherInfo);
    }
}
