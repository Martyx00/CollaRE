package ezvcard.a.b;

import com.github.a.a.b.e;
import ezvcard.VCardDataType;
import ezvcard.VCardVersion;
import ezvcard.a.a;
import ezvcard.a.c;
import ezvcard.parameter.VCardParameters;
import ezvcard.property.Xml;
import org.xml.sax.SAXException;

/* compiled from: XmlScribe */
public class bh extends bg<Xml> {
    public bh() {
        super(Xml.class, "XML");
    }

    /* access modifiers changed from: protected */
    @Override // ezvcard.a.b.bg
    public VCardDataType a(VCardVersion vCardVersion) {
        return VCardDataType.e;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public Xml b(String str, VCardDataType vCardDataType, VCardParameters vCardParameters, c cVar) {
        try {
            return new Xml(e.a(str));
        } catch (SAXException unused) {
            throw new a(21, new Object[0]);
        }
    }
}
