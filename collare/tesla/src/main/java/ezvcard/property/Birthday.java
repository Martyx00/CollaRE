package ezvcard.property;

import ezvcard.util.PartialDate;
import java.util.Date;

public class Birthday extends DateOrTimeProperty {
    public Birthday(Date date) {
        super(date);
    }

    public Birthday(Date date, boolean z) {
        super(date, z);
    }

    public Birthday(PartialDate partialDate) {
        super(partialDate);
    }

    public Birthday(String str) {
        super(str);
    }

    public Birthday(Birthday birthday) {
        super(birthday);
    }

    @Override // ezvcard.property.VCardProperty
    public Birthday copy() {
        return new Birthday(this);
    }
}
