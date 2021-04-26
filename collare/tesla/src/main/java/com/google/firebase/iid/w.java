package com.google.firebase.iid;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.os.Parcelable;
import android.support.v4.util.l;
import android.util.Log;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.iid.ao;
import java.util.concurrent.ExecutionException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* access modifiers changed from: package-private */
public final class w {

    /* renamed from: a  reason: collision with root package name */
    private static int f3950a;

    /* renamed from: b  reason: collision with root package name */
    private static PendingIntent f3951b;

    /* renamed from: c  reason: collision with root package name */
    private final l<String, TaskCompletionSource<Bundle>> f3952c = new l<>();

    /* renamed from: d  reason: collision with root package name */
    private final Context f3953d;
    private final q e;
    private Messenger f;
    private Messenger g;
    private ao h;

    public w(Context context, q qVar) {
        this.f3953d = context;
        this.e = qVar;
        this.f = new Messenger(new x(this, Looper.getMainLooper()));
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private final void a(Message message) {
        if (message == null || !(message.obj instanceof Intent)) {
            Log.w("FirebaseInstanceId", "Dropping invalid message");
            return;
        }
        Intent intent = (Intent) message.obj;
        intent.setExtrasClassLoader(new ao.a());
        if (intent.hasExtra("google.messenger")) {
            Parcelable parcelableExtra = intent.getParcelableExtra("google.messenger");
            if (parcelableExtra instanceof ao) {
                this.h = (ao) parcelableExtra;
            }
            if (parcelableExtra instanceof Messenger) {
                this.g = (Messenger) parcelableExtra;
            }
        }
        Intent intent2 = (Intent) message.obj;
        String action = intent2.getAction();
        if ("com.google.android.c2dm.intent.REGISTRATION".equals(action)) {
            String stringExtra = intent2.getStringExtra("registration_id");
            if (stringExtra == null) {
                stringExtra = intent2.getStringExtra("unregistered");
            }
            if (stringExtra == null) {
                String stringExtra2 = intent2.getStringExtra("error");
                if (stringExtra2 == null) {
                    String valueOf = String.valueOf(intent2.getExtras());
                    StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 49);
                    sb.append("Unexpected response, no error or registration id ");
                    sb.append(valueOf);
                    Log.w("FirebaseInstanceId", sb.toString());
                    return;
                }
                if (Log.isLoggable("FirebaseInstanceId", 3)) {
                    String valueOf2 = String.valueOf(stringExtra2);
                    Log.d("FirebaseInstanceId", valueOf2.length() != 0 ? "Received InstanceID error ".concat(valueOf2) : new String("Received InstanceID error "));
                }
                if (stringExtra2.startsWith("|")) {
                    String[] split = stringExtra2.split("\\|");
                    if (split.length <= 2 || !"ID".equals(split[1])) {
                        String valueOf3 = String.valueOf(stringExtra2);
                        Log.w("FirebaseInstanceId", valueOf3.length() != 0 ? "Unexpected structured response ".concat(valueOf3) : new String("Unexpected structured response "));
                        return;
                    }
                    String str = split[2];
                    String str2 = split[3];
                    if (str2.startsWith(":")) {
                        str2 = str2.substring(1);
                    }
                    a(str, intent2.putExtra("error", str2).getExtras());
                    return;
                }
                synchronized (this.f3952c) {
                    for (int i = 0; i < this.f3952c.size(); i++) {
                        a(this.f3952c.b(i), intent2.getExtras());
                    }
                }
                return;
            }
            Matcher matcher = Pattern.compile("\\|ID\\|([^|]+)\\|:?+(.*)").matcher(stringExtra);
            if (matcher.matches()) {
                String group = matcher.group(1);
                String group2 = matcher.group(2);
                Bundle extras = intent2.getExtras();
                extras.putString("registration_id", group2);
                a(group, extras);
            } else if (Log.isLoggable("FirebaseInstanceId", 3)) {
                String valueOf4 = String.valueOf(stringExtra);
                Log.d("FirebaseInstanceId", valueOf4.length() != 0 ? "Unexpected response string: ".concat(valueOf4) : new String("Unexpected response string: "));
            }
        } else if (Log.isLoggable("FirebaseInstanceId", 3)) {
            String valueOf5 = String.valueOf(action);
            Log.d("FirebaseInstanceId", valueOf5.length() != 0 ? "Unexpected response action: ".concat(valueOf5) : new String("Unexpected response action: "));
        }
    }

    private static synchronized void a(Context context, Intent intent) {
        synchronized (w.class) {
            if (f3951b == null) {
                Intent intent2 = new Intent();
                intent2.setPackage("com.google.example.invalidpackage");
                f3951b = PendingIntent.getBroadcast(context, 0, intent2, 0);
            }
            intent.putExtra("app", f3951b);
        }
    }

    private final void a(String str, Bundle bundle) {
        synchronized (this.f3952c) {
            TaskCompletionSource<Bundle> remove = this.f3952c.remove(str);
            if (remove == null) {
                String valueOf = String.valueOf(str);
                Log.w("FirebaseInstanceId", valueOf.length() != 0 ? "Missing callback for ".concat(valueOf) : new String("Missing callback for "));
                return;
            }
            remove.setResult(bundle);
        }
    }

    /* access modifiers changed from: package-private */
    public final Bundle a(Bundle bundle) {
        if (this.e.d() < 12000000) {
            return b(bundle);
        }
        try {
            return (Bundle) Tasks.await(e.a(this.f3953d).b(1, bundle));
        } catch (InterruptedException | ExecutionException e2) {
            if (Log.isLoggable("FirebaseInstanceId", 3)) {
                String valueOf = String.valueOf(e2);
                StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 22);
                sb.append("Error making request: ");
                sb.append(valueOf);
                Log.d("FirebaseInstanceId", sb.toString());
            }
            if (!(e2.getCause() instanceof o) || ((o) e2.getCause()).a() != 4) {
                return null;
            }
            return b(bundle);
        }
    }

    private final Bundle b(Bundle bundle) {
        Bundle c2 = c(bundle);
        if (c2 == null || !c2.containsKey("google.messenger")) {
            return c2;
        }
        Bundle c3 = c(bundle);
        if (c3 == null || !c3.containsKey("google.messenger")) {
            return c3;
        }
        return null;
    }

    private static synchronized String a() {
        String num;
        synchronized (w.class) {
            int i = f3950a;
            f3950a = i + 1;
            num = Integer.toString(i);
        }
        return num;
    }

    /* JADX WARNING: Removed duplicated region for block: B:36:0x00ef A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final android.os.Bundle c(android.os.Bundle r9) {
        /*
        // Method dump skipped, instructions count: 297
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.iid.w.c(android.os.Bundle):android.os.Bundle");
    }
}
