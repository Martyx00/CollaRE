package com.airbnb.android.react.maps;

import android.content.Context;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.TileOverlay;
import com.google.android.gms.maps.model.TileOverlayOptions;
import com.google.android.gms.maps.model.UrlTileProvider;
import java.net.MalformedURLException;
import java.net.URL;

/* compiled from: AirMapUrlTile */
public class i extends c {

    /* renamed from: a  reason: collision with root package name */
    private TileOverlayOptions f1579a;

    /* renamed from: b  reason: collision with root package name */
    private TileOverlay f1580b;

    /* renamed from: c  reason: collision with root package name */
    private a f1581c;

    /* renamed from: d  reason: collision with root package name */
    private String f1582d;
    private float e;
    private float f;
    private float g;
    private boolean h;

    /* access modifiers changed from: package-private */
    /* compiled from: AirMapUrlTile */
    public class a extends UrlTileProvider {

        /* renamed from: b  reason: collision with root package name */
        private String f1584b;

        public a(int i, int i2, String str) {
            super(i, i2);
            this.f1584b = str;
        }

        @Override // com.google.android.gms.maps.model.UrlTileProvider
        public synchronized URL getTileUrl(int i, int i2, int i3) {
            if (i.this.h) {
                i2 = ((1 << i3) - i2) - 1;
            }
            String replace = this.f1584b.replace("{x}", Integer.toString(i)).replace("{y}", Integer.toString(i2)).replace("{z}", Integer.toString(i3));
            if (i.this.f > BitmapDescriptorFactory.HUE_RED && ((float) i3) > i.this.f) {
                return null;
            }
            if (i.this.g > BitmapDescriptorFactory.HUE_RED && ((float) i3) < i.this.g) {
                return null;
            }
            try {
                return new URL(replace);
            } catch (MalformedURLException e) {
                throw new AssertionError(e);
            }
        }

        public void a(String str) {
            this.f1584b = str;
        }
    }

    public i(Context context) {
        super(context);
    }

    public void setUrlTemplate(String str) {
        this.f1582d = str;
        a aVar = this.f1581c;
        if (aVar != null) {
            aVar.a(str);
        }
        TileOverlay tileOverlay = this.f1580b;
        if (tileOverlay != null) {
            tileOverlay.clearTileCache();
        }
    }

    public void setZIndex(float f2) {
        this.e = f2;
        TileOverlay tileOverlay = this.f1580b;
        if (tileOverlay != null) {
            tileOverlay.setZIndex(f2);
        }
    }

    public void setMaximumZ(float f2) {
        this.f = f2;
        TileOverlay tileOverlay = this.f1580b;
        if (tileOverlay != null) {
            tileOverlay.clearTileCache();
        }
    }

    public void setMinimumZ(float f2) {
        this.g = f2;
        TileOverlay tileOverlay = this.f1580b;
        if (tileOverlay != null) {
            tileOverlay.clearTileCache();
        }
    }

    public void setFlipY(boolean z) {
        this.h = z;
        TileOverlay tileOverlay = this.f1580b;
        if (tileOverlay != null) {
            tileOverlay.clearTileCache();
        }
    }

    public TileOverlayOptions getTileOverlayOptions() {
        if (this.f1579a == null) {
            this.f1579a = a();
        }
        return this.f1579a;
    }

    private TileOverlayOptions a() {
        TileOverlayOptions tileOverlayOptions = new TileOverlayOptions();
        tileOverlayOptions.zIndex(this.e);
        this.f1581c = new a(256, 256, this.f1582d);
        tileOverlayOptions.tileProvider(this.f1581c);
        return tileOverlayOptions;
    }

    @Override // com.airbnb.android.react.maps.c
    public Object getFeature() {
        return this.f1580b;
    }

    public void a(GoogleMap googleMap) {
        this.f1580b = googleMap.addTileOverlay(getTileOverlayOptions());
    }

    @Override // com.airbnb.android.react.maps.c
    public void b(GoogleMap googleMap) {
        this.f1580b.remove();
    }
}
