package com.facebook.react.views.webview;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.Picture;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.ConsoleMessage;
import android.webkit.CookieManager;
import android.webkit.GeolocationPermissions;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.LifecycleEventListener;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableMapKeySetIterator;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.common.e;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.UIManagerModule;
import com.facebook.react.uimanager.af;
import com.facebook.react.views.webview.a.c;
import com.facebook.react.views.webview.a.d;
import com.google.android.gms.common.internal.ImagesContract;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Pattern;
import org.json.JSONException;
import org.json.JSONObject;

@com.facebook.react.module.a.a(a = ReactWebViewManager.REACT_CLASS)
public class ReactWebViewManager extends SimpleViewManager<WebView> {
    protected static final String BLANK_URL = "about:blank";
    protected static final String BRIDGE_NAME = "__REACT_WEB_VIEW_BRIDGE";
    public static final int COMMAND_GO_BACK = 1;
    public static final int COMMAND_GO_FORWARD = 2;
    public static final int COMMAND_INJECT_JAVASCRIPT = 6;
    public static final int COMMAND_POST_MESSAGE = 5;
    public static final int COMMAND_RELOAD = 3;
    public static final int COMMAND_STOP_LOADING = 4;
    protected static final String HTML_ENCODING = "UTF-8";
    protected static final String HTML_MIME_TYPE = "text/html";
    protected static final String HTTP_METHOD_POST = "POST";
    private static final String INTENT_URL_PREFIX = "intent://";
    public static final String REACT_CLASS = "RCTWebView";
    protected WebView.PictureListener mPictureListener;
    protected a mWebViewConfig;

    @Override // com.facebook.react.bridge.NativeModule, com.facebook.react.uimanager.ViewManager
    public String getName() {
        return REACT_CLASS;
    }

    /* access modifiers changed from: protected */
    public static class b extends WebViewClient {

        /* renamed from: a  reason: collision with root package name */
        protected boolean f3596a = false;

        /* renamed from: b  reason: collision with root package name */
        protected ReadableArray f3597b;

        /* renamed from: c  reason: collision with root package name */
        protected List<Pattern> f3598c;

        protected b() {
        }

        public void onPageFinished(WebView webView, String str) {
            super.onPageFinished(webView, str);
            if (!this.f3596a) {
                a aVar = (a) webView;
                aVar.a();
                aVar.b();
                a(webView, str);
            }
        }

        public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
            super.onPageStarted(webView, str, bitmap);
            this.f3596a = false;
            ReactWebViewManager.dispatchEvent(webView, new c(webView.getId(), b(webView, str)));
        }

        @Override // android.webkit.WebViewClient
        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            if (str.equals(ReactWebViewManager.BLANK_URL)) {
                return false;
            }
            ReadableArray readableArray = this.f3597b;
            if (readableArray != null && readableArray.size() > 0) {
                Iterator<Object> it = this.f3597b.toArrayList().iterator();
                while (it.hasNext()) {
                    if (str.startsWith((String) it.next())) {
                        a(webView.getContext(), str);
                        return true;
                    }
                }
            }
            List<Pattern> list = this.f3598c;
            if (list != null && a(list, str)) {
                return false;
            }
            a(webView.getContext(), str);
            return true;
        }

        /* JADX WARNING: Removed duplicated region for block: B:13:0x0048  */
        /* JADX WARNING: Removed duplicated region for block: B:9:0x001a  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private void a(android.content.Context r5, java.lang.String r6) {
            /*
            // Method dump skipped, instructions count: 121
            */
            throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.views.webview.ReactWebViewManager.b.a(android.content.Context, java.lang.String):void");
        }

        private boolean a(List<Pattern> list, String str) {
            Uri parse = Uri.parse(str);
            String str2 = (parse.getScheme() != null ? parse.getScheme() : "") + "://" + (parse.getAuthority() != null ? parse.getAuthority() : "");
            for (Pattern pattern : list) {
                if (pattern.matcher(str2).matches()) {
                    return true;
                }
            }
            return false;
        }

        public void onReceivedError(WebView webView, int i, String str, String str2) {
            super.onReceivedError(webView, i, str, str2);
            this.f3596a = true;
            a(webView, str2);
            WritableMap b2 = b(webView, str2);
            b2.putDouble("code", (double) i);
            b2.putString("description", str);
            ReactWebViewManager.dispatchEvent(webView, new com.facebook.react.views.webview.a.a(webView.getId(), b2));
        }

        /* access modifiers changed from: protected */
        public void a(WebView webView, String str) {
            ReactWebViewManager.dispatchEvent(webView, new com.facebook.react.views.webview.a.b(webView.getId(), b(webView, str)));
        }

        /* access modifiers changed from: protected */
        public WritableMap b(WebView webView, String str) {
            WritableMap createMap = Arguments.createMap();
            createMap.putDouble("target", (double) webView.getId());
            createMap.putString(ImagesContract.URL, str);
            createMap.putBoolean("loading", !this.f3596a && webView.getProgress() != 100);
            createMap.putString("title", webView.getTitle());
            createMap.putBoolean("canGoBack", webView.canGoBack());
            createMap.putBoolean("canGoForward", webView.canGoForward());
            return createMap;
        }

        public void a(ReadableArray readableArray) {
            this.f3597b = readableArray;
        }

        public void a(List<Pattern> list) {
            this.f3598c = list;
        }
    }

    /* access modifiers changed from: protected */
    public static class a extends WebView implements LifecycleEventListener {

        /* renamed from: a  reason: collision with root package name */
        protected String f3591a;

        /* renamed from: b  reason: collision with root package name */
        protected boolean f3592b = false;

        /* renamed from: c  reason: collision with root package name */
        protected b f3593c;

        @Override // com.facebook.react.bridge.LifecycleEventListener
        public void onHostPause() {
        }

        @Override // com.facebook.react.bridge.LifecycleEventListener
        public void onHostResume() {
        }

        /* access modifiers changed from: protected */
        /* renamed from: com.facebook.react.views.webview.ReactWebViewManager$a$a  reason: collision with other inner class name */
        public class C0060a {

            /* renamed from: a  reason: collision with root package name */
            a f3594a;

            C0060a(a aVar) {
                this.f3594a = aVar;
            }

            @JavascriptInterface
            public void postMessage(String str) {
                this.f3594a.b(str);
            }
        }

        public a(af afVar) {
            super(afVar);
        }

        @Override // com.facebook.react.bridge.LifecycleEventListener
        public void onHostDestroy() {
            c();
        }

        public void setWebViewClient(WebViewClient webViewClient) {
            super.setWebViewClient(webViewClient);
            this.f3593c = (b) webViewClient;
        }

        public b getReactWebViewClient() {
            return this.f3593c;
        }

        public void setInjectedJavaScript(String str) {
            this.f3591a = str;
        }

        /* access modifiers changed from: protected */
        public C0060a a(a aVar) {
            return new C0060a(aVar);
        }

        public void setMessagingEnabled(boolean z) {
            if (this.f3592b != z) {
                this.f3592b = z;
                if (z) {
                    addJavascriptInterface(a(this), ReactWebViewManager.BRIDGE_NAME);
                    b();
                    return;
                }
                removeJavascriptInterface(ReactWebViewManager.BRIDGE_NAME);
            }
        }

        /* access modifiers changed from: protected */
        public void a(String str) {
            if (Build.VERSION.SDK_INT >= 19) {
                evaluateJavascript(str, null);
                return;
            }
            try {
                loadUrl("javascript:" + URLEncoder.encode(str, ReactWebViewManager.HTML_ENCODING));
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException(e);
            }
        }

        public void a() {
            String str;
            if (getSettings().getJavaScriptEnabled() && (str = this.f3591a) != null && !TextUtils.isEmpty(str)) {
                a("(function() {\n" + this.f3591a + ";\n})();");
            }
        }

        public void b() {
            if (this.f3592b) {
                a("(window.originalPostMessage = window.postMessage,window.postMessage = function(data) {__REACT_WEB_VIEW_BRIDGE.postMessage(String(data));})");
            }
        }

        public void b(String str) {
            ReactWebViewManager.dispatchEvent(this, new d(getId(), str));
        }

        /* access modifiers changed from: protected */
        public void c() {
            setWebViewClient(null);
            destroy();
        }
    }

    public ReactWebViewManager() {
        this.mWebViewConfig = new a() {
            /* class com.facebook.react.views.webview.ReactWebViewManager.AnonymousClass1 */

            @Override // com.facebook.react.views.webview.a
            public void a(WebView webView) {
            }
        };
    }

    public ReactWebViewManager(a aVar) {
        this.mWebViewConfig = aVar;
    }

    /* access modifiers changed from: protected */
    public a createReactWebViewInstance(af afVar) {
        return new a(afVar);
    }

    /* access modifiers changed from: protected */
    @Override // com.facebook.react.uimanager.ViewManager
    @TargetApi(21)
    public WebView createViewInstance(af afVar) {
        a createReactWebViewInstance = createReactWebViewInstance(afVar);
        createReactWebViewInstance.setWebChromeClient(new WebChromeClient() {
            /* class com.facebook.react.views.webview.ReactWebViewManager.AnonymousClass2 */

            public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
                return true;
            }

            public void onGeolocationPermissionsShowPrompt(String str, GeolocationPermissions.Callback callback) {
                callback.invoke(str, true, false);
            }
        });
        afVar.addLifecycleEventListener(createReactWebViewInstance);
        this.mWebViewConfig.a(createReactWebViewInstance);
        WebSettings settings = createReactWebViewInstance.getSettings();
        settings.setBuiltInZoomControls(true);
        settings.setDisplayZoomControls(false);
        settings.setDomStorageEnabled(true);
        settings.setAllowFileAccess(false);
        settings.setAllowContentAccess(false);
        settings.setAllowFileAccessFromFileURLs(false);
        setAllowUniversalAccessFromFileURLs(createReactWebViewInstance, false);
        setMixedContentMode(createReactWebViewInstance, "never");
        createReactWebViewInstance.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
        setGeolocationEnabled(createReactWebViewInstance, false);
        return createReactWebViewInstance;
    }

    @com.facebook.react.uimanager.a.a(a = "hardwareAccelerationEnabledExperimental", f = true)
    public void sethardwareAccelerationEnabledExperimental(WebView webView, boolean z) {
        if (!z) {
            webView.setLayerType(1, null);
        }
    }

    @com.facebook.react.uimanager.a.a(a = "javaScriptEnabled")
    public void setJavaScriptEnabled(WebView webView, boolean z) {
        webView.getSettings().setJavaScriptEnabled(z);
    }

    @com.facebook.react.uimanager.a.a(a = "thirdPartyCookiesEnabled")
    public void setThirdPartyCookiesEnabled(WebView webView, boolean z) {
        if (Build.VERSION.SDK_INT >= 21) {
            CookieManager.getInstance().setAcceptThirdPartyCookies(webView, z);
        }
    }

    @com.facebook.react.uimanager.a.a(a = "scalesPageToFit")
    public void setScalesPageToFit(WebView webView, boolean z) {
        webView.getSettings().setUseWideViewPort(!z);
    }

    @com.facebook.react.uimanager.a.a(a = "domStorageEnabled")
    public void setDomStorageEnabled(WebView webView, boolean z) {
        webView.getSettings().setDomStorageEnabled(z);
    }

    @com.facebook.react.uimanager.a.a(a = "userAgent")
    public void setUserAgent(WebView webView, String str) {
        if (str != null) {
            webView.getSettings().setUserAgentString(str);
        }
    }

    @com.facebook.react.uimanager.a.a(a = "mediaPlaybackRequiresUserAction")
    @TargetApi(17)
    public void setMediaPlaybackRequiresUserAction(WebView webView, boolean z) {
        webView.getSettings().setMediaPlaybackRequiresUserGesture(z);
    }

    @com.facebook.react.uimanager.a.a(a = "allowUniversalAccessFromFileURLs")
    public void setAllowUniversalAccessFromFileURLs(WebView webView, boolean z) {
        webView.getSettings().setAllowUniversalAccessFromFileURLs(z);
    }

    @com.facebook.react.uimanager.a.a(a = "saveFormDataDisabled")
    public void setSaveFormDataDisabled(WebView webView, boolean z) {
        webView.getSettings().setSaveFormData(!z);
    }

    @com.facebook.react.uimanager.a.a(a = "injectedJavaScript")
    public void setInjectedJavaScript(WebView webView, String str) {
        ((a) webView).setInjectedJavaScript(str);
    }

    @com.facebook.react.uimanager.a.a(a = "messagingEnabled")
    public void setMessagingEnabled(WebView webView, boolean z) {
        ((a) webView).setMessagingEnabled(z);
    }

    @com.facebook.react.uimanager.a.a(a = FirebaseAnalytics.b.SOURCE)
    public void setSource(WebView webView, ReadableMap readableMap) {
        if (readableMap != null) {
            if (readableMap.hasKey("html")) {
                String string = readableMap.getString("html");
                if (readableMap.hasKey("baseUrl")) {
                    webView.loadDataWithBaseURL(readableMap.getString("baseUrl"), string, HTML_MIME_TYPE, HTML_ENCODING, null);
                    return;
                } else {
                    webView.loadData(string, HTML_MIME_TYPE, HTML_ENCODING);
                    return;
                }
            } else if (readableMap.hasKey("uri")) {
                String string2 = readableMap.getString("uri");
                String url = webView.getUrl();
                if (url != null && url.equals(string2)) {
                    return;
                }
                if (!readableMap.hasKey(FirebaseAnalytics.b.METHOD) || !readableMap.getString(FirebaseAnalytics.b.METHOD).equalsIgnoreCase(HTTP_METHOD_POST)) {
                    HashMap hashMap = new HashMap();
                    if (readableMap.hasKey("headers")) {
                        ReadableMap map = readableMap.getMap("headers");
                        ReadableMapKeySetIterator keySetIterator = map.keySetIterator();
                        while (keySetIterator.hasNextKey()) {
                            String nextKey = keySetIterator.nextKey();
                            if (!"user-agent".equals(nextKey.toLowerCase(Locale.ENGLISH))) {
                                hashMap.put(nextKey, map.getString(nextKey));
                            } else if (webView.getSettings() != null) {
                                webView.getSettings().setUserAgentString(map.getString(nextKey));
                            }
                        }
                    }
                    webView.loadUrl(string2, hashMap);
                    return;
                }
                byte[] bArr = null;
                if (readableMap.hasKey("body")) {
                    String string3 = readableMap.getString("body");
                    try {
                        bArr = string3.getBytes(HTML_ENCODING);
                    } catch (UnsupportedEncodingException unused) {
                        bArr = string3.getBytes();
                    }
                }
                if (bArr == null) {
                    bArr = new byte[0];
                }
                webView.postUrl(string2, bArr);
                return;
            }
        }
        webView.loadUrl(BLANK_URL);
    }

    @com.facebook.react.uimanager.a.a(a = "onContentSizeChange")
    public void setOnContentSizeChange(WebView webView, boolean z) {
        if (z) {
            webView.setPictureListener(getPictureListener());
        } else {
            webView.setPictureListener(null);
        }
    }

    @com.facebook.react.uimanager.a.a(a = "mixedContentMode")
    public void setMixedContentMode(WebView webView, String str) {
        if (Build.VERSION.SDK_INT < 21) {
            return;
        }
        if (str == null || "never".equals(str)) {
            webView.getSettings().setMixedContentMode(1);
        } else if ("always".equals(str)) {
            webView.getSettings().setMixedContentMode(0);
        } else if ("compatibility".equals(str)) {
            webView.getSettings().setMixedContentMode(2);
        }
    }

    @com.facebook.react.uimanager.a.a(a = "urlPrefixesForDefaultIntent")
    public void setUrlPrefixesForDefaultIntent(WebView webView, ReadableArray readableArray) {
        b reactWebViewClient = ((a) webView).getReactWebViewClient();
        if (reactWebViewClient != null && readableArray != null) {
            reactWebViewClient.a(readableArray);
        }
    }

    @com.facebook.react.uimanager.a.a(a = "allowFileAccess")
    public void setAllowFileAccess(WebView webView, Boolean bool) {
        webView.getSettings().setAllowFileAccess(bool != null && bool.booleanValue());
    }

    @com.facebook.react.uimanager.a.a(a = "geolocationEnabled")
    public void setGeolocationEnabled(WebView webView, Boolean bool) {
        webView.getSettings().setGeolocationEnabled(bool != null && bool.booleanValue());
    }

    @com.facebook.react.uimanager.a.a(a = "originWhitelist")
    public void setOriginWhitelist(WebView webView, ReadableArray readableArray) {
        b reactWebViewClient = ((a) webView).getReactWebViewClient();
        if (!(reactWebViewClient == null || readableArray == null)) {
            LinkedList linkedList = new LinkedList();
            for (int i = 0; i < readableArray.size(); i++) {
                linkedList.add(Pattern.compile(readableArray.getString(i)));
            }
            reactWebViewClient.a(linkedList);
        }
    }

    /* access modifiers changed from: protected */
    public void addEventEmitters(af afVar, WebView webView) {
        webView.setWebViewClient(new b());
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public Map<String, Integer> getCommandsMap() {
        return e.a("goBack", 1, "goForward", 2, "reload", 3, "stopLoading", 4, "postMessage", 5, "injectJavaScript", 6);
    }

    public void receiveCommand(WebView webView, int i, ReadableArray readableArray) {
        switch (i) {
            case 1:
                webView.goBack();
                return;
            case 2:
                webView.goForward();
                return;
            case 3:
                webView.reload();
                return;
            case 4:
                webView.stopLoading();
                return;
            case 5:
                try {
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("data", readableArray.getString(0));
                    ((a) webView).a("(function () {var event;var data = " + jSONObject.toString() + ";try {event = new MessageEvent('message', data);} catch (e) {event = document.createEvent('MessageEvent');event.initMessageEvent('message', true, true, data.data, data.origin, data.lastEventId, data.source);}document.dispatchEvent(event);})();");
                    return;
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            case 6:
                ((a) webView).a(readableArray.getString(0));
                return;
            default:
                return;
        }
    }

    public void onDropViewInstance(WebView webView) {
        super.onDropViewInstance((View) webView);
        a aVar = (a) webView;
        ((af) webView.getContext()).removeLifecycleEventListener(aVar);
        aVar.c();
    }

    /* access modifiers changed from: protected */
    public WebView.PictureListener getPictureListener() {
        if (this.mPictureListener == null) {
            this.mPictureListener = new WebView.PictureListener() {
                /* class com.facebook.react.views.webview.ReactWebViewManager.AnonymousClass3 */

                public void onNewPicture(WebView webView, Picture picture) {
                    ReactWebViewManager.dispatchEvent(webView, new com.facebook.react.uimanager.events.b(webView.getId(), webView.getWidth(), webView.getContentHeight()));
                }
            };
        }
        return this.mPictureListener;
    }

    protected static void dispatchEvent(WebView webView, com.facebook.react.uimanager.events.c cVar) {
        ((UIManagerModule) ((ReactContext) webView.getContext()).getNativeModule(UIManagerModule.class)).getEventDispatcher().a(cVar);
    }
}
