package android.arch.lifecycle;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.arch.lifecycle.c;
import android.os.Bundle;

/* compiled from: ReportFragment */
public class m extends Fragment {

    /* renamed from: a  reason: collision with root package name */
    private a f94a;

    /* access modifiers changed from: package-private */
    /* compiled from: ReportFragment */
    public interface a {
        void a();

        void b();

        void c();
    }

    public static void a(Activity activity) {
        FragmentManager fragmentManager = activity.getFragmentManager();
        if (fragmentManager.findFragmentByTag("android.arch.lifecycle.LifecycleDispatcher.report_fragment_tag") == null) {
            fragmentManager.beginTransaction().add(new m(), "android.arch.lifecycle.LifecycleDispatcher.report_fragment_tag").commit();
            fragmentManager.executePendingTransactions();
        }
    }

    private void a(a aVar) {
        if (aVar != null) {
            aVar.a();
        }
    }

    private void b(a aVar) {
        if (aVar != null) {
            aVar.b();
        }
    }

    private void c(a aVar) {
        if (aVar != null) {
            aVar.c();
        }
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        a(this.f94a);
        a(c.a.ON_CREATE);
    }

    public void onStart() {
        super.onStart();
        b(this.f94a);
        a(c.a.ON_START);
    }

    public void onResume() {
        super.onResume();
        c(this.f94a);
        a(c.a.ON_RESUME);
    }

    public void onPause() {
        super.onPause();
        a(c.a.ON_PAUSE);
    }

    public void onStop() {
        super.onStop();
        a(c.a.ON_STOP);
    }

    public void onDestroy() {
        super.onDestroy();
        a(c.a.ON_DESTROY);
        this.f94a = null;
    }

    private void a(c.a aVar) {
        Activity activity = getActivity();
        if (activity instanceof g) {
            ((g) activity).a().a(aVar);
        } else if (activity instanceof e) {
            c lifecycle = ((e) activity).getLifecycle();
            if (lifecycle instanceof f) {
                ((f) lifecycle).a(aVar);
            }
        }
    }
}
