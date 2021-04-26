package ezvcard.a.b;

import com.github.a.a.b.e;
import ezvcard.VCardDataType;
import ezvcard.VCardVersion;
import ezvcard.a.c;
import ezvcard.parameter.VCardParameters;
import ezvcard.property.Gender;
import ezvcard.property.StructuredName;

/* compiled from: StructuredNameScribe */
public class az extends bg<StructuredName> {
    public az() {
        super(StructuredName.class, Gender.NONE);
    }

    /* access modifiers changed from: protected */
    @Override // ezvcard.a.b.bg
    public VCardDataType a(VCardVersion vCardVersion) {
        return VCardDataType.e;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public StructuredName b(String str, VCardDataType vCardDataType, VCardParameters vCardParameters, c cVar) {
        StructuredName structuredName = new StructuredName();
        if (cVar.a() == VCardVersion.V2_1) {
            e.a aVar = new e.a(str);
            structuredName.setFamily(aVar.a());
            structuredName.setGiven(aVar.a());
            String a2 = aVar.a();
            if (a2 != null) {
                structuredName.getAdditionalNames().add(a2);
            }
            String a3 = aVar.a();
            if (a3 != null) {
                structuredName.getPrefixes().add(a3);
            }
            String a4 = aVar.a();
            if (a4 != null) {
                structuredName.getSuffixes().add(a4);
            }
        } else {
            e.b bVar = new e.b(str);
            structuredName.setFamily(bVar.a());
            structuredName.setGiven(bVar.a());
            structuredName.getAdditionalNames().addAll(bVar.b());
            structuredName.getPrefixes().addAll(bVar.b());
            structuredName.getSuffixes().addAll(bVar.b());
        }
        return structuredName;
    }
}
