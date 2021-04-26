package com.google.firebase.iid;

import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;

final class l {

    /* renamed from: a  reason: collision with root package name */
    private final Messenger f3933a;

    /* renamed from: b  reason: collision with root package name */
    private final ao f3934b;

    l(IBinder iBinder) {
        String interfaceDescriptor = iBinder.getInterfaceDescriptor();
        if ("android.os.IMessenger".equals(interfaceDescriptor)) {
            this.f3933a = new Messenger(iBinder);
            this.f3934b = null;
        } else if ("com.google.android.gms.iid.IMessengerCompat".equals(interfaceDescriptor)) {
            this.f3934b = new ao(iBinder);
            this.f3933a = null;
        } else {
            String valueOf = String.valueOf(interfaceDescriptor);
            Log.w("MessengerIpcClient", valueOf.length() != 0 ? "Invalid interface descriptor: ".concat(valueOf) : new String("Invalid interface descriptor: "));
            throw new RemoteException();
        }
    }

    /* access modifiers changed from: package-private */
    public final void a(Message message) {
        Messenger messenger = this.f3933a;
        if (messenger != null) {
            messenger.send(message);
            return;
        }
        ao aoVar = this.f3934b;
        if (aoVar != null) {
            aoVar.a(message);
            return;
        }
        throw new IllegalStateException("Both messengers are null");
    }
}
