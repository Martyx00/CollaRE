package android.support.v4.d;

import android.os.Bundle;
import android.os.Handler;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.d.c;

/* compiled from: ResultReceiver */
public class e implements Parcelable {
    public static final Parcelable.Creator<e> CREATOR = new Parcelable.Creator<e>() {
        /* class android.support.v4.d.e.AnonymousClass1 */

        /* renamed from: a */
        public e createFromParcel(Parcel parcel) {
            return new e(parcel);
        }

        /* renamed from: a */
        public e[] newArray(int i) {
            return new e[i];
        }
    };

    /* renamed from: a  reason: collision with root package name */
    final boolean f397a = false;

    /* renamed from: b  reason: collision with root package name */
    final Handler f398b = null;

    /* renamed from: c  reason: collision with root package name */
    c f399c;

    /* access modifiers changed from: protected */
    public void a(int i, Bundle bundle) {
    }

    public int describeContents() {
        return 0;
    }

    /* compiled from: ResultReceiver */
    class b implements Runnable {

        /* renamed from: a  reason: collision with root package name */
        final int f401a;

        /* renamed from: b  reason: collision with root package name */
        final Bundle f402b;

        b(int i, Bundle bundle) {
            this.f401a = i;
            this.f402b = bundle;
        }

        public void run() {
            e.this.a(this.f401a, this.f402b);
        }
    }

    /* compiled from: ResultReceiver */
    class a extends c.a {
        a() {
        }

        @Override // android.support.v4.d.c
        public void a(int i, Bundle bundle) {
            if (e.this.f398b != null) {
                e.this.f398b.post(new b(i, bundle));
            } else {
                e.this.a(i, bundle);
            }
        }
    }

    public void writeToParcel(Parcel parcel, int i) {
        synchronized (this) {
            if (this.f399c == null) {
                this.f399c = new a();
            }
            parcel.writeStrongBinder(this.f399c.asBinder());
        }
    }

    e(Parcel parcel) {
        this.f399c = c.a.a(parcel.readStrongBinder());
    }
}
