package ezvcard.a.b;

import com.github.a.a.b.e;
import ezvcard.VCardDataType;
import ezvcard.VCardVersion;
import ezvcard.a.c;
import ezvcard.parameter.VCardParameters;
import ezvcard.property.PlaceProperty;
import ezvcard.util.GeoUri;

/* compiled from: PlacePropertyScribe */
public abstract class al<T extends PlaceProperty> extends bg<T> {
    /* access modifiers changed from: protected */
    public abstract T b();

    public al(Class<T> cls, String str) {
        super(cls, str);
    }

    /* access modifiers changed from: protected */
    @Override // ezvcard.a.b.bg
    public VCardDataType a(VCardVersion vCardVersion) {
        return VCardDataType.e;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public T b(String str, VCardDataType vCardDataType, VCardParameters vCardParameters, c cVar) {
        T b2 = b();
        String a2 = e.a(str);
        if (vCardDataType == VCardDataType.e) {
            b2.setText(a2);
            return b2;
        } else if (vCardDataType == VCardDataType.f5692d) {
            try {
                b2.setGeoUri(GeoUri.a(a2));
            } catch (IllegalArgumentException unused) {
                b2.setUri(a2);
            }
            return b2;
        } else {
            b2.setText(a2);
            return b2;
        }
    }
}
