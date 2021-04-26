package okhttp3;

import com.google.android.gms.common.api.Api;
import java.util.concurrent.TimeUnit;
import okhttp3.internal.http.HttpHeaders;

public final class CacheControl {
    public static final CacheControl FORCE_CACHE = new Builder().onlyIfCached().maxStale(Api.BaseClientBuilder.API_PRIORITY_OTHER, TimeUnit.SECONDS).build();
    public static final CacheControl FORCE_NETWORK = new Builder().noCache().build();
    String headerValue;
    private final boolean immutable;
    private final boolean isPrivate;
    private final boolean isPublic;
    private final int maxAgeSeconds;
    private final int maxStaleSeconds;
    private final int minFreshSeconds;
    private final boolean mustRevalidate;
    private final boolean noCache;
    private final boolean noStore;
    private final boolean noTransform;
    private final boolean onlyIfCached;
    private final int sMaxAgeSeconds;

    private CacheControl(boolean z, boolean z2, int i, int i2, boolean z3, boolean z4, boolean z5, int i3, int i4, boolean z6, boolean z7, boolean z8, String str) {
        this.noCache = z;
        this.noStore = z2;
        this.maxAgeSeconds = i;
        this.sMaxAgeSeconds = i2;
        this.isPrivate = z3;
        this.isPublic = z4;
        this.mustRevalidate = z5;
        this.maxStaleSeconds = i3;
        this.minFreshSeconds = i4;
        this.onlyIfCached = z6;
        this.noTransform = z7;
        this.immutable = z8;
        this.headerValue = str;
    }

    CacheControl(Builder builder) {
        this.noCache = builder.noCache;
        this.noStore = builder.noStore;
        this.maxAgeSeconds = builder.maxAgeSeconds;
        this.sMaxAgeSeconds = -1;
        this.isPrivate = false;
        this.isPublic = false;
        this.mustRevalidate = false;
        this.maxStaleSeconds = builder.maxStaleSeconds;
        this.minFreshSeconds = builder.minFreshSeconds;
        this.onlyIfCached = builder.onlyIfCached;
        this.noTransform = builder.noTransform;
        this.immutable = builder.immutable;
    }

    public boolean noCache() {
        return this.noCache;
    }

    public boolean noStore() {
        return this.noStore;
    }

    public int maxAgeSeconds() {
        return this.maxAgeSeconds;
    }

    public int sMaxAgeSeconds() {
        return this.sMaxAgeSeconds;
    }

    public boolean isPrivate() {
        return this.isPrivate;
    }

    public boolean isPublic() {
        return this.isPublic;
    }

    public boolean mustRevalidate() {
        return this.mustRevalidate;
    }

    public int maxStaleSeconds() {
        return this.maxStaleSeconds;
    }

    public int minFreshSeconds() {
        return this.minFreshSeconds;
    }

    public boolean onlyIfCached() {
        return this.onlyIfCached;
    }

    public boolean noTransform() {
        return this.noTransform;
    }

    public boolean immutable() {
        return this.immutable;
    }

    public static CacheControl parse(Headers headers) {
        int i;
        String str;
        Headers headers2 = headers;
        int size = headers.size();
        int i2 = 0;
        boolean z = true;
        String str2 = null;
        boolean z2 = false;
        boolean z3 = false;
        int i3 = -1;
        int i4 = -1;
        boolean z4 = false;
        boolean z5 = false;
        boolean z6 = false;
        int i5 = -1;
        int i6 = -1;
        boolean z7 = false;
        boolean z8 = false;
        boolean z9 = false;
        while (i2 < size) {
            String name = headers2.name(i2);
            String value = headers2.value(i2);
            if (name.equalsIgnoreCase("Cache-Control")) {
                if (str2 != null) {
                    z = false;
                } else {
                    str2 = value;
                }
            } else if (name.equalsIgnoreCase("Pragma")) {
                z = false;
            } else {
                i2++;
                headers2 = headers;
            }
            for (int i7 = 0; i7 < value.length(); i7 = i) {
                int skipUntil = HttpHeaders.skipUntil(value, i7, "=,;");
                String trim = value.substring(i7, skipUntil).trim();
                if (skipUntil == value.length() || value.charAt(skipUntil) == ',' || value.charAt(skipUntil) == ';') {
                    i = skipUntil + 1;
                    str = null;
                } else {
                    int skipWhitespace = HttpHeaders.skipWhitespace(value, skipUntil + 1);
                    if (skipWhitespace >= value.length() || value.charAt(skipWhitespace) != '\"') {
                        i = HttpHeaders.skipUntil(value, skipWhitespace, ",;");
                        str = value.substring(skipWhitespace, i).trim();
                    } else {
                        int i8 = skipWhitespace + 1;
                        int skipUntil2 = HttpHeaders.skipUntil(value, i8, "\"");
                        str = value.substring(i8, skipUntil2);
                        i = skipUntil2 + 1;
                    }
                }
                if ("no-cache".equalsIgnoreCase(trim)) {
                    z2 = true;
                } else if ("no-store".equalsIgnoreCase(trim)) {
                    z3 = true;
                } else if ("max-age".equalsIgnoreCase(trim)) {
                    i3 = HttpHeaders.parseSeconds(str, -1);
                } else if ("s-maxage".equalsIgnoreCase(trim)) {
                    i4 = HttpHeaders.parseSeconds(str, -1);
                } else if ("private".equalsIgnoreCase(trim)) {
                    z4 = true;
                } else if ("public".equalsIgnoreCase(trim)) {
                    z5 = true;
                } else if ("must-revalidate".equalsIgnoreCase(trim)) {
                    z6 = true;
                } else if ("max-stale".equalsIgnoreCase(trim)) {
                    i5 = HttpHeaders.parseSeconds(str, Api.BaseClientBuilder.API_PRIORITY_OTHER);
                } else if ("min-fresh".equalsIgnoreCase(trim)) {
                    i6 = HttpHeaders.parseSeconds(str, -1);
                } else if ("only-if-cached".equalsIgnoreCase(trim)) {
                    z7 = true;
                } else if ("no-transform".equalsIgnoreCase(trim)) {
                    z8 = true;
                } else if ("immutable".equalsIgnoreCase(trim)) {
                    z9 = true;
                }
            }
            i2++;
            headers2 = headers;
        }
        return new CacheControl(z2, z3, i3, i4, z4, z5, z6, i5, i6, z7, z8, z9, !z ? null : str2);
    }

    public String toString() {
        String str = this.headerValue;
        if (str != null) {
            return str;
        }
        String headerValue2 = headerValue();
        this.headerValue = headerValue2;
        return headerValue2;
    }

    private String headerValue() {
        StringBuilder sb = new StringBuilder();
        if (this.noCache) {
            sb.append("no-cache, ");
        }
        if (this.noStore) {
            sb.append("no-store, ");
        }
        if (this.maxAgeSeconds != -1) {
            sb.append("max-age=");
            sb.append(this.maxAgeSeconds);
            sb.append(", ");
        }
        if (this.sMaxAgeSeconds != -1) {
            sb.append("s-maxage=");
            sb.append(this.sMaxAgeSeconds);
            sb.append(", ");
        }
        if (this.isPrivate) {
            sb.append("private, ");
        }
        if (this.isPublic) {
            sb.append("public, ");
        }
        if (this.mustRevalidate) {
            sb.append("must-revalidate, ");
        }
        if (this.maxStaleSeconds != -1) {
            sb.append("max-stale=");
            sb.append(this.maxStaleSeconds);
            sb.append(", ");
        }
        if (this.minFreshSeconds != -1) {
            sb.append("min-fresh=");
            sb.append(this.minFreshSeconds);
            sb.append(", ");
        }
        if (this.onlyIfCached) {
            sb.append("only-if-cached, ");
        }
        if (this.noTransform) {
            sb.append("no-transform, ");
        }
        if (this.immutable) {
            sb.append("immutable, ");
        }
        if (sb.length() == 0) {
            return "";
        }
        sb.delete(sb.length() - 2, sb.length());
        return sb.toString();
    }

    public static final class Builder {
        boolean immutable;
        int maxAgeSeconds = -1;
        int maxStaleSeconds = -1;
        int minFreshSeconds = -1;
        boolean noCache;
        boolean noStore;
        boolean noTransform;
        boolean onlyIfCached;

        public Builder noCache() {
            this.noCache = true;
            return this;
        }

        public Builder noStore() {
            this.noStore = true;
            return this;
        }

        public Builder maxAge(int i, TimeUnit timeUnit) {
            if (i >= 0) {
                long seconds = timeUnit.toSeconds((long) i);
                this.maxAgeSeconds = seconds > 2147483647L ? Api.BaseClientBuilder.API_PRIORITY_OTHER : (int) seconds;
                return this;
            }
            throw new IllegalArgumentException("maxAge < 0: " + i);
        }

        public Builder maxStale(int i, TimeUnit timeUnit) {
            if (i >= 0) {
                long seconds = timeUnit.toSeconds((long) i);
                this.maxStaleSeconds = seconds > 2147483647L ? Api.BaseClientBuilder.API_PRIORITY_OTHER : (int) seconds;
                return this;
            }
            throw new IllegalArgumentException("maxStale < 0: " + i);
        }

        public Builder minFresh(int i, TimeUnit timeUnit) {
            if (i >= 0) {
                long seconds = timeUnit.toSeconds((long) i);
                this.minFreshSeconds = seconds > 2147483647L ? Api.BaseClientBuilder.API_PRIORITY_OTHER : (int) seconds;
                return this;
            }
            throw new IllegalArgumentException("minFresh < 0: " + i);
        }

        public Builder onlyIfCached() {
            this.onlyIfCached = true;
            return this;
        }

        public Builder noTransform() {
            this.noTransform = true;
            return this;
        }

        public Builder immutable() {
            this.immutable = true;
            return this;
        }

        public CacheControl build() {
            return new CacheControl(this);
        }
    }
}
