package com.crashlytics.android.core;

import io.a.a.a.a.b.a;
import io.a.a.a.a.b.u;
import io.a.a.a.a.e.c;
import io.a.a.a.a.e.d;
import io.a.a.a.a.e.e;
import io.a.a.a.i;
import io.a.a.a.l;
import java.io.File;
import java.util.Map;

class DefaultCreateReportSpiCall extends a implements CreateReportSpiCall {
    static final String FILE_CONTENT_TYPE = "application/octet-stream";
    static final String FILE_PARAM = "report[file]";
    static final String IDENTIFIER_PARAM = "report[identifier]";
    static final String MULTI_FILE_PARAM = "report[file";

    public DefaultCreateReportSpiCall(i iVar, String str, String str2, e eVar) {
        super(iVar, str, str2, eVar, c.POST);
    }

    DefaultCreateReportSpiCall(i iVar, String str, String str2, e eVar, c cVar) {
        super(iVar, str, str2, eVar, cVar);
    }

    @Override // com.crashlytics.android.core.CreateReportSpiCall
    public boolean invoke(CreateReportRequest createReportRequest) {
        d applyMultipartDataTo = applyMultipartDataTo(applyHeadersTo(getHttpRequest(), createReportRequest), createReportRequest.report);
        l g = io.a.a.a.c.g();
        g.a(CrashlyticsCore.TAG, "Sending report to: " + getUrl());
        int b2 = applyMultipartDataTo.b();
        l g2 = io.a.a.a.c.g();
        g2.a(CrashlyticsCore.TAG, "Create report request ID: " + applyMultipartDataTo.b(a.HEADER_REQUEST_ID));
        l g3 = io.a.a.a.c.g();
        g3.a(CrashlyticsCore.TAG, "Result was: " + b2);
        return u.a(b2) == 0;
    }

    private d applyHeadersTo(d dVar, CreateReportRequest createReportRequest) {
        d a2 = dVar.a(a.HEADER_API_KEY, createReportRequest.apiKey).a(a.HEADER_CLIENT_TYPE, a.ANDROID_CLIENT_TYPE).a(a.HEADER_CLIENT_VERSION, this.kit.getVersion());
        for (Map.Entry<String, String> entry : createReportRequest.report.getCustomHeaders().entrySet()) {
            a2 = a2.a(entry);
        }
        return a2;
    }

    private d applyMultipartDataTo(d dVar, Report report) {
        dVar.e(IDENTIFIER_PARAM, report.getIdentifier());
        if (report.getFiles().length == 1) {
            io.a.a.a.c.g().a(CrashlyticsCore.TAG, "Adding single file " + report.getFileName() + " to report " + report.getIdentifier());
            return dVar.a(FILE_PARAM, report.getFileName(), FILE_CONTENT_TYPE, report.getFile());
        }
        File[] files = report.getFiles();
        int i = 0;
        for (File file : files) {
            io.a.a.a.c.g().a(CrashlyticsCore.TAG, "Adding file " + file.getName() + " to report " + report.getIdentifier());
            StringBuilder sb = new StringBuilder();
            sb.append(MULTI_FILE_PARAM);
            sb.append(i);
            sb.append("]");
            dVar.a(sb.toString(), file.getName(), FILE_CONTENT_TYPE, file);
            i++;
        }
        return dVar;
    }
}
