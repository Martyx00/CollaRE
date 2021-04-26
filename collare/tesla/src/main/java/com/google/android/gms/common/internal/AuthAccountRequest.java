package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.IAccountAccessor;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@SafeParcelable.Class(creator = "AuthAccountRequestCreator")
public class AuthAccountRequest extends AbstractSafeParcelable {
    public static final Parcelable.Creator<AuthAccountRequest> CREATOR = new zaa();
    @SafeParcelable.VersionField(id = 1)
    private final int zalf;
    @SafeParcelable.Field(id = 2)
    @Deprecated
    private final IBinder zanx;
    @SafeParcelable.Field(id = 3)
    private final Scope[] zany;
    @SafeParcelable.Field(id = 4)
    private Integer zanz;
    @SafeParcelable.Field(id = 5)
    private Integer zaoa;
    @SafeParcelable.Field(id = 6, type = "android.accounts.Account")
    private Account zax;

    @SafeParcelable.Constructor
    AuthAccountRequest(@SafeParcelable.Param(id = 1) int i, @SafeParcelable.Param(id = 2) IBinder iBinder, @SafeParcelable.Param(id = 3) Scope[] scopeArr, @SafeParcelable.Param(id = 4) Integer num, @SafeParcelable.Param(id = 5) Integer num2, @SafeParcelable.Param(id = 6) Account account) {
        this.zalf = i;
        this.zanx = iBinder;
        this.zany = scopeArr;
        this.zanz = num;
        this.zaoa = num2;
        this.zax = account;
    }

    @Deprecated
    public AuthAccountRequest(IAccountAccessor iAccountAccessor, Set<Scope> set) {
        this(3, iAccountAccessor.asBinder(), (Scope[]) set.toArray(new Scope[set.size()]), null, null, null);
    }

    public AuthAccountRequest(Account account, Set<Scope> set) {
        this(3, null, (Scope[]) set.toArray(new Scope[set.size()]), null, null, (Account) Preconditions.checkNotNull(account));
    }

    public Account getAccount() {
        Account account = this.zax;
        if (account != null) {
            return account;
        }
        IBinder iBinder = this.zanx;
        if (iBinder != null) {
            return AccountAccessor.getAccountBinderSafe(IAccountAccessor.Stub.asInterface(iBinder));
        }
        return null;
    }

    public Set<Scope> getScopes() {
        return new HashSet(Arrays.asList(this.zany));
    }

    public AuthAccountRequest setOauthPolicy(Integer num) {
        this.zanz = num;
        return this;
    }

    public Integer getOauthPolicy() {
        return this.zanz;
    }

    public AuthAccountRequest setPolicyAction(Integer num) {
        this.zaoa = num;
        return this;
    }

    public Integer getPolicyAction() {
        return this.zaoa;
    }

    public void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.zalf);
        SafeParcelWriter.writeIBinder(parcel, 2, this.zanx, false);
        SafeParcelWriter.writeTypedArray(parcel, 3, this.zany, i, false);
        SafeParcelWriter.writeIntegerObject(parcel, 4, this.zanz, false);
        SafeParcelWriter.writeIntegerObject(parcel, 5, this.zaoa, false);
        SafeParcelWriter.writeParcelable(parcel, 6, this.zax, i, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
