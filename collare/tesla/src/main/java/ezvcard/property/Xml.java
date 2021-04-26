package ezvcard.property;

import com.google.firebase.analytics.FirebaseAnalytics;
import ezvcard.VCard;
import ezvcard.VCardVersion;
import ezvcard.c;
import ezvcard.d;
import ezvcard.util.k;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

@c(a = {VCardVersion.V4_0})
public class Xml extends VCardProperty implements HasAltId {
    private Document value;

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public Xml(String str) {
        this(str == null ? null : k.a(str));
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public Xml(Element element) {
        this(element == null ? null : detachElement(element));
    }

    private static Document detachElement(Element element) {
        Document a2 = k.a();
        a2.appendChild(a2.importNode(element, true));
        return a2;
    }

    public Xml(Document document) {
        this.value = document;
    }

    public Xml(Xml xml) {
        super(xml);
        Document document = xml.value;
        if (document != null) {
            Element documentElement = document.getDocumentElement();
            this.value = documentElement == null ? k.a() : detachElement(documentElement);
        }
    }

    public Document getValue() {
        return this.value;
    }

    public void setValue(Document document) {
        this.value = document;
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
        if (this.value == null) {
            list.add(new d(8, new Object[0]));
        }
    }

    /* access modifiers changed from: protected */
    @Override // ezvcard.property.VCardProperty
    public Map<String, Object> toStringValues() {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        Document document = this.value;
        linkedHashMap.put(FirebaseAnalytics.b.VALUE, document == null ? "null" : k.a(document));
        return linkedHashMap;
    }

    @Override // ezvcard.property.VCardProperty
    public Xml copy() {
        return new Xml(this);
    }

    @Override // ezvcard.property.VCardProperty
    public int hashCode() {
        int hashCode = super.hashCode() * 31;
        Document document = this.value;
        return hashCode + (document == null ? 0 : k.a(document).hashCode());
    }

    @Override // ezvcard.property.VCardProperty
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!super.equals(obj)) {
            return false;
        }
        Xml xml = (Xml) obj;
        Document document = this.value;
        if (document != null) {
            return xml.value != null && k.a(document).equals(k.a(xml.value));
        }
        if (xml.value != null) {
            return false;
        }
    }
}
