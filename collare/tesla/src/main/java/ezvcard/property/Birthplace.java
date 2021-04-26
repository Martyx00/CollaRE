package ezvcard.property;

import ezvcard.VCardVersion;
import ezvcard.c;

@c(a = {VCardVersion.V4_0})
public class Birthplace extends PlaceProperty {
    public Birthplace() {
    }

    public Birthplace(double d2, double d3) {
        super(d2, d3);
    }

    public Birthplace(String str) {
        super(str);
    }

    public Birthplace(Birthplace birthplace) {
        super(birthplace);
    }

    @Override // ezvcard.property.VCardProperty
    public Birthplace copy() {
        return new Birthplace(this);
    }
}
