package com.google.maps.android.a;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.util.g;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.GroundOverlay;
import com.google.android.gms.maps.model.GroundOverlayOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.maps.android.a;
import com.google.maps.android.a.a.a;
import com.google.maps.android.a.a.c;
import com.google.maps.android.a.a.d;
import com.google.maps.android.a.a.f;
import com.google.maps.android.a.a.i;
import com.google.maps.android.a.a.j;
import com.google.maps.android.a.a.k;
import com.google.maps.android.a.a.l;
import com.google.maps.android.a.b.b;
import com.google.maps.android.a.b.e;
import com.google.maps.android.a.b.n;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.spongycastle.i18n.TextBundle;

/* compiled from: Renderer */
public class h {

    /* renamed from: a  reason: collision with root package name */
    private static final Object f4013a = null;

    /* renamed from: b  reason: collision with root package name */
    private GoogleMap f4014b;

    /* renamed from: c  reason: collision with root package name */
    private final a<b> f4015c = new a<>();

    /* renamed from: d  reason: collision with root package name */
    private HashMap<String, n> f4016d;
    private HashMap<String, n> e;
    private HashMap<String, String> f;
    private a<b> g;
    private HashMap<e, GroundOverlay> h;
    private final ArrayList<String> i;
    private final g<String, Bitmap> j;
    private boolean k;
    private Context l;
    private ArrayList<b> m;
    private final j n;
    private final com.google.maps.android.a.a.e o;
    private final l p;

    public h(GoogleMap googleMap, Context context) {
        this.f4014b = googleMap;
        this.l = context;
        this.k = false;
        this.j = new g<>(50);
        this.i = new ArrayList<>();
        this.e = new HashMap<>();
        this.n = null;
        this.o = null;
        this.p = null;
        this.g = new a<>();
    }

    public boolean a() {
        return this.k;
    }

    /* access modifiers changed from: protected */
    public void a(boolean z) {
        this.k = z;
    }

    /* access modifiers changed from: protected */
    public void a(Object obj, b bVar) {
        this.g.put(bVar, obj);
    }

    /* access modifiers changed from: protected */
    public HashMap<? extends b, Object> b() {
        return this.f4015c;
    }

    public ArrayList<String> c() {
        return this.i;
    }

    public HashMap<String, n> d() {
        return this.e;
    }

    public HashMap<String, String> e() {
        return this.f;
    }

    public g<String, Bitmap> f() {
        return this.j;
    }

    public HashMap<e, GroundOverlay> g() {
        return this.h;
    }

    public ArrayList<b> h() {
        return this.m;
    }

    /* access modifiers changed from: protected */
    public n a(String str) {
        return this.e.get(str) != null ? this.e.get(str) : this.e.get(null);
    }

    public void i() {
        this.e.putAll(this.f4016d);
    }

    public void a(HashMap<String, n> hashMap) {
        this.e.putAll(hashMap);
    }

    public void a(String str, Bitmap bitmap) {
        this.j.put(str, bitmap);
    }

    private void a(com.google.maps.android.a.a.b bVar) {
        if (bVar.e() == null) {
            bVar.a(this.n);
        }
        if (bVar.f() == null) {
            bVar.a(this.o);
        }
        if (bVar.g() == null) {
            bVar.a(this.p);
        }
    }

    /* access modifiers changed from: protected */
    public void a(HashMap<String, n> hashMap, HashMap<String, String> hashMap2, HashMap<com.google.maps.android.a.b.j, Object> hashMap3, ArrayList<b> arrayList, HashMap<e, GroundOverlay> hashMap4) {
        this.f4016d = hashMap;
        this.f = hashMap2;
        this.f4015c.putAll(hashMap3);
        this.m = arrayList;
        this.h = hashMap4;
    }

    public void a(b bVar) {
        Object obj = f4013a;
        if (bVar instanceof com.google.maps.android.a.a.b) {
            a((com.google.maps.android.a.a.b) bVar);
        }
        if (this.k) {
            if (this.f4015c.containsKey(bVar)) {
                a(this.f4015c.get(bVar));
            }
            if (bVar.d()) {
                if (bVar instanceof com.google.maps.android.a.b.j) {
                    com.google.maps.android.a.b.j jVar = (com.google.maps.android.a.b.j) bVar;
                    obj = a(jVar, bVar.c(), a(bVar.b()), jVar.f(), b(bVar));
                } else {
                    obj = a(bVar, bVar.c());
                }
            }
        }
        this.f4015c.put(bVar, obj);
    }

    public static void a(Object obj) {
        if (obj instanceof Marker) {
            ((Marker) obj).remove();
        } else if (obj instanceof Polyline) {
            ((Polyline) obj).remove();
        } else if (obj instanceof Polygon) {
            ((Polygon) obj).remove();
        } else if (obj instanceof ArrayList) {
            Iterator it = ((ArrayList) obj).iterator();
            while (it.hasNext()) {
                a(it.next());
            }
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    public Object a(b bVar, c cVar) {
        char c2;
        String c3 = cVar.c();
        switch (c3.hashCode()) {
            case -2116761119:
                if (c3.equals("MultiPolygon")) {
                    c2 = 5;
                    break;
                }
                c2 = 65535;
                break;
            case -1065891849:
                if (c3.equals("MultiPoint")) {
                    c2 = 3;
                    break;
                }
                c2 = 65535;
                break;
            case -627102946:
                if (c3.equals("MultiLineString")) {
                    c2 = 4;
                    break;
                }
                c2 = 65535;
                break;
            case 77292912:
                if (c3.equals("Point")) {
                    c2 = 0;
                    break;
                }
                c2 = 65535;
                break;
            case 1267133722:
                if (c3.equals("Polygon")) {
                    c2 = 2;
                    break;
                }
                c2 = 65535;
                break;
            case 1806700869:
                if (c3.equals("LineString")) {
                    c2 = 1;
                    break;
                }
                c2 = 65535;
                break;
            case 1950410960:
                if (c3.equals("GeometryCollection")) {
                    c2 = 6;
                    break;
                }
                c2 = 65535;
                break;
            default:
                c2 = 65535;
                break;
        }
        MarkerOptions markerOptions = null;
        PolygonOptions polygonOptions = null;
        PolylineOptions polylineOptions = null;
        switch (c2) {
            case 0:
                if (bVar instanceof com.google.maps.android.a.a.b) {
                    markerOptions = ((com.google.maps.android.a.a.b) bVar).i();
                } else if (bVar instanceof com.google.maps.android.a.b.j) {
                    markerOptions = ((com.google.maps.android.a.b.j) bVar).h();
                }
                return a(markerOptions, (i) cVar);
            case 1:
                if (bVar instanceof com.google.maps.android.a.a.b) {
                    polylineOptions = ((com.google.maps.android.a.a.b) bVar).j();
                } else if (bVar instanceof com.google.maps.android.a.b.j) {
                    polylineOptions = ((com.google.maps.android.a.b.j) bVar).i();
                }
                return a(polylineOptions, (d) cVar);
            case 2:
                if (bVar instanceof com.google.maps.android.a.a.b) {
                    polygonOptions = ((com.google.maps.android.a.a.b) bVar).h();
                } else if (bVar instanceof com.google.maps.android.a.b.j) {
                    polygonOptions = ((com.google.maps.android.a.b.j) bVar).g();
                }
                return a(polygonOptions, (a) cVar);
            case 3:
                return a(((com.google.maps.android.a.a.b) bVar).e(), (com.google.maps.android.a.a.g) cVar);
            case 4:
                return a(((com.google.maps.android.a.a.b) bVar).f(), (f) cVar);
            case 5:
                return a(((com.google.maps.android.a.a.b) bVar).g(), (com.google.maps.android.a.a.h) cVar);
            case 6:
                return a((com.google.maps.android.a.a.b) bVar, ((c) cVar).b());
            default:
                return null;
        }
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0058, code lost:
        if (r0.equals("Point") != false) goto L_0x005c;
     */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x005f A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x0061  */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x006e  */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x0098  */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x00c2  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object a(com.google.maps.android.a.b.j r11, com.google.maps.android.a.c r12, com.google.maps.android.a.b.n r13, com.google.maps.android.a.b.n r14, boolean r15) {
        /*
        // Method dump skipped, instructions count: 252
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.maps.android.a.h.a(com.google.maps.android.a.b.j, com.google.maps.android.a.c, com.google.maps.android.a.b.n, com.google.maps.android.a.b.n, boolean):java.lang.Object");
    }

    /* access modifiers changed from: protected */
    public Marker a(MarkerOptions markerOptions, g gVar) {
        markerOptions.position(gVar.d());
        return this.f4014b.addMarker(markerOptions);
    }

    private void a(MarkerOptions markerOptions, n nVar, String str) {
        MarkerOptions l2 = nVar.l();
        if (nVar.c("heading")) {
            markerOptions.rotation(l2.getRotation());
        }
        if (nVar.c("hotSpot")) {
            markerOptions.anchor(l2.getAnchorU(), l2.getAnchorV());
        }
        if (nVar.c("markerColor")) {
            markerOptions.icon(l2.getIcon());
        }
        if (nVar.c("iconUrl")) {
            a(nVar.g(), markerOptions);
        } else if (str != null) {
            a(str, markerOptions);
        }
    }

    /* access modifiers changed from: protected */
    public Polyline a(PolylineOptions polylineOptions, e eVar) {
        polylineOptions.addAll(eVar.d());
        Polyline addPolyline = this.f4014b.addPolyline(polylineOptions);
        addPolyline.setClickable(true);
        return addPolyline;
    }

    private void a(PolylineOptions polylineOptions, n nVar) {
        PolylineOptions m2 = nVar.m();
        if (nVar.c("outlineColor")) {
            polylineOptions.color(m2.getColor());
        }
        if (nVar.c("width")) {
            polylineOptions.width(m2.getWidth());
        }
        if (nVar.i()) {
            polylineOptions.color(n.b(m2.getColor()));
        }
    }

    /* access modifiers changed from: protected */
    public Polygon a(PolygonOptions polygonOptions, a aVar) {
        polygonOptions.addAll(aVar.a());
        for (List<LatLng> list : aVar.b()) {
            polygonOptions.addHole(list);
        }
        Polygon addPolygon = this.f4014b.addPolygon(polygonOptions);
        addPolygon.setClickable(true);
        return addPolygon;
    }

    private void a(PolygonOptions polygonOptions, n nVar) {
        PolygonOptions n2 = nVar.n();
        if (nVar.c() && nVar.c("fillColor")) {
            polygonOptions.fillColor(n2.getFillColor());
        }
        if (nVar.e()) {
            if (nVar.c("outlineColor")) {
                polygonOptions.strokeColor(n2.getStrokeColor());
            }
            if (nVar.c("width")) {
                polygonOptions.strokeWidth(n2.getStrokeWidth());
            }
        }
        if (nVar.j()) {
            polygonOptions.fillColor(n.b(n2.getFillColor()));
        }
    }

    private ArrayList<Object> a(com.google.maps.android.a.a.b bVar, List<c> list) {
        ArrayList<Object> arrayList = new ArrayList<>();
        for (c cVar : list) {
            arrayList.add(a(bVar, cVar));
        }
        return arrayList;
    }

    protected static boolean b(b bVar) {
        return !bVar.b("visibility") || Integer.parseInt(bVar.a("visibility")) != 0;
    }

    public void a(HashMap<String, String> hashMap, HashMap<String, n> hashMap2) {
        for (String str : hashMap.keySet()) {
            String str2 = hashMap.get(str);
            if (hashMap2.containsKey(str2)) {
                hashMap2.put(str, hashMap2.get(str2));
            }
        }
    }

    private ArrayList<Object> a(com.google.maps.android.a.b.j jVar, com.google.maps.android.a.b.h hVar, n nVar, n nVar2, boolean z) {
        ArrayList<Object> arrayList = new ArrayList<>();
        Iterator<c> it = hVar.d().iterator();
        while (it.hasNext()) {
            arrayList.add(a(jVar, it.next(), nVar, nVar2, z));
        }
        return arrayList;
    }

    private ArrayList<Marker> a(j jVar, com.google.maps.android.a.a.g gVar) {
        ArrayList<Marker> arrayList = new ArrayList<>();
        for (i iVar : gVar.b()) {
            arrayList.add(a(jVar.m(), iVar));
        }
        return arrayList;
    }

    private ArrayList<Polyline> a(com.google.maps.android.a.a.e eVar, f fVar) {
        ArrayList<Polyline> arrayList = new ArrayList<>();
        for (d dVar : fVar.b()) {
            arrayList.add(a(eVar.i(), dVar));
        }
        return arrayList;
    }

    private ArrayList<Polygon> a(l lVar, com.google.maps.android.a.a.h hVar) {
        ArrayList<Polygon> arrayList = new ArrayList<>();
        for (k kVar : hVar.b()) {
            arrayList.add(a(lVar.i(), kVar));
        }
        return arrayList;
    }

    private void a(String str, MarkerOptions markerOptions) {
        if (this.j.get(str) != null) {
            markerOptions.icon(BitmapDescriptorFactory.fromBitmap(this.j.get(str)));
        } else if (!this.i.contains(str)) {
            this.i.add(str);
        }
    }

    public GroundOverlay a(GroundOverlayOptions groundOverlayOptions) {
        return this.f4014b.addGroundOverlay(groundOverlayOptions);
    }

    private void a(n nVar, Marker marker, com.google.maps.android.a.b.j jVar) {
        boolean b2 = jVar.b("name");
        boolean b3 = jVar.b("description");
        boolean f2 = nVar.f();
        boolean containsKey = nVar.k().containsKey(TextBundle.TEXT_ENTRY);
        if (f2 && containsKey) {
            marker.setTitle(nVar.k().get(TextBundle.TEXT_ENTRY));
            j();
        } else if (f2 && b2) {
            marker.setTitle(jVar.a("name"));
            j();
        } else if (b2 && b3) {
            marker.setTitle(jVar.a("name"));
            marker.setSnippet(jVar.a("description"));
            j();
        } else if (b3) {
            marker.setTitle(jVar.a("description"));
            j();
        } else if (b2) {
            marker.setTitle(jVar.a("name"));
            j();
        }
    }

    private void j() {
        this.f4014b.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {
            /* class com.google.maps.android.a.h.AnonymousClass1 */

            @Override // com.google.android.gms.maps.GoogleMap.InfoWindowAdapter
            public View getInfoWindow(Marker marker) {
                return null;
            }

            @Override // com.google.android.gms.maps.GoogleMap.InfoWindowAdapter
            public View getInfoContents(Marker marker) {
                View inflate = LayoutInflater.from(h.this.l).inflate(a.b.amu_info_window, (ViewGroup) null);
                TextView textView = (TextView) inflate.findViewById(a.C0068a.window);
                if (marker.getSnippet() != null) {
                    textView.setText(Html.fromHtml(marker.getTitle() + "<br>" + marker.getSnippet()));
                } else {
                    textView.setText(Html.fromHtml(marker.getTitle()));
                }
                return inflate;
            }
        });
    }
}
