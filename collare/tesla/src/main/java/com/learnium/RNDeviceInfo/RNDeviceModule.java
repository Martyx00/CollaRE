package com.learnium.RNDeviceInfo;

import android.app.ActivityManager;
import android.app.KeyguardManager;
import android.bluetooth.BluetoothAdapter;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.format.DateFormat;
import android.text.format.Formatter;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import android.webkit.WebSettings;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.google.android.gms.iid.InstanceID;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import io.a.a.a.a.b.a;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

public class RNDeviceModule extends ReactContextBaseJavaModule {
    ReactApplicationContext reactContext;
    WifiInfo wifiInfo;

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return "RNDeviceInfo";
    }

    public RNDeviceModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
        this.reactContext = reactApplicationContext;
    }

    private WifiInfo getWifiInfo() {
        if (this.wifiInfo == null) {
            this.wifiInfo = ((WifiManager) this.reactContext.getApplicationContext().getSystemService("wifi")).getConnectionInfo();
        }
        return this.wifiInfo;
    }

    private String getCurrentLanguage() {
        Locale locale = getReactApplicationContext().getResources().getConfiguration().locale;
        if (Build.VERSION.SDK_INT >= 21) {
            return locale.toLanguageTag();
        }
        StringBuilder sb = new StringBuilder();
        sb.append(locale.getLanguage());
        if (locale.getCountry() != null) {
            sb.append("-");
            sb.append(locale.getCountry());
        }
        return sb.toString();
    }

    private String getCurrentCountry() {
        return getReactApplicationContext().getResources().getConfiguration().locale.getCountry();
    }

    private Boolean isEmulator() {
        return Boolean.valueOf(Build.FINGERPRINT.startsWith("generic") || Build.FINGERPRINT.startsWith("unknown") || Build.MODEL.contains("google_sdk") || Build.MODEL.contains("Emulator") || Build.MODEL.contains("Android SDK built for x86") || Build.MANUFACTURER.contains("Genymotion") || (Build.BRAND.startsWith("generic") && Build.DEVICE.startsWith("generic")) || "google_sdk".equals(Build.PRODUCT));
    }

    private Boolean isTablet() {
        int i = getReactApplicationContext().getResources().getConfiguration().screenLayout & 15;
        return Boolean.valueOf(i == 3 || i == 4);
    }

    private Boolean is24Hour() {
        return Boolean.valueOf(DateFormat.is24HourFormat(this.reactContext.getApplicationContext()));
    }

    @ReactMethod
    public void isPinOrFingerprintSet(Callback callback) {
        callback.invoke(Boolean.valueOf(((KeyguardManager) this.reactContext.getApplicationContext().getSystemService("keyguard")).isKeyguardSecure()));
    }

    @ReactMethod
    public void getIpAddress(Promise promise) {
        promise.resolve(Formatter.formatIpAddress(getWifiInfo().getIpAddress()));
    }

    @ReactMethod
    public void getMacAddress(Promise promise) {
        promise.resolve(getWifiInfo().getMacAddress());
    }

    private float getStatusBarHeight(DisplayMetrics displayMetrics) {
        ReactApplicationContext reactApplicationContext = getReactApplicationContext();
        int identifier = reactApplicationContext.getResources().getIdentifier("status_bar_height", "dimen", a.ANDROID_CLIENT_TYPE);
        return identifier > 0 ? ((float) reactApplicationContext.getResources().getDimensionPixelSize(identifier)) / displayMetrics.density : BitmapDescriptorFactory.HUE_RED;
    }

    private float getSoftMenuBarHeight(DisplayMetrics displayMetrics) {
        float realHeight = getRealHeight(displayMetrics);
        ReactApplicationContext reactApplicationContext = getReactApplicationContext();
        DisplayMetrics displayMetrics2 = reactApplicationContext.getResources().getDisplayMetrics();
        ((WindowManager) reactApplicationContext.getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
        return Math.max((float) BitmapDescriptorFactory.HUE_RED, realHeight - (((float) displayMetrics2.heightPixels) / displayMetrics.density));
    }

    private float getRealHeight(DisplayMetrics displayMetrics) {
        return ((float) displayMetrics.heightPixels) / displayMetrics.density;
    }

    private float getRealWidth(DisplayMetrics displayMetrics) {
        return ((float) displayMetrics.widthPixels) / displayMetrics.density;
    }

    @ReactMethod
    public String getCarrier() {
        return ((TelephonyManager) this.reactContext.getSystemService("phone")).getNetworkOperatorName();
    }

    @Override // com.facebook.react.bridge.BaseJavaModule
    public Map<String, Object> getConstants() {
        HashMap hashMap = new HashMap();
        PackageManager packageManager = this.reactContext.getPackageManager();
        String packageName = this.reactContext.getPackageName();
        hashMap.put("appVersion", "not available");
        hashMap.put("appName", "not available");
        hashMap.put("buildVersion", "not available");
        hashMap.put("buildNumber", 0);
        try {
            packageManager.getPackageInfo(packageName, 0);
            PackageInfo packageInfo = packageManager.getPackageInfo(packageName, 0);
            String charSequence = this.reactContext.getApplicationInfo().loadLabel(this.reactContext.getPackageManager()).toString();
            hashMap.put("appVersion", packageInfo.versionName);
            hashMap.put("buildNumber", Integer.valueOf(packageInfo.versionCode));
            hashMap.put("firstInstallTime", Long.valueOf(packageInfo.firstInstallTime));
            hashMap.put("lastUpdateTime", Long.valueOf(packageInfo.lastUpdateTime));
            hashMap.put("appName", charSequence);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        String str = "Unknown";
        if (this.reactContext.checkCallingOrSelfPermission("android.permission.BLUETOOTH") == 0) {
            try {
                BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
                if (defaultAdapter != null) {
                    str = defaultAdapter.getName();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        try {
            if (Class.forName("com.google.android.gms.iid.InstanceID") != null) {
                hashMap.put("instanceId", InstanceID.getInstance(this.reactContext).getId());
            }
        } catch (ClassNotFoundException unused) {
            hashMap.put("instanceId", "N/A: Add com.google.android.gms:play-services-gcm to your project.");
        }
        hashMap.put("serialNumber", Build.SERIAL);
        hashMap.put("deviceName", str);
        hashMap.put("systemName", "Android");
        hashMap.put("systemVersion", Build.VERSION.RELEASE);
        hashMap.put("model", Build.MODEL);
        hashMap.put("brand", Build.BRAND);
        hashMap.put("deviceId", Build.BOARD);
        hashMap.put("apiLevel", Integer.valueOf(Build.VERSION.SDK_INT));
        hashMap.put("deviceLocale", getCurrentLanguage());
        hashMap.put("deviceCountry", getCurrentCountry());
        hashMap.put("uniqueId", Settings.Secure.getString(getReactApplicationContext().getContentResolver(), "android_id"));
        hashMap.put("systemManufacturer", Build.MANUFACTURER);
        hashMap.put("bundleId", packageName);
        if (Build.VERSION.SDK_INT >= 17) {
            try {
                hashMap.put("userAgent", WebSettings.getDefaultUserAgent(this.reactContext));
            } catch (RuntimeException unused2) {
                hashMap.put("userAgent", System.getProperty("http.agent"));
            }
        }
        hashMap.put("timezone", TimeZone.getDefault().getID());
        hashMap.put("isEmulator", isEmulator());
        hashMap.put("isTablet", isTablet());
        hashMap.put("is24Hour", is24Hour());
        if (getCurrentActivity() != null && (getCurrentActivity().checkCallingOrSelfPermission("android.permission.READ_PHONE_STATE") == 0 || getCurrentActivity().checkCallingOrSelfPermission("android.permission.READ_SMS") == 0 || getCurrentActivity().checkCallingOrSelfPermission("android.permission.READ_PHONE_NUMBERS") == 0)) {
            hashMap.put("phoneNumber", ((TelephonyManager) this.reactContext.getApplicationContext().getSystemService("phone")).getLine1Number());
        }
        ReactApplicationContext reactApplicationContext = getReactApplicationContext();
        DisplayMetrics displayMetrics = reactApplicationContext.getResources().getDisplayMetrics();
        ((WindowManager) reactApplicationContext.getSystemService("window")).getDefaultDisplay().getRealMetrics(displayMetrics);
        hashMap.put("screenHeight", new Float(getRealHeight(displayMetrics)));
        hashMap.put("screenWidth", new Float(getRealWidth(displayMetrics)));
        HashMap hashMap2 = new HashMap();
        hashMap2.put("top", Float.valueOf(getStatusBarHeight(displayMetrics)));
        hashMap2.put("bottom", Float.valueOf(getSoftMenuBarHeight(displayMetrics)));
        hashMap2.put("left", Double.valueOf(0.0d));
        hashMap2.put("right", Double.valueOf(0.0d));
        hashMap.put("screenSafeAreaInsets", hashMap2);
        hashMap.put("carrier", getCarrier());
        hashMap.put("maxMemory", Long.valueOf(Runtime.getRuntime().maxMemory()));
        ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
        ((ActivityManager) this.reactContext.getSystemService("activity")).getMemoryInfo(memoryInfo);
        hashMap.put("totalMemory", Long.valueOf(memoryInfo.totalMem));
        return hashMap;
    }
}
