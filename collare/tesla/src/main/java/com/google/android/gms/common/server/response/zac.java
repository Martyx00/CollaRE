package com.google.android.gms.common.server.response;

import com.google.android.gms.common.server.response.FastParser;
import java.io.BufferedReader;

final class zac implements FastParser.zaa<Float> {
    zac() {
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // com.google.android.gms.common.server.response.FastParser.zaa
    public final /* synthetic */ Float zah(FastParser fastParser, BufferedReader bufferedReader) {
        return Float.valueOf(FastParser.zac(fastParser, bufferedReader));
    }
}
