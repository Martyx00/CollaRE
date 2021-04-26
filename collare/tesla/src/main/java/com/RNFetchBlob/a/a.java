package com.RNFetchBlob.a;

import c.c;
import c.e;
import c.l;
import c.t;
import c.u;
import com.RNFetchBlob.f;
import com.RNFetchBlob.g;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import java.nio.charset.Charset;
import okhttp3.MediaType;
import okhttp3.ResponseBody;

/* compiled from: RNFetchBlobDefaultResp */
public class a extends ResponseBody {

    /* renamed from: a  reason: collision with root package name */
    String f1446a;

    /* renamed from: b  reason: collision with root package name */
    ReactApplicationContext f1447b;

    /* renamed from: c  reason: collision with root package name */
    ResponseBody f1448c;

    /* renamed from: d  reason: collision with root package name */
    boolean f1449d = false;

    public a(ReactApplicationContext reactApplicationContext, String str, ResponseBody responseBody, boolean z) {
        this.f1447b = reactApplicationContext;
        this.f1446a = str;
        this.f1448c = responseBody;
        this.f1449d = z;
    }

    @Override // okhttp3.ResponseBody
    public MediaType contentType() {
        return this.f1448c.contentType();
    }

    @Override // okhttp3.ResponseBody
    public long contentLength() {
        return this.f1448c.contentLength();
    }

    @Override // okhttp3.ResponseBody
    public e source() {
        return l.a(new C0032a(this.f1448c.source()));
    }

    /* renamed from: com.RNFetchBlob.a.a$a  reason: collision with other inner class name */
    /* compiled from: RNFetchBlobDefaultResp */
    private class C0032a implements t {

        /* renamed from: a  reason: collision with root package name */
        e f1450a;

        /* renamed from: b  reason: collision with root package name */
        long f1451b = 0;

        @Override // java.io.Closeable, c.t, java.lang.AutoCloseable
        public void close() {
        }

        @Override // c.t
        public u timeout() {
            return null;
        }

        C0032a(e eVar) {
            this.f1450a = eVar;
        }

        @Override // c.t
        public long read(c cVar, long j) {
            long read = this.f1450a.read(cVar, j);
            this.f1451b += read > 0 ? read : 0;
            f b2 = g.b(a.this.f1446a);
            long contentLength = a.this.contentLength();
            if (!(b2 == null || contentLength == 0 || !b2.a((float) (this.f1451b / a.this.contentLength())))) {
                WritableMap createMap = Arguments.createMap();
                createMap.putString("taskId", a.this.f1446a);
                createMap.putString("written", String.valueOf(this.f1451b));
                createMap.putString("total", String.valueOf(a.this.contentLength()));
                if (a.this.f1449d) {
                    createMap.putString("chunk", cVar.a(Charset.defaultCharset()));
                } else {
                    createMap.putString("chunk", "");
                }
                ((DeviceEventManagerModule.RCTDeviceEventEmitter) a.this.f1447b.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)).emit("RNFetchBlobProgress", createMap);
            }
            return read;
        }
    }
}
