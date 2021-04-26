package com.google.android.gms.tagmanager;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import com.google.android.gms.common.util.VisibleForTesting;

@VisibleForTesting
public class PreviewActivity extends Activity {
    public void onCreate(Bundle bundle) {
        try {
            super.onCreate(bundle);
            zzdi.zzcz("Preview activity");
            Uri data = getIntent().getData();
            if (!TagManager.getInstance(this).zzb(data)) {
                String valueOf = String.valueOf(data);
                StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 73);
                sb.append("Cannot preview the app with the uri: ");
                sb.append(valueOf);
                sb.append(". Launching current version instead.");
                String sb2 = sb.toString();
                zzdi.zzab(sb2);
                AlertDialog create = new AlertDialog.Builder(this).create();
                create.setTitle("Preview failure");
                create.setMessage(sb2);
                create.setButton(-1, "Continue", new zzeg(this));
                create.show();
            }
            Intent launchIntentForPackage = getPackageManager().getLaunchIntentForPackage(getPackageName());
            if (launchIntentForPackage != null) {
                String valueOf2 = String.valueOf(getPackageName());
                zzdi.zzcz(valueOf2.length() != 0 ? "Invoke the launch activity for package name: ".concat(valueOf2) : new String("Invoke the launch activity for package name: "));
                startActivity(launchIntentForPackage);
                return;
            }
            String valueOf3 = String.valueOf(getPackageName());
            zzdi.zzcz(valueOf3.length() != 0 ? "No launch activity found for package name: ".concat(valueOf3) : new String("No launch activity found for package name: "));
        } catch (Exception e) {
            String valueOf4 = String.valueOf(e.getMessage());
            zzdi.e(valueOf4.length() != 0 ? "Calling preview threw an exception: ".concat(valueOf4) : new String("Calling preview threw an exception: "));
        }
    }
}
