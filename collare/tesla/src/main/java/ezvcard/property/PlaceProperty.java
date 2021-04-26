package ezvcard.property;

import ezvcard.VCard;
import ezvcard.VCardVersion;
import ezvcard.d;
import ezvcard.util.GeoUri;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.spongycastle.i18n.TextBundle;

public class PlaceProperty extends VCardProperty implements HasAltId {
    protected GeoUri geoUri;
    protected String text;
    protected String uri;

    public PlaceProperty() {
    }

    public PlaceProperty(double d2, double d3) {
        setCoordinates(d2, d3);
    }

    public PlaceProperty(String str) {
        setText(str);
    }

    public PlaceProperty(PlaceProperty placeProperty) {
        super(placeProperty);
        this.geoUri = placeProperty.geoUri;
        this.uri = placeProperty.uri;
        this.text = placeProperty.text;
    }

    public Double getLatitude() {
        GeoUri geoUri2 = this.geoUri;
        if (geoUri2 == null) {
            return null;
        }
        return geoUri2.a();
    }

    public Double getLongitude() {
        GeoUri geoUri2 = this.geoUri;
        if (geoUri2 == null) {
            return null;
        }
        return geoUri2.b();
    }

    public GeoUri getGeoUri() {
        return this.geoUri;
    }

    public void setCoordinates(double d2, double d3) {
        setGeoUri(new GeoUri.a(Double.valueOf(d2), Double.valueOf(d3)).a());
    }

    public void setGeoUri(GeoUri geoUri2) {
        this.geoUri = geoUri2;
        this.uri = null;
        this.text = null;
    }

    public String getUri() {
        return this.uri;
    }

    public void setUri(String str) {
        this.uri = str;
        this.geoUri = null;
        this.text = null;
    }

    public String getText() {
        return this.text;
    }

    public void setText(String str) {
        this.text = str;
        this.geoUri = null;
        this.uri = null;
    }

    @Override // ezvcard.property.HasAltId
    public String getAltId() {
        return this.parameters.a();
    }

    @Override // ezvcard.property.HasAltId
    public void setAltId(String str) {
        this.parameters.a(str);
    }

    @Override // ezvcard.property.VCardProperty
    public String getLanguage() {
        return super.getLanguage();
    }

    @Override // ezvcard.property.VCardProperty
    public void setLanguage(String str) {
        super.setLanguage(str);
    }

    /* access modifiers changed from: protected */
    @Override // ezvcard.property.VCardProperty
    public void _validate(List<d> list, VCardVersion vCardVersion, VCard vCard) {
        if (this.uri == null && this.text == null && this.geoUri == null) {
            list.add(new d(8, new Object[0]));
        }
    }

    /* access modifiers changed from: protected */
    @Override // ezvcard.property.VCardProperty
    public Map<String, Object> toStringValues() {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("geoUri", this.geoUri);
        linkedHashMap.put("uri", this.uri);
        linkedHashMap.put(TextBundle.TEXT_ENTRY, this.text);
        return linkedHashMap;
    }

    @Override // ezvcard.property.VCardProperty
    public int hashCode() {
        int hashCode = super.hashCode() * 31;
        GeoUri geoUri2 = this.geoUri;
        int i = 0;
        int hashCode2 = (hashCode + (geoUri2 == null ? 0 : geoUri2.hashCode())) * 31;
        String str = this.text;
        int hashCode3 = (hashCode2 + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.uri;
        if (str2 != null) {
            i = str2.hashCode();
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
        PlaceProperty placeProperty = (PlaceProperty) obj;
        GeoUri geoUri2 = this.geoUri;
        if (geoUri2 == null) {
            if (placeProperty.geoUri != null) {
                return false;
            }
        } else if (!geoUri2.equals(placeProperty.geoUri)) {
            return false;
        }
        String str = this.text;
        if (str == null) {
            if (placeProperty.text != null) {
                return false;
            }
        } else if (!str.equals(placeProperty.text)) {
            return false;
        }
        String str2 = this.uri;
        if (str2 == null) {
            if (placeProperty.uri != null) {
                return false;
            }
        } else if (!str2.equals(placeProperty.uri)) {
            return false;
        }
        return true;
    }
}
