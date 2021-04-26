package com.google.android.gms.common;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Preconditions;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@KeepForSdk
public class BlockingServiceConnection implements ServiceConnection {
    private boolean zze = false;
    private final BlockingQueue<IBinder> zzf = new LinkedBlockingQueue();

    public void onServiceDisconnected(ComponentName componentName) {
    }

    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        this.zzf.add(iBinder);
    }

    @KeepForSdk
    public IBinder getServiceWithTimeout(long j, TimeUnit timeUnit) {
        Preconditions.checkNotMainThread("BlockingServiceConnection.getServiceWithTimeout() called on main thread");
        if (!this.zze) {
            this.zze = true;
            IBinder poll = this.zzf.poll(j, timeUnit);
            if (poll != null) {
                return poll;
            }
            throw new TimeoutException("Timed out waiting for the service connection");
        }
        throw new IllegalStateException("Cannot call get on this connection more than once");
    }

    @KeepForSdk
    public IBinder getService() {
        Preconditions.checkNotMainThread("BlockingServiceConnection.getService() called on main thread");
        if (!this.zze) {
            this.zze = true;
            return this.zzf.take();
        }
        throw new IllegalStateException("Cannot call get on this connection more than once");
    }
}
