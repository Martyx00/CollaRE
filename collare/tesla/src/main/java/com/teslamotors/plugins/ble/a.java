package com.teslamotors.plugins.ble;

import android.os.Build;
import android.os.ParcelUuid;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/* compiled from: BLEConstants */
public class a {

    /* renamed from: a  reason: collision with root package name */
    static final boolean f5367a = (Build.VERSION.SDK_INT >= 26);

    /* renamed from: b  reason: collision with root package name */
    public static final UUID f5368b = UUID.fromString("00000211-b2d1-43f0-9b88-960cebf8b91e");

    /* renamed from: c  reason: collision with root package name */
    public static final ParcelUuid f5369c = ParcelUuid.fromString("00001122-0000-1000-8000-00805F9B34FB");

    /* renamed from: d  reason: collision with root package name */
    public static final UUID f5370d = UUID.fromString("00000212-b2d1-43f0-9b88-960cebf8b91e");
    public static final UUID e = UUID.fromString("00000213-b2d1-43f0-9b88-960cebf8b91e");
    public static final UUID f = UUID.fromString("00000214-b2d1-43f0-9b88-960cebf8b91e");
    public static final UUID g = UUID.fromString("00002902-0000-1000-8000-00805f9b34fb");
    public static final byte[] h = {2};
    public static final SimpleDateFormat i = new SimpleDateFormat("MM/dd/yy HH:mm:ss.SSS");
    public static final Set<String> j = new HashSet(Arrays.asList("C", "R", "D", "P"));
    public static final Set<String> k = new HashSet(Arrays.asList("C"));
}
