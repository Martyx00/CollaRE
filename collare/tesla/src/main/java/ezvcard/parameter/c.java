package ezvcard.parameter;

/* compiled from: Pid */
public class c {

    /* renamed from: a  reason: collision with root package name */
    private final Integer f5802a;

    /* renamed from: b  reason: collision with root package name */
    private final Integer f5803b;

    public c(Integer num, Integer num2) {
        if (num != null) {
            this.f5802a = num;
            this.f5803b = num2;
            return;
        }
        throw new NullPointerException("Local ID must not be null.");
    }

    public static c a(String str) {
        String str2;
        int indexOf = str.indexOf(46);
        Integer num = null;
        if (indexOf < 0) {
            str2 = null;
        } else {
            String substring = str.substring(0, indexOf);
            str2 = indexOf == str.length() + -1 ? null : str.substring(indexOf + 1);
            str = substring;
        }
        Integer valueOf = Integer.valueOf(str);
        if (str2 != null) {
            num = Integer.valueOf(str2);
        }
        return new c(valueOf, num);
    }

    public String toString() {
        if (this.f5803b == null) {
            return Integer.toString(this.f5802a.intValue());
        }
        return this.f5802a + "." + this.f5803b;
    }

    public int hashCode() {
        Integer num = this.f5803b;
        return (((num == null ? 0 : num.hashCode()) + 31) * 31) + this.f5802a.hashCode();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        c cVar = (c) obj;
        Integer num = this.f5803b;
        if (num == null) {
            if (cVar.f5803b != null) {
                return false;
            }
        } else if (!num.equals(cVar.f5803b)) {
            return false;
        }
        return this.f5802a.equals(cVar.f5802a);
    }
}
