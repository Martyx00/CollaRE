package com.google.android.gms.dynamite;

import android.content.Context;
import android.database.Cursor;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.CrashUtils;
import com.google.android.gms.common.util.DynamiteApi;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

@KeepForSdk
public final class DynamiteModule {
    @KeepForSdk
    public static final VersionPolicy PREFER_HIGHEST_OR_LOCAL_VERSION = new zzd();
    @KeepForSdk
    public static final VersionPolicy PREFER_HIGHEST_OR_LOCAL_VERSION_NO_FORCE_STAGING = new zze();
    @KeepForSdk
    public static final VersionPolicy PREFER_HIGHEST_OR_REMOTE_VERSION = new zzf();
    @KeepForSdk
    public static final VersionPolicy PREFER_LOCAL = new zzc();
    @KeepForSdk
    public static final VersionPolicy PREFER_REMOTE = new zzb();
    private static Boolean zzif = null;
    private static zzi zzig = null;
    private static zzk zzih = null;
    private static String zzii = null;
    private static int zzij = -1;
    private static final ThreadLocal<zza> zzik = new ThreadLocal<>();
    private static final VersionPolicy.zza zzil = new zza();
    private static final VersionPolicy zzim = new zzg();
    private final Context zzin;

    @DynamiteApi
    public static class DynamiteLoaderClassLoader {
        public static ClassLoader sClassLoader;
    }

    public interface VersionPolicy {

        public interface zza {
            int getLocalVersion(Context context, String str);

            int zza(Context context, String str, boolean z);
        }

        public static class zzb {
            public int zzir = 0;
            public int zzis = 0;
            public int zzit = 0;
        }

        zzb zza(Context context, String str, zza zza2);
    }

    /* access modifiers changed from: private */
    public static class zza {
        public Cursor zzio;

        private zza() {
        }

        /* synthetic */ zza(zza zza) {
            this();
        }
    }

    @KeepForSdk
    public static DynamiteModule load(Context context, VersionPolicy versionPolicy, String str) {
        zza zza2 = zzik.get();
        zza zza3 = new zza(null);
        zzik.set(zza3);
        try {
            VersionPolicy.zzb zza4 = versionPolicy.zza(context, str, zzil);
            int i = zza4.zzir;
            int i2 = zza4.zzis;
            StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 68 + String.valueOf(str).length());
            sb.append("Considering local module ");
            sb.append(str);
            sb.append(":");
            sb.append(i);
            sb.append(" and remote module ");
            sb.append(str);
            sb.append(":");
            sb.append(i2);
            Log.i("DynamiteModule", sb.toString());
            if (zza4.zzit == 0 || ((zza4.zzit == -1 && zza4.zzir == 0) || (zza4.zzit == 1 && zza4.zzis == 0))) {
                int i3 = zza4.zzir;
                int i4 = zza4.zzis;
                StringBuilder sb2 = new StringBuilder(91);
                sb2.append("No acceptable module found. Local version is ");
                sb2.append(i3);
                sb2.append(" and remote version is ");
                sb2.append(i4);
                sb2.append(".");
                throw new LoadingException(sb2.toString(), (zza) null);
            } else if (zza4.zzit == -1) {
                return zze(context, str);
            } else {
                if (zza4.zzit == 1) {
                    try {
                        DynamiteModule zza5 = zza(context, str, zza4.zzis);
                        if (zza3.zzio != null) {
                            zza3.zzio.close();
                        }
                        zzik.set(zza2);
                        return zza5;
                    } catch (LoadingException e) {
                        String valueOf = String.valueOf(e.getMessage());
                        Log.w("DynamiteModule", valueOf.length() != 0 ? "Failed to load remote module: ".concat(valueOf) : new String("Failed to load remote module: "));
                        if (zza4.zzir == 0 || versionPolicy.zza(context, str, new zzb(zza4.zzir, 0)).zzit != -1) {
                            throw new LoadingException("Remote load failed. No local fallback found.", e, null);
                        }
                        DynamiteModule zze = zze(context, str);
                        if (zza3.zzio != null) {
                            zza3.zzio.close();
                        }
                        zzik.set(zza2);
                        return zze;
                    }
                } else {
                    int i5 = zza4.zzit;
                    StringBuilder sb3 = new StringBuilder(47);
                    sb3.append("VersionPolicy returned invalid code:");
                    sb3.append(i5);
                    throw new LoadingException(sb3.toString(), (zza) null);
                }
            }
        } finally {
            if (zza3.zzio != null) {
                zza3.zzio.close();
            }
            zzik.set(zza2);
        }
    }

    @KeepForSdk
    public static class LoadingException extends Exception {
        private LoadingException(String str) {
            super(str);
        }

        private LoadingException(String str, Throwable th) {
            super(str, th);
        }

        /* synthetic */ LoadingException(String str, zza zza) {
            this(str);
        }

        /* synthetic */ LoadingException(String str, Throwable th, zza zza) {
            this(str, th);
        }
    }

    /* access modifiers changed from: private */
    public static class zzb implements VersionPolicy.zza {
        private final int zzip;
        private final int zziq = 0;

        public zzb(int i, int i2) {
            this.zzip = i;
        }

        @Override // com.google.android.gms.dynamite.DynamiteModule.VersionPolicy.zza
        public final int zza(Context context, String str, boolean z) {
            return 0;
        }

        @Override // com.google.android.gms.dynamite.DynamiteModule.VersionPolicy.zza
        public final int getLocalVersion(Context context, String str) {
            return this.zzip;
        }
    }

    @KeepForSdk
    public static int getLocalVersion(Context context, String str) {
        try {
            ClassLoader classLoader = context.getApplicationContext().getClassLoader();
            StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 61);
            sb.append("com.google.android.gms.dynamite.descriptors.");
            sb.append(str);
            sb.append(".ModuleDescriptor");
            Class<?> loadClass = classLoader.loadClass(sb.toString());
            Field declaredField = loadClass.getDeclaredField("MODULE_ID");
            Field declaredField2 = loadClass.getDeclaredField("MODULE_VERSION");
            if (declaredField.get(null).equals(str)) {
                return declaredField2.getInt(null);
            }
            String valueOf = String.valueOf(declaredField.get(null));
            StringBuilder sb2 = new StringBuilder(String.valueOf(valueOf).length() + 51 + String.valueOf(str).length());
            sb2.append("Module descriptor id '");
            sb2.append(valueOf);
            sb2.append("' didn't match expected id '");
            sb2.append(str);
            sb2.append("'");
            Log.e("DynamiteModule", sb2.toString());
            return 0;
        } catch (ClassNotFoundException unused) {
            StringBuilder sb3 = new StringBuilder(String.valueOf(str).length() + 45);
            sb3.append("Local module descriptor class for ");
            sb3.append(str);
            sb3.append(" not found.");
            Log.w("DynamiteModule", sb3.toString());
            return 0;
        } catch (Exception e) {
            String valueOf2 = String.valueOf(e.getMessage());
            Log.e("DynamiteModule", valueOf2.length() != 0 ? "Failed to load module descriptor class: ".concat(valueOf2) : new String("Failed to load module descriptor class: "));
            return 0;
        }
    }

    public static int zza(Context context, String str, boolean z) {
        Boolean bool;
        try {
            synchronized (DynamiteModule.class) {
                Boolean bool2 = zzif;
                if (bool2 == null) {
                    try {
                        Class<?> loadClass = context.getApplicationContext().getClassLoader().loadClass(DynamiteLoaderClassLoader.class.getName());
                        Field declaredField = loadClass.getDeclaredField("sClassLoader");
                        synchronized (loadClass) {
                            ClassLoader classLoader = (ClassLoader) declaredField.get(null);
                            if (classLoader != null) {
                                if (classLoader == ClassLoader.getSystemClassLoader()) {
                                    bool = Boolean.FALSE;
                                } else {
                                    try {
                                        zza(classLoader);
                                    } catch (LoadingException unused) {
                                    }
                                    bool = Boolean.TRUE;
                                }
                            } else if ("com.google.android.gms".equals(context.getApplicationContext().getPackageName())) {
                                declaredField.set(null, ClassLoader.getSystemClassLoader());
                                bool = Boolean.FALSE;
                            } else {
                                try {
                                    int zzc = zzc(context, str, z);
                                    if (zzii != null) {
                                        if (!zzii.isEmpty()) {
                                            zzh zzh = new zzh(zzii, ClassLoader.getSystemClassLoader());
                                            zza(zzh);
                                            declaredField.set(null, zzh);
                                            zzif = Boolean.TRUE;
                                            return zzc;
                                        }
                                    }
                                    return zzc;
                                } catch (LoadingException unused2) {
                                    declaredField.set(null, ClassLoader.getSystemClassLoader());
                                    bool = Boolean.FALSE;
                                    bool2 = bool;
                                    zzif = bool2;
                                    if (!bool2.booleanValue()) {
                                        return zzb(context, str, z);
                                    }
                                    try {
                                        return zzc(context, str, z);
                                    } catch (LoadingException e) {
                                        String valueOf = String.valueOf(e.getMessage());
                                        Log.w("DynamiteModule", valueOf.length() != 0 ? "Failed to retrieve remote module version: ".concat(valueOf) : new String("Failed to retrieve remote module version: "));
                                        return 0;
                                    }
                                }
                            }
                        }
                    } catch (ClassNotFoundException | IllegalAccessException | NoSuchFieldException e2) {
                        String valueOf2 = String.valueOf(e2);
                        StringBuilder sb = new StringBuilder(String.valueOf(valueOf2).length() + 30);
                        sb.append("Failed to load module via V2: ");
                        sb.append(valueOf2);
                        Log.w("DynamiteModule", sb.toString());
                        bool2 = Boolean.FALSE;
                    }
                }
            }
        } catch (Throwable th) {
            CrashUtils.addDynamiteErrorToDropBox(context, th);
            throw th;
        }
    }

    private static int zzb(Context context, String str, boolean z) {
        zzi zzj = zzj(context);
        if (zzj == null) {
            return 0;
        }
        try {
            if (zzj.zzak() >= 2) {
                return zzj.zzb(ObjectWrapper.wrap(context), str, z);
            }
            Log.w("DynamiteModule", "IDynamite loader version < 2, falling back to getModuleVersion2");
            return zzj.zza(ObjectWrapper.wrap(context), str, z);
        } catch (RemoteException e) {
            String valueOf = String.valueOf(e.getMessage());
            Log.w("DynamiteModule", valueOf.length() != 0 ? "Failed to retrieve remote module version: ".concat(valueOf) : new String("Failed to retrieve remote module version: "));
            return 0;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:44:0x00a7 A[Catch:{ all -> 0x00b0 }] */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x00a8 A[Catch:{ all -> 0x00b0 }] */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x00b4  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static int zzc(android.content.Context r8, java.lang.String r9, boolean r10) {
        /*
        // Method dump skipped, instructions count: 184
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.dynamite.DynamiteModule.zzc(android.content.Context, java.lang.String, boolean):int");
    }

    @KeepForSdk
    public static int getRemoteVersion(Context context, String str) {
        return zza(context, str, false);
    }

    private static DynamiteModule zze(Context context, String str) {
        String valueOf = String.valueOf(str);
        Log.i("DynamiteModule", valueOf.length() != 0 ? "Selected local version of ".concat(valueOf) : new String("Selected local version of "));
        return new DynamiteModule(context.getApplicationContext());
    }

    private static DynamiteModule zza(Context context, String str, int i) {
        Boolean bool;
        IObjectWrapper iObjectWrapper;
        try {
            synchronized (DynamiteModule.class) {
                bool = zzif;
            }
            if (bool == null) {
                throw new LoadingException("Failed to determine which loading route to use.", (zza) null);
            } else if (bool.booleanValue()) {
                return zzb(context, str, i);
            } else {
                StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 51);
                sb.append("Selected remote version of ");
                sb.append(str);
                sb.append(", version >= ");
                sb.append(i);
                Log.i("DynamiteModule", sb.toString());
                zzi zzj = zzj(context);
                if (zzj != null) {
                    if (zzj.zzak() >= 2) {
                        iObjectWrapper = zzj.zzb(ObjectWrapper.wrap(context), str, i);
                    } else {
                        Log.w("DynamiteModule", "Dynamite loader version < 2, falling back to createModuleContext");
                        iObjectWrapper = zzj.zza(ObjectWrapper.wrap(context), str, i);
                    }
                    if (ObjectWrapper.unwrap(iObjectWrapper) != null) {
                        return new DynamiteModule((Context) ObjectWrapper.unwrap(iObjectWrapper));
                    }
                    throw new LoadingException("Failed to load remote module.", (zza) null);
                }
                throw new LoadingException("Failed to create IDynamiteLoader.", (zza) null);
            }
        } catch (RemoteException e) {
            throw new LoadingException("Failed to load remote module.", e, null);
        } catch (LoadingException e2) {
            throw e2;
        } catch (Throwable th) {
            CrashUtils.addDynamiteErrorToDropBox(context, th);
            throw new LoadingException("Failed to load remote module.", th, null);
        }
    }

    private static zzi zzj(Context context) {
        zzi zzi;
        synchronized (DynamiteModule.class) {
            if (zzig != null) {
                return zzig;
            } else if (GoogleApiAvailabilityLight.getInstance().isGooglePlayServicesAvailable(context) != 0) {
                return null;
            } else {
                try {
                    IBinder iBinder = (IBinder) context.createPackageContext("com.google.android.gms", 3).getClassLoader().loadClass("com.google.android.gms.chimera.container.DynamiteLoaderImpl").newInstance();
                    if (iBinder == null) {
                        zzi = null;
                    } else {
                        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.dynamite.IDynamiteLoader");
                        if (queryLocalInterface instanceof zzi) {
                            zzi = (zzi) queryLocalInterface;
                        } else {
                            zzi = new zzj(iBinder);
                        }
                    }
                    if (zzi != null) {
                        zzig = zzi;
                        return zzi;
                    }
                } catch (Exception e) {
                    String valueOf = String.valueOf(e.getMessage());
                    Log.e("DynamiteModule", valueOf.length() != 0 ? "Failed to load IDynamiteLoader from GmsCore: ".concat(valueOf) : new String("Failed to load IDynamiteLoader from GmsCore: "));
                }
                return null;
            }
        }
    }

    @KeepForSdk
    public final Context getModuleContext() {
        return this.zzin;
    }

    private static DynamiteModule zzb(Context context, String str, int i) {
        zzk zzk;
        IObjectWrapper iObjectWrapper;
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 51);
        sb.append("Selected remote version of ");
        sb.append(str);
        sb.append(", version >= ");
        sb.append(i);
        Log.i("DynamiteModule", sb.toString());
        synchronized (DynamiteModule.class) {
            zzk = zzih;
        }
        if (zzk != null) {
            zza zza2 = zzik.get();
            if (zza2 == null || zza2.zzio == null) {
                throw new LoadingException("No result cursor", (zza) null);
            }
            Context applicationContext = context.getApplicationContext();
            Cursor cursor = zza2.zzio;
            ObjectWrapper.wrap(null);
            if (zzaj().booleanValue()) {
                Log.v("DynamiteModule", "Dynamite loader version >= 2, using loadModule2NoCrashUtils");
                iObjectWrapper = zzk.zzb(ObjectWrapper.wrap(applicationContext), str, i, ObjectWrapper.wrap(cursor));
            } else {
                Log.w("DynamiteModule", "Dynamite loader version < 2, falling back to loadModule2");
                iObjectWrapper = zzk.zza(ObjectWrapper.wrap(applicationContext), str, i, ObjectWrapper.wrap(cursor));
            }
            Context context2 = (Context) ObjectWrapper.unwrap(iObjectWrapper);
            if (context2 != null) {
                return new DynamiteModule(context2);
            }
            throw new LoadingException("Failed to get module context", (zza) null);
        }
        throw new LoadingException("DynamiteLoaderV2 was not cached.", (zza) null);
    }

    private static Boolean zzaj() {
        Boolean valueOf;
        synchronized (DynamiteModule.class) {
            valueOf = Boolean.valueOf(zzij >= 2);
        }
        return valueOf;
    }

    private static void zza(ClassLoader classLoader) {
        zzk zzk;
        try {
            IBinder iBinder = (IBinder) classLoader.loadClass("com.google.android.gms.dynamiteloader.DynamiteLoaderV2").getConstructor(new Class[0]).newInstance(new Object[0]);
            if (iBinder == null) {
                zzk = null;
            } else {
                IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.dynamite.IDynamiteLoaderV2");
                if (queryLocalInterface instanceof zzk) {
                    zzk = (zzk) queryLocalInterface;
                } else {
                    zzk = new zzl(iBinder);
                }
            }
            zzih = zzk;
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException e) {
            throw new LoadingException("Failed to instantiate dynamite loader", e, null);
        }
    }

    @KeepForSdk
    public final IBinder instantiate(String str) {
        try {
            return (IBinder) this.zzin.getClassLoader().loadClass(str).newInstance();
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            String valueOf = String.valueOf(str);
            throw new LoadingException(valueOf.length() != 0 ? "Failed to instantiate module class: ".concat(valueOf) : new String("Failed to instantiate module class: "), e, null);
        }
    }

    private DynamiteModule(Context context) {
        this.zzin = (Context) Preconditions.checkNotNull(context);
    }
}
