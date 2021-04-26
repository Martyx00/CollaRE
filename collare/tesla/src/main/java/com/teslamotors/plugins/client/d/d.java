package com.teslamotors.plugins.client.d;

import android.content.Context;
import android.text.TextUtils;
import android.util.Pair;
import com.teslamotors.plugins.client.b;
import com.teslamotors.plugins.client.b.c;
import io.realm.g;
import io.realm.o;
import io.realm.r;
import io.realm.y;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* compiled from: RealmHelper */
public class d {

    /* renamed from: a  reason: collision with root package name */
    private static d f5533a;

    /* renamed from: b  reason: collision with root package name */
    private static BlockingQueue<Runnable> f5534b = new ArrayBlockingQueue(1000);

    /* renamed from: c  reason: collision with root package name */
    private static ThreadPoolExecutor f5535c = new ThreadPoolExecutor(5, 16, 500, TimeUnit.MILLISECONDS, f5534b);

    /* renamed from: d  reason: collision with root package name */
    private final r f5536d = new r.a().a("TrealmStorage").a(2).a();
    private final f e;

    public static d a(Context context) {
        if (f5533a == null) {
            f5533a = new d(context);
        }
        return f5533a;
    }

    private d(Context context) {
        this.e = f.a(context);
    }

    /* JADX WARNING: Removed duplicated region for block: B:24:0x0093  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x009a  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String a(java.lang.String r7) {
        /*
        // Method dump skipped, instructions count: 158
        */
        throw new UnsupportedOperationException("Method not decompiled: com.teslamotors.plugins.client.d.d.a(java.lang.String):java.lang.String");
    }

    public List<Pair<String, String>> a(List<String> list, List<String> list2) {
        if (list.size() == 0 && list2.size() == 0) {
            return Collections.emptyList();
        }
        o oVar = null;
        try {
            o b2 = o.b(this.f5536d);
            ArrayList<c> arrayList = new ArrayList();
            if (list.size() != 0) {
                arrayList.addAll(b2.a(b2.a(c.class).a("async_key", (String[]) list.toArray(new String[list.size()])).b()));
            }
            if (list2.size() != 0) {
                for (String str : list2) {
                    arrayList.addAll(b2.a(b2.a(c.class).b("async_key", str).b()));
                }
            }
            b.b("RealmHelper", "Retrieve Values: Keys [" + TextUtils.join(",", list) + "] Prefixes [" + TextUtils.join(",", list2) + "] Success with # of Results " + arrayList.size());
            final List<Pair<String, String>> synchronizedList = Collections.synchronizedList(new ArrayList());
            final CountDownLatch countDownLatch = new CountDownLatch(arrayList.size());
            for (c cVar : arrayList) {
                final String a2 = cVar.a();
                final String b3 = cVar.b();
                f5535c.execute(new Runnable() {
                    /* class com.teslamotors.plugins.client.d.d.AnonymousClass1 */

                    public void run() {
                        String b2 = d.this.b((d) a2, b3);
                        synchronized (synchronizedList) {
                            synchronizedList.add(new Pair(a2, b2));
                        }
                        countDownLatch.countDown();
                    }
                });
            }
            countDownLatch.await();
            if (b2 != null) {
                b2.close();
            }
            return synchronizedList;
        } catch (Error e2) {
            b.a("RealmHelper", "Retrieving Values Error", e2);
            List<Pair<String, String>> emptyList = Collections.emptyList();
            if (0 != 0) {
                oVar.close();
            }
            return emptyList;
        } catch (InterruptedException e3) {
            b.a("RealmHelper", "Countdown Interrupted", e3);
            List<Pair<String, String>> emptyList2 = Collections.emptyList();
            if (0 != 0) {
                oVar.close();
            }
            return emptyList2;
        } catch (Throwable th) {
            if (0 != 0) {
                oVar.close();
            }
            throw th;
        }
    }

    public boolean a(String str, String str2) {
        o oVar = null;
        try {
            oVar = o.b(this.f5536d);
            oVar.b();
            oVar.a(new c().a(str).b(c(str, str2)), new g[0]);
            b.b("RealmHelper", "Set Value " + str + " Success");
            oVar.c();
            if (oVar != null) {
                oVar.close();
            }
            return true;
        } catch (Error e2) {
            b.a("RealmHelper", "Set Value " + str + " Error", e2);
            if (oVar != null) {
                oVar.close();
            }
            return false;
        } catch (Throwable th) {
            if (oVar != null) {
                oVar.close();
            }
            throw th;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:28:0x00a8  */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x00b7  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x00be  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean a(final android.os.Bundle r16) {
        /*
        // Method dump skipped, instructions count: 194
        */
        throw new UnsupportedOperationException("Method not decompiled: com.teslamotors.plugins.client.d.d.a(android.os.Bundle):boolean");
    }

    public boolean b(String str) {
        o oVar = null;
        try {
            oVar = o.b(this.f5536d);
            oVar.b();
            oVar.a(c.class).a("async_key", str).b().c();
            b.b("RealmHelper", "Remove Value " + str + " Success");
            oVar.c();
            if (oVar != null) {
                oVar.close();
            }
            return true;
        } catch (Error e2) {
            b.a("RealmHelper", "Remove Value " + str + " Error", e2);
            if (oVar != null) {
                oVar.close();
            }
            return false;
        } catch (Throwable th) {
            if (oVar != null) {
                oVar.close();
            }
            throw th;
        }
    }

    public boolean b(List<String> list, List<String> list2) {
        if (list.size() == 0 && list2.size() == 0) {
            return true;
        }
        o oVar = null;
        try {
            o b2 = o.b(this.f5536d);
            b2.b();
            if (list.size() != 0) {
                b2.a(c.class).a("async_key", (String[]) list.toArray(new String[list.size()])).b().c();
            }
            if (list2.size() != 0) {
                for (String str : list2) {
                    b2.a(c.class).b("async_key", str).b().c();
                }
            }
            b.b("RealmHelper", "Remove Values: Keys [" + TextUtils.join(",", list) + "] Prefixes [" + TextUtils.join(",", list2) + "] Success");
            b2.c();
            if (b2 != null) {
                b2.close();
            }
            return true;
        } catch (Error e2) {
            b.a("RealmHelper", "Remove Values Error", e2);
            if (0 != 0) {
                oVar.close();
            }
            return false;
        } catch (Throwable th) {
            if (0 != 0) {
                oVar.close();
            }
            throw th;
        }
    }

    public boolean c(List<String> list, List<String> list2) {
        o oVar = null;
        try {
            o b2 = o.b(this.f5536d);
            b2.b();
            y b3 = b2.a(c.class).b();
            if (list.size() > 0) {
                b3 = b3.e().a().a("async_key", (String[]) list.toArray(new String[list.size()])).b();
            }
            if (list2.size() > 0) {
                for (String str : list2) {
                    b3 = b3.e().a().b("async_key", str).b();
                }
            }
            if (b3.size() > 0) {
                b3.c();
            }
            b.b("RealmHelper", "Clear Values: Keep Keys [" + TextUtils.join(",", list) + "] Keep Prefixes [" + TextUtils.join(",", list2) + "] Success");
            b2.c();
            if (b2 != null) {
                b2.close();
            }
            return true;
        } catch (Error e2) {
            b.a("RealmHelper", "Clear Values Error", e2);
            if (0 != 0) {
                oVar.close();
            }
            return false;
        } catch (Throwable th) {
            if (0 != 0) {
                oVar.close();
            }
            throw th;
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private String b(String str, String str2) {
        return str.startsWith(io.a.a.a.a.d.b.ROLL_OVER_FILE_NAME_SEPARATOR) ? this.e.a(str2) : str2;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private String c(String str, String str2) {
        return str.startsWith(io.a.a.a.a.d.b.ROLL_OVER_FILE_NAME_SEPARATOR) ? this.e.b(str2) : str2;
    }
}
