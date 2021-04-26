package ezvcard.property;

import ezvcard.VCardVersion;
import ezvcard.c;

@c(a = {VCardVersion.V3_0})
public class SortString extends TextProperty {
    public SortString(String str) {
        super(str);
    }

    public SortString(SortString sortString) {
        super(sortString);
    }

    @Override // ezvcard.property.VCardProperty
    public SortString copy() {
        return new SortString(this);
    }
}
