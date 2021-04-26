package android.support.v4.app;

import android.app.Notification;
import android.app.RemoteInput;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.y;
import android.text.TextUtils;
import android.util.SparseArray;
import android.widget.RemoteViews;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* access modifiers changed from: package-private */
/* compiled from: NotificationCompatBuilder */
public class z implements x {

    /* renamed from: a  reason: collision with root package name */
    private final Notification.Builder f329a;

    /* renamed from: b  reason: collision with root package name */
    private final y.c f330b;

    /* renamed from: c  reason: collision with root package name */
    private RemoteViews f331c;

    /* renamed from: d  reason: collision with root package name */
    private RemoteViews f332d;
    private final List<Bundle> e = new ArrayList();
    private final Bundle f = new Bundle();
    private int g;
    private RemoteViews h;

    z(y.c cVar) {
        this.f330b = cVar;
        if (Build.VERSION.SDK_INT >= 26) {
            this.f329a = new Notification.Builder(cVar.f321a, cVar.I);
        } else {
            this.f329a = new Notification.Builder(cVar.f321a);
        }
        Notification notification = cVar.N;
        this.f329a.setWhen(notification.when).setSmallIcon(notification.icon, notification.iconLevel).setContent(notification.contentView).setTicker(notification.tickerText, cVar.h).setVibrate(notification.vibrate).setLights(notification.ledARGB, notification.ledOnMS, notification.ledOffMS).setOngoing((notification.flags & 2) != 0).setOnlyAlertOnce((notification.flags & 8) != 0).setAutoCancel((notification.flags & 16) != 0).setDefaults(notification.defaults).setContentTitle(cVar.f324d).setContentText(cVar.e).setContentInfo(cVar.j).setContentIntent(cVar.f).setDeleteIntent(notification.deleteIntent).setFullScreenIntent(cVar.g, (notification.flags & 128) != 0).setLargeIcon(cVar.i).setNumber(cVar.k).setProgress(cVar.r, cVar.s, cVar.t);
        if (Build.VERSION.SDK_INT < 21) {
            this.f329a.setSound(notification.sound, notification.audioStreamType);
        }
        if (Build.VERSION.SDK_INT >= 16) {
            this.f329a.setSubText(cVar.p).setUsesChronometer(cVar.n).setPriority(cVar.l);
            Iterator<y.a> it = cVar.f322b.iterator();
            while (it.hasNext()) {
                a(it.next());
            }
            if (cVar.B != null) {
                this.f.putAll(cVar.B);
            }
            if (Build.VERSION.SDK_INT < 20) {
                if (cVar.x) {
                    this.f.putBoolean("android.support.localOnly", true);
                }
                if (cVar.u != null) {
                    this.f.putString("android.support.groupKey", cVar.u);
                    if (cVar.v) {
                        this.f.putBoolean("android.support.isGroupSummary", true);
                    } else {
                        this.f.putBoolean("android.support.useSideChannel", true);
                    }
                }
                if (cVar.w != null) {
                    this.f.putString("android.support.sortKey", cVar.w);
                }
            }
            this.f331c = cVar.F;
            this.f332d = cVar.G;
        }
        if (Build.VERSION.SDK_INT >= 19) {
            this.f329a.setShowWhen(cVar.m);
            if (Build.VERSION.SDK_INT < 21 && cVar.O != null && !cVar.O.isEmpty()) {
                this.f.putStringArray("android.people", (String[]) cVar.O.toArray(new String[cVar.O.size()]));
            }
        }
        if (Build.VERSION.SDK_INT >= 20) {
            this.f329a.setLocalOnly(cVar.x).setGroup(cVar.u).setGroupSummary(cVar.v).setSortKey(cVar.w);
            this.g = cVar.M;
        }
        if (Build.VERSION.SDK_INT >= 21) {
            this.f329a.setCategory(cVar.A).setColor(cVar.C).setVisibility(cVar.D).setPublicVersion(cVar.E).setSound(notification.sound, notification.audioAttributes);
            Iterator<String> it2 = cVar.O.iterator();
            while (it2.hasNext()) {
                this.f329a.addPerson(it2.next());
            }
            this.h = cVar.H;
            if (cVar.f323c.size() > 0) {
                Bundle bundle = cVar.a().getBundle("android.car.EXTENSIONS");
                bundle = bundle == null ? new Bundle() : bundle;
                Bundle bundle2 = new Bundle();
                for (int i = 0; i < cVar.f323c.size(); i++) {
                    bundle2.putBundle(Integer.toString(i), aa.a(cVar.f323c.get(i)));
                }
                bundle.putBundle("invisible_actions", bundle2);
                cVar.a().putBundle("android.car.EXTENSIONS", bundle);
                this.f.putBundle("android.car.EXTENSIONS", bundle);
            }
        }
        if (Build.VERSION.SDK_INT >= 24) {
            this.f329a.setExtras(cVar.B).setRemoteInputHistory(cVar.q);
            if (cVar.F != null) {
                this.f329a.setCustomContentView(cVar.F);
            }
            if (cVar.G != null) {
                this.f329a.setCustomBigContentView(cVar.G);
            }
            if (cVar.H != null) {
                this.f329a.setCustomHeadsUpContentView(cVar.H);
            }
        }
        if (Build.VERSION.SDK_INT >= 26) {
            this.f329a.setBadgeIconType(cVar.J).setShortcutId(cVar.K).setTimeoutAfter(cVar.L).setGroupAlertBehavior(cVar.M);
            if (cVar.z) {
                this.f329a.setColorized(cVar.y);
            }
            if (!TextUtils.isEmpty(cVar.I)) {
                this.f329a.setSound(null).setDefaults(0).setLights(0, 0, 0).setVibrate(null);
            }
        }
    }

    @Override // android.support.v4.app.x
    public Notification.Builder a() {
        return this.f329a;
    }

    public Notification b() {
        Bundle a2;
        RemoteViews d2;
        RemoteViews c2;
        y.d dVar = this.f330b.o;
        if (dVar != null) {
            dVar.a(this);
        }
        RemoteViews b2 = dVar != null ? dVar.b(this) : null;
        Notification c3 = c();
        if (b2 != null) {
            c3.contentView = b2;
        } else if (this.f330b.F != null) {
            c3.contentView = this.f330b.F;
        }
        if (!(Build.VERSION.SDK_INT < 16 || dVar == null || (c2 = dVar.c(this)) == null)) {
            c3.bigContentView = c2;
        }
        if (!(Build.VERSION.SDK_INT < 21 || dVar == null || (d2 = this.f330b.o.d(this)) == null)) {
            c3.headsUpContentView = d2;
        }
        if (!(Build.VERSION.SDK_INT < 16 || dVar == null || (a2 = y.a(c3)) == null)) {
            dVar.a(a2);
        }
        return c3;
    }

    private void a(y.a aVar) {
        Bundle bundle;
        if (Build.VERSION.SDK_INT >= 20) {
            Notification.Action.Builder builder = new Notification.Action.Builder(aVar.a(), aVar.b(), aVar.c());
            if (aVar.f() != null) {
                for (RemoteInput remoteInput : ad.a(aVar.f())) {
                    builder.addRemoteInput(remoteInput);
                }
            }
            if (aVar.d() != null) {
                bundle = new Bundle(aVar.d());
            } else {
                bundle = new Bundle();
            }
            bundle.putBoolean("android.support.allowGeneratedReplies", aVar.e());
            if (Build.VERSION.SDK_INT >= 24) {
                builder.setAllowGeneratedReplies(aVar.e());
            }
            bundle.putInt("android.support.action.semanticAction", aVar.g());
            if (Build.VERSION.SDK_INT >= 28) {
                builder.setSemanticAction(aVar.g());
            }
            bundle.putBoolean("android.support.action.showsUserInterface", aVar.i());
            builder.addExtras(bundle);
            this.f329a.addAction(builder.build());
        } else if (Build.VERSION.SDK_INT >= 16) {
            this.e.add(aa.a(this.f329a, aVar));
        }
    }

    /* access modifiers changed from: protected */
    public Notification c() {
        if (Build.VERSION.SDK_INT >= 26) {
            return this.f329a.build();
        }
        if (Build.VERSION.SDK_INT >= 24) {
            Notification build = this.f329a.build();
            if (this.g != 0) {
                if (!(build.getGroup() == null || (build.flags & 512) == 0 || this.g != 2)) {
                    a(build);
                }
                if (build.getGroup() != null && (build.flags & 512) == 0 && this.g == 1) {
                    a(build);
                }
            }
            return build;
        } else if (Build.VERSION.SDK_INT >= 21) {
            this.f329a.setExtras(this.f);
            Notification build2 = this.f329a.build();
            RemoteViews remoteViews = this.f331c;
            if (remoteViews != null) {
                build2.contentView = remoteViews;
            }
            RemoteViews remoteViews2 = this.f332d;
            if (remoteViews2 != null) {
                build2.bigContentView = remoteViews2;
            }
            RemoteViews remoteViews3 = this.h;
            if (remoteViews3 != null) {
                build2.headsUpContentView = remoteViews3;
            }
            if (this.g != 0) {
                if (!(build2.getGroup() == null || (build2.flags & 512) == 0 || this.g != 2)) {
                    a(build2);
                }
                if (build2.getGroup() != null && (build2.flags & 512) == 0 && this.g == 1) {
                    a(build2);
                }
            }
            return build2;
        } else if (Build.VERSION.SDK_INT >= 20) {
            this.f329a.setExtras(this.f);
            Notification build3 = this.f329a.build();
            RemoteViews remoteViews4 = this.f331c;
            if (remoteViews4 != null) {
                build3.contentView = remoteViews4;
            }
            RemoteViews remoteViews5 = this.f332d;
            if (remoteViews5 != null) {
                build3.bigContentView = remoteViews5;
            }
            if (this.g != 0) {
                if (!(build3.getGroup() == null || (build3.flags & 512) == 0 || this.g != 2)) {
                    a(build3);
                }
                if (build3.getGroup() != null && (build3.flags & 512) == 0 && this.g == 1) {
                    a(build3);
                }
            }
            return build3;
        } else if (Build.VERSION.SDK_INT >= 19) {
            SparseArray<Bundle> a2 = aa.a(this.e);
            if (a2 != null) {
                this.f.putSparseParcelableArray("android.support.actionExtras", a2);
            }
            this.f329a.setExtras(this.f);
            Notification build4 = this.f329a.build();
            RemoteViews remoteViews6 = this.f331c;
            if (remoteViews6 != null) {
                build4.contentView = remoteViews6;
            }
            RemoteViews remoteViews7 = this.f332d;
            if (remoteViews7 != null) {
                build4.bigContentView = remoteViews7;
            }
            return build4;
        } else if (Build.VERSION.SDK_INT < 16) {
            return this.f329a.getNotification();
        } else {
            Notification build5 = this.f329a.build();
            Bundle a3 = y.a(build5);
            Bundle bundle = new Bundle(this.f);
            for (String str : this.f.keySet()) {
                if (a3.containsKey(str)) {
                    bundle.remove(str);
                }
            }
            a3.putAll(bundle);
            SparseArray<Bundle> a4 = aa.a(this.e);
            if (a4 != null) {
                y.a(build5).putSparseParcelableArray("android.support.actionExtras", a4);
            }
            RemoteViews remoteViews8 = this.f331c;
            if (remoteViews8 != null) {
                build5.contentView = remoteViews8;
            }
            RemoteViews remoteViews9 = this.f332d;
            if (remoteViews9 != null) {
                build5.bigContentView = remoteViews9;
            }
            return build5;
        }
    }

    private void a(Notification notification) {
        notification.sound = null;
        notification.vibrate = null;
        notification.defaults &= -2;
        notification.defaults &= -3;
    }
}
