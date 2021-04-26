package org.spongycastle.pqc.jcajce.provider.newhope;

import java.security.InvalidAlgorithmParameterException;
import java.security.Key;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;
import org.spongycastle.crypto.params.AsymmetricKeyParameter;
import org.spongycastle.jcajce.provider.asymmetric.util.BaseAgreementSpi;
import org.spongycastle.pqc.crypto.ExchangePair;
import org.spongycastle.pqc.crypto.newhope.NHAgreement;
import org.spongycastle.pqc.crypto.newhope.NHExchangePairGenerator;
import org.spongycastle.pqc.crypto.newhope.NHPublicKeyParameters;
import org.spongycastle.util.Arrays;

public class KeyAgreementSpi extends BaseAgreementSpi {
    private NHAgreement agreement;
    private NHExchangePairGenerator exchangePairGenerator;
    private BCNHPublicKey otherPartyKey;
    private byte[] shared;

    public KeyAgreementSpi() {
        super("NH", null);
    }

    /* access modifiers changed from: protected */
    @Override // javax.crypto.KeyAgreementSpi
    public void engineInit(Key key, SecureRandom secureRandom) {
        if (key != null) {
            this.agreement = new NHAgreement();
            this.agreement.init(((BCNHPrivateKey) key).getKeyParams());
            return;
        }
        this.exchangePairGenerator = new NHExchangePairGenerator(secureRandom);
    }

    /* access modifiers changed from: protected */
    @Override // javax.crypto.KeyAgreementSpi
    public void engineInit(Key key, AlgorithmParameterSpec algorithmParameterSpec, SecureRandom secureRandom) {
        throw new InvalidAlgorithmParameterException("NewHope does not require parameters");
    }

    /* access modifiers changed from: protected */
    @Override // javax.crypto.KeyAgreementSpi
    public Key engineDoPhase(Key key, boolean z) {
        if (z) {
            this.otherPartyKey = (BCNHPublicKey) key;
            NHExchangePairGenerator nHExchangePairGenerator = this.exchangePairGenerator;
            if (nHExchangePairGenerator != null) {
                ExchangePair GenerateExchange = nHExchangePairGenerator.GenerateExchange((AsymmetricKeyParameter) this.otherPartyKey.getKeyParams());
                this.shared = GenerateExchange.getSharedValue();
                return new BCNHPublicKey((NHPublicKeyParameters) GenerateExchange.getPublicKey());
            }
            this.shared = this.agreement.calculateAgreement(this.otherPartyKey.getKeyParams());
            return null;
        }
        throw new IllegalStateException("NewHope can only be between two parties.");
    }

    /* access modifiers changed from: protected */
    @Override // javax.crypto.KeyAgreementSpi, org.spongycastle.jcajce.provider.asymmetric.util.BaseAgreementSpi
    public byte[] engineGenerateSecret() {
        byte[] clone = Arrays.clone(this.shared);
        Arrays.fill(this.shared, (byte) 0);
        return clone;
    }

    /* access modifiers changed from: protected */
    @Override // javax.crypto.KeyAgreementSpi, org.spongycastle.jcajce.provider.asymmetric.util.BaseAgreementSpi
    public int engineGenerateSecret(byte[] bArr, int i) {
        byte[] bArr2 = this.shared;
        System.arraycopy(bArr2, 0, bArr, i, bArr2.length);
        Arrays.fill(this.shared, (byte) 0);
        return this.shared.length;
    }

    /* access modifiers changed from: protected */
    @Override // org.spongycastle.jcajce.provider.asymmetric.util.BaseAgreementSpi
    public byte[] calcSecret() {
        return engineGenerateSecret();
    }
}
