package com.google.android.gms.tasks;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* access modifiers changed from: package-private */
public final class zzx implements Continuation<Void, Task<List<Task<?>>>> {
    private final /* synthetic */ Collection zzae;

    zzx(Collection collection) {
        this.zzae = collection;
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [com.google.android.gms.tasks.Task] */
    @Override // com.google.android.gms.tasks.Continuation
    public final /* synthetic */ Task<List<Task<?>>> then(Task<Void> task) {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(this.zzae);
        return Tasks.forResult(arrayList);
    }
}
