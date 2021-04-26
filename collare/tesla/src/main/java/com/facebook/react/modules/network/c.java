package com.facebook.react.modules.network;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.ValueCallback;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.GuardedAsyncTask;
import com.facebook.react.bridge.GuardedResultAsyncTask;
import com.facebook.react.bridge.ReactContext;
import java.net.CookieHandler;
import java.net.URI;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/* compiled from: ForwardingCookieHandler */
public class c extends CookieHandler {

    /* renamed from: a  reason: collision with root package name */
    private static final boolean f2928a = (Build.VERSION.SDK_INT < 21);

    /* renamed from: b  reason: collision with root package name */
    private final a f2929b = new a();

    /* renamed from: c  reason: collision with root package name */
    private final ReactContext f2930c;

    /* renamed from: d  reason: collision with root package name */
    private CookieManager f2931d;

    public c(ReactContext reactContext) {
        this.f2930c = reactContext;
    }

    @Override // java.net.CookieHandler
    public Map<String, List<String>> get(URI uri, Map<String, List<String>> map) {
        CookieManager c2 = c();
        if (c2 == null) {
            return Collections.emptyMap();
        }
        String cookie = c2.getCookie(uri.toString());
        if (TextUtils.isEmpty(cookie)) {
            return Collections.emptyMap();
        }
        return Collections.singletonMap("Cookie", Collections.singletonList(cookie));
    }

    @Override // java.net.CookieHandler
    public void put(URI uri, Map<String, List<String>> map) {
        String uri2 = uri.toString();
        for (Map.Entry<String, List<String>> entry : map.entrySet()) {
            String key = entry.getKey();
            if (key != null && a(key)) {
                a(uri2, entry.getValue());
            }
        }
    }

    public void a(final Callback callback) {
        if (f2928a) {
            new GuardedResultAsyncTask<Boolean>(this.f2930c) {
                /* class com.facebook.react.modules.network.c.AnonymousClass1 */

                /* access modifiers changed from: protected */
                /* renamed from: a */
                public Boolean doInBackgroundGuarded() {
                    CookieManager c2 = c.this.c();
                    if (c2 != null) {
                        c2.removeAllCookie();
                    }
                    c.this.f2929b.a();
                    return true;
                }

                /* access modifiers changed from: protected */
                /* renamed from: a */
                public void onPostExecuteGuarded(Boolean bool) {
                    callback.invoke(bool);
                }
            }.execute(new Void[0]);
        } else {
            b(callback);
        }
    }

    private void b(final Callback callback) {
        CookieManager c2 = c();
        if (c2 != null) {
            c2.removeAllCookies(new ValueCallback<Boolean>() {
                /* class com.facebook.react.modules.network.c.AnonymousClass2 */

                /* renamed from: a */
                public void onReceiveValue(Boolean bool) {
                    c.this.f2929b.a();
                    callback.invoke(bool);
                }
            });
        }
    }

    public void a() {
        if (f2928a) {
            CookieManager c2 = c();
            if (c2 != null) {
                c2.removeExpiredCookie();
            }
            this.f2929b.b();
        }
    }

    private void a(final String str, final List<String> list) {
        final CookieManager c2 = c();
        if (c2 != null) {
            if (f2928a) {
                a(new Runnable() {
                    /* class com.facebook.react.modules.network.c.AnonymousClass3 */

                    public void run() {
                        for (String str : list) {
                            c2.setCookie(str, str);
                        }
                        c.this.f2929b.a();
                    }
                });
                return;
            }
            for (String str2 : list) {
                a(str, str2);
            }
            c2.flush();
            this.f2929b.a();
        }
    }

    @TargetApi(21)
    private void a(String str, String str2) {
        CookieManager c2 = c();
        if (c2 != null) {
            c2.setCookie(str, str2, null);
        }
    }

    private static boolean a(String str) {
        return str.equalsIgnoreCase("Set-cookie") || str.equalsIgnoreCase("Set-cookie2");
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void a(final Runnable runnable) {
        new GuardedAsyncTask<Void, Void>(this.f2930c) {
            /* class com.facebook.react.modules.network.c.AnonymousClass4 */

            /* access modifiers changed from: protected */
            /* renamed from: a */
            public void doInBackgroundGuarded(Void... voidArr) {
                runnable.run();
            }
        }.execute(new Void[0]);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private CookieManager c() {
        if (this.f2931d == null) {
            a(this.f2930c);
            try {
                this.f2931d = CookieManager.getInstance();
                if (f2928a) {
                    this.f2931d.removeExpiredCookie();
                }
            } catch (IllegalArgumentException unused) {
                return null;
            } catch (Exception e) {
                String message = e.getMessage();
                if (message != null && message.contains("No WebView installed")) {
                    return null;
                }
                throw e;
            }
        }
        return this.f2931d;
    }

    private static void a(Context context) {
        if (f2928a) {
            CookieSyncManager.createInstance(context).sync();
        }
    }

    /* access modifiers changed from: private */
    /* compiled from: ForwardingCookieHandler */
    public class a {

        /* renamed from: b  reason: collision with root package name */
        private final Handler f2943b;

        public a() {
            this.f2943b = new Handler(Looper.getMainLooper(), new Handler.Callback(c.this) {
                /* class com.facebook.react.modules.network.c.a.AnonymousClass1 */

                public boolean handleMessage(Message message) {
                    if (message.what != 1) {
                        return false;
                    }
                    a.this.b();
                    return true;
                }
            });
        }

        public void a() {
            if (c.f2928a) {
                this.f2943b.sendEmptyMessageDelayed(1, 30000);
            }
        }

        public void b() {
            this.f2943b.removeMessages(1);
            c.this.a((c) new Runnable() {
                /* class com.facebook.react.modules.network.c.a.AnonymousClass2 */

                public void run() {
                    if (c.f2928a) {
                        CookieSyncManager.getInstance().sync();
                    } else {
                        a.this.c();
                    }
                }
            });
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        @TargetApi(21)
        private void c() {
            CookieManager c2 = c.this.c();
            if (c2 != null) {
                c2.flush();
            }
        }
    }
}
