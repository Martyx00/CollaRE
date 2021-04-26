package ezvcard.property;

import ezvcard.VCard;
import ezvcard.VCardVersion;
import ezvcard.d;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ListProperty<T> extends VCardProperty {
    protected final List<T> values;

    public ListProperty() {
        this.values = new ArrayList();
    }

    public ListProperty(ListProperty<T> listProperty) {
        super(listProperty);
        this.values = new ArrayList(listProperty.values);
    }

    public List<T> getValues() {
        return this.values;
    }

    /* access modifiers changed from: protected */
    @Override // ezvcard.property.VCardProperty
    public void _validate(List<d> list, VCardVersion vCardVersion, VCard vCard) {
        if (this.values.isEmpty()) {
            list.add(new d(8, new Object[0]));
        }
    }

    /* access modifiers changed from: protected */
    @Override // ezvcard.property.VCardProperty
    public Map<String, Object> toStringValues() {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("values", this.values);
        return linkedHashMap;
    }

    @Override // ezvcard.property.VCardProperty
    public int hashCode() {
        return (super.hashCode() * 31) + this.values.hashCode();
    }

    @Override // ezvcard.property.VCardProperty
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return super.equals(obj) && this.values.equals(((ListProperty) obj).values);
    }
}
