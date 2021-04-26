package com.facebook.react.modules.blob;

import android.content.res.Resources;
import android.database.Cursor;
import android.net.Uri;
import android.webkit.MimeTypeMap;
import c.f;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.common.e;
import com.facebook.react.module.a.a;
import com.facebook.react.modules.network.NetworkingModule;
import com.facebook.react.modules.websocket.WebSocketModule;
import com.google.android.gms.measurement.AppMeasurement;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

@a(a = BlobModule.NAME)
public class BlobModule extends ReactContextBaseJavaModule {
    protected static final String NAME = "BlobModule";
    private final Map<String, byte[]> mBlobs = new HashMap();
    private final NetworkingModule.a mNetworkingRequestBodyHandler = new NetworkingModule.a() {
        /* class com.facebook.react.modules.blob.BlobModule.AnonymousClass3 */

        @Override // com.facebook.react.modules.network.NetworkingModule.a
        public boolean a(ReadableMap readableMap) {
            return readableMap.hasKey("blob");
        }

        @Override // com.facebook.react.modules.network.NetworkingModule.a
        public RequestBody a(ReadableMap readableMap, String str) {
            if (readableMap.hasKey(AppMeasurement.Param.TYPE) && !readableMap.getString(AppMeasurement.Param.TYPE).isEmpty()) {
                str = readableMap.getString(AppMeasurement.Param.TYPE);
            }
            if (str == null) {
                str = "application/octet-stream";
            }
            ReadableMap map = readableMap.getMap("blob");
            return RequestBody.create(MediaType.parse(str), BlobModule.this.resolve(map.getString("blobId"), map.getInt("offset"), map.getInt("size")));
        }
    };
    private final NetworkingModule.b mNetworkingResponseHandler = new NetworkingModule.b() {
        /* class com.facebook.react.modules.blob.BlobModule.AnonymousClass4 */

        @Override // com.facebook.react.modules.network.NetworkingModule.b
        public boolean a(String str) {
            return "blob".equals(str);
        }

        @Override // com.facebook.react.modules.network.NetworkingModule.b
        public WritableMap a(ResponseBody responseBody) {
            byte[] bytes = responseBody.bytes();
            WritableMap createMap = Arguments.createMap();
            createMap.putString("blobId", BlobModule.this.store(bytes));
            createMap.putInt("offset", 0);
            createMap.putInt("size", bytes.length);
            return createMap;
        }
    };
    private final NetworkingModule.c mNetworkingUriHandler = new NetworkingModule.c() {
        /* class com.facebook.react.modules.blob.BlobModule.AnonymousClass2 */

        @Override // com.facebook.react.modules.network.NetworkingModule.c
        public boolean a(Uri uri, String str) {
            String scheme = uri.getScheme();
            if (("http".equals(scheme) || "https".equals(scheme)) || !"blob".equals(str)) {
                return false;
            }
            return true;
        }

        @Override // com.facebook.react.modules.network.NetworkingModule.c
        public WritableMap a(Uri uri) {
            byte[] bytesFromUri = BlobModule.this.getBytesFromUri(uri);
            WritableMap createMap = Arguments.createMap();
            createMap.putString("blobId", BlobModule.this.store(bytesFromUri));
            createMap.putInt("offset", 0);
            createMap.putInt("size", bytesFromUri.length);
            createMap.putString(AppMeasurement.Param.TYPE, BlobModule.this.getMimeTypeFromUri(uri));
            createMap.putString("name", BlobModule.this.getNameFromUri(uri));
            createMap.putDouble("lastModified", (double) BlobModule.this.getLastModifiedFromUri(uri));
            return createMap;
        }
    };
    private final WebSocketModule.a mWebSocketContentHandler = new WebSocketModule.a() {
        /* class com.facebook.react.modules.blob.BlobModule.AnonymousClass1 */

        @Override // com.facebook.react.modules.websocket.WebSocketModule.a
        public void a(String str, WritableMap writableMap) {
            writableMap.putString("data", str);
        }

        @Override // com.facebook.react.modules.websocket.WebSocketModule.a
        public void a(f fVar, WritableMap writableMap) {
            byte[] i = fVar.i();
            WritableMap createMap = Arguments.createMap();
            createMap.putString("blobId", BlobModule.this.store(i));
            createMap.putInt("offset", 0);
            createMap.putInt("size", i.length);
            writableMap.putMap("data", createMap);
            writableMap.putString(AppMeasurement.Param.TYPE, "blob");
        }
    };

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return NAME;
    }

    public BlobModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }

    @Override // com.facebook.react.bridge.BaseJavaModule
    public Map<String, Object> getConstants() {
        Resources resources = getReactApplicationContext().getResources();
        int identifier = resources.getIdentifier("blob_provider_authority", "string", getReactApplicationContext().getPackageName());
        if (identifier == 0) {
            return null;
        }
        return e.a("BLOB_URI_SCHEME", FirebaseAnalytics.b.CONTENT, "BLOB_URI_HOST", resources.getString(identifier));
    }

    public String store(byte[] bArr) {
        String uuid = UUID.randomUUID().toString();
        store(bArr, uuid);
        return uuid;
    }

    public void store(byte[] bArr, String str) {
        this.mBlobs.put(str, bArr);
    }

    public void remove(String str) {
        this.mBlobs.remove(str);
    }

    public byte[] resolve(Uri uri) {
        String lastPathSegment = uri.getLastPathSegment();
        String queryParameter = uri.getQueryParameter("offset");
        int parseInt = queryParameter != null ? Integer.parseInt(queryParameter, 10) : 0;
        String queryParameter2 = uri.getQueryParameter("size");
        return resolve(lastPathSegment, parseInt, queryParameter2 != null ? Integer.parseInt(queryParameter2, 10) : -1);
    }

    public byte[] resolve(String str, int i, int i2) {
        byte[] bArr = this.mBlobs.get(str);
        if (bArr == null) {
            return null;
        }
        if (i2 == -1) {
            i2 = bArr.length - i;
        }
        return (i > 0 || i2 != bArr.length) ? Arrays.copyOfRange(bArr, i, i2 + i) : bArr;
    }

    public byte[] resolve(ReadableMap readableMap) {
        return resolve(readableMap.getString("blobId"), readableMap.getInt("offset"), readableMap.getInt("size"));
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private byte[] getBytesFromUri(Uri uri) {
        InputStream openInputStream = getReactApplicationContext().getContentResolver().openInputStream(uri);
        if (openInputStream != null) {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] bArr = new byte[1024];
            while (true) {
                int read = openInputStream.read(bArr);
                if (read == -1) {
                    return byteArrayOutputStream.toByteArray();
                }
                byteArrayOutputStream.write(bArr, 0, read);
            }
        } else {
            throw new FileNotFoundException("File not found for " + uri);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private String getNameFromUri(Uri uri) {
        if ("file".equals(uri.getScheme())) {
            return uri.getLastPathSegment();
        }
        Cursor query = getReactApplicationContext().getContentResolver().query(uri, new String[]{"_display_name"}, null, null, null);
        if (query != null) {
            try {
                if (query.moveToFirst()) {
                    return query.getString(0);
                }
                query.close();
            } finally {
                query.close();
            }
        }
        return uri.getLastPathSegment();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private long getLastModifiedFromUri(Uri uri) {
        if ("file".equals(uri.getScheme())) {
            return new File(uri.toString()).lastModified();
        }
        return 0;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private String getMimeTypeFromUri(Uri uri) {
        String fileExtensionFromUrl;
        String type = getReactApplicationContext().getContentResolver().getType(uri);
        if (type == null && (fileExtensionFromUrl = MimeTypeMap.getFileExtensionFromUrl(uri.getPath())) != null) {
            type = MimeTypeMap.getSingleton().getMimeTypeFromExtension(fileExtensionFromUrl);
        }
        return type == null ? "" : type;
    }

    private WebSocketModule getWebSocketModule() {
        return (WebSocketModule) getReactApplicationContext().getNativeModule(WebSocketModule.class);
    }

    @ReactMethod
    public void addNetworkingHandler() {
        NetworkingModule networkingModule = (NetworkingModule) getReactApplicationContext().getNativeModule(NetworkingModule.class);
        networkingModule.addUriHandler(this.mNetworkingUriHandler);
        networkingModule.addRequestBodyHandler(this.mNetworkingRequestBodyHandler);
        networkingModule.addResponseHandler(this.mNetworkingResponseHandler);
    }

    @ReactMethod
    public void addWebSocketHandler(int i) {
        getWebSocketModule().setContentHandler(i, this.mWebSocketContentHandler);
    }

    @ReactMethod
    public void removeWebSocketHandler(int i) {
        getWebSocketModule().setContentHandler(i, null);
    }

    @ReactMethod
    public void sendOverSocket(ReadableMap readableMap, int i) {
        byte[] resolve = resolve(readableMap.getString("blobId"), readableMap.getInt("offset"), readableMap.getInt("size"));
        if (resolve != null) {
            getWebSocketModule().sendBinary(f.a(resolve), i);
        } else {
            getWebSocketModule().sendBinary((f) null, i);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x0061  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0077  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0044 A[SYNTHETIC] */
    @com.facebook.react.bridge.ReactMethod
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void createFromParts(com.facebook.react.bridge.ReadableArray r10, java.lang.String r11) {
        /*
        // Method dump skipped, instructions count: 184
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.modules.blob.BlobModule.createFromParts(com.facebook.react.bridge.ReadableArray, java.lang.String):void");
    }

    @ReactMethod
    public void release(String str) {
        remove(str);
    }
}
