package org.webrtc.voiceengine;

import android.annotation.TargetApi;
import android.media.AudioManager;
import android.media.AudioRecord;
import android.media.AudioTrack;
import android.os.Build;
import io.a.a.a.a.d.b;
import java.util.Timer;
import java.util.TimerTask;
import org.webrtc.ContextUtils;
import org.webrtc.Logging;
import org.webrtc.MediaStreamTrack;

public class WebRtcAudioManager {
    private static final int BITS_PER_SAMPLE = 16;
    private static final boolean DEBUG = false;
    private static final int DEFAULT_FRAME_PER_BUFFER = 256;
    private static final String TAG = "WebRtcAudioManager";
    private static final boolean blacklistDeviceForAAudioUsage = true;
    private static boolean blacklistDeviceForOpenSLESUsage = false;
    private static boolean blacklistDeviceForOpenSLESUsageIsOverridden = false;
    private static boolean useStereoInput = false;
    private static boolean useStereoOutput = false;
    private boolean aAudio;
    private final AudioManager audioManager;
    private boolean hardwareAEC;
    private boolean hardwareAGC;
    private boolean hardwareNS;
    private boolean initialized = false;
    private int inputBufferSize;
    private int inputChannels;
    private boolean lowLatencyInput;
    private boolean lowLatencyOutput;
    private final long nativeAudioManager;
    private int nativeChannels;
    private int nativeSampleRate;
    private int outputBufferSize;
    private int outputChannels;
    private boolean proAudio;
    private int sampleRate;
    private final VolumeLogger volumeLogger;

    private native void nativeCacheAudioParameters(int i, int i2, int i3, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, boolean z7, int i4, int i5, long j);

    public static synchronized void setBlacklistDeviceForOpenSLESUsage(boolean z) {
        synchronized (WebRtcAudioManager.class) {
            blacklistDeviceForOpenSLESUsageIsOverridden = blacklistDeviceForAAudioUsage;
            blacklistDeviceForOpenSLESUsage = z;
        }
    }

    public static synchronized void setStereoOutput(boolean z) {
        synchronized (WebRtcAudioManager.class) {
            Logging.w(TAG, "Overriding default output behavior: setStereoOutput(" + z + ')');
            useStereoOutput = z;
        }
    }

    public static synchronized void setStereoInput(boolean z) {
        synchronized (WebRtcAudioManager.class) {
            Logging.w(TAG, "Overriding default input behavior: setStereoInput(" + z + ')');
            useStereoInput = z;
        }
    }

    public static synchronized boolean getStereoOutput() {
        boolean z;
        synchronized (WebRtcAudioManager.class) {
            z = useStereoOutput;
        }
        return z;
    }

    public static synchronized boolean getStereoInput() {
        boolean z;
        synchronized (WebRtcAudioManager.class) {
            z = useStereoInput;
        }
        return z;
    }

    private static class VolumeLogger {
        private static final String THREAD_NAME = "WebRtcVolumeLevelLoggerThread";
        private static final int TIMER_PERIOD_IN_SECONDS = 30;
        private final AudioManager audioManager;
        private Timer timer;

        public VolumeLogger(AudioManager audioManager2) {
            this.audioManager = audioManager2;
        }

        public void start() {
            this.timer = new Timer(THREAD_NAME);
            this.timer.schedule(new LogVolumeTask(this.audioManager.getStreamMaxVolume(2), this.audioManager.getStreamMaxVolume(0)), 0, 30000);
        }

        /* access modifiers changed from: private */
        public class LogVolumeTask extends TimerTask {
            private final int maxRingVolume;
            private final int maxVoiceCallVolume;

            LogVolumeTask(int i, int i2) {
                this.maxRingVolume = i;
                this.maxVoiceCallVolume = i2;
            }

            public void run() {
                int mode = VolumeLogger.this.audioManager.getMode();
                if (mode == 1) {
                    Logging.d(WebRtcAudioManager.TAG, "STREAM_RING stream volume: " + VolumeLogger.this.audioManager.getStreamVolume(2) + " (max=" + this.maxRingVolume + ")");
                } else if (mode == 3) {
                    Logging.d(WebRtcAudioManager.TAG, "VOICE_CALL stream volume: " + VolumeLogger.this.audioManager.getStreamVolume(0) + " (max=" + this.maxVoiceCallVolume + ")");
                }
            }
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void stop() {
            Timer timer2 = this.timer;
            if (timer2 != null) {
                timer2.cancel();
                this.timer = null;
            }
        }
    }

    WebRtcAudioManager(long j) {
        Logging.d(TAG, "ctor" + WebRtcAudioUtils.getThreadInfo());
        this.nativeAudioManager = j;
        this.audioManager = (AudioManager) ContextUtils.getApplicationContext().getSystemService(MediaStreamTrack.AUDIO_TRACK_KIND);
        this.volumeLogger = new VolumeLogger(this.audioManager);
        storeAudioParameters();
        nativeCacheAudioParameters(this.sampleRate, this.outputChannels, this.inputChannels, this.hardwareAEC, this.hardwareAGC, this.hardwareNS, this.lowLatencyOutput, this.lowLatencyInput, this.proAudio, this.aAudio, this.outputBufferSize, this.inputBufferSize, j);
        WebRtcAudioUtils.logAudioState(TAG);
    }

    private boolean init() {
        Logging.d(TAG, "init" + WebRtcAudioUtils.getThreadInfo());
        if (this.initialized) {
            return blacklistDeviceForAAudioUsage;
        }
        Logging.d(TAG, "audio mode is: " + WebRtcAudioUtils.modeToString(this.audioManager.getMode()));
        this.initialized = blacklistDeviceForAAudioUsage;
        this.volumeLogger.start();
        return blacklistDeviceForAAudioUsage;
    }

    private void dispose() {
        Logging.d(TAG, "dispose" + WebRtcAudioUtils.getThreadInfo());
        if (this.initialized) {
            this.volumeLogger.stop();
        }
    }

    private boolean isCommunicationModeEnabled() {
        if (this.audioManager.getMode() == 3) {
            return blacklistDeviceForAAudioUsage;
        }
        return false;
    }

    private boolean isDeviceBlacklistedForOpenSLESUsage() {
        boolean z;
        if (blacklistDeviceForOpenSLESUsageIsOverridden) {
            z = blacklistDeviceForOpenSLESUsage;
        } else {
            z = WebRtcAudioUtils.deviceIsBlacklistedForOpenSLESUsage();
        }
        if (z) {
            Logging.d(TAG, Build.MODEL + " is blacklisted for OpenSL ES usage!");
        }
        return z;
    }

    private void storeAudioParameters() {
        int i;
        int i2;
        int i3 = 2;
        this.outputChannels = getStereoOutput() ? 2 : 1;
        if (!getStereoInput()) {
            i3 = 1;
        }
        this.inputChannels = i3;
        this.sampleRate = getNativeOutputSampleRate();
        this.hardwareAEC = isAcousticEchoCancelerSupported();
        this.hardwareAGC = false;
        this.hardwareNS = isNoiseSuppressorSupported();
        this.lowLatencyOutput = isLowLatencyOutputSupported();
        this.lowLatencyInput = isLowLatencyInputSupported();
        this.proAudio = isProAudioSupported();
        this.aAudio = isAAudioSupported();
        if (this.lowLatencyOutput) {
            i = getLowLatencyOutputFramesPerBuffer();
        } else {
            i = getMinOutputFrameSize(this.sampleRate, this.outputChannels);
        }
        this.outputBufferSize = i;
        if (this.lowLatencyInput) {
            i2 = getLowLatencyInputFramesPerBuffer();
        } else {
            i2 = getMinInputFrameSize(this.sampleRate, this.inputChannels);
        }
        this.inputBufferSize = i2;
    }

    private boolean hasEarpiece() {
        return ContextUtils.getApplicationContext().getPackageManager().hasSystemFeature("android.hardware.telephony");
    }

    private boolean isLowLatencyOutputSupported() {
        return ContextUtils.getApplicationContext().getPackageManager().hasSystemFeature("android.hardware.audio.low_latency");
    }

    public boolean isLowLatencyInputSupported() {
        if (!WebRtcAudioUtils.runningOnLollipopOrHigher() || !isLowLatencyOutputSupported()) {
            return false;
        }
        return blacklistDeviceForAAudioUsage;
    }

    @TargetApi(23)
    private boolean isProAudioSupported() {
        if (!WebRtcAudioUtils.runningOnMarshmallowOrHigher() || !ContextUtils.getApplicationContext().getPackageManager().hasSystemFeature("android.hardware.audio.pro")) {
            return false;
        }
        return blacklistDeviceForAAudioUsage;
    }

    private boolean isAAudioSupported() {
        Logging.w(TAG, "AAudio support is currently disabled on all devices!");
        return false;
    }

    private int getNativeOutputSampleRate() {
        int i;
        if (WebRtcAudioUtils.runningOnEmulator()) {
            Logging.d(TAG, "Running emulator, overriding sample rate to 8 kHz.");
            return b.MAX_BYTE_SIZE_PER_FILE;
        } else if (WebRtcAudioUtils.isDefaultSampleRateOverridden()) {
            Logging.d(TAG, "Default sample rate is overriden to " + WebRtcAudioUtils.getDefaultSampleRateHz() + " Hz");
            return WebRtcAudioUtils.getDefaultSampleRateHz();
        } else {
            if (WebRtcAudioUtils.runningOnJellyBeanMR1OrHigher()) {
                i = getSampleRateOnJellyBeanMR10OrHigher();
            } else {
                i = WebRtcAudioUtils.getDefaultSampleRateHz();
            }
            Logging.d(TAG, "Sample rate is set to " + i + " Hz");
            return i;
        }
    }

    @TargetApi(17)
    private int getSampleRateOnJellyBeanMR10OrHigher() {
        String property = this.audioManager.getProperty("android.media.property.OUTPUT_SAMPLE_RATE");
        if (property == null) {
            return WebRtcAudioUtils.getDefaultSampleRateHz();
        }
        return Integer.parseInt(property);
    }

    @TargetApi(17)
    private int getLowLatencyOutputFramesPerBuffer() {
        String property;
        assertTrue(isLowLatencyOutputSupported());
        if (WebRtcAudioUtils.runningOnJellyBeanMR1OrHigher() && (property = this.audioManager.getProperty("android.media.property.OUTPUT_FRAMES_PER_BUFFER")) != null) {
            return Integer.parseInt(property);
        }
        return 256;
    }

    private static boolean isAcousticEchoCancelerSupported() {
        return WebRtcAudioEffects.canUseAcousticEchoCanceler();
    }

    private static boolean isNoiseSuppressorSupported() {
        return WebRtcAudioEffects.canUseNoiseSuppressor();
    }

    private static int getMinOutputFrameSize(int i, int i2) {
        return AudioTrack.getMinBufferSize(i, i2 == 1 ? 4 : 12, 2) / (i2 * 2);
    }

    private int getLowLatencyInputFramesPerBuffer() {
        assertTrue(isLowLatencyInputSupported());
        return getLowLatencyOutputFramesPerBuffer();
    }

    private static int getMinInputFrameSize(int i, int i2) {
        return AudioRecord.getMinBufferSize(i, i2 == 1 ? 16 : 12, 2) / (i2 * 2);
    }

    private static void assertTrue(boolean z) {
        if (!z) {
            throw new AssertionError("Expected condition to be true");
        }
    }
}
