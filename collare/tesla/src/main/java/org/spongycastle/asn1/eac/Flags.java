package org.spongycastle.asn1.eac;

import java.util.Enumeration;
import java.util.Hashtable;

public class Flags {
    int value = 0;

    public Flags() {
    }

    public Flags(int i) {
        this.value = i;
    }

    public void set(int i) {
        this.value = i | this.value;
    }

    public boolean isSet(int i) {
        return (i & this.value) != 0;
    }

    public int getFlags() {
        return this.value;
    }

    /* access modifiers changed from: package-private */
    public String decode(Hashtable hashtable) {
        StringJoiner stringJoiner = new StringJoiner(" ");
        Enumeration keys = hashtable.keys();
        while (keys.hasMoreElements()) {
            Integer num = (Integer) keys.nextElement();
            if (isSet(num.intValue())) {
                stringJoiner.add((String) hashtable.get(num));
            }
        }
        return stringJoiner.toString();
    }

    private class StringJoiner {
        boolean First = true;

        /* renamed from: b  reason: collision with root package name */
        StringBuffer f6387b = new StringBuffer();
        String mSeparator;

        public StringJoiner(String str) {
            this.mSeparator = str;
        }

        public void add(String str) {
            if (this.First) {
                this.First = false;
            } else {
                this.f6387b.append(this.mSeparator);
            }
            this.f6387b.append(str);
        }

        public String toString() {
            return this.f6387b.toString();
        }
    }
}
