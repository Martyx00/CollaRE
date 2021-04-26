package com.crashlytics.android.answers;

import io.a.a.a.a.b.a;
import io.a.a.a.a.b.u;
import io.a.a.a.a.d.f;
import io.a.a.a.a.e.c;
import io.a.a.a.a.e.d;
import io.a.a.a.a.e.e;
import io.a.a.a.i;
import io.a.a.a.l;
import java.io.File;
import java.util.List;

/* access modifiers changed from: package-private */
public class SessionAnalyticsFilesSender extends a implements f {
    static final String FILE_CONTENT_TYPE = "application/vnd.crashlytics.android.events";
    static final String FILE_PARAM_NAME = "session_analytics_file_";
    private final String apiKey;

    public SessionAnalyticsFilesSender(i iVar, String str, String str2, e eVar, String str3) {
        super(iVar, str, str2, eVar, c.POST);
        this.apiKey = str3;
    }

    @Override // io.a.a.a.a.d.f
    public boolean send(List<File> list) {
        d a2 = getHttpRequest().a(a.HEADER_CLIENT_TYPE, a.ANDROID_CLIENT_TYPE).a(a.HEADER_CLIENT_VERSION, this.kit.getVersion()).a(a.HEADER_API_KEY, this.apiKey);
        int i = 0;
        for (File file : list) {
            a2.a(FILE_PARAM_NAME + i, file.getName(), FILE_CONTENT_TYPE, file);
            i++;
        }
        l g = io.a.a.a.c.g();
        g.a(Answers.TAG, "Sending " + list.size() + " analytics files to " + getUrl());
        int b2 = a2.b();
        l g2 = io.a.a.a.c.g();
        g2.a(Answers.TAG, "Response code for analytics file send is " + b2);
        if (u.a(b2) == 0) {
            return true;
        }
        return false;
    }
}
