package ezvcard.property;

import ezvcard.VCardVersion;
import ezvcard.c;

@c(a = {VCardVersion.V2_1, VCardVersion.V3_0})
public class Mailer extends TextProperty {
    public Mailer(String str) {
        super(str);
    }

    public Mailer(Mailer mailer) {
        super(mailer);
    }

    @Override // ezvcard.property.VCardProperty
    public Mailer copy() {
        return new Mailer(this);
    }
}
