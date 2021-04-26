package ezvcard.a.b;

import com.github.a.a.b.e;
import ezvcard.VCardDataType;
import ezvcard.VCardVersion;
import ezvcard.a.a;
import ezvcard.a.c;
import ezvcard.parameter.VCardParameters;
import ezvcard.property.Geo;
import ezvcard.util.GeoUri;

/* compiled from: GeoScribe */
public class t extends bg<Geo> {
    public t() {
        super(Geo.class, "GEO");
    }

    /* access modifiers changed from: protected */
    @Override // ezvcard.a.b.bg
    public VCardDataType a(VCardVersion vCardVersion) {
        switch (vCardVersion) {
            case V2_1:
            case V3_0:
                return null;
            case V4_0:
                return VCardDataType.f5692d;
            default:
                return null;
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public Geo b(String str, VCardDataType vCardDataType, VCardParameters vCardParameters, c cVar) {
        if (str.length() == 0) {
            return new Geo((GeoUri) null);
        }
        switch (cVar.a()) {
            case V2_1:
            case V3_0:
                int indexOf = str.indexOf(59);
                if (indexOf >= 0) {
                    String substring = str.substring(0, indexOf);
                    String substring2 = str.substring(indexOf + 1);
                    try {
                        try {
                            return new Geo(Double.valueOf(substring), Double.valueOf(substring2));
                        } catch (NumberFormatException unused) {
                            throw new a(10, substring2);
                        }
                    } catch (NumberFormatException unused2) {
                        throw new a(8, substring);
                    }
                } else {
                    throw new a(11, new Object[0]);
                }
            case V4_0:
                return a(e.a(str));
            default:
                return null;
        }
    }

    private Geo a(String str) {
        try {
            return new Geo(GeoUri.a(str));
        } catch (IllegalArgumentException unused) {
            throw new a(12, new Object[0]);
        }
    }
}
