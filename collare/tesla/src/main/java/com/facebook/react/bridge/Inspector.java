package com.facebook.react.bridge;

import com.facebook.j.a.a;
import com.facebook.jni.HybridData;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@a
public class Inspector {
    private final HybridData mHybridData;

    @a
    public interface RemoteConnection {
        @a
        void onDisconnect();

        @a
        void onMessage(String str);
    }

    private native LocalConnection connectNative(int i, RemoteConnection remoteConnection);

    private native Page[] getPagesNative();

    private static native Inspector instance();

    static {
        ReactBridge.staticInit();
    }

    public static List<Page> getPages() {
        try {
            return Arrays.asList(instance().getPagesNative());
        } catch (UnsatisfiedLinkError e) {
            com.facebook.common.e.a.c("ReactNative", "Inspector doesn't work in open source yet", e);
            return Collections.emptyList();
        }
    }

    public static LocalConnection connect(int i, RemoteConnection remoteConnection) {
        try {
            return instance().connectNative(i, remoteConnection);
        } catch (UnsatisfiedLinkError e) {
            com.facebook.common.e.a.c("ReactNative", "Inspector doesn't work in open source yet", e);
            throw new RuntimeException(e);
        }
    }

    private Inspector(HybridData hybridData) {
        this.mHybridData = hybridData;
    }

    @a
    public static class Page {
        private final int mId;
        private final String mTitle;
        private final String mVM;

        public int getId() {
            return this.mId;
        }

        public String getTitle() {
            return this.mTitle;
        }

        public String getVM() {
            return this.mVM;
        }

        public String toString() {
            return "Page{mId=" + this.mId + ", mTitle='" + this.mTitle + '\'' + '}';
        }

        @a
        private Page(int i, String str, String str2) {
            this.mId = i;
            this.mTitle = str;
            this.mVM = str2;
        }
    }

    @a
    public static class LocalConnection {
        private final HybridData mHybridData;

        public native void disconnect();

        public native void sendMessage(String str);

        private LocalConnection(HybridData hybridData) {
            this.mHybridData = hybridData;
        }
    }
}
