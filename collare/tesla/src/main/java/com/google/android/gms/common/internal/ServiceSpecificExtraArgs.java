package com.google.android.gms.common.internal;

import com.google.android.gms.common.annotation.KeepForSdk;

@KeepForSdk
public final class ServiceSpecificExtraArgs {

    @KeepForSdk
    public interface CastExtraArgs {
        @KeepForSdk
        public static final String LISTENER = "listener";
    }

    @KeepForSdk
    public interface GamesExtraArgs {
        @KeepForSdk
        public static final String DESIRED_LOCALE = "com.google.android.gms.games.key.desiredLocale";
        @KeepForSdk
        public static final String GAME_PACKAGE_NAME = "com.google.android.gms.games.key.gamePackageName";
        @KeepForSdk
        public static final String SIGNIN_OPTIONS = "com.google.android.gms.games.key.signInOptions";
        @KeepForSdk
        public static final String WINDOW_TOKEN = "com.google.android.gms.games.key.popupWindowToken";
    }

    @KeepForSdk
    public interface PlusExtraArgs {
        @KeepForSdk
        public static final String PLUS_AUTH_PACKAGE = "auth_package";
    }

    private ServiceSpecificExtraArgs() {
    }
}
