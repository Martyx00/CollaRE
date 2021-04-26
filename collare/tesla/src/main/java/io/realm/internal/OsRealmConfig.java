package io.realm.internal;

import io.realm.CompactOnLaunchCallback;
import io.realm.internal.OsSharedRealm;
import io.realm.log.RealmLog;
import io.realm.r;
import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

public class OsRealmConfig implements i {

    /* renamed from: a  reason: collision with root package name */
    private static final long f6198a = nativeGetFinalizerPtr();

    /* renamed from: b  reason: collision with root package name */
    private final r f6199b;

    /* renamed from: c  reason: collision with root package name */
    private final URI f6200c;

    /* renamed from: d  reason: collision with root package name */
    private final long f6201d;
    private final h e;
    private final CompactOnLaunchCallback f;
    private final OsSharedRealm.MigrationCallback g;
    private final OsSharedRealm.InitializationCallback h;

    private static native long nativeCreate(String str, String str2, boolean z, boolean z2);

    private static native String nativeCreateAndSetSyncConfig(long j, String str, String str2, String str3, String str4, boolean z, byte b2, String str5, String str6, String[] strArr);

    private static native void nativeEnableChangeNotification(long j, boolean z);

    private static native long nativeGetFinalizerPtr();

    private static native void nativeSetCompactOnLaunchCallback(long j, CompactOnLaunchCallback compactOnLaunchCallback);

    private static native void nativeSetEncryptionKey(long j, byte[] bArr);

    private static native void nativeSetInMemory(long j, boolean z);

    private native void nativeSetInitializationCallback(long j, OsSharedRealm.InitializationCallback initializationCallback);

    private native void nativeSetSchemaConfig(long j, byte b2, long j2, long j3, OsSharedRealm.MigrationCallback migrationCallback);

    private static native void nativeSetSyncConfigSslSettings(long j, boolean z, String str);

    public enum b {
        FULL(0),
        MEM_ONLY(1);
        

        /* renamed from: c  reason: collision with root package name */
        final int f6209c;

        private b(int i) {
            this.f6209c = i;
        }
    }

    public enum c {
        SCHEMA_MODE_AUTOMATIC((byte) 0),
        SCHEMA_MODE_IMMUTABLE((byte) 1),
        SCHEMA_MODE_READONLY((byte) 2),
        SCHEMA_MODE_RESET_FILE((byte) 3),
        SCHEMA_MODE_ADDITIVE((byte) 4),
        SCHEMA_MODE_MANUAL((byte) 5);
        
        final byte g;

        private c(byte b2) {
            this.g = b2;
        }

        public byte a() {
            return this.g;
        }
    }

    public static class a {

        /* renamed from: a  reason: collision with root package name */
        private r f6202a;

        /* renamed from: b  reason: collision with root package name */
        private OsSchemaInfo f6203b = null;

        /* renamed from: c  reason: collision with root package name */
        private OsSharedRealm.MigrationCallback f6204c = null;

        /* renamed from: d  reason: collision with root package name */
        private OsSharedRealm.InitializationCallback f6205d = null;
        private boolean e = false;
        private String f = "";

        public a(r rVar) {
            this.f6202a = rVar;
        }

        public a a(OsSchemaInfo osSchemaInfo) {
            this.f6203b = osSchemaInfo;
            return this;
        }

        public a a(OsSharedRealm.MigrationCallback migrationCallback) {
            this.f6204c = migrationCallback;
            return this;
        }

        public a a(OsSharedRealm.InitializationCallback initializationCallback) {
            this.f6205d = initializationCallback;
            return this;
        }

        public a a(boolean z) {
            this.e = z;
            return this;
        }

        /* access modifiers changed from: package-private */
        public OsRealmConfig a() {
            return new OsRealmConfig(this.f6202a, this.f, this.e, this.f6203b, this.f6204c, this.f6205d);
        }

        public a a(File file) {
            this.f = file.getAbsolutePath();
            return this;
        }
    }

    private OsRealmConfig(r rVar, String str, boolean z, OsSchemaInfo osSchemaInfo, OsSharedRealm.MigrationCallback migrationCallback, OsSharedRealm.InitializationCallback initializationCallback) {
        long j;
        this.e = new h();
        this.f6199b = rVar;
        boolean z2 = true;
        this.f6201d = nativeCreate(rVar.m(), str, false, true);
        h.f6281a.a(this);
        Object[] b2 = j.a().b(this.f6199b);
        String str2 = (String) b2[0];
        String str3 = (String) b2[1];
        String str4 = (String) b2[2];
        String str5 = (String) b2[3];
        boolean equals = Boolean.TRUE.equals(b2[4]);
        String str6 = (String) b2[5];
        Byte b3 = (Byte) b2[6];
        boolean equals2 = Boolean.TRUE.equals(b2[7]);
        String str7 = (String) b2[8];
        String str8 = (String) b2[9];
        Map map = (Map) b2[10];
        String[] strArr = new String[(map != null ? map.size() * 2 : 0)];
        if (map != null) {
            int i = 0;
            for (Map.Entry entry : map.entrySet()) {
                strArr[i] = (String) entry.getKey();
                strArr[i + 1] = (String) entry.getValue();
                i += 2;
            }
        }
        byte[] c2 = rVar.c();
        if (c2 != null) {
            nativeSetEncryptionKey(this.f6201d, c2);
        }
        nativeSetInMemory(this.f6201d, rVar.g() != b.MEM_ONLY ? false : z2);
        nativeEnableChangeNotification(this.f6201d, z);
        c cVar = c.SCHEMA_MODE_MANUAL;
        if (rVar.p()) {
            cVar = c.SCHEMA_MODE_IMMUTABLE;
        } else if (rVar.o()) {
            cVar = c.SCHEMA_MODE_READONLY;
        } else if (str3 != null) {
            cVar = c.SCHEMA_MODE_ADDITIVE;
        } else if (rVar.f()) {
            cVar = c.SCHEMA_MODE_RESET_FILE;
        }
        long d2 = rVar.d();
        if (osSchemaInfo == null) {
            j = 0;
        } else {
            j = osSchemaInfo.getNativePtr();
        }
        this.g = migrationCallback;
        nativeSetSchemaConfig(this.f6201d, cVar.a(), d2, j, migrationCallback);
        this.f = rVar.l();
        CompactOnLaunchCallback compactOnLaunchCallback = this.f;
        if (compactOnLaunchCallback != null) {
            nativeSetCompactOnLaunchCallback(this.f6201d, compactOnLaunchCallback);
        }
        this.h = initializationCallback;
        if (initializationCallback != null) {
            nativeSetInitializationCallback(this.f6201d, initializationCallback);
        }
        URI uri = null;
        if (str3 != null) {
            try {
                uri = new URI(nativeCreateAndSetSyncConfig(this.f6201d, str3, str4, str2, str5, equals2, b3.byteValue(), str7, str8, strArr));
            } catch (URISyntaxException e2) {
                RealmLog.b(e2, "Cannot create a URI from the Realm URL address", new Object[0]);
            }
            nativeSetSyncConfigSslSettings(this.f6201d, equals, str6);
        }
        this.f6200c = uri;
    }

    @Override // io.realm.internal.i
    public long getNativePtr() {
        return this.f6201d;
    }

    @Override // io.realm.internal.i
    public long getNativeFinalizerPtr() {
        return f6198a;
    }

    public r a() {
        return this.f6199b;
    }

    public URI b() {
        return this.f6200c;
    }

    /* access modifiers changed from: package-private */
    public h c() {
        return this.e;
    }
}
