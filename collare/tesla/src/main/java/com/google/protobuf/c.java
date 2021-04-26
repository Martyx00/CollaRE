package com.google.protobuf;

import com.google.protobuf.ad;
import com.google.protobuf.b;
import java.io.IOException;
import java.io.InputStream;

/* compiled from: AbstractParser */
public abstract class c<MessageType extends ad> implements aj<MessageType> {

    /* renamed from: a  reason: collision with root package name */
    private static final q f4177a = q.d();

    private au a(MessageType messagetype) {
        if (messagetype instanceof b) {
            return ((b) messagetype).newUninitializedMessageException();
        }
        return new au(messagetype);
    }

    private MessageType b(MessageType messagetype) {
        if (messagetype == null || messagetype.isInitialized()) {
            return messagetype;
        }
        throw a(messagetype).a().a(messagetype);
    }

    /* JADX DEBUG: Multi-variable search result rejected for r0v0, resolved type: com.google.protobuf.c<MessageType extends com.google.protobuf.ad> */
    /* JADX WARN: Multi-variable type inference failed */
    /* renamed from: a */
    public MessageType b(h hVar, q qVar) {
        return (MessageType) b((ad) d(hVar, qVar));
    }

    /* renamed from: a */
    public MessageType b(h hVar) {
        return b(hVar, f4177a);
    }

    public MessageType a(g gVar, q qVar) {
        try {
            h g = gVar.g();
            MessageType messagetype = (MessageType) ((ad) d(g, qVar));
            try {
                g.a(0);
                return messagetype;
            } catch (v e) {
                throw e.a(messagetype);
            }
        } catch (v e2) {
            throw e2;
        }
    }

    /* renamed from: b */
    public MessageType c(g gVar, q qVar) {
        return b(a(gVar, qVar));
    }

    public MessageType a(byte[] bArr, int i, int i2, q qVar) {
        try {
            h a2 = h.a(bArr, i, i2);
            MessageType messagetype = (MessageType) ((ad) d(a2, qVar));
            try {
                a2.a(0);
                return messagetype;
            } catch (v e) {
                throw e.a(messagetype);
            }
        } catch (v e2) {
            throw e2;
        }
    }

    public MessageType b(byte[] bArr, int i, int i2, q qVar) {
        return b(a(bArr, i, i2, qVar));
    }

    /* renamed from: a */
    public MessageType b(byte[] bArr, q qVar) {
        return b(bArr, 0, bArr.length, qVar);
    }

    /* renamed from: a */
    public MessageType b(byte[] bArr) {
        return b(bArr, f4177a);
    }

    public MessageType a(InputStream inputStream, q qVar) {
        h a2 = h.a(inputStream);
        MessageType messagetype = (MessageType) ((ad) d(a2, qVar));
        try {
            a2.a(0);
            return messagetype;
        } catch (v e) {
            throw e.a(messagetype);
        }
    }

    /* renamed from: b */
    public MessageType f(InputStream inputStream, q qVar) {
        return b(a(inputStream, qVar));
    }

    /* renamed from: a */
    public MessageType d(InputStream inputStream) {
        return f(inputStream, f4177a);
    }

    public MessageType c(InputStream inputStream, q qVar) {
        try {
            int read = inputStream.read();
            if (read == -1) {
                return null;
            }
            return a(new b.a.C0072a(inputStream, h.a(read, inputStream)), qVar);
        } catch (IOException e) {
            throw new v(e);
        }
    }

    /* renamed from: d */
    public MessageType e(InputStream inputStream, q qVar) {
        return b(c(inputStream, qVar));
    }

    /* renamed from: b */
    public MessageType c(InputStream inputStream) {
        return e(inputStream, f4177a);
    }
}
