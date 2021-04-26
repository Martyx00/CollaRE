package android.support.v7.widget;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.database.DataSetObservable;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.util.Log;
import android.util.Xml;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* access modifiers changed from: package-private */
/* compiled from: ActivityChooserModel */
public class d extends DataSetObservable {

    /* renamed from: a  reason: collision with root package name */
    static final String f1229a = "d";
    private static final Object e = new Object();
    private static final Map<String, d> f = new HashMap();

    /* renamed from: b  reason: collision with root package name */
    final Context f1230b;

    /* renamed from: c  reason: collision with root package name */
    final String f1231c;

    /* renamed from: d  reason: collision with root package name */
    boolean f1232d;
    private final Object g;
    private final List<a> h;
    private final List<c> i;
    private Intent j;
    private b k;
    private int l;
    private boolean m;
    private boolean n;
    private boolean o;
    private AbstractC0029d p;

    /* compiled from: ActivityChooserModel */
    public interface b {
        void a(Intent intent, List<a> list, List<c> list2);
    }

    /* renamed from: android.support.v7.widget.d$d  reason: collision with other inner class name */
    /* compiled from: ActivityChooserModel */
    public interface AbstractC0029d {
        boolean a(d dVar, Intent intent);
    }

    public int a() {
        int size;
        synchronized (this.g) {
            d();
            size = this.h.size();
        }
        return size;
    }

    public ResolveInfo a(int i2) {
        ResolveInfo resolveInfo;
        synchronized (this.g) {
            d();
            resolveInfo = this.h.get(i2).f1233a;
        }
        return resolveInfo;
    }

    public int a(ResolveInfo resolveInfo) {
        synchronized (this.g) {
            d();
            List<a> list = this.h;
            int size = list.size();
            for (int i2 = 0; i2 < size; i2++) {
                if (list.get(i2).f1233a == resolveInfo) {
                    return i2;
                }
            }
            return -1;
        }
    }

    public Intent b(int i2) {
        synchronized (this.g) {
            if (this.j == null) {
                return null;
            }
            d();
            a aVar = this.h.get(i2);
            ComponentName componentName = new ComponentName(aVar.f1233a.activityInfo.packageName, aVar.f1233a.activityInfo.name);
            Intent intent = new Intent(this.j);
            intent.setComponent(componentName);
            if (this.p != null) {
                if (this.p.a(this, new Intent(intent))) {
                    return null;
                }
            }
            a(new c(componentName, System.currentTimeMillis(), 1.0f));
            return intent;
        }
    }

    public ResolveInfo b() {
        synchronized (this.g) {
            d();
            if (this.h.isEmpty()) {
                return null;
            }
            return this.h.get(0).f1233a;
        }
    }

    public void c(int i2) {
        synchronized (this.g) {
            d();
            a aVar = this.h.get(i2);
            a aVar2 = this.h.get(0);
            a(new c(new ComponentName(aVar.f1233a.activityInfo.packageName, aVar.f1233a.activityInfo.name), System.currentTimeMillis(), aVar2 != null ? (aVar2.f1234b - aVar.f1234b) + 5.0f : 1.0f));
        }
    }

    private void c() {
        if (!this.m) {
            throw new IllegalStateException("No preceding call to #readHistoricalData");
        } else if (this.n) {
            this.n = false;
            if (!TextUtils.isEmpty(this.f1231c)) {
                new e().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new ArrayList(this.i), this.f1231c);
            }
        }
    }

    private void d() {
        boolean f2 = f() | g();
        h();
        if (f2) {
            e();
            notifyChanged();
        }
    }

    private boolean e() {
        if (this.k == null || this.j == null || this.h.isEmpty() || this.i.isEmpty()) {
            return false;
        }
        this.k.a(this.j, this.h, Collections.unmodifiableList(this.i));
        return true;
    }

    private boolean f() {
        if (!this.o || this.j == null) {
            return false;
        }
        this.o = false;
        this.h.clear();
        List<ResolveInfo> queryIntentActivities = this.f1230b.getPackageManager().queryIntentActivities(this.j, 0);
        int size = queryIntentActivities.size();
        for (int i2 = 0; i2 < size; i2++) {
            this.h.add(new a(queryIntentActivities.get(i2)));
        }
        return true;
    }

    private boolean g() {
        if (!this.f1232d || !this.n || TextUtils.isEmpty(this.f1231c)) {
            return false;
        }
        this.f1232d = false;
        this.m = true;
        i();
        return true;
    }

    private boolean a(c cVar) {
        boolean add = this.i.add(cVar);
        if (add) {
            this.n = true;
            h();
            c();
            e();
            notifyChanged();
        }
        return add;
    }

    private void h() {
        int size = this.i.size() - this.l;
        if (size > 0) {
            this.n = true;
            for (int i2 = 0; i2 < size; i2++) {
                this.i.remove(0);
            }
        }
    }

    /* compiled from: ActivityChooserModel */
    public static final class c {

        /* renamed from: a  reason: collision with root package name */
        public final ComponentName f1235a;

        /* renamed from: b  reason: collision with root package name */
        public final long f1236b;

        /* renamed from: c  reason: collision with root package name */
        public final float f1237c;

        public c(String str, long j, float f) {
            this(ComponentName.unflattenFromString(str), j, f);
        }

        public c(ComponentName componentName, long j, float f) {
            this.f1235a = componentName;
            this.f1236b = j;
            this.f1237c = f;
        }

        public int hashCode() {
            ComponentName componentName = this.f1235a;
            int hashCode = componentName == null ? 0 : componentName.hashCode();
            long j = this.f1236b;
            return ((((hashCode + 31) * 31) + ((int) (j ^ (j >>> 32)))) * 31) + Float.floatToIntBits(this.f1237c);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            c cVar = (c) obj;
            ComponentName componentName = this.f1235a;
            if (componentName == null) {
                if (cVar.f1235a != null) {
                    return false;
                }
            } else if (!componentName.equals(cVar.f1235a)) {
                return false;
            }
            return this.f1236b == cVar.f1236b && Float.floatToIntBits(this.f1237c) == Float.floatToIntBits(cVar.f1237c);
        }

        public String toString() {
            return "[" + "; activity:" + this.f1235a + "; time:" + this.f1236b + "; weight:" + new BigDecimal((double) this.f1237c) + "]";
        }
    }

    /* compiled from: ActivityChooserModel */
    public static final class a implements Comparable<a> {

        /* renamed from: a  reason: collision with root package name */
        public final ResolveInfo f1233a;

        /* renamed from: b  reason: collision with root package name */
        public float f1234b;

        public a(ResolveInfo resolveInfo) {
            this.f1233a = resolveInfo;
        }

        public int hashCode() {
            return Float.floatToIntBits(this.f1234b) + 31;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return obj != null && getClass() == obj.getClass() && Float.floatToIntBits(this.f1234b) == Float.floatToIntBits(((a) obj).f1234b);
        }

        /* renamed from: a */
        public int compareTo(a aVar) {
            return Float.floatToIntBits(aVar.f1234b) - Float.floatToIntBits(this.f1234b);
        }

        public String toString() {
            return "[" + "resolveInfo:" + this.f1233a.toString() + "; weight:" + new BigDecimal((double) this.f1234b) + "]";
        }
    }

    private void i() {
        try {
            FileInputStream openFileInput = this.f1230b.openFileInput(this.f1231c);
            try {
                XmlPullParser newPullParser = Xml.newPullParser();
                newPullParser.setInput(openFileInput, "UTF-8");
                int i2 = 0;
                while (i2 != 1 && i2 != 2) {
                    i2 = newPullParser.next();
                }
                if ("historical-records".equals(newPullParser.getName())) {
                    List<c> list = this.i;
                    list.clear();
                    while (true) {
                        int next = newPullParser.next();
                        if (next == 1) {
                            if (openFileInput == null) {
                                return;
                            }
                        } else if (!(next == 3 || next == 4)) {
                            if ("historical-record".equals(newPullParser.getName())) {
                                list.add(new c(newPullParser.getAttributeValue(null, "activity"), Long.parseLong(newPullParser.getAttributeValue(null, "time")), Float.parseFloat(newPullParser.getAttributeValue(null, "weight"))));
                            } else {
                                throw new XmlPullParserException("Share records file not well-formed.");
                            }
                        }
                    }
                    try {
                        openFileInput.close();
                    } catch (IOException unused) {
                    }
                } else {
                    throw new XmlPullParserException("Share records file does not start with historical-records tag.");
                }
            } catch (XmlPullParserException e2) {
                String str = f1229a;
                Log.e(str, "Error reading historical recrod file: " + this.f1231c, e2);
                if (openFileInput == null) {
                }
            } catch (IOException e3) {
                String str2 = f1229a;
                Log.e(str2, "Error reading historical recrod file: " + this.f1231c, e3);
                if (openFileInput == null) {
                }
            } catch (Throwable th) {
                if (openFileInput != null) {
                    try {
                        openFileInput.close();
                    } catch (IOException unused2) {
                    }
                }
                throw th;
            }
        } catch (FileNotFoundException unused3) {
        }
    }

    /* access modifiers changed from: private */
    /* compiled from: ActivityChooserModel */
    public final class e extends AsyncTask<Object, Void, Void> {
        e() {
        }

        /* JADX WARNING: Code restructure failed: missing block: B:10:0x006f, code lost:
            if (r12 != null) goto L_0x0071;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:12:?, code lost:
            r12.close();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:18:0x0096, code lost:
            if (r12 == null) goto L_0x00dd;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:21:0x00b8, code lost:
            if (r12 == null) goto L_0x00dd;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:24:0x00da, code lost:
            if (r12 == null) goto L_0x00dd;
         */
        /* renamed from: a */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public java.lang.Void doInBackground(java.lang.Object... r12) {
            /*
            // Method dump skipped, instructions count: 256
            */
            throw new UnsupportedOperationException("Method not decompiled: android.support.v7.widget.d.e.doInBackground(java.lang.Object[]):java.lang.Void");
        }
    }
}
