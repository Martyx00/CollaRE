package android.support.v4.app;

import android.app.Notification;
import android.os.Bundle;
import android.support.v4.app.y;
import android.util.Log;
import android.util.SparseArray;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/* access modifiers changed from: package-private */
/* compiled from: NotificationCompatJellybean */
public class aa {

    /* renamed from: a  reason: collision with root package name */
    private static final Object f154a = new Object();

    /* renamed from: b  reason: collision with root package name */
    private static Field f155b;

    /* renamed from: c  reason: collision with root package name */
    private static boolean f156c;

    /* renamed from: d  reason: collision with root package name */
    private static final Object f157d = new Object();

    public static SparseArray<Bundle> a(List<Bundle> list) {
        int size = list.size();
        SparseArray<Bundle> sparseArray = null;
        for (int i = 0; i < size; i++) {
            Bundle bundle = list.get(i);
            if (bundle != null) {
                if (sparseArray == null) {
                    sparseArray = new SparseArray<>();
                }
                sparseArray.put(i, bundle);
            }
        }
        return sparseArray;
    }

    public static Bundle a(Notification notification) {
        synchronized (f154a) {
            if (f156c) {
                return null;
            }
            try {
                if (f155b == null) {
                    Field declaredField = Notification.class.getDeclaredField("extras");
                    if (!Bundle.class.isAssignableFrom(declaredField.getType())) {
                        Log.e("NotificationCompat", "Notification.extras field is not of type Bundle");
                        f156c = true;
                        return null;
                    }
                    declaredField.setAccessible(true);
                    f155b = declaredField;
                }
                Bundle bundle = (Bundle) f155b.get(notification);
                if (bundle == null) {
                    bundle = new Bundle();
                    f155b.set(notification, bundle);
                }
                return bundle;
            } catch (IllegalAccessException e) {
                Log.e("NotificationCompat", "Unable to access notification extras", e);
                f156c = true;
                return null;
            } catch (NoSuchFieldException e2) {
                Log.e("NotificationCompat", "Unable to access notification extras", e2);
                f156c = true;
                return null;
            }
        }
    }

    public static Bundle a(Notification.Builder builder, y.a aVar) {
        builder.addAction(aVar.a(), aVar.b(), aVar.c());
        Bundle bundle = new Bundle(aVar.d());
        if (aVar.f() != null) {
            bundle.putParcelableArray("android.support.remoteInputs", a(aVar.f()));
        }
        if (aVar.h() != null) {
            bundle.putParcelableArray("android.support.dataRemoteInputs", a(aVar.h()));
        }
        bundle.putBoolean("android.support.allowGeneratedReplies", aVar.e());
        return bundle;
    }

    static Bundle a(y.a aVar) {
        Bundle bundle;
        Bundle bundle2 = new Bundle();
        bundle2.putInt("icon", aVar.a());
        bundle2.putCharSequence("title", aVar.b());
        bundle2.putParcelable("actionIntent", aVar.c());
        if (aVar.d() != null) {
            bundle = new Bundle(aVar.d());
        } else {
            bundle = new Bundle();
        }
        bundle.putBoolean("android.support.allowGeneratedReplies", aVar.e());
        bundle2.putBundle("extras", bundle);
        bundle2.putParcelableArray("remoteInputs", a(aVar.f()));
        bundle2.putBoolean("showsUserInterface", aVar.i());
        bundle2.putInt("semanticAction", aVar.g());
        return bundle2;
    }

    private static Bundle a(ad adVar) {
        Bundle bundle = new Bundle();
        bundle.putString("resultKey", adVar.a());
        bundle.putCharSequence("label", adVar.b());
        bundle.putCharSequenceArray("choices", adVar.c());
        bundle.putBoolean("allowFreeFormInput", adVar.e());
        bundle.putBundle("extras", adVar.f());
        Set<String> d2 = adVar.d();
        if (d2 != null && !d2.isEmpty()) {
            ArrayList<String> arrayList = new ArrayList<>(d2.size());
            for (String str : d2) {
                arrayList.add(str);
            }
            bundle.putStringArrayList("allowedDataTypes", arrayList);
        }
        return bundle;
    }

    private static Bundle[] a(ad[] adVarArr) {
        if (adVarArr == null) {
            return null;
        }
        Bundle[] bundleArr = new Bundle[adVarArr.length];
        for (int i = 0; i < adVarArr.length; i++) {
            bundleArr[i] = a(adVarArr[i]);
        }
        return bundleArr;
    }
}
