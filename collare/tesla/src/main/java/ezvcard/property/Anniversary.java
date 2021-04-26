package ezvcard.property;

import ezvcard.VCardVersion;
import ezvcard.c;
import ezvcard.util.PartialDate;
import java.util.Date;

@c(a = {VCardVersion.V4_0})
public class Anniversary extends DateOrTimeProperty {
    public Anniversary(Date date) {
        super(date);
    }

    public Anniversary(Date date, boolean z) {
        super(date, z);
    }

    public Anniversary(PartialDate partialDate) {
        super(partialDate);
    }

    public Anniversary(String str) {
        super(str);
    }

    public Anniversary(Anniversary anniversary) {
        super(anniversary);
    }

    @Override // ezvcard.property.VCardProperty
    public Anniversary copy() {
        return new Anniversary(this);
    }
}
