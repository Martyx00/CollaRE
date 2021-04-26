package android.support.v4.app;

import android.app.Service;
import android.app.job.JobInfo;
import android.app.job.JobParameters;
import android.app.job.JobScheduler;
import android.app.job.JobServiceEngine;
import android.app.job.JobWorkItem;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.IBinder;
import android.os.PowerManager;
import java.util.ArrayList;
import java.util.HashMap;

/* compiled from: JobIntentService */
public abstract class u extends Service {
    static final Object h = new Object();
    static final HashMap<ComponentName, h> i = new HashMap<>();

    /* renamed from: a  reason: collision with root package name */
    b f298a;

    /* renamed from: b  reason: collision with root package name */
    h f299b;

    /* renamed from: c  reason: collision with root package name */
    a f300c;

    /* renamed from: d  reason: collision with root package name */
    boolean f301d = false;
    boolean e = false;
    boolean f = false;
    final ArrayList<d> g;

    /* access modifiers changed from: package-private */
    /* compiled from: JobIntentService */
    public interface b {
        IBinder a();

        e b();
    }

    /* access modifiers changed from: package-private */
    /* compiled from: JobIntentService */
    public interface e {
        Intent a();

        void b();
    }

    /* access modifiers changed from: protected */
    public abstract void a(Intent intent);

    public boolean onStopCurrentWork() {
        return true;
    }

    /* access modifiers changed from: package-private */
    /* compiled from: JobIntentService */
    public static abstract class h {

        /* renamed from: c  reason: collision with root package name */
        final ComponentName f315c;

        /* renamed from: d  reason: collision with root package name */
        boolean f316d;
        int e;

        public void a() {
        }

        /* access modifiers changed from: package-private */
        public abstract void a(Intent intent);

        public void b() {
        }

        public void c() {
        }

        h(Context context, ComponentName componentName) {
            this.f315c = componentName;
        }

        /* access modifiers changed from: package-private */
        public void a(int i) {
            if (!this.f316d) {
                this.f316d = true;
                this.e = i;
            } else if (this.e != i) {
                throw new IllegalArgumentException("Given job ID " + i + " is different than previous " + this.e);
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* compiled from: JobIntentService */
    public static final class c extends h {

        /* renamed from: a  reason: collision with root package name */
        boolean f303a;

        /* renamed from: b  reason: collision with root package name */
        boolean f304b;
        private final Context f;
        private final PowerManager.WakeLock g;
        private final PowerManager.WakeLock h;

        c(Context context, ComponentName componentName) {
            super(context, componentName);
            this.f = context.getApplicationContext();
            PowerManager powerManager = (PowerManager) context.getSystemService("power");
            this.g = powerManager.newWakeLock(1, componentName.getClassName() + ":launch");
            this.g.setReferenceCounted(false);
            this.h = powerManager.newWakeLock(1, componentName.getClassName() + ":run");
            this.h.setReferenceCounted(false);
        }

        /* access modifiers changed from: package-private */
        @Override // android.support.v4.app.u.h
        public void a(Intent intent) {
            Intent intent2 = new Intent(intent);
            intent2.setComponent(this.f315c);
            if (this.f.startService(intent2) != null) {
                synchronized (this) {
                    if (!this.f303a) {
                        this.f303a = true;
                        if (!this.f304b) {
                            this.g.acquire(60000);
                        }
                    }
                }
            }
        }

        @Override // android.support.v4.app.u.h
        public void a() {
            synchronized (this) {
                this.f303a = false;
            }
        }

        @Override // android.support.v4.app.u.h
        public void b() {
            synchronized (this) {
                if (!this.f304b) {
                    this.f304b = true;
                    this.h.acquire(600000);
                    this.g.release();
                }
            }
        }

        @Override // android.support.v4.app.u.h
        public void c() {
            synchronized (this) {
                if (this.f304b) {
                    if (this.f303a) {
                        this.g.acquire(60000);
                    }
                    this.f304b = false;
                    this.h.release();
                }
            }
        }
    }

    /* compiled from: JobIntentService */
    static final class f extends JobServiceEngine implements b {

        /* renamed from: a  reason: collision with root package name */
        final u f308a;

        /* renamed from: b  reason: collision with root package name */
        final Object f309b = new Object();

        /* renamed from: c  reason: collision with root package name */
        JobParameters f310c;

        /* compiled from: JobIntentService */
        final class a implements e {

            /* renamed from: a  reason: collision with root package name */
            final JobWorkItem f311a;

            a(JobWorkItem jobWorkItem) {
                this.f311a = jobWorkItem;
            }

            @Override // android.support.v4.app.u.e
            public Intent a() {
                return this.f311a.getIntent();
            }

            @Override // android.support.v4.app.u.e
            public void b() {
                synchronized (f.this.f309b) {
                    if (f.this.f310c != null) {
                        f.this.f310c.completeWork(this.f311a);
                    }
                }
            }
        }

        f(u uVar) {
            super(uVar);
            this.f308a = uVar;
        }

        @Override // android.support.v4.app.u.b
        public IBinder a() {
            return getBinder();
        }

        public boolean onStartJob(JobParameters jobParameters) {
            this.f310c = jobParameters;
            this.f308a.a(false);
            return true;
        }

        public boolean onStopJob(JobParameters jobParameters) {
            boolean a2 = this.f308a.a();
            synchronized (this.f309b) {
                this.f310c = null;
            }
            return a2;
        }

        /* JADX WARNING: Code restructure failed: missing block: B:10:0x0013, code lost:
            r1.getIntent().setExtrasClassLoader(r3.f308a.getClassLoader());
         */
        /* JADX WARNING: Code restructure failed: missing block: B:11:0x0025, code lost:
            return new android.support.v4.app.u.f.a(r3, r1);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:12:0x0026, code lost:
            return null;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:9:0x0011, code lost:
            if (r1 == null) goto L_0x0026;
         */
        @Override // android.support.v4.app.u.b
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public android.support.v4.app.u.e b() {
            /*
                r3 = this;
                java.lang.Object r0 = r3.f309b
                monitor-enter(r0)
                android.app.job.JobParameters r1 = r3.f310c     // Catch:{ all -> 0x0027 }
                r2 = 0
                if (r1 != 0) goto L_0x000a
                monitor-exit(r0)     // Catch:{ all -> 0x0027 }
                return r2
            L_0x000a:
                android.app.job.JobParameters r1 = r3.f310c     // Catch:{ all -> 0x0027 }
                android.app.job.JobWorkItem r1 = r1.dequeueWork()     // Catch:{ all -> 0x0027 }
                monitor-exit(r0)     // Catch:{ all -> 0x0027 }
                if (r1 == 0) goto L_0x0026
                android.content.Intent r0 = r1.getIntent()
                android.support.v4.app.u r2 = r3.f308a
                java.lang.ClassLoader r2 = r2.getClassLoader()
                r0.setExtrasClassLoader(r2)
                android.support.v4.app.u$f$a r0 = new android.support.v4.app.u$f$a
                r0.<init>(r1)
                return r0
            L_0x0026:
                return r2
            L_0x0027:
                r1 = move-exception
                monitor-exit(r0)
                throw r1
            */
            throw new UnsupportedOperationException("Method not decompiled: android.support.v4.app.u.f.b():android.support.v4.app.u$e");
        }
    }

    /* access modifiers changed from: package-private */
    /* compiled from: JobIntentService */
    public static final class g extends h {

        /* renamed from: a  reason: collision with root package name */
        private final JobInfo f313a;

        /* renamed from: b  reason: collision with root package name */
        private final JobScheduler f314b;

        g(Context context, ComponentName componentName, int i) {
            super(context, componentName);
            a(i);
            this.f313a = new JobInfo.Builder(i, this.f315c).setOverrideDeadline(0).build();
            this.f314b = (JobScheduler) context.getApplicationContext().getSystemService("jobscheduler");
        }

        /* access modifiers changed from: package-private */
        @Override // android.support.v4.app.u.h
        public void a(Intent intent) {
            this.f314b.enqueue(this.f313a, new JobWorkItem(intent));
        }
    }

    /* access modifiers changed from: package-private */
    /* compiled from: JobIntentService */
    public final class d implements e {

        /* renamed from: a  reason: collision with root package name */
        final Intent f305a;

        /* renamed from: b  reason: collision with root package name */
        final int f306b;

        d(Intent intent, int i) {
            this.f305a = intent;
            this.f306b = i;
        }

        @Override // android.support.v4.app.u.e
        public Intent a() {
            return this.f305a;
        }

        @Override // android.support.v4.app.u.e
        public void b() {
            u.this.stopSelf(this.f306b);
        }
    }

    /* access modifiers changed from: package-private */
    /* compiled from: JobIntentService */
    public final class a extends AsyncTask<Void, Void, Void> {
        a() {
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public Void doInBackground(Void... voidArr) {
            while (true) {
                e c2 = u.this.c();
                if (c2 == null) {
                    return null;
                }
                u.this.a(c2.a());
                c2.b();
            }
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public void onCancelled(Void r1) {
            u.this.b();
        }

        /* access modifiers changed from: protected */
        /* renamed from: b */
        public void onPostExecute(Void r1) {
            u.this.b();
        }
    }

    public u() {
        if (Build.VERSION.SDK_INT >= 26) {
            this.g = null;
        } else {
            this.g = new ArrayList<>();
        }
    }

    public void onCreate() {
        super.onCreate();
        if (Build.VERSION.SDK_INT >= 26) {
            this.f298a = new f(this);
            this.f299b = null;
            return;
        }
        this.f298a = null;
        this.f299b = a(this, new ComponentName(this, getClass()), false, 0);
    }

    public int onStartCommand(Intent intent, int i2, int i3) {
        if (this.g == null) {
            return 2;
        }
        this.f299b.a();
        synchronized (this.g) {
            ArrayList<d> arrayList = this.g;
            if (intent == null) {
                intent = new Intent();
            }
            arrayList.add(new d(intent, i3));
            a(true);
        }
        return 3;
    }

    public IBinder onBind(Intent intent) {
        b bVar = this.f298a;
        if (bVar != null) {
            return bVar.a();
        }
        return null;
    }

    public void onDestroy() {
        super.onDestroy();
        ArrayList<d> arrayList = this.g;
        if (arrayList != null) {
            synchronized (arrayList) {
                this.f = true;
                this.f299b.c();
            }
        }
    }

    public static void enqueueWork(Context context, Class cls, int i2, Intent intent) {
        enqueueWork(context, new ComponentName(context, cls), i2, intent);
    }

    public static void enqueueWork(Context context, ComponentName componentName, int i2, Intent intent) {
        if (intent != null) {
            synchronized (h) {
                h a2 = a(context, componentName, true, i2);
                a2.a(i2);
                a2.a(intent);
            }
            return;
        }
        throw new IllegalArgumentException("work must not be null");
    }

    static h a(Context context, ComponentName componentName, boolean z, int i2) {
        h hVar = i.get(componentName);
        if (hVar == null) {
            if (Build.VERSION.SDK_INT < 26) {
                hVar = new c(context, componentName);
            } else if (z) {
                hVar = new g(context, componentName, i2);
            } else {
                throw new IllegalArgumentException("Can't be here without a job id");
            }
            i.put(componentName, hVar);
        }
        return hVar;
    }

    public void setInterruptIfStopped(boolean z) {
        this.f301d = z;
    }

    public boolean isStopped() {
        return this.e;
    }

    /* access modifiers changed from: package-private */
    public boolean a() {
        a aVar = this.f300c;
        if (aVar != null) {
            aVar.cancel(this.f301d);
        }
        this.e = true;
        return onStopCurrentWork();
    }

    /* access modifiers changed from: package-private */
    public void a(boolean z) {
        if (this.f300c == null) {
            this.f300c = new a();
            h hVar = this.f299b;
            if (hVar != null && z) {
                hVar.b();
            }
            this.f300c.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
        }
    }

    /* access modifiers changed from: package-private */
    public void b() {
        ArrayList<d> arrayList = this.g;
        if (arrayList != null) {
            synchronized (arrayList) {
                this.f300c = null;
                if (this.g != null && this.g.size() > 0) {
                    a(false);
                } else if (!this.f) {
                    this.f299b.c();
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public e c() {
        b bVar = this.f298a;
        if (bVar != null) {
            return bVar.b();
        }
        synchronized (this.g) {
            if (this.g.size() <= 0) {
                return null;
            }
            return this.g.remove(0);
        }
    }
}
