package ezvcard.property;

import com.github.a.a.a;
import com.github.a.a.c.b;
import com.google.firebase.analytics.FirebaseAnalytics;
import ezvcard.VCard;
import ezvcard.VCardDataType;
import ezvcard.VCardVersion;
import ezvcard.d;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class RawProperty extends TextProperty {
    private VCardDataType dataType;
    private String propertyName;

    public RawProperty(String str, String str2) {
        this(str, str2, null);
    }

    public RawProperty(String str, String str2, VCardDataType vCardDataType) {
        super(str2);
        this.propertyName = str;
        this.dataType = vCardDataType;
    }

    public RawProperty(RawProperty rawProperty) {
        super(rawProperty);
        this.propertyName = rawProperty.propertyName;
        this.dataType = rawProperty.dataType;
    }

    public String getPropertyName() {
        return this.propertyName;
    }

    public void setPropertyName(String str) {
        this.propertyName = str;
    }

    public VCardDataType getDataType() {
        return this.dataType;
    }

    public void setDataType(VCardDataType vCardDataType) {
        this.dataType = vCardDataType;
    }

    /* access modifiers changed from: protected */
    @Override // ezvcard.property.SimpleProperty, ezvcard.property.VCardProperty
    public void _validate(List<d> list, VCardVersion vCardVersion, VCard vCard) {
        a syntaxStyle = vCardVersion.getSyntaxStyle();
        com.github.a.a.c.a b2 = b.b(syntaxStyle, true);
        if (b2.a(this.propertyName)) {
            return;
        }
        if (syntaxStyle == a.OLD) {
            list.add(new d(33, this.propertyName, b2.a().a(true)));
            return;
        }
        list.add(new d(24, this.propertyName));
    }

    /* access modifiers changed from: protected */
    @Override // ezvcard.property.SimpleProperty, ezvcard.property.VCardProperty
    public Map<String, Object> toStringValues() {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("propertyName", this.propertyName);
        linkedHashMap.put("dataType", this.dataType);
        linkedHashMap.put(FirebaseAnalytics.b.VALUE, this.value);
        return linkedHashMap;
    }

    @Override // ezvcard.property.VCardProperty
    public RawProperty copy() {
        return new RawProperty(this);
    }

    @Override // ezvcard.property.SimpleProperty, ezvcard.property.VCardProperty
    public int hashCode() {
        int hashCode = super.hashCode() * 31;
        VCardDataType vCardDataType = this.dataType;
        int i = 0;
        int hashCode2 = (hashCode + (vCardDataType == null ? 0 : vCardDataType.hashCode())) * 31;
        String str = this.propertyName;
        if (str != null) {
            i = str.toLowerCase().hashCode();
        }
        return hashCode2 + i;
    }

    @Override // ezvcard.property.SimpleProperty, ezvcard.property.VCardProperty
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!super.equals(obj)) {
            return false;
        }
        RawProperty rawProperty = (RawProperty) obj;
        VCardDataType vCardDataType = this.dataType;
        if (vCardDataType == null) {
            if (rawProperty.dataType != null) {
                return false;
            }
        } else if (!vCardDataType.equals(rawProperty.dataType)) {
            return false;
        }
        String str = this.propertyName;
        if (str == null) {
            if (rawProperty.propertyName != null) {
                return false;
            }
        } else if (!str.equalsIgnoreCase(rawProperty.propertyName)) {
            return false;
        }
        return true;
    }
}
