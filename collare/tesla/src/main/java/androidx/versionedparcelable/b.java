package androidx.versionedparcelable;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.SparseIntArray;

/* compiled from: VersionedParcelParcel */
class b extends a {

    /* renamed from: a  reason: collision with root package name */
    private final SparseIntArray f1310a;

    /* renamed from: b  reason: collision with root package name */
    private final Parcel f1311b;

    /* renamed from: c  reason: collision with root package name */
    private final int f1312c;

    /* renamed from: d  reason: collision with root package name */
    private final int f1313d;
    private final String e;
    private int f;
    private int g;

    b(Parcel parcel) {
        this(parcel, parcel.dataPosition(), parcel.dataSize(), "");
    }

    b(Parcel parcel, int i, int i2, String str) {
        this.f1310a = new SparseIntArray();
        this.f = -1;
        this.g = 0;
        this.f1311b = parcel;
        this.f1312c = i;
        this.f1313d = i2;
        this.g = this.f1312c;
        this.e = str;
    }

    private int d(int i) {
        int readInt;
        do {
            int i2 = this.g;
            if (i2 >= this.f1313d) {
                return -1;
            }
            this.f1311b.setDataPosition(i2);
            int readInt2 = this.f1311b.readInt();
            readInt = this.f1311b.readInt();
            this.g += readInt2;
        } while (readInt != i);
        return this.f1311b.dataPosition();
    }

    @Override // androidx.versionedparcelable.a
    public boolean b(int i) {
        int d2 = d(i);
        if (d2 == -1) {
            return false;
        }
        this.f1311b.setDataPosition(d2);
        return true;
    }

    @Override // androidx.versionedparcelable.a
    public void c(int i) {
        b();
        this.f = i;
        this.f1310a.put(i, this.f1311b.dataPosition());
        a(0);
        a(i);
    }

    @Override // androidx.versionedparcelable.a
    public void b() {
        int i = this.f;
        if (i >= 0) {
            int i2 = this.f1310a.get(i);
            int dataPosition = this.f1311b.dataPosition();
            this.f1311b.setDataPosition(i2);
            this.f1311b.writeInt(dataPosition - i2);
            this.f1311b.setDataPosition(dataPosition);
        }
    }

    /* access modifiers changed from: protected */
    @Override // androidx.versionedparcelable.a
    public a c() {
        Parcel parcel = this.f1311b;
        int dataPosition = parcel.dataPosition();
        int i = this.g;
        if (i == this.f1312c) {
            i = this.f1313d;
        }
        return new b(parcel, dataPosition, i, this.e + "  ");
    }

    @Override // androidx.versionedparcelable.a
    public void a(byte[] bArr) {
        if (bArr != null) {
            this.f1311b.writeInt(bArr.length);
            this.f1311b.writeByteArray(bArr);
            return;
        }
        this.f1311b.writeInt(-1);
    }

    @Override // androidx.versionedparcelable.a
    public void a(int i) {
        this.f1311b.writeInt(i);
    }

    @Override // androidx.versionedparcelable.a
    public void a(String str) {
        this.f1311b.writeString(str);
    }

    @Override // androidx.versionedparcelable.a
    public void a(Parcelable parcelable) {
        this.f1311b.writeParcelable(parcelable, 0);
    }

    @Override // androidx.versionedparcelable.a
    public int d() {
        return this.f1311b.readInt();
    }

    @Override // androidx.versionedparcelable.a
    public String e() {
        return this.f1311b.readString();
    }

    @Override // androidx.versionedparcelable.a
    public byte[] f() {
        int readInt = this.f1311b.readInt();
        if (readInt < 0) {
            return null;
        }
        byte[] bArr = new byte[readInt];
        this.f1311b.readByteArray(bArr);
        return bArr;
    }

    @Override // androidx.versionedparcelable.a
    public <T extends Parcelable> T g() {
        return (T) this.f1311b.readParcelable(getClass().getClassLoader());
    }
}
