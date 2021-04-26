package org.webrtc;

import android.content.Context;
import java.util.List;
import org.webrtc.EglBase;
import org.webrtc.Logging;
import org.webrtc.NativeLibrary;
import org.webrtc.PeerConnection;
import org.webrtc.audio.AudioDeviceModule;
import org.webrtc.audio.LegacyAudioDeviceModule;

public class PeerConnectionFactory {
    private static final String TAG = "PeerConnectionFactory";
    public static final String TRIAL_ENABLED = "Enabled";
    private static final String VIDEO_CAPTURER_THREAD_NAME = "VideoCapturerThread";
    @Deprecated
    public static final String VIDEO_FRAME_EMIT_TRIAL = "VideoFrameEmit";
    private static boolean enableVideoHwAcceleration = false;
    private static volatile boolean internalTracerInitialized = false;
    private static Thread networkThread;
    private static Thread signalingThread;
    private static Thread workerThread;
    private final long nativeFactory;

    private static native long nativeCreateAudioSource(long j, MediaConstraints mediaConstraints);

    private static native long nativeCreateAudioTrack(long j, String str, long j2);

    private static native long nativeCreateLocalMediaStream(long j, String str);

    private static native long nativeCreatePeerConnection(long j, PeerConnection.RTCConfiguration rTCConfiguration, MediaConstraints mediaConstraints, long j2);

    private static native long nativeCreatePeerConnectionFactory(Context context, Options options, long j, VideoEncoderFactory videoEncoderFactory, VideoDecoderFactory videoDecoderFactory, long j2, long j3);

    private static native long nativeCreateVideoSource(long j, boolean z);

    private static native long nativeCreateVideoTrack(long j, String str, long j2);

    private static native void nativeDeleteLoggable();

    private static native String nativeFindFieldTrialsFullName(String str);

    private static native void nativeFreeFactory(long j);

    private static native long nativeGetNativePeerConnectionFactory(long j);

    private static native void nativeInitializeAndroidGlobals();

    private static native void nativeInitializeFieldTrials(String str);

    private static native void nativeInitializeInternalTracer();

    private static native void nativeInjectLoggable(JNILogging jNILogging, int i);

    private static native void nativeInvokeThreadsCallbacks(long j);

    private static native void nativeShutdownInternalTracer();

    private static native boolean nativeStartAecDump(long j, int i, int i2);

    private static native boolean nativeStartInternalTracingCapture(String str);

    private static native void nativeStopAecDump(long j);

    private static native void nativeStopInternalTracingCapture();

    public static class InitializationOptions {
        final Context applicationContext;
        final boolean enableInternalTracer;
        final boolean enableVideoHwAcceleration;
        final String fieldTrials;
        Loggable loggable;
        Logging.Severity loggableSeverity;
        final NativeLibraryLoader nativeLibraryLoader;
        final String nativeLibraryName;

        private InitializationOptions(Context context, String str, boolean z, boolean z2, NativeLibraryLoader nativeLibraryLoader2, String str2, Loggable loggable2, Logging.Severity severity) {
            this.applicationContext = context;
            this.fieldTrials = str;
            this.enableInternalTracer = z;
            this.enableVideoHwAcceleration = z2;
            this.nativeLibraryLoader = nativeLibraryLoader2;
            this.nativeLibraryName = str2;
            this.loggable = loggable2;
            this.loggableSeverity = severity;
        }

        public static Builder builder(Context context) {
            return new Builder(context);
        }

        public static class Builder {
            private final Context applicationContext;
            private boolean enableInternalTracer = false;
            private boolean enableVideoHwAcceleration = true;
            private String fieldTrials = "";
            private Loggable loggable = null;
            private Logging.Severity loggableSeverity = null;
            private NativeLibraryLoader nativeLibraryLoader = new NativeLibrary.DefaultLoader();
            private String nativeLibraryName = "jingle_peerconnection_so";

            Builder(Context context) {
                this.applicationContext = context;
            }

            public Builder setFieldTrials(String str) {
                this.fieldTrials = str;
                return this;
            }

            public Builder setEnableInternalTracer(boolean z) {
                this.enableInternalTracer = z;
                return this;
            }

            @Deprecated
            public Builder setEnableVideoHwAcceleration(boolean z) {
                this.enableVideoHwAcceleration = z;
                return this;
            }

            public Builder setNativeLibraryLoader(NativeLibraryLoader nativeLibraryLoader2) {
                this.nativeLibraryLoader = nativeLibraryLoader2;
                return this;
            }

            public Builder setNativeLibraryName(String str) {
                this.nativeLibraryName = str;
                return this;
            }

            public Builder setInjectableLogger(Loggable loggable2, Logging.Severity severity) {
                this.loggable = loggable2;
                this.loggableSeverity = severity;
                return this;
            }

            public InitializationOptions createInitializationOptions() {
                return new InitializationOptions(this.applicationContext, this.fieldTrials, this.enableInternalTracer, this.enableVideoHwAcceleration, this.nativeLibraryLoader, this.nativeLibraryName, this.loggable, this.loggableSeverity);
            }
        }
    }

    public static class Options {
        static final int ADAPTER_TYPE_ANY = 32;
        static final int ADAPTER_TYPE_CELLULAR = 4;
        static final int ADAPTER_TYPE_ETHERNET = 1;
        static final int ADAPTER_TYPE_LOOPBACK = 16;
        static final int ADAPTER_TYPE_UNKNOWN = 0;
        static final int ADAPTER_TYPE_VPN = 8;
        static final int ADAPTER_TYPE_WIFI = 2;
        public boolean disableEncryption;
        public boolean disableNetworkMonitor;
        public boolean enableAes128Sha1_32CryptoCipher;
        public int networkIgnoreMask;

        /* access modifiers changed from: package-private */
        @CalledByNative("Options")
        public int getNetworkIgnoreMask() {
            return this.networkIgnoreMask;
        }

        /* access modifiers changed from: package-private */
        @CalledByNative("Options")
        public boolean getDisableEncryption() {
            return this.disableEncryption;
        }

        /* access modifiers changed from: package-private */
        @CalledByNative("Options")
        public boolean getDisableNetworkMonitor() {
            return this.disableNetworkMonitor;
        }

        /* access modifiers changed from: package-private */
        @CalledByNative("Options")
        public boolean getEnableAes128Sha1_32CryptoCipher() {
            return this.enableAes128Sha1_32CryptoCipher;
        }
    }

    public static class Builder {
        private AudioDeviceModule audioDeviceModule;
        private AudioProcessingFactory audioProcessingFactory;
        private VideoDecoderFactory decoderFactory;
        private VideoEncoderFactory encoderFactory;
        private FecControllerFactoryFactoryInterface fecControllerFactoryFactory;
        private Options options;

        private Builder() {
            this.audioDeviceModule = new LegacyAudioDeviceModule();
        }

        public Builder setOptions(Options options2) {
            this.options = options2;
            return this;
        }

        public Builder setAudioDeviceModule(AudioDeviceModule audioDeviceModule2) {
            this.audioDeviceModule = audioDeviceModule2;
            return this;
        }

        public Builder setVideoEncoderFactory(VideoEncoderFactory videoEncoderFactory) {
            this.encoderFactory = videoEncoderFactory;
            return this;
        }

        public Builder setVideoDecoderFactory(VideoDecoderFactory videoDecoderFactory) {
            this.decoderFactory = videoDecoderFactory;
            return this;
        }

        public Builder setAudioProcessingFactory(AudioProcessingFactory audioProcessingFactory2) {
            if (audioProcessingFactory2 != null) {
                this.audioProcessingFactory = audioProcessingFactory2;
                return this;
            }
            throw new NullPointerException("PeerConnectionFactory builder does not accept a null AudioProcessingFactory.");
        }

        public Builder setFecControllerFactoryFactoryInterface(FecControllerFactoryFactoryInterface fecControllerFactoryFactoryInterface) {
            this.fecControllerFactoryFactory = fecControllerFactoryFactoryInterface;
            return this;
        }

        public PeerConnectionFactory createPeerConnectionFactory() {
            SoftwareVideoDecoderFactory softwareVideoDecoderFactory;
            SoftwareVideoEncoderFactory softwareVideoEncoderFactory;
            VideoEncoderFactory videoEncoderFactory = this.encoderFactory;
            VideoDecoderFactory videoDecoderFactory = this.decoderFactory;
            if (videoEncoderFactory == null && videoDecoderFactory == null && !PeerConnectionFactory.enableVideoHwAcceleration) {
                softwareVideoEncoderFactory = new SoftwareVideoEncoderFactory();
                softwareVideoDecoderFactory = new SoftwareVideoDecoderFactory();
            } else {
                if (videoEncoderFactory == null) {
                    videoEncoderFactory = MediaCodecVideoEncoder.createFactory();
                }
                if (videoDecoderFactory == null) {
                    softwareVideoEncoderFactory = videoEncoderFactory;
                    softwareVideoDecoderFactory = MediaCodecVideoDecoder.createFactory();
                } else {
                    softwareVideoEncoderFactory = videoEncoderFactory;
                    softwareVideoDecoderFactory = videoDecoderFactory;
                }
            }
            return new PeerConnectionFactory(this.options, this.audioDeviceModule, softwareVideoEncoderFactory, softwareVideoDecoderFactory, this.audioProcessingFactory, this.fecControllerFactoryFactory);
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    public static void initialize(InitializationOptions initializationOptions) {
        ContextUtils.initialize(initializationOptions.applicationContext);
        NativeLibrary.initialize(initializationOptions.nativeLibraryLoader, initializationOptions.nativeLibraryName);
        enableVideoHwAcceleration = initializationOptions.enableVideoHwAcceleration;
        nativeInitializeAndroidGlobals();
        nativeInitializeFieldTrials(initializationOptions.fieldTrials);
        if (initializationOptions.enableInternalTracer && !internalTracerInitialized) {
            initializeInternalTracer();
        }
        if (initializationOptions.loggable != null) {
            Logging.injectLoggable(initializationOptions.loggable, initializationOptions.loggableSeverity);
            nativeInjectLoggable(new JNILogging(initializationOptions.loggable), initializationOptions.loggableSeverity.ordinal());
            return;
        }
        Logging.d(TAG, "PeerConnectionFactory was initialized without an injected Loggable. Any existing Loggable will be deleted.");
        Logging.deleteInjectedLoggable();
        nativeDeleteLoggable();
    }

    private void checkInitializeHasBeenCalled() {
        if (!NativeLibrary.isLoaded() || ContextUtils.getApplicationContext() == null) {
            throw new IllegalStateException("PeerConnectionFactory.initialize was not called before creating a PeerConnectionFactory.");
        }
    }

    private static void initializeInternalTracer() {
        internalTracerInitialized = true;
        nativeInitializeInternalTracer();
    }

    public static void shutdownInternalTracer() {
        internalTracerInitialized = false;
        nativeShutdownInternalTracer();
    }

    @Deprecated
    public static void initializeFieldTrials(String str) {
        nativeInitializeFieldTrials(str);
    }

    public static String fieldTrialsFindFullName(String str) {
        return NativeLibrary.isLoaded() ? nativeFindFieldTrialsFullName(str) : "";
    }

    public static boolean startInternalTracingCapture(String str) {
        return nativeStartInternalTracingCapture(str);
    }

    public static void stopInternalTracingCapture() {
        nativeStopInternalTracingCapture();
    }

    private PeerConnectionFactory(Options options, AudioDeviceModule audioDeviceModule, VideoEncoderFactory videoEncoderFactory, VideoDecoderFactory videoDecoderFactory, AudioProcessingFactory audioProcessingFactory, FecControllerFactoryFactoryInterface fecControllerFactoryFactoryInterface) {
        long j;
        long j2;
        long j3;
        checkInitializeHasBeenCalled();
        Context applicationContext = ContextUtils.getApplicationContext();
        if (audioDeviceModule == null) {
            j = 0;
        } else {
            j = audioDeviceModule.getNativeAudioDeviceModulePointer();
        }
        if (audioProcessingFactory == null) {
            j2 = 0;
        } else {
            j2 = audioProcessingFactory.createNative();
        }
        if (fecControllerFactoryFactoryInterface == null) {
            j3 = 0;
        } else {
            j3 = fecControllerFactoryFactoryInterface.createNative();
        }
        this.nativeFactory = nativeCreatePeerConnectionFactory(applicationContext, options, j, videoEncoderFactory, videoDecoderFactory, j2, j3);
        if (this.nativeFactory == 0) {
            throw new RuntimeException("Failed to initialize PeerConnectionFactory!");
        }
    }

    @CalledByNative
    PeerConnectionFactory(long j) {
        checkInitializeHasBeenCalled();
        if (j != 0) {
            this.nativeFactory = j;
            return;
        }
        throw new RuntimeException("Failed to initialize PeerConnectionFactory!");
    }

    @Deprecated
    public PeerConnection createPeerConnection(PeerConnection.RTCConfiguration rTCConfiguration, MediaConstraints mediaConstraints, PeerConnection.Observer observer) {
        long createNativePeerConnectionObserver = PeerConnection.createNativePeerConnectionObserver(observer);
        if (createNativePeerConnectionObserver == 0) {
            return null;
        }
        long nativeCreatePeerConnection = nativeCreatePeerConnection(this.nativeFactory, rTCConfiguration, mediaConstraints, createNativePeerConnectionObserver);
        if (nativeCreatePeerConnection == 0) {
            return null;
        }
        return new PeerConnection(nativeCreatePeerConnection);
    }

    @Deprecated
    public PeerConnection createPeerConnection(List<PeerConnection.IceServer> list, MediaConstraints mediaConstraints, PeerConnection.Observer observer) {
        return createPeerConnection(new PeerConnection.RTCConfiguration(list), mediaConstraints, observer);
    }

    public PeerConnection createPeerConnection(List<PeerConnection.IceServer> list, PeerConnection.Observer observer) {
        return createPeerConnection(new PeerConnection.RTCConfiguration(list), observer);
    }

    public PeerConnection createPeerConnection(PeerConnection.RTCConfiguration rTCConfiguration, PeerConnection.Observer observer) {
        return createPeerConnection(rTCConfiguration, (MediaConstraints) null, observer);
    }

    public PeerConnection createPeerConnection(PeerConnection.RTCConfiguration rTCConfiguration, PeerConnectionDependencies peerConnectionDependencies) {
        return createPeerConnection(rTCConfiguration, (MediaConstraints) null, peerConnectionDependencies.getObserver());
    }

    public MediaStream createLocalMediaStream(String str) {
        return new MediaStream(nativeCreateLocalMediaStream(this.nativeFactory, str));
    }

    public VideoSource createVideoSource(boolean z) {
        return new VideoSource(nativeCreateVideoSource(this.nativeFactory, z));
    }

    @Deprecated
    public VideoSource createVideoSource(VideoCapturer videoCapturer) {
        SurfaceTextureHelper create = SurfaceTextureHelper.create(VIDEO_CAPTURER_THREAD_NAME, MediaCodecVideoEncoder.getEglContext());
        VideoSource videoSource = new VideoSource(nativeCreateVideoSource(this.nativeFactory, videoCapturer.isScreencast()), create);
        videoCapturer.initialize(create, ContextUtils.getApplicationContext(), videoSource.getCapturerObserver());
        return videoSource;
    }

    public VideoTrack createVideoTrack(String str, VideoSource videoSource) {
        return new VideoTrack(nativeCreateVideoTrack(this.nativeFactory, str, videoSource.nativeSource));
    }

    public AudioSource createAudioSource(MediaConstraints mediaConstraints) {
        return new AudioSource(nativeCreateAudioSource(this.nativeFactory, mediaConstraints));
    }

    public AudioTrack createAudioTrack(String str, AudioSource audioSource) {
        return new AudioTrack(nativeCreateAudioTrack(this.nativeFactory, str, audioSource.nativeSource));
    }

    public boolean startAecDump(int i, int i2) {
        return nativeStartAecDump(this.nativeFactory, i, i2);
    }

    public void stopAecDump() {
        nativeStopAecDump(this.nativeFactory);
    }

    @Deprecated
    public void setVideoHwAccelerationOptions(EglBase.Context context, EglBase.Context context2) {
        MediaCodecVideoEncoder.setEglContext(context);
        MediaCodecVideoDecoder.setEglContext(context2);
    }

    public void dispose() {
        nativeFreeFactory(this.nativeFactory);
        networkThread = null;
        workerThread = null;
        signalingThread = null;
        MediaCodecVideoEncoder.disposeEglContext();
        MediaCodecVideoDecoder.disposeEglContext();
    }

    public void threadsCallbacks() {
        nativeInvokeThreadsCallbacks(this.nativeFactory);
    }

    public long getNativePeerConnectionFactory() {
        return nativeGetNativePeerConnectionFactory(this.nativeFactory);
    }

    public long getNativeOwnedFactoryAndThreads() {
        return this.nativeFactory;
    }

    private static void printStackTrace(Thread thread, String str) {
        if (thread != null) {
            StackTraceElement[] stackTrace = thread.getStackTrace();
            if (stackTrace.length > 0) {
                Logging.d(TAG, str + " stacks trace:");
                for (StackTraceElement stackTraceElement : stackTrace) {
                    Logging.d(TAG, stackTraceElement.toString());
                }
            }
        }
    }

    public static void printStackTraces() {
        printStackTrace(networkThread, "Network thread");
        printStackTrace(workerThread, "Worker thread");
        printStackTrace(signalingThread, "Signaling thread");
    }

    @CalledByNative
    private static void onNetworkThreadReady() {
        networkThread = Thread.currentThread();
        Logging.d(TAG, "onNetworkThreadReady");
    }

    @CalledByNative
    private static void onWorkerThreadReady() {
        workerThread = Thread.currentThread();
        Logging.d(TAG, "onWorkerThreadReady");
    }

    @CalledByNative
    private static void onSignalingThreadReady() {
        signalingThread = Thread.currentThread();
        Logging.d(TAG, "onSignalingThreadReady");
    }
}
