package c;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;

/* compiled from: Okio */
public final class l {

    /* renamed from: a  reason: collision with root package name */
    static final Logger f1342a = Logger.getLogger(l.class.getName());

    private l() {
    }

    public static e a(t tVar) {
        return new o(tVar);
    }

    public static d a(s sVar) {
        return new n(sVar);
    }

    public static s a(OutputStream outputStream) {
        return a(outputStream, new u());
    }

    private static s a(final OutputStream outputStream, final u uVar) {
        if (outputStream == null) {
            throw new IllegalArgumentException("out == null");
        } else if (uVar != null) {
            return new s() {
                /* class c.l.AnonymousClass1 */

                @Override // c.s
                public void write(c cVar, long j) {
                    v.a(cVar.f1322b, 0, j);
                    while (j > 0) {
                        u.this.throwIfReached();
                        p pVar = cVar.f1321a;
                        int min = (int) Math.min(j, (long) (pVar.f1366c - pVar.f1365b));
                        outputStream.write(pVar.f1364a, pVar.f1365b, min);
                        pVar.f1365b += min;
                        long j2 = (long) min;
                        j -= j2;
                        cVar.f1322b -= j2;
                        if (pVar.f1365b == pVar.f1366c) {
                            cVar.f1321a = pVar.c();
                            q.a(pVar);
                        }
                    }
                }

                @Override // c.s, java.io.Flushable
                public void flush() {
                    outputStream.flush();
                }

                @Override // java.io.Closeable, c.s, java.lang.AutoCloseable
                public void close() {
                    outputStream.close();
                }

                @Override // c.s
                public u timeout() {
                    return u.this;
                }

                public String toString() {
                    return "sink(" + outputStream + ")";
                }
            };
        } else {
            throw new IllegalArgumentException("timeout == null");
        }
    }

    public static s a(Socket socket) {
        if (socket == null) {
            throw new IllegalArgumentException("socket == null");
        } else if (socket.getOutputStream() != null) {
            a c2 = c(socket);
            return c2.sink(a(socket.getOutputStream(), c2));
        } else {
            throw new IOException("socket's output stream == null");
        }
    }

    public static t a(InputStream inputStream) {
        return a(inputStream, new u());
    }

    private static t a(final InputStream inputStream, final u uVar) {
        if (inputStream == null) {
            throw new IllegalArgumentException("in == null");
        } else if (uVar != null) {
            return new t() {
                /* class c.l.AnonymousClass2 */

                @Override // c.t
                public long read(c cVar, long j) {
                    int i = (j > 0 ? 1 : (j == 0 ? 0 : -1));
                    if (i < 0) {
                        throw new IllegalArgumentException("byteCount < 0: " + j);
                    } else if (i == 0) {
                        return 0;
                    } else {
                        try {
                            u.this.throwIfReached();
                            p e = cVar.e(1);
                            int read = inputStream.read(e.f1364a, e.f1366c, (int) Math.min(j, (long) (8192 - e.f1366c)));
                            if (read == -1) {
                                return -1;
                            }
                            e.f1366c += read;
                            long j2 = (long) read;
                            cVar.f1322b += j2;
                            return j2;
                        } catch (AssertionError e2) {
                            if (l.a(e2)) {
                                throw new IOException(e2);
                            }
                            throw e2;
                        }
                    }
                }

                @Override // java.io.Closeable, c.t, java.lang.AutoCloseable
                public void close() {
                    inputStream.close();
                }

                @Override // c.t
                public u timeout() {
                    return u.this;
                }

                public String toString() {
                    return "source(" + inputStream + ")";
                }
            };
        } else {
            throw new IllegalArgumentException("timeout == null");
        }
    }

    public static t a(File file) {
        if (file != null) {
            return a(new FileInputStream(file));
        }
        throw new IllegalArgumentException("file == null");
    }

    public static s b(File file) {
        if (file != null) {
            return a(new FileOutputStream(file));
        }
        throw new IllegalArgumentException("file == null");
    }

    public static s c(File file) {
        if (file != null) {
            return a(new FileOutputStream(file, true));
        }
        throw new IllegalArgumentException("file == null");
    }

    public static s a() {
        return new s() {
            /* class c.l.AnonymousClass3 */

            @Override // java.io.Closeable, c.s, java.lang.AutoCloseable
            public void close() {
            }

            @Override // c.s, java.io.Flushable
            public void flush() {
            }

            @Override // c.s
            public void write(c cVar, long j) {
                cVar.i(j);
            }

            @Override // c.s
            public u timeout() {
                return u.NONE;
            }
        };
    }

    public static t b(Socket socket) {
        if (socket == null) {
            throw new IllegalArgumentException("socket == null");
        } else if (socket.getInputStream() != null) {
            a c2 = c(socket);
            return c2.source(a(socket.getInputStream(), c2));
        } else {
            throw new IOException("socket's input stream == null");
        }
    }

    private static a c(final Socket socket) {
        return new a() {
            /* class c.l.AnonymousClass4 */

            /* access modifiers changed from: protected */
            @Override // c.a
            public IOException newTimeoutException(IOException iOException) {
                SocketTimeoutException socketTimeoutException = new SocketTimeoutException("timeout");
                if (iOException != null) {
                    socketTimeoutException.initCause(iOException);
                }
                return socketTimeoutException;
            }

            /* access modifiers changed from: protected */
            @Override // c.a
            public void timedOut() {
                try {
                    socket.close();
                } catch (Exception e) {
                    Logger logger = l.f1342a;
                    Level level = Level.WARNING;
                    logger.log(level, "Failed to close timed out socket " + socket, (Throwable) e);
                } catch (AssertionError e2) {
                    if (l.a(e2)) {
                        Logger logger2 = l.f1342a;
                        Level level2 = Level.WARNING;
                        logger2.log(level2, "Failed to close timed out socket " + socket, (Throwable) e2);
                        return;
                    }
                    throw e2;
                }
            }
        };
    }

    static boolean a(AssertionError assertionError) {
        return (assertionError.getCause() == null || assertionError.getMessage() == null || !assertionError.getMessage().contains("getsockname failed")) ? false : true;
    }
}
