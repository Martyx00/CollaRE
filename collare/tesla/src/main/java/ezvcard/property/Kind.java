package ezvcard.property;

import ezvcard.VCardVersion;
import ezvcard.c;

@c(a = {VCardVersion.V4_0})
public class Kind extends TextProperty {
    public static final String APPLICATION = "application";
    public static final String DEVICE = "device";
    public static final String GROUP = "group";
    public static final String INDIVIDUAL = "individual";
    public static final String LOCATION = "location";
    public static final String ORG = "org";

    public Kind(String str) {
        super(str);
    }

    public Kind(Kind kind) {
        super(kind);
    }

    public boolean isIndividual() {
        return INDIVIDUAL.equals(this.value);
    }

    public boolean isGroup() {
        return GROUP.equals(this.value);
    }

    public boolean isOrg() {
        return ORG.equals(this.value);
    }

    public boolean isLocation() {
        return "location".equals(this.value);
    }

    public boolean isApplication() {
        return APPLICATION.equals(this.value);
    }

    public boolean isDevice() {
        return DEVICE.equals(this.value);
    }

    public static Kind individual() {
        return new Kind(INDIVIDUAL);
    }

    public static Kind group() {
        return new Kind(GROUP);
    }

    public static Kind org() {
        return new Kind(ORG);
    }

    public static Kind location() {
        return new Kind("location");
    }

    public static Kind application() {
        return new Kind(APPLICATION);
    }

    public static Kind device() {
        return new Kind(DEVICE);
    }

    @Override // ezvcard.property.VCardProperty
    public Kind copy() {
        return new Kind(this);
    }
}
