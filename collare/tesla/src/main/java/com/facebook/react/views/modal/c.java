package com.facebook.react.views.modal;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStructure;
import android.view.accessibility.AccessibilityEvent;
import android.widget.FrameLayout;
import com.facebook.react.bridge.GuardedRunnable;
import com.facebook.react.bridge.LifecycleEventListener;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.f;
import com.facebook.react.uimanager.UIManagerModule;
import com.facebook.react.uimanager.ab;
import com.facebook.react.uimanager.events.d;
import com.facebook.react.uimanager.g;
import java.util.ArrayList;

/* compiled from: ReactModalHostView */
public class c extends ViewGroup implements LifecycleEventListener {

    /* renamed from: a  reason: collision with root package name */
    private a f3349a;

    /* renamed from: b  reason: collision with root package name */
    private Dialog f3350b;

    /* renamed from: c  reason: collision with root package name */
    private boolean f3351c;

    /* renamed from: d  reason: collision with root package name */
    private String f3352d;
    private boolean e;
    private boolean f;
    private DialogInterface.OnShowListener g;
    private b h;

    /* compiled from: ReactModalHostView */
    public interface b {
        void a(DialogInterface dialogInterface);
    }

    @Override // android.view.View, android.view.ViewGroup
    public void addChildrenForAccessibility(ArrayList<View> arrayList) {
    }

    public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        return false;
    }

    @Override // com.facebook.react.bridge.LifecycleEventListener
    public void onHostPause() {
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
    }

    public c(Context context) {
        super(context);
        ((ReactContext) context).addLifecycleEventListener(this);
        this.f3349a = new a(context);
    }

    @TargetApi(23)
    public void dispatchProvideStructure(ViewStructure viewStructure) {
        this.f3349a.dispatchProvideStructure(viewStructure);
    }

    @Override // android.view.ViewGroup
    public void addView(View view, int i) {
        this.f3349a.addView(view, i);
    }

    public int getChildCount() {
        return this.f3349a.getChildCount();
    }

    public View getChildAt(int i) {
        return this.f3349a.getChildAt(i);
    }

    public void removeView(View view) {
        this.f3349a.removeView(view);
    }

    public void removeViewAt(int i) {
        this.f3349a.removeView(getChildAt(i));
    }

    public void a() {
        ((ReactContext) getContext()).removeLifecycleEventListener(this);
        c();
    }

    private void c() {
        Activity activity;
        Dialog dialog = this.f3350b;
        if (dialog != null) {
            if (dialog.isShowing() && ((activity = (Activity) com.facebook.react.views.a.a.a(this.f3350b.getContext(), Activity.class)) == null || !activity.isFinishing())) {
                this.f3350b.dismiss();
            }
            this.f3350b = null;
            ((ViewGroup) this.f3349a.getParent()).removeViewAt(0);
        }
    }

    /* access modifiers changed from: protected */
    public void setOnRequestCloseListener(b bVar) {
        this.h = bVar;
    }

    /* access modifiers changed from: protected */
    public void setOnShowListener(DialogInterface.OnShowListener onShowListener) {
        this.g = onShowListener;
    }

    /* access modifiers changed from: protected */
    public void setTransparent(boolean z) {
        this.f3351c = z;
    }

    /* access modifiers changed from: protected */
    public void setAnimationType(String str) {
        this.f3352d = str;
        this.f = true;
    }

    /* access modifiers changed from: protected */
    public void setHardwareAccelerated(boolean z) {
        this.e = z;
        this.f = true;
    }

    @Override // com.facebook.react.bridge.LifecycleEventListener
    public void onHostResume() {
        b();
    }

    @Override // com.facebook.react.bridge.LifecycleEventListener
    public void onHostDestroy() {
        a();
    }

    public Dialog getDialog() {
        return this.f3350b;
    }

    private Activity getCurrentActivity() {
        return ((ReactContext) getContext()).getCurrentActivity();
    }

    /* access modifiers changed from: protected */
    public void b() {
        if (this.f3350b != null) {
            if (this.f) {
                c();
            } else {
                d();
                return;
            }
        }
        this.f = false;
        int i = f.c.Theme_FullScreenDialog;
        if (this.f3352d.equals("fade")) {
            i = f.c.Theme_FullScreenDialogAnimatedFade;
        } else if (this.f3352d.equals("slide")) {
            i = f.c.Theme_FullScreenDialogAnimatedSlide;
        }
        Activity currentActivity = getCurrentActivity();
        this.f3350b = new Dialog(currentActivity == null ? getContext() : currentActivity, i);
        this.f3350b.setContentView(getContentView());
        d();
        this.f3350b.setOnShowListener(this.g);
        this.f3350b.setOnKeyListener(new DialogInterface.OnKeyListener() {
            /* class com.facebook.react.views.modal.c.AnonymousClass1 */

            public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
                if (keyEvent.getAction() != 1) {
                    return false;
                }
                if (i == 4) {
                    com.facebook.i.a.a.a(c.this.h, "setOnRequestCloseListener must be called by the manager");
                    c.this.h.a(dialogInterface);
                    return true;
                }
                Activity currentActivity = ((ReactContext) c.this.getContext()).getCurrentActivity();
                if (currentActivity != null) {
                    return currentActivity.onKeyUp(i, keyEvent);
                }
                return false;
            }
        });
        this.f3350b.getWindow().setSoftInputMode(16);
        if (this.e) {
            this.f3350b.getWindow().addFlags(16777216);
        }
        if (currentActivity != null && !currentActivity.isFinishing()) {
            this.f3350b.show();
        }
    }

    private View getContentView() {
        FrameLayout frameLayout = new FrameLayout(getContext());
        frameLayout.addView(this.f3349a);
        frameLayout.setFitsSystemWindows(true);
        return frameLayout;
    }

    private void d() {
        com.facebook.i.a.a.a(this.f3350b, "mDialog must exist when we call updateProperties");
        Activity currentActivity = getCurrentActivity();
        if (currentActivity != null) {
            if ((currentActivity.getWindow().getAttributes().flags & 1024) != 0) {
                this.f3350b.getWindow().addFlags(1024);
            } else {
                this.f3350b.getWindow().clearFlags(1024);
            }
        }
        if (this.f3351c) {
            this.f3350b.getWindow().clearFlags(2);
            return;
        }
        this.f3350b.getWindow().setDimAmount(0.5f);
        this.f3350b.getWindow().setFlags(2, 2);
    }

    /* access modifiers changed from: package-private */
    /* compiled from: ReactModalHostView */
    public static class a extends com.facebook.react.views.view.f implements ab {

        /* renamed from: a  reason: collision with root package name */
        private final g f3354a = new g(this);

        public void requestDisallowInterceptTouchEvent(boolean z) {
        }

        public a(Context context) {
            super(context);
        }

        /* access modifiers changed from: protected */
        @Override // com.facebook.react.views.view.f
        public void onSizeChanged(final int i, final int i2, int i3, int i4) {
            super.onSizeChanged(i, i2, i3, i4);
            if (getChildCount() > 0) {
                final int id = getChildAt(0).getId();
                ReactContext a2 = a();
                a2.runOnNativeModulesQueueThread(new GuardedRunnable(a2) {
                    /* class com.facebook.react.views.modal.c.a.AnonymousClass1 */

                    @Override // com.facebook.react.bridge.GuardedRunnable
                    public void runGuarded() {
                        ((UIManagerModule) a.this.a().getNativeModule(UIManagerModule.class)).updateNodeSize(id, i, i2);
                    }
                });
            }
        }

        @Override // com.facebook.react.uimanager.ab
        public void a(Throwable th) {
            a().handleException(new RuntimeException(th));
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private ReactContext a() {
            return (ReactContext) getContext();
        }

        @Override // com.facebook.react.views.view.f
        public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
            this.f3354a.b(motionEvent, b());
            return super.onInterceptTouchEvent(motionEvent);
        }

        @Override // com.facebook.react.views.view.f
        public boolean onTouchEvent(MotionEvent motionEvent) {
            this.f3354a.b(motionEvent, b());
            super.onTouchEvent(motionEvent);
            return true;
        }

        @Override // com.facebook.react.uimanager.ab
        public void a(MotionEvent motionEvent) {
            this.f3354a.a(motionEvent, b());
        }

        private d b() {
            return ((UIManagerModule) a().getNativeModule(UIManagerModule.class)).getEventDispatcher();
        }
    }
}
