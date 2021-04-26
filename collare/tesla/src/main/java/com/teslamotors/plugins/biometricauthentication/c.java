package com.teslamotors.plugins.biometricauthentication;

import android.annotation.TargetApi;
import android.hardware.fingerprint.FingerprintManager;
import android.os.CancellationSignal;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import d.a.b.a;
import d.a.d.a;

@TargetApi(23)
/* compiled from: FingerprintUiHelper */
public class c extends FingerprintManager.AuthenticationCallback {

    /* renamed from: a  reason: collision with root package name */
    Runnable f5331a = new Runnable() {
        /* class com.teslamotors.plugins.biometricauthentication.c.AnonymousClass3 */

        public void run() {
            c.this.f5334d.setTextColor(c.this.f5334d.getResources().getColor(a.C0155a.hint_color, null));
            c.this.f5334d.setText(c.this.f5334d.getResources().getString(a.C0157a.biometric_fingerprint_hint));
            c.this.f5333c.setImageResource(a.b.ic_fp_40px);
        }
    };

    /* renamed from: b  reason: collision with root package name */
    private final FingerprintManager f5332b;

    /* renamed from: c  reason: collision with root package name */
    private ImageView f5333c;

    /* renamed from: d  reason: collision with root package name */
    private TextView f5334d;
    private Button e;
    private Button f;
    private a g;
    private CancellationSignal h;
    private boolean i;

    /* compiled from: FingerprintUiHelper */
    public interface a {
        void a();

        void b();
    }

    public c(FingerprintManager fingerprintManager, ImageView imageView, TextView textView, Button button, Button button2, a aVar) {
        this.f5333c = imageView;
        this.f5334d = textView;
        this.e = button;
        this.f = button2;
        this.g = aVar;
        this.f5332b = fingerprintManager;
    }

    public void a(FingerprintManager.CryptoObject cryptoObject) {
        this.h = new CancellationSignal();
        this.i = false;
        this.f5332b.authenticate(cryptoObject, this.h, 0, this, null);
        this.f5333c.setImageResource(a.b.ic_fp_40px);
    }

    public void a() {
        CancellationSignal cancellationSignal = this.h;
        if (cancellationSignal != null) {
            this.i = true;
            cancellationSignal.cancel();
            this.h = null;
        }
    }

    public void onAuthenticationError(int i2, CharSequence charSequence) {
        if (!this.i && i2 != 5) {
            a(charSequence, i2 == 7);
            this.f5333c.postDelayed(new Runnable() {
                /* class com.teslamotors.plugins.biometricauthentication.c.AnonymousClass1 */

                public void run() {
                    c.this.g.b();
                }
            }, 1600);
        }
    }

    public void onAuthenticationHelp(int i2, CharSequence charSequence) {
        a(charSequence, false);
    }

    public void onAuthenticationFailed() {
        a(this.f5333c.getResources().getString(a.C0157a.biometric_fingerprint_not_recognized), false);
    }

    public void onAuthenticationSucceeded(FingerprintManager.AuthenticationResult authenticationResult) {
        this.f5334d.removeCallbacks(this.f5331a);
        this.f5333c.setImageResource(a.b.ic_fingerprint_success);
        TextView textView = this.f5334d;
        textView.setTextColor(textView.getResources().getColor(a.C0155a.success_color, null));
        TextView textView2 = this.f5334d;
        textView2.setText(textView2.getResources().getString(a.C0157a.biometric_fingerprint_success));
        this.e.setEnabled(false);
        this.f.setEnabled(false);
        this.f5333c.postDelayed(new Runnable() {
            /* class com.teslamotors.plugins.biometricauthentication.c.AnonymousClass2 */

            public void run() {
                c.this.g.a();
            }
        }, 1300);
    }

    private void a(CharSequence charSequence, boolean z) {
        this.f5333c.setImageResource(a.b.ic_fingerprint_error);
        this.f5334d.setText(charSequence);
        TextView textView = this.f5334d;
        textView.setTextColor(textView.getResources().getColor(a.C0155a.warning_color, null));
        this.f5334d.removeCallbacks(this.f5331a);
        if (!z) {
            this.f5334d.postDelayed(this.f5331a, 1600);
        }
    }
}
