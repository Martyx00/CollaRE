package ezvcard.a.b;

import com.github.a.a.b.e;
import ezvcard.VCardDataType;
import ezvcard.VCardVersion;
import ezvcard.a.a;
import ezvcard.a.c;
import ezvcard.parameter.VCardParameters;
import ezvcard.property.DateOrTimeProperty;
import ezvcard.util.PartialDate;
import java.util.Date;

/* compiled from: DateOrTimePropertyScribe */
public abstract class l<T extends DateOrTimeProperty> extends bg<T> {
    /* access modifiers changed from: protected */
    public abstract T b(PartialDate partialDate);

    /* access modifiers changed from: protected */
    public abstract T b(String str);

    /* access modifiers changed from: protected */
    public abstract T b(Date date, boolean z);

    public l(Class<T> cls, String str) {
        super(cls, str);
    }

    /* access modifiers changed from: protected */
    @Override // ezvcard.a.b.bg
    public VCardDataType a(VCardVersion vCardVersion) {
        switch (vCardVersion) {
            case V2_1:
            case V3_0:
                return null;
            case V4_0:
                return VCardDataType.i;
            default:
                return null;
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public T b(String str, VCardDataType vCardDataType, VCardParameters vCardParameters, c cVar) {
        String a2 = e.a(str);
        if (cVar.a() == VCardVersion.V4_0 && vCardDataType == VCardDataType.e) {
            return b(a2);
        }
        return a(a2, cVar);
    }

    private T a(String str, c cVar) {
        try {
            return b(h(str), str.contains("T"));
        } catch (IllegalArgumentException unused) {
            if (cVar.a() == VCardVersion.V2_1 || cVar.a() == VCardVersion.V3_0) {
                throw new a(5, new Object[0]);
            }
            try {
                return b(PartialDate.a(str));
            } catch (IllegalArgumentException unused2) {
                cVar.a(6, new Object[0]);
                return b(str);
            }
        }
    }
}
