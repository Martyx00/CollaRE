package com.google.android.gms.tasks;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/* access modifiers changed from: package-private */
public final class zzw implements Continuation<Void, List<TResult>> {
    private final /* synthetic */ Collection zzae;

    zzw(Collection collection) {
        this.zzae = collection;
    }

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [com.google.android.gms.tasks.Task] */
    @Override // com.google.android.gms.tasks.Continuation
    public final /* synthetic */ Object then(Task<Void> task) {
        if (this.zzae.size() == 0) {
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList();
        for (Task task2 : this.zzae) {
            arrayList.add(task2.getResult());
        }
        return arrayList;
    }
}
