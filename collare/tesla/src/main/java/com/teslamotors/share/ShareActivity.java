package com.teslamotors.share;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.teslamotors.plugins.client.d;
import com.teslamotors.plugins.client.f;
import com.teslamotors.plugins.crashlytics.b;
import com.teslamotors.share.a;
import com.teslamotors.share.b;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.json.JSONObject;

public class ShareActivity extends Activity {

    /* renamed from: a  reason: collision with root package name */
    private static final ExecutorService f5648a = Executors.newSingleThreadExecutor();

    /* renamed from: b  reason: collision with root package name */
    private e f5649b;

    /* renamed from: c  reason: collision with root package name */
    private Handler f5650c;

    /* renamed from: d  reason: collision with root package name */
    private boolean f5651d;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        b.a(this);
        this.f5649b = new e(getApplicationContext());
        d();
        if (a()) {
            c b2 = b();
            if (!c.a(b2)) {
                a(a.EnumC0152a.Error, getString(b.d.share_extension_error_invalid_content));
                return;
            }
            a(a.EnumC0152a.Processing, "", 10);
            a(b2);
        }
    }

    private boolean a() {
        if (this.f5649b.b()) {
            a(a.EnumC0152a.Error, getString(b.d.share_extension_error_launch_app_first));
            return false;
        } else if (!this.f5649b.a()) {
            a(a.EnumC0152a.Error, getString(b.d.share_extension_error_require_log_in));
            return false;
        } else if (!this.f5649b.c()) {
            a(a.EnumC0152a.Error, getString(b.d.share_extension_error_no_vehicle_data));
            return false;
        } else if (this.f5649b.f()) {
            a(a.EnumC0152a.Error, getString(b.d.share_extension_error_vehicle_in_service));
            return false;
        } else if (this.f5649b.d()) {
            return true;
        } else {
            a(a.EnumC0152a.Error, getString(b.d.share_extension_error_vehicle_unsupported));
            return false;
        }
    }

    private c b() {
        Intent intent = getIntent();
        String action = intent.getAction();
        String type = intent.getType();
        c cVar = new c("share_ext_content_raw", f.a(getApplicationContext()).E());
        cVar.b(intent);
        if ("android.intent.action.SEND".equals(action) && type != null) {
            cVar.a(intent);
            if (type.equals("text/x-vcard")) {
                cVar.a(getApplicationContext(), intent);
            }
        }
        return cVar;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void a(a.EnumC0152a aVar, String str) {
        a(aVar, str, 5);
    }

    private void a(a.EnumC0152a aVar, String str, int i) {
        a.a(getFragmentManager(), aVar, str, i);
    }

    private void a(final c cVar) {
        a(false);
        f5648a.execute(new Runnable() {
            /* class com.teslamotors.share.ShareActivity.AnonymousClass1 */

            public void run() {
                ShareActivity.this.f5649b.a(cVar.a(), new com.teslamotors.plugins.client.a.b() {
                    /* class com.teslamotors.share.ShareActivity.AnonymousClass1.AnonymousClass1 */

                    @Override // com.teslamotors.plugins.client.a.b
                    public void a(JSONObject jSONObject) {
                        String str;
                        if (ShareActivity.this.c()) {
                            if (ShareActivity.this.f5649b == null) {
                                str = "";
                            } else {
                                str = ShareActivity.this.getString(b.d.share_to_vehicle_success).replace("%vehicleName%", ShareActivity.this.f5649b.g());
                            }
                            ShareActivity.this.f5650c.obtainMessage(1, str).sendToTarget();
                            ShareActivity.this.a((ShareActivity) true);
                        }
                    }

                    @Override // com.teslamotors.plugins.client.a.b
                    public void a(d dVar) {
                        int i = b.d.share_to_vehicle_error;
                        if (d.OWNERAPI_ERROR_VEHICLE_IN_SERVICE == dVar) {
                            i = b.d.share_extension_error_vehicle_in_service;
                            ShareActivity.this.f5649b.b(true);
                            ShareActivity.this.f5649b.h();
                        } else if (d.OWNERAPI_ERROR_MOBILE_ACCESS_DISABLED == dVar) {
                            i = b.d.share_extension_error_mobile_access_disabled;
                            ShareActivity.this.f5649b.a(false);
                            ShareActivity.this.f5649b.h();
                        }
                        if (ShareActivity.this.c()) {
                            ShareActivity.this.f5650c.obtainMessage(2, ShareActivity.this.getString(i)).sendToTarget();
                            ShareActivity.this.a((ShareActivity) true);
                        }
                    }
                });
            }
        });
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            /* class com.teslamotors.share.ShareActivity.AnonymousClass2 */

            public void run() {
                if (ShareActivity.this.c()) {
                    ShareActivity.this.a((ShareActivity) a.EnumC0152a.Error, (a.EnumC0152a) ShareActivity.this.getString(b.d.share_to_vehicle_timeout));
                    ShareActivity.this.a((ShareActivity) true);
                }
            }
        }, 9500);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private synchronized void a(boolean z) {
        this.f5651d = z;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private synchronized boolean c() {
        return !this.f5651d;
    }

    private void d() {
        this.f5650c = new Handler(Looper.getMainLooper()) {
            /* class com.teslamotors.share.ShareActivity.AnonymousClass3 */

            public void handleMessage(Message message) {
                String str = (String) message.obj;
                switch (message.what) {
                    case 1:
                        ShareActivity.this.a((ShareActivity) a.EnumC0152a.Success, (a.EnumC0152a) str);
                        return;
                    case 2:
                        ShareActivity.this.a((ShareActivity) a.EnumC0152a.Error, (a.EnumC0152a) str);
                        return;
                    case 3:
                        ShareActivity.this.a((ShareActivity) a.EnumC0152a.Timeout, (a.EnumC0152a) str);
                        return;
                    case 4:
                        ShareActivity.this.a((ShareActivity) a.EnumC0152a.Alert, (a.EnumC0152a) str);
                        return;
                    default:
                        super.handleMessage(message);
                        return;
                }
            }
        };
    }
}
