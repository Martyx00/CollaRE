package a;

/* compiled from: TaskCompletionSource */
public class i<TResult> {

    /* renamed from: a  reason: collision with root package name */
    private final h<TResult> f34a = new h<>();

    public h<TResult> a() {
        return this.f34a;
    }

    public boolean b() {
        return this.f34a.g();
    }

    public boolean a(TResult tresult) {
        return this.f34a.b((Object) tresult);
    }

    public boolean a(Exception exc) {
        return this.f34a.b(exc);
    }

    public void c() {
        if (!b()) {
            throw new IllegalStateException("Cannot cancel a completed task.");
        }
    }

    public void b(TResult tresult) {
        if (!a((Object) tresult)) {
            throw new IllegalStateException("Cannot set the result of a completed task.");
        }
    }

    public void b(Exception exc) {
        if (!a(exc)) {
            throw new IllegalStateException("Cannot set the error on a completed task.");
        }
    }
}
