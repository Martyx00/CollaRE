package com.airbnb.android.react.maps;

import android.content.Context;
import android.graphics.Bitmap;
import com.facebook.react.bridge.ReadableArray;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.GroundOverlay;
import com.google.android.gms.maps.model.GroundOverlayOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;

/* compiled from: AirMapOverlay */
public class f extends c implements m {

    /* renamed from: a  reason: collision with root package name */
    private GroundOverlayOptions f1567a;

    /* renamed from: b  reason: collision with root package name */
    private GroundOverlay f1568b;

    /* renamed from: c  reason: collision with root package name */
    private LatLngBounds f1569c;

    /* renamed from: d  reason: collision with root package name */
    private BitmapDescriptor f1570d;
    private Bitmap e;
    private float f;
    private final n g;
    private GoogleMap h;

    public f(Context context) {
        super(context);
        this.g = new n(context, getResources(), this);
    }

    public void setBounds(ReadableArray readableArray) {
        this.f1569c = new LatLngBounds(new LatLng(readableArray.getArray(1).getDouble(0), readableArray.getArray(0).getDouble(1)), new LatLng(readableArray.getArray(0).getDouble(0), readableArray.getArray(1).getDouble(1)));
        GroundOverlay groundOverlay = this.f1568b;
        if (groundOverlay != null) {
            groundOverlay.setPositionFromBounds(this.f1569c);
        }
    }

    public void setZIndex(float f2) {
        this.f = f2;
        GroundOverlay groundOverlay = this.f1568b;
        if (groundOverlay != null) {
            groundOverlay.setZIndex(f2);
        }
    }

    public void setImage(String str) {
        this.g.a(str);
    }

    public GroundOverlayOptions getGroundOverlayOptions() {
        if (this.f1567a == null) {
            this.f1567a = b();
        }
        return this.f1567a;
    }

    private GroundOverlayOptions b() {
        GroundOverlayOptions groundOverlayOptions = this.f1567a;
        if (groundOverlayOptions != null) {
            return groundOverlayOptions;
        }
        if (this.f1570d == null) {
            return null;
        }
        GroundOverlayOptions groundOverlayOptions2 = new GroundOverlayOptions();
        groundOverlayOptions2.image(this.f1570d);
        groundOverlayOptions2.positionFromBounds(this.f1569c);
        groundOverlayOptions2.zIndex(this.f);
        return groundOverlayOptions2;
    }

    @Override // com.airbnb.android.react.maps.c
    public Object getFeature() {
        return this.f1568b;
    }

    public void a(GoogleMap googleMap) {
        GroundOverlayOptions groundOverlayOptions = getGroundOverlayOptions();
        if (groundOverlayOptions != null) {
            this.f1568b = googleMap.addGroundOverlay(groundOverlayOptions);
            this.f1568b.setClickable(true);
            return;
        }
        this.h = googleMap;
    }

    @Override // com.airbnb.android.react.maps.c
    public void b(GoogleMap googleMap) {
        this.h = null;
        GroundOverlay groundOverlay = this.f1568b;
        if (groundOverlay != null) {
            groundOverlay.remove();
            this.f1568b = null;
            this.f1567a = null;
        }
    }

    @Override // com.airbnb.android.react.maps.m
    public void setIconBitmap(Bitmap bitmap) {
        this.e = bitmap;
    }

    @Override // com.airbnb.android.react.maps.m
    public void setIconBitmapDescriptor(BitmapDescriptor bitmapDescriptor) {
        this.f1570d = bitmapDescriptor;
    }

    @Override // com.airbnb.android.react.maps.m
    public void a() {
        this.f1568b = getGroundOverlay();
        GroundOverlay groundOverlay = this.f1568b;
        if (groundOverlay != null) {
            groundOverlay.setImage(this.f1570d);
            this.f1568b.setClickable(true);
        }
    }

    private GroundOverlay getGroundOverlay() {
        GroundOverlayOptions groundOverlayOptions;
        GroundOverlay groundOverlay = this.f1568b;
        if (groundOverlay != null) {
            return groundOverlay;
        }
        if (this.h == null || (groundOverlayOptions = getGroundOverlayOptions()) == null) {
            return null;
        }
        return this.h.addGroundOverlay(groundOverlayOptions);
    }
}
