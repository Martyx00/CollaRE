package com.facebook.common.k;

import com.facebook.common.d.i;
import java.io.InputStream;

/* compiled from: StreamUtil */
public class d {
    public static long a(InputStream inputStream, long j) {
        i.a(inputStream);
        i.a(j >= 0);
        long j2 = j;
        while (j2 > 0) {
            long skip = inputStream.skip(j2);
            if (skip > 0) {
                j2 -= skip;
            } else if (inputStream.read() == -1) {
                return j - j2;
            } else {
                j2--;
            }
        }
        return j;
    }
}
