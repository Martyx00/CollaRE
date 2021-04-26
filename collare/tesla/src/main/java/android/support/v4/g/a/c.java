package android.support.v4.g.a;

import android.os.Build;
import android.view.accessibility.AccessibilityRecord;

/* compiled from: AccessibilityRecordCompat */
public class c {

    /* renamed from: a  reason: collision with root package name */
    private final AccessibilityRecord f468a;

    public static void a(AccessibilityRecord accessibilityRecord, int i) {
        if (Build.VERSION.SDK_INT >= 15) {
            accessibilityRecord.setMaxScrollX(i);
        }
    }

    public static void b(AccessibilityRecord accessibilityRecord, int i) {
        if (Build.VERSION.SDK_INT >= 15) {
            accessibilityRecord.setMaxScrollY(i);
        }
    }

    @Deprecated
    public int hashCode() {
        AccessibilityRecord accessibilityRecord = this.f468a;
        if (accessibilityRecord == null) {
            return 0;
        }
        return accessibilityRecord.hashCode();
    }

    @Deprecated
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        c cVar = (c) obj;
        AccessibilityRecord accessibilityRecord = this.f468a;
        if (accessibilityRecord == null) {
            if (cVar.f468a != null) {
                return false;
            }
        } else if (!accessibilityRecord.equals(cVar.f468a)) {
            return false;
        }
        return true;
    }
}
