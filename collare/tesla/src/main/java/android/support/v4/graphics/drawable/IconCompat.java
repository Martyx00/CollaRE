package android.support.v4.graphics.drawable;

import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.PorterDuff;
import android.graphics.drawable.Icon;
import android.os.Build;
import android.os.Parcelable;
import android.util.Log;
import androidx.versionedparcelable.CustomVersionedParcelable;
import java.io.ByteArrayOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.nio.charset.Charset;

public class IconCompat extends CustomVersionedParcelable {
    static final PorterDuff.Mode h = PorterDuff.Mode.SRC_IN;

    /* renamed from: a  reason: collision with root package name */
    public int f551a;

    /* renamed from: b  reason: collision with root package name */
    Object f552b;

    /* renamed from: c  reason: collision with root package name */
    public byte[] f553c;

    /* renamed from: d  reason: collision with root package name */
    public Parcelable f554d;
    public int e;
    public int f;
    public ColorStateList g = null;
    PorterDuff.Mode i = h;
    public String j;

    private static String a(int i2) {
        switch (i2) {
            case 1:
                return "BITMAP";
            case 2:
                return "RESOURCE";
            case 3:
                return "DATA";
            case 4:
                return "URI";
            case 5:
                return "BITMAP_MASKABLE";
            default:
                return "UNKNOWN";
        }
    }

    public String a() {
        if (this.f551a == -1 && Build.VERSION.SDK_INT >= 23) {
            return a((Icon) this.f552b);
        }
        if (this.f551a == 2) {
            return ((String) this.f552b).split(":", -1)[0];
        }
        throw new IllegalStateException("called getResPackage() on " + this);
    }

    public int b() {
        if (this.f551a == -1 && Build.VERSION.SDK_INT >= 23) {
            return b((Icon) this.f552b);
        }
        if (this.f551a == 2) {
            return this.e;
        }
        throw new IllegalStateException("called getResId() on " + this);
    }

    public String toString() {
        if (this.f551a == -1) {
            return String.valueOf(this.f552b);
        }
        StringBuilder sb = new StringBuilder("Icon(typ=");
        sb.append(a(this.f551a));
        switch (this.f551a) {
            case 1:
            case 5:
                sb.append(" size=");
                sb.append(((Bitmap) this.f552b).getWidth());
                sb.append("x");
                sb.append(((Bitmap) this.f552b).getHeight());
                break;
            case 2:
                sb.append(" pkg=");
                sb.append(a());
                sb.append(" id=");
                sb.append(String.format("0x%08x", Integer.valueOf(b())));
                break;
            case 3:
                sb.append(" len=");
                sb.append(this.e);
                if (this.f != 0) {
                    sb.append(" off=");
                    sb.append(this.f);
                    break;
                }
                break;
            case 4:
                sb.append(" uri=");
                sb.append(this.f552b);
                break;
        }
        if (this.g != null) {
            sb.append(" tint=");
            sb.append(this.g);
        }
        if (this.i != h) {
            sb.append(" mode=");
            sb.append(this.i);
        }
        sb.append(")");
        return sb.toString();
    }

    @Override // androidx.versionedparcelable.CustomVersionedParcelable
    public void a(boolean z) {
        this.j = this.i.name();
        int i2 = this.f551a;
        if (i2 != -1) {
            switch (i2) {
                case 1:
                case 5:
                    if (z) {
                        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                        ((Bitmap) this.f552b).compress(Bitmap.CompressFormat.PNG, 90, byteArrayOutputStream);
                        this.f553c = byteArrayOutputStream.toByteArray();
                        return;
                    }
                    this.f554d = (Parcelable) this.f552b;
                    return;
                case 2:
                    this.f553c = ((String) this.f552b).getBytes(Charset.forName("UTF-16"));
                    return;
                case 3:
                    this.f553c = (byte[]) this.f552b;
                    return;
                case 4:
                    this.f553c = this.f552b.toString().getBytes(Charset.forName("UTF-16"));
                    return;
                default:
                    return;
            }
        } else if (!z) {
            this.f554d = (Parcelable) this.f552b;
        } else {
            throw new IllegalArgumentException("Can't serialize Icon created with IconCompat#createFromIcon");
        }
    }

    @Override // androidx.versionedparcelable.CustomVersionedParcelable
    public void c() {
        this.i = PorterDuff.Mode.valueOf(this.j);
        int i2 = this.f551a;
        if (i2 != -1) {
            switch (i2) {
                case 1:
                case 5:
                    Parcelable parcelable = this.f554d;
                    if (parcelable != null) {
                        this.f552b = parcelable;
                        return;
                    }
                    byte[] bArr = this.f553c;
                    this.f552b = bArr;
                    this.f551a = 3;
                    this.e = 0;
                    this.f = bArr.length;
                    return;
                case 2:
                case 4:
                    this.f552b = new String(this.f553c, Charset.forName("UTF-16"));
                    return;
                case 3:
                    this.f552b = this.f553c;
                    return;
                default:
                    return;
            }
        } else {
            Parcelable parcelable2 = this.f554d;
            if (parcelable2 != null) {
                this.f552b = parcelable2;
                return;
            }
            throw new IllegalArgumentException("Invalid icon");
        }
    }

    private static String a(Icon icon) {
        if (Build.VERSION.SDK_INT >= 28) {
            return icon.getResPackage();
        }
        try {
            return (String) icon.getClass().getMethod("getResPackage", new Class[0]).invoke(icon, new Object[0]);
        } catch (IllegalAccessException e2) {
            Log.e("IconCompat", "Unable to get icon package", e2);
            return null;
        } catch (InvocationTargetException e3) {
            Log.e("IconCompat", "Unable to get icon package", e3);
            return null;
        } catch (NoSuchMethodException e4) {
            Log.e("IconCompat", "Unable to get icon package", e4);
            return null;
        }
    }

    private static int b(Icon icon) {
        if (Build.VERSION.SDK_INT >= 28) {
            return icon.getResId();
        }
        try {
            return ((Integer) icon.getClass().getMethod("getResId", new Class[0]).invoke(icon, new Object[0])).intValue();
        } catch (IllegalAccessException e2) {
            Log.e("IconCompat", "Unable to get icon resource", e2);
            return 0;
        } catch (InvocationTargetException e3) {
            Log.e("IconCompat", "Unable to get icon resource", e3);
            return 0;
        } catch (NoSuchMethodException e4) {
            Log.e("IconCompat", "Unable to get icon resource", e4);
            return 0;
        }
    }
}
