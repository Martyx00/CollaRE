package ezvcard.property;

import ezvcard.VCard;
import ezvcard.VCardVersion;
import ezvcard.d;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class StructuredName extends VCardProperty implements HasAltId {
    private final List<String> additional;
    private String family;
    private String given;
    private final List<String> prefixes;
    private final List<String> suffixes;

    public StructuredName() {
        this.additional = new ArrayList();
        this.prefixes = new ArrayList();
        this.suffixes = new ArrayList();
    }

    public StructuredName(StructuredName structuredName) {
        super(structuredName);
        this.family = structuredName.family;
        this.given = structuredName.given;
        this.additional = new ArrayList(structuredName.additional);
        this.prefixes = new ArrayList(structuredName.prefixes);
        this.suffixes = new ArrayList(structuredName.suffixes);
    }

    public String getFamily() {
        return this.family;
    }

    public void setFamily(String str) {
        this.family = str;
    }

    public String getGiven() {
        return this.given;
    }

    public void setGiven(String str) {
        this.given = str;
    }

    public List<String> getAdditionalNames() {
        return this.additional;
    }

    public List<String> getPrefixes() {
        return this.prefixes;
    }

    public List<String> getSuffixes() {
        return this.suffixes;
    }

    public List<String> getSortAs() {
        return this.parameters.m();
    }

    public void setSortAs(String str) {
        this.parameters.a(str);
    }

    public void setSortAs(String str, String str2) {
        this.parameters.a(str, str2);
    }

    @Override // ezvcard.property.VCardProperty
    public String getLanguage() {
        return super.getLanguage();
    }

    @Override // ezvcard.property.VCardProperty
    public void setLanguage(String str) {
        super.setLanguage(str);
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
    public Map<String, Object> toStringValues() {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("family", this.family);
        linkedHashMap.put("given", this.given);
        linkedHashMap.put("additional", this.additional);
        linkedHashMap.put("prefixes", this.prefixes);
        linkedHashMap.put("suffixes", this.suffixes);
        return linkedHashMap;
    }

    /* access modifiers changed from: protected */
    @Override // ezvcard.property.VCardProperty
    public void _validate(List<d> list, VCardVersion vCardVersion, VCard vCard) {
        if (vCardVersion != VCardVersion.V2_1) {
            return;
        }
        if (this.additional.size() > 1 || this.prefixes.size() > 1 || this.suffixes.size() > 1) {
            list.add(new d(34, new Object[0]));
        }
    }

    @Override // ezvcard.property.VCardProperty
    public StructuredName copy() {
        return new StructuredName(this);
    }

    @Override // ezvcard.property.VCardProperty
    public int hashCode() {
        int hashCode = ((super.hashCode() * 31) + this.additional.hashCode()) * 31;
        String str = this.family;
        int i = 0;
        int hashCode2 = (hashCode + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.given;
        if (str2 != null) {
            i = str2.hashCode();
        }
        return ((((hashCode2 + i) * 31) + this.prefixes.hashCode()) * 31) + this.suffixes.hashCode();
    }

    @Override // ezvcard.property.VCardProperty
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!super.equals(obj)) {
            return false;
        }
        StructuredName structuredName = (StructuredName) obj;
        if (!this.additional.equals(structuredName.additional)) {
            return false;
        }
        String str = this.family;
        if (str == null) {
            if (structuredName.family != null) {
                return false;
            }
        } else if (!str.equals(structuredName.family)) {
            return false;
        }
        String str2 = this.given;
        if (str2 == null) {
            if (structuredName.given != null) {
                return false;
            }
        } else if (!str2.equals(structuredName.given)) {
            return false;
        }
        return this.prefixes.equals(structuredName.prefixes) && this.suffixes.equals(structuredName.suffixes);
    }
}
