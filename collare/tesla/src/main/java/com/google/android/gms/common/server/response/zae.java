package com.google.android.gms.common.server.response;

import com.google.android.gms.common.server.response.FastParser;
import java.io.BufferedReader;

final class zae implements FastParser.zaa<Boolean> {
    zae() {
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // com.google.android.gms.common.server.response.FastParser.zaa
    public final /* synthetic */ Boolean zah(FastParser fastParser, BufferedReader bufferedReader) {
        return Boolean.valueOf(FastParser.zaa(fastParser, bufferedReader, false));
    }
}
