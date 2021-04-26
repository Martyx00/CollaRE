package com.teslamotors.plugins.securewebview;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Bundle;
import android.support.v4.content.f;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.google.android.gms.common.internal.ImagesContract;
import com.teslamotors.plugins.securewebview.a;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class SecureWebviewActivity extends Activity {

    /* renamed from: a  reason: collision with root package name */
    private static final Set<String> f5627a = new HashSet(Arrays.asList("www.tesla.com", "stage.tesla.com", "www.tesla.cn", "stage.tesla.cn", "static-assets.tesla.com", "sdlc-assets.tesla.com", "stagecn-assets.teslamotors.com", "static-assets.tesla.cn", "js.stripe.com", "proxysvc-referral-stage.teslamotors.com", "proxysvc-referral-stage2.teslamotors.com", "proxysvc-referral-stage-cn.teslamotors.com", "proxysvc-referral-prod.teslamotors.com", "proxysvc-referral-prod-cn.teslamotors.com", "ownership.tesla.com", "ownership.tesla.cn", "onboarding-pre-delivery-stage.teslamotors.com", "onboarding-pre-delivery-stage-cn.teslamotors.com", "onboarding-pre-delivery-prod.teslamotors.com", "onboarding-pre-delivery.tesla.cn"));

    /* renamed from: b  reason: collision with root package name */
    private BroadcastReceiver f5628b = new BroadcastReceiver() {
        /* class com.teslamotors.plugins.securewebview.SecureWebviewActivity.AnonymousClass2 */

        public void onReceive(Context context, Intent intent) {
            SecureWebviewActivity.this.b();
        }
    };

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(a.c.activity_webview);
        Intent intent = getIntent();
        String stringExtra = intent.getStringExtra("header");
        String stringExtra2 = intent.getStringExtra(ImagesContract.URL);
        int intExtra = intent.getIntExtra("controlTintColor", -7829368);
        int intExtra2 = intent.getIntExtra("barTintColor", -16777216);
        TextView textView = (TextView) findViewById(a.b.textView);
        WebView webView = (WebView) findViewById(a.b.secure_webview);
        a(webView);
        webView.setBackgroundColor(-16777216);
        webView.loadUrl(stringExtra2);
        ((LinearLayout) findViewById(a.b.nav_bar)).setBackgroundColor(intExtra2);
        textView.setText(stringExtra);
        textView.setTextColor(intExtra);
        ((TextView) findViewById(a.b.done_button)).setOnClickListener(new View.OnClickListener() {
            /* class com.teslamotors.plugins.securewebview.SecureWebviewActivity.AnonymousClass1 */

            public void onClick(View view) {
                SecureWebviewActivity.this.b();
            }
        });
    }

    public void onResume() {
        super.onResume();
        f.a(this).a(this.f5628b, new IntentFilter("com.teslamotors.plugins.securewebview.dismiss"));
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        f.a(this).a(this.f5628b);
        super.onPause();
    }

    public void onBackPressed() {
        super.onBackPressed();
        b();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void b() {
        setResult(201);
        android.support.v4.app.a.b((Activity) this);
    }

    private void a(WebView webView) {
        WebSettings settings = webView.getSettings();
        settings.setAllowContentAccess(false);
        settings.setAllowFileAccess(false);
        settings.setAllowFileAccessFromFileURLs(false);
        settings.setAllowUniversalAccessFromFileURLs(false);
        settings.setJavaScriptEnabled(true);
        webView.setWebViewClient(new a());
    }

    /* access modifiers changed from: private */
    public class a extends WebViewClient {
        private a() {
        }

        @Override // android.webkit.WebViewClient
        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            return a(webView, Uri.parse(str));
        }

        @Override // android.webkit.WebViewClient
        @TargetApi(24)
        public boolean shouldOverrideUrlLoading(WebView webView, WebResourceRequest webResourceRequest) {
            return a(webView, webResourceRequest.getUrl());
        }

        public void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
            webView.loadUrl("https://www.tesla.com");
        }

        private boolean a(WebView webView, Uri uri) {
            if (SecureWebviewActivity.f5627a.contains(uri.getHost()) && "https".equals(uri.getScheme())) {
                return true;
            }
            webView.loadUrl("https://www.tesla.com");
            return true;
        }
    }
}
