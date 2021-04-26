package org.spongycastle.asn1.x500.style;

public class X500NameTokenizer {
    private StringBuffer buf;
    private int index;
    private char separator;
    private String value;

    public X500NameTokenizer(String str) {
        this(str, ',');
    }

    public X500NameTokenizer(String str, char c2) {
        this.buf = new StringBuffer();
        this.value = str;
        this.index = -1;
        this.separator = c2;
    }

    public boolean hasMoreTokens() {
        return this.index != this.value.length();
    }

    public String nextToken() {
        if (this.index == this.value.length()) {
            return null;
        }
        int i = this.index + 1;
        this.buf.setLength(0);
        boolean z = false;
        boolean z2 = false;
        while (i != this.value.length()) {
            char charAt = this.value.charAt(i);
            if (charAt == '\"') {
                if (!z) {
                    z2 = !z2;
                }
                this.buf.append(charAt);
                z = false;
            } else if (z || z2) {
                this.buf.append(charAt);
                z = false;
            } else if (charAt == '\\') {
                this.buf.append(charAt);
                z = true;
            } else if (charAt == this.separator) {
                break;
            } else {
                this.buf.append(charAt);
            }
            i++;
        }
        this.index = i;
        return this.buf.toString();
    }
}
