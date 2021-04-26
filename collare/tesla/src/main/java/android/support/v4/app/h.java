package android.support.v4.app;

import android.app.Activity;
import android.arch.lifecycle.c;
import android.arch.lifecycle.p;
import android.arch.lifecycle.q;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.support.v4.app.a;
import android.support.v4.util.m;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import java.io.FileDescriptor;
import java.io.PrintWriter;

/* compiled from: FragmentActivity */
public class h extends ag implements q, a.AbstractC0004a, a.c {

    /* renamed from: a  reason: collision with root package name */
    final Handler f195a = new Handler() {
        /* class android.support.v4.app.h.AnonymousClass1 */

        public void handleMessage(Message message) {
            if (message.what != 2) {
                super.handleMessage(message);
                return;
            }
            h.this.a_();
            h.this.f196b.m();
        }
    };

    /* renamed from: b  reason: collision with root package name */
    final j f196b = j.a(new a());

    /* renamed from: c  reason: collision with root package name */
    boolean f197c;

    /* renamed from: d  reason: collision with root package name */
    boolean f198d;
    boolean e = true;
    boolean f;
    boolean g;
    boolean h;
    int i;
    m<String> j;
    private p k;

    public void a(g gVar) {
    }

    public Object b() {
        return null;
    }

    /* compiled from: FragmentActivity */
    static final class b {

        /* renamed from: a  reason: collision with root package name */
        Object f201a;

        /* renamed from: b  reason: collision with root package name */
        p f202b;

        /* renamed from: c  reason: collision with root package name */
        n f203c;

        b() {
        }
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i2, int i3, Intent intent) {
        this.f196b.b();
        int i4 = i2 >> 16;
        if (i4 != 0) {
            int i5 = i4 - 1;
            String a2 = this.j.a(i5);
            this.j.c(i5);
            if (a2 == null) {
                Log.w("FragmentActivity", "Activity result delivered for unknown Fragment.");
                return;
            }
            g a3 = this.f196b.a(a2);
            if (a3 == null) {
                Log.w("FragmentActivity", "Activity result no fragment exists for who: " + a2);
                return;
            }
            a3.onActivityResult(i2 & 65535, i3, intent);
            return;
        }
        a.b a4 = a.a();
        if (a4 == null || !a4.a(this, i2, i3, intent)) {
            super.onActivityResult(i2, i3, intent);
        }
    }

    public void onBackPressed() {
        l a2 = this.f196b.a();
        boolean d2 = a2.d();
        if (d2 && Build.VERSION.SDK_INT <= 25) {
            return;
        }
        if (d2 || !a2.b()) {
            super.onBackPressed();
        }
    }

    public void onMultiWindowModeChanged(boolean z) {
        this.f196b.a(z);
    }

    public void onPictureInPictureModeChanged(boolean z) {
        this.f196b.b(z);
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        this.f196b.b();
        this.f196b.a(configuration);
    }

    @Override // android.arch.lifecycle.q
    public p getViewModelStore() {
        if (getApplication() != null) {
            if (this.k == null) {
                b bVar = (b) getLastNonConfigurationInstance();
                if (bVar != null) {
                    this.k = bVar.f202b;
                }
                if (this.k == null) {
                    this.k = new p();
                }
            }
            return this.k;
        }
        throw new IllegalStateException("Your activity is not yet attached to the Application instance. You can't request ViewModel before onCreate call.");
    }

    @Override // android.support.v4.app.ag, android.arch.lifecycle.e
    public c getLifecycle() {
        return super.getLifecycle();
    }

    /* access modifiers changed from: protected */
    @Override // android.support.v4.app.ag
    public void onCreate(Bundle bundle) {
        n nVar = null;
        this.f196b.a((g) null);
        super.onCreate(bundle);
        b bVar = (b) getLastNonConfigurationInstance();
        if (!(bVar == null || bVar.f202b == null || this.k != null)) {
            this.k = bVar.f202b;
        }
        if (bundle != null) {
            Parcelable parcelable = bundle.getParcelable("android:support:fragments");
            j jVar = this.f196b;
            if (bVar != null) {
                nVar = bVar.f203c;
            }
            jVar.a(parcelable, nVar);
            if (bundle.containsKey("android:support:next_request_index")) {
                this.i = bundle.getInt("android:support:next_request_index");
                int[] intArray = bundle.getIntArray("android:support:request_indicies");
                String[] stringArray = bundle.getStringArray("android:support:request_fragment_who");
                if (intArray == null || stringArray == null || intArray.length != stringArray.length) {
                    Log.w("FragmentActivity", "Invalid requestCode mapping in savedInstanceState.");
                } else {
                    this.j = new m<>(intArray.length);
                    for (int i2 = 0; i2 < intArray.length; i2++) {
                        this.j.b(intArray[i2], stringArray[i2]);
                    }
                }
            }
        }
        if (this.j == null) {
            this.j = new m<>();
            this.i = 0;
        }
        this.f196b.e();
    }

    public boolean onCreatePanelMenu(int i2, Menu menu) {
        if (i2 == 0) {
            return super.onCreatePanelMenu(i2, menu) | this.f196b.a(menu, getMenuInflater());
        }
        return super.onCreatePanelMenu(i2, menu);
    }

    public View onCreateView(View view, String str, Context context, AttributeSet attributeSet) {
        View a2 = a(view, str, context, attributeSet);
        return a2 == null ? super.onCreateView(view, str, context, attributeSet) : a2;
    }

    public View onCreateView(String str, Context context, AttributeSet attributeSet) {
        View a2 = a((View) null, str, context, attributeSet);
        return a2 == null ? super.onCreateView(str, context, attributeSet) : a2;
    }

    /* access modifiers changed from: package-private */
    public final View a(View view, String str, Context context, AttributeSet attributeSet) {
        return this.f196b.a(view, str, context, attributeSet);
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        if (this.k != null && !isChangingConfigurations()) {
            this.k.a();
        }
        this.f196b.k();
    }

    public void onLowMemory() {
        super.onLowMemory();
        this.f196b.l();
    }

    public boolean onMenuItemSelected(int i2, MenuItem menuItem) {
        if (super.onMenuItemSelected(i2, menuItem)) {
            return true;
        }
        if (i2 == 0) {
            return this.f196b.a(menuItem);
        }
        if (i2 != 6) {
            return false;
        }
        return this.f196b.b(menuItem);
    }

    public void onPanelClosed(int i2, Menu menu) {
        if (i2 == 0) {
            this.f196b.b(menu);
        }
        super.onPanelClosed(i2, menu);
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        this.f198d = false;
        if (this.f195a.hasMessages(2)) {
            this.f195a.removeMessages(2);
            a_();
        }
        this.f196b.i();
    }

    /* access modifiers changed from: protected */
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        this.f196b.b();
    }

    public void onStateNotSaved() {
        this.f196b.b();
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        this.f195a.sendEmptyMessage(2);
        this.f198d = true;
        this.f196b.m();
    }

    /* access modifiers changed from: protected */
    public void onPostResume() {
        super.onPostResume();
        this.f195a.removeMessages(2);
        a_();
        this.f196b.m();
    }

    /* access modifiers changed from: protected */
    public void a_() {
        this.f196b.h();
    }

    public boolean onPreparePanel(int i2, View view, Menu menu) {
        if (i2 != 0 || menu == null) {
            return super.onPreparePanel(i2, view, menu);
        }
        return a(view, menu) | this.f196b.a(menu);
    }

    /* access modifiers changed from: protected */
    public boolean a(View view, Menu menu) {
        return super.onPreparePanel(0, view, menu);
    }

    public final Object onRetainNonConfigurationInstance() {
        Object b2 = b();
        n d2 = this.f196b.d();
        if (d2 == null && this.k == null && b2 == null) {
            return null;
        }
        b bVar = new b();
        bVar.f201a = b2;
        bVar.f202b = this.k;
        bVar.f203c = d2;
        return bVar;
    }

    /* access modifiers changed from: protected */
    @Override // android.support.v4.app.ag
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        e();
        Parcelable c2 = this.f196b.c();
        if (c2 != null) {
            bundle.putParcelable("android:support:fragments", c2);
        }
        if (this.j.b() > 0) {
            bundle.putInt("android:support:next_request_index", this.i);
            int[] iArr = new int[this.j.b()];
            String[] strArr = new String[this.j.b()];
            for (int i2 = 0; i2 < this.j.b(); i2++) {
                iArr[i2] = this.j.d(i2);
                strArr[i2] = this.j.e(i2);
            }
            bundle.putIntArray("android:support:request_indicies", iArr);
            bundle.putStringArray("android:support:request_fragment_who", strArr);
        }
    }

    /* access modifiers changed from: protected */
    public void onStart() {
        super.onStart();
        this.e = false;
        if (!this.f197c) {
            this.f197c = true;
            this.f196b.f();
        }
        this.f196b.b();
        this.f196b.m();
        this.f196b.g();
    }

    /* access modifiers changed from: protected */
    public void onStop() {
        super.onStop();
        this.e = true;
        e();
        this.f196b.j();
    }

    @Deprecated
    public void c() {
        invalidateOptionsMenu();
    }

    public void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        super.dump(str, fileDescriptor, printWriter, strArr);
        printWriter.print(str);
        printWriter.print("Local FragmentActivity ");
        printWriter.print(Integer.toHexString(System.identityHashCode(this)));
        printWriter.println(" State:");
        String str2 = str + "  ";
        printWriter.print(str2);
        printWriter.print("mCreated=");
        printWriter.print(this.f197c);
        printWriter.print(" mResumed=");
        printWriter.print(this.f198d);
        printWriter.print(" mStopped=");
        printWriter.print(this.e);
        if (getApplication() != null) {
            v.a(this).a(str2, fileDescriptor, printWriter, strArr);
        }
        this.f196b.a().a(str, fileDescriptor, printWriter, strArr);
    }

    public l d() {
        return this.f196b.a();
    }

    public void startActivityForResult(Intent intent, int i2) {
        if (!this.h && i2 != -1) {
            b(i2);
        }
        super.startActivityForResult(intent, i2);
    }

    public void startActivityForResult(Intent intent, int i2, Bundle bundle) {
        if (!this.h && i2 != -1) {
            b(i2);
        }
        super.startActivityForResult(intent, i2, bundle);
    }

    @Override // android.app.Activity
    public void startIntentSenderForResult(IntentSender intentSender, int i2, Intent intent, int i3, int i4, int i5) {
        if (!this.g && i2 != -1) {
            b(i2);
        }
        super.startIntentSenderForResult(intentSender, i2, intent, i3, i4, i5);
    }

    @Override // android.app.Activity
    public void startIntentSenderForResult(IntentSender intentSender, int i2, Intent intent, int i3, int i4, int i5, Bundle bundle) {
        if (!this.g && i2 != -1) {
            b(i2);
        }
        super.startIntentSenderForResult(intentSender, i2, intent, i3, i4, i5, bundle);
    }

    static void b(int i2) {
        if ((i2 & -65536) != 0) {
            throw new IllegalArgumentException("Can only use lower 16 bits for requestCode");
        }
    }

    @Override // android.support.v4.app.a.c
    public final void a(int i2) {
        if (!this.f && i2 != -1) {
            b(i2);
        }
    }

    @Override // android.support.v4.app.a.AbstractC0004a
    public void onRequestPermissionsResult(int i2, String[] strArr, int[] iArr) {
        this.f196b.b();
        int i3 = (i2 >> 16) & 65535;
        if (i3 != 0) {
            int i4 = i3 - 1;
            String a2 = this.j.a(i4);
            this.j.c(i4);
            if (a2 == null) {
                Log.w("FragmentActivity", "Activity result delivered for unknown Fragment.");
                return;
            }
            g a3 = this.f196b.a(a2);
            if (a3 == null) {
                Log.w("FragmentActivity", "Activity result no fragment exists for who: " + a2);
                return;
            }
            a3.onRequestPermissionsResult(i2 & 65535, strArr, iArr);
        }
    }

    public void a(g gVar, Intent intent, int i2, Bundle bundle) {
        this.h = true;
        if (i2 == -1) {
            try {
                a.a(this, intent, -1, bundle);
            } finally {
                this.h = false;
            }
        } else {
            b(i2);
            a.a(this, intent, ((b(gVar) + 1) << 16) + (i2 & 65535), bundle);
            this.h = false;
        }
    }

    public void a(g gVar, IntentSender intentSender, int i2, Intent intent, int i3, int i4, int i5, Bundle bundle) {
        this.g = true;
        if (i2 == -1) {
            try {
                a.a(this, intentSender, i2, intent, i3, i4, i5, bundle);
            } finally {
                this.g = false;
            }
        } else {
            b(i2);
            a.a(this, intentSender, ((b(gVar) + 1) << 16) + (i2 & 65535), intent, i3, i4, i5, bundle);
            this.g = false;
        }
    }

    private int b(g gVar) {
        if (this.j.b() < 65534) {
            while (this.j.f(this.i) >= 0) {
                this.i = (this.i + 1) % 65534;
            }
            int i2 = this.i;
            this.j.b(i2, gVar.mWho);
            this.i = (this.i + 1) % 65534;
            return i2;
        }
        throw new IllegalStateException("Too many pending Fragment activity results.");
    }

    /* JADX INFO: finally extract failed */
    /* access modifiers changed from: package-private */
    public void a(g gVar, String[] strArr, int i2) {
        if (i2 == -1) {
            a.a(this, strArr, i2);
            return;
        }
        b(i2);
        try {
            this.f = true;
            a.a(this, strArr, ((b(gVar) + 1) << 16) + (i2 & 65535));
            this.f = false;
        } catch (Throwable th) {
            this.f = false;
            throw th;
        }
    }

    /* compiled from: FragmentActivity */
    class a extends k<h> {
        public a() {
            super(h.this);
        }

        @Override // android.support.v4.app.k
        public void a(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
            h.this.dump(str, fileDescriptor, printWriter, strArr);
        }

        @Override // android.support.v4.app.k
        public boolean a(g gVar) {
            return !h.this.isFinishing();
        }

        @Override // android.support.v4.app.k
        public LayoutInflater b() {
            return h.this.getLayoutInflater().cloneInContext(h.this);
        }

        /* renamed from: c */
        public h g() {
            return h.this;
        }

        @Override // android.support.v4.app.k
        public void d() {
            h.this.c();
        }

        @Override // android.support.v4.app.k
        public void a(g gVar, Intent intent, int i, Bundle bundle) {
            h.this.a(gVar, intent, i, bundle);
        }

        @Override // android.support.v4.app.k
        public void a(g gVar, IntentSender intentSender, int i, Intent intent, int i2, int i3, int i4, Bundle bundle) {
            h.this.a(gVar, intentSender, i, intent, i2, i3, i4, bundle);
        }

        @Override // android.support.v4.app.k
        public void a(g gVar, String[] strArr, int i) {
            h.this.a(gVar, strArr, i);
        }

        @Override // android.support.v4.app.k
        public boolean a(String str) {
            return a.a((Activity) h.this, str);
        }

        @Override // android.support.v4.app.k
        public boolean e() {
            return h.this.getWindow() != null;
        }

        @Override // android.support.v4.app.k
        public int f() {
            Window window = h.this.getWindow();
            if (window == null) {
                return 0;
            }
            return window.getAttributes().windowAnimations;
        }

        @Override // android.support.v4.app.k
        public void b(g gVar) {
            h.this.a(gVar);
        }

        @Override // android.support.v4.app.k, android.support.v4.app.i
        public View a(int i) {
            return h.this.findViewById(i);
        }

        @Override // android.support.v4.app.k, android.support.v4.app.i
        public boolean a() {
            Window window = h.this.getWindow();
            return (window == null || window.peekDecorView() == null) ? false : true;
        }
    }

    private void e() {
        do {
        } while (a(d(), c.b.CREATED));
    }

    private static boolean a(l lVar, c.b bVar) {
        boolean z = false;
        for (g gVar : lVar.c()) {
            if (gVar != null) {
                if (gVar.getLifecycle().a().a(c.b.STARTED)) {
                    gVar.mLifecycleRegistry.a(bVar);
                    z = true;
                }
                l peekChildFragmentManager = gVar.peekChildFragmentManager();
                if (peekChildFragmentManager != null) {
                    z |= a(peekChildFragmentManager, bVar);
                }
            }
        }
        return z;
    }
}
