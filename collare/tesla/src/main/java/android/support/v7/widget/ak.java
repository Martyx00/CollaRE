package android.support.v7.widget;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v7.view.menu.ListMenuItemView;
import android.support.v7.view.menu.g;
import android.support.v7.view.menu.h;
import android.support.v7.view.menu.j;
import android.transition.Transition;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.widget.HeaderViewListAdapter;
import android.widget.ListAdapter;
import android.widget.PopupWindow;
import java.lang.reflect.Method;

/* compiled from: MenuPopupWindow */
public class ak extends ai implements aj {

    /* renamed from: a  reason: collision with root package name */
    private static Method f1150a;

    /* renamed from: b  reason: collision with root package name */
    private aj f1151b;

    static {
        try {
            f1150a = PopupWindow.class.getDeclaredMethod("setTouchModal", Boolean.TYPE);
        } catch (NoSuchMethodException unused) {
            Log.i("MenuPopupWindow", "Could not find method setTouchModal() on PopupWindow. Oh well.");
        }
    }

    public ak(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
    }

    /* access modifiers changed from: package-private */
    @Override // android.support.v7.widget.ai
    public ae a(Context context, boolean z) {
        a aVar = new a(context, z);
        aVar.setHoverListener(this);
        return aVar;
    }

    public void a(Object obj) {
        if (Build.VERSION.SDK_INT >= 23) {
            this.g.setEnterTransition((Transition) obj);
        }
    }

    public void b(Object obj) {
        if (Build.VERSION.SDK_INT >= 23) {
            this.g.setExitTransition((Transition) obj);
        }
    }

    public void a(aj ajVar) {
        this.f1151b = ajVar;
    }

    public void c(boolean z) {
        Method method = f1150a;
        if (method != null) {
            try {
                method.invoke(this.g, Boolean.valueOf(z));
            } catch (Exception unused) {
                Log.i("MenuPopupWindow", "Could not invoke setTouchModal() on PopupWindow. Oh well.");
            }
        }
    }

    @Override // android.support.v7.widget.aj
    public void b(h hVar, MenuItem menuItem) {
        aj ajVar = this.f1151b;
        if (ajVar != null) {
            ajVar.b(hVar, menuItem);
        }
    }

    @Override // android.support.v7.widget.aj
    public void a(h hVar, MenuItem menuItem) {
        aj ajVar = this.f1151b;
        if (ajVar != null) {
            ajVar.a(hVar, menuItem);
        }
    }

    /* compiled from: MenuPopupWindow */
    public static class a extends ae {

        /* renamed from: b  reason: collision with root package name */
        final int f1152b;

        /* renamed from: c  reason: collision with root package name */
        final int f1153c;

        /* renamed from: d  reason: collision with root package name */
        private aj f1154d;
        private MenuItem e;

        @Override // android.support.v7.widget.ae
        public /* bridge */ /* synthetic */ int a(int i, int i2, int i3, int i4, int i5) {
            return super.a(i, i2, i3, i4, i5);
        }

        @Override // android.support.v7.widget.ae
        public /* bridge */ /* synthetic */ boolean a(MotionEvent motionEvent, int i) {
            return super.a(motionEvent, i);
        }

        @Override // android.support.v7.widget.ae
        public /* bridge */ /* synthetic */ boolean hasFocus() {
            return super.hasFocus();
        }

        @Override // android.support.v7.widget.ae
        public /* bridge */ /* synthetic */ boolean hasWindowFocus() {
            return super.hasWindowFocus();
        }

        @Override // android.support.v7.widget.ae
        public /* bridge */ /* synthetic */ boolean isFocused() {
            return super.isFocused();
        }

        @Override // android.support.v7.widget.ae
        public /* bridge */ /* synthetic */ boolean isInTouchMode() {
            return super.isInTouchMode();
        }

        @Override // android.support.v7.widget.ae
        public /* bridge */ /* synthetic */ boolean onTouchEvent(MotionEvent motionEvent) {
            return super.onTouchEvent(motionEvent);
        }

        @Override // android.support.v7.widget.ae, android.widget.AbsListView
        public /* bridge */ /* synthetic */ void setSelector(Drawable drawable) {
            super.setSelector(drawable);
        }

        public a(Context context, boolean z) {
            super(context, z);
            Configuration configuration = context.getResources().getConfiguration();
            if (Build.VERSION.SDK_INT < 17 || 1 != configuration.getLayoutDirection()) {
                this.f1152b = 22;
                this.f1153c = 21;
                return;
            }
            this.f1152b = 21;
            this.f1153c = 22;
        }

        public void setHoverListener(aj ajVar) {
            this.f1154d = ajVar;
        }

        public boolean onKeyDown(int i, KeyEvent keyEvent) {
            ListMenuItemView listMenuItemView = (ListMenuItemView) getSelectedView();
            if (listMenuItemView != null && i == this.f1152b) {
                if (listMenuItemView.isEnabled() && listMenuItemView.getItemData().hasSubMenu()) {
                    performItemClick(listMenuItemView, getSelectedItemPosition(), getSelectedItemId());
                }
                return true;
            } else if (listMenuItemView == null || i != this.f1153c) {
                return super.onKeyDown(i, keyEvent);
            } else {
                setSelection(-1);
                ((g) getAdapter()).a().a(false);
                return true;
            }
        }

        @Override // android.support.v7.widget.ae
        public boolean onHoverEvent(MotionEvent motionEvent) {
            int i;
            g gVar;
            int pointToPosition;
            int i2;
            if (this.f1154d != null) {
                ListAdapter adapter = getAdapter();
                if (adapter instanceof HeaderViewListAdapter) {
                    HeaderViewListAdapter headerViewListAdapter = (HeaderViewListAdapter) adapter;
                    i = headerViewListAdapter.getHeadersCount();
                    gVar = (g) headerViewListAdapter.getWrappedAdapter();
                } else {
                    i = 0;
                    gVar = (g) adapter;
                }
                j jVar = null;
                if (motionEvent.getAction() != 10 && (pointToPosition = pointToPosition((int) motionEvent.getX(), (int) motionEvent.getY())) != -1 && (i2 = pointToPosition - i) >= 0 && i2 < gVar.getCount()) {
                    jVar = gVar.getItem(i2);
                }
                MenuItem menuItem = this.e;
                if (menuItem != jVar) {
                    h a2 = gVar.a();
                    if (menuItem != null) {
                        this.f1154d.a(a2, menuItem);
                    }
                    this.e = jVar;
                    if (jVar != null) {
                        this.f1154d.b(a2, jVar);
                    }
                }
            }
            return super.onHoverEvent(motionEvent);
        }
    }
}
