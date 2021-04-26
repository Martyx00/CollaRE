package com.reactnativecommunity.webview;

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
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
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
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.UIManagerModule;
import com.facebook.react.uimanager.af;
import com.google.android.gms.common.internal.ImagesContract;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.reactnativecommunity.webview.a.c;
import com.reactnativecommunity.webview.a.d;
import com.reactnativecommunity.webview.a.e;
import com.reactnativecommunity.webview.a.f;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

@com.facebook.react.module.a.a(a = RNCWebViewManager.REACT_CLASS)
public class RNCWebViewManager extends SimpleViewManager<WebView> {
    protected static final String BLANK_URL = "about:blank";
    protected static final String BRIDGE_NAME = "__REACT_WEB_VIEW_BRIDGE";
    public static final int COMMAND_GO_BACK = 1;
    public static final int COMMAND_GO_FORWARD = 2;
    public static final int COMMAND_INJECT_JAVASCRIPT = 6;
    public static final int COMMAND_LOAD_URL = 7;
    public static final int COMMAND_POST_MESSAGE = 5;
    public static final int COMMAND_RELOAD = 3;
    public static final int COMMAND_STOP_LOADING = 4;
    protected static final String HTML_ENCODING = "UTF-8";
    protected static final String HTML_MIME_TYPE = "text/html";
    protected static final String HTTP_METHOD_POST = "POST";
    protected static final String REACT_CLASS = "RNCWebView";
    private a aPackage;
    protected WebView.PictureListener mPictureListener;
    protected b mWebViewConfig;

    @Override // com.facebook.react.bridge.NativeModule, com.facebook.react.uimanager.ViewManager
    public String getName() {
        return REACT_CLASS;
    }

    /* access modifiers changed from: protected */
    public static class b extends WebViewClient {

        /* renamed from: a  reason: collision with root package name */
        protected boolean f4766a = false;

        /* renamed from: b  reason: collision with root package name */
        protected ReadableArray f4767b;

        protected b() {
        }

        public void onPageFinished(WebView webView, String str) {
            super.onPageFinished(webView, str);
            if (!this.f4766a) {
                a aVar = (a) webView;
                aVar.a();
                aVar.b();
                a(webView, str);
            }
        }

        public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
            super.onPageStarted(webView, str, bitmap);
            this.f4766a = false;
            RNCWebViewManager.dispatchEvent(webView, new d(webView.getId(), b(webView, str)));
        }

        @Override // android.webkit.WebViewClient
        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            RNCWebViewManager.dispatchEvent(webView, new f(webView.getId(), str));
            return true;
        }

        @Override // android.webkit.WebViewClient
        @TargetApi(24)
        public boolean shouldOverrideUrlLoading(WebView webView, WebResourceRequest webResourceRequest) {
            RNCWebViewManager.dispatchEvent(webView, new f(webView.getId(), webResourceRequest.getUrl().toString()));
            return true;
        }

        public void onReceivedError(WebView webView, int i, String str, String str2) {
            super.onReceivedError(webView, i, str, str2);
            this.f4766a = true;
            a(webView, str2);
            WritableMap b2 = b(webView, str2);
            b2.putDouble("code", (double) i);
            b2.putString("description", str);
            RNCWebViewManager.dispatchEvent(webView, new com.reactnativecommunity.webview.a.a(webView.getId(), b2));
        }

        /* access modifiers changed from: protected */
        public void a(WebView webView, String str) {
            RNCWebViewManager.dispatchEvent(webView, new com.reactnativecommunity.webview.a.b(webView.getId(), b(webView, str)));
        }

        /* access modifiers changed from: protected */
        public WritableMap b(WebView webView, String str) {
            WritableMap createMap = Arguments.createMap();
            createMap.putDouble("target", (double) webView.getId());
            createMap.putString(ImagesContract.URL, str);
            createMap.putBoolean("loading", !this.f4766a && webView.getProgress() != 100);
            createMap.putString("title", webView.getTitle());
            createMap.putBoolean("canGoBack", webView.canGoBack());
            createMap.putBoolean("canGoForward", webView.canGoForward());
            return createMap;
        }

        public void a(ReadableArray readableArray) {
            this.f4767b = readableArray;
        }
    }

    /* access modifiers changed from: protected */
    public static class a extends WebView implements LifecycleEventListener {

        /* renamed from: a  reason: collision with root package name */
        protected String f4761a;

        /* renamed from: b  reason: collision with root package name */
        protected boolean f4762b = false;

        /* renamed from: c  reason: collision with root package name */
        protected b f4763c;

        @Override // com.facebook.react.bridge.LifecycleEventListener
        public void onHostPause() {
        }

        @Override // com.facebook.react.bridge.LifecycleEventListener
        public void onHostResume() {
        }

        /* access modifiers changed from: protected */
        /* renamed from: com.reactnativecommunity.webview.RNCWebViewManager$a$a  reason: collision with other inner class name */
        public class C0085a {

            /* renamed from: a  reason: collision with root package name */
            a f4764a;

            C0085a(a aVar) {
                this.f4764a = aVar;
            }

            @JavascriptInterface
            public void postMessage(String str) {
                this.f4764a.b(str);
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
            this.f4763c = (b) webViewClient;
        }

        public b getRNCWebViewClient() {
            return this.f4763c;
        }

        public void setInjectedJavaScript(String str) {
            this.f4761a = str;
        }

        /* access modifiers changed from: protected */
        public C0085a a(a aVar) {
            return new C0085a(aVar);
        }

        public void setMessagingEnabled(boolean z) {
            if (this.f4762b != z) {
                this.f4762b = z;
                if (z) {
                    addJavascriptInterface(a(this), RNCWebViewManager.BRIDGE_NAME);
                    b();
                    return;
                }
                removeJavascriptInterface(RNCWebViewManager.BRIDGE_NAME);
            }
        }

        /* access modifiers changed from: protected */
        public void a(String str) {
            if (Build.VERSION.SDK_INT >= 19) {
                evaluateJavascript(str, null);
                return;
            }
            try {
                loadUrl("javascript:" + URLEncoder.encode(str, RNCWebViewManager.HTML_ENCODING));
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException(e);
            }
        }

        public void a() {
            String str;
            if (getSettings().getJavaScriptEnabled() && (str = this.f4761a) != null && !TextUtils.isEmpty(str)) {
                a("(function() {\n" + this.f4761a + ";\n})();");
            }
        }

        public void b() {
            if (this.f4762b) {
                a("(window.originalPostMessage = window.postMessage,window.postMessage = function(data) {__REACT_WEB_VIEW_BRIDGE.postMessage(String(data));})");
            }
        }

        public void b(String str) {
            RNCWebViewManager.dispatchEvent(this, new e(getId(), str));
        }

        /* access modifiers changed from: protected */
        public void c() {
            setWebViewClient(null);
            destroy();
        }
    }

    public RNCWebViewManager() {
        this.mWebViewConfig = new b() {
            /* class com.reactnativecommunity.webview.RNCWebViewManager.AnonymousClass1 */

            @Override // com.reactnativecommunity.webview.b
            public void a(WebView webView) {
            }
        };
    }

    public RNCWebViewManager(b bVar) {
        this.mWebViewConfig = bVar;
    }

    /* access modifiers changed from: protected */
    public a createRNCWebViewInstance(af afVar) {
        return new a(afVar);
    }

    /* access modifiers changed from: protected */
    @Override // com.facebook.react.uimanager.ViewManager
    @TargetApi(21)
    public WebView createViewInstance(af afVar) {
        a createRNCWebViewInstance = createRNCWebViewInstance(afVar);
        createRNCWebViewInstance.setWebChromeClient(new WebChromeClient() {
            /* class com.reactnativecommunity.webview.RNCWebViewManager.AnonymousClass2 */

            public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
                return true;
            }

            public void onProgressChanged(WebView webView, int i) {
                super.onProgressChanged(webView, i);
                WritableMap createMap = Arguments.createMap();
                createMap.putDouble("target", (double) webView.getId());
                createMap.putString("title", webView.getTitle());
                createMap.putBoolean("canGoBack", webView.canGoBack());
                createMap.putBoolean("canGoForward", webView.canGoForward());
                createMap.putDouble("progress", (double) (((float) i) / 100.0f));
                RNCWebViewManager.dispatchEvent(webView, new c(webView.getId(), createMap));
            }

            public void onGeolocationPermissionsShowPrompt(String str, GeolocationPermissions.Callback callback) {
                callback.invoke(str, true, false);
            }

            @Override // android.webkit.WebChromeClient
            @TargetApi(21)
            public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> valueCallback, WebChromeClient.FileChooserParams fileChooserParams) {
                String[] acceptTypes = fileChooserParams.getAcceptTypes();
                boolean z = true;
                if (fileChooserParams.getMode() != 1) {
                    z = false;
                }
                return RNCWebViewManager.this.getModule().startPhotoPickerIntent(valueCallback, fileChooserParams.createIntent(), acceptTypes, z);
            }
        });
        afVar.addLifecycleEventListener(createRNCWebViewInstance);
        this.mWebViewConfig.a(createRNCWebViewInstance);
        WebSettings settings = createRNCWebViewInstance.getSettings();
        settings.setBuiltInZoomControls(true);
        settings.setDisplayZoomControls(false);
        settings.setDomStorageEnabled(true);
        settings.setAllowFileAccess(false);
        settings.setAllowContentAccess(false);
        if (Build.VERSION.SDK_INT >= 16) {
            settings.setAllowFileAccessFromFileURLs(false);
            setAllowUniversalAccessFromFileURLs(createRNCWebViewInstance, false);
        }
        setMixedContentMode(createRNCWebViewInstance, "never");
        createRNCWebViewInstance.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
        setGeolocationEnabled(createRNCWebViewInstance, false);
        return createRNCWebViewInstance;
    }

    @com.facebook.react.uimanager.a.a(a = "javaScriptEnabled")
    public void setJavaScriptEnabled(WebView webView, boolean z) {
        webView.getSettings().setJavaScriptEnabled(z);
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x0039  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x003e  */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x0043  */
    @com.facebook.react.uimanager.a.a(a = "overScrollMode")
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setOverScrollMode(android.webkit.WebView r6, java.lang.String r7) {
        /*
            r5 = this;
            int r0 = r7.hashCode()
            r1 = -1414557169(0xffffffffabaf920f, float:-1.2475037E-12)
            r2 = 1
            r3 = 2
            r4 = 0
            if (r0 == r1) goto L_0x002b
            r1 = 104712844(0x63dca8c, float:3.5695757E-35)
            if (r0 == r1) goto L_0x0021
            r1 = 951530617(0x38b73479, float:8.735894E-5)
            if (r0 == r1) goto L_0x0017
            goto L_0x0035
        L_0x0017:
            java.lang.String r0 = "content"
            boolean r7 = r7.equals(r0)
            if (r7 == 0) goto L_0x0035
            r7 = 1
            goto L_0x0036
        L_0x0021:
            java.lang.String r0 = "never"
            boolean r7 = r7.equals(r0)
            if (r7 == 0) goto L_0x0035
            r7 = 0
            goto L_0x0036
        L_0x002b:
            java.lang.String r0 = "always"
            boolean r7 = r7.equals(r0)
            if (r7 == 0) goto L_0x0035
            r7 = 2
            goto L_0x0036
        L_0x0035:
            r7 = -1
        L_0x0036:
            switch(r7) {
                case 0: goto L_0x0043;
                case 1: goto L_0x003e;
                default: goto L_0x0039;
            }
        L_0x0039:
            java.lang.Integer r7 = java.lang.Integer.valueOf(r4)
            goto L_0x0047
        L_0x003e:
            java.lang.Integer r7 = java.lang.Integer.valueOf(r2)
            goto L_0x0047
        L_0x0043:
            java.lang.Integer r7 = java.lang.Integer.valueOf(r3)
        L_0x0047:
            int r7 = r7.intValue()
            r6.setOverScrollMode(r7)
            return
            switch-data {0->0x0043, 1->0x003e, }
        */
        throw new UnsupportedOperationException("Method not decompiled: com.reactnativecommunity.webview.RNCWebViewManager.setOverScrollMode(android.webkit.WebView, java.lang.String):void");
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
            byte[] bArr = null;
            if (readableMap.hasKey("html")) {
                String string = readableMap.getString("html");
                if (readableMap.hasKey("baseUrl")) {
                    webView.loadDataWithBaseURL(readableMap.getString("baseUrl"), string, HTML_MIME_TYPE, HTML_ENCODING, null);
                    return;
                } else {
                    webView.loadData(string, "text/html; charset=UTF-8", null);
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
        b rNCWebViewClient = ((a) webView).getRNCWebViewClient();
        if (rNCWebViewClient != null && readableArray != null) {
            rNCWebViewClient.a(readableArray);
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

    /* access modifiers changed from: protected */
    public void addEventEmitters(af afVar, WebView webView) {
        webView.setWebViewClient(new b());
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public Map getExportedCustomDirectEventTypeConstants() {
        Map<String, Object> exportedCustomDirectEventTypeConstants = super.getExportedCustomDirectEventTypeConstants();
        if (exportedCustomDirectEventTypeConstants == null) {
            exportedCustomDirectEventTypeConstants = com.facebook.react.common.e.a();
        }
        exportedCustomDirectEventTypeConstants.put("topLoadingProgress", com.facebook.react.common.e.a("registrationName", "onLoadingProgress"));
        exportedCustomDirectEventTypeConstants.put("topShouldStartLoadWithRequest", com.facebook.react.common.e.a("registrationName", "onShouldStartLoadWithRequest"));
        return exportedCustomDirectEventTypeConstants;
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public Map<String, Integer> getCommandsMap() {
        return com.facebook.react.common.e.a("goBack", 1, "goForward", 2, "reload", 3, "stopLoading", 4, "postMessage", 5, "injectJavaScript", 6, "loadUrl", 7);
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
            case 7:
                if (readableArray != null) {
                    webView.loadUrl(readableArray.getString(0));
                    return;
                }
                throw new RuntimeException("Arguments for loading an url are null!");
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
                /* class com.reactnativecommunity.webview.RNCWebViewManager.AnonymousClass3 */

                public void onNewPicture(WebView webView, Picture picture) {
                    RNCWebViewManager.dispatchEvent(webView, new com.facebook.react.uimanager.events.b(webView.getId(), webView.getWidth(), webView.getContentHeight()));
                }
            };
        }
        return this.mPictureListener;
    }

    protected static void dispatchEvent(WebView webView, com.facebook.react.uimanager.events.c cVar) {
        ((UIManagerModule) ((ReactContext) webView.getContext()).getNativeModule(UIManagerModule.class)).getEventDispatcher().a(cVar);
    }

    public a getPackage() {
        return this.aPackage;
    }

    public void setPackage(a aVar) {
        this.aPackage = aVar;
    }

    public RNCWebViewModule getModule() {
        return this.aPackage.a();
    }
}
