package io.realm.internal.a;

import io.realm.RealmFieldType;
import io.realm.internal.Table;
import java.util.List;
import java.util.Locale;
import java.util.Set;

/* access modifiers changed from: package-private */
/* compiled from: DynamicFieldDescriptor */
public class b extends c {
    private final Table f;

    b(Table table, String str, Set<RealmFieldType> set, Set<RealmFieldType> set2) {
        super(str, set, set2);
        this.f = table;
    }

    /* access modifiers changed from: protected */
    @Override // io.realm.internal.a.c
    public void a(List<String> list) {
        int size = list.size();
        long[] jArr = new long[size];
        String str = null;
        Table table = this.f;
        String str2 = null;
        RealmFieldType realmFieldType = null;
        for (int i = 0; i < size; i++) {
            str2 = list.get(i);
            if (str2 == null || str2.length() <= 0) {
                throw new IllegalArgumentException("Invalid query: Field descriptor contains an empty field.  A field description may not begin with or contain adjacent periods ('.').");
            }
            str = table.i();
            long a2 = table.a(str2);
            if (a2 >= 0) {
                realmFieldType = table.b(a2);
                if (i < size - 1) {
                    a(str, str2, realmFieldType);
                    table = table.c(a2);
                }
                jArr[i] = a2;
            } else {
                throw new IllegalArgumentException(String.format(Locale.US, "Invalid query: field '%s' not found in table '%s'.", str2, str));
            }
        }
        a(str, str2, realmFieldType, jArr, new long[size]);
    }
}
