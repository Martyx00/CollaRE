package com.crashlytics.android.core;

import com.crashlytics.android.core.Report;
import io.a.a.a.c;
import java.io.File;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* access modifiers changed from: package-private */
public class InvalidSessionReport implements Report {
    private final Map<String, String> customHeaders = new HashMap(ReportUploader.HEADER_INVALID_CLS_FILE);
    private final File[] files;
    private final String identifier;

    public InvalidSessionReport(String str, File[] fileArr) {
        this.files = fileArr;
        this.identifier = str;
    }

    @Override // com.crashlytics.android.core.Report
    public String getFileName() {
        return this.files[0].getName();
    }

    @Override // com.crashlytics.android.core.Report
    public String getIdentifier() {
        return this.identifier;
    }

    @Override // com.crashlytics.android.core.Report
    public File getFile() {
        return this.files[0];
    }

    @Override // com.crashlytics.android.core.Report
    public File[] getFiles() {
        return this.files;
    }

    @Override // com.crashlytics.android.core.Report
    public Map<String, String> getCustomHeaders() {
        return Collections.unmodifiableMap(this.customHeaders);
    }

    @Override // com.crashlytics.android.core.Report
    public void remove() {
        File[] fileArr = this.files;
        for (File file : fileArr) {
            c.g().a(CrashlyticsCore.TAG, "Removing invalid report file at " + file.getPath());
            file.delete();
        }
    }

    @Override // com.crashlytics.android.core.Report
    public Report.Type getType() {
        return Report.Type.JAVA;
    }
}
