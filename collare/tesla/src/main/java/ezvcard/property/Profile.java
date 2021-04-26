package ezvcard.property;

import ezvcard.VCard;
import ezvcard.VCardVersion;
import ezvcard.c;
import ezvcard.d;
import java.util.List;

@c(a = {VCardVersion.V3_0})
public class Profile extends TextProperty {
    public Profile() {
        super("VCARD");
    }

    public Profile(Profile profile) {
        super(profile);
    }

    /* access modifiers changed from: protected */
    @Override // ezvcard.property.SimpleProperty, ezvcard.property.VCardProperty
    public void _validate(List<d> list, VCardVersion vCardVersion, VCard vCard) {
        if (!"VCARD".equalsIgnoreCase((String) this.value)) {
            list.add(new d(18, this.value));
        }
    }

    @Override // ezvcard.property.VCardProperty
    public Profile copy() {
        return new Profile(this);
    }
}
