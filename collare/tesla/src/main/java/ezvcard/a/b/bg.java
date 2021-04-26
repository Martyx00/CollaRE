package ezvcard.a.b;

import ezvcard.VCardDataType;
import ezvcard.VCardVersion;
import ezvcard.a.c;
import ezvcard.parameter.VCardParameters;
import ezvcard.property.VCardProperty;
import ezvcard.util.i;
import java.util.Date;
import javax.xml.namespace.QName;

/* compiled from: VCardPropertyScribe */
public abstract class bg<T extends VCardProperty> {

    /* renamed from: b  reason: collision with root package name */
    protected final Class<T> f5712b;

    /* renamed from: c  reason: collision with root package name */
    protected final String f5713c;

    /* renamed from: d  reason: collision with root package name */
    protected final QName f5714d;

    /* access modifiers changed from: protected */
    public abstract VCardDataType a(VCardVersion vCardVersion);

    /* access modifiers changed from: protected */
    public abstract T b(String str, VCardDataType vCardDataType, VCardParameters vCardParameters, c cVar);

    public bg(Class<T> cls, String str) {
        this(cls, str, new QName(VCardVersion.V4_0.getXmlNamespace(), str.toLowerCase()));
    }

    public bg(Class<T> cls, String str, QName qName) {
        this.f5712b = cls;
        this.f5713c = str;
        this.f5714d = qName;
    }

    public Class<T> c() {
        return this.f5712b;
    }

    public String d() {
        return this.f5713c;
    }

    public QName e() {
        return this.f5714d;
    }

    public final VCardDataType b(VCardVersion vCardVersion) {
        return a(vCardVersion);
    }

    public final T c(String str, VCardDataType vCardDataType, VCardParameters vCardParameters, c cVar) {
        T b2 = b(str, vCardDataType, vCardParameters, cVar);
        b2.setParameters(vCardParameters);
        return b2;
    }

    protected static Date h(String str) {
        return i.a(str);
    }
}
