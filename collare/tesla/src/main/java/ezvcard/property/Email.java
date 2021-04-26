package ezvcard.property;

import ezvcard.VCard;
import ezvcard.VCardVersion;
import ezvcard.d;
import ezvcard.parameter.EmailType;
import ezvcard.parameter.VCardParameters;
import ezvcard.parameter.c;
import java.util.List;

public class Email extends TextProperty implements HasAltId {
    public Email(String str) {
        super(str);
    }

    public Email(Email email) {
        super(email);
    }

    public List<EmailType> getTypes() {
        VCardParameters vCardParameters = this.parameters;
        vCardParameters.getClass();
        return new VCardParameters.b<EmailType>(vCardParameters) {
            /* class ezvcard.property.Email.AnonymousClass1 */

            {
                r2.getClass();
            }

            /* access modifiers changed from: protected */
            @Override // ezvcard.parameter.VCardParameters.c
            public EmailType _asObject(String str) {
                return EmailType.a(str);
            }
        };
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

    /* access modifiers changed from: protected */
    @Override // ezvcard.property.SimpleProperty, ezvcard.property.VCardProperty
    public void _validate(List<d> list, VCardVersion vCardVersion, VCard vCard) {
        super._validate(list, vCardVersion, vCard);
        for (EmailType emailType : getTypes()) {
            if (emailType != EmailType.f5760c && !emailType.a(vCardVersion)) {
                list.add(new d(9, emailType.c()));
            }
        }
    }

    @Override // ezvcard.property.VCardProperty
    public Email copy() {
        return new Email(this);
    }
}
