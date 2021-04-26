package com.rnfs;

import android.os.AsyncTask;
import android.webkit.MimeTypeMap;
import java.util.concurrent.atomic.AtomicBoolean;

/* compiled from: Uploader */
public class h extends AsyncTask<f, int[], g> {

    /* renamed from: a  reason: collision with root package name */
    private f f4826a;

    /* renamed from: b  reason: collision with root package name */
    private g f4827b;

    /* renamed from: c  reason: collision with root package name */
    private AtomicBoolean f4828c = new AtomicBoolean(false);

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public g doInBackground(f... fVarArr) {
        this.f4826a = fVarArr[0];
        this.f4827b = new g();
        new Thread(new Runnable() {
            /* class com.rnfs.h.AnonymousClass1 */

            public void run() {
                try {
                    h.this.a(h.this.f4826a, h.this.f4827b);
                    h.this.f4826a.f.a(h.this.f4827b);
                } catch (Exception e) {
                    h.this.f4827b.f4824c = e;
                    h.this.f4826a.f.a(h.this.f4827b);
                }
            }
        }).start();
        return this.f4827b;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* JADX WARNING: Removed duplicated region for block: B:80:0x033f  */
    /* JADX WARNING: Removed duplicated region for block: B:82:0x0344  */
    /* JADX WARNING: Removed duplicated region for block: B:84:0x0349  */
    /* JADX WARNING: Removed duplicated region for block: B:86:0x034e  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void a(com.rnfs.f r22, com.rnfs.g r23) {
        /*
        // Method dump skipped, instructions count: 850
        */
        throw new UnsupportedOperationException("Method not decompiled: com.rnfs.h.a(com.rnfs.f, com.rnfs.g):void");
    }

    /* access modifiers changed from: protected */
    public String a(String str) {
        String fileExtensionFromUrl = MimeTypeMap.getFileExtensionFromUrl(str);
        String mimeTypeFromExtension = fileExtensionFromUrl != null ? MimeTypeMap.getSingleton().getMimeTypeFromExtension(fileExtensionFromUrl.toLowerCase()) : null;
        return mimeTypeFromExtension == null ? "*/*" : mimeTypeFromExtension;
    }

    /* access modifiers changed from: protected */
    public void a() {
        this.f4828c.set(true);
    }
}
