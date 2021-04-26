package com.google.protobuf;

/* compiled from: MessageLite */
public interface ad extends ae {

    /* compiled from: MessageLite */
    public interface a extends ae, Cloneable {
        a c(h hVar, q qVar);

        ad u();
    }

    a D();

    aj<? extends ad> getParserForType();

    int getSerializedSize();

    byte[] toByteArray();

    g toByteString();

    void writeTo(i iVar);
}
