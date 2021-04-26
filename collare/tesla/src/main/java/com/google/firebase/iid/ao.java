package com.google.firebase.iid;

import android.os.Build;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

public class ao implements Parcelable {
    public static final Parcelable.Creator<ao> CREATOR = new ap();

    /* renamed from: a  reason: collision with root package name */
    private Messenger f3892a;

    /* renamed from: b  reason: collision with root package name */
    private ay f3893b;

    public static final class a extends ClassLoader {
        /* access modifiers changed from: protected */
        @Override // java.lang.ClassLoader
        public final Class<?> loadClass(String str, boolean z) {
            if (!"com.google.android.gms.iid.MessengerCompat".equals(str)) {
                return super.loadClass(str, z);
            }
            if (!FirebaseInstanceId.h()) {
                return ao.class;
            }
            Log.d("FirebaseInstanceId", "Using renamed FirebaseIidMessengerCompat class");
            return ao.class;
        }
    }

    public ao(IBinder iBinder) {
        if (Build.VERSION.SDK_INT >= 21) {
            this.f3892a = new Messenger(iBinder);
        } else {
            this.f3893b = new az(iBinder);
        }
    }

    public int describeContents() {
        return 0;
    }

    public final void a(Message message) {
        Messenger messenger = this.f3892a;
        if (messenger != null) {
            messenger.send(message);
        } else {
            this.f3893b.a(message);
        }
    }

    private final IBinder a() {
        Messenger messenger = this.f3892a;
        return messenger != null ? messenger.getBinder() : this.f3893b.asBinder();
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        try {
            return a().equals(((ao) obj).a());
        } catch (ClassCastException unused) {
            return false;
        }
    }

    public int hashCode() {
        return a().hashCode();
    }

    public void writeToParcel(Parcel parcel, int i) {
        Messenger messenger = this.f3892a;
        if (messenger != null) {
            parcel.writeStrongBinder(messenger.getBinder());
        } else {
            parcel.writeStrongBinder(this.f3893b.asBinder());
        }
    }
}
