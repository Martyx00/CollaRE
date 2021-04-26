package com.google.android.gms.common.internal;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.support.v4.util.l;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.base.R;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.util.DeviceProperties;
import com.google.android.gms.common.wrappers.Wrappers;

public final class ConnectionErrorMessages {
    private static final l<String, String> zaog = new l<>();

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    public static String getErrorTitle(Context context, int i) {
        Resources resources = context.getResources();
        if (i != 20) {
            switch (i) {
                case 1:
                    return resources.getString(R.string.common_google_play_services_install_title);
                case 2:
                    return resources.getString(R.string.common_google_play_services_update_title);
                case 3:
                    return resources.getString(R.string.common_google_play_services_enable_title);
                case 4:
                case 6:
                    break;
                case 5:
                    Log.e("GoogleApiAvailability", "An invalid account was specified when connecting. Please provide a valid account.");
                    return zaa(context, "common_google_play_services_invalid_account_title");
                case 7:
                    Log.e("GoogleApiAvailability", "Network error occurred. Please retry request later.");
                    return zaa(context, "common_google_play_services_network_error_title");
                case 8:
                    Log.e("GoogleApiAvailability", "Internal error occurred. Please see logs for detailed information");
                    return null;
                case 9:
                    Log.e("GoogleApiAvailability", "Google Play services is invalid. Cannot recover.");
                    return null;
                case 10:
                    Log.e("GoogleApiAvailability", "Developer error occurred. Please see logs for detailed information");
                    return null;
                case 11:
                    Log.e("GoogleApiAvailability", "The application is not licensed to the user.");
                    return null;
                default:
                    switch (i) {
                        case 16:
                            Log.e("GoogleApiAvailability", "One of the API components you attempted to connect to is not available.");
                            return null;
                        case 17:
                            Log.e("GoogleApiAvailability", "The specified account could not be signed in.");
                            return zaa(context, "common_google_play_services_sign_in_failed_title");
                        case 18:
                            break;
                        default:
                            StringBuilder sb = new StringBuilder(33);
                            sb.append("Unexpected error code ");
                            sb.append(i);
                            Log.e("GoogleApiAvailability", sb.toString());
                            return null;
                    }
            }
            return null;
        }
        Log.e("GoogleApiAvailability", "The current user profile is restricted and could not use authenticated features.");
        return zaa(context, "common_google_play_services_restricted_profile_title");
    }

    public static String getErrorNotificationTitle(Context context, int i) {
        String str;
        if (i == 6) {
            str = zaa(context, "common_google_play_services_resolution_required_title");
        } else {
            str = getErrorTitle(context, i);
        }
        return str == null ? context.getResources().getString(R.string.common_google_play_services_notification_ticker) : str;
    }

    public static String getErrorMessage(Context context, int i) {
        Resources resources = context.getResources();
        String appName = getAppName(context);
        if (i == 5) {
            return zaa(context, "common_google_play_services_invalid_account_text", appName);
        }
        if (i == 7) {
            return zaa(context, "common_google_play_services_network_error_text", appName);
        }
        if (i == 9) {
            return resources.getString(R.string.common_google_play_services_unsupported_text, appName);
        } else if (i == 20) {
            return zaa(context, "common_google_play_services_restricted_profile_text", appName);
        } else {
            switch (i) {
                case 1:
                    return resources.getString(R.string.common_google_play_services_install_text, appName);
                case 2:
                    if (DeviceProperties.isWearableWithoutPlayStore(context)) {
                        return resources.getString(R.string.common_google_play_services_wear_update_text);
                    }
                    return resources.getString(R.string.common_google_play_services_update_text, appName);
                case 3:
                    return resources.getString(R.string.common_google_play_services_enable_text, appName);
                default:
                    switch (i) {
                        case 16:
                            return zaa(context, "common_google_play_services_api_unavailable_text", appName);
                        case 17:
                            return zaa(context, "common_google_play_services_sign_in_failed_text", appName);
                        case 18:
                            return resources.getString(R.string.common_google_play_services_updating_text, appName);
                        default:
                            return resources.getString(com.google.android.gms.common.R.string.common_google_play_services_unknown_issue, appName);
                    }
            }
        }
    }

    public static String getErrorNotificationMessage(Context context, int i) {
        if (i == 6) {
            return zaa(context, "common_google_play_services_resolution_required_text", getAppName(context));
        }
        return getErrorMessage(context, i);
    }

    public static String getErrorDialogButtonMessage(Context context, int i) {
        Resources resources = context.getResources();
        switch (i) {
            case 1:
                return resources.getString(R.string.common_google_play_services_install_button);
            case 2:
                return resources.getString(R.string.common_google_play_services_update_button);
            case 3:
                return resources.getString(R.string.common_google_play_services_enable_button);
            default:
                return resources.getString(17039370);
        }
    }

    public static String getAppName(Context context) {
        String packageName = context.getPackageName();
        try {
            return Wrappers.packageManager(context).getApplicationLabel(packageName).toString();
        } catch (PackageManager.NameNotFoundException | NullPointerException unused) {
            String str = context.getApplicationInfo().name;
            return TextUtils.isEmpty(str) ? packageName : str;
        }
    }

    private static String zaa(Context context, String str, String str2) {
        Resources resources = context.getResources();
        String zaa = zaa(context, str);
        if (zaa == null) {
            zaa = resources.getString(com.google.android.gms.common.R.string.common_google_play_services_unknown_issue);
        }
        return String.format(resources.getConfiguration().locale, zaa, str2);
    }

    private static String zaa(Context context, String str) {
        synchronized (zaog) {
            String str2 = zaog.get(str);
            if (str2 != null) {
                return str2;
            }
            Resources remoteResource = GooglePlayServicesUtil.getRemoteResource(context);
            if (remoteResource == null) {
                return null;
            }
            int identifier = remoteResource.getIdentifier(str, "string", "com.google.android.gms");
            if (identifier == 0) {
                String valueOf = String.valueOf(str);
                Log.w("GoogleApiAvailability", valueOf.length() != 0 ? "Missing resource: ".concat(valueOf) : new String("Missing resource: "));
                return null;
            }
            String string = remoteResource.getString(identifier);
            if (TextUtils.isEmpty(string)) {
                String valueOf2 = String.valueOf(str);
                Log.w("GoogleApiAvailability", valueOf2.length() != 0 ? "Got empty resource: ".concat(valueOf2) : new String("Got empty resource: "));
                return null;
            }
            zaog.put(str, string);
            return string;
        }
    }

    public static String getDefaultNotificationChannelName(Context context) {
        return context.getResources().getString(R.string.common_google_play_services_notification_channel_name);
    }

    private ConnectionErrorMessages() {
    }
}
