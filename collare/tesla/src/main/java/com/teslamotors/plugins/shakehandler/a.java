package com.teslamotors.plugins.shakehandler;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import com.facebook.react.common.f;

/* compiled from: ShakeDetector */
public class a implements SensorEventListener {

    /* renamed from: a  reason: collision with root package name */
    private static final String f5634a = "a";

    /* renamed from: b  reason: collision with root package name */
    private final f.a f5635b;

    /* renamed from: c  reason: collision with root package name */
    private SensorManager f5636c;

    /* renamed from: d  reason: collision with root package name */
    private long f5637d;
    private int e;
    private double[] f;
    private long[] g;

    public void onAccuracyChanged(Sensor sensor, int i) {
    }

    public a(f.a aVar) {
        this.f5635b = aVar;
    }

    public void a(SensorManager sensorManager) {
        com.facebook.i.a.a.a(sensorManager);
        Sensor defaultSensor = sensorManager.getDefaultSensor(1);
        if (defaultSensor != null) {
            this.f5636c = sensorManager;
            this.f5637d = -1;
            this.e = 0;
            this.f = new double[25];
            this.g = new long[25];
            this.f5636c.registerListener(this, defaultSensor, 2);
        }
    }

    public void a() {
        SensorManager sensorManager = this.f5636c;
        if (sensorManager != null) {
            sensorManager.unregisterListener(this);
            this.f5636c = null;
        }
    }

    public void onSensorChanged(SensorEvent sensorEvent) {
        long j = (long) (((double) sensorEvent.timestamp) / 1000000.0d);
        if (j - this.f5637d >= 20) {
            com.facebook.i.a.a.a(this.g);
            com.facebook.i.a.a.a(this.f);
            float f2 = sensorEvent.values[0];
            float f3 = sensorEvent.values[1];
            float f4 = sensorEvent.values[2];
            this.f5637d = j;
            long[] jArr = this.g;
            int i = this.e;
            jArr[i] = j;
            this.f[i] = Math.sqrt((double) ((f2 * f2) + (f3 * f3) + (f4 * f4)));
            a(j);
            this.e = (this.e + 1) % 25;
        }
    }

    private void a(long j) {
        com.facebook.i.a.a.a(this.g);
        com.facebook.i.a.a.a(this.f);
        int i = 0;
        int i2 = 0;
        for (int i3 = 0; i3 < 25; i3++) {
            int i4 = ((this.e - i3) + 25) % 25;
            if (j - this.g[i4] < 500) {
                i2++;
                if (this.f[i4] >= 25.0d) {
                    i++;
                }
            }
        }
        if (((double) i) / ((double) i2) > 0.5d) {
            this.f5635b.a();
        }
    }
}
