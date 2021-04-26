package org.spongycastle.crypto.tls;

import org.spongycastle.crypto.AsymmetricBlockCipher;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.Digest;
import org.spongycastle.crypto.Signer;
import org.spongycastle.crypto.digests.NullDigest;
import org.spongycastle.crypto.encodings.PKCS1Encoding;
import org.spongycastle.crypto.engines.RSABlindedEngine;
import org.spongycastle.crypto.params.AsymmetricKeyParameter;
import org.spongycastle.crypto.params.ParametersWithRandom;
import org.spongycastle.crypto.params.RSAKeyParameters;
import org.spongycastle.crypto.signers.GenericSigner;
import org.spongycastle.crypto.signers.RSADigestSigner;

public class TlsRSASigner extends AbstractTlsSigner {
    @Override // org.spongycastle.crypto.tls.TlsSigner
    public byte[] generateRawSignature(SignatureAndHashAlgorithm signatureAndHashAlgorithm, AsymmetricKeyParameter asymmetricKeyParameter, byte[] bArr) {
        Signer makeSigner = makeSigner(signatureAndHashAlgorithm, true, true, new ParametersWithRandom(asymmetricKeyParameter, this.context.getSecureRandom()));
        makeSigner.update(bArr, 0, bArr.length);
        return makeSigner.generateSignature();
    }

    @Override // org.spongycastle.crypto.tls.TlsSigner
    public boolean verifyRawSignature(SignatureAndHashAlgorithm signatureAndHashAlgorithm, byte[] bArr, AsymmetricKeyParameter asymmetricKeyParameter, byte[] bArr2) {
        Signer makeSigner = makeSigner(signatureAndHashAlgorithm, true, false, asymmetricKeyParameter);
        makeSigner.update(bArr2, 0, bArr2.length);
        return makeSigner.verifySignature(bArr);
    }

    @Override // org.spongycastle.crypto.tls.TlsSigner
    public Signer createSigner(SignatureAndHashAlgorithm signatureAndHashAlgorithm, AsymmetricKeyParameter asymmetricKeyParameter) {
        return makeSigner(signatureAndHashAlgorithm, false, true, new ParametersWithRandom(asymmetricKeyParameter, this.context.getSecureRandom()));
    }

    @Override // org.spongycastle.crypto.tls.TlsSigner
    public Signer createVerifyer(SignatureAndHashAlgorithm signatureAndHashAlgorithm, AsymmetricKeyParameter asymmetricKeyParameter) {
        return makeSigner(signatureAndHashAlgorithm, false, false, asymmetricKeyParameter);
    }

    @Override // org.spongycastle.crypto.tls.TlsSigner
    public boolean isValidPublicKey(AsymmetricKeyParameter asymmetricKeyParameter) {
        return (asymmetricKeyParameter instanceof RSAKeyParameters) && !asymmetricKeyParameter.isPrivate();
    }

    /* access modifiers changed from: protected */
    public Signer makeSigner(SignatureAndHashAlgorithm signatureAndHashAlgorithm, boolean z, boolean z2, CipherParameters cipherParameters) {
        Digest digest;
        Signer signer;
        if ((signatureAndHashAlgorithm != null) != TlsUtils.isTLSv12(this.context)) {
            throw new IllegalStateException();
        } else if (signatureAndHashAlgorithm == null || signatureAndHashAlgorithm.getSignature() == 1) {
            if (z) {
                digest = new NullDigest();
            } else if (signatureAndHashAlgorithm == null) {
                digest = new CombinedHash();
            } else {
                digest = TlsUtils.createHash(signatureAndHashAlgorithm.getHash());
            }
            if (signatureAndHashAlgorithm != null) {
                signer = new RSADigestSigner(digest, TlsUtils.getOIDForHashAlgorithm(signatureAndHashAlgorithm.getHash()));
            } else {
                signer = new GenericSigner(createRSAImpl(), digest);
            }
            signer.init(z2, cipherParameters);
            return signer;
        } else {
            throw new IllegalStateException();
        }
    }

    /* access modifiers changed from: protected */
    public AsymmetricBlockCipher createRSAImpl() {
        return new PKCS1Encoding(new RSABlindedEngine());
    }
}
