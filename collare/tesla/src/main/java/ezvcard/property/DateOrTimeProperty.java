package ezvcard.property;

import ezvcard.VCard;
import ezvcard.VCardVersion;
import ezvcard.d;
import ezvcard.parameter.Calscale;
import ezvcard.util.PartialDate;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.spongycastle.i18n.TextBundle;

public class DateOrTimeProperty extends VCardProperty implements HasAltId {
    private Date date;
    private boolean dateHasTime;
    private PartialDate partialDate;
    private String text;

    public DateOrTimeProperty(Date date2) {
        this(date2, false);
    }

    public DateOrTimeProperty(Date date2, boolean z) {
        setDate(date2, z);
    }

    public DateOrTimeProperty(PartialDate partialDate2) {
        setPartialDate(partialDate2);
    }

    public DateOrTimeProperty(String str) {
        setText(str);
    }

    public DateOrTimeProperty(DateOrTimeProperty dateOrTimeProperty) {
        super(dateOrTimeProperty);
        this.text = dateOrTimeProperty.text;
        Date date2 = dateOrTimeProperty.date;
        this.date = date2 == null ? null : new Date(date2.getTime());
        this.partialDate = dateOrTimeProperty.partialDate;
        this.dateHasTime = dateOrTimeProperty.dateHasTime;
    }

    public Date getDate() {
        return this.date;
    }

    public void setDate(Date date2, boolean z) {
        this.date = date2;
        if (date2 == null) {
            z = false;
        }
        this.dateHasTime = z;
        this.text = null;
        this.partialDate = null;
    }

    public PartialDate getPartialDate() {
        return this.partialDate;
    }

    public void setPartialDate(PartialDate partialDate2) {
        boolean z;
        this.partialDate = partialDate2;
        if (partialDate2 == null) {
            z = false;
        } else {
            z = partialDate2.g();
        }
        this.dateHasTime = z;
        this.text = null;
        this.date = null;
    }

    public String getText() {
        return this.text;
    }

    public void setText(String str) {
        this.text = str;
        this.date = null;
        this.partialDate = null;
        this.dateHasTime = false;
    }

    public boolean hasTime() {
        return this.dateHasTime;
    }

    public Calscale getCalscale() {
        return this.parameters.b();
    }

    public void setCalscale(Calscale calscale) {
        this.parameters.a(calscale);
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
    public void _validate(List<d> list, VCardVersion vCardVersion, VCard vCard) {
        if (this.date == null && this.partialDate == null && this.text == null) {
            list.add(new d(8, new Object[0]));
        }
        if (vCardVersion == VCardVersion.V2_1 || vCardVersion == VCardVersion.V3_0) {
            if (this.text != null) {
                list.add(new d(11, new Object[0]));
            }
            if (this.partialDate != null) {
                list.add(new d(12, new Object[0]));
            }
        }
    }

    /* access modifiers changed from: protected */
    @Override // ezvcard.property.VCardProperty
    public Map<String, Object> toStringValues() {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put(TextBundle.TEXT_ENTRY, this.text);
        linkedHashMap.put("date", this.date);
        linkedHashMap.put("dateHasTime", Boolean.valueOf(this.dateHasTime));
        linkedHashMap.put("partialDate", this.partialDate);
        return linkedHashMap;
    }

    @Override // ezvcard.property.VCardProperty
    public int hashCode() {
        int hashCode = super.hashCode() * 31;
        Date date2 = this.date;
        int i = 0;
        int hashCode2 = (((hashCode + (date2 == null ? 0 : date2.hashCode())) * 31) + (this.dateHasTime ? 1231 : 1237)) * 31;
        PartialDate partialDate2 = this.partialDate;
        int hashCode3 = (hashCode2 + (partialDate2 == null ? 0 : partialDate2.hashCode())) * 31;
        String str = this.text;
        if (str != null) {
            i = str.hashCode();
        }
        return hashCode3 + i;
    }

    @Override // ezvcard.property.VCardProperty
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!super.equals(obj)) {
            return false;
        }
        DateOrTimeProperty dateOrTimeProperty = (DateOrTimeProperty) obj;
        Date date2 = this.date;
        if (date2 == null) {
            if (dateOrTimeProperty.date != null) {
                return false;
            }
        } else if (!date2.equals(dateOrTimeProperty.date)) {
            return false;
        }
        if (this.dateHasTime != dateOrTimeProperty.dateHasTime) {
            return false;
        }
        PartialDate partialDate2 = this.partialDate;
        if (partialDate2 == null) {
            if (dateOrTimeProperty.partialDate != null) {
                return false;
            }
        } else if (!partialDate2.equals(dateOrTimeProperty.partialDate)) {
            return false;
        }
        String str = this.text;
        if (str == null) {
            if (dateOrTimeProperty.text != null) {
                return false;
            }
        } else if (!str.equals(dateOrTimeProperty.text)) {
            return false;
        }
        return true;
    }
}
