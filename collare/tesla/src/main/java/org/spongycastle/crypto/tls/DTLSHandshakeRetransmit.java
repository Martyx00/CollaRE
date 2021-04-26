package org.spongycastle.crypto.tls;

interface DTLSHandshakeRetransmit {
    void receivedHandshakeRecord(int i, byte[] bArr, int i2, int i3);
}
