package android.support.v4.g;

import android.content.Context;
import android.util.Log;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;

/* compiled from: ActionProvider */
public abstract class c {

    /* renamed from: a  reason: collision with root package name */
    private final Context f478a;

    /* renamed from: b  reason: collision with root package name */
    private a f479b;

    /* renamed from: c  reason: collision with root package name */
    private b f480c;

    /* compiled from: ActionProvider */
    public interface a {
        void a(boolean z);
    }

    /* compiled from: ActionProvider */
    public interface b {
        void a(boolean z);
    }

    public abstract View a();

    public void a(SubMenu subMenu) {
    }

    public boolean b() {
        return false;
    }

    public boolean c() {
        return true;
    }

    public boolean d() {
        return false;
    }

    public boolean e() {
        return false;
    }

    public c(Context context) {
        this.f478a = context;
    }

    public View a(MenuItem menuItem) {
        return a();
    }

    public void a(boolean z) {
        a aVar = this.f479b;
        if (aVar != null) {
            aVar.a(z);
        }
    }

    public void a(a aVar) {
        this.f479b = aVar;
    }

    public void a(b bVar) {
        if (!(this.f480c == null || bVar == null)) {
            Log.w("ActionProvider(support)", "setVisibilityListener: Setting a new ActionProvider.VisibilityListener when one is already set. Are you reusing this " + getClass().getSimpleName() + " instance while it is still in use somewhere else?");
        }
        this.f480c = bVar;
    }

    public void f() {
        this.f480c = null;
        this.f479b = null;
    }
}
