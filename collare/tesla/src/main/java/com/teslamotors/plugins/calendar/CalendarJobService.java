package com.teslamotors.plugins.calendar;

import android.app.job.JobInfo;
import android.app.job.JobParameters;
import android.app.job.JobScheduler;
import android.app.job.JobService;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.provider.CalendarContract;
import android.util.Log;

public class CalendarJobService extends JobService {

    /* renamed from: a  reason: collision with root package name */
    private static final String f5462a = "CalendarJobService";

    /* renamed from: b  reason: collision with root package name */
    private a f5463b = null;

    public static void a(Context context) {
        if (Build.VERSION.SDK_INT >= 24) {
            JobInfo.Builder builder = new JobInfo.Builder(1001, new ComponentName(context, CalendarJobService.class));
            builder.setRequiredNetworkType(1);
            builder.addTriggerContentUri(new JobInfo.TriggerContentUri(CalendarContract.CONTENT_URI, 1));
            JobScheduler jobScheduler = (JobScheduler) context.getSystemService("jobscheduler");
            Log.i(f5462a, String.format("Scheduling calendar sync with %d pending jobs", Integer.valueOf(jobScheduler.getAllPendingJobs().size())));
            jobScheduler.schedule(builder.build());
        }
    }

    public boolean onStartJob(JobParameters jobParameters) {
        Intent b2 = CalendarSyncService.b(getApplicationContext(), new Intent());
        if (b2 == null) {
            return false;
        }
        Log.i(f5462a, "Starting calendar sync");
        this.f5463b = new a(b2, jobParameters);
        this.f5463b.execute(new Object[0]);
        return true;
    }

    public boolean onStopJob(JobParameters jobParameters) {
        a aVar = this.f5463b;
        if (aVar == null || aVar.getStatus().equals(AsyncTask.Status.FINISHED)) {
            return false;
        }
        this.f5463b.cancel(true);
        return true;
    }

    class a extends AsyncTask {

        /* renamed from: b  reason: collision with root package name */
        private final Intent f5465b;

        /* renamed from: c  reason: collision with root package name */
        private volatile Object f5466c = null;

        /* renamed from: d  reason: collision with root package name */
        private final JobParameters f5467d;

        public a(Intent intent, JobParameters jobParameters) {
            this.f5465b = intent;
            this.f5467d = jobParameters;
        }

        /* access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public Object doInBackground(Object[] objArr) {
            String b2 = CalendarSyncService.b(this.f5465b.getStringExtra("vid"), this.f5465b.getStringExtra("reason"), CalendarJobService.this.getApplicationContext(), Boolean.valueOf(this.f5465b.getBooleanExtra("force_sync", false)), Boolean.valueOf(this.f5465b.getBooleanExtra("sync_enabled", false)));
            Log.i(CalendarJobService.f5462a, String.format("Calendar sync finished with result: %s", b2));
            return b2;
        }

        /* access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(Object obj) {
            String str = (String) obj;
            if ("CALENDAR_SYNC_NO_ERROR".equalsIgnoreCase(str)) {
                CalendarJobService.a(CalendarJobService.this.getApplicationContext());
            }
            CalendarJobService.this.jobFinished(this.f5467d, "CALENDAR_SYNC_FAILED_TO_SEND".equalsIgnoreCase(str));
        }

        /* access modifiers changed from: protected */
        public void onCancelled() {
            CalendarJobService.a(CalendarJobService.this.getApplicationContext());
            Log.i(CalendarJobService.f5462a, String.format("Calendar sync cancelled", new Object[0]));
        }
    }
}
