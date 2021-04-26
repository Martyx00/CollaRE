package ezvcard.property;

import java.util.Date;

public class Revision extends SimpleProperty<Date> {
    public Revision(Date date) {
        super(date);
    }

    public Revision(Revision revision) {
        super((SimpleProperty) revision);
        this.value = revision.value == null ? null : new Date(((Date) revision.value).getTime());
    }

    public static Revision now() {
        return new Revision(new Date());
    }

    @Override // ezvcard.property.VCardProperty
    public Revision copy() {
        return new Revision(this);
    }
}
