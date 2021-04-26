package org.spongycastle.util.test;

import org.spongycastle.util.encoders.Hex;
import org.spongycastle.util.test.FixedSecureRandom;

public class TestRandomData extends FixedSecureRandom {
    public TestRandomData(String str) {
        super(new FixedSecureRandom.Source[]{new FixedSecureRandom.Data(Hex.decode(str))});
    }

    public TestRandomData(byte[] bArr) {
        super(new FixedSecureRandom.Source[]{new FixedSecureRandom.Data(bArr)});
    }
}
