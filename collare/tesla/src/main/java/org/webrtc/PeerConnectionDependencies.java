package org.webrtc;

import org.webrtc.PeerConnection;

public final class PeerConnectionDependencies {
    private PeerConnection.Observer observer;

    public static class Builder {
        private PeerConnection.Observer observer;

        private Builder(PeerConnection.Observer observer2) {
            this.observer = observer2;
        }

        public PeerConnectionDependencies createPeerConnectionDependencies() {
            return new PeerConnectionDependencies(this.observer);
        }
    }

    public static Builder builder(PeerConnection.Observer observer2) {
        return new Builder(observer2);
    }

    /* access modifiers changed from: package-private */
    public PeerConnection.Observer getObserver() {
        return this.observer;
    }

    private PeerConnectionDependencies(PeerConnection.Observer observer2) {
        this.observer = observer2;
    }
}
