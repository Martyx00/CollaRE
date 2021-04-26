package io.realm.internal;

import io.realm.internal.OsRealmConfig;
import io.realm.internal.OsResults;
import io.realm.internal.android.AndroidRealmNotifier;
import io.realm.r;
import java.io.Closeable;
import java.io.File;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Keep
public final class OsSharedRealm implements i, Closeable {
    public static final byte FILE_EXCEPTION_INCOMPATIBLE_SYNC_FILE = 7;
    public static final byte FILE_EXCEPTION_KIND_ACCESS_ERROR = 0;
    public static final byte FILE_EXCEPTION_KIND_BAD_HISTORY = 1;
    public static final byte FILE_EXCEPTION_KIND_EXISTS = 3;
    public static final byte FILE_EXCEPTION_KIND_FORMAT_UPGRADE_REQUIRED = 6;
    public static final byte FILE_EXCEPTION_KIND_INCOMPATIBLE_LOCK_FILE = 5;
    public static final byte FILE_EXCEPTION_KIND_NOT_FOUND = 4;
    public static final byte FILE_EXCEPTION_KIND_PERMISSION_DENIED = 2;
    private static final long nativeFinalizerPtr = nativeGetFinalizerPtr();
    private static final List<OsSharedRealm> sharedRealmsUnderConstruction = new CopyOnWriteArrayList();
    private static volatile File temporaryDirectory;
    public final a capabilities;
    public final h context;
    final List<WeakReference<OsResults.a>> iterators = new ArrayList();
    private final long nativePtr;
    private final OsRealmConfig osRealmConfig;
    private final List<WeakReference<l>> pendingRows = new CopyOnWriteArrayList();
    public final RealmNotifier realmNotifier;
    private final OsSchemaInfo schemaInfo;
    private final List<OsSharedRealm> tempSharedRealmsForCallback = new ArrayList();

    @Keep
    public interface InitializationCallback {
        void onInit(OsSharedRealm osSharedRealm);
    }

    @Keep
    public interface MigrationCallback {
        void onMigrationNeeded(OsSharedRealm osSharedRealm, long j, long j2);
    }

    @Keep
    public interface SchemaChangedCallback {
        void onSchemaChanged();
    }

    private static native void nativeBeginTransaction(long j);

    private static native void nativeCancelTransaction(long j);

    private static native void nativeCloseSharedRealm(long j);

    private static native void nativeCommitTransaction(long j);

    private static native boolean nativeCompact(long j);

    private static native long nativeCreateTable(long j, String str);

    private static native long nativeCreateTableWithPrimaryKeyField(long j, String str, String str2, boolean z, boolean z2);

    private static native int nativeGetClassPrivileges(long j, String str);

    private static native long nativeGetFinalizerPtr();

    private static native int nativeGetObjectPrivileges(long j, long j2);

    private static native int nativeGetRealmPrivileges(long j);

    private static native long nativeGetSchemaInfo(long j);

    private static native long nativeGetSharedRealm(long j, RealmNotifier realmNotifier2);

    private static native long nativeGetTable(long j, String str);

    private static native String nativeGetTableName(long j, int i);

    private static native long[] nativeGetVersionID(long j);

    private static native boolean nativeHasTable(long j, String str);

    private static native void nativeInit(String str);

    private static native boolean nativeIsAutoRefresh(long j);

    private static native boolean nativeIsClosed(long j);

    private static native boolean nativeIsEmpty(long j);

    private static native boolean nativeIsInTransaction(long j);

    private static native boolean nativeIsPartial(long j);

    private static native void nativeRefresh(long j);

    private static native void nativeRegisterSchemaChangedCallback(long j, SchemaChangedCallback schemaChangedCallback);

    private static native void nativeRenameTable(long j, String str, String str2);

    private static native void nativeSetAutoRefresh(long j, boolean z);

    private static native long nativeSize(long j);

    private static native void nativeStopWaitForChange(long j);

    private static native boolean nativeWaitForChange(long j);

    private static native void nativeWriteCopy(long j, String str, byte[] bArr);

    public static class a implements Comparable<a> {

        /* renamed from: a  reason: collision with root package name */
        public final long f6227a;

        /* renamed from: b  reason: collision with root package name */
        public final long f6228b;

        a(long j, long j2) {
            this.f6227a = j;
            this.f6228b = j2;
        }

        /* renamed from: a */
        public int compareTo(a aVar) {
            if (aVar != null) {
                long j = this.f6227a;
                long j2 = aVar.f6227a;
                if (j > j2) {
                    return 1;
                }
                return j < j2 ? -1 : 0;
            }
            throw new IllegalArgumentException("Version cannot be compared to a null value.");
        }

        public String toString() {
            return "VersionID{version=" + this.f6227a + ", index=" + this.f6228b + '}';
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            a aVar = (a) obj;
            if (this.f6227a == aVar.f6227a && this.f6228b == aVar.f6228b) {
                return true;
            }
            return false;
        }

        public int hashCode() {
            long j = this.f6227a;
            long j2 = this.f6228b;
            return (((super.hashCode() * 31) + ((int) (j ^ (j >>> 32)))) * 31) + ((int) (j2 ^ (j2 >>> 32)));
        }
    }

    private OsSharedRealm(OsRealmConfig osRealmConfig2) {
        io.realm.internal.android.a aVar = new io.realm.internal.android.a();
        AndroidRealmNotifier androidRealmNotifier = new AndroidRealmNotifier(this, aVar);
        this.context = osRealmConfig2.c();
        sharedRealmsUnderConstruction.add(this);
        try {
            this.nativePtr = nativeGetSharedRealm(osRealmConfig2.getNativePtr(), androidRealmNotifier);
            this.tempSharedRealmsForCallback.clear();
            sharedRealmsUnderConstruction.remove(this);
            this.osRealmConfig = osRealmConfig2;
            this.schemaInfo = new OsSchemaInfo(nativeGetSchemaInfo(this.nativePtr), this);
            this.context.a(this);
            this.capabilities = aVar;
            this.realmNotifier = androidRealmNotifier;
            nativeSetAutoRefresh(this.nativePtr, aVar.a());
        } catch (Throwable th) {
            this.tempSharedRealmsForCallback.clear();
            sharedRealmsUnderConstruction.remove(this);
            throw th;
        }
    }

    private OsSharedRealm(long j, OsRealmConfig osRealmConfig2) {
        this.nativePtr = j;
        this.osRealmConfig = osRealmConfig2;
        this.schemaInfo = new OsSchemaInfo(nativeGetSchemaInfo(this.nativePtr), this);
        this.context = osRealmConfig2.c();
        this.context.a(this);
        this.capabilities = new io.realm.internal.android.a();
        this.realmNotifier = null;
        boolean z = false;
        nativeSetAutoRefresh(this.nativePtr, false);
        Iterator<OsSharedRealm> it = sharedRealmsUnderConstruction.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            OsSharedRealm next = it.next();
            if (next.context == osRealmConfig2.c()) {
                z = true;
                next.tempSharedRealmsForCallback.add(this);
                break;
            }
        }
        if (!z) {
            throw new IllegalStateException("Cannot find the parent 'OsSharedRealm' which is under construction.");
        }
    }

    public static OsSharedRealm getInstance(r rVar) {
        return getInstance(new OsRealmConfig.a(rVar));
    }

    public static OsSharedRealm getInstance(OsRealmConfig.a aVar) {
        OsRealmConfig a2 = aVar.a();
        j.a().a(a2);
        return new OsSharedRealm(a2);
    }

    public static void initialize(File file) {
        if (temporaryDirectory == null) {
            String absolutePath = file.getAbsolutePath();
            if (file.isDirectory() || file.mkdirs() || file.isDirectory()) {
                if (!absolutePath.endsWith("/")) {
                    absolutePath = absolutePath + "/";
                }
                nativeInit(absolutePath);
                temporaryDirectory = file;
                return;
            }
            throw new f("failed to create temporary directory: " + absolutePath);
        }
    }

    public static File getTemporaryDirectory() {
        return temporaryDirectory;
    }

    public void beginTransaction() {
        detachIterators();
        executePendingRowQueries();
        nativeBeginTransaction(this.nativePtr);
    }

    public void commitTransaction() {
        nativeCommitTransaction(this.nativePtr);
    }

    public void cancelTransaction() {
        nativeCancelTransaction(this.nativePtr);
    }

    public boolean isInTransaction() {
        return nativeIsInTransaction(this.nativePtr);
    }

    public boolean hasTable(String str) {
        return nativeHasTable(this.nativePtr, str);
    }

    public Table getTable(String str) {
        return new Table(this, nativeGetTable(this.nativePtr, str));
    }

    public Table createTable(String str) {
        return new Table(this, nativeCreateTable(this.nativePtr, str));
    }

    public Table createTableWithPrimaryKey(String str, String str2, boolean z, boolean z2) {
        return new Table(this, nativeCreateTableWithPrimaryKeyField(this.nativePtr, str, str2, z, z2));
    }

    public void renameTable(String str, String str2) {
        nativeRenameTable(this.nativePtr, str, str2);
    }

    public String getTableName(int i) {
        return nativeGetTableName(this.nativePtr, i);
    }

    public long size() {
        return nativeSize(this.nativePtr);
    }

    public String getPath() {
        return this.osRealmConfig.a().m();
    }

    public boolean isEmpty() {
        return nativeIsEmpty(this.nativePtr);
    }

    public void refresh() {
        nativeRefresh(this.nativePtr);
    }

    public a getVersionID() {
        long[] nativeGetVersionID = nativeGetVersionID(this.nativePtr);
        return new a(nativeGetVersionID[0], nativeGetVersionID[1]);
    }

    public boolean isClosed() {
        return nativeIsClosed(this.nativePtr);
    }

    public void writeCopy(File file, byte[] bArr) {
        if (!file.isFile() || !file.exists()) {
            nativeWriteCopy(this.nativePtr, file.getAbsolutePath(), bArr);
            return;
        }
        throw new IllegalArgumentException("The destination file must not exist");
    }

    public boolean waitForChange() {
        return nativeWaitForChange(this.nativePtr);
    }

    public void stopWaitForChange() {
        nativeStopWaitForChange(this.nativePtr);
    }

    public boolean compact() {
        return nativeCompact(this.nativePtr);
    }

    public void setAutoRefresh(boolean z) {
        this.capabilities.a(null);
        nativeSetAutoRefresh(this.nativePtr, z);
    }

    public boolean isAutoRefresh() {
        return nativeIsAutoRefresh(this.nativePtr);
    }

    public r getConfiguration() {
        return this.osRealmConfig.a();
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        RealmNotifier realmNotifier2 = this.realmNotifier;
        if (realmNotifier2 != null) {
            realmNotifier2.close();
        }
        synchronized (this.context) {
            nativeCloseSharedRealm(this.nativePtr);
        }
    }

    @Override // io.realm.internal.i
    public long getNativePtr() {
        return this.nativePtr;
    }

    @Override // io.realm.internal.i
    public long getNativeFinalizerPtr() {
        return nativeFinalizerPtr;
    }

    public OsSchemaInfo getSchemaInfo() {
        return this.schemaInfo;
    }

    public void registerSchemaChangedCallback(SchemaChangedCallback schemaChangedCallback) {
        nativeRegisterSchemaChangedCallback(this.nativePtr, schemaChangedCallback);
    }

    public boolean isPartial() {
        return nativeIsPartial(this.nativePtr);
    }

    public boolean isSyncRealm() {
        return this.osRealmConfig.b() != null;
    }

    /* access modifiers changed from: package-private */
    public void addIterator(OsResults.a aVar) {
        this.iterators.add(new WeakReference<>(aVar));
    }

    private void detachIterators() {
        for (WeakReference<OsResults.a> weakReference : this.iterators) {
            OsResults.a aVar = weakReference.get();
            if (aVar != null) {
                aVar.a();
            }
        }
        this.iterators.clear();
    }

    /* access modifiers changed from: package-private */
    public void invalidateIterators() {
        for (WeakReference<OsResults.a> weakReference : this.iterators) {
            OsResults.a aVar = weakReference.get();
            if (aVar != null) {
                aVar.b();
            }
        }
        this.iterators.clear();
    }

    /* access modifiers changed from: package-private */
    public void addPendingRow(l lVar) {
        this.pendingRows.add(new WeakReference<>(lVar));
    }

    /* access modifiers changed from: package-private */
    public void removePendingRow(l lVar) {
        for (WeakReference<l> weakReference : this.pendingRows) {
            l lVar2 = weakReference.get();
            if (lVar2 == null || lVar2 == lVar) {
                this.pendingRows.remove(weakReference);
            }
        }
    }

    private void executePendingRowQueries() {
        for (WeakReference<l> weakReference : this.pendingRows) {
            l lVar = weakReference.get();
            if (lVar != null) {
                lVar.e();
            }
        }
        this.pendingRows.clear();
    }

    private static void runMigrationCallback(long j, OsRealmConfig osRealmConfig2, MigrationCallback migrationCallback, long j2) {
        migrationCallback.onMigrationNeeded(new OsSharedRealm(j, osRealmConfig2), j2, osRealmConfig2.a().d());
    }

    private static void runInitializationCallback(long j, OsRealmConfig osRealmConfig2, InitializationCallback initializationCallback) {
        initializationCallback.onInit(new OsSharedRealm(j, osRealmConfig2));
    }
}
