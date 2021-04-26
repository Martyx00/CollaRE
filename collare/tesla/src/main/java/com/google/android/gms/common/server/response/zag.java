package com.google.android.gms.common.server.response;

import com.google.android.gms.common.server.response.FastParser;
import java.io.BufferedReader;
import java.math.BigInteger;

final class zag implements FastParser.zaa<BigInteger> {
    zag() {
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // com.google.android.gms.common.server.response.FastParser.zaa
    public final /* synthetic */ BigInteger zah(FastParser fastParser, BufferedReader bufferedReader) {
        return FastParser.zaf(fastParser, bufferedReader);
    }
}
