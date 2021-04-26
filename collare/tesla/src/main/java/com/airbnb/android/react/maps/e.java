package com.airbnb.android.react.maps;

import android.animation.ObjectAnimator;
import android.animation.TypeEvaluator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Property;
import android.view.View;
import android.widget.LinearLayout;
import com.facebook.c.c;
import com.facebook.common.h.a;
import com.facebook.f.c.d;
import com.facebook.f.e.q;
import com.facebook.f.i.b;
import com.facebook.react.bridge.ReadableMap;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

/* compiled from: AirMapMarker */
public class e extends c {
    private boolean A = false;
    private boolean B = true;
    private boolean C = false;
    private final AirMapMarkerManager D;
    private String E;
    private final b<?> F;
    private c<a<com.facebook.imagepipeline.j.b>> G;
    private final d<com.facebook.imagepipeline.j.e> H = new com.facebook.f.c.c<com.facebook.imagepipeline.j.e>() {
        /* class com.airbnb.android.react.maps.e.AnonymousClass1 */

        /* JADX WARNING: Removed duplicated region for block: B:28:0x008d  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void a(java.lang.String r3, com.facebook.imagepipeline.j.e r4, android.graphics.drawable.Animatable r5) {
            /*
            // Method dump skipped, instructions count: 145
            */
            throw new UnsupportedOperationException("Method not decompiled: com.airbnb.android.react.maps.e.AnonymousClass1.a(java.lang.String, com.facebook.imagepipeline.j.e, android.graphics.drawable.Animatable):void");
        }
    };
    private Bitmap I = null;

    /* renamed from: a  reason: collision with root package name */
    private MarkerOptions f1561a;

    /* renamed from: b  reason: collision with root package name */
    private Marker f1562b;

    /* renamed from: c  reason: collision with root package name */
    private int f1563c;

    /* renamed from: d  reason: collision with root package name */
    private int f1564d;
    private String e;
    private LatLng f;
    private String g;
    private String h;
    private boolean i;
    private float j;
    private float k;
    private a l;
    private View m;
    private final Context n;
    private float o = BitmapDescriptorFactory.HUE_RED;
    private BitmapDescriptor p;
    private Bitmap q;
    private float r = BitmapDescriptorFactory.HUE_RED;
    private boolean s = false;
    private boolean t = false;
    private int u = 0;
    private float v = 1.0f;
    private float w;
    private float x;
    private boolean y;
    private boolean z = true;

    public e(Context context, AirMapMarkerManager airMapMarkerManager) {
        super(context);
        this.n = context;
        this.D = airMapMarkerManager;
        this.F = b.a(g(), context);
        this.F.b();
    }

    public e(Context context, MarkerOptions markerOptions, AirMapMarkerManager airMapMarkerManager) {
        super(context);
        this.n = context;
        this.D = airMapMarkerManager;
        this.F = b.a(g(), context);
        this.F.b();
        this.f = markerOptions.getPosition();
        a((double) markerOptions.getAnchorU(), (double) markerOptions.getAnchorV());
        b((double) markerOptions.getInfoWindowAnchorU(), (double) markerOptions.getInfoWindowAnchorV());
        setTitle(markerOptions.getTitle());
        setSnippet(markerOptions.getSnippet());
        setRotation(markerOptions.getRotation());
        setFlat(markerOptions.isFlat());
        setDraggable(markerOptions.isDraggable());
        setZIndex(Math.round(markerOptions.getZIndex()));
        setAlpha(markerOptions.getAlpha());
        this.p = markerOptions.getIcon();
    }

    private com.facebook.f.f.a g() {
        return new com.facebook.f.f.b(getResources()).a(q.b.f1945c).a(0).r();
    }

    public void setCoordinate(ReadableMap readableMap) {
        this.f = new LatLng(readableMap.getDouble("latitude"), readableMap.getDouble("longitude"));
        Marker marker = this.f1562b;
        if (marker != null) {
            marker.setPosition(this.f);
        }
        a(false);
    }

    public void setIdentifier(String str) {
        this.e = str;
        a(false);
    }

    public String getIdentifier() {
        return this.e;
    }

    public void setTitle(String str) {
        this.g = str;
        Marker marker = this.f1562b;
        if (marker != null) {
            marker.setTitle(str);
        }
        a(false);
    }

    public void setSnippet(String str) {
        this.h = str;
        Marker marker = this.f1562b;
        if (marker != null) {
            marker.setSnippet(str);
        }
        a(false);
    }

    public void setRotation(float f2) {
        this.r = f2;
        Marker marker = this.f1562b;
        if (marker != null) {
            marker.setRotation(f2);
        }
        a(false);
    }

    public void setFlat(boolean z2) {
        this.s = z2;
        Marker marker = this.f1562b;
        if (marker != null) {
            marker.setFlat(z2);
        }
        a(false);
    }

    public void setDraggable(boolean z2) {
        this.t = z2;
        Marker marker = this.f1562b;
        if (marker != null) {
            marker.setDraggable(z2);
        }
        a(false);
    }

    public void setZIndex(int i2) {
        this.u = i2;
        Marker marker = this.f1562b;
        if (marker != null) {
            marker.setZIndex((float) i2);
        }
        a(false);
    }

    public void setOpacity(float f2) {
        this.v = f2;
        Marker marker = this.f1562b;
        if (marker != null) {
            marker.setAlpha(f2);
        }
        a(false);
    }

    public void setMarkerHue(float f2) {
        this.o = f2;
        a(false);
    }

    public void a(double d2, double d3) {
        this.i = true;
        this.j = (float) d2;
        this.k = (float) d3;
        Marker marker = this.f1562b;
        if (marker != null) {
            marker.setAnchor(this.j, this.k);
        }
        a(false);
    }

    public void b(double d2, double d3) {
        this.y = true;
        this.w = (float) d2;
        this.x = (float) d3;
        Marker marker = this.f1562b;
        if (marker != null) {
            marker.setInfoWindowAnchor(this.w, this.x);
        }
        a(false);
    }

    public void setTracksViewChanges(boolean z2) {
        this.z = z2;
        h();
    }

    private void h() {
        boolean z2 = this.z && this.C && this.f1562b != null;
        if (z2 != this.A) {
            this.A = z2;
            if (z2) {
                t.a().a(this);
                return;
            }
            t.a().b(this);
            b();
        }
    }

    public boolean a() {
        if (!this.A) {
            return false;
        }
        b();
        return true;
    }

    public void b() {
        if (this.f1562b != null) {
            if (!this.C) {
                this.B = false;
            }
            Marker marker = this.f1562b;
            if (marker != null) {
                marker.setIcon(getIcon());
            }
        }
    }

    public LatLng a(float f2, LatLng latLng, LatLng latLng2) {
        double d2 = (double) f2;
        return new LatLng(((latLng2.latitude - latLng.latitude) * d2) + latLng.latitude, ((latLng2.longitude - latLng.longitude) * d2) + latLng.longitude);
    }

    public void a(LatLng latLng, Integer num) {
        AnonymousClass2 r0 = new TypeEvaluator<LatLng>() {
            /* class com.airbnb.android.react.maps.e.AnonymousClass2 */

            /* renamed from: a */
            public LatLng evaluate(float f, LatLng latLng, LatLng latLng2) {
                return e.this.a(f, latLng, latLng2);
            }
        };
        Property of = Property.of(Marker.class, LatLng.class, "position");
        ObjectAnimator ofObject = ObjectAnimator.ofObject(this.f1562b, of, r0, latLng);
        ofObject.setDuration((long) num.intValue());
        ofObject.start();
    }

    /* JADX WARNING: Removed duplicated region for block: B:10:0x002e A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:11:0x002f  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setImage(java.lang.String r6) {
        /*
        // Method dump skipped, instructions count: 255
        */
        throw new UnsupportedOperationException("Method not decompiled: com.airbnb.android.react.maps.e.setImage(java.lang.String):void");
    }

    public void a(BitmapDescriptor bitmapDescriptor, Bitmap bitmap) {
        this.p = bitmapDescriptor;
        this.q = bitmap;
        this.B = true;
        a(true);
    }

    public void setIconBitmap(Bitmap bitmap) {
        this.q = bitmap;
    }

    public MarkerOptions getMarkerOptions() {
        if (this.f1561a == null) {
            this.f1561a = new MarkerOptions();
        }
        a(this.f1561a);
        return this.f1561a;
    }

    @Override // android.view.ViewGroup
    public void addView(View view, int i2) {
        super.addView(view, i2);
        if (!(view instanceof a)) {
            this.C = true;
            h();
        }
        a(true);
    }

    @Override // com.facebook.react.views.view.f
    public void requestLayout() {
        super.requestLayout();
        if (getChildCount() == 0 && this.C) {
            this.C = false;
            i();
            h();
            a(true);
        }
    }

    @Override // com.airbnb.android.react.maps.c
    public Object getFeature() {
        return this.f1562b;
    }

    public void a(GoogleMap googleMap) {
        this.f1562b = googleMap.addMarker(getMarkerOptions());
        h();
    }

    @Override // com.airbnb.android.react.maps.c
    public void b(GoogleMap googleMap) {
        this.f1562b.remove();
        this.f1562b = null;
        h();
    }

    private BitmapDescriptor getIcon() {
        if (!this.C) {
            BitmapDescriptor bitmapDescriptor = this.p;
            if (bitmapDescriptor != null) {
                return bitmapDescriptor;
            }
            return BitmapDescriptorFactory.defaultMarker(this.o);
        } else if (this.p == null) {
            return BitmapDescriptorFactory.fromBitmap(j());
        } else {
            Bitmap j2 = j();
            Bitmap createBitmap = Bitmap.createBitmap(Math.max(this.q.getWidth(), j2.getWidth()), Math.max(this.q.getHeight(), j2.getHeight()), this.q.getConfig());
            Canvas canvas = new Canvas(createBitmap);
            canvas.drawBitmap(this.q, BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, (Paint) null);
            canvas.drawBitmap(j2, BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, (Paint) null);
            return BitmapDescriptorFactory.fromBitmap(createBitmap);
        }
    }

    private MarkerOptions a(MarkerOptions markerOptions) {
        markerOptions.position(this.f);
        if (this.i) {
            markerOptions.anchor(this.j, this.k);
        }
        if (this.y) {
            markerOptions.infoWindowAnchor(this.w, this.x);
        }
        markerOptions.title(this.g);
        markerOptions.snippet(this.h);
        markerOptions.rotation(this.r);
        markerOptions.flat(this.s);
        markerOptions.draggable(this.t);
        markerOptions.zIndex((float) this.u);
        markerOptions.alpha(this.v);
        markerOptions.icon(getIcon());
        return markerOptions;
    }

    public void a(boolean z2) {
        if (this.f1562b != null) {
            if (z2) {
                b();
            }
            if (this.i) {
                this.f1562b.setAnchor(this.j, this.k);
            } else {
                this.f1562b.setAnchor(0.5f, 1.0f);
            }
            if (this.y) {
                this.f1562b.setInfoWindowAnchor(this.w, this.x);
            } else {
                this.f1562b.setInfoWindowAnchor(0.5f, BitmapDescriptorFactory.HUE_RED);
            }
        }
    }

    public void a(int i2, int i3) {
        this.f1563c = i2;
        this.f1564d = i3;
        a(true);
    }

    private void i() {
        this.I = null;
    }

    private Bitmap j() {
        int i2 = this.f1563c;
        int i3 = 100;
        if (i2 <= 0) {
            i2 = 100;
        }
        int i4 = this.f1564d;
        if (i4 > 0) {
            i3 = i4;
        }
        buildDrawingCache();
        Bitmap bitmap = this.I;
        if (bitmap == null || bitmap.isRecycled() || bitmap.getWidth() != i2 || bitmap.getHeight() != i3) {
            bitmap = Bitmap.createBitmap(i2, i3, Bitmap.Config.ARGB_8888);
            this.I = bitmap;
        } else {
            bitmap.eraseColor(0);
        }
        draw(new Canvas(bitmap));
        return bitmap;
    }

    public void setCalloutView(a aVar) {
        this.l = aVar;
    }

    public a getCalloutView() {
        return this.l;
    }

    public View getCallout() {
        if (this.l == null) {
            return null;
        }
        if (this.m == null) {
            k();
        }
        if (this.l.getTooltip()) {
            return this.m;
        }
        return null;
    }

    public View getInfoContents() {
        if (this.l == null) {
            return null;
        }
        if (this.m == null) {
            k();
        }
        if (this.l.getTooltip()) {
            return null;
        }
        return this.m;
    }

    private void k() {
        a aVar = this.l;
        if (aVar != null && aVar.getChildCount() != 0) {
            LinearLayout linearLayout = new LinearLayout(this.n);
            linearLayout.setOrientation(1);
            linearLayout.setLayoutParams(new LinearLayout.LayoutParams(this.l.f1547a, this.l.f1548b, BitmapDescriptorFactory.HUE_RED));
            LinearLayout linearLayout2 = new LinearLayout(this.n);
            linearLayout2.setOrientation(0);
            linearLayout2.setLayoutParams(new LinearLayout.LayoutParams(this.l.f1547a, this.l.f1548b, BitmapDescriptorFactory.HUE_RED));
            linearLayout.addView(linearLayout2);
            linearLayout2.addView(this.l);
            this.m = linearLayout;
        }
    }

    private int a(String str) {
        return getResources().getIdentifier(str, "drawable", getContext().getPackageName());
    }

    private BitmapDescriptor b(String str) {
        return BitmapDescriptorFactory.fromResource(a(str));
    }
}
