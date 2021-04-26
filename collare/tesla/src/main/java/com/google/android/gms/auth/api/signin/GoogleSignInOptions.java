package com.google.android.gms.auth.api.signin;

import android.accounts.Account;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.google.android.gms.auth.api.signin.internal.GoogleSignInOptionsExtensionParcelable;
import com.google.android.gms.auth.api.signin.internal.HashAccumulator;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.AccountType;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@SafeParcelable.Class(creator = "GoogleSignInOptionsCreator")
public class GoogleSignInOptions extends AbstractSafeParcelable implements Api.ApiOptions.Optional, ReflectedParcelable {
    public static final Parcelable.Creator<GoogleSignInOptions> CREATOR = new zad();
    public static final GoogleSignInOptions DEFAULT_GAMES_SIGN_IN = new Builder().requestScopes(zau, new Scope[0]).build();
    public static final GoogleSignInOptions DEFAULT_SIGN_IN = new Builder().requestId().requestProfile().build();
    private static Comparator<Scope> zaaf = new zac();
    @VisibleForTesting
    public static final Scope zar = new Scope(Scopes.PROFILE);
    @VisibleForTesting
    public static final Scope zas = new Scope(Scopes.EMAIL);
    @VisibleForTesting
    public static final Scope zat = new Scope(Scopes.OPEN_ID);
    @VisibleForTesting
    public static final Scope zau = new Scope(Scopes.GAMES_LITE);
    @VisibleForTesting
    public static final Scope zav = new Scope(Scopes.GAMES);
    @SafeParcelable.VersionField(id = 1)
    private final int versionCode;
    @SafeParcelable.Field(getter = "isForceCodeForRefreshToken", id = 6)
    private final boolean zaaa;
    @SafeParcelable.Field(getter = "getServerClientId", id = 7)
    private String zaab;
    @SafeParcelable.Field(getter = "getHostedDomain", id = 8)
    private String zaac;
    @SafeParcelable.Field(getter = "getExtensions", id = 9)
    private ArrayList<GoogleSignInOptionsExtensionParcelable> zaad;
    private Map<Integer, GoogleSignInOptionsExtensionParcelable> zaae;
    @SafeParcelable.Field(getter = "getScopes", id = 2)
    private final ArrayList<Scope> zaw;
    @SafeParcelable.Field(getter = "getAccount", id = 3)
    private Account zax;
    @SafeParcelable.Field(getter = "isIdTokenRequested", id = 4)
    private boolean zay;
    @SafeParcelable.Field(getter = "isServerAuthCodeRequested", id = 5)
    private final boolean zaz;

    public static GoogleSignInOptions zab(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        JSONObject jSONObject = new JSONObject(str);
        HashSet hashSet = new HashSet();
        JSONArray jSONArray = jSONObject.getJSONArray("scopes");
        int length = jSONArray.length();
        for (int i = 0; i < length; i++) {
            hashSet.add(new Scope(jSONArray.getString(i)));
        }
        String optString = jSONObject.optString("accountName", null);
        return new GoogleSignInOptions(3, new ArrayList(hashSet), !TextUtils.isEmpty(optString) ? new Account(optString, AccountType.GOOGLE) : null, jSONObject.getBoolean("idTokenRequested"), jSONObject.getBoolean("serverAuthRequested"), jSONObject.getBoolean("forceCodeForRefreshToken"), jSONObject.optString("serverClientId", null), jSONObject.optString("hostedDomain", null), new HashMap());
    }

    public static final class Builder {
        private Set<Scope> mScopes = new HashSet();
        private boolean zaaa;
        private String zaab;
        private String zaac;
        private Map<Integer, GoogleSignInOptionsExtensionParcelable> zaag = new HashMap();
        private Account zax;
        private boolean zay;
        private boolean zaz;

        public Builder() {
        }

        public Builder(GoogleSignInOptions googleSignInOptions) {
            Preconditions.checkNotNull(googleSignInOptions);
            this.mScopes = new HashSet(googleSignInOptions.zaw);
            this.zaz = googleSignInOptions.zaz;
            this.zaaa = googleSignInOptions.zaaa;
            this.zay = googleSignInOptions.zay;
            this.zaab = googleSignInOptions.zaab;
            this.zax = googleSignInOptions.zax;
            this.zaac = googleSignInOptions.zaac;
            this.zaag = GoogleSignInOptions.zaa(googleSignInOptions.zaad);
        }

        public final Builder requestId() {
            this.mScopes.add(GoogleSignInOptions.zat);
            return this;
        }

        public final Builder requestEmail() {
            this.mScopes.add(GoogleSignInOptions.zas);
            return this;
        }

        public final Builder requestProfile() {
            this.mScopes.add(GoogleSignInOptions.zar);
            return this;
        }

        public final Builder requestScopes(Scope scope, Scope... scopeArr) {
            this.mScopes.add(scope);
            this.mScopes.addAll(Arrays.asList(scopeArr));
            return this;
        }

        public final Builder requestIdToken(String str) {
            this.zay = true;
            this.zaab = zac(str);
            return this;
        }

        public final Builder requestServerAuthCode(String str) {
            return requestServerAuthCode(str, false);
        }

        public final Builder requestServerAuthCode(String str, boolean z) {
            this.zaz = true;
            this.zaab = zac(str);
            this.zaaa = z;
            return this;
        }

        public final Builder setAccountName(String str) {
            this.zax = new Account(Preconditions.checkNotEmpty(str), AccountType.GOOGLE);
            return this;
        }

        public final Builder setHostedDomain(String str) {
            this.zaac = Preconditions.checkNotEmpty(str);
            return this;
        }

        public final Builder addExtension(GoogleSignInOptionsExtension googleSignInOptionsExtension) {
            if (!this.zaag.containsKey(Integer.valueOf(googleSignInOptionsExtension.getExtensionType()))) {
                if (googleSignInOptionsExtension.getImpliedScopes() != null) {
                    this.mScopes.addAll(googleSignInOptionsExtension.getImpliedScopes());
                }
                this.zaag.put(Integer.valueOf(googleSignInOptionsExtension.getExtensionType()), new GoogleSignInOptionsExtensionParcelable(googleSignInOptionsExtension));
                return this;
            }
            throw new IllegalStateException("Only one extension per type may be added");
        }

        public final GoogleSignInOptions build() {
            if (this.mScopes.contains(GoogleSignInOptions.zav) && this.mScopes.contains(GoogleSignInOptions.zau)) {
                this.mScopes.remove(GoogleSignInOptions.zau);
            }
            if (this.zay && (this.zax == null || !this.mScopes.isEmpty())) {
                requestId();
            }
            return new GoogleSignInOptions(3, new ArrayList(this.mScopes), this.zax, this.zay, this.zaz, this.zaaa, this.zaab, this.zaac, this.zaag, null);
        }

        private final String zac(String str) {
            Preconditions.checkNotEmpty(str);
            String str2 = this.zaab;
            Preconditions.checkArgument(str2 == null || str2.equals(str), "two different server client ids provided");
            return str;
        }
    }

    @SafeParcelable.Constructor
    GoogleSignInOptions(@SafeParcelable.Param(id = 1) int i, @SafeParcelable.Param(id = 2) ArrayList<Scope> arrayList, @SafeParcelable.Param(id = 3) Account account, @SafeParcelable.Param(id = 4) boolean z, @SafeParcelable.Param(id = 5) boolean z2, @SafeParcelable.Param(id = 6) boolean z3, @SafeParcelable.Param(id = 7) String str, @SafeParcelable.Param(id = 8) String str2, @SafeParcelable.Param(id = 9) ArrayList<GoogleSignInOptionsExtensionParcelable> arrayList2) {
        this(i, arrayList, account, z, z2, z3, str, str2, zaa(arrayList2));
    }

    private GoogleSignInOptions(int i, ArrayList<Scope> arrayList, Account account, boolean z, boolean z2, boolean z3, String str, String str2, Map<Integer, GoogleSignInOptionsExtensionParcelable> map) {
        this.versionCode = i;
        this.zaw = arrayList;
        this.zax = account;
        this.zay = z;
        this.zaz = z2;
        this.zaaa = z3;
        this.zaab = str;
        this.zaac = str2;
        this.zaad = new ArrayList<>(map.values());
        this.zaae = map;
    }

    @KeepForSdk
    public ArrayList<Scope> getScopes() {
        return new ArrayList<>(this.zaw);
    }

    public Scope[] getScopeArray() {
        ArrayList<Scope> arrayList = this.zaw;
        return (Scope[]) arrayList.toArray(new Scope[arrayList.size()]);
    }

    @KeepForSdk
    public Account getAccount() {
        return this.zax;
    }

    @KeepForSdk
    public boolean isIdTokenRequested() {
        return this.zay;
    }

    @KeepForSdk
    public boolean isServerAuthCodeRequested() {
        return this.zaz;
    }

    @KeepForSdk
    public boolean isForceCodeForRefreshToken() {
        return this.zaaa;
    }

    @KeepForSdk
    public String getServerClientId() {
        return this.zaab;
    }

    @KeepForSdk
    public ArrayList<GoogleSignInOptionsExtensionParcelable> getExtensions() {
        return this.zaad;
    }

    /* access modifiers changed from: private */
    public static Map<Integer, GoogleSignInOptionsExtensionParcelable> zaa(List<GoogleSignInOptionsExtensionParcelable> list) {
        HashMap hashMap = new HashMap();
        if (list == null) {
            return hashMap;
        }
        for (GoogleSignInOptionsExtensionParcelable googleSignInOptionsExtensionParcelable : list) {
            hashMap.put(Integer.valueOf(googleSignInOptionsExtensionParcelable.getType()), googleSignInOptionsExtensionParcelable);
        }
        return hashMap;
    }

    public void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.versionCode);
        SafeParcelWriter.writeTypedList(parcel, 2, getScopes(), false);
        SafeParcelWriter.writeParcelable(parcel, 3, getAccount(), i, false);
        SafeParcelWriter.writeBoolean(parcel, 4, isIdTokenRequested());
        SafeParcelWriter.writeBoolean(parcel, 5, isServerAuthCodeRequested());
        SafeParcelWriter.writeBoolean(parcel, 6, isForceCodeForRefreshToken());
        SafeParcelWriter.writeString(parcel, 7, getServerClientId(), false);
        SafeParcelWriter.writeString(parcel, 8, this.zaac, false);
        SafeParcelWriter.writeTypedList(parcel, 9, getExtensions(), false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:20:0x004a, code lost:
        if (r3.zax.equals(r4.getAccount()) != false) goto L_0x004c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0069, code lost:
        if (r3.zaab.equals(r4.getServerClientId()) != false) goto L_0x006b;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(java.lang.Object r4) {
        /*
        // Method dump skipped, instructions count: 137
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.auth.api.signin.GoogleSignInOptions.equals(java.lang.Object):boolean");
    }

    public int hashCode() {
        ArrayList arrayList = new ArrayList();
        ArrayList<Scope> arrayList2 = this.zaw;
        int size = arrayList2.size();
        int i = 0;
        while (i < size) {
            Scope scope = arrayList2.get(i);
            i++;
            arrayList.add(scope.getScopeUri());
        }
        Collections.sort(arrayList);
        return new HashAccumulator().addObject(arrayList).addObject(this.zax).addObject(this.zaab).zaa(this.zaaa).zaa(this.zay).zaa(this.zaz).hash();
    }

    public final String zae() {
        return zad().toString();
    }

    private final JSONObject zad() {
        JSONObject jSONObject = new JSONObject();
        try {
            JSONArray jSONArray = new JSONArray();
            Collections.sort(this.zaw, zaaf);
            ArrayList<Scope> arrayList = this.zaw;
            int size = arrayList.size();
            int i = 0;
            while (i < size) {
                Scope scope = arrayList.get(i);
                i++;
                jSONArray.put(scope.getScopeUri());
            }
            jSONObject.put("scopes", jSONArray);
            if (this.zax != null) {
                jSONObject.put("accountName", this.zax.name);
            }
            jSONObject.put("idTokenRequested", this.zay);
            jSONObject.put("forceCodeForRefreshToken", this.zaaa);
            jSONObject.put("serverAuthRequested", this.zaz);
            if (!TextUtils.isEmpty(this.zaab)) {
                jSONObject.put("serverClientId", this.zaab);
            }
            if (!TextUtils.isEmpty(this.zaac)) {
                jSONObject.put("hostedDomain", this.zaac);
            }
            return jSONObject;
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    /* synthetic */ GoogleSignInOptions(int i, ArrayList arrayList, Account account, boolean z, boolean z2, boolean z3, String str, String str2, Map map, zac zac) {
        this(3, arrayList, account, z, z2, z3, str, str2, map);
    }
}
