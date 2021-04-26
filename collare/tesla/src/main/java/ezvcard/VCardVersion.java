package ezvcard;

import com.github.a.a.a;

public enum VCardVersion {
    V2_1("2.1", a.OLD, null),
    V3_0("3.0", a.NEW, null),
    V4_0("4.0", a.NEW, "urn:ietf:params:xml:ns:vcard-4.0");
    
    private final a syntaxStyle;
    private final String version;
    private final String xmlNamespace;

    private VCardVersion(String str, a aVar, String str2) {
        this.version = str;
        this.syntaxStyle = aVar;
        this.xmlNamespace = str2;
    }

    public String getVersion() {
        return this.version;
    }

    public a getSyntaxStyle() {
        return this.syntaxStyle;
    }

    public String getXmlNamespace() {
        return this.xmlNamespace;
    }

    public static VCardVersion valueOfByStr(String str) {
        VCardVersion[] values = values();
        for (VCardVersion vCardVersion : values) {
            if (vCardVersion.getVersion().equals(str)) {
                return vCardVersion;
            }
        }
        return null;
    }

    public static VCardVersion valueOfByXmlNamespace(String str) {
        VCardVersion[] values = values();
        for (VCardVersion vCardVersion : values) {
            String xmlNamespace2 = vCardVersion.getXmlNamespace();
            if (xmlNamespace2 != null && xmlNamespace2.equals(str)) {
                return vCardVersion;
            }
        }
        return null;
    }

    public String toString() {
        return this.version;
    }
}
