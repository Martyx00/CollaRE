package io.realm.internal.core;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import io.realm.RealmFieldType;
import io.realm.ab;
import io.realm.internal.Keep;
import io.realm.internal.Table;
import io.realm.internal.a.c;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

@Keep
public class QueryDescriptor {
    public static final Set<RealmFieldType> DISTINCT_VALID_FIELD_TYPES = Collections.unmodifiableSet(new HashSet(Arrays.asList(RealmFieldType.BOOLEAN, RealmFieldType.INTEGER, RealmFieldType.STRING, RealmFieldType.DATE)));
    public static final Set<RealmFieldType> SORT_VALID_FIELD_TYPES = Collections.unmodifiableSet(new HashSet(Arrays.asList(RealmFieldType.BOOLEAN, RealmFieldType.INTEGER, RealmFieldType.FLOAT, RealmFieldType.DOUBLE, RealmFieldType.STRING, RealmFieldType.DATE)));
    private final boolean[] ascendings;
    private final long[][] columnIndices;
    private final Table table;

    public static QueryDescriptor getInstanceForSort(c.a aVar, Table table2, String str, ab abVar) {
        return getInstanceForSort(aVar, table2, new String[]{str}, new ab[]{abVar});
    }

    public static QueryDescriptor getInstanceForSort(c.a aVar, Table table2, String[] strArr, ab[] abVarArr) {
        if (abVarArr == null || abVarArr.length == 0) {
            throw new IllegalArgumentException("You must provide at least one sort order.");
        } else if (strArr.length == abVarArr.length) {
            return getInstance(aVar, table2, strArr, abVarArr, c.f6248d, SORT_VALID_FIELD_TYPES, "Sort is not supported");
        } else {
            throw new IllegalArgumentException("Number of fields and sort orders do not match.");
        }
    }

    public static QueryDescriptor getInstanceForDistinct(c.a aVar, Table table2, String str) {
        return getInstanceForDistinct(aVar, table2, new String[]{str});
    }

    public static QueryDescriptor getInstanceForDistinct(c.a aVar, Table table2, String[] strArr) {
        return getInstance(aVar, table2, strArr, null, c.e, DISTINCT_VALID_FIELD_TYPES, "Distinct is not supported");
    }

    private static QueryDescriptor getInstance(c.a aVar, Table table2, String[] strArr, ab[] abVarArr, Set<RealmFieldType> set, Set<RealmFieldType> set2, String str) {
        if (strArr == null || strArr.length == 0) {
            throw new IllegalArgumentException("You must provide at least one field name.");
        }
        long[][] jArr = new long[strArr.length][];
        for (int i = 0; i < strArr.length; i++) {
            c a2 = c.a(aVar, table2, strArr[i], set, (Set<RealmFieldType>) null);
            checkFieldType(a2, set2, str, strArr[i]);
            jArr[i] = a2.a();
        }
        return new QueryDescriptor(table2, jArr, abVarArr);
    }

    public static QueryDescriptor getTestInstance(Table table2, long[] jArr) {
        return new QueryDescriptor(table2, new long[][]{jArr}, null);
    }

    private static void checkFieldType(c cVar, Set<RealmFieldType> set, String str, String str2) {
        if (!set.contains(cVar.d())) {
            throw new IllegalArgumentException(String.format(Locale.US, "%s on '%s' field '%s' in '%s'.", str, cVar.d(), cVar.c(), str2));
        }
    }

    private QueryDescriptor(Table table2, long[][] jArr, ab[] abVarArr) {
        this.table = table2;
        this.columnIndices = jArr;
        if (abVarArr != null) {
            this.ascendings = new boolean[abVarArr.length];
            for (int i = 0; i < abVarArr.length; i++) {
                this.ascendings[i] = abVarArr[i].a();
            }
            return;
        }
        this.ascendings = null;
    }

    @SuppressFBWarnings({"EI_EXPOSE_REP"})
    public long[][] getColumnIndices() {
        return this.columnIndices;
    }

    @SuppressFBWarnings({"EI_EXPOSE_REP"})
    public boolean[] getAscendings() {
        return this.ascendings;
    }

    private long getTablePtr() {
        return this.table.getNativePtr();
    }
}
