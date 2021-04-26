package com.google.protobuf;

/* access modifiers changed from: package-private */
/* compiled from: TextFormatEscaper */
public final class as {

    /* access modifiers changed from: private */
    /* compiled from: TextFormatEscaper */
    public interface a {
        byte a(int i);

        int a();
    }

    static String a(a aVar) {
        StringBuilder sb = new StringBuilder(aVar.a());
        for (int i = 0; i < aVar.a(); i++) {
            byte a2 = aVar.a(i);
            if (a2 == 34) {
                sb.append("\\\"");
            } else if (a2 == 39) {
                sb.append("\\'");
            } else if (a2 != 92) {
                switch (a2) {
                    case 7:
                        sb.append("\\a");
                        continue;
                    case 8:
                        sb.append("\\b");
                        continue;
                    case 9:
                        sb.append("\\t");
                        continue;
                    case 10:
                        sb.append("\\n");
                        continue;
                    case 11:
                        sb.append("\\v");
                        continue;
                    case 12:
                        sb.append("\\f");
                        continue;
                    case 13:
                        sb.append("\\r");
                        continue;
                    default:
                        if (a2 < 32 || a2 > 126) {
                            sb.append('\\');
                            sb.append((char) (((a2 >>> 6) & 3) + 48));
                            sb.append((char) (((a2 >>> 3) & 7) + 48));
                            sb.append((char) ((a2 & 7) + 48));
                            break;
                        } else {
                            sb.append((char) a2);
                            continue;
                        }
                        break;
                }
            } else {
                sb.append("\\\\");
            }
        }
        return sb.toString();
    }

    static String a(final g gVar) {
        return a(new a() {
            /* class com.google.protobuf.as.AnonymousClass1 */

            @Override // com.google.protobuf.as.a
            public int a() {
                return gVar.b();
            }

            @Override // com.google.protobuf.as.a
            public byte a(int i) {
                return gVar.a(i);
            }
        });
    }

    static String a(final byte[] bArr) {
        return a(new a() {
            /* class com.google.protobuf.as.AnonymousClass2 */

            @Override // com.google.protobuf.as.a
            public int a() {
                return bArr.length;
            }

            @Override // com.google.protobuf.as.a
            public byte a(int i) {
                return bArr[i];
            }
        });
    }

    static String a(String str) {
        return a(g.a(str));
    }

    static String b(String str) {
        return str.replace("\\", "\\\\").replace("\"", "\\\"");
    }
}
