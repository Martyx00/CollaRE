package com.RNFetchBlob;

import android.net.Uri;
import android.util.Base64;
import c.d;
import com.RNFetchBlob.g;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.google.android.gms.gcm.Task;
import com.google.android.gms.measurement.AppMeasurement;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import okhttp3.MediaType;
import okhttp3.RequestBody;

/* access modifiers changed from: package-private */
/* compiled from: RNFetchBlobBody */
public class a extends RequestBody {

    /* renamed from: a  reason: collision with root package name */
    int f1437a = 0;

    /* renamed from: b  reason: collision with root package name */
    private InputStream f1438b;

    /* renamed from: c  reason: collision with root package name */
    private long f1439c = 0;

    /* renamed from: d  reason: collision with root package name */
    private ReadableArray f1440d;
    private String e;
    private String f;
    private g.a g;
    private MediaType h;
    private File i;
    private Boolean j = false;

    a(String str) {
        this.e = str;
    }

    /* access modifiers changed from: package-private */
    public a a(boolean z) {
        this.j = Boolean.valueOf(z);
        return this;
    }

    /* access modifiers changed from: package-private */
    public a a(MediaType mediaType) {
        this.h = mediaType;
        return this;
    }

    /* access modifiers changed from: package-private */
    public a a(g.a aVar) {
        this.g = aVar;
        return this;
    }

    /* access modifiers changed from: package-private */
    public a a(String str) {
        this.f = str;
        if (this.f == null) {
            this.f = "";
            this.g = g.a.AsIs;
        }
        try {
            switch (this.g) {
                case SingleFile:
                    this.f1438b = b();
                    this.f1439c = (long) this.f1438b.available();
                    break;
                case AsIs:
                    this.f1439c = (long) this.f.getBytes().length;
                    this.f1438b = new ByteArrayInputStream(this.f.getBytes());
                    break;
            }
        } catch (Exception e2) {
            e2.printStackTrace();
            h.b("RNFetchBlob failed to create single content request body :" + e2.getLocalizedMessage() + "\r\n");
        }
        return this;
    }

    /* access modifiers changed from: package-private */
    public a a(ReadableArray readableArray) {
        this.f1440d = readableArray;
        try {
            this.i = c();
            this.f1438b = new FileInputStream(this.i);
            this.f1439c = this.i.length();
        } catch (Exception e2) {
            e2.printStackTrace();
            h.b("RNFetchBlob failed to create request multipart body :" + e2.getLocalizedMessage());
        }
        return this;
    }

    @Override // okhttp3.RequestBody
    public long contentLength() {
        if (this.j.booleanValue()) {
            return -1;
        }
        return this.f1439c;
    }

    @Override // okhttp3.RequestBody
    public MediaType contentType() {
        return this.h;
    }

    @Override // okhttp3.RequestBody
    public void writeTo(d dVar) {
        try {
            a(this.f1438b, dVar);
        } catch (Exception e2) {
            h.b(e2.getLocalizedMessage());
            e2.printStackTrace();
        }
    }

    /* access modifiers changed from: package-private */
    public boolean a() {
        try {
            if (this.i == null || !this.i.exists()) {
                return true;
            }
            this.i.delete();
            return true;
        } catch (Exception e2) {
            h.b(e2.getLocalizedMessage());
            return false;
        }
    }

    private InputStream b() {
        if (this.f.startsWith("RNFetchBlob-file://")) {
            String d2 = d.d(this.f.substring(19));
            if (d.c(d2)) {
                try {
                    return RNFetchBlob.RCTContext.getAssets().open(d2.replace("bundle-assets://", ""));
                } catch (Exception e2) {
                    throw new Exception("error when getting request stream from asset : " + e2.getLocalizedMessage());
                }
            } else {
                File file = new File(d.d(d2));
                try {
                    if (!file.exists()) {
                        file.createNewFile();
                    }
                    return new FileInputStream(file);
                } catch (Exception e3) {
                    throw new Exception("error when getting request stream: " + e3.getLocalizedMessage());
                }
            }
        } else if (this.f.startsWith("RNFetchBlob-content://")) {
            String substring = this.f.substring(22);
            try {
                return RNFetchBlob.RCTContext.getContentResolver().openInputStream(Uri.parse(substring));
            } catch (Exception e4) {
                throw new Exception("error when getting request stream for content URI: " + substring, e4);
            }
        } else {
            try {
                return new ByteArrayInputStream(Base64.decode(this.f, 0));
            } catch (Exception e5) {
                throw new Exception("error when getting request stream: " + e5.getLocalizedMessage());
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0146, code lost:
        if (r6 != null) goto L_0x0148;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0148, code lost:
        r6.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x0170, code lost:
        if (r6 == null) goto L_0x01c6;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.io.File c() {
        /*
        // Method dump skipped, instructions count: 501
        */
        throw new UnsupportedOperationException("Method not decompiled: com.RNFetchBlob.a.c():java.io.File");
    }

    private void a(InputStream inputStream, d dVar) {
        byte[] bArr = new byte[Task.EXTRAS_LIMIT_BYTES];
        long j2 = 0;
        while (true) {
            int read = inputStream.read(bArr, 0, Task.EXTRAS_LIMIT_BYTES);
            if (read > 0) {
                dVar.c(bArr, 0, read);
                j2 += (long) read;
                a(j2);
            } else {
                inputStream.close();
                return;
            }
        }
    }

    private void a(InputStream inputStream, FileOutputStream fileOutputStream) {
        byte[] bArr = new byte[Task.EXTRAS_LIMIT_BYTES];
        while (true) {
            int read = inputStream.read(bArr);
            if (read > 0) {
                fileOutputStream.write(bArr, 0, read);
            } else {
                inputStream.close();
                return;
            }
        }
    }

    private ArrayList<C0031a> d() {
        ArrayList<C0031a> arrayList = new ArrayList<>();
        ReactApplicationContext reactApplicationContext = RNFetchBlob.RCTContext;
        long j2 = 0;
        for (int i2 = 0; i2 < this.f1440d.size(); i2++) {
            C0031a aVar = new C0031a(this.f1440d.getMap(i2));
            arrayList.add(aVar);
            if (aVar.f1445d == null) {
                h.b("RNFetchBlob multipart request builder has found a field without `data` property, the field `" + aVar.f1442a + "` will be removed implicitly.");
            } else if (aVar.f1443b != null) {
                String str = aVar.f1445d;
                if (str.startsWith("RNFetchBlob-file://")) {
                    String d2 = d.d(str.substring(19));
                    if (d.c(d2)) {
                        try {
                            j2 += (long) reactApplicationContext.getAssets().open(d2.replace("bundle-assets://", "")).available();
                        } catch (IOException e2) {
                            h.b(e2.getLocalizedMessage());
                        }
                    } else {
                        j2 += new File(d.d(d2)).length();
                    }
                } else if (str.startsWith("RNFetchBlob-content://")) {
                    String substring = str.substring(22);
                    InputStream inputStream = null;
                    try {
                        inputStream = reactApplicationContext.getContentResolver().openInputStream(Uri.parse(substring));
                        j2 += (long) inputStream.available();
                        if (inputStream == null) {
                        }
                    } catch (Exception e3) {
                        h.b("Failed to estimate form data length from content URI:" + substring + ", " + e3.getLocalizedMessage());
                        if (inputStream == null) {
                        }
                    } catch (Throwable th) {
                        if (inputStream != null) {
                            inputStream.close();
                        }
                        throw th;
                    }
                    inputStream.close();
                } else {
                    j2 += (long) Base64.decode(str, 0).length;
                }
            } else {
                j2 += (long) aVar.f1445d.getBytes().length;
            }
        }
        this.f1439c = j2;
        return arrayList;
    }

    /* access modifiers changed from: private */
    /* renamed from: com.RNFetchBlob.a$a  reason: collision with other inner class name */
    /* compiled from: RNFetchBlobBody */
    public class C0031a {

        /* renamed from: a  reason: collision with root package name */
        public String f1442a;

        /* renamed from: b  reason: collision with root package name */
        String f1443b;

        /* renamed from: c  reason: collision with root package name */
        String f1444c;

        /* renamed from: d  reason: collision with root package name */
        public String f1445d;

        C0031a(ReadableMap readableMap) {
            if (readableMap.hasKey("name")) {
                this.f1442a = readableMap.getString("name");
            }
            if (readableMap.hasKey("filename")) {
                this.f1443b = readableMap.getString("filename");
            }
            if (readableMap.hasKey(AppMeasurement.Param.TYPE)) {
                this.f1444c = readableMap.getString(AppMeasurement.Param.TYPE);
            } else {
                this.f1444c = this.f1443b == null ? "text/plain" : "application/octet-stream";
            }
            if (readableMap.hasKey("data")) {
                this.f1445d = readableMap.getString("data");
            }
        }
    }

    private void a(long j2) {
        f c2 = g.c(this.e);
        if (c2 != null) {
            long j3 = this.f1439c;
            if (j3 != 0 && c2.a(((float) j2) / ((float) j3))) {
                WritableMap createMap = Arguments.createMap();
                createMap.putString("taskId", this.e);
                createMap.putString("written", String.valueOf(j2));
                createMap.putString("total", String.valueOf(this.f1439c));
                ((DeviceEventManagerModule.RCTDeviceEventEmitter) RNFetchBlob.RCTContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)).emit("RNFetchBlobProgress-upload", createMap);
            }
        }
    }
}
