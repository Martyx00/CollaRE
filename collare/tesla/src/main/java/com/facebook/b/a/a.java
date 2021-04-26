package com.facebook.b.a;

/* compiled from: CacheErrorLogger */
public interface a {

    /* renamed from: com.facebook.b.a.a$a  reason: collision with other inner class name */
    /* compiled from: CacheErrorLogger */
    public enum EnumC0036a {
        READ_DECODE,
        READ_FILE,
        READ_FILE_NOT_FOUND,
        READ_INVALID_ENTRY,
        WRITE_ENCODE,
        WRITE_CREATE_TEMPFILE,
        WRITE_UPDATE_FILE_NOT_FOUND,
        WRITE_RENAME_FILE_TEMPFILE_NOT_FOUND,
        WRITE_RENAME_FILE_TEMPFILE_PARENT_NOT_FOUND,
        WRITE_RENAME_FILE_OTHER,
        WRITE_CREATE_DIR,
        WRITE_CALLBACK_ERROR,
        WRITE_INVALID_ENTRY,
        DELETE_FILE,
        EVICTION,
        GENERIC_IO,
        OTHER
    }

    void a(EnumC0036a aVar, Class<?> cls, String str, Throwable th);
}
