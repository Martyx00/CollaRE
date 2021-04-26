package android.support.v4.d;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

/* compiled from: IResultReceiver */
public interface c extends IInterface {
    void a(int i, Bundle bundle);

    /* compiled from: IResultReceiver */
    public static abstract class a extends Binder implements c {
        public IBinder asBinder() {
            return this;
        }

        public a() {
            attachInterface(this, "android.support.v4.os.IResultReceiver");
        }

        public static c a(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("android.support.v4.os.IResultReceiver");
            if (queryLocalInterface == null || !(queryLocalInterface instanceof c)) {
                return new C0007a(iBinder);
            }
            return (c) queryLocalInterface;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
            if (i == 1) {
                parcel.enforceInterface("android.support.v4.os.IResultReceiver");
                a(parcel.readInt(), parcel.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcel) : null);
                return true;
            } else if (i != 1598968902) {
                return super.onTransact(i, parcel, parcel2, i2);
            } else {
                parcel2.writeString("android.support.v4.os.IResultReceiver");
                return true;
            }
        }

        /* renamed from: android.support.v4.d.c$a$a  reason: collision with other inner class name */
        /* compiled from: IResultReceiver */
        private static class C0007a implements c {

            /* renamed from: a  reason: collision with root package name */
            private IBinder f396a;

            C0007a(IBinder iBinder) {
                this.f396a = iBinder;
            }

            public IBinder asBinder() {
                return this.f396a;
            }

            @Override // android.support.v4.d.c
            public void a(int i, Bundle bundle) {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("android.support.v4.os.IResultReceiver");
                    obtain.writeInt(i);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.f396a.transact(1, obtain, null, 1);
                } finally {
                    obtain.recycle();
                }
            }
        }
    }
}
