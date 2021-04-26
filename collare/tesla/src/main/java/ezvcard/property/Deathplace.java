package ezvcard.property;

import ezvcard.VCardVersion;
import ezvcard.c;

@c(a = {VCardVersion.V4_0})
public class Deathplace extends PlaceProperty {
    public Deathplace() {
    }

    public Deathplace(double d2, double d3) {
        super(d2, d3);
    }

    public Deathplace(String str) {
        super(str);
    }

    public Deathplace(Deathplace deathplace) {
        super(deathplace);
    }

    @Override // ezvcard.property.VCardProperty
    public Deathplace copy() {
        return new Deathplace(this);
    }
}
