package io.realm.internal.a;

import io.realm.RealmFieldType;
import io.realm.internal.a.c;
import io.realm.internal.c;
import java.util.List;
import java.util.Locale;
import java.util.Set;

/* access modifiers changed from: package-private */
/* compiled from: CachedFieldDescriptor */
public class a extends c {
    private final c.a f;
    private final String g;

    a(c.a aVar, String str, String str2, Set<RealmFieldType> set, Set<RealmFieldType> set2) {
        super(str2, set, set2);
        this.g = str;
        this.f = aVar;
    }

    /* access modifiers changed from: protected */
    @Override // io.realm.internal.a.c
    public void a(List<String> list) {
        long j;
        int size = list.size();
        long[] jArr = new long[size];
        long[] jArr2 = new long[size];
        String str = null;
        RealmFieldType realmFieldType = null;
        String str2 = this.g;
        int i = 0;
        while (i < size) {
            str = list.get(i);
            if (str == null || str.length() <= 0) {
                throw new IllegalArgumentException("Invalid query: Field descriptor contains an empty field.  A field description may not begin with or contain adjacent periods ('.').");
            }
            io.realm.internal.c a2 = this.f.a(str2);
            if (a2 != null) {
                c.a a3 = a2.a(str);
                if (a3 != null) {
                    RealmFieldType realmFieldType2 = a3.f6270b;
                    if (i < size - 1) {
                        a(str2, str, realmFieldType2);
                        str2 = a3.f6271c;
                    }
                    jArr[i] = a3.f6269a;
                    if (realmFieldType2 != RealmFieldType.LINKING_OBJECTS) {
                        j = 0;
                    } else {
                        j = this.f.b(a3.f6271c);
                    }
                    jArr2[i] = j;
                    i++;
                    realmFieldType = realmFieldType2;
                } else {
                    throw new IllegalArgumentException(String.format(Locale.US, "Invalid query: field '%s' not found in class '%s'.", str, str2));
                }
            } else {
                throw new IllegalArgumentException(String.format(Locale.US, "Invalid query: class '%s' not found in this schema.", str2));
            }
        }
        a(str2, str, realmFieldType, jArr, jArr2);
    }
}
