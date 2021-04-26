package com.teslamotors.plugins.ble.c;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import com.teslamotors.a.a;
import com.teslamotors.plugins.ble.b.b;
import com.teslamotors.plugins.ble.d;
import com.teslamotors.plugins.ble.f;
import com.teslamotors.plugins.ble.g;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;

/* compiled from: VehicleStateMachine */
public class c {
    private static final Set<a> i = new HashSet(Arrays.asList(a.WAITING_FOR_STATUS, a.GET_STATUS, a.ALL_CRYPTO_COMPLETE, a.CONNECTED_IDLE));
    private static final Set<a> j = new HashSet(Arrays.asList(a.WAITING_FOR_STATUS, a.GET_STATUS, a.ALL_CRYPTO_COMPLETE, a.CONNECTED_IDLE, a.IDLE, a.WAITING_FOR_PERMISSION, a.WAITING_FOR_CRYPTO_COUNTER));
    private static final Set<a> k = new HashSet(Arrays.asList(a.WAITING_FOR_STATUS, a.WAITING_FOR_PUB_KEY, a.WAITING_FOR_PERMISSION, a.WAITING_FOR_CRYPTO_COUNTER, a.WAITING_FOR_WHITELIST, a.WAITING_FOR_COMMAND_PERIPHERAL));
    private static final Set<a> l = new HashSet(Arrays.asList(a.IDLE, a.WAITING_FOR_PUB_KEY, a.WAITING_FOR_WHITELIST, a.WAITING_FOR_COMMAND_PERIPHERAL, a.WAITING_FOR_PERMISSION, a.WAITING_FOR_CRYPTO_COUNTER, a.WAITING_FOR_STATUS, a.CONNECTED_IDLE));
    private boolean A;
    private final d B;
    private final String C;
    private final f D;
    private final Context E;
    private Runnable F = new Runnable() {
        /* class com.teslamotors.plugins.ble.c.c.AnonymousClass2 */

        public void run() {
            if (!c.k.contains(c.this.p) || c.this.D.i() == b.CONNECTED) {
                if (c.this.f5435c > 10 && !c.this.r) {
                    c.this.a((c) "Timed out getting public key");
                    c.this.a((c) a.IDLE);
                    c.this.D.m();
                } else if (c.this.f5436d > 10 && !c.this.t) {
                    c.this.a((c) "Timed out getting whitelist");
                    c.this.a((c) a.IDLE);
                    c.this.D.m();
                } else if (c.this.f == 10) {
                    c.this.a((c) "Timed out getting status", (String) new Exception("[TMBLE Logic] Failed to get vehicle status after 10 attempts; resetting command peripheral."));
                    c.this.a((c) a.IDLE);
                    c.this.D.m();
                } else if (c.this.g > 10) {
                    c.this.a((c) "Timed out getting permissions");
                    c.this.a((c) a.SYNC_CRYPTO_COUNTER);
                } else if (c.this.e > 10) {
                    c.this.a((c) "Timed out getting IV");
                    c.this.a((c) a.ALL_CRYPTO_COMPLETE);
                } else if (c.this.p == a.WAITING_FOR_PUB_KEY) {
                    c.this.f5435c++;
                    c.this.a((c) "Restarting timer for request pub key");
                    c.this.a((c) a.REQUEST_PUB_KEY);
                } else if (c.this.p == a.WAITING_FOR_WHITELIST) {
                    c.this.f5436d++;
                    c.this.a((c) "Restarting timer for whitelist");
                    c.this.a((c) a.CHECK_WHITELIST);
                } else if (c.this.p == a.WAITING_FOR_STATUS) {
                    c.this.f++;
                    c.this.a((c) "Restarting timer for status");
                    c.this.a((c) a.GET_STATUS);
                } else if (c.this.p == a.WAITING_FOR_PERMISSION) {
                    c.this.g++;
                    c.this.a((c) "Restarting timer for permission");
                    c.this.a((c) a.GET_PERMISSION);
                } else if (c.this.p == a.WAITING_FOR_CRYPTO_COUNTER) {
                    c.this.e++;
                    c.this.a((c) "Restarting timer for syncing crypto counter");
                    c.this.a((c) a.SYNC_CRYPTO_COUNTER);
                }
            } else if (!c.this.D.k()) {
                c cVar = c.this;
                cVar.a((c) String.format("Timed out in state %s but no other candidates. Cancelling retries", cVar.p.name()));
                c.this.a((c) a.IDLE);
            } else {
                c cVar2 = c.this;
                cVar2.a((c) String.format("Timed out in state %s. Trying a different command peripheral", cVar2.p.name()));
                c.this.a((c) a.CONNECT_TO_COMMAND_PERIPHERAL);
            }
        }
    };

    /* renamed from: a  reason: collision with root package name */
    byte[] f5433a;

    /* renamed from: b  reason: collision with root package name */
    byte[] f5434b = null;

    /* renamed from: c  reason: collision with root package name */
    public int f5435c;

    /* renamed from: d  reason: collision with root package name */
    public int f5436d;
    public int e;
    public int f;
    public int g;
    Handler h;
    private PublicKey m;
    private PrivateKey n;
    private PublicKey o;
    private a p;
    private boolean q;
    private boolean r;
    private boolean s;
    private boolean t;
    private String u;
    private a.bv v;
    private AtomicLong w = new AtomicLong();
    private a.j x;
    private int y;
    private int z;

    public c(f fVar, String str, String str2, Context context) {
        boolean z2 = false;
        this.q = false;
        this.r = false;
        this.s = false;
        this.t = false;
        this.A = false;
        this.p = a.IDLE;
        this.h = new Handler(Looper.getMainLooper());
        this.B = new d();
        this.x = null;
        this.D = fVar;
        this.C = str;
        this.u = str2;
        this.E = context;
        this.w.set(com.teslamotors.plugins.client.f.a(this.E).e(this.C, str2));
        this.y = (int) com.teslamotors.plugins.client.f.a(this.E).f(this.C, str2);
        this.t = this.y > 0 ? true : z2;
    }

    private synchronized long r() {
        return com.teslamotors.plugins.client.f.a(this.E).a(this.w.incrementAndGet(), this.C, this.u);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void a(a aVar) {
        boolean z2 = true;
        a(String.format("State Change: %s -> %s", this.p, aVar));
        if (this.p == aVar) {
            z2 = false;
        }
        this.p = aVar;
        if (z2 || !l.contains(this.p)) {
            s();
        }
    }

    private void s() {
        a(String.format("State: %s", this.p));
        switch (this.p) {
            case GENERATE_KEY_PAIR:
                if (!this.q) {
                    try {
                        KeyPair a2 = this.B.a(this.u, this.E);
                        if (a2 == null) {
                            a("Generating key pair for ");
                            try {
                                a2 = d.a();
                                if (!this.B.a(a2, this.u, this.E)) {
                                    a("[TMBLE] Unable to store local key pair");
                                    a2 = null;
                                }
                            } catch (Exception e2) {
                                a("Unrecoverable failure trying to generate local key pair", e2);
                                return;
                            }
                        } else {
                            a("Found key pair");
                        }
                        if (a2 != null) {
                            this.q = true;
                            this.n = a2.getPrivate();
                            this.m = a2.getPublic();
                            this.f5434b = d.a(this.m);
                            a(String.format("Local pub key ID    : %s", g.a(this.f5434b)));
                        }
                        t();
                    } catch (Exception e3) {
                        a("Unrecoverable failure trying to get local key pair", e3);
                        return;
                    }
                }
                a(a.IDLE);
                return;
            case CONNECT_TO_COMMAND_PERIPHERAL:
                if (this.D.j()) {
                    a("trying to connect to new command peripheral when one already exists");
                    com.teslamotors.plugins.client.f.a(this.E).a("[TMBLE Logic] trying to connect to new command peripheral when one already exists", (Integer) 100, (Map<String, Object>) null);
                    a(a.CONNECTION_ESTABLISHED);
                    return;
                } else if (this.D.k()) {
                    a("connect to command peripheral with candidate peripherals but no command peripheral");
                    u();
                    a(a.WAITING_FOR_COMMAND_PERIPHERAL);
                    this.D.l();
                    return;
                } else {
                    a("connect to command peripheral with no peripherals; going back to idle");
                    a(a.IDLE);
                    return;
                }
            case CONNECTION_ESTABLISHED:
                if (!this.r) {
                    a(a.REQUEST_PUB_KEY);
                    return;
                } else if (!this.q) {
                    a(a.GENERATE_KEY_PAIR);
                    return;
                } else if (!this.s) {
                    a(a.DERIVE_SHARED_KEY);
                    return;
                } else if (!this.t) {
                    a(a.CHECK_WHITELIST);
                    return;
                } else {
                    t();
                    a(a.ALL_CRYPTO_COMPLETE);
                    return;
                }
            case REQUEST_PUB_KEY:
                if (this.A) {
                    u();
                    a(a.WAITING_FOR_PUB_KEY);
                    b(a.aq.INFORMATION_REQUEST_TYPE_GET_EPHEMERAL_PUBLIC_KEY);
                    return;
                }
                return;
            case PARSE_PUB_KEY:
                PublicKey v2 = v();
                this.o = v2;
                this.f5435c = 0;
                if (v2 != null) {
                    this.r = true;
                    com.teslamotors.plugins.client.f.a(this.E).g(this.C, g.a(d.b(this.o)));
                    a(a.DERIVE_SHARED_KEY);
                    t();
                    return;
                }
                a("Failed to parse public key");
                com.teslamotors.plugins.client.f.a(this.E).a("[TMBLE Logic] Could not parse public key", (Integer) 100, (Map<String, Object>) null);
                a(a.REQUEST_PUB_KEY);
                return;
            case DERIVE_SHARED_KEY:
                if (!this.s && this.r && this.q) {
                    a("Performing ECDH Key Exchange");
                    this.f5433a = d.a(this.o, this.n);
                    if (this.f5433a != null) {
                        this.s = true;
                        a("Performed ECDH Key Exchange - shared secret:");
                        if (!this.t) {
                            a(a.CHECK_WHITELIST);
                            return;
                        } else {
                            a(a.ALL_CRYPTO_COMPLETE);
                            return;
                        }
                    } else {
                        a("Could not perform Key Exchange.");
                        com.teslamotors.plugins.client.f.a(this.E).a("[TMBLE Logic] Could not perform key exchange", (Integer) 100, (Map<String, Object>) null);
                        this.r = false;
                        a(a.REQUEST_PUB_KEY);
                        return;
                    }
                } else if (!this.s || !this.r || !this.q) {
                    a(String.format("Could not derive shared keys. Derived keys %s. Remote key %s. Local key %s", Boolean.valueOf(this.s), Boolean.valueOf(this.r), Boolean.valueOf(this.q)));
                    com.teslamotors.plugins.client.f.a(this.E).a("[TMBLE Logic] Could not derive shared keys", (Integer) 100, (Map<String, Object>) new HashMap<String, Object>() {
                        /* class com.teslamotors.plugins.ble.c.c.AnonymousClass1 */

                        {
                            put("derived_keys", Boolean.valueOf(c.this.s));
                            put("remote_key", Boolean.valueOf(c.this.r));
                            put("local_key", Boolean.valueOf(c.this.q));
                        }
                    });
                    return;
                } else {
                    return;
                }
            case CHECK_WHITELIST:
                if (this.A) {
                    u();
                    a(a.WAITING_FOR_WHITELIST);
                    b(a.aq.INFORMATION_REQUEST_TYPE_GET_WHITELIST_INFO);
                    return;
                }
                return;
            case GET_STATUS:
                if (this.A) {
                    u();
                    a(a.WAITING_FOR_STATUS);
                    b(a.aq.INFORMATION_REQUEST_TYPE_GET_STATUS);
                    return;
                }
                return;
            case GET_PERMISSION:
                if (this.A) {
                    u();
                    a(a.WAITING_FOR_PERMISSION);
                    b(a.aq.INFORMATION_REQUEST_TYPE_GET_WHITELIST_ENTRY_INFO);
                    return;
                }
                return;
            case SYNC_CRYPTO_COUNTER:
                if (this.A) {
                    u();
                    a(a.WAITING_FOR_CRYPTO_COUNTER);
                    b(a.aq.INFORMATION_REQUEST_TYPE_GET_COUNTER);
                    return;
                }
                return;
            case ALL_CRYPTO_COMPLETE:
                a(a.GET_STATUS);
                return;
            case CONNECTED_IDLE:
                this.D.o();
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void t() {
        this.D.a(true);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void a(String str, Exception exc) {
        this.D.a(String.format("[Logic Error] %s", str), exc);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void a(String str) {
        this.D.a(String.format("[Logic] %s", str));
    }

    private void u() {
        this.h.removeCallbacks(this.F);
        this.h.postDelayed(this.F, 2000);
    }

    private PublicKey v() {
        a(String.format("Parsing certificate to retrieve public key: %s", "REDACTED"));
        this.o = this.B.a(this.v.e().d());
        return this.o;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void b(a.aq aqVar) {
        this.D.a(a(aqVar), aqVar.name());
    }

    public void a() {
        this.h.post(new Runnable() {
            /* class com.teslamotors.plugins.ble.c.c.AnonymousClass3 */

            public void run() {
                c cVar = c.this;
                cVar.a((c) ("start: " + c.this.p));
                if (c.this.p == a.IDLE && c.this.u != null && !c.this.u.isEmpty()) {
                    c.this.a((c) a.GENERATE_KEY_PAIR);
                }
            }
        });
    }

    public void b() {
        this.h.post(new Runnable() {
            /* class com.teslamotors.plugins.ble.c.c.AnonymousClass4 */

            public void run() {
                c cVar = c.this;
                cVar.a((c) ("connectionEstablished - in state " + c.this.p));
                c cVar2 = c.this;
                cVar2.f5435c = 0;
                cVar2.f5436d = 0;
                cVar2.f = 0;
                cVar2.e = 0;
                cVar2.g = 0;
                cVar2.A = true;
                c.this.a((c) a.CONNECTION_ESTABLISHED);
            }
        });
    }

    public void a(com.teslamotors.plugins.ble.a.c cVar, final boolean z2) {
        this.h.post(new Runnable() {
            /* class com.teslamotors.plugins.ble.c.c.AnonymousClass5 */

            public void run() {
                c cVar = c.this;
                cVar.a((c) ("connectionLost - in state " + c.this.p));
                if (!z2) {
                    c.this.a((c) "connectionLost non-command peripheral");
                    return;
                }
                c.this.A = false;
                c.this.h.removeCallbacks(c.this.F);
                c.this.a((c) a.CONNECT_TO_COMMAND_PERIPHERAL);
            }
        });
    }

    public void a(final com.teslamotors.plugins.ble.a.c cVar) {
        this.h.post(new Runnable() {
            /* class com.teslamotors.plugins.ble.c.c.AnonymousClass6 */

            public void run() {
                c cVar = c.this;
                cVar.a((c) String.format("Found peripheral %s - in state %s", cVar, cVar.p));
                if (c.this.D.j() || c.this.p == a.WAITING_FOR_COMMAND_PERIPHERAL || c.this.p == a.CONNECT_TO_COMMAND_PERIPHERAL) {
                    c.this.D.o();
                    return;
                }
                c.this.a((c) "mVehicleController does not have command peripheral... Connecting");
                c.this.a((c) a.CONNECT_TO_COMMAND_PERIPHERAL);
            }
        });
    }

    public void c() {
        this.h.post(new Runnable() {
            /* class com.teslamotors.plugins.ble.c.c.AnonymousClass7 */

            public void run() {
                c.this.r = false;
                c.this.s = false;
                c.this.t = false;
                c.this.y = 0;
                com.teslamotors.plugins.client.f.a(c.this.E).a(c.this.C, c.this.u, 0);
                c cVar = c.this;
                cVar.f5435c = 0;
                cVar.e = 0;
                cVar.f5436d = 0;
                cVar.f = 0;
                cVar.g = 0;
                cVar.f5433a = null;
                cVar.z = 0;
                c.this.x = null;
                c.this.a((c) "Forgetting remote ephemeral and derived secret");
                c.this.a((c) a.IDLE);
            }
        });
    }

    public void a(final a.ac acVar, final com.teslamotors.plugins.ble.a.c cVar) {
        this.h.post(new Runnable() {
            /* class com.teslamotors.plugins.ble.c.c.AnonymousClass8 */

            public void run() {
                a.bv e = acVar.e();
                if (!e.e().c()) {
                    c.this.a((c) String.format("Received session info with public key: %s", "REDACTED"));
                    if (c.this.p == a.WAITING_FOR_PUB_KEY) {
                        c.this.v = e;
                        c.this.a((c) a.PARSE_PUB_KEY);
                    }
                }
                if (e.d() != 0 || c.this.p == a.WAITING_FOR_CRYPTO_COUNTER) {
                    c cVar = c.this;
                    cVar.e = 0;
                    long j = cVar.w.get();
                    c.this.a((c) String.format("Received session info with counter. VCSEC: %d Phone: %d", Integer.valueOf(e.d()), Long.valueOf(j)));
                    if (((long) e.d()) > j) {
                        c.this.w.set((long) e.d());
                        com.teslamotors.plugins.client.f.a(c.this.E).a(c.this.w.get(), c.this.C, c.this.u);
                        if (!c.this.D.u()) {
                            HashMap hashMap = new HashMap();
                            hashMap.put("vcsec_iv", Integer.valueOf(e.d()));
                            hashMap.put("phone_iv", Long.valueOf(j));
                            hashMap.put("delta", Long.valueOf(((long) e.d()) - j));
                            com.teslamotors.plugins.client.f.a(c.this.E).a("[TMBLE Logic] IV inconsistent outside of pairing...", (Integer) 100, (Map<String, Object>) hashMap);
                            c.this.a((c) String.format("WARNING! VCSEC and phone IV were inconsistent outside of pairing. VCSEC: %d Phone: %d", Integer.valueOf(e.d()), Long.valueOf(j)));
                        }
                    }
                    if (c.this.p == a.WAITING_FOR_CRYPTO_COUNTER) {
                        c.this.a((c) a.ALL_CRYPTO_COMPLETE);
                    }
                }
                a.ec d2 = acVar.d();
                if (d2 != d2.F()) {
                    c cVar2 = c.this;
                    cVar2.f = 0;
                    cVar2.a((c) String.format("Received vehicle status: %s", com.teslamotors.plugins.ble.a.d.b(d2)));
                    if (c.this.p == a.WAITING_FOR_STATUS) {
                        if (c.this.x == null) {
                            c.this.b((c) a.aq.INFORMATION_REQUEST_TYPE_GET_CAPABILITIES);
                        }
                        c.this.a((c) a.CONNECTED_IDLE);
                    }
                }
                if (acVar.l() != a.j.h()) {
                    c.this.x = acVar.l();
                    c cVar3 = c.this;
                    cVar3.a((c) String.format("Received capabilities: CP Open: %s, CP Close: %s", Boolean.valueOf(cVar3.x.c()), Boolean.valueOf(c.this.x.d())));
                }
                a.q g = acVar.g();
                if (g.F() != g) {
                    if (g.f() != a.cf.i()) {
                        a.ce e2 = g.f().e();
                        int c2 = g.f().c();
                        c.this.D.a(c2, b.a(g.e()), b.a(e2), cVar);
                        c.this.a((c) String.format("Command Status: %s, Counter: %s, Message Information: %s", g.e().name(), Integer.valueOf(c2), e2.name()));
                        if (g.e() == a.bl.OPERATIONSTATUS_ERROR) {
                            if (e2 == a.ce.SIGNEDMESSAGE_INFORMATION_FAULT_NOT_ON_WHITELIST) {
                                c.this.b((c) a.aq.INFORMATION_REQUEST_TYPE_GET_WHITELIST_INFO);
                            } else if (e2 != a.ce.SIGNEDMESSAGE_INFORMATION_FAULT_AES_DECRYPT_AUTH) {
                                c.this.b((c) a.aq.INFORMATION_REQUEST_TYPE_GET_COUNTER);
                            } else if (!c.this.D.u()) {
                                com.teslamotors.plugins.client.f.a(c.this.E).a("[TMBLE Logic] AES Decrypt auth fault outside of pairing", (Integer) 100, (Map<String, Object>) null);
                                c.this.a((c) "Got AES Fault - re-fetching public key / deriving keys");
                                c.this.r = false;
                                c.this.s = false;
                                c.this.a((c) a.REQUEST_PUB_KEY);
                            }
                        }
                    } else {
                        c.this.a((c) String.format("Command Status: %s", g.e().name()));
                    }
                }
                a.ei i = acVar.i();
                if (i != i.F()) {
                    boolean z = c.this.t;
                    c.this.t = false;
                    c.this.y = i.c();
                    c cVar4 = c.this;
                    cVar4.f5436d = 0;
                    cVar4.a((c) String.format("Received whitelist message with %s entries", Integer.valueOf(cVar4.y)));
                    for (int i2 = 0; i2 < c.this.y; i2++) {
                        a.as a2 = i.a(i2);
                        if (!a2.c().c()) {
                            byte[] d3 = a2.c().d();
                            if (Arrays.equals(d3, c.this.f5434b)) {
                                c.this.t = true;
                            }
                            c.this.a((c) String.format("Whitelist Entry %s", g.a(Arrays.copyOf(d3, 4))));
                        }
                    }
                    if (!z || !c.this.t) {
                        if (c.this.t) {
                            c cVar5 = c.this;
                            cVar5.a((c) String.format("Found [%s] in whitelist! -> GET_PERMISSION", g.a(cVar5.f5434b)));
                            c.this.a((c) a.GET_PERMISSION);
                        } else {
                            c.this.z = 0;
                            c cVar6 = c.this;
                            cVar6.a((c) String.format("Did NOT find key [%s] in whitelist -> IDLE", g.a(cVar6.f5434b)));
                            c.this.a((c) a.IDLE);
                            if (c.this.D.u()) {
                                c.this.a((c) "Staying connected while unauthorized");
                            } else {
                                c.this.h.post(new Runnable() {
                                    /* class com.teslamotors.plugins.ble.c.c.AnonymousClass8.AnonymousClass1 */

                                    public void run() {
                                        c.this.D.n();
                                    }
                                });
                            }
                        }
                    }
                    com.teslamotors.plugins.client.f.a(c.this.E).a(c.this.C, c.this.u, c.this.t ? (long) c.this.y : 0);
                    c.this.t();
                }
                a.eg j2 = acVar.j();
                if (j2 != j2.F()) {
                    int size = j2.g().size();
                    if (size == 0 || !j2.c()) {
                        c.this.a((c) String.format("WARNING. Whitelist Entry Information Empty. %o permissions, isKeyIDPresent: %b", Integer.valueOf(size), Boolean.valueOf(j2.c())));
                    } else {
                        c.this.a((c) String.format("Whitelist Entry Information. %o permissions found for key [%s]", Integer.valueOf(size), g.a(Arrays.copyOf(j2.d().c().d(), 4))));
                        c.this.z = 0;
                        for (int i3 = 0; i3 < size; i3++) {
                            c.this.a((c) String.format("Permission %o: %s", Integer.valueOf(i3), j2.a(i3).name()));
                            c.this.z |= 1 << j2.a(i3).a();
                        }
                    }
                    c cVar7 = c.this;
                    cVar7.g = 0;
                    cVar7.a((c) "Received permissions! -> SYNC_CRYPTO_COUNTER");
                    c.this.a((c) a.SYNC_CRYPTO_COUNTER);
                }
            }
        });
    }

    public com.teslamotors.plugins.ble.a.a a(a.d dVar) {
        a.dl g2 = a.dl.z().a(a.g.f().a(dVar).u()).u();
        if (i.contains(this.p) || (j.contains(this.p) && dVar == a.d.AUTHENTICATION_LEVEL_NONE)) {
            return a(g2);
        }
        a(String.format("Skipped authentication response due to wrong state: %s", this.p.name()));
        return null;
    }

    public com.teslamotors.plugins.ble.a.a d() {
        a.dl g2 = a.dl.z().a(a.au.g().C().a(a.ar.KEY_FORM_FACTOR_ANDROID_DEVICE)).u();
        if (i.contains(this.p)) {
            return a(g2);
        }
        a(String.format("Skipped key metadata response due to wrong state: %s", this.p.name()));
        return null;
    }

    public com.teslamotors.plugins.ble.a.a a(a.bs bsVar) {
        a.dl g2 = a.dl.z().a(bsVar).u();
        if (i.contains(this.p)) {
            return a(g2);
        }
        a(String.format("Rejected command due to wrong state: %s", this.p.name()));
        return null;
    }

    private com.teslamotors.plugins.ble.a.a a(a.dl dlVar) {
        long r2 = r();
        byte[] a2 = d.a(dlVar.toByteArray(), this.f5433a, r2);
        if (a2 == null) {
            a("Encrypted command is null!");
            return null;
        }
        a(String.format("Signed message - (%s) %s %s", Integer.valueOf(a2.length - 16), g.a(this.f5434b), Long.valueOf(r2)));
        return new com.teslamotors.plugins.ble.a.a(r2, a(a(a.cb.SIGNATURE_TYPE_AES_GCM, Arrays.copyOf(a2, a2.length - 16), Arrays.copyOfRange(a2, a2.length - 16, a2.length), r2, this.f5434b)));
    }

    private a.cc a(a.cb cbVar, byte[] bArr, byte[] bArr2, long j2, byte[] bArr3) {
        a.cc.C0109a i2 = a.cc.l().C();
        if (!(cbVar != a.cb.SIGNATURE_TYPE_AES_GCM || bArr3 == null || bArr2 == null)) {
            i2.a((int) j2).b(com.google.protobuf.g.a(bArr3)).d(com.google.protobuf.g.a(bArr2));
        }
        i2.a(cbVar);
        i2.c(com.google.protobuf.g.a(bArr));
        return i2.u();
    }

    private a.df a(a.cc ccVar) {
        if (ccVar == null) {
            return null;
        }
        return a.df.g().a(ccVar).u();
    }

    public a.df e() {
        return a(a(true, d.b(this.m)));
    }

    public a.df f() {
        return a(a(false, d.b(this.m)));
    }

    private a.cc a(boolean z2, byte[] bArr) {
        if (!j.contains(this.p)) {
            a(String.format("Rejected whitelist operation due to wrong state %s", this.p));
            return null;
        } else if (!this.q || bArr == null) {
            a("Rejected whitelist operation due to keys not generated");
            return null;
        } else {
            a.bq g2 = a.bq.g().C().a(com.google.protobuf.g.a(bArr)).u();
            a.el.C0138a l2 = a.el.o().C();
            if (z2) {
                a.bm.C0102a h2 = a.bm.k().C();
                h2.a(a.ek.WHITELISTKEYPERMISSION_LOCAL_UNLOCK);
                h2.a(a.ek.WHITELISTKEYPERMISSION_LOCAL_DRIVE);
                h2.a(a.ek.WHITELISTKEYPERMISSION_REMOTE_UNLOCK);
                h2.a(a.ek.WHITELISTKEYPERMISSION_REMOTE_DRIVE);
                h2.a(g2);
                l2.a(h2);
                l2.a(a.au.g().C().a(a.ar.KEY_FORM_FACTOR_ANDROID_DEVICE));
            } else {
                l2.b(g2);
            }
            return a(a.cb.SIGNATURE_TYPE_PRESENT_KEY, a.dl.z().a(l2).u().toByteArray(), null, r(), this.f5434b);
        }
    }

    public boolean g() {
        return this.f5433a != null;
    }

    public int h() {
        return this.y;
    }

    public boolean i() {
        return this.t;
    }

    public boolean j() {
        return this.q;
    }

    public boolean k() {
        return this.r;
    }

    public long l() {
        return this.w.get();
    }

    public int m() {
        return this.z;
    }

    public a.j n() {
        return this.x;
    }

    public a.df a(a.aq aqVar) {
        return a(aqVar, this.f5434b);
    }

    public a.df a(a.aq aqVar, byte[] bArr) {
        a.ao.C0095a a2 = a.ao.g().a(aqVar);
        if (this.f5434b != null && (aqVar == a.aq.INFORMATION_REQUEST_TYPE_GET_WHITELIST_ENTRY_INFO || aqVar == a.aq.INFORMATION_REQUEST_TYPE_GET_TOKEN || aqVar == a.aq.INFORMATION_REQUEST_TYPE_GET_COUNTER || aqVar == a.aq.INFORMATION_REQUEST_TYPE_GET_SESSION_DATA)) {
            a2.a(a.as.g().C().a(com.google.protobuf.g.a(bArr)).u());
        }
        return a.df.g().a(a.dl.z().a(a2.u()).u()).u();
    }

    public String o() {
        byte[] bArr = this.f5434b;
        if (bArr == null || bArr.length == 0) {
            return null;
        }
        return g.a(bArr);
    }

    public byte[] p() {
        PublicKey publicKey = this.m;
        if (publicKey == null) {
            return null;
        }
        return d.b(publicKey);
    }

    public byte[] a(byte[] bArr, byte[] bArr2) {
        a.bv bvVar;
        boolean z2 = this.r && (bvVar = this.v) != null && Arrays.equals(bvVar.e().d(), bArr2);
        if (this.s && z2) {
            return d.a(this.f5433a, bArr);
        }
        if (!this.q) {
            return null;
        }
        this.B.a(bArr2);
        byte[] a2 = d.a(this.B.a(bArr2), this.n);
        if (a2 != null) {
            return d.a(a2, bArr);
        }
        return null;
    }
}
