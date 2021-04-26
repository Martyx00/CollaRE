package com.crashlytics.android.core;

class CreateReportRequest {
    public final String apiKey;
    public final Report report;

    public CreateReportRequest(String str, Report report2) {
        this.apiKey = str;
        this.report = report2;
    }
}
