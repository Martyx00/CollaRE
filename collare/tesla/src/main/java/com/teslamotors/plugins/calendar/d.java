package com.teslamotors.plugins.calendar;

import android.os.Parcel;

/* compiled from: DataUtil */
public class d {
    public static void a(Parcel parcel, Object obj) {
        parcel.writeString(obj == null ? "null" : obj.toString());
    }

    public static Long a(Parcel parcel) {
        String readString = parcel.readString();
        if (readString.equals("null")) {
            return null;
        }
        return Long.valueOf(readString);
    }

    public static Integer b(Parcel parcel) {
        String readString = parcel.readString();
        if (readString.equals("null")) {
            return null;
        }
        return Integer.valueOf(readString);
    }

    public static Boolean c(Parcel parcel) {
        String readString = parcel.readString();
        if (readString.equals("null")) {
            return null;
        }
        return Boolean.valueOf(readString);
    }

    public static String d(Parcel parcel) {
        String readString = parcel.readString();
        if (readString.equals("null")) {
            return null;
        }
        return readString;
    }
}
