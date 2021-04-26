package com.teslamotors.plugins.biometricauthentication;

import android.app.Activity;
import android.app.KeyguardManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.a.a.a;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.WritableNativeArray;
import com.facebook.react.bridge.WritableNativeMap;
import com.teslamotors.plugins.client.f;

public class BiometricAuthenticationModule extends ReactContextBaseJavaModule {
    public static final String BIOMETRICS_BUNDLE_KEY_FALLBACK_TITLE = "fallback";
    public static final String BIOMETRICS_BUNDLE_KEY_MESSAGE = "message";
    public static final String BIOMETRICS_BUNDLE_KEY_TITLE = "title";
    private static final String BIOMETRICS_DIALOG_TAG = "BiometricsDialogTag";
    private static final String BIOMETRICS_ERROR_NOT_AVAILABLE = "BIOMETRICS_ERROR_BIOMETRICS_NOT_AVAILABLE";
    public static final String BIOMETRICS_ERROR_NO_ERROR = "BIOMETRICS_ERROR_NO_ERROR";
    private static final String BIOMETRICS_ERROR_NO_FINGERS_ENROLLED = "BIOMETRICS_ERROR_NO_FINGERS_ENROLLED";
    private static final String BIOMETRICS_ERROR_NO_PASSCODE_SET = "BIOMETRICS_ERROR_NO_PASSCODE_SET";
    public static final String BIOMETRICS_ERROR_USER_CANCELLED = "BIOMETRICS_ERROR_USER_CANCELLED";
    public static final String BIOMETRICS_ERROR_USER_SELECTED_FALLBACK = "BIOMETRICS_ERROR_USER_SELECTED_FALLBACK";
    private static final String BIOMETRY_TYPE_FINGERPRINT = "FINGERPRINT";
    private static final String REACT_CLASS = "TMBiometricAuthentication";
    public static final String RESULT = "result";
    private static final String TAG = "BiometricAuthenticationModule";

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return REACT_CLASS;
    }

    public BiometricAuthenticationModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }

    @ReactMethod
    public void supportedBiometryTypes(Promise promise) {
        a fingerprintManager = getFingerprintManager(getReactApplicationContext());
        WritableNativeMap writableNativeMap = new WritableNativeMap();
        WritableNativeArray writableNativeArray = new WritableNativeArray();
        try {
            onLogMessage("Checking supported biometry types...");
            String preFlightAuthenticationError = preFlightAuthenticationError(fingerprintManager);
            if (preFlightAuthenticationError == null || preFlightAuthenticationError.equals(BIOMETRICS_ERROR_NO_FINGERS_ENROLLED)) {
                writableNativeArray.pushString(BIOMETRY_TYPE_FINGERPRINT);
            }
        } catch (Exception e) {
            onLogMessage("Error checking supported biometry types - " + e.getMessage());
            f.a(getReactApplicationContext()).a(TAG, "Failed to check biometric device support", e);
        }
        writableNativeMap.putArray(RESULT, writableNativeArray);
        promise.resolve(writableNativeMap);
    }

    @ReactMethod
    private void checkBiometricEligibility(Promise promise) {
        a fingerprintManager = getFingerprintManager(getReactApplicationContext());
        WritableNativeMap writableNativeMap = new WritableNativeMap();
        onLogMessage("Checking eligibility for biometric authentication...");
        String preFlightAuthenticationError = preFlightAuthenticationError(fingerprintManager);
        if (preFlightAuthenticationError == null) {
            writableNativeMap.putString(RESULT, BIOMETRICS_ERROR_NO_ERROR);
        } else {
            writableNativeMap.putString(RESULT, preFlightAuthenticationError);
        }
        promise.resolve(writableNativeMap);
    }

    @ReactMethod
    public void requestBiometricAuthentication(String str, String str2, String str3, Promise promise) {
        a fingerprintManager = getFingerprintManager(getReactApplicationContext());
        WritableNativeMap writableNativeMap = new WritableNativeMap();
        onLogMessage("Biometric authentication requested - checking eligibility...");
        String preFlightAuthenticationError = preFlightAuthenticationError(fingerprintManager);
        if (preFlightAuthenticationError != null) {
            writableNativeMap.putString(RESULT, preFlightAuthenticationError);
            promise.resolve(writableNativeMap);
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putString("title", str);
        bundle.putString(BIOMETRICS_BUNDLE_KEY_FALLBACK_TITLE, str3);
        bundle.putString(BIOMETRICS_BUNDLE_KEY_MESSAGE, str2);
        Activity currentActivity = getCurrentActivity();
        if (currentActivity != null) {
            b bVar = new b();
            bVar.setArguments(bundle);
            bVar.a(promise);
            bVar.show(currentActivity.getFragmentManager(), BIOMETRICS_DIALOG_TAG);
        }
    }

    private String preFlightAuthenticationError(a aVar) {
        if (aVar == null) {
            onLogMessage("On eligibility check, mFingerprintManager was null");
            return BIOMETRICS_ERROR_NOT_AVAILABLE;
        } else if (!aVar.b()) {
            onLogMessage("On eligibility check, mFingerprintManager.isHardwareDetected() was false");
            return BIOMETRICS_ERROR_NOT_AVAILABLE;
        } else if (android.support.v4.app.a.b(getReactApplicationContext(), "android.permission.USE_FINGERPRINT") != 0) {
            onLogMessage("On eligibility check, ActivityCompat.checkSelfPermission(getReactApplicationContext(), Manifest.permission.USE_FINGERPRINT) was not PackageManager.PERMISSION_GRANTED");
            return BIOMETRICS_ERROR_NOT_AVAILABLE;
        } else if (!aVar.a()) {
            onLogMessage("On eligibility check, mFingerprintManager.hasEnrolledFingerprints() was false");
            return BIOMETRICS_ERROR_NO_FINGERS_ENROLLED;
        } else if (!getKeyguardManager(getReactApplicationContext()).isKeyguardSecure()) {
            onLogMessage("On eligibility check, getKeyguardManager(getReactApplicationContext()).isKeyguardSecure() was false");
            return BIOMETRICS_ERROR_NO_PASSCODE_SET;
        } else {
            onLogMessage("All eligibility checks passed");
            return null;
        }
    }

    private void onLogMessage(String str) {
        f.a(getReactApplicationContext()).a(TAG, String.format("[BIOMETRIC SECURITY - NATIVE] %s", str));
    }

    private static a getFingerprintManager(Context context) {
        return a.a(context);
    }

    private static KeyguardManager getKeyguardManager(Context context) {
        return (KeyguardManager) context.getSystemService("keyguard");
    }
}
