package org.spongycastle.crypto;

public interface SkippingCipher {
    long getPosition();

    long seekTo(long j);

    long skip(long j);
}
