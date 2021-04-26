package org.webrtc;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MediaStream {
    private static final String TAG = "MediaStream";
    public final List<AudioTrack> audioTracks = new ArrayList();
    final long nativeStream;
    public final List<VideoTrack> preservedVideoTracks = new ArrayList();
    public final List<VideoTrack> videoTracks = new ArrayList();

    private static native boolean nativeAddAudioTrackToNativeStream(long j, long j2);

    private static native boolean nativeAddVideoTrackToNativeStream(long j, long j2);

    private static native String nativeGetId(long j);

    private static native boolean nativeRemoveAudioTrack(long j, long j2);

    private static native boolean nativeRemoveVideoTrack(long j, long j2);

    @CalledByNative
    public MediaStream(long j) {
        this.nativeStream = j;
    }

    public boolean addTrack(AudioTrack audioTrack) {
        if (!nativeAddAudioTrackToNativeStream(this.nativeStream, audioTrack.nativeTrack)) {
            return false;
        }
        this.audioTracks.add(audioTrack);
        return true;
    }

    public boolean addTrack(VideoTrack videoTrack) {
        if (!nativeAddVideoTrackToNativeStream(this.nativeStream, videoTrack.nativeTrack)) {
            return false;
        }
        this.videoTracks.add(videoTrack);
        return true;
    }

    public boolean addPreservedTrack(VideoTrack videoTrack) {
        if (!nativeAddVideoTrackToNativeStream(this.nativeStream, videoTrack.nativeTrack)) {
            return false;
        }
        this.preservedVideoTracks.add(videoTrack);
        return true;
    }

    public boolean removeTrack(AudioTrack audioTrack) {
        this.audioTracks.remove(audioTrack);
        return nativeRemoveAudioTrack(this.nativeStream, audioTrack.nativeTrack);
    }

    public boolean removeTrack(VideoTrack videoTrack) {
        this.videoTracks.remove(videoTrack);
        this.preservedVideoTracks.remove(videoTrack);
        return nativeRemoveVideoTrack(this.nativeStream, videoTrack.nativeTrack);
    }

    @CalledByNative
    public void dispose() {
        while (!this.audioTracks.isEmpty()) {
            AudioTrack audioTrack = this.audioTracks.get(0);
            removeTrack(audioTrack);
            audioTrack.dispose();
        }
        while (!this.videoTracks.isEmpty()) {
            VideoTrack videoTrack = this.videoTracks.get(0);
            removeTrack(videoTrack);
            videoTrack.dispose();
        }
        while (!this.preservedVideoTracks.isEmpty()) {
            removeTrack(this.preservedVideoTracks.get(0));
        }
        JniCommon.nativeReleaseRef(this.nativeStream);
    }

    public String getId() {
        return nativeGetId(this.nativeStream);
    }

    public String toString() {
        return "[" + getId() + ":A=" + this.audioTracks.size() + ":V=" + this.videoTracks.size() + "]";
    }

    /* access modifiers changed from: package-private */
    @CalledByNative
    public void addNativeAudioTrack(long j) {
        this.audioTracks.add(new AudioTrack(j));
    }

    /* access modifiers changed from: package-private */
    @CalledByNative
    public void addNativeVideoTrack(long j) {
        this.videoTracks.add(new VideoTrack(j));
    }

    /* access modifiers changed from: package-private */
    @CalledByNative
    public void removeAudioTrack(long j) {
        removeMediaStreamTrack(this.audioTracks, j);
    }

    /* access modifiers changed from: package-private */
    @CalledByNative
    public void removeVideoTrack(long j) {
        removeMediaStreamTrack(this.videoTracks, j);
    }

    private static void removeMediaStreamTrack(List<? extends MediaStreamTrack> list, long j) {
        Iterator<? extends MediaStreamTrack> it = list.iterator();
        while (it.hasNext()) {
            MediaStreamTrack mediaStreamTrack = (MediaStreamTrack) it.next();
            if (mediaStreamTrack.nativeTrack == j) {
                mediaStreamTrack.dispose();
                it.remove();
                return;
            }
        }
        Logging.e(TAG, "Couldn't not find track");
    }
}
