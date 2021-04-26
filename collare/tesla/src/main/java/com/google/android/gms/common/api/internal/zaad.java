package com.google.android.gms.common.api.internal;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;

/* access modifiers changed from: package-private */
public final class zaad implements OnCompleteListener<TResult> {
    private final /* synthetic */ zaab zafn;
    private final /* synthetic */ TaskCompletionSource zafo;

    zaad(zaab zaab, TaskCompletionSource taskCompletionSource) {
        this.zafn = zaab;
        this.zafo = taskCompletionSource;
    }

    @Override // com.google.android.gms.tasks.OnCompleteListener
    public final void onComplete(Task<TResult> task) {
        this.zafn.zafl.remove(this.zafo);
    }
}
