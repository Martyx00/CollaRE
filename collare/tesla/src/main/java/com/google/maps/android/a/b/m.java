package com.google.maps.android.a.b;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.GroundOverlay;
import com.google.android.gms.maps.model.Marker;
import com.google.maps.android.a.c;
import com.google.maps.android.a.h;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/* compiled from: KmlRenderer */
public class m extends h {

    /* renamed from: a  reason: collision with root package name */
    private final ArrayList<String> f3999a = new ArrayList<>();

    /* renamed from: b  reason: collision with root package name */
    private boolean f4000b = false;

    /* renamed from: c  reason: collision with root package name */
    private boolean f4001c = false;

    /* renamed from: d  reason: collision with root package name */
    private HashMap<e, GroundOverlay> f4002d;
    private ArrayList<b> e;

    m(GoogleMap googleMap, Context context) {
        super(googleMap, context);
    }

    private static BitmapDescriptor a(Bitmap bitmap, Double d2) {
        return BitmapDescriptorFactory.fromBitmap(Bitmap.createScaledBitmap(bitmap, (int) (((double) bitmap.getWidth()) * d2.doubleValue()), (int) (((double) bitmap.getHeight()) * d2.doubleValue()), false));
    }

    static boolean a(b bVar, boolean z) {
        boolean z2 = !bVar.c("visibility") || Integer.parseInt(bVar.b("visibility")) != 0;
        if (!z || !z2) {
            return false;
        }
        return true;
    }

    public void j() {
        a(true);
        this.f4002d = g();
        this.e = h();
        i();
        a(e(), d());
        a(this.f4002d, this.e);
        a((Iterable<b>) this.e, true);
        b(b());
        if (!this.f4001c) {
            m();
        }
        if (!this.f4000b) {
            l();
        }
    }

    /* access modifiers changed from: package-private */
    public void b(HashMap<String, n> hashMap, HashMap<String, String> hashMap2, HashMap<j, Object> hashMap3, ArrayList<b> arrayList, HashMap<e, GroundOverlay> hashMap4) {
        a(hashMap, hashMap2, hashMap3, arrayList, hashMap4);
    }

    public Iterable<b> k() {
        return this.e;
    }

    private void b(HashMap<? extends com.google.maps.android.a.b, Object> hashMap) {
        for (com.google.maps.android.a.b bVar : hashMap.keySet()) {
            a(bVar);
        }
    }

    private void a(Iterable<b> iterable, boolean z) {
        for (b bVar : iterable) {
            boolean a2 = a(bVar, z);
            if (bVar.a() != null) {
                a(bVar.a());
            }
            if (bVar.b() != null) {
                super.a(bVar.b(), d());
            }
            b(bVar, a2);
            if (bVar.e()) {
                a(bVar.f(), a2);
            }
        }
    }

    private void b(b bVar, boolean z) {
        for (j jVar : bVar.g()) {
            boolean z2 = z && b(jVar);
            if (jVar.c() != null) {
                String b2 = jVar.b();
                c c2 = jVar.c();
                n a2 = a(b2);
                j jVar2 = jVar;
                Object a3 = a(jVar2, c2, a2, jVar2.f(), z2);
                bVar.a(jVar2, a3);
                a(a3, jVar);
            }
        }
    }

    private void l() {
        this.f4000b = true;
        Iterator<String> it = c().iterator();
        while (it.hasNext()) {
            new b(it.next()).execute(new String[0]);
            it.remove();
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void a(String str, HashMap<j, Object> hashMap) {
        for (j jVar : hashMap.keySet()) {
            n nVar = d().get(jVar.b());
            j jVar2 = jVar;
            n f = jVar2.f();
            if ("Point".equals(jVar.c().c())) {
                boolean z = true;
                boolean z2 = f != null && str.equals(f.g());
                if (nVar == null || !str.equals(nVar.g())) {
                    z = false;
                }
                if (z2) {
                    a(f, hashMap, jVar2);
                } else if (z) {
                    a(nVar, hashMap, jVar2);
                }
            }
        }
    }

    private void a(n nVar, HashMap<j, Object> hashMap, j jVar) {
        double d2 = nVar.d();
        ((Marker) hashMap.get(jVar)).setIcon(a(f().get(nVar.g()), Double.valueOf(d2)));
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void a(String str, Iterable<b> iterable) {
        for (b bVar : iterable) {
            a(str, bVar.d());
            if (bVar.e()) {
                a(str, bVar.f());
            }
        }
    }

    private void a(HashMap<e, GroundOverlay> hashMap, Iterable<b> iterable) {
        c(hashMap);
        for (b bVar : iterable) {
            a(bVar.c(), bVar.f());
        }
    }

    private void c(HashMap<e, GroundOverlay> hashMap) {
        for (e eVar : hashMap.keySet()) {
            String a2 = eVar.a();
            if (!(a2 == null || eVar.b() == null)) {
                if (f().get(a2) != null) {
                    a(a2, this.f4002d, true);
                } else if (!this.f3999a.contains(a2)) {
                    this.f3999a.add(a2);
                }
            }
        }
    }

    private void m() {
        this.f4001c = true;
        Iterator<String> it = this.f3999a.iterator();
        while (it.hasNext()) {
            new a(it.next()).execute(new String[0]);
            it.remove();
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void a(String str, HashMap<e, GroundOverlay> hashMap, boolean z) {
        BitmapDescriptor fromBitmap = BitmapDescriptorFactory.fromBitmap(f().get(str));
        for (e eVar : hashMap.keySet()) {
            if (eVar.a().equals(str)) {
                GroundOverlay a2 = a(eVar.c().image(fromBitmap));
                if (!z) {
                    a2.setVisible(false);
                }
                hashMap.put(eVar, a2);
            }
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void a(String str, Iterable<b> iterable, boolean z) {
        for (b bVar : iterable) {
            boolean a2 = a(bVar, z);
            a(str, bVar.c(), a2);
            if (bVar.e()) {
                a(str, bVar.f(), a2);
            }
        }
    }

    /* access modifiers changed from: private */
    /* compiled from: KmlRenderer */
    public class b extends AsyncTask<String, Void, Bitmap> {

        /* renamed from: b  reason: collision with root package name */
        private final String f4006b;

        public b(String str) {
            this.f4006b = str;
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public Bitmap doInBackground(String... strArr) {
            try {
                return BitmapFactory.decodeStream((InputStream) new URL(this.f4006b).getContent());
            } catch (MalformedURLException unused) {
                return BitmapFactory.decodeFile(this.f4006b);
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public void onPostExecute(Bitmap bitmap) {
            if (bitmap == null) {
                Log.e("KmlRenderer", "Image at this URL could not be found " + this.f4006b);
                return;
            }
            m.this.a(this.f4006b, bitmap);
            if (m.this.a()) {
                m mVar = m.this;
                mVar.a((m) this.f4006b, (String) mVar.b());
                m mVar2 = m.this;
                mVar2.a((m) this.f4006b, (String) mVar2.e);
            }
        }
    }

    /* access modifiers changed from: private */
    /* compiled from: KmlRenderer */
    public class a extends AsyncTask<String, Void, Bitmap> {

        /* renamed from: b  reason: collision with root package name */
        private final String f4004b;

        public a(String str) {
            this.f4004b = str;
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public Bitmap doInBackground(String... strArr) {
            try {
                return BitmapFactory.decodeStream((InputStream) new URL(this.f4004b).getContent());
            } catch (MalformedURLException unused) {
                return BitmapFactory.decodeFile(this.f4004b);
            } catch (IOException e) {
                Log.e("KmlRenderer", "Image [" + this.f4004b + "] download issue", e);
                return null;
            }
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public void onPostExecute(Bitmap bitmap) {
            if (bitmap == null) {
                Log.e("KmlRenderer", "Image at this URL could not be found " + this.f4004b);
                return;
            }
            m.this.a(this.f4004b, bitmap);
            if (m.this.a()) {
                m mVar = m.this;
                mVar.a((m) this.f4004b, (String) mVar.f4002d, (HashMap) true);
                m mVar2 = m.this;
                mVar2.a((m) this.f4004b, (String) mVar2.e, (Iterable) true);
            }
        }
    }
}
