package com.facebook.react.common;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import java.util.concurrent.TimeUnit;

/* compiled from: ShakeDetector */
public class f implements SensorEventListener {

    /* renamed from: a  reason: collision with root package name */
    private static final long f2609a = TimeUnit.NANOSECONDS.convert(20, TimeUnit.MILLISECONDS);

    /* renamed from: b  reason: collision with root package name */
    private static final float f2610b = ((float) TimeUnit.NANOSECONDS.convert(3, TimeUnit.SECONDS));

    /* renamed from: c  reason: collision with root package name */
    private float f2611c;

    /* renamed from: d  reason: collision with root package name */
    private float f2612d;
    private float e;
    private final a f;
    private long g;
    private int h;
    private long i;
    private int j;

    /* compiled from: ShakeDetector */
    public interface a {
        void a();
    }

    public void onAccuracyChanged(Sensor sensor, int i2) {
    }

    private void a() {
        this.h = 0;
        this.f2611c = BitmapDescriptorFactory.HUE_RED;
        this.f2612d = BitmapDescriptorFactory.HUE_RED;
        this.e = BitmapDescriptorFactory.HUE_RED;
    }

    private boolean a(float f2) {
        return Math.abs(f2) > 13.042845f;
    }

    private void a(long j2) {
        this.i = j2;
        this.h++;
    }

    public void onSensorChanged(SensorEvent sensorEvent) {
        if (sensorEvent.timestamp - this.g >= f2609a) {
            float f2 = sensorEvent.values[0];
            float f3 = sensorEvent.values[1];
            float f4 = sensorEvent.values[2] - 9.80665f;
            this.g = sensorEvent.timestamp;
            if (a(f2) && this.f2611c * f2 <= BitmapDescriptorFactory.HUE_RED) {
                a(sensorEvent.timestamp);
                this.f2611c = f2;
            } else if (a(f3) && this.f2612d * f3 <= BitmapDescriptorFactory.HUE_RED) {
                a(sensorEvent.timestamp);
                this.f2612d = f3;
            } else if (a(f4) && this.e * f4 <= BitmapDescriptorFactory.HUE_RED) {
                a(sensorEvent.timestamp);
                this.e = f4;
            }
            b(sensorEvent.timestamp);
        }
    }

    private void b(long j2) {
        if (this.h >= this.j * 8) {
            a();
            this.f.a();
        }
        if (((float) (j2 - this.i)) > f2610b) {
            a();
        }
    }
}
