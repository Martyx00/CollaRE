package com.teslamotors.plugins.ble.a;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.BluetoothGattService;
import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.SystemClock;
import android.support.v4.util.j;
import com.google.protobuf.v;
import com.teslamotors.a.a;
import com.teslamotors.plugins.ble.b;
import com.teslamotors.plugins.ble.g;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.UUID;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;
import org.spongycastle.asn1.x509.DisplayText;

/* compiled from: Peripheral */
public class c {
    private final BluetoothGattCallback A = new BluetoothGattCallback() {
        /* class com.teslamotors.plugins.ble.a.c.AnonymousClass5 */

        public void onDescriptorWrite(BluetoothGatt bluetoothGatt, BluetoothGattDescriptor bluetoothGattDescriptor, int i) {
            byte[] value = bluetoothGattDescriptor.getValue();
            boolean equals = Arrays.equals(value, BluetoothGattDescriptor.ENABLE_INDICATION_VALUE);
            c cVar = c.this;
            cVar.a((c) String.format("%s onDescriptorWrite - status: 0x%02X, value: %s attempts: %d", cVar, Integer.valueOf(i), g.a(value), Integer.valueOf(c.this.o)));
            if (!equals || i == 0) {
                c.this.u.set(false);
                if (c.this.f5380d.b(c.this) && Arrays.equals(value, BluetoothGattDescriptor.ENABLE_INDICATION_VALUE)) {
                    c.this.q = 0;
                    c.this.r = 0;
                    c.this.f5380d.d(c.this);
                }
            } else if (c.this.o <= 5) {
                c.k(c.this);
                c.this.s.post(new Runnable() {
                    /* class com.teslamotors.plugins.ble.a.c.AnonymousClass5.AnonymousClass1 */

                    public void run() {
                        c.this.j();
                    }
                });
            }
        }

        public void onDescriptorRead(BluetoothGatt bluetoothGatt, BluetoothGattDescriptor bluetoothGattDescriptor, int i) {
            c cVar = c.this;
            cVar.a((c) String.format("%s onDescriptorRead - status: 0x%02X, value: %s", cVar, Integer.valueOf(i), g.a(bluetoothGattDescriptor.getValue())));
        }

        public void onReliableWriteCompleted(BluetoothGatt bluetoothGatt, int i) {
            c cVar = c.this;
            cVar.a((c) String.format("%s onReliableWriteCompleted - status: 0x%02Xs", cVar, Integer.valueOf(i)));
        }

        public void onPhyUpdate(BluetoothGatt bluetoothGatt, int i, int i2, int i3) {
            c cVar = c.this;
            cVar.a((c) String.format("%s onPhyUpdate: txPhy %d rxPhy %d", cVar, Integer.valueOf(i), Integer.valueOf(i2)));
        }

        public void onPhyRead(BluetoothGatt bluetoothGatt, int i, int i2, int i3) {
            c cVar = c.this;
            cVar.a((c) String.format("%s onPhyRead: txPhy %d rxPhy %d status: 0x%02s", cVar, Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3)));
        }

        public void onConnectionStateChange(BluetoothGatt bluetoothGatt, int i, int i2) {
            String str = "UNKNOWN";
            switch (i2) {
                case 0:
                    str = "DISCONNECTED";
                    break;
                case 1:
                    str = "CONNECTING";
                    break;
                case 2:
                    str = "CONNECTED";
                    break;
                case 3:
                    str = "DISCONNECTING";
                    break;
            }
            c cVar = c.this;
            cVar.a((c) String.format("%s onConnectionStateChange: %s status: 0x%02X", cVar, str, Integer.valueOf(i)));
            if (i2 == 0) {
                c.this.t();
                c.this.p();
                c.this.m().removeCallbacksAndMessages(c.this.e());
                if (c.this.v) {
                    c cVar2 = c.this;
                    cVar2.a((c) String.format("Disconnected from finished peripheral %s", cVar2));
                    try {
                        bluetoothGatt.close();
                    } catch (Exception e) {
                        c cVar3 = c.this;
                        cVar3.a((c) String.format("Failed to close gatt after finishing use of %s", cVar3), (String) e);
                    }
                    c.this.u();
                    return;
                }
                c.this.f5380d.c(c.this);
                if (c.this.e.isEnabled()) {
                    c.this.c((c) i);
                } else {
                    c cVar4 = c.this;
                    cVar4.a((c) String.format("Disconnected from %s but adapter is not on; doing nothing", cVar4));
                }
            } else if (i2 == 2) {
                if (c.this.v) {
                    c cVar5 = c.this;
                    cVar5.a((c) String.format("Got CONNECTED callback for peripheral when 'finished' - %s", cVar5), (String) null);
                    return;
                } else if (i == 0 && !c.this.b()) {
                    c.this.d((c) true);
                    c.this.a((c) "Requesting MTU of size 115");
                    bluetoothGatt.requestMtu(115);
                    c.this.u.set(true);
                }
            }
            c.this.f5380d.a(c.this, i2);
        }

        public void onServicesDiscovered(BluetoothGatt bluetoothGatt, int i) {
            c.this.u.set(false);
            if (i == 0) {
                c cVar = c.this;
                cVar.a((c) String.format("onServicesDiscovered Success - %s - status: 0x%02X", cVar, Integer.valueOf(i)));
                c.this.b((c) bluetoothGatt.getService(com.teslamotors.plugins.ble.a.f5368b));
                return;
            }
            c cVar2 = c.this;
            cVar2.a((c) "onServicesDiscovered Failure", (String) new Exception(String.format("onServicesDiscovered Failure - %s - status: 0x%02X", cVar2, Integer.valueOf(i))));
        }

        public void onCharacteristicRead(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, int i) {
            byte[] value = bluetoothGattCharacteristic.getValue();
            Object[] objArr = new Object[1];
            objArr[0] = Integer.valueOf(value == null ? 0 : value.length);
            c.this.a((c) String.format("onCharacteristicRead: %s - status 0x%02X", String.format("(%s)", objArr), Integer.valueOf(i)));
            if (com.teslamotors.plugins.ble.a.f.equals(bluetoothGattCharacteristic.getUuid())) {
                byte[] value2 = bluetoothGattCharacteristic.getValue();
                c.this.a((c) String.format("Got serial port version: %s", g.a(value2)));
                c.this.f5380d.a(value2);
                c.this.u.set(false);
                c.this.a(true);
                c.this.f5380d.a(c.this);
            }
        }

        public void onCharacteristicWrite(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, int i) {
            String a2 = g.a(bluetoothGattCharacteristic.getValue());
            boolean z = false;
            c.this.u.set(false);
            c.this.s();
            String format = String.format("(%s)", Integer.valueOf(a2.length() / 2));
            c cVar = c.this;
            cVar.a((c) String.format("%s - onCharacteristicWrite: %s - status 0x%02X, %s", cVar, bluetoothGattCharacteristic.getUuid(), Integer.valueOf(i), format));
            b bVar = c.this.f5380d;
            if (i == 0) {
                z = true;
            }
            bVar.a(z, i == 0 ? null : "PHONE_KEY_TRANSMISSION_FAILURE", a2, c.this);
        }

        public void onCharacteristicChanged(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
            String str;
            byte[] value = bluetoothGattCharacteristic.getValue();
            String format = String.format("(%s)", Integer.valueOf(value.length));
            if (c.this.q <= 0 || c.this.r <= 0) {
                str = "[new buffer]";
            } else {
                str = String.format("[%d bytes remaining]", Integer.valueOf(c.this.r - c.this.q));
            }
            if (!c.this.f5380d.e(c.this)) {
                c cVar = c.this;
                cVar.a((c) String.format("%s - onCharacteristicChanged - Wrong peripheral! ignoring %s", cVar, format));
            } else if (value == null || value.length == 0) {
                c cVar2 = c.this;
                cVar2.a((c) String.format("%s - onCharacteristicChanged - got empty data from VCSEC! ignoring %s", cVar2, format));
            } else {
                c cVar3 = c.this;
                cVar3.a((c) String.format("%s - onCharacteristicChanged - %s - %s", cVar3, format, str));
                c.this.a((c) value);
            }
        }

        public void onReadRemoteRssi(BluetoothGatt bluetoothGatt, int i, int i2) {
            if (i2 == 0) {
                c.this.a((c) i);
            } else {
                c cVar = c.this;
                cVar.a((c) String.format("Failed to get RSSI for %s", cVar));
                c.this.a((c) -1000);
            }
            c.this.f5380d.a(false);
        }

        public void onMtuChanged(BluetoothGatt bluetoothGatt, int i, int i2) {
            g.b(bluetoothGatt.getDevice().getAddress());
            c.this.a((c) String.format("MTU Size changed to %s with status 0x%02X on %s", Integer.valueOf(i), Integer.valueOf(i2), c.this));
            c.this.u.set(false);
            if (bluetoothGatt.getService(com.teslamotors.plugins.ble.a.f5368b) != null) {
                c cVar = c.this;
                cVar.a((c) String.format("Already know about services on %s, discovering anyway", cVar));
            }
            c.this.m().postAtTime(new a(), c.this.e(), SystemClock.uptimeMillis());
        }
    };

    /* renamed from: a  reason: collision with root package name */
    private final String f5377a;

    /* renamed from: b  reason: collision with root package name */
    private final BluetoothDevice f5378b;

    /* renamed from: c  reason: collision with root package name */
    private BluetoothGatt f5379c;

    /* renamed from: d  reason: collision with root package name */
    private final b f5380d;
    private final BluetoothAdapter e;
    private final Context f;
    private volatile int g;
    private AtomicBoolean h;
    private AtomicBoolean i;
    private BluetoothGattService j;
    private AtomicLong k;
    private volatile long l = 0;
    private String m;
    private String n;
    private int o = 0;
    private byte[] p = new byte[DisplayText.DISPLAY_TEXT_MAXIMUM_SIZE];
    private int q = 0;
    private int r = 0;
    private Handler s;
    private HandlerThread t;
    private AtomicBoolean u;
    private boolean v;
    private boolean w;
    private Queue<j<byte[], String>> x = new ArrayBlockingQueue(10);
    private boolean y = false;
    private Runnable z = new Runnable() {
        /* class com.teslamotors.plugins.ble.a.c.AnonymousClass4 */

        public void run() {
            if (c.this.n() != null && c.this.b() && c.this.y) {
                if (c.this.n().readRemoteRssi()) {
                    c.this.m().postDelayed(c.this.z, 1000);
                } else {
                    c.this.f5380d.f(c.this);
                }
            }
        }
    };

    static /* synthetic */ int k(c cVar) {
        int i2 = cVar.o;
        cVar.o = i2 + 1;
        return i2;
    }

    public c(String str, BluetoothDevice bluetoothDevice, BluetoothGatt bluetoothGatt, b bVar, BluetoothAdapter bluetoothAdapter, Context context) {
        this.f5377a = str;
        this.f5378b = bluetoothDevice;
        this.f5379c = bluetoothGatt;
        this.h = new AtomicBoolean(false);
        this.k = new AtomicLong(0);
        this.f5380d = bVar;
        this.e = bluetoothAdapter;
        this.f = context;
        this.u = new AtomicBoolean(false);
        this.i = new AtomicBoolean(false);
        this.v = false;
        this.g = -1000;
        this.n = bluetoothDevice.getAddress();
        this.w = false;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private Handler m() {
        if (this.t == null) {
            this.t = new HandlerThread(this.f5377a + "-" + this.f5378b.getAddress());
            this.t.start();
            this.s = new Handler(this.t.getLooper());
        }
        return this.s;
    }

    public String a() {
        return this.f5377a;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private BluetoothGatt n() {
        return this.f5379c;
    }

    private void a(BluetoothGatt bluetoothGatt) {
        this.f5379c = bluetoothGatt;
    }

    public boolean b() {
        return this.h.get();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void d(boolean z2) {
        this.h.set(z2);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void a(int i2) {
        this.g = i2;
    }

    public int c() {
        if (this.h.get()) {
            return this.g;
        }
        return -1000;
    }

    public boolean d() {
        return this.f5377a.equals("C");
    }

    public String e() {
        String address = this.f5378b.getAddress();
        if (!this.n.equalsIgnoreCase(address)) {
            a(String.format("Unexpected MAC address change from %s to %s", g.b(this.n), g.b(address)), (Exception) null);
        }
        return this.n;
    }

    public String f() {
        if (this.m == null) {
            this.m = g.b(this.f5378b.getAddress());
        }
        return this.m;
    }

    public String toString() {
        String f2 = f();
        Object[] objArr = new Object[4];
        objArr[0] = this.f5377a;
        objArr[1] = f2;
        objArr[2] = Integer.valueOf(hashCode());
        objArr[3] = this.h.get() ? "connected" : "disconnected";
        return String.format("%s - %s (%s) (%s)", objArr);
    }

    public void a(boolean z2) {
        this.i.set(z2);
    }

    public boolean g() {
        return this.i.get();
    }

    private void a(BluetoothGattService bluetoothGattService) {
        this.j = bluetoothGattService;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private BluetoothGattService o() {
        return this.j;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void p() {
        this.k.set(0);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void e(boolean z2) {
        this.k.set(SystemClock.elapsedRealtime());
        if (z2) {
            q();
        }
    }

    private void q() {
        this.l = SystemClock.elapsedRealtime();
    }

    public boolean h() {
        return this.l + 10000 < SystemClock.elapsedRealtime();
    }

    public long i() {
        return (SystemClock.elapsedRealtime() - this.l) / 1000;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private boolean r() {
        return this.k.get() + 5000 > SystemClock.elapsedRealtime();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void b(BluetoothGattService bluetoothGattService) {
        if (bluetoothGattService != null && com.teslamotors.plugins.ble.a.f5368b.equals(bluetoothGattService.getUuid())) {
            a(bluetoothGattService);
            List<BluetoothGattCharacteristic> characteristics = bluetoothGattService.getCharacteristics();
            a(String.format("tesla service found - %s - %s characteristics", this, Integer.valueOf(characteristics.size())));
            boolean z2 = false;
            boolean z3 = false;
            boolean z4 = false;
            for (BluetoothGattCharacteristic bluetoothGattCharacteristic : characteristics) {
                UUID uuid = bluetoothGattCharacteristic.getUuid();
                a(String.format("characteristic found - %s - %s", this, uuid));
                if (com.teslamotors.plugins.ble.a.e.equals(uuid)) {
                    z2 = true;
                } else if (com.teslamotors.plugins.ble.a.f5370d.equals(uuid)) {
                    z3 = true;
                } else if (com.teslamotors.plugins.ble.a.f.equals(uuid) && !this.f5380d.h()) {
                    z4 = n().readCharacteristic(bluetoothGattCharacteristic);
                }
                if (z2 && z3) {
                    Object[] objArr = new Object[2];
                    objArr[0] = this;
                    objArr[1] = z4 ? "including" : "NOT including";
                    a(String.format("%s - Found RX and TX characteristics, %s version", objArr));
                    if (!z4) {
                        this.u.set(false);
                        a(true);
                        this.f5380d.a(this);
                        return;
                    }
                    return;
                }
            }
        }
        a(String.format("%s - DID NOT find RX and TX characteristics!", this));
    }

    public boolean j() {
        boolean z2;
        Exception e2;
        BluetoothGatt n2;
        BluetoothGattDescriptor descriptor;
        a(String.format("requestIndications: %s", this));
        try {
            BluetoothGattService o2 = o();
            if (o2 == null) {
                a(String.format("Null mGatt service on %s - cannot register for notifications", this));
                return false;
            }
            BluetoothGattCharacteristic characteristic = o2.getCharacteristic(com.teslamotors.plugins.ble.a.e);
            if (characteristic == null) {
                a(String.format("Null FROM_VEHICLE characteristic on %s - cannot register for notifications", this));
                return false;
            }
            boolean z3 = (!com.teslamotors.plugins.ble.a.e.equals(characteristic.getUuid()) || (n2 = n()) == null || (descriptor = characteristic.getDescriptor(com.teslamotors.plugins.ble.a.g)) == null || !descriptor.setValue(BluetoothGattDescriptor.ENABLE_INDICATION_VALUE)) ? false : n2.writeDescriptor(descriptor) && n2.setCharacteristicNotification(characteristic, true);
            if (z3) {
                try {
                    this.u.set(true);
                    a(String.format("%s - Registered for indications on %s, waiting for callback", this, com.teslamotors.plugins.ble.a.e));
                    return z3;
                } catch (Exception e3) {
                    z2 = z3;
                    e2 = e3;
                    a(String.format("%s - Failed to register for indications on %s", this, com.teslamotors.plugins.ble.a.e), e2);
                    return z2;
                }
            } else {
                a(String.format("%s - Failed to register for indications on %s", this, com.teslamotors.plugins.ble.a.e));
                return z3;
            }
        } catch (Exception e4) {
            e2 = e4;
            z2 = false;
            a(String.format("%s - Failed to register for indications on %s", this, com.teslamotors.plugins.ble.a.e), e2);
            return z2;
        }
    }

    private int b(int i2) {
        if (!(i2 == 0 || i2 == 8)) {
            if (i2 == 22) {
                return 2000;
            }
            if (i2 != 34) {
                if (i2 == 133) {
                    return 2000;
                }
                a(String.format("Got unknown status code: 0x%02X", Integer.valueOf(i2)));
                return 500;
            }
        }
        return 500;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void a(byte[] bArr) {
        a.ac b2 = b(bArr);
        if (b2 != null) {
            this.f5380d.a(b2, this);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void s() {
        m().post(new Runnable() {
            /* class com.teslamotors.plugins.ble.a.c.AnonymousClass1 */

            public void run() {
                if (!c.this.u.get()) {
                    c.this.u.set(true);
                    c cVar = c.this;
                    cVar.a((c) ((j) cVar.x.poll()));
                }
            }
        });
    }

    public void a(byte[] bArr, String str) {
        if (this.x.offer(new j<>(bArr, str))) {
            a(String.format("Added message %s - %s to queue of size %d", g.a(bArr), str, Integer.valueOf(this.x.size() - 1)));
        } else {
            a(String.format("Failed to add message %s - %s to queue of size %s", g.a(bArr), str, Integer.valueOf(this.x.size())));
        }
        s();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void a(final j<byte[], String> jVar) {
        if (jVar == null) {
            this.u.set(false);
        } else {
            m().post(new Runnable() {
                /* class com.teslamotors.plugins.ble.a.c.AnonymousClass2 */

                public void run() {
                    BluetoothGatt n = c.this.n();
                    BluetoothGattService o = c.this.o();
                    String a2 = g.a((byte[]) jVar.f667a);
                    String format = String.format("(%s)", Integer.valueOf(a2.length()));
                    if (n == null || o == null) {
                        c.this.a((c) "Failed to write to car - peripheral Gatt or Gatt Service was null!");
                        c.this.u.set(false);
                        c.this.f5380d.a(false, "PHONE_KEY_TRANSMISSION_FAILURE", a2, c.this);
                        return;
                    }
                    BluetoothGattCharacteristic characteristic = o.getCharacteristic(com.teslamotors.plugins.ble.a.f5370d);
                    if (characteristic == null) {
                        c cVar = c.this;
                        cVar.a((c) String.format("Failed to write to car on %s - empty TX characteristic!", cVar), (String) null);
                        c.this.u.set(false);
                        c.this.f5380d.a(false, "PHONE_KEY_TRANSMISSION_FAILURE", a2, c.this);
                        return;
                    }
                    try {
                        if (characteristic.setValue((byte[]) jVar.f667a) && n.writeCharacteristic(characteristic)) {
                            c.this.a((c) String.format("%s - Sending %s: %s", c.this, jVar.f668b, format));
                            return;
                        }
                    } catch (Exception e) {
                        c cVar2 = c.this;
                        cVar2.a((c) String.format("%s - Exception when sending %s: %s", cVar2, jVar.f668b, format), (String) e);
                    }
                    c cVar3 = c.this;
                    cVar3.a((c) String.format("%s - Failed to send %s: %s", cVar3, jVar.f668b, format), (String) null);
                    c.this.u.set(false);
                    c.this.f5380d.a(false, "PHONE_KEY_TRANSMISSION_FAILURE", a2, c.this);
                }
            });
        }
    }

    private a.ac b(byte[] bArr) {
        a.ac acVar = null;
        if (this.q == 0 && this.r == 0) {
            int i2 = (bArr[0] << 8) | (bArr[1] & 255);
            if (i2 > 200 || i2 < 0) {
                a(String.format("Got 'initial' message with length header of %d, ignoring", Integer.valueOf(i2)));
                return null;
            }
            a(String.format("Got 'initial' message with length header of %d", Integer.valueOf(i2)));
            this.r = i2;
            this.q = bArr.length - 2;
            System.arraycopy(bArr, 2, this.p, 0, bArr.length - 2);
        } else {
            int length = bArr.length;
            int i3 = this.r;
            int i4 = this.q;
            if (length <= i3 - i4) {
                System.arraycopy(bArr, 0, this.p, i4, bArr.length);
                this.q += bArr.length;
            } else {
                a(String.format("Failed to add data of length %s to buffer of size %s with %s remaining. Clearing buffer", Integer.valueOf(bArr.length), Integer.valueOf(this.r), Integer.valueOf(this.r - this.q)));
                this.q = 0;
                this.r = 0;
                return null;
            }
        }
        int i5 = this.q;
        int i6 = this.r;
        if (i5 >= i6) {
            try {
                a(" Received complete message: " + String.format("(%s bytes)", Integer.valueOf(i6)));
                acVar = a.ac.a(Arrays.copyOfRange(this.p, 0, this.r));
            } catch (v e2) {
                a(String.format("Failed to deserialize message: %s", e2.toString()), (Exception) null);
            }
            this.r = 0;
            this.q = 0;
        }
        return acVar;
    }

    public void k() {
        String e2 = e();
        if (e2 != null) {
            a("Creating direct connection to: " + this);
            a(this.e.getRemoteDevice(e2).connectGatt(this.f, false, this.A));
            e(true);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void t() {
        this.o = 0;
        d(false);
        a(false);
        b(false);
        int i2 = 0;
        while (true) {
            j<byte[], String> poll = this.x.poll();
            if (poll == null) {
                a(String.format("Disconnecting from %s with %d pending commands", this, Integer.valueOf(i2)));
                return;
            }
            this.f5380d.a(false, "PHONE_KEY_NOT_CONNECTED", g.a((byte[]) poll.f667a), this);
            i2++;
        }
    }

    public void l() {
        t();
        this.f5380d.c(this);
        BluetoothGatt n2 = n();
        if (n2 != null) {
            try {
                this.v = true;
                n2.disconnect();
                n2.close();
                a(String.format("Disconnected Gatt object from %s", this));
            } catch (Exception e2) {
                a("Failed to disconnect Gatt object", e2);
            } catch (Throwable th) {
                a((BluetoothGatt) null);
                throw th;
            }
            a((BluetoothGatt) null);
        } else {
            a(String.format("Gatt object on %s already null!", this));
        }
        u();
    }

    public void b(boolean z2) {
        this.w = z2;
    }

    public void c(boolean z2) {
        if (this.y != z2) {
            this.y = z2;
            if (z2) {
                m().postDelayed(this.z, (long) (Math.random() * 1000.0d));
            } else {
                m().removeCallbacks(this.z);
            }
        }
    }

    /* compiled from: Peripheral */
    class a implements Runnable {

        /* renamed from: a  reason: collision with root package name */
        int f5388a = 0;

        a() {
        }

        public void run() {
            if (c.this.n() == null || !c.this.b()) {
                c cVar = c.this;
                cVar.a((c) String.format("Not connected to %s - cancelling service discovery", cVar));
            } else if (!c.this.g()) {
                this.f5388a++;
                if (!c.this.n().discoverServices()) {
                    this.f5388a = 5;
                }
                c.this.u.set(true);
                if (this.f5388a <= 4) {
                    c cVar2 = c.this;
                    cVar2.a((c) String.format("Looking for services on %s", cVar2));
                    c.this.m().postAtTime(this, c.this.e(), SystemClock.uptimeMillis() + 10000);
                    return;
                }
                c.this.a((c) String.format("Exceeded max retries of %s for finding services on %s", 4, c.this), (String) null);
                c.this.f5380d.f(c.this);
            }
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void c(int i2) {
        m().removeCallbacksAndMessages(e());
        m().postAtTime(new Runnable() {
            /* class com.teslamotors.plugins.ble.a.c.AnonymousClass3 */

            public void run() {
                boolean b2 = c.this.b();
                boolean r = c.this.r();
                if (c.this.e() == null || b2 || r) {
                    c cVar = c.this;
                    cVar.a((c) String.format("Unable to reconnect to %s - isConnected: %s inProgress: %s", cVar, Boolean.valueOf(b2), Boolean.valueOf(r)));
                } else if (c.this.n() != null) {
                    if (c.this.n().connect()) {
                        c cVar2 = c.this;
                        cVar2.a((c) String.format("Setting up long connect to %s via mGatt.connect()", cVar2));
                    } else {
                        c cVar3 = c.this;
                        cVar3.a((c) String.format("Failed to setting up long connect to %s via mGatt.connect()", cVar3), (String) null);
                    }
                    c.this.e((c) false);
                } else {
                    c.this.k();
                }
            }
        }, e(), ((long) b(i2)) + SystemClock.uptimeMillis());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void u() {
        HandlerThread handlerThread = this.t;
        if (handlerThread != null) {
            handlerThread.quitSafely();
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void a(String str) {
        this.f5380d.a(str);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void a(String str, Exception exc) {
        this.f5380d.a(str, exc);
    }
}
