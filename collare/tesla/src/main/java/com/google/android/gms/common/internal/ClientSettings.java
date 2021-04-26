package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.content.Context;
import android.support.v4.util.b;
import android.view.View;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.signin.SignInOptions;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@VisibleForTesting
@KeepForSdk
public final class ClientSettings {
    public static final String KEY_CLIENT_SESSION_ID = "com.google.android.gms.common.internal.ClientSettings.sessionId";
    private final Set<Scope> zabr;
    private final int zabt;
    private final View zabu;
    private final String zabv;
    private final String zabw;
    private final boolean zaby;
    private final Set<Scope> zaob;
    private final Map<Api<?>, OptionalApiSettings> zaoc;
    private final SignInOptions zaod;
    private Integer zaoe;
    private final Account zax;

    public static final class OptionalApiSettings {
        public final Set<Scope> mScopes;

        public OptionalApiSettings(Set<Scope> set) {
            Preconditions.checkNotNull(set);
            this.mScopes = Collections.unmodifiableSet(set);
        }
    }

    @KeepForSdk
    public static ClientSettings createDefault(Context context) {
        return new GoogleApiClient.Builder(context).buildClientSettings();
    }

    @KeepForSdk
    public ClientSettings(Account account, Set<Scope> set, Map<Api<?>, OptionalApiSettings> map, int i, View view, String str, String str2, SignInOptions signInOptions) {
        this(account, set, map, i, view, str, str2, signInOptions, false);
    }

    @KeepForSdk
    public static final class Builder {
        private int zabt = 0;
        private View zabu;
        private String zabv;
        private String zabw;
        private boolean zaby;
        private Map<Api<?>, OptionalApiSettings> zaoc;
        private SignInOptions zaod = SignInOptions.DEFAULT;
        private b<Scope> zaof;
        private Account zax;

        public final Builder setAccount(Account account) {
            this.zax = account;
            return this;
        }

        public final Builder addRequiredScope(Scope scope) {
            if (this.zaof == null) {
                this.zaof = new b<>();
            }
            this.zaof.add(scope);
            return this;
        }

        public final Builder addAllRequiredScopes(Collection<Scope> collection) {
            if (this.zaof == null) {
                this.zaof = new b<>();
            }
            this.zaof.addAll(collection);
            return this;
        }

        public final Builder setOptionalApiSettingsMap(Map<Api<?>, OptionalApiSettings> map) {
            this.zaoc = map;
            return this;
        }

        public final Builder setGravityForPopups(int i) {
            this.zabt = i;
            return this;
        }

        public final Builder setViewForPopups(View view) {
            this.zabu = view;
            return this;
        }

        @KeepForSdk
        public final Builder setRealClientPackageName(String str) {
            this.zabv = str;
            return this;
        }

        public final Builder setRealClientClassName(String str) {
            this.zabw = str;
            return this;
        }

        public final Builder setSignInOptions(SignInOptions signInOptions) {
            this.zaod = signInOptions;
            return this;
        }

        public final Builder enableSignInClientDisconnectFix() {
            this.zaby = true;
            return this;
        }

        @KeepForSdk
        public final ClientSettings build() {
            return new ClientSettings(this.zax, this.zaof, this.zaoc, this.zabt, this.zabu, this.zabv, this.zabw, this.zaod, this.zaby);
        }
    }

    public ClientSettings(Account account, Set<Scope> set, Map<Api<?>, OptionalApiSettings> map, int i, View view, String str, String str2, SignInOptions signInOptions, boolean z) {
        this.zax = account;
        this.zabr = set == null ? Collections.EMPTY_SET : Collections.unmodifiableSet(set);
        this.zaoc = map == null ? Collections.EMPTY_MAP : map;
        this.zabu = view;
        this.zabt = i;
        this.zabv = str;
        this.zabw = str2;
        this.zaod = signInOptions;
        this.zaby = z;
        HashSet hashSet = new HashSet(this.zabr);
        for (OptionalApiSettings optionalApiSettings : this.zaoc.values()) {
            hashSet.addAll(optionalApiSettings.mScopes);
        }
        this.zaob = Collections.unmodifiableSet(hashSet);
    }

    @KeepForSdk
    @Deprecated
    public final String getAccountName() {
        Account account = this.zax;
        if (account != null) {
            return account.name;
        }
        return null;
    }

    @KeepForSdk
    public final Account getAccount() {
        return this.zax;
    }

    @KeepForSdk
    public final Account getAccountOrDefault() {
        Account account = this.zax;
        if (account != null) {
            return account;
        }
        return new Account("<<default account>>", AccountType.GOOGLE);
    }

    @KeepForSdk
    public final int getGravityForPopups() {
        return this.zabt;
    }

    @KeepForSdk
    public final Set<Scope> getRequiredScopes() {
        return this.zabr;
    }

    @KeepForSdk
    public final Set<Scope> getAllRequestedScopes() {
        return this.zaob;
    }

    public final Map<Api<?>, OptionalApiSettings> getOptionalApiSettings() {
        return this.zaoc;
    }

    @KeepForSdk
    public final String getRealClientPackageName() {
        return this.zabv;
    }

    public final String getRealClientClassName() {
        return this.zabw;
    }

    @KeepForSdk
    public final View getViewForPopups() {
        return this.zabu;
    }

    public final SignInOptions getSignInOptions() {
        return this.zaod;
    }

    public final Integer getClientSessionId() {
        return this.zaoe;
    }

    public final void setClientSessionId(Integer num) {
        this.zaoe = num;
    }

    @KeepForSdk
    public final Set<Scope> getApplicableScopes(Api<?> api) {
        OptionalApiSettings optionalApiSettings = this.zaoc.get(api);
        if (optionalApiSettings == null || optionalApiSettings.mScopes.isEmpty()) {
            return this.zabr;
        }
        HashSet hashSet = new HashSet(this.zabr);
        hashSet.addAll(optionalApiSettings.mScopes);
        return hashSet;
    }

    public final boolean isSignInClientDisconnectFixEnabled() {
        return this.zaby;
    }
}
