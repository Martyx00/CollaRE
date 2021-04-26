package org.spongycastle.util;

import java.util.Collection;

public interface StreamParser {
    Object read();

    Collection readAll();
}
