package ezvcard.property;

import ezvcard.VCardVersion;
import ezvcard.c;
import java.util.List;

@c(a = {VCardVersion.V4_0})
public class Language extends TextProperty implements HasAltId {
    public Language(String str) {
        super(str);
    }

    public Language(Language language) {
        super(language);
    }

    public String getType() {
        return this.parameters.o();
    }

    public void setType(String str) {
        this.parameters.f(str);
    }

    @Override // ezvcard.property.VCardProperty
    public List<ezvcard.parameter.c> getPids() {
        return super.getPids();
    }

    @Override // ezvcard.property.VCardProperty
    public Integer getPref() {
        return super.getPref();
    }

    @Override // ezvcard.property.VCardProperty
    public void setPref(Integer num) {
        super.setPref(num);
    }

    @Override // ezvcard.property.HasAltId
    public String getAltId() {
        return this.parameters.a();
    }

    @Override // ezvcard.property.HasAltId
    public void setAltId(String str) {
        this.parameters.a(str);
    }

    @Override // ezvcard.property.VCardProperty
    public Language copy() {
        return new Language(this);
    }
}
