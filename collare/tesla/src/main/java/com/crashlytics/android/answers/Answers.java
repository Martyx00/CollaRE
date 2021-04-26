package com.crashlytics.android.answers;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import io.a.a.a.a.b.j;
import io.a.a.a.a.b.q;
import io.a.a.a.a.g.t;
import io.a.a.a.c;
import io.a.a.a.i;
import io.a.a.a.l;
import java.io.File;

public class Answers extends i<Boolean> {
    static final String CRASHLYTICS_API_ENDPOINT = "com.crashlytics.ApiEndpoint";
    public static final String TAG = "Answers";
    SessionAnalyticsManager analyticsManager;
    boolean firebaseEnabled = false;

    @Override // io.a.a.a.i
    public String getIdentifier() {
        return "com.crashlytics.sdk.android:answers";
    }

    @Override // io.a.a.a.i
    public String getVersion() {
        return "1.4.3.27";
    }

    public static Answers getInstance() {
        return (Answers) c.a(Answers.class);
    }

    public void logCustom(CustomEvent customEvent) {
        if (customEvent == null) {
            throw new NullPointerException("event must not be null");
        } else if (this.firebaseEnabled) {
            logFirebaseModeEnabledWarning("logCustom");
        } else {
            SessionAnalyticsManager sessionAnalyticsManager = this.analyticsManager;
            if (sessionAnalyticsManager != null) {
                sessionAnalyticsManager.onCustom(customEvent);
            }
        }
    }

    public void logPurchase(PurchaseEvent purchaseEvent) {
        if (purchaseEvent == null) {
            throw new NullPointerException("event must not be null");
        } else if (this.firebaseEnabled) {
            logFirebaseModeEnabledWarning("logPurchase");
        } else {
            SessionAnalyticsManager sessionAnalyticsManager = this.analyticsManager;
            if (sessionAnalyticsManager != null) {
                sessionAnalyticsManager.onPredefined(purchaseEvent);
            }
        }
    }

    public void logLogin(LoginEvent loginEvent) {
        if (loginEvent == null) {
            throw new NullPointerException("event must not be null");
        } else if (this.firebaseEnabled) {
            logFirebaseModeEnabledWarning("logLogin");
        } else {
            SessionAnalyticsManager sessionAnalyticsManager = this.analyticsManager;
            if (sessionAnalyticsManager != null) {
                sessionAnalyticsManager.onPredefined(loginEvent);
            }
        }
    }

    public void logShare(ShareEvent shareEvent) {
        if (shareEvent == null) {
            throw new NullPointerException("event must not be null");
        } else if (this.firebaseEnabled) {
            logFirebaseModeEnabledWarning("logShare");
        } else {
            SessionAnalyticsManager sessionAnalyticsManager = this.analyticsManager;
            if (sessionAnalyticsManager != null) {
                sessionAnalyticsManager.onPredefined(shareEvent);
            }
        }
    }

    public void logInvite(InviteEvent inviteEvent) {
        if (inviteEvent == null) {
            throw new NullPointerException("event must not be null");
        } else if (this.firebaseEnabled) {
            logFirebaseModeEnabledWarning("logInvite");
        } else {
            SessionAnalyticsManager sessionAnalyticsManager = this.analyticsManager;
            if (sessionAnalyticsManager != null) {
                sessionAnalyticsManager.onPredefined(inviteEvent);
            }
        }
    }

    public void logSignUp(SignUpEvent signUpEvent) {
        if (signUpEvent == null) {
            throw new NullPointerException("event must not be null");
        } else if (this.firebaseEnabled) {
            logFirebaseModeEnabledWarning("logSignUp");
        } else {
            SessionAnalyticsManager sessionAnalyticsManager = this.analyticsManager;
            if (sessionAnalyticsManager != null) {
                sessionAnalyticsManager.onPredefined(signUpEvent);
            }
        }
    }

    public void logLevelStart(LevelStartEvent levelStartEvent) {
        if (levelStartEvent == null) {
            throw new NullPointerException("event must not be null");
        } else if (this.firebaseEnabled) {
            logFirebaseModeEnabledWarning("logLevelStart");
        } else {
            SessionAnalyticsManager sessionAnalyticsManager = this.analyticsManager;
            if (sessionAnalyticsManager != null) {
                sessionAnalyticsManager.onPredefined(levelStartEvent);
            }
        }
    }

    public void logLevelEnd(LevelEndEvent levelEndEvent) {
        if (levelEndEvent == null) {
            throw new NullPointerException("event must not be null");
        } else if (this.firebaseEnabled) {
            logFirebaseModeEnabledWarning("logLevelEnd");
        } else {
            SessionAnalyticsManager sessionAnalyticsManager = this.analyticsManager;
            if (sessionAnalyticsManager != null) {
                sessionAnalyticsManager.onPredefined(levelEndEvent);
            }
        }
    }

    public void logAddToCart(AddToCartEvent addToCartEvent) {
        if (addToCartEvent == null) {
            throw new NullPointerException("event must not be null");
        } else if (this.firebaseEnabled) {
            logFirebaseModeEnabledWarning("logAddToCart");
        } else {
            SessionAnalyticsManager sessionAnalyticsManager = this.analyticsManager;
            if (sessionAnalyticsManager != null) {
                sessionAnalyticsManager.onPredefined(addToCartEvent);
            }
        }
    }

    public void logStartCheckout(StartCheckoutEvent startCheckoutEvent) {
        if (startCheckoutEvent == null) {
            throw new NullPointerException("event must not be null");
        } else if (this.firebaseEnabled) {
            logFirebaseModeEnabledWarning("logStartCheckout");
        } else {
            SessionAnalyticsManager sessionAnalyticsManager = this.analyticsManager;
            if (sessionAnalyticsManager != null) {
                sessionAnalyticsManager.onPredefined(startCheckoutEvent);
            }
        }
    }

    public void logRating(RatingEvent ratingEvent) {
        if (ratingEvent == null) {
            throw new NullPointerException("event must not be null");
        } else if (this.firebaseEnabled) {
            logFirebaseModeEnabledWarning("logRating");
        } else {
            SessionAnalyticsManager sessionAnalyticsManager = this.analyticsManager;
            if (sessionAnalyticsManager != null) {
                sessionAnalyticsManager.onPredefined(ratingEvent);
            }
        }
    }

    public void logContentView(ContentViewEvent contentViewEvent) {
        if (contentViewEvent == null) {
            throw new NullPointerException("event must not be null");
        } else if (this.firebaseEnabled) {
            logFirebaseModeEnabledWarning("logContentView");
        } else {
            SessionAnalyticsManager sessionAnalyticsManager = this.analyticsManager;
            if (sessionAnalyticsManager != null) {
                sessionAnalyticsManager.onPredefined(contentViewEvent);
            }
        }
    }

    public void logSearch(SearchEvent searchEvent) {
        if (searchEvent == null) {
            throw new NullPointerException("event must not be null");
        } else if (this.firebaseEnabled) {
            logFirebaseModeEnabledWarning("logSearch");
        } else {
            SessionAnalyticsManager sessionAnalyticsManager = this.analyticsManager;
            if (sessionAnalyticsManager != null) {
                sessionAnalyticsManager.onPredefined(searchEvent);
            }
        }
    }

    public void onException(j.b bVar) {
        SessionAnalyticsManager sessionAnalyticsManager = this.analyticsManager;
        if (sessionAnalyticsManager != null) {
            sessionAnalyticsManager.onError(bVar.a());
        }
    }

    public void onException(j.a aVar) {
        SessionAnalyticsManager sessionAnalyticsManager = this.analyticsManager;
        if (sessionAnalyticsManager != null) {
            sessionAnalyticsManager.onCrash(aVar.a(), aVar.b());
        }
    }

    /* access modifiers changed from: protected */
    @Override // io.a.a.a.i
    @SuppressLint({"NewApi"})
    public boolean onPreExecute() {
        long j;
        try {
            Context context = getContext();
            PackageManager packageManager = context.getPackageManager();
            String packageName = context.getPackageName();
            PackageInfo packageInfo = packageManager.getPackageInfo(packageName, 0);
            String num = Integer.toString(packageInfo.versionCode);
            String str = packageInfo.versionName == null ? "0.0" : packageInfo.versionName;
            if (Build.VERSION.SDK_INT >= 9) {
                j = packageInfo.firstInstallTime;
            } else {
                j = new File(packageManager.getApplicationInfo(packageName, 0).sourceDir).lastModified();
            }
            this.analyticsManager = SessionAnalyticsManager.build(this, context, getIdManager(), num, str, j);
            this.analyticsManager.enable();
            this.firebaseEnabled = new q().b(context);
            return true;
        } catch (Exception e) {
            c.g().e(TAG, "Error retrieving app properties", e);
            return false;
        }
    }

    /* access modifiers changed from: protected */
    @Override // io.a.a.a.i
    public Boolean doInBackground() {
        if (!new q().c(getContext())) {
            c.g().a("Fabric", "Analytics collection disabled, because data collection is disabled by Firebase.");
            this.analyticsManager.disable();
            return false;
        }
        try {
            t b2 = io.a.a.a.a.g.q.a().b();
            if (b2 == null) {
                c.g().e(TAG, "Failed to retrieve settings");
                return false;
            } else if (b2.f6072d.f6047d) {
                c.g().a(TAG, "Analytics collection enabled");
                this.analyticsManager.setAnalyticsSettingsData(b2.e, getOverridenSpiEndpoint());
                return true;
            } else {
                c.g().a(TAG, "Analytics collection disabled");
                this.analyticsManager.disable();
                return false;
            }
        } catch (Exception e) {
            c.g().e(TAG, "Error dealing with settings", e);
            return false;
        }
    }

    /* access modifiers changed from: package-private */
    public String getOverridenSpiEndpoint() {
        return io.a.a.a.a.b.i.b(getContext(), CRASHLYTICS_API_ENDPOINT);
    }

    private void logFirebaseModeEnabledWarning(String str) {
        l g = c.g();
        g.d(TAG, "Method " + str + " is not supported when using Crashlytics through Firebase.");
    }
}
