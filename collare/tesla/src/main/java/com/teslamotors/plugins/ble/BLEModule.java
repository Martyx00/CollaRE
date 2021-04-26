package com.teslamotors.plugins.ble;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.os.SystemClock;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.google.android.gms.measurement.AppMeasurement;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.teslamotors.a.a;
import com.teslamotors.plugins.ble.b.b;
import com.teslamotors.plugins.ble.b.d;
import com.teslamotors.plugins.ble.b.e;
import com.teslamotors.plugins.ble.b.g;
import com.teslamotors.plugins.ble.b.h;
import com.teslamotors.plugins.ble.b.i;
import com.teslamotors.plugins.ble.b.j;
import com.teslamotors.plugins.ble.b.k;
import com.teslamotors.plugins.client.c;
import com.teslamotors.plugins.client.f;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import org.json.JSONObject;

public class BLEModule extends ReactContextBaseJavaModule implements c {
    private static final String TAG = "BLEModule";
    private Promise clearPromise;
    private AtomicInteger currentCommandID = new AtomicInteger(1);
    private String initializationMessage;
    boolean mBound;
    private f mClient;
    private Map<Integer, Promise> mCommandPromises;
    private Context mContext;
    private boolean mEventSubscriptionsReady;
    private Handler mHandler;
    private final Messenger mMessenger;
    Messenger mService;
    private final ServiceConnection mServiceConnection = new ServiceConnection() {
        /* class com.teslamotors.plugins.ble.BLEModule.AnonymousClass1 */

        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            BLEModule.this.mService = new Messenger(iBinder);
            BLEModule.this.mBound = true;
            Message obtain = Message.obtain();
            obtain.what = e.Register.a();
            BLEModule.this.sendMessage(obtain);
            BLEModule bLEModule = BLEModule.this;
            bLEModule.markEventSubscriptionsReady(bLEModule.mEventSubscriptionsReady);
            if (!BLEModule.this.mEventSubscriptionsReady) {
                BLEModule.this.logInfo("BLEService was bound when event subscriptions weren't ready");
            }
        }

        public void onServiceDisconnected(ComponentName componentName) {
            BLEModule bLEModule = BLEModule.this;
            bLEModule.mBound = false;
            bLEModule.mService = null;
        }
    };
    private Promise publicKeyInfoPromise;
    private Promise scanPromise;

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return "TMBLE";
    }

    class a extends Handler {
        public a(Looper looper) {
            super(looper);
        }

        public void handleMessage(Message message) {
            Bundle data = message.getData();
            data.setClassLoader(getClass().getClassLoader());
            switch (e.a(message.what)) {
                case ClearPeripheralsResult:
                    BLEModule.this.onClearPeripheralsResult();
                    return;
                case ScanForPeripheralsResult:
                    BLEModule.this.onScanForPeripheralsResult((k) data.getParcelable(e.ScanForPeripheralsResult.b()));
                    return;
                case PublicKeyInfoResult:
                    BLEModule.this.onPublicKeyInfoResult((h) data.getParcelable(e.PublicKeyInfoResult.b()));
                    return;
                case CommandResult:
                    BLEModule.this.onCommandResult((com.teslamotors.plugins.ble.b.a) data.getParcelable(e.CommandResult.b()));
                    return;
                case StatusMessage:
                    BLEModule.this.onStatusMessage((i) data.getParcelable(e.StatusMessage.b()));
                    return;
                case SwitchProductMessage:
                    BLEModule.this.onProductSwitchRequest((j) data.getParcelable(e.SwitchProductMessage.b()));
                    return;
                case LogMessage:
                    d dVar = (d) data.getParcelable(e.LogMessage.b());
                    if (dVar == null) {
                        return;
                    }
                    if (dVar.c() == 6) {
                        BLEModule.this.mClient.a(dVar.a(), dVar.b(), dVar.d());
                        return;
                    } else {
                        BLEModule.this.mClient.a(dVar.a(), dVar.b());
                        return;
                    }
                case RegisterComplete:
                    BLEModule.this.getStatus(null);
                    return;
                default:
                    super.handleMessage(message);
                    return;
            }
        }
    }

    private WritableMap getPeripheralsAsMap(g gVar) {
        WritableNativeMap writableNativeMap = new WritableNativeMap();
        if (gVar != null) {
            for (Map.Entry<String, com.teslamotors.plugins.ble.b.f> entry : gVar.f5414a.entrySet()) {
                WritableNativeMap writableNativeMap2 = new WritableNativeMap();
                com.teslamotors.plugins.ble.b.f value = entry.getValue();
                writableNativeMap2.putString("name", value.f5410a);
                writableNativeMap2.putBoolean("connected", value.f5411b);
                writableNativeMap2.putString("identifier", value.f5412c);
                writableNativeMap2.putInt("rssi", value.f5413d);
                writableNativeMap.putMap(entry.getKey(), writableNativeMap2);
            }
        }
        return writableNativeMap;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void onClearPeripheralsResult() {
        Promise promise = this.clearPromise;
        if (promise != null) {
            promise.resolve(null);
            this.clearPromise = null;
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void onScanForPeripheralsResult(k kVar) {
        if (this.scanPromise != null) {
            WritableNativeMap writableNativeMap = new WritableNativeMap();
            writableNativeMap.putInt("total_found", kVar.f5424c);
            writableNativeMap.putString("error", kVar.f5423b);
            writableNativeMap.putBoolean(FirebaseAnalytics.b.SUCCESS, kVar.f5422a);
            writableNativeMap.putMap("peripherals", getPeripheralsAsMap(kVar.f5425d));
            this.scanPromise.resolve(writableNativeMap);
            this.scanPromise = null;
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void onCommandResult(com.teslamotors.plugins.ble.b.a aVar) {
        boolean z = true;
        if (!this.mCommandPromises.containsKey(Integer.valueOf(aVar.f5394c))) {
            logInfo(String.format("Got command result of %s - %s for ID %s with no associated promise", aVar.f5392a.name(), aVar.f5393b, Integer.valueOf(aVar.f5394c)));
            return;
        }
        logInfo(String.format("Got command result of %s - %s for ID %s", aVar.f5392a.name(), aVar.f5393b, Integer.valueOf(aVar.f5394c)));
        this.mHandler.removeCallbacksAndMessages(Integer.valueOf(aVar.f5394c));
        if (aVar.f5392a == b.WAIT) {
            addCommandTimeout(aVar.f5394c);
            return;
        }
        WritableNativeMap writableNativeMap = new WritableNativeMap();
        if (aVar.f5392a != b.OK) {
            z = false;
        }
        writableNativeMap.putBoolean(FirebaseAnalytics.b.SUCCESS, z);
        writableNativeMap.putString("error", aVar.f5393b);
        this.mCommandPromises.get(Integer.valueOf(aVar.f5394c)).resolve(writableNativeMap);
        this.mCommandPromises.remove(Integer.valueOf(aVar.f5394c));
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void onStatusMessage(i iVar) {
        WritableNativeMap writableNativeMap = new WritableNativeMap();
        if (iVar.f5417a != null) {
            writableNativeMap.putBoolean("df", iVar.f5417a.booleanValue());
        }
        if (iVar.f5419c != null) {
            writableNativeMap.putBoolean("dr", iVar.f5419c.booleanValue());
        }
        if (iVar.f5418b != null) {
            writableNativeMap.putBoolean("pf", iVar.f5418b.booleanValue());
        }
        if (iVar.f5420d != null) {
            writableNativeMap.putBoolean("pr", iVar.f5420d.booleanValue());
        }
        if (iVar.e != null) {
            writableNativeMap.putBoolean("ft", iVar.e.booleanValue());
        }
        if (iVar.f != null) {
            writableNativeMap.putBoolean("rt", iVar.f.booleanValue());
        }
        if (iVar.g != null) {
            writableNativeMap.putBoolean("cp", iVar.g.booleanValue());
        }
        writableNativeMap.putBoolean("remote_start", iVar.j);
        writableNativeMap.putBoolean("locked", iVar.h);
        writableNativeMap.putBoolean("locked_before_2017_40", iVar.i);
        writableNativeMap.putString("vin", iVar.k);
        writableNativeMap.putInt("whitelist_key_count", iVar.l);
        writableNativeMap.putBoolean("whitelist_has_key", iVar.m);
        writableNativeMap.putBoolean("local_key_pair", iVar.n);
        writableNativeMap.putBoolean("remote_key_pair", iVar.o);
        writableNativeMap.putBoolean("shared_secret", iVar.p);
        writableNativeMap.putInt("session_counter", (int) iVar.q);
        writableNativeMap.putInt("permissions", iVar.r);
        writableNativeMap.putBoolean("is_connected", iVar.u);
        writableNativeMap.putString("command_peripheral", iVar.v);
        writableNativeMap.putDouble(AppMeasurement.Param.TIMESTAMP, iVar.s);
        writableNativeMap.putBoolean("bluetooth_enabled", iVar.t);
        writableNativeMap.putMap("peripherals", getPeripheralsAsMap(iVar.w));
        writableNativeMap.putBoolean("hw_filtering", iVar.x);
        writableNativeMap.putMap("capabilities", com.teslamotors.plugins.client.d.b.b(new JSONObject(iVar.y)));
        emitDeviceEvent("ble:status", writableNativeMap);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void onProductSwitchRequest(j jVar) {
        WritableNativeMap writableNativeMap = new WritableNativeMap();
        writableNativeMap.putString("vin", jVar.a());
        emitDeviceEvent("ble:productSwitch", writableNativeMap);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void logInfo(String str) {
        this.mClient.a(TAG, String.format("[TMBLE Module] %s", str));
    }

    private void logError(String str, Exception exc) {
        this.mClient.a(TAG, String.format("[TMBLE Module Error] %s", str), exc);
    }

    public BLEModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
        this.mContext = reactApplicationContext.getApplicationContext();
        this.mClient = f.a(reactApplicationContext);
        this.mClient.a(this);
        this.mMessenger = new Messenger(new a(getReactApplicationContext().getMainLooper()));
        this.mCommandPromises = new ConcurrentHashMap();
        this.mHandler = new Handler(getReactApplicationContext().getMainLooper());
    }

    @Override // com.facebook.react.bridge.BaseJavaModule, com.facebook.react.bridge.NativeModule
    public void initialize() {
        String y = this.mClient.y();
        String x = this.mClient.x();
        String e = this.mClient.e(y);
        if (BLEService.a(getReactApplicationContext(), y, x)) {
            startService(y, x, e);
        }
    }

    @Override // com.facebook.react.bridge.BaseJavaModule, com.facebook.react.bridge.NativeModule
    public void onCatalystInstanceDestroy() {
        if (this.mBound) {
            Message obtain = Message.obtain();
            obtain.what = e.Unregister.a();
            sendMessage(obtain);
            getReactApplicationContext().unbindService(this.mServiceConnection);
            this.mBound = false;
        }
    }

    @ReactMethod
    public void markEventSubscriptionsReady(boolean z) {
        String str;
        this.mEventSubscriptionsReady = z;
        if (z && (str = this.initializationMessage) != null) {
            this.mClient.a(TAG, str);
            this.initializationMessage = null;
        }
        Message obtain = Message.obtain();
        obtain.what = e.SetEventSubscriptionsReady.a();
        Bundle bundle = new Bundle();
        bundle.putBoolean(e.SetEventSubscriptionsReady.b(), this.mEventSubscriptionsReady);
        obtain.setData(bundle);
        if (!sendMessage(obtain) && z) {
            logInfo("Failed to notify service that event subscriptions were ready");
        }
    }

    @ReactMethod
    public void scanForPeripherals(String str, boolean z, Promise promise) {
        this.scanPromise = promise;
        Message obtain = Message.obtain();
        obtain.what = e.ScanForPeripherals.a();
        Bundle bundle = new Bundle();
        bundle.putString(e.ScanForPeripherals.b(), str);
        obtain.setData(bundle);
        obtain.arg1 = z ? 1 : 0;
        if (!sendMessage(obtain) && this.scanPromise != null) {
            WritableNativeMap writableNativeMap = new WritableNativeMap();
            writableNativeMap.putBoolean(FirebaseAnalytics.b.SUCCESS, false);
            writableNativeMap.putString("error", "PHONE_KEY_SCAN_FAILURE");
            this.scanPromise.resolve(writableNativeMap);
            this.scanPromise = null;
            f a2 = f.a(getReactApplicationContext());
            startService(str, a2.x(), a2.e(str));
        }
    }

    private void addCommandTimeout(final int i) {
        this.mHandler.removeCallbacksAndMessages(Integer.valueOf(i));
        this.mHandler.postAtTime(new Runnable() {
            /* class com.teslamotors.plugins.ble.BLEModule.AnonymousClass2 */

            public void run() {
                if (BLEModule.this.mCommandPromises.containsKey(Integer.valueOf(i))) {
                    WritableNativeMap writableNativeMap = new WritableNativeMap();
                    writableNativeMap.putBoolean(FirebaseAnalytics.b.SUCCESS, false);
                    writableNativeMap.putString("error", "PHONE_KEY_COMMAND_TIMEOUT");
                    ((Promise) BLEModule.this.mCommandPromises.get(Integer.valueOf(i))).resolve(writableNativeMap);
                    BLEModule.this.mCommandPromises.remove(Integer.valueOf(i));
                }
            }
        }, Integer.valueOf(i), SystemClock.uptimeMillis() + 6000);
    }

    @ReactMethod
    public void resetCommandPeripheral() {
        Message obtain = Message.obtain();
        obtain.what = e.ResetCommandPeripheral.a();
        sendMessage(obtain);
    }

    @ReactMethod
    public void startMonitoringRSSI() {
        Message obtain = Message.obtain();
        obtain.what = e.StartMonitoringRSSI.a();
        sendMessage(obtain);
    }

    @ReactMethod
    public void stopMonitoringRSSI() {
        Message obtain = Message.obtain();
        obtain.what = e.StopMonitoringRSSI.a();
        sendMessage(obtain);
    }

    @ReactMethod
    public void startService(String str, String str2, String str3) {
        Intent intent = new Intent(getReactApplicationContext(), BLEService.class);
        intent.putExtra("VIN", str);
        intent.putExtra("ACCOUNT_EMAIL", str2);
        intent.putExtra("VEHICLE_NAME", str3);
        getReactApplicationContext().startService(intent);
        getReactApplicationContext().bindService(intent, this.mServiceConnection, 1);
    }

    @ReactMethod
    public void getStatus(Promise promise) {
        Message obtain = Message.obtain();
        obtain.what = e.GetStatus.a();
        sendMessage(obtain);
        WritableNativeMap writableNativeMap = new WritableNativeMap();
        writableNativeMap.putBoolean(FirebaseAnalytics.b.SUCCESS, true);
        if (promise != null) {
            promise.resolve(writableNativeMap);
        }
    }

    @ReactMethod
    public void setStayConnectedWhenUnauthorized(boolean z) {
        Message obtain = Message.obtain();
        obtain.what = e.SetConnectionBehaviorWhenUnauthorized.a();
        obtain.arg1 = z ? 1 : 0;
        sendMessage(obtain);
    }

    @ReactMethod
    public void sendNullAuthResponse(Promise promise) {
        Message obtain = Message.obtain();
        obtain.what = e.AuthResponse.a();
        obtain.arg1 = nextCommandID();
        obtain.arg2 = a.d.AUTHENTICATION_LEVEL_NONE.a();
        sendMessage(obtain);
        this.mCommandPromises.put(Integer.valueOf(obtain.arg1), promise);
        addCommandTimeout(obtain.arg1);
    }

    @ReactMethod
    public void commandUnlock(Promise promise) {
        Message obtain = Message.obtain();
        obtain.what = e.UnlockCar.a();
        obtain.arg1 = nextCommandID();
        sendMessage(obtain);
        this.mCommandPromises.put(Integer.valueOf(obtain.arg1), promise);
        addCommandTimeout(obtain.arg1);
    }

    @ReactMethod
    public void commandLock(Promise promise) {
        Message obtain = Message.obtain();
        obtain.what = e.LockCar.a();
        obtain.arg1 = nextCommandID();
        sendMessage(obtain);
        this.mCommandPromises.put(Integer.valueOf(obtain.arg1), promise);
        addCommandTimeout(obtain.arg1);
    }

    @ReactMethod
    public void commandFrunk(Promise promise) {
        Message obtain = Message.obtain();
        obtain.what = e.OpenFrunk.a();
        obtain.arg1 = nextCommandID();
        sendMessage(obtain);
        this.mCommandPromises.put(Integer.valueOf(obtain.arg1), promise);
        addCommandTimeout(obtain.arg1);
    }

    @ReactMethod
    public void commandTrunk(Promise promise) {
        Message obtain = Message.obtain();
        obtain.what = e.OpenTrunk.a();
        obtain.arg1 = nextCommandID();
        sendMessage(obtain);
        this.mCommandPromises.put(Integer.valueOf(obtain.arg1), promise);
        addCommandTimeout(obtain.arg1);
    }

    @ReactMethod
    public void commandOpenChargePort(Promise promise) {
        Message obtain = Message.obtain();
        obtain.what = e.OpenChargePort.a();
        obtain.arg1 = nextCommandID();
        sendMessage(obtain);
        this.mCommandPromises.put(Integer.valueOf(obtain.arg1), promise);
        addCommandTimeout(obtain.arg1);
    }

    @ReactMethod
    public void commandCloseChargePort(Promise promise) {
        Message obtain = Message.obtain();
        obtain.what = e.CloseChargePort.a();
        obtain.arg1 = nextCommandID();
        sendMessage(obtain);
        this.mCommandPromises.put(Integer.valueOf(obtain.arg1), promise);
        addCommandTimeout(obtain.arg1);
    }

    @ReactMethod
    public void clearPeripherals(Promise promise) {
        Message obtain = Message.obtain();
        obtain.what = e.ClearPeripherals.a();
        boolean sendMessage = sendMessage(obtain);
        this.clearPromise = promise;
        if (!sendMessage) {
            f a2 = f.a(getReactApplicationContext());
            a2.a(a2.y(), a2.x(), new HashSet());
            if (this.clearPromise != null) {
                WritableNativeMap writableNativeMap = new WritableNativeMap();
                writableNativeMap.putBoolean(FirebaseAnalytics.b.SUCCESS, true);
                this.clearPromise.resolve(writableNativeMap);
                this.clearPromise = null;
            }
        }
    }

    @ReactMethod
    public void addToWhitelist(Promise promise) {
        Message obtain = Message.obtain();
        obtain.what = e.AddToWhitelist.a();
        obtain.arg1 = nextCommandID();
        sendMessage(obtain);
        this.mCommandPromises.put(Integer.valueOf(obtain.arg1), promise);
        addCommandTimeout(obtain.arg1);
    }

    @ReactMethod
    public void removeFromWhitelist(Promise promise) {
        Message obtain = Message.obtain();
        obtain.what = e.RemoveFromWhitelist.a();
        obtain.arg1 = nextCommandID();
        sendMessage(obtain);
        this.mCommandPromises.put(Integer.valueOf(obtain.arg1), promise);
        addCommandTimeout(obtain.arg1);
    }

    @ReactMethod
    public void updateWhitelist(Promise promise) {
        Message obtain = Message.obtain();
        obtain.what = e.GetWhiteslistUpdate.a();
        obtain.arg1 = nextCommandID();
        sendMessage(obtain);
        this.mCommandPromises.put(Integer.valueOf(obtain.arg1), promise);
        addCommandTimeout(obtain.arg1);
    }

    @ReactMethod
    public void setAccountEmail(String str) {
        if (this.mBound) {
            Message obtain = Message.obtain();
            obtain.what = e.SetAccountEmail.a();
            Bundle bundle = new Bundle();
            bundle.putString(e.SetAccountEmail.b(), str);
            obtain.setData(bundle);
            sendMessage(obtain);
        }
    }

    @ReactMethod
    public void setSelectedVIN(String str, String str2) {
        String x = f.a(getReactApplicationContext()).x();
        if (!this.mBound && BLEService.a(getReactApplicationContext(), str, x)) {
            startService(str, x, str2);
        } else if (this.mBound) {
            Message obtain = Message.obtain();
            obtain.what = e.SetVin.a();
            Bundle bundle = new Bundle();
            ArrayList<String> arrayList = new ArrayList<>();
            arrayList.add(str);
            arrayList.add(str2);
            bundle.putStringArrayList(e.SetVin.b(), arrayList);
            obtain.setData(bundle);
            sendMessage(obtain);
        }
    }

    @ReactMethod
    public void getPublicKeyInfo(Promise promise) {
        this.publicKeyInfoPromise = promise;
        Message obtain = Message.obtain();
        obtain.what = e.GetPublicKeyInfo.a();
        if (!sendMessage(obtain) && promise != null) {
            WritableNativeMap writableNativeMap = new WritableNativeMap();
            writableNativeMap.putBoolean(FirebaseAnalytics.b.SUCCESS, false);
            writableNativeMap.putString("error", "PHONE_KEY_PUBLIC_KEY_RETRIEVAL_ERROR");
            this.publicKeyInfoPromise.resolve(writableNativeMap);
            this.publicKeyInfoPromise = null;
        }
    }

    @ReactMethod
    public void promoteServiceToForeground() {
        if (this.mBound) {
            Message obtain = Message.obtain();
            obtain.what = e.PromoteServiceToForeground.a();
            sendMessage(obtain);
        }
    }

    private void stopService() {
        if (this.mBound) {
            Message obtain = Message.obtain();
            obtain.what = e.Unregister.a();
            sendMessage(obtain);
            getReactApplicationContext().unbindService(this.mServiceConnection);
            this.mBound = false;
        }
        BLEService.d(getReactApplicationContext());
        BLEService.b(getReactApplicationContext());
        getReactApplicationContext().stopService(new Intent(getReactApplicationContext(), BLEService.class));
    }

    private int nextCommandID() {
        return this.currentCommandID.getAndAdd(1);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void onPublicKeyInfoResult(h hVar) {
        if (this.publicKeyInfoPromise != null) {
            WritableNativeMap writableNativeMap = new WritableNativeMap();
            writableNativeMap.putBoolean(FirebaseAnalytics.b.SUCCESS, (hVar == null || hVar.a() == null || hVar.b() == null) ? false : true);
            writableNativeMap.putString("public_key", hVar.b() == null ? "" : g.a(hVar.b()));
            writableNativeMap.putString("key_hash", hVar.a() == null ? "" : hVar.a());
            if (hVar == null) {
                writableNativeMap.putString("error", "PHONE_KEY_PUBLIC_KEY_NOT_FOUND");
            }
            this.publicKeyInfoPromise.resolve(writableNativeMap);
            this.publicKeyInfoPromise = null;
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private boolean sendMessage(Message message) {
        Messenger messenger;
        if (!this.mBound || (messenger = this.mService) == null) {
            return false;
        }
        try {
            message.replyTo = this.mMessenger;
            messenger.send(message);
            return true;
        } catch (RemoteException unused) {
            return false;
        }
    }

    @Override // com.teslamotors.plugins.client.c
    public void emitDeviceEvent(String str, Object obj) {
        ((DeviceEventManagerModule.RCTDeviceEventEmitter) getReactApplicationContext().getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)).emit(str, obj);
    }
}
