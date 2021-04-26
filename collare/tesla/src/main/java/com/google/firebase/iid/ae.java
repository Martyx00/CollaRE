package com.google.firebase.iid;

import android.support.v4.util.a;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.tasks.TaskCompletionSource;
import ezvcard.property.Gender;
import java.io.IOException;
import java.util.Map;

/* access modifiers changed from: package-private */
public final class ae {

    /* renamed from: a  reason: collision with root package name */
    private int f3870a = 0;

    /* renamed from: b  reason: collision with root package name */
    private final Map<Integer, TaskCompletionSource<Void>> f3871b = new a();

    /* renamed from: c  reason: collision with root package name */
    private final z f3872c;

    ae(z zVar) {
        this.f3872c = zVar;
    }

    /* access modifiers changed from: package-private */
    public final synchronized boolean a() {
        return b() != null;
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x001c, code lost:
        if (a(r5, r0) != false) goto L_0x0020;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x001e, code lost:
        return false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0020, code lost:
        monitor-enter(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:?, code lost:
        r2 = r4.f3871b.remove(java.lang.Integer.valueOf(r4.f3870a));
        a(r0);
        r4.f3870a++;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0037, code lost:
        monitor-exit(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0038, code lost:
        if (r2 == null) goto L_0x0000;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x003a, code lost:
        r2.setResult(null);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean a(com.google.firebase.iid.FirebaseInstanceId r5) {
        /*
            r4 = this;
        L_0x0000:
            monitor-enter(r4)
            java.lang.String r0 = r4.b()     // Catch:{ all -> 0x0042 }
            r1 = 1
            if (r0 != 0) goto L_0x0017
            boolean r5 = com.google.firebase.iid.FirebaseInstanceId.h()     // Catch:{ all -> 0x0042 }
            if (r5 == 0) goto L_0x0015
            java.lang.String r5 = "FirebaseInstanceId"
            java.lang.String r0 = "topic sync succeeded"
            android.util.Log.d(r5, r0)     // Catch:{ all -> 0x0042 }
        L_0x0015:
            monitor-exit(r4)     // Catch:{ all -> 0x0042 }
            return r1
        L_0x0017:
            monitor-exit(r4)     // Catch:{ all -> 0x0042 }
            boolean r2 = a(r5, r0)
            if (r2 != 0) goto L_0x0020
            r5 = 0
            return r5
        L_0x0020:
            monitor-enter(r4)
            java.util.Map<java.lang.Integer, com.google.android.gms.tasks.TaskCompletionSource<java.lang.Void>> r2 = r4.f3871b     // Catch:{ all -> 0x003f }
            int r3 = r4.f3870a     // Catch:{ all -> 0x003f }
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)     // Catch:{ all -> 0x003f }
            java.lang.Object r2 = r2.remove(r3)     // Catch:{ all -> 0x003f }
            com.google.android.gms.tasks.TaskCompletionSource r2 = (com.google.android.gms.tasks.TaskCompletionSource) r2     // Catch:{ all -> 0x003f }
            r4.a(r0)     // Catch:{ all -> 0x003f }
            int r0 = r4.f3870a     // Catch:{ all -> 0x003f }
            int r0 = r0 + r1
            r4.f3870a = r0     // Catch:{ all -> 0x003f }
            monitor-exit(r4)     // Catch:{ all -> 0x003f }
            if (r2 == 0) goto L_0x0000
            r0 = 0
            r2.setResult(r0)
            goto L_0x0000
        L_0x003f:
            r5 = move-exception
            monitor-exit(r4)
            throw r5
        L_0x0042:
            r5 = move-exception
            monitor-exit(r4)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.iid.ae.a(com.google.firebase.iid.FirebaseInstanceId):boolean");
    }

    private final String b() {
        String a2;
        synchronized (this.f3872c) {
            a2 = this.f3872c.a();
        }
        if (TextUtils.isEmpty(a2)) {
            return null;
        }
        String[] split = a2.split(",");
        if (split.length <= 1 || TextUtils.isEmpty(split[1])) {
            return null;
        }
        return split[1];
    }

    private final synchronized boolean a(String str) {
        synchronized (this.f3872c) {
            String a2 = this.f3872c.a();
            String valueOf = String.valueOf(",");
            String valueOf2 = String.valueOf(str);
            if (!a2.startsWith(valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf))) {
                return false;
            }
            String valueOf3 = String.valueOf(",");
            String valueOf4 = String.valueOf(str);
            this.f3872c.a(a2.substring((valueOf4.length() != 0 ? valueOf3.concat(valueOf4) : new String(valueOf3)).length()));
            return true;
        }
    }

    private static boolean a(FirebaseInstanceId firebaseInstanceId, String str) {
        String[] split = str.split("!");
        if (split.length == 2) {
            String str2 = split[0];
            String str3 = split[1];
            char c2 = 65535;
            try {
                int hashCode = str2.hashCode();
                if (hashCode != 83) {
                    if (hashCode == 85) {
                        if (str2.equals(Gender.UNKNOWN)) {
                            c2 = 1;
                        }
                    }
                } else if (str2.equals("S")) {
                    c2 = 0;
                }
                switch (c2) {
                    case 0:
                        firebaseInstanceId.a(str3);
                        if (FirebaseInstanceId.h()) {
                            Log.d("FirebaseInstanceId", "subscribe operation succeeded");
                            break;
                        }
                        break;
                    case 1:
                        firebaseInstanceId.b(str3);
                        if (FirebaseInstanceId.h()) {
                            Log.d("FirebaseInstanceId", "unsubscribe operation succeeded");
                            break;
                        }
                        break;
                }
            } catch (IOException e) {
                String valueOf = String.valueOf(e.getMessage());
                Log.e("FirebaseInstanceId", valueOf.length() != 0 ? "Topic sync failed: ".concat(valueOf) : new String("Topic sync failed: "));
                return false;
            }
        }
        return true;
    }
}
