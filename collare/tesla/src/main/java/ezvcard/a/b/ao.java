package ezvcard.a.b;

import ezvcard.VCardDataType;
import ezvcard.VCardVersion;
import ezvcard.a.c;
import ezvcard.parameter.VCardParameters;
import ezvcard.property.RawProperty;

/* compiled from: RawPropertyScribe */
public class ao extends bg<RawProperty> {
    /* access modifiers changed from: protected */
    @Override // ezvcard.a.b.bg
    public VCardDataType a(VCardVersion vCardVersion) {
        return null;
    }

    public ao(String str) {
        super(RawProperty.class, str);
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public RawProperty b(String str, VCardDataType vCardDataType, VCardParameters vCardParameters, c cVar) {
        RawProperty rawProperty = new RawProperty(this.f5713c, str);
        rawProperty.setDataType(vCardDataType);
        return rawProperty;
    }
}
