package org.spongycastle.asn1;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

public class ASN1StreamParser {
    private final InputStream _in;
    private final int _limit;
    private final byte[][] tmpBuffers;

    public ASN1StreamParser(InputStream inputStream) {
        this(inputStream, StreamUtil.findLimit(inputStream));
    }

    public ASN1StreamParser(InputStream inputStream, int i) {
        this._in = inputStream;
        this._limit = i;
        this.tmpBuffers = new byte[11][];
    }

    public ASN1StreamParser(byte[] bArr) {
        this(new ByteArrayInputStream(bArr), bArr.length);
    }

    /* access modifiers changed from: package-private */
    public ASN1Encodable readIndef(int i) {
        if (i == 4) {
            return new BEROctetStringParser(this);
        }
        if (i == 8) {
            return new DERExternalParser(this);
        }
        switch (i) {
            case 16:
                return new BERSequenceParser(this);
            case 17:
                return new BERSetParser(this);
            default:
                throw new ASN1Exception("unknown BER object encountered: 0x" + Integer.toHexString(i));
        }
    }

    /* access modifiers changed from: package-private */
    public ASN1Encodable readImplicit(boolean z, int i) {
        InputStream inputStream = this._in;
        if (!(inputStream instanceof IndefiniteLengthInputStream)) {
            if (z) {
                if (i == 4) {
                    return new BEROctetStringParser(this);
                }
                switch (i) {
                    case 16:
                        return new DERSequenceParser(this);
                    case 17:
                        return new DERSetParser(this);
                }
            } else if (i == 4) {
                return new DEROctetStringParser((DefiniteLengthInputStream) inputStream);
            } else {
                switch (i) {
                    case 16:
                        throw new ASN1Exception("sets must use constructed encoding (see X.690 8.11.1/8.12.1)");
                    case 17:
                        throw new ASN1Exception("sequences must use constructed encoding (see X.690 8.9.1/8.10.1)");
                }
            }
            throw new ASN1Exception("implicit tagging not implemented");
        } else if (z) {
            return readIndef(i);
        } else {
            throw new IOException("indefinite-length primitive encoding encountered");
        }
    }

    /* access modifiers changed from: package-private */
    public ASN1Primitive readTaggedObject(boolean z, int i) {
        if (!z) {
            return new DERTaggedObject(false, i, new DEROctetString(((DefiniteLengthInputStream) this._in).toByteArray()));
        }
        ASN1EncodableVector readVector = readVector();
        if (this._in instanceof IndefiniteLengthInputStream) {
            if (readVector.size() == 1) {
                return new BERTaggedObject(true, i, readVector.get(0));
            }
            return new BERTaggedObject(false, i, BERFactory.createSequence(readVector));
        } else if (readVector.size() == 1) {
            return new DERTaggedObject(true, i, readVector.get(0));
        } else {
            return new DERTaggedObject(false, i, DERFactory.createSequence(readVector));
        }
    }

    public ASN1Encodable readObject() {
        int read = this._in.read();
        if (read == -1) {
            return null;
        }
        boolean z = false;
        set00Check(false);
        int readTagNumber = ASN1InputStream.readTagNumber(this._in, read);
        if ((read & 32) != 0) {
            z = true;
        }
        int readLength = ASN1InputStream.readLength(this._in, this._limit);
        if (readLength >= 0) {
            DefiniteLengthInputStream definiteLengthInputStream = new DefiniteLengthInputStream(this._in, readLength);
            if ((read & 64) != 0) {
                return new DERApplicationSpecific(z, readTagNumber, definiteLengthInputStream.toByteArray());
            }
            if ((read & 128) != 0) {
                return new BERTaggedObjectParser(z, readTagNumber, new ASN1StreamParser(definiteLengthInputStream));
            }
            if (z) {
                if (readTagNumber == 4) {
                    return new BEROctetStringParser(new ASN1StreamParser(definiteLengthInputStream));
                }
                if (readTagNumber == 8) {
                    return new DERExternalParser(new ASN1StreamParser(definiteLengthInputStream));
                }
                switch (readTagNumber) {
                    case 16:
                        return new DERSequenceParser(new ASN1StreamParser(definiteLengthInputStream));
                    case 17:
                        return new DERSetParser(new ASN1StreamParser(definiteLengthInputStream));
                    default:
                        throw new IOException("unknown tag " + readTagNumber + " encountered");
                }
            } else if (readTagNumber == 4) {
                return new DEROctetStringParser(definiteLengthInputStream);
            } else {
                try {
                    return ASN1InputStream.createPrimitiveDERObject(readTagNumber, definiteLengthInputStream, this.tmpBuffers);
                } catch (IllegalArgumentException e) {
                    throw new ASN1Exception("corrupted stream detected", e);
                }
            }
        } else if (z) {
            ASN1StreamParser aSN1StreamParser = new ASN1StreamParser(new IndefiniteLengthInputStream(this._in, this._limit), this._limit);
            if ((read & 64) != 0) {
                return new BERApplicationSpecificParser(readTagNumber, aSN1StreamParser);
            }
            if ((read & 128) != 0) {
                return new BERTaggedObjectParser(true, readTagNumber, aSN1StreamParser);
            }
            return aSN1StreamParser.readIndef(readTagNumber);
        } else {
            throw new IOException("indefinite-length primitive encoding encountered");
        }
    }

    private void set00Check(boolean z) {
        InputStream inputStream = this._in;
        if (inputStream instanceof IndefiniteLengthInputStream) {
            ((IndefiniteLengthInputStream) inputStream).setEofOn00(z);
        }
    }

    /* access modifiers changed from: package-private */
    public ASN1EncodableVector readVector() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        while (true) {
            ASN1Encodable readObject = readObject();
            if (readObject == null) {
                return aSN1EncodableVector;
            }
            if (readObject instanceof InMemoryRepresentable) {
                aSN1EncodableVector.add(((InMemoryRepresentable) readObject).getLoadedObject());
            } else {
                aSN1EncodableVector.add(readObject.toASN1Primitive());
            }
        }
    }
}
