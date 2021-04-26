package com.airbnb.android.react.maps;

import android.content.Context;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Tile;
import com.google.android.gms.maps.model.TileOverlay;
import com.google.android.gms.maps.model.TileOverlayOptions;
import com.google.android.gms.maps.model.TileProvider;

/* compiled from: AirMapLocalTile */
public class d extends c {

    /* renamed from: a  reason: collision with root package name */
    private TileOverlayOptions f1554a;

    /* renamed from: b  reason: collision with root package name */
    private TileOverlay f1555b;

    /* renamed from: c  reason: collision with root package name */
    private a f1556c;

    /* renamed from: d  reason: collision with root package name */
    private String f1557d;
    private float e;
    private float f;

    /* access modifiers changed from: package-private */
    /* compiled from: AirMapLocalTile */
    public class a implements TileProvider {

        /* renamed from: b  reason: collision with root package name */
        private int f1559b;

        /* renamed from: c  reason: collision with root package name */
        private String f1560c;

        public a(int i, String str) {
            this.f1559b = i;
            this.f1560c = str;
        }

        @Override // com.google.android.gms.maps.model.TileProvider
        public Tile getTile(int i, int i2, int i3) {
            byte[] a2 = a(i, i2, i3);
            if (a2 == null) {
                return TileProvider.NO_TILE;
            }
            int i4 = this.f1559b;
            return new Tile(i4, i4, a2);
        }

        public void a(String str) {
            this.f1560c = str;
        }

        public void a(int i) {
            this.f1559b = i;
        }

        /* JADX WARNING: Can't wrap try/catch for region: R(9:6|7|(2:8|(1:10)(1:63))|11|12|13|14|15|16) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:14:0x002e */
        /* JADX WARNING: Removed duplicated region for block: B:33:0x004e A[SYNTHETIC, Splitter:B:33:0x004e] */
        /* JADX WARNING: Removed duplicated region for block: B:37:0x0055 A[SYNTHETIC, Splitter:B:37:0x0055] */
        /* JADX WARNING: Removed duplicated region for block: B:45:0x0061 A[SYNTHETIC, Splitter:B:45:0x0061] */
        /* JADX WARNING: Removed duplicated region for block: B:49:0x0068 A[SYNTHETIC, Splitter:B:49:0x0068] */
        /* JADX WARNING: Removed duplicated region for block: B:55:0x006f A[SYNTHETIC, Splitter:B:55:0x006f] */
        /* JADX WARNING: Removed duplicated region for block: B:59:0x0076 A[SYNTHETIC, Splitter:B:59:0x0076] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private byte[] a(int r7, int r8, int r9) {
            /*
            // Method dump skipped, instructions count: 122
            */
            throw new UnsupportedOperationException("Method not decompiled: com.airbnb.android.react.maps.d.a.a(int, int, int):byte[]");
        }

        private String b(int i, int i2, int i3) {
            return this.f1560c.replace("{x}", Integer.toString(i)).replace("{y}", Integer.toString(i2)).replace("{z}", Integer.toString(i3));
        }
    }

    public d(Context context) {
        super(context);
    }

    public void setPathTemplate(String str) {
        this.f1557d = str;
        a aVar = this.f1556c;
        if (aVar != null) {
            aVar.a(str);
        }
        TileOverlay tileOverlay = this.f1555b;
        if (tileOverlay != null) {
            tileOverlay.clearTileCache();
        }
    }

    public void setZIndex(float f2) {
        this.f = f2;
        TileOverlay tileOverlay = this.f1555b;
        if (tileOverlay != null) {
            tileOverlay.setZIndex(f2);
        }
    }

    public void setTileSize(float f2) {
        this.e = f2;
        a aVar = this.f1556c;
        if (aVar != null) {
            aVar.a((int) f2);
        }
    }

    public TileOverlayOptions getTileOverlayOptions() {
        if (this.f1554a == null) {
            this.f1554a = a();
        }
        return this.f1554a;
    }

    private TileOverlayOptions a() {
        TileOverlayOptions tileOverlayOptions = new TileOverlayOptions();
        tileOverlayOptions.zIndex(this.f);
        this.f1556c = new a((int) this.e, this.f1557d);
        tileOverlayOptions.tileProvider(this.f1556c);
        return tileOverlayOptions;
    }

    @Override // com.airbnb.android.react.maps.c
    public Object getFeature() {
        return this.f1555b;
    }

    public void a(GoogleMap googleMap) {
        this.f1555b = googleMap.addTileOverlay(getTileOverlayOptions());
    }

    @Override // com.airbnb.android.react.maps.c
    public void b(GoogleMap googleMap) {
        this.f1555b.remove();
    }
}
