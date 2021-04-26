package ezvcard.property;

import ezvcard.VCardVersion;
import ezvcard.c;

@c(a = {VCardVersion.V3_0})
public class SourceDisplayText extends TextProperty {
    public SourceDisplayText(String str) {
        super(str);
    }

    public SourceDisplayText(SourceDisplayText sourceDisplayText) {
        super(sourceDisplayText);
    }

    @Override // ezvcard.property.VCardProperty
    public SourceDisplayText copy() {
        return new SourceDisplayText(this);
    }
}
