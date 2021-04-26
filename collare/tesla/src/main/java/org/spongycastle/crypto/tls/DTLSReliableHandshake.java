package org.spongycastle.crypto.tls;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;
import org.spongycastle.util.Integers;

class DTLSReliableHandshake {
    private static final int MAX_RECEIVE_AHEAD = 10;
    private Hashtable currentInboundFlight = new Hashtable();
    private TlsHandshakeHash handshakeHash;
    private int message_seq = 0;
    private int next_receive_seq = 0;
    private Vector outboundFlight = new Vector();
    private Hashtable previousInboundFlight = null;
    private DTLSRecordLayer recordLayer;
    private boolean sending = true;

    DTLSReliableHandshake(TlsContext tlsContext, DTLSRecordLayer dTLSRecordLayer) {
        this.recordLayer = dTLSRecordLayer;
        this.handshakeHash = new DeferredHash();
        this.handshakeHash.init(tlsContext);
    }

    /* access modifiers changed from: package-private */
    public void notifyHelloComplete() {
        this.handshakeHash = this.handshakeHash.notifyPRFDetermined();
    }

    /* access modifiers changed from: package-private */
    public TlsHandshakeHash getHandshakeHash() {
        return this.handshakeHash;
    }

    /* access modifiers changed from: package-private */
    public TlsHandshakeHash prepareToFinish() {
        TlsHandshakeHash tlsHandshakeHash = this.handshakeHash;
        this.handshakeHash = tlsHandshakeHash.stopTracking();
        return tlsHandshakeHash;
    }

    /* access modifiers changed from: package-private */
    public void sendMessage(short s, byte[] bArr) {
        TlsUtils.checkUint24(bArr.length);
        if (!this.sending) {
            checkInboundFlight();
            this.sending = true;
            this.outboundFlight.removeAllElements();
        }
        int i = this.message_seq;
        this.message_seq = i + 1;
        Message message = new Message(i, s, bArr);
        this.outboundFlight.addElement(message);
        writeMessage(message);
        updateHandshakeMessagesDigest(message);
    }

    /* access modifiers changed from: package-private */
    public byte[] receiveMessageBody(short s) {
        Message receiveMessage = receiveMessage();
        if (receiveMessage.getType() == s) {
            return receiveMessage.getBody();
        }
        throw new TlsFatalAlert(10);
    }

    /* access modifiers changed from: package-private */
    public Message receiveMessage() {
        DTLSReassembler dTLSReassembler;
        byte[] bodyIfComplete;
        DTLSReassembler dTLSReassembler2;
        byte[] bodyIfComplete2;
        if (this.sending) {
            this.sending = false;
            prepareInboundFlight();
        }
        DTLSReassembler dTLSReassembler3 = (DTLSReassembler) this.currentInboundFlight.get(Integers.valueOf(this.next_receive_seq));
        if (dTLSReassembler3 == null || (bodyIfComplete2 = dTLSReassembler3.getBodyIfComplete()) == null) {
            byte[] bArr = null;
            int i = 1000;
            while (true) {
                int receiveLimit = this.recordLayer.getReceiveLimit();
                if (bArr == null || bArr.length < receiveLimit) {
                    bArr = new byte[receiveLimit];
                }
                while (true) {
                    try {
                        int receive = this.recordLayer.receive(bArr, 0, receiveLimit, i);
                        if (receive < 0) {
                            break;
                        } else if (receive >= 12) {
                            int readUint24 = TlsUtils.readUint24(bArr, 9);
                            if (receive == readUint24 + 12) {
                                int readUint16 = TlsUtils.readUint16(bArr, 4);
                                if (readUint16 <= this.next_receive_seq + 10) {
                                    short readUint8 = TlsUtils.readUint8(bArr, 0);
                                    int readUint242 = TlsUtils.readUint24(bArr, 1);
                                    int readUint243 = TlsUtils.readUint24(bArr, 6);
                                    if (readUint243 + readUint24 <= readUint242) {
                                        if (readUint16 >= this.next_receive_seq) {
                                            DTLSReassembler dTLSReassembler4 = (DTLSReassembler) this.currentInboundFlight.get(Integers.valueOf(readUint16));
                                            if (dTLSReassembler4 == null) {
                                                DTLSReassembler dTLSReassembler5 = new DTLSReassembler(readUint8, readUint242);
                                                this.currentInboundFlight.put(Integers.valueOf(readUint16), dTLSReassembler5);
                                                dTLSReassembler = dTLSReassembler5;
                                            } else {
                                                dTLSReassembler = dTLSReassembler4;
                                            }
                                            dTLSReassembler.contributeFragment(readUint8, readUint242, bArr, 12, readUint243, readUint24);
                                            if (readUint16 == this.next_receive_seq && (bodyIfComplete = dTLSReassembler.getBodyIfComplete()) != null) {
                                                this.previousInboundFlight = null;
                                                int i2 = this.next_receive_seq;
                                                this.next_receive_seq = i2 + 1;
                                                return updateHandshakeMessagesDigest(new Message(i2, dTLSReassembler.getMsgType(), bodyIfComplete));
                                            }
                                        } else if (!(this.previousInboundFlight == null || (dTLSReassembler2 = (DTLSReassembler) this.previousInboundFlight.get(Integers.valueOf(readUint16))) == null)) {
                                            dTLSReassembler2.contributeFragment(readUint8, readUint242, bArr, 12, readUint243, readUint24);
                                            if (checkAll(this.previousInboundFlight)) {
                                                resendOutboundFlight();
                                                i = Math.min(i * 2, 60000);
                                                resetAll(this.previousInboundFlight);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    } catch (IOException unused) {
                    }
                }
                resendOutboundFlight();
                i = Math.min(i * 2, 60000);
            }
        } else {
            this.previousInboundFlight = null;
            int i3 = this.next_receive_seq;
            this.next_receive_seq = i3 + 1;
            return updateHandshakeMessagesDigest(new Message(i3, dTLSReassembler3.getMsgType(), bodyIfComplete2));
        }
    }

    /* access modifiers changed from: package-private */
    public void finish() {
        AnonymousClass1 r0;
        if (!this.sending) {
            checkInboundFlight();
        } else if (this.currentInboundFlight != null) {
            r0 = new DTLSHandshakeRetransmit() {
                /* class org.spongycastle.crypto.tls.DTLSReliableHandshake.AnonymousClass1 */

                @Override // org.spongycastle.crypto.tls.DTLSHandshakeRetransmit
                public void receivedHandshakeRecord(int i, byte[] bArr, int i2, int i3) {
                    int readUint16;
                    DTLSReassembler dTLSReassembler;
                    if (i3 >= 12) {
                        int readUint24 = TlsUtils.readUint24(bArr, i2 + 9);
                        if (i3 == readUint24 + 12 && (readUint16 = TlsUtils.readUint16(bArr, i2 + 4)) < DTLSReliableHandshake.this.next_receive_seq) {
                            short readUint8 = TlsUtils.readUint8(bArr, i2);
                            if (i == (readUint8 == 20 ? 1 : 0)) {
                                int readUint242 = TlsUtils.readUint24(bArr, i2 + 1);
                                int readUint243 = TlsUtils.readUint24(bArr, i2 + 6);
                                if (readUint243 + readUint24 <= readUint242 && (dTLSReassembler = (DTLSReassembler) DTLSReliableHandshake.this.currentInboundFlight.get(Integers.valueOf(readUint16))) != null) {
                                    dTLSReassembler.contributeFragment(readUint8, readUint242, bArr, i2 + 12, readUint243, readUint24);
                                    if (DTLSReliableHandshake.checkAll(DTLSReliableHandshake.this.currentInboundFlight)) {
                                        DTLSReliableHandshake.this.resendOutboundFlight();
                                        DTLSReliableHandshake.resetAll(DTLSReliableHandshake.this.currentInboundFlight);
                                    }
                                }
                            }
                        }
                    }
                }
            };
            this.recordLayer.handshakeSuccessful(r0);
        }
        r0 = null;
        this.recordLayer.handshakeSuccessful(r0);
    }

    /* access modifiers changed from: package-private */
    public void resetHandshakeMessagesDigest() {
        this.handshakeHash.reset();
    }

    private void checkInboundFlight() {
        Enumeration keys = this.currentInboundFlight.keys();
        while (keys.hasMoreElements()) {
            ((Integer) keys.nextElement()).intValue();
            int i = this.next_receive_seq;
        }
    }

    private void prepareInboundFlight() {
        resetAll(this.currentInboundFlight);
        this.previousInboundFlight = this.currentInboundFlight;
        this.currentInboundFlight = new Hashtable();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void resendOutboundFlight() {
        this.recordLayer.resetWriteEpoch();
        for (int i = 0; i < this.outboundFlight.size(); i++) {
            writeMessage((Message) this.outboundFlight.elementAt(i));
        }
    }

    private Message updateHandshakeMessagesDigest(Message message) {
        if (message.getType() != 0) {
            byte[] body = message.getBody();
            byte[] bArr = new byte[12];
            TlsUtils.writeUint8(message.getType(), bArr, 0);
            TlsUtils.writeUint24(body.length, bArr, 1);
            TlsUtils.writeUint16(message.getSeq(), bArr, 4);
            TlsUtils.writeUint24(0, bArr, 6);
            TlsUtils.writeUint24(body.length, bArr, 9);
            this.handshakeHash.update(bArr, 0, bArr.length);
            this.handshakeHash.update(body, 0, body.length);
        }
        return message;
    }

    private void writeMessage(Message message) {
        int sendLimit = this.recordLayer.getSendLimit() - 12;
        if (sendLimit >= 1) {
            int length = message.getBody().length;
            int i = 0;
            do {
                int min = Math.min(length - i, sendLimit);
                writeHandshakeFragment(message, i, min);
                i += min;
            } while (i < length);
            return;
        }
        throw new TlsFatalAlert(80);
    }

    private void writeHandshakeFragment(Message message, int i, int i2) {
        RecordLayerBuffer recordLayerBuffer = new RecordLayerBuffer(i2 + 12);
        TlsUtils.writeUint8(message.getType(), (OutputStream) recordLayerBuffer);
        TlsUtils.writeUint24(message.getBody().length, recordLayerBuffer);
        TlsUtils.writeUint16(message.getSeq(), recordLayerBuffer);
        TlsUtils.writeUint24(i, recordLayerBuffer);
        TlsUtils.writeUint24(i2, recordLayerBuffer);
        recordLayerBuffer.write(message.getBody(), i, i2);
        recordLayerBuffer.sendToRecordLayer(this.recordLayer);
    }

    /* access modifiers changed from: private */
    public static boolean checkAll(Hashtable hashtable) {
        Enumeration elements = hashtable.elements();
        while (elements.hasMoreElements()) {
            if (((DTLSReassembler) elements.nextElement()).getBodyIfComplete() == null) {
                return false;
            }
        }
        return true;
    }

    /* access modifiers changed from: private */
    public static void resetAll(Hashtable hashtable) {
        Enumeration elements = hashtable.elements();
        while (elements.hasMoreElements()) {
            ((DTLSReassembler) elements.nextElement()).reset();
        }
    }

    /* access modifiers changed from: package-private */
    public static class Message {
        private final byte[] body;
        private final int message_seq;
        private final short msg_type;

        private Message(int i, short s, byte[] bArr) {
            this.message_seq = i;
            this.msg_type = s;
            this.body = bArr;
        }

        public int getSeq() {
            return this.message_seq;
        }

        public short getType() {
            return this.msg_type;
        }

        public byte[] getBody() {
            return this.body;
        }
    }

    /* access modifiers changed from: package-private */
    public static class RecordLayerBuffer extends ByteArrayOutputStream {
        RecordLayerBuffer(int i) {
            super(i);
        }

        /* access modifiers changed from: package-private */
        public void sendToRecordLayer(DTLSRecordLayer dTLSRecordLayer) {
            dTLSRecordLayer.send(this.buf, 0, this.count);
            this.buf = null;
        }
    }
}
