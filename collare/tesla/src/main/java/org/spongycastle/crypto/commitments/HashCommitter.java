package org.spongycastle.crypto.commitments;

import java.security.SecureRandom;
import org.spongycastle.crypto.Commitment;
import org.spongycastle.crypto.Committer;
import org.spongycastle.crypto.DataLengthException;
import org.spongycastle.crypto.Digest;
import org.spongycastle.crypto.ExtendedDigest;
import org.spongycastle.util.Arrays;

public class HashCommitter implements Committer {
    private final int byteLength;
    private final Digest digest;
    private final SecureRandom random;

    public HashCommitter(ExtendedDigest extendedDigest, SecureRandom secureRandom) {
        this.digest = extendedDigest;
        this.byteLength = extendedDigest.getByteLength();
        this.random = secureRandom;
    }

    @Override // org.spongycastle.crypto.Committer
    public Commitment commit(byte[] bArr) {
        int length = bArr.length;
        int i = this.byteLength;
        if (length <= i / 2) {
            byte[] bArr2 = new byte[(i - bArr.length)];
            this.random.nextBytes(bArr2);
            return new Commitment(bArr2, calculateCommitment(bArr2, bArr));
        }
        throw new DataLengthException("Message to be committed to too large for digest.");
    }

    @Override // org.spongycastle.crypto.Committer
    public boolean isRevealed(Commitment commitment, byte[] bArr) {
        if (bArr.length + commitment.getSecret().length == this.byteLength) {
            return Arrays.constantTimeAreEqual(commitment.getCommitment(), calculateCommitment(commitment.getSecret(), bArr));
        }
        throw new DataLengthException("Message and witness secret lengths do not match.");
    }

    private byte[] calculateCommitment(byte[] bArr, byte[] bArr2) {
        byte[] bArr3 = new byte[this.digest.getDigestSize()];
        this.digest.update(bArr, 0, bArr.length);
        this.digest.update(bArr2, 0, bArr2.length);
        this.digest.doFinal(bArr3, 0);
        return bArr3;
    }
}
