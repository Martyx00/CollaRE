package org.spongycastle.crypto.tls;

import java.util.Hashtable;
import java.util.Vector;

public interface TlsServer extends TlsPeer {
    CertificateRequest getCertificateRequest();

    CertificateStatus getCertificateStatus();

    TlsCredentials getCredentials();

    TlsKeyExchange getKeyExchange();

    NewSessionTicket getNewSessionTicket();

    int getSelectedCipherSuite();

    short getSelectedCompressionMethod();

    Hashtable getServerExtensions();

    Vector getServerSupplementalData();

    ProtocolVersion getServerVersion();

    void init(TlsServerContext tlsServerContext);

    void notifyClientCertificate(Certificate certificate);

    void notifyClientVersion(ProtocolVersion protocolVersion);

    void notifyFallback(boolean z);

    void notifyOfferedCipherSuites(int[] iArr);

    void notifyOfferedCompressionMethods(short[] sArr);

    void processClientExtensions(Hashtable hashtable);

    void processClientSupplementalData(Vector vector);
}
