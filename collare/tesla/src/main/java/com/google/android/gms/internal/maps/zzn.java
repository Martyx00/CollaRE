package com.google.android.gms.internal.maps;

import android.os.IBinder;
import android.os.IInterface;
import java.util.List;

public interface zzn extends IInterface {
    int getActiveLevelIndex();

    int getDefaultLevelIndex();

    List<IBinder> getLevels();

    boolean isUnderground();

    boolean zzb(zzn zzn);

    int zzj();
}
