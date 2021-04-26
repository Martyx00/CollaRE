package com.google.android.gms.common.images;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.ImagesContract;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.Locale;
import org.json.JSONException;
import org.json.JSONObject;

@SafeParcelable.Class(creator = "WebImageCreator")
public final class WebImage extends AbstractSafeParcelable {
    public static final Parcelable.Creator<WebImage> CREATOR = new zae();
    @SafeParcelable.VersionField(id = 1)
    private final int zalf;
    @SafeParcelable.Field(getter = "getWidth", id = 3)
    private final int zane;
    @SafeParcelable.Field(getter = "getHeight", id = 4)
    private final int zanf;
    @SafeParcelable.Field(getter = "getUrl", id = 2)
    private final Uri zang;

    @SafeParcelable.Constructor
    WebImage(@SafeParcelable.Param(id = 1) int i, @SafeParcelable.Param(id = 2) Uri uri, @SafeParcelable.Param(id = 3) int i2, @SafeParcelable.Param(id = 4) int i3) {
        this.zalf = i;
        this.zang = uri;
        this.zane = i2;
        this.zanf = i3;
    }

    public WebImage(Uri uri, int i, int i2) {
        this(1, uri, i, i2);
        if (uri == null) {
            throw new IllegalArgumentException("url cannot be null");
        } else if (i < 0 || i2 < 0) {
            throw new IllegalArgumentException("width and height must not be negative");
        }
    }

    public WebImage(Uri uri) {
        this(uri, 0, 0);
    }

    @KeepForSdk
    public WebImage(JSONObject jSONObject) {
        this(zaa(jSONObject), jSONObject.optInt("width", 0), jSONObject.optInt("height", 0));
    }

    private static Uri zaa(JSONObject jSONObject) {
        if (jSONObject.has(ImagesContract.URL)) {
            try {
                return Uri.parse(jSONObject.getString(ImagesContract.URL));
            } catch (JSONException unused) {
            }
        }
        return null;
    }

    public final Uri getUrl() {
        return this.zang;
    }

    public final int getWidth() {
        return this.zane;
    }

    public final int getHeight() {
        return this.zanf;
    }

    public final String toString() {
        return String.format(Locale.US, "Image %dx%d %s", Integer.valueOf(this.zane), Integer.valueOf(this.zanf), this.zang.toString());
    }

    @KeepForSdk
    public final JSONObject toJson() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(ImagesContract.URL, this.zang.toString());
            jSONObject.put("width", this.zane);
            jSONObject.put("height", this.zanf);
        } catch (JSONException unused) {
        }
        return jSONObject;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof WebImage)) {
            return false;
        }
        WebImage webImage = (WebImage) obj;
        return Objects.equal(this.zang, webImage.zang) && this.zane == webImage.zane && this.zanf == webImage.zanf;
    }

    public final int hashCode() {
        return Objects.hashCode(this.zang, Integer.valueOf(this.zane), Integer.valueOf(this.zanf));
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.zalf);
        SafeParcelWriter.writeParcelable(parcel, 2, getUrl(), i, false);
        SafeParcelWriter.writeInt(parcel, 3, getWidth());
        SafeParcelWriter.writeInt(parcel, 4, getHeight());
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
