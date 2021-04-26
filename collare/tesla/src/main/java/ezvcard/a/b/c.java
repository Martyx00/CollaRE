package ezvcard.a.b;

import ezvcard.property.Anniversary;
import ezvcard.util.PartialDate;
import java.util.Date;

/* compiled from: AnniversaryScribe */
public class c extends l<Anniversary> {
    public c() {
        super(Anniversary.class, "ANNIVERSARY");
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public Anniversary b(String str) {
        return new Anniversary(str);
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public Anniversary b(Date date, boolean z) {
        return new Anniversary(date, z);
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public Anniversary b(PartialDate partialDate) {
        return new Anniversary(partialDate);
    }
}
