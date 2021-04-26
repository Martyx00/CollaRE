package com.airbnb.android.react.maps;

import android.content.Context;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.TileOverlay;
import com.google.android.gms.maps.model.TileOverlayOptions;
import com.google.android.gms.maps.model.UrlTileProvider;
import java.net.MalformedURLException;
import java.net.URL;

/* compiled from: AirMapWMSTile */
public class k extends c {

    /* renamed from: a  reason: collision with root package name */
    private static final double[] f1614a = {-2.003750834789244E7d, 2.003750834789244E7d};

    /* renamed from: b  reason: collision with root package name */
    private TileOverlayOptions f1615b;

    /* renamed from: c  reason: collision with root package name */
    private TileOverlay f1616c;

    /* renamed from: d  reason: collision with root package name */
    private a f1617d;
    private String e;
    private float f;
    private float g;
    private float h;
    private int i;
    private float j;

    /* access modifiers changed from: package-private */
    /* compiled from: AirMapWMSTile */
    public class a extends UrlTileProvider {

        /* renamed from: b  reason: collision with root package name */
        private String f1619b;

        /* renamed from: c  reason: collision with root package name */
        private int f1620c;

        /* renamed from: d  reason: collision with root package name */
        private int f1621d;

        public a(int i, int i2, String str) {
            super(i, i2);
            this.f1619b = str;
            this.f1620c = i;
            this.f1621d = i2;
        }

        @Override // com.google.android.gms.maps.model.UrlTileProvider
        public synchronized URL getTileUrl(int i, int i2, int i3) {
            if (k.this.g > BitmapDescriptorFactory.HUE_RED && ((float) i3) > k.this.g) {
                return null;
            }
            if (k.this.h > BitmapDescriptorFactory.HUE_RED && ((float) i3) < k.this.h) {
                return null;
            }
            double[] a2 = a(i, i2, i3);
            try {
                return new URL(this.f1619b.replace("{minX}", Double.toString(a2[0])).replace("{minY}", Double.toString(a2[1])).replace("{maxX}", Double.toString(a2[2])).replace("{maxY}", Double.toString(a2[3])).replace("{width}", Integer.toString(this.f1620c)).replace("{height}", Integer.toString(this.f1621d)));
            } catch (MalformedURLException e) {
                throw new AssertionError(e);
            }
        }

        private double[] a(int i, int i2, int i3) {
            double pow = 4.007501669578488E7d / Math.pow(2.0d, (double) i3);
            return new double[]{k.f1614a[0] + (((double) i) * pow), k.f1614a[1] - (((double) (i2 + 1)) * pow), k.f1614a[0] + (((double) (i + 1)) * pow), k.f1614a[1] - (((double) i2) * pow)};
        }

        public void a(String str) {
            this.f1619b = str;
        }
    }

    public k(Context context) {
        super(context);
    }

    public void setUrlTemplate(String str) {
        this.e = str;
        a aVar = this.f1617d;
        if (aVar != null) {
            aVar.a(str);
        }
        TileOverlay tileOverlay = this.f1616c;
        if (tileOverlay != null) {
            tileOverlay.clearTileCache();
        }
    }

    public void setZIndex(float f2) {
        this.f = f2;
        TileOverlay tileOverlay = this.f1616c;
        if (tileOverlay != null) {
            tileOverlay.setZIndex(f2);
        }
    }

    public void setMaximumZ(float f2) {
        this.g = f2;
        TileOverlay tileOverlay = this.f1616c;
        if (tileOverlay != null) {
            tileOverlay.clearTileCache();
        }
    }

    public void setMinimumZ(float f2) {
        this.h = f2;
        TileOverlay tileOverlay = this.f1616c;
        if (tileOverlay != null) {
            tileOverlay.clearTileCache();
        }
    }

    public void setTileSize(int i2) {
        this.i = i2;
        TileOverlay tileOverlay = this.f1616c;
        if (tileOverlay != null) {
            tileOverlay.clearTileCache();
        }
    }

    public void setOpacity(float f2) {
        this.j = f2;
        TileOverlay tileOverlay = this.f1616c;
        if (tileOverlay != null) {
            tileOverlay.setTransparency(1.0f - f2);
        }
    }

    public TileOverlayOptions getTileOverlayOptions() {
        if (this.f1615b == null) {
            this.f1615b = b();
        }
        return this.f1615b;
    }

    private TileOverlayOptions b() {
        TileOverlayOptions tileOverlayOptions = new TileOverlayOptions();
        tileOverlayOptions.zIndex(this.f);
        tileOverlayOptions.transparency(1.0f - this.j);
        int i2 = this.i;
        this.f1617d = new a(i2, i2, this.e);
        tileOverlayOptions.tileProvider(this.f1617d);
        return tileOverlayOptions;
    }

    @Override // com.airbnb.android.react.maps.c
    public Object getFeature() {
        return this.f1616c;
    }

    public void a(GoogleMap googleMap) {
        this.f1616c = googleMap.addTileOverlay(getTileOverlayOptions());
    }

    @Override // com.airbnb.android.react.maps.c
    public void b(GoogleMap googleMap) {
        this.f1616c.remove();
    }
}
