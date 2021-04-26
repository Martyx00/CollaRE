package android.support.v4.app;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

/* access modifiers changed from: package-private */
/* compiled from: FragmentState */
public final class p implements Parcelable {
    public static final Parcelable.Creator<p> CREATOR = new Parcelable.Creator<p>() {
        /* class android.support.v4.app.p.AnonymousClass1 */

        /* renamed from: a */
        public p createFromParcel(Parcel parcel) {
            return new p(parcel);
        }

        /* renamed from: a */
        public p[] newArray(int i) {
            return new p[i];
        }
    };

    /* renamed from: a  reason: collision with root package name */
    final String f253a;

    /* renamed from: b  reason: collision with root package name */
    final int f254b;

    /* renamed from: c  reason: collision with root package name */
    final boolean f255c;

    /* renamed from: d  reason: collision with root package name */
    final int f256d;
    final int e;
    final String f;
    final boolean g;
    final boolean h;
    final Bundle i;
    final boolean j;
    Bundle k;
    g l;

    public int describeContents() {
        return 0;
    }

    p(g gVar) {
        this.f253a = gVar.getClass().getName();
        this.f254b = gVar.mIndex;
        this.f255c = gVar.mFromLayout;
        this.f256d = gVar.mFragmentId;
        this.e = gVar.mContainerId;
        this.f = gVar.mTag;
        this.g = gVar.mRetainInstance;
        this.h = gVar.mDetached;
        this.i = gVar.mArguments;
        this.j = gVar.mHidden;
    }

    p(Parcel parcel) {
        this.f253a = parcel.readString();
        this.f254b = parcel.readInt();
        boolean z = true;
        this.f255c = parcel.readInt() != 0;
        this.f256d = parcel.readInt();
        this.e = parcel.readInt();
        this.f = parcel.readString();
        this.g = parcel.readInt() != 0;
        this.h = parcel.readInt() != 0;
        this.i = parcel.readBundle();
        this.j = parcel.readInt() == 0 ? false : z;
        this.k = parcel.readBundle();
    }

    public g a(k kVar, i iVar, g gVar, n nVar, android.arch.lifecycle.p pVar) {
        if (this.l == null) {
            Context i2 = kVar.i();
            Bundle bundle = this.i;
            if (bundle != null) {
                bundle.setClassLoader(i2.getClassLoader());
            }
            if (iVar != null) {
                this.l = iVar.a(i2, this.f253a, this.i);
            } else {
                this.l = g.instantiate(i2, this.f253a, this.i);
            }
            Bundle bundle2 = this.k;
            if (bundle2 != null) {
                bundle2.setClassLoader(i2.getClassLoader());
                this.l.mSavedFragmentState = this.k;
            }
            this.l.setIndex(this.f254b, gVar);
            g gVar2 = this.l;
            gVar2.mFromLayout = this.f255c;
            gVar2.mRestored = true;
            gVar2.mFragmentId = this.f256d;
            gVar2.mContainerId = this.e;
            gVar2.mTag = this.f;
            gVar2.mRetainInstance = this.g;
            gVar2.mDetached = this.h;
            gVar2.mHidden = this.j;
            gVar2.mFragmentManager = kVar.f206b;
            if (m.f209a) {
                Log.v("FragmentManager", "Instantiated fragment " + this.l);
            }
        }
        g gVar3 = this.l;
        gVar3.mChildNonConfig = nVar;
        gVar3.mViewModelStore = pVar;
        return gVar3;
    }

    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeString(this.f253a);
        parcel.writeInt(this.f254b);
        parcel.writeInt(this.f255c ? 1 : 0);
        parcel.writeInt(this.f256d);
        parcel.writeInt(this.e);
        parcel.writeString(this.f);
        parcel.writeInt(this.g ? 1 : 0);
        parcel.writeInt(this.h ? 1 : 0);
        parcel.writeBundle(this.i);
        parcel.writeInt(this.j ? 1 : 0);
        parcel.writeBundle(this.k);
    }
}
