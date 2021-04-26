package ezvcard.property;

import java.util.UUID;

public class Uid extends UriProperty {
    public Uid(String str) {
        super(str);
    }

    public Uid(Uid uid) {
        super(uid);
    }

    public static Uid random() {
        String uuid = UUID.randomUUID().toString();
        return new Uid("urn:uuid:" + uuid);
    }

    @Override // ezvcard.property.VCardProperty
    public Uid copy() {
        return new Uid(this);
    }
}
