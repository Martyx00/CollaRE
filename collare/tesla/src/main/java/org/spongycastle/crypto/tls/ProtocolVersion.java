package org.spongycastle.crypto.tls;

import org.spongycastle.util.Strings;

public final class ProtocolVersion {
    public static final ProtocolVersion DTLSv10 = new ProtocolVersion(65279, "DTLS 1.0");
    public static final ProtocolVersion DTLSv12 = new ProtocolVersion(65277, "DTLS 1.2");
    public static final ProtocolVersion SSLv3 = new ProtocolVersion(768, "SSL 3.0");
    public static final ProtocolVersion TLSv10 = new ProtocolVersion(769, "TLS 1.0");
    public static final ProtocolVersion TLSv11 = new ProtocolVersion(770, "TLS 1.1");
    public static final ProtocolVersion TLSv12 = new ProtocolVersion(771, "TLS 1.2");
    private String name;
    private int version;

    private ProtocolVersion(int i, String str) {
        this.version = i & 65535;
        this.name = str;
    }

    public int getFullVersion() {
        return this.version;
    }

    public int getMajorVersion() {
        return this.version >> 8;
    }

    public int getMinorVersion() {
        return this.version & 255;
    }

    public boolean isDTLS() {
        return getMajorVersion() == 254;
    }

    public boolean isSSL() {
        return this == SSLv3;
    }

    public boolean isTLS() {
        return getMajorVersion() == 3;
    }

    public ProtocolVersion getEquivalentTLSVersion() {
        if (!isDTLS()) {
            return this;
        }
        if (this == DTLSv10) {
            return TLSv11;
        }
        return TLSv12;
    }

    public boolean isEqualOrEarlierVersionOf(ProtocolVersion protocolVersion) {
        if (getMajorVersion() != protocolVersion.getMajorVersion()) {
            return false;
        }
        int minorVersion = protocolVersion.getMinorVersion() - getMinorVersion();
        if (isDTLS()) {
            if (minorVersion <= 0) {
                return true;
            }
        } else if (minorVersion >= 0) {
            return true;
        }
        return false;
    }

    public boolean isLaterVersionOf(ProtocolVersion protocolVersion) {
        if (getMajorVersion() != protocolVersion.getMajorVersion()) {
            return false;
        }
        int minorVersion = protocolVersion.getMinorVersion() - getMinorVersion();
        if (isDTLS()) {
            if (minorVersion > 0) {
                return true;
            }
        } else if (minorVersion < 0) {
            return true;
        }
        return false;
    }

    public boolean equals(Object obj) {
        return this == obj || ((obj instanceof ProtocolVersion) && equals((ProtocolVersion) obj));
    }

    public boolean equals(ProtocolVersion protocolVersion) {
        return protocolVersion != null && this.version == protocolVersion.version;
    }

    public int hashCode() {
        return this.version;
    }

    public static ProtocolVersion get(int i, int i2) {
        if (i == 3) {
            switch (i2) {
                case 0:
                    return SSLv3;
                case 1:
                    return TLSv10;
                case 2:
                    return TLSv11;
                case 3:
                    return TLSv12;
                default:
                    return getUnknownVersion(i, i2, "TLS");
            }
        } else if (i == 254) {
            switch (i2) {
                case 253:
                    return DTLSv12;
                case 254:
                    throw new TlsFatalAlert(47);
                case 255:
                    return DTLSv10;
                default:
                    return getUnknownVersion(i, i2, "DTLS");
            }
        } else {
            throw new TlsFatalAlert(47);
        }
    }

    public String toString() {
        return this.name;
    }

    private static ProtocolVersion getUnknownVersion(int i, int i2, String str) {
        TlsUtils.checkUint8(i);
        TlsUtils.checkUint8(i2);
        int i3 = (i << 8) | i2;
        String upperCase = Strings.toUpperCase(Integer.toHexString(65536 | i3).substring(1));
        return new ProtocolVersion(i3, str + " 0x" + upperCase);
    }
}
