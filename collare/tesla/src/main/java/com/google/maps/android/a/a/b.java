package com.google.maps.android.a.a;

import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import java.util.Arrays;
import java.util.Observable;
import java.util.Observer;

/* compiled from: GeoJsonFeature */
public class b extends com.google.maps.android.a.b implements Observer {

    /* renamed from: a  reason: collision with root package name */
    private final String f3972a;

    /* renamed from: b  reason: collision with root package name */
    private final LatLngBounds f3973b;

    /* renamed from: c  reason: collision with root package name */
    private j f3974c;

    /* renamed from: d  reason: collision with root package name */
    private e f3975d;
    private l e;

    public j e() {
        return this.f3974c;
    }

    public void a(j jVar) {
        if (jVar != null) {
            j jVar2 = this.f3974c;
            if (jVar2 != null) {
                jVar2.deleteObserver(this);
            }
            this.f3974c = jVar;
            this.f3974c.addObserver(this);
            a((m) this.f3974c);
            return;
        }
        throw new IllegalArgumentException("Point style cannot be null");
    }

    public e f() {
        return this.f3975d;
    }

    public void a(e eVar) {
        if (eVar != null) {
            e eVar2 = this.f3975d;
            if (eVar2 != null) {
                eVar2.deleteObserver(this);
            }
            this.f3975d = eVar;
            this.f3975d.addObserver(this);
            a((m) this.f3975d);
            return;
        }
        throw new IllegalArgumentException("Line string style cannot be null");
    }

    public l g() {
        return this.e;
    }

    public void a(l lVar) {
        if (lVar != null) {
            l lVar2 = this.e;
            if (lVar2 != null) {
                lVar2.deleteObserver(this);
            }
            this.e = lVar;
            this.e.addObserver(this);
            a((m) this.e);
            return;
        }
        throw new IllegalArgumentException("Polygon style cannot be null");
    }

    public PolygonOptions h() {
        return this.e.i();
    }

    public MarkerOptions i() {
        return this.f3974c.m();
    }

    public PolylineOptions j() {
        return this.f3975d.i();
    }

    private void a(m mVar) {
        if (d() && Arrays.asList(mVar.b()).contains(c().c())) {
            setChanged();
            notifyObservers();
        }
    }

    public String toString() {
        return "Feature{" + "\n bounding box=" + this.f3973b + ",\n geometry=" + c() + ",\n point style=" + this.f3974c + ",\n line string style=" + this.f3975d + ",\n polygon style=" + this.e + ",\n id=" + this.f3972a + ",\n properties=" + a() + "\n}\n";
    }

    public void update(Observable observable, Object obj) {
        if (observable instanceof m) {
            a((m) observable);
        }
    }
}
