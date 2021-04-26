package org.spongycastle.crypto.tls;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Vector;
import org.spongycastle.util.Arrays;
import org.spongycastle.util.io.Streams;

public class ServerNameList {
    protected Vector serverNameList;

    public ServerNameList(Vector vector) {
        if (vector != null) {
            this.serverNameList = vector;
            return;
        }
        throw new IllegalArgumentException("'serverNameList' must not be null");
    }

    public Vector getServerNameList() {
        return this.serverNameList;
    }

    public void encode(OutputStream outputStream) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        short[] sArr = new short[0];
        for (int i = 0; i < this.serverNameList.size(); i++) {
            ServerName serverName = (ServerName) this.serverNameList.elementAt(i);
            sArr = checkNameType(sArr, serverName.getNameType());
            if (sArr != null) {
                serverName.encode(byteArrayOutputStream);
            } else {
                throw new TlsFatalAlert(80);
            }
        }
        TlsUtils.checkUint16(byteArrayOutputStream.size());
        TlsUtils.writeUint16(byteArrayOutputStream.size(), outputStream);
        Streams.writeBufTo(byteArrayOutputStream, outputStream);
    }

    public static ServerNameList parse(InputStream inputStream) {
        int readUint16 = TlsUtils.readUint16(inputStream);
        if (readUint16 >= 1) {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(TlsUtils.readFully(readUint16, inputStream));
            short[] sArr = new short[0];
            Vector vector = new Vector();
            while (byteArrayInputStream.available() > 0) {
                ServerName parse = ServerName.parse(byteArrayInputStream);
                sArr = checkNameType(sArr, parse.getNameType());
                if (sArr != null) {
                    vector.addElement(parse);
                } else {
                    throw new TlsFatalAlert(47);
                }
            }
            return new ServerNameList(vector);
        }
        throw new TlsFatalAlert(50);
    }

    private static short[] checkNameType(short[] sArr, short s) {
        if (!NameType.isValid(s) || Arrays.contains(sArr, s)) {
            return null;
        }
        return Arrays.append(sArr, s);
    }
}
