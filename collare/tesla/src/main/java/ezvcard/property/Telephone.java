package ezvcard.property;

import ezvcard.VCard;
import ezvcard.VCardVersion;
import ezvcard.d;
import ezvcard.parameter.TelephoneType;
import ezvcard.parameter.VCardParameters;
import ezvcard.parameter.c;
import ezvcard.util.TelUri;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.spongycastle.i18n.TextBundle;

public class Telephone extends VCardProperty implements HasAltId {
    private String text;
    private TelUri uri;

    public Telephone(String str) {
        setText(str);
    }

    public Telephone(TelUri telUri) {
        setUri(telUri);
    }

    public Telephone(Telephone telephone) {
        super(telephone);
        this.text = telephone.text;
        this.uri = telephone.uri;
    }

    public String getText() {
        return this.text;
    }

    public void setText(String str) {
        this.text = str;
        this.uri = null;
    }

    public TelUri getUri() {
        return this.uri;
    }

    public void setUri(TelUri telUri) {
        this.text = null;
        this.uri = telUri;
    }

    public List<TelephoneType> getTypes() {
        VCardParameters vCardParameters = this.parameters;
        vCardParameters.getClass();
        return new VCardParameters.b<TelephoneType>(vCardParameters) {
            /* class ezvcard.property.Telephone.AnonymousClass1 */

            {
                r2.getClass();
            }

            /* access modifiers changed from: protected */
            @Override // ezvcard.parameter.VCardParameters.c
            public TelephoneType _asObject(String str) {
                return TelephoneType.a(str);
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
    @Override // ezvcard.property.VCardProperty
    public void _validate(List<d> list, VCardVersion vCardVersion, VCard vCard) {
        if (this.uri == null && this.text == null) {
            list.add(new d(8, new Object[0]));
        }
        if (this.uri != null && (vCardVersion == VCardVersion.V2_1 || vCardVersion == VCardVersion.V3_0)) {
            list.add(new d(19, new Object[0]));
        }
        for (TelephoneType telephoneType : getTypes()) {
            if (telephoneType != TelephoneType.k && !telephoneType.a(vCardVersion)) {
                list.add(new d(9, telephoneType.c()));
            }
        }
    }

    /* access modifiers changed from: protected */
    @Override // ezvcard.property.VCardProperty
    public Map<String, Object> toStringValues() {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("uri", this.uri);
        linkedHashMap.put(TextBundle.TEXT_ENTRY, this.text);
        return linkedHashMap;
    }

    @Override // ezvcard.property.VCardProperty
    public Telephone copy() {
        return new Telephone(this);
    }

    @Override // ezvcard.property.VCardProperty
    public int hashCode() {
        int hashCode = super.hashCode() * 31;
        String str = this.text;
        int i = 0;
        int hashCode2 = (hashCode + (str == null ? 0 : str.hashCode())) * 31;
        TelUri telUri = this.uri;
        if (telUri != null) {
            i = telUri.hashCode();
        }
        return hashCode2 + i;
    }

    @Override // ezvcard.property.VCardProperty
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!super.equals(obj)) {
            return false;
        }
        Telephone telephone = (Telephone) obj;
        String str = this.text;
        if (str == null) {
            if (telephone.text != null) {
                return false;
            }
        } else if (!str.equals(telephone.text)) {
            return false;
        }
        TelUri telUri = this.uri;
        if (telUri == null) {
            if (telephone.uri != null) {
                return false;
            }
        } else if (!telUri.equals(telephone.uri)) {
            return false;
        }
        return true;
    }
}
