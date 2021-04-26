package com.google.android.gms.internal.measurement;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.SocketAddress;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Arrays;
import javax.net.ssl.HandshakeCompletedListener;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;

/* access modifiers changed from: package-private */
public final class zzkf extends SSLSocket {
    private final SSLSocket zzatc;

    zzkf(zzke zzke, SSLSocket sSLSocket) {
        this.zzatc = sSLSocket;
    }

    public final void addHandshakeCompletedListener(HandshakeCompletedListener handshakeCompletedListener) {
        this.zzatc.addHandshakeCompletedListener(handshakeCompletedListener);
    }

    @Override // java.net.Socket
    public final void bind(SocketAddress socketAddress) {
        this.zzatc.bind(socketAddress);
    }

    @Override // java.net.Socket, java.io.Closeable, java.lang.AutoCloseable
    public final synchronized void close() {
        this.zzatc.close();
    }

    @Override // java.net.Socket
    public final void connect(SocketAddress socketAddress) {
        this.zzatc.connect(socketAddress);
    }

    @Override // java.net.Socket
    public final void connect(SocketAddress socketAddress, int i) {
        this.zzatc.connect(socketAddress, i);
    }

    public final boolean equals(Object obj) {
        return this.zzatc.equals(obj);
    }

    public final SocketChannel getChannel() {
        return this.zzatc.getChannel();
    }

    public final boolean getEnableSessionCreation() {
        return this.zzatc.getEnableSessionCreation();
    }

    public final String[] getEnabledCipherSuites() {
        return this.zzatc.getEnabledCipherSuites();
    }

    public final String[] getEnabledProtocols() {
        return this.zzatc.getEnabledProtocols();
    }

    public final InetAddress getInetAddress() {
        return this.zzatc.getInetAddress();
    }

    @Override // java.net.Socket
    public final InputStream getInputStream() {
        return this.zzatc.getInputStream();
    }

    @Override // java.net.Socket
    public final boolean getKeepAlive() {
        return this.zzatc.getKeepAlive();
    }

    public final InetAddress getLocalAddress() {
        return this.zzatc.getLocalAddress();
    }

    public final int getLocalPort() {
        return this.zzatc.getLocalPort();
    }

    public final SocketAddress getLocalSocketAddress() {
        return this.zzatc.getLocalSocketAddress();
    }

    public final boolean getNeedClientAuth() {
        return this.zzatc.getNeedClientAuth();
    }

    @Override // java.net.Socket
    public final boolean getOOBInline() {
        return this.zzatc.getOOBInline();
    }

    @Override // java.net.Socket
    public final OutputStream getOutputStream() {
        return this.zzatc.getOutputStream();
    }

    public final int getPort() {
        return this.zzatc.getPort();
    }

    @Override // java.net.Socket
    public final synchronized int getReceiveBufferSize() {
        return this.zzatc.getReceiveBufferSize();
    }

    public final SocketAddress getRemoteSocketAddress() {
        return this.zzatc.getRemoteSocketAddress();
    }

    @Override // java.net.Socket
    public final boolean getReuseAddress() {
        return this.zzatc.getReuseAddress();
    }

    @Override // java.net.Socket
    public final synchronized int getSendBufferSize() {
        return this.zzatc.getSendBufferSize();
    }

    public final SSLSession getSession() {
        return this.zzatc.getSession();
    }

    @Override // java.net.Socket
    public final int getSoLinger() {
        return this.zzatc.getSoLinger();
    }

    @Override // java.net.Socket
    public final synchronized int getSoTimeout() {
        return this.zzatc.getSoTimeout();
    }

    public final String[] getSupportedCipherSuites() {
        return this.zzatc.getSupportedCipherSuites();
    }

    public final String[] getSupportedProtocols() {
        return this.zzatc.getSupportedProtocols();
    }

    @Override // java.net.Socket
    public final boolean getTcpNoDelay() {
        return this.zzatc.getTcpNoDelay();
    }

    @Override // java.net.Socket
    public final int getTrafficClass() {
        return this.zzatc.getTrafficClass();
    }

    public final boolean getUseClientMode() {
        return this.zzatc.getUseClientMode();
    }

    public final boolean getWantClientAuth() {
        return this.zzatc.getWantClientAuth();
    }

    public final boolean isBound() {
        return this.zzatc.isBound();
    }

    public final boolean isClosed() {
        return this.zzatc.isClosed();
    }

    public final boolean isConnected() {
        return this.zzatc.isConnected();
    }

    public final boolean isInputShutdown() {
        return this.zzatc.isInputShutdown();
    }

    public final boolean isOutputShutdown() {
        return this.zzatc.isOutputShutdown();
    }

    public final void removeHandshakeCompletedListener(HandshakeCompletedListener handshakeCompletedListener) {
        this.zzatc.removeHandshakeCompletedListener(handshakeCompletedListener);
    }

    @Override // java.net.Socket
    public final void sendUrgentData(int i) {
        this.zzatc.sendUrgentData(i);
    }

    public final void setEnableSessionCreation(boolean z) {
        this.zzatc.setEnableSessionCreation(z);
    }

    public final void setEnabledCipherSuites(String[] strArr) {
        this.zzatc.setEnabledCipherSuites(strArr);
    }

    public final void setEnabledProtocols(String[] strArr) {
        if (strArr != null && Arrays.asList(strArr).contains("SSLv3")) {
            ArrayList arrayList = new ArrayList(Arrays.asList(this.zzatc.getEnabledProtocols()));
            if (arrayList.size() > 1) {
                arrayList.remove("SSLv3");
            }
            strArr = (String[]) arrayList.toArray(new String[arrayList.size()]);
        }
        this.zzatc.setEnabledProtocols(strArr);
    }

    @Override // java.net.Socket
    public final void setKeepAlive(boolean z) {
        this.zzatc.setKeepAlive(z);
    }

    public final void setNeedClientAuth(boolean z) {
        this.zzatc.setNeedClientAuth(z);
    }

    @Override // java.net.Socket
    public final void setOOBInline(boolean z) {
        this.zzatc.setOOBInline(z);
    }

    public final void setPerformancePreferences(int i, int i2, int i3) {
        this.zzatc.setPerformancePreferences(i, i2, i3);
    }

    @Override // java.net.Socket
    public final synchronized void setReceiveBufferSize(int i) {
        this.zzatc.setReceiveBufferSize(i);
    }

    @Override // java.net.Socket
    public final void setReuseAddress(boolean z) {
        this.zzatc.setReuseAddress(z);
    }

    @Override // java.net.Socket
    public final synchronized void setSendBufferSize(int i) {
        this.zzatc.setSendBufferSize(i);
    }

    @Override // java.net.Socket
    public final void setSoLinger(boolean z, int i) {
        this.zzatc.setSoLinger(z, i);
    }

    @Override // java.net.Socket
    public final synchronized void setSoTimeout(int i) {
        this.zzatc.setSoTimeout(i);
    }

    @Override // java.net.Socket
    public final void setTcpNoDelay(boolean z) {
        this.zzatc.setTcpNoDelay(z);
    }

    @Override // java.net.Socket
    public final void setTrafficClass(int i) {
        this.zzatc.setTrafficClass(i);
    }

    public final void setUseClientMode(boolean z) {
        this.zzatc.setUseClientMode(z);
    }

    public final void setWantClientAuth(boolean z) {
        this.zzatc.setWantClientAuth(z);
    }

    @Override // java.net.Socket
    public final void shutdownInput() {
        this.zzatc.shutdownInput();
    }

    @Override // java.net.Socket
    public final void shutdownOutput() {
        this.zzatc.shutdownOutput();
    }

    @Override // javax.net.ssl.SSLSocket
    public final void startHandshake() {
        this.zzatc.startHandshake();
    }

    public final String toString() {
        return this.zzatc.toString();
    }
}
