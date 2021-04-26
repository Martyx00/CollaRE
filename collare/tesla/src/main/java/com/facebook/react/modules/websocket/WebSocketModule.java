package com.facebook.react.modules.websocket;

import c.f;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableMapKeySetIterator;
import com.facebook.react.bridge.ReadableType;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.facebook.react.modules.network.c;
import com.google.android.gms.measurement.AppMeasurement;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.teslamotors.plugins.biometricauthentication.BiometricAuthenticationModule;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import org.spongycastle.i18n.TextBundle;

@com.facebook.react.module.a.a(a = WebSocketModule.NAME, d = false)
public final class WebSocketModule extends ReactContextBaseJavaModule {
    public static final String NAME = "WebSocketModule";
    private final Map<Integer, a> mContentHandlers = new ConcurrentHashMap();
    private c mCookieHandler;
    private ReactContext mReactContext;
    private final Map<Integer, WebSocket> mWebSocketConnections = new ConcurrentHashMap();

    public interface a {
        void a(f fVar, WritableMap writableMap);

        void a(String str, WritableMap writableMap);
    }

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return NAME;
    }

    public WebSocketModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
        this.mReactContext = reactApplicationContext;
        this.mCookieHandler = new c(reactApplicationContext);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void sendEvent(String str, WritableMap writableMap) {
        ((DeviceEventManagerModule.RCTDeviceEventEmitter) this.mReactContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)).emit(str, writableMap);
    }

    public void setContentHandler(int i, a aVar) {
        if (aVar != null) {
            this.mContentHandlers.put(Integer.valueOf(i), aVar);
        } else {
            this.mContentHandlers.remove(Integer.valueOf(i));
        }
    }

    @ReactMethod
    public void connect(String str, ReadableArray readableArray, ReadableMap readableMap, final int i) {
        OkHttpClient build = new OkHttpClient.Builder().connectTimeout(10, TimeUnit.SECONDS).writeTimeout(10, TimeUnit.SECONDS).readTimeout(0, TimeUnit.MINUTES).build();
        Request.Builder url = new Request.Builder().tag(Integer.valueOf(i)).url(str);
        String cookie = getCookie(str);
        if (cookie != null) {
            url.addHeader("Cookie", cookie);
        }
        if (readableMap == null || !readableMap.hasKey("headers") || !readableMap.getType("headers").equals(ReadableType.Map)) {
            url.addHeader(FirebaseAnalytics.b.ORIGIN, getDefaultOrigin(str));
        } else {
            ReadableMap map = readableMap.getMap("headers");
            ReadableMapKeySetIterator keySetIterator = map.keySetIterator();
            if (!map.hasKey(FirebaseAnalytics.b.ORIGIN)) {
                url.addHeader(FirebaseAnalytics.b.ORIGIN, getDefaultOrigin(str));
            }
            while (keySetIterator.hasNextKey()) {
                String nextKey = keySetIterator.nextKey();
                if (ReadableType.String.equals(map.getType(nextKey))) {
                    url.addHeader(nextKey, map.getString(nextKey));
                } else {
                    com.facebook.common.e.a.c("ReactNative", "Ignoring: requested " + nextKey + ", value not a string");
                }
            }
        }
        if (readableArray != null && readableArray.size() > 0) {
            StringBuilder sb = new StringBuilder("");
            for (int i2 = 0; i2 < readableArray.size(); i2++) {
                String trim = readableArray.getString(i2).trim();
                if (!trim.isEmpty() && !trim.contains(",")) {
                    sb.append(trim);
                    sb.append(",");
                }
            }
            if (sb.length() > 0) {
                sb.replace(sb.length() - 1, sb.length(), "");
                url.addHeader("Sec-WebSocket-Protocol", sb.toString());
            }
        }
        build.newWebSocket(url.build(), new WebSocketListener() {
            /* class com.facebook.react.modules.websocket.WebSocketModule.AnonymousClass1 */

            @Override // okhttp3.WebSocketListener
            public void onOpen(WebSocket webSocket, Response response) {
                WebSocketModule.this.mWebSocketConnections.put(Integer.valueOf(i), webSocket);
                WritableMap createMap = Arguments.createMap();
                createMap.putInt("id", i);
                WebSocketModule.this.sendEvent("websocketOpen", createMap);
            }

            @Override // okhttp3.WebSocketListener
            public void onClosing(WebSocket webSocket, int i, String str) {
                webSocket.close(i, str);
            }

            @Override // okhttp3.WebSocketListener
            public void onClosed(WebSocket webSocket, int i, String str) {
                WritableMap createMap = Arguments.createMap();
                createMap.putInt("id", i);
                createMap.putInt("code", i);
                createMap.putString("reason", str);
                WebSocketModule.this.sendEvent("websocketClosed", createMap);
            }

            @Override // okhttp3.WebSocketListener
            public void onFailure(WebSocket webSocket, Throwable th, Response response) {
                WebSocketModule.this.notifyWebSocketFailed(i, th.getMessage());
            }

            @Override // okhttp3.WebSocketListener
            public void onMessage(WebSocket webSocket, String str) {
                WritableMap createMap = Arguments.createMap();
                createMap.putInt("id", i);
                createMap.putString(AppMeasurement.Param.TYPE, TextBundle.TEXT_ENTRY);
                a aVar = (a) WebSocketModule.this.mContentHandlers.get(Integer.valueOf(i));
                if (aVar != null) {
                    aVar.a(str, createMap);
                } else {
                    createMap.putString("data", str);
                }
                WebSocketModule.this.sendEvent("websocketMessage", createMap);
            }

            @Override // okhttp3.WebSocketListener
            public void onMessage(WebSocket webSocket, f fVar) {
                WritableMap createMap = Arguments.createMap();
                createMap.putInt("id", i);
                createMap.putString(AppMeasurement.Param.TYPE, "binary");
                a aVar = (a) WebSocketModule.this.mContentHandlers.get(Integer.valueOf(i));
                if (aVar != null) {
                    aVar.a(fVar, createMap);
                } else {
                    createMap.putString("data", fVar.b());
                }
                WebSocketModule.this.sendEvent("websocketMessage", createMap);
            }
        });
        build.dispatcher().executorService().shutdown();
    }

    @ReactMethod
    public void close(int i, String str, int i2) {
        WebSocket webSocket = this.mWebSocketConnections.get(Integer.valueOf(i2));
        if (webSocket != null) {
            try {
                webSocket.close(i, str);
                this.mWebSocketConnections.remove(Integer.valueOf(i2));
                this.mContentHandlers.remove(Integer.valueOf(i2));
            } catch (Exception e) {
                com.facebook.common.e.a.c("ReactNative", "Could not close WebSocket connection for id " + i2, e);
            }
        }
    }

    @ReactMethod
    public void send(String str, int i) {
        WebSocket webSocket = this.mWebSocketConnections.get(Integer.valueOf(i));
        if (webSocket == null) {
            WritableMap createMap = Arguments.createMap();
            createMap.putInt("id", i);
            createMap.putString(BiometricAuthenticationModule.BIOMETRICS_BUNDLE_KEY_MESSAGE, "client is null");
            sendEvent("websocketFailed", createMap);
            WritableMap createMap2 = Arguments.createMap();
            createMap2.putInt("id", i);
            createMap2.putInt("code", 0);
            createMap2.putString("reason", "client is null");
            sendEvent("websocketClosed", createMap2);
            this.mWebSocketConnections.remove(Integer.valueOf(i));
            this.mContentHandlers.remove(Integer.valueOf(i));
            return;
        }
        try {
            webSocket.send(str);
        } catch (Exception e) {
            notifyWebSocketFailed(i, e.getMessage());
        }
    }

    @ReactMethod
    public void sendBinary(String str, int i) {
        WebSocket webSocket = this.mWebSocketConnections.get(Integer.valueOf(i));
        if (webSocket == null) {
            WritableMap createMap = Arguments.createMap();
            createMap.putInt("id", i);
            createMap.putString(BiometricAuthenticationModule.BIOMETRICS_BUNDLE_KEY_MESSAGE, "client is null");
            sendEvent("websocketFailed", createMap);
            WritableMap createMap2 = Arguments.createMap();
            createMap2.putInt("id", i);
            createMap2.putInt("code", 0);
            createMap2.putString("reason", "client is null");
            sendEvent("websocketClosed", createMap2);
            this.mWebSocketConnections.remove(Integer.valueOf(i));
            this.mContentHandlers.remove(Integer.valueOf(i));
            return;
        }
        try {
            webSocket.send(f.b(str));
        } catch (Exception e) {
            notifyWebSocketFailed(i, e.getMessage());
        }
    }

    public void sendBinary(f fVar, int i) {
        WebSocket webSocket = this.mWebSocketConnections.get(Integer.valueOf(i));
        if (webSocket == null) {
            WritableMap createMap = Arguments.createMap();
            createMap.putInt("id", i);
            createMap.putString(BiometricAuthenticationModule.BIOMETRICS_BUNDLE_KEY_MESSAGE, "client is null");
            sendEvent("websocketFailed", createMap);
            WritableMap createMap2 = Arguments.createMap();
            createMap2.putInt("id", i);
            createMap2.putInt("code", 0);
            createMap2.putString("reason", "client is null");
            sendEvent("websocketClosed", createMap2);
            this.mWebSocketConnections.remove(Integer.valueOf(i));
            this.mContentHandlers.remove(Integer.valueOf(i));
            return;
        }
        try {
            webSocket.send(fVar);
        } catch (Exception e) {
            notifyWebSocketFailed(i, e.getMessage());
        }
    }

    @ReactMethod
    public void ping(int i) {
        WebSocket webSocket = this.mWebSocketConnections.get(Integer.valueOf(i));
        if (webSocket == null) {
            WritableMap createMap = Arguments.createMap();
            createMap.putInt("id", i);
            createMap.putString(BiometricAuthenticationModule.BIOMETRICS_BUNDLE_KEY_MESSAGE, "client is null");
            sendEvent("websocketFailed", createMap);
            WritableMap createMap2 = Arguments.createMap();
            createMap2.putInt("id", i);
            createMap2.putInt("code", 0);
            createMap2.putString("reason", "client is null");
            sendEvent("websocketClosed", createMap2);
            this.mWebSocketConnections.remove(Integer.valueOf(i));
            this.mContentHandlers.remove(Integer.valueOf(i));
            return;
        }
        try {
            webSocket.send(f.f1330b);
        } catch (Exception e) {
            notifyWebSocketFailed(i, e.getMessage());
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void notifyWebSocketFailed(int i, String str) {
        WritableMap createMap = Arguments.createMap();
        createMap.putInt("id", i);
        createMap.putString(BiometricAuthenticationModule.BIOMETRICS_BUNDLE_KEY_MESSAGE, str);
        sendEvent("websocketFailed", createMap);
    }

    private static String getDefaultOrigin(String str) {
        String str2 = "";
        try {
            URI uri = new URI(str);
            if (uri.getScheme().equals("wss")) {
                str2 = str2 + "https";
            } else if (uri.getScheme().equals("ws")) {
                str2 = str2 + "http";
            } else if (uri.getScheme().equals("http") || uri.getScheme().equals("https")) {
                str2 = str2 + uri.getScheme();
            }
            if (uri.getPort() != -1) {
                return String.format("%s://%s:%s", str2, uri.getHost(), Integer.valueOf(uri.getPort()));
            }
            return String.format("%s://%s", str2, uri.getHost());
        } catch (URISyntaxException unused) {
            throw new IllegalArgumentException("Unable to set " + str + " as default origin header");
        }
    }

    private String getCookie(String str) {
        try {
            List<String> list = this.mCookieHandler.get(new URI(getDefaultOrigin(str)), new HashMap()).get("Cookie");
            if (list == null) {
                return null;
            }
            if (list.isEmpty()) {
                return null;
            }
            return list.get(0);
        } catch (IOException | URISyntaxException unused) {
            throw new IllegalArgumentException("Unable to get cookie from " + str);
        }
    }
}
