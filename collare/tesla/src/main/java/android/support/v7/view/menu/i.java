package android.support.v7.view.menu;

import android.content.DialogInterface;
import android.os.IBinder;
import android.support.v7.a.a;
import android.support.v7.app.b;
import android.support.v7.view.menu.o;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import org.spongycastle.asn1.cmp.PKIFailureInfo;

/* compiled from: MenuDialogHelper */
class i implements DialogInterface.OnClickListener, DialogInterface.OnDismissListener, DialogInterface.OnKeyListener, o.a {

    /* renamed from: a  reason: collision with root package name */
    f f995a;

    /* renamed from: b  reason: collision with root package name */
    private h f996b;

    /* renamed from: c  reason: collision with root package name */
    private b f997c;

    /* renamed from: d  reason: collision with root package name */
    private o.a f998d;

    public i(h hVar) {
        this.f996b = hVar;
    }

    public void a(IBinder iBinder) {
        h hVar = this.f996b;
        b.a aVar = new b.a(hVar.f());
        this.f995a = new f(aVar.a(), a.g.abc_list_menu_item_layout);
        this.f995a.a(this);
        this.f996b.a(this.f995a);
        aVar.a(this.f995a.a(), this);
        View p = hVar.p();
        if (p != null) {
            aVar.a(p);
        } else {
            aVar.a(hVar.o()).a(hVar.n());
        }
        aVar.a(this);
        this.f997c = aVar.b();
        this.f997c.setOnDismissListener(this);
        WindowManager.LayoutParams attributes = this.f997c.getWindow().getAttributes();
        attributes.type = 1003;
        if (iBinder != null) {
            attributes.token = iBinder;
        }
        attributes.flags |= PKIFailureInfo.unsupportedVersion;
        this.f997c.show();
    }

    public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
        Window window;
        View decorView;
        KeyEvent.DispatcherState keyDispatcherState;
        View decorView2;
        KeyEvent.DispatcherState keyDispatcherState2;
        if (i == 82 || i == 4) {
            if (keyEvent.getAction() == 0 && keyEvent.getRepeatCount() == 0) {
                Window window2 = this.f997c.getWindow();
                if (!(window2 == null || (decorView2 = window2.getDecorView()) == null || (keyDispatcherState2 = decorView2.getKeyDispatcherState()) == null)) {
                    keyDispatcherState2.startTracking(keyEvent, this);
                    return true;
                }
            } else if (keyEvent.getAction() == 1 && !keyEvent.isCanceled() && (window = this.f997c.getWindow()) != null && (decorView = window.getDecorView()) != null && (keyDispatcherState = decorView.getKeyDispatcherState()) != null && keyDispatcherState.isTracking(keyEvent)) {
                this.f996b.a(true);
                dialogInterface.dismiss();
                return true;
            }
        }
        return this.f996b.performShortcut(i, keyEvent, 0);
    }

    public void a() {
        b bVar = this.f997c;
        if (bVar != null) {
            bVar.dismiss();
        }
    }

    public void onDismiss(DialogInterface dialogInterface) {
        this.f995a.a(this.f996b, true);
    }

    @Override // android.support.v7.view.menu.o.a
    public void a(h hVar, boolean z) {
        if (z || hVar == this.f996b) {
            a();
        }
        o.a aVar = this.f998d;
        if (aVar != null) {
            aVar.a(hVar, z);
        }
    }

    @Override // android.support.v7.view.menu.o.a
    public boolean a(h hVar) {
        o.a aVar = this.f998d;
        if (aVar != null) {
            return aVar.a(hVar);
        }
        return false;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        this.f996b.a((j) this.f995a.a().getItem(i), 0);
    }
}
