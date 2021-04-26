package com.google.android.gms.internal.measurement;

import java.net.InetAddress;
import java.net.Socket;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

/* access modifiers changed from: package-private */
public final class zzke extends SSLSocketFactory {
    private final SSLSocketFactory zzatb;

    zzke() {
        this(HttpsURLConnection.getDefaultSSLSocketFactory());
    }

    private zzke(SSLSocketFactory sSLSocketFactory) {
        this.zzatb = sSLSocketFactory;
    }

    private final SSLSocket zza(SSLSocket sSLSocket) {
        return new zzkf(this, sSLSocket);
    }

    @Override // javax.net.SocketFactory
    public final Socket createSocket() {
        return zza((SSLSocket) this.zzatb.createSocket());
    }

    @Override // javax.net.SocketFactory
    public final Socket createSocket(String str, int i) {
        return zza((SSLSocket) this.zzatb.createSocket(str, i));
    }

    @Override // javax.net.SocketFactory
    public final Socket createSocket(String str, int i, InetAddress inetAddress, int i2) {
        return zza((SSLSocket) this.zzatb.createSocket(str, i, inetAddress, i2));
    }

    @Override // javax.net.SocketFactory
    public final Socket createSocket(InetAddress inetAddress, int i) {
        return zza((SSLSocket) this.zzatb.createSocket(inetAddress, i));
    }

    @Override // javax.net.SocketFactory
    public final Socket createSocket(InetAddress inetAddress, int i, InetAddress inetAddress2, int i2) {
        return zza((SSLSocket) this.zzatb.createSocket(inetAddress, i, inetAddress2, i2));
    }

    @Override // javax.net.ssl.SSLSocketFactory
    public final Socket createSocket(Socket socket, String str, int i, boolean z) {
        return zza((SSLSocket) this.zzatb.createSocket(socket, str, i, z));
    }

    public final String[] getDefaultCipherSuites() {
        return this.zzatb.getDefaultCipherSuites();
    }

    public final String[] getSupportedCipherSuites() {
        return this.zzatb.getSupportedCipherSuites();
    }
}
