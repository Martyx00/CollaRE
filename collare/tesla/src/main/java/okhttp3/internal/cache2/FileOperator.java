package okhttp3.internal.cache2;

import c.c;
import java.nio.channels.FileChannel;

final class FileOperator {
    private final FileChannel fileChannel;

    FileOperator(FileChannel fileChannel2) {
        this.fileChannel = fileChannel2;
    }

    public void write(long j, c cVar, long j2) {
        if (j2 < 0 || j2 > cVar.a()) {
            throw new IndexOutOfBoundsException();
        }
        long j3 = j;
        long j4 = j2;
        while (j4 > 0) {
            long transferFrom = this.fileChannel.transferFrom(cVar, j3, j4);
            j3 += transferFrom;
            j4 -= transferFrom;
        }
    }

    public void read(long j, c cVar, long j2) {
        if (j2 >= 0) {
            while (j2 > 0) {
                long transferTo = this.fileChannel.transferTo(j, j2, cVar);
                j += transferTo;
                j2 -= transferTo;
            }
            return;
        }
        throw new IndexOutOfBoundsException();
    }
}
