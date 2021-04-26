package android.support.v7.app;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.a.a;
import android.support.v7.app.AlertController;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ListAdapter;

/* compiled from: AlertDialog */
public class b extends g implements DialogInterface {

    /* renamed from: a  reason: collision with root package name */
    final AlertController f809a = new AlertController(getContext(), this, getWindow());

    protected b(Context context, int i) {
        super(context, a(context, i));
    }

    static int a(Context context, int i) {
        if (((i >>> 24) & 255) >= 1) {
            return i;
        }
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(a.C0020a.alertDialogTheme, typedValue, true);
        return typedValue.resourceId;
    }

    @Override // android.support.v7.app.g, android.app.Dialog
    public void setTitle(CharSequence charSequence) {
        super.setTitle(charSequence);
        this.f809a.a(charSequence);
    }

    /* access modifiers changed from: protected */
    @Override // android.support.v7.app.g
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.f809a.a();
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (this.f809a.a(i, keyEvent)) {
            return true;
        }
        return super.onKeyDown(i, keyEvent);
    }

    public boolean onKeyUp(int i, KeyEvent keyEvent) {
        if (this.f809a.b(i, keyEvent)) {
            return true;
        }
        return super.onKeyUp(i, keyEvent);
    }

    /* compiled from: AlertDialog */
    public static class a {

        /* renamed from: a  reason: collision with root package name */
        private final AlertController.a f810a;

        /* renamed from: b  reason: collision with root package name */
        private final int f811b;

        public a(Context context) {
            this(context, b.a(context, 0));
        }

        public a(Context context, int i) {
            this.f810a = new AlertController.a(new ContextThemeWrapper(context, b.a(context, i)));
            this.f811b = i;
        }

        public Context a() {
            return this.f810a.f784a;
        }

        public a a(CharSequence charSequence) {
            this.f810a.f = charSequence;
            return this;
        }

        public a a(View view) {
            this.f810a.g = view;
            return this;
        }

        public a a(Drawable drawable) {
            this.f810a.f787d = drawable;
            return this;
        }

        public a a(DialogInterface.OnKeyListener onKeyListener) {
            this.f810a.u = onKeyListener;
            return this;
        }

        public a a(ListAdapter listAdapter, DialogInterface.OnClickListener onClickListener) {
            AlertController.a aVar = this.f810a;
            aVar.w = listAdapter;
            aVar.x = onClickListener;
            return this;
        }

        public b b() {
            b bVar = new b(this.f810a.f784a, this.f811b);
            this.f810a.a(bVar.f809a);
            bVar.setCancelable(this.f810a.r);
            if (this.f810a.r) {
                bVar.setCanceledOnTouchOutside(true);
            }
            bVar.setOnCancelListener(this.f810a.s);
            bVar.setOnDismissListener(this.f810a.t);
            if (this.f810a.u != null) {
                bVar.setOnKeyListener(this.f810a.u);
            }
            return bVar;
        }
    }
}
