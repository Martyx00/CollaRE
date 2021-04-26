package ezvcard.parameter;

public class MediaTypeParameter extends VCardParameter {

    /* renamed from: d  reason: collision with root package name */
    protected final String f5784d;
    protected final String e;

    public MediaTypeParameter(String str, String str2, String str3) {
        super(str);
        this.f5784d = str2;
        this.e = str3;
    }

    public String a() {
        return this.f5784d;
    }

    public String b() {
        return this.e;
    }

    @Override // ezvcard.parameter.VCardParameter
    public int hashCode() {
        int hashCode = super.hashCode() * 31;
        String str = this.e;
        int i = 0;
        int hashCode2 = (hashCode + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.f5784d;
        if (str2 != null) {
            i = str2.hashCode();
        }
        return hashCode2 + i;
    }

    @Override // ezvcard.parameter.VCardParameter
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!super.equals(obj) || getClass() != obj.getClass()) {
            return false;
        }
        MediaTypeParameter mediaTypeParameter = (MediaTypeParameter) obj;
        String str = this.e;
        if (str == null) {
            if (mediaTypeParameter.e != null) {
                return false;
            }
        } else if (!str.equals(mediaTypeParameter.e)) {
            return false;
        }
        String str2 = this.f5784d;
        if (str2 == null) {
            if (mediaTypeParameter.f5784d != null) {
                return false;
            }
        } else if (!str2.equals(mediaTypeParameter.f5784d)) {
            return false;
        }
        return true;
    }
}
