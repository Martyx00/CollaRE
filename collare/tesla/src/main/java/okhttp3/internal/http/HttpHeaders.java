package okhttp3.internal.http;

import c.c;
import c.f;
import com.google.android.gms.common.api.Api;
import java.io.EOFException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import okhttp3.Challenge;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.internal.Util;

public final class HttpHeaders {
    private static final f QUOTED_STRING_DELIMITERS = f.a("\"\\");
    private static final f TOKEN_DELIMITERS = f.a("\t ,=");

    private HttpHeaders() {
    }

    public static long contentLength(Response response) {
        return contentLength(response.headers());
    }

    public static long contentLength(Headers headers) {
        return stringToLong(headers.get("Content-Length"));
    }

    private static long stringToLong(String str) {
        if (str == null) {
            return -1;
        }
        try {
            return Long.parseLong(str);
        } catch (NumberFormatException unused) {
            return -1;
        }
    }

    public static boolean varyMatches(Response response, Headers headers, Request request) {
        for (String str : varyFields(response)) {
            if (!Util.equal(headers.values(str), request.headers(str))) {
                return false;
            }
        }
        return true;
    }

    public static boolean hasVaryAll(Response response) {
        return hasVaryAll(response.headers());
    }

    public static boolean hasVaryAll(Headers headers) {
        return varyFields(headers).contains("*");
    }

    private static Set<String> varyFields(Response response) {
        return varyFields(response.headers());
    }

    public static Set<String> varyFields(Headers headers) {
        Set<String> emptySet = Collections.emptySet();
        int size = headers.size();
        Set<String> set = emptySet;
        for (int i = 0; i < size; i++) {
            if ("Vary".equalsIgnoreCase(headers.name(i))) {
                String value = headers.value(i);
                if (set.isEmpty()) {
                    set = new TreeSet<>(String.CASE_INSENSITIVE_ORDER);
                }
                for (String str : value.split(",")) {
                    set.add(str.trim());
                }
            }
        }
        return set;
    }

    public static Headers varyHeaders(Response response) {
        return varyHeaders(response.networkResponse().request().headers(), response.headers());
    }

    public static Headers varyHeaders(Headers headers, Headers headers2) {
        Set<String> varyFields = varyFields(headers2);
        if (varyFields.isEmpty()) {
            return new Headers.Builder().build();
        }
        Headers.Builder builder = new Headers.Builder();
        int size = headers.size();
        for (int i = 0; i < size; i++) {
            String name = headers.name(i);
            if (varyFields.contains(name)) {
                builder.add(name, headers.value(i));
            }
        }
        return builder.build();
    }

    public static List<Challenge> parseChallenges(Headers headers, String str) {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < headers.size(); i++) {
            if (str.equalsIgnoreCase(headers.name(i))) {
                parseChallengeHeader(arrayList, new c().b(headers.value(i)));
            }
        }
        return arrayList;
    }

    private static void parseChallengeHeader(List<Challenge> list, c cVar) {
        String str;
        String str2 = null;
        while (true) {
            if (str2 == null) {
                skipWhitespaceAndCommas(cVar);
                str2 = readToken(cVar);
                if (str2 == null) {
                    return;
                }
            }
            boolean skipWhitespaceAndCommas = skipWhitespaceAndCommas(cVar);
            String readToken = readToken(cVar);
            if (readToken != null) {
                int skipAll = skipAll(cVar, (byte) 61);
                boolean skipWhitespaceAndCommas2 = skipWhitespaceAndCommas(cVar);
                if (skipWhitespaceAndCommas || (!skipWhitespaceAndCommas2 && !cVar.f())) {
                    LinkedHashMap linkedHashMap = new LinkedHashMap();
                    int skipAll2 = skipAll + skipAll(cVar, (byte) 61);
                    while (true) {
                        if (readToken == null) {
                            readToken = readToken(cVar);
                            if (skipWhitespaceAndCommas(cVar)) {
                                break;
                            }
                            skipAll2 = skipAll(cVar, (byte) 61);
                        }
                        if (skipAll2 == 0) {
                            break;
                        } else if (skipAll2 <= 1 && !skipWhitespaceAndCommas(cVar)) {
                            if (cVar.f() || cVar.c(0L) != 34) {
                                str = readToken(cVar);
                            } else {
                                str = readQuotedString(cVar);
                            }
                            if (str == null || ((String) linkedHashMap.put(readToken, str)) != null) {
                                return;
                            }
                            if (skipWhitespaceAndCommas(cVar) || cVar.f()) {
                                readToken = null;
                            } else {
                                return;
                            }
                        } else {
                            return;
                        }
                    }
                    list.add(new Challenge(str2, linkedHashMap));
                    str2 = readToken;
                } else {
                    list.add(new Challenge(str2, Collections.singletonMap(null, readToken + repeat('=', skipAll))));
                    str2 = null;
                }
            } else if (cVar.f()) {
                list.add(new Challenge(str2, Collections.emptyMap()));
                return;
            } else {
                return;
            }
        }
    }

    private static boolean skipWhitespaceAndCommas(c cVar) {
        boolean z = false;
        while (!cVar.f()) {
            byte c2 = cVar.c(0L);
            if (c2 != 44) {
                if (c2 != 32 && c2 != 9) {
                    break;
                }
                cVar.i();
            } else {
                cVar.i();
                z = true;
            }
        }
        return z;
    }

    private static int skipAll(c cVar, byte b2) {
        int i = 0;
        while (!cVar.f() && cVar.c(0L) == b2) {
            i++;
            cVar.i();
        }
        return i;
    }

    private static String readQuotedString(c cVar) {
        if (cVar.i() == 34) {
            c cVar2 = new c();
            while (true) {
                long b2 = cVar.b(QUOTED_STRING_DELIMITERS);
                if (b2 == -1) {
                    return null;
                }
                if (cVar.c(b2) == 34) {
                    cVar2.write(cVar, b2);
                    cVar.i();
                    return cVar2.r();
                } else if (cVar.a() == b2 + 1) {
                    return null;
                } else {
                    cVar2.write(cVar, b2);
                    cVar.i();
                    cVar2.write(cVar, 1);
                }
            }
        } else {
            throw new IllegalArgumentException();
        }
    }

    private static String readToken(c cVar) {
        try {
            long b2 = cVar.b(TOKEN_DELIMITERS);
            if (b2 == -1) {
                b2 = cVar.a();
            }
            if (b2 != 0) {
                return cVar.e(b2);
            }
            return null;
        } catch (EOFException unused) {
            throw new AssertionError();
        }
    }

    private static String repeat(char c2, int i) {
        char[] cArr = new char[i];
        Arrays.fill(cArr, c2);
        return new String(cArr);
    }

    public static void receiveHeaders(CookieJar cookieJar, HttpUrl httpUrl, Headers headers) {
        if (cookieJar != CookieJar.NO_COOKIES) {
            List<Cookie> parseAll = Cookie.parseAll(httpUrl, headers);
            if (!parseAll.isEmpty()) {
                cookieJar.saveFromResponse(httpUrl, parseAll);
            }
        }
    }

    public static boolean hasBody(Response response) {
        if (response.request().method().equals("HEAD")) {
            return false;
        }
        int code = response.code();
        if (((code >= 100 && code < 200) || code == 204 || code == 304) && contentLength(response) == -1 && !"chunked".equalsIgnoreCase(response.header("Transfer-Encoding"))) {
            return false;
        }
        return true;
    }

    public static int skipUntil(String str, int i, String str2) {
        while (i < str.length() && str2.indexOf(str.charAt(i)) == -1) {
            i++;
        }
        return i;
    }

    public static int skipWhitespace(String str, int i) {
        while (i < str.length() && ((r0 = str.charAt(i)) == ' ' || r0 == '\t')) {
            i++;
        }
        return i;
    }

    public static int parseSeconds(String str, int i) {
        try {
            long parseLong = Long.parseLong(str);
            if (parseLong > 2147483647L) {
                return Api.BaseClientBuilder.API_PRIORITY_OTHER;
            }
            if (parseLong < 0) {
                return 0;
            }
            return (int) parseLong;
        } catch (NumberFormatException unused) {
            return i;
        }
    }
}
