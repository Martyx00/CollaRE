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
import java.io.File;
import java.io.FileOutputStream;
import okhttp3.MediaType;
import okhttp3.ResponseBody;

/* compiled from: RNFetchBlobFileResp */
public class b extends ResponseBody {
    static final /* synthetic */ boolean g = (!b.class.desiredAssertionStatus());

    /* renamed from: a  reason: collision with root package name */
    String f1453a;

    /* renamed from: b  reason: collision with root package name */
    ResponseBody f1454b;

    /* renamed from: c  reason: collision with root package name */
    String f1455c;

    /* renamed from: d  reason: collision with root package name */
    long f1456d = 0;
    ReactApplicationContext e;
    FileOutputStream f;

    public b(ReactApplicationContext reactApplicationContext, String str, ResponseBody responseBody, String str2, boolean z) {
        this.e = reactApplicationContext;
        this.f1453a = str;
        this.f1454b = responseBody;
        if (g || str2 != null) {
            this.f1455c = str2;
            if (str2 != null) {
                boolean z2 = !z;
                String replace = str2.replace("?append=true", "");
                this.f1455c = replace;
                File file = new File(replace);
                File parentFile = file.getParentFile();
                if (parentFile == null || parentFile.exists() || parentFile.mkdirs()) {
                    if (!file.exists()) {
                        file.createNewFile();
                    }
                    this.f = new FileOutputStream(new File(replace), z2);
                    return;
                }
                throw new IllegalStateException("Couldn't create dir: " + parentFile);
            }
            return;
        }
        throw new AssertionError();
    }

    @Override // okhttp3.ResponseBody
    public MediaType contentType() {
        return this.f1454b.contentType();
    }

    @Override // okhttp3.ResponseBody
    public long contentLength() {
        return this.f1454b.contentLength();
    }

    @Override // okhttp3.ResponseBody
    public e source() {
        return l.a(new a());
    }

    /* compiled from: RNFetchBlobFileResp */
    private class a implements t {
        @Override // c.t
        public u timeout() {
            return null;
        }

        private a() {
        }

        @Override // c.t
        public long read(c cVar, long j) {
            int i = (int) j;
            try {
                byte[] bArr = new byte[i];
                long read = (long) b.this.f1454b.byteStream().read(bArr, 0, i);
                int i2 = (read > 0 ? 1 : (read == 0 ? 0 : -1));
                b.this.f1456d += i2 > 0 ? read : 0;
                if (i2 > 0) {
                    b.this.f.write(bArr, 0, (int) read);
                }
                f b2 = g.b(b.this.f1453a);
                if (!(b2 == null || b.this.contentLength() == 0 || !b2.a((float) (b.this.f1456d / b.this.contentLength())))) {
                    WritableMap createMap = Arguments.createMap();
                    createMap.putString("taskId", b.this.f1453a);
                    createMap.putString("written", String.valueOf(b.this.f1456d));
                    createMap.putString("total", String.valueOf(b.this.contentLength()));
                    ((DeviceEventManagerModule.RCTDeviceEventEmitter) b.this.e.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)).emit("RNFetchBlobProgress", createMap);
                }
                return read;
            } catch (Exception unused) {
                return -1;
            }
        }

        @Override // java.io.Closeable, c.t, java.lang.AutoCloseable
        public void close() {
            b.this.f.close();
        }
    }
}
