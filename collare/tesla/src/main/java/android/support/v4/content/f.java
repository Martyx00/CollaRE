package android.support.v4.content;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import com.google.android.gms.measurement.AppMeasurement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

/* compiled from: LocalBroadcastManager */
public final class f {
    private static final Object f = new Object();
    private static f g;

    /* renamed from: a  reason: collision with root package name */
    private final Context f366a;

    /* renamed from: b  reason: collision with root package name */
    private final HashMap<BroadcastReceiver, ArrayList<b>> f367b = new HashMap<>();

    /* renamed from: c  reason: collision with root package name */
    private final HashMap<String, ArrayList<b>> f368c = new HashMap<>();

    /* renamed from: d  reason: collision with root package name */
    private final ArrayList<a> f369d = new ArrayList<>();
    private final Handler e;

    /* access modifiers changed from: private */
    /* compiled from: LocalBroadcastManager */
    public static final class b {

        /* renamed from: a  reason: collision with root package name */
        final IntentFilter f373a;

        /* renamed from: b  reason: collision with root package name */
        final BroadcastReceiver f374b;

        /* renamed from: c  reason: collision with root package name */
        boolean f375c;

        /* renamed from: d  reason: collision with root package name */
        boolean f376d;

        b(IntentFilter intentFilter, BroadcastReceiver broadcastReceiver) {
            this.f373a = intentFilter;
            this.f374b = broadcastReceiver;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder(128);
            sb.append("Receiver{");
            sb.append(this.f374b);
            sb.append(" filter=");
            sb.append(this.f373a);
            if (this.f376d) {
                sb.append(" DEAD");
            }
            sb.append("}");
            return sb.toString();
        }
    }

    /* access modifiers changed from: private */
    /* compiled from: LocalBroadcastManager */
    public static final class a {

        /* renamed from: a  reason: collision with root package name */
        final Intent f371a;

        /* renamed from: b  reason: collision with root package name */
        final ArrayList<b> f372b;

        a(Intent intent, ArrayList<b> arrayList) {
            this.f371a = intent;
            this.f372b = arrayList;
        }
    }

    public static f a(Context context) {
        f fVar;
        synchronized (f) {
            if (g == null) {
                g = new f(context.getApplicationContext());
            }
            fVar = g;
        }
        return fVar;
    }

    private f(Context context) {
        this.f366a = context;
        this.e = new Handler(context.getMainLooper()) {
            /* class android.support.v4.content.f.AnonymousClass1 */

            public void handleMessage(Message message) {
                if (message.what != 1) {
                    super.handleMessage(message);
                } else {
                    f.this.a();
                }
            }
        };
    }

    public void a(BroadcastReceiver broadcastReceiver, IntentFilter intentFilter) {
        synchronized (this.f367b) {
            b bVar = new b(intentFilter, broadcastReceiver);
            ArrayList<b> arrayList = this.f367b.get(broadcastReceiver);
            if (arrayList == null) {
                arrayList = new ArrayList<>(1);
                this.f367b.put(broadcastReceiver, arrayList);
            }
            arrayList.add(bVar);
            for (int i = 0; i < intentFilter.countActions(); i++) {
                String action = intentFilter.getAction(i);
                ArrayList<b> arrayList2 = this.f368c.get(action);
                if (arrayList2 == null) {
                    arrayList2 = new ArrayList<>(1);
                    this.f368c.put(action, arrayList2);
                }
                arrayList2.add(bVar);
            }
        }
    }

    public void a(BroadcastReceiver broadcastReceiver) {
        synchronized (this.f367b) {
            ArrayList<b> remove = this.f367b.remove(broadcastReceiver);
            if (remove != null) {
                for (int size = remove.size() - 1; size >= 0; size--) {
                    b bVar = remove.get(size);
                    bVar.f376d = true;
                    for (int i = 0; i < bVar.f373a.countActions(); i++) {
                        String action = bVar.f373a.getAction(i);
                        ArrayList<b> arrayList = this.f368c.get(action);
                        if (arrayList != null) {
                            for (int size2 = arrayList.size() - 1; size2 >= 0; size2--) {
                                b bVar2 = arrayList.get(size2);
                                if (bVar2.f374b == broadcastReceiver) {
                                    bVar2.f376d = true;
                                    arrayList.remove(size2);
                                }
                            }
                            if (arrayList.size() <= 0) {
                                this.f368c.remove(action);
                            }
                        }
                    }
                }
            }
        }
    }

    public boolean a(Intent intent) {
        String str;
        ArrayList<b> arrayList;
        int i;
        String str2;
        ArrayList arrayList2;
        String str3;
        synchronized (this.f367b) {
            String action = intent.getAction();
            String resolveTypeIfNeeded = intent.resolveTypeIfNeeded(this.f366a.getContentResolver());
            Uri data = intent.getData();
            String scheme = intent.getScheme();
            Set<String> categories = intent.getCategories();
            boolean z = (intent.getFlags() & 8) != 0;
            if (z) {
                Log.v("LocalBroadcastManager", "Resolving type " + resolveTypeIfNeeded + " scheme " + scheme + " of intent " + intent);
            }
            ArrayList<b> arrayList3 = this.f368c.get(intent.getAction());
            if (arrayList3 != null) {
                if (z) {
                    Log.v("LocalBroadcastManager", "Action list: " + arrayList3);
                }
                ArrayList arrayList4 = null;
                int i2 = 0;
                while (i2 < arrayList3.size()) {
                    b bVar = arrayList3.get(i2);
                    if (z) {
                        Log.v("LocalBroadcastManager", "Matching against filter " + bVar.f373a);
                    }
                    if (!bVar.f375c) {
                        str2 = action;
                        arrayList2 = arrayList4;
                        i = i2;
                        arrayList = arrayList3;
                        str = resolveTypeIfNeeded;
                        int match = bVar.f373a.match(action, resolveTypeIfNeeded, scheme, data, categories, "LocalBroadcastManager");
                        if (match >= 0) {
                            if (z) {
                                Log.v("LocalBroadcastManager", "  Filter matched!  match=0x" + Integer.toHexString(match));
                            }
                            arrayList4 = arrayList2 == null ? new ArrayList() : arrayList2;
                            arrayList4.add(bVar);
                            bVar.f375c = true;
                            i2 = i + 1;
                            action = str2;
                            arrayList3 = arrayList;
                            resolveTypeIfNeeded = str;
                        } else if (z) {
                            switch (match) {
                                case -4:
                                    str3 = "category";
                                    break;
                                case -3:
                                    str3 = "action";
                                    break;
                                case -2:
                                    str3 = "data";
                                    break;
                                case -1:
                                    str3 = AppMeasurement.Param.TYPE;
                                    break;
                                default:
                                    str3 = "unknown reason";
                                    break;
                            }
                            Log.v("LocalBroadcastManager", "  Filter did not match: " + str3);
                        }
                    } else if (z) {
                        Log.v("LocalBroadcastManager", "  Filter's target already added");
                        i = i2;
                        arrayList = arrayList3;
                        str2 = action;
                        str = resolveTypeIfNeeded;
                        arrayList2 = arrayList4;
                    } else {
                        i = i2;
                        arrayList = arrayList3;
                        str2 = action;
                        str = resolveTypeIfNeeded;
                        arrayList2 = arrayList4;
                    }
                    arrayList4 = arrayList2;
                    i2 = i + 1;
                    action = str2;
                    arrayList3 = arrayList;
                    resolveTypeIfNeeded = str;
                }
                if (arrayList4 != null) {
                    for (int i3 = 0; i3 < arrayList4.size(); i3++) {
                        ((b) arrayList4.get(i3)).f375c = false;
                    }
                    this.f369d.add(new a(intent, arrayList4));
                    if (!this.e.hasMessages(1)) {
                        this.e.sendEmptyMessage(1);
                    }
                    return true;
                }
            }
            return false;
        }
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x001d, code lost:
        if (r2 >= r1.length) goto L_0x0000;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x001f, code lost:
        r3 = r1[r2];
        r4 = r3.f372b.size();
        r5 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0028, code lost:
        if (r5 >= r4) goto L_0x0042;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x002a, code lost:
        r6 = r3.f372b.get(r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0034, code lost:
        if (r6.f376d != false) goto L_0x003f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0036, code lost:
        r6.f374b.onReceive(r9.f366a, r3.f371a);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x003f, code lost:
        r5 = r5 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0042, code lost:
        r2 = r2 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x001a, code lost:
        r2 = 0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a() {
        /*
            r9 = this;
        L_0x0000:
            java.util.HashMap<android.content.BroadcastReceiver, java.util.ArrayList<android.support.v4.content.f$b>> r0 = r9.f367b
            monitor-enter(r0)
            java.util.ArrayList<android.support.v4.content.f$a> r1 = r9.f369d     // Catch:{ all -> 0x0045 }
            int r1 = r1.size()     // Catch:{ all -> 0x0045 }
            if (r1 > 0) goto L_0x000d
            monitor-exit(r0)     // Catch:{ all -> 0x0045 }
            return
        L_0x000d:
            android.support.v4.content.f$a[] r1 = new android.support.v4.content.f.a[r1]     // Catch:{ all -> 0x0045 }
            java.util.ArrayList<android.support.v4.content.f$a> r2 = r9.f369d     // Catch:{ all -> 0x0045 }
            r2.toArray(r1)     // Catch:{ all -> 0x0045 }
            java.util.ArrayList<android.support.v4.content.f$a> r2 = r9.f369d     // Catch:{ all -> 0x0045 }
            r2.clear()     // Catch:{ all -> 0x0045 }
            monitor-exit(r0)     // Catch:{ all -> 0x0045 }
            r0 = 0
            r2 = 0
        L_0x001c:
            int r3 = r1.length
            if (r2 >= r3) goto L_0x0000
            r3 = r1[r2]
            java.util.ArrayList<android.support.v4.content.f$b> r4 = r3.f372b
            int r4 = r4.size()
            r5 = 0
        L_0x0028:
            if (r5 >= r4) goto L_0x0042
            java.util.ArrayList<android.support.v4.content.f$b> r6 = r3.f372b
            java.lang.Object r6 = r6.get(r5)
            android.support.v4.content.f$b r6 = (android.support.v4.content.f.b) r6
            boolean r7 = r6.f376d
            if (r7 != 0) goto L_0x003f
            android.content.BroadcastReceiver r6 = r6.f374b
            android.content.Context r7 = r9.f366a
            android.content.Intent r8 = r3.f371a
            r6.onReceive(r7, r8)
        L_0x003f:
            int r5 = r5 + 1
            goto L_0x0028
        L_0x0042:
            int r2 = r2 + 1
            goto L_0x001c
        L_0x0045:
            r1 = move-exception
            monitor-exit(r0)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.content.f.a():void");
    }
}
