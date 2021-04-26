package com.crashlytics.android.core;

import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import io.a.a.a.a.b.h;
import io.a.a.a.c;
import io.a.a.a.l;
import java.io.File;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/* access modifiers changed from: package-private */
public class ReportUploader {
    static final Map<String, String> HEADER_INVALID_CLS_FILE = Collections.singletonMap("X-CRASHLYTICS-INVALID-SESSION", "1");
    private static final short[] RETRY_INTERVALS = {10, 20, 30, 60, 120, 300};
    private final String apiKey;
    private final CreateReportSpiCall createReportCall;
    private final Object fileAccessLock = new Object();
    private final HandlingExceptionCheck handlingExceptionCheck;
    private final ReportFilesProvider reportFilesProvider;
    private Thread uploadThread;

    /* access modifiers changed from: package-private */
    public interface HandlingExceptionCheck {
        boolean isHandlingException();
    }

    /* access modifiers changed from: package-private */
    public interface ReportFilesProvider {
        File[] getCompleteSessionFiles();

        File[] getInvalidSessionFiles();

        File[] getNativeReportFiles();
    }

    /* access modifiers changed from: package-private */
    public interface SendCheck {
        boolean canSendReports();
    }

    /* access modifiers changed from: package-private */
    public static final class AlwaysSendCheck implements SendCheck {
        @Override // com.crashlytics.android.core.ReportUploader.SendCheck
        public boolean canSendReports() {
            return true;
        }

        AlwaysSendCheck() {
        }
    }

    public ReportUploader(String str, CreateReportSpiCall createReportSpiCall, ReportFilesProvider reportFilesProvider2, HandlingExceptionCheck handlingExceptionCheck2) {
        if (createReportSpiCall != null) {
            this.createReportCall = createReportSpiCall;
            this.apiKey = str;
            this.reportFilesProvider = reportFilesProvider2;
            this.handlingExceptionCheck = handlingExceptionCheck2;
            return;
        }
        throw new IllegalArgumentException("createReportCall must not be null.");
    }

    public synchronized void uploadReports(float f, SendCheck sendCheck) {
        if (this.uploadThread != null) {
            c.g().a(CrashlyticsCore.TAG, "Report upload has already been started.");
            return;
        }
        this.uploadThread = new Thread(new Worker(f, sendCheck), "Crashlytics Report Uploader");
        this.uploadThread.start();
    }

    /* access modifiers changed from: package-private */
    public boolean isUploading() {
        return this.uploadThread != null;
    }

    /* access modifiers changed from: package-private */
    public boolean forceUpload(Report report) {
        boolean z;
        synchronized (this.fileAccessLock) {
            z = false;
            try {
                boolean invoke = this.createReportCall.invoke(new CreateReportRequest(this.apiKey, report));
                l g = c.g();
                StringBuilder sb = new StringBuilder();
                sb.append("Crashlytics report upload ");
                sb.append(invoke ? "complete: " : "FAILED: ");
                sb.append(report.getIdentifier());
                g.c(CrashlyticsCore.TAG, sb.toString());
                if (invoke) {
                    report.remove();
                    z = true;
                }
            } catch (Exception e) {
                l g2 = c.g();
                g2.e(CrashlyticsCore.TAG, "Error occurred sending report " + report, e);
            }
        }
        return z;
    }

    /* access modifiers changed from: package-private */
    public List<Report> findReports() {
        File[] completeSessionFiles;
        File[] invalidSessionFiles;
        File[] nativeReportFiles;
        c.g().a(CrashlyticsCore.TAG, "Checking for crash reports...");
        synchronized (this.fileAccessLock) {
            completeSessionFiles = this.reportFilesProvider.getCompleteSessionFiles();
            invalidSessionFiles = this.reportFilesProvider.getInvalidSessionFiles();
            nativeReportFiles = this.reportFilesProvider.getNativeReportFiles();
        }
        LinkedList linkedList = new LinkedList();
        if (completeSessionFiles != null) {
            for (File file : completeSessionFiles) {
                c.g().a(CrashlyticsCore.TAG, "Found crash report " + file.getPath());
                linkedList.add(new SessionReport(file));
            }
        }
        HashMap hashMap = new HashMap();
        if (invalidSessionFiles != null) {
            for (File file2 : invalidSessionFiles) {
                String sessionIdFromSessionFile = CrashlyticsController.getSessionIdFromSessionFile(file2);
                if (!hashMap.containsKey(sessionIdFromSessionFile)) {
                    hashMap.put(sessionIdFromSessionFile, new LinkedList());
                }
                ((List) hashMap.get(sessionIdFromSessionFile)).add(file2);
            }
        }
        for (String str : hashMap.keySet()) {
            c.g().a(CrashlyticsCore.TAG, "Found invalid session: " + str);
            List list = (List) hashMap.get(str);
            linkedList.add(new InvalidSessionReport(str, (File[]) list.toArray(new File[list.size()])));
        }
        if (nativeReportFiles != null) {
            for (File file3 : nativeReportFiles) {
                linkedList.add(new NativeSessionReport(file3));
            }
        }
        if (linkedList.isEmpty()) {
            c.g().a(CrashlyticsCore.TAG, "No reports found.");
        }
        return linkedList;
    }

    private class Worker extends h {
        private final float delay;
        private final SendCheck sendCheck;

        Worker(float f, SendCheck sendCheck2) {
            this.delay = f;
            this.sendCheck = sendCheck2;
        }

        @Override // io.a.a.a.a.b.h
        public void onRun() {
            try {
                attemptUploadWithRetry();
            } catch (Exception e) {
                c.g().e(CrashlyticsCore.TAG, "An unexpected error occurred while attempting to upload crash reports.", e);
            }
            ReportUploader.this.uploadThread = null;
        }

        private void attemptUploadWithRetry() {
            l g = c.g();
            g.a(CrashlyticsCore.TAG, "Starting report processing in " + this.delay + " second(s)...");
            float f = this.delay;
            if (f > BitmapDescriptorFactory.HUE_RED) {
                try {
                    Thread.sleep((long) (f * 1000.0f));
                } catch (InterruptedException unused) {
                    Thread.currentThread().interrupt();
                    return;
                }
            }
            List<Report> findReports = ReportUploader.this.findReports();
            if (!ReportUploader.this.handlingExceptionCheck.isHandlingException()) {
                if (findReports.isEmpty() || this.sendCheck.canSendReports()) {
                    int i = 0;
                    while (!findReports.isEmpty() && !ReportUploader.this.handlingExceptionCheck.isHandlingException()) {
                        l g2 = c.g();
                        g2.a(CrashlyticsCore.TAG, "Attempting to send " + findReports.size() + " report(s)");
                        for (Report report : findReports) {
                            ReportUploader.this.forceUpload(report);
                        }
                        findReports = ReportUploader.this.findReports();
                        if (!findReports.isEmpty()) {
                            int i2 = i + 1;
                            long j = (long) ReportUploader.RETRY_INTERVALS[Math.min(i, ReportUploader.RETRY_INTERVALS.length - 1)];
                            l g3 = c.g();
                            g3.a(CrashlyticsCore.TAG, "Report submisson: scheduling delayed retry in " + j + " seconds");
                            try {
                                Thread.sleep(j * 1000);
                                i = i2;
                            } catch (InterruptedException unused2) {
                                Thread.currentThread().interrupt();
                                return;
                            }
                        }
                    }
                    return;
                }
                l g4 = c.g();
                g4.a(CrashlyticsCore.TAG, "User declined to send. Removing " + findReports.size() + " Report(s).");
                for (Report report2 : findReports) {
                    report2.remove();
                }
            }
        }
    }
}
