package com.google.android.gms.common;

import com.google.android.gms.common.api.internal.zai;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;
import java.util.Map;

final class zaa implements Continuation<Map<zai<?>, String>, Void> {
    zaa(GoogleApiAvailability googleApiAvailability) {
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [com.google.android.gms.tasks.Task] */
    @Override // com.google.android.gms.tasks.Continuation
    public final /* synthetic */ Void then(Task<Map<zai<?>, String>> task) {
        task.getResult();
        return null;
    }
}
