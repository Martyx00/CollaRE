package com.crashlytics.android.answers;

import android.content.Context;
import android.os.Bundle;
import java.lang.reflect.Method;

public class AppMeasurementEventLogger implements EventLogger {
    private static final String ANALYTIC_CLASS = "com.google.android.gms.measurement.AppMeasurement";
    private static final String GET_INSTANCE_METHOD = "getInstance";
    private static final String LOG_METHOD = "logEventInternal";
    private final Object logEventInstance;
    private final Method logEventMethod;

    public static EventLogger getEventLogger(Context context) {
        Object instance;
        Method logEventMethod2;
        Class cls = getClass(context);
        if (cls == null || (instance = getInstance(context, cls)) == null || (logEventMethod2 = getLogEventMethod(context, cls)) == null) {
            return null;
        }
        return new AppMeasurementEventLogger(instance, logEventMethod2);
    }

    private static Class getClass(Context context) {
        try {
            return context.getClassLoader().loadClass(ANALYTIC_CLASS);
        } catch (Exception unused) {
            return null;
        }
    }

    private static Object getInstance(Context context, Class cls) {
        try {
            return cls.getDeclaredMethod(GET_INSTANCE_METHOD, Context.class).invoke(cls, context);
        } catch (Exception unused) {
            return null;
        }
    }

    private static Method getLogEventMethod(Context context, Class cls) {
        try {
            return cls.getDeclaredMethod(LOG_METHOD, String.class, String.class, Bundle.class);
        } catch (Exception unused) {
            return null;
        }
    }

    public AppMeasurementEventLogger(Object obj, Method method) {
        this.logEventInstance = obj;
        this.logEventMethod = method;
    }

    @Override // com.crashlytics.android.answers.EventLogger
    public void logEvent(String str, Bundle bundle) {
        logEvent("fab", str, bundle);
    }

    @Override // com.crashlytics.android.answers.EventLogger
    public void logEvent(String str, String str2, Bundle bundle) {
        try {
            this.logEventMethod.invoke(this.logEventInstance, str, str2, bundle);
        } catch (Exception unused) {
        }
    }
}
