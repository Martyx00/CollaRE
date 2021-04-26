package com.crashlytics.android.core;

import android.content.Context;
import android.os.Bundle;
import io.a.a.a.c;
import io.a.a.a.l;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import org.json.JSONException;
import org.json.JSONObject;

/* access modifiers changed from: package-private */
public class DefaultAppMeasurementEventListenerRegistrar implements AppMeasurementEventListenerRegistrar {
    private static final String ANALYTIC_CLASS = "com.google.android.gms.measurement.AppMeasurement";
    private static final String ANALYTIC_CLASS_ON_EVENT_LISTENER = "com.google.android.gms.measurement.AppMeasurement$OnEventListener";
    private static final String CRASH_ORIGIN = "crash";
    private static final String GET_INSTANCE_METHOD = "getInstance";
    private static final String NAME = "name";
    private static final String PARAMETERS = "parameters";
    private static final String REGISTER_METHOD = "registerOnMeasurementEventListener";
    private final CrashlyticsCore crashlyticsCore;

    static AppMeasurementEventListenerRegistrar instanceFrom(CrashlyticsCore crashlyticsCore2) {
        return new DefaultAppMeasurementEventListenerRegistrar(crashlyticsCore2);
    }

    private DefaultAppMeasurementEventListenerRegistrar(CrashlyticsCore crashlyticsCore2) {
        this.crashlyticsCore = crashlyticsCore2;
    }

    @Override // com.crashlytics.android.core.AppMeasurementEventListenerRegistrar
    public boolean register() {
        Class<?> cls = getClass(ANALYTIC_CLASS);
        if (cls == null) {
            c.g().d(CrashlyticsCore.TAG, "Firebase Analytics is not present; you will not see automatic logging of events before a crash occurs.");
            return false;
        }
        Object instance = getInstance(cls);
        if (instance != null) {
            return invoke(cls, instance, REGISTER_METHOD);
        }
        c.g().d(CrashlyticsCore.TAG, "Could not create an instance of Firebase Analytics.");
        return false;
    }

    private Class<?> getClass(String str) {
        try {
            return this.crashlyticsCore.getContext().getClassLoader().loadClass(str);
        } catch (Exception unused) {
            return null;
        }
    }

    private Object getInstance(Class<?> cls) {
        try {
            return cls.getDeclaredMethod(GET_INSTANCE_METHOD, Context.class).invoke(cls, this.crashlyticsCore.getContext());
        } catch (Exception e) {
            c.g().a(CrashlyticsCore.TAG, "Could not get instance of com.google.android.gms.measurement.AppMeasurement", e);
            return null;
        }
    }

    private boolean invoke(Class<?> cls, Object obj, String str) {
        Class<?> cls2 = getClass(ANALYTIC_CLASS_ON_EVENT_LISTENER);
        if (cls2 == null) {
            c.g().a(CrashlyticsCore.TAG, "Could not get class com.google.android.gms.measurement.AppMeasurement$OnEventListener");
            return false;
        }
        try {
            cls.getDeclaredMethod(str, cls2).invoke(obj, onEventListenerProxy(cls2));
            return true;
        } catch (NoSuchMethodException e) {
            l g = c.g();
            g.d(CrashlyticsCore.TAG, "Expected method missing: " + str, e);
            return false;
        } catch (InvocationTargetException e2) {
            l g2 = c.g();
            g2.d(CrashlyticsCore.TAG, "Cannot invoke method: " + str, e2);
            return false;
        } catch (IllegalAccessException e3) {
            l g3 = c.g();
            g3.d(CrashlyticsCore.TAG, "Cannot access method: " + str, e3);
            return false;
        }
    }

    private Object onEventListenerProxy(Class cls) {
        return Proxy.newProxyInstance(this.crashlyticsCore.getContext().getClassLoader(), new Class[]{cls}, new InvocationHandler() {
            /* class com.crashlytics.android.core.DefaultAppMeasurementEventListenerRegistrar.AnonymousClass1 */

            @Override // java.lang.reflect.InvocationHandler
            public Object invoke(Object obj, Method method, Object[] objArr) {
                if (objArr.length == 4) {
                    String str = (String) objArr[0];
                    String str2 = (String) objArr[1];
                    Bundle bundle = (Bundle) objArr[2];
                    if (str == null || str.equals("crash")) {
                        return null;
                    }
                    DefaultAppMeasurementEventListenerRegistrar.writeEventToUserLog(DefaultAppMeasurementEventListenerRegistrar.this.crashlyticsCore, str2, bundle);
                    return null;
                }
                throw new RuntimeException("Unexpected AppMeasurement.OnEventListener signature");
            }
        });
    }

    /* access modifiers changed from: private */
    public static void writeEventToUserLog(CrashlyticsCore crashlyticsCore2, String str, Bundle bundle) {
        try {
            crashlyticsCore2.log("$A$:" + serializeEvent(str, bundle));
        } catch (JSONException unused) {
            l g = c.g();
            g.d(CrashlyticsCore.TAG, "Unable to serialize Firebase Analytics event; " + str);
        }
    }

    private static String serializeEvent(String str, Bundle bundle) {
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = new JSONObject();
        for (String str2 : bundle.keySet()) {
            jSONObject2.put(str2, bundle.get(str2));
        }
        jSONObject.put(NAME, str);
        jSONObject.put(PARAMETERS, jSONObject2);
        return jSONObject.toString();
    }
}
