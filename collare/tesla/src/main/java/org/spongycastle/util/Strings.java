package org.spongycastle.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.ArrayList;
import java.util.Vector;
import org.spongycastle.asn1.cmp.PKIFailureInfo;

public final class Strings {
    private static String LINE_SEPARATOR;

    static {
        try {
            LINE_SEPARATOR = (String) AccessController.doPrivileged(new PrivilegedAction<String>() {
                /* class org.spongycastle.util.Strings.AnonymousClass1 */

                @Override // java.security.PrivilegedAction
                public String run() {
                    return System.getProperty("line.separator");
                }
            });
        } catch (Exception unused) {
            try {
                LINE_SEPARATOR = String.format("%n", new Object[0]);
            } catch (Exception unused2) {
                LINE_SEPARATOR = "\n";
            }
        }
    }

    public static String fromUTF8ByteArray(byte[] bArr) {
        char c2;
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        while (i2 < bArr.length) {
            i3++;
            if ((bArr[i2] & 240) == 240) {
                i3++;
                i2 += 4;
            } else if ((bArr[i2] & 224) == 224) {
                i2 += 3;
            } else {
                i2 = (bArr[i2] & 192) == 192 ? i2 + 2 : i2 + 1;
            }
        }
        char[] cArr = new char[i3];
        int i4 = 0;
        while (i < bArr.length) {
            if ((bArr[i] & 240) == 240) {
                int i5 = (((((bArr[i] & 3) << 18) | ((bArr[i + 1] & 63) << 12)) | ((bArr[i + 2] & 63) << 6)) | (bArr[i + 3] & 63)) - PKIFailureInfo.notAuthorized;
                c2 = (char) ((i5 & 1023) | 56320);
                cArr[i4] = (char) (55296 | (i5 >> 10));
                i += 4;
                i4++;
            } else if ((bArr[i] & 224) == 224) {
                c2 = (char) (((bArr[i] & 15) << 12) | ((bArr[i + 1] & 63) << 6) | (bArr[i + 2] & 63));
                i += 3;
            } else if ((bArr[i] & 208) == 208) {
                c2 = (char) (((bArr[i] & 31) << 6) | (bArr[i + 1] & 63));
                i += 2;
            } else if ((bArr[i] & 192) == 192) {
                c2 = (char) (((bArr[i] & 31) << 6) | (bArr[i + 1] & 63));
                i += 2;
            } else {
                c2 = (char) (bArr[i] & 255);
                i++;
            }
            cArr[i4] = c2;
            i4++;
        }
        return new String(cArr);
    }

    public static byte[] toUTF8ByteArray(String str) {
        return toUTF8ByteArray(str.toCharArray());
    }

    public static byte[] toUTF8ByteArray(char[] cArr) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            toUTF8ByteArray(cArr, byteArrayOutputStream);
            return byteArrayOutputStream.toByteArray();
        } catch (IOException unused) {
            throw new IllegalStateException("cannot encode string to byte array!");
        }
    }

    public static void toUTF8ByteArray(char[] cArr, OutputStream outputStream) {
        int i = 0;
        while (i < cArr.length) {
            char c2 = cArr[i];
            if (c2 < 128) {
                outputStream.write(c2);
            } else if (c2 < 2048) {
                outputStream.write((c2 >> 6) | 192);
                outputStream.write((c2 & '?') | 128);
            } else if (c2 < 55296 || c2 > 57343) {
                outputStream.write((c2 >> '\f') | 224);
                outputStream.write(((c2 >> 6) & 63) | 128);
                outputStream.write((c2 & '?') | 128);
            } else {
                i++;
                if (i < cArr.length) {
                    char c3 = cArr[i];
                    if (c2 <= 56319) {
                        int i2 = (((c2 & 1023) << 10) | (c3 & 1023)) + PKIFailureInfo.notAuthorized;
                        outputStream.write((i2 >> 18) | 240);
                        outputStream.write(((i2 >> 12) & 63) | 128);
                        outputStream.write(((i2 >> 6) & 63) | 128);
                        outputStream.write((i2 & 63) | 128);
                    } else {
                        throw new IllegalStateException("invalid UTF-16 codepoint");
                    }
                } else {
                    throw new IllegalStateException("invalid UTF-16 codepoint");
                }
            }
            i++;
        }
    }

    public static String toUpperCase(String str) {
        char[] charArray = str.toCharArray();
        boolean z = false;
        for (int i = 0; i != charArray.length; i++) {
            char c2 = charArray[i];
            if ('a' <= c2 && 'z' >= c2) {
                charArray[i] = (char) ((c2 - 'a') + 65);
                z = true;
            }
        }
        return z ? new String(charArray) : str;
    }

    public static String toLowerCase(String str) {
        char[] charArray = str.toCharArray();
        boolean z = false;
        for (int i = 0; i != charArray.length; i++) {
            char c2 = charArray[i];
            if ('A' <= c2 && 'Z' >= c2) {
                charArray[i] = (char) ((c2 - 'A') + 97);
                z = true;
            }
        }
        return z ? new String(charArray) : str;
    }

    public static byte[] toByteArray(char[] cArr) {
        byte[] bArr = new byte[cArr.length];
        for (int i = 0; i != bArr.length; i++) {
            bArr[i] = (byte) cArr[i];
        }
        return bArr;
    }

    public static byte[] toByteArray(String str) {
        byte[] bArr = new byte[str.length()];
        for (int i = 0; i != bArr.length; i++) {
            bArr[i] = (byte) str.charAt(i);
        }
        return bArr;
    }

    public static int toByteArray(String str, byte[] bArr, int i) {
        int length = str.length();
        for (int i2 = 0; i2 < length; i2++) {
            bArr[i + i2] = (byte) str.charAt(i2);
        }
        return length;
    }

    public static String fromByteArray(byte[] bArr) {
        return new String(asCharArray(bArr));
    }

    public static char[] asCharArray(byte[] bArr) {
        char[] cArr = new char[bArr.length];
        for (int i = 0; i != cArr.length; i++) {
            cArr[i] = (char) (bArr[i] & 255);
        }
        return cArr;
    }

    public static String[] split(String str, char c2) {
        int i;
        Vector vector = new Vector();
        boolean z = true;
        while (true) {
            if (!z) {
                break;
            }
            int indexOf = str.indexOf(c2);
            if (indexOf > 0) {
                vector.addElement(str.substring(0, indexOf));
                str = str.substring(indexOf + 1);
            } else {
                vector.addElement(str);
                z = false;
            }
        }
        String[] strArr = new String[vector.size()];
        for (i = 0; i != strArr.length; i++) {
            strArr[i] = (String) vector.elementAt(i);
        }
        return strArr;
    }

    public static StringList newList() {
        return new StringListImpl();
    }

    public static String lineSeparator() {
        return LINE_SEPARATOR;
    }

    private static class StringListImpl extends ArrayList<String> implements StringList {
        private StringListImpl() {
        }

        @Override // java.util.List, java.util.AbstractList, org.spongycastle.util.StringList, java.util.ArrayList
        public /* bridge */ /* synthetic */ String get(int i) {
            return (String) super.get(i);
        }

        @Override // org.spongycastle.util.StringList
        public boolean add(String str) {
            return super.add((Object) str);
        }

        public String set(int i, String str) {
            return (String) super.set(i, (Object) str);
        }

        public void add(int i, String str) {
            super.add(i, (Object) str);
        }

        @Override // org.spongycastle.util.StringList
        public String[] toStringArray() {
            String[] strArr = new String[size()];
            for (int i = 0; i != strArr.length; i++) {
                strArr[i] = (String) get(i);
            }
            return strArr;
        }

        @Override // org.spongycastle.util.StringList
        public String[] toStringArray(int i, int i2) {
            String[] strArr = new String[(i2 - i)];
            int i3 = i;
            while (i3 != size() && i3 != i2) {
                strArr[i3 - i] = (String) get(i3);
                i3++;
            }
            return strArr;
        }
    }
}
