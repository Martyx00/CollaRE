package com.google.android.gms.common.sqlite;

import android.database.AbstractWindowedCursor;
import android.database.CrossProcessCursor;
import android.database.Cursor;
import android.database.CursorWindow;
import com.google.android.gms.common.annotation.KeepForSdk;

@KeepForSdk
public class CursorWrapper extends android.database.CursorWrapper implements CrossProcessCursor {
    private AbstractWindowedCursor zzez;

    @KeepForSdk
    public CursorWrapper(Cursor cursor) {
        super(cursor);
        for (int i = 0; i < 10 && (cursor instanceof android.database.CursorWrapper); i++) {
            cursor = ((android.database.CursorWrapper) cursor).getWrappedCursor();
        }
        if (!(cursor instanceof AbstractWindowedCursor)) {
            String valueOf = String.valueOf(cursor.getClass().getName());
            throw new IllegalArgumentException(valueOf.length() != 0 ? "Unknown type: ".concat(valueOf) : new String("Unknown type: "));
        }
        this.zzez = (AbstractWindowedCursor) cursor;
    }

    @KeepForSdk
    public CursorWindow getWindow() {
        return this.zzez.getWindow();
    }

    @KeepForSdk
    public void setWindow(CursorWindow cursorWindow) {
        this.zzez.setWindow(cursorWindow);
    }

    @KeepForSdk
    public void fillWindow(int i, CursorWindow cursorWindow) {
        this.zzez.fillWindow(i, cursorWindow);
    }

    public boolean onMove(int i, int i2) {
        return this.zzez.onMove(i, i2);
    }

    public /* synthetic */ Cursor getWrappedCursor() {
        return this.zzez;
    }
}
