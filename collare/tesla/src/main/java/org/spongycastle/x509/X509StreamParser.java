package org.spongycastle.x509;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.security.NoSuchAlgorithmException;
import java.security.Provider;
import java.util.Collection;
import org.spongycastle.x509.X509Util;
import org.spongycastle.x509.util.StreamParser;

public class X509StreamParser implements StreamParser {
    private Provider _provider;
    private X509StreamParserSpi _spi;

    public static X509StreamParser getInstance(String str) {
        try {
            return createParser(X509Util.getImplementation("X509StreamParser", str));
        } catch (NoSuchAlgorithmException e) {
            throw new NoSuchParserException(e.getMessage());
        }
    }

    public static X509StreamParser getInstance(String str, String str2) {
        return getInstance(str, X509Util.getProvider(str2));
    }

    public static X509StreamParser getInstance(String str, Provider provider) {
        try {
            return createParser(X509Util.getImplementation("X509StreamParser", str, provider));
        } catch (NoSuchAlgorithmException e) {
            throw new NoSuchParserException(e.getMessage());
        }
    }

    private static X509StreamParser createParser(X509Util.Implementation implementation) {
        return new X509StreamParser(implementation.getProvider(), (X509StreamParserSpi) implementation.getEngine());
    }

    private X509StreamParser(Provider provider, X509StreamParserSpi x509StreamParserSpi) {
        this._provider = provider;
        this._spi = x509StreamParserSpi;
    }

    public Provider getProvider() {
        return this._provider;
    }

    public void init(InputStream inputStream) {
        this._spi.engineInit(inputStream);
    }

    public void init(byte[] bArr) {
        this._spi.engineInit(new ByteArrayInputStream(bArr));
    }

    @Override // org.spongycastle.x509.util.StreamParser
    public Object read() {
        return this._spi.engineRead();
    }

    @Override // org.spongycastle.x509.util.StreamParser
    public Collection readAll() {
        return this._spi.engineReadAll();
    }
}
