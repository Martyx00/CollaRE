package ezvcard.a.b;

import com.github.a.a.b.e;
import ezvcard.VCardDataType;
import ezvcard.VCardVersion;
import ezvcard.a.c;
import ezvcard.parameter.VCardParameters;
import ezvcard.property.Telephone;
import ezvcard.util.TelUri;

/* compiled from: TelephoneScribe */
public class ba extends bg<Telephone> {
    public ba() {
        super(Telephone.class, "TEL");
    }

    /* access modifiers changed from: protected */
    @Override // ezvcard.a.b.bg
    public VCardDataType a(VCardVersion vCardVersion) {
        return VCardDataType.e;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public Telephone b(String str, VCardDataType vCardDataType, VCardParameters vCardParameters, c cVar) {
        return a(e.a(str), vCardDataType, cVar);
    }

    private Telephone a(String str, VCardDataType vCardDataType, c cVar) {
        try {
            return new Telephone(TelUri.a(str));
        } catch (IllegalArgumentException unused) {
            if (vCardDataType == VCardDataType.f5692d) {
                cVar.a(18, new Object[0]);
            }
            return new Telephone(str);
        }
    }
}
