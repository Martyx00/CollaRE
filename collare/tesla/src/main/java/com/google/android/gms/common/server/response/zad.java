package com.google.android.gms.common.server.response;

import com.google.android.gms.common.server.response.FastParser;
import java.io.BufferedReader;

final class zad implements FastParser.zaa<Double> {
    zad() {
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // com.google.android.gms.common.server.response.FastParser.zaa
    public final /* synthetic */ Double zah(FastParser fastParser, BufferedReader bufferedReader) {
        return Double.valueOf(FastParser.zad(fastParser, bufferedReader));
    }
}
