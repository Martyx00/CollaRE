package com.facebook.react.modules.statusbar;

import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.support.v4.g.t;
import android.view.View;
import android.view.WindowInsets;
import com.facebook.react.bridge.GuardedRunnable;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.UiThreadUtil;
import com.facebook.react.common.e;
import com.facebook.react.module.a.a;
import com.facebook.react.uimanager.o;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import java.util.Map;
import org.spongycastle.asn1.cmp.PKIFailureInfo;

@a(a = StatusBarModule.NAME)
public class StatusBarModule extends ReactContextBaseJavaModule {
    private static final String DEFAULT_BACKGROUND_COLOR_KEY = "DEFAULT_BACKGROUND_COLOR";
    private static final String HEIGHT_KEY = "HEIGHT";
    public static final String NAME = "StatusBarManager";

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return NAME;
    }

    public StatusBarModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }

    @Override // com.facebook.react.bridge.BaseJavaModule
    public Map<String, Object> getConstants() {
        ReactApplicationContext reactApplicationContext = getReactApplicationContext();
        Activity currentActivity = getCurrentActivity();
        int identifier = reactApplicationContext.getResources().getIdentifier("status_bar_height", "dimen", io.a.a.a.a.b.a.ANDROID_CLIENT_TYPE);
        float d2 = identifier > 0 ? o.d((float) reactApplicationContext.getResources().getDimensionPixelSize(identifier)) : BitmapDescriptorFactory.HUE_RED;
        String str = "black";
        if (currentActivity != null && Build.VERSION.SDK_INT >= 21) {
            str = String.format("#%06X", Integer.valueOf(currentActivity.getWindow().getStatusBarColor() & 16777215));
        }
        return e.a(HEIGHT_KEY, Float.valueOf(d2), DEFAULT_BACKGROUND_COLOR_KEY, str);
    }

    @ReactMethod
    public void setColor(final int i, final boolean z) {
        final Activity currentActivity = getCurrentActivity();
        if (currentActivity == null) {
            com.facebook.common.e.a.c("ReactNative", "StatusBarModule: Ignored status bar change, current activity is null.");
        } else if (Build.VERSION.SDK_INT >= 21) {
            UiThreadUtil.runOnUiThread(new GuardedRunnable(getReactApplicationContext()) {
                /* class com.facebook.react.modules.statusbar.StatusBarModule.AnonymousClass1 */

                @Override // com.facebook.react.bridge.GuardedRunnable
                @TargetApi(21)
                public void runGuarded() {
                    currentActivity.getWindow().addFlags(PKIFailureInfo.systemUnavail);
                    if (z) {
                        int statusBarColor = currentActivity.getWindow().getStatusBarColor();
                        ValueAnimator ofObject = ValueAnimator.ofObject(new ArgbEvaluator(), Integer.valueOf(statusBarColor), Integer.valueOf(i));
                        ofObject.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                            /* class com.facebook.react.modules.statusbar.StatusBarModule.AnonymousClass1.AnonymousClass1 */

                            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                                currentActivity.getWindow().setStatusBarColor(((Integer) valueAnimator.getAnimatedValue()).intValue());
                            }
                        });
                        ofObject.setDuration(300L).setStartDelay(0);
                        ofObject.start();
                        return;
                    }
                    currentActivity.getWindow().setStatusBarColor(i);
                }
            });
        }
    }

    @ReactMethod
    public void setTranslucent(final boolean z) {
        final Activity currentActivity = getCurrentActivity();
        if (currentActivity == null) {
            com.facebook.common.e.a.c("ReactNative", "StatusBarModule: Ignored status bar change, current activity is null.");
        } else if (Build.VERSION.SDK_INT >= 21) {
            UiThreadUtil.runOnUiThread(new GuardedRunnable(getReactApplicationContext()) {
                /* class com.facebook.react.modules.statusbar.StatusBarModule.AnonymousClass2 */

                @Override // com.facebook.react.bridge.GuardedRunnable
                @TargetApi(21)
                public void runGuarded() {
                    View decorView = currentActivity.getWindow().getDecorView();
                    if (z) {
                        decorView.setOnApplyWindowInsetsListener(new View.OnApplyWindowInsetsListener() {
                            /* class com.facebook.react.modules.statusbar.StatusBarModule.AnonymousClass2.AnonymousClass1 */

                            public WindowInsets onApplyWindowInsets(View view, WindowInsets windowInsets) {
                                WindowInsets onApplyWindowInsets = view.onApplyWindowInsets(windowInsets);
                                return onApplyWindowInsets.replaceSystemWindowInsets(onApplyWindowInsets.getSystemWindowInsetLeft(), 0, onApplyWindowInsets.getSystemWindowInsetRight(), onApplyWindowInsets.getSystemWindowInsetBottom());
                            }
                        });
                    } else {
                        decorView.setOnApplyWindowInsetsListener(null);
                    }
                    t.m(decorView);
                }
            });
        }
    }

    @ReactMethod
    public void setHidden(final boolean z) {
        final Activity currentActivity = getCurrentActivity();
        if (currentActivity == null) {
            com.facebook.common.e.a.c("ReactNative", "StatusBarModule: Ignored status bar change, current activity is null.");
        } else {
            UiThreadUtil.runOnUiThread(new Runnable() {
                /* class com.facebook.react.modules.statusbar.StatusBarModule.AnonymousClass3 */

                public void run() {
                    if (z) {
                        currentActivity.getWindow().addFlags(1024);
                        currentActivity.getWindow().clearFlags(2048);
                        return;
                    }
                    currentActivity.getWindow().addFlags(2048);
                    currentActivity.getWindow().clearFlags(1024);
                }
            });
        }
    }

    @ReactMethod
    public void setStyle(final String str) {
        final Activity currentActivity = getCurrentActivity();
        if (currentActivity == null) {
            com.facebook.common.e.a.c("ReactNative", "StatusBarModule: Ignored status bar change, current activity is null.");
        } else if (Build.VERSION.SDK_INT >= 23) {
            UiThreadUtil.runOnUiThread(new Runnable() {
                /* class com.facebook.react.modules.statusbar.StatusBarModule.AnonymousClass4 */

                @TargetApi(23)
                public void run() {
                    View decorView = currentActivity.getWindow().getDecorView();
                    int systemUiVisibility = decorView.getSystemUiVisibility();
                    decorView.setSystemUiVisibility("dark-content".equals(str) ? systemUiVisibility | PKIFailureInfo.certRevoked : systemUiVisibility & -8193);
                }
            });
        }
    }
}
