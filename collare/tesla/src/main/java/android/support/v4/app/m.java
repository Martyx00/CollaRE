package android.support.v4.app;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.arch.lifecycle.p;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Build;
import android.os.Bundle;
import android.os.Looper;
import android.os.Parcelable;
import android.support.v4.app.g;
import android.support.v4.app.l;
import android.support.v4.g.t;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.ScaleAnimation;
import android.view.animation.Transformation;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/* access modifiers changed from: package-private */
/* compiled from: FragmentManager */
public final class m extends l implements LayoutInflater.Factory2 {
    static final Interpolator F = new DecelerateInterpolator(2.5f);
    static final Interpolator G = new DecelerateInterpolator(1.5f);
    static final Interpolator H = new AccelerateInterpolator(2.5f);
    static final Interpolator I = new AccelerateInterpolator(1.5f);

    /* renamed from: a  reason: collision with root package name */
    static boolean f209a = false;
    static Field q;
    Bundle A = null;
    SparseArray<Parcelable> B = null;
    ArrayList<j> C;
    n D;
    Runnable E = new Runnable() {
        /* class android.support.v4.app.m.AnonymousClass1 */

        public void run() {
            m.this.g();
        }
    };
    private final CopyOnWriteArrayList<f> J = new CopyOnWriteArrayList<>();

    /* renamed from: b  reason: collision with root package name */
    ArrayList<h> f210b;

    /* renamed from: c  reason: collision with root package name */
    boolean f211c;

    /* renamed from: d  reason: collision with root package name */
    int f212d = 0;
    final ArrayList<g> e = new ArrayList<>();
    SparseArray<g> f;
    ArrayList<c> g;
    ArrayList<g> h;
    ArrayList<c> i;
    ArrayList<Integer> j;
    ArrayList<l.b> k;
    int l = 0;
    k m;
    i n;
    g o;
    g p;
    boolean r;
    boolean s;
    boolean t;
    boolean u;
    String v;
    boolean w;
    ArrayList<c> x;
    ArrayList<Boolean> y;
    ArrayList<g> z;

    /* access modifiers changed from: private */
    /* compiled from: FragmentManager */
    public static final class f {

        /* renamed from: a  reason: collision with root package name */
        final l.a f236a;

        /* renamed from: b  reason: collision with root package name */
        final boolean f237b;
    }

    /* access modifiers changed from: package-private */
    /* compiled from: FragmentManager */
    public static class g {

        /* renamed from: a  reason: collision with root package name */
        public static final int[] f238a = {16842755, 16842960, 16842961};
    }

    /* access modifiers changed from: package-private */
    /* compiled from: FragmentManager */
    public interface h {
        boolean a(ArrayList<c> arrayList, ArrayList<Boolean> arrayList2);
    }

    public static int b(int i2, boolean z2) {
        if (i2 == 4097) {
            return z2 ? 1 : 2;
        }
        if (i2 == 4099) {
            return z2 ? 5 : 6;
        }
        if (i2 != 8194) {
            return -1;
        }
        return z2 ? 3 : 4;
    }

    public static int d(int i2) {
        if (i2 == 4097) {
            return 8194;
        }
        if (i2 != 4099) {
            return i2 != 8194 ? 0 : 4097;
        }
        return 4099;
    }

    /* access modifiers changed from: package-private */
    public LayoutInflater.Factory2 x() {
        return this;
    }

    m() {
    }

    static boolean a(c cVar) {
        if (cVar.f229a instanceof AlphaAnimation) {
            return true;
        }
        if (!(cVar.f229a instanceof AnimationSet)) {
            return a(cVar.f230b);
        }
        List<Animation> animations = ((AnimationSet) cVar.f229a).getAnimations();
        for (int i2 = 0; i2 < animations.size(); i2++) {
            if (animations.get(i2) instanceof AlphaAnimation) {
                return true;
            }
        }
        return false;
    }

    static boolean a(Animator animator) {
        PropertyValuesHolder[] values;
        if (animator == null) {
            return false;
        }
        if (animator instanceof ValueAnimator) {
            for (PropertyValuesHolder propertyValuesHolder : ((ValueAnimator) animator).getValues()) {
                if ("alpha".equals(propertyValuesHolder.getPropertyName())) {
                    return true;
                }
            }
        } else if (animator instanceof AnimatorSet) {
            ArrayList<Animator> childAnimations = ((AnimatorSet) animator).getChildAnimations();
            for (int i2 = 0; i2 < childAnimations.size(); i2++) {
                if (a(childAnimations.get(i2))) {
                    return true;
                }
            }
        }
        return false;
    }

    static boolean a(View view, c cVar) {
        if (view == null || cVar == null || Build.VERSION.SDK_INT < 19 || view.getLayerType() != 0 || !t.o(view) || !a(cVar)) {
            return false;
        }
        return true;
    }

    private void a(RuntimeException runtimeException) {
        Log.e("FragmentManager", runtimeException.getMessage());
        Log.e("FragmentManager", "Activity state:");
        PrintWriter printWriter = new PrintWriter(new android.support.v4.util.e("FragmentManager"));
        k kVar = this.m;
        if (kVar != null) {
            try {
                kVar.a("  ", (FileDescriptor) null, printWriter, new String[0]);
            } catch (Exception e2) {
                Log.e("FragmentManager", "Failed dumping state", e2);
            }
        } else {
            try {
                a("  ", (FileDescriptor) null, printWriter, new String[0]);
            } catch (Exception e3) {
                Log.e("FragmentManager", "Failed dumping state", e3);
            }
        }
        throw runtimeException;
    }

    @Override // android.support.v4.app.l
    public q a() {
        return new c(this);
    }

    @Override // android.support.v4.app.l
    public boolean b() {
        y();
        return a((String) null, -1, 0);
    }

    @Override // android.support.v4.app.l
    public void a(int i2, int i3) {
        if (i2 >= 0) {
            a((h) new i(null, i2, i3), false);
            return;
        }
        throw new IllegalArgumentException("Bad id: " + i2);
    }

    private boolean a(String str, int i2, int i3) {
        l peekChildFragmentManager;
        g();
        c(true);
        g gVar = this.p;
        if (gVar != null && i2 < 0 && str == null && (peekChildFragmentManager = gVar.peekChildFragmentManager()) != null && peekChildFragmentManager.b()) {
            return true;
        }
        boolean a2 = a(this.x, this.y, str, i2, i3);
        if (a2) {
            this.f211c = true;
            try {
                b(this.x, this.y);
            } finally {
                z();
            }
        }
        h();
        C();
        return a2;
    }

    public void a(Bundle bundle, String str, g gVar) {
        if (gVar.mIndex < 0) {
            a(new IllegalStateException("Fragment " + gVar + " is not currently in the FragmentManager"));
        }
        bundle.putInt(str, gVar.mIndex);
    }

    public g a(Bundle bundle, String str) {
        int i2 = bundle.getInt(str, -1);
        if (i2 == -1) {
            return null;
        }
        g gVar = this.f.get(i2);
        if (gVar == null) {
            a(new IllegalStateException("Fragment no longer exists for key " + str + ": index " + i2));
        }
        return gVar;
    }

    @Override // android.support.v4.app.l
    public List<g> c() {
        List<g> list;
        if (this.e.isEmpty()) {
            return Collections.emptyList();
        }
        synchronized (this.e) {
            list = (List) this.e.clone();
        }
        return list;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(128);
        sb.append("FragmentManager{");
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        sb.append(" in ");
        g gVar = this.o;
        if (gVar != null) {
            android.support.v4.util.d.a(gVar, sb);
        } else {
            android.support.v4.util.d.a(this.m, sb);
        }
        sb.append("}}");
        return sb.toString();
    }

    @Override // android.support.v4.app.l
    public void a(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        int size;
        int size2;
        int size3;
        int size4;
        int size5;
        String str2 = str + "    ";
        SparseArray<g> sparseArray = this.f;
        if (sparseArray != null && (size5 = sparseArray.size()) > 0) {
            printWriter.print(str);
            printWriter.print("Active Fragments in ");
            printWriter.print(Integer.toHexString(System.identityHashCode(this)));
            printWriter.println(":");
            for (int i2 = 0; i2 < size5; i2++) {
                g valueAt = this.f.valueAt(i2);
                printWriter.print(str);
                printWriter.print("  #");
                printWriter.print(i2);
                printWriter.print(": ");
                printWriter.println(valueAt);
                if (valueAt != null) {
                    valueAt.dump(str2, fileDescriptor, printWriter, strArr);
                }
            }
        }
        int size6 = this.e.size();
        if (size6 > 0) {
            printWriter.print(str);
            printWriter.println("Added Fragments:");
            for (int i3 = 0; i3 < size6; i3++) {
                printWriter.print(str);
                printWriter.print("  #");
                printWriter.print(i3);
                printWriter.print(": ");
                printWriter.println(this.e.get(i3).toString());
            }
        }
        ArrayList<g> arrayList = this.h;
        if (arrayList != null && (size4 = arrayList.size()) > 0) {
            printWriter.print(str);
            printWriter.println("Fragments Created Menus:");
            for (int i4 = 0; i4 < size4; i4++) {
                printWriter.print(str);
                printWriter.print("  #");
                printWriter.print(i4);
                printWriter.print(": ");
                printWriter.println(this.h.get(i4).toString());
            }
        }
        ArrayList<c> arrayList2 = this.g;
        if (arrayList2 != null && (size3 = arrayList2.size()) > 0) {
            printWriter.print(str);
            printWriter.println("Back Stack:");
            for (int i5 = 0; i5 < size3; i5++) {
                c cVar = this.g.get(i5);
                printWriter.print(str);
                printWriter.print("  #");
                printWriter.print(i5);
                printWriter.print(": ");
                printWriter.println(cVar.toString());
                cVar.a(str2, fileDescriptor, printWriter, strArr);
            }
        }
        synchronized (this) {
            if (this.i != null && (size2 = this.i.size()) > 0) {
                printWriter.print(str);
                printWriter.println("Back Stack Indices:");
                for (int i6 = 0; i6 < size2; i6++) {
                    printWriter.print(str);
                    printWriter.print("  #");
                    printWriter.print(i6);
                    printWriter.print(": ");
                    printWriter.println((c) this.i.get(i6));
                }
            }
            if (this.j != null && this.j.size() > 0) {
                printWriter.print(str);
                printWriter.print("mAvailBackStackIndices: ");
                printWriter.println(Arrays.toString(this.j.toArray()));
            }
        }
        ArrayList<h> arrayList3 = this.f210b;
        if (arrayList3 != null && (size = arrayList3.size()) > 0) {
            printWriter.print(str);
            printWriter.println("Pending Actions:");
            for (int i7 = 0; i7 < size; i7++) {
                printWriter.print(str);
                printWriter.print("  #");
                printWriter.print(i7);
                printWriter.print(": ");
                printWriter.println((h) this.f210b.get(i7));
            }
        }
        printWriter.print(str);
        printWriter.println("FragmentManager misc state:");
        printWriter.print(str);
        printWriter.print("  mHost=");
        printWriter.println(this.m);
        printWriter.print(str);
        printWriter.print("  mContainer=");
        printWriter.println(this.n);
        if (this.o != null) {
            printWriter.print(str);
            printWriter.print("  mParent=");
            printWriter.println(this.o);
        }
        printWriter.print(str);
        printWriter.print("  mCurState=");
        printWriter.print(this.l);
        printWriter.print(" mStateSaved=");
        printWriter.print(this.s);
        printWriter.print(" mStopped=");
        printWriter.print(this.t);
        printWriter.print(" mDestroyed=");
        printWriter.println(this.u);
        if (this.r) {
            printWriter.print(str);
            printWriter.print("  mNeedMenuInvalidate=");
            printWriter.println(this.r);
        }
        if (this.v != null) {
            printWriter.print(str);
            printWriter.print("  mNoTransactionsBecause=");
            printWriter.println(this.v);
        }
    }

    static c a(Context context, float f2, float f3, float f4, float f5) {
        AnimationSet animationSet = new AnimationSet(false);
        ScaleAnimation scaleAnimation = new ScaleAnimation(f2, f3, f2, f3, 1, 0.5f, 1, 0.5f);
        scaleAnimation.setInterpolator(F);
        scaleAnimation.setDuration(220);
        animationSet.addAnimation(scaleAnimation);
        AlphaAnimation alphaAnimation = new AlphaAnimation(f4, f5);
        alphaAnimation.setInterpolator(G);
        alphaAnimation.setDuration(220);
        animationSet.addAnimation(alphaAnimation);
        return new c(animationSet);
    }

    static c a(Context context, float f2, float f3) {
        AlphaAnimation alphaAnimation = new AlphaAnimation(f2, f3);
        alphaAnimation.setInterpolator(G);
        alphaAnimation.setDuration(220);
        return new c(alphaAnimation);
    }

    /* access modifiers changed from: package-private */
    public c a(g gVar, int i2, boolean z2, int i3) {
        int b2;
        int nextAnim = gVar.getNextAnim();
        Animation onCreateAnimation = gVar.onCreateAnimation(i2, z2, nextAnim);
        if (onCreateAnimation != null) {
            return new c(onCreateAnimation);
        }
        Animator onCreateAnimator = gVar.onCreateAnimator(i2, z2, nextAnim);
        if (onCreateAnimator != null) {
            return new c(onCreateAnimator);
        }
        if (nextAnim != 0) {
            boolean equals = "anim".equals(this.m.i().getResources().getResourceTypeName(nextAnim));
            boolean z3 = false;
            if (equals) {
                try {
                    Animation loadAnimation = AnimationUtils.loadAnimation(this.m.i(), nextAnim);
                    if (loadAnimation != null) {
                        return new c(loadAnimation);
                    }
                    z3 = true;
                } catch (Resources.NotFoundException e2) {
                    throw e2;
                } catch (RuntimeException unused) {
                }
            }
            if (!z3) {
                try {
                    Animator loadAnimator = AnimatorInflater.loadAnimator(this.m.i(), nextAnim);
                    if (loadAnimator != null) {
                        return new c(loadAnimator);
                    }
                } catch (RuntimeException e3) {
                    if (!equals) {
                        Animation loadAnimation2 = AnimationUtils.loadAnimation(this.m.i(), nextAnim);
                        if (loadAnimation2 != null) {
                            return new c(loadAnimation2);
                        }
                    } else {
                        throw e3;
                    }
                }
            }
        }
        if (i2 == 0 || (b2 = b(i2, z2)) < 0) {
            return null;
        }
        switch (b2) {
            case 1:
                return a(this.m.i(), 1.125f, 1.0f, (float) BitmapDescriptorFactory.HUE_RED, 1.0f);
            case 2:
                return a(this.m.i(), 1.0f, 0.975f, 1.0f, (float) BitmapDescriptorFactory.HUE_RED);
            case 3:
                return a(this.m.i(), 0.975f, 1.0f, (float) BitmapDescriptorFactory.HUE_RED, 1.0f);
            case 4:
                return a(this.m.i(), 1.0f, 1.075f, 1.0f, (float) BitmapDescriptorFactory.HUE_RED);
            case 5:
                return a(this.m.i(), (float) BitmapDescriptorFactory.HUE_RED, 1.0f);
            case 6:
                return a(this.m.i(), 1.0f, (float) BitmapDescriptorFactory.HUE_RED);
            default:
                if (i3 == 0 && this.m.e()) {
                    i3 = this.m.f();
                }
                return i3 == 0 ? null : null;
        }
    }

    public void a(g gVar) {
        if (!gVar.mDeferStart) {
            return;
        }
        if (this.f211c) {
            this.w = true;
            return;
        }
        gVar.mDeferStart = false;
        a(gVar, this.l, 0, 0, false);
    }

    private static void b(View view, c cVar) {
        if (view != null && cVar != null && a(view, cVar)) {
            if (cVar.f230b != null) {
                cVar.f230b.addListener(new d(view));
                return;
            }
            Animation.AnimationListener a2 = a(cVar.f229a);
            view.setLayerType(2, null);
            cVar.f229a.setAnimationListener(new a(view, a2));
        }
    }

    private static Animation.AnimationListener a(Animation animation) {
        try {
            if (q == null) {
                q = Animation.class.getDeclaredField("mListener");
                q.setAccessible(true);
            }
            return (Animation.AnimationListener) q.get(animation);
        } catch (NoSuchFieldException e2) {
            Log.e("FragmentManager", "No field with the name mListener is found in Animation class", e2);
            return null;
        } catch (IllegalAccessException e3) {
            Log.e("FragmentManager", "Cannot access Animation's mListener field", e3);
            return null;
        }
    }

    /* access modifiers changed from: package-private */
    public boolean a(int i2) {
        return this.l >= i2;
    }

    /* access modifiers changed from: package-private */
    public void a(g gVar, int i2, int i3, int i4, boolean z2) {
        int i5;
        ViewGroup viewGroup;
        String str;
        m mVar;
        int i6 = 1;
        boolean z3 = true;
        if (!gVar.mAdded || gVar.mDetached) {
            i5 = i2;
            if (i5 > 1) {
                i5 = 1;
            }
        } else {
            i5 = i2;
        }
        if (gVar.mRemoving && i5 > gVar.mState) {
            if (gVar.mState != 0 || !gVar.isInBackStack()) {
                i5 = gVar.mState;
            } else {
                i5 = 1;
            }
        }
        int i7 = (!gVar.mDeferStart || gVar.mState >= 3 || i5 <= 2) ? i5 : 2;
        if (gVar.mState > i7) {
            if (gVar.mState > i7) {
                switch (gVar.mState) {
                    case 4:
                        if (i7 < 4) {
                            if (f209a) {
                                Log.v("FragmentManager", "movefrom RESUMED: " + gVar);
                            }
                            gVar.performPause();
                            d(gVar, false);
                        }
                    case 3:
                        if (i7 < 3) {
                            if (f209a) {
                                Log.v("FragmentManager", "movefrom STARTED: " + gVar);
                            }
                            gVar.performStop();
                            e(gVar, false);
                        }
                    case 2:
                        if (i7 < 2) {
                            if (f209a) {
                                Log.v("FragmentManager", "movefrom ACTIVITY_CREATED: " + gVar);
                            }
                            if (gVar.mView != null && this.m.a(gVar) && gVar.mSavedViewState == null) {
                                m(gVar);
                            }
                            gVar.performDestroyView();
                            f(gVar, false);
                            if (!(gVar.mView == null || gVar.mContainer == null)) {
                                gVar.mContainer.endViewTransition(gVar.mView);
                                gVar.mView.clearAnimation();
                                c a2 = (this.l <= 0 || this.u || gVar.mView.getVisibility() != 0 || gVar.mPostponedAlpha < BitmapDescriptorFactory.HUE_RED) ? null : a(gVar, i3, false, i4);
                                gVar.mPostponedAlpha = BitmapDescriptorFactory.HUE_RED;
                                if (a2 != null) {
                                    a(gVar, a2, i7);
                                }
                                gVar.mContainer.removeView(gVar.mView);
                            }
                            gVar.mContainer = null;
                            gVar.mView = null;
                            gVar.mViewLifecycleOwner = null;
                            gVar.mViewLifecycleOwnerLiveData.b((android.arch.lifecycle.e) null);
                            gVar.mInnerView = null;
                            gVar.mInLayout = false;
                        }
                        break;
                    case 1:
                        if (i7 < 1) {
                            if (this.u) {
                                if (gVar.getAnimatingAway() != null) {
                                    View animatingAway = gVar.getAnimatingAway();
                                    gVar.setAnimatingAway(null);
                                    animatingAway.clearAnimation();
                                } else if (gVar.getAnimator() != null) {
                                    Animator animator = gVar.getAnimator();
                                    gVar.setAnimator(null);
                                    animator.cancel();
                                }
                            }
                            if (gVar.getAnimatingAway() != null || gVar.getAnimator() != null) {
                                gVar.setStateAfterAnimating(i7);
                                break;
                            } else {
                                if (f209a) {
                                    Log.v("FragmentManager", "movefrom CREATED: " + gVar);
                                }
                                if (!gVar.mRetaining) {
                                    gVar.performDestroy();
                                    g(gVar, false);
                                } else {
                                    gVar.mState = 0;
                                }
                                gVar.performDetach();
                                h(gVar, false);
                                if (!z2) {
                                    if (!gVar.mRetaining) {
                                        g(gVar);
                                    } else {
                                        gVar.mHost = null;
                                        gVar.mParentFragment = null;
                                        gVar.mFragmentManager = null;
                                    }
                                }
                            }
                        }
                        break;
                    default:
                        i6 = i7;
                        break;
                }
            }
            i6 = i7;
        } else if (!gVar.mFromLayout || gVar.mInLayout) {
            if (!(gVar.getAnimatingAway() == null && gVar.getAnimator() == null)) {
                gVar.setAnimatingAway(null);
                gVar.setAnimator(null);
                a(gVar, gVar.getStateAfterAnimating(), 0, 0, true);
            }
            switch (gVar.mState) {
                case 0:
                    if (i7 > 0) {
                        if (f209a) {
                            Log.v("FragmentManager", "moveto CREATED: " + gVar);
                        }
                        if (gVar.mSavedFragmentState != null) {
                            gVar.mSavedFragmentState.setClassLoader(this.m.i().getClassLoader());
                            gVar.mSavedViewState = gVar.mSavedFragmentState.getSparseParcelableArray("android:view_state");
                            gVar.mTarget = a(gVar.mSavedFragmentState, "android:target_state");
                            if (gVar.mTarget != null) {
                                gVar.mTargetRequestCode = gVar.mSavedFragmentState.getInt("android:target_req_state", 0);
                            }
                            if (gVar.mSavedUserVisibleHint != null) {
                                gVar.mUserVisibleHint = gVar.mSavedUserVisibleHint.booleanValue();
                                gVar.mSavedUserVisibleHint = null;
                            } else {
                                gVar.mUserVisibleHint = gVar.mSavedFragmentState.getBoolean("android:user_visible_hint", true);
                            }
                            if (!gVar.mUserVisibleHint) {
                                gVar.mDeferStart = true;
                                if (i7 > 2) {
                                    i7 = 2;
                                }
                            }
                        }
                        k kVar = this.m;
                        gVar.mHost = kVar;
                        g gVar2 = this.o;
                        gVar.mParentFragment = gVar2;
                        if (gVar2 != null) {
                            mVar = gVar2.mChildFragmentManager;
                        } else {
                            mVar = kVar.k();
                        }
                        gVar.mFragmentManager = mVar;
                        if (gVar.mTarget != null) {
                            if (this.f.get(gVar.mTarget.mIndex) != gVar.mTarget) {
                                throw new IllegalStateException("Fragment " + gVar + " declared target fragment " + gVar.mTarget + " that does not belong to this FragmentManager!");
                            } else if (gVar.mTarget.mState < 1) {
                                a(gVar.mTarget, 1, 0, 0, true);
                            }
                        }
                        a(gVar, this.m.i(), false);
                        gVar.mCalled = false;
                        gVar.onAttach(this.m.i());
                        if (gVar.mCalled) {
                            if (gVar.mParentFragment == null) {
                                this.m.b(gVar);
                            } else {
                                gVar.mParentFragment.onAttachFragment(gVar);
                            }
                            b(gVar, this.m.i(), false);
                            if (!gVar.mIsCreated) {
                                a(gVar, gVar.mSavedFragmentState, false);
                                gVar.performCreate(gVar.mSavedFragmentState);
                                b(gVar, gVar.mSavedFragmentState, false);
                            } else {
                                gVar.restoreChildFragmentState(gVar.mSavedFragmentState);
                                gVar.mState = 1;
                            }
                            gVar.mRetaining = false;
                        } else {
                            throw new af("Fragment " + gVar + " did not call through to super.onAttach()");
                        }
                    }
                case 1:
                    c(gVar);
                    if (i7 > 1) {
                        if (f209a) {
                            Log.v("FragmentManager", "moveto ACTIVITY_CREATED: " + gVar);
                        }
                        if (!gVar.mFromLayout) {
                            if (gVar.mContainerId != 0) {
                                if (gVar.mContainerId == -1) {
                                    a(new IllegalArgumentException("Cannot create fragment " + gVar + " for a container view with no id"));
                                }
                                viewGroup = (ViewGroup) this.n.a(gVar.mContainerId);
                                if (viewGroup == null && !gVar.mRestored) {
                                    try {
                                        str = gVar.getResources().getResourceName(gVar.mContainerId);
                                    } catch (Resources.NotFoundException unused) {
                                        str = "unknown";
                                    }
                                    a(new IllegalArgumentException("No view found for id 0x" + Integer.toHexString(gVar.mContainerId) + " (" + str + ") for fragment " + gVar));
                                }
                            } else {
                                viewGroup = null;
                            }
                            gVar.mContainer = viewGroup;
                            gVar.performCreateView(gVar.performGetLayoutInflater(gVar.mSavedFragmentState), viewGroup, gVar.mSavedFragmentState);
                            if (gVar.mView != null) {
                                gVar.mInnerView = gVar.mView;
                                gVar.mView.setSaveFromParentEnabled(false);
                                if (viewGroup != null) {
                                    viewGroup.addView(gVar.mView);
                                }
                                if (gVar.mHidden) {
                                    gVar.mView.setVisibility(8);
                                }
                                gVar.onViewCreated(gVar.mView, gVar.mSavedFragmentState);
                                a(gVar, gVar.mView, gVar.mSavedFragmentState, false);
                                if (gVar.mView.getVisibility() != 0 || gVar.mContainer == null) {
                                    z3 = false;
                                }
                                gVar.mIsNewlyAdded = z3;
                            } else {
                                gVar.mInnerView = null;
                            }
                        }
                        gVar.performActivityCreated(gVar.mSavedFragmentState);
                        c(gVar, gVar.mSavedFragmentState, false);
                        if (gVar.mView != null) {
                            gVar.restoreViewState(gVar.mSavedFragmentState);
                        }
                        gVar.mSavedFragmentState = null;
                    }
                case 2:
                    if (i7 > 2) {
                        if (f209a) {
                            Log.v("FragmentManager", "moveto STARTED: " + gVar);
                        }
                        gVar.performStart();
                        b(gVar, false);
                    }
                case 3:
                    if (i7 > 3) {
                        if (f209a) {
                            Log.v("FragmentManager", "moveto RESUMED: " + gVar);
                        }
                        gVar.performResume();
                        c(gVar, false);
                        gVar.mSavedFragmentState = null;
                        gVar.mSavedViewState = null;
                    }
                    i6 = i7;
                    break;
                default:
                    i6 = i7;
                    break;
            }
        } else {
            return;
        }
        if (gVar.mState != i6) {
            Log.w("FragmentManager", "moveToState: Fragment state for " + gVar + " not updated inline; " + "expected state " + i6 + " found " + gVar.mState);
            gVar.mState = i6;
        }
    }

    private void a(final g gVar, c cVar, int i2) {
        final View view = gVar.mView;
        final ViewGroup viewGroup = gVar.mContainer;
        viewGroup.startViewTransition(view);
        gVar.setStateAfterAnimating(i2);
        if (cVar.f229a != null) {
            e eVar = new e(cVar.f229a, viewGroup, view);
            gVar.setAnimatingAway(gVar.mView);
            eVar.setAnimationListener(new b(a(eVar)) {
                /* class android.support.v4.app.m.AnonymousClass2 */

                @Override // android.support.v4.app.m.b
                public void onAnimationEnd(Animation animation) {
                    super.onAnimationEnd(animation);
                    viewGroup.post(new Runnable() {
                        /* class android.support.v4.app.m.AnonymousClass2.AnonymousClass1 */

                        public void run() {
                            if (gVar.getAnimatingAway() != null) {
                                gVar.setAnimatingAway(null);
                                m.this.a(gVar, gVar.getStateAfterAnimating(), 0, 0, false);
                            }
                        }
                    });
                }
            });
            b(view, cVar);
            gVar.mView.startAnimation(eVar);
            return;
        }
        Animator animator = cVar.f230b;
        gVar.setAnimator(cVar.f230b);
        animator.addListener(new AnimatorListenerAdapter() {
            /* class android.support.v4.app.m.AnonymousClass3 */

            public void onAnimationEnd(Animator animator) {
                viewGroup.endViewTransition(view);
                Animator animator2 = gVar.getAnimator();
                gVar.setAnimator(null);
                if (animator2 != null && viewGroup.indexOfChild(view) < 0) {
                    m mVar = m.this;
                    g gVar = gVar;
                    mVar.a(gVar, gVar.getStateAfterAnimating(), 0, 0, false);
                }
            }
        });
        animator.setTarget(gVar.mView);
        b(gVar.mView, cVar);
        animator.start();
    }

    /* access modifiers changed from: package-private */
    public void b(g gVar) {
        a(gVar, this.l, 0, 0, false);
    }

    /* access modifiers changed from: package-private */
    public void c(g gVar) {
        if (gVar.mFromLayout && !gVar.mPerformedCreateView) {
            gVar.performCreateView(gVar.performGetLayoutInflater(gVar.mSavedFragmentState), null, gVar.mSavedFragmentState);
            if (gVar.mView != null) {
                gVar.mInnerView = gVar.mView;
                gVar.mView.setSaveFromParentEnabled(false);
                if (gVar.mHidden) {
                    gVar.mView.setVisibility(8);
                }
                gVar.onViewCreated(gVar.mView, gVar.mSavedFragmentState);
                a(gVar, gVar.mView, gVar.mSavedFragmentState, false);
                return;
            }
            gVar.mInnerView = null;
        }
    }

    /* access modifiers changed from: package-private */
    public void d(final g gVar) {
        if (gVar.mView != null) {
            c a2 = a(gVar, gVar.getNextTransition(), !gVar.mHidden, gVar.getNextTransitionStyle());
            if (a2 == null || a2.f230b == null) {
                if (a2 != null) {
                    b(gVar.mView, a2);
                    gVar.mView.startAnimation(a2.f229a);
                    a2.f229a.start();
                }
                gVar.mView.setVisibility((!gVar.mHidden || gVar.isHideReplaced()) ? 0 : 8);
                if (gVar.isHideReplaced()) {
                    gVar.setHideReplaced(false);
                }
            } else {
                a2.f230b.setTarget(gVar.mView);
                if (!gVar.mHidden) {
                    gVar.mView.setVisibility(0);
                } else if (gVar.isHideReplaced()) {
                    gVar.setHideReplaced(false);
                } else {
                    final ViewGroup viewGroup = gVar.mContainer;
                    final View view = gVar.mView;
                    viewGroup.startViewTransition(view);
                    a2.f230b.addListener(new AnimatorListenerAdapter() {
                        /* class android.support.v4.app.m.AnonymousClass4 */

                        public void onAnimationEnd(Animator animator) {
                            viewGroup.endViewTransition(view);
                            animator.removeListener(this);
                            if (gVar.mView != null) {
                                gVar.mView.setVisibility(8);
                            }
                        }
                    });
                }
                b(gVar.mView, a2);
                a2.f230b.start();
            }
        }
        if (gVar.mAdded && gVar.mHasMenu && gVar.mMenuVisible) {
            this.r = true;
        }
        gVar.mHiddenChanged = false;
        gVar.onHiddenChanged(gVar.mHidden);
    }

    /* access modifiers changed from: package-private */
    public void e(g gVar) {
        int i2;
        ViewGroup viewGroup;
        int indexOfChild;
        int indexOfChild2;
        if (gVar != null) {
            int i3 = this.l;
            if (gVar.mRemoving) {
                i2 = gVar.isInBackStack() ? Math.min(i3, 1) : Math.min(i3, 0);
            } else {
                i2 = i3;
            }
            a(gVar, i2, gVar.getNextTransition(), gVar.getNextTransitionStyle(), false);
            if (gVar.mView != null) {
                g p2 = p(gVar);
                if (p2 != null && (indexOfChild2 = viewGroup.indexOfChild(gVar.mView)) < (indexOfChild = (viewGroup = gVar.mContainer).indexOfChild(p2.mView))) {
                    viewGroup.removeViewAt(indexOfChild2);
                    viewGroup.addView(gVar.mView, indexOfChild);
                }
                if (gVar.mIsNewlyAdded && gVar.mContainer != null) {
                    if (gVar.mPostponedAlpha > BitmapDescriptorFactory.HUE_RED) {
                        gVar.mView.setAlpha(gVar.mPostponedAlpha);
                    }
                    gVar.mPostponedAlpha = BitmapDescriptorFactory.HUE_RED;
                    gVar.mIsNewlyAdded = false;
                    c a2 = a(gVar, gVar.getNextTransition(), true, gVar.getNextTransitionStyle());
                    if (a2 != null) {
                        b(gVar.mView, a2);
                        if (a2.f229a != null) {
                            gVar.mView.startAnimation(a2.f229a);
                        } else {
                            a2.f230b.setTarget(gVar.mView);
                            a2.f230b.start();
                        }
                    }
                }
            }
            if (gVar.mHiddenChanged) {
                d(gVar);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void a(int i2, boolean z2) {
        k kVar;
        if (this.m == null && i2 != 0) {
            throw new IllegalStateException("No activity");
        } else if (z2 || i2 != this.l) {
            this.l = i2;
            if (this.f != null) {
                int size = this.e.size();
                for (int i3 = 0; i3 < size; i3++) {
                    e(this.e.get(i3));
                }
                int size2 = this.f.size();
                for (int i4 = 0; i4 < size2; i4++) {
                    g valueAt = this.f.valueAt(i4);
                    if (valueAt != null && ((valueAt.mRemoving || valueAt.mDetached) && !valueAt.mIsNewlyAdded)) {
                        e(valueAt);
                    }
                }
                e();
                if (this.r && (kVar = this.m) != null && this.l == 4) {
                    kVar.d();
                    this.r = false;
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void e() {
        if (this.f != null) {
            for (int i2 = 0; i2 < this.f.size(); i2++) {
                g valueAt = this.f.valueAt(i2);
                if (valueAt != null) {
                    a(valueAt);
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void f(g gVar) {
        if (gVar.mIndex < 0) {
            int i2 = this.f212d;
            this.f212d = i2 + 1;
            gVar.setIndex(i2, this.o);
            if (this.f == null) {
                this.f = new SparseArray<>();
            }
            this.f.put(gVar.mIndex, gVar);
            if (f209a) {
                Log.v("FragmentManager", "Allocated fragment index " + gVar);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void g(g gVar) {
        if (gVar.mIndex >= 0) {
            if (f209a) {
                Log.v("FragmentManager", "Freeing fragment index " + gVar);
            }
            this.f.put(gVar.mIndex, null);
            gVar.initState();
        }
    }

    public void a(g gVar, boolean z2) {
        if (f209a) {
            Log.v("FragmentManager", "add: " + gVar);
        }
        f(gVar);
        if (gVar.mDetached) {
            return;
        }
        if (!this.e.contains(gVar)) {
            synchronized (this.e) {
                this.e.add(gVar);
            }
            gVar.mAdded = true;
            gVar.mRemoving = false;
            if (gVar.mView == null) {
                gVar.mHiddenChanged = false;
            }
            if (gVar.mHasMenu && gVar.mMenuVisible) {
                this.r = true;
            }
            if (z2) {
                b(gVar);
                return;
            }
            return;
        }
        throw new IllegalStateException("Fragment already added: " + gVar);
    }

    public void h(g gVar) {
        if (f209a) {
            Log.v("FragmentManager", "remove: " + gVar + " nesting=" + gVar.mBackStackNesting);
        }
        boolean z2 = !gVar.isInBackStack();
        if (!gVar.mDetached || z2) {
            synchronized (this.e) {
                this.e.remove(gVar);
            }
            if (gVar.mHasMenu && gVar.mMenuVisible) {
                this.r = true;
            }
            gVar.mAdded = false;
            gVar.mRemoving = true;
        }
    }

    public void i(g gVar) {
        if (f209a) {
            Log.v("FragmentManager", "hide: " + gVar);
        }
        if (!gVar.mHidden) {
            gVar.mHidden = true;
            gVar.mHiddenChanged = true ^ gVar.mHiddenChanged;
        }
    }

    public void j(g gVar) {
        if (f209a) {
            Log.v("FragmentManager", "show: " + gVar);
        }
        if (gVar.mHidden) {
            gVar.mHidden = false;
            gVar.mHiddenChanged = !gVar.mHiddenChanged;
        }
    }

    public void k(g gVar) {
        if (f209a) {
            Log.v("FragmentManager", "detach: " + gVar);
        }
        if (!gVar.mDetached) {
            gVar.mDetached = true;
            if (gVar.mAdded) {
                if (f209a) {
                    Log.v("FragmentManager", "remove from detach: " + gVar);
                }
                synchronized (this.e) {
                    this.e.remove(gVar);
                }
                if (gVar.mHasMenu && gVar.mMenuVisible) {
                    this.r = true;
                }
                gVar.mAdded = false;
            }
        }
    }

    public void l(g gVar) {
        if (f209a) {
            Log.v("FragmentManager", "attach: " + gVar);
        }
        if (gVar.mDetached) {
            gVar.mDetached = false;
            if (gVar.mAdded) {
                return;
            }
            if (!this.e.contains(gVar)) {
                if (f209a) {
                    Log.v("FragmentManager", "add from attach: " + gVar);
                }
                synchronized (this.e) {
                    this.e.add(gVar);
                }
                gVar.mAdded = true;
                if (gVar.mHasMenu && gVar.mMenuVisible) {
                    this.r = true;
                    return;
                }
                return;
            }
            throw new IllegalStateException("Fragment already added: " + gVar);
        }
    }

    public g b(int i2) {
        for (int size = this.e.size() - 1; size >= 0; size--) {
            g gVar = this.e.get(size);
            if (gVar != null && gVar.mFragmentId == i2) {
                return gVar;
            }
        }
        SparseArray<g> sparseArray = this.f;
        if (sparseArray == null) {
            return null;
        }
        for (int size2 = sparseArray.size() - 1; size2 >= 0; size2--) {
            g valueAt = this.f.valueAt(size2);
            if (valueAt != null && valueAt.mFragmentId == i2) {
                return valueAt;
            }
        }
        return null;
    }

    @Override // android.support.v4.app.l
    public g a(String str) {
        if (str != null) {
            for (int size = this.e.size() - 1; size >= 0; size--) {
                g gVar = this.e.get(size);
                if (gVar != null && str.equals(gVar.mTag)) {
                    return gVar;
                }
            }
        }
        SparseArray<g> sparseArray = this.f;
        if (sparseArray == null || str == null) {
            return null;
        }
        for (int size2 = sparseArray.size() - 1; size2 >= 0; size2--) {
            g valueAt = this.f.valueAt(size2);
            if (valueAt != null && str.equals(valueAt.mTag)) {
                return valueAt;
            }
        }
        return null;
    }

    public g b(String str) {
        g findFragmentByWho;
        SparseArray<g> sparseArray = this.f;
        if (sparseArray == null || str == null) {
            return null;
        }
        for (int size = sparseArray.size() - 1; size >= 0; size--) {
            g valueAt = this.f.valueAt(size);
            if (!(valueAt == null || (findFragmentByWho = valueAt.findFragmentByWho(str)) == null)) {
                return findFragmentByWho;
            }
        }
        return null;
    }

    private void y() {
        if (d()) {
            throw new IllegalStateException("Can not perform this action after onSaveInstanceState");
        } else if (this.v != null) {
            throw new IllegalStateException("Can not perform this action inside of " + this.v);
        }
    }

    @Override // android.support.v4.app.l
    public boolean d() {
        return this.s || this.t;
    }

    public void a(h hVar, boolean z2) {
        if (!z2) {
            y();
        }
        synchronized (this) {
            if (!this.u) {
                if (this.m != null) {
                    if (this.f210b == null) {
                        this.f210b = new ArrayList<>();
                    }
                    this.f210b.add(hVar);
                    f();
                    return;
                }
            }
            if (!z2) {
                throw new IllegalStateException("Activity has been destroyed");
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void f() {
        synchronized (this) {
            boolean z2 = false;
            boolean z3 = this.C != null && !this.C.isEmpty();
            if (this.f210b != null && this.f210b.size() == 1) {
                z2 = true;
            }
            if (z3 || z2) {
                this.m.j().removeCallbacks(this.E);
                this.m.j().post(this.E);
            }
        }
    }

    public int a(c cVar) {
        synchronized (this) {
            if (this.j != null) {
                if (this.j.size() > 0) {
                    int intValue = this.j.remove(this.j.size() - 1).intValue();
                    if (f209a) {
                        Log.v("FragmentManager", "Adding back stack index " + intValue + " with " + cVar);
                    }
                    this.i.set(intValue, cVar);
                    return intValue;
                }
            }
            if (this.i == null) {
                this.i = new ArrayList<>();
            }
            int size = this.i.size();
            if (f209a) {
                Log.v("FragmentManager", "Setting back stack index " + size + " to " + cVar);
            }
            this.i.add(cVar);
            return size;
        }
    }

    public void a(int i2, c cVar) {
        synchronized (this) {
            if (this.i == null) {
                this.i = new ArrayList<>();
            }
            int size = this.i.size();
            if (i2 < size) {
                if (f209a) {
                    Log.v("FragmentManager", "Setting back stack index " + i2 + " to " + cVar);
                }
                this.i.set(i2, cVar);
            } else {
                while (size < i2) {
                    this.i.add(null);
                    if (this.j == null) {
                        this.j = new ArrayList<>();
                    }
                    if (f209a) {
                        Log.v("FragmentManager", "Adding available back stack index " + size);
                    }
                    this.j.add(Integer.valueOf(size));
                    size++;
                }
                if (f209a) {
                    Log.v("FragmentManager", "Adding back stack index " + i2 + " with " + cVar);
                }
                this.i.add(cVar);
            }
        }
    }

    public void c(int i2) {
        synchronized (this) {
            this.i.set(i2, null);
            if (this.j == null) {
                this.j = new ArrayList<>();
            }
            if (f209a) {
                Log.v("FragmentManager", "Freeing back stack index " + i2);
            }
            this.j.add(Integer.valueOf(i2));
        }
    }

    private void c(boolean z2) {
        if (this.f211c) {
            throw new IllegalStateException("FragmentManager is already executing transactions");
        } else if (this.m == null) {
            throw new IllegalStateException("Fragment host has been destroyed");
        } else if (Looper.myLooper() == this.m.j().getLooper()) {
            if (!z2) {
                y();
            }
            if (this.x == null) {
                this.x = new ArrayList<>();
                this.y = new ArrayList<>();
            }
            this.f211c = true;
            try {
                a((ArrayList<c>) null, (ArrayList<Boolean>) null);
            } finally {
                this.f211c = false;
            }
        } else {
            throw new IllegalStateException("Must be called from main thread of fragment host");
        }
    }

    public void b(h hVar, boolean z2) {
        if (!z2 || (this.m != null && !this.u)) {
            c(z2);
            if (hVar.a(this.x, this.y)) {
                this.f211c = true;
                try {
                    b(this.x, this.y);
                } finally {
                    z();
                }
            }
            h();
            C();
        }
    }

    private void z() {
        this.f211c = false;
        this.y.clear();
        this.x.clear();
    }

    /* JADX INFO: finally extract failed */
    public boolean g() {
        c(true);
        boolean z2 = false;
        while (c(this.x, this.y)) {
            this.f211c = true;
            try {
                b(this.x, this.y);
                z();
                z2 = true;
            } catch (Throwable th) {
                z();
                throw th;
            }
        }
        h();
        C();
        return z2;
    }

    private void a(ArrayList<c> arrayList, ArrayList<Boolean> arrayList2) {
        int indexOf;
        int indexOf2;
        ArrayList<j> arrayList3 = this.C;
        int size = arrayList3 == null ? 0 : arrayList3.size();
        int i2 = 0;
        while (i2 < size) {
            j jVar = this.C.get(i2);
            if (arrayList != null && !jVar.f243a && (indexOf2 = arrayList.indexOf(jVar.f244b)) != -1 && arrayList2.get(indexOf2).booleanValue()) {
                jVar.e();
            } else if (jVar.c() || (arrayList != null && jVar.f244b.a(arrayList, 0, arrayList.size()))) {
                this.C.remove(i2);
                i2--;
                size--;
                if (arrayList == null || jVar.f243a || (indexOf = arrayList.indexOf(jVar.f244b)) == -1 || !arrayList2.get(indexOf).booleanValue()) {
                    jVar.d();
                } else {
                    jVar.e();
                }
            }
            i2++;
        }
    }

    private void b(ArrayList<c> arrayList, ArrayList<Boolean> arrayList2) {
        if (arrayList != null && !arrayList.isEmpty()) {
            if (arrayList2 == null || arrayList.size() != arrayList2.size()) {
                throw new IllegalStateException("Internal error with the back stack records");
            }
            a(arrayList, arrayList2);
            int size = arrayList.size();
            int i2 = 0;
            int i3 = 0;
            while (i2 < size) {
                if (!arrayList.get(i2).t) {
                    if (i3 != i2) {
                        a(arrayList, arrayList2, i3, i2);
                    }
                    i3 = i2 + 1;
                    if (arrayList2.get(i2).booleanValue()) {
                        while (i3 < size && arrayList2.get(i3).booleanValue() && !arrayList.get(i3).t) {
                            i3++;
                        }
                    }
                    a(arrayList, arrayList2, i2, i3);
                    i2 = i3 - 1;
                }
                i2++;
            }
            if (i3 != size) {
                a(arrayList, arrayList2, i3, size);
            }
        }
    }

    private void a(ArrayList<c> arrayList, ArrayList<Boolean> arrayList2, int i2, int i3) {
        int i4;
        int i5 = i2;
        boolean z2 = arrayList.get(i5).t;
        ArrayList<g> arrayList3 = this.z;
        if (arrayList3 == null) {
            this.z = new ArrayList<>();
        } else {
            arrayList3.clear();
        }
        this.z.addAll(this.e);
        g w2 = w();
        boolean z3 = false;
        for (int i6 = i5; i6 < i3; i6++) {
            c cVar = arrayList.get(i6);
            if (!arrayList2.get(i6).booleanValue()) {
                w2 = cVar.a(this.z, w2);
            } else {
                w2 = cVar.b(this.z, w2);
            }
            z3 = z3 || cVar.i;
        }
        this.z.clear();
        if (!z2) {
            r.a(this, arrayList, arrayList2, i2, i3, false);
        }
        b(arrayList, arrayList2, i2, i3);
        if (z2) {
            android.support.v4.util.b<g> bVar = new android.support.v4.util.b<>();
            b(bVar);
            int a2 = a(arrayList, arrayList2, i2, i3, bVar);
            a(bVar);
            i4 = a2;
        } else {
            i4 = i3;
        }
        if (i4 != i5 && z2) {
            r.a(this, arrayList, arrayList2, i2, i4, true);
            a(this.l, true);
        }
        while (i5 < i3) {
            c cVar2 = arrayList.get(i5);
            if (arrayList2.get(i5).booleanValue() && cVar2.m >= 0) {
                c(cVar2.m);
                cVar2.m = -1;
            }
            cVar2.b();
            i5++;
        }
        if (z3) {
            i();
        }
    }

    private void a(android.support.v4.util.b<g> bVar) {
        int size = bVar.size();
        for (int i2 = 0; i2 < size; i2++) {
            g b2 = bVar.b(i2);
            if (!b2.mAdded) {
                View view = b2.getView();
                b2.mPostponedAlpha = view.getAlpha();
                view.setAlpha(BitmapDescriptorFactory.HUE_RED);
            }
        }
    }

    private int a(ArrayList<c> arrayList, ArrayList<Boolean> arrayList2, int i2, int i3, android.support.v4.util.b<g> bVar) {
        int i4 = i3;
        for (int i5 = i3 - 1; i5 >= i2; i5--) {
            c cVar = arrayList.get(i5);
            boolean booleanValue = arrayList2.get(i5).booleanValue();
            if (cVar.g() && !cVar.a(arrayList, i5 + 1, i3)) {
                if (this.C == null) {
                    this.C = new ArrayList<>();
                }
                j jVar = new j(cVar, booleanValue);
                this.C.add(jVar);
                cVar.a(jVar);
                if (booleanValue) {
                    cVar.f();
                } else {
                    cVar.b(false);
                }
                i4--;
                if (i5 != i4) {
                    arrayList.remove(i5);
                    arrayList.add(i4, cVar);
                }
                b(bVar);
            }
        }
        return i4;
    }

    /* access modifiers changed from: package-private */
    public void a(c cVar, boolean z2, boolean z3, boolean z4) {
        if (z2) {
            cVar.b(z4);
        } else {
            cVar.f();
        }
        ArrayList arrayList = new ArrayList(1);
        ArrayList arrayList2 = new ArrayList(1);
        arrayList.add(cVar);
        arrayList2.add(Boolean.valueOf(z2));
        if (z3) {
            r.a(this, (ArrayList<c>) arrayList, (ArrayList<Boolean>) arrayList2, 0, 1, true);
        }
        if (z4) {
            a(this.l, true);
        }
        SparseArray<g> sparseArray = this.f;
        if (sparseArray != null) {
            int size = sparseArray.size();
            for (int i2 = 0; i2 < size; i2++) {
                g valueAt = this.f.valueAt(i2);
                if (valueAt != null && valueAt.mView != null && valueAt.mIsNewlyAdded && cVar.b(valueAt.mContainerId)) {
                    if (valueAt.mPostponedAlpha > BitmapDescriptorFactory.HUE_RED) {
                        valueAt.mView.setAlpha(valueAt.mPostponedAlpha);
                    }
                    if (z4) {
                        valueAt.mPostponedAlpha = BitmapDescriptorFactory.HUE_RED;
                    } else {
                        valueAt.mPostponedAlpha = -1.0f;
                        valueAt.mIsNewlyAdded = false;
                    }
                }
            }
        }
    }

    private g p(g gVar) {
        ViewGroup viewGroup = gVar.mContainer;
        View view = gVar.mView;
        if (viewGroup == null || view == null) {
            return null;
        }
        for (int indexOf = this.e.indexOf(gVar) - 1; indexOf >= 0; indexOf--) {
            g gVar2 = this.e.get(indexOf);
            if (gVar2.mContainer == viewGroup && gVar2.mView != null) {
                return gVar2;
            }
        }
        return null;
    }

    private static void b(ArrayList<c> arrayList, ArrayList<Boolean> arrayList2, int i2, int i3) {
        while (i2 < i3) {
            c cVar = arrayList.get(i2);
            boolean z2 = true;
            if (arrayList2.get(i2).booleanValue()) {
                cVar.a(-1);
                if (i2 != i3 - 1) {
                    z2 = false;
                }
                cVar.b(z2);
            } else {
                cVar.a(1);
                cVar.f();
            }
            i2++;
        }
    }

    private void b(android.support.v4.util.b<g> bVar) {
        int i2 = this.l;
        if (i2 >= 1) {
            int min = Math.min(i2, 3);
            int size = this.e.size();
            for (int i3 = 0; i3 < size; i3++) {
                g gVar = this.e.get(i3);
                if (gVar.mState < min) {
                    a(gVar, min, gVar.getNextAnim(), gVar.getNextTransition(), false);
                    if (gVar.mView != null && !gVar.mHidden && gVar.mIsNewlyAdded) {
                        bVar.add(gVar);
                    }
                }
            }
        }
    }

    private void A() {
        if (this.C != null) {
            while (!this.C.isEmpty()) {
                this.C.remove(0).d();
            }
        }
    }

    private void B() {
        SparseArray<g> sparseArray = this.f;
        int size = sparseArray == null ? 0 : sparseArray.size();
        for (int i2 = 0; i2 < size; i2++) {
            g valueAt = this.f.valueAt(i2);
            if (valueAt != null) {
                if (valueAt.getAnimatingAway() != null) {
                    int stateAfterAnimating = valueAt.getStateAfterAnimating();
                    View animatingAway = valueAt.getAnimatingAway();
                    Animation animation = animatingAway.getAnimation();
                    if (animation != null) {
                        animation.cancel();
                        animatingAway.clearAnimation();
                    }
                    valueAt.setAnimatingAway(null);
                    a(valueAt, stateAfterAnimating, 0, 0, false);
                } else if (valueAt.getAnimator() != null) {
                    valueAt.getAnimator().end();
                }
            }
        }
    }

    private boolean c(ArrayList<c> arrayList, ArrayList<Boolean> arrayList2) {
        synchronized (this) {
            if (this.f210b != null) {
                if (this.f210b.size() != 0) {
                    int size = this.f210b.size();
                    boolean z2 = false;
                    for (int i2 = 0; i2 < size; i2++) {
                        z2 |= this.f210b.get(i2).a(arrayList, arrayList2);
                    }
                    this.f210b.clear();
                    this.m.j().removeCallbacks(this.E);
                    return z2;
                }
            }
            return false;
        }
    }

    /* access modifiers changed from: package-private */
    public void h() {
        if (this.w) {
            this.w = false;
            e();
        }
    }

    /* access modifiers changed from: package-private */
    public void i() {
        if (this.k != null) {
            for (int i2 = 0; i2 < this.k.size(); i2++) {
                this.k.get(i2).a();
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void b(c cVar) {
        if (this.g == null) {
            this.g = new ArrayList<>();
        }
        this.g.add(cVar);
    }

    /* access modifiers changed from: package-private */
    public boolean a(ArrayList<c> arrayList, ArrayList<Boolean> arrayList2, String str, int i2, int i3) {
        int i4;
        ArrayList<c> arrayList3 = this.g;
        if (arrayList3 == null) {
            return false;
        }
        if (str == null && i2 < 0 && (i3 & 1) == 0) {
            int size = arrayList3.size() - 1;
            if (size < 0) {
                return false;
            }
            arrayList.add(this.g.remove(size));
            arrayList2.add(true);
        } else {
            if (str != null || i2 >= 0) {
                i4 = this.g.size() - 1;
                while (i4 >= 0) {
                    c cVar = this.g.get(i4);
                    if ((str != null && str.equals(cVar.h())) || (i2 >= 0 && i2 == cVar.m)) {
                        break;
                    }
                    i4--;
                }
                if (i4 < 0) {
                    return false;
                }
                if ((i3 & 1) != 0) {
                    i4--;
                    while (i4 >= 0) {
                        c cVar2 = this.g.get(i4);
                        if ((str == null || !str.equals(cVar2.h())) && (i2 < 0 || i2 != cVar2.m)) {
                            break;
                        }
                        i4--;
                    }
                }
            } else {
                i4 = -1;
            }
            if (i4 == this.g.size() - 1) {
                return false;
            }
            for (int size2 = this.g.size() - 1; size2 > i4; size2--) {
                arrayList.add(this.g.remove(size2));
                arrayList2.add(true);
            }
        }
        return true;
    }

    /* access modifiers changed from: package-private */
    public n j() {
        a(this.D);
        return this.D;
    }

    private static void a(n nVar) {
        if (nVar != null) {
            List<g> a2 = nVar.a();
            if (a2 != null) {
                for (g gVar : a2) {
                    gVar.mRetaining = true;
                }
            }
            List<n> b2 = nVar.b();
            if (b2 != null) {
                for (n nVar2 : b2) {
                    a(nVar2);
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void k() {
        ArrayList arrayList;
        ArrayList arrayList2;
        ArrayList arrayList3;
        n nVar;
        if (this.f != null) {
            arrayList3 = null;
            arrayList2 = null;
            arrayList = null;
            for (int i2 = 0; i2 < this.f.size(); i2++) {
                g valueAt = this.f.valueAt(i2);
                if (valueAt != null) {
                    if (valueAt.mRetainInstance) {
                        if (arrayList3 == null) {
                            arrayList3 = new ArrayList();
                        }
                        arrayList3.add(valueAt);
                        valueAt.mTargetIndex = valueAt.mTarget != null ? valueAt.mTarget.mIndex : -1;
                        if (f209a) {
                            Log.v("FragmentManager", "retainNonConfig: keeping retained " + valueAt);
                        }
                    }
                    if (valueAt.mChildFragmentManager != null) {
                        valueAt.mChildFragmentManager.k();
                        nVar = valueAt.mChildFragmentManager.D;
                    } else {
                        nVar = valueAt.mChildNonConfig;
                    }
                    if (arrayList2 == null && nVar != null) {
                        arrayList2 = new ArrayList(this.f.size());
                        for (int i3 = 0; i3 < i2; i3++) {
                            arrayList2.add(null);
                        }
                    }
                    if (arrayList2 != null) {
                        arrayList2.add(nVar);
                    }
                    if (arrayList == null && valueAt.mViewModelStore != null) {
                        arrayList = new ArrayList(this.f.size());
                        for (int i4 = 0; i4 < i2; i4++) {
                            arrayList.add(null);
                        }
                    }
                    if (arrayList != null) {
                        arrayList.add(valueAt.mViewModelStore);
                    }
                }
            }
        } else {
            arrayList3 = null;
            arrayList2 = null;
            arrayList = null;
        }
        if (arrayList3 == null && arrayList2 == null && arrayList == null) {
            this.D = null;
        } else {
            this.D = new n(arrayList3, arrayList2, arrayList);
        }
    }

    /* access modifiers changed from: package-private */
    public void m(g gVar) {
        if (gVar.mInnerView != null) {
            SparseArray<Parcelable> sparseArray = this.B;
            if (sparseArray == null) {
                this.B = new SparseArray<>();
            } else {
                sparseArray.clear();
            }
            gVar.mInnerView.saveHierarchyState(this.B);
            if (this.B.size() > 0) {
                gVar.mSavedViewState = this.B;
                this.B = null;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public Bundle n(g gVar) {
        Bundle bundle;
        if (this.A == null) {
            this.A = new Bundle();
        }
        gVar.performSaveInstanceState(this.A);
        d(gVar, this.A, false);
        if (!this.A.isEmpty()) {
            bundle = this.A;
            this.A = null;
        } else {
            bundle = null;
        }
        if (gVar.mView != null) {
            m(gVar);
        }
        if (gVar.mSavedViewState != null) {
            if (bundle == null) {
                bundle = new Bundle();
            }
            bundle.putSparseParcelableArray("android:view_state", gVar.mSavedViewState);
        }
        if (!gVar.mUserVisibleHint) {
            if (bundle == null) {
                bundle = new Bundle();
            }
            bundle.putBoolean("android:user_visible_hint", gVar.mUserVisibleHint);
        }
        return bundle;
    }

    /* access modifiers changed from: package-private */
    public Parcelable l() {
        int[] iArr;
        int size;
        A();
        B();
        g();
        this.s = true;
        d[] dVarArr = null;
        this.D = null;
        SparseArray<g> sparseArray = this.f;
        if (sparseArray == null || sparseArray.size() <= 0) {
            return null;
        }
        int size2 = this.f.size();
        p[] pVarArr = new p[size2];
        boolean z2 = false;
        for (int i2 = 0; i2 < size2; i2++) {
            g valueAt = this.f.valueAt(i2);
            if (valueAt != null) {
                if (valueAt.mIndex < 0) {
                    a(new IllegalStateException("Failure saving state: active " + valueAt + " has cleared index: " + valueAt.mIndex));
                }
                p pVar = new p(valueAt);
                pVarArr[i2] = pVar;
                if (valueAt.mState <= 0 || pVar.k != null) {
                    pVar.k = valueAt.mSavedFragmentState;
                } else {
                    pVar.k = n(valueAt);
                    if (valueAt.mTarget != null) {
                        if (valueAt.mTarget.mIndex < 0) {
                            a(new IllegalStateException("Failure saving state: " + valueAt + " has target not in fragment manager: " + valueAt.mTarget));
                        }
                        if (pVar.k == null) {
                            pVar.k = new Bundle();
                        }
                        a(pVar.k, "android:target_state", valueAt.mTarget);
                        if (valueAt.mTargetRequestCode != 0) {
                            pVar.k.putInt("android:target_req_state", valueAt.mTargetRequestCode);
                        }
                    }
                }
                if (f209a) {
                    Log.v("FragmentManager", "Saved state of " + valueAt + ": " + pVar.k);
                }
                z2 = true;
            }
        }
        if (!z2) {
            if (f209a) {
                Log.v("FragmentManager", "saveAllState: no fragments!");
            }
            return null;
        }
        int size3 = this.e.size();
        if (size3 > 0) {
            iArr = new int[size3];
            for (int i3 = 0; i3 < size3; i3++) {
                iArr[i3] = this.e.get(i3).mIndex;
                if (iArr[i3] < 0) {
                    a(new IllegalStateException("Failure saving state: active " + this.e.get(i3) + " has cleared index: " + iArr[i3]));
                }
                if (f209a) {
                    Log.v("FragmentManager", "saveAllState: adding fragment #" + i3 + ": " + this.e.get(i3));
                }
            }
        } else {
            iArr = null;
        }
        ArrayList<c> arrayList = this.g;
        if (arrayList != null && (size = arrayList.size()) > 0) {
            dVarArr = new d[size];
            for (int i4 = 0; i4 < size; i4++) {
                dVarArr[i4] = new d(this.g.get(i4));
                if (f209a) {
                    Log.v("FragmentManager", "saveAllState: adding back stack #" + i4 + ": " + this.g.get(i4));
                }
            }
        }
        o oVar = new o();
        oVar.f249a = pVarArr;
        oVar.f250b = iArr;
        oVar.f251c = dVarArr;
        g gVar = this.p;
        if (gVar != null) {
            oVar.f252d = gVar.mIndex;
        }
        oVar.e = this.f212d;
        k();
        return oVar;
    }

    /* access modifiers changed from: package-private */
    public void a(Parcelable parcelable, n nVar) {
        List<p> list;
        List<n> list2;
        if (parcelable != null) {
            o oVar = (o) parcelable;
            if (oVar.f249a != null) {
                if (nVar != null) {
                    List<g> a2 = nVar.a();
                    list2 = nVar.b();
                    list = nVar.c();
                    int size = a2 != null ? a2.size() : 0;
                    for (int i2 = 0; i2 < size; i2++) {
                        g gVar = a2.get(i2);
                        if (f209a) {
                            Log.v("FragmentManager", "restoreAllState: re-attaching retained " + gVar);
                        }
                        int i3 = 0;
                        while (i3 < oVar.f249a.length && oVar.f249a[i3].f254b != gVar.mIndex) {
                            i3++;
                        }
                        if (i3 == oVar.f249a.length) {
                            a(new IllegalStateException("Could not find active fragment with index " + gVar.mIndex));
                        }
                        p pVar = oVar.f249a[i3];
                        pVar.l = gVar;
                        gVar.mSavedViewState = null;
                        gVar.mBackStackNesting = 0;
                        gVar.mInLayout = false;
                        gVar.mAdded = false;
                        gVar.mTarget = null;
                        if (pVar.k != null) {
                            pVar.k.setClassLoader(this.m.i().getClassLoader());
                            gVar.mSavedViewState = pVar.k.getSparseParcelableArray("android:view_state");
                            gVar.mSavedFragmentState = pVar.k;
                        }
                    }
                } else {
                    list2 = null;
                    list = null;
                }
                this.f = new SparseArray<>(oVar.f249a.length);
                int i4 = 0;
                while (i4 < oVar.f249a.length) {
                    p pVar2 = oVar.f249a[i4];
                    if (pVar2 != null) {
                        g a3 = pVar2.a(this.m, this.n, this.o, (list2 == null || i4 >= list2.size()) ? null : list2.get(i4), (list == null || i4 >= list.size()) ? null : list.get(i4));
                        if (f209a) {
                            Log.v("FragmentManager", "restoreAllState: active #" + i4 + ": " + a3);
                        }
                        this.f.put(a3.mIndex, a3);
                        pVar2.l = null;
                    }
                    i4++;
                }
                if (nVar != null) {
                    List<g> a4 = nVar.a();
                    int size2 = a4 != null ? a4.size() : 0;
                    for (int i5 = 0; i5 < size2; i5++) {
                        g gVar2 = a4.get(i5);
                        if (gVar2.mTargetIndex >= 0) {
                            gVar2.mTarget = this.f.get(gVar2.mTargetIndex);
                            if (gVar2.mTarget == null) {
                                Log.w("FragmentManager", "Re-attaching retained fragment " + gVar2 + " target no longer exists: " + gVar2.mTargetIndex);
                            }
                        }
                    }
                }
                this.e.clear();
                if (oVar.f250b != null) {
                    for (int i6 = 0; i6 < oVar.f250b.length; i6++) {
                        g gVar3 = this.f.get(oVar.f250b[i6]);
                        if (gVar3 == null) {
                            a(new IllegalStateException("No instantiated fragment for index #" + oVar.f250b[i6]));
                        }
                        gVar3.mAdded = true;
                        if (f209a) {
                            Log.v("FragmentManager", "restoreAllState: added #" + i6 + ": " + gVar3);
                        }
                        if (!this.e.contains(gVar3)) {
                            synchronized (this.e) {
                                this.e.add(gVar3);
                            }
                        } else {
                            throw new IllegalStateException("Already added!");
                        }
                    }
                }
                if (oVar.f251c != null) {
                    this.g = new ArrayList<>(oVar.f251c.length);
                    for (int i7 = 0; i7 < oVar.f251c.length; i7++) {
                        c a5 = oVar.f251c[i7].a(this);
                        if (f209a) {
                            Log.v("FragmentManager", "restoreAllState: back stack #" + i7 + " (index " + a5.m + "): " + a5);
                            PrintWriter printWriter = new PrintWriter(new android.support.v4.util.e("FragmentManager"));
                            a5.a("  ", printWriter, false);
                            printWriter.close();
                        }
                        this.g.add(a5);
                        if (a5.m >= 0) {
                            a(a5.m, a5);
                        }
                    }
                } else {
                    this.g = null;
                }
                if (oVar.f252d >= 0) {
                    this.p = this.f.get(oVar.f252d);
                }
                this.f212d = oVar.e;
            }
        }
    }

    private void C() {
        SparseArray<g> sparseArray = this.f;
        if (sparseArray != null) {
            for (int size = sparseArray.size() - 1; size >= 0; size--) {
                if (this.f.valueAt(size) == null) {
                    SparseArray<g> sparseArray2 = this.f;
                    sparseArray2.delete(sparseArray2.keyAt(size));
                }
            }
        }
    }

    public void a(k kVar, i iVar, g gVar) {
        if (this.m == null) {
            this.m = kVar;
            this.n = iVar;
            this.o = gVar;
            return;
        }
        throw new IllegalStateException("Already attached");
    }

    public void m() {
        this.D = null;
        this.s = false;
        this.t = false;
        int size = this.e.size();
        for (int i2 = 0; i2 < size; i2++) {
            g gVar = this.e.get(i2);
            if (gVar != null) {
                gVar.noteStateNotSaved();
            }
        }
    }

    public void n() {
        this.s = false;
        this.t = false;
        e(1);
    }

    public void o() {
        this.s = false;
        this.t = false;
        e(2);
    }

    public void p() {
        this.s = false;
        this.t = false;
        e(3);
    }

    public void q() {
        this.s = false;
        this.t = false;
        e(4);
    }

    public void r() {
        e(3);
    }

    public void s() {
        this.t = true;
        e(2);
    }

    public void t() {
        e(1);
    }

    public void u() {
        this.u = true;
        g();
        e(0);
        this.m = null;
        this.n = null;
        this.o = null;
    }

    /* JADX INFO: finally extract failed */
    private void e(int i2) {
        try {
            this.f211c = true;
            a(i2, false);
            this.f211c = false;
            g();
        } catch (Throwable th) {
            this.f211c = false;
            throw th;
        }
    }

    public void a(boolean z2) {
        for (int size = this.e.size() - 1; size >= 0; size--) {
            g gVar = this.e.get(size);
            if (gVar != null) {
                gVar.performMultiWindowModeChanged(z2);
            }
        }
    }

    public void b(boolean z2) {
        for (int size = this.e.size() - 1; size >= 0; size--) {
            g gVar = this.e.get(size);
            if (gVar != null) {
                gVar.performPictureInPictureModeChanged(z2);
            }
        }
    }

    public void a(Configuration configuration) {
        for (int i2 = 0; i2 < this.e.size(); i2++) {
            g gVar = this.e.get(i2);
            if (gVar != null) {
                gVar.performConfigurationChanged(configuration);
            }
        }
    }

    public void v() {
        for (int i2 = 0; i2 < this.e.size(); i2++) {
            g gVar = this.e.get(i2);
            if (gVar != null) {
                gVar.performLowMemory();
            }
        }
    }

    public boolean a(Menu menu, MenuInflater menuInflater) {
        if (this.l < 1) {
            return false;
        }
        ArrayList<g> arrayList = null;
        boolean z2 = false;
        for (int i2 = 0; i2 < this.e.size(); i2++) {
            g gVar = this.e.get(i2);
            if (gVar != null && gVar.performCreateOptionsMenu(menu, menuInflater)) {
                if (arrayList == null) {
                    arrayList = new ArrayList<>();
                }
                arrayList.add(gVar);
                z2 = true;
            }
        }
        if (this.h != null) {
            for (int i3 = 0; i3 < this.h.size(); i3++) {
                g gVar2 = this.h.get(i3);
                if (arrayList == null || !arrayList.contains(gVar2)) {
                    gVar2.onDestroyOptionsMenu();
                }
            }
        }
        this.h = arrayList;
        return z2;
    }

    public boolean a(Menu menu) {
        if (this.l < 1) {
            return false;
        }
        boolean z2 = false;
        for (int i2 = 0; i2 < this.e.size(); i2++) {
            g gVar = this.e.get(i2);
            if (gVar != null && gVar.performPrepareOptionsMenu(menu)) {
                z2 = true;
            }
        }
        return z2;
    }

    public boolean a(MenuItem menuItem) {
        if (this.l < 1) {
            return false;
        }
        for (int i2 = 0; i2 < this.e.size(); i2++) {
            g gVar = this.e.get(i2);
            if (gVar != null && gVar.performOptionsItemSelected(menuItem)) {
                return true;
            }
        }
        return false;
    }

    public boolean b(MenuItem menuItem) {
        if (this.l < 1) {
            return false;
        }
        for (int i2 = 0; i2 < this.e.size(); i2++) {
            g gVar = this.e.get(i2);
            if (gVar != null && gVar.performContextItemSelected(menuItem)) {
                return true;
            }
        }
        return false;
    }

    public void b(Menu menu) {
        if (this.l >= 1) {
            for (int i2 = 0; i2 < this.e.size(); i2++) {
                g gVar = this.e.get(i2);
                if (gVar != null) {
                    gVar.performOptionsMenuClosed(menu);
                }
            }
        }
    }

    public void o(g gVar) {
        if (gVar == null || (this.f.get(gVar.mIndex) == gVar && (gVar.mHost == null || gVar.getFragmentManager() == this))) {
            this.p = gVar;
            return;
        }
        throw new IllegalArgumentException("Fragment " + gVar + " is not an active fragment of FragmentManager " + this);
    }

    public g w() {
        return this.p;
    }

    /* access modifiers changed from: package-private */
    public void a(g gVar, Context context, boolean z2) {
        g gVar2 = this.o;
        if (gVar2 != null) {
            l fragmentManager = gVar2.getFragmentManager();
            if (fragmentManager instanceof m) {
                ((m) fragmentManager).a(gVar, context, true);
            }
        }
        Iterator<f> it = this.J.iterator();
        while (it.hasNext()) {
            f next = it.next();
            if (!z2 || next.f237b) {
                next.f236a.a(this, gVar, context);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void b(g gVar, Context context, boolean z2) {
        g gVar2 = this.o;
        if (gVar2 != null) {
            l fragmentManager = gVar2.getFragmentManager();
            if (fragmentManager instanceof m) {
                ((m) fragmentManager).b(gVar, context, true);
            }
        }
        Iterator<f> it = this.J.iterator();
        while (it.hasNext()) {
            f next = it.next();
            if (!z2 || next.f237b) {
                next.f236a.b(this, gVar, context);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void a(g gVar, Bundle bundle, boolean z2) {
        g gVar2 = this.o;
        if (gVar2 != null) {
            l fragmentManager = gVar2.getFragmentManager();
            if (fragmentManager instanceof m) {
                ((m) fragmentManager).a(gVar, bundle, true);
            }
        }
        Iterator<f> it = this.J.iterator();
        while (it.hasNext()) {
            f next = it.next();
            if (!z2 || next.f237b) {
                next.f236a.a(this, gVar, bundle);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void b(g gVar, Bundle bundle, boolean z2) {
        g gVar2 = this.o;
        if (gVar2 != null) {
            l fragmentManager = gVar2.getFragmentManager();
            if (fragmentManager instanceof m) {
                ((m) fragmentManager).b(gVar, bundle, true);
            }
        }
        Iterator<f> it = this.J.iterator();
        while (it.hasNext()) {
            f next = it.next();
            if (!z2 || next.f237b) {
                next.f236a.b(this, gVar, bundle);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void c(g gVar, Bundle bundle, boolean z2) {
        g gVar2 = this.o;
        if (gVar2 != null) {
            l fragmentManager = gVar2.getFragmentManager();
            if (fragmentManager instanceof m) {
                ((m) fragmentManager).c(gVar, bundle, true);
            }
        }
        Iterator<f> it = this.J.iterator();
        while (it.hasNext()) {
            f next = it.next();
            if (!z2 || next.f237b) {
                next.f236a.c(this, gVar, bundle);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void a(g gVar, View view, Bundle bundle, boolean z2) {
        g gVar2 = this.o;
        if (gVar2 != null) {
            l fragmentManager = gVar2.getFragmentManager();
            if (fragmentManager instanceof m) {
                ((m) fragmentManager).a(gVar, view, bundle, true);
            }
        }
        Iterator<f> it = this.J.iterator();
        while (it.hasNext()) {
            f next = it.next();
            if (!z2 || next.f237b) {
                next.f236a.a(this, gVar, view, bundle);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void b(g gVar, boolean z2) {
        g gVar2 = this.o;
        if (gVar2 != null) {
            l fragmentManager = gVar2.getFragmentManager();
            if (fragmentManager instanceof m) {
                ((m) fragmentManager).b(gVar, true);
            }
        }
        Iterator<f> it = this.J.iterator();
        while (it.hasNext()) {
            f next = it.next();
            if (!z2 || next.f237b) {
                next.f236a.a(this, gVar);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void c(g gVar, boolean z2) {
        g gVar2 = this.o;
        if (gVar2 != null) {
            l fragmentManager = gVar2.getFragmentManager();
            if (fragmentManager instanceof m) {
                ((m) fragmentManager).c(gVar, true);
            }
        }
        Iterator<f> it = this.J.iterator();
        while (it.hasNext()) {
            f next = it.next();
            if (!z2 || next.f237b) {
                next.f236a.b(this, gVar);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void d(g gVar, boolean z2) {
        g gVar2 = this.o;
        if (gVar2 != null) {
            l fragmentManager = gVar2.getFragmentManager();
            if (fragmentManager instanceof m) {
                ((m) fragmentManager).d(gVar, true);
            }
        }
        Iterator<f> it = this.J.iterator();
        while (it.hasNext()) {
            f next = it.next();
            if (!z2 || next.f237b) {
                next.f236a.c(this, gVar);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void e(g gVar, boolean z2) {
        g gVar2 = this.o;
        if (gVar2 != null) {
            l fragmentManager = gVar2.getFragmentManager();
            if (fragmentManager instanceof m) {
                ((m) fragmentManager).e(gVar, true);
            }
        }
        Iterator<f> it = this.J.iterator();
        while (it.hasNext()) {
            f next = it.next();
            if (!z2 || next.f237b) {
                next.f236a.d(this, gVar);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void d(g gVar, Bundle bundle, boolean z2) {
        g gVar2 = this.o;
        if (gVar2 != null) {
            l fragmentManager = gVar2.getFragmentManager();
            if (fragmentManager instanceof m) {
                ((m) fragmentManager).d(gVar, bundle, true);
            }
        }
        Iterator<f> it = this.J.iterator();
        while (it.hasNext()) {
            f next = it.next();
            if (!z2 || next.f237b) {
                next.f236a.d(this, gVar, bundle);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void f(g gVar, boolean z2) {
        g gVar2 = this.o;
        if (gVar2 != null) {
            l fragmentManager = gVar2.getFragmentManager();
            if (fragmentManager instanceof m) {
                ((m) fragmentManager).f(gVar, true);
            }
        }
        Iterator<f> it = this.J.iterator();
        while (it.hasNext()) {
            f next = it.next();
            if (!z2 || next.f237b) {
                next.f236a.e(this, gVar);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void g(g gVar, boolean z2) {
        g gVar2 = this.o;
        if (gVar2 != null) {
            l fragmentManager = gVar2.getFragmentManager();
            if (fragmentManager instanceof m) {
                ((m) fragmentManager).g(gVar, true);
            }
        }
        Iterator<f> it = this.J.iterator();
        while (it.hasNext()) {
            f next = it.next();
            if (!z2 || next.f237b) {
                next.f236a.f(this, gVar);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void h(g gVar, boolean z2) {
        g gVar2 = this.o;
        if (gVar2 != null) {
            l fragmentManager = gVar2.getFragmentManager();
            if (fragmentManager instanceof m) {
                ((m) fragmentManager).h(gVar, true);
            }
        }
        Iterator<f> it = this.J.iterator();
        while (it.hasNext()) {
            f next = it.next();
            if (!z2 || next.f237b) {
                next.f236a.g(this, gVar);
            }
        }
    }

    public View onCreateView(View view, String str, Context context, AttributeSet attributeSet) {
        g gVar;
        if (!"fragment".equals(str)) {
            return null;
        }
        String attributeValue = attributeSet.getAttributeValue(null, "class");
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, g.f238a);
        int i2 = 0;
        String string = attributeValue == null ? obtainStyledAttributes.getString(0) : attributeValue;
        int resourceId = obtainStyledAttributes.getResourceId(1, -1);
        String string2 = obtainStyledAttributes.getString(2);
        obtainStyledAttributes.recycle();
        if (!g.isSupportFragmentClass(this.m.i(), string)) {
            return null;
        }
        if (view != null) {
            i2 = view.getId();
        }
        if (i2 == -1 && resourceId == -1 && string2 == null) {
            throw new IllegalArgumentException(attributeSet.getPositionDescription() + ": Must specify unique android:id, android:tag, or have a parent with an id for " + string);
        }
        g b2 = resourceId != -1 ? b(resourceId) : null;
        if (b2 == null && string2 != null) {
            b2 = a(string2);
        }
        if (b2 == null && i2 != -1) {
            b2 = b(i2);
        }
        if (f209a) {
            Log.v("FragmentManager", "onCreateView: id=0x" + Integer.toHexString(resourceId) + " fname=" + string + " existing=" + b2);
        }
        if (b2 == null) {
            g a2 = this.n.a(context, string, null);
            a2.mFromLayout = true;
            a2.mFragmentId = resourceId != 0 ? resourceId : i2;
            a2.mContainerId = i2;
            a2.mTag = string2;
            a2.mInLayout = true;
            a2.mFragmentManager = this;
            k kVar = this.m;
            a2.mHost = kVar;
            a2.onInflate(kVar.i(), attributeSet, a2.mSavedFragmentState);
            a(a2, true);
            gVar = a2;
        } else if (!b2.mInLayout) {
            b2.mInLayout = true;
            b2.mHost = this.m;
            if (!b2.mRetaining) {
                b2.onInflate(this.m.i(), attributeSet, b2.mSavedFragmentState);
            }
            gVar = b2;
        } else {
            throw new IllegalArgumentException(attributeSet.getPositionDescription() + ": Duplicate id 0x" + Integer.toHexString(resourceId) + ", tag " + string2 + ", or parent id 0x" + Integer.toHexString(i2) + " with another fragment for " + string);
        }
        if (this.l >= 1 || !gVar.mFromLayout) {
            b(gVar);
        } else {
            a(gVar, 1, 0, 0, false);
        }
        if (gVar.mView != null) {
            if (resourceId != 0) {
                gVar.mView.setId(resourceId);
            }
            if (gVar.mView.getTag() == null) {
                gVar.mView.setTag(string2);
            }
            return gVar.mView;
        }
        throw new IllegalStateException("Fragment " + string + " did not create a view.");
    }

    public View onCreateView(String str, Context context, AttributeSet attributeSet) {
        return onCreateView(null, str, context, attributeSet);
    }

    /* compiled from: FragmentManager */
    private class i implements h {

        /* renamed from: a  reason: collision with root package name */
        final String f239a;

        /* renamed from: b  reason: collision with root package name */
        final int f240b;

        /* renamed from: c  reason: collision with root package name */
        final int f241c;

        i(String str, int i, int i2) {
            this.f239a = str;
            this.f240b = i;
            this.f241c = i2;
        }

        @Override // android.support.v4.app.m.h
        public boolean a(ArrayList<c> arrayList, ArrayList<Boolean> arrayList2) {
            l peekChildFragmentManager;
            if (m.this.p == null || this.f240b >= 0 || this.f239a != null || (peekChildFragmentManager = m.this.p.peekChildFragmentManager()) == null || !peekChildFragmentManager.b()) {
                return m.this.a(arrayList, arrayList2, this.f239a, this.f240b, this.f241c);
            }
            return false;
        }
    }

    /* access modifiers changed from: package-private */
    /* compiled from: FragmentManager */
    public static class j implements g.c {

        /* renamed from: a  reason: collision with root package name */
        final boolean f243a;

        /* renamed from: b  reason: collision with root package name */
        final c f244b;

        /* renamed from: c  reason: collision with root package name */
        private int f245c;

        j(c cVar, boolean z) {
            this.f243a = z;
            this.f244b = cVar;
        }

        @Override // android.support.v4.app.g.c
        public void a() {
            this.f245c--;
            if (this.f245c == 0) {
                this.f244b.f173a.f();
            }
        }

        @Override // android.support.v4.app.g.c
        public void b() {
            this.f245c++;
        }

        public boolean c() {
            return this.f245c == 0;
        }

        public void d() {
            boolean z = this.f245c > 0;
            m mVar = this.f244b.f173a;
            int size = mVar.e.size();
            for (int i = 0; i < size; i++) {
                g gVar = mVar.e.get(i);
                gVar.setOnStartEnterTransitionListener(null);
                if (z && gVar.isPostponed()) {
                    gVar.startPostponedEnterTransition();
                }
            }
            this.f244b.f173a.a(this.f244b, this.f243a, !z, true);
        }

        public void e() {
            this.f244b.f173a.a(this.f244b, this.f243a, false, false);
        }
    }

    /* access modifiers changed from: private */
    /* compiled from: FragmentManager */
    public static class c {

        /* renamed from: a  reason: collision with root package name */
        public final Animation f229a;

        /* renamed from: b  reason: collision with root package name */
        public final Animator f230b;

        c(Animation animation) {
            this.f229a = animation;
            this.f230b = null;
            if (animation == null) {
                throw new IllegalStateException("Animation cannot be null");
            }
        }

        c(Animator animator) {
            this.f229a = null;
            this.f230b = animator;
            if (animator == null) {
                throw new IllegalStateException("Animator cannot be null");
            }
        }
    }

    /* compiled from: FragmentManager */
    private static class b implements Animation.AnimationListener {

        /* renamed from: a  reason: collision with root package name */
        private final Animation.AnimationListener f228a;

        b(Animation.AnimationListener animationListener) {
            this.f228a = animationListener;
        }

        public void onAnimationStart(Animation animation) {
            Animation.AnimationListener animationListener = this.f228a;
            if (animationListener != null) {
                animationListener.onAnimationStart(animation);
            }
        }

        public void onAnimationEnd(Animation animation) {
            Animation.AnimationListener animationListener = this.f228a;
            if (animationListener != null) {
                animationListener.onAnimationEnd(animation);
            }
        }

        public void onAnimationRepeat(Animation animation) {
            Animation.AnimationListener animationListener = this.f228a;
            if (animationListener != null) {
                animationListener.onAnimationRepeat(animation);
            }
        }
    }

    /* access modifiers changed from: private */
    /* compiled from: FragmentManager */
    public static class a extends b {

        /* renamed from: a  reason: collision with root package name */
        View f226a;

        a(View view, Animation.AnimationListener animationListener) {
            super(animationListener);
            this.f226a = view;
        }

        @Override // android.support.v4.app.m.b
        public void onAnimationEnd(Animation animation) {
            if (t.u(this.f226a) || Build.VERSION.SDK_INT >= 24) {
                this.f226a.post(new Runnable() {
                    /* class android.support.v4.app.m.a.AnonymousClass1 */

                    public void run() {
                        a.this.f226a.setLayerType(0, null);
                    }
                });
            } else {
                this.f226a.setLayerType(0, null);
            }
            super.onAnimationEnd(animation);
        }
    }

    /* access modifiers changed from: private */
    /* compiled from: FragmentManager */
    public static class d extends AnimatorListenerAdapter {

        /* renamed from: a  reason: collision with root package name */
        View f231a;

        d(View view) {
            this.f231a = view;
        }

        public void onAnimationStart(Animator animator) {
            this.f231a.setLayerType(2, null);
        }

        public void onAnimationEnd(Animator animator) {
            this.f231a.setLayerType(0, null);
            animator.removeListener(this);
        }
    }

    /* access modifiers changed from: private */
    /* compiled from: FragmentManager */
    public static class e extends AnimationSet implements Runnable {

        /* renamed from: a  reason: collision with root package name */
        private final ViewGroup f232a;

        /* renamed from: b  reason: collision with root package name */
        private final View f233b;

        /* renamed from: c  reason: collision with root package name */
        private boolean f234c;

        /* renamed from: d  reason: collision with root package name */
        private boolean f235d;
        private boolean e = true;

        e(Animation animation, ViewGroup viewGroup, View view) {
            super(false);
            this.f232a = viewGroup;
            this.f233b = view;
            addAnimation(animation);
            this.f232a.post(this);
        }

        public boolean getTransformation(long j, Transformation transformation) {
            this.e = true;
            if (this.f234c) {
                return !this.f235d;
            }
            if (!super.getTransformation(j, transformation)) {
                this.f234c = true;
                ac.a(this.f232a, this);
            }
            return true;
        }

        public boolean getTransformation(long j, Transformation transformation, float f) {
            this.e = true;
            if (this.f234c) {
                return !this.f235d;
            }
            if (!super.getTransformation(j, transformation, f)) {
                this.f234c = true;
                ac.a(this.f232a, this);
            }
            return true;
        }

        public void run() {
            if (this.f234c || !this.e) {
                this.f232a.endViewTransition(this.f233b);
                this.f235d = true;
                return;
            }
            this.e = false;
            this.f232a.post(this);
        }
    }
}
