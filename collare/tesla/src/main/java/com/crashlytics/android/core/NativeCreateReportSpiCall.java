package com.crashlytics.android.core;

import ezvcard.property.Kind;
import io.a.a.a.a.b.a;
import io.a.a.a.a.b.u;
import io.a.a.a.a.e.c;
import io.a.a.a.a.e.d;
import io.a.a.a.a.e.e;
import io.a.a.a.i;
import io.a.a.a.l;
import java.io.File;

class NativeCreateReportSpiCall extends a implements CreateReportSpiCall {
    private static final String APP_META_FILE_MULTIPART_PARAM = "app_meta_file";
    private static final String BINARY_IMAGES_FILE_MULTIPART_PARAM = "binary_images_file";
    private static final String DEVICE_META_FILE_MULTIPART_PARAM = "device_meta_file";
    private static final String GZIP_FILE_CONTENT_TYPE = "application/octet-stream";
    private static final String KEYS_FILE_MULTIPART_PARAM = "keys_file";
    private static final String LOGS_FILE_MULTIPART_PARAM = "logs_file";
    private static final String METADATA_FILE_MULTIPART_PARAM = "crash_meta_file";
    private static final String MINIDUMP_FILE_MULTIPART_PARAM = "minidump_file";
    private static final String OS_META_FILE_MULTIPART_PARAM = "os_meta_file";
    private static final String REPORT_IDENTIFIER_PARAM = "report_id";
    private static final String SESSION_META_FILE_MULTIPART_PARAM = "session_meta_file";
    private static final String USER_META_FILE_MULTIPART_PARAM = "user_meta_file";

    public NativeCreateReportSpiCall(i iVar, String str, String str2, e eVar) {
        super(iVar, str, str2, eVar, c.POST);
    }

    @Override // com.crashlytics.android.core.CreateReportSpiCall
    public boolean invoke(CreateReportRequest createReportRequest) {
        d applyMultipartDataTo = applyMultipartDataTo(applyHeadersTo(getHttpRequest(), createReportRequest.apiKey), createReportRequest.report);
        l g = io.a.a.a.c.g();
        g.a(CrashlyticsCore.TAG, "Sending report to: " + getUrl());
        int b2 = applyMultipartDataTo.b();
        l g2 = io.a.a.a.c.g();
        g2.a(CrashlyticsCore.TAG, "Result was: " + b2);
        return u.a(b2) == 0;
    }

    private d applyHeadersTo(d dVar, String str) {
        dVar.a(a.HEADER_USER_AGENT, a.CRASHLYTICS_USER_AGENT + this.kit.getVersion()).a(a.HEADER_CLIENT_TYPE, a.ANDROID_CLIENT_TYPE).a(a.HEADER_CLIENT_VERSION, this.kit.getVersion()).a(a.HEADER_API_KEY, str);
        return dVar;
    }

    private d applyMultipartDataTo(d dVar, Report report) {
        dVar.e(REPORT_IDENTIFIER_PARAM, report.getIdentifier());
        File[] files = report.getFiles();
        for (File file : files) {
            if (file.getName().equals("minidump")) {
                dVar.a(MINIDUMP_FILE_MULTIPART_PARAM, file.getName(), GZIP_FILE_CONTENT_TYPE, file);
            } else if (file.getName().equals("metadata")) {
                dVar.a(METADATA_FILE_MULTIPART_PARAM, file.getName(), GZIP_FILE_CONTENT_TYPE, file);
            } else if (file.getName().equals("binaryImages")) {
                dVar.a(BINARY_IMAGES_FILE_MULTIPART_PARAM, file.getName(), GZIP_FILE_CONTENT_TYPE, file);
            } else if (file.getName().equals("session")) {
                dVar.a(SESSION_META_FILE_MULTIPART_PARAM, file.getName(), GZIP_FILE_CONTENT_TYPE, file);
            } else if (file.getName().equals("app")) {
                dVar.a(APP_META_FILE_MULTIPART_PARAM, file.getName(), GZIP_FILE_CONTENT_TYPE, file);
            } else if (file.getName().equals(Kind.DEVICE)) {
                dVar.a(DEVICE_META_FILE_MULTIPART_PARAM, file.getName(), GZIP_FILE_CONTENT_TYPE, file);
            } else if (file.getName().equals("os")) {
                dVar.a(OS_META_FILE_MULTIPART_PARAM, file.getName(), GZIP_FILE_CONTENT_TYPE, file);
            } else if (file.getName().equals("user")) {
                dVar.a(USER_META_FILE_MULTIPART_PARAM, file.getName(), GZIP_FILE_CONTENT_TYPE, file);
            } else if (file.getName().equals("logs")) {
                dVar.a(LOGS_FILE_MULTIPART_PARAM, file.getName(), GZIP_FILE_CONTENT_TYPE, file);
            } else if (file.getName().equals("keys")) {
                dVar.a(KEYS_FILE_MULTIPART_PARAM, file.getName(), GZIP_FILE_CONTENT_TYPE, file);
            }
        }
        return dVar;
    }
}
