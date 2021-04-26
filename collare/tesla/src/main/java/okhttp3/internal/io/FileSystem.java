package okhttp3.internal.io;

import c.l;
import c.s;
import c.t;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public interface FileSystem {
    public static final FileSystem SYSTEM = new FileSystem() {
        /* class okhttp3.internal.io.FileSystem.AnonymousClass1 */

        @Override // okhttp3.internal.io.FileSystem
        public t source(File file) {
            return l.a(file);
        }

        @Override // okhttp3.internal.io.FileSystem
        public s sink(File file) {
            try {
                return l.b(file);
            } catch (FileNotFoundException unused) {
                file.getParentFile().mkdirs();
                return l.b(file);
            }
        }

        @Override // okhttp3.internal.io.FileSystem
        public s appendingSink(File file) {
            try {
                return l.c(file);
            } catch (FileNotFoundException unused) {
                file.getParentFile().mkdirs();
                return l.c(file);
            }
        }

        @Override // okhttp3.internal.io.FileSystem
        public void delete(File file) {
            if (!file.delete() && file.exists()) {
                throw new IOException("failed to delete " + file);
            }
        }

        @Override // okhttp3.internal.io.FileSystem
        public boolean exists(File file) {
            return file.exists();
        }

        @Override // okhttp3.internal.io.FileSystem
        public long size(File file) {
            return file.length();
        }

        @Override // okhttp3.internal.io.FileSystem
        public void rename(File file, File file2) {
            delete(file2);
            if (!file.renameTo(file2)) {
                throw new IOException("failed to rename " + file + " to " + file2);
            }
        }

        @Override // okhttp3.internal.io.FileSystem
        public void deleteContents(File file) {
            File[] listFiles = file.listFiles();
            if (listFiles != null) {
                for (File file2 : listFiles) {
                    if (file2.isDirectory()) {
                        deleteContents(file2);
                    }
                    if (!file2.delete()) {
                        throw new IOException("failed to delete " + file2);
                    }
                }
                return;
            }
            throw new IOException("not a readable directory: " + file);
        }
    };

    s appendingSink(File file);

    void delete(File file);

    void deleteContents(File file);

    boolean exists(File file);

    void rename(File file, File file2);

    s sink(File file);

    long size(File file);

    t source(File file);
}
