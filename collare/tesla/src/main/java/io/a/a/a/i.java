package io.a.a.a;

import android.content.Context;
import io.a.a.a.a.b.r;
import io.a.a.a.a.c.d;
import io.a.a.a.a.c.l;
import java.io.File;
import java.util.Collection;

/* compiled from: Kit */
public abstract class i<Result> implements Comparable<i> {
    Context context;
    final d dependsOnAnnotation = ((d) getClass().getAnnotation(d.class));
    c fabric;
    r idManager;
    f<Result> initializationCallback;
    h<Result> initializationTask = new h<>(this);

    /* access modifiers changed from: protected */
    public abstract Result doInBackground();

    public abstract String getIdentifier();

    public abstract String getVersion();

    /* access modifiers changed from: protected */
    public void onCancelled(Result result) {
    }

    /* access modifiers changed from: protected */
    public void onPostExecute(Result result) {
    }

    /* access modifiers changed from: protected */
    public boolean onPreExecute() {
        return true;
    }

    /* access modifiers changed from: package-private */
    public void injectParameters(Context context2, c cVar, f<Result> fVar, r rVar) {
        this.fabric = cVar;
        this.context = new d(context2, getIdentifier(), getPath());
        this.initializationCallback = fVar;
        this.idManager = rVar;
    }

    /* access modifiers changed from: package-private */
    public final void initialize() {
        this.initializationTask.a(this.fabric.e(), (Object[]) new Void[]{null});
    }

    /* access modifiers changed from: protected */
    public r getIdManager() {
        return this.idManager;
    }

    public Context getContext() {
        return this.context;
    }

    public c getFabric() {
        return this.fabric;
    }

    public String getPath() {
        return ".Fabric" + File.separator + getIdentifier();
    }

    public int compareTo(i iVar) {
        if (containsAnnotatedDependency(iVar)) {
            return 1;
        }
        if (iVar.containsAnnotatedDependency(this)) {
            return -1;
        }
        if (hasAnnotatedDependency() && !iVar.hasAnnotatedDependency()) {
            return 1;
        }
        if (hasAnnotatedDependency() || !iVar.hasAnnotatedDependency()) {
            return 0;
        }
        return -1;
    }

    /* access modifiers changed from: package-private */
    public boolean containsAnnotatedDependency(i iVar) {
        if (hasAnnotatedDependency()) {
            for (Class<?> cls : this.dependsOnAnnotation.a()) {
                if (cls.isAssignableFrom(iVar.getClass())) {
                    return true;
                }
            }
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    public boolean hasAnnotatedDependency() {
        return this.dependsOnAnnotation != null;
    }

    /* access modifiers changed from: protected */
    public Collection<l> getDependencies() {
        return this.initializationTask.getDependencies();
    }
}
