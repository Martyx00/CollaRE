package com.google.android.gms.internal.measurement;

import java.io.IOException;

public final class zzaci extends IOException {
    public zzaci(String str) {
        super(str);
    }

    static zzaci zzvw() {
        return new zzaci("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either than the input has been truncated or that an embedded message misreported its own length.");
    }

    static zzaci zzvx() {
        return new zzaci("CodedInputStream encountered an embedded string or message which claimed to have negative size.");
    }

    static zzaci zzvy() {
        return new zzaci("CodedInputStream encountered a malformed varint.");
    }

    static zzaci zzvz() {
        return new zzaci("Protocol message had too many levels of nesting.  May be malicious.  Use CodedInputStream.setRecursionLimit() to increase the depth limit.");
    }
}
