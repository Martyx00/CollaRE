package com.crashlytics.android.core;

import com.crashlytics.android.core.Report;
import io.a.a.a.c;
import io.a.a.a.l;
import java.io.File;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* access modifiers changed from: package-private */
public class SessionReport implements Report {
    private final Map<String, String> customHeaders;
    private final File file;
    private final File[] files;

    public SessionReport(File file2) {
        this(file2, Collections.emptyMap());
    }

    public SessionReport(File file2, Map<String, String> map) {
        this.file = file2;
        this.files = new File[]{file2};
        this.customHeaders = new HashMap(map);
        if (this.file.length() == 0) {
            this.customHeaders.putAll(ReportUploader.HEADER_INVALID_CLS_FILE);
        }
    }

    @Override // com.crashlytics.android.core.Report
    public File getFile() {
        return this.file;
    }

    @Override // com.crashlytics.android.core.Report
    public File[] getFiles() {
        return this.files;
    }

    @Override // com.crashlytics.android.core.Report
    public String getFileName() {
        return getFile().getName();
    }

    @Override // com.crashlytics.android.core.Report
    public String getIdentifier() {
        String fileName = getFileName();
        return fileName.substring(0, fileName.lastIndexOf(46));
    }

    @Override // com.crashlytics.android.core.Report
    public Map<String, String> getCustomHeaders() {
        return Collections.unmodifiableMap(this.customHeaders);
    }

    @Override // com.crashlytics.android.core.Report
    public void remove() {
        l g = c.g();
        g.a(CrashlyticsCore.TAG, "Removing report at " + this.file.getPath());
        this.file.delete();
    }

    @Override // com.crashlytics.android.core.Report
    public Report.Type getType() {
        return Report.Type.JAVA;
    }
}
