package c;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.util.concurrent.TimeUnit;

/* compiled from: AsyncTimeout */
public class a extends u {
    private static final long IDLE_TIMEOUT_MILLIS = TimeUnit.SECONDS.toMillis(60);
    private static final long IDLE_TIMEOUT_NANOS = TimeUnit.MILLISECONDS.toNanos(IDLE_TIMEOUT_MILLIS);
    private static final int TIMEOUT_WRITE_SIZE = 65536;
    static a head;
    private boolean inQueue;
    private a next;
    private long timeoutAt;

    /* access modifiers changed from: protected */
    public void timedOut() {
    }

    public final void enter() {
        if (!this.inQueue) {
            long timeoutNanos = timeoutNanos();
            boolean hasDeadline = hasDeadline();
            if (timeoutNanos != 0 || hasDeadline) {
                this.inQueue = true;
                scheduleTimeout(this, timeoutNanos, hasDeadline);
                return;
            }
            return;
        }
        throw new IllegalStateException("Unbalanced enter/exit");
    }

    private static synchronized void scheduleTimeout(a aVar, long j, boolean z) {
        synchronized (a.class) {
            if (head == null) {
                head = new a();
                new C0030a().start();
            }
            long nanoTime = System.nanoTime();
            int i = (j > 0 ? 1 : (j == 0 ? 0 : -1));
            if (i != 0 && z) {
                aVar.timeoutAt = Math.min(j, aVar.deadlineNanoTime() - nanoTime) + nanoTime;
            } else if (i != 0) {
                aVar.timeoutAt = j + nanoTime;
            } else if (z) {
                aVar.timeoutAt = aVar.deadlineNanoTime();
            } else {
                throw new AssertionError();
            }
            long remainingNanos = aVar.remainingNanos(nanoTime);
            a aVar2 = head;
            while (true) {
                if (aVar2.next == null) {
                    break;
                } else if (remainingNanos < aVar2.next.remainingNanos(nanoTime)) {
                    break;
                } else {
                    aVar2 = aVar2.next;
                }
            }
            aVar.next = aVar2.next;
            aVar2.next = aVar;
            if (aVar2 == head) {
                a.class.notify();
            }
        }
    }

    public final boolean exit() {
        if (!this.inQueue) {
            return false;
        }
        this.inQueue = false;
        return cancelScheduledTimeout(this);
    }

    private static synchronized boolean cancelScheduledTimeout(a aVar) {
        synchronized (a.class) {
            for (a aVar2 = head; aVar2 != null; aVar2 = aVar2.next) {
                if (aVar2.next == aVar) {
                    aVar2.next = aVar.next;
                    aVar.next = null;
                    return false;
                }
            }
            return true;
        }
    }

    private long remainingNanos(long j) {
        return this.timeoutAt - j;
    }

    public final s sink(final s sVar) {
        return new s() {
            /* class c.a.AnonymousClass1 */

            @Override // c.s
            public void write(c cVar, long j) {
                v.a(cVar.f1322b, 0, j);
                while (true) {
                    long j2 = 0;
                    if (j > 0) {
                        p pVar = cVar.f1321a;
                        while (true) {
                            if (j2 >= 65536) {
                                break;
                            }
                            j2 += (long) (pVar.f1366c - pVar.f1365b);
                            if (j2 >= j) {
                                j2 = j;
                                break;
                            }
                            pVar = pVar.f;
                        }
                        a.this.enter();
                        try {
                            sVar.write(cVar, j2);
                            j -= j2;
                            a.this.exit(true);
                        } catch (IOException e) {
                            throw a.this.exit(e);
                        } catch (Throwable th) {
                            a.this.exit(false);
                            throw th;
                        }
                    } else {
                        return;
                    }
                }
            }

            @Override // c.s, java.io.Flushable
            public void flush() {
                a.this.enter();
                try {
                    sVar.flush();
                    a.this.exit(true);
                } catch (IOException e) {
                    throw a.this.exit(e);
                } catch (Throwable th) {
                    a.this.exit(false);
                    throw th;
                }
            }

            @Override // java.io.Closeable, c.s, java.lang.AutoCloseable
            public void close() {
                a.this.enter();
                try {
                    sVar.close();
                    a.this.exit(true);
                } catch (IOException e) {
                    throw a.this.exit(e);
                } catch (Throwable th) {
                    a.this.exit(false);
                    throw th;
                }
            }

            @Override // c.s
            public u timeout() {
                return a.this;
            }

            public String toString() {
                return "AsyncTimeout.sink(" + sVar + ")";
            }
        };
    }

    public final t source(final t tVar) {
        return new t() {
            /* class c.a.AnonymousClass2 */

            @Override // c.t
            public long read(c cVar, long j) {
                a.this.enter();
                try {
                    long read = tVar.read(cVar, j);
                    a.this.exit(true);
                    return read;
                } catch (IOException e) {
                    throw a.this.exit(e);
                } catch (Throwable th) {
                    a.this.exit(false);
                    throw th;
                }
            }

            @Override // java.io.Closeable, c.t, java.lang.AutoCloseable
            public void close() {
                try {
                    tVar.close();
                    a.this.exit(true);
                } catch (IOException e) {
                    throw a.this.exit(e);
                } catch (Throwable th) {
                    a.this.exit(false);
                    throw th;
                }
            }

            @Override // c.t
            public u timeout() {
                return a.this;
            }

            public String toString() {
                return "AsyncTimeout.source(" + tVar + ")";
            }
        };
    }

    /* access modifiers changed from: package-private */
    public final void exit(boolean z) {
        if (exit() && z) {
            throw newTimeoutException(null);
        }
    }

    /* access modifiers changed from: package-private */
    public final IOException exit(IOException iOException) {
        if (!exit()) {
            return iOException;
        }
        return newTimeoutException(iOException);
    }

    /* access modifiers changed from: protected */
    public IOException newTimeoutException(IOException iOException) {
        InterruptedIOException interruptedIOException = new InterruptedIOException("timeout");
        if (iOException != null) {
            interruptedIOException.initCause(iOException);
        }
        return interruptedIOException;
    }

    /* access modifiers changed from: private */
    /* renamed from: c.a$a  reason: collision with other inner class name */
    /* compiled from: AsyncTimeout */
    public static final class C0030a extends Thread {
        C0030a() {
            super("Okio Watchdog");
            setDaemon(true);
        }

        /* JADX WARNING: Code restructure failed: missing block: B:15:0x0015, code lost:
            r1.timedOut();
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void run() {
            /*
                r3 = this;
            L_0x0000:
                java.lang.Class<c.a> r0 = c.a.class
                monitor-enter(r0)     // Catch:{ InterruptedException -> 0x0000 }
                c.a r1 = c.a.awaitTimeout()     // Catch:{ all -> 0x0019 }
                if (r1 != 0) goto L_0x000b
                monitor-exit(r0)     // Catch:{ all -> 0x0019 }
                goto L_0x0000
            L_0x000b:
                c.a r2 = c.a.head     // Catch:{ all -> 0x0019 }
                if (r1 != r2) goto L_0x0014
                r1 = 0
                c.a.head = r1     // Catch:{ all -> 0x0019 }
                monitor-exit(r0)     // Catch:{ all -> 0x0019 }
                return
            L_0x0014:
                monitor-exit(r0)     // Catch:{ all -> 0x0019 }
                r1.timedOut()
                goto L_0x0000
            L_0x0019:
                r1 = move-exception
                monitor-exit(r0)
                throw r1
            */
            throw new UnsupportedOperationException("Method not decompiled: c.a.C0030a.run():void");
        }
    }

    static a awaitTimeout() {
        a aVar = head.next;
        if (aVar == null) {
            long nanoTime = System.nanoTime();
            a.class.wait(IDLE_TIMEOUT_MILLIS);
            if (head.next != null || System.nanoTime() - nanoTime < IDLE_TIMEOUT_NANOS) {
                return null;
            }
            return head;
        }
        long remainingNanos = aVar.remainingNanos(System.nanoTime());
        if (remainingNanos > 0) {
            long j = remainingNanos / 1000000;
            a.class.wait(j, (int) (remainingNanos - (1000000 * j)));
            return null;
        }
        head.next = aVar.next;
        aVar.next = null;
        return aVar;
    }
}
