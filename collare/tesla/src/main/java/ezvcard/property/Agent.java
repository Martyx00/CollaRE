package ezvcard.property;

import com.google.android.gms.common.internal.ImagesContract;
import ezvcard.VCard;
import ezvcard.VCardVersion;
import ezvcard.c;
import ezvcard.d;
import java.text.NumberFormat;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@c(a = {VCardVersion.V2_1, VCardVersion.V3_0})
public class Agent extends VCardProperty {
    private String url;
    private VCard vcard;

    public Agent() {
    }

    public Agent(String str) {
        setUrl(str);
    }

    public Agent(VCard vCard) {
        setVCard(vCard);
    }

    public Agent(Agent agent) {
        super(agent);
        this.url = agent.url;
        VCard vCard = agent.vcard;
        this.vcard = vCard == null ? null : new VCard(vCard);
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String str) {
        this.url = str;
        this.vcard = null;
    }

    public VCard getVCard() {
        return this.vcard;
    }

    public void setVCard(VCard vCard) {
        this.vcard = vCard;
        this.url = null;
    }

    /* access modifiers changed from: protected */
    @Override // ezvcard.property.VCardProperty
    public void _validate(List<d> list, VCardVersion vCardVersion, VCard vCard) {
        String str;
        if (this.url == null && this.vcard == null) {
            list.add(new d(8, new Object[0]));
        }
        if (this.vcard != null) {
            NumberFormat integerInstance = NumberFormat.getIntegerInstance();
            integerInstance.setMinimumIntegerDigits(2);
            Iterator<Map.Entry<VCardProperty, List<d>>> it = this.vcard.b(vCardVersion).iterator();
            while (it.hasNext()) {
                Map.Entry<VCardProperty, List<d>> next = it.next();
                VCardProperty key = next.getKey();
                for (d dVar : next.getValue()) {
                    if (key == null) {
                        str = "";
                    } else {
                        str = key.getClass().getSimpleName();
                    }
                    int intValue = dVar.a().intValue();
                    list.add(new d(10, str, intValue >= 0 ? "W" + integerInstance.format((long) intValue) : "", dVar.b()));
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    @Override // ezvcard.property.VCardProperty
    public Map<String, Object> toStringValues() {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put(ImagesContract.URL, this.url);
        linkedHashMap.put("vcard", this.vcard);
        return linkedHashMap;
    }

    @Override // ezvcard.property.VCardProperty
    public Agent copy() {
        return new Agent(this);
    }

    @Override // ezvcard.property.VCardProperty
    public int hashCode() {
        int hashCode = super.hashCode() * 31;
        String str = this.url;
        int i = 0;
        int hashCode2 = (hashCode + (str == null ? 0 : str.hashCode())) * 31;
        VCard vCard = this.vcard;
        if (vCard != null) {
            i = vCard.hashCode();
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
        Agent agent = (Agent) obj;
        String str = this.url;
        if (str == null) {
            if (agent.url != null) {
                return false;
            }
        } else if (!str.equals(agent.url)) {
            return false;
        }
        VCard vCard = this.vcard;
        if (vCard == null) {
            if (agent.vcard != null) {
                return false;
            }
        } else if (!vCard.equals(agent.vcard)) {
            return false;
        }
        return true;
    }
}
