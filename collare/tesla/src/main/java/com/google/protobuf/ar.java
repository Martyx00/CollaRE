package com.google.protobuf;

import com.google.protobuf.at;
import com.google.protobuf.av;
import com.google.protobuf.k;
import java.io.IOException;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

/* compiled from: TextFormat */
public final class ar {

    /* renamed from: a  reason: collision with root package name */
    private static final Logger f4108a = Logger.getLogger(ar.class.getName());

    /* renamed from: b  reason: collision with root package name */
    private static final b f4109b = b.a().a();

    private static boolean a(byte b2) {
        return 48 <= b2 && b2 <= 55;
    }

    private static boolean b(byte b2) {
        return (48 <= b2 && b2 <= 57) || (97 <= b2 && b2 <= 102) || (65 <= b2 && b2 <= 70);
    }

    private static int c(byte b2) {
        return (48 > b2 || b2 > 57) ? (97 > b2 || b2 > 122) ? (b2 - 65) + 10 : (b2 - 97) + 10 : b2 - 48;
    }

    private ar() {
    }

    public static void a(ag agVar, Appendable appendable) {
        c.f4122a.a((c) agVar, (ag) a(appendable));
    }

    public static void a(av avVar, Appendable appendable) {
        c.f4122a.a((c) avVar, (av) a(appendable));
    }

    public static String a(ag agVar) {
        try {
            StringBuilder sb = new StringBuilder();
            a(agVar, sb);
            return sb.toString();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    public static String a(av avVar) {
        try {
            StringBuilder sb = new StringBuilder();
            a(avVar, sb);
            return sb.toString();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    /* access modifiers changed from: private */
    public static void b(int i, Object obj, d dVar) {
        int a2 = ba.a(i);
        if (a2 != 5) {
            switch (a2) {
                case 0:
                    dVar.a(a(((Long) obj).longValue()));
                    return;
                case 1:
                    dVar.a(String.format(null, "0x%016x", (Long) obj));
                    return;
                case 2:
                    try {
                        av a3 = av.a((g) obj);
                        dVar.a("{");
                        dVar.c();
                        dVar.a();
                        c.f4122a.a((c) a3, (av) dVar);
                        dVar.b();
                        dVar.a("}");
                        return;
                    } catch (v unused) {
                        dVar.a("\"");
                        dVar.a(a((g) obj));
                        dVar.a("\"");
                        return;
                    }
                case 3:
                    c.f4122a.a((c) ((av) obj), (av) dVar);
                    return;
                default:
                    throw new IllegalArgumentException("Bad tag: " + i);
            }
        } else {
            dVar.a(String.format(null, "0x%08x", (Integer) obj));
        }
    }

    /* access modifiers changed from: private */
    /* compiled from: TextFormat */
    public static final class c {

        /* renamed from: a  reason: collision with root package name */
        static final c f4122a = new c(true);

        /* renamed from: b  reason: collision with root package name */
        static final c f4123b = new c(false);

        /* renamed from: c  reason: collision with root package name */
        private final boolean f4124c;

        private c(boolean z) {
            this.f4124c = z;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void a(ag agVar, d dVar) {
            for (Map.Entry<k.f, Object> entry : agVar.getAllFields().entrySet()) {
                a(entry.getKey(), entry.getValue(), dVar);
            }
            a(agVar.getUnknownFields(), dVar);
        }

        private void a(k.f fVar, Object obj, d dVar) {
            if (fVar.p()) {
                for (Object obj2 : (List) obj) {
                    b(fVar, obj2, dVar);
                }
                return;
            }
            b(fVar, obj, dVar);
        }

        private void b(k.f fVar, Object obj, d dVar) {
            if (fVar.u()) {
                dVar.a("[");
                if (!fVar.v().e().d() || fVar.i() != k.f.b.MESSAGE || !fVar.o() || fVar.x() != fVar.y()) {
                    dVar.a(fVar.c());
                } else {
                    dVar.a(fVar.y().c());
                }
                dVar.a("]");
            } else if (fVar.i() == k.f.b.GROUP) {
                dVar.a(fVar.y().b());
            } else {
                dVar.a(fVar.b());
            }
            if (fVar.g() == k.f.a.MESSAGE) {
                dVar.a(" {");
                dVar.c();
                dVar.a();
            } else {
                dVar.a(": ");
            }
            c(fVar, obj, dVar);
            if (fVar.g() == k.f.a.MESSAGE) {
                dVar.b();
                dVar.a("}");
            }
            dVar.c();
        }

        private void c(k.f fVar, Object obj, d dVar) {
            String str;
            switch (fVar.i()) {
                case INT32:
                case SINT32:
                case SFIXED32:
                    dVar.a(((Integer) obj).toString());
                    return;
                case INT64:
                case SINT64:
                case SFIXED64:
                    dVar.a(((Long) obj).toString());
                    return;
                case BOOL:
                    dVar.a(((Boolean) obj).toString());
                    return;
                case FLOAT:
                    dVar.a(((Float) obj).toString());
                    return;
                case DOUBLE:
                    dVar.a(((Double) obj).toString());
                    return;
                case UINT32:
                case FIXED32:
                    dVar.a(ar.a(((Integer) obj).intValue()));
                    return;
                case UINT64:
                case FIXED64:
                    dVar.a(ar.a(((Long) obj).longValue()));
                    return;
                case STRING:
                    dVar.a("\"");
                    if (this.f4124c) {
                        str = as.a((String) obj);
                    } else {
                        str = ar.a((String) obj).replace("\n", "\\n");
                    }
                    dVar.a(str);
                    dVar.a("\"");
                    return;
                case BYTES:
                    dVar.a("\"");
                    if (obj instanceof g) {
                        dVar.a(ar.a((g) obj));
                    } else {
                        dVar.a(ar.a((byte[]) obj));
                    }
                    dVar.a("\"");
                    return;
                case ENUM:
                    dVar.a(((k.e) obj).b());
                    return;
                case MESSAGE:
                case GROUP:
                    a((ac) obj, dVar);
                    return;
                default:
                    return;
            }
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void a(av avVar, d dVar) {
            for (Map.Entry<Integer, av.b> entry : avVar.d().entrySet()) {
                int intValue = entry.getKey().intValue();
                av.b value = entry.getValue();
                a(intValue, 0, value.b(), dVar);
                a(intValue, 5, value.c(), dVar);
                a(intValue, 1, value.d(), dVar);
                a(intValue, 2, value.e(), dVar);
                for (av avVar2 : value.f()) {
                    dVar.a(entry.getKey().toString());
                    dVar.a(" {");
                    dVar.c();
                    dVar.a();
                    a(avVar2, dVar);
                    dVar.b();
                    dVar.a("}");
                    dVar.c();
                }
            }
        }

        private void a(int i, int i2, List<?> list, d dVar) {
            for (Object obj : list) {
                dVar.a(String.valueOf(i));
                dVar.a(": ");
                ar.b(i2, obj, dVar);
                dVar.c();
            }
        }
    }

    public static String a(int i) {
        if (i >= 0) {
            return Integer.toString(i);
        }
        return Long.toString(((long) i) & 4294967295L);
    }

    public static String a(long j) {
        if (j >= 0) {
            return Long.toString(j);
        }
        return BigInteger.valueOf(j & Long.MAX_VALUE).setBit(63).toString();
    }

    private static d a(Appendable appendable) {
        return new d(appendable, false);
    }

    /* access modifiers changed from: private */
    /* compiled from: TextFormat */
    public static final class d {

        /* renamed from: a  reason: collision with root package name */
        private final Appendable f4125a;

        /* renamed from: b  reason: collision with root package name */
        private final StringBuilder f4126b;

        /* renamed from: c  reason: collision with root package name */
        private final boolean f4127c;

        /* renamed from: d  reason: collision with root package name */
        private boolean f4128d;

        private d(Appendable appendable, boolean z) {
            this.f4126b = new StringBuilder();
            this.f4128d = false;
            this.f4125a = appendable;
            this.f4127c = z;
        }

        public void a() {
            this.f4126b.append("  ");
        }

        public void b() {
            int length = this.f4126b.length();
            if (length != 0) {
                this.f4126b.setLength(length - 2);
                return;
            }
            throw new IllegalArgumentException(" Outdent() without matching Indent().");
        }

        public void a(CharSequence charSequence) {
            if (this.f4128d) {
                this.f4128d = false;
                this.f4125a.append(this.f4127c ? " " : this.f4126b);
            }
            this.f4125a.append(charSequence);
        }

        public void c() {
            if (!this.f4127c) {
                this.f4125a.append("\n");
            }
            this.f4128d = true;
        }
    }

    /* compiled from: TextFormat */
    public static class b {

        /* renamed from: a  reason: collision with root package name */
        private final boolean f4111a;

        /* renamed from: b  reason: collision with root package name */
        private final boolean f4112b;

        /* renamed from: c  reason: collision with root package name */
        private final EnumC0071b f4113c;

        /* renamed from: d  reason: collision with root package name */
        private at.a f4114d;

        /* renamed from: com.google.protobuf.ar$b$b  reason: collision with other inner class name */
        /* compiled from: TextFormat */
        public enum EnumC0071b {
            ALLOW_SINGULAR_OVERWRITES,
            FORBID_SINGULAR_OVERWRITES
        }

        private b(boolean z, boolean z2, EnumC0071b bVar, at.a aVar) {
            this.f4111a = z;
            this.f4112b = z2;
            this.f4113c = bVar;
            this.f4114d = aVar;
        }

        public static a a() {
            return new a();
        }

        /* compiled from: TextFormat */
        public static class a {

            /* renamed from: a  reason: collision with root package name */
            private boolean f4115a = false;

            /* renamed from: b  reason: collision with root package name */
            private boolean f4116b = false;

            /* renamed from: c  reason: collision with root package name */
            private EnumC0071b f4117c = EnumC0071b.ALLOW_SINGULAR_OVERWRITES;

            /* renamed from: d  reason: collision with root package name */
            private at.a f4118d = null;

            public b a() {
                return new b(this.f4115a, this.f4116b, this.f4117c, this.f4118d);
            }
        }
    }

    public static String a(g gVar) {
        return as.a(gVar);
    }

    public static String a(byte[] bArr) {
        return as.a(bArr);
    }

    public static g a(CharSequence charSequence) {
        g a2 = g.a(charSequence.toString());
        byte[] bArr = new byte[a2.b()];
        int i = 0;
        int i2 = 0;
        while (i < a2.b()) {
            byte a3 = a2.a(i);
            if (a3 == 92) {
                i++;
                if (i < a2.b()) {
                    byte a4 = a2.a(i);
                    if (a(a4)) {
                        int c2 = c(a4);
                        int i3 = i + 1;
                        if (i3 < a2.b() && a(a2.a(i3))) {
                            c2 = (c2 * 8) + c(a2.a(i3));
                            i = i3;
                        }
                        int i4 = i + 1;
                        if (i4 < a2.b() && a(a2.a(i4))) {
                            c2 = (c2 * 8) + c(a2.a(i4));
                            i = i4;
                        }
                        bArr[i2] = (byte) c2;
                        i2++;
                    } else if (a4 == 34) {
                        bArr[i2] = 34;
                        i2++;
                    } else if (a4 == 39) {
                        bArr[i2] = 39;
                        i2++;
                    } else if (a4 == 92) {
                        bArr[i2] = 92;
                        i2++;
                    } else if (a4 == 102) {
                        bArr[i2] = 12;
                        i2++;
                    } else if (a4 == 110) {
                        bArr[i2] = 10;
                        i2++;
                    } else if (a4 == 114) {
                        bArr[i2] = 13;
                        i2++;
                    } else if (a4 == 116) {
                        bArr[i2] = 9;
                        i2++;
                    } else if (a4 == 118) {
                        bArr[i2] = 11;
                        i2++;
                    } else if (a4 != 120) {
                        switch (a4) {
                            case 97:
                                bArr[i2] = 7;
                                i2++;
                                continue;
                            case 98:
                                bArr[i2] = 8;
                                i2++;
                                continue;
                            default:
                                throw new a("Invalid escape sequence: '\\" + ((char) a4) + '\'');
                        }
                    } else {
                        i++;
                        if (i >= a2.b() || !b(a2.a(i))) {
                            throw new a("Invalid escape sequence: '\\x' with no digits");
                        }
                        int c3 = c(a2.a(i));
                        int i5 = i + 1;
                        if (i5 < a2.b() && b(a2.a(i5))) {
                            c3 = (c3 * 16) + c(a2.a(i5));
                            i = i5;
                        }
                        bArr[i2] = (byte) c3;
                        i2++;
                    }
                } else {
                    throw new a("Invalid escape sequence: '\\' at end of string.");
                }
            } else {
                bArr[i2] = a3;
                i2++;
            }
            i++;
        }
        if (bArr.length == i2) {
            return g.b(bArr);
        }
        return g.a(bArr, 0, i2);
    }

    /* compiled from: TextFormat */
    public static class a extends IOException {
        a(String str) {
            super(str);
        }
    }

    public static String a(String str) {
        return as.b(str);
    }

    static int b(String str) {
        return (int) a(str, true, false);
    }

    static int c(String str) {
        return (int) a(str, false, false);
    }

    static long d(String str) {
        return a(str, true, true);
    }

    static long e(String str) {
        return a(str, false, true);
    }

    private static long a(String str, boolean z, boolean z2) {
        int i = 0;
        boolean z3 = true;
        if (!str.startsWith("-", 0)) {
            z3 = false;
        } else if (z) {
            i = 1;
        } else {
            throw new NumberFormatException("Number must be positive: " + str);
        }
        int i2 = 10;
        if (str.startsWith("0x", i)) {
            i += 2;
            i2 = 16;
        } else if (str.startsWith("0", i)) {
            i2 = 8;
        }
        String substring = str.substring(i);
        if (substring.length() < 16) {
            long parseLong = Long.parseLong(substring, i2);
            if (z3) {
                parseLong = -parseLong;
            }
            if (z2) {
                return parseLong;
            }
            if (z) {
                if (parseLong <= 2147483647L && parseLong >= -2147483648L) {
                    return parseLong;
                }
                throw new NumberFormatException("Number out of range for 32-bit signed integer: " + str);
            } else if (parseLong < 4294967296L && parseLong >= 0) {
                return parseLong;
            } else {
                throw new NumberFormatException("Number out of range for 32-bit unsigned integer: " + str);
            }
        } else {
            BigInteger bigInteger = new BigInteger(substring, i2);
            if (z3) {
                bigInteger = bigInteger.negate();
            }
            if (!z2) {
                if (z) {
                    if (bigInteger.bitLength() > 31) {
                        throw new NumberFormatException("Number out of range for 32-bit signed integer: " + str);
                    }
                } else if (bigInteger.bitLength() > 32) {
                    throw new NumberFormatException("Number out of range for 32-bit unsigned integer: " + str);
                }
            } else if (z) {
                if (bigInteger.bitLength() > 63) {
                    throw new NumberFormatException("Number out of range for 64-bit signed integer: " + str);
                }
            } else if (bigInteger.bitLength() > 64) {
                throw new NumberFormatException("Number out of range for 64-bit unsigned integer: " + str);
            }
            return bigInteger.longValue();
        }
    }
}
