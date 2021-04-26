package ezvcard.a.b;

import com.github.a.a.b.e;
import ezvcard.VCardDataType;
import ezvcard.VCardVersion;
import ezvcard.a.c;
import ezvcard.parameter.VCardParameters;
import ezvcard.property.VCardProperty;

/* compiled from: SimplePropertyScribe */
public abstract class at<T extends VCardProperty> extends bg<T> {

    /* renamed from: a  reason: collision with root package name */
    protected final VCardDataType f5709a;

    /* access modifiers changed from: protected */
    public abstract T b(String str);

    public at(Class<T> cls, String str, VCardDataType vCardDataType) {
        super(cls, str);
        this.f5709a = vCardDataType;
    }

    /* access modifiers changed from: protected */
    @Override // ezvcard.a.b.bg
    public VCardDataType a(VCardVersion vCardVersion) {
        return this.f5709a;
    }

    /* access modifiers changed from: protected */
    @Override // ezvcard.a.b.bg
    public T b(String str, VCardDataType vCardDataType, VCardParameters vCardParameters, c cVar) {
        return b(e.a(str));
    }
}
