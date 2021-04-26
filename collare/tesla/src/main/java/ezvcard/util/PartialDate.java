package ezvcard.util;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class PartialDate {

    /* renamed from: a  reason: collision with root package name */
    private static final b[] f5812a = {new b("(\\d{4})", 0), new b("(\\d{4})-(\\d{2})", 0, 1), new b("(\\d{4})-?(\\d{2})-?(\\d{2})", 0, 1, 2), new b("--(\\d{2})-?(\\d{2})", 1, 2), new b("--(\\d{2})", 1), new b("---(\\d{2})", 2)};

    /* renamed from: b  reason: collision with root package name */
    private static final b[] f5813b = {new b("(\\d{2})(([-+]\\d{1,2}):?(\\d{2})?)?", 3, null, 6, 7), new b("(\\d{2}):?(\\d{2})(([-+]\\d{1,2}):?(\\d{2})?)?", 3, 4, null, 6, 7), new b("(\\d{2}):?(\\d{2}):?(\\d{2})(([-+]\\d{1,2}):?(\\d{2})?)?", 3, 4, 5, null, 6, 7), new b("-(\\d{2}):?(\\d{2})(([-+]\\d{1,2}):?(\\d{2})?)?", 4, 5, null, 6, 7), new b("-(\\d{2})(([-+]\\d{1,2}):?(\\d{2})?)?", 4, null, 6, 7), new b("--(\\d{2})(([-+]\\d{1,2}):?(\\d{2})?)?", 5, null, 6, 7)};

    /* renamed from: c  reason: collision with root package name */
    private final Integer[] f5814c;

    /* renamed from: d  reason: collision with root package name */
    private final UtcOffset f5815d;

    private PartialDate(Integer[] numArr, UtcOffset utcOffset) {
        this.f5814c = numArr;
        this.f5815d = utcOffset;
    }

    public static PartialDate a(String str) {
        String str2;
        boolean z;
        int indexOf = str.indexOf(84);
        String str3 = null;
        if (indexOf < 0) {
            str2 = str;
        } else {
            str2 = str.substring(0, indexOf);
            if (indexOf < str.length() - 1) {
                str3 = str.substring(indexOf + 1);
            }
        }
        a aVar = new a();
        if (str3 == null) {
            z = a(str2, aVar) || b(str2, aVar);
        } else if (str2.length() == 0) {
            z = b(str3, aVar);
        } else {
            z = a(str2, aVar) && b(str3, aVar);
        }
        if (z) {
            return aVar.a();
        }
        throw ezvcard.b.INSTANCE.d(36, str);
    }

    private static boolean a(String str, a aVar) {
        return a(str, aVar, f5812a);
    }

    private static boolean b(String str, a aVar) {
        return a(str, aVar, f5813b);
    }

    private static boolean a(String str, a aVar, b[] bVarArr) {
        for (b bVar : bVarArr) {
            if (bVar.a(aVar, str)) {
                return true;
            }
        }
        return false;
    }

    public Integer a() {
        return this.f5814c[0];
    }

    private boolean h() {
        return a() != null;
    }

    public Integer b() {
        return this.f5814c[1];
    }

    private boolean i() {
        return b() != null;
    }

    public Integer c() {
        return this.f5814c[2];
    }

    private boolean j() {
        return c() != null;
    }

    public Integer d() {
        return this.f5814c[3];
    }

    private boolean k() {
        return d() != null;
    }

    public Integer e() {
        return this.f5814c[4];
    }

    private boolean l() {
        return e() != null;
    }

    public Integer f() {
        return this.f5814c[5];
    }

    private boolean m() {
        return f() != null;
    }

    private boolean n() {
        return this.f5815d != null;
    }

    public boolean g() {
        return k() || l() || m();
    }

    public String a(boolean z) {
        StringBuilder sb = new StringBuilder();
        DecimalFormat decimalFormat = new DecimalFormat("00");
        String str = null;
        String num = h() ? a().toString() : null;
        String format = i() ? decimalFormat.format(b()) : null;
        String format2 = j() ? decimalFormat.format(c()) : null;
        String str2 = z ? "-" : "";
        if (h() && !i() && !j()) {
            sb.append(num);
        } else if (!h() && i() && !j()) {
            sb.append("--");
            sb.append(format);
        } else if (!h() && !i() && j()) {
            sb.append("---");
            sb.append(format2);
        } else if (h() && i() && !j()) {
            sb.append(num);
            sb.append("-");
            sb.append(format);
        } else if (!h() && i() && j()) {
            sb.append("--");
            sb.append(format);
            sb.append(str2);
            sb.append(format2);
        } else if (h() && !i() && j()) {
            throw new IllegalStateException(ezvcard.b.INSTANCE.c(38, new Object[0]));
        } else if (h() && i() && j()) {
            sb.append(num);
            sb.append(str2);
            sb.append(format);
            sb.append(str2);
            sb.append(format2);
        }
        if (g()) {
            sb.append('T');
            String format3 = k() ? decimalFormat.format(d()) : null;
            String format4 = l() ? decimalFormat.format(e()) : null;
            if (m()) {
                str = decimalFormat.format(f());
            }
            String str3 = z ? ":" : "";
            if (k() && !l() && !m()) {
                sb.append(format3);
            } else if (!k() && l() && !m()) {
                sb.append("-");
                sb.append(format4);
            } else if (!k() && !l() && m()) {
                sb.append("--");
                sb.append(str);
            } else if (k() && l() && !m()) {
                sb.append(format3);
                sb.append(str3);
                sb.append(format4);
            } else if (!k() && l() && m()) {
                sb.append("-");
                sb.append(format4);
                sb.append(str3);
                sb.append(str);
            } else if (k() && !l() && m()) {
                throw new IllegalStateException(ezvcard.b.INSTANCE.c(39, new Object[0]));
            } else if (k() && l() && m()) {
                sb.append(format3);
                sb.append(str3);
                sb.append(format4);
                sb.append(str3);
                sb.append(str);
            }
            if (n()) {
                sb.append(this.f5815d.a(z));
            }
        }
        return sb.toString();
    }

    public int hashCode() {
        int hashCode = (Arrays.hashCode(this.f5814c) + 31) * 31;
        UtcOffset utcOffset = this.f5815d;
        return hashCode + (utcOffset == null ? 0 : utcOffset.hashCode());
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        PartialDate partialDate = (PartialDate) obj;
        if (!Arrays.equals(this.f5814c, partialDate.f5814c)) {
            return false;
        }
        UtcOffset utcOffset = this.f5815d;
        if (utcOffset == null) {
            if (partialDate.f5815d != null) {
                return false;
            }
        } else if (!utcOffset.equals(partialDate.f5815d)) {
            return false;
        }
        return true;
    }

    public String toString() {
        return a(true);
    }

    /* access modifiers changed from: private */
    public static class b {

        /* renamed from: a  reason: collision with root package name */
        private Pattern f5818a;

        /* renamed from: b  reason: collision with root package name */
        private Integer[] f5819b;

        public b(String str, Integer... numArr) {
            this.f5818a = Pattern.compile('^' + str + '$');
            this.f5819b = numArr;
        }

        public boolean a(a aVar, String str) {
            String group;
            Matcher matcher = this.f5818a.matcher(str);
            if (!matcher.find()) {
                return false;
            }
            Integer num = null;
            Integer num2 = null;
            int i = 0;
            boolean z = false;
            while (true) {
                Integer[] numArr = this.f5819b;
                if (i >= numArr.length) {
                    break;
                }
                Integer num3 = numArr[i];
                if (!(num3 == null || (group = matcher.group(i + 1)) == null)) {
                    boolean startsWith = group.startsWith("+");
                    if (startsWith) {
                        group = group.substring(1);
                    }
                    int parseInt = Integer.parseInt(group);
                    if (num3.intValue() == 6) {
                        num = Integer.valueOf(parseInt);
                        z = startsWith;
                    } else if (num3.intValue() == 7) {
                        num2 = Integer.valueOf(parseInt);
                    } else {
                        aVar.f5816a[num3.intValue()] = Integer.valueOf(parseInt);
                    }
                }
                i++;
            }
            if (num != null) {
                if (num2 == null) {
                    num2 = 0;
                }
                aVar.f5817b = new UtcOffset(z, num.intValue(), num2.intValue());
            }
            return true;
        }
    }

    public static class a {

        /* renamed from: a  reason: collision with root package name */
        private final Integer[] f5816a = new Integer[6];

        /* renamed from: b  reason: collision with root package name */
        private UtcOffset f5817b;

        public PartialDate a() {
            Integer[] numArr = this.f5816a;
            if (numArr[0] == null || numArr[1] != null || numArr[2] == null) {
                Integer[] numArr2 = this.f5816a;
                if (numArr2[3] == null || numArr2[4] != null || numArr2[5] == null) {
                    return new PartialDate(this.f5816a, this.f5817b);
                }
                throw ezvcard.b.INSTANCE.d(39, new Object[0]);
            }
            throw ezvcard.b.INSTANCE.d(38, new Object[0]);
        }
    }
}
