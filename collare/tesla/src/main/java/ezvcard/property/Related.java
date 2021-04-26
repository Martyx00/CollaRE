package ezvcard.property;

import ezvcard.VCard;
import ezvcard.VCardVersion;
import ezvcard.c;
import ezvcard.d;
import ezvcard.parameter.RelatedType;
import ezvcard.parameter.VCardParameters;
import ezvcard.util.TelUri;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.spongycastle.i18n.TextBundle;

@c(a = {VCardVersion.V4_0})
public class Related extends VCardProperty implements HasAltId {
    private String text;
    private String uri;

    public Related() {
    }

    public Related(String str) {
        setUri(str);
    }

    public Related(Related related) {
        super(related);
        this.uri = related.uri;
        this.text = related.text;
    }

    public static Related email(String str) {
        return new Related("mailto:" + str);
    }

    public static Related im(String str, String str2) {
        return new Related(str + ":" + str2);
    }

    public static Related telephone(TelUri telUri) {
        return new Related(telUri.toString());
    }

    public String getUri() {
        return this.uri;
    }

    public void setUri(String str) {
        this.uri = str;
        this.text = null;
    }

    public String getText() {
        return this.text;
    }

    public void setText(String str) {
        this.text = str;
        this.uri = null;
    }

    public List<RelatedType> getTypes() {
        VCardParameters vCardParameters = this.parameters;
        vCardParameters.getClass();
        return new VCardParameters.b<RelatedType>(vCardParameters) {
            /* class ezvcard.property.Related.AnonymousClass1 */

            {
                r2.getClass();
            }

            /* access modifiers changed from: protected */
            @Override // ezvcard.parameter.VCardParameters.c
            public RelatedType _asObject(String str) {
                return RelatedType.a(str);
            }
        };
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

    /* access modifiers changed from: protected */
    @Override // ezvcard.property.VCardProperty
    public void _validate(List<d> list, VCardVersion vCardVersion, VCard vCard) {
        if (this.uri == null && this.text == null) {
            list.add(new d(8, new Object[0]));
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
    public Related copy() {
        return new Related(this);
    }

    @Override // ezvcard.property.VCardProperty
    public int hashCode() {
        int hashCode = super.hashCode() * 31;
        String str = this.text;
        int i = 0;
        int hashCode2 = (hashCode + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.uri;
        if (str2 != null) {
            i = str2.hashCode();
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
        Related related = (Related) obj;
        String str = this.text;
        if (str == null) {
            if (related.text != null) {
                return false;
            }
        } else if (!str.equals(related.text)) {
            return false;
        }
        String str2 = this.uri;
        if (str2 == null) {
            if (related.uri != null) {
                return false;
            }
        } else if (!str2.equals(related.uri)) {
            return false;
        }
        return true;
    }
}
