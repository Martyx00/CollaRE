package ezvcard.util;

import ezvcard.b;
import java.util.TimeZone;

public final class UtcOffset {

    /* renamed from: a  reason: collision with root package name */
    private final long f5828a;

    private static long a(long j) {
        return j * 60 * 60 * 1000;
    }

    private static long b(long j) {
        return j * 60 * 1000;
    }

    public UtcOffset(boolean z, int i, int i2) {
        this.f5828a = ((long) (z ? 1 : -1)) * (a((long) Math.abs(i)) + b((long) Math.abs(i2)));
    }

    public UtcOffset(long j) {
        this.f5828a = j;
    }

    public static UtcOffset a(String str) {
        boolean z;
        int i;
        String str2;
        int i2 = 0;
        char charAt = str.charAt(0);
        if (charAt == '-') {
            i = 1;
            z = false;
        } else if (charAt == '+') {
            i = 1;
            z = true;
        } else {
            i = 0;
            z = true;
        }
        int i3 = i + 4;
        int indexOf = str.indexOf(58, i);
        if (indexOf >= 0) {
            i3++;
        }
        if (str.length() <= i3) {
            String str3 = null;
            if (indexOf < 0) {
                str2 = str.substring(i);
                int length = str2.length() - 2;
                if (length > 0) {
                    str3 = str2.substring(length);
                    str2 = str2.substring(0, length);
                }
            } else {
                str2 = str.substring(i, indexOf);
                if (indexOf < str.length() - 1) {
                    str3 = str.substring(indexOf + 1);
                }
            }
            try {
                int parseInt = Integer.parseInt(str2);
                if (str3 != null) {
                    i2 = Integer.parseInt(str3);
                }
                return new UtcOffset(z, parseInt, i2);
            } catch (NumberFormatException unused) {
                throw b.INSTANCE.d(40, str);
            }
        } else {
            throw b.INSTANCE.d(40, str);
        }
    }

    public static UtcOffset a(TimeZone timeZone) {
        return new UtcOffset((long) timeZone.getOffset(System.currentTimeMillis()));
    }

    public long a() {
        return this.f5828a;
    }

    public String toString() {
        return a(false);
    }

    public String a(boolean z) {
        StringBuilder sb = new StringBuilder();
        boolean z2 = this.f5828a >= 0;
        long abs = Math.abs(c(this.f5828a));
        long abs2 = Math.abs(d(this.f5828a));
        sb.append(z2 ? '+' : '-');
        if (abs < 10) {
            sb.append('0');
        }
        sb.append(abs);
        if (z) {
            sb.append(':');
        }
        if (abs2 < 10) {
            sb.append('0');
        }
        sb.append(abs2);
        return sb.toString();
    }

    public int hashCode() {
        long j = this.f5828a;
        return 31 + ((int) (j ^ (j >>> 32)));
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return obj != null && getClass() == obj.getClass() && this.f5828a == ((UtcOffset) obj).f5828a;
    }

    private static long c(long j) {
        return ((j / 1000) / 60) / 60;
    }

    private static long d(long j) {
        return ((j / 1000) / 60) % 60;
    }
}
