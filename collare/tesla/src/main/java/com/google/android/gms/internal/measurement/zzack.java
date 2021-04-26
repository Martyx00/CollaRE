package com.google.android.gms.internal.measurement;

import io.a.a.a.a.d.b;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import org.spongycastle.asn1.x509.DisplayText;

public final class zzack {
    private static void zza(String str, Object obj, StringBuffer stringBuffer, StringBuffer stringBuffer2) {
        String str2;
        if (obj == null) {
            return;
        }
        if (obj instanceof zzacj) {
            int length = stringBuffer.length();
            if (str != null) {
                stringBuffer2.append(stringBuffer);
                stringBuffer2.append(zzfs(str));
                stringBuffer2.append(" <\n");
                stringBuffer.append("  ");
            }
            Class<?> cls = obj.getClass();
            Field[] fields = cls.getFields();
            for (Field field : fields) {
                int modifiers = field.getModifiers();
                String name = field.getName();
                if (!"cachedSize".equals(name) && (modifiers & 1) == 1 && (modifiers & 8) != 8 && !name.startsWith(b.ROLL_OVER_FILE_NAME_SEPARATOR) && !name.endsWith(b.ROLL_OVER_FILE_NAME_SEPARATOR)) {
                    Class<?> type = field.getType();
                    Object obj2 = field.get(obj);
                    if (!type.isArray() || type.getComponentType() == Byte.TYPE) {
                        zza(name, obj2, stringBuffer, stringBuffer2);
                    } else {
                        int length2 = obj2 == null ? 0 : Array.getLength(obj2);
                        for (int i = 0; i < length2; i++) {
                            zza(name, Array.get(obj2, i), stringBuffer, stringBuffer2);
                        }
                    }
                }
            }
            for (Method method : cls.getMethods()) {
                String name2 = method.getName();
                if (name2.startsWith("set")) {
                    String substring = name2.substring(3);
                    try {
                        String valueOf = String.valueOf(substring);
                        if (((Boolean) cls.getMethod(valueOf.length() != 0 ? "has".concat(valueOf) : new String("has"), new Class[0]).invoke(obj, new Object[0])).booleanValue()) {
                            String valueOf2 = String.valueOf(substring);
                            zza(substring, cls.getMethod(valueOf2.length() != 0 ? "get".concat(valueOf2) : new String("get"), new Class[0]).invoke(obj, new Object[0]), stringBuffer, stringBuffer2);
                        }
                    } catch (NoSuchMethodException unused) {
                    }
                }
            }
            if (str != null) {
                stringBuffer.setLength(length);
                stringBuffer2.append(stringBuffer);
                stringBuffer2.append(">\n");
                return;
            }
            return;
        }
        String zzfs = zzfs(str);
        stringBuffer2.append(stringBuffer);
        stringBuffer2.append(zzfs);
        stringBuffer2.append(": ");
        if (obj instanceof String) {
            String str3 = (String) obj;
            if (!str3.startsWith("http") && str3.length() > 200) {
                str3 = String.valueOf(str3.substring(0, DisplayText.DISPLAY_TEXT_MAXIMUM_SIZE)).concat("[...]");
            }
            int length3 = str3.length();
            StringBuilder sb = new StringBuilder(length3);
            for (int i2 = 0; i2 < length3; i2++) {
                char charAt = str3.charAt(i2);
                if (charAt < ' ' || charAt > '~' || charAt == '\"' || charAt == '\'') {
                    sb.append(String.format("\\u%04x", Integer.valueOf(charAt)));
                } else {
                    sb.append(charAt);
                }
            }
            String sb2 = sb.toString();
            stringBuffer2.append("\"");
            stringBuffer2.append(sb2);
            str2 = "\"";
        } else {
            if (obj instanceof byte[]) {
                byte[] bArr = (byte[]) obj;
                if (bArr == null) {
                    str2 = "\"\"";
                } else {
                    stringBuffer2.append('\"');
                    for (byte b2 : bArr) {
                        int i3 = b2 & 255;
                        if (i3 == 92 || i3 == 34) {
                            stringBuffer2.append('\\');
                        } else if (i3 < 32 || i3 >= 127) {
                            stringBuffer2.append(String.format("\\%03o", Integer.valueOf(i3)));
                        }
                        stringBuffer2.append((char) i3);
                    }
                    stringBuffer2.append('\"');
                }
            } else {
                stringBuffer2.append(obj);
            }
            stringBuffer2.append("\n");
        }
        stringBuffer2.append(str2);
        stringBuffer2.append("\n");
    }

    public static <T extends zzacj> String zzc(T t) {
        if (t == null) {
            return "";
        }
        StringBuffer stringBuffer = new StringBuffer();
        try {
            zza(null, t, new StringBuffer(), stringBuffer);
            return stringBuffer.toString();
        } catch (IllegalAccessException e) {
            String valueOf = String.valueOf(e.getMessage());
            return valueOf.length() != 0 ? "Error printing proto: ".concat(valueOf) : new String("Error printing proto: ");
        } catch (InvocationTargetException e2) {
            String valueOf2 = String.valueOf(e2.getMessage());
            return valueOf2.length() != 0 ? "Error printing proto: ".concat(valueOf2) : new String("Error printing proto: ");
        }
    }

    private static String zzfs(String str) {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < str.length(); i++) {
            char charAt = str.charAt(i);
            if (i != 0) {
                if (Character.isUpperCase(charAt)) {
                    stringBuffer.append('_');
                }
                stringBuffer.append(charAt);
            }
            charAt = Character.toLowerCase(charAt);
            stringBuffer.append(charAt);
        }
        return stringBuffer.toString();
    }
}
