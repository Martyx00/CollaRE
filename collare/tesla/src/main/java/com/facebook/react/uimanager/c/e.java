package com.facebook.react.uimanager.c;

import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.UiThreadUtil;

/* compiled from: LayoutAnimationController */
public class e {

    /* renamed from: a  reason: collision with root package name */
    private final a f3201a = new h();

    /* renamed from: b  reason: collision with root package name */
    private final a f3202b = new k();

    /* renamed from: c  reason: collision with root package name */
    private final a f3203c = new i();

    /* renamed from: d  reason: collision with root package name */
    private final SparseArray<j> f3204d = new SparseArray<>(0);
    private boolean e;

    public void a(ReadableMap readableMap) {
        if (readableMap == null) {
            a();
            return;
        }
        int i = 0;
        this.e = false;
        if (readableMap.hasKey("duration")) {
            i = readableMap.getInt("duration");
        }
        if (readableMap.hasKey(g.a(g.CREATE))) {
            this.f3201a.a(readableMap.getMap(g.a(g.CREATE)), i);
            this.e = true;
        }
        if (readableMap.hasKey(g.a(g.UPDATE))) {
            this.f3202b.a(readableMap.getMap(g.a(g.UPDATE)), i);
            this.e = true;
        }
        if (readableMap.hasKey(g.a(g.DELETE))) {
            this.f3203c.a(readableMap.getMap(g.a(g.DELETE)), i);
            this.e = true;
        }
    }

    public void a() {
        this.f3201a.b();
        this.f3202b.b();
        this.f3203c.b();
        this.e = false;
    }

    public boolean a(View view) {
        return (this.e && view.getParent() != null) || this.f3204d.get(view.getId()) != null;
    }

    public void a(View view, int i, int i2, int i3, int i4) {
        UiThreadUtil.assertOnUiThread();
        final int id = view.getId();
        j jVar = this.f3204d.get(id);
        if (jVar != null) {
            jVar.a(i, i2, i3, i4);
            return;
        }
        Animation b2 = ((view.getWidth() == 0 || view.getHeight() == 0) ? this.f3201a : this.f3202b).b(view, i, i2, i3, i4);
        if (b2 instanceof j) {
            b2.setAnimationListener(new Animation.AnimationListener() {
                /* class com.facebook.react.uimanager.c.e.AnonymousClass1 */

                public void onAnimationRepeat(Animation animation) {
                }

                public void onAnimationStart(Animation animation) {
                    e.this.f3204d.put(id, (j) animation);
                }

                public void onAnimationEnd(Animation animation) {
                    e.this.f3204d.remove(id);
                }
            });
        } else {
            view.layout(i, i2, i3 + i, i4 + i2);
        }
        if (b2 != null) {
            view.startAnimation(b2);
        }
    }

    public void a(View view, final f fVar) {
        UiThreadUtil.assertOnUiThread();
        Animation b2 = this.f3203c.b(view, view.getLeft(), view.getTop(), view.getWidth(), view.getHeight());
        if (b2 != null) {
            b(view);
            b2.setAnimationListener(new Animation.AnimationListener() {
                /* class com.facebook.react.uimanager.c.e.AnonymousClass2 */

                public void onAnimationRepeat(Animation animation) {
                }

                public void onAnimationStart(Animation animation) {
                }

                public void onAnimationEnd(Animation animation) {
                    fVar.a();
                }
            });
            view.startAnimation(b2);
            return;
        }
        fVar.a();
    }

    private void b(View view) {
        view.setClickable(false);
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            for (int i = 0; i < viewGroup.getChildCount(); i++) {
                b(viewGroup.getChildAt(i));
            }
        }
    }
}
