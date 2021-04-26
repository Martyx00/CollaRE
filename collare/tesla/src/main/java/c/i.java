package c;

import java.util.concurrent.TimeUnit;

/* compiled from: ForwardingTimeout */
public class i extends u {

    /* renamed from: a  reason: collision with root package name */
    private u f1333a;

    public i(u uVar) {
        if (uVar != null) {
            this.f1333a = uVar;
            return;
        }
        throw new IllegalArgumentException("delegate == null");
    }

    public final u a() {
        return this.f1333a;
    }

    public final i a(u uVar) {
        if (uVar != null) {
            this.f1333a = uVar;
            return this;
        }
        throw new IllegalArgumentException("delegate == null");
    }

    @Override // c.u
    public u timeout(long j, TimeUnit timeUnit) {
        return this.f1333a.timeout(j, timeUnit);
    }

    @Override // c.u
    public long timeoutNanos() {
        return this.f1333a.timeoutNanos();
    }

    @Override // c.u
    public boolean hasDeadline() {
        return this.f1333a.hasDeadline();
    }

    @Override // c.u
    public long deadlineNanoTime() {
        return this.f1333a.deadlineNanoTime();
    }

    @Override // c.u
    public u deadlineNanoTime(long j) {
        return this.f1333a.deadlineNanoTime(j);
    }

    @Override // c.u
    public u clearTimeout() {
        return this.f1333a.clearTimeout();
    }

    @Override // c.u
    public u clearDeadline() {
        return this.f1333a.clearDeadline();
    }

    @Override // c.u
    public void throwIfReached() {
        this.f1333a.throwIfReached();
    }
}
