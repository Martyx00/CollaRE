package android.support.v4.app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import java.io.FileDescriptor;
import java.io.PrintWriter;

/* compiled from: FragmentHostCallback */
public abstract class k<E> extends i {

    /* renamed from: a  reason: collision with root package name */
    private final Activity f205a;

    /* renamed from: b  reason: collision with root package name */
    final m f206b;

    /* renamed from: c  reason: collision with root package name */
    private final Context f207c;

    /* renamed from: d  reason: collision with root package name */
    private final Handler f208d;
    private final int e;

    @Override // android.support.v4.app.i
    public View a(int i) {
        return null;
    }

    public void a(g gVar, String[] strArr, int i) {
    }

    public void a(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
    }

    @Override // android.support.v4.app.i
    public boolean a() {
        return true;
    }

    public boolean a(g gVar) {
        return true;
    }

    public boolean a(String str) {
        return false;
    }

    /* access modifiers changed from: package-private */
    public void b(g gVar) {
    }

    public void d() {
    }

    public boolean e() {
        return true;
    }

    public abstract E g();

    k(h hVar) {
        this(hVar, hVar, hVar.f195a, 0);
    }

    k(Activity activity, Context context, Handler handler, int i) {
        this.f206b = new m();
        this.f205a = activity;
        this.f207c = (Context) android.support.v4.util.k.a(context, "context == null");
        this.f208d = (Handler) android.support.v4.util.k.a(handler, "handler == null");
        this.e = i;
    }

    public LayoutInflater b() {
        return LayoutInflater.from(this.f207c);
    }

    public void a(g gVar, Intent intent, int i, Bundle bundle) {
        if (i == -1) {
            this.f207c.startActivity(intent);
            return;
        }
        throw new IllegalStateException("Starting activity with a requestCode requires a FragmentActivity host");
    }

    public void a(g gVar, IntentSender intentSender, int i, Intent intent, int i2, int i3, int i4, Bundle bundle) {
        if (i == -1) {
            a.a(this.f205a, intentSender, i, intent, i2, i3, i4, bundle);
            return;
        }
        throw new IllegalStateException("Starting intent sender with a requestCode requires a FragmentActivity host");
    }

    public int f() {
        return this.e;
    }

    /* access modifiers changed from: package-private */
    public Activity h() {
        return this.f205a;
    }

    /* access modifiers changed from: package-private */
    public Context i() {
        return this.f207c;
    }

    /* access modifiers changed from: package-private */
    public Handler j() {
        return this.f208d;
    }

    /* access modifiers changed from: package-private */
    public m k() {
        return this.f206b;
    }
}
