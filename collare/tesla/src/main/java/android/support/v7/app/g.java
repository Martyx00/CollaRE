package android.support.v7.app;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.g.f;
import android.support.v7.a.a;
import android.support.v7.view.b;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;

/* compiled from: AppCompatDialog */
public class g extends Dialog implements d {

    /* renamed from: a  reason: collision with root package name */
    private e f841a;

    /* renamed from: b  reason: collision with root package name */
    private final f.a f842b = new f.a() {
        /* class android.support.v7.app.g.AnonymousClass1 */

        @Override // android.support.v4.g.f.a
        public boolean a(KeyEvent keyEvent) {
            return g.this.a(keyEvent);
        }
    };

    @Override // android.support.v7.app.d
    public b a(b.a aVar) {
        return null;
    }

    @Override // android.support.v7.app.d
    public void a(b bVar) {
    }

    @Override // android.support.v7.app.d
    public void b(b bVar) {
    }

    public g(Context context, int i) {
        super(context, a(context, i));
        a().a((Bundle) null);
        a().i();
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        a().h();
        super.onCreate(bundle);
        a().a(bundle);
    }

    @Override // android.app.Dialog
    public void setContentView(int i) {
        a().b(i);
    }

    @Override // android.app.Dialog
    public void setContentView(View view) {
        a().a(view);
    }

    public void setContentView(View view, ViewGroup.LayoutParams layoutParams) {
        a().a(view, layoutParams);
    }

    @Override // android.app.Dialog
    public <T extends View> T findViewById(int i) {
        return (T) a().a(i);
    }

    @Override // android.app.Dialog
    public void setTitle(CharSequence charSequence) {
        super.setTitle(charSequence);
        a().a(charSequence);
    }

    @Override // android.app.Dialog
    public void setTitle(int i) {
        super.setTitle(i);
        a().a(getContext().getString(i));
    }

    public void addContentView(View view, ViewGroup.LayoutParams layoutParams) {
        a().b(view, layoutParams);
    }

    /* access modifiers changed from: protected */
    public void onStop() {
        super.onStop();
        a().d();
    }

    public boolean a(int i) {
        return a().c(i);
    }

    public void invalidateOptionsMenu() {
        a().f();
    }

    public e a() {
        if (this.f841a == null) {
            this.f841a = e.a(this, this);
        }
        return this.f841a;
    }

    private static int a(Context context, int i) {
        if (i != 0) {
            return i;
        }
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(a.C0020a.dialogTheme, typedValue, true);
        return typedValue.resourceId;
    }

    /* access modifiers changed from: package-private */
    public boolean a(KeyEvent keyEvent) {
        return super.dispatchKeyEvent(keyEvent);
    }

    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        return f.a(this.f842b, getWindow().getDecorView(), this, keyEvent);
    }
}
