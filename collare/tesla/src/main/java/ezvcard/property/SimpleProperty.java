package ezvcard.property;

import com.google.firebase.analytics.FirebaseAnalytics;
import ezvcard.VCard;
import ezvcard.VCardVersion;
import ezvcard.d;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class SimpleProperty<T> extends VCardProperty {
    protected T value;

    public SimpleProperty(T t) {
        this.value = t;
    }

    public SimpleProperty(SimpleProperty<T> simpleProperty) {
        super(simpleProperty);
        this.value = simpleProperty.value;
    }

    public T getValue() {
        return this.value;
    }

    public void setValue(T t) {
        this.value = t;
    }

    /* access modifiers changed from: protected */
    @Override // ezvcard.property.VCardProperty
    public void _validate(List<d> list, VCardVersion vCardVersion, VCard vCard) {
        if (this.value == null) {
            list.add(new d(8, new Object[0]));
        }
    }

    /* access modifiers changed from: protected */
    @Override // ezvcard.property.VCardProperty
    public Map<String, Object> toStringValues() {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put(FirebaseAnalytics.b.VALUE, this.value);
        return linkedHashMap;
    }

    @Override // ezvcard.property.VCardProperty
    public int hashCode() {
        int hashCode = super.hashCode() * 31;
        T t = this.value;
        return hashCode + (t == null ? 0 : t.hashCode());
    }

    @Override // ezvcard.property.VCardProperty
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!super.equals(obj)) {
            return false;
        }
        SimpleProperty simpleProperty = (SimpleProperty) obj;
        T t = this.value;
        if (t == null) {
            if (simpleProperty.value != null) {
                return false;
            }
        } else if (!t.equals(simpleProperty.value)) {
            return false;
        }
        return true;
    }
}
