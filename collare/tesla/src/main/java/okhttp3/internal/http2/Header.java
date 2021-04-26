package okhttp3.internal.http2;

import c.f;
import okhttp3.Headers;
import okhttp3.internal.Util;

public final class Header {
    public static final f PSEUDO_PREFIX = f.a(":");
    public static final f RESPONSE_STATUS = f.a(RESPONSE_STATUS_UTF8);
    public static final String RESPONSE_STATUS_UTF8 = ":status";
    public static final f TARGET_AUTHORITY = f.a(TARGET_AUTHORITY_UTF8);
    public static final String TARGET_AUTHORITY_UTF8 = ":authority";
    public static final f TARGET_METHOD = f.a(TARGET_METHOD_UTF8);
    public static final String TARGET_METHOD_UTF8 = ":method";
    public static final f TARGET_PATH = f.a(TARGET_PATH_UTF8);
    public static final String TARGET_PATH_UTF8 = ":path";
    public static final f TARGET_SCHEME = f.a(TARGET_SCHEME_UTF8);
    public static final String TARGET_SCHEME_UTF8 = ":scheme";
    final int hpackSize;
    public final f name;
    public final f value;

    /* access modifiers changed from: package-private */
    public interface Listener {
        void onHeaders(Headers headers);
    }

    public Header(String str, String str2) {
        this(f.a(str), f.a(str2));
    }

    public Header(f fVar, String str) {
        this(fVar, f.a(str));
    }

    public Header(f fVar, f fVar2) {
        this.name = fVar;
        this.value = fVar2;
        this.hpackSize = fVar.h() + 32 + fVar2.h();
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Header)) {
            return false;
        }
        Header header = (Header) obj;
        if (!this.name.equals(header.name) || !this.value.equals(header.value)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return ((527 + this.name.hashCode()) * 31) + this.value.hashCode();
    }

    public String toString() {
        return Util.format("%s: %s", this.name.a(), this.value.a());
    }
}
