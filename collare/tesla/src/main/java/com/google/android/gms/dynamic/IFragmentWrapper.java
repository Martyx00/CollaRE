package com.google.android.gms.dynamic;

import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.common.zzb;
import com.google.android.gms.internal.common.zzc;

public interface IFragmentWrapper extends IInterface {

    public static abstract class Stub extends zzb implements IFragmentWrapper {
        public Stub() {
            super("com.google.android.gms.dynamic.IFragmentWrapper");
        }

        public static class zza extends com.google.android.gms.internal.common.zza implements IFragmentWrapper {
            zza(IBinder iBinder) {
                super(iBinder, "com.google.android.gms.dynamic.IFragmentWrapper");
            }

            @Override // com.google.android.gms.dynamic.IFragmentWrapper
            public final IObjectWrapper zzae() {
                Parcel zza = zza(2, zza());
                IObjectWrapper asInterface = IObjectWrapper.Stub.asInterface(zza.readStrongBinder());
                zza.recycle();
                return asInterface;
            }

            @Override // com.google.android.gms.dynamic.IFragmentWrapper
            public final Bundle getArguments() {
                Parcel zza = zza(3, zza());
                Bundle bundle = (Bundle) zzc.zza(zza, Bundle.CREATOR);
                zza.recycle();
                return bundle;
            }

            @Override // com.google.android.gms.dynamic.IFragmentWrapper
            public final int getId() {
                Parcel zza = zza(4, zza());
                int readInt = zza.readInt();
                zza.recycle();
                return readInt;
            }

            @Override // com.google.android.gms.dynamic.IFragmentWrapper
            public final IFragmentWrapper zzaf() {
                Parcel zza = zza(5, zza());
                IFragmentWrapper asInterface = Stub.asInterface(zza.readStrongBinder());
                zza.recycle();
                return asInterface;
            }

            @Override // com.google.android.gms.dynamic.IFragmentWrapper
            public final IObjectWrapper zzag() {
                Parcel zza = zza(6, zza());
                IObjectWrapper asInterface = IObjectWrapper.Stub.asInterface(zza.readStrongBinder());
                zza.recycle();
                return asInterface;
            }

            @Override // com.google.android.gms.dynamic.IFragmentWrapper
            public final boolean getRetainInstance() {
                Parcel zza = zza(7, zza());
                boolean zza2 = zzc.zza(zza);
                zza.recycle();
                return zza2;
            }

            @Override // com.google.android.gms.dynamic.IFragmentWrapper
            public final String getTag() {
                Parcel zza = zza(8, zza());
                String readString = zza.readString();
                zza.recycle();
                return readString;
            }

            @Override // com.google.android.gms.dynamic.IFragmentWrapper
            public final IFragmentWrapper zzah() {
                Parcel zza = zza(9, zza());
                IFragmentWrapper asInterface = Stub.asInterface(zza.readStrongBinder());
                zza.recycle();
                return asInterface;
            }

            @Override // com.google.android.gms.dynamic.IFragmentWrapper
            public final int getTargetRequestCode() {
                Parcel zza = zza(10, zza());
                int readInt = zza.readInt();
                zza.recycle();
                return readInt;
            }

            @Override // com.google.android.gms.dynamic.IFragmentWrapper
            public final boolean getUserVisibleHint() {
                Parcel zza = zza(11, zza());
                boolean zza2 = zzc.zza(zza);
                zza.recycle();
                return zza2;
            }

            @Override // com.google.android.gms.dynamic.IFragmentWrapper
            public final IObjectWrapper zzai() {
                Parcel zza = zza(12, zza());
                IObjectWrapper asInterface = IObjectWrapper.Stub.asInterface(zza.readStrongBinder());
                zza.recycle();
                return asInterface;
            }

            @Override // com.google.android.gms.dynamic.IFragmentWrapper
            public final boolean isAdded() {
                Parcel zza = zza(13, zza());
                boolean zza2 = zzc.zza(zza);
                zza.recycle();
                return zza2;
            }

            @Override // com.google.android.gms.dynamic.IFragmentWrapper
            public final boolean isDetached() {
                Parcel zza = zza(14, zza());
                boolean zza2 = zzc.zza(zza);
                zza.recycle();
                return zza2;
            }

            @Override // com.google.android.gms.dynamic.IFragmentWrapper
            public final boolean isHidden() {
                Parcel zza = zza(15, zza());
                boolean zza2 = zzc.zza(zza);
                zza.recycle();
                return zza2;
            }

            @Override // com.google.android.gms.dynamic.IFragmentWrapper
            public final boolean isInLayout() {
                Parcel zza = zza(16, zza());
                boolean zza2 = zzc.zza(zza);
                zza.recycle();
                return zza2;
            }

            @Override // com.google.android.gms.dynamic.IFragmentWrapper
            public final boolean isRemoving() {
                Parcel zza = zza(17, zza());
                boolean zza2 = zzc.zza(zza);
                zza.recycle();
                return zza2;
            }

            @Override // com.google.android.gms.dynamic.IFragmentWrapper
            public final boolean isResumed() {
                Parcel zza = zza(18, zza());
                boolean zza2 = zzc.zza(zza);
                zza.recycle();
                return zza2;
            }

            @Override // com.google.android.gms.dynamic.IFragmentWrapper
            public final boolean isVisible() {
                Parcel zza = zza(19, zza());
                boolean zza2 = zzc.zza(zza);
                zza.recycle();
                return zza2;
            }

            @Override // com.google.android.gms.dynamic.IFragmentWrapper
            public final void zza(IObjectWrapper iObjectWrapper) {
                Parcel zza = zza();
                zzc.zza(zza, iObjectWrapper);
                zzb(20, zza);
            }

            @Override // com.google.android.gms.dynamic.IFragmentWrapper
            public final void setHasOptionsMenu(boolean z) {
                Parcel zza = zza();
                zzc.writeBoolean(zza, z);
                zzb(21, zza);
            }

            @Override // com.google.android.gms.dynamic.IFragmentWrapper
            public final void setMenuVisibility(boolean z) {
                Parcel zza = zza();
                zzc.writeBoolean(zza, z);
                zzb(22, zza);
            }

            @Override // com.google.android.gms.dynamic.IFragmentWrapper
            public final void setRetainInstance(boolean z) {
                Parcel zza = zza();
                zzc.writeBoolean(zza, z);
                zzb(23, zza);
            }

            @Override // com.google.android.gms.dynamic.IFragmentWrapper
            public final void setUserVisibleHint(boolean z) {
                Parcel zza = zza();
                zzc.writeBoolean(zza, z);
                zzb(24, zza);
            }

            @Override // com.google.android.gms.dynamic.IFragmentWrapper
            public final void startActivity(Intent intent) {
                Parcel zza = zza();
                zzc.zza(zza, intent);
                zzb(25, zza);
            }

            @Override // com.google.android.gms.dynamic.IFragmentWrapper
            public final void startActivityForResult(Intent intent, int i) {
                Parcel zza = zza();
                zzc.zza(zza, intent);
                zza.writeInt(i);
                zzb(26, zza);
            }

            @Override // com.google.android.gms.dynamic.IFragmentWrapper
            public final void zzb(IObjectWrapper iObjectWrapper) {
                Parcel zza = zza();
                zzc.zza(zza, iObjectWrapper);
                zzb(27, zza);
            }
        }

        public static IFragmentWrapper asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.dynamic.IFragmentWrapper");
            if (queryLocalInterface instanceof IFragmentWrapper) {
                return (IFragmentWrapper) queryLocalInterface;
            }
            return new zza(iBinder);
        }

        /* access modifiers changed from: protected */
        @Override // com.google.android.gms.internal.common.zzb
        public final boolean zza(int i, Parcel parcel, Parcel parcel2, int i2) {
            switch (i) {
                case 2:
                    IObjectWrapper zzae = zzae();
                    parcel2.writeNoException();
                    zzc.zza(parcel2, zzae);
                    return true;
                case 3:
                    Bundle arguments = getArguments();
                    parcel2.writeNoException();
                    zzc.zzb(parcel2, arguments);
                    return true;
                case 4:
                    int id = getId();
                    parcel2.writeNoException();
                    parcel2.writeInt(id);
                    return true;
                case 5:
                    IFragmentWrapper zzaf = zzaf();
                    parcel2.writeNoException();
                    zzc.zza(parcel2, zzaf);
                    return true;
                case 6:
                    IObjectWrapper zzag = zzag();
                    parcel2.writeNoException();
                    zzc.zza(parcel2, zzag);
                    return true;
                case 7:
                    boolean retainInstance = getRetainInstance();
                    parcel2.writeNoException();
                    zzc.writeBoolean(parcel2, retainInstance);
                    return true;
                case 8:
                    String tag = getTag();
                    parcel2.writeNoException();
                    parcel2.writeString(tag);
                    return true;
                case 9:
                    IFragmentWrapper zzah = zzah();
                    parcel2.writeNoException();
                    zzc.zza(parcel2, zzah);
                    return true;
                case 10:
                    int targetRequestCode = getTargetRequestCode();
                    parcel2.writeNoException();
                    parcel2.writeInt(targetRequestCode);
                    return true;
                case 11:
                    boolean userVisibleHint = getUserVisibleHint();
                    parcel2.writeNoException();
                    zzc.writeBoolean(parcel2, userVisibleHint);
                    return true;
                case 12:
                    IObjectWrapper zzai = zzai();
                    parcel2.writeNoException();
                    zzc.zza(parcel2, zzai);
                    return true;
                case 13:
                    boolean isAdded = isAdded();
                    parcel2.writeNoException();
                    zzc.writeBoolean(parcel2, isAdded);
                    return true;
                case 14:
                    boolean isDetached = isDetached();
                    parcel2.writeNoException();
                    zzc.writeBoolean(parcel2, isDetached);
                    return true;
                case 15:
                    boolean isHidden = isHidden();
                    parcel2.writeNoException();
                    zzc.writeBoolean(parcel2, isHidden);
                    return true;
                case 16:
                    boolean isInLayout = isInLayout();
                    parcel2.writeNoException();
                    zzc.writeBoolean(parcel2, isInLayout);
                    return true;
                case 17:
                    boolean isRemoving = isRemoving();
                    parcel2.writeNoException();
                    zzc.writeBoolean(parcel2, isRemoving);
                    return true;
                case 18:
                    boolean isResumed = isResumed();
                    parcel2.writeNoException();
                    zzc.writeBoolean(parcel2, isResumed);
                    return true;
                case 19:
                    boolean isVisible = isVisible();
                    parcel2.writeNoException();
                    zzc.writeBoolean(parcel2, isVisible);
                    return true;
                case 20:
                    zza(IObjectWrapper.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 21:
                    setHasOptionsMenu(zzc.zza(parcel));
                    parcel2.writeNoException();
                    return true;
                case 22:
                    setMenuVisibility(zzc.zza(parcel));
                    parcel2.writeNoException();
                    return true;
                case 23:
                    setRetainInstance(zzc.zza(parcel));
                    parcel2.writeNoException();
                    return true;
                case 24:
                    setUserVisibleHint(zzc.zza(parcel));
                    parcel2.writeNoException();
                    return true;
                case 25:
                    startActivity((Intent) zzc.zza(parcel, Intent.CREATOR));
                    parcel2.writeNoException();
                    return true;
                case 26:
                    startActivityForResult((Intent) zzc.zza(parcel, Intent.CREATOR), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 27:
                    zzb(IObjectWrapper.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                default:
                    return false;
            }
        }
    }

    Bundle getArguments();

    int getId();

    boolean getRetainInstance();

    String getTag();

    int getTargetRequestCode();

    boolean getUserVisibleHint();

    boolean isAdded();

    boolean isDetached();

    boolean isHidden();

    boolean isInLayout();

    boolean isRemoving();

    boolean isResumed();

    boolean isVisible();

    void setHasOptionsMenu(boolean z);

    void setMenuVisibility(boolean z);

    void setRetainInstance(boolean z);

    void setUserVisibleHint(boolean z);

    void startActivity(Intent intent);

    void startActivityForResult(Intent intent, int i);

    void zza(IObjectWrapper iObjectWrapper);

    IObjectWrapper zzae();

    IFragmentWrapper zzaf();

    IObjectWrapper zzag();

    IFragmentWrapper zzah();

    IObjectWrapper zzai();

    void zzb(IObjectWrapper iObjectWrapper);
}
