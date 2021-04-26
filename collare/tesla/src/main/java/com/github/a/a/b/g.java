package com.github.a.a.b;

/* compiled from: Warning */
public enum g {
    MALFORMED_LINE("Skipping malformed line (no colon character found)."),
    EMPTY_BEGIN("Ignoring BEGIN property that does not have a component name."),
    EMPTY_END("Ignoring END property that does not have a component name."),
    UNMATCHED_END("Ignoring END property that does not match up with any BEGIN properties."),
    UNKNOWN_VERSION("Unknown version number found. Treating it as a regular property."),
    UNKNOWN_CHARSET("The property's character encoding is not supported by this system.  The value will be decoded into the default quoted-printable character encoding."),
    QUOTED_PRINTABLE_ERROR("Unable to decode the property's quoted-printable value.  Value will be treated as plain-text.");
    
    private String h;

    private g(String str) {
        this.h = str;
    }

    public String a() {
        return this.h;
    }

    public String toString() {
        return this.h;
    }
}
