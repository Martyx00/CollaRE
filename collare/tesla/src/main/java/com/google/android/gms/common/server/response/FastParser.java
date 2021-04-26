package com.google.android.gms.common.server.response;

import android.util.Log;
import com.google.android.gms.common.server.response.FastJsonResponse;
import com.google.android.gms.common.util.Base64Utils;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import org.spongycastle.asn1.cmp.PKIFailureInfo;

public class FastParser<T extends FastJsonResponse> {
    private static final char[] zaqg = {'u', 'l', 'l'};
    private static final char[] zaqh = {'r', 'u', 'e'};
    private static final char[] zaqi = {'r', 'u', 'e', '\"'};
    private static final char[] zaqj = {'a', 'l', 's', 'e'};
    private static final char[] zaqk = {'a', 'l', 's', 'e', '\"'};
    private static final char[] zaql = {'\n'};
    private static final zaa<Integer> zaqn = new zaa();
    private static final zaa<Long> zaqo = new zab();
    private static final zaa<Float> zaqp = new zac();
    private static final zaa<Double> zaqq = new zad();
    private static final zaa<Boolean> zaqr = new zae();
    private static final zaa<String> zaqs = new zaf();
    private static final zaa<BigInteger> zaqt = new zag();
    private static final zaa<BigDecimal> zaqu = new zah();
    private final char[] zaqb = new char[1];
    private final char[] zaqc = new char[32];
    private final char[] zaqd = new char[1024];
    private final StringBuilder zaqe = new StringBuilder(32);
    private final StringBuilder zaqf = new StringBuilder(1024);
    private final Stack<Integer> zaqm = new Stack<>();

    public interface zaa<O> {
        O zah(FastParser fastParser, BufferedReader bufferedReader);
    }

    public static class ParseException extends Exception {
        public ParseException(String str) {
            super(str);
        }

        public ParseException(String str, Throwable th) {
            super(str, th);
        }

        public ParseException(Throwable th) {
            super(th);
        }
    }

    public void parse(InputStream inputStream, T t) {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream), 1024);
        try {
            this.zaqm.push(0);
            char zaj = zaj(bufferedReader);
            if (zaj != 0) {
                if (zaj == '[') {
                    this.zaqm.push(5);
                    Map<String, FastJsonResponse.Field<?, ?>> fieldMappings = t.getFieldMappings();
                    if (fieldMappings.size() == 1) {
                        FastJsonResponse.Field<?, ?> value = fieldMappings.entrySet().iterator().next().getValue();
                        t.addConcreteTypeArrayInternal(value, value.zapv, zaa(bufferedReader, value));
                    } else {
                        throw new ParseException("Object array response class must have a single Field");
                    }
                } else if (zaj == '{') {
                    this.zaqm.push(1);
                    zaa(bufferedReader, t);
                } else {
                    StringBuilder sb = new StringBuilder(19);
                    sb.append("Unexpected token: ");
                    sb.append(zaj);
                    throw new ParseException(sb.toString());
                }
                zak(0);
                try {
                    bufferedReader.close();
                } catch (IOException unused) {
                    Log.w("FastParser", "Failed to close reader while parsing.");
                }
            } else {
                throw new ParseException("No data to parse");
            }
        } catch (IOException e) {
            throw new ParseException(e);
        } catch (Throwable th) {
            try {
                bufferedReader.close();
            } catch (IOException unused2) {
                Log.w("FastParser", "Failed to close reader while parsing.");
            }
            throw th;
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for r15v0, resolved type: com.google.android.gms.common.server.response.FastJsonResponse */
    /* JADX WARN: Multi-variable type inference failed */
    private final boolean zaa(BufferedReader bufferedReader, FastJsonResponse fastJsonResponse) {
        HashMap hashMap;
        Map<String, FastJsonResponse.Field<?, ?>> fieldMappings = fastJsonResponse.getFieldMappings();
        String zaa2 = zaa(bufferedReader);
        if (zaa2 == null) {
            zak(1);
            return false;
        }
        while (zaa2 != null) {
            FastJsonResponse.Field<?, ?> field = fieldMappings.get(zaa2);
            if (field == null) {
                zaa2 = zab(bufferedReader);
            } else {
                this.zaqm.push(4);
                switch (field.zapr) {
                    case 0:
                        if (!field.zaps) {
                            fastJsonResponse.zaa((FastJsonResponse.Field) field, zad(bufferedReader));
                            break;
                        } else {
                            fastJsonResponse.zaa((FastJsonResponse.Field) field, (ArrayList<Integer>) zaa(bufferedReader, (zaa<O>) zaqn));
                            break;
                        }
                    case 1:
                        if (!field.zaps) {
                            fastJsonResponse.zaa((FastJsonResponse.Field) field, zaf(bufferedReader));
                            break;
                        } else {
                            fastJsonResponse.zab((FastJsonResponse.Field) field, (ArrayList<BigInteger>) zaa(bufferedReader, (zaa<O>) zaqt));
                            break;
                        }
                    case 2:
                        if (!field.zaps) {
                            fastJsonResponse.zaa((FastJsonResponse.Field) field, zae(bufferedReader));
                            break;
                        } else {
                            fastJsonResponse.zac(field, zaa(bufferedReader, (zaa<O>) zaqo));
                            break;
                        }
                    case 3:
                        if (!field.zaps) {
                            fastJsonResponse.zaa((FastJsonResponse.Field) field, zag(bufferedReader));
                            break;
                        } else {
                            fastJsonResponse.zad(field, zaa(bufferedReader, (zaa<O>) zaqp));
                            break;
                        }
                    case 4:
                        if (!field.zaps) {
                            fastJsonResponse.zaa(field, zah(bufferedReader));
                            break;
                        } else {
                            fastJsonResponse.zae(field, zaa(bufferedReader, (zaa<O>) zaqq));
                            break;
                        }
                    case 5:
                        if (!field.zaps) {
                            fastJsonResponse.zaa((FastJsonResponse.Field) field, zai(bufferedReader));
                            break;
                        } else {
                            fastJsonResponse.zaf(field, zaa(bufferedReader, (zaa<O>) zaqu));
                            break;
                        }
                    case 6:
                        if (!field.zaps) {
                            fastJsonResponse.zaa(field, zaa(bufferedReader, false));
                            break;
                        } else {
                            fastJsonResponse.zag(field, zaa(bufferedReader, (zaa<O>) zaqr));
                            break;
                        }
                    case 7:
                        if (!field.zaps) {
                            fastJsonResponse.zaa((FastJsonResponse.Field) field, zac(bufferedReader));
                            break;
                        } else {
                            fastJsonResponse.zah(field, zaa(bufferedReader, (zaa<O>) zaqs));
                            break;
                        }
                    case 8:
                        fastJsonResponse.zaa((FastJsonResponse.Field) field, Base64Utils.decode(zaa(bufferedReader, this.zaqd, this.zaqf, zaql)));
                        break;
                    case 9:
                        fastJsonResponse.zaa((FastJsonResponse.Field) field, Base64Utils.decodeUrlSafe(zaa(bufferedReader, this.zaqd, this.zaqf, zaql)));
                        break;
                    case 10:
                        char zaj = zaj(bufferedReader);
                        if (zaj == 'n') {
                            zab(bufferedReader, zaqg);
                            hashMap = null;
                        } else if (zaj == '{') {
                            this.zaqm.push(1);
                            hashMap = new HashMap();
                            while (true) {
                                char zaj2 = zaj(bufferedReader);
                                if (zaj2 == 0) {
                                    throw new ParseException("Unexpected EOF");
                                } else if (zaj2 == '\"') {
                                    String zab = zab(bufferedReader, this.zaqc, this.zaqe, null);
                                    if (zaj(bufferedReader) != ':') {
                                        String valueOf = String.valueOf(zab);
                                        throw new ParseException(valueOf.length() != 0 ? "No map value found for key ".concat(valueOf) : new String("No map value found for key "));
                                    } else if (zaj(bufferedReader) != '\"') {
                                        String valueOf2 = String.valueOf(zab);
                                        throw new ParseException(valueOf2.length() != 0 ? "Expected String value for key ".concat(valueOf2) : new String("Expected String value for key "));
                                    } else {
                                        hashMap.put(zab, zab(bufferedReader, this.zaqc, this.zaqe, null));
                                        char zaj3 = zaj(bufferedReader);
                                        if (zaj3 != ',') {
                                            if (zaj3 == '}') {
                                                zak(1);
                                            } else {
                                                StringBuilder sb = new StringBuilder(48);
                                                sb.append("Unexpected character while parsing string map: ");
                                                sb.append(zaj3);
                                                throw new ParseException(sb.toString());
                                            }
                                        }
                                    }
                                } else if (zaj2 == '}') {
                                    zak(1);
                                }
                            }
                        } else {
                            throw new ParseException("Expected start of a map object");
                        }
                        fastJsonResponse.zaa((FastJsonResponse.Field) field, (Map<String, String>) hashMap);
                        break;
                    case 11:
                        if (field.zaps) {
                            char zaj4 = zaj(bufferedReader);
                            if (zaj4 == 'n') {
                                zab(bufferedReader, zaqg);
                                fastJsonResponse.addConcreteTypeArrayInternal(field, field.zapv, null);
                                break;
                            } else {
                                this.zaqm.push(5);
                                if (zaj4 == '[') {
                                    fastJsonResponse.addConcreteTypeArrayInternal(field, field.zapv, zaa(bufferedReader, field));
                                    break;
                                } else {
                                    throw new ParseException("Expected array start");
                                }
                            }
                        } else {
                            char zaj5 = zaj(bufferedReader);
                            if (zaj5 == 'n') {
                                zab(bufferedReader, zaqg);
                                fastJsonResponse.addConcreteTypeInternal(field, field.zapv, null);
                                break;
                            } else {
                                this.zaqm.push(1);
                                if (zaj5 == '{') {
                                    try {
                                        FastJsonResponse zacp = field.zacp();
                                        zaa(bufferedReader, zacp);
                                        fastJsonResponse.addConcreteTypeInternal(field, field.zapv, zacp);
                                        break;
                                    } catch (InstantiationException e) {
                                        throw new ParseException("Error instantiating inner object", e);
                                    } catch (IllegalAccessException e2) {
                                        throw new ParseException("Error instantiating inner object", e2);
                                    }
                                } else {
                                    throw new ParseException("Expected start of object");
                                }
                            }
                        }
                    default:
                        int i = field.zapr;
                        StringBuilder sb2 = new StringBuilder(30);
                        sb2.append("Invalid field type ");
                        sb2.append(i);
                        throw new ParseException(sb2.toString());
                }
                zak(4);
                zak(2);
                char zaj6 = zaj(bufferedReader);
                if (zaj6 == ',') {
                    zaa2 = zaa(bufferedReader);
                } else if (zaj6 == '}') {
                    zaa2 = null;
                } else {
                    StringBuilder sb3 = new StringBuilder(55);
                    sb3.append("Expected end of object or field separator, but found: ");
                    sb3.append(zaj6);
                    throw new ParseException(sb3.toString());
                }
            }
        }
        zak(1);
        return true;
    }

    private final String zaa(BufferedReader bufferedReader) {
        this.zaqm.push(2);
        char zaj = zaj(bufferedReader);
        if (zaj == '\"') {
            this.zaqm.push(3);
            String zab = zab(bufferedReader, this.zaqc, this.zaqe, null);
            zak(3);
            if (zaj(bufferedReader) == ':') {
                return zab;
            }
            throw new ParseException("Expected key/value separator");
        } else if (zaj == ']') {
            zak(2);
            zak(1);
            zak(5);
            return null;
        } else if (zaj == '}') {
            zak(2);
            return null;
        } else {
            StringBuilder sb = new StringBuilder(19);
            sb.append("Unexpected token: ");
            sb.append(zaj);
            throw new ParseException(sb.toString());
        }
    }

    private final String zab(BufferedReader bufferedReader) {
        bufferedReader.mark(1024);
        char zaj = zaj(bufferedReader);
        if (zaj != '\"') {
            if (zaj != ',') {
                int i = 1;
                if (zaj == '[') {
                    this.zaqm.push(5);
                    bufferedReader.mark(32);
                    if (zaj(bufferedReader) == ']') {
                        zak(5);
                    } else {
                        bufferedReader.reset();
                        boolean z = false;
                        boolean z2 = false;
                        while (i > 0) {
                            char zaj2 = zaj(bufferedReader);
                            if (zaj2 == 0) {
                                throw new ParseException("Unexpected EOF while parsing array");
                            } else if (!Character.isISOControl(zaj2)) {
                                if (zaj2 == '\"' && !z) {
                                    z2 = !z2;
                                }
                                if (zaj2 == '[' && !z2) {
                                    i++;
                                }
                                if (zaj2 == ']' && !z2) {
                                    i--;
                                }
                                z = (zaj2 != '\\' || !z2) ? false : !z;
                            } else {
                                throw new ParseException("Unexpected control character while reading array");
                            }
                        }
                        zak(5);
                    }
                } else if (zaj != '{') {
                    bufferedReader.reset();
                    zaa(bufferedReader, this.zaqd);
                } else {
                    this.zaqm.push(1);
                    bufferedReader.mark(32);
                    char zaj3 = zaj(bufferedReader);
                    if (zaj3 == '}') {
                        zak(1);
                    } else if (zaj3 == '\"') {
                        bufferedReader.reset();
                        zaa(bufferedReader);
                        do {
                        } while (zab(bufferedReader) != null);
                        zak(1);
                    } else {
                        StringBuilder sb = new StringBuilder(18);
                        sb.append("Unexpected token ");
                        sb.append(zaj3);
                        throw new ParseException(sb.toString());
                    }
                }
            } else {
                throw new ParseException("Missing value");
            }
        } else if (bufferedReader.read(this.zaqb) != -1) {
            char c2 = this.zaqb[0];
            boolean z3 = false;
            do {
                if (c2 != '\"' || z3) {
                    z3 = c2 == '\\' ? !z3 : false;
                    if (bufferedReader.read(this.zaqb) != -1) {
                        c2 = this.zaqb[0];
                    } else {
                        throw new ParseException("Unexpected EOF while parsing string");
                    }
                }
            } while (!Character.isISOControl(c2));
            throw new ParseException("Unexpected control character while reading string");
        } else {
            throw new ParseException("Unexpected EOF while parsing string");
        }
        char zaj4 = zaj(bufferedReader);
        if (zaj4 == ',') {
            zak(2);
            return zaa(bufferedReader);
        } else if (zaj4 == '}') {
            zak(2);
            return null;
        } else {
            StringBuilder sb2 = new StringBuilder(18);
            sb2.append("Unexpected token ");
            sb2.append(zaj4);
            throw new ParseException(sb2.toString());
        }
    }

    /* access modifiers changed from: public */
    private final String zac(BufferedReader bufferedReader) {
        return zaa(bufferedReader, this.zaqc, this.zaqe, null);
    }

    private final <O> ArrayList<O> zaa(BufferedReader bufferedReader, zaa<O> zaa2) {
        char zaj = zaj(bufferedReader);
        if (zaj == 'n') {
            zab(bufferedReader, zaqg);
            return null;
        } else if (zaj == '[') {
            this.zaqm.push(5);
            ArrayList<O> arrayList = new ArrayList<>();
            while (true) {
                bufferedReader.mark(1024);
                char zaj2 = zaj(bufferedReader);
                if (zaj2 == 0) {
                    throw new ParseException("Unexpected EOF");
                } else if (zaj2 != ',') {
                    if (zaj2 != ']') {
                        bufferedReader.reset();
                        arrayList.add(zaa2.zah(this, bufferedReader));
                    } else {
                        zak(5);
                        return arrayList;
                    }
                }
            }
        } else {
            throw new ParseException("Expected start of array");
        }
    }

    private final String zaa(BufferedReader bufferedReader, char[] cArr, StringBuilder sb, char[] cArr2) {
        char zaj = zaj(bufferedReader);
        if (zaj == '\"') {
            return zab(bufferedReader, cArr, sb, cArr2);
        }
        if (zaj == 'n') {
            zab(bufferedReader, zaqg);
            return null;
        }
        throw new ParseException("Expected string");
    }

    /* JADX WARNING: Removed duplicated region for block: B:37:0x0032 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.lang.String zab(java.io.BufferedReader r9, char[] r10, java.lang.StringBuilder r11, char[] r12) {
        /*
        // Method dump skipped, instructions count: 121
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.server.response.FastParser.zab(java.io.BufferedReader, char[], java.lang.StringBuilder, char[]):java.lang.String");
    }

    /* access modifiers changed from: public */
    private final int zad(BufferedReader bufferedReader) {
        int i;
        boolean z;
        int i2;
        int i3;
        int i4;
        int zaa2 = zaa(bufferedReader, this.zaqd);
        if (zaa2 == 0) {
            return 0;
        }
        char[] cArr = this.zaqd;
        if (zaa2 > 0) {
            if (cArr[0] == '-') {
                i2 = 1;
                z = true;
                i = PKIFailureInfo.systemUnavail;
            } else {
                i2 = 0;
                z = false;
                i = -2147483647;
            }
            if (i2 < zaa2) {
                i4 = i2 + 1;
                int digit = Character.digit(cArr[i2], 10);
                if (digit >= 0) {
                    i3 = -digit;
                } else {
                    throw new ParseException("Unexpected non-digit character");
                }
            } else {
                i4 = i2;
                i3 = 0;
            }
            while (i4 < zaa2) {
                int i5 = i4 + 1;
                int digit2 = Character.digit(cArr[i4], 10);
                if (digit2 < 0) {
                    throw new ParseException("Unexpected non-digit character");
                } else if (i3 >= -214748364) {
                    int i6 = i3 * 10;
                    if (i6 >= i + digit2) {
                        i3 = i6 - digit2;
                        i4 = i5;
                    } else {
                        throw new ParseException("Number too large");
                    }
                } else {
                    throw new ParseException("Number too large");
                }
            }
            if (!z) {
                return -i3;
            }
            if (i4 > 1) {
                return i3;
            }
            throw new ParseException("No digits to parse");
        }
        throw new ParseException("No number to parse");
    }

    /* access modifiers changed from: public */
    private final long zae(BufferedReader bufferedReader) {
        boolean z;
        long j;
        long j2;
        int i;
        int zaa2 = zaa(bufferedReader, this.zaqd);
        if (zaa2 == 0) {
            return 0;
        }
        char[] cArr = this.zaqd;
        if (zaa2 > 0) {
            int i2 = 0;
            if (cArr[0] == '-') {
                j = Long.MIN_VALUE;
                i2 = 1;
                z = true;
            } else {
                j = -9223372036854775807L;
                z = false;
            }
            if (i2 < zaa2) {
                i = i2 + 1;
                int digit = Character.digit(cArr[i2], 10);
                if (digit >= 0) {
                    j2 = (long) (-digit);
                } else {
                    throw new ParseException("Unexpected non-digit character");
                }
            } else {
                j2 = 0;
                i = i2;
            }
            while (i < zaa2) {
                int i3 = i + 1;
                int digit2 = Character.digit(cArr[i], 10);
                if (digit2 < 0) {
                    throw new ParseException("Unexpected non-digit character");
                } else if (j2 >= -922337203685477580L) {
                    long j3 = j2 * 10;
                    long j4 = (long) digit2;
                    if (j3 >= j + j4) {
                        j2 = j3 - j4;
                        i = i3;
                    } else {
                        throw new ParseException("Number too large");
                    }
                } else {
                    throw new ParseException("Number too large");
                }
            }
            if (!z) {
                return -j2;
            }
            if (i > 1) {
                return j2;
            }
            throw new ParseException("No digits to parse");
        }
        throw new ParseException("No number to parse");
    }

    /* access modifiers changed from: public */
    private final BigInteger zaf(BufferedReader bufferedReader) {
        int zaa2 = zaa(bufferedReader, this.zaqd);
        if (zaa2 == 0) {
            return null;
        }
        return new BigInteger(new String(this.zaqd, 0, zaa2));
    }

    /* access modifiers changed from: public */
    private final boolean zaa(BufferedReader bufferedReader, boolean z) {
        while (true) {
            char zaj = zaj(bufferedReader);
            if (zaj != '\"') {
                if (zaj == 'f') {
                    zab(bufferedReader, z ? zaqk : zaqj);
                    return false;
                } else if (zaj == 'n') {
                    zab(bufferedReader, zaqg);
                    return false;
                } else if (zaj == 't') {
                    zab(bufferedReader, z ? zaqi : zaqh);
                    return true;
                } else {
                    StringBuilder sb = new StringBuilder(19);
                    sb.append("Unexpected token: ");
                    sb.append(zaj);
                    throw new ParseException(sb.toString());
                }
            } else if (!z) {
                z = true;
            } else {
                throw new ParseException("No boolean value found in string");
            }
        }
    }

    /* access modifiers changed from: public */
    private final float zag(BufferedReader bufferedReader) {
        int zaa2 = zaa(bufferedReader, this.zaqd);
        if (zaa2 == 0) {
            return BitmapDescriptorFactory.HUE_RED;
        }
        return Float.parseFloat(new String(this.zaqd, 0, zaa2));
    }

    /* access modifiers changed from: public */
    private final double zah(BufferedReader bufferedReader) {
        int zaa2 = zaa(bufferedReader, this.zaqd);
        if (zaa2 == 0) {
            return 0.0d;
        }
        return Double.parseDouble(new String(this.zaqd, 0, zaa2));
    }

    /* access modifiers changed from: public */
    private final BigDecimal zai(BufferedReader bufferedReader) {
        int zaa2 = zaa(bufferedReader, this.zaqd);
        if (zaa2 == 0) {
            return null;
        }
        return new BigDecimal(new String(this.zaqd, 0, zaa2));
    }

    /* JADX DEBUG: Multi-variable search result rejected for r0v0, resolved type: java.util.ArrayList<T extends com.google.android.gms.common.server.response.FastJsonResponse> */
    /* JADX WARN: Multi-variable type inference failed */
    private final <T extends FastJsonResponse> ArrayList<T> zaa(BufferedReader bufferedReader, FastJsonResponse.Field<?, ?> field) {
        ArrayList<T> arrayList = (ArrayList<T>) new ArrayList();
        char zaj = zaj(bufferedReader);
        if (zaj == ']') {
            zak(5);
            return arrayList;
        } else if (zaj == 'n') {
            zab(bufferedReader, zaqg);
            zak(5);
            return null;
        } else if (zaj == '{') {
            this.zaqm.push(1);
            while (true) {
                try {
                    FastJsonResponse zacp = field.zacp();
                    if (!zaa(bufferedReader, zacp)) {
                        return arrayList;
                    }
                    arrayList.add(zacp);
                    char zaj2 = zaj(bufferedReader);
                    if (zaj2 != ',') {
                        if (zaj2 == ']') {
                            zak(5);
                            return arrayList;
                        }
                        StringBuilder sb = new StringBuilder(19);
                        sb.append("Unexpected token: ");
                        sb.append(zaj2);
                        throw new ParseException(sb.toString());
                    } else if (zaj(bufferedReader) == '{') {
                        this.zaqm.push(1);
                    } else {
                        throw new ParseException("Expected start of next object in array");
                    }
                } catch (InstantiationException e) {
                    throw new ParseException("Error instantiating inner object", e);
                } catch (IllegalAccessException e2) {
                    throw new ParseException("Error instantiating inner object", e2);
                }
            }
        } else {
            StringBuilder sb2 = new StringBuilder(19);
            sb2.append("Unexpected token: ");
            sb2.append(zaj);
            throw new ParseException(sb2.toString());
        }
    }

    private final char zaj(BufferedReader bufferedReader) {
        if (bufferedReader.read(this.zaqb) == -1) {
            return 0;
        }
        while (Character.isWhitespace(this.zaqb[0])) {
            if (bufferedReader.read(this.zaqb) == -1) {
                return 0;
            }
        }
        return this.zaqb[0];
    }

    private final int zaa(BufferedReader bufferedReader, char[] cArr) {
        int i;
        char zaj = zaj(bufferedReader);
        if (zaj == 0) {
            throw new ParseException("Unexpected EOF");
        } else if (zaj == ',') {
            throw new ParseException("Missing value");
        } else if (zaj == 'n') {
            zab(bufferedReader, zaqg);
            return 0;
        } else {
            bufferedReader.mark(1024);
            if (zaj == '\"') {
                i = 0;
                boolean z = false;
                while (i < cArr.length && bufferedReader.read(cArr, i, 1) != -1) {
                    char c2 = cArr[i];
                    if (Character.isISOControl(c2)) {
                        throw new ParseException("Unexpected control character while reading string");
                    } else if (c2 != '\"' || z) {
                        z = c2 == '\\' ? !z : false;
                        i++;
                    } else {
                        bufferedReader.reset();
                        bufferedReader.skip((long) (i + 1));
                        return i;
                    }
                }
            } else {
                cArr[0] = zaj;
                i = 1;
                while (i < cArr.length && bufferedReader.read(cArr, i, 1) != -1) {
                    if (cArr[i] == '}' || cArr[i] == ',' || Character.isWhitespace(cArr[i]) || cArr[i] == ']') {
                        bufferedReader.reset();
                        bufferedReader.skip((long) (i - 1));
                        cArr[i] = 0;
                        return i;
                    }
                    i++;
                }
            }
            if (i == cArr.length) {
                throw new ParseException("Absurdly long value");
            }
            throw new ParseException("Unexpected EOF");
        }
    }

    private final void zab(BufferedReader bufferedReader, char[] cArr) {
        int i = 0;
        while (i < cArr.length) {
            int read = bufferedReader.read(this.zaqc, 0, cArr.length - i);
            if (read != -1) {
                for (int i2 = 0; i2 < read; i2++) {
                    if (cArr[i2 + i] != this.zaqc[i2]) {
                        throw new ParseException("Unexpected character");
                    }
                }
                i += read;
            } else {
                throw new ParseException("Unexpected EOF");
            }
        }
    }

    private final void zak(int i) {
        if (!this.zaqm.isEmpty()) {
            int intValue = this.zaqm.pop().intValue();
            if (intValue != i) {
                StringBuilder sb = new StringBuilder(46);
                sb.append("Expected state ");
                sb.append(i);
                sb.append(" but had ");
                sb.append(intValue);
                throw new ParseException(sb.toString());
            }
            return;
        }
        StringBuilder sb2 = new StringBuilder(46);
        sb2.append("Expected state ");
        sb2.append(i);
        sb2.append(" but had empty stack");
        throw new ParseException(sb2.toString());
    }
}
