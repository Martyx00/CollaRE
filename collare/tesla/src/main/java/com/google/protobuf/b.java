package com.google.protobuf;

import com.google.protobuf.ad;
import com.google.protobuf.b;
import com.google.protobuf.b.a;
import com.google.protobuf.g;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* compiled from: AbstractMessageLite */
public abstract class b<MessageType extends b<MessageType, BuilderType>, BuilderType extends a<MessageType, BuilderType>> implements ad {
    protected int memoizedHashCode = 0;

    @Override // com.google.protobuf.ad
    public g toByteString() {
        try {
            g.e c2 = g.c(getSerializedSize());
            writeTo(c2.b());
            return c2.a();
        } catch (IOException e) {
            throw new RuntimeException(getSerializingExceptionMessage("ByteString"), e);
        }
    }

    @Override // com.google.protobuf.ad
    public byte[] toByteArray() {
        try {
            byte[] bArr = new byte[getSerializedSize()];
            i a2 = i.a(bArr);
            writeTo(a2);
            a2.c();
            return bArr;
        } catch (IOException e) {
            throw new RuntimeException(getSerializingExceptionMessage("byte array"), e);
        }
    }

    public void writeTo(OutputStream outputStream) {
        i a2 = i.a(outputStream, i.a(getSerializedSize()));
        writeTo(a2);
        a2.a();
    }

    public void writeDelimitedTo(OutputStream outputStream) {
        int serializedSize = getSerializedSize();
        i a2 = i.a(outputStream, i.a(i.r(serializedSize) + serializedSize));
        a2.q(serializedSize);
        writeTo(a2);
        a2.a();
    }

    /* access modifiers changed from: package-private */
    public int getMemoizedSerializedSize() {
        throw new UnsupportedOperationException();
    }

    /* access modifiers changed from: package-private */
    public void setMemoizedSerializedSize(int i) {
        throw new UnsupportedOperationException();
    }

    /* access modifiers changed from: package-private */
    public au newUninitializedMessageException() {
        return new au(this);
    }

    private String getSerializingExceptionMessage(String str) {
        return "Serializing " + getClass().getName() + " to a " + str + " threw an IOException (should never happen).";
    }

    protected static void checkByteStringIsUtf8(g gVar) {
        if (!gVar.f()) {
            throw new IllegalArgumentException("Byte string is not UTF-8.");
        }
    }

    @Deprecated
    protected static <T> void addAll(Iterable<T> iterable, Collection<? super T> collection) {
        a.a(iterable, (List) collection);
    }

    protected static <T> void addAll(Iterable<T> iterable, List<? super T> list) {
        a.a(iterable, list);
    }

    /* compiled from: AbstractMessageLite */
    public static abstract class a<MessageType extends b<MessageType, BuilderType>, BuilderType extends a<MessageType, BuilderType>> implements ad.a {
        /* renamed from: b */
        public abstract BuilderType c(h hVar, q qVar);

        /* renamed from: d */
        public abstract BuilderType clone();

        public BuilderType b(g gVar, q qVar) {
            try {
                h g = gVar.g();
                c(g, qVar);
                g.a(0);
                return this;
            } catch (v e) {
                throw e;
            } catch (IOException e2) {
                throw new RuntimeException(a("ByteString"), e2);
            }
        }

        /* renamed from: com.google.protobuf.b$a$a  reason: collision with other inner class name */
        /* compiled from: AbstractMessageLite */
        static final class C0072a extends FilterInputStream {

            /* renamed from: a  reason: collision with root package name */
            private int f4159a;

            C0072a(InputStream inputStream, int i) {
                super(inputStream);
                this.f4159a = i;
            }

            @Override // java.io.FilterInputStream, java.io.InputStream
            public int available() {
                return Math.min(super.available(), this.f4159a);
            }

            @Override // java.io.FilterInputStream, java.io.InputStream
            public int read() {
                if (this.f4159a <= 0) {
                    return -1;
                }
                int read = super.read();
                if (read >= 0) {
                    this.f4159a--;
                }
                return read;
            }

            @Override // java.io.FilterInputStream, java.io.InputStream
            public int read(byte[] bArr, int i, int i2) {
                int i3 = this.f4159a;
                if (i3 <= 0) {
                    return -1;
                }
                int read = super.read(bArr, i, Math.min(i2, i3));
                if (read >= 0) {
                    this.f4159a -= read;
                }
                return read;
            }

            @Override // java.io.FilterInputStream, java.io.InputStream
            public long skip(long j) {
                long skip = super.skip(Math.min(j, (long) this.f4159a));
                if (skip >= 0) {
                    this.f4159a = (int) (((long) this.f4159a) - skip);
                }
                return skip;
            }
        }

        private String a(String str) {
            return "Reading " + getClass().getName() + " from a " + str + " threw an IOException (should never happen).";
        }

        private static <T> void b(Iterable<T> iterable, List<? super T> list) {
            if ((list instanceof ArrayList) && (iterable instanceof Collection)) {
                ((ArrayList) list).ensureCapacity(list.size() + ((Collection) iterable).size());
            }
            int size = list.size();
            for (T t : iterable) {
                if (t == null) {
                    String str = "Element at index " + (list.size() - size) + " is null.";
                    for (int size2 = list.size() - 1; size2 >= size; size2--) {
                        list.remove(size2);
                    }
                    throw new NullPointerException(str);
                }
                list.add(t);
            }
        }

        protected static <T> void a(Iterable<T> iterable, List<? super T> list) {
            u.a(iterable);
            if (iterable instanceof z) {
                List<?> d2 = ((z) iterable).d();
                z zVar = (z) list;
                int size = list.size();
                for (Object obj : d2) {
                    if (obj == null) {
                        String str = "Element at index " + (zVar.size() - size) + " is null.";
                        for (int size2 = zVar.size() - 1; size2 >= size; size2--) {
                            zVar.remove(size2);
                        }
                        throw new NullPointerException(str);
                    } else if (obj instanceof g) {
                        zVar.a((g) obj);
                    } else {
                        zVar.add((String) obj);
                    }
                }
            } else if (iterable instanceof ak) {
                list.addAll((Collection) iterable);
            } else {
                b(iterable, list);
            }
        }
    }
}
