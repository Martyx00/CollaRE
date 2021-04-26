package ezvcard.util;

import ezvcard.b;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* compiled from: VCardDateFormat */
public enum i {
    DATE_BASIC("yyyyMMdd"),
    DATE_EXTENDED("yyyy-MM-dd"),
    DATE_TIME_BASIC("yyyyMMdd'T'HHmmssZ"),
    DATE_TIME_EXTENDED("yyyy-MM-dd'T'HH:mm:ssZ") {
    },
    UTC_DATE_TIME_BASIC("yyyyMMdd'T'HHmmss'Z'") {
    },
    UTC_DATE_TIME_EXTENDED("yyyy-MM-dd'T'HH:mm:ss'Z'") {
    },
    HCARD_DATE_TIME("yyyy-MM-dd'T'HH:mm:ssZ");
    
    protected final String h;

    private i(String str) {
        this.h = str;
    }

    public static Date a(String str) {
        a aVar = new a(str);
        if (aVar.a()) {
            Calendar instance = Calendar.getInstance(aVar.j() ? TimeZone.getTimeZone("UTC") : TimeZone.getDefault());
            instance.clear();
            instance.set(1, aVar.b());
            instance.set(2, aVar.c() - 1);
            instance.set(5, aVar.d());
            if (aVar.e()) {
                instance.set(11, aVar.f());
                instance.set(12, aVar.g());
                instance.set(13, aVar.h());
                instance.set(14, aVar.i());
                if (aVar.j()) {
                    instance.set(15, aVar.k());
                }
            }
            return instance.getTime();
        }
        throw b.INSTANCE.d(41, str);
    }

    /* access modifiers changed from: private */
    /* compiled from: VCardDateFormat */
    public static class a {

        /* renamed from: a  reason: collision with root package name */
        private static final Pattern f5867a = Pattern.compile("^(\\d{4})-?(\\d{2})-?(\\d{2})(T(\\d{2}):?(\\d{2}):?(\\d{2})(\\.\\d+)?(Z|([-+])((\\d{2})|((\\d{2}):?(\\d{2}))))?)?$");

        /* renamed from: b  reason: collision with root package name */
        private final Matcher f5868b;

        /* renamed from: c  reason: collision with root package name */
        private final boolean f5869c = this.f5868b.find();

        public a(String str) {
            this.f5868b = f5867a.matcher(str);
        }

        public boolean a() {
            return this.f5869c;
        }

        public int b() {
            return a(1);
        }

        public int c() {
            return a(2);
        }

        public int d() {
            return a(3);
        }

        public boolean e() {
            return this.f5868b.group(5) != null;
        }

        public int f() {
            return a(5);
        }

        public int g() {
            return a(6);
        }

        public int h() {
            return a(7);
        }

        public int i() {
            if (this.f5868b.group(8) == null) {
                return 0;
            }
            return (int) Math.round(Double.parseDouble(this.f5868b.group(8)) * 1000.0d);
        }

        public boolean j() {
            return this.f5868b.group(9) != null;
        }

        public int k() {
            int i;
            int i2 = 0;
            if (this.f5868b.group(9).equals("Z")) {
                return 0;
            }
            int i3 = this.f5868b.group(10).equals("+") ? 1 : -1;
            if (this.f5868b.group(12) != null) {
                i = a(12);
            } else {
                i = a(14);
                i2 = a(15);
            }
            return ((i * 60 * 60 * 1000) + (i2 * 60 * 1000)) * i3;
        }

        private int a(int i) {
            return Integer.parseInt(this.f5868b.group(i));
        }
    }

    public static TimeZone b(String str) {
        TimeZone timeZone = TimeZone.getTimeZone(str);
        if ("GMT".equals(timeZone.getID())) {
            return null;
        }
        return timeZone;
    }
}
