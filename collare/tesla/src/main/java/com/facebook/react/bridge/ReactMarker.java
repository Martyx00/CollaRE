package com.facebook.react.bridge;

import com.facebook.j.a.a;
import java.util.ArrayList;
import java.util.List;

@a
public class ReactMarker {
    private static final List<MarkerListener> sListeners = new ArrayList();

    public interface MarkerListener {
        void logMarker(ReactMarkerConstants reactMarkerConstants, String str, int i);
    }

    @a
    public static void addListener(MarkerListener markerListener) {
        synchronized (sListeners) {
            if (!sListeners.contains(markerListener)) {
                sListeners.add(markerListener);
            }
        }
    }

    @a
    public static void removeListener(MarkerListener markerListener) {
        synchronized (sListeners) {
            sListeners.remove(markerListener);
        }
    }

    @a
    public static void clearMarkerListeners() {
        synchronized (sListeners) {
            sListeners.clear();
        }
    }

    @a
    public static void logMarker(String str) {
        logMarker(str, (String) null);
    }

    @a
    public static void logMarker(String str, int i) {
        logMarker(str, (String) null, i);
    }

    @a
    public static void logMarker(String str, String str2) {
        logMarker(str, str2, 0);
    }

    @a
    public static void logMarker(String str, String str2, int i) {
        logMarker(ReactMarkerConstants.valueOf(str), str2, i);
    }

    @a
    public static void logMarker(ReactMarkerConstants reactMarkerConstants) {
        logMarker(reactMarkerConstants, (String) null, 0);
    }

    @a
    public static void logMarker(ReactMarkerConstants reactMarkerConstants, int i) {
        logMarker(reactMarkerConstants, (String) null, i);
    }

    @a
    public static void logMarker(ReactMarkerConstants reactMarkerConstants, String str) {
        logMarker(reactMarkerConstants, str, 0);
    }

    @a
    public static void logMarker(ReactMarkerConstants reactMarkerConstants, String str, int i) {
        synchronized (sListeners) {
            for (MarkerListener markerListener : sListeners) {
                markerListener.logMarker(reactMarkerConstants, str, i);
            }
        }
    }
}
