package com.airbnb.android.react.maps;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.location.Location;
import android.os.Build;
import android.support.v4.content.h;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.LifecycleEventListener;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.WritableNativeArray;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.uimanager.UIManagerModule;
import com.facebook.react.uimanager.af;
import com.facebook.react.uimanager.events.d;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.IndoorBuilding;
import com.google.android.gms.maps.model.IndoorLevel;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PointOfInterest;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.measurement.AppMeasurement;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.maps.android.a.b.b;
import com.google.maps.android.a.b.f;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import org.xmlpull.v1.XmlPullParserException;

/* compiled from: AirMapView */
public class j extends MapView implements GoogleMap.InfoWindowAdapter, GoogleMap.OnIndoorStateChangeListener, GoogleMap.OnMarkerDragListener, GoogleMap.OnPoiClickListener, OnMapReadyCallback {
    private static final String[] t = {"android.permission.ACCESS_FINE_LOCATION", "android.permission.ACCESS_COARSE_LOCATION"};
    private LifecycleEventListener A;
    private boolean B = false;
    private boolean C = false;
    private final af D;
    private final d E;
    private s F;

    /* renamed from: a  reason: collision with root package name */
    public GoogleMap f1585a;

    /* renamed from: b  reason: collision with root package name */
    private f f1586b;

    /* renamed from: c  reason: collision with root package name */
    private ProgressBar f1587c;

    /* renamed from: d  reason: collision with root package name */
    private RelativeLayout f1588d;
    private ImageView e;
    private Boolean f = false;
    private Integer g = null;
    private Integer h = null;
    private final int i = 50;
    private LatLngBounds j;
    private CameraUpdate k;
    private boolean l = false;
    private boolean m = false;
    private boolean n = true;
    private boolean o = false;
    private boolean p = false;
    private boolean q = false;
    private LatLngBounds r;
    private int s = 0;
    private final List<c> u = new ArrayList();
    private final Map<Marker, e> v = new HashMap();
    private final Map<Polyline, h> w = new HashMap();
    private final Map<Polygon, g> x = new HashMap();
    private final android.support.v4.g.d y;
    private final AirMapManager z;

    private static boolean a(Context context) {
        return context == null || context.getResources() == null || context.getResources().getConfiguration() == null;
    }

    private static Context a(af afVar, ReactApplicationContext reactApplicationContext) {
        if (!a((Context) reactApplicationContext.getCurrentActivity())) {
            return reactApplicationContext.getCurrentActivity();
        }
        if (!a((Context) afVar)) {
            return afVar;
        }
        if (!a((Context) afVar.getCurrentActivity())) {
            return afVar.getCurrentActivity();
        }
        return !a(afVar.getApplicationContext()) ? afVar.getApplicationContext() : afVar;
    }

    public j(af afVar, ReactApplicationContext reactApplicationContext, AirMapManager airMapManager, GoogleMapOptions googleMapOptions) {
        super(a(afVar, reactApplicationContext), googleMapOptions);
        this.z = airMapManager;
        this.D = afVar;
        super.onCreate(null);
        super.onResume();
        super.getMapAsync(this);
        this.y = new android.support.v4.g.d(afVar, new GestureDetector.SimpleOnGestureListener() {
            /* class com.airbnb.android.react.maps.j.AnonymousClass1 */

            public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
                if (!j.this.m) {
                    return false;
                }
                j.this.a(motionEvent2);
                return false;
            }
        });
        addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            /* class com.airbnb.android.react.maps.j.AnonymousClass8 */

            public void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
                if (!j.this.B) {
                    j.this.f();
                }
            }
        });
        this.E = ((UIManagerModule) afVar.getNativeModule(UIManagerModule.class)).getEventDispatcher();
        this.F = new s(this.D);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(0, 0);
        layoutParams.width = 0;
        layoutParams.height = 0;
        layoutParams.leftMargin = 99999999;
        layoutParams.topMargin = 99999999;
        this.F.setLayoutParams(layoutParams);
        addView(this.F);
    }

    @Override // com.google.android.gms.maps.OnMapReadyCallback
    public void onMapReady(final GoogleMap googleMap) {
        if (!this.C) {
            this.f1585a = googleMap;
            this.f1585a.setInfoWindowAdapter(this);
            this.f1585a.setOnMarkerDragListener(this);
            this.f1585a.setOnPoiClickListener(this);
            this.f1585a.setOnIndoorStateChangeListener(this);
            this.z.pushEvent(this.D, this, "onMapReady", new WritableNativeMap());
            googleMap.setOnMyLocationChangeListener(new GoogleMap.OnMyLocationChangeListener() {
                /* class com.airbnb.android.react.maps.j.AnonymousClass9 */

                @Override // com.google.android.gms.maps.GoogleMap.OnMyLocationChangeListener
                public void onMyLocationChange(Location location) {
                    WritableNativeMap writableNativeMap = new WritableNativeMap();
                    WritableNativeMap writableNativeMap2 = new WritableNativeMap();
                    writableNativeMap2.putDouble("latitude", location.getLatitude());
                    writableNativeMap2.putDouble("longitude", location.getLongitude());
                    writableNativeMap2.putDouble("altitude", location.getAltitude());
                    writableNativeMap2.putDouble(AppMeasurement.Param.TIMESTAMP, (double) location.getTime());
                    writableNativeMap2.putDouble("accuracy", (double) location.getAccuracy());
                    writableNativeMap2.putDouble("speed", (double) location.getSpeed());
                    if (Build.VERSION.SDK_INT >= 18) {
                        writableNativeMap2.putBoolean("isFromMockProvider", location.isFromMockProvider());
                    }
                    writableNativeMap.putMap("coordinate", writableNativeMap2);
                    j.this.z.pushEvent(j.this.D, this, "onUserLocationChange", writableNativeMap);
                }
            });
            googleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                /* class com.airbnb.android.react.maps.j.AnonymousClass10 */

                @Override // com.google.android.gms.maps.GoogleMap.OnMarkerClickListener
                public boolean onMarkerClick(Marker marker) {
                    e a2 = j.this.a((j) marker);
                    WritableMap a3 = j.this.a(marker.getPosition());
                    a3.putString("action", "marker-press");
                    a3.putString("id", a2.getIdentifier());
                    j.this.z.pushEvent(j.this.D, this, "onMarkerPress", a3);
                    WritableMap a4 = j.this.a(marker.getPosition());
                    a4.putString("action", "marker-press");
                    a4.putString("id", a2.getIdentifier());
                    j.this.z.pushEvent(j.this.D, a2, "onPress", a4);
                    if (this.n) {
                        return false;
                    }
                    marker.showInfoWindow();
                    return true;
                }
            });
            googleMap.setOnPolygonClickListener(new GoogleMap.OnPolygonClickListener() {
                /* class com.airbnb.android.react.maps.j.AnonymousClass11 */

                @Override // com.google.android.gms.maps.GoogleMap.OnPolygonClickListener
                public void onPolygonClick(Polygon polygon) {
                    WritableMap a2 = j.this.a(polygon.getPoints().get(0));
                    a2.putString("action", "polygon-press");
                    j.this.z.pushEvent(j.this.D, (View) j.this.x.get(polygon), "onPress", a2);
                }
            });
            googleMap.setOnPolylineClickListener(new GoogleMap.OnPolylineClickListener() {
                /* class com.airbnb.android.react.maps.j.AnonymousClass12 */

                @Override // com.google.android.gms.maps.GoogleMap.OnPolylineClickListener
                public void onPolylineClick(Polyline polyline) {
                    WritableMap a2 = j.this.a(polyline.getPoints().get(0));
                    a2.putString("action", "polyline-press");
                    j.this.z.pushEvent(j.this.D, (View) j.this.w.get(polyline), "onPress", a2);
                }
            });
            googleMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
                /* class com.airbnb.android.react.maps.j.AnonymousClass13 */

                @Override // com.google.android.gms.maps.GoogleMap.OnInfoWindowClickListener
                public void onInfoWindowClick(Marker marker) {
                    WritableMap a2 = j.this.a(marker.getPosition());
                    a2.putString("action", "callout-press");
                    j.this.z.pushEvent(j.this.D, this, "onCalloutPress", a2);
                    WritableMap a3 = j.this.a(marker.getPosition());
                    a3.putString("action", "callout-press");
                    e a4 = j.this.a((j) marker);
                    j.this.z.pushEvent(j.this.D, a4, "onCalloutPress", a3);
                    WritableMap a5 = j.this.a(marker.getPosition());
                    a5.putString("action", "callout-press");
                    a calloutView = a4.getCalloutView();
                    if (calloutView != null) {
                        j.this.z.pushEvent(j.this.D, calloutView, "onPress", a5);
                    }
                }
            });
            googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
                /* class com.airbnb.android.react.maps.j.AnonymousClass14 */

                @Override // com.google.android.gms.maps.GoogleMap.OnMapClickListener
                public void onMapClick(LatLng latLng) {
                    WritableMap a2 = j.this.a(latLng);
                    a2.putString("action", "press");
                    j.this.z.pushEvent(j.this.D, this, "onPress", a2);
                }
            });
            googleMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
                /* class com.airbnb.android.react.maps.j.AnonymousClass15 */

                @Override // com.google.android.gms.maps.GoogleMap.OnMapLongClickListener
                public void onMapLongClick(LatLng latLng) {
                    j.this.a(latLng).putString("action", "long-press");
                    j.this.z.pushEvent(j.this.D, this, "onLongPress", j.this.a(latLng));
                }
            });
            googleMap.setOnCameraMoveStartedListener(new GoogleMap.OnCameraMoveStartedListener() {
                /* class com.airbnb.android.react.maps.j.AnonymousClass2 */

                @Override // com.google.android.gms.maps.GoogleMap.OnCameraMoveStartedListener
                public void onCameraMoveStarted(int i) {
                    j.this.s = i;
                }
            });
            googleMap.setOnCameraMoveListener(new GoogleMap.OnCameraMoveListener() {
                /* class com.airbnb.android.react.maps.j.AnonymousClass3 */

                @Override // com.google.android.gms.maps.GoogleMap.OnCameraMoveListener
                public void onCameraMove() {
                    LatLngBounds latLngBounds = googleMap.getProjection().getVisibleRegion().latLngBounds;
                    j.this.r = null;
                    j.this.E.a(new q(j.this.getId(), latLngBounds, true));
                }
            });
            googleMap.setOnCameraIdleListener(new GoogleMap.OnCameraIdleListener() {
                /* class com.airbnb.android.react.maps.j.AnonymousClass4 */

                @Override // com.google.android.gms.maps.GoogleMap.OnCameraIdleListener
                public void onCameraIdle() {
                    LatLngBounds latLngBounds = googleMap.getProjection().getVisibleRegion().latLngBounds;
                    if (j.this.s == 0) {
                        return;
                    }
                    if (j.this.r == null || o.a(latLngBounds, j.this.r)) {
                        j.this.r = latLngBounds;
                        j.this.E.a(new q(j.this.getId(), latLngBounds, false));
                    }
                }
            });
            googleMap.setOnMapLoadedCallback(new GoogleMap.OnMapLoadedCallback() {
                /* class com.airbnb.android.react.maps.j.AnonymousClass5 */

                @Override // com.google.android.gms.maps.GoogleMap.OnMapLoadedCallback
                public void onMapLoaded() {
                    j.this.f = true;
                    j.this.f();
                }
            });
            this.A = new LifecycleEventListener() {
                /* class com.airbnb.android.react.maps.j.AnonymousClass6 */

                @Override // com.facebook.react.bridge.LifecycleEventListener
                public void onHostResume() {
                    if (j.this.b()) {
                        googleMap.setMyLocationEnabled(j.this.l);
                    }
                    synchronized (j.this) {
                        if (!j.this.C) {
                            j.this.onResume();
                        }
                        j.this.B = false;
                    }
                }

                @Override // com.facebook.react.bridge.LifecycleEventListener
                public void onHostPause() {
                    if (j.this.b()) {
                        googleMap.setMyLocationEnabled(false);
                    }
                    synchronized (j.this) {
                        if (!j.this.C) {
                            j.this.onPause();
                        }
                        j.this.B = true;
                    }
                }

                @Override // com.facebook.react.bridge.LifecycleEventListener
                public void onHostDestroy() {
                    j.this.a();
                }
            };
            this.D.addLifecycleEventListener(this.A);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private boolean b() {
        if (h.a(getContext(), t[0]) == 0 || h.a(getContext(), t[1]) == 0) {
            return true;
        }
        return false;
    }

    public synchronized void a() {
        if (!this.C) {
            this.C = true;
            if (!(this.A == null || this.D == null)) {
                this.D.removeLifecycleEventListener(this.A);
                this.A = null;
            }
            if (!this.B) {
                onPause();
                this.B = true;
            }
            onDestroy();
        }
    }

    public void setInitialRegion(ReadableMap readableMap) {
        if (!this.p && readableMap != null) {
            setRegion(readableMap);
            this.p = true;
        }
    }

    public void setInitialCamera(ReadableMap readableMap) {
        if (!this.q && readableMap != null) {
            setCamera(readableMap);
            this.q = true;
        }
    }

    public void setRegion(ReadableMap readableMap) {
        if (readableMap != null) {
            Double valueOf = Double.valueOf(readableMap.getDouble("longitude"));
            Double valueOf2 = Double.valueOf(readableMap.getDouble("latitude"));
            Double valueOf3 = Double.valueOf(readableMap.getDouble("longitudeDelta"));
            Double valueOf4 = Double.valueOf(readableMap.getDouble("latitudeDelta"));
            LatLngBounds latLngBounds = new LatLngBounds(new LatLng(valueOf2.doubleValue() - (valueOf4.doubleValue() / 2.0d), valueOf.doubleValue() - (valueOf3.doubleValue() / 2.0d)), new LatLng(valueOf2.doubleValue() + (valueOf4.doubleValue() / 2.0d), valueOf.doubleValue() + (valueOf3.doubleValue() / 2.0d)));
            if (super.getHeight() <= 0 || super.getWidth() <= 0) {
                this.f1585a.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(valueOf2.doubleValue(), valueOf.doubleValue()), 10.0f));
                this.j = latLngBounds;
                return;
            }
            this.f1585a.moveCamera(CameraUpdateFactory.newLatLngBounds(latLngBounds, 0));
            this.j = null;
        }
    }

    public void setCamera(ReadableMap readableMap) {
        if (readableMap != null) {
            CameraPosition.Builder builder = new CameraPosition.Builder();
            ReadableMap map = readableMap.getMap("center");
            if (map != null) {
                builder.target(new LatLng(Double.valueOf(map.getDouble("latitude")).doubleValue(), Double.valueOf(map.getDouble("longitude")).doubleValue()));
            }
            builder.tilt((float) readableMap.getDouble("pitch"));
            builder.bearing((float) readableMap.getDouble("heading"));
            builder.zoom((float) readableMap.getInt("zoom"));
            CameraUpdate newCameraPosition = CameraUpdateFactory.newCameraPosition(builder.build());
            if (super.getHeight() <= 0 || super.getWidth() <= 0) {
                this.k = newCameraPosition;
                return;
            }
            this.f1585a.moveCamera(newCameraPosition);
            this.k = null;
        }
    }

    public void setShowsUserLocation(boolean z2) {
        this.l = z2;
        if (b()) {
            this.f1585a.setMyLocationEnabled(z2);
        }
    }

    public void setShowsMyLocationButton(boolean z2) {
        if (b() || !z2) {
            this.f1585a.getUiSettings().setMyLocationButtonEnabled(z2);
        }
    }

    public void setToolbarEnabled(boolean z2) {
        if (b() || !z2) {
            this.f1585a.getUiSettings().setMapToolbarEnabled(z2);
        }
    }

    public void setCacheEnabled(boolean z2) {
        this.o = z2;
        f();
    }

    public void a(boolean z2) {
        if (z2 && !this.f.booleanValue()) {
            getMapLoadingLayoutView().setVisibility(0);
        }
    }

    public void setMoveOnMarkerPress(boolean z2) {
        this.n = z2;
    }

    public void setLoadingBackgroundColor(Integer num) {
        this.g = num;
        RelativeLayout relativeLayout = this.f1588d;
        if (relativeLayout == null) {
            return;
        }
        if (num == null) {
            relativeLayout.setBackgroundColor(-1);
        } else {
            relativeLayout.setBackgroundColor(this.g.intValue());
        }
    }

    public void setLoadingIndicatorColor(Integer num) {
        this.h = num;
        if (this.f1587c != null) {
            Integer valueOf = num == null ? Integer.valueOf(Color.parseColor("#606060")) : num;
            if (Build.VERSION.SDK_INT >= 21) {
                ColorStateList valueOf2 = ColorStateList.valueOf(num.intValue());
                ColorStateList valueOf3 = ColorStateList.valueOf(num.intValue());
                ColorStateList valueOf4 = ColorStateList.valueOf(num.intValue());
                this.f1587c.setProgressTintList(valueOf2);
                this.f1587c.setSecondaryProgressTintList(valueOf3);
                this.f1587c.setIndeterminateTintList(valueOf4);
                return;
            }
            PorterDuff.Mode mode = PorterDuff.Mode.SRC_IN;
            if (Build.VERSION.SDK_INT <= 10) {
                mode = PorterDuff.Mode.MULTIPLY;
            }
            if (this.f1587c.getIndeterminateDrawable() != null) {
                this.f1587c.getIndeterminateDrawable().setColorFilter(valueOf.intValue(), mode);
            }
            if (this.f1587c.getProgressDrawable() != null) {
                this.f1587c.getProgressDrawable().setColorFilter(valueOf.intValue(), mode);
            }
        }
    }

    public void setHandlePanDrag(boolean z2) {
        this.m = z2;
    }

    public void a(View view, int i2) {
        if (view instanceof e) {
            e eVar = (e) view;
            eVar.a(this.f1585a);
            this.u.add(i2, eVar);
            int visibility = eVar.getVisibility();
            eVar.setVisibility(4);
            ViewGroup viewGroup = (ViewGroup) eVar.getParent();
            if (viewGroup != null) {
                viewGroup.removeView(eVar);
            }
            this.F.addView(eVar);
            eVar.setVisibility(visibility);
            this.v.put((Marker) eVar.getFeature(), eVar);
        } else if (view instanceof h) {
            h hVar = (h) view;
            hVar.a(this.f1585a);
            this.u.add(i2, hVar);
            this.w.put((Polyline) hVar.getFeature(), hVar);
        } else if (view instanceof g) {
            g gVar = (g) view;
            gVar.a(this.f1585a);
            this.u.add(i2, gVar);
            this.x.put((Polygon) gVar.getFeature(), gVar);
        } else if (view instanceof b) {
            b bVar = (b) view;
            bVar.a(this.f1585a);
            this.u.add(i2, bVar);
        } else if (view instanceof i) {
            i iVar = (i) view;
            iVar.a(this.f1585a);
            this.u.add(i2, iVar);
        } else if (view instanceof k) {
            k kVar = (k) view;
            kVar.a(this.f1585a);
            this.u.add(i2, kVar);
        } else if (view instanceof d) {
            d dVar = (d) view;
            dVar.a(this.f1585a);
            this.u.add(i2, dVar);
        } else if (view instanceof f) {
            f fVar = (f) view;
            fVar.a(this.f1585a);
            this.u.add(i2, fVar);
        } else if (view instanceof ViewGroup) {
            ViewGroup viewGroup2 = (ViewGroup) view;
            for (int i3 = 0; i3 < viewGroup2.getChildCount(); i3++) {
                a(viewGroup2.getChildAt(i3), i2);
            }
        } else {
            addView(view, i2);
        }
    }

    public int getFeatureCount() {
        return this.u.size();
    }

    public View a(int i2) {
        return this.u.get(i2);
    }

    public void b(int i2) {
        c remove = this.u.remove(i2);
        if (remove instanceof e) {
            this.v.remove(remove.getFeature());
        }
        remove.b(this.f1585a);
    }

    public WritableMap a(LatLng latLng) {
        WritableNativeMap writableNativeMap = new WritableNativeMap();
        WritableNativeMap writableNativeMap2 = new WritableNativeMap();
        writableNativeMap2.putDouble("latitude", latLng.latitude);
        writableNativeMap2.putDouble("longitude", latLng.longitude);
        writableNativeMap.putMap("coordinate", writableNativeMap2);
        Point screenLocation = this.f1585a.getProjection().toScreenLocation(latLng);
        WritableNativeMap writableNativeMap3 = new WritableNativeMap();
        writableNativeMap3.putDouble("x", (double) screenLocation.x);
        writableNativeMap3.putDouble("y", (double) screenLocation.y);
        writableNativeMap.putMap("position", writableNativeMap3);
        return writableNativeMap;
    }

    public void a(Object obj) {
        if (this.j != null) {
            HashMap hashMap = (HashMap) obj;
            int intValue = hashMap.get("width") == null ? 0 : ((Float) hashMap.get("width")).intValue();
            int intValue2 = hashMap.get("height") == null ? 0 : ((Float) hashMap.get("height")).intValue();
            if (intValue <= 0 || intValue2 <= 0) {
                this.f1585a.moveCamera(CameraUpdateFactory.newLatLngBounds(this.j, 0));
            } else {
                this.f1585a.moveCamera(CameraUpdateFactory.newLatLngBounds(this.j, intValue, intValue2, 0));
            }
            this.j = null;
            this.k = null;
            return;
        }
        CameraUpdate cameraUpdate = this.k;
        if (cameraUpdate != null) {
            this.f1585a.moveCamera(cameraUpdate);
            this.k = null;
        }
    }

    public void a(ReadableMap readableMap, int i2) {
        GoogleMap googleMap = this.f1585a;
        if (googleMap != null) {
            CameraPosition.Builder builder = new CameraPosition.Builder(googleMap.getCameraPosition());
            if (readableMap.hasKey("zoom")) {
                builder.zoom((float) readableMap.getDouble("zoom"));
            }
            if (readableMap.hasKey("heading")) {
                builder.bearing((float) readableMap.getDouble("heading"));
            }
            if (readableMap.hasKey("pitch")) {
                builder.tilt((float) readableMap.getDouble("pitch"));
            }
            if (readableMap.hasKey("center")) {
                ReadableMap map = readableMap.getMap("center");
                builder.target(new LatLng(map.getDouble("latitude"), map.getDouble("longitude")));
            }
            CameraUpdate newCameraPosition = CameraUpdateFactory.newCameraPosition(builder.build());
            if (i2 <= 0) {
                this.f1585a.moveCamera(newCameraPosition);
            } else {
                this.f1585a.animateCamera(newCameraPosition, i2, null);
            }
        }
    }

    public void a(LatLng latLng, float f2, float f3, int i2) {
        GoogleMap googleMap = this.f1585a;
        if (googleMap != null) {
            this.f1585a.animateCamera(CameraUpdateFactory.newCameraPosition(new CameraPosition.Builder(googleMap.getCameraPosition()).bearing(f2).tilt(f3).target(latLng).build()), i2, null);
        }
    }

    public void a(LatLngBounds latLngBounds, int i2) {
        GoogleMap googleMap = this.f1585a;
        if (googleMap != null) {
            googleMap.animateCamera(CameraUpdateFactory.newLatLngBounds(latLngBounds, 0), i2, null);
        }
    }

    public void a(float f2, int i2) {
        GoogleMap googleMap = this.f1585a;
        if (googleMap != null) {
            this.f1585a.animateCamera(CameraUpdateFactory.newCameraPosition(new CameraPosition.Builder(googleMap.getCameraPosition()).tilt(f2).build()), i2, null);
        }
    }

    public void b(float f2, int i2) {
        GoogleMap googleMap = this.f1585a;
        if (googleMap != null) {
            this.f1585a.animateCamera(CameraUpdateFactory.newCameraPosition(new CameraPosition.Builder(googleMap.getCameraPosition()).bearing(f2).build()), i2, null);
        }
    }

    public void a(LatLng latLng, int i2) {
        GoogleMap googleMap = this.f1585a;
        if (googleMap != null) {
            googleMap.animateCamera(CameraUpdateFactory.newLatLng(latLng), i2, null);
        }
    }

    public void b(boolean z2) {
        if (this.f1585a != null) {
            LatLngBounds.Builder builder = new LatLngBounds.Builder();
            boolean z3 = false;
            for (c cVar : this.u) {
                if (cVar instanceof e) {
                    builder.include(((Marker) cVar.getFeature()).getPosition());
                    z3 = true;
                }
            }
            if (z3) {
                CameraUpdate newLatLngBounds = CameraUpdateFactory.newLatLngBounds(builder.build(), 50);
                if (z2) {
                    this.f1585a.animateCamera(newLatLngBounds);
                } else {
                    this.f1585a.moveCamera(newLatLngBounds);
                }
            }
        }
    }

    public void a(ReadableArray readableArray, ReadableMap readableMap, boolean z2) {
        if (this.f1585a != null) {
            LatLngBounds.Builder builder = new LatLngBounds.Builder();
            String[] strArr = new String[readableArray.size()];
            boolean z3 = false;
            for (int i2 = 0; i2 < readableArray.size(); i2++) {
                strArr[i2] = readableArray.getString(i2);
            }
            List asList = Arrays.asList(strArr);
            for (c cVar : this.u) {
                if (cVar instanceof e) {
                    String identifier = ((e) cVar).getIdentifier();
                    Marker marker = (Marker) cVar.getFeature();
                    if (asList.contains(identifier)) {
                        builder.include(marker.getPosition());
                        z3 = true;
                    }
                }
            }
            if (z3) {
                CameraUpdate newLatLngBounds = CameraUpdateFactory.newLatLngBounds(builder.build(), 50);
                if (readableMap != null) {
                    this.f1585a.setPadding(readableMap.getInt("left"), readableMap.getInt("top"), readableMap.getInt("right"), readableMap.getInt("bottom"));
                }
                if (z2) {
                    this.f1585a.animateCamera(newLatLngBounds);
                } else {
                    this.f1585a.moveCamera(newLatLngBounds);
                }
            }
        }
    }

    public void b(ReadableArray readableArray, ReadableMap readableMap, boolean z2) {
        if (this.f1585a != null) {
            LatLngBounds.Builder builder = new LatLngBounds.Builder();
            for (int i2 = 0; i2 < readableArray.size(); i2++) {
                ReadableMap map = readableArray.getMap(i2);
                builder.include(new LatLng(Double.valueOf(map.getDouble("latitude")).doubleValue(), Double.valueOf(map.getDouble("longitude")).doubleValue()));
            }
            CameraUpdate newLatLngBounds = CameraUpdateFactory.newLatLngBounds(builder.build(), 50);
            if (readableMap != null) {
                this.f1585a.setPadding(readableMap.getInt("left"), readableMap.getInt("top"), readableMap.getInt("right"), readableMap.getInt("bottom"));
            }
            if (z2) {
                this.f1585a.animateCamera(newLatLngBounds);
            } else {
                this.f1585a.moveCamera(newLatLngBounds);
            }
            this.f1585a.setPadding(0, 0, 0, 0);
        }
    }

    public double[][] getMapBoundaries() {
        LatLngBounds latLngBounds = this.f1585a.getProjection().getVisibleRegion().latLngBounds;
        LatLng latLng = latLngBounds.northeast;
        LatLng latLng2 = latLngBounds.southwest;
        return new double[][]{new double[]{latLng.longitude, latLng.latitude}, new double[]{latLng2.longitude, latLng2.latitude}};
    }

    public void a(ReadableMap readableMap, ReadableMap readableMap2) {
        if (this.f1585a != null) {
            LatLngBounds.Builder builder = new LatLngBounds.Builder();
            builder.include(new LatLng(Double.valueOf(readableMap.getDouble("latitude")).doubleValue(), Double.valueOf(readableMap.getDouble("longitude")).doubleValue()));
            builder.include(new LatLng(Double.valueOf(readableMap2.getDouble("latitude")).doubleValue(), Double.valueOf(readableMap2.getDouble("longitude")).doubleValue()));
            this.f1585a.setLatLngBoundsForCameraTarget(builder.build());
        }
    }

    @Override // com.google.android.gms.maps.GoogleMap.InfoWindowAdapter
    public View getInfoWindow(Marker marker) {
        return a(marker).getCallout();
    }

    @Override // com.google.android.gms.maps.GoogleMap.InfoWindowAdapter
    public View getInfoContents(Marker marker) {
        return a(marker).getInfoContents();
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        this.y.a(motionEvent);
        boolean z2 = false;
        switch (android.support.v4.g.j.a(motionEvent)) {
            case 0:
                ViewParent parent = getParent();
                GoogleMap googleMap = this.f1585a;
                if (googleMap != null && googleMap.getUiSettings().isScrollGesturesEnabled()) {
                    z2 = true;
                }
                parent.requestDisallowInterceptTouchEvent(z2);
                break;
            case 1:
                getParent().requestDisallowInterceptTouchEvent(false);
                break;
        }
        super.dispatchTouchEvent(motionEvent);
        return true;
    }

    @Override // com.google.android.gms.maps.GoogleMap.OnMarkerDragListener
    public void onMarkerDragStart(Marker marker) {
        this.z.pushEvent(this.D, this, "onMarkerDragStart", a(marker.getPosition()));
        this.z.pushEvent(this.D, a(marker), "onDragStart", a(marker.getPosition()));
    }

    @Override // com.google.android.gms.maps.GoogleMap.OnMarkerDragListener
    public void onMarkerDrag(Marker marker) {
        this.z.pushEvent(this.D, this, "onMarkerDrag", a(marker.getPosition()));
        this.z.pushEvent(this.D, a(marker), "onDrag", a(marker.getPosition()));
    }

    @Override // com.google.android.gms.maps.GoogleMap.OnMarkerDragListener
    public void onMarkerDragEnd(Marker marker) {
        this.z.pushEvent(this.D, this, "onMarkerDragEnd", a(marker.getPosition()));
        this.z.pushEvent(this.D, a(marker), "onDragEnd", a(marker.getPosition()));
    }

    @Override // com.google.android.gms.maps.GoogleMap.OnPoiClickListener
    public void onPoiClick(PointOfInterest pointOfInterest) {
        WritableMap a2 = a(pointOfInterest.latLng);
        a2.putString("placeId", pointOfInterest.placeId);
        a2.putString("name", pointOfInterest.name);
        this.z.pushEvent(this.D, this, "onPoiClick", a2);
    }

    private ProgressBar getMapLoadingProgressBar() {
        if (this.f1587c == null) {
            this.f1587c = new ProgressBar(getContext());
            this.f1587c.setIndeterminate(true);
        }
        Integer num = this.h;
        if (num != null) {
            setLoadingIndicatorColor(num);
        }
        return this.f1587c;
    }

    private RelativeLayout getMapLoadingLayoutView() {
        if (this.f1588d == null) {
            this.f1588d = new RelativeLayout(getContext());
            this.f1588d.setBackgroundColor(-3355444);
            addView(this.f1588d, new ViewGroup.LayoutParams(-1, -1));
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
            layoutParams.addRule(13);
            this.f1588d.addView(getMapLoadingProgressBar(), layoutParams);
            this.f1588d.setVisibility(4);
        }
        setLoadingBackgroundColor(this.g);
        return this.f1588d;
    }

    private ImageView getCacheImageView() {
        if (this.e == null) {
            this.e = new ImageView(getContext());
            addView(this.e, new ViewGroup.LayoutParams(-1, -1));
            this.e.setVisibility(4);
        }
        return this.e;
    }

    private void c() {
        ImageView imageView = this.e;
        if (imageView != null) {
            ((ViewGroup) imageView.getParent()).removeView(this.e);
            this.e = null;
        }
    }

    private void d() {
        ProgressBar progressBar = this.f1587c;
        if (progressBar != null) {
            ((ViewGroup) progressBar.getParent()).removeView(this.f1587c);
            this.f1587c = null;
        }
    }

    private void e() {
        d();
        RelativeLayout relativeLayout = this.f1588d;
        if (relativeLayout != null) {
            ((ViewGroup) relativeLayout.getParent()).removeView(this.f1588d);
            this.f1588d = null;
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void f() {
        if (this.o) {
            final ImageView cacheImageView = getCacheImageView();
            final RelativeLayout mapLoadingLayoutView = getMapLoadingLayoutView();
            cacheImageView.setVisibility(4);
            mapLoadingLayoutView.setVisibility(0);
            if (this.f.booleanValue()) {
                this.f1585a.snapshot(new GoogleMap.SnapshotReadyCallback() {
                    /* class com.airbnb.android.react.maps.j.AnonymousClass7 */

                    @Override // com.google.android.gms.maps.GoogleMap.SnapshotReadyCallback
                    public void onSnapshotReady(Bitmap bitmap) {
                        cacheImageView.setImageBitmap(bitmap);
                        cacheImageView.setVisibility(0);
                        mapLoadingLayoutView.setVisibility(4);
                    }
                });
                return;
            }
            return;
        }
        c();
        if (this.f.booleanValue()) {
            e();
        }
    }

    public void a(MotionEvent motionEvent) {
        this.z.pushEvent(this.D, this, "onPanDrag", a(this.f1585a.getProjection().fromScreenLocation(new Point((int) motionEvent.getX(), (int) motionEvent.getY()))));
    }

    public void setKmlSrc(String str) {
        try {
            InputStream inputStream = (InputStream) new l(this.D).execute(str).get();
            if (inputStream != null) {
                this.f1586b = new f(this.f1585a, inputStream, this.D);
                this.f1586b.c();
                WritableNativeMap writableNativeMap = new WritableNativeMap();
                WritableNativeArray writableNativeArray = new WritableNativeArray();
                if (this.f1586b.b() == null) {
                    this.z.pushEvent(this.D, this, "onKmlReady", writableNativeMap);
                    return;
                }
                b next = this.f1586b.b().iterator().next();
                if (next != null) {
                    if (next.f() != null) {
                        if (next.f().iterator().hasNext()) {
                            next = next.f().iterator().next();
                        }
                        Integer num = 0;
                        for (com.google.maps.android.a.b.j jVar : next.g()) {
                            MarkerOptions markerOptions = new MarkerOptions();
                            if (jVar.f() != null) {
                                markerOptions = jVar.h();
                            } else {
                                markerOptions.icon(BitmapDescriptorFactory.defaultMarker());
                            }
                            LatLng latLng = (LatLng) jVar.c().d();
                            String str2 = "";
                            String str3 = "";
                            if (jVar.b("name")) {
                                str2 = jVar.a("name");
                            }
                            if (jVar.b("description")) {
                                str3 = jVar.a("description");
                            }
                            markerOptions.position(latLng);
                            markerOptions.title(str2);
                            markerOptions.snippet(str3);
                            e eVar = new e(this.D, markerOptions, this.z.getMarkerManager());
                            if (jVar.f() != null && jVar.f().g() != null) {
                                eVar.setImage(jVar.f().g());
                            } else if (next.a(jVar.e()) != null) {
                                eVar.setImage(next.a(jVar.e()).g());
                            }
                            String str4 = str2 + " - " + num;
                            eVar.setIdentifier(str4);
                            Integer valueOf = Integer.valueOf(num.intValue() + 1);
                            a(eVar, num.intValue());
                            WritableMap a2 = a(latLng);
                            a2.putString("id", str4);
                            a2.putString("title", str2);
                            a2.putString("description", str3);
                            writableNativeArray.pushMap(a2);
                            num = valueOf;
                        }
                        writableNativeMap.putArray("markers", writableNativeArray);
                        this.z.pushEvent(this.D, this, "onKmlReady", writableNativeMap);
                        return;
                    }
                }
                this.z.pushEvent(this.D, this, "onKmlReady", writableNativeMap);
            }
        } catch (XmlPullParserException e2) {
            e2.printStackTrace();
        } catch (IOException e3) {
            e3.printStackTrace();
        } catch (InterruptedException e4) {
            e4.printStackTrace();
        } catch (ExecutionException e5) {
            e5.printStackTrace();
        }
    }

    @Override // com.google.android.gms.maps.GoogleMap.OnIndoorStateChangeListener
    public void onIndoorBuildingFocused() {
        IndoorBuilding focusedBuilding = this.f1585a.getFocusedBuilding();
        int i2 = 0;
        if (focusedBuilding != null) {
            List<IndoorLevel> levels = focusedBuilding.getLevels();
            WritableArray createArray = Arguments.createArray();
            for (IndoorLevel indoorLevel : levels) {
                WritableMap createMap = Arguments.createMap();
                createMap.putInt(FirebaseAnalytics.b.INDEX, i2);
                createMap.putString("name", indoorLevel.getName());
                createMap.putString("shortName", indoorLevel.getShortName());
                createArray.pushMap(createMap);
                i2++;
            }
            WritableMap createMap2 = Arguments.createMap();
            WritableMap createMap3 = Arguments.createMap();
            createMap3.putArray("levels", createArray);
            createMap3.putInt("activeLevelIndex", focusedBuilding.getActiveLevelIndex());
            createMap3.putBoolean("underground", focusedBuilding.isUnderground());
            createMap2.putMap("IndoorBuilding", createMap3);
            this.z.pushEvent(this.D, this, "onIndoorBuildingFocused", createMap2);
            return;
        }
        WritableMap createMap4 = Arguments.createMap();
        WritableArray createArray2 = Arguments.createArray();
        WritableMap createMap5 = Arguments.createMap();
        createMap5.putArray("levels", createArray2);
        createMap5.putInt("activeLevelIndex", 0);
        createMap5.putBoolean("underground", false);
        createMap4.putMap("IndoorBuilding", createMap5);
        this.z.pushEvent(this.D, this, "onIndoorBuildingFocused", createMap4);
    }

    @Override // com.google.android.gms.maps.GoogleMap.OnIndoorStateChangeListener
    public void onIndoorLevelActivated(IndoorBuilding indoorBuilding) {
        int activeLevelIndex;
        if (indoorBuilding != null && (activeLevelIndex = indoorBuilding.getActiveLevelIndex()) >= 0 && activeLevelIndex < indoorBuilding.getLevels().size()) {
            IndoorLevel indoorLevel = indoorBuilding.getLevels().get(activeLevelIndex);
            WritableMap createMap = Arguments.createMap();
            WritableMap createMap2 = Arguments.createMap();
            createMap2.putInt("activeLevelIndex", activeLevelIndex);
            createMap2.putString("name", indoorLevel.getName());
            createMap2.putString("shortName", indoorLevel.getShortName());
            createMap.putMap("IndoorLevel", createMap2);
            this.z.pushEvent(this.D, this, "onIndoorLevelActivated", createMap);
        }
    }

    public void setIndoorActiveLevelIndex(int i2) {
        IndoorLevel indoorLevel;
        IndoorBuilding focusedBuilding = this.f1585a.getFocusedBuilding();
        if (focusedBuilding != null && i2 >= 0 && i2 < focusedBuilding.getLevels().size() && (indoorLevel = focusedBuilding.getLevels().get(i2)) != null) {
            indoorLevel.activate();
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private e a(Marker marker) {
        e eVar = this.v.get(marker);
        if (eVar != null) {
            return eVar;
        }
        for (Map.Entry<Marker, e> entry : this.v.entrySet()) {
            if (entry.getKey().getPosition().equals(marker.getPosition()) && entry.getKey().getTitle().equals(marker.getTitle())) {
                return entry.getValue();
            }
        }
        return eVar;
    }
}
