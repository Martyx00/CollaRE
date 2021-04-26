package android.support.v7.app;

import android.app.Activity;
import android.app.Dialog;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

/* compiled from: AppCompatDelegate */
public abstract class e {

    /* renamed from: a  reason: collision with root package name */
    private static int f812a = -1;

    public abstract a a();

    public abstract <T extends View> T a(int i);

    public abstract void a(Configuration configuration);

    public abstract void a(Bundle bundle);

    public abstract void a(View view);

    public abstract void a(View view, ViewGroup.LayoutParams layoutParams);

    public abstract void a(CharSequence charSequence);

    public abstract MenuInflater b();

    public abstract void b(int i);

    public abstract void b(Bundle bundle);

    public abstract void b(View view, ViewGroup.LayoutParams layoutParams);

    public abstract void c();

    public abstract void c(Bundle bundle);

    public abstract boolean c(int i);

    public abstract void d();

    public abstract void e();

    public abstract void f();

    public abstract void g();

    public abstract void h();

    public abstract boolean i();

    public static e a(Activity activity, d dVar) {
        return new f(activity, activity.getWindow(), dVar);
    }

    public static e a(Dialog dialog, d dVar) {
        return new f(dialog.getContext(), dialog.getWindow(), dVar);
    }

    e() {
    }

    public static int j() {
        return f812a;
    }
}
