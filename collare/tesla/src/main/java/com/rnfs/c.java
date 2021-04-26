package com.rnfs;

import android.os.AsyncTask;
import android.os.Build;
import java.net.HttpURLConnection;
import java.util.concurrent.atomic.AtomicBoolean;

/* compiled from: Downloader */
public class c extends AsyncTask<a, long[], b> {

    /* renamed from: a  reason: collision with root package name */
    b f4813a;

    /* renamed from: b  reason: collision with root package name */
    private a f4814b;

    /* renamed from: c  reason: collision with root package name */
    private AtomicBoolean f4815c = new AtomicBoolean(false);

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public b doInBackground(a... aVarArr) {
        this.f4814b = aVarArr[0];
        this.f4813a = new b();
        new Thread(new Runnable() {
            /* class com.rnfs.c.AnonymousClass1 */

            public void run() {
                try {
                    c.this.a(c.this.f4814b, c.this.f4813a);
                    c.this.f4814b.g.a(c.this.f4813a);
                } catch (Exception e) {
                    c.this.f4813a.f4812c = e;
                    c.this.f4814b.g.a(c.this.f4813a);
                }
            }
        }).start();
        return this.f4813a;
    }

    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:46:0x00f2 */
    /* JADX DEBUG: Multi-variable search result rejected for r9v0, resolved type: int */
    /* JADX DEBUG: Multi-variable search result rejected for r9v3, resolved type: int */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r9v6 */
    /* JADX WARN: Type inference failed for: r9v8, types: [java.io.OutputStream] */
    /* JADX WARN: Type inference failed for: r9v9 */
    /* JADX WARN: Type inference failed for: r9v10 */
    /* JADX WARN: Type inference failed for: r9v16 */
    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* JADX WARNING: Removed duplicated region for block: B:93:0x01bb  */
    /* JADX WARNING: Removed duplicated region for block: B:95:0x01c0  */
    /* JADX WARNING: Removed duplicated region for block: B:97:0x01c5  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void a(com.rnfs.a r26, com.rnfs.b r27) {
        /*
        // Method dump skipped, instructions count: 457
        */
        throw new UnsupportedOperationException("Method not decompiled: com.rnfs.c.a(com.rnfs.a, com.rnfs.b):void");
    }

    private long a(HttpURLConnection httpURLConnection) {
        if (Build.VERSION.SDK_INT >= 24) {
            return httpURLConnection.getContentLengthLong();
        }
        return (long) httpURLConnection.getContentLength();
    }

    /* access modifiers changed from: protected */
    public void a() {
        this.f4815c.set(true);
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void onProgressUpdate(long[]... jArr) {
        super.onProgressUpdate(jArr);
        this.f4814b.i.a(jArr[0][0], jArr[0][1]);
    }
}
