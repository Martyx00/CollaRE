package ezvcard.a.b;

import ezvcard.parameter.ImageType;
import ezvcard.property.Photo;

/* compiled from: PhotoScribe */
public class ak extends v<Photo> {
    public ak() {
        super(Photo.class, "PHOTO");
    }

    /* access modifiers changed from: protected */
    public Photo a(String str, ImageType imageType) {
        return new Photo(str, imageType);
    }

    /* access modifiers changed from: protected */
    public Photo a(byte[] bArr, ImageType imageType) {
        return new Photo(bArr, imageType);
    }
}
