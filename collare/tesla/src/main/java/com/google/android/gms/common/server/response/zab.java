package com.google.android.gms.common.server.response;

import com.google.android.gms.common.server.response.FastParser;
import java.io.BufferedReader;

final class zab implements FastParser.zaa<Long> {
    zab() {
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // com.google.android.gms.common.server.response.FastParser.zaa
    public final /* synthetic */ Long zah(FastParser fastParser, BufferedReader bufferedReader) {
        return Long.valueOf(FastParser.zab(fastParser, bufferedReader));
    }
}
