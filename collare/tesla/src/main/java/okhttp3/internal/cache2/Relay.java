package okhttp3.internal.cache2;

import c.c;
import c.f;
import c.t;
import c.u;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import okhttp3.internal.Util;

final class Relay {
    private static final long FILE_HEADER_SIZE = 32;
    static final f PREFIX_CLEAN = f.a("OkHttp cache v1\n");
    static final f PREFIX_DIRTY = f.a("OkHttp DIRTY :(\n");
    private static final int SOURCE_FILE = 2;
    private static final int SOURCE_UPSTREAM = 1;
    final c buffer = new c();
    final long bufferMaxSize;
    boolean complete;
    RandomAccessFile file;
    private final f metadata;
    int sourceCount;
    t upstream;
    final c upstreamBuffer = new c();
    long upstreamPos;
    Thread upstreamReader;

    private Relay(RandomAccessFile randomAccessFile, t tVar, long j, f fVar, long j2) {
        this.file = randomAccessFile;
        this.upstream = tVar;
        this.complete = tVar == null;
        this.upstreamPos = j;
        this.metadata = fVar;
        this.bufferMaxSize = j2;
    }

    public static Relay edit(File file2, t tVar, f fVar, long j) {
        RandomAccessFile randomAccessFile = new RandomAccessFile(file2, "rw");
        Relay relay = new Relay(randomAccessFile, tVar, 0, fVar, j);
        randomAccessFile.setLength(0);
        relay.writeHeader(PREFIX_DIRTY, -1, -1);
        return relay;
    }

    public static Relay read(File file2) {
        RandomAccessFile randomAccessFile = new RandomAccessFile(file2, "rw");
        FileOperator fileOperator = new FileOperator(randomAccessFile.getChannel());
        c cVar = new c();
        fileOperator.read(0, cVar, FILE_HEADER_SIZE);
        if (cVar.d((long) PREFIX_CLEAN.h()).equals(PREFIX_CLEAN)) {
            long l = cVar.l();
            long l2 = cVar.l();
            c cVar2 = new c();
            fileOperator.read(l + FILE_HEADER_SIZE, cVar2, l2);
            return new Relay(randomAccessFile, null, l, cVar2.q(), 0);
        }
        throw new IOException("unreadable cache file");
    }

    private void writeHeader(f fVar, long j, long j2) {
        c cVar = new c();
        cVar.c(fVar);
        cVar.j(j);
        cVar.j(j2);
        if (cVar.a() == FILE_HEADER_SIZE) {
            new FileOperator(this.file.getChannel()).write(0, cVar, FILE_HEADER_SIZE);
            return;
        }
        throw new IllegalArgumentException();
    }

    private void writeMetadata(long j) {
        c cVar = new c();
        cVar.c(this.metadata);
        new FileOperator(this.file.getChannel()).write(FILE_HEADER_SIZE + j, cVar, (long) this.metadata.h());
    }

    /* access modifiers changed from: package-private */
    public void commit(long j) {
        writeMetadata(j);
        this.file.getChannel().force(false);
        writeHeader(PREFIX_CLEAN, j, (long) this.metadata.h());
        this.file.getChannel().force(false);
        synchronized (this) {
            this.complete = true;
        }
        Util.closeQuietly(this.upstream);
        this.upstream = null;
    }

    /* access modifiers changed from: package-private */
    public boolean isClosed() {
        return this.file == null;
    }

    public f metadata() {
        return this.metadata;
    }

    public t newSource() {
        synchronized (this) {
            if (this.file == null) {
                return null;
            }
            this.sourceCount++;
            return new RelaySource();
        }
    }

    class RelaySource implements t {
        private FileOperator fileOperator = new FileOperator(Relay.this.file.getChannel());
        private long sourcePos;
        private final u timeout = new u();

        RelaySource() {
        }

        /* JADX WARNING: Code restructure failed: missing block: B:20:0x004a, code lost:
            r5 = 2;
         */
        @Override // c.t
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public long read(c.c r22, long r23) {
            /*
            // Method dump skipped, instructions count: 322
            */
            throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.cache2.Relay.RelaySource.read(c.c, long):long");
        }

        @Override // c.t
        public u timeout() {
            return this.timeout;
        }

        @Override // java.io.Closeable, c.t, java.lang.AutoCloseable
        public void close() {
            if (this.fileOperator != null) {
                RandomAccessFile randomAccessFile = null;
                this.fileOperator = null;
                synchronized (Relay.this) {
                    Relay relay = Relay.this;
                    relay.sourceCount--;
                    if (Relay.this.sourceCount == 0) {
                        RandomAccessFile randomAccessFile2 = Relay.this.file;
                        Relay.this.file = null;
                        randomAccessFile = randomAccessFile2;
                    }
                }
                if (randomAccessFile != null) {
                    Util.closeQuietly(randomAccessFile);
                }
            }
        }
    }
}
