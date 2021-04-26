package ezvcard.property;

import ezvcard.VCard;
import ezvcard.VCardVersion;
import ezvcard.d;
import ezvcard.parameter.c;
import ezvcard.util.UtcOffset;
import ezvcard.util.i;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.SimpleTimeZone;
import java.util.TimeZone;
import org.spongycastle.i18n.TextBundle;

public class Timezone extends VCardProperty implements HasAltId {
    private UtcOffset offset;
    private String text;

    public Timezone(String str) {
        this(null, str);
    }

    public Timezone(UtcOffset utcOffset) {
        this(utcOffset, null);
    }

    public Timezone(UtcOffset utcOffset, String str) {
        setOffset(utcOffset);
        setText(str);
    }

    public Timezone(TimeZone timeZone) {
        this(UtcOffset.a(timeZone), timeZone.getID());
    }

    public Timezone(Timezone timezone) {
        super(timezone);
        this.offset = timezone.offset;
        this.text = timezone.text;
    }

    public UtcOffset getOffset() {
        return this.offset;
    }

    public void setOffset(UtcOffset utcOffset) {
        this.offset = utcOffset;
    }

    public String getText() {
        return this.text;
    }

    public void setText(String str) {
        this.text = str;
    }

    public TimeZone toTimeZone() {
        TimeZone b2;
        String str = this.text;
        if (str != null && (b2 = i.b(str)) != null) {
            return b2;
        }
        if (this.offset == null) {
            return null;
        }
        String str2 = this.text;
        if (str2 == null) {
            str2 = "";
        }
        return new SimpleTimeZone((int) this.offset.a(), str2);
    }

    public String getType() {
        return this.parameters.o();
    }

    public void setType(String str) {
        this.parameters.f(str);
    }

    public String getMediaType() {
        return this.parameters.j();
    }

    public void setMediaType(String str) {
        this.parameters.e(str);
    }

    @Override // ezvcard.property.VCardProperty
    public List<c> getPids() {
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
        if (this.offset == null && this.text == null) {
            list.add(new d(8, new Object[0]));
        }
        if (this.offset == null && vCardVersion == VCardVersion.V2_1) {
            list.add(new d(20, new Object[0]));
        }
    }

    /* access modifiers changed from: protected */
    @Override // ezvcard.property.VCardProperty
    public Map<String, Object> toStringValues() {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("offset", this.offset);
        linkedHashMap.put(TextBundle.TEXT_ENTRY, this.text);
        return linkedHashMap;
    }

    @Override // ezvcard.property.VCardProperty
    public Timezone copy() {
        return new Timezone(this);
    }

    @Override // ezvcard.property.VCardProperty
    public int hashCode() {
        int hashCode = super.hashCode() * 31;
        UtcOffset utcOffset = this.offset;
        int i = 0;
        int hashCode2 = (hashCode + (utcOffset == null ? 0 : utcOffset.hashCode())) * 31;
        String str = this.text;
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
        Timezone timezone = (Timezone) obj;
        UtcOffset utcOffset = this.offset;
        if (utcOffset == null) {
            if (timezone.offset != null) {
                return false;
            }
        } else if (!utcOffset.equals(timezone.offset)) {
            return false;
        }
        String str = this.text;
        if (str == null) {
            if (timezone.text != null) {
                return false;
            }
        } else if (!str.equals(timezone.text)) {
            return false;
        }
        return true;
    }
}
