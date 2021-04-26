package org.webrtc.audio;

import android.annotation.TargetApi;
import android.content.Context;
import android.media.AudioManager;
import android.media.AudioRecord;
import android.media.AudioTrack;
import io.a.a.a.a.d.b;
import org.webrtc.CalledByNative;
import org.webrtc.Logging;
import org.webrtc.MediaStreamTrack;

class WebRtcAudioManager {
    private static final int BITS_PER_SAMPLE = 16;
    private static final int DEFAULT_FRAME_PER_BUFFER = 256;
    private static final int DEFAULT_SAMPLE_RATE_HZ = 16000;
    private static final String TAG = "WebRtcAudioManagerExternal";

    WebRtcAudioManager() {
    }

    @CalledByNative
    static AudioManager getAudioManager(Context context) {
        return (AudioManager) context.getSystemService(MediaStreamTrack.AUDIO_TRACK_KIND);
    }

    @CalledByNative
    static int getOutputBufferSize(Context context, AudioManager audioManager, int i, int i2) {
        if (isLowLatencyOutputSupported(context)) {
            return getLowLatencyFramesPerBuffer(audioManager);
        }
        return getMinOutputFrameSize(i, i2);
    }

    @CalledByNative
    static int getInputBufferSize(Context context, AudioManager audioManager, int i, int i2) {
        if (isLowLatencyInputSupported(context)) {
            return getLowLatencyFramesPerBuffer(audioManager);
        }
        return getMinInputFrameSize(i, i2);
    }

    private static boolean isLowLatencyOutputSupported(Context context) {
        return context.getPackageManager().hasSystemFeature("android.hardware.audio.low_latency");
    }

    private static boolean isLowLatencyInputSupported(Context context) {
        return WebRtcAudioUtils.runningOnLollipopOrHigher() && isLowLatencyOutputSupported(context);
    }

    @CalledByNative
    static int getSampleRate(AudioManager audioManager) {
        if (WebRtcAudioUtils.runningOnEmulator()) {
            Logging.d(TAG, "Running emulator, overriding sample rate to 8 kHz.");
            return b.MAX_BYTE_SIZE_PER_FILE;
        }
        int sampleRateOnJellyBeanMR10OrHigher = WebRtcAudioUtils.runningOnJellyBeanMR1OrHigher() ? getSampleRateOnJellyBeanMR10OrHigher(audioManager) : DEFAULT_SAMPLE_RATE_HZ;
        Logging.d(TAG, "Sample rate is set to " + sampleRateOnJellyBeanMR10OrHigher + " Hz");
        return sampleRateOnJellyBeanMR10OrHigher;
    }

    @TargetApi(17)
    private static int getSampleRateOnJellyBeanMR10OrHigher(AudioManager audioManager) {
        String property = audioManager.getProperty("android.media.property.OUTPUT_SAMPLE_RATE");
        if (property == null) {
            return DEFAULT_SAMPLE_RATE_HZ;
        }
        return Integer.parseInt(property);
    }

    @TargetApi(17)
    private static int getLowLatencyFramesPerBuffer(AudioManager audioManager) {
        String property;
        if (WebRtcAudioUtils.runningOnJellyBeanMR1OrHigher() && (property = audioManager.getProperty("android.media.property.OUTPUT_FRAMES_PER_BUFFER")) != null) {
            return Integer.parseInt(property);
        }
        return 256;
    }

    private static int getMinOutputFrameSize(int i, int i2) {
        return AudioTrack.getMinBufferSize(i, i2 == 1 ? 4 : 12, 2) / (i2 * 2);
    }

    private static int getMinInputFrameSize(int i, int i2) {
        return AudioRecord.getMinBufferSize(i, i2 == 1 ? 16 : 12, 2) / (i2 * 2);
    }
}
