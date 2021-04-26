package com.facebook.react.animated;

import com.facebook.react.bridge.JavaOnlyArray;
import com.facebook.react.bridge.JavaOnlyMap;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.google.android.gms.measurement.AppMeasurement;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.ArrayList;
import java.util.List;

/* access modifiers changed from: package-private */
/* compiled from: TransformAnimatedNode */
public class r extends b {
    private final l e;
    private final List<c> f;

    /* access modifiers changed from: private */
    /* compiled from: TransformAnimatedNode */
    public class c {

        /* renamed from: c  reason: collision with root package name */
        public String f2578c;

        private c() {
        }
    }

    /* access modifiers changed from: private */
    /* compiled from: TransformAnimatedNode */
    public class a extends c {

        /* renamed from: a  reason: collision with root package name */
        public int f2574a;

        private a() {
            super();
        }
    }

    /* access modifiers changed from: private */
    /* compiled from: TransformAnimatedNode */
    public class b extends c {

        /* renamed from: a  reason: collision with root package name */
        public double f2576a;

        private b() {
            super();
        }
    }

    r(ReadableMap readableMap, l lVar) {
        ReadableArray array = readableMap.getArray("transforms");
        this.f = new ArrayList(array.size());
        for (int i = 0; i < array.size(); i++) {
            ReadableMap map = array.getMap(i);
            String string = map.getString("property");
            if (map.getString(AppMeasurement.Param.TYPE).equals("animated")) {
                a aVar = new a();
                aVar.f2578c = string;
                aVar.f2574a = map.getInt("nodeTag");
                this.f.add(aVar);
            } else {
                b bVar = new b();
                bVar.f2578c = string;
                bVar.f2576a = map.getDouble(FirebaseAnalytics.b.VALUE);
                this.f.add(bVar);
            }
        }
        this.e = lVar;
    }

    public void a(JavaOnlyMap javaOnlyMap) {
        double d2;
        ArrayList arrayList = new ArrayList(this.f.size());
        for (c cVar : this.f) {
            if (cVar instanceof a) {
                b a2 = this.e.a(((a) cVar).f2574a);
                if (a2 == null) {
                    throw new IllegalArgumentException("Mapped style node does not exists");
                } else if (a2 instanceof s) {
                    d2 = ((s) a2).b();
                } else {
                    throw new IllegalArgumentException("Unsupported type of node used as a transform child node " + a2.getClass());
                }
            } else {
                d2 = ((b) cVar).f2576a;
            }
            arrayList.add(JavaOnlyMap.of(cVar.f2578c, Double.valueOf(d2)));
        }
        javaOnlyMap.putArray("transform", JavaOnlyArray.from(arrayList));
    }
}
