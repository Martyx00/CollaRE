package ezvcard.a.b;

import com.github.a.a.b.e;
import ezvcard.VCardDataType;
import ezvcard.VCardVersion;
import ezvcard.a.a;
import ezvcard.a.c;
import ezvcard.parameter.VCardParameters;
import ezvcard.property.Timezone;
import ezvcard.util.UtcOffset;

/* compiled from: TimezoneScribe */
public class bb extends bg<Timezone> {
    public bb() {
        super(Timezone.class, "TZ");
    }

    /* access modifiers changed from: protected */
    @Override // ezvcard.a.b.bg
    public VCardDataType a(VCardVersion vCardVersion) {
        switch (vCardVersion) {
            case V2_1:
            case V3_0:
                return VCardDataType.n;
            case V4_0:
                return VCardDataType.e;
            default:
                return null;
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public Timezone b(String str, VCardDataType vCardDataType, VCardParameters vCardParameters, c cVar) {
        return a(e.a(str), vCardDataType, cVar);
    }

    private Timezone a(String str, VCardDataType vCardDataType, c cVar) {
        if (str == null || str.length() == 0) {
            return new Timezone((String) null);
        }
        switch (cVar.a()) {
            case V2_1:
                try {
                    return new Timezone(UtcOffset.a(str));
                } catch (IllegalArgumentException unused) {
                    throw new a(19, new Object[0]);
                }
            case V3_0:
            case V4_0:
                try {
                    return new Timezone(UtcOffset.a(str));
                } catch (IllegalArgumentException unused2) {
                    if (vCardDataType == VCardDataType.n) {
                        cVar.a(20, new Object[0]);
                    }
                    return new Timezone(str);
                }
            default:
                return new Timezone((String) null);
        }
    }
}
