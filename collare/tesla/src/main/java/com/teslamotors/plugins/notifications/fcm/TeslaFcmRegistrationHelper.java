package com.teslamotors.plugins.notifications.fcm;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.a;
import com.teslamotors.plugins.client.f;
import com.teslamotors.plugins.notifications.b;
import com.teslamotors.plugins.notifications.e;
import java.io.IOException;

public class TeslaFcmRegistrationHelper implements e {
    @Override // com.teslamotors.plugins.notifications.e
    public void a(final Context context) {
        FirebaseApp.a(context);
        FirebaseInstanceId.a().d().addOnCompleteListener(new OnCompleteListener<a>() {
            /* class com.teslamotors.plugins.notifications.fcm.TeslaFcmRegistrationHelper.AnonymousClass1 */

            @Override // com.google.android.gms.tasks.OnCompleteListener
            public void onComplete(Task<a> task) {
                if (!task.isSuccessful() || task.getResult() == null) {
                    Log.e("FcmRegistrationHelper", "Failed to complete token refresh", task.getException());
                    return;
                }
                String a2 = task.getResult().a();
                f.a(context).b(a2);
                b.a(context).a(a2);
            }
        });
    }

    @Override // com.teslamotors.plugins.notifications.e
    public void b(Context context) {
        FirebaseApp.a(context);
        new AsyncTask() {
            /* class com.teslamotors.plugins.notifications.fcm.TeslaFcmRegistrationHelper.AnonymousClass2 */

            /* access modifiers changed from: protected */
            @Override // android.os.AsyncTask
            public Object doInBackground(Object[] objArr) {
                try {
                    FirebaseInstanceId.a().e();
                    return null;
                } catch (IOException e) {
                    Log.e("FcmRegistrationHelper", "Failed to revoke token", e);
                    return null;
                }
            }
        }.execute(new Object[0]);
    }
}
