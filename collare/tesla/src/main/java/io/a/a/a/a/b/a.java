package io.a.a.a.a.b;

import io.a.a.a.a.e.c;
import io.a.a.a.a.e.d;
import io.a.a.a.a.e.e;
import io.a.a.a.i;
import java.util.Collections;
import java.util.Map;
import java.util.regex.Pattern;

/* compiled from: AbstractSpiCall */
public abstract class a {
    public static final String ACCEPT_JSON_VALUE = "application/json";
    public static final String ANDROID_CLIENT_TYPE = "android";
    public static final String CLS_ANDROID_SDK_DEVELOPER_TOKEN = "470fa2b4ae81cd56ecbcda9735803434cec591fa";
    public static final String CRASHLYTICS_USER_AGENT = "Crashlytics Android SDK/";
    public static final int DEFAULT_TIMEOUT = 10000;
    public static final String HEADER_ACCEPT = "Accept";
    public static final String HEADER_API_KEY = "X-CRASHLYTICS-API-KEY";
    public static final String HEADER_CLIENT_TYPE = "X-CRASHLYTICS-API-CLIENT-TYPE";
    public static final String HEADER_CLIENT_VERSION = "X-CRASHLYTICS-API-CLIENT-VERSION";
    public static final String HEADER_DEVELOPER_TOKEN = "X-CRASHLYTICS-DEVELOPER-TOKEN";
    public static final String HEADER_REQUEST_ID = "X-REQUEST-ID";
    public static final String HEADER_USER_AGENT = "User-Agent";
    private static final Pattern PROTOCOL_AND_HOST_PATTERN = Pattern.compile("http(s?)://[^\\/]+", 2);
    protected final i kit;
    private final c method;
    private final String protocolAndHostOverride;
    private final e requestFactory;
    private final String url;

    public a(i iVar, String str, String str2, e eVar, c cVar) {
        if (str2 == null) {
            throw new IllegalArgumentException("url must not be null.");
        } else if (eVar != null) {
            this.kit = iVar;
            this.protocolAndHostOverride = str;
            this.url = overrideProtocolAndHost(str2);
            this.requestFactory = eVar;
            this.method = cVar;
        } else {
            throw new IllegalArgumentException("requestFactory must not be null.");
        }
    }

    /* access modifiers changed from: protected */
    public String getUrl() {
        return this.url;
    }

    /* access modifiers changed from: protected */
    public d getHttpRequest() {
        return getHttpRequest(Collections.emptyMap());
    }

    /* access modifiers changed from: protected */
    public d getHttpRequest(Map<String, String> map) {
        d a2 = this.requestFactory.a(this.method, getUrl(), map).a(false).a(DEFAULT_TIMEOUT);
        return a2.a(HEADER_USER_AGENT, CRASHLYTICS_USER_AGENT + this.kit.getVersion()).a(HEADER_DEVELOPER_TOKEN, CLS_ANDROID_SDK_DEVELOPER_TOKEN);
    }

    private String overrideProtocolAndHost(String str) {
        return !i.d(this.protocolAndHostOverride) ? PROTOCOL_AND_HOST_PATTERN.matcher(str).replaceFirst(this.protocolAndHostOverride) : str;
    }
}
