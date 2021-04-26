package com.crashlytics.android.core;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.Comparator;

/* access modifiers changed from: package-private */
public final class Utils {
    private static final FilenameFilter ALL_FILES_FILTER = new FilenameFilter() {
        /* class com.crashlytics.android.core.Utils.AnonymousClass1 */

        public boolean accept(File file, String str) {
            return true;
        }
    };

    private Utils() {
    }

    static int capFileCount(File file, int i, Comparator<File> comparator) {
        return capFileCount(file, ALL_FILES_FILTER, i, comparator);
    }

    static int capFileCount(File file, FilenameFilter filenameFilter, int i, Comparator<File> comparator) {
        File[] listFiles = file.listFiles(filenameFilter);
        if (listFiles == null) {
            return 0;
        }
        int length = listFiles.length;
        Arrays.sort(listFiles, comparator);
        for (File file2 : listFiles) {
            if (length <= i) {
                return length;
            }
            file2.delete();
            length--;
        }
        return length;
    }
}
