package ezvcard.a.b;

import com.github.a.a.b.e;
import ezvcard.VCardDataType;
import ezvcard.VCardVersion;
import ezvcard.a.c;
import ezvcard.parameter.VCardParameters;
import ezvcard.property.TextListProperty;
import java.util.List;

/* compiled from: ListPropertyScribe */
public abstract class ac<T extends TextListProperty> extends bg<T> {
    /* access modifiers changed from: protected */
    public abstract T b();

    public ac(Class<T> cls, String str) {
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
        return a(e.b(str));
    }

    private T a(List<String> list) {
        T b2 = b();
        b2.getValues().addAll(list);
        return b2;
    }
}
