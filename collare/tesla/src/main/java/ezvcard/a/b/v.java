package ezvcard.a.b;

import ezvcard.parameter.ImageType;
import ezvcard.property.ImageProperty;

/* compiled from: ImagePropertyScribe */
public abstract class v<T extends ImageProperty> extends d<T, ImageType> {
    public v(Class<T> cls, String str) {
        super(cls, str);
    }

    /* access modifiers changed from: protected */
    /* renamed from: e */
    public ImageType b(String str) {
        return ImageType.b(str, null, null);
    }

    /* access modifiers changed from: protected */
    /* renamed from: f */
    public ImageType a(String str) {
        return ImageType.b(null, str, null);
    }

    /* access modifiers changed from: protected */
    /* renamed from: g */
    public ImageType c(String str) {
        return ImageType.a(null, null, str);
    }
}
