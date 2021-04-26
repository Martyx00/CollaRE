package org.webrtc;

/* access modifiers changed from: package-private */
public class NativeLibrary {
    private static String TAG = "NativeLibrary";
    private static boolean libraryLoaded = false;
    private static Object lock = new Object();

    NativeLibrary() {
    }

    /* access modifiers changed from: package-private */
    public static class DefaultLoader implements NativeLibraryLoader {
        DefaultLoader() {
        }

        @Override // org.webrtc.NativeLibraryLoader
        public boolean load(String str) {
            String str2 = NativeLibrary.TAG;
            Logging.d(str2, "Loading library: " + str);
            try {
                System.loadLibrary(str);
                return true;
            } catch (UnsatisfiedLinkError e) {
                String str3 = NativeLibrary.TAG;
                Logging.e(str3, "Failed to load native library: " + str, e);
                return false;
            }
        }
    }

    static void initialize(NativeLibraryLoader nativeLibraryLoader, String str) {
        synchronized (lock) {
            if (libraryLoaded) {
                Logging.d(TAG, "Native library has already been loaded.");
                return;
            }
            String str2 = TAG;
            Logging.d(str2, "Loading native library: " + str);
            libraryLoaded = nativeLibraryLoader.load(str);
        }
    }

    static boolean isLoaded() {
        boolean z;
        synchronized (lock) {
            z = libraryLoaded;
        }
        return z;
    }
}
