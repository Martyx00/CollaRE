package com.facebook.react.views.toolbar;

import android.content.Context;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import com.facebook.f.c.d;
import com.facebook.f.e.q;
import com.facebook.f.i.e;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.uimanager.o;

/* compiled from: ReactToolbar */
public class b extends Toolbar {
    private final com.facebook.f.i.b e;
    private final com.facebook.f.i.b f;
    private final com.facebook.f.i.b g;
    private final e<com.facebook.f.f.a> h = new e<>();
    private AbstractC0059b i;
    private AbstractC0059b j;
    private AbstractC0059b k;
    private final Runnable l = new Runnable() {
        /* class com.facebook.react.views.toolbar.b.AnonymousClass4 */

        public void run() {
            b bVar = b.this;
            bVar.measure(View.MeasureSpec.makeMeasureSpec(bVar.getWidth(), 1073741824), View.MeasureSpec.makeMeasureSpec(b.this.getHeight(), 1073741824));
            b bVar2 = b.this;
            bVar2.layout(bVar2.getLeft(), b.this.getTop(), b.this.getRight(), b.this.getBottom());
        }
    };

    /* access modifiers changed from: private */
    /* renamed from: com.facebook.react.views.toolbar.b$b  reason: collision with other inner class name */
    /* compiled from: ReactToolbar */
    public abstract class AbstractC0059b extends com.facebook.f.c.c<com.facebook.imagepipeline.j.e> {

        /* renamed from: a  reason: collision with root package name */
        private final com.facebook.f.i.b f3552a;

        /* renamed from: c  reason: collision with root package name */
        private c f3554c;

        /* access modifiers changed from: protected */
        public abstract void a(Drawable drawable);

        public AbstractC0059b(com.facebook.f.i.b bVar) {
            this.f3552a = bVar;
        }

        public void a(c cVar) {
            this.f3554c = cVar;
        }

        public void a(String str, com.facebook.imagepipeline.j.e eVar, Animatable animatable) {
            super.a(str, (Object) eVar, animatable);
            com.facebook.imagepipeline.j.e eVar2 = this.f3554c;
            if (eVar2 == null) {
                eVar2 = eVar;
            }
            a(new a(this.f3552a.f(), eVar2));
        }
    }

    /* access modifiers changed from: private */
    /* compiled from: ReactToolbar */
    public class a extends AbstractC0059b {

        /* renamed from: c  reason: collision with root package name */
        private final MenuItem f3551c;

        a(MenuItem menuItem, com.facebook.f.i.b bVar) {
            super(bVar);
            this.f3551c = menuItem;
        }

        /* access modifiers changed from: protected */
        @Override // com.facebook.react.views.toolbar.b.AbstractC0059b
        public void a(Drawable drawable) {
            this.f3551c.setIcon(drawable);
            b.this.requestLayout();
        }
    }

    /* access modifiers changed from: private */
    /* compiled from: ReactToolbar */
    public static class c implements com.facebook.imagepipeline.j.e {

        /* renamed from: a  reason: collision with root package name */
        private int f3555a;

        /* renamed from: b  reason: collision with root package name */
        private int f3556b;

        public c(int i, int i2) {
            this.f3555a = i;
            this.f3556b = i2;
        }

        @Override // com.facebook.imagepipeline.j.e
        public int f() {
            return this.f3555a;
        }

        @Override // com.facebook.imagepipeline.j.e
        public int g() {
            return this.f3556b;
        }
    }

    public b(Context context) {
        super(context);
        this.e = com.facebook.f.i.b.a(o(), context);
        this.f = com.facebook.f.i.b.a(o(), context);
        this.g = com.facebook.f.i.b.a(o(), context);
        this.i = new AbstractC0059b(this.e) {
            /* class com.facebook.react.views.toolbar.b.AnonymousClass1 */

            /* access modifiers changed from: protected */
            @Override // com.facebook.react.views.toolbar.b.AbstractC0059b
            public void a(Drawable drawable) {
                b.this.setLogo(drawable);
            }
        };
        this.j = new AbstractC0059b(this.f) {
            /* class com.facebook.react.views.toolbar.b.AnonymousClass2 */

            /* access modifiers changed from: protected */
            @Override // com.facebook.react.views.toolbar.b.AbstractC0059b
            public void a(Drawable drawable) {
                b.this.setNavigationIcon(drawable);
            }
        };
        this.k = new AbstractC0059b(this.g) {
            /* class com.facebook.react.views.toolbar.b.AnonymousClass3 */

            /* access modifiers changed from: protected */
            @Override // com.facebook.react.views.toolbar.b.AbstractC0059b
            public void a(Drawable drawable) {
                b.this.setOverflowIcon(drawable);
            }
        };
    }

    public void requestLayout() {
        super.requestLayout();
        post(this.l);
    }

    @Override // android.support.v7.widget.Toolbar
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        m();
    }

    public void onStartTemporaryDetach() {
        super.onStartTemporaryDetach();
        m();
    }

    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        n();
    }

    public void onFinishTemporaryDetach() {
        super.onFinishTemporaryDetach();
        n();
    }

    private void m() {
        this.e.c();
        this.f.c();
        this.g.c();
        this.h.b();
    }

    private void n() {
        this.e.b();
        this.f.b();
        this.g.b();
        this.h.a();
    }

    /* access modifiers changed from: package-private */
    public void setLogoSource(ReadableMap readableMap) {
        a(readableMap, this.i, this.e);
    }

    /* access modifiers changed from: package-private */
    public void setNavIconSource(ReadableMap readableMap) {
        a(readableMap, this.j, this.f);
    }

    /* access modifiers changed from: package-private */
    public void setOverflowIconSource(ReadableMap readableMap) {
        a(readableMap, this.k, this.g);
    }

    /* access modifiers changed from: package-private */
    public void setActions(ReadableArray readableArray) {
        Menu menu = getMenu();
        menu.clear();
        this.h.c();
        if (readableArray != null) {
            for (int i2 = 0; i2 < readableArray.size(); i2++) {
                ReadableMap map = readableArray.getMap(i2);
                MenuItem add = menu.add(0, 0, i2, map.getString("title"));
                if (map.hasKey("icon")) {
                    a(add, map.getMap("icon"));
                }
                int i3 = map.hasKey("show") ? map.getInt("show") : 0;
                if (map.hasKey("showWithText") && map.getBoolean("showWithText")) {
                    i3 |= 4;
                }
                add.setShowAsAction(i3);
            }
        }
    }

    private void a(MenuItem menuItem, ReadableMap readableMap) {
        com.facebook.f.i.b a2 = com.facebook.f.i.b.a(o(), getContext());
        a aVar = new a(menuItem, a2);
        aVar.a(a(readableMap));
        a(readableMap, aVar, a2);
        this.h.a(a2);
    }

    private void a(ReadableMap readableMap, AbstractC0059b bVar, com.facebook.f.i.b bVar2) {
        String string = readableMap != null ? readableMap.getString("uri") : null;
        if (string == null) {
            bVar.a((c) null);
            bVar.a((Drawable) null);
        } else if (string.startsWith("http://") || string.startsWith("https://") || string.startsWith("file://")) {
            bVar.a(a(readableMap));
            bVar2.a(((com.facebook.f.a.a.e) ((com.facebook.f.a.a.e) com.facebook.f.a.a.c.a().b(Uri.parse(string)).a((d) bVar)).c(bVar2.d())).o());
            bVar2.f().setVisible(true, true);
        } else {
            bVar.a(b(string));
        }
    }

    private com.facebook.f.f.a o() {
        return new com.facebook.f.f.b(getResources()).a(q.b.f1945c).a(0).r();
    }

    private int a(String str) {
        return getResources().getIdentifier(str, "drawable", getContext().getPackageName());
    }

    private Drawable b(String str) {
        if (a(str) != 0) {
            return getResources().getDrawable(a(str));
        }
        return null;
    }

    private c a(ReadableMap readableMap) {
        if (!readableMap.hasKey("width") || !readableMap.hasKey("height")) {
            return null;
        }
        return new c(Math.round(o.a((float) readableMap.getInt("width"))), Math.round(o.a((float) readableMap.getInt("height"))));
    }
}
