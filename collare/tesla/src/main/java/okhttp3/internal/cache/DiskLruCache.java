package okhttp3.internal.cache;

import c.d;
import c.e;
import c.l;
import c.s;
import c.t;
import java.io.Closeable;
import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.Flushable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.NoSuchElementException;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;
import okhttp3.internal.Util;
import okhttp3.internal.io.FileSystem;
import okhttp3.internal.platform.Platform;

public final class DiskLruCache implements Closeable, Flushable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    static final long ANY_SEQUENCE_NUMBER = -1;
    private static final String CLEAN = "CLEAN";
    private static final String DIRTY = "DIRTY";
    static final String JOURNAL_FILE = "journal";
    static final String JOURNAL_FILE_BACKUP = "journal.bkp";
    static final String JOURNAL_FILE_TEMP = "journal.tmp";
    static final Pattern LEGAL_KEY_PATTERN = Pattern.compile("[a-z0-9_-]{1,120}");
    static final String MAGIC = "libcore.io.DiskLruCache";
    private static final String READ = "READ";
    private static final String REMOVE = "REMOVE";
    static final String VERSION_1 = "1";
    private final int appVersion;
    private final Runnable cleanupRunnable = new Runnable() {
        /* class okhttp3.internal.cache.DiskLruCache.AnonymousClass1 */

        public void run() {
            synchronized (DiskLruCache.this) {
                if (!(!DiskLruCache.this.initialized) && !DiskLruCache.this.closed) {
                    try {
                        DiskLruCache.this.trimToSize();
                    } catch (IOException unused) {
                        DiskLruCache.this.mostRecentTrimFailed = true;
                    }
                    try {
                        if (DiskLruCache.this.journalRebuildRequired()) {
                            DiskLruCache.this.rebuildJournal();
                            DiskLruCache.this.redundantOpCount = 0;
                        }
                    } catch (IOException unused2) {
                        DiskLruCache.this.mostRecentRebuildFailed = true;
                        DiskLruCache.this.journalWriter = l.a(l.a());
                    }
                }
            }
        }
    };
    boolean closed;
    final File directory;
    private final Executor executor;
    final FileSystem fileSystem;
    boolean hasJournalErrors;
    boolean initialized;
    private final File journalFile;
    private final File journalFileBackup;
    private final File journalFileTmp;
    d journalWriter;
    final LinkedHashMap<String, Entry> lruEntries = new LinkedHashMap<>(0, 0.75f, true);
    private long maxSize;
    boolean mostRecentRebuildFailed;
    boolean mostRecentTrimFailed;
    private long nextSequenceNumber = 0;
    int redundantOpCount;
    private long size = 0;
    final int valueCount;

    DiskLruCache(FileSystem fileSystem2, File file, int i, int i2, long j, Executor executor2) {
        this.fileSystem = fileSystem2;
        this.directory = file;
        this.appVersion = i;
        this.journalFile = new File(file, JOURNAL_FILE);
        this.journalFileTmp = new File(file, JOURNAL_FILE_TEMP);
        this.journalFileBackup = new File(file, JOURNAL_FILE_BACKUP);
        this.valueCount = i2;
        this.maxSize = j;
        this.executor = executor2;
    }

    public synchronized void initialize() {
        if (!this.initialized) {
            if (this.fileSystem.exists(this.journalFileBackup)) {
                if (this.fileSystem.exists(this.journalFile)) {
                    this.fileSystem.delete(this.journalFileBackup);
                } else {
                    this.fileSystem.rename(this.journalFileBackup, this.journalFile);
                }
            }
            if (this.fileSystem.exists(this.journalFile)) {
                try {
                    readJournal();
                    processJournal();
                    this.initialized = true;
                    return;
                } catch (IOException e) {
                    Platform platform = Platform.get();
                    platform.log(5, "DiskLruCache " + this.directory + " is corrupt: " + e.getMessage() + ", removing", e);
                    delete();
                    this.closed = false;
                } catch (Throwable th) {
                    this.closed = false;
                    throw th;
                }
            }
            rebuildJournal();
            this.initialized = true;
        }
    }

    public static DiskLruCache create(FileSystem fileSystem2, File file, int i, int i2, long j) {
        if (j <= 0) {
            throw new IllegalArgumentException("maxSize <= 0");
        } else if (i2 > 0) {
            return new DiskLruCache(fileSystem2, file, i, i2, j, new ThreadPoolExecutor(0, 1, 60, TimeUnit.SECONDS, new LinkedBlockingQueue(), Util.threadFactory("OkHttp DiskLruCache", true)));
        } else {
            throw new IllegalArgumentException("valueCount <= 0");
        }
    }

    private void readJournal() {
        e a2 = l.a(this.fileSystem.source(this.journalFile));
        try {
            String s = a2.s();
            String s2 = a2.s();
            String s3 = a2.s();
            String s4 = a2.s();
            String s5 = a2.s();
            if (!MAGIC.equals(s) || !VERSION_1.equals(s2) || !Integer.toString(this.appVersion).equals(s3) || !Integer.toString(this.valueCount).equals(s4) || !"".equals(s5)) {
                throw new IOException("unexpected journal header: [" + s + ", " + s2 + ", " + s4 + ", " + s5 + "]");
            }
            int i = 0;
            while (true) {
                try {
                    readJournalLine(a2.s());
                    i++;
                } catch (EOFException unused) {
                    this.redundantOpCount = i - this.lruEntries.size();
                    if (!a2.f()) {
                        rebuildJournal();
                    } else {
                        this.journalWriter = newJournalWriter();
                    }
                    return;
                }
            }
        } finally {
            Util.closeQuietly(a2);
        }
    }

    private d newJournalWriter() {
        return l.a(new FaultHidingSink(this.fileSystem.appendingSink(this.journalFile)) {
            /* class okhttp3.internal.cache.DiskLruCache.AnonymousClass2 */
            static final /* synthetic */ boolean $assertionsDisabled = false;

            /* access modifiers changed from: protected */
            @Override // okhttp3.internal.cache.FaultHidingSink
            public void onException(IOException iOException) {
                DiskLruCache.this.hasJournalErrors = true;
            }
        });
    }

    private void readJournalLine(String str) {
        String str2;
        int indexOf = str.indexOf(32);
        if (indexOf != -1) {
            int i = indexOf + 1;
            int indexOf2 = str.indexOf(32, i);
            if (indexOf2 == -1) {
                str2 = str.substring(i);
                if (indexOf == 6 && str.startsWith(REMOVE)) {
                    this.lruEntries.remove(str2);
                    return;
                }
            } else {
                str2 = str.substring(i, indexOf2);
            }
            Entry entry = this.lruEntries.get(str2);
            if (entry == null) {
                entry = new Entry(str2);
                this.lruEntries.put(str2, entry);
            }
            if (indexOf2 != -1 && indexOf == 5 && str.startsWith(CLEAN)) {
                String[] split = str.substring(indexOf2 + 1).split(" ");
                entry.readable = true;
                entry.currentEditor = null;
                entry.setLengths(split);
            } else if (indexOf2 == -1 && indexOf == 5 && str.startsWith(DIRTY)) {
                entry.currentEditor = new Editor(entry);
            } else if (indexOf2 != -1 || indexOf != 4 || !str.startsWith(READ)) {
                throw new IOException("unexpected journal line: " + str);
            }
        } else {
            throw new IOException("unexpected journal line: " + str);
        }
    }

    private void processJournal() {
        this.fileSystem.delete(this.journalFileTmp);
        Iterator<Entry> it = this.lruEntries.values().iterator();
        while (it.hasNext()) {
            Entry next = it.next();
            int i = 0;
            if (next.currentEditor == null) {
                while (i < this.valueCount) {
                    this.size += next.lengths[i];
                    i++;
                }
            } else {
                next.currentEditor = null;
                while (i < this.valueCount) {
                    this.fileSystem.delete(next.cleanFiles[i]);
                    this.fileSystem.delete(next.dirtyFiles[i]);
                    i++;
                }
                it.remove();
            }
        }
    }

    /* JADX INFO: finally extract failed */
    /* access modifiers changed from: package-private */
    public synchronized void rebuildJournal() {
        if (this.journalWriter != null) {
            this.journalWriter.close();
        }
        d a2 = l.a(this.fileSystem.sink(this.journalFileTmp));
        try {
            a2.b(MAGIC).i(10);
            a2.b(VERSION_1).i(10);
            a2.n((long) this.appVersion).i(10);
            a2.n((long) this.valueCount).i(10);
            a2.i(10);
            for (Entry entry : this.lruEntries.values()) {
                if (entry.currentEditor != null) {
                    a2.b(DIRTY).i(32);
                    a2.b(entry.key);
                    a2.i(10);
                } else {
                    a2.b(CLEAN).i(32);
                    a2.b(entry.key);
                    entry.writeLengths(a2);
                    a2.i(10);
                }
            }
            a2.close();
            if (this.fileSystem.exists(this.journalFile)) {
                this.fileSystem.rename(this.journalFile, this.journalFileBackup);
            }
            this.fileSystem.rename(this.journalFileTmp, this.journalFile);
            this.fileSystem.delete(this.journalFileBackup);
            this.journalWriter = newJournalWriter();
            this.hasJournalErrors = false;
            this.mostRecentRebuildFailed = false;
        } catch (Throwable th) {
            a2.close();
            throw th;
        }
    }

    public synchronized Snapshot get(String str) {
        initialize();
        checkNotClosed();
        validateKey(str);
        Entry entry = this.lruEntries.get(str);
        if (entry != null) {
            if (entry.readable) {
                Snapshot snapshot = entry.snapshot();
                if (snapshot == null) {
                    return null;
                }
                this.redundantOpCount++;
                this.journalWriter.b(READ).i(32).b(str).i(10);
                if (journalRebuildRequired()) {
                    this.executor.execute(this.cleanupRunnable);
                }
                return snapshot;
            }
        }
        return null;
    }

    public Editor edit(String str) {
        return edit(str, ANY_SEQUENCE_NUMBER);
    }

    /* access modifiers changed from: package-private */
    public synchronized Editor edit(String str, long j) {
        initialize();
        checkNotClosed();
        validateKey(str);
        Entry entry = this.lruEntries.get(str);
        if (j != ANY_SEQUENCE_NUMBER && (entry == null || entry.sequenceNumber != j)) {
            return null;
        }
        if (entry != null && entry.currentEditor != null) {
            return null;
        }
        if (this.mostRecentTrimFailed || this.mostRecentRebuildFailed) {
            this.executor.execute(this.cleanupRunnable);
            return null;
        }
        this.journalWriter.b(DIRTY).i(32).b(str).i(10);
        this.journalWriter.flush();
        if (this.hasJournalErrors) {
            return null;
        }
        if (entry == null) {
            entry = new Entry(str);
            this.lruEntries.put(str, entry);
        }
        Editor editor = new Editor(entry);
        entry.currentEditor = editor;
        return editor;
    }

    public File getDirectory() {
        return this.directory;
    }

    public synchronized long getMaxSize() {
        return this.maxSize;
    }

    public synchronized void setMaxSize(long j) {
        this.maxSize = j;
        if (this.initialized) {
            this.executor.execute(this.cleanupRunnable);
        }
    }

    public synchronized long size() {
        initialize();
        return this.size;
    }

    /* access modifiers changed from: package-private */
    public synchronized void completeEdit(Editor editor, boolean z) {
        Entry entry = editor.entry;
        if (entry.currentEditor == editor) {
            if (z && !entry.readable) {
                for (int i = 0; i < this.valueCount; i++) {
                    if (!editor.written[i]) {
                        editor.abort();
                        throw new IllegalStateException("Newly created entry didn't create value for index " + i);
                    } else if (!this.fileSystem.exists(entry.dirtyFiles[i])) {
                        editor.abort();
                        return;
                    }
                }
            }
            for (int i2 = 0; i2 < this.valueCount; i2++) {
                File file = entry.dirtyFiles[i2];
                if (!z) {
                    this.fileSystem.delete(file);
                } else if (this.fileSystem.exists(file)) {
                    File file2 = entry.cleanFiles[i2];
                    this.fileSystem.rename(file, file2);
                    long j = entry.lengths[i2];
                    long size2 = this.fileSystem.size(file2);
                    entry.lengths[i2] = size2;
                    this.size = (this.size - j) + size2;
                }
            }
            this.redundantOpCount++;
            entry.currentEditor = null;
            if (entry.readable || z) {
                entry.readable = true;
                this.journalWriter.b(CLEAN).i(32);
                this.journalWriter.b(entry.key);
                entry.writeLengths(this.journalWriter);
                this.journalWriter.i(10);
                if (z) {
                    long j2 = this.nextSequenceNumber;
                    this.nextSequenceNumber = 1 + j2;
                    entry.sequenceNumber = j2;
                }
            } else {
                this.lruEntries.remove(entry.key);
                this.journalWriter.b(REMOVE).i(32);
                this.journalWriter.b(entry.key);
                this.journalWriter.i(10);
            }
            this.journalWriter.flush();
            if (this.size > this.maxSize || journalRebuildRequired()) {
                this.executor.execute(this.cleanupRunnable);
            }
            return;
        }
        throw new IllegalStateException();
    }

    /* access modifiers changed from: package-private */
    public boolean journalRebuildRequired() {
        int i = this.redundantOpCount;
        return i >= 2000 && i >= this.lruEntries.size();
    }

    public synchronized boolean remove(String str) {
        initialize();
        checkNotClosed();
        validateKey(str);
        Entry entry = this.lruEntries.get(str);
        if (entry == null) {
            return false;
        }
        boolean removeEntry = removeEntry(entry);
        if (removeEntry && this.size <= this.maxSize) {
            this.mostRecentTrimFailed = false;
        }
        return removeEntry;
    }

    /* access modifiers changed from: package-private */
    public boolean removeEntry(Entry entry) {
        if (entry.currentEditor != null) {
            entry.currentEditor.detach();
        }
        for (int i = 0; i < this.valueCount; i++) {
            this.fileSystem.delete(entry.cleanFiles[i]);
            this.size -= entry.lengths[i];
            entry.lengths[i] = 0;
        }
        this.redundantOpCount++;
        this.journalWriter.b(REMOVE).i(32).b(entry.key).i(10);
        this.lruEntries.remove(entry.key);
        if (journalRebuildRequired()) {
            this.executor.execute(this.cleanupRunnable);
        }
        return true;
    }

    public synchronized boolean isClosed() {
        return this.closed;
    }

    private synchronized void checkNotClosed() {
        if (isClosed()) {
            throw new IllegalStateException("cache is closed");
        }
    }

    @Override // java.io.Flushable
    public synchronized void flush() {
        if (this.initialized) {
            checkNotClosed();
            trimToSize();
            this.journalWriter.flush();
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public synchronized void close() {
        if (this.initialized) {
            if (!this.closed) {
                Entry[] entryArr = (Entry[]) this.lruEntries.values().toArray(new Entry[this.lruEntries.size()]);
                for (Entry entry : entryArr) {
                    if (entry.currentEditor != null) {
                        entry.currentEditor.abort();
                    }
                }
                trimToSize();
                this.journalWriter.close();
                this.journalWriter = null;
                this.closed = true;
                return;
            }
        }
        this.closed = true;
    }

    /* access modifiers changed from: package-private */
    public void trimToSize() {
        while (this.size > this.maxSize) {
            removeEntry(this.lruEntries.values().iterator().next());
        }
        this.mostRecentTrimFailed = false;
    }

    public void delete() {
        close();
        this.fileSystem.deleteContents(this.directory);
    }

    public synchronized void evictAll() {
        initialize();
        for (Entry entry : (Entry[]) this.lruEntries.values().toArray(new Entry[this.lruEntries.size()])) {
            removeEntry(entry);
        }
        this.mostRecentTrimFailed = false;
    }

    private void validateKey(String str) {
        if (!LEGAL_KEY_PATTERN.matcher(str).matches()) {
            throw new IllegalArgumentException("keys must match regex [a-z0-9_-]{1,120}: \"" + str + "\"");
        }
    }

    public synchronized Iterator<Snapshot> snapshots() {
        initialize();
        return new Iterator<Snapshot>() {
            /* class okhttp3.internal.cache.DiskLruCache.AnonymousClass3 */
            final Iterator<Entry> delegate = new ArrayList(DiskLruCache.this.lruEntries.values()).iterator();
            Snapshot nextSnapshot;
            Snapshot removeSnapshot;

            public boolean hasNext() {
                if (this.nextSnapshot != null) {
                    return true;
                }
                synchronized (DiskLruCache.this) {
                    if (DiskLruCache.this.closed) {
                        return false;
                    }
                    while (this.delegate.hasNext()) {
                        Snapshot snapshot = this.delegate.next().snapshot();
                        if (snapshot != null) {
                            this.nextSnapshot = snapshot;
                            return true;
                        }
                    }
                    return false;
                }
            }

            @Override // java.util.Iterator
            public Snapshot next() {
                if (hasNext()) {
                    this.removeSnapshot = this.nextSnapshot;
                    this.nextSnapshot = null;
                    return this.removeSnapshot;
                }
                throw new NoSuchElementException();
            }

            public void remove() {
                Snapshot snapshot = this.removeSnapshot;
                if (snapshot != null) {
                    try {
                        DiskLruCache.this.remove(snapshot.key);
                    } catch (IOException unused) {
                    } catch (Throwable th) {
                        this.removeSnapshot = null;
                        throw th;
                    }
                    this.removeSnapshot = null;
                    return;
                }
                throw new IllegalStateException("remove() before next()");
            }
        };
    }

    public final class Snapshot implements Closeable {
        private final String key;
        private final long[] lengths;
        private final long sequenceNumber;
        private final t[] sources;

        Snapshot(String str, long j, t[] tVarArr, long[] jArr) {
            this.key = str;
            this.sequenceNumber = j;
            this.sources = tVarArr;
            this.lengths = jArr;
        }

        public String key() {
            return this.key;
        }

        public Editor edit() {
            return DiskLruCache.this.edit(this.key, this.sequenceNumber);
        }

        public t getSource(int i) {
            return this.sources[i];
        }

        public long getLength(int i) {
            return this.lengths[i];
        }

        @Override // java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            for (t tVar : this.sources) {
                Util.closeQuietly(tVar);
            }
        }
    }

    public final class Editor {
        private boolean done;
        final Entry entry;
        final boolean[] written;

        Editor(Entry entry2) {
            this.entry = entry2;
            this.written = entry2.readable ? null : new boolean[DiskLruCache.this.valueCount];
        }

        /* access modifiers changed from: package-private */
        public void detach() {
            if (this.entry.currentEditor == this) {
                for (int i = 0; i < DiskLruCache.this.valueCount; i++) {
                    try {
                        DiskLruCache.this.fileSystem.delete(this.entry.dirtyFiles[i]);
                    } catch (IOException unused) {
                    }
                }
                this.entry.currentEditor = null;
            }
        }

        public t newSource(int i) {
            synchronized (DiskLruCache.this) {
                if (this.done) {
                    throw new IllegalStateException();
                } else if (!this.entry.readable || this.entry.currentEditor != this) {
                    return null;
                } else {
                    try {
                        return DiskLruCache.this.fileSystem.source(this.entry.cleanFiles[i]);
                    } catch (FileNotFoundException unused) {
                        return null;
                    }
                }
            }
        }

        public s newSink(int i) {
            synchronized (DiskLruCache.this) {
                if (this.done) {
                    throw new IllegalStateException();
                } else if (this.entry.currentEditor != this) {
                    return l.a();
                } else {
                    if (!this.entry.readable) {
                        this.written[i] = true;
                    }
                    try {
                        return new FaultHidingSink(DiskLruCache.this.fileSystem.sink(this.entry.dirtyFiles[i])) {
                            /* class okhttp3.internal.cache.DiskLruCache.Editor.AnonymousClass1 */

                            /* access modifiers changed from: protected */
                            @Override // okhttp3.internal.cache.FaultHidingSink
                            public void onException(IOException iOException) {
                                synchronized (DiskLruCache.this) {
                                    Editor.this.detach();
                                }
                            }
                        };
                    } catch (FileNotFoundException unused) {
                        return l.a();
                    }
                }
            }
        }

        public void commit() {
            synchronized (DiskLruCache.this) {
                if (!this.done) {
                    if (this.entry.currentEditor == this) {
                        DiskLruCache.this.completeEdit(this, true);
                    }
                    this.done = true;
                } else {
                    throw new IllegalStateException();
                }
            }
        }

        public void abort() {
            synchronized (DiskLruCache.this) {
                if (!this.done) {
                    if (this.entry.currentEditor == this) {
                        DiskLruCache.this.completeEdit(this, false);
                    }
                    this.done = true;
                } else {
                    throw new IllegalStateException();
                }
            }
        }

        public void abortUnlessCommitted() {
            synchronized (DiskLruCache.this) {
                if (!this.done && this.entry.currentEditor == this) {
                    try {
                        DiskLruCache.this.completeEdit(this, false);
                    } catch (IOException unused) {
                    }
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public final class Entry {
        final File[] cleanFiles;
        Editor currentEditor;
        final File[] dirtyFiles;
        final String key;
        final long[] lengths;
        boolean readable;
        long sequenceNumber;

        Entry(String str) {
            this.key = str;
            this.lengths = new long[DiskLruCache.this.valueCount];
            this.cleanFiles = new File[DiskLruCache.this.valueCount];
            this.dirtyFiles = new File[DiskLruCache.this.valueCount];
            StringBuilder sb = new StringBuilder(str);
            sb.append('.');
            int length = sb.length();
            for (int i = 0; i < DiskLruCache.this.valueCount; i++) {
                sb.append(i);
                this.cleanFiles[i] = new File(DiskLruCache.this.directory, sb.toString());
                sb.append(".tmp");
                this.dirtyFiles[i] = new File(DiskLruCache.this.directory, sb.toString());
                sb.setLength(length);
            }
        }

        /* access modifiers changed from: package-private */
        public void setLengths(String[] strArr) {
            if (strArr.length == DiskLruCache.this.valueCount) {
                for (int i = 0; i < strArr.length; i++) {
                    try {
                        this.lengths[i] = Long.parseLong(strArr[i]);
                    } catch (NumberFormatException unused) {
                        throw invalidLengths(strArr);
                    }
                }
                return;
            }
            throw invalidLengths(strArr);
        }

        /* access modifiers changed from: package-private */
        public void writeLengths(d dVar) {
            for (long j : this.lengths) {
                dVar.i(32).n(j);
            }
        }

        private IOException invalidLengths(String[] strArr) {
            throw new IOException("unexpected journal line: " + Arrays.toString(strArr));
        }

        /* access modifiers changed from: package-private */
        public Snapshot snapshot() {
            if (Thread.holdsLock(DiskLruCache.this)) {
                t[] tVarArr = new t[DiskLruCache.this.valueCount];
                long[] jArr = (long[]) this.lengths.clone();
                int i = 0;
                for (int i2 = 0; i2 < DiskLruCache.this.valueCount; i2++) {
                    try {
                        tVarArr[i2] = DiskLruCache.this.fileSystem.source(this.cleanFiles[i2]);
                    } catch (FileNotFoundException unused) {
                        while (i < DiskLruCache.this.valueCount && tVarArr[i] != null) {
                            Util.closeQuietly(tVarArr[i]);
                            i++;
                        }
                        try {
                            DiskLruCache.this.removeEntry(this);
                            return null;
                        } catch (IOException unused2) {
                            return null;
                        }
                    }
                }
                return new Snapshot(this.key, this.sequenceNumber, tVarArr, jArr);
            }
            throw new AssertionError();
        }
    }
}
