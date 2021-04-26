package com.google.android.gms.common.server.response;

import com.google.android.gms.common.server.response.FastParser;
import java.io.BufferedReader;

final class zaa implements FastParser.zaa<Integer> {
    zaa() {
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // com.google.android.gms.common.server.response.FastParser.zaa
    public final /* synthetic */ Integer zah(FastParser fastParser, BufferedReader bufferedReader) {
        return Integer.valueOf(FastParser.zaa(fastParser, bufferedReader));
    }
}
