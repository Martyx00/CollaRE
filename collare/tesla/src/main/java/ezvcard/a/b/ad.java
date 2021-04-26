package ezvcard.a.b;

import ezvcard.parameter.ImageType;
import ezvcard.property.Logo;

/* compiled from: LogoScribe */
public class ad extends v<Logo> {
    public ad() {
        super(Logo.class, "LOGO");
    }

    /* access modifiers changed from: protected */
    public Logo a(String str, ImageType imageType) {
        return new Logo(str, imageType);
    }

    /* access modifiers changed from: protected */
    public Logo a(byte[] bArr, ImageType imageType) {
        return new Logo(bArr, imageType);
    }
}
