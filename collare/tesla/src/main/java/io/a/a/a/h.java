package io.a.a.a;

import io.a.a.a.a.b.w;
import io.a.a.a.a.c.e;
import io.a.a.a.a.c.f;
import io.a.a.a.a.c.m;

/* access modifiers changed from: package-private */
/* compiled from: InitializationTask */
public class h<Result> extends f<Void, Void, Result> {

    /* renamed from: a  reason: collision with root package name */
    final i<Result> f6095a;

    public h(i<Result> iVar) {
        this.f6095a = iVar;
    }

    /* access modifiers changed from: protected */
    @Override // io.a.a.a.a.c.a
    public void a() {
        super.a();
        w a2 = a("onPreExecute");
        try {
            boolean onPreExecute = this.f6095a.onPreExecute();
            a2.b();
            if (onPreExecute) {
                return;
            }
        } catch (m e) {
            throw e;
        } catch (Exception e2) {
            c.g().e("Fabric", "Failure onPreExecute()", e2);
            a2.b();
        } catch (Throwable th) {
            a2.b();
            a(true);
            throw th;
        }
        a(true);
    }

    /* access modifiers changed from: protected */
    public Result a(Void... voidArr) {
        w a2 = a("doInBackground");
        Result doInBackground = !d() ? this.f6095a.doInBackground() : null;
        a2.b();
        return doInBackground;
    }

    /* access modifiers changed from: protected */
    @Override // io.a.a.a.a.c.a
    public void a(Result result) {
        this.f6095a.onPostExecute(result);
        this.f6095a.initializationCallback.a(result);
    }

    /* access modifiers changed from: protected */
    @Override // io.a.a.a.a.c.a
    public void b(Result result) {
        this.f6095a.onCancelled(result);
        this.f6095a.initializationCallback.a(new g(this.f6095a.getIdentifier() + " Initialization was cancelled"));
    }

    @Override // io.a.a.a.a.c.f, io.a.a.a.a.c.i
    public e getPriority() {
        return e.HIGH;
    }

    private w a(String str) {
        w wVar = new w(this.f6095a.getIdentifier() + "." + str, "KitInitialization");
        wVar.a();
        return wVar;
    }
}
