package io.realm.internal;

import io.realm.RealmFieldType;
import java.util.Locale;
import org.spongycastle.crypto.tls.CipherSuite;

public class Property implements i {

    /* renamed from: b  reason: collision with root package name */
    private static final long f6229b = nativeGetFinalizerPtr();

    /* renamed from: a  reason: collision with root package name */
    private long f6230a;

    static native long nativeCreateComputedLinkProperty(String str, String str2, String str3);

    static native long nativeCreatePersistedLinkProperty(String str, int i, String str2);

    static native long nativeCreatePersistedProperty(String str, int i, boolean z, boolean z2);

    private static native long nativeGetColumnIndex(long j);

    private static native long nativeGetFinalizerPtr();

    private static native String nativeGetLinkedObjectName(long j);

    private static native int nativeGetType(long j);

    Property(long j) {
        this.f6230a = j;
        h.f6281a.a(this);
    }

    static int a(RealmFieldType realmFieldType, boolean z) {
        int i = 1;
        int i2 = 0;
        switch (realmFieldType) {
            case OBJECT:
                return 71;
            case LIST:
                return CipherSuite.TLS_DHE_DSS_WITH_CAMELLIA_256_CBC_SHA;
            case LINKING_OBJECTS:
                return CipherSuite.TLS_DHE_RSA_WITH_CAMELLIA_256_CBC_SHA;
            case INTEGER:
                i = 0;
                break;
            case BOOLEAN:
                break;
            case STRING:
                i = 2;
                break;
            case BINARY:
                i = 3;
                break;
            case DATE:
                i = 4;
                break;
            case FLOAT:
                i = 5;
                break;
            case DOUBLE:
                i = 6;
                break;
            case INTEGER_LIST:
                i = 128;
                break;
            case BOOLEAN_LIST:
                i = 129;
                break;
            case STRING_LIST:
                i = 130;
                break;
            case BINARY_LIST:
                i = 131;
                break;
            case DATE_LIST:
                i = CipherSuite.TLS_RSA_WITH_CAMELLIA_256_CBC_SHA;
                break;
            case FLOAT_LIST:
                i = CipherSuite.TLS_DH_DSS_WITH_CAMELLIA_256_CBC_SHA;
                break;
            case DOUBLE_LIST:
                i = CipherSuite.TLS_DH_RSA_WITH_CAMELLIA_256_CBC_SHA;
                break;
            default:
                throw new IllegalArgumentException(String.format(Locale.US, "Unsupported filed type: '%s'.", realmFieldType.name()));
        }
        if (!z) {
            i2 = 64;
        }
        return i | i2;
    }

    private static RealmFieldType a(int i) {
        int i2 = i & -65;
        switch (i2) {
            case 0:
                return RealmFieldType.INTEGER;
            case 1:
                return RealmFieldType.BOOLEAN;
            case 2:
                return RealmFieldType.STRING;
            case 3:
                return RealmFieldType.BINARY;
            case 4:
                return RealmFieldType.DATE;
            case 5:
                return RealmFieldType.FLOAT;
            case 6:
                return RealmFieldType.DOUBLE;
            case 7:
                return RealmFieldType.OBJECT;
            default:
                switch (i2) {
                    case 128:
                        return RealmFieldType.INTEGER_LIST;
                    case 129:
                        return RealmFieldType.BOOLEAN_LIST;
                    case 130:
                        return RealmFieldType.STRING_LIST;
                    case 131:
                        return RealmFieldType.BINARY_LIST;
                    case CipherSuite.TLS_RSA_WITH_CAMELLIA_256_CBC_SHA /*{ENCODED_INT: 132}*/:
                        return RealmFieldType.DATE_LIST;
                    case CipherSuite.TLS_DH_DSS_WITH_CAMELLIA_256_CBC_SHA /*{ENCODED_INT: 133}*/:
                        return RealmFieldType.FLOAT_LIST;
                    case CipherSuite.TLS_DH_RSA_WITH_CAMELLIA_256_CBC_SHA /*{ENCODED_INT: 134}*/:
                        return RealmFieldType.DOUBLE_LIST;
                    case CipherSuite.TLS_DHE_DSS_WITH_CAMELLIA_256_CBC_SHA /*{ENCODED_INT: 135}*/:
                        return RealmFieldType.LIST;
                    case CipherSuite.TLS_DHE_RSA_WITH_CAMELLIA_256_CBC_SHA /*{ENCODED_INT: 136}*/:
                        return RealmFieldType.LINKING_OBJECTS;
                    default:
                        throw new IllegalArgumentException(String.format(Locale.US, "Unsupported property type: '%d'", Integer.valueOf(i)));
                }
        }
    }

    public RealmFieldType a() {
        return a(nativeGetType(this.f6230a));
    }

    public String b() {
        return nativeGetLinkedObjectName(this.f6230a);
    }

    public long c() {
        return nativeGetColumnIndex(this.f6230a);
    }

    @Override // io.realm.internal.i
    public long getNativePtr() {
        return this.f6230a;
    }

    @Override // io.realm.internal.i
    public long getNativeFinalizerPtr() {
        return f6229b;
    }
}
