package ezvcard.property;

import com.google.android.gms.common.internal.ImagesContract;
import ezvcard.VCard;
import ezvcard.VCardVersion;
import ezvcard.d;
import ezvcard.parameter.MediaTypeParameter;
import ezvcard.parameter.c;
import ezvcard.util.e;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public abstract class BinaryProperty<T extends MediaTypeParameter> extends VCardProperty implements HasAltId {
    protected T contentType;
    protected byte[] data;
    protected String url;

    public BinaryProperty() {
    }

    public BinaryProperty(String str, T t) {
        setUrl(str, t);
    }

    public BinaryProperty(byte[] bArr, T t) {
        setData(bArr, t);
    }

    public BinaryProperty(InputStream inputStream, T t) {
        this(new e(inputStream).a(), t);
    }

    public BinaryProperty(File file, T t) {
        this(new BufferedInputStream(new FileInputStream(file)), t);
    }

    public BinaryProperty(BinaryProperty<T> binaryProperty) {
        super(binaryProperty);
        byte[] bArr = binaryProperty.data;
        this.data = bArr == null ? null : (byte[]) bArr.clone();
        this.url = binaryProperty.url;
        this.contentType = binaryProperty.contentType;
    }

    public byte[] getData() {
        return this.data;
    }

    public void setData(byte[] bArr, T t) {
        this.url = null;
        this.data = bArr;
        setContentType(t);
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String str, T t) {
        this.url = str;
        this.data = null;
        setContentType(t);
    }

    public T getContentType() {
        return this.contentType;
    }

    public void setContentType(T t) {
        this.contentType = t;
    }

    public String getType() {
        return this.parameters.o();
    }

    public void setType(String str) {
        this.parameters.f(str);
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
        if (this.url == null && this.data == null) {
            list.add(new d(8, new Object[0]));
        }
    }

    /* access modifiers changed from: protected */
    @Override // ezvcard.property.VCardProperty
    public Map<String, Object> toStringValues() {
        String str;
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        if (this.data == null) {
            str = "null";
        } else {
            str = "length: " + this.data.length;
        }
        linkedHashMap.put("data", str);
        linkedHashMap.put(ImagesContract.URL, this.url);
        linkedHashMap.put("contentType", this.contentType);
        return linkedHashMap;
    }

    @Override // ezvcard.property.VCardProperty
    public int hashCode() {
        int hashCode = super.hashCode() * 31;
        T t = this.contentType;
        int i = 0;
        int hashCode2 = (((hashCode + (t == null ? 0 : t.hashCode())) * 31) + Arrays.hashCode(this.data)) * 31;
        String str = this.url;
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
        BinaryProperty binaryProperty = (BinaryProperty) obj;
        T t = this.contentType;
        if (t == null) {
            if (binaryProperty.contentType != null) {
                return false;
            }
        } else if (!t.equals(binaryProperty.contentType)) {
            return false;
        }
        if (!Arrays.equals(this.data, binaryProperty.data)) {
            return false;
        }
        String str = this.url;
        if (str == null) {
            if (binaryProperty.url != null) {
                return false;
            }
        } else if (!str.equals(binaryProperty.url)) {
            return false;
        }
        return true;
    }
}
