package android.support.v4.g;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.os.Build;
import android.view.View;
import android.view.animation.Interpolator;
import java.lang.ref.WeakReference;

/* compiled from: ViewPropertyAnimatorCompat */
public final class y {

    /* renamed from: a  reason: collision with root package name */
    Runnable f532a = null;

    /* renamed from: b  reason: collision with root package name */
    Runnable f533b = null;

    /* renamed from: c  reason: collision with root package name */
    int f534c = -1;

    /* renamed from: d  reason: collision with root package name */
    private WeakReference<View> f535d;

    y(View view) {
        this.f535d = new WeakReference<>(view);
    }

    /* compiled from: ViewPropertyAnimatorCompat */
    static class a implements z {

        /* renamed from: a  reason: collision with root package name */
        y f542a;

        /* renamed from: b  reason: collision with root package name */
        boolean f543b;

        a(y yVar) {
            this.f542a = yVar;
        }

        @Override // android.support.v4.g.z
        public void a(View view) {
            this.f543b = false;
            z zVar = null;
            if (this.f542a.f534c > -1) {
                view.setLayerType(2, null);
            }
            if (this.f542a.f532a != null) {
                Runnable runnable = this.f542a.f532a;
                this.f542a.f532a = null;
                runnable.run();
            }
            Object tag = view.getTag(2113929216);
            if (tag instanceof z) {
                zVar = (z) tag;
            }
            if (zVar != null) {
                zVar.a(view);
            }
        }

        @Override // android.support.v4.g.z
        public void b(View view) {
            z zVar = null;
            if (this.f542a.f534c > -1) {
                view.setLayerType(this.f542a.f534c, null);
                this.f542a.f534c = -1;
            }
            if (Build.VERSION.SDK_INT >= 16 || !this.f543b) {
                if (this.f542a.f533b != null) {
                    Runnable runnable = this.f542a.f533b;
                    this.f542a.f533b = null;
                    runnable.run();
                }
                Object tag = view.getTag(2113929216);
                if (tag instanceof z) {
                    zVar = (z) tag;
                }
                if (zVar != null) {
                    zVar.b(view);
                }
                this.f543b = true;
            }
        }

        @Override // android.support.v4.g.z
        public void c(View view) {
            Object tag = view.getTag(2113929216);
            z zVar = tag instanceof z ? (z) tag : null;
            if (zVar != null) {
                zVar.c(view);
            }
        }
    }

    public y a(long j) {
        View view = this.f535d.get();
        if (view != null) {
            view.animate().setDuration(j);
        }
        return this;
    }

    public y a(float f) {
        View view = this.f535d.get();
        if (view != null) {
            view.animate().alpha(f);
        }
        return this;
    }

    public y b(float f) {
        View view = this.f535d.get();
        if (view != null) {
            view.animate().translationY(f);
        }
        return this;
    }

    public long a() {
        View view = this.f535d.get();
        if (view != null) {
            return view.animate().getDuration();
        }
        return 0;
    }

    public y a(Interpolator interpolator) {
        View view = this.f535d.get();
        if (view != null) {
            view.animate().setInterpolator(interpolator);
        }
        return this;
    }

    public y b(long j) {
        View view = this.f535d.get();
        if (view != null) {
            view.animate().setStartDelay(j);
        }
        return this;
    }

    public void b() {
        View view = this.f535d.get();
        if (view != null) {
            view.animate().cancel();
        }
    }

    public void c() {
        View view = this.f535d.get();
        if (view != null) {
            view.animate().start();
        }
    }

    public y a(z zVar) {
        View view = this.f535d.get();
        if (view != null) {
            if (Build.VERSION.SDK_INT >= 16) {
                a(view, zVar);
            } else {
                view.setTag(2113929216, zVar);
                a(view, new a(this));
            }
        }
        return this;
    }

    private void a(final View view, final z zVar) {
        if (zVar != null) {
            view.animate().setListener(new AnimatorListenerAdapter() {
                /* class android.support.v4.g.y.AnonymousClass1 */

                public void onAnimationCancel(Animator animator) {
                    zVar.c(view);
                }

                public void onAnimationEnd(Animator animator) {
                    zVar.b(view);
                }

                public void onAnimationStart(Animator animator) {
                    zVar.a(view);
                }
            });
        } else {
            view.animate().setListener(null);
        }
    }

    public y a(final ab abVar) {
        final View view = this.f535d.get();
        if (view != null && Build.VERSION.SDK_INT >= 19) {
            AnonymousClass2 r1 = null;
            if (abVar != null) {
                r1 = new ValueAnimator.AnimatorUpdateListener() {
                    /* class android.support.v4.g.y.AnonymousClass2 */

                    public void onAnimationUpdate(ValueAnimator valueAnimator) {
                        abVar.a(view);
                    }
                };
            }
            view.animate().setUpdateListener(r1);
        }
        return this;
    }
}
