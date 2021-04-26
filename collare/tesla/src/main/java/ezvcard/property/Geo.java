package ezvcard.property;

import ezvcard.VCard;
import ezvcard.VCardVersion;
import ezvcard.d;
import ezvcard.parameter.c;
import ezvcard.util.GeoUri;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Geo extends VCardProperty implements HasAltId {
    private GeoUri uri;

    public Geo(Double d2, Double d3) {
        this(new GeoUri.a(d2, d3).a());
    }

    public Geo(GeoUri geoUri) {
        this.uri = geoUri;
    }

    public Geo(Geo geo) {
        super(geo);
        this.uri = geo.uri;
    }

    public Double getLatitude() {
        GeoUri geoUri = this.uri;
        if (geoUri == null) {
            return null;
        }
        return geoUri.a();
    }

    public void setLatitude(Double d2) {
        GeoUri geoUri = this.uri;
        if (geoUri == null) {
            this.uri = new GeoUri.a(d2, null).a();
        } else {
            this.uri = new GeoUri.a(geoUri).a(d2).a();
        }
    }

    public Double getLongitude() {
        GeoUri geoUri = this.uri;
        if (geoUri == null) {
            return null;
        }
        return geoUri.b();
    }

    public void setLongitude(Double d2) {
        GeoUri geoUri = this.uri;
        if (geoUri == null) {
            this.uri = new GeoUri.a(null, d2).a();
        } else {
            this.uri = new GeoUri.a(geoUri).b(d2).a();
        }
    }

    public GeoUri getGeoUri() {
        return this.uri;
    }

    public void setGeoUri(GeoUri geoUri) {
        this.uri = geoUri;
    }

    public String getType() {
        return this.parameters.o();
    }

    public void setType(String str) {
        this.parameters.f(str);
    }

    public String getMediaType() {
        return this.parameters.j();
    }

    public void setMediaType(String str) {
        this.parameters.e(str);
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
        if (getLatitude() == null) {
            list.add(new d(13, new Object[0]));
        }
        if (getLongitude() == null) {
            list.add(new d(14, new Object[0]));
        }
    }

    /* access modifiers changed from: protected */
    @Override // ezvcard.property.VCardProperty
    public Map<String, Object> toStringValues() {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("uri", this.uri);
        return linkedHashMap;
    }

    @Override // ezvcard.property.VCardProperty
    public Geo copy() {
        return new Geo(this);
    }

    @Override // ezvcard.property.VCardProperty
    public int hashCode() {
        int hashCode = super.hashCode() * 31;
        GeoUri geoUri = this.uri;
        return hashCode + (geoUri == null ? 0 : geoUri.hashCode());
    }

    @Override // ezvcard.property.VCardProperty
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!super.equals(obj)) {
            return false;
        }
        Geo geo = (Geo) obj;
        GeoUri geoUri = this.uri;
        if (geoUri == null) {
            if (geo.uri != null) {
                return false;
            }
        } else if (!geoUri.equals(geo.uri)) {
            return false;
        }
        return true;
    }
}
