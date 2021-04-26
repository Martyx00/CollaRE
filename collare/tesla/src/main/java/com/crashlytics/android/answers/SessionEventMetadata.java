package com.crashlytics.android.answers;

/* access modifiers changed from: package-private */
public final class SessionEventMetadata {
    public final String appBundleId;
    public final String appVersionCode;
    public final String appVersionName;
    public final String betaDeviceToken;
    public final String buildId;
    public final String deviceModel;
    public final String executionId;
    public final String installationId;
    public final Boolean limitAdTrackingEnabled;
    public final String osVersion;
    private String stringRepresentation;

    public SessionEventMetadata(String str, String str2, String str3, Boolean bool, String str4, String str5, String str6, String str7, String str8, String str9) {
        this.appBundleId = str;
        this.executionId = str2;
        this.installationId = str3;
        this.limitAdTrackingEnabled = bool;
        this.betaDeviceToken = str4;
        this.buildId = str5;
        this.osVersion = str6;
        this.deviceModel = str7;
        this.appVersionCode = str8;
        this.appVersionName = str9;
    }

    public String toString() {
        if (this.stringRepresentation == null) {
            this.stringRepresentation = "appBundleId=" + this.appBundleId + ", executionId=" + this.executionId + ", installationId=" + this.installationId + ", limitAdTrackingEnabled=" + this.limitAdTrackingEnabled + ", betaDeviceToken=" + this.betaDeviceToken + ", buildId=" + this.buildId + ", osVersion=" + this.osVersion + ", deviceModel=" + this.deviceModel + ", appVersionCode=" + this.appVersionCode + ", appVersionName=" + this.appVersionName;
        }
        return this.stringRepresentation;
    }
}
