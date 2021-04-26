package ezvcard.a.b;

import com.github.a.a.b.e;
import ezvcard.VCardDataType;
import ezvcard.VCardVersion;
import ezvcard.a.c;
import ezvcard.parameter.VCardParameters;
import ezvcard.property.Related;

/* compiled from: RelatedScribe */
public class ap extends bg<Related> {
    public ap() {
        super(Related.class, "RELATED");
    }

    /* access modifiers changed from: protected */
    @Override // ezvcard.a.b.bg
    public VCardDataType a(VCardVersion vCardVersion) {
        return VCardDataType.f5692d;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public Related b(String str, VCardDataType vCardDataType, VCardParameters vCardParameters, c cVar) {
        String a2 = e.a(str);
        Related related = new Related();
        if (vCardDataType == VCardDataType.e) {
            related.setText(a2);
        } else {
            related.setUri(a2);
        }
        return related;
    }
}
