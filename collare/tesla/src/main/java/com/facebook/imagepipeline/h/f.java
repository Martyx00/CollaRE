package com.facebook.imagepipeline.h;

import com.facebook.common.d.b;
import com.facebook.common.d.i;
import com.facebook.common.d.n;
import com.facebook.common.g.a;
import com.facebook.imagepipeline.j.d;
import java.io.IOException;
import java.io.InputStream;

/* compiled from: ProgressiveJpegParser */
public class f {

    /* renamed from: a  reason: collision with root package name */
    private int f2158a = 0;

    /* renamed from: b  reason: collision with root package name */
    private int f2159b = 0;

    /* renamed from: c  reason: collision with root package name */
    private int f2160c = 0;

    /* renamed from: d  reason: collision with root package name */
    private int f2161d = 0;
    private int e = 0;
    private int f = 0;
    private boolean g;
    private final a h;

    private static boolean a(int i) {
        if (i == 1) {
            return false;
        }
        return ((i >= 208 && i <= 215) || i == 217 || i == 216) ? false : true;
    }

    public f(a aVar) {
        this.h = (a) i.a(aVar);
    }

    public boolean a(d dVar) {
        if (this.f2158a == 6 || dVar.l() <= this.f2160c) {
            return false;
        }
        com.facebook.common.g.f fVar = new com.facebook.common.g.f(dVar.d(), (byte[]) this.h.a(16384), this.h);
        try {
            com.facebook.common.k.d.a(fVar, (long) this.f2160c);
            return a(fVar);
        } catch (IOException e2) {
            n.b(e2);
            return false;
        } finally {
            b.a(fVar);
        }
    }

    private boolean a(InputStream inputStream) {
        int read;
        int i = this.e;
        while (this.f2158a != 6 && (read = inputStream.read()) != -1) {
            try {
                this.f2160c++;
                if (this.g) {
                    this.f2158a = 6;
                    this.g = false;
                    return false;
                }
                switch (this.f2158a) {
                    case 0:
                        if (read != 255) {
                            this.f2158a = 6;
                            break;
                        } else {
                            this.f2158a = 1;
                            break;
                        }
                    case 1:
                        if (read != 216) {
                            this.f2158a = 6;
                            break;
                        } else {
                            this.f2158a = 2;
                            break;
                        }
                    case 2:
                        if (read != 255) {
                            break;
                        } else {
                            this.f2158a = 3;
                            break;
                        }
                    case 3:
                        if (read != 255) {
                            if (read != 0) {
                                if (read != 217) {
                                    if (read == 218) {
                                        b(this.f2160c - 2);
                                    }
                                    if (!a(read)) {
                                        this.f2158a = 2;
                                        break;
                                    } else {
                                        this.f2158a = 4;
                                        break;
                                    }
                                } else {
                                    this.g = true;
                                    b(this.f2160c - 2);
                                    this.f2158a = 2;
                                    break;
                                }
                            } else {
                                this.f2158a = 2;
                                break;
                            }
                        } else {
                            this.f2158a = 3;
                            break;
                        }
                    case 4:
                        this.f2158a = 5;
                        break;
                    case 5:
                        int i2 = ((this.f2159b << 8) + read) - 2;
                        com.facebook.common.k.d.a(inputStream, (long) i2);
                        this.f2160c += i2;
                        this.f2158a = 2;
                        break;
                    default:
                        i.b(false);
                        break;
                }
                this.f2159b = read;
            } catch (IOException e2) {
                n.b(e2);
            }
        }
        if (this.f2158a == 6 || this.e == i) {
            return false;
        }
        return true;
    }

    private void b(int i) {
        if (this.f2161d > 0) {
            this.f = i;
        }
        int i2 = this.f2161d;
        this.f2161d = i2 + 1;
        this.e = i2;
    }

    public int a() {
        return this.f;
    }

    public int b() {
        return this.e;
    }

    public boolean c() {
        return this.g;
    }
}
