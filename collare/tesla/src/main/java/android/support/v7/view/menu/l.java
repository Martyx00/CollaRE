package android.support.v7.view.menu;

import android.content.Context;
import android.support.v4.b.a.b;
import android.support.v4.g.c;
import android.support.v7.view.menu.k;
import android.view.ActionProvider;
import android.view.MenuItem;
import android.view.View;

/* access modifiers changed from: package-private */
/* compiled from: MenuItemWrapperJB */
public class l extends k {
    l(Context context, b bVar) {
        super(context, bVar);
    }

    /* access modifiers changed from: package-private */
    @Override // android.support.v7.view.menu.k
    public k.a a(ActionProvider actionProvider) {
        return new a(this.f963a, actionProvider);
    }

    /* compiled from: MenuItemWrapperJB */
    class a extends k.a implements ActionProvider.VisibilityListener {

        /* renamed from: c  reason: collision with root package name */
        c.b f1010c;

        public a(Context context, ActionProvider actionProvider) {
            super(context, actionProvider);
        }

        @Override // android.support.v4.g.c
        public View a(MenuItem menuItem) {
            return this.f1005a.onCreateActionView(menuItem);
        }

        @Override // android.support.v4.g.c
        public boolean b() {
            return this.f1005a.overridesItemVisibility();
        }

        @Override // android.support.v4.g.c
        public boolean c() {
            return this.f1005a.isVisible();
        }

        @Override // android.support.v4.g.c
        public void a(c.b bVar) {
            this.f1010c = bVar;
            this.f1005a.setVisibilityListener(bVar != null ? this : null);
        }

        public void onActionProviderVisibilityChanged(boolean z) {
            c.b bVar = this.f1010c;
            if (bVar != null) {
                bVar.a(z);
            }
        }
    }
}
