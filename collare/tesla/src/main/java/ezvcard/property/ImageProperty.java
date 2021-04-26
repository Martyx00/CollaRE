package ezvcard.property;

import ezvcard.parameter.ImageType;
import java.io.File;
import java.io.InputStream;

public class ImageProperty extends BinaryProperty<ImageType> {
    public ImageProperty(String str, ImageType imageType) {
        super(str, imageType);
    }

    public ImageProperty(byte[] bArr, ImageType imageType) {
        super(bArr, imageType);
    }

    public ImageProperty(InputStream inputStream, ImageType imageType) {
        super(inputStream, imageType);
    }

    public ImageProperty(File file, ImageType imageType) {
        super(file, imageType);
    }

    public ImageProperty(ImageProperty imageProperty) {
        super(imageProperty);
    }
}
