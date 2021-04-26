package com.teslamotors.plugins.ble;

import android.annotation.TargetApi;
import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanFilter;
import android.bluetooth.le.ScanResult;
import android.bluetooth.le.ScanSettings;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.hardware.TriggerEvent;
import android.hardware.TriggerEventListener;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.os.ParcelUuid;
import android.os.Parcelable;
import android.os.RemoteException;
import android.support.v4.app.y;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import com.google.protobuf.v;
import com.teslamotors.a.a;
import com.teslamotors.plugins.ble.b.g;
import com.teslamotors.plugins.ble.b.h;
import com.teslamotors.plugins.ble.b.i;
import com.teslamotors.plugins.ble.b.j;
import com.teslamotors.plugins.ble.b.k;
import com.teslamotors.plugins.client.f;
import d.a.c.a;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ArrayBlockingQueue;
import org.spongycastle.asn1.cmp.PKIFailureInfo;
import org.spongycastle.pqc.jcajce.spec.McElieceCCA2KeyGenParameterSpec;

@TargetApi(21)
public class BLEService extends Service implements SensorEventListener {

    /* renamed from: a  reason: collision with root package name */
    public static final ScanSettings f5344a = new ScanSettings.Builder().setScanMode(2).build();

    /* renamed from: c  reason: collision with root package name */
    private static final String f5345c = "BLEService";
    private SensorManager A;
    private Sensor B;
    private Sensor C;
    private Sensor D;
    private final TriggerEventListener E = new e();
    private final TriggerEventListener F = new b();
    private final TriggerEventListener G = new d();
    private boolean H = false;
    private boolean I = false;
    private boolean J = false;
    private boolean K = false;
    private a.an L = a.an.IMU_STATE_NOT_CONFIGURED;
    private boolean M = false;
    private CountDownTimer N = new CountDownTimer(300000, 60000) {
        /* class com.teslamotors.plugins.ble.BLEService.AnonymousClass1 */

        public void onTick(long j) {
            BLEService.this.f((BLEService) String.format("Stationary alarm seconds remaining: %d", Long.valueOf(j / 1000)));
            if (!BLEService.this.H && BLEService.this.B != null) {
                BLEService bLEService = BLEService.this;
                bLEService.H = bLEService.A.requestTriggerSensor(BLEService.this.E, BLEService.this.B);
            }
            if (!BLEService.this.I && BLEService.this.C != null) {
                BLEService bLEService2 = BLEService.this;
                bLEService2.I = bLEService2.A.requestTriggerSensor(BLEService.this.F, BLEService.this.C);
            }
            if (!BLEService.this.J && BLEService.this.D != null) {
                BLEService bLEService3 = BLEService.this;
                bLEService3.J = bLEService3.A.requestTriggerSensor(BLEService.this.G, BLEService.this.D);
            }
        }

        public void onFinish() {
            BLEService.this.f((BLEService) "Stationary alarm triggered");
            BLEService.this.M = false;
            if (BLEService.this.K) {
                BLEService.this.i.a(a.c.ACTIVITY_STATIONARY);
            }
            if (BLEService.this.L != a.an.IMU_STATE_INACTIVITY) {
                BLEService.this.L = a.an.IMU_STATE_INACTIVITY;
                BLEService.this.i.a(a.an.IMU_STATE_INACTIVITY);
            }
        }
    };
    private final BroadcastReceiver O = new BroadcastReceiver() {
        /* class com.teslamotors.plugins.ble.BLEService.AnonymousClass2 */

        /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals("android.bluetooth.adapter.action.STATE_CHANGED")) {
                int intExtra = intent.getIntExtra("android.bluetooth.adapter.extra.STATE", PKIFailureInfo.systemUnavail);
                BLEService.this.h.removeCallbacks(BLEService.this.Q);
                switch (intExtra) {
                    case 10:
                        BLEService.this.h.postDelayed(BLEService.this.Q, 5000);
                        break;
                    case 11:
                        BLEService.this.f((BLEService) "Bluetooth adapter is turning ON");
                        return;
                    case 12:
                        BLEService.this.f((BLEService) "Bluetooth adapter is ON");
                        BLEService.this.i.c();
                        BLEService.this.i.d();
                        BLEService.this.h.postDelayed(BLEService.this.Q, 100);
                        return;
                    case 13:
                        break;
                    default:
                        return;
                }
                Object[] objArr = new Object[1];
                objArr[0] = intExtra == 10 ? "" : "turning";
                BLEService.this.f((BLEService) String.format("Bluetooth adapter is %s OFF", objArr));
                BLEService.this.c((BLEService) false);
                BLEService.this.x = false;
                BLEService.this.K = false;
                BLEService.this.i.e();
                BLEService.this.i.b();
            } else if (action.equals("android.intent.action.SCREEN_ON")) {
                BLEService.this.f((BLEService) "Screen On received");
            } else if (action.equals("android.intent.action.BATTERY_CHANGED")) {
                BLEService.this.n = BLEService.a(intent);
                BLEService.this.d();
            }
        }
    };
    private Runnable P = new Runnable() {
        /* class com.teslamotors.plugins.ble.BLEService.AnonymousClass3 */

        public void run() {
            BLEService bLEService = BLEService.this;
            Object[] objArr = new Object[1];
            objArr[0] = bLEService.u ? "saving" : "NOT saving";
            bLEService.f((BLEService) String.format("Timeout fired - stopping scan and %s results", objArr));
            BLEService bLEService2 = BLEService.this;
            bLEService2.c((BLEService) bLEService2.u);
        }
    };
    private Runnable Q = new Runnable() {
        /* class com.teslamotors.plugins.ble.BLEService.AnonymousClass4 */

        public void run() {
            NotificationManager notificationManager = (NotificationManager) BLEService.this.getSystemService("notification");
            if (notificationManager != null) {
                BLEService bLEService = BLEService.this;
                if (!BLEService.b(bLEService, bLEService.j, BLEService.this.k) || BLEService.this.g == null || BLEService.this.g.isEnabled()) {
                    try {
                        notificationManager.cancel(444);
                    } catch (Exception e) {
                        BLEService.this.a((BLEService) "Failed to cancel nag notification", (String) e);
                    }
                } else {
                    notificationManager.notify(444, new y.c(BLEService.this, "default_channel").a(a.C0156a.ic_notification).a((CharSequence) BLEService.this.getString(a.b.phone_key_notification_bluetooth_disabled_title)).b((CharSequence) BLEService.this.getString(a.b.phone_key_notification_bluetooth_disabled_message)).b(0).b());
                }
            }
        }
    };
    private Runnable R = new Runnable() {
        /* class com.teslamotors.plugins.ble.BLEService.AnonymousClass5 */

        public void run() {
            BLEService.this.b(false);
        }
    };
    private final ScanCallback S = new ScanCallback() {
        /* class com.teslamotors.plugins.ble.BLEService.AnonymousClass6 */

        public void onScanResult(int i, ScanResult scanResult) {
            BluetoothDevice device = scanResult.getDevice();
            String address = device.getAddress();
            if (!BLEService.this.s.contains(address)) {
                if (scanResult.getScanRecord() == null || scanResult.getScanRecord().getServiceUuids() == null) {
                    BLEService.this.s.add(address);
                    return;
                }
                BLEService.this.s.add(address);
                for (ParcelUuid parcelUuid : scanResult.getScanRecord().getServiceUuids()) {
                    if (parcelUuid.equals(a.f5369c)) {
                        BLEService.this.f((BLEService) String.format("Scan result service: %s %s %s", device.getName(), device.getAddress(), parcelUuid.toString()));
                        if (BLEService.this.u) {
                            if (BLEService.this.a((BLEService) scanResult) && !BLEService.this.t.containsKey(address)) {
                                BLEService.this.r.add(address);
                                BLEService.this.t.put(address, new com.teslamotors.plugins.ble.a.c(BLEService.this.e((BLEService) scanResult.getScanRecord().getDeviceName()), scanResult.getDevice(), null, BLEService.this.i, BLEService.this.g, BLEService.this.getApplicationContext()));
                                if (BLEService.this.r.size() >= BLEService.this.z.size()) {
                                    BLEService.this.f((BLEService) "Found all peripherals - stopping scan");
                                    BLEService.this.c((BLEService) true);
                                }
                            }
                        }
                    }
                }
            }
        }

        public void onScanFailed(int i) {
            BLEService.this.f((BLEService) String.format("Scan Failed: %s", g.a(i)));
        }
    };

    /* renamed from: b  reason: collision with root package name */
    final Messenger f5346b = new Messenger(new a());

    /* renamed from: d  reason: collision with root package name */
    private Queue<String> f5347d = new ArrayBlockingQueue(100);
    private boolean e = false;
    private BluetoothManager f;
    private BluetoothAdapter g;
    private volatile c h;
    private b i = new c();
    private String j = null;
    private String k = "";
    private String l = "";
    private long m = 0;
    private boolean n;
    private boolean o;
    private boolean p;
    private i q;
    private HashSet<String> r = new HashSet<>();
    private HashSet<String> s = new HashSet<>();
    private Map<String, com.teslamotors.plugins.ble.a.c> t;
    private boolean u;
    private String v;
    private BluetoothLeScanner w;
    private boolean x;
    private volatile List<Messenger> y = Collections.synchronizedList(new ArrayList());
    private Set<String> z = a.j;

    public void d() {
    }

    class e extends TriggerEventListener {
        e() {
        }

        public void onTrigger(TriggerEvent triggerEvent) {
            BLEService bLEService = BLEService.this;
            bLEService.H = bLEService.A.requestTriggerSensor(BLEService.this.E, BLEService.this.B);
            if (!BLEService.this.M) {
                BLEService.this.M = true;
                BLEService.this.N.start();
                if (BLEService.this.K) {
                    BLEService.this.i.a(a.c.ACTIVITY_STATIONARY);
                }
            }
        }
    }

    class b extends TriggerEventListener {
        b() {
        }

        public void onTrigger(TriggerEvent triggerEvent) {
            BLEService.this.a("MotionListener", "Motion detected !");
            if (BLEService.this.K) {
                BLEService.this.i.a(a.c.ACTIVITY_MOTION);
            }
            if (BLEService.this.L != a.an.IMU_STATE_ACTIVITY) {
                BLEService.this.L = a.an.IMU_STATE_ACTIVITY;
                BLEService.this.i.a(a.an.IMU_STATE_ACTIVITY);
            }
            BLEService bLEService = BLEService.this;
            bLEService.I = bLEService.A.requestTriggerSensor(BLEService.this.F, BLEService.this.C);
            if (BLEService.this.M) {
                BLEService.this.N.cancel();
                BLEService.this.M = false;
            }
        }
    }

    class d extends TriggerEventListener {
        d() {
        }

        public void onTrigger(TriggerEvent triggerEvent) {
            BLEService.this.a("SignificantMotionListener", "Significant Motion detected !");
            if (BLEService.this.K) {
                BLEService.this.i.a(a.c.ACTIVITY_SIGNIFICANT_MOTION);
            }
            BLEService bLEService = BLEService.this;
            bLEService.J = bLEService.A.requestTriggerSensor(BLEService.this.G, BLEService.this.D);
        }
    }

    public void a(boolean z2) {
        this.K = z2;
    }

    public a.an a() {
        return this.L;
    }

    public void onCreate() {
        super.onCreate();
        if (Build.VERSION.SDK_INT >= 26) {
            com.teslamotors.plugins.notifications.b.a(getApplicationContext()).a();
        }
        com.teslamotors.plugins.crashlytics.b.a(this);
        f a2 = f.a(getApplicationContext());
        a2.F();
        this.h = new c(Looper.getMainLooper());
        this.j = a2.y();
        this.k = a2.x();
        this.l = a2.e(this.j);
        f("onCreate");
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.SCREEN_ON");
        intentFilter.addAction("android.bluetooth.adapter.action.STATE_CHANGED");
        registerReceiver(this.O, intentFilter);
        c(getApplicationContext());
        j();
    }

    private Notification f() {
        return g().b();
    }

    private y.c g() {
        i iVar = this.q;
        y.c a2 = a((Context) this, iVar == null ? "" : getString(iVar.a()));
        if (this.i.i() == com.teslamotors.plugins.ble.c.b.CONNECTED && this.q.m) {
            if (this.q.h) {
                a2.a(a.C0156a.ic_notification, getString(a.b.notification_unlock_button), a(this, com.teslamotors.plugins.ble.b.e.UnlockCar));
            } else {
                a2.a(a.C0156a.ic_notification, getString(a.b.notification_lock_button), a(this, com.teslamotors.plugins.ble.b.e.LockCar));
            }
            a2.a(a.C0156a.ic_notification, getString(a.b.notification_front_trunk_button), a(this, com.teslamotors.plugins.ble.b.e.OpenFrunk));
            a2.a(a.C0156a.ic_notification, getString(a.b.notification_rear_trunk_button), a(this, com.teslamotors.plugins.ble.b.e.OpenTrunk));
        }
        a2.a((CharSequence) this.l);
        return a2;
    }

    static y.c a(Context context, String str) {
        return new y.c(context, "phone_key_service_channel").a((CharSequence) context.getString(a.b.phone_key_notification_title)).b((CharSequence) str).c(Color.parseColor("#CC0000")).a("com.teslamotors.plugins.ble.BLE_SERVICE").a(true).a(a.C0156a.ic_notification).a(PendingIntent.getActivity(context, 0, f.a(context).D(), 134217728));
    }

    private void h() {
        if (a.f5367a && b(this, this.j, this.k) && this.p) {
            ((NotificationManager) getSystemService("notification")).notify(333, f());
        }
    }

    public int onStartCommand(Intent intent, int i2, int i3) {
        String str;
        f("onStartCommand");
        if (intent != null) {
            str = intent.getAction();
            if (!"com.teslamotors.plugins.ble.action.REINITIALIZE_CONNECTION".equalsIgnoreCase(str) && !"com.teslamotors.plugins.ble.action.RESTART_BG_SCAN".equalsIgnoreCase(str)) {
                if ("com.teslamotors.ble.action.COMMAND".equalsIgnoreCase(str)) {
                    Message obtain = Message.obtain();
                    obtain.what = intent.getIntExtra("command_id", com.teslamotors.plugins.ble.b.e.GetStatus.a());
                    obtain.arg1 = -obtain.what;
                    this.i.a(obtain);
                } else {
                    this.j = intent.getStringExtra("VIN");
                    this.k = intent.getStringExtra("ACCOUNT_EMAIL");
                    this.l = intent.getStringExtra("VEHICLE_NAME");
                }
            }
        } else {
            str = null;
        }
        k();
        if (this.j != null && this.k != null && this.g != null && !this.i.a().equalsIgnoreCase(this.j)) {
            this.i = new f(this.j, this.k, getApplicationContext(), this.g, this);
        } else if (str != null) {
            if ("com.teslamotors.plugins.ble.action.REINITIALIZE_CONNECTION".equalsIgnoreCase(str)) {
                this.i.c();
                c(getApplicationContext());
            } else if ("com.teslamotors.plugins.ble.action.RESTART_BG_SCAN".equalsIgnoreCase(str)) {
                this.i.e();
                this.i.d();
            }
        }
        i();
        b(true);
        return super.onStartCommand(intent, i2, i3);
    }

    private void i() {
        this.A = (SensorManager) getSystemService("sensor");
        SensorManager sensorManager = this.A;
        if (sensorManager == null) {
            f("could not initialize sensor manager");
            return;
        }
        this.B = sensorManager.getDefaultSensor(29);
        this.C = this.A.getDefaultSensor(30);
        this.D = this.A.getDefaultSensor(17);
        if (this.B == null && this.C == null) {
            f("could not initialize motion and stationary detectors; skipping rest of registration");
            return;
        }
        Sensor sensor = this.B;
        if (sensor != null) {
            if (sensor.getReportingMode() != 2) {
                this.H = this.A.registerListener(this, this.B, 3);
                f(String.format("StationaryListener REPORTING_MODE: %d", Integer.valueOf(this.B.getReportingMode())));
            } else {
                this.H = this.A.requestTriggerSensor(this.E, this.B);
                a("StationaryListener: ", "REPORTING_MODE_ONE_SHOT");
            }
        }
        Sensor sensor2 = this.C;
        if (sensor2 != null) {
            if (sensor2.getReportingMode() != 2) {
                this.I = this.A.registerListener(this, this.C, 3);
                f(String.format("MotionListener REPORTING_MODE: %d", Integer.valueOf(this.C.getReportingMode())));
            } else {
                this.I = this.A.requestTriggerSensor(this.F, this.C);
                a("MotionListener: ", "REPORTING_MODE_ONE_SHOT");
            }
        }
        Sensor sensor3 = this.D;
        if (sensor3 == null) {
            return;
        }
        if (sensor3.getReportingMode() != 2) {
            this.J = this.A.registerListener(this, this.D, 3);
            f(String.format("SignificantMotionListener REPORTING_MODE: %d", Integer.valueOf(this.D.getReportingMode())));
            return;
        }
        this.J = this.A.requestTriggerSensor(this.G, this.D);
        a("SignificantMotionListener: ", "REPORTING_MODE_ONE_SHOT");
    }

    public void onSensorChanged(SensorEvent sensorEvent) {
        if (sensorEvent.sensor.getType() == 29) {
            a(f5345c, "onSensorChanged: stationary detected !");
        }
        if (sensorEvent.sensor.getType() == 30) {
            a(f5345c, "onSensorChanged: Motion detected !");
        }
        if (sensorEvent.sensor.getType() == 17) {
            a(f5345c, "onSensorChanged: Significant motion detected !");
        }
    }

    public void onAccuracyChanged(Sensor sensor, int i2) {
        String str = f5345c;
        Log.d(str, "Sensor Accuracy changed ! " + i2);
    }

    class a extends Handler {
        a() {
        }

        /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
        public void handleMessage(Message message) {
            boolean z;
            List<Pair<String, String>> z2;
            boolean z3 = false;
            if (message.arg1 > 0) {
                BLEService.this.f((BLEService) String.format("Handling message: %s with arg %s", com.teslamotors.plugins.ble.b.e.b(message.what), Integer.valueOf(message.arg1)));
            } else {
                Log.i(BLEService.f5345c, String.format("Handling message: %s", com.teslamotors.plugins.ble.b.e.b(message.what)));
            }
            switch (com.teslamotors.plugins.ble.b.e.a(message.what)) {
                case ScanForPeripherals:
                    if (BLEService.a(f.a(BLEService.this.getApplicationContext())) > 1) {
                        BLEService.this.z = a.k;
                    } else {
                        BLEService.this.z = a.j;
                    }
                    BLEService bLEService = BLEService.this;
                    String str = (String) message.getData().get(com.teslamotors.plugins.ble.b.e.ScanForPeripherals.b());
                    if (message.arg1 > 0) {
                        z3 = true;
                    }
                    bLEService.a((BLEService) str, (String) z3);
                    return;
                case GetStatus:
                    BLEService.this.b(true);
                    return;
                case SetVin:
                    ArrayList<String> stringArrayList = message.getData().getStringArrayList(com.teslamotors.plugins.ble.b.e.SetVin.b());
                    BLEService.this.b((BLEService) stringArrayList.get(0), stringArrayList.get(1));
                    return;
                case SetAccountEmail:
                    BLEService.this.d((BLEService) message.getData().getString(com.teslamotors.plugins.ble.b.e.SetAccountEmail.b()));
                    return;
                case PromoteServiceToForeground:
                    BLEService.this.j();
                    return;
                case Register:
                    BLEService.this.a((BLEService) message.replyTo);
                    return;
                case Unregister:
                    BLEService.this.b((BLEService) message.replyTo);
                    return;
                case SetEventSubscriptionsReady:
                    BLEService.this.a((BLEService) Boolean.valueOf(message.getData().getBoolean(com.teslamotors.plugins.ble.b.e.SetEventSubscriptionsReady.b())));
                    return;
                case NFCGetPublicKeyBytes:
                    Message obtain = Message.obtain();
                    obtain.what = com.teslamotors.plugins.ble.b.e.NFCGetPublicKeyBytesInfo.a();
                    Bundle bundle = new Bundle();
                    h g = BLEService.this.i.g();
                    if (g != null) {
                        bundle.putParcelable(com.teslamotors.plugins.ble.b.e.NFCGetPublicKeyBytesInfo.b(), g);
                    } else {
                        BLEService.this.a(BLEService.f5345c, "on receiving NFCGetPublicKeyBytes, Pubkey is not ready yet");
                    }
                    obtain.setData(bundle);
                    try {
                        message.replyTo.send(obtain);
                        return;
                    } catch (RemoteException e) {
                        BLEService.this.a(BLEService.f5345c, "Failed to send message to NFC client", e);
                        return;
                    }
                case NFCEncryptWithSharedSecret:
                    ArrayList<String> stringArrayList2 = message.getData().getStringArrayList(com.teslamotors.plugins.ble.b.e.NFCEncryptWithSharedSecret.b());
                    byte[] a2 = g.a(stringArrayList2.get(0));
                    byte[] a3 = g.a(stringArrayList2.get(1));
                    Message obtain2 = Message.obtain();
                    obtain2.what = com.teslamotors.plugins.ble.b.e.NFCEncryptWithSharedSecretResponse.a();
                    Bundle bundle2 = new Bundle();
                    com.teslamotors.plugins.ble.b.c a4 = BLEService.this.i.a(a2, a3);
                    if (a4 != null) {
                        bundle2.putParcelable(com.teslamotors.plugins.ble.b.e.NFCEncryptWithSharedSecretResponse.b(), a4);
                    } else {
                        BLEService.this.a(BLEService.f5345c, "on receiving NFCEncryptWithSharedSecret, shared secret is not ready yet");
                    }
                    obtain2.setData(bundle2);
                    try {
                        message.replyTo.send(obtain2);
                    } catch (RemoteException e2) {
                        BLEService.this.a(BLEService.f5345c, "Failed to send message to client", e2);
                    }
                    f a5 = f.a(BLEService.this.getApplicationContext());
                    String h = a5.h(BLEService.this.j);
                    if (h != null) {
                        BLEService bLEService2 = BLEService.this;
                        String str2 = BLEService.f5345c;
                        bLEService2.a(str2, "Currently Selected VIN : " + BLEService.this.j + " Public Key: " + h);
                        if (h.equalsIgnoreCase(stringArrayList2.get(1))) {
                            BLEService.this.a(BLEService.f5345c, "Not switching VIN");
                            z = false;
                        } else {
                            z = true;
                        }
                    } else {
                        z = true;
                    }
                    if (z && (z2 = a5.z()) != null) {
                        BLEService bLEService3 = BLEService.this;
                        String str3 = BLEService.f5345c;
                        bLEService3.a(str3, "Looking for the key" + stringArrayList2.get(1));
                        BLEService.this.a(BLEService.f5345c, "Vehicles Public key Found ::::");
                        for (int i = 0; i < z2.size(); i++) {
                            String str4 = TextUtils.split((String) z2.get(i).first, "VEHICLE_BLE_PUBLIC_KEY_")[1];
                            BLEService bLEService4 = BLEService.this;
                            String str5 = BLEService.f5345c;
                            bLEService4.a(str5, "VIN: " + str4 + ", Remote public key : " + g.a(g.a((String) z2.get(i).second)));
                            if (Arrays.equals(g.a((String) z2.get(i).second), a3)) {
                                BLEService bLEService5 = BLEService.this;
                                String str6 = BLEService.f5345c;
                                bLEService5.a(str6, "Matching vehicle Found" + str4);
                                BLEService.this.a((BLEService) str4);
                                return;
                            }
                        }
                        return;
                    }
                    return;
                case NFCProtoMessage:
                    a.ac acVar = null;
                    try {
                        acVar = a.ac.a(message.getData().getByteArray(com.teslamotors.plugins.ble.b.e.NFCProtoMessage.b()));
                    } catch (v e3) {
                        BLEService.this.a(BLEService.f5345c, String.format("Failed to deserialize message: %s", e3.toString()));
                    }
                    if (acVar != null) {
                        a.bo h2 = acVar.h();
                        if (!h2.c().c()) {
                            String e4 = h2.c().e();
                            BLEService bLEService6 = BLEService.this;
                            String str7 = BLEService.f5345c;
                            bLEService6.a(str7, "VIN through NFC Proto message: " + e4);
                            BLEService bLEService7 = BLEService.this;
                            String str8 = BLEService.f5345c;
                            bLEService7.a(str8, "Currently Selected VIN : " + BLEService.this.j);
                            if (BLEService.this.j.equalsIgnoreCase(e4)) {
                                BLEService.this.a(BLEService.f5345c, "Proto message ignored, Not Switching VIN");
                                return;
                            }
                            BLEService.this.a(BLEService.f5345c, "Using Proto message to switch VIN");
                            BLEService.this.a((BLEService) e4);
                            return;
                        }
                        return;
                    }
                    return;
                case ClearPeripherals:
                    BLEService.this.c((BLEService) false);
                    break;
                case AuthResponse:
                case SetConnectionBehaviorWhenUnauthorized:
                case GetPublicKeyInfo:
                case ResetCommandPeripheral:
                case StartMonitoringRSSI:
                case StopMonitoringRSSI:
                case UnlockCar:
                case LockCar:
                case OpenFrunk:
                case OpenTrunk:
                case OpenChargePort:
                case CloseChargePort:
                case AddToWhitelist:
                case GetWhiteslistUpdate:
                case RemoveFromWhitelist:
                    break;
                default:
                    super.handleMessage(message);
                    throw new RuntimeException(String.format("Unsupported message: %s", com.teslamotors.plugins.ble.b.e.a(message.what)));
            }
            BLEService.this.i.a(message);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void a(String str) {
        f a2 = f.a(getApplicationContext());
        if (a2.f(str)) {
            b(str, a2.e(str));
            a(com.teslamotors.plugins.ble.b.e.SwitchProductMessage, new j(str));
            return;
        }
        f("VIN Not found in the Product list.");
    }

    /* access modifiers changed from: protected */
    public void a(com.teslamotors.plugins.ble.b.e eVar, Parcelable parcelable) {
        for (int size = this.y.size() - 1; size >= 0; size--) {
            Message obtain = Message.obtain();
            Bundle bundle = new Bundle();
            if (parcelable != null) {
                bundle.putParcelable(eVar.b(), parcelable);
            }
            obtain.what = eVar.a();
            obtain.setData(bundle);
            try {
                this.y.get(size).send(obtain);
            } catch (RemoteException e2) {
                Log.e(f5345c, "Failed to send message to client - removing", e2);
                if (this.y.size() > size) {
                    try {
                        this.y.remove(size);
                    } catch (IndexOutOfBoundsException unused) {
                    }
                }
            }
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void a(Boolean bool) {
        boolean z2 = !this.e && bool.booleanValue();
        this.e = bool.booleanValue();
        if (z2) {
            while (this.f5347d.size() > 0) {
                a(com.teslamotors.plugins.ble.b.e.LogMessage, new com.teslamotors.plugins.ble.b.d(f5345c, this.f5347d.poll(), 4, null));
            }
            b(true);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void a(String str, boolean z2) {
        String str2;
        this.v = b(str);
        this.r.clear();
        this.s.clear();
        this.u = z2;
        this.t = new HashMap();
        k();
        if (this.f == null) {
            f("Unable to obtain a BluetoothManager.");
            str2 = "PHONE_KEY_BLUETOOTH_INACCESSIBLE";
        } else {
            str2 = null;
        }
        BluetoothAdapter adapter = this.f.getAdapter();
        if (adapter == null) {
            f("Unable to obtain a BluetoothAdapter.");
            str2 = "PHONE_KEY_BLUETOOTH_INACCESSIBLE";
        } else if (!adapter.isEnabled()) {
            str2 = "PHONE_KEY_BLUETOOTH_DISABLED";
        }
        if (adapter != null) {
            this.w = adapter.getBluetoothLeScanner();
            if (this.w == null) {
                f("Unable to obtain a Bluetooth LE Scanner.");
                str2 = "PHONE_KEY_BLUETOOTH_INACCESSIBLE";
            }
            if (this.x) {
                str2 = "PHONE_KEY_SCAN_IN_PROGRESS";
            }
            if (str2 == null) {
                f("Starting Scan");
                try {
                    this.x = true;
                    this.w.startScan((List<ScanFilter>) null, f5344a, this.S);
                    this.h.postDelayed(this.P, 20000);
                } catch (Exception e2) {
                    this.x = false;
                    a("Failed to start scan", e2);
                    str2 = "PHONE_KEY_SCAN_FAILURE";
                }
            }
        }
        if (str2 != null) {
            c(str2);
        }
    }

    private String b(String str) {
        if (str == null || str.length() == 0) {
            return null;
        }
        try {
            MessageDigest instance = MessageDigest.getInstance(McElieceCCA2KeyGenParameterSpec.SHA1);
            instance.update(str.getBytes());
            return g.a(instance.digest()).substring(0, 16);
        } catch (Exception e2) {
            a("Failed to get VIN identifier", e2);
            return null;
        }
    }

    public boolean b() {
        return this.x;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void c(boolean z2) {
        BluetoothAdapter bluetoothAdapter;
        this.h.removeCallbacks(this.P);
        if (this.x) {
            this.x = false;
            if (!(this.w == null || (bluetoothAdapter = this.g) == null || !bluetoothAdapter.isEnabled())) {
                f(String.format("Stopping pairing scan with %d peripherals", Integer.valueOf(this.r.size())));
                this.w.stopScan(this.S);
                if (z2 && this.r.size() > 0) {
                    this.i.b();
                    this.i.a(this.t);
                    this.i.f();
                }
            }
            c((String) null);
        }
    }

    private void c(String str) {
        a(com.teslamotors.plugins.ble.b.e.ScanForPeripheralsResult, new k(str == null, str, this.r.size(), a(this.t)));
    }

    public static g a(Map<String, com.teslamotors.plugins.ble.a.c> map) {
        g gVar = new g();
        for (Map.Entry<String, com.teslamotors.plugins.ble.a.c> entry : map.entrySet()) {
            com.teslamotors.plugins.ble.a.c value = entry.getValue();
            gVar.f5414a.put(value.a(), new com.teslamotors.plugins.ble.b.f(value));
        }
        return gVar;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void a(Messenger messenger) {
        this.y.add(messenger);
        Message obtain = Message.obtain();
        obtain.what = com.teslamotors.plugins.ble.b.e.RegisterComplete.a();
        try {
            messenger.send(obtain);
            f("Registering " + messenger);
        } catch (RemoteException e2) {
            Log.e(f5345c, "Failed to send message to client", e2);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void b(Messenger messenger) {
        f("Unregistering " + messenger);
        this.y.remove(messenger);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void b(String str, String str2) {
        String str3 = this.j != null ? "REDACTED" : "null";
        String str4 = str != null ? "REDACTED" : "null";
        this.l = str2;
        f(String.format("Setting VIN from %s to %s", str3, str4));
        if (str == null) {
            str = "";
        }
        k();
        if (!str.equalsIgnoreCase(this.j)) {
            this.i.b();
            this.m = 0;
            this.j = str;
            this.i = new f(this.j, this.k, getApplicationContext(), this.g, this);
        }
        if (!a((Context) this, this.j, this.k)) {
            f("No VIN, non-applicable VIN, or no user - stopping BLE Service");
            c();
            stopSelf();
        } else if (b(this, this.j, this.k)) {
            j();
        } else {
            c();
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void d(String str) {
        f(String.format("Changing email from %s to %s", this.k != null ? "REDACTED" : "null", str != null ? "REDACTED" : "null"));
        if (str == null) {
            str = "";
        }
        if (!str.equalsIgnoreCase(this.k)) {
            this.i.b();
            this.m = 0;
            this.k = str;
            if (a((Context) this, this.j, this.k)) {
                this.i = new f(this.j, this.k, getApplicationContext(), this.g, this);
            } else {
                this.i = new c();
            }
        }
        if (!a((Context) this, this.j, this.k)) {
            f("No VIN, non-applicable VIN, or no user - stopping BLE Service");
            c();
            stopSelf();
        }
    }

    /* access modifiers changed from: protected */
    public void c() {
        if (this.p) {
            f("Demoting service from foreground");
            this.p = false;
            stopForeground(true);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void j() {
        if (!a.f5367a || !b(this, this.j, this.k)) {
            f("NOT promoting service to foreground");
            return;
        }
        f("Promoting service to foreground");
        startForeground(333, f());
        this.p = true;
    }

    public IBinder onBind(Intent intent) {
        return this.f5346b.getBinder();
    }

    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(this.O);
        this.i.b();
        this.h.removeCallbacksAndMessages(null);
        b(true);
    }

    /* access modifiers changed from: private */
    public final class c extends Handler {
        public c(Looper looper) {
            super(looper);
        }

        public void handleMessage(Message message) {
            String str = BLEService.f5345c;
            Log.i(str, "Handling msg:" + message);
        }
    }

    private void k() {
        if (this.f == null) {
            this.f = (BluetoothManager) getSystemService("bluetooth");
            if (this.f == null) {
                f("Unable to initialize BluetoothManager.");
            }
        }
        this.g = this.f.getAdapter();
        BluetoothAdapter bluetoothAdapter = this.g;
        if (bluetoothAdapter == null) {
            f("Unable to obtain a BluetoothAdapter.");
        } else if (bluetoothAdapter.isEnabled()) {
            this.o = this.g.isOffloadedFilteringSupported();
            f.a(getApplicationContext()).b(4, this.o ? "filtering" : "unsupported");
        }
    }

    private void a(String str, String str2, int i2, Exception exc) {
        if (!this.e || this.y.size() == 0) {
            if (this.f5347d.size() == 100) {
                this.f5347d.poll();
            }
            this.f5347d.offer(String.format("PRE-LAUNCH [%s]: %s", a.i.format(new Date()), str2));
            return;
        }
        a(com.teslamotors.plugins.ble.b.e.LogMessage, new com.teslamotors.plugins.ble.b.d(str, str2, i2, exc));
    }

    public synchronized void b(boolean z2) {
        long currentTimeMillis = System.currentTimeMillis() - this.m;
        if (z2 || currentTimeMillis >= 500) {
            this.m = System.currentTimeMillis();
            this.q = new i();
            this.q.t = this.g != null && this.g.isEnabled();
            this.q.x = this.o;
            this.i.a(this.q);
            h();
            a(com.teslamotors.plugins.ble.b.e.StatusMessage, this.q);
            d();
            return;
        }
        this.h.removeCallbacks(this.R);
        this.h.postDelayed(this.R, 500 - currentTimeMillis);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private String e(String str) {
        if (str == null || str.length() <= 0) {
            return "X";
        }
        return str.substring(str.length() - 1);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private boolean a(ScanResult scanResult) {
        String str;
        if (scanResult.getScanRecord() == null || scanResult.getScanRecord().getDeviceName() == null) {
            return false;
        }
        String deviceName = scanResult.getScanRecord().getDeviceName();
        if (!deviceName.startsWith("S") || (str = this.v) == null || !deviceName.contains(str) || !this.z.contains(e(deviceName))) {
            return false;
        }
        return true;
    }

    public static void a(Context context) {
        if (Build.VERSION.SDK_INT >= 24) {
            ((AlarmManager) context.getSystemService("alarm")).setWindow(0, System.currentTimeMillis() + 1200000, 600000, e(context));
        }
    }

    public static void b(Context context) {
        AlarmManager alarmManager;
        if (Build.VERSION.SDK_INT >= 24 && (alarmManager = (AlarmManager) context.getSystemService("alarm")) != null) {
            alarmManager.cancel(e(context));
        }
    }

    private static PendingIntent e(Context context) {
        Intent intent = new Intent(context, BLEService.class);
        intent.setAction("com.teslamotors.plugins.ble.action.RESTART_BG_SCAN");
        return PendingIntent.getService(context, 0, intent, 0);
    }

    public static void c(Context context) {
        ((AlarmManager) context.getSystemService("alarm")).setWindow(0, System.currentTimeMillis() + 14400000, 14400000, f(context));
    }

    public static void d(Context context) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService("alarm");
        if (alarmManager != null) {
            alarmManager.cancel(f(context));
        }
    }

    private static PendingIntent f(Context context) {
        Intent intent = new Intent(context, BLEService.class);
        intent.setAction("com.teslamotors.plugins.ble.action.REINITIALIZE_CONNECTION");
        return PendingIntent.getService(context, 0, intent, 0);
    }

    private static PendingIntent a(Context context, com.teslamotors.plugins.ble.b.e eVar) {
        Intent intent = new Intent(context, BLEService.class);
        intent.setAction("com.teslamotors.ble.action.COMMAND");
        intent.putExtra("command_id", eVar.a());
        return PendingIntent.getService(context, eVar.a(), intent, 0);
    }

    public static boolean a(Context context, String str, String str2) {
        boolean z2;
        f a2 = f.a(context);
        boolean z3 = str != null && str.length() > 4;
        if (z3) {
            String substring = str.substring(3, 4);
            z2 = substring.equalsIgnoreCase("3") || substring.equalsIgnoreCase("Y");
        } else {
            z2 = false;
        }
        if (str2 == null || !z3 || (!z2 && a2.d(str, str2).size() <= 0)) {
            return false;
        }
        return true;
    }

    public static boolean b(Context context, String str, String str2) {
        return a(context, str, str2) && f.a(context).d(str, str2).size() > 0;
    }

    public static boolean a(Intent intent) {
        int intExtra = intent.getIntExtra("plugged", -1);
        return intExtra == 1 || intExtra == 2 || intExtra == 4;
    }

    public void a(String str, String str2, Exception exc) {
        a(str, String.format("[TMBLE Error] %s", str2), 6, exc);
    }

    public void a(String str, String str2) {
        a(str, String.format("[TMBLE] %s", str2), 4, null);
    }

    public static int a(f fVar) {
        com.teslamotors.plugins.client.c.a o2 = fVar.o();
        if (o2 != null) {
            return o2.d();
        }
        return 1;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void f(String str) {
        a(f5345c, str);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void a(String str, Exception exc) {
        a(f5345c, str, exc);
    }
}
