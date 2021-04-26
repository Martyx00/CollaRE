package com.RNFetchBlob;

import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;

/* access modifiers changed from: package-private */
/* compiled from: RNFetchBlobConfig */
public class b {

    /* renamed from: a  reason: collision with root package name */
    public Boolean f1458a;

    /* renamed from: b  reason: collision with root package name */
    public String f1459b;

    /* renamed from: c  reason: collision with root package name */
    public String f1460c;

    /* renamed from: d  reason: collision with root package name */
    public ReadableMap f1461d;
    public Boolean e;
    public String f;
    public String g;
    public Boolean h;
    public Boolean i = true;
    public long j = 60000;
    public Boolean k;
    public Boolean l;
    public ReadableArray m;

    b(ReadableMap readableMap) {
        boolean z = false;
        this.k = false;
        this.l = true;
        String str = null;
        this.m = null;
        if (readableMap != null) {
            this.f1458a = Boolean.valueOf(readableMap.hasKey("fileCache") ? readableMap.getBoolean("fileCache") : false);
            this.f1459b = readableMap.hasKey("path") ? readableMap.getString("path") : null;
            this.f1460c = readableMap.hasKey("appendExt") ? readableMap.getString("appendExt") : "";
            this.e = Boolean.valueOf(readableMap.hasKey("trusty") ? readableMap.getBoolean("trusty") : false);
            if (readableMap.hasKey("addAndroidDownloads")) {
                this.f1461d = readableMap.getMap("addAndroidDownloads");
            }
            if (readableMap.hasKey("binaryContentTypes")) {
                this.m = readableMap.getArray("binaryContentTypes");
            }
            String str2 = this.f1459b;
            if (str2 != null && str2.toLowerCase().contains("?append=true")) {
                this.i = false;
            }
            if (readableMap.hasKey("overwrite")) {
                this.i = Boolean.valueOf(readableMap.getBoolean("overwrite"));
            }
            if (readableMap.hasKey("followRedirect")) {
                this.l = Boolean.valueOf(readableMap.getBoolean("followRedirect"));
            }
            this.f = readableMap.hasKey("key") ? readableMap.getString("key") : null;
            this.g = readableMap.hasKey("contentType") ? readableMap.getString("contentType") : str;
            this.k = Boolean.valueOf(readableMap.hasKey("increment") ? readableMap.getBoolean("increment") : false);
            this.h = Boolean.valueOf(readableMap.hasKey("auto") ? readableMap.getBoolean("auto") : z);
            if (readableMap.hasKey("timeout")) {
                this.j = (long) readableMap.getInt("timeout");
            }
        }
    }
}
