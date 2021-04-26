package com.crashlytics.android.core;

public class UserMetaData {
    public static final UserMetaData EMPTY = new UserMetaData();
    public final String email;
    public final String id;
    public final String name;

    public UserMetaData() {
        this(null, null, null);
    }

    public UserMetaData(String str, String str2, String str3) {
        this.id = str;
        this.name = str2;
        this.email = str3;
    }

    public boolean isEmpty() {
        return this.id == null && this.name == null && this.email == null;
    }
}
