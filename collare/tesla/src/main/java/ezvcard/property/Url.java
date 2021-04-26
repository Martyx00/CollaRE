package ezvcard.property;

import ezvcard.parameter.c;
import java.util.List;

public class Url extends UriProperty implements HasAltId {
    public Url(String str) {
        super(str);
    }

    public Url(Url url) {
        super(url);
    }

    public String getMediaType() {
        return this.parameters.j();
    }

    public void setMediaType(String str) {
        this.parameters.e(str);
    }

    @Override // ezvcard.property.VCardProperty
    public List<c> getPids() {
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

    public String getType() {
        return this.parameters.o();
    }

    public void setType(String str) {
        this.parameters.f(str);
    }

    @Override // ezvcard.property.VCardProperty
    public Url copy() {
        return new Url(this);
    }
}
