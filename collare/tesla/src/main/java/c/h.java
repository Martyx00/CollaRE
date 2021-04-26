package c;

/* compiled from: ForwardingSource */
public abstract class h implements t {
    private final t delegate;

    public h(t tVar) {
        if (tVar != null) {
            this.delegate = tVar;
            return;
        }
        throw new IllegalArgumentException("delegate == null");
    }

    public final t delegate() {
        return this.delegate;
    }

    @Override // c.t
    public long read(c cVar, long j) {
        return this.delegate.read(cVar, j);
    }

    @Override // c.t
    public u timeout() {
        return this.delegate.timeout();
    }

    @Override // java.io.Closeable, c.t, java.lang.AutoCloseable
    public void close() {
        this.delegate.close();
    }

    public String toString() {
        return getClass().getSimpleName() + "(" + this.delegate.toString() + ")";
    }
}
