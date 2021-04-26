package ezvcard.property;

import ezvcard.VCardVersion;
import ezvcard.c;
import ezvcard.parameter.ExpertiseLevel;

@c(a = {VCardVersion.V4_0})
public class Expertise extends TextProperty implements HasAltId {
    public Expertise(String str) {
        super(str);
    }

    public Expertise(Expertise expertise) {
        super(expertise);
    }

    public ExpertiseLevel getLevel() {
        String i = this.parameters.i();
        if (i == null) {
            return null;
        }
        return ExpertiseLevel.a(i);
    }

    public void setLevel(ExpertiseLevel expertiseLevel) {
        this.parameters.d(expertiseLevel == null ? null : expertiseLevel.c());
    }

    @Override // ezvcard.property.VCardProperty
    public Integer getIndex() {
        return super.getIndex();
    }

    @Override // ezvcard.property.VCardProperty
    public void setIndex(Integer num) {
        super.setIndex(num);
    }

    public String getType() {
        return this.parameters.o();
    }

    public void setType(String str) {
        this.parameters.f(str);
    }

    @Override // ezvcard.property.VCardProperty
    public String getLanguage() {
        return super.getLanguage();
    }

    @Override // ezvcard.property.VCardProperty
    public void setLanguage(String str) {
        super.setLanguage(str);
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
    public Expertise copy() {
        return new Expertise(this);
    }
}
