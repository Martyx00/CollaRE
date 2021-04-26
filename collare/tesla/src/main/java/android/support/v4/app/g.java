package android.support.v4.app;

import android.animation.Animator;
import android.app.Activity;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.c;
import android.arch.lifecycle.e;
import android.arch.lifecycle.f;
import android.arch.lifecycle.j;
import android.arch.lifecycle.p;
import android.arch.lifecycle.q;
import android.content.ComponentCallbacks;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Looper;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.util.l;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;

/* compiled from: Fragment */
public class g implements e, q, ComponentCallbacks, View.OnCreateContextMenuListener {
    static final int ACTIVITY_CREATED = 2;
    static final int CREATED = 1;
    static final int INITIALIZING = 0;
    static final int RESUMED = 4;
    static final int STARTED = 3;
    static final Object USE_DEFAULT_TRANSITION = new Object();
    private static final l<String, Class<?>> sClassMap = new l<>();
    boolean mAdded;
    a mAnimationInfo;
    Bundle mArguments;
    int mBackStackNesting;
    boolean mCalled;
    m mChildFragmentManager;
    n mChildNonConfig;
    ViewGroup mContainer;
    int mContainerId;
    boolean mDeferStart;
    boolean mDetached;
    int mFragmentId;
    m mFragmentManager;
    boolean mFromLayout;
    boolean mHasMenu;
    boolean mHidden;
    boolean mHiddenChanged;
    k mHost;
    boolean mInLayout;
    int mIndex = -1;
    View mInnerView;
    boolean mIsCreated;
    boolean mIsNewlyAdded;
    LayoutInflater mLayoutInflater;
    f mLifecycleRegistry = new f(this);
    boolean mMenuVisible = true;
    g mParentFragment;
    boolean mPerformedCreateView;
    float mPostponedAlpha;
    boolean mRemoving;
    boolean mRestored;
    boolean mRetainInstance;
    boolean mRetaining;
    Bundle mSavedFragmentState;
    Boolean mSavedUserVisibleHint;
    SparseArray<Parcelable> mSavedViewState;
    int mState = 0;
    String mTag;
    g mTarget;
    int mTargetIndex = -1;
    int mTargetRequestCode;
    boolean mUserVisibleHint = true;
    View mView;
    e mViewLifecycleOwner;
    j<e> mViewLifecycleOwnerLiveData = new j<>();
    f mViewLifecycleRegistry;
    p mViewModelStore;
    String mWho;

    /* access modifiers changed from: package-private */
    /* compiled from: Fragment */
    public interface c {
        void a();

        void b();
    }

    public void onActivityResult(int i, int i2, Intent intent) {
    }

    public void onAttachFragment(g gVar) {
    }

    public boolean onContextItemSelected(MenuItem menuItem) {
        return false;
    }

    public Animation onCreateAnimation(int i, boolean z, int i2) {
        return null;
    }

    public Animator onCreateAnimator(int i, boolean z, int i2) {
        return null;
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return null;
    }

    public void onDestroyOptionsMenu() {
    }

    public void onHiddenChanged(boolean z) {
    }

    public void onMultiWindowModeChanged(boolean z) {
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        return false;
    }

    public void onOptionsMenuClosed(Menu menu) {
    }

    public void onPictureInPictureModeChanged(boolean z) {
    }

    public void onPrepareOptionsMenu(Menu menu) {
    }

    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
    }

    public void onSaveInstanceState(Bundle bundle) {
    }

    public void onViewCreated(View view, Bundle bundle) {
    }

    @Override // android.arch.lifecycle.e
    public android.arch.lifecycle.c getLifecycle() {
        return this.mLifecycleRegistry;
    }

    public e getViewLifecycleOwner() {
        e eVar = this.mViewLifecycleOwner;
        if (eVar != null) {
            return eVar;
        }
        throw new IllegalStateException("Can't access the Fragment View's LifecycleOwner when getView() is null i.e., before onCreateView() or after onDestroyView()");
    }

    public LiveData<e> getViewLifecycleOwnerLiveData() {
        return this.mViewLifecycleOwnerLiveData;
    }

    @Override // android.arch.lifecycle.q
    public p getViewModelStore() {
        if (getContext() != null) {
            if (this.mViewModelStore == null) {
                this.mViewModelStore = new p();
            }
            return this.mViewModelStore;
        }
        throw new IllegalStateException("Can't access ViewModels from detached fragment");
    }

    /* compiled from: Fragment */
    public static class d implements Parcelable {
        public static final Parcelable.Creator<d> CREATOR = new Parcelable.ClassLoaderCreator<d>() {
            /* class android.support.v4.app.g.d.AnonymousClass1 */

            /* renamed from: a */
            public d createFromParcel(Parcel parcel) {
                return new d(parcel, null);
            }

            /* renamed from: a */
            public d createFromParcel(Parcel parcel, ClassLoader classLoader) {
                return new d(parcel, classLoader);
            }

            /* renamed from: a */
            public d[] newArray(int i) {
                return new d[i];
            }
        };

        /* renamed from: a  reason: collision with root package name */
        final Bundle f194a;

        public int describeContents() {
            return 0;
        }

        d(Parcel parcel, ClassLoader classLoader) {
            Bundle bundle;
            this.f194a = parcel.readBundle();
            if (classLoader != null && (bundle = this.f194a) != null) {
                bundle.setClassLoader(classLoader);
            }
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeBundle(this.f194a);
        }
    }

    /* compiled from: Fragment */
    public static class b extends RuntimeException {
        public b(String str, Exception exc) {
            super(str, exc);
        }
    }

    public static g instantiate(Context context, String str) {
        return instantiate(context, str, null);
    }

    public static g instantiate(Context context, String str, Bundle bundle) {
        try {
            Class<?> cls = sClassMap.get(str);
            if (cls == null) {
                cls = context.getClassLoader().loadClass(str);
                sClassMap.put(str, cls);
            }
            g gVar = (g) cls.getConstructor(new Class[0]).newInstance(new Object[0]);
            if (bundle != null) {
                bundle.setClassLoader(gVar.getClass().getClassLoader());
                gVar.setArguments(bundle);
            }
            return gVar;
        } catch (ClassNotFoundException e) {
            throw new b("Unable to instantiate fragment " + str + ": make sure class name exists, is public, and has an" + " empty constructor that is public", e);
        } catch (InstantiationException e2) {
            throw new b("Unable to instantiate fragment " + str + ": make sure class name exists, is public, and has an" + " empty constructor that is public", e2);
        } catch (IllegalAccessException e3) {
            throw new b("Unable to instantiate fragment " + str + ": make sure class name exists, is public, and has an" + " empty constructor that is public", e3);
        } catch (NoSuchMethodException e4) {
            throw new b("Unable to instantiate fragment " + str + ": could not find Fragment constructor", e4);
        } catch (InvocationTargetException e5) {
            throw new b("Unable to instantiate fragment " + str + ": calling Fragment constructor caused an exception", e5);
        }
    }

    static boolean isSupportFragmentClass(Context context, String str) {
        try {
            Class<?> cls = sClassMap.get(str);
            if (cls == null) {
                cls = context.getClassLoader().loadClass(str);
                sClassMap.put(str, cls);
            }
            return g.class.isAssignableFrom(cls);
        } catch (ClassNotFoundException unused) {
            return false;
        }
    }

    /* access modifiers changed from: package-private */
    public final void restoreViewState(Bundle bundle) {
        SparseArray<Parcelable> sparseArray = this.mSavedViewState;
        if (sparseArray != null) {
            this.mInnerView.restoreHierarchyState(sparseArray);
            this.mSavedViewState = null;
        }
        this.mCalled = false;
        onViewStateRestored(bundle);
        if (!this.mCalled) {
            throw new af("Fragment " + this + " did not call through to super.onViewStateRestored()");
        } else if (this.mView != null) {
            this.mViewLifecycleRegistry.a(c.a.ON_CREATE);
        }
    }

    /* access modifiers changed from: package-private */
    public final void setIndex(int i, g gVar) {
        this.mIndex = i;
        if (gVar != null) {
            this.mWho = gVar.mWho + ":" + this.mIndex;
            return;
        }
        this.mWho = "android:fragment:" + this.mIndex;
    }

    /* access modifiers changed from: package-private */
    public final boolean isInBackStack() {
        return this.mBackStackNesting > 0;
    }

    public final boolean equals(Object obj) {
        return super.equals(obj);
    }

    public final int hashCode() {
        return super.hashCode();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(128);
        android.support.v4.util.d.a(this, sb);
        if (this.mIndex >= 0) {
            sb.append(" #");
            sb.append(this.mIndex);
        }
        if (this.mFragmentId != 0) {
            sb.append(" id=0x");
            sb.append(Integer.toHexString(this.mFragmentId));
        }
        if (this.mTag != null) {
            sb.append(" ");
            sb.append(this.mTag);
        }
        sb.append('}');
        return sb.toString();
    }

    public final int getId() {
        return this.mFragmentId;
    }

    public final String getTag() {
        return this.mTag;
    }

    public void setArguments(Bundle bundle) {
        if (this.mIndex < 0 || !isStateSaved()) {
            this.mArguments = bundle;
            return;
        }
        throw new IllegalStateException("Fragment already active and state has been saved");
    }

    public final Bundle getArguments() {
        return this.mArguments;
    }

    public final boolean isStateSaved() {
        m mVar = this.mFragmentManager;
        if (mVar == null) {
            return false;
        }
        return mVar.d();
    }

    public void setInitialSavedState(d dVar) {
        if (this.mIndex < 0) {
            this.mSavedFragmentState = (dVar == null || dVar.f194a == null) ? null : dVar.f194a;
            return;
        }
        throw new IllegalStateException("Fragment already active");
    }

    public void setTargetFragment(g gVar, int i) {
        l fragmentManager = getFragmentManager();
        l fragmentManager2 = gVar != null ? gVar.getFragmentManager() : null;
        if (fragmentManager == null || fragmentManager2 == null || fragmentManager == fragmentManager2) {
            for (g gVar2 = gVar; gVar2 != null; gVar2 = gVar2.getTargetFragment()) {
                if (gVar2 == this) {
                    throw new IllegalArgumentException("Setting " + gVar + " as the target of " + this + " would create a target cycle");
                }
            }
            this.mTarget = gVar;
            this.mTargetRequestCode = i;
            return;
        }
        throw new IllegalArgumentException("Fragment " + gVar + " must share the same FragmentManager to be set as a target fragment");
    }

    public final g getTargetFragment() {
        return this.mTarget;
    }

    public final int getTargetRequestCode() {
        return this.mTargetRequestCode;
    }

    public Context getContext() {
        k kVar = this.mHost;
        if (kVar == null) {
            return null;
        }
        return kVar.i();
    }

    public final Context requireContext() {
        Context context = getContext();
        if (context != null) {
            return context;
        }
        throw new IllegalStateException("Fragment " + this + " not attached to a context.");
    }

    public final h getActivity() {
        k kVar = this.mHost;
        if (kVar == null) {
            return null;
        }
        return (h) kVar.h();
    }

    public final h requireActivity() {
        h activity = getActivity();
        if (activity != null) {
            return activity;
        }
        throw new IllegalStateException("Fragment " + this + " not attached to an activity.");
    }

    public final Object getHost() {
        k kVar = this.mHost;
        if (kVar == null) {
            return null;
        }
        return kVar.g();
    }

    public final Object requireHost() {
        Object host = getHost();
        if (host != null) {
            return host;
        }
        throw new IllegalStateException("Fragment " + this + " not attached to a host.");
    }

    public final Resources getResources() {
        return requireContext().getResources();
    }

    public final CharSequence getText(int i) {
        return getResources().getText(i);
    }

    public final String getString(int i) {
        return getResources().getString(i);
    }

    public final String getString(int i, Object... objArr) {
        return getResources().getString(i, objArr);
    }

    public final l getFragmentManager() {
        return this.mFragmentManager;
    }

    public final l requireFragmentManager() {
        l fragmentManager = getFragmentManager();
        if (fragmentManager != null) {
            return fragmentManager;
        }
        throw new IllegalStateException("Fragment " + this + " not associated with a fragment manager.");
    }

    public final l getChildFragmentManager() {
        if (this.mChildFragmentManager == null) {
            instantiateChildFragmentManager();
            int i = this.mState;
            if (i >= 4) {
                this.mChildFragmentManager.q();
            } else if (i >= 3) {
                this.mChildFragmentManager.p();
            } else if (i >= 2) {
                this.mChildFragmentManager.o();
            } else if (i >= 1) {
                this.mChildFragmentManager.n();
            }
        }
        return this.mChildFragmentManager;
    }

    /* access modifiers changed from: package-private */
    public l peekChildFragmentManager() {
        return this.mChildFragmentManager;
    }

    public final g getParentFragment() {
        return this.mParentFragment;
    }

    public final boolean isAdded() {
        return this.mHost != null && this.mAdded;
    }

    public final boolean isDetached() {
        return this.mDetached;
    }

    public final boolean isRemoving() {
        return this.mRemoving;
    }

    public final boolean isInLayout() {
        return this.mInLayout;
    }

    public final boolean isResumed() {
        return this.mState >= 4;
    }

    public final boolean isVisible() {
        View view;
        return isAdded() && !isHidden() && (view = this.mView) != null && view.getWindowToken() != null && this.mView.getVisibility() == 0;
    }

    public final boolean isHidden() {
        return this.mHidden;
    }

    public final boolean hasOptionsMenu() {
        return this.mHasMenu;
    }

    public final boolean isMenuVisible() {
        return this.mMenuVisible;
    }

    public void setRetainInstance(boolean z) {
        this.mRetainInstance = z;
    }

    public final boolean getRetainInstance() {
        return this.mRetainInstance;
    }

    public void setHasOptionsMenu(boolean z) {
        if (this.mHasMenu != z) {
            this.mHasMenu = z;
            if (isAdded() && !isHidden()) {
                this.mHost.d();
            }
        }
    }

    public void setMenuVisibility(boolean z) {
        if (this.mMenuVisible != z) {
            this.mMenuVisible = z;
            if (this.mHasMenu && isAdded() && !isHidden()) {
                this.mHost.d();
            }
        }
    }

    public void setUserVisibleHint(boolean z) {
        if (!this.mUserVisibleHint && z && this.mState < 3 && this.mFragmentManager != null && isAdded() && this.mIsCreated) {
            this.mFragmentManager.a(this);
        }
        this.mUserVisibleHint = z;
        this.mDeferStart = this.mState < 3 && !z;
        if (this.mSavedFragmentState != null) {
            this.mSavedUserVisibleHint = Boolean.valueOf(z);
        }
    }

    public boolean getUserVisibleHint() {
        return this.mUserVisibleHint;
    }

    @Deprecated
    public v getLoaderManager() {
        return v.a(this);
    }

    public void startActivity(Intent intent) {
        startActivity(intent, null);
    }

    public void startActivity(Intent intent, Bundle bundle) {
        k kVar = this.mHost;
        if (kVar != null) {
            kVar.a(this, intent, -1, bundle);
            return;
        }
        throw new IllegalStateException("Fragment " + this + " not attached to Activity");
    }

    public void startActivityForResult(Intent intent, int i) {
        startActivityForResult(intent, i, null);
    }

    public void startActivityForResult(Intent intent, int i, Bundle bundle) {
        k kVar = this.mHost;
        if (kVar != null) {
            kVar.a(this, intent, i, bundle);
            return;
        }
        throw new IllegalStateException("Fragment " + this + " not attached to Activity");
    }

    public void startIntentSenderForResult(IntentSender intentSender, int i, Intent intent, int i2, int i3, int i4, Bundle bundle) {
        k kVar = this.mHost;
        if (kVar != null) {
            kVar.a(this, intentSender, i, intent, i2, i3, i4, bundle);
            return;
        }
        throw new IllegalStateException("Fragment " + this + " not attached to Activity");
    }

    public final void requestPermissions(String[] strArr, int i) {
        k kVar = this.mHost;
        if (kVar != null) {
            kVar.a(this, strArr, i);
            return;
        }
        throw new IllegalStateException("Fragment " + this + " not attached to Activity");
    }

    public boolean shouldShowRequestPermissionRationale(String str) {
        k kVar = this.mHost;
        if (kVar != null) {
            return kVar.a(str);
        }
        return false;
    }

    public LayoutInflater onGetLayoutInflater(Bundle bundle) {
        return getLayoutInflater(bundle);
    }

    public final LayoutInflater getLayoutInflater() {
        LayoutInflater layoutInflater = this.mLayoutInflater;
        return layoutInflater == null ? performGetLayoutInflater(null) : layoutInflater;
    }

    /* access modifiers changed from: package-private */
    public LayoutInflater performGetLayoutInflater(Bundle bundle) {
        this.mLayoutInflater = onGetLayoutInflater(bundle);
        return this.mLayoutInflater;
    }

    @Deprecated
    public LayoutInflater getLayoutInflater(Bundle bundle) {
        k kVar = this.mHost;
        if (kVar != null) {
            LayoutInflater b2 = kVar.b();
            getChildFragmentManager();
            android.support.v4.g.g.a(b2, this.mChildFragmentManager.x());
            return b2;
        }
        throw new IllegalStateException("onGetLayoutInflater() cannot be executed until the Fragment is attached to the FragmentManager.");
    }

    public void onInflate(Context context, AttributeSet attributeSet, Bundle bundle) {
        this.mCalled = true;
        k kVar = this.mHost;
        Activity h = kVar == null ? null : kVar.h();
        if (h != null) {
            this.mCalled = false;
            onInflate(h, attributeSet, bundle);
        }
    }

    @Deprecated
    public void onInflate(Activity activity, AttributeSet attributeSet, Bundle bundle) {
        this.mCalled = true;
    }

    public void onAttach(Context context) {
        this.mCalled = true;
        k kVar = this.mHost;
        Activity h = kVar == null ? null : kVar.h();
        if (h != null) {
            this.mCalled = false;
            onAttach(h);
        }
    }

    @Deprecated
    public void onAttach(Activity activity) {
        this.mCalled = true;
    }

    public void onCreate(Bundle bundle) {
        this.mCalled = true;
        restoreChildFragmentState(bundle);
        m mVar = this.mChildFragmentManager;
        if (mVar != null && !mVar.a(1)) {
            this.mChildFragmentManager.n();
        }
    }

    /* access modifiers changed from: package-private */
    public void restoreChildFragmentState(Bundle bundle) {
        Parcelable parcelable;
        if (bundle != null && (parcelable = bundle.getParcelable("android:support:fragments")) != null) {
            if (this.mChildFragmentManager == null) {
                instantiateChildFragmentManager();
            }
            this.mChildFragmentManager.a(parcelable, this.mChildNonConfig);
            this.mChildNonConfig = null;
            this.mChildFragmentManager.n();
        }
    }

    public View getView() {
        return this.mView;
    }

    public void onActivityCreated(Bundle bundle) {
        this.mCalled = true;
    }

    public void onViewStateRestored(Bundle bundle) {
        this.mCalled = true;
    }

    public void onStart() {
        this.mCalled = true;
    }

    public void onResume() {
        this.mCalled = true;
    }

    public void onConfigurationChanged(Configuration configuration) {
        this.mCalled = true;
    }

    public void onPause() {
        this.mCalled = true;
    }

    public void onStop() {
        this.mCalled = true;
    }

    public void onLowMemory() {
        this.mCalled = true;
    }

    public void onDestroyView() {
        this.mCalled = true;
    }

    public void onDestroy() {
        boolean z = true;
        this.mCalled = true;
        h activity = getActivity();
        if (activity == null || !activity.isChangingConfigurations()) {
            z = false;
        }
        p pVar = this.mViewModelStore;
        if (pVar != null && !z) {
            pVar.a();
        }
    }

    /* access modifiers changed from: package-private */
    public void initState() {
        this.mIndex = -1;
        this.mWho = null;
        this.mAdded = false;
        this.mRemoving = false;
        this.mFromLayout = false;
        this.mInLayout = false;
        this.mRestored = false;
        this.mBackStackNesting = 0;
        this.mFragmentManager = null;
        this.mChildFragmentManager = null;
        this.mHost = null;
        this.mFragmentId = 0;
        this.mContainerId = 0;
        this.mTag = null;
        this.mHidden = false;
        this.mDetached = false;
        this.mRetaining = false;
    }

    public void onDetach() {
        this.mCalled = true;
    }

    public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
        getActivity().onCreateContextMenu(contextMenu, view, contextMenuInfo);
    }

    public void registerForContextMenu(View view) {
        view.setOnCreateContextMenuListener(this);
    }

    public void unregisterForContextMenu(View view) {
        view.setOnCreateContextMenuListener(null);
    }

    public void setEnterSharedElementCallback(ae aeVar) {
        ensureAnimationInfo().o = aeVar;
    }

    public void setExitSharedElementCallback(ae aeVar) {
        ensureAnimationInfo().p = aeVar;
    }

    public void setEnterTransition(Object obj) {
        ensureAnimationInfo().g = obj;
    }

    public Object getEnterTransition() {
        a aVar = this.mAnimationInfo;
        if (aVar == null) {
            return null;
        }
        return aVar.g;
    }

    public void setReturnTransition(Object obj) {
        ensureAnimationInfo().h = obj;
    }

    public Object getReturnTransition() {
        a aVar = this.mAnimationInfo;
        if (aVar == null) {
            return null;
        }
        return aVar.h == USE_DEFAULT_TRANSITION ? getEnterTransition() : this.mAnimationInfo.h;
    }

    public void setExitTransition(Object obj) {
        ensureAnimationInfo().i = obj;
    }

    public Object getExitTransition() {
        a aVar = this.mAnimationInfo;
        if (aVar == null) {
            return null;
        }
        return aVar.i;
    }

    public void setReenterTransition(Object obj) {
        ensureAnimationInfo().j = obj;
    }

    public Object getReenterTransition() {
        a aVar = this.mAnimationInfo;
        if (aVar == null) {
            return null;
        }
        return aVar.j == USE_DEFAULT_TRANSITION ? getExitTransition() : this.mAnimationInfo.j;
    }

    public void setSharedElementEnterTransition(Object obj) {
        ensureAnimationInfo().k = obj;
    }

    public Object getSharedElementEnterTransition() {
        a aVar = this.mAnimationInfo;
        if (aVar == null) {
            return null;
        }
        return aVar.k;
    }

    public void setSharedElementReturnTransition(Object obj) {
        ensureAnimationInfo().l = obj;
    }

    public Object getSharedElementReturnTransition() {
        a aVar = this.mAnimationInfo;
        if (aVar == null) {
            return null;
        }
        return aVar.l == USE_DEFAULT_TRANSITION ? getSharedElementEnterTransition() : this.mAnimationInfo.l;
    }

    public void setAllowEnterTransitionOverlap(boolean z) {
        ensureAnimationInfo().n = Boolean.valueOf(z);
    }

    public boolean getAllowEnterTransitionOverlap() {
        a aVar = this.mAnimationInfo;
        if (aVar == null || aVar.n == null) {
            return true;
        }
        return this.mAnimationInfo.n.booleanValue();
    }

    public void setAllowReturnTransitionOverlap(boolean z) {
        ensureAnimationInfo().m = Boolean.valueOf(z);
    }

    public boolean getAllowReturnTransitionOverlap() {
        a aVar = this.mAnimationInfo;
        if (aVar == null || aVar.m == null) {
            return true;
        }
        return this.mAnimationInfo.m.booleanValue();
    }

    public void postponeEnterTransition() {
        ensureAnimationInfo().q = true;
    }

    public void startPostponedEnterTransition() {
        m mVar = this.mFragmentManager;
        if (mVar == null || mVar.m == null) {
            ensureAnimationInfo().q = false;
        } else if (Looper.myLooper() != this.mFragmentManager.m.j().getLooper()) {
            this.mFragmentManager.m.j().postAtFrontOfQueue(new Runnable() {
                /* class android.support.v4.app.g.AnonymousClass1 */

                public void run() {
                    g.this.callStartTransitionListener();
                }
            });
        } else {
            callStartTransitionListener();
        }
    }

    /* access modifiers changed from: package-private */
    public void callStartTransitionListener() {
        c cVar;
        a aVar = this.mAnimationInfo;
        if (aVar == null) {
            cVar = null;
        } else {
            aVar.q = false;
            cVar = aVar.r;
            this.mAnimationInfo.r = null;
        }
        if (cVar != null) {
            cVar.a();
        }
    }

    public void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        printWriter.print(str);
        printWriter.print("mFragmentId=#");
        printWriter.print(Integer.toHexString(this.mFragmentId));
        printWriter.print(" mContainerId=#");
        printWriter.print(Integer.toHexString(this.mContainerId));
        printWriter.print(" mTag=");
        printWriter.println(this.mTag);
        printWriter.print(str);
        printWriter.print("mState=");
        printWriter.print(this.mState);
        printWriter.print(" mIndex=");
        printWriter.print(this.mIndex);
        printWriter.print(" mWho=");
        printWriter.print(this.mWho);
        printWriter.print(" mBackStackNesting=");
        printWriter.println(this.mBackStackNesting);
        printWriter.print(str);
        printWriter.print("mAdded=");
        printWriter.print(this.mAdded);
        printWriter.print(" mRemoving=");
        printWriter.print(this.mRemoving);
        printWriter.print(" mFromLayout=");
        printWriter.print(this.mFromLayout);
        printWriter.print(" mInLayout=");
        printWriter.println(this.mInLayout);
        printWriter.print(str);
        printWriter.print("mHidden=");
        printWriter.print(this.mHidden);
        printWriter.print(" mDetached=");
        printWriter.print(this.mDetached);
        printWriter.print(" mMenuVisible=");
        printWriter.print(this.mMenuVisible);
        printWriter.print(" mHasMenu=");
        printWriter.println(this.mHasMenu);
        printWriter.print(str);
        printWriter.print("mRetainInstance=");
        printWriter.print(this.mRetainInstance);
        printWriter.print(" mRetaining=");
        printWriter.print(this.mRetaining);
        printWriter.print(" mUserVisibleHint=");
        printWriter.println(this.mUserVisibleHint);
        if (this.mFragmentManager != null) {
            printWriter.print(str);
            printWriter.print("mFragmentManager=");
            printWriter.println(this.mFragmentManager);
        }
        if (this.mHost != null) {
            printWriter.print(str);
            printWriter.print("mHost=");
            printWriter.println(this.mHost);
        }
        if (this.mParentFragment != null) {
            printWriter.print(str);
            printWriter.print("mParentFragment=");
            printWriter.println(this.mParentFragment);
        }
        if (this.mArguments != null) {
            printWriter.print(str);
            printWriter.print("mArguments=");
            printWriter.println(this.mArguments);
        }
        if (this.mSavedFragmentState != null) {
            printWriter.print(str);
            printWriter.print("mSavedFragmentState=");
            printWriter.println(this.mSavedFragmentState);
        }
        if (this.mSavedViewState != null) {
            printWriter.print(str);
            printWriter.print("mSavedViewState=");
            printWriter.println(this.mSavedViewState);
        }
        if (this.mTarget != null) {
            printWriter.print(str);
            printWriter.print("mTarget=");
            printWriter.print(this.mTarget);
            printWriter.print(" mTargetRequestCode=");
            printWriter.println(this.mTargetRequestCode);
        }
        if (getNextAnim() != 0) {
            printWriter.print(str);
            printWriter.print("mNextAnim=");
            printWriter.println(getNextAnim());
        }
        if (this.mContainer != null) {
            printWriter.print(str);
            printWriter.print("mContainer=");
            printWriter.println(this.mContainer);
        }
        if (this.mView != null) {
            printWriter.print(str);
            printWriter.print("mView=");
            printWriter.println(this.mView);
        }
        if (this.mInnerView != null) {
            printWriter.print(str);
            printWriter.print("mInnerView=");
            printWriter.println(this.mView);
        }
        if (getAnimatingAway() != null) {
            printWriter.print(str);
            printWriter.print("mAnimatingAway=");
            printWriter.println(getAnimatingAway());
            printWriter.print(str);
            printWriter.print("mStateAfterAnimating=");
            printWriter.println(getStateAfterAnimating());
        }
        if (getContext() != null) {
            v.a(this).a(str, fileDescriptor, printWriter, strArr);
        }
        if (this.mChildFragmentManager != null) {
            printWriter.print(str);
            printWriter.println("Child " + this.mChildFragmentManager + ":");
            m mVar = this.mChildFragmentManager;
            mVar.a(str + "  ", fileDescriptor, printWriter, strArr);
        }
    }

    /* access modifiers changed from: package-private */
    public g findFragmentByWho(String str) {
        if (str.equals(this.mWho)) {
            return this;
        }
        m mVar = this.mChildFragmentManager;
        if (mVar != null) {
            return mVar.b(str);
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public void instantiateChildFragmentManager() {
        if (this.mHost != null) {
            this.mChildFragmentManager = new m();
            this.mChildFragmentManager.a(this.mHost, new i() {
                /* class android.support.v4.app.g.AnonymousClass2 */

                @Override // android.support.v4.app.i
                public View a(int i) {
                    if (g.this.mView != null) {
                        return g.this.mView.findViewById(i);
                    }
                    throw new IllegalStateException("Fragment does not have a view");
                }

                @Override // android.support.v4.app.i
                public boolean a() {
                    return g.this.mView != null;
                }

                @Override // android.support.v4.app.i
                public g a(Context context, String str, Bundle bundle) {
                    return g.this.mHost.a(context, str, bundle);
                }
            }, this);
            return;
        }
        throw new IllegalStateException("Fragment has not been attached yet.");
    }

    /* access modifiers changed from: package-private */
    public void performCreate(Bundle bundle) {
        m mVar = this.mChildFragmentManager;
        if (mVar != null) {
            mVar.m();
        }
        this.mState = 1;
        this.mCalled = false;
        onCreate(bundle);
        this.mIsCreated = true;
        if (this.mCalled) {
            this.mLifecycleRegistry.a(c.a.ON_CREATE);
            return;
        }
        throw new af("Fragment " + this + " did not call through to super.onCreate()");
    }

    /* access modifiers changed from: package-private */
    public void performCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        m mVar = this.mChildFragmentManager;
        if (mVar != null) {
            mVar.m();
        }
        this.mPerformedCreateView = true;
        this.mViewLifecycleOwner = new e() {
            /* class android.support.v4.app.g.AnonymousClass3 */

            @Override // android.arch.lifecycle.e
            public android.arch.lifecycle.c getLifecycle() {
                if (g.this.mViewLifecycleRegistry == null) {
                    g gVar = g.this;
                    gVar.mViewLifecycleRegistry = new f(gVar.mViewLifecycleOwner);
                }
                return g.this.mViewLifecycleRegistry;
            }
        };
        this.mViewLifecycleRegistry = null;
        this.mView = onCreateView(layoutInflater, viewGroup, bundle);
        if (this.mView != null) {
            this.mViewLifecycleOwner.getLifecycle();
            this.mViewLifecycleOwnerLiveData.b(this.mViewLifecycleOwner);
        } else if (this.mViewLifecycleRegistry == null) {
            this.mViewLifecycleOwner = null;
        } else {
            throw new IllegalStateException("Called getViewLifecycleOwner() but onCreateView() returned null");
        }
    }

    /* access modifiers changed from: package-private */
    public void performActivityCreated(Bundle bundle) {
        m mVar = this.mChildFragmentManager;
        if (mVar != null) {
            mVar.m();
        }
        this.mState = 2;
        this.mCalled = false;
        onActivityCreated(bundle);
        if (this.mCalled) {
            m mVar2 = this.mChildFragmentManager;
            if (mVar2 != null) {
                mVar2.o();
                return;
            }
            return;
        }
        throw new af("Fragment " + this + " did not call through to super.onActivityCreated()");
    }

    /* access modifiers changed from: package-private */
    public void performStart() {
        m mVar = this.mChildFragmentManager;
        if (mVar != null) {
            mVar.m();
            this.mChildFragmentManager.g();
        }
        this.mState = 3;
        this.mCalled = false;
        onStart();
        if (this.mCalled) {
            m mVar2 = this.mChildFragmentManager;
            if (mVar2 != null) {
                mVar2.p();
            }
            this.mLifecycleRegistry.a(c.a.ON_START);
            if (this.mView != null) {
                this.mViewLifecycleRegistry.a(c.a.ON_START);
                return;
            }
            return;
        }
        throw new af("Fragment " + this + " did not call through to super.onStart()");
    }

    /* access modifiers changed from: package-private */
    public void performResume() {
        m mVar = this.mChildFragmentManager;
        if (mVar != null) {
            mVar.m();
            this.mChildFragmentManager.g();
        }
        this.mState = 4;
        this.mCalled = false;
        onResume();
        if (this.mCalled) {
            m mVar2 = this.mChildFragmentManager;
            if (mVar2 != null) {
                mVar2.q();
                this.mChildFragmentManager.g();
            }
            this.mLifecycleRegistry.a(c.a.ON_RESUME);
            if (this.mView != null) {
                this.mViewLifecycleRegistry.a(c.a.ON_RESUME);
                return;
            }
            return;
        }
        throw new af("Fragment " + this + " did not call through to super.onResume()");
    }

    /* access modifiers changed from: package-private */
    public void noteStateNotSaved() {
        m mVar = this.mChildFragmentManager;
        if (mVar != null) {
            mVar.m();
        }
    }

    /* access modifiers changed from: package-private */
    public void performMultiWindowModeChanged(boolean z) {
        onMultiWindowModeChanged(z);
        m mVar = this.mChildFragmentManager;
        if (mVar != null) {
            mVar.a(z);
        }
    }

    /* access modifiers changed from: package-private */
    public void performPictureInPictureModeChanged(boolean z) {
        onPictureInPictureModeChanged(z);
        m mVar = this.mChildFragmentManager;
        if (mVar != null) {
            mVar.b(z);
        }
    }

    /* access modifiers changed from: package-private */
    public void performConfigurationChanged(Configuration configuration) {
        onConfigurationChanged(configuration);
        m mVar = this.mChildFragmentManager;
        if (mVar != null) {
            mVar.a(configuration);
        }
    }

    /* access modifiers changed from: package-private */
    public void performLowMemory() {
        onLowMemory();
        m mVar = this.mChildFragmentManager;
        if (mVar != null) {
            mVar.v();
        }
    }

    /* access modifiers changed from: package-private */
    public boolean performCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        boolean z = false;
        if (this.mHidden) {
            return false;
        }
        if (this.mHasMenu && this.mMenuVisible) {
            onCreateOptionsMenu(menu, menuInflater);
            z = true;
        }
        m mVar = this.mChildFragmentManager;
        return mVar != null ? z | mVar.a(menu, menuInflater) : z;
    }

    /* access modifiers changed from: package-private */
    public boolean performPrepareOptionsMenu(Menu menu) {
        boolean z = false;
        if (this.mHidden) {
            return false;
        }
        if (this.mHasMenu && this.mMenuVisible) {
            onPrepareOptionsMenu(menu);
            z = true;
        }
        m mVar = this.mChildFragmentManager;
        return mVar != null ? z | mVar.a(menu) : z;
    }

    /* access modifiers changed from: package-private */
    public boolean performOptionsItemSelected(MenuItem menuItem) {
        if (this.mHidden) {
            return false;
        }
        if (this.mHasMenu && this.mMenuVisible && onOptionsItemSelected(menuItem)) {
            return true;
        }
        m mVar = this.mChildFragmentManager;
        if (mVar == null || !mVar.a(menuItem)) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: package-private */
    public boolean performContextItemSelected(MenuItem menuItem) {
        if (this.mHidden) {
            return false;
        }
        if (onContextItemSelected(menuItem)) {
            return true;
        }
        m mVar = this.mChildFragmentManager;
        if (mVar == null || !mVar.b(menuItem)) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: package-private */
    public void performOptionsMenuClosed(Menu menu) {
        if (!this.mHidden) {
            if (this.mHasMenu && this.mMenuVisible) {
                onOptionsMenuClosed(menu);
            }
            m mVar = this.mChildFragmentManager;
            if (mVar != null) {
                mVar.b(menu);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void performSaveInstanceState(Bundle bundle) {
        Parcelable l;
        onSaveInstanceState(bundle);
        m mVar = this.mChildFragmentManager;
        if (mVar != null && (l = mVar.l()) != null) {
            bundle.putParcelable("android:support:fragments", l);
        }
    }

    /* access modifiers changed from: package-private */
    public void performPause() {
        if (this.mView != null) {
            this.mViewLifecycleRegistry.a(c.a.ON_PAUSE);
        }
        this.mLifecycleRegistry.a(c.a.ON_PAUSE);
        m mVar = this.mChildFragmentManager;
        if (mVar != null) {
            mVar.r();
        }
        this.mState = 3;
        this.mCalled = false;
        onPause();
        if (!this.mCalled) {
            throw new af("Fragment " + this + " did not call through to super.onPause()");
        }
    }

    /* access modifiers changed from: package-private */
    public void performStop() {
        if (this.mView != null) {
            this.mViewLifecycleRegistry.a(c.a.ON_STOP);
        }
        this.mLifecycleRegistry.a(c.a.ON_STOP);
        m mVar = this.mChildFragmentManager;
        if (mVar != null) {
            mVar.s();
        }
        this.mState = 2;
        this.mCalled = false;
        onStop();
        if (!this.mCalled) {
            throw new af("Fragment " + this + " did not call through to super.onStop()");
        }
    }

    /* access modifiers changed from: package-private */
    public void performDestroyView() {
        if (this.mView != null) {
            this.mViewLifecycleRegistry.a(c.a.ON_DESTROY);
        }
        m mVar = this.mChildFragmentManager;
        if (mVar != null) {
            mVar.t();
        }
        this.mState = 1;
        this.mCalled = false;
        onDestroyView();
        if (this.mCalled) {
            v.a(this).a();
            this.mPerformedCreateView = false;
            return;
        }
        throw new af("Fragment " + this + " did not call through to super.onDestroyView()");
    }

    /* access modifiers changed from: package-private */
    public void performDestroy() {
        this.mLifecycleRegistry.a(c.a.ON_DESTROY);
        m mVar = this.mChildFragmentManager;
        if (mVar != null) {
            mVar.u();
        }
        this.mState = 0;
        this.mCalled = false;
        this.mIsCreated = false;
        onDestroy();
        if (this.mCalled) {
            this.mChildFragmentManager = null;
            return;
        }
        throw new af("Fragment " + this + " did not call through to super.onDestroy()");
    }

    /* access modifiers changed from: package-private */
    public void performDetach() {
        this.mCalled = false;
        onDetach();
        this.mLayoutInflater = null;
        if (this.mCalled) {
            m mVar = this.mChildFragmentManager;
            if (mVar == null) {
                return;
            }
            if (this.mRetaining) {
                mVar.u();
                this.mChildFragmentManager = null;
                return;
            }
            throw new IllegalStateException("Child FragmentManager of " + this + " was not " + " destroyed and this fragment is not retaining instance");
        }
        throw new af("Fragment " + this + " did not call through to super.onDetach()");
    }

    /* access modifiers changed from: package-private */
    public void setOnStartEnterTransitionListener(c cVar) {
        ensureAnimationInfo();
        if (cVar != this.mAnimationInfo.r) {
            if (cVar == null || this.mAnimationInfo.r == null) {
                if (this.mAnimationInfo.q) {
                    this.mAnimationInfo.r = cVar;
                }
                if (cVar != null) {
                    cVar.b();
                    return;
                }
                return;
            }
            throw new IllegalStateException("Trying to set a replacement startPostponedEnterTransition on " + this);
        }
    }

    private a ensureAnimationInfo() {
        if (this.mAnimationInfo == null) {
            this.mAnimationInfo = new a();
        }
        return this.mAnimationInfo;
    }

    /* access modifiers changed from: package-private */
    public int getNextAnim() {
        a aVar = this.mAnimationInfo;
        if (aVar == null) {
            return 0;
        }
        return aVar.f193d;
    }

    /* access modifiers changed from: package-private */
    public void setNextAnim(int i) {
        if (this.mAnimationInfo != null || i != 0) {
            ensureAnimationInfo().f193d = i;
        }
    }

    /* access modifiers changed from: package-private */
    public int getNextTransition() {
        a aVar = this.mAnimationInfo;
        if (aVar == null) {
            return 0;
        }
        return aVar.e;
    }

    /* access modifiers changed from: package-private */
    public void setNextTransition(int i, int i2) {
        if (this.mAnimationInfo != null || i != 0 || i2 != 0) {
            ensureAnimationInfo();
            a aVar = this.mAnimationInfo;
            aVar.e = i;
            aVar.f = i2;
        }
    }

    /* access modifiers changed from: package-private */
    public int getNextTransitionStyle() {
        a aVar = this.mAnimationInfo;
        if (aVar == null) {
            return 0;
        }
        return aVar.f;
    }

    /* access modifiers changed from: package-private */
    public ae getEnterTransitionCallback() {
        a aVar = this.mAnimationInfo;
        if (aVar == null) {
            return null;
        }
        return aVar.o;
    }

    /* access modifiers changed from: package-private */
    public ae getExitTransitionCallback() {
        a aVar = this.mAnimationInfo;
        if (aVar == null) {
            return null;
        }
        return aVar.p;
    }

    /* access modifiers changed from: package-private */
    public View getAnimatingAway() {
        a aVar = this.mAnimationInfo;
        if (aVar == null) {
            return null;
        }
        return aVar.f190a;
    }

    /* access modifiers changed from: package-private */
    public void setAnimatingAway(View view) {
        ensureAnimationInfo().f190a = view;
    }

    /* access modifiers changed from: package-private */
    public void setAnimator(Animator animator) {
        ensureAnimationInfo().f191b = animator;
    }

    /* access modifiers changed from: package-private */
    public Animator getAnimator() {
        a aVar = this.mAnimationInfo;
        if (aVar == null) {
            return null;
        }
        return aVar.f191b;
    }

    /* access modifiers changed from: package-private */
    public int getStateAfterAnimating() {
        a aVar = this.mAnimationInfo;
        if (aVar == null) {
            return 0;
        }
        return aVar.f192c;
    }

    /* access modifiers changed from: package-private */
    public void setStateAfterAnimating(int i) {
        ensureAnimationInfo().f192c = i;
    }

    /* access modifiers changed from: package-private */
    public boolean isPostponed() {
        a aVar = this.mAnimationInfo;
        if (aVar == null) {
            return false;
        }
        return aVar.q;
    }

    /* access modifiers changed from: package-private */
    public boolean isHideReplaced() {
        a aVar = this.mAnimationInfo;
        if (aVar == null) {
            return false;
        }
        return aVar.s;
    }

    /* access modifiers changed from: package-private */
    public void setHideReplaced(boolean z) {
        ensureAnimationInfo().s = z;
    }

    /* access modifiers changed from: package-private */
    /* compiled from: Fragment */
    public static class a {

        /* renamed from: a  reason: collision with root package name */
        View f190a;

        /* renamed from: b  reason: collision with root package name */
        Animator f191b;

        /* renamed from: c  reason: collision with root package name */
        int f192c;

        /* renamed from: d  reason: collision with root package name */
        int f193d;
        int e;
        int f;
        Object g = null;
        Object h = g.USE_DEFAULT_TRANSITION;
        Object i = null;
        Object j = g.USE_DEFAULT_TRANSITION;
        Object k = null;
        Object l = g.USE_DEFAULT_TRANSITION;
        Boolean m;
        Boolean n;
        ae o = null;
        ae p = null;
        boolean q;
        c r;
        boolean s;

        a() {
        }
    }
}
