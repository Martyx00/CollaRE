package com.teslamotors.plugins.colorblending;

import android.content.Context;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import com.facebook.b.a.i;
import com.facebook.f.c.b;
import com.facebook.f.c.c;
import com.facebook.f.e.q;
import com.facebook.f.i.d;
import com.facebook.imagepipeline.j.e;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.uimanager.UIManagerModule;
import java.io.File;

/* compiled from: ColorBlendingImageView */
public class a extends d {
    private static final String k = "a";

    /* renamed from: a  reason: collision with root package name */
    private com.facebook.react.views.b.a f5572a;

    /* renamed from: b  reason: collision with root package name */
    private Drawable f5573b;

    /* renamed from: c  reason: collision with root package name */
    private q.b f5574c = com.facebook.react.views.image.d.a();

    /* renamed from: d  reason: collision with root package name */
    private boolean f5575d;
    private final b e;
    private com.facebook.f.c.d f;
    private final Object g;
    private int h = -1;
    private com.facebook.imagepipeline.o.d i;
    private c j;
    private com.facebook.react.uimanager.events.d l;
    private boolean m = false;

    public boolean hasOverlappingRendering() {
        return false;
    }

    public a(Context context, b bVar, Object obj) {
        super(context, new com.facebook.f.f.b(context.getResources()).r());
        this.e = bVar;
        this.g = obj;
    }

    public void setShouldNotifyLoadEvents(boolean z) {
        if (!z) {
            this.f = null;
        } else {
            this.l = ((UIManagerModule) ((ReactContext) getContext()).getNativeModule(UIManagerModule.class)).getEventDispatcher();
            this.f = new c<e>() {
                /* class com.teslamotors.plugins.colorblending.a.AnonymousClass1 */

                @Override // com.facebook.f.c.c, com.facebook.f.c.d
                public void a(String str, Object obj) {
                    a.this.l.a(new com.facebook.react.views.image.b(a.this.getId(), 4));
                }

                public void a(String str, e eVar, Animatable animatable) {
                    if (eVar != null) {
                        a.this.l.a(new com.facebook.react.views.image.b(a.this.getId(), 2, a.this.f5572a.a(), eVar.f(), eVar.g()));
                        if (!a.this.m) {
                            a.this.l.a(new com.facebook.react.views.image.b(a.this.getId(), 3, a.this.f5572a.a(), eVar.f(), eVar.g()));
                        }
                    }
                }

                @Override // com.facebook.f.c.c, com.facebook.f.c.d
                public void a(String str, Throwable th) {
                    a.this.l.a(new com.facebook.react.views.image.b(a.this.getId(), 1));
                    a.this.l.a(new com.facebook.react.views.image.b(a.this.getId(), 3));
                }
            };
        }
        this.f5575d = true;
    }

    public void setSource(String str) {
        if (str != null) {
            this.f5572a = new com.facebook.react.views.b.a(getContext(), str);
            this.f5575d = true;
        }
    }

    public void setLoadingIndicatorSource(String str) {
        Drawable b2 = com.facebook.react.views.b.c.a().b(getContext(), str);
        this.f5573b = b2 != null ? new com.facebook.f.e.b(b2, 1000) : null;
        this.f5575d = true;
    }

    public void setFadeDuration(int i2) {
        this.h = i2;
    }

    public void e() {
        com.facebook.imagepipeline.o.c cVar;
        if (this.f5575d && this.j != null && this.f5572a != null) {
            com.facebook.f.f.a aVar = (com.facebook.f.f.a) getHierarchy();
            aVar.a(this.f5574c);
            Drawable drawable = this.f5573b;
            if (drawable != null) {
                aVar.a(drawable, q.b.e);
            }
            int i2 = this.h;
            if (i2 < 0) {
                this.f5572a.d();
                i2 = 0;
            }
            aVar.a(i2);
            new i(this.j.e());
            File g2 = this.j.g();
            if (g2 == null || !g2.exists()) {
                cVar = com.facebook.imagepipeline.o.c.a(this.f5572a.b()).a(this.i);
                this.m = true;
            } else {
                cVar = com.facebook.imagepipeline.o.c.a(Uri.fromFile(g2));
                this.m = false;
            }
            com.facebook.imagepipeline.o.b o = cVar.a((com.facebook.imagepipeline.e.e) null).a(false).b(false).c(false).o();
            this.e.c();
            this.e.a(true).a(this.g).c(getController()).b(o);
            this.e.a(this.f);
            setController(this.e.o());
            this.f5575d = false;
        }
    }

    /* access modifiers changed from: protected */
    public void onSizeChanged(int i2, int i3, int i4, int i5) {
        super.onSizeChanged(i2, i3, i4, i5);
        if (i2 > 0 && i3 > 0) {
            e();
        }
    }

    public void setBlendingSpecification(ReadableMap readableMap) {
        this.j = new c(readableMap);
        this.i = new d(this.j, this);
        setSource(this.j.c());
        e();
    }

    public void f() {
        com.facebook.react.uimanager.events.d dVar = this.l;
        if (dVar != null && this.m) {
            dVar.a(new com.facebook.react.views.image.b(getId(), 3, this.f5572a.a()));
        }
    }
}
