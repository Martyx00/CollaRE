package ezvcard.a.b;

import ezvcard.VCardDataType;
import ezvcard.VCardVersion;
import ezvcard.a.a;
import ezvcard.a.c;
import ezvcard.parameter.VCardParameters;
import ezvcard.property.Revision;
import java.util.Date;

/* compiled from: RevisionScribe */
public class aq extends bg<Revision> {
    public aq() {
        super(Revision.class, "REV");
    }

    /* access modifiers changed from: protected */
    @Override // ezvcard.a.b.bg
    public VCardDataType a(VCardVersion vCardVersion) {
        return VCardDataType.j;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public Revision b(String str, VCardDataType vCardDataType, VCardParameters vCardParameters, c cVar) {
        return a(str);
    }

    private Revision a(String str) {
        if (str == null || str.length() == 0) {
            return new Revision((Date) null);
        }
        try {
            return new Revision(h(str));
        } catch (IllegalArgumentException unused) {
            throw new a(5, new Object[0]);
        }
    }
}
