package org.spongycastle.crypto.agreement.jpake;

import java.math.BigInteger;

public class JPAKERound3Payload {
    private final BigInteger macTag;
    private final String participantId;

    public JPAKERound3Payload(String str, BigInteger bigInteger) {
        this.participantId = str;
        this.macTag = bigInteger;
    }

    public String getParticipantId() {
        return this.participantId;
    }

    public BigInteger getMacTag() {
        return this.macTag;
    }
}
