package com.teslamotors.plugins.ble;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.nfc.cardemulation.HostApduService;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.Parcelable;
import android.os.RemoteException;
import com.teslamotors.plugins.ble.b.c;
import com.teslamotors.plugins.ble.b.e;
import com.teslamotors.plugins.ble.b.h;
import com.teslamotors.plugins.client.b;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class TeslaCardEmulationService extends HostApduService {

    /* renamed from: c  reason: collision with root package name */
    private static final String f5360c = "TeslaCardEmulationService";

    /* renamed from: d  reason: collision with root package name */
    private static final byte[] f5361d = {111, 0};
    private static final byte[] e = {110, 0};
    private static final byte[] f = {109, 0};
    private static final byte[] g = {-112, 0};
    private static final byte[] h = {109, 0};
    private static final byte[] i = {107, 0};

    /* renamed from: a  reason: collision with root package name */
    Messenger f5362a;

    /* renamed from: b  reason: collision with root package name */
    boolean f5363b;
    private final Messenger j = new Messenger(new a());
    private final ServiceConnection k = new ServiceConnection() {
        /* class com.teslamotors.plugins.ble.TeslaCardEmulationService.AnonymousClass1 */

        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            b.b(TeslaCardEmulationService.f5360c, "onServiceConnected");
            TeslaCardEmulationService.this.f5362a = new Messenger(iBinder);
            TeslaCardEmulationService.this.f5363b = true;
        }

        public void onServiceDisconnected(ComponentName componentName) {
            if (TeslaCardEmulationService.this.f5363b) {
                TeslaCardEmulationService.this.f5363b = false;
                b.b(TeslaCardEmulationService.f5360c, "onServiceDisconnected");
            }
            TeslaCardEmulationService.this.f5362a = null;
        }
    };

    class a extends Handler {
        a() {
        }

        public void handleMessage(Message message) {
            Bundle data = message.getData();
            data.setClassLoader(getClass().getClassLoader());
            switch (e.a(message.what)) {
                case NFCGetPublicKeyBytesInfo:
                    Parcelable parcelable = data.getParcelable(e.NFCGetPublicKeyBytesInfo.b());
                    if (parcelable != null) {
                        TeslaCardEmulationService.this.a((TeslaCardEmulationService) ((h) parcelable));
                        return;
                    }
                    b.b(TeslaCardEmulationService.f5360c, "Pub key not ready yet, resend the request");
                    Message obtain = Message.obtain();
                    obtain.what = e.NFCGetPublicKeyBytes.a();
                    sendMessage(obtain);
                    return;
                case NFCEncryptWithSharedSecretResponse:
                    Parcelable parcelable2 = data.getParcelable(e.NFCEncryptWithSharedSecretResponse.b());
                    if (parcelable2 != null) {
                        TeslaCardEmulationService.this.a((TeslaCardEmulationService) ((c) parcelable2));
                        return;
                    } else {
                        b.b(TeslaCardEmulationService.f5360c, "NFC auth response not ready yet");
                        return;
                    }
                case RegisterComplete:
                    b.b(TeslaCardEmulationService.f5360c, "Successfully registered to received callbacks from BLE service");
                    return;
                default:
                    return;
            }
        }
    }

    public void onCreate() {
        b.b(f5360c, "onCreate called");
        bindService(new Intent(this, BLEService.class), this.k, 1);
    }

    public void onDestroy() {
        if (this.f5363b) {
            this.f5363b = false;
        }
        this.f5362a = null;
        unbindService(this.k);
        b.b(f5360c, "onDestroy called");
    }

    public void onDeactivated(int i2) {
        String str = f5360c;
        b.b(str, "Deactivated: " + i2);
    }

    private static byte[] a(short s) {
        return new byte[]{(byte) (s >> 8), (byte) (s & 255)};
    }

    private boolean a(Message message) {
        Messenger messenger;
        String str = f5360c;
        b.b(str, "Sending message:" + e.b(message.what));
        boolean z = false;
        if (!this.f5363b || (messenger = this.f5362a) == null) {
            b.b(f5360c, "Failed to send message - service not bound");
            return false;
        }
        try {
            message.replyTo = this.j;
            messenger.send(message);
            z = true;
            String str2 = f5360c;
            b.b(str2, "Sent to Server from client :" + this.f5362a.toString() + "Reply to " + this.j.toString());
            return true;
        } catch (RemoteException e2) {
            String str3 = f5360c;
            b.c(str3, "Failed to send message - Remote Exception! : " + e2.getMessage());
            return z;
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void a(h hVar) {
        byte[] b2 = hVar.b();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        if (b2 != null) {
            try {
                byteArrayOutputStream.write(b2);
                byteArrayOutputStream.write(g);
            } catch (IOException e2) {
                String str = f5360c;
                b.c(str, "IOException adding public key to array" + e2.getMessage());
            }
        } else {
            byteArrayOutputStream.write(a((short) 28435));
        }
        sendResponseApdu(byteArrayOutputStream.toByteArray());
        String str2 = f5360c;
        b.b(str2, "APDU Response (Get Pub key):" + byteArrayOutputStream.toByteArray().toString());
        String str3 = f5360c;
        b.b(str3, "APDU Response (Get Pub key):Length =" + byteArrayOutputStream.size());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void a(c cVar) {
        byte[] a2 = cVar.a();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        if (a2 != null) {
            try {
                byteArrayOutputStream.write(Arrays.copyOfRange(a2, 0, 16));
                byteArrayOutputStream.write(g);
            } catch (IOException e2) {
                String str = f5360c;
                b.c(str, "Exception adding encrypted response to the message" + e2.getMessage());
            }
        } else {
            byteArrayOutputStream.write(a((short) 28442));
        }
        sendResponseApdu(byteArrayOutputStream.toByteArray());
        String str2 = f5360c;
        b.b(str2, "APDU Response (Get Auth response):" + byteArrayOutputStream.toByteArray().toString());
        String str3 = f5360c;
        b.b(str3, "APDU Response (Get Auth response):Length =" + byteArrayOutputStream.size());
    }

    public byte[] processCommandApdu(byte[] bArr, Bundle bundle) {
        b.b(f5360c, "Called Tesla Logic applet");
        byte[] bArr2 = g;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte b2 = bArr[1];
        if (b2 == -92) {
            bArr2 = g;
            b.b(f5360c, "Called to enable Tesla Logic applet");
        } else if (b2 != 4) {
            if (b2 != 17) {
                if (b2 == 20) {
                    try {
                        byteArrayOutputStream.write(a((short) 49));
                    } catch (IOException e2) {
                        String str = f5360c;
                        b.c(str, "Exception when sending form factor" + e2.getMessage());
                    }
                } else if (b2 != 27) {
                    bArr2 = h;
                } else {
                    b.b(f5360c, "Got Vehicle Info");
                    if (bArr[4] < 30) {
                        byte[] copyOfRange = Arrays.copyOfRange(bArr, 5, bArr[4] + 5);
                        String str2 = f5360c;
                        b.b(str2, "From Vehicle Protobuf : " + g.a(copyOfRange));
                        Message obtain = Message.obtain();
                        obtain.what = e.NFCProtoMessage.a();
                        Bundle bundle2 = new Bundle();
                        bundle2.putByteArray(e.NFCProtoMessage.b(), copyOfRange);
                        obtain.setData(bundle2);
                        if (!a(obtain)) {
                            bArr2 = a((short) 28442);
                        }
                    } else {
                        b.b(f5360c, "Ignore Vehicle proto, too long info");
                    }
                }
            } else if (((short) bArr[2]) != 0) {
                bArr2 = i;
            } else if (bArr[4] != 81) {
                bArr2 = a((short) 28441);
            } else {
                byte[] copyOfRange2 = Arrays.copyOfRange(bArr, 5, 70);
                byte[] copyOfRange3 = Arrays.copyOfRange(bArr, 70, 86);
                String str3 = f5360c;
                b.b(str3, "Requested Vehicle Public Key :" + g.a(copyOfRange2));
                String str4 = f5360c;
                b.b(str4, "Requested Vehicle Token :" + g.a(copyOfRange3));
                Message obtain2 = Message.obtain();
                obtain2.what = e.NFCEncryptWithSharedSecret.a();
                Bundle bundle3 = new Bundle();
                ArrayList<String> arrayList = new ArrayList<>();
                arrayList.add(g.a(copyOfRange3));
                arrayList.add(g.a(copyOfRange2));
                bundle3.putStringArrayList(e.NFCEncryptWithSharedSecret.b(), arrayList);
                obtain2.setData(bundle3);
                if (a(obtain2)) {
                    return null;
                }
                bArr2 = a((short) 28442);
            }
        } else if (((short) bArr[2]) != 0) {
            bArr2 = i;
        } else {
            Message obtain3 = Message.obtain();
            obtain3.what = e.NFCGetPublicKeyBytes.a();
            if (a(obtain3)) {
                return null;
            }
            bArr2 = a((short) 28435);
        }
        try {
            byteArrayOutputStream.write(bArr2);
        } catch (IOException e3) {
            String str5 = f5360c;
            b.c(str5, "Exception adding status code" + e3.getMessage());
        }
        String str6 = f5360c;
        b.b(str6, "APDU Response :" + byteArrayOutputStream.toByteArray().toString());
        String str7 = f5360c;
        b.b(str7, "APDU Response :Length =" + byteArrayOutputStream.size());
        return byteArrayOutputStream.toByteArray();
    }
}
