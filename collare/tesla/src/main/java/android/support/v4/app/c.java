package android.support.v4.app;

import android.support.v4.app.g;
import android.support.v4.app.m;
import android.support.v4.util.e;
import android.util.Log;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.lang.reflect.Modifier;
import java.util.ArrayList;

/* access modifiers changed from: package-private */
/* compiled from: BackStackRecord */
public final class c extends q implements m.h {

    /* renamed from: a  reason: collision with root package name */
    final m f173a;

    /* renamed from: b  reason: collision with root package name */
    ArrayList<a> f174b = new ArrayList<>();

    /* renamed from: c  reason: collision with root package name */
    int f175c;

    /* renamed from: d  reason: collision with root package name */
    int f176d;
    int e;
    int f;
    int g;
    int h;
    boolean i;
    boolean j = true;
    String k;
    boolean l;
    int m = -1;
    int n;
    CharSequence o;
    int p;
    CharSequence q;
    ArrayList<String> r;
    ArrayList<String> s;
    boolean t = false;
    ArrayList<Runnable> u;

    /* access modifiers changed from: package-private */
    /* compiled from: BackStackRecord */
    public static final class a {

        /* renamed from: a  reason: collision with root package name */
        int f177a;

        /* renamed from: b  reason: collision with root package name */
        g f178b;

        /* renamed from: c  reason: collision with root package name */
        int f179c;

        /* renamed from: d  reason: collision with root package name */
        int f180d;
        int e;
        int f;

        a() {
        }

        a(int i, g gVar) {
            this.f177a = i;
            this.f178b = gVar;
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(128);
        sb.append("BackStackEntry{");
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        if (this.m >= 0) {
            sb.append(" #");
            sb.append(this.m);
        }
        if (this.k != null) {
            sb.append(" ");
            sb.append(this.k);
        }
        sb.append("}");
        return sb.toString();
    }

    public void a(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        a(str, printWriter, true);
    }

    public void a(String str, PrintWriter printWriter, boolean z) {
        String str2;
        if (z) {
            printWriter.print(str);
            printWriter.print("mName=");
            printWriter.print(this.k);
            printWriter.print(" mIndex=");
            printWriter.print(this.m);
            printWriter.print(" mCommitted=");
            printWriter.println(this.l);
            if (this.g != 0) {
                printWriter.print(str);
                printWriter.print("mTransition=#");
                printWriter.print(Integer.toHexString(this.g));
                printWriter.print(" mTransitionStyle=#");
                printWriter.println(Integer.toHexString(this.h));
            }
            if (!(this.f175c == 0 && this.f176d == 0)) {
                printWriter.print(str);
                printWriter.print("mEnterAnim=#");
                printWriter.print(Integer.toHexString(this.f175c));
                printWriter.print(" mExitAnim=#");
                printWriter.println(Integer.toHexString(this.f176d));
            }
            if (!(this.e == 0 && this.f == 0)) {
                printWriter.print(str);
                printWriter.print("mPopEnterAnim=#");
                printWriter.print(Integer.toHexString(this.e));
                printWriter.print(" mPopExitAnim=#");
                printWriter.println(Integer.toHexString(this.f));
            }
            if (!(this.n == 0 && this.o == null)) {
                printWriter.print(str);
                printWriter.print("mBreadCrumbTitleRes=#");
                printWriter.print(Integer.toHexString(this.n));
                printWriter.print(" mBreadCrumbTitleText=");
                printWriter.println(this.o);
            }
            if (!(this.p == 0 && this.q == null)) {
                printWriter.print(str);
                printWriter.print("mBreadCrumbShortTitleRes=#");
                printWriter.print(Integer.toHexString(this.p));
                printWriter.print(" mBreadCrumbShortTitleText=");
                printWriter.println(this.q);
            }
        }
        if (!this.f174b.isEmpty()) {
            printWriter.print(str);
            printWriter.println("Operations:");
            String str3 = str + "    ";
            int size = this.f174b.size();
            for (int i2 = 0; i2 < size; i2++) {
                a aVar = this.f174b.get(i2);
                switch (aVar.f177a) {
                    case 0:
                        str2 = "NULL";
                        break;
                    case 1:
                        str2 = "ADD";
                        break;
                    case 2:
                        str2 = "REPLACE";
                        break;
                    case 3:
                        str2 = "REMOVE";
                        break;
                    case 4:
                        str2 = "HIDE";
                        break;
                    case 5:
                        str2 = "SHOW";
                        break;
                    case 6:
                        str2 = "DETACH";
                        break;
                    case 7:
                        str2 = "ATTACH";
                        break;
                    case 8:
                        str2 = "SET_PRIMARY_NAV";
                        break;
                    case 9:
                        str2 = "UNSET_PRIMARY_NAV";
                        break;
                    default:
                        str2 = "cmd=" + aVar.f177a;
                        break;
                }
                printWriter.print(str);
                printWriter.print("  Op #");
                printWriter.print(i2);
                printWriter.print(": ");
                printWriter.print(str2);
                printWriter.print(" ");
                printWriter.println(aVar.f178b);
                if (z) {
                    if (!(aVar.f179c == 0 && aVar.f180d == 0)) {
                        printWriter.print(str);
                        printWriter.print("enterAnim=#");
                        printWriter.print(Integer.toHexString(aVar.f179c));
                        printWriter.print(" exitAnim=#");
                        printWriter.println(Integer.toHexString(aVar.f180d));
                    }
                    if (aVar.e != 0 || aVar.f != 0) {
                        printWriter.print(str);
                        printWriter.print("popEnterAnim=#");
                        printWriter.print(Integer.toHexString(aVar.e));
                        printWriter.print(" popExitAnim=#");
                        printWriter.println(Integer.toHexString(aVar.f));
                    }
                }
            }
        }
    }

    public c(m mVar) {
        this.f173a = mVar;
    }

    /* access modifiers changed from: package-private */
    public void a(a aVar) {
        this.f174b.add(aVar);
        aVar.f179c = this.f175c;
        aVar.f180d = this.f176d;
        aVar.e = this.e;
        aVar.f = this.f;
    }

    @Override // android.support.v4.app.q
    public q a(g gVar, String str) {
        a(0, gVar, str, 1);
        return this;
    }

    private void a(int i2, g gVar, String str, int i3) {
        Class<?> cls = gVar.getClass();
        int modifiers = cls.getModifiers();
        if (cls.isAnonymousClass() || !Modifier.isPublic(modifiers) || (cls.isMemberClass() && !Modifier.isStatic(modifiers))) {
            throw new IllegalStateException("Fragment " + cls.getCanonicalName() + " must be a public static class to be  properly recreated from" + " instance state.");
        }
        gVar.mFragmentManager = this.f173a;
        if (str != null) {
            if (gVar.mTag == null || str.equals(gVar.mTag)) {
                gVar.mTag = str;
            } else {
                throw new IllegalStateException("Can't change tag of fragment " + gVar + ": was " + gVar.mTag + " now " + str);
            }
        }
        if (i2 != 0) {
            if (i2 == -1) {
                throw new IllegalArgumentException("Can't add fragment " + gVar + " with tag " + str + " to container view with no id");
            } else if (gVar.mFragmentId == 0 || gVar.mFragmentId == i2) {
                gVar.mFragmentId = i2;
                gVar.mContainerId = i2;
            } else {
                throw new IllegalStateException("Can't change container ID of fragment " + gVar + ": was " + gVar.mFragmentId + " now " + i2);
            }
        }
        a(new a(i3, gVar));
    }

    @Override // android.support.v4.app.q
    public q a(g gVar) {
        a(new a(3, gVar));
        return this;
    }

    public q a() {
        if (!this.i) {
            this.j = false;
            return this;
        }
        throw new IllegalStateException("This transaction is already being added to the back stack");
    }

    /* access modifiers changed from: package-private */
    public void a(int i2) {
        if (this.i) {
            if (m.f209a) {
                Log.v("FragmentManager", "Bump nesting in " + this + " by " + i2);
            }
            int size = this.f174b.size();
            for (int i3 = 0; i3 < size; i3++) {
                a aVar = this.f174b.get(i3);
                if (aVar.f178b != null) {
                    aVar.f178b.mBackStackNesting += i2;
                    if (m.f209a) {
                        Log.v("FragmentManager", "Bump nesting of " + aVar.f178b + " to " + aVar.f178b.mBackStackNesting);
                    }
                }
            }
        }
    }

    public void b() {
        ArrayList<Runnable> arrayList = this.u;
        if (arrayList != null) {
            int size = arrayList.size();
            for (int i2 = 0; i2 < size; i2++) {
                this.u.get(i2).run();
            }
            this.u = null;
        }
    }

    @Override // android.support.v4.app.q
    public int c() {
        return a(false);
    }

    @Override // android.support.v4.app.q
    public int d() {
        return a(true);
    }

    @Override // android.support.v4.app.q
    public void e() {
        a();
        this.f173a.b((m.h) this, false);
    }

    /* access modifiers changed from: package-private */
    public int a(boolean z) {
        if (!this.l) {
            if (m.f209a) {
                Log.v("FragmentManager", "Commit: " + this);
                PrintWriter printWriter = new PrintWriter(new e("FragmentManager"));
                a("  ", (FileDescriptor) null, printWriter, (String[]) null);
                printWriter.close();
            }
            this.l = true;
            if (this.i) {
                this.m = this.f173a.a(this);
            } else {
                this.m = -1;
            }
            this.f173a.a(this, z);
            return this.m;
        }
        throw new IllegalStateException("commit already called");
    }

    @Override // android.support.v4.app.m.h
    public boolean a(ArrayList<c> arrayList, ArrayList<Boolean> arrayList2) {
        if (m.f209a) {
            Log.v("FragmentManager", "Run: " + this);
        }
        arrayList.add(this);
        arrayList2.add(false);
        if (!this.i) {
            return true;
        }
        this.f173a.b(this);
        return true;
    }

    /* access modifiers changed from: package-private */
    public boolean b(int i2) {
        int size = this.f174b.size();
        for (int i3 = 0; i3 < size; i3++) {
            a aVar = this.f174b.get(i3);
            int i4 = aVar.f178b != null ? aVar.f178b.mContainerId : 0;
            if (i4 != 0 && i4 == i2) {
                return true;
            }
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    public boolean a(ArrayList<c> arrayList, int i2, int i3) {
        if (i3 == i2) {
            return false;
        }
        int size = this.f174b.size();
        int i4 = -1;
        for (int i5 = 0; i5 < size; i5++) {
            a aVar = this.f174b.get(i5);
            int i6 = aVar.f178b != null ? aVar.f178b.mContainerId : 0;
            if (!(i6 == 0 || i6 == i4)) {
                for (int i7 = i2; i7 < i3; i7++) {
                    c cVar = arrayList.get(i7);
                    int size2 = cVar.f174b.size();
                    for (int i8 = 0; i8 < size2; i8++) {
                        a aVar2 = cVar.f174b.get(i8);
                        if ((aVar2.f178b != null ? aVar2.f178b.mContainerId : 0) == i6) {
                            return true;
                        }
                    }
                }
                i4 = i6;
            }
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    public void f() {
        int size = this.f174b.size();
        for (int i2 = 0; i2 < size; i2++) {
            a aVar = this.f174b.get(i2);
            g gVar = aVar.f178b;
            if (gVar != null) {
                gVar.setNextTransition(this.g, this.h);
            }
            int i3 = aVar.f177a;
            if (i3 != 1) {
                switch (i3) {
                    case 3:
                        gVar.setNextAnim(aVar.f180d);
                        this.f173a.h(gVar);
                        break;
                    case 4:
                        gVar.setNextAnim(aVar.f180d);
                        this.f173a.i(gVar);
                        break;
                    case 5:
                        gVar.setNextAnim(aVar.f179c);
                        this.f173a.j(gVar);
                        break;
                    case 6:
                        gVar.setNextAnim(aVar.f180d);
                        this.f173a.k(gVar);
                        break;
                    case 7:
                        gVar.setNextAnim(aVar.f179c);
                        this.f173a.l(gVar);
                        break;
                    case 8:
                        this.f173a.o(gVar);
                        break;
                    case 9:
                        this.f173a.o(null);
                        break;
                    default:
                        throw new IllegalArgumentException("Unknown cmd: " + aVar.f177a);
                }
            } else {
                gVar.setNextAnim(aVar.f179c);
                this.f173a.a(gVar, false);
            }
            if (!(this.t || aVar.f177a == 1 || gVar == null)) {
                this.f173a.e(gVar);
            }
        }
        if (!this.t) {
            m mVar = this.f173a;
            mVar.a(mVar.l, true);
        }
    }

    /* access modifiers changed from: package-private */
    public void b(boolean z) {
        for (int size = this.f174b.size() - 1; size >= 0; size--) {
            a aVar = this.f174b.get(size);
            g gVar = aVar.f178b;
            if (gVar != null) {
                gVar.setNextTransition(m.d(this.g), this.h);
            }
            int i2 = aVar.f177a;
            if (i2 != 1) {
                switch (i2) {
                    case 3:
                        gVar.setNextAnim(aVar.e);
                        this.f173a.a(gVar, false);
                        break;
                    case 4:
                        gVar.setNextAnim(aVar.e);
                        this.f173a.j(gVar);
                        break;
                    case 5:
                        gVar.setNextAnim(aVar.f);
                        this.f173a.i(gVar);
                        break;
                    case 6:
                        gVar.setNextAnim(aVar.e);
                        this.f173a.l(gVar);
                        break;
                    case 7:
                        gVar.setNextAnim(aVar.f);
                        this.f173a.k(gVar);
                        break;
                    case 8:
                        this.f173a.o(null);
                        break;
                    case 9:
                        this.f173a.o(gVar);
                        break;
                    default:
                        throw new IllegalArgumentException("Unknown cmd: " + aVar.f177a);
                }
            } else {
                gVar.setNextAnim(aVar.f);
                this.f173a.h(gVar);
            }
            if (!(this.t || aVar.f177a == 3 || gVar == null)) {
                this.f173a.e(gVar);
            }
        }
        if (!this.t && z) {
            m mVar = this.f173a;
            mVar.a(mVar.l, true);
        }
    }

    /* access modifiers changed from: package-private */
    public g a(ArrayList<g> arrayList, g gVar) {
        g gVar2 = gVar;
        int i2 = 0;
        while (i2 < this.f174b.size()) {
            a aVar = this.f174b.get(i2);
            switch (aVar.f177a) {
                case 1:
                case 7:
                    arrayList.add(aVar.f178b);
                    break;
                case 2:
                    g gVar3 = aVar.f178b;
                    int i3 = gVar3.mContainerId;
                    g gVar4 = gVar2;
                    int i4 = i2;
                    boolean z = false;
                    for (int size = arrayList.size() - 1; size >= 0; size--) {
                        g gVar5 = arrayList.get(size);
                        if (gVar5.mContainerId == i3) {
                            if (gVar5 == gVar3) {
                                z = true;
                            } else {
                                if (gVar5 == gVar4) {
                                    this.f174b.add(i4, new a(9, gVar5));
                                    i4++;
                                    gVar4 = null;
                                }
                                a aVar2 = new a(3, gVar5);
                                aVar2.f179c = aVar.f179c;
                                aVar2.e = aVar.e;
                                aVar2.f180d = aVar.f180d;
                                aVar2.f = aVar.f;
                                this.f174b.add(i4, aVar2);
                                arrayList.remove(gVar5);
                                i4++;
                            }
                        }
                    }
                    if (z) {
                        this.f174b.remove(i4);
                        i2 = i4 - 1;
                    } else {
                        aVar.f177a = 1;
                        arrayList.add(gVar3);
                        i2 = i4;
                    }
                    gVar2 = gVar4;
                    break;
                case 3:
                case 6:
                    arrayList.remove(aVar.f178b);
                    if (aVar.f178b == gVar2) {
                        this.f174b.add(i2, new a(9, aVar.f178b));
                        i2++;
                        gVar2 = null;
                        break;
                    } else {
                        break;
                    }
                case 8:
                    this.f174b.add(i2, new a(9, gVar2));
                    i2++;
                    gVar2 = aVar.f178b;
                    break;
            }
            i2++;
        }
        return gVar2;
    }

    /* access modifiers changed from: package-private */
    public g b(ArrayList<g> arrayList, g gVar) {
        for (int i2 = 0; i2 < this.f174b.size(); i2++) {
            a aVar = this.f174b.get(i2);
            int i3 = aVar.f177a;
            if (i3 != 1) {
                if (i3 != 3) {
                    switch (i3) {
                        case 8:
                            gVar = null;
                            break;
                        case 9:
                            gVar = aVar.f178b;
                            break;
                    }
                }
                arrayList.add(aVar.f178b);
            }
            arrayList.remove(aVar.f178b);
        }
        return gVar;
    }

    /* access modifiers changed from: package-private */
    public boolean g() {
        for (int i2 = 0; i2 < this.f174b.size(); i2++) {
            if (b(this.f174b.get(i2))) {
                return true;
            }
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    public void a(g.c cVar) {
        for (int i2 = 0; i2 < this.f174b.size(); i2++) {
            a aVar = this.f174b.get(i2);
            if (b(aVar)) {
                aVar.f178b.setOnStartEnterTransitionListener(cVar);
            }
        }
    }

    private static boolean b(a aVar) {
        g gVar = aVar.f178b;
        return gVar != null && gVar.mAdded && gVar.mView != null && !gVar.mDetached && !gVar.mHidden && gVar.isPostponed();
    }

    public String h() {
        return this.k;
    }
}
