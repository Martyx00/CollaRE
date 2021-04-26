package com.crashlytics.android.core;

import com.crashlytics.android.core.Report;
import io.a.a.a.c;
import java.io.File;
import java.util.Map;

/* access modifiers changed from: package-private */
public class NativeSessionReport implements Report {
    private final File reportDirectory;

    @Override // com.crashlytics.android.core.Report
    public Map<String, String> getCustomHeaders() {
        return null;
    }

    @Override // com.crashlytics.android.core.Report
    public File getFile() {
        return null;
    }

    @Override // com.crashlytics.android.core.Report
    public String getFileName() {
        return null;
    }

    public NativeSessionReport(File file) {
        this.reportDirectory = file;
    }

    @Override // com.crashlytics.android.core.Report
    public void remove() {
        File[] files = getFiles();
        for (File file : files) {
            c.g().a(CrashlyticsCore.TAG, "Removing native report file at " + file.getPath());
            file.delete();
        }
        c.g().a(CrashlyticsCore.TAG, "Removing native report directory at " + this.reportDirectory);
        this.reportDirectory.delete();
    }

    @Override // com.crashlytics.android.core.Report
    public String getIdentifier() {
        return this.reportDirectory.getName();
    }

    @Override // com.crashlytics.android.core.Report
    public File[] getFiles() {
        return this.reportDirectory.listFiles();
    }

    @Override // com.crashlytics.android.core.Report
    public Report.Type getType() {
        return Report.Type.NATIVE;
    }
}
