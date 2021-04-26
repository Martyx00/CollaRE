package android.support.v4.app;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

/* compiled from: FragmentController */
public class j {

    /* renamed from: a  reason: collision with root package name */
    private final k<?> f204a;

    public static j a(k<?> kVar) {
        return new j(kVar);
    }

    private j(k<?> kVar) {
        this.f204a = kVar;
    }

    public l a() {
        return this.f204a.k();
    }

    public g a(String str) {
        return this.f204a.f206b.b(str);
    }

    public void a(g gVar) {
        m mVar = this.f204a.f206b;
        k<?> kVar = this.f204a;
        mVar.a(kVar, kVar, gVar);
    }

    public View a(View view, String str, Context context, AttributeSet attributeSet) {
        return this.f204a.f206b.onCreateView(view, str, context, attributeSet);
    }

    public void b() {
        this.f204a.f206b.m();
    }

    public Parcelable c() {
        return this.f204a.f206b.l();
    }

    public void a(Parcelable parcelable, n nVar) {
        this.f204a.f206b.a(parcelable, nVar);
    }

    public n d() {
        return this.f204a.f206b.j();
    }

    public void e() {
        this.f204a.f206b.n();
    }

    public void f() {
        this.f204a.f206b.o();
    }

    public void g() {
        this.f204a.f206b.p();
    }

    public void h() {
        this.f204a.f206b.q();
    }

    public void i() {
        this.f204a.f206b.r();
    }

    public void j() {
        this.f204a.f206b.s();
    }

    public void k() {
        this.f204a.f206b.u();
    }

    public void a(boolean z) {
        this.f204a.f206b.a(z);
    }

    public void b(boolean z) {
        this.f204a.f206b.b(z);
    }

    public void a(Configuration configuration) {
        this.f204a.f206b.a(configuration);
    }

    public void l() {
        this.f204a.f206b.v();
    }

    public boolean a(Menu menu, MenuInflater menuInflater) {
        return this.f204a.f206b.a(menu, menuInflater);
    }

    public boolean a(Menu menu) {
        return this.f204a.f206b.a(menu);
    }

    public boolean a(MenuItem menuItem) {
        return this.f204a.f206b.a(menuItem);
    }

    public boolean b(MenuItem menuItem) {
        return this.f204a.f206b.b(menuItem);
    }

    public void b(Menu menu) {
        this.f204a.f206b.b(menu);
    }

    public boolean m() {
        return this.f204a.f206b.g();
    }
}
