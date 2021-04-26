package org.spongycastle.crypto.tls;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Vector;
import org.spongycastle.asn1.x509.Certificate;
import org.spongycastle.crypto.params.AsymmetricKeyParameter;
import org.spongycastle.crypto.params.ECPrivateKeyParameters;
import org.spongycastle.crypto.params.ECPublicKeyParameters;
import org.spongycastle.crypto.util.PublicKeyFactory;

public class TlsECDHKeyExchange extends AbstractTlsKeyExchange {
    protected TlsAgreementCredentials agreementCredentials;
    protected short[] clientECPointFormats;
    protected ECPrivateKeyParameters ecAgreePrivateKey;
    protected ECPublicKeyParameters ecAgreePublicKey;
    protected int[] namedCurves;
    protected short[] serverECPointFormats;
    protected AsymmetricKeyParameter serverPublicKey;
    protected TlsSigner tlsSigner;

    public TlsECDHKeyExchange(int i, Vector vector, int[] iArr, short[] sArr, short[] sArr2) {
        super(i, vector);
        switch (i) {
            case 16:
            case 18:
            case 20:
                this.tlsSigner = null;
                break;
            case 17:
                this.tlsSigner = new TlsECDSASigner();
                break;
            case 19:
                this.tlsSigner = new TlsRSASigner();
                break;
            default:
                throw new IllegalArgumentException("unsupported key exchange algorithm");
        }
        this.namedCurves = iArr;
        this.clientECPointFormats = sArr;
        this.serverECPointFormats = sArr2;
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
        if (this.keyExchange != 20) {
            throw new TlsFatalAlert(10);
        }
    }

    @Override // org.spongycastle.crypto.tls.TlsKeyExchange, org.spongycastle.crypto.tls.AbstractTlsKeyExchange
    public void processServerCertificate(Certificate certificate) {
        if (this.keyExchange == 20) {
            throw new TlsFatalAlert(10);
        } else if (!certificate.isEmpty()) {
            Certificate certificateAt = certificate.getCertificateAt(0);
            try {
                this.serverPublicKey = PublicKeyFactory.createKey(certificateAt.getSubjectPublicKeyInfo());
                TlsSigner tlsSigner2 = this.tlsSigner;
                if (tlsSigner2 == null) {
                    try {
                        this.ecAgreePublicKey = TlsECCUtils.validateECPublicKey((ECPublicKeyParameters) this.serverPublicKey);
                        TlsUtils.validateKeyUsage(certificateAt, 8);
                    } catch (ClassCastException e) {
                        throw new TlsFatalAlert(46, e);
                    }
                } else if (tlsSigner2.isValidPublicKey(this.serverPublicKey)) {
                    TlsUtils.validateKeyUsage(certificateAt, 128);
                } else {
                    throw new TlsFatalAlert(46);
                }
                super.processServerCertificate(certificate);
            } catch (RuntimeException e2) {
                throw new TlsFatalAlert(43, e2);
            }
        } else {
            throw new TlsFatalAlert(42);
        }
    }

    @Override // org.spongycastle.crypto.tls.TlsKeyExchange, org.spongycastle.crypto.tls.AbstractTlsKeyExchange
    public boolean requiresServerKeyExchange() {
        switch (this.keyExchange) {
            case 17:
            case 19:
            case 20:
                return true;
            case 18:
            default:
                return false;
        }
    }

    @Override // org.spongycastle.crypto.tls.TlsKeyExchange, org.spongycastle.crypto.tls.AbstractTlsKeyExchange
    public byte[] generateServerKeyExchange() {
        if (!requiresServerKeyExchange()) {
            return null;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        this.ecAgreePrivateKey = TlsECCUtils.generateEphemeralServerKeyExchange(this.context.getSecureRandom(), this.namedCurves, this.clientECPointFormats, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    @Override // org.spongycastle.crypto.tls.TlsKeyExchange, org.spongycastle.crypto.tls.AbstractTlsKeyExchange
    public void processServerKeyExchange(InputStream inputStream) {
        if (requiresServerKeyExchange()) {
            this.ecAgreePublicKey = TlsECCUtils.validateECPublicKey(TlsECCUtils.deserializeECPublicKey(this.clientECPointFormats, TlsECCUtils.readECParameters(this.namedCurves, this.clientECPointFormats, inputStream), TlsUtils.readOpaque8(inputStream)));
            return;
        }
        throw new TlsFatalAlert(10);
    }

    @Override // org.spongycastle.crypto.tls.TlsKeyExchange
    public void validateCertificateRequest(CertificateRequest certificateRequest) {
        short[] certificateTypes = certificateRequest.getCertificateTypes();
        for (short s : certificateTypes) {
            switch (s) {
                case 1:
                case 2:
                    break;
                default:
                    switch (s) {
                        default:
                            throw new TlsFatalAlert(47);
                        case 64:
                        case 65:
                        case 66:
                            break;
                    }
            }
        }
    }

    @Override // org.spongycastle.crypto.tls.TlsKeyExchange
    public void processClientCredentials(TlsCredentials tlsCredentials) {
        if (this.keyExchange == 20) {
            throw new TlsFatalAlert(80);
        } else if (tlsCredentials instanceof TlsAgreementCredentials) {
            this.agreementCredentials = (TlsAgreementCredentials) tlsCredentials;
        } else if (!(tlsCredentials instanceof TlsSignerCredentials)) {
            throw new TlsFatalAlert(80);
        }
    }

    @Override // org.spongycastle.crypto.tls.TlsKeyExchange
    public void generateClientKeyExchange(OutputStream outputStream) {
        if (this.agreementCredentials == null) {
            this.ecAgreePrivateKey = TlsECCUtils.generateEphemeralClientKeyExchange(this.context.getSecureRandom(), this.serverECPointFormats, this.ecAgreePublicKey.getParameters(), outputStream);
        }
    }

    @Override // org.spongycastle.crypto.tls.TlsKeyExchange, org.spongycastle.crypto.tls.AbstractTlsKeyExchange
    public void processClientCertificate(Certificate certificate) {
        if (this.keyExchange == 20) {
            throw new TlsFatalAlert(10);
        }
    }

    @Override // org.spongycastle.crypto.tls.TlsKeyExchange, org.spongycastle.crypto.tls.AbstractTlsKeyExchange
    public void processClientKeyExchange(InputStream inputStream) {
        if (this.ecAgreePublicKey == null) {
            byte[] readOpaque8 = TlsUtils.readOpaque8(inputStream);
            this.ecAgreePublicKey = TlsECCUtils.validateECPublicKey(TlsECCUtils.deserializeECPublicKey(this.serverECPointFormats, this.ecAgreePrivateKey.getParameters(), readOpaque8));
        }
    }

    @Override // org.spongycastle.crypto.tls.TlsKeyExchange
    public byte[] generatePremasterSecret() {
        TlsAgreementCredentials tlsAgreementCredentials = this.agreementCredentials;
        if (tlsAgreementCredentials != null) {
            return tlsAgreementCredentials.generateAgreement(this.ecAgreePublicKey);
        }
        ECPrivateKeyParameters eCPrivateKeyParameters = this.ecAgreePrivateKey;
        if (eCPrivateKeyParameters != null) {
            return TlsECCUtils.calculateECDHBasicAgreement(this.ecAgreePublicKey, eCPrivateKeyParameters);
        }
        throw new TlsFatalAlert(80);
    }
}
