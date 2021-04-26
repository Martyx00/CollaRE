package org.webrtc;

public interface RefCounted {
    void release();

    void retain();
}
