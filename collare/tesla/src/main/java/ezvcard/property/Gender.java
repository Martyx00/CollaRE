package ezvcard.property;

import ezvcard.VCard;
import ezvcard.VCardVersion;
import ezvcard.c;
import ezvcard.d;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.spongycastle.i18n.TextBundle;

@c(a = {VCardVersion.V4_0})
public class Gender extends VCardProperty {
    public static final String FEMALE = "F";
    public static final String MALE = "M";
    public static final String NONE = "N";
    public static final String OTHER = "O";
    public static final String UNKNOWN = "U";
    private String gender;
    private String text;

    public Gender(String str) {
        this.gender = str;
    }

    public Gender(Gender gender2) {
        super(gender2);
        this.gender = gender2.gender;
        this.text = gender2.text;
    }

    public String getText() {
        return this.text;
    }

    public void setText(String str) {
        this.text = str;
    }

    public String getGender() {
        return this.gender;
    }

    public void setGender(String str) {
        this.gender = str;
    }

    public boolean isMale() {
        return MALE.equals(this.gender);
    }

    public boolean isFemale() {
        return FEMALE.equals(this.gender);
    }

    public boolean isOther() {
        return OTHER.equals(this.gender);
    }

    public boolean isNone() {
        return NONE.equals(this.gender);
    }

    public boolean isUnknown() {
        return UNKNOWN.equals(this.gender);
    }

    public static Gender male() {
        return new Gender(MALE);
    }

    public static Gender female() {
        return new Gender(FEMALE);
    }

    public static Gender other() {
        return new Gender(OTHER);
    }

    public static Gender none() {
        return new Gender(NONE);
    }

    public static Gender unknown() {
        return new Gender(UNKNOWN);
    }

    /* access modifiers changed from: protected */
    @Override // ezvcard.property.VCardProperty
    public void _validate(List<d> list, VCardVersion vCardVersion, VCard vCard) {
        if (this.gender == null) {
            list.add(new d(8, new Object[0]));
        }
    }

    /* access modifiers changed from: protected */
    @Override // ezvcard.property.VCardProperty
    public Map<String, Object> toStringValues() {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("gender", this.gender);
        linkedHashMap.put(TextBundle.TEXT_ENTRY, this.text);
        return linkedHashMap;
    }

    @Override // ezvcard.property.VCardProperty
    public Gender copy() {
        return new Gender(this);
    }

    @Override // ezvcard.property.VCardProperty
    public int hashCode() {
        int hashCode = super.hashCode() * 31;
        String str = this.gender;
        int i = 0;
        int hashCode2 = (hashCode + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.text;
        if (str2 != null) {
            i = str2.hashCode();
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
        Gender gender2 = (Gender) obj;
        String str = this.gender;
        if (str == null) {
            if (gender2.gender != null) {
                return false;
            }
        } else if (!str.equals(gender2.gender)) {
            return false;
        }
        String str2 = this.text;
        if (str2 == null) {
            if (gender2.text != null) {
                return false;
            }
        } else if (!str2.equals(gender2.text)) {
            return false;
        }
        return true;
    }
}
