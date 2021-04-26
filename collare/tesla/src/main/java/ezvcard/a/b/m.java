package ezvcard.a.b;

import ezvcard.property.Deathdate;
import ezvcard.util.PartialDate;
import java.util.Date;

/* compiled from: DeathdateScribe */
public class m extends l<Deathdate> {
    public m() {
        super(Deathdate.class, "DEATHDATE");
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public Deathdate b(String str) {
        return new Deathdate(str);
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public Deathdate b(Date date, boolean z) {
        return new Deathdate(date, z);
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public Deathdate b(PartialDate partialDate) {
        return new Deathdate(partialDate);
    }
}
