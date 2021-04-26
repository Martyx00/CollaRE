package android.support.v4.app;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.graphics.Bitmap;
import android.media.AudioAttributes;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.widget.RemoteViews;
import java.util.ArrayList;

/* compiled from: NotificationCompat */
public class y {

    /* compiled from: NotificationCompat */
    public static class c {
        String A;
        Bundle B;
        int C;
        int D;
        Notification E;
        RemoteViews F;
        RemoteViews G;
        RemoteViews H;
        String I;
        int J;
        String K;
        long L;
        int M;
        Notification N;
        @Deprecated
        public ArrayList<String> O;

        /* renamed from: a  reason: collision with root package name */
        public Context f321a;

        /* renamed from: b  reason: collision with root package name */
        public ArrayList<a> f322b;

        /* renamed from: c  reason: collision with root package name */
        ArrayList<a> f323c;

        /* renamed from: d  reason: collision with root package name */
        CharSequence f324d;
        CharSequence e;
        PendingIntent f;
        PendingIntent g;
        RemoteViews h;
        Bitmap i;
        CharSequence j;
        int k;
        int l;
        boolean m;
        boolean n;
        d o;
        CharSequence p;
        CharSequence[] q;
        int r;
        int s;
        boolean t;
        String u;
        boolean v;
        String w;
        boolean x;
        boolean y;
        boolean z;

        public c(Context context, String str) {
            this.f322b = new ArrayList<>();
            this.f323c = new ArrayList<>();
            this.m = true;
            this.x = false;
            this.C = 0;
            this.D = 0;
            this.J = 0;
            this.M = 0;
            this.N = new Notification();
            this.f321a = context;
            this.I = str;
            this.N.when = System.currentTimeMillis();
            this.N.audioStreamType = -1;
            this.l = 0;
            this.O = new ArrayList<>();
        }

        @Deprecated
        public c(Context context) {
            this(context, null);
        }

        public c a(long j2) {
            this.N.when = j2;
            return this;
        }

        public c a(int i2) {
            this.N.icon = i2;
            return this;
        }

        public c a(CharSequence charSequence) {
            this.f324d = d(charSequence);
            return this;
        }

        public c b(CharSequence charSequence) {
            this.e = d(charSequence);
            return this;
        }

        public c a(PendingIntent pendingIntent) {
            this.f = pendingIntent;
            return this;
        }

        public c b(PendingIntent pendingIntent) {
            this.N.deleteIntent = pendingIntent;
            return this;
        }

        public c c(CharSequence charSequence) {
            this.N.tickerText = d(charSequence);
            return this;
        }

        public c a(Uri uri) {
            Notification notification = this.N;
            notification.sound = uri;
            notification.audioStreamType = -1;
            if (Build.VERSION.SDK_INT >= 21) {
                this.N.audioAttributes = new AudioAttributes.Builder().setContentType(4).setUsage(5).build();
            }
            return this;
        }

        public c a(boolean z2) {
            a(8, z2);
            return this;
        }

        public c b(boolean z2) {
            a(16, z2);
            return this;
        }

        public c c(boolean z2) {
            this.x = z2;
            return this;
        }

        private void a(int i2, boolean z2) {
            if (z2) {
                Notification notification = this.N;
                notification.flags = i2 | notification.flags;
                return;
            }
            Notification notification2 = this.N;
            notification2.flags = (~i2) & notification2.flags;
        }

        public c b(int i2) {
            this.l = i2;
            return this;
        }

        public c a(String str) {
            this.u = str;
            return this;
        }

        public Bundle a() {
            if (this.B == null) {
                this.B = new Bundle();
            }
            return this.B;
        }

        public c a(int i2, CharSequence charSequence, PendingIntent pendingIntent) {
            this.f322b.add(new a(i2, charSequence, pendingIntent));
            return this;
        }

        public c a(d dVar) {
            if (this.o != dVar) {
                this.o = dVar;
                d dVar2 = this.o;
                if (dVar2 != null) {
                    dVar2.a(this);
                }
            }
            return this;
        }

        public c c(int i2) {
            this.C = i2;
            return this;
        }

        public c d(int i2) {
            this.D = i2;
            return this;
        }

        public c b(String str) {
            this.I = str;
            return this;
        }

        public Notification b() {
            return new z(this).b();
        }

        protected static CharSequence d(CharSequence charSequence) {
            return (charSequence != null && charSequence.length() > 5120) ? charSequence.subSequence(0, 5120) : charSequence;
        }
    }

    /* compiled from: NotificationCompat */
    public static abstract class d {

        /* renamed from: a  reason: collision with root package name */
        protected c f325a;

        /* renamed from: b  reason: collision with root package name */
        CharSequence f326b;

        /* renamed from: c  reason: collision with root package name */
        CharSequence f327c;

        /* renamed from: d  reason: collision with root package name */
        boolean f328d = false;

        public void a(Bundle bundle) {
        }

        public void a(x xVar) {
        }

        public RemoteViews b(x xVar) {
            return null;
        }

        public RemoteViews c(x xVar) {
            return null;
        }

        public RemoteViews d(x xVar) {
            return null;
        }

        public void a(c cVar) {
            if (this.f325a != cVar) {
                this.f325a = cVar;
                c cVar2 = this.f325a;
                if (cVar2 != null) {
                    cVar2.a(this);
                }
            }
        }
    }

    /* compiled from: NotificationCompat */
    public static class b extends d {
        private CharSequence e;

        public b a(CharSequence charSequence) {
            this.e = c.d(charSequence);
            return this;
        }

        @Override // android.support.v4.app.y.d
        public void a(x xVar) {
            if (Build.VERSION.SDK_INT >= 16) {
                Notification.BigTextStyle bigText = new Notification.BigTextStyle(xVar.a()).setBigContentTitle(this.f326b).bigText(this.e);
                if (this.f328d) {
                    bigText.setSummaryText(this.f327c);
                }
            }
        }
    }

    /* compiled from: NotificationCompat */
    public static class a {

        /* renamed from: a  reason: collision with root package name */
        final Bundle f317a;

        /* renamed from: b  reason: collision with root package name */
        boolean f318b;

        /* renamed from: c  reason: collision with root package name */
        public int f319c;

        /* renamed from: d  reason: collision with root package name */
        public CharSequence f320d;
        public PendingIntent e;
        private final ad[] f;
        private final ad[] g;
        private boolean h;
        private final int i;

        public a(int i2, CharSequence charSequence, PendingIntent pendingIntent) {
            this(i2, charSequence, pendingIntent, new Bundle(), null, null, true, 0, true);
        }

        a(int i2, CharSequence charSequence, PendingIntent pendingIntent, Bundle bundle, ad[] adVarArr, ad[] adVarArr2, boolean z, int i3, boolean z2) {
            this.f318b = true;
            this.f319c = i2;
            this.f320d = c.d(charSequence);
            this.e = pendingIntent;
            this.f317a = bundle == null ? new Bundle() : bundle;
            this.f = adVarArr;
            this.g = adVarArr2;
            this.h = z;
            this.i = i3;
            this.f318b = z2;
        }

        public int a() {
            return this.f319c;
        }

        public CharSequence b() {
            return this.f320d;
        }

        public PendingIntent c() {
            return this.e;
        }

        public Bundle d() {
            return this.f317a;
        }

        public boolean e() {
            return this.h;
        }

        public ad[] f() {
            return this.f;
        }

        public int g() {
            return this.i;
        }

        public ad[] h() {
            return this.g;
        }

        public boolean i() {
            return this.f318b;
        }
    }

    public static Bundle a(Notification notification) {
        if (Build.VERSION.SDK_INT >= 19) {
            return notification.extras;
        }
        if (Build.VERSION.SDK_INT >= 16) {
            return aa.a(notification);
        }
        return null;
    }
}
