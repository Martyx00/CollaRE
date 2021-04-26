package android.support.v7.view;

import android.content.Context;
import android.support.v4.util.l;
import android.support.v7.view.b;
import android.support.v7.view.menu.q;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import java.util.ArrayList;

/* compiled from: SupportActionModeWrapper */
public class f extends ActionMode {

    /* renamed from: a  reason: collision with root package name */
    final Context f919a;

    /* renamed from: b  reason: collision with root package name */
    final b f920b;

    public f(Context context, b bVar) {
        this.f919a = context;
        this.f920b = bVar;
    }

    public Object getTag() {
        return this.f920b.j();
    }

    public void setTag(Object obj) {
        this.f920b.a(obj);
    }

    @Override // android.view.ActionMode
    public void setTitle(CharSequence charSequence) {
        this.f920b.b(charSequence);
    }

    @Override // android.view.ActionMode
    public void setSubtitle(CharSequence charSequence) {
        this.f920b.a(charSequence);
    }

    public void invalidate() {
        this.f920b.d();
    }

    public void finish() {
        this.f920b.c();
    }

    public Menu getMenu() {
        return q.a(this.f919a, (android.support.v4.b.a.a) this.f920b.b());
    }

    public CharSequence getTitle() {
        return this.f920b.f();
    }

    @Override // android.view.ActionMode
    public void setTitle(int i) {
        this.f920b.a(i);
    }

    public CharSequence getSubtitle() {
        return this.f920b.g();
    }

    @Override // android.view.ActionMode
    public void setSubtitle(int i) {
        this.f920b.b(i);
    }

    public View getCustomView() {
        return this.f920b.i();
    }

    public void setCustomView(View view) {
        this.f920b.a(view);
    }

    public MenuInflater getMenuInflater() {
        return this.f920b.a();
    }

    public boolean getTitleOptionalHint() {
        return this.f920b.k();
    }

    public void setTitleOptionalHint(boolean z) {
        this.f920b.a(z);
    }

    public boolean isTitleOptional() {
        return this.f920b.h();
    }

    /* compiled from: SupportActionModeWrapper */
    public static class a implements b.a {

        /* renamed from: a  reason: collision with root package name */
        final ActionMode.Callback f921a;

        /* renamed from: b  reason: collision with root package name */
        final Context f922b;

        /* renamed from: c  reason: collision with root package name */
        final ArrayList<f> f923c = new ArrayList<>();

        /* renamed from: d  reason: collision with root package name */
        final l<Menu, Menu> f924d = new l<>();

        public a(Context context, ActionMode.Callback callback) {
            this.f922b = context;
            this.f921a = callback;
        }

        @Override // android.support.v7.view.b.a
        public boolean a(b bVar, Menu menu) {
            return this.f921a.onCreateActionMode(b(bVar), a(menu));
        }

        @Override // android.support.v7.view.b.a
        public boolean b(b bVar, Menu menu) {
            return this.f921a.onPrepareActionMode(b(bVar), a(menu));
        }

        @Override // android.support.v7.view.b.a
        public boolean a(b bVar, MenuItem menuItem) {
            return this.f921a.onActionItemClicked(b(bVar), q.a(this.f922b, (android.support.v4.b.a.b) menuItem));
        }

        @Override // android.support.v7.view.b.a
        public void a(b bVar) {
            this.f921a.onDestroyActionMode(b(bVar));
        }

        private Menu a(Menu menu) {
            Menu menu2 = this.f924d.get(menu);
            if (menu2 != null) {
                return menu2;
            }
            Menu a2 = q.a(this.f922b, (android.support.v4.b.a.a) menu);
            this.f924d.put(menu, a2);
            return a2;
        }

        public ActionMode b(b bVar) {
            int size = this.f923c.size();
            for (int i = 0; i < size; i++) {
                f fVar = this.f923c.get(i);
                if (fVar != null && fVar.f920b == bVar) {
                    return fVar;
                }
            }
            f fVar2 = new f(this.f922b, bVar);
            this.f923c.add(fVar2);
            return fVar2;
        }
    }
}
