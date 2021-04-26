package com.teslamotors.plugins.ble;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanFilter;
import android.bluetooth.le.ScanResult;
import android.bluetooth.le.ScanSettings;
import android.content.ComponentName;
import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Parcelable;
import android.support.v4.util.j;
import com.teslamotors.a.a;
import com.teslamotors.plugins.ble.a.d;
import com.teslamotors.plugins.ble.b.a;
import com.teslamotors.plugins.ble.b.e;
import com.teslamotors.plugins.ble.b.g;
import com.teslamotors.plugins.ble.b.h;
import com.teslamotors.plugins.ble.b.i;
import com.teslamotors.plugins.ble.c.b;
import com.teslamotors.plugins.ble.c.c;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/* compiled from: BLEVehicleController */
public class f implements b {

    /* renamed from: a  reason: collision with root package name */
    private final BLEService f5454a;

    /* renamed from: b  reason: collision with root package name */
    private ConcurrentHashMap<String, Integer> f5455b;

    /* renamed from: c  reason: collision with root package name */
    private ConcurrentHashMap<String, Integer> f5456c;

    /* renamed from: d  reason: collision with root package name */
    private final String f5457d;
    private final c e;
    private ConcurrentHashMap<String, com.teslamotors.plugins.ble.a.c> f = new ConcurrentHashMap<>();
    private Set<com.teslamotors.plugins.ble.a.c> g = Collections.newSetFromMap(new ConcurrentHashMap());
    private com.teslamotors.plugins.ble.a.c h;
    private com.teslamotors.plugins.ble.a.c i;
    private final Context j;
    private final BluetoothAdapter k;
    private final String l;
    private d m;
    private List<String> n = new ArrayList();
    private volatile Handler o;
    private boolean p;
    private BluetoothLeScanner q;
    private boolean r;
    private byte[] s;
    private boolean t;
    private final ScanCallback u = new ScanCallback() {
        /* class com.teslamotors.plugins.ble.f.AnonymousClass2 */

        private void a(ScanResult scanResult) {
            BluetoothDevice device;
            com.teslamotors.plugins.ble.a.c cVar;
            if ((Build.VERSION.SDK_INT < 26 || scanResult.isConnectable()) && scanResult != null && (device = scanResult.getDevice()) != null && (cVar = (com.teslamotors.plugins.ble.a.c) f.this.f.get(device.getAddress())) != null && !cVar.b()) {
                if (!cVar.h() || scanResult.getRssi() <= -95) {
                    f.this.a(String.format("BG Scan; NOT re-initializing connection to %s - last connection occurred %s seconds ago / RSSI: %s", cVar, Long.valueOf(cVar.i()), Integer.valueOf(scanResult.getRssi())));
                    return;
                }
                f.this.a(String.format("BG Scan; making direct connection to %s with RSSI %s", cVar, Integer.valueOf(scanResult.getRssi())));
                f.this.g(cVar);
            }
        }

        public void onScanResult(int i, ScanResult scanResult) {
            a(scanResult);
        }

        public void onScanFailed(int i) {
            f.this.a(String.format("BG Scan failed with error %s", g.a(i)));
        }
    };

    public void o() {
    }

    public f(String str, String str2, Context context, BluetoothAdapter bluetoothAdapter, BLEService bLEService) {
        this.f5457d = str;
        this.l = str2;
        this.k = bluetoothAdapter;
        this.s = null;
        this.h = null;
        this.i = null;
        this.j = context;
        this.f5455b = new ConcurrentHashMap<>();
        this.f5456c = new ConcurrentHashMap<>();
        this.e = new c(this, this.f5457d, this.l, context);
        this.e.a();
        this.m = new d();
        this.f5454a = bLEService;
        this.o = new Handler(Looper.getMainLooper());
        f();
        d();
    }

    @Override // com.teslamotors.plugins.ble.b
    public String a() {
        return this.f5457d;
    }

    @Override // com.teslamotors.plugins.ble.b
    public void a(i iVar) {
        this.m.a(iVar);
        iVar.l = this.e.h();
        iVar.m = this.e.i();
        iVar.n = this.e.j();
        iVar.o = this.e.k();
        iVar.p = this.e.g();
        iVar.q = this.e.l();
        iVar.r = this.e.m();
        com.teslamotors.plugins.ble.a.c cVar = this.h;
        iVar.u = cVar != null && cVar.g();
        com.teslamotors.plugins.ble.a.c cVar2 = this.h;
        iVar.v = cVar2 == null ? "" : cVar2.e();
        iVar.s = this.m.a();
        iVar.k = this.f5457d;
        g a2 = BLEService.a(this.f);
        if (a2.f5414a.isEmpty()) {
            for (j<String, String> jVar : z()) {
                a2.f5414a.put(jVar.f667a, new com.teslamotors.plugins.ble.b.f("?", false, jVar.f668b, -1000));
            }
        }
        iVar.w = a2;
        iVar.y = a(this.e.n());
    }

    @Override // com.teslamotors.plugins.ble.b
    public void a(boolean z) {
        this.f5454a.b(z);
    }

    public boolean j() {
        return this.h != null;
    }

    public boolean k() {
        for (Map.Entry<String, com.teslamotors.plugins.ble.a.c> entry : this.f.entrySet()) {
            if (entry.getValue().b() && entry.getValue().g()) {
                return true;
            }
        }
        return false;
    }

    @Override // com.teslamotors.plugins.ble.b
    public b i() {
        com.teslamotors.plugins.ble.a.c cVar = this.h;
        if (cVar == null) {
            return b.DISCONNECTED;
        }
        return cVar.b() ? b.CONNECTED : b.DISCONNECTED;
    }

    public void l() {
        if (this.h != null) {
            a("Command peripheral already exists - not connecting new command peripheral");
        }
        ArrayList arrayList = new ArrayList();
        for (Map.Entry<String, com.teslamotors.plugins.ble.a.c> entry : this.f.entrySet()) {
            com.teslamotors.plugins.ble.a.c value = entry.getValue();
            if (value.b() && value.g()) {
                arrayList.add(value);
            }
        }
        int size = arrayList.size();
        if (size > 0) {
            com.teslamotors.plugins.ble.a.c cVar = (com.teslamotors.plugins.ble.a.c) (size == 1 ? arrayList.get(0) : arrayList.get(new Random().nextInt(size)));
            a(String.format("connectCommandPeripheral - chose %s from %s", cVar, arrayList));
            if (cVar.j()) {
                this.i = cVar;
            }
        }
    }

    public void m() {
        com.teslamotors.plugins.ble.a.c cVar = this.h;
        if (cVar != null) {
            f(cVar);
        }
    }

    @Override // com.teslamotors.plugins.ble.b
    public void c() {
        if (this.f.size() == 0) {
            f();
        } else {
            ArrayList<com.teslamotors.plugins.ble.a.c> arrayList = new ArrayList();
            for (com.teslamotors.plugins.ble.a.c cVar : v()) {
                if (!cVar.b()) {
                    cVar.l();
                    arrayList.add(new com.teslamotors.plugins.ble.a.c(cVar.a(), this.k.getRemoteDevice(cVar.e()), null, this, this.k, this.j));
                }
            }
            for (com.teslamotors.plugins.ble.a.c cVar2 : arrayList) {
                this.f.put(cVar2.e(), cVar2);
                h(cVar2);
            }
        }
        a(true);
    }

    public void g(com.teslamotors.plugins.ble.a.c cVar) {
        if (!cVar.b()) {
            f(cVar);
        }
    }

    @Override // com.teslamotors.plugins.ble.b
    public void f(com.teslamotors.plugins.ble.a.c cVar) {
        if (cVar.e() != null) {
            a("disconnecting/reconnecting to " + cVar);
            cVar.l();
            com.teslamotors.plugins.ble.a.c cVar2 = new com.teslamotors.plugins.ble.a.c(cVar.a(), this.k.getRemoteDevice(cVar.e()), null, this, this.k, this.j);
            this.f.put(cVar2.e(), cVar2);
            h(cVar2);
        }
    }

    private List<com.teslamotors.plugins.ble.a.c> v() {
        ArrayList arrayList = new ArrayList();
        for (Map.Entry<String, com.teslamotors.plugins.ble.a.c> entry : this.f.entrySet()) {
            if (entry.getValue() == null || !entry.getValue().d()) {
                arrayList.add(entry.getValue());
            } else {
                arrayList.add(0, entry.getValue());
            }
        }
        return arrayList;
    }

    private void w() {
        this.h = null;
        for (j<String, String> jVar : z()) {
            S s2 = jVar.f668b;
            this.f.put(s2, new com.teslamotors.plugins.ble.a.c(jVar.f667a, this.k.getRemoteDevice(s2), null, this, this.k, this.j));
        }
        this.n = a(this.f.keySet());
    }

    @Override // com.teslamotors.plugins.ble.b
    public void f() {
        w();
        for (com.teslamotors.plugins.ble.a.c cVar : v()) {
            h(cVar);
        }
    }

    public void h(com.teslamotors.plugins.ble.a.c cVar) {
        if (!this.k.isEnabled()) {
            a("Cannot create connection - adapter is not enabled");
        } else if (cVar.e() == null) {
            a("Cannot create connection - mac is null");
        } else {
            cVar.k();
        }
    }

    private void x() {
        Iterator<Map.Entry<String, com.teslamotors.plugins.ble.a.c>> it = this.f.entrySet().iterator();
        while (it.hasNext()) {
            it.remove();
            it.next().getValue().l();
        }
        this.n = Collections.EMPTY_LIST;
        a(true);
    }

    @Override // com.teslamotors.plugins.ble.b
    public void b() {
        x();
    }

    public void n() {
        this.h = null;
        a(Collections.EMPTY_MAP);
        x();
        BLEService bLEService = this.f5454a;
        BLEService.d(this.j);
        this.e.c();
        this.f5454a.a(e.ClearPeripheralsResult, (Parcelable) null);
        this.f5454a.c();
        a(true);
    }

    public void a(int i2, com.teslamotors.plugins.ble.b.b bVar, String str, com.teslamotors.plugins.ble.a.c cVar) {
        String a2 = a((long) i2);
        if (this.f5455b.containsKey(a2)) {
            Integer num = this.f5455b.get(a2);
            this.f5455b.remove(a2);
            a(num);
            this.f5454a.a(e.CommandResult, new a(bVar, str, num.intValue()));
        }
        if (bVar.equals(com.teslamotors.plugins.ble.b.b.OK)) {
            cVar.b(true);
            this.g.remove(cVar);
        }
    }

    @Override // com.teslamotors.plugins.ble.b
    public com.teslamotors.plugins.ble.b.c a(byte[] bArr, byte[] bArr2) {
        return new com.teslamotors.plugins.ble.b.c(this.e.a(bArr, bArr2));
    }

    private void a(byte[] bArr, String str) {
        a(bArr, this.h, str);
    }

    private void a(byte[] bArr, com.teslamotors.plugins.ble.a.c cVar, String str) {
        if (bArr == null) {
            a(String.format("WARNING: Null bytes to send for type %s", str));
            return;
        }
        String a2 = g.a(bArr);
        if (cVar == null || !cVar.g()) {
            a(false, "PHONE_KEY_NOT_CONNECTED", a2, cVar);
        } else {
            cVar.a(bArr, str);
        }
    }

    @Override // com.teslamotors.plugins.ble.b
    public void a(Message message) {
        boolean z = false;
        switch (e.a(message.what)) {
            case ClearPeripherals:
                n();
                return;
            case GetPublicKeyInfo:
                p();
                return;
            case ResetCommandPeripheral:
                m();
                return;
            case StartMonitoringRSSI:
                this.r = true;
                y();
                return;
            case StopMonitoringRSSI:
                this.r = false;
                s();
                return;
            case AuthResponse:
                a(message.arg1, message.arg2);
                return;
            case UnlockCar:
                a(message.arg1);
                return;
            case LockCar:
                b(message.arg1);
                return;
            case OpenFrunk:
                c(message.arg1);
                return;
            case OpenTrunk:
                d(message.arg1);
                return;
            case OpenChargePort:
                e(message.arg1);
                return;
            case CloseChargePort:
                f(message.arg1);
                return;
            case AddToWhitelist:
                h(message.arg1);
                return;
            case GetWhiteslistUpdate:
                i(message.arg1);
                return;
            case RemoveFromWhitelist:
                g(message.arg1);
                return;
            case SetConnectionBehaviorWhenUnauthorized:
                if (message.arg1 > 0) {
                    z = true;
                }
                this.t = z;
                return;
            default:
                return;
        }
    }

    public void a(a.df dfVar, String str) {
        a(a(dfVar), str);
    }

    private void g(int i2) {
        byte[] a2 = a(this.e.f());
        if (a2 == null) {
            this.f5454a.a(e.CommandResult, new com.teslamotors.plugins.ble.b.a(com.teslamotors.plugins.ble.b.b.ERROR, "PHONE_KEY_COULD_NOT_CREATE_MESSAGE", i2));
            return;
        }
        this.f5456c.put(g.a(a2), Integer.valueOf(i2));
        a(a2, com.teslamotors.plugins.ble.a.b.REMOVE_FROM_WHITELIST.name());
    }

    private void h(int i2) {
        byte[] a2 = a(this.e.e());
        if (a2 == null) {
            this.f5454a.a(e.CommandResult, new com.teslamotors.plugins.ble.b.a(com.teslamotors.plugins.ble.b.b.ERROR, "PHONE_KEY_COULD_NOT_CREATE_MESSAGE", i2));
            return;
        }
        this.f5456c.put(g.a(a2), Integer.valueOf(i2));
        a(a2, com.teslamotors.plugins.ble.a.b.ADD_TO_WHITELIST.name());
    }

    private void i(int i2) {
        byte[] a2 = a(this.e.a(a.aq.INFORMATION_REQUEST_TYPE_GET_WHITELIST_INFO));
        this.f5456c.put(g.a(a2), Integer.valueOf(i2));
        a(a2, com.teslamotors.plugins.ble.a.b.GET_WHITELIST_INFO.name());
    }

    public void p() {
        this.f5454a.a(e.PublicKeyInfoResult, new h(this.e.o(), this.e.p()));
    }

    @Override // com.teslamotors.plugins.ble.b
    public h g() {
        return new h(this.e.o(), this.e.p());
    }

    @Override // com.teslamotors.plugins.ble.b
    public void a(a.c cVar) {
        a(a.df.g().a(a.dl.z().a(cVar).u()).u(), cVar.name());
    }

    @Override // com.teslamotors.plugins.ble.b
    public void a(a.an anVar) {
        a(a.df.g().a(a.dl.z().a(anVar).u()).u(), anVar.name());
    }

    private void a(int i2, int i3) {
        a(a.d.b(i3), i2, com.teslamotors.plugins.ble.a.b.AUTH_RESPONSE_NULL.name());
    }

    public void a(int i2) {
        a(a.bs.RKE_ACTION_UNLOCK, i2, com.teslamotors.plugins.ble.a.b.RKE_ACTION_UNLOCK.name());
    }

    public void b(int i2) {
        a(a.bs.RKE_ACTION_LOCK, i2, com.teslamotors.plugins.ble.a.b.RKE_ACTION_LOCK.name());
    }

    public void c(int i2) {
        a(a.bs.RKE_ACTION_OPEN_FRUNK, i2, com.teslamotors.plugins.ble.a.b.RKE_ACTION_OPEN_FRUNK.name());
    }

    public void d(int i2) {
        a(a.bs.RKE_ACTION_OPEN_TRUNK, i2, com.teslamotors.plugins.ble.a.b.RKE_ACTION_OPEN_TRUNK.name());
    }

    public void e(int i2) {
        a(a.bs.RKE_ACTION_OPEN_CHARGE_PORT, i2, com.teslamotors.plugins.ble.a.b.RKE_ACTION_OPEN_CHARGE_PORT.name());
    }

    public void f(int i2) {
        a(a.bs.RKE_ACTION_CLOSE_CHARGE_PORT, i2, com.teslamotors.plugins.ble.a.b.RKE_ACTION_CLOSE_CHARGE_PORT.name());
    }

    private void a(a.d dVar, int i2, String str) {
        a(this.e.a(dVar), i2, str);
    }

    private void a(a.bs bsVar, int i2, String str) {
        a(this.e.a(bsVar), i2, str);
    }

    private void a(com.teslamotors.plugins.ble.a.a aVar, int i2, String str) {
        byte[] a2 = aVar != null ? a(aVar.b()) : null;
        if (a2 == null) {
            this.f5454a.a(e.CommandResult, new com.teslamotors.plugins.ble.b.a(com.teslamotors.plugins.ble.b.b.ERROR, "PHONE_KEY_COULD_NOT_CREATE_MESSAGE", i2));
            return;
        }
        this.f5455b.put(a(aVar.a()), Integer.valueOf(i2));
        this.f5455b.put(g.a(a2), Integer.valueOf(i2));
        a(a2, str);
    }

    public static byte[] a(a.df dfVar) {
        if (dfVar == null) {
            return null;
        }
        byte[] byteArray = dfVar.toByteArray();
        byte[] bArr = new byte[(byteArray.length + 2)];
        for (int i2 = 2; i2 < bArr.length; i2++) {
            bArr[i2] = byteArray[i2 - 2];
        }
        bArr[0] = (byte) ((byteArray.length >> 8) & 255);
        bArr[1] = (byte) (byteArray.length & 255);
        return bArr;
    }

    @Override // com.teslamotors.plugins.ble.b
    public void a(boolean z, String str, String str2, com.teslamotors.plugins.ble.a.c cVar) {
        if (z || str != "PHONE_KEY_TRANSMISSION_FAILURE") {
            o();
            if (!z && this.f5455b.containsKey(str2)) {
                Integer remove = this.f5455b.remove(str2);
                a(remove);
                this.f5454a.a(e.CommandResult, new com.teslamotors.plugins.ble.b.a(com.teslamotors.plugins.ble.b.b.ERROR, str, remove.intValue()));
            } else if (this.f5456c.containsKey(str2)) {
                this.f5454a.a(e.CommandResult, new com.teslamotors.plugins.ble.b.a(z ? com.teslamotors.plugins.ble.b.b.OK : com.teslamotors.plugins.ble.b.b.ERROR, str, this.f5456c.remove(str2).intValue()));
            }
        } else {
            f(cVar);
        }
    }

    public boolean q() {
        for (Map.Entry<String, com.teslamotors.plugins.ble.a.c> entry : this.f.entrySet()) {
            if (entry.getValue().b()) {
                return true;
            }
        }
        return false;
    }

    public boolean r() {
        for (Map.Entry<String, com.teslamotors.plugins.ble.a.c> entry : this.f.entrySet()) {
            if (!entry.getValue().b()) {
                return false;
            }
        }
        if (this.f.size() > 0) {
            return true;
        }
        return false;
    }

    @Override // com.teslamotors.plugins.ble.b
    public void d() {
        if (!this.n.isEmpty()) {
            this.o.postDelayed(new Runnable() {
                /* class com.teslamotors.plugins.ble.f.AnonymousClass1 */

                public void run() {
                    boolean z;
                    if (!f.this.f5454a.b() && !f.this.p) {
                        BLEService unused = f.this.f5454a;
                        if (BLEService.a(f.this.j, f.this.f5457d, f.this.l)) {
                            f fVar = f.this;
                            fVar.q = fVar.k == null ? null : f.this.k.getBluetoothLeScanner();
                            if (f.this.q == null || f.this.k == null) {
                                f.this.a("Failed to BG Scan - Unable to obtain a Bluetooth LE Scanner/Adapter/Manager");
                            } else if (f.this.q()) {
                                f.this.a("Skipping BG Scan - already has connected peripherals");
                            } else if (f.this.j() && f.this.i() == b.CONNECTED) {
                                f.this.a("Skipping BG Scan - already has command peripheral");
                            } else if (!f.this.k.isEnabled()) {
                                f.this.a("Skipping BG Scan - adapter not enabled");
                            } else if (!f.this.n.isEmpty()) {
                                try {
                                    z = f.this.k.isOffloadedFilteringSupported();
                                } catch (Exception unused2) {
                                    z = false;
                                }
                                if (!z) {
                                    f.this.a("Skipping BG Scan - offloaded filtering NOT supported");
                                    return;
                                }
                                ScanSettings build = new ScanSettings.Builder().setScanMode(0).build();
                                ArrayList arrayList = new ArrayList();
                                for (String str : f.this.n) {
                                    arrayList.add(new ScanFilter.Builder().setDeviceAddress(str).build());
                                }
                                f.this.a(String.format("Starting BG Scan for %s address(es)", Integer.valueOf(arrayList.size())));
                                try {
                                    f.this.q.startScan(arrayList, build, f.this.u);
                                    f.this.p = true;
                                } catch (Exception e) {
                                    f.this.a("BG Scan failed", e);
                                }
                                BLEService unused3 = f.this.f5454a;
                                BLEService.a(f.this.j);
                            }
                        }
                    }
                }
            }, 2000);
        }
    }

    private List<String> a(Collection<String> collection) {
        ArrayList arrayList = new ArrayList();
        if (Build.VERSION.SDK_INT > 26 || collection == null || collection.isEmpty()) {
            return arrayList;
        }
        for (String str : collection) {
            if ((g.a(str.split(":")[0])[0] & 192) != 128) {
                arrayList.add(str);
            }
        }
        return arrayList;
    }

    @Override // com.teslamotors.plugins.ble.b
    public void e() {
        if (this.p) {
            BLEService bLEService = this.f5454a;
            BLEService.b(this.j);
            this.p = false;
            if (this.q != null && this.k.isEnabled()) {
                a("Stopping BG Scan");
                this.q.stopScan(this.u);
            }
        }
    }

    @Override // com.teslamotors.plugins.ble.b
    public void a(Map<String, com.teslamotors.plugins.ble.a.c> map) {
        int i2;
        HashSet hashSet = new HashSet();
        this.n = a(this.f.keySet());
        Iterator<Map.Entry<String, com.teslamotors.plugins.ble.a.c>> it = map.entrySet().iterator();
        while (true) {
            i2 = 2;
            if (!it.hasNext()) {
                break;
            }
            Map.Entry<String, com.teslamotors.plugins.ble.a.c> next = it.next();
            hashSet.add(String.format("%s%s%s", next.getValue().a(), "-", next.getValue().e()));
        }
        a(String.format("Setting stored device addresses to %s", hashSet));
        com.teslamotors.plugins.client.f.a(this.j).a(this.f5457d, this.l, hashSet);
        if (hashSet.isEmpty()) {
            BLEService bLEService = this.f5454a;
            BLEService.d(this.j);
        } else {
            BLEService bLEService2 = this.f5454a;
            BLEService.c(this.j);
        }
        if (!hashSet.isEmpty()) {
            i2 = 1;
        }
        this.j.getPackageManager().setComponentEnabledSetting(new ComponentName(this.j.getApplicationContext(), BLEBootReceiver.class), i2, 1);
    }

    private static String a(long j2) {
        return String.format("IV-%s", Long.valueOf(j2));
    }

    private void a(Integer num) {
        Iterator<Map.Entry<String, Integer>> it = this.f5455b.entrySet().iterator();
        while (it.hasNext()) {
            if (it.next().getValue().equals(num)) {
                it.remove();
                return;
            }
        }
    }

    private void y() {
        for (Map.Entry<String, com.teslamotors.plugins.ble.a.c> entry : this.f.entrySet()) {
            entry.getValue().c(true);
        }
    }

    public void s() {
        for (Map.Entry<String, com.teslamotors.plugins.ble.a.c> entry : this.f.entrySet()) {
            entry.getValue().c(false);
        }
    }

    public void t() {
        if (this.r) {
            y();
        } else {
            s();
        }
    }

    private Set<j<String, String>> z() {
        HashSet hashSet = new HashSet();
        com.teslamotors.plugins.client.f a2 = com.teslamotors.plugins.client.f.a(this.j);
        for (String str : a2.d(this.f5457d, this.l)) {
            String[] split = str.split("-");
            if (split.length == 2) {
                if (BLEService.a(a2) <= 1 || "C".equalsIgnoreCase(split[0])) {
                    hashSet.add(new j(split[0], split[1]));
                } else {
                    a(String.format("Key version greater than 1; - skipping %s", split[0]));
                }
            }
        }
        return hashSet;
    }

    private HashMap<String, Boolean> a(a.j jVar) {
        HashMap<String, Boolean> hashMap = new HashMap<>();
        if (jVar != null) {
            hashMap.put("open_unlock_charge_port", Boolean.valueOf(jVar.c()));
            hashMap.put("close_charge_port", Boolean.valueOf(jVar.d()));
        }
        return hashMap;
    }

    @Override // com.teslamotors.plugins.ble.b
    public void a(String str) {
        this.f5454a.a("BLEVehicleController", str);
    }

    @Override // com.teslamotors.plugins.ble.b
    public void a(String str, Exception exc) {
        this.f5454a.a("BLEVehicleController", str, exc);
    }

    @Override // com.teslamotors.plugins.ble.b
    public void a(com.teslamotors.plugins.ble.a.c cVar) {
        this.e.a(cVar);
    }

    @Override // com.teslamotors.plugins.ble.b
    public void a(byte[] bArr) {
        this.s = bArr;
    }

    @Override // com.teslamotors.plugins.ble.b
    public boolean h() {
        return this.s != null;
    }

    @Override // com.teslamotors.plugins.ble.b
    public void a(a.ac acVar, com.teslamotors.plugins.ble.a.c cVar) {
        boolean z;
        this.m.a(((double) System.currentTimeMillis()) / 1000.0d);
        this.e.a(acVar, cVar);
        a.ec d2 = acVar.d();
        if (d2 != d2.F()) {
            this.m.a(d2);
            d dVar = this.m;
            a(String.format("Got vehicle status: %s - %s", dVar, dVar.b()));
            z = true;
        } else {
            z = false;
        }
        a.e f2 = acVar.f();
        if (f2 != f2.F() && this.e.i()) {
            com.teslamotors.plugins.ble.a.a a2 = this.e.a(f2.h());
            if (a2 == null) {
                a(String.format("Cannot reply to auth request with level %s", f2.h().name()));
            } else {
                byte[] a3 = a(a2.b());
                a(String.format("Received auth request with level %s", f2.h().name()));
                a(a3, f2.h().name());
                if (f2.h() == a.d.AUTHENTICATION_LEVEL_NONE) {
                    a(this.f5454a.a());
                }
                z = true;
            }
        }
        a.ag s2 = acVar.s();
        if (s2 == a.ag.GENEALOGYREQUEST_READ && this.e.i()) {
            com.teslamotors.plugins.ble.a.a d3 = this.e.d();
            if (d3 == null) {
                a(String.format("Cannot reply to genealogy request", new Object[0]));
            } else {
                byte[] a4 = a(d3.b());
                a(String.format("Received genealogy request", new Object[0]));
                a(a4, s2.name());
            }
        }
        a.am v = acVar.v();
        if (this.e.i()) {
            switch (v) {
                case IMU_REQUEST_ENABLE_CONTINUOUS_ACTIVITY_UPDATE:
                    this.f5454a.a(true);
                    a(String.format("Enabling continuous activity updates", new Object[0]));
                    break;
                case IMU_REQUEST_DISABLE_CONTINUOUS_ACTIVITY_UPDATE:
                    this.f5454a.a(false);
                    a(String.format("Disabling continuous activity updates", new Object[0]));
                    break;
                case IMU_REQUEST_GET_SLEEP_STATE:
                    a(String.format("Sending IMU activity state", new Object[0]));
                    a(this.f5454a.a());
                    break;
            }
        }
        a(z);
    }

    @Override // com.teslamotors.plugins.ble.b
    public synchronized boolean e(com.teslamotors.plugins.ble.a.c cVar) {
        boolean z;
        z = this.h != null && this.h.e().equals(cVar.e());
        if (z && !this.h.equals(cVar)) {
            a(String.format("Duplicate command peripheral references! %s", this), new Exception("Duplicate command peripheral references!"));
        }
        return z;
    }

    @Override // com.teslamotors.plugins.ble.b
    public synchronized boolean b(com.teslamotors.plugins.ble.a.c cVar) {
        return this.i != null && this.i.e().equalsIgnoreCase(cVar.e());
    }

    @Override // com.teslamotors.plugins.ble.b
    public synchronized void d(com.teslamotors.plugins.ble.a.c cVar) {
        if (this.i != null && this.i.e().equalsIgnoreCase(cVar.e())) {
            this.h = cVar;
            this.i = null;
            this.e.b();
        }
    }

    @Override // com.teslamotors.plugins.ble.b
    public void c(com.teslamotors.plugins.ble.a.c cVar) {
        boolean z;
        if (e(cVar)) {
            z = true;
            this.h = null;
        } else {
            z = false;
        }
        this.g.remove(cVar);
        this.e.a(cVar, z);
        this.f5454a.a(false);
    }

    @Override // com.teslamotors.plugins.ble.b
    public void a(com.teslamotors.plugins.ble.a.c cVar, int i2) {
        t();
        a(true);
        if (!q()) {
            d();
        } else if (r()) {
            e();
        }
    }

    public boolean u() {
        return this.t;
    }
}
