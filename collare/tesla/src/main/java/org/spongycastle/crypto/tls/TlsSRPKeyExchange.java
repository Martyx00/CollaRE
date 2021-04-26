package org.spongycastle.crypto.tls;

import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.util.Vector;
import org.spongycastle.asn1.x509.Certificate;
import org.spongycastle.crypto.CryptoException;
import org.spongycastle.crypto.Digest;
import org.spongycastle.crypto.Signer;
import org.spongycastle.crypto.agreement.srp.SRP6Client;
import org.spongycastle.crypto.agreement.srp.SRP6Server;
import org.spongycastle.crypto.agreement.srp.SRP6Util;
import org.spongycastle.crypto.params.AsymmetricKeyParameter;
import org.spongycastle.crypto.params.SRP6GroupParameters;
import org.spongycastle.crypto.util.PublicKeyFactory;
import org.spongycastle.util.Arrays;
import org.spongycastle.util.BigIntegers;
import org.spongycastle.util.io.TeeInputStream;

public class TlsSRPKeyExchange extends AbstractTlsKeyExchange {
    protected TlsSRPGroupVerifier groupVerifier;
    protected byte[] identity;
    protected byte[] password;
    protected TlsSignerCredentials serverCredentials;
    protected AsymmetricKeyParameter serverPublicKey;
    protected SRP6Client srpClient;
    protected SRP6GroupParameters srpGroup;
    protected BigInteger srpPeerCredentials;
    protected byte[] srpSalt;
    protected SRP6Server srpServer;
    protected BigInteger srpVerifier;
    protected TlsSigner tlsSigner;

    @Override // org.spongycastle.crypto.tls.TlsKeyExchange, org.spongycastle.crypto.tls.AbstractTlsKeyExchange
    public boolean requiresServerKeyExchange() {
        return true;
    }

    protected static TlsSigner createSigner(int i) {
        switch (i) {
            case 21:
                return null;
            case 22:
                return new TlsDSSSigner();
            case 23:
                return new TlsRSASigner();
            default:
                throw new IllegalArgumentException("unsupported key exchange algorithm");
        }
    }

    public TlsSRPKeyExchange(int i, Vector vector, byte[] bArr, byte[] bArr2) {
        this(i, vector, new DefaultTlsSRPGroupVerifier(), bArr, bArr2);
    }

    public TlsSRPKeyExchange(int i, Vector vector, TlsSRPGroupVerifier tlsSRPGroupVerifier, byte[] bArr, byte[] bArr2) {
        super(i, vector);
        this.serverPublicKey = null;
        this.srpGroup = null;
        this.srpClient = null;
        this.srpServer = null;
        this.srpPeerCredentials = null;
        this.srpVerifier = null;
        this.srpSalt = null;
        this.serverCredentials = null;
        this.tlsSigner = createSigner(i);
        this.groupVerifier = tlsSRPGroupVerifier;
        this.identity = bArr;
        this.password = bArr2;
        this.srpClient = new SRP6Client();
    }

    public TlsSRPKeyExchange(int i, Vector vector, byte[] bArr, TlsSRPLoginParameters tlsSRPLoginParameters) {
        super(i, vector);
        this.serverPublicKey = null;
        this.srpGroup = null;
        this.srpClient = null;
        this.srpServer = null;
        this.srpPeerCredentials = null;
        this.srpVerifier = null;
        this.srpSalt = null;
        this.serverCredentials = null;
        this.tlsSigner = createSigner(i);
        this.identity = bArr;
        this.srpServer = new SRP6Server();
        this.srpGroup = tlsSRPLoginParameters.getGroup();
        this.srpVerifier = tlsSRPLoginParameters.getVerifier();
        this.srpSalt = tlsSRPLoginParameters.getSalt();
    }

    @Override // org.spongycastle.crypto.tls.TlsKeyExchange, org.spongycastle.crypto.tls.AbstractTlsKeyExchange
    public void init(TlsContext tlsContext) {
        super.init(tlsContext);
        TlsSigner tlsSigner2 = this.tlsSigner;
        if (tlsSigner2 != null) {
            tlsSigner2.init(tlsContext);
        }
    }

    @Override // org.spongycastle.crypto.tls.TlsKeyExchange
    public void skipServerCredentials() {
        if (this.tlsSigner != null) {
            throw new TlsFatalAlert(10);
        }
    }

    @Override // org.spongycastle.crypto.tls.TlsKeyExchange, org.spongycastle.crypto.tls.AbstractTlsKeyExchange
    public void processServerCertificate(Certificate certificate) {
        if (this.tlsSigner == null) {
            throw new TlsFatalAlert(10);
        } else if (!certificate.isEmpty()) {
            Certificate certificateAt = certificate.getCertificateAt(0);
            try {
                this.serverPublicKey = PublicKeyFactory.createKey(certificateAt.getSubjectPublicKeyInfo());
                if (this.tlsSigner.isValidPublicKey(this.serverPublicKey)) {
                    TlsUtils.validateKeyUsage(certificateAt, 128);
                    super.processServerCertificate(certificate);
                    return;
                }
                throw new TlsFatalAlert(46);
            } catch (RuntimeException e) {
                throw new TlsFatalAlert(43, e);
            }
        } else {
            throw new TlsFatalAlert(42);
        }
    }

    @Override // org.spongycastle.crypto.tls.TlsKeyExchange, org.spongycastle.crypto.tls.AbstractTlsKeyExchange
    public void processServerCredentials(TlsCredentials tlsCredentials) {
        if (this.keyExchange == 21 || !(tlsCredentials instanceof TlsSignerCredentials)) {
            throw new TlsFatalAlert(80);
        }
        processServerCertificate(tlsCredentials.getCertificate());
        this.serverCredentials = (TlsSignerCredentials) tlsCredentials;
    }

    @Override // org.spongycastle.crypto.tls.TlsKeyExchange, org.spongycastle.crypto.tls.AbstractTlsKeyExchange
    public byte[] generateServerKeyExchange() {
        this.srpServer.init(this.srpGroup, this.srpVerifier, TlsUtils.createHash((short) 2), this.context.getSecureRandom());
        ServerSRPParams serverSRPParams = new ServerSRPParams(this.srpGroup.getN(), this.srpGroup.getG(), this.srpSalt, this.srpServer.generateServerCredentials());
        DigestInputBuffer digestInputBuffer = new DigestInputBuffer();
        serverSRPParams.encode(digestInputBuffer);
        if (this.serverCredentials != null) {
            SignatureAndHashAlgorithm signatureAndHashAlgorithm = TlsUtils.getSignatureAndHashAlgorithm(this.context, this.serverCredentials);
            Digest createHash = TlsUtils.createHash(signatureAndHashAlgorithm);
            SecurityParameters securityParameters = this.context.getSecurityParameters();
            createHash.update(securityParameters.clientRandom, 0, securityParameters.clientRandom.length);
            createHash.update(securityParameters.serverRandom, 0, securityParameters.serverRandom.length);
            digestInputBuffer.updateDigest(createHash);
            byte[] bArr = new byte[createHash.getDigestSize()];
            createHash.doFinal(bArr, 0);
            new DigitallySigned(signatureAndHashAlgorithm, this.serverCredentials.generateCertificateSignature(bArr)).encode(digestInputBuffer);
        }
        return digestInputBuffer.toByteArray();
    }

    @Override // org.spongycastle.crypto.tls.TlsKeyExchange, org.spongycastle.crypto.tls.AbstractTlsKeyExchange
    public void processServerKeyExchange(InputStream inputStream) {
        InputStream inputStream2;
        SignerInputBuffer signerInputBuffer;
        SecurityParameters securityParameters = this.context.getSecurityParameters();
        if (this.tlsSigner != null) {
            signerInputBuffer = new SignerInputBuffer();
            inputStream2 = new TeeInputStream(inputStream, signerInputBuffer);
        } else {
            signerInputBuffer = null;
            inputStream2 = inputStream;
        }
        ServerSRPParams parse = ServerSRPParams.parse(inputStream2);
        if (signerInputBuffer != null) {
            DigitallySigned parseSignature = parseSignature(inputStream);
            Signer initVerifyer = initVerifyer(this.tlsSigner, parseSignature.getAlgorithm(), securityParameters);
            signerInputBuffer.updateSigner(initVerifyer);
            if (!initVerifyer.verifySignature(parseSignature.getSignature())) {
                throw new TlsFatalAlert(51);
            }
        }
        this.srpGroup = new SRP6GroupParameters(parse.getN(), parse.getG());
        if (this.groupVerifier.accept(this.srpGroup)) {
            this.srpSalt = parse.getS();
            try {
                this.srpPeerCredentials = SRP6Util.validatePublicValue(this.srpGroup.getN(), parse.getB());
                this.srpClient.init(this.srpGroup, TlsUtils.createHash((short) 2), this.context.getSecureRandom());
            } catch (CryptoException e) {
                throw new TlsFatalAlert(47, e);
            }
        } else {
            throw new TlsFatalAlert(71);
        }
    }

    @Override // org.spongycastle.crypto.tls.TlsKeyExchange
    public void validateCertificateRequest(CertificateRequest certificateRequest) {
        throw new TlsFatalAlert(10);
    }

    @Override // org.spongycastle.crypto.tls.TlsKeyExchange
    public void processClientCredentials(TlsCredentials tlsCredentials) {
        throw new TlsFatalAlert(80);
    }

    @Override // org.spongycastle.crypto.tls.TlsKeyExchange
    public void generateClientKeyExchange(OutputStream outputStream) {
        TlsSRPUtils.writeSRPParameter(this.srpClient.generateClientCredentials(this.srpSalt, this.identity, this.password), outputStream);
        this.context.getSecurityParameters().srpIdentity = Arrays.clone(this.identity);
    }

    @Override // org.spongycastle.crypto.tls.TlsKeyExchange, org.spongycastle.crypto.tls.AbstractTlsKeyExchange
    public void processClientKeyExchange(InputStream inputStream) {
        try {
            this.srpPeerCredentials = SRP6Util.validatePublicValue(this.srpGroup.getN(), TlsSRPUtils.readSRPParameter(inputStream));
            this.context.getSecurityParameters().srpIdentity = Arrays.clone(this.identity);
        } catch (CryptoException e) {
            throw new TlsFatalAlert(47, e);
        }
    }

    @Override // org.spongycastle.crypto.tls.TlsKeyExchange
    public byte[] generatePremasterSecret() {
        BigInteger bigInteger;
        try {
            if (this.srpServer != null) {
                bigInteger = this.srpServer.calculateSecret(this.srpPeerCredentials);
            } else {
                bigInteger = this.srpClient.calculateSecret(this.srpPeerCredentials);
            }
            return BigIntegers.asUnsignedByteArray(bigInteger);
        } catch (CryptoException e) {
            throw new TlsFatalAlert(47, e);
        }
    }

    /* access modifiers changed from: protected */
    public Signer initVerifyer(TlsSigner tlsSigner2, SignatureAndHashAlgorithm signatureAndHashAlgorithm, SecurityParameters securityParameters) {
        Signer createVerifyer = tlsSigner2.createVerifyer(signatureAndHashAlgorithm, this.serverPublicKey);
        createVerifyer.update(securityParameters.clientRandom, 0, securityParameters.clientRandom.length);
        createVerifyer.update(securityParameters.serverRandom, 0, securityParameters.serverRandom.length);
        return createVerifyer;
    }
}
