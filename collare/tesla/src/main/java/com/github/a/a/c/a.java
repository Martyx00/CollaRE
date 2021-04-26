package com.github.a.a.c;

import java.util.BitSet;
import org.spongycastle.asn1.eac.EACTags;

/* compiled from: AllowedCharacters */
public class a {

    /* renamed from: a  reason: collision with root package name */
    private final BitSet f3755a;

    /* renamed from: b  reason: collision with root package name */
    private final boolean f3756b;

    public a(BitSet bitSet, boolean z) {
        this.f3755a = bitSet;
        this.f3756b = z;
    }

    public boolean a(String str) {
        for (int i = 0; i < str.length(); i++) {
            char charAt = str.charAt(i);
            if (charAt >= 128) {
                if (!this.f3756b) {
                    return false;
                }
            } else if (!this.f3755a.get(charAt)) {
                return false;
            }
        }
        return true;
    }

    public a a() {
        BitSet bitSet = (BitSet) this.f3755a.clone();
        bitSet.flip(0, 128);
        return new a(bitSet, !this.f3756b);
    }

    public String toString() {
        return a(false);
    }

    public String a(boolean z) {
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        for (int i = 0; i < 128; i++) {
            if (this.f3755a.get(i)) {
                String str = null;
                char c2 = (char) i;
                if (c2 == '\r') {
                    str = "\\r";
                } else if (c2 != ' ') {
                    switch (c2) {
                        case '\t':
                            str = "\\t";
                            break;
                        case '\n':
                            str = "\\n";
                            break;
                        default:
                            if (i < 32 || i == 127) {
                                if (z) {
                                    break;
                                } else {
                                    str = "(" + i + ")";
                                    break;
                                }
                            }
                    }
                } else {
                    str = "<space>";
                }
                sb.append(' ');
                if (str == null) {
                    sb.append(c2);
                } else {
                    sb.append(str);
                }
            }
        }
        sb.append(" ]");
        return sb.toString();
    }

    /* renamed from: com.github.a.a.c.a$a  reason: collision with other inner class name */
    /* compiled from: AllowedCharacters */
    public static class C0065a {

        /* renamed from: a  reason: collision with root package name */
        private final BitSet f3757a;

        /* renamed from: b  reason: collision with root package name */
        private boolean f3758b;

        public C0065a() {
            this.f3757a = new BitSet(128);
            this.f3758b = false;
        }

        public C0065a(a aVar) {
            this.f3757a = (BitSet) aVar.f3755a.clone();
            this.f3758b = aVar.f3756b;
        }

        public C0065a a() {
            this.f3757a.set(0, 128);
            this.f3758b = true;
            return this;
        }

        public C0065a a(int i, int i2) {
            this.f3757a.set(i, i2 + 1);
            return this;
        }

        public C0065a a(String str) {
            a(str, true);
            return this;
        }

        public C0065a a(char c2) {
            this.f3757a.set(c2);
            return this;
        }

        public C0065a b() {
            return a(32, EACTags.NON_INTERINDUSTRY_DATA_OBJECT_NESTING_TEMPLATE);
        }

        public C0065a c() {
            this.f3758b = true;
            return this;
        }

        public C0065a b(String str) {
            a(str, false);
            return this;
        }

        public C0065a b(char c2) {
            this.f3757a.set((int) c2, false);
            return this;
        }

        public a d() {
            return new a(this.f3757a, this.f3758b);
        }

        private void a(String str, boolean z) {
            for (int i = 0; i < str.length(); i++) {
                this.f3757a.set(str.charAt(i), z);
            }
        }
    }
}
