package com.google.firebase.iid;

import android.os.Bundle;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;
import java.io.IOException;

/* access modifiers changed from: package-private */
public final class ax implements Continuation<Bundle, String> {

    /* renamed from: a  reason: collision with root package name */
    private final /* synthetic */ au f3914a;

    ax(au auVar) {
        this.f3914a = auVar;
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [com.google.android.gms.tasks.Task] */
    @Override // com.google.android.gms.tasks.Continuation
    public final /* synthetic */ String then(Task<Bundle> task) {
        au auVar = this.f3914a;
        return au.a(task.getResult(IOException.class));
    }
}
