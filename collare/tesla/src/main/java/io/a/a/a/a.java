package io.a.a.a;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import java.util.HashSet;
import java.util.Set;

/* compiled from: ActivityLifecycleManager */
public class a {

    /* renamed from: a  reason: collision with root package name */
    private final Application f5870a;

    /* renamed from: b  reason: collision with root package name */
    private C0161a f5871b;

    /* compiled from: ActivityLifecycleManager */
    public static abstract class b {
        public void onActivityCreated(Activity activity, Bundle bundle) {
        }

        public void onActivityDestroyed(Activity activity) {
        }

        public void onActivityPaused(Activity activity) {
        }

        public void onActivityResumed(Activity activity) {
        }

        public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        }

        public void onActivityStarted(Activity activity) {
        }

        public void onActivityStopped(Activity activity) {
        }
    }

    public a(Context context) {
        this.f5870a = (Application) context.getApplicationContext();
        if (Build.VERSION.SDK_INT >= 14) {
            this.f5871b = new C0161a(this.f5870a);
        }
    }

    public boolean a(b bVar) {
        C0161a aVar = this.f5871b;
        return aVar != null && aVar.a(bVar);
    }

    public void a() {
        C0161a aVar = this.f5871b;
        if (aVar != null) {
            aVar.a();
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: io.a.a.a.a$a  reason: collision with other inner class name */
    /* compiled from: ActivityLifecycleManager */
    public static class C0161a {

        /* renamed from: a  reason: collision with root package name */
        private final Set<Application.ActivityLifecycleCallbacks> f5872a = new HashSet();

        /* renamed from: b  reason: collision with root package name */
        private final Application f5873b;

        C0161a(Application application) {
            this.f5873b = application;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        @TargetApi(14)
        private void a() {
            for (Application.ActivityLifecycleCallbacks activityLifecycleCallbacks : this.f5872a) {
                this.f5873b.unregisterActivityLifecycleCallbacks(activityLifecycleCallbacks);
            }
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        @TargetApi(14)
        private boolean a(final b bVar) {
            if (this.f5873b == null) {
                return false;
            }
            AnonymousClass1 r0 = new Application.ActivityLifecycleCallbacks() {
                /* class io.a.a.a.a.C0161a.AnonymousClass1 */

                public void onActivityCreated(Activity activity, Bundle bundle) {
                    bVar.onActivityCreated(activity, bundle);
                }

                public void onActivityStarted(Activity activity) {
                    bVar.onActivityStarted(activity);
                }

                public void onActivityResumed(Activity activity) {
                    bVar.onActivityResumed(activity);
                }

                public void onActivityPaused(Activity activity) {
                    bVar.onActivityPaused(activity);
                }

                public void onActivityStopped(Activity activity) {
                    bVar.onActivityStopped(activity);
                }

                public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
                    bVar.onActivitySaveInstanceState(activity, bundle);
                }

                public void onActivityDestroyed(Activity activity) {
                    bVar.onActivityDestroyed(activity);
                }
            };
            this.f5873b.registerActivityLifecycleCallbacks(r0);
            this.f5872a.add(r0);
            return true;
        }
    }
}
