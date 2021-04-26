package org.spongycastle.crypto.tls;

import org.spongycastle.util.Arrays;

public class SecurityParameters {
    int cipherSuite = -1;
    byte[] clientRandom = null;
    short compressionAlgorithm = 0;
    boolean encryptThenMAC = false;
    int entity = -1;
    boolean extendedMasterSecret = false;
    byte[] masterSecret = null;
    short maxFragmentLength = -1;
    int prfAlgorithm = -1;
    byte[] pskIdentity = null;
    byte[] serverRandom = null;
    byte[] sessionHash = null;
    byte[] srpIdentity = null;
    boolean truncatedHMac = false;
    int verifyDataLength = -1;

    /* access modifiers changed from: package-private */
    public void clear() {
        byte[] bArr = this.masterSecret;
        if (bArr != null) {
            Arrays.fill(bArr, (byte) 0);
            this.masterSecret = null;
        }
    }

    public int getEntity() {
        return this.entity;
    }

    public int getCipherSuite() {
        return this.cipherSuite;
    }

    public short getCompressionAlgorithm() {
        return this.compressionAlgorithm;
    }

    public int getPrfAlgorithm() {
        return this.prfAlgorithm;
    }

    public int getVerifyDataLength() {
        return this.verifyDataLength;
    }

    public byte[] getMasterSecret() {
        return this.masterSecret;
    }

    public byte[] getClientRandom() {
        return this.clientRandom;
    }

    public byte[] getServerRandom() {
        return this.serverRandom;
    }

    public byte[] getSessionHash() {
        return this.sessionHash;
    }

    public byte[] getPskIdentity() {
        return this.pskIdentity;
    }

    public byte[] getPSKIdentity() {
        return this.pskIdentity;
    }

    public byte[] getSRPIdentity() {
        return this.srpIdentity;
    }
}
