package ezvcard.a.b;

import ezvcard.property.Birthday;
import ezvcard.util.PartialDate;
import java.util.Date;

/* compiled from: BirthdayScribe */
public class e extends l<Birthday> {
    public e() {
        super(Birthday.class, "BDAY");
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public Birthday b(String str) {
        return new Birthday(str);
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public Birthday b(Date date, boolean z) {
        return new Birthday(date, z);
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public Birthday b(PartialDate partialDate) {
        return new Birthday(partialDate);
    }
}
