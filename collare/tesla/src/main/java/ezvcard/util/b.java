package ezvcard.util;

import java.util.BitSet;

/* compiled from: CharacterBitSet */
public class b {

    /* renamed from: a  reason: collision with root package name */
    private final BitSet f5841a = new BitSet(128);

    /* renamed from: b  reason: collision with root package name */
    private final String f5842b;

    public b(String str) {
        this.f5842b = str;
        int i = 0;
        while (i < str.length()) {
            char charAt = str.charAt(i);
            if ((i < str.length() + -2 ? str.charAt(i + 1) : 0) == '-') {
                i += 2;
                char charAt2 = str.charAt(i);
                if (charAt > charAt2) {
                    charAt2 = charAt;
                    charAt = charAt2;
                }
                this.f5841a.set(charAt, charAt2 + 1);
            } else {
                this.f5841a.set(charAt);
            }
            i++;
        }
    }

    public int hashCode() {
        return this.f5841a.hashCode();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            return this.f5841a.equals(((b) obj).f5841a);
        }
        return false;
    }

    public String toString() {
        return this.f5842b;
    }
}
