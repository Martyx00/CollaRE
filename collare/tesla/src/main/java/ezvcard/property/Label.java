package ezvcard.property;

import ezvcard.VCardVersion;
import ezvcard.c;
import ezvcard.parameter.AddressType;
import ezvcard.parameter.VCardParameters;
import java.util.List;

@c(a = {VCardVersion.V2_1, VCardVersion.V3_0})
public class Label extends TextProperty {
    public Label(String str) {
        super(str);
    }

    public Label(Label label) {
        super(label);
    }

    public List<AddressType> getTypes() {
        VCardParameters vCardParameters = this.parameters;
        vCardParameters.getClass();
        return new VCardParameters.b<AddressType>(vCardParameters) {
            /* class ezvcard.property.Label.AnonymousClass1 */

            {
                r2.getClass();
            }

            /* access modifiers changed from: protected */
            @Override // ezvcard.parameter.VCardParameters.c
            public AddressType _asObject(String str) {
                return AddressType.a(str);
            }
        };
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
    public Label copy() {
        return new Label(this);
    }
}
