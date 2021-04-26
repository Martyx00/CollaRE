package ezvcard.property;

import ezvcard.VCard;
import ezvcard.VCardVersion;
import ezvcard.c;
import ezvcard.d;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@c(a = {VCardVersion.V4_0})
public class ClientPidMap extends VCardProperty {
    private Integer pid;
    private String uri;

    public ClientPidMap(Integer num, String str) {
        this.pid = num;
        this.uri = str;
    }

    public ClientPidMap(ClientPidMap clientPidMap) {
        super(clientPidMap);
        this.pid = clientPidMap.pid;
        this.uri = clientPidMap.uri;
    }

    public static ClientPidMap random(Integer num) {
        String uuid = UUID.randomUUID().toString();
        return new ClientPidMap(num, "urn:uuid:" + uuid);
    }

    public Integer getPid() {
        return this.pid;
    }

    public void setPid(Integer num) {
        this.pid = num;
    }

    public String getUri() {
        return this.uri;
    }

    public void setUri(String str) {
        this.uri = str;
    }

    /* access modifiers changed from: protected */
    @Override // ezvcard.property.VCardProperty
    public void _validate(List<d> list, VCardVersion vCardVersion, VCard vCard) {
        if (this.pid == null && this.uri == null) {
            list.add(new d(8, new Object[0]));
        }
    }

    /* access modifiers changed from: protected */
    @Override // ezvcard.property.VCardProperty
    public Map<String, Object> toStringValues() {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("pid", this.pid);
        linkedHashMap.put("uri", this.uri);
        return linkedHashMap;
    }

    @Override // ezvcard.property.VCardProperty
    public ClientPidMap copy() {
        return new ClientPidMap(this);
    }

    @Override // ezvcard.property.VCardProperty
    public int hashCode() {
        int hashCode = super.hashCode() * 31;
        Integer num = this.pid;
        int i = 0;
        int hashCode2 = (hashCode + (num == null ? 0 : num.hashCode())) * 31;
        String str = this.uri;
        if (str != null) {
            i = str.hashCode();
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
        ClientPidMap clientPidMap = (ClientPidMap) obj;
        Integer num = this.pid;
        if (num == null) {
            if (clientPidMap.pid != null) {
                return false;
            }
        } else if (!num.equals(clientPidMap.pid)) {
            return false;
        }
        String str = this.uri;
        if (str == null) {
            if (clientPidMap.uri != null) {
                return false;
            }
        } else if (!str.equals(clientPidMap.uri)) {
            return false;
        }
        return true;
    }
}
