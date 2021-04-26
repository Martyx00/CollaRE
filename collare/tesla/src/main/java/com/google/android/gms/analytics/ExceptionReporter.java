package com.google.android.gms.analytics;

import android.content.Context;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.measurement.zzcl;
import java.lang.Thread;
import java.util.ArrayList;

@VisibleForTesting
public class ExceptionReporter implements Thread.UncaughtExceptionHandler {
    private final Thread.UncaughtExceptionHandler zzqv;
    private final Tracker zzqw;
    private final Context zzqx;
    private ExceptionParser zzqy;
    private GoogleAnalytics zzqz;

    public ExceptionReporter(Tracker tracker, Thread.UncaughtExceptionHandler uncaughtExceptionHandler, Context context) {
        if (tracker == null) {
            throw new NullPointerException("tracker cannot be null");
        } else if (context != null) {
            this.zzqv = uncaughtExceptionHandler;
            this.zzqw = tracker;
            this.zzqy = new StandardExceptionParser(context, new ArrayList());
            this.zzqx = context.getApplicationContext();
            String valueOf = String.valueOf(uncaughtExceptionHandler == null ? "null" : uncaughtExceptionHandler.getClass().getName());
            zzcl.v(valueOf.length() != 0 ? "ExceptionReporter created, original handler is ".concat(valueOf) : new String("ExceptionReporter created, original handler is "));
        } else {
            throw new NullPointerException("context cannot be null");
        }
    }

    public ExceptionParser getExceptionParser() {
        return this.zzqy;
    }

    public void setExceptionParser(ExceptionParser exceptionParser) {
        this.zzqy = exceptionParser;
    }

    public void uncaughtException(Thread thread, Throwable th) {
        String str = "UncaughtException";
        if (this.zzqy != null) {
            str = this.zzqy.getDescription(thread != null ? thread.getName() : null, th);
        }
        String valueOf = String.valueOf(str);
        zzcl.v(valueOf.length() != 0 ? "Reporting uncaught exception: ".concat(valueOf) : new String("Reporting uncaught exception: "));
        this.zzqw.send(new HitBuilders.ExceptionBuilder().setDescription(str).setFatal(true).build());
        if (this.zzqz == null) {
            this.zzqz = GoogleAnalytics.getInstance(this.zzqx);
        }
        GoogleAnalytics googleAnalytics = this.zzqz;
        googleAnalytics.dispatchLocalHits();
        googleAnalytics.zzh().zzby().zzbp();
        if (this.zzqv != null) {
            zzcl.v("Passing exception to the original handler");
            this.zzqv.uncaughtException(thread, th);
        }
    }

    /* access modifiers changed from: package-private */
    public final Thread.UncaughtExceptionHandler zzl() {
        return this.zzqv;
    }
}
