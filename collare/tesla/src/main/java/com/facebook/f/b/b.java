package com.facebook.f.b;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

/* compiled from: DraweeEventTracker */
public class b {

    /* renamed from: b  reason: collision with root package name */
    private static final b f1860b = new b();

    /* renamed from: c  reason: collision with root package name */
    private static boolean f1861c = true;

    /* renamed from: a  reason: collision with root package name */
    private final Queue<a> f1862a = new ArrayBlockingQueue(20);

    /* compiled from: DraweeEventTracker */
    public enum a {
        ON_SET_HIERARCHY,
        ON_CLEAR_HIERARCHY,
        ON_SET_CONTROLLER,
        ON_CLEAR_OLD_CONTROLLER,
        ON_CLEAR_CONTROLLER,
        ON_INIT_CONTROLLER,
        ON_ATTACH_CONTROLLER,
        ON_DETACH_CONTROLLER,
        ON_RELEASE_CONTROLLER,
        ON_DATASOURCE_SUBMIT,
        ON_DATASOURCE_RESULT,
        ON_DATASOURCE_RESULT_INT,
        ON_DATASOURCE_FAILURE,
        ON_DATASOURCE_FAILURE_INT,
        ON_HOLDER_ATTACH,
        ON_HOLDER_DETACH,
        ON_DRAWABLE_SHOW,
        ON_DRAWABLE_HIDE,
        ON_ACTIVITY_START,
        ON_ACTIVITY_STOP,
        ON_RUN_CLEAR_CONTROLLER,
        ON_SCHEDULE_CLEAR_CONTROLLER,
        ON_SAME_CONTROLLER_SKIPPED,
        ON_SUBMIT_CACHE_HIT
    }

    private b() {
    }

    public static b a() {
        return f1861c ? new b() : f1860b;
    }

    public void a(a aVar) {
        if (f1861c) {
            if (this.f1862a.size() + 1 > 20) {
                this.f1862a.poll();
            }
            this.f1862a.add(aVar);
        }
    }

    public String toString() {
        return this.f1862a.toString();
    }
}
