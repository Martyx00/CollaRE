package ezvcard.property;

import ezvcard.VCard;
import ezvcard.VCardVersion;
import ezvcard.c;
import ezvcard.d;
import ezvcard.parameter.ImppType;
import ezvcard.parameter.VCardParameters;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@c(a = {VCardVersion.V3_0, VCardVersion.V4_0})
public class Impp extends VCardProperty implements HasAltId {
    private static final String AIM = "aim";
    private static final String ICQ = "icq";
    private static final String IRC = "irc";
    private static final String MSN = "msnim";
    private static final String SIP = "sip";
    private static final String SKYPE = "skype";
    private static final String XMPP = "xmpp";
    private static final String YAHOO = "ymsgr";
    private URI uri;

    public Impp(String str) {
        setUri(str);
    }

    public Impp(URI uri2) {
        setUri(uri2);
    }

    public Impp(String str, String str2) {
        setUri(str, str2);
    }

    public Impp(Impp impp) {
        super(impp);
        this.uri = impp.uri;
    }

    public static Impp aim(String str) {
        return new Impp(AIM, str);
    }

    public boolean isAim() {
        return isProtocol(AIM);
    }

    public static Impp yahoo(String str) {
        return new Impp(YAHOO, str);
    }

    public boolean isYahoo() {
        return isProtocol(YAHOO);
    }

    public static Impp msn(String str) {
        return new Impp(MSN, str);
    }

    public boolean isMsn() {
        return isProtocol(MSN);
    }

    public static Impp icq(String str) {
        return new Impp(ICQ, str);
    }

    public boolean isIcq() {
        return isProtocol(ICQ);
    }

    public static Impp irc(String str) {
        return new Impp(IRC, str);
    }

    public boolean isIrc() {
        return isProtocol(IRC);
    }

    public static Impp sip(String str) {
        return new Impp(SIP, str);
    }

    public boolean isSip() {
        return isProtocol(SIP);
    }

    public static Impp skype(String str) {
        return new Impp(SKYPE, str);
    }

    public boolean isSkype() {
        return isProtocol(SKYPE);
    }

    public static Impp xmpp(String str) {
        return new Impp(XMPP, str);
    }

    public boolean isXmpp() {
        return isProtocol(XMPP);
    }

    private boolean isProtocol(String str) {
        URI uri2 = this.uri;
        return uri2 != null && str.equals(uri2.getScheme());
    }

    public URI getUri() {
        return this.uri;
    }

    public void setUri(String str) {
        setUri(str == null ? null : URI.create(str));
    }

    public void setUri(URI uri2) {
        this.uri = uri2;
    }

    public void setUri(String str, String str2) {
        try {
            this.uri = new URI(str, str2, null);
        } catch (URISyntaxException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public String getProtocol() {
        URI uri2 = this.uri;
        if (uri2 == null) {
            return null;
        }
        return uri2.getScheme();
    }

    public String getHandle() {
        URI uri2 = this.uri;
        if (uri2 == null) {
            return null;
        }
        return uri2.getSchemeSpecificPart();
    }

    public List<ImppType> getTypes() {
        VCardParameters vCardParameters = this.parameters;
        vCardParameters.getClass();
        return new VCardParameters.b<ImppType>(vCardParameters) {
            /* class ezvcard.property.Impp.AnonymousClass1 */

            {
                r2.getClass();
            }

            /* access modifiers changed from: protected */
            @Override // ezvcard.parameter.VCardParameters.c
            public ImppType _asObject(String str) {
                return ImppType.a(str);
            }
        };
    }

    public String getMediaType() {
        return this.parameters.j();
    }

    public void setMediaType(String str) {
        this.parameters.e(str);
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
        if (this.uri == null) {
            list.add(new d(8, new Object[0]));
        }
    }

    /* access modifiers changed from: protected */
    @Override // ezvcard.property.VCardProperty
    public Map<String, Object> toStringValues() {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("uri", this.uri);
        return linkedHashMap;
    }

    @Override // ezvcard.property.VCardProperty
    public Impp copy() {
        return new Impp(this);
    }

    @Override // ezvcard.property.VCardProperty
    public int hashCode() {
        int hashCode = super.hashCode() * 31;
        URI uri2 = this.uri;
        return hashCode + (uri2 == null ? 0 : uri2.hashCode());
    }

    @Override // ezvcard.property.VCardProperty
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!super.equals(obj)) {
            return false;
        }
        Impp impp = (Impp) obj;
        URI uri2 = this.uri;
        if (uri2 == null) {
            if (impp.uri != null) {
                return false;
            }
        } else if (!uri2.equals(impp.uri)) {
            return false;
        }
        return true;
    }
}
