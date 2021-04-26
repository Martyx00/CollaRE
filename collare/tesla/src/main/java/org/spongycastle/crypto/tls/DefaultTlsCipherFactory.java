package org.spongycastle.crypto.tls;

import org.spongycastle.crypto.BlockCipher;
import org.spongycastle.crypto.Digest;
import org.spongycastle.crypto.StreamCipher;
import org.spongycastle.crypto.engines.AESEngine;
import org.spongycastle.crypto.engines.CamelliaEngine;
import org.spongycastle.crypto.engines.DESedeEngine;
import org.spongycastle.crypto.engines.RC4Engine;
import org.spongycastle.crypto.engines.SEEDEngine;
import org.spongycastle.crypto.modes.AEADBlockCipher;
import org.spongycastle.crypto.modes.CBCBlockCipher;
import org.spongycastle.crypto.modes.CCMBlockCipher;
import org.spongycastle.crypto.modes.GCMBlockCipher;
import org.spongycastle.crypto.modes.OCBBlockCipher;

public class DefaultTlsCipherFactory extends AbstractTlsCipherFactory {
    @Override // org.spongycastle.crypto.tls.AbstractTlsCipherFactory, org.spongycastle.crypto.tls.TlsCipherFactory
    public TlsCipher createCipher(TlsContext tlsContext, int i, int i2) {
        if (i == 0) {
            return createNullCipher(tlsContext, i2);
        }
        if (i == 2) {
            return createRC4Cipher(tlsContext, 16, i2);
        }
        switch (i) {
            case 7:
                return createDESedeCipher(tlsContext, i2);
            case 8:
                return createAESCipher(tlsContext, 16, i2);
            case 9:
                return createAESCipher(tlsContext, 32, i2);
            case 10:
                return createCipher_AES_GCM(tlsContext, 16, 16);
            case 11:
                return createCipher_AES_GCM(tlsContext, 32, 16);
            case 12:
                return createCamelliaCipher(tlsContext, 16, i2);
            case 13:
                return createCamelliaCipher(tlsContext, 32, i2);
            case 14:
                return createSEEDCipher(tlsContext, i2);
            case 15:
                return createCipher_AES_CCM(tlsContext, 16, 16);
            case 16:
                return createCipher_AES_CCM(tlsContext, 16, 8);
            case 17:
                return createCipher_AES_CCM(tlsContext, 32, 16);
            case 18:
                return createCipher_AES_CCM(tlsContext, 32, 8);
            case 19:
                return createCipher_Camellia_GCM(tlsContext, 16, 16);
            case 20:
                return createCipher_Camellia_GCM(tlsContext, 32, 16);
            default:
                switch (i) {
                    case 102:
                        return createChaCha20Poly1305(tlsContext);
                    case 103:
                        return createCipher_AES_OCB(tlsContext, 16, 12);
                    case 104:
                        return createCipher_AES_OCB(tlsContext, 32, 12);
                    default:
                        throw new TlsFatalAlert(80);
                }
        }
    }

    /* access modifiers changed from: protected */
    public TlsBlockCipher createAESCipher(TlsContext tlsContext, int i, int i2) {
        return new TlsBlockCipher(tlsContext, createAESBlockCipher(), createAESBlockCipher(), createHMACDigest(i2), createHMACDigest(i2), i);
    }

    /* access modifiers changed from: protected */
    public TlsBlockCipher createCamelliaCipher(TlsContext tlsContext, int i, int i2) {
        return new TlsBlockCipher(tlsContext, createCamelliaBlockCipher(), createCamelliaBlockCipher(), createHMACDigest(i2), createHMACDigest(i2), i);
    }

    /* access modifiers changed from: protected */
    public TlsCipher createChaCha20Poly1305(TlsContext tlsContext) {
        return new Chacha20Poly1305(tlsContext);
    }

    /* access modifiers changed from: protected */
    public TlsAEADCipher createCipher_AES_CCM(TlsContext tlsContext, int i, int i2) {
        return new TlsAEADCipher(tlsContext, createAEADBlockCipher_AES_CCM(), createAEADBlockCipher_AES_CCM(), i, i2);
    }

    /* access modifiers changed from: protected */
    public TlsAEADCipher createCipher_AES_GCM(TlsContext tlsContext, int i, int i2) {
        return new TlsAEADCipher(tlsContext, createAEADBlockCipher_AES_GCM(), createAEADBlockCipher_AES_GCM(), i, i2);
    }

    /* access modifiers changed from: protected */
    public TlsAEADCipher createCipher_AES_OCB(TlsContext tlsContext, int i, int i2) {
        return new TlsAEADCipher(tlsContext, createAEADBlockCipher_AES_OCB(), createAEADBlockCipher_AES_OCB(), i, i2, 2);
    }

    /* access modifiers changed from: protected */
    public TlsAEADCipher createCipher_Camellia_GCM(TlsContext tlsContext, int i, int i2) {
        return new TlsAEADCipher(tlsContext, createAEADBlockCipher_Camellia_GCM(), createAEADBlockCipher_Camellia_GCM(), i, i2);
    }

    /* access modifiers changed from: protected */
    public TlsBlockCipher createDESedeCipher(TlsContext tlsContext, int i) {
        return new TlsBlockCipher(tlsContext, createDESedeBlockCipher(), createDESedeBlockCipher(), createHMACDigest(i), createHMACDigest(i), 24);
    }

    /* access modifiers changed from: protected */
    public TlsNullCipher createNullCipher(TlsContext tlsContext, int i) {
        return new TlsNullCipher(tlsContext, createHMACDigest(i), createHMACDigest(i));
    }

    /* access modifiers changed from: protected */
    public TlsStreamCipher createRC4Cipher(TlsContext tlsContext, int i, int i2) {
        return new TlsStreamCipher(tlsContext, createRC4StreamCipher(), createRC4StreamCipher(), createHMACDigest(i2), createHMACDigest(i2), i, false);
    }

    /* access modifiers changed from: protected */
    public TlsBlockCipher createSEEDCipher(TlsContext tlsContext, int i) {
        return new TlsBlockCipher(tlsContext, createSEEDBlockCipher(), createSEEDBlockCipher(), createHMACDigest(i), createHMACDigest(i), 16);
    }

    /* access modifiers changed from: protected */
    public BlockCipher createAESEngine() {
        return new AESEngine();
    }

    /* access modifiers changed from: protected */
    public BlockCipher createCamelliaEngine() {
        return new CamelliaEngine();
    }

    /* access modifiers changed from: protected */
    public BlockCipher createAESBlockCipher() {
        return new CBCBlockCipher(createAESEngine());
    }

    /* access modifiers changed from: protected */
    public AEADBlockCipher createAEADBlockCipher_AES_CCM() {
        return new CCMBlockCipher(createAESEngine());
    }

    /* access modifiers changed from: protected */
    public AEADBlockCipher createAEADBlockCipher_AES_GCM() {
        return new GCMBlockCipher(createAESEngine());
    }

    /* access modifiers changed from: protected */
    public AEADBlockCipher createAEADBlockCipher_AES_OCB() {
        return new OCBBlockCipher(createAESEngine(), createAESEngine());
    }

    /* access modifiers changed from: protected */
    public AEADBlockCipher createAEADBlockCipher_Camellia_GCM() {
        return new GCMBlockCipher(createCamelliaEngine());
    }

    /* access modifiers changed from: protected */
    public BlockCipher createCamelliaBlockCipher() {
        return new CBCBlockCipher(createCamelliaEngine());
    }

    /* access modifiers changed from: protected */
    public BlockCipher createDESedeBlockCipher() {
        return new CBCBlockCipher(new DESedeEngine());
    }

    /* access modifiers changed from: protected */
    public StreamCipher createRC4StreamCipher() {
        return new RC4Engine();
    }

    /* access modifiers changed from: protected */
    public BlockCipher createSEEDBlockCipher() {
        return new CBCBlockCipher(new SEEDEngine());
    }

    /* access modifiers changed from: protected */
    public Digest createHMACDigest(int i) {
        switch (i) {
            case 0:
                return null;
            case 1:
                return TlsUtils.createHash((short) 1);
            case 2:
                return TlsUtils.createHash((short) 2);
            case 3:
                return TlsUtils.createHash((short) 4);
            case 4:
                return TlsUtils.createHash((short) 5);
            case 5:
                return TlsUtils.createHash((short) 6);
            default:
                throw new TlsFatalAlert(80);
        }
    }
}
