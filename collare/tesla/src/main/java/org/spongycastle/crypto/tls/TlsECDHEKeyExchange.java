package org.spongycastle.crypto.tls;

import java.io.InputStream;
import java.util.Vector;
import org.spongycastle.crypto.Digest;
import org.spongycastle.crypto.Signer;
import org.spongycastle.crypto.params.ECDomainParameters;
import org.spongycastle.util.io.TeeInputStream;

public class TlsECDHEKeyExchange extends TlsECDHKeyExchange {
    protected TlsSignerCredentials serverCredentials = null;

    public TlsECDHEKeyExchange(int i, Vector vector, int[] iArr, short[] sArr, short[] sArr2) {
        super(i, vector, iArr, sArr, sArr2);
    }

    @Override // org.spongycastle.crypto.tls.TlsKeyExchange, org.spongycastle.crypto.tls.AbstractTlsKeyExchange
    public void processServerCredentials(TlsCredentials tlsCredentials) {
        if (tlsCredentials instanceof TlsSignerCredentials) {
            processServerCertificate(tlsCredentials.getCertificate());
            this.serverCredentials = (TlsSignerCredentials) tlsCredentials;
            return;
        }
        throw new TlsFatalAlert(80);
    }

    @Override // org.spongycastle.crypto.tls.TlsECDHKeyExchange, org.spongycastle.crypto.tls.TlsKeyExchange, org.spongycastle.crypto.tls.AbstractTlsKeyExchange
    public byte[] generateServerKeyExchange() {
        DigestInputBuffer digestInputBuffer = new DigestInputBuffer();
        this.ecAgreePrivateKey = TlsECCUtils.generateEphemeralServerKeyExchange(this.context.getSecureRandom(), this.namedCurves, this.clientECPointFormats, digestInputBuffer);
        SignatureAndHashAlgorithm signatureAndHashAlgorithm = TlsUtils.getSignatureAndHashAlgorithm(this.context, this.serverCredentials);
        Digest createHash = TlsUtils.createHash(signatureAndHashAlgorithm);
        SecurityParameters securityParameters = this.context.getSecurityParameters();
        createHash.update(securityParameters.clientRandom, 0, securityParameters.clientRandom.length);
        createHash.update(securityParameters.serverRandom, 0, securityParameters.serverRandom.length);
        digestInputBuffer.updateDigest(createHash);
        byte[] bArr = new byte[createHash.getDigestSize()];
        createHash.doFinal(bArr, 0);
        new DigitallySigned(signatureAndHashAlgorithm, this.serverCredentials.generateCertificateSignature(bArr)).encode(digestInputBuffer);
        return digestInputBuffer.toByteArray();
    }

    @Override // org.spongycastle.crypto.tls.TlsECDHKeyExchange, org.spongycastle.crypto.tls.TlsKeyExchange, org.spongycastle.crypto.tls.AbstractTlsKeyExchange
    public void processServerKeyExchange(InputStream inputStream) {
        SecurityParameters securityParameters = this.context.getSecurityParameters();
        SignerInputBuffer signerInputBuffer = new SignerInputBuffer();
        TeeInputStream teeInputStream = new TeeInputStream(inputStream, signerInputBuffer);
        ECDomainParameters readECParameters = TlsECCUtils.readECParameters(this.namedCurves, this.clientECPointFormats, teeInputStream);
        byte[] readOpaque8 = TlsUtils.readOpaque8(teeInputStream);
        DigitallySigned parseSignature = parseSignature(inputStream);
        Signer initVerifyer = initVerifyer(this.tlsSigner, parseSignature.getAlgorithm(), securityParameters);
        signerInputBuffer.updateSigner(initVerifyer);
        if (initVerifyer.verifySignature(parseSignature.getSignature())) {
            this.ecAgreePublicKey = TlsECCUtils.validateECPublicKey(TlsECCUtils.deserializeECPublicKey(this.clientECPointFormats, readECParameters, readOpaque8));
            return;
        }
        throw new TlsFatalAlert(51);
    }

    @Override // org.spongycastle.crypto.tls.TlsECDHKeyExchange, org.spongycastle.crypto.tls.TlsKeyExchange
    public void validateCertificateRequest(CertificateRequest certificateRequest) {
        short[] certificateTypes = certificateRequest.getCertificateTypes();
        for (short s : certificateTypes) {
            if (s != 64) {
                switch (s) {
                    default:
                        throw new TlsFatalAlert(47);
                    case 1:
                    case 2:
                        break;
                }
            }
        }
    }

    @Override // org.spongycastle.crypto.tls.TlsECDHKeyExchange, org.spongycastle.crypto.tls.TlsKeyExchange
    public void processClientCredentials(TlsCredentials tlsCredentials) {
        if (!(tlsCredentials instanceof TlsSignerCredentials)) {
            throw new TlsFatalAlert(80);
        }
    }

    /* access modifiers changed from: protected */
    public Signer initVerifyer(TlsSigner tlsSigner, SignatureAndHashAlgorithm signatureAndHashAlgorithm, SecurityParameters securityParameters) {
        Signer createVerifyer = tlsSigner.createVerifyer(signatureAndHashAlgorithm, this.serverPublicKey);
        createVerifyer.update(securityParameters.clientRandom, 0, securityParameters.clientRandom.length);
        createVerifyer.update(securityParameters.serverRandom, 0, securityParameters.serverRandom.length);
        return createVerifyer;
    }
}
