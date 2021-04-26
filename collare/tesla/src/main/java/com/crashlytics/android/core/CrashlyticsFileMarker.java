package com.crashlytics.android.core;

import io.a.a.a.a.f.a;
import io.a.a.a.c;
import io.a.a.a.l;
import java.io.File;
import java.io.IOException;

/* access modifiers changed from: package-private */
public class CrashlyticsFileMarker {
    private final a fileStore;
    private final String markerName;

    public CrashlyticsFileMarker(String str, a aVar) {
        this.markerName = str;
        this.fileStore = aVar;
    }

    public boolean create() {
        try {
            return getMarkerFile().createNewFile();
        } catch (IOException e) {
            l g = c.g();
            g.e(CrashlyticsCore.TAG, "Error creating marker: " + this.markerName, e);
            return false;
        }
    }

    public boolean isPresent() {
        return getMarkerFile().exists();
    }

    public boolean remove() {
        return getMarkerFile().delete();
    }

    private File getMarkerFile() {
        return new File(this.fileStore.a(), this.markerName);
    }
}
