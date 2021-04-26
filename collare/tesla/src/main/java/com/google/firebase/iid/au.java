package com.google.firebase.iid;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.FirebaseApp;
import java.io.IOException;
import java.util.concurrent.Executor;

/* access modifiers changed from: package-private */
public final class au implements b {

    /* renamed from: a  reason: collision with root package name */
    private final FirebaseApp f3907a;

    /* renamed from: b  reason: collision with root package name */
    private final q f3908b;

    /* renamed from: c  reason: collision with root package name */
    private final w f3909c;

    /* renamed from: d  reason: collision with root package name */
    private final Executor f3910d;

    au(FirebaseApp firebaseApp, q qVar, Executor executor) {
        this(firebaseApp, qVar, executor, new w(firebaseApp.a(), qVar));
    }

    @Override // com.google.firebase.iid.b
    public final boolean a() {
        return true;
    }

    private au(FirebaseApp firebaseApp, q qVar, Executor executor, w wVar) {
        this.f3907a = firebaseApp;
        this.f3908b = qVar;
        this.f3909c = wVar;
        this.f3910d = executor;
    }

    @Override // com.google.firebase.iid.b
    public final boolean b() {
        return this.f3908b.a() != 0;
    }

    @Override // com.google.firebase.iid.b
    public final Task<Void> a(String str, String str2) {
        return Tasks.forResult(null);
    }

    @Override // com.google.firebase.iid.b
    public final Task<String> a(String str, String str2, String str3, String str4) {
        return b(a(str, str3, str4, new Bundle()));
    }

    @Override // com.google.firebase.iid.b
    public final Task<Void> a(String str) {
        Bundle bundle = new Bundle();
        bundle.putString("iid-operation", "delete");
        bundle.putString("delete", "1");
        return a(b(a(str, "*", "*", bundle)));
    }

    @Override // com.google.firebase.iid.b
    public final Task<Void> a(String str, String str2, String str3) {
        Bundle bundle = new Bundle();
        String valueOf = String.valueOf("/topics/");
        String valueOf2 = String.valueOf(str3);
        bundle.putString("gcm.topic", valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf));
        String valueOf3 = String.valueOf("/topics/");
        String valueOf4 = String.valueOf(str3);
        return a(b(a(str, str2, valueOf4.length() != 0 ? valueOf3.concat(valueOf4) : new String(valueOf3), bundle)));
    }

    @Override // com.google.firebase.iid.b
    public final Task<Void> b(String str, String str2, String str3) {
        Bundle bundle = new Bundle();
        String valueOf = String.valueOf("/topics/");
        String valueOf2 = String.valueOf(str3);
        bundle.putString("gcm.topic", valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf));
        bundle.putString("delete", "1");
        String valueOf3 = String.valueOf("/topics/");
        String valueOf4 = String.valueOf(str3);
        return a(b(a(str, str2, valueOf4.length() != 0 ? valueOf3.concat(valueOf4) : new String(valueOf3), bundle)));
    }

    private final Task<Bundle> a(String str, String str2, String str3, Bundle bundle) {
        bundle.putString("scope", str3);
        bundle.putString("sender", str2);
        bundle.putString("subtype", str2);
        bundle.putString("appid", str);
        bundle.putString("gmp_app_id", this.f3907a.c().a());
        bundle.putString("gmsv", Integer.toString(this.f3908b.d()));
        bundle.putString("osv", Integer.toString(Build.VERSION.SDK_INT));
        bundle.putString("app_ver", this.f3908b.b());
        bundle.putString("app_ver_name", this.f3908b.c());
        bundle.putString("cliv", "fiid-12451000");
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        this.f3910d.execute(new av(this, bundle, taskCompletionSource));
        return taskCompletionSource.getTask();
    }

    /* access modifiers changed from: private */
    public static String a(Bundle bundle) {
        if (bundle != null) {
            String string = bundle.getString("registration_id");
            if (string != null) {
                return string;
            }
            String string2 = bundle.getString("unregistered");
            if (string2 != null) {
                return string2;
            }
            String string3 = bundle.getString("error");
            if ("RST".equals(string3)) {
                throw new IOException("INSTANCE_ID_RESET");
            } else if (string3 != null) {
                throw new IOException(string3);
            } else {
                String valueOf = String.valueOf(bundle);
                StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 21);
                sb.append("Unexpected response: ");
                sb.append(valueOf);
                Log.w("FirebaseInstanceId", sb.toString(), new Throwable());
                throw new IOException("SERVICE_NOT_AVAILABLE");
            }
        } else {
            throw new IOException("SERVICE_NOT_AVAILABLE");
        }
    }

    private final <T> Task<Void> a(Task<T> task) {
        return task.continueWith(al.a(), new aw(this));
    }

    private final Task<String> b(Task<Bundle> task) {
        return task.continueWith(this.f3910d, new ax(this));
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void a(Bundle bundle, TaskCompletionSource taskCompletionSource) {
        try {
            taskCompletionSource.setResult(this.f3909c.a(bundle));
        } catch (IOException e) {
            taskCompletionSource.setException(e);
        }
    }
}
