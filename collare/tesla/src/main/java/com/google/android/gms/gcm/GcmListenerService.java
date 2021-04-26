package com.google.android.gms.gcm;

import android.app.ActivityManager;
import android.app.KeyguardManager;
import android.content.Intent;
import android.os.Bundle;
import android.os.Process;
import android.util.Log;
import com.google.android.gms.iid.zze;
import java.util.Iterator;
import java.util.List;

@Deprecated
public class GcmListenerService extends zze {
    static void zzd(Bundle bundle) {
        Iterator<String> it = bundle.keySet().iterator();
        while (it.hasNext()) {
            String next = it.next();
            if (next != null && next.startsWith("google.c.")) {
                it.remove();
            }
        }
    }

    @Override // com.google.android.gms.iid.zze
    public void handleIntent(Intent intent) {
        if (!"com.google.android.c2dm.intent.RECEIVE".equals(intent.getAction())) {
            String valueOf = String.valueOf(intent.getAction());
            Log.w("GcmListenerService", valueOf.length() != 0 ? "Unknown intent action: ".concat(valueOf) : new String("Unknown intent action: "));
            return;
        }
        String stringExtra = intent.getStringExtra("message_type");
        if (stringExtra == null) {
            stringExtra = GoogleCloudMessaging.MESSAGE_TYPE_MESSAGE;
        }
        char c2 = 65535;
        int hashCode = stringExtra.hashCode();
        boolean z = false;
        if (hashCode != -2062414158) {
            if (hashCode != 102161) {
                if (hashCode != 814694033) {
                    if (hashCode == 814800675 && stringExtra.equals(GoogleCloudMessaging.MESSAGE_TYPE_SEND_EVENT)) {
                        c2 = 2;
                    }
                } else if (stringExtra.equals(GoogleCloudMessaging.MESSAGE_TYPE_SEND_ERROR)) {
                    c2 = 3;
                }
            } else if (stringExtra.equals(GoogleCloudMessaging.MESSAGE_TYPE_MESSAGE)) {
                c2 = 0;
            }
        } else if (stringExtra.equals(GoogleCloudMessaging.MESSAGE_TYPE_DELETED)) {
            c2 = 1;
        }
        switch (c2) {
            case 0:
                Bundle extras = intent.getExtras();
                extras.remove("message_type");
                extras.remove("android.support.content.wakelockid");
                if ("1".equals(zzd.zzd(extras, "gcm.n.e")) || zzd.zzd(extras, "gcm.n.icon") != null) {
                    if (!((KeyguardManager) getSystemService("keyguard")).inKeyguardRestrictedInputMode()) {
                        int myPid = Process.myPid();
                        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = ((ActivityManager) getSystemService("activity")).getRunningAppProcesses();
                        if (runningAppProcesses != null) {
                            Iterator<ActivityManager.RunningAppProcessInfo> it = runningAppProcesses.iterator();
                            while (true) {
                                if (it.hasNext()) {
                                    ActivityManager.RunningAppProcessInfo next = it.next();
                                    if (next.pid == myPid) {
                                        if (next.importance == 100) {
                                            z = true;
                                        }
                                    }
                                }
                            }
                        }
                    }
                    if (!z) {
                        zzd.zzd(this).zze(extras);
                        return;
                    }
                    Bundle bundle = new Bundle();
                    Iterator<String> it2 = extras.keySet().iterator();
                    while (it2.hasNext()) {
                        String next2 = it2.next();
                        String string = extras.getString(next2);
                        if (next2.startsWith("gcm.notification.")) {
                            next2 = next2.replace("gcm.notification.", "gcm.n.");
                        }
                        if (next2.startsWith("gcm.n.")) {
                            if (!"gcm.n.e".equals(next2)) {
                                bundle.putString(next2.substring(6), string);
                            }
                            it2.remove();
                        }
                    }
                    String string2 = bundle.getString("sound2");
                    if (string2 != null) {
                        bundle.remove("sound2");
                        bundle.putString("sound", string2);
                    }
                    if (!bundle.isEmpty()) {
                        extras.putBundle("notification", bundle);
                    }
                }
                String string3 = extras.getString("from");
                extras.remove("from");
                zzd(extras);
                onMessageReceived(string3, extras);
                return;
            case 1:
                onDeletedMessages();
                return;
            case 2:
                onMessageSent(intent.getStringExtra("google.message_id"));
                return;
            case 3:
                String stringExtra2 = intent.getStringExtra("google.message_id");
                if (stringExtra2 == null) {
                    stringExtra2 = intent.getStringExtra("message_id");
                }
                onSendError(stringExtra2, intent.getStringExtra("error"));
                return;
            default:
                String valueOf2 = String.valueOf(stringExtra);
                Log.w("GcmListenerService", valueOf2.length() != 0 ? "Received message with unknown type: ".concat(valueOf2) : new String("Received message with unknown type: "));
                return;
        }
    }

    public void onDeletedMessages() {
    }

    public void onMessageReceived(String str, Bundle bundle) {
    }

    public void onMessageSent(String str) {
    }

    public void onSendError(String str, String str2) {
    }
}
