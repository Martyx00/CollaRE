package com.google.android.gms.tasks;

import android.app.Activity;
import com.google.android.gms.common.api.internal.LifecycleCallback;
import com.google.android.gms.common.api.internal.LifecycleFragment;
import com.google.android.gms.common.internal.Preconditions;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CancellationException;
import java.util.concurrent.Executor;

/* access modifiers changed from: package-private */
public final class zzu<TResult> extends Task<TResult> {
    private final Object mLock = new Object();
    private TResult zzaa;
    private Exception zzab;
    private final zzr<TResult> zzx = new zzr<>();
    private boolean zzy;
    private volatile boolean zzz;

    zzu() {
    }

    @Override // com.google.android.gms.tasks.Task
    public final boolean isComplete() {
        boolean z;
        synchronized (this.mLock) {
            z = this.zzy;
        }
        return z;
    }

    private static class zza extends LifecycleCallback {
        private final List<WeakReference<zzq<?>>> zzac = new ArrayList();

        public static zza zza(Activity activity) {
            LifecycleFragment fragment = getFragment(activity);
            zza zza = (zza) fragment.getCallbackOrNull("TaskOnStopCallback", zza.class);
            return zza == null ? new zza(fragment) : zza;
        }

        private zza(LifecycleFragment lifecycleFragment) {
            super(lifecycleFragment);
            this.mLifecycleFragment.addCallback("TaskOnStopCallback", this);
        }

        public final <T> void zzb(zzq<T> zzq) {
            synchronized (this.zzac) {
                this.zzac.add(new WeakReference<>(zzq));
            }
        }

        @Override // com.google.android.gms.common.api.internal.LifecycleCallback
        public void onStop() {
            synchronized (this.zzac) {
                for (WeakReference<zzq<?>> weakReference : this.zzac) {
                    zzq<?> zzq = weakReference.get();
                    if (zzq != null) {
                        zzq.cancel();
                    }
                }
                this.zzac.clear();
            }
        }
    }

    @Override // com.google.android.gms.tasks.Task
    public final boolean isCanceled() {
        return this.zzz;
    }

    @Override // com.google.android.gms.tasks.Task
    public final boolean isSuccessful() {
        boolean z;
        synchronized (this.mLock) {
            z = this.zzy && !this.zzz && this.zzab == null;
        }
        return z;
    }

    @Override // com.google.android.gms.tasks.Task
    public final TResult getResult() {
        TResult tresult;
        synchronized (this.mLock) {
            zzb();
            zzd();
            if (this.zzab == null) {
                tresult = this.zzaa;
            } else {
                throw new RuntimeExecutionException(this.zzab);
            }
        }
        return tresult;
    }

    @Override // com.google.android.gms.tasks.Task
    public final <X extends Throwable> TResult getResult(Class<X> cls) {
        TResult tresult;
        synchronized (this.mLock) {
            zzb();
            zzd();
            if (cls.isInstance(this.zzab)) {
                throw cls.cast(this.zzab);
            } else if (this.zzab == null) {
                tresult = this.zzaa;
            } else {
                throw new RuntimeExecutionException(this.zzab);
            }
        }
        return tresult;
    }

    @Override // com.google.android.gms.tasks.Task
    public final Exception getException() {
        Exception exc;
        synchronized (this.mLock) {
            exc = this.zzab;
        }
        return exc;
    }

    @Override // com.google.android.gms.tasks.Task
    public final Task<TResult> addOnSuccessListener(OnSuccessListener<? super TResult> onSuccessListener) {
        return addOnSuccessListener(TaskExecutors.MAIN_THREAD, onSuccessListener);
    }

    @Override // com.google.android.gms.tasks.Task
    public final Task<TResult> addOnSuccessListener(Executor executor, OnSuccessListener<? super TResult> onSuccessListener) {
        this.zzx.zza(new zzm(executor, onSuccessListener));
        zze();
        return this;
    }

    @Override // com.google.android.gms.tasks.Task
    public final Task<TResult> addOnSuccessListener(Activity activity, OnSuccessListener<? super TResult> onSuccessListener) {
        zzm zzm = new zzm(TaskExecutors.MAIN_THREAD, onSuccessListener);
        this.zzx.zza(zzm);
        zza.zza(activity).zzb(zzm);
        zze();
        return this;
    }

    @Override // com.google.android.gms.tasks.Task
    public final Task<TResult> addOnFailureListener(OnFailureListener onFailureListener) {
        return addOnFailureListener(TaskExecutors.MAIN_THREAD, onFailureListener);
    }

    @Override // com.google.android.gms.tasks.Task
    public final Task<TResult> addOnFailureListener(Executor executor, OnFailureListener onFailureListener) {
        this.zzx.zza(new zzk(executor, onFailureListener));
        zze();
        return this;
    }

    @Override // com.google.android.gms.tasks.Task
    public final Task<TResult> addOnFailureListener(Activity activity, OnFailureListener onFailureListener) {
        zzk zzk = new zzk(TaskExecutors.MAIN_THREAD, onFailureListener);
        this.zzx.zza(zzk);
        zza.zza(activity).zzb(zzk);
        zze();
        return this;
    }

    @Override // com.google.android.gms.tasks.Task
    public final Task<TResult> addOnCompleteListener(OnCompleteListener<TResult> onCompleteListener) {
        return addOnCompleteListener(TaskExecutors.MAIN_THREAD, onCompleteListener);
    }

    @Override // com.google.android.gms.tasks.Task
    public final Task<TResult> addOnCompleteListener(Executor executor, OnCompleteListener<TResult> onCompleteListener) {
        this.zzx.zza(new zzi(executor, onCompleteListener));
        zze();
        return this;
    }

    @Override // com.google.android.gms.tasks.Task
    public final Task<TResult> addOnCompleteListener(Activity activity, OnCompleteListener<TResult> onCompleteListener) {
        zzi zzi = new zzi(TaskExecutors.MAIN_THREAD, onCompleteListener);
        this.zzx.zza(zzi);
        zza.zza(activity).zzb(zzi);
        zze();
        return this;
    }

    @Override // com.google.android.gms.tasks.Task
    public final <TContinuationResult> Task<TContinuationResult> continueWith(Continuation<TResult, TContinuationResult> continuation) {
        return continueWith(TaskExecutors.MAIN_THREAD, continuation);
    }

    @Override // com.google.android.gms.tasks.Task
    public final <TContinuationResult> Task<TContinuationResult> continueWith(Executor executor, Continuation<TResult, TContinuationResult> continuation) {
        zzu zzu = new zzu();
        this.zzx.zza(new zzc(executor, continuation, zzu));
        zze();
        return zzu;
    }

    @Override // com.google.android.gms.tasks.Task
    public final <TContinuationResult> Task<TContinuationResult> continueWithTask(Continuation<TResult, Task<TContinuationResult>> continuation) {
        return continueWithTask(TaskExecutors.MAIN_THREAD, continuation);
    }

    @Override // com.google.android.gms.tasks.Task
    public final Task<TResult> addOnCanceledListener(OnCanceledListener onCanceledListener) {
        return addOnCanceledListener(TaskExecutors.MAIN_THREAD, onCanceledListener);
    }

    @Override // com.google.android.gms.tasks.Task
    public final Task<TResult> addOnCanceledListener(Executor executor, OnCanceledListener onCanceledListener) {
        this.zzx.zza(new zzg(executor, onCanceledListener));
        zze();
        return this;
    }

    @Override // com.google.android.gms.tasks.Task
    public final Task<TResult> addOnCanceledListener(Activity activity, OnCanceledListener onCanceledListener) {
        zzg zzg = new zzg(TaskExecutors.MAIN_THREAD, onCanceledListener);
        this.zzx.zza(zzg);
        zza.zza(activity).zzb(zzg);
        zze();
        return this;
    }

    @Override // com.google.android.gms.tasks.Task
    public final <TContinuationResult> Task<TContinuationResult> continueWithTask(Executor executor, Continuation<TResult, Task<TContinuationResult>> continuation) {
        zzu zzu = new zzu();
        this.zzx.zza(new zze(executor, continuation, zzu));
        zze();
        return zzu;
    }

    @Override // com.google.android.gms.tasks.Task
    public final <TContinuationResult> Task<TContinuationResult> onSuccessTask(Executor executor, SuccessContinuation<TResult, TContinuationResult> successContinuation) {
        zzu zzu = new zzu();
        this.zzx.zza(new zzo(executor, successContinuation, zzu));
        zze();
        return zzu;
    }

    @Override // com.google.android.gms.tasks.Task
    public final <TContinuationResult> Task<TContinuationResult> onSuccessTask(SuccessContinuation<TResult, TContinuationResult> successContinuation) {
        return onSuccessTask(TaskExecutors.MAIN_THREAD, successContinuation);
    }

    public final void setResult(TResult tresult) {
        synchronized (this.mLock) {
            zzc();
            this.zzy = true;
            this.zzaa = tresult;
        }
        this.zzx.zza(this);
    }

    public final boolean trySetResult(TResult tresult) {
        synchronized (this.mLock) {
            if (this.zzy) {
                return false;
            }
            this.zzy = true;
            this.zzaa = tresult;
            this.zzx.zza(this);
            return true;
        }
    }

    public final void setException(Exception exc) {
        Preconditions.checkNotNull(exc, "Exception must not be null");
        synchronized (this.mLock) {
            zzc();
            this.zzy = true;
            this.zzab = exc;
        }
        this.zzx.zza(this);
    }

    public final boolean trySetException(Exception exc) {
        Preconditions.checkNotNull(exc, "Exception must not be null");
        synchronized (this.mLock) {
            if (this.zzy) {
                return false;
            }
            this.zzy = true;
            this.zzab = exc;
            this.zzx.zza(this);
            return true;
        }
    }

    public final boolean zza() {
        synchronized (this.mLock) {
            if (this.zzy) {
                return false;
            }
            this.zzy = true;
            this.zzz = true;
            this.zzx.zza(this);
            return true;
        }
    }

    private final void zzb() {
        Preconditions.checkState(this.zzy, "Task is not yet complete");
    }

    private final void zzc() {
        Preconditions.checkState(!this.zzy, "Task is already complete");
    }

    private final void zzd() {
        if (this.zzz) {
            throw new CancellationException("Task is already canceled.");
        }
    }

    private final void zze() {
        synchronized (this.mLock) {
            if (this.zzy) {
                this.zzx.zza(this);
            }
        }
    }
}
