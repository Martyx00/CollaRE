package io.a.a.a.a.c;

import io.a.a.a.a.c.b;
import io.a.a.a.a.c.i;
import io.a.a.a.a.c.l;
import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/* compiled from: DependencyPriorityBlockingQueue */
public class c<E extends b & l & i> extends PriorityBlockingQueue<E> {

    /* renamed from: a  reason: collision with root package name */
    final Queue<E> f5966a = new LinkedList();

    /* renamed from: b  reason: collision with root package name */
    private final ReentrantLock f5967b = new ReentrantLock();

    /* renamed from: a */
    public E take() {
        return b(0, null, null);
    }

    /* renamed from: b */
    public E peek() {
        try {
            return b(1, null, null);
        } catch (InterruptedException unused) {
            return null;
        }
    }

    /* renamed from: a */
    public E poll(long j, TimeUnit timeUnit) {
        return b(3, Long.valueOf(j), timeUnit);
    }

    /* renamed from: c */
    public E poll() {
        try {
            return b(2, null, null);
        } catch (InterruptedException unused) {
            return null;
        }
    }

    public int size() {
        try {
            this.f5967b.lock();
            return this.f5966a.size() + super.size();
        } finally {
            this.f5967b.unlock();
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.concurrent.PriorityBlockingQueue
    public <T> T[] toArray(T[] tArr) {
        try {
            this.f5967b.lock();
            return (T[]) a(super.toArray(tArr), this.f5966a.toArray(tArr));
        } finally {
            this.f5967b.unlock();
        }
    }

    public Object[] toArray() {
        try {
            this.f5967b.lock();
            return a(super.toArray(), this.f5966a.toArray());
        } finally {
            this.f5967b.unlock();
        }
    }

    @Override // java.util.concurrent.BlockingQueue, java.util.concurrent.PriorityBlockingQueue
    public int drainTo(Collection<? super E> collection) {
        try {
            this.f5967b.lock();
            int drainTo = super.drainTo(collection) + this.f5966a.size();
            while (!this.f5966a.isEmpty()) {
                collection.add(this.f5966a.poll());
            }
            return drainTo;
        } finally {
            this.f5967b.unlock();
        }
    }

    @Override // java.util.concurrent.BlockingQueue, java.util.concurrent.PriorityBlockingQueue
    public int drainTo(Collection<? super E> collection, int i) {
        try {
            this.f5967b.lock();
            int drainTo = super.drainTo(collection, i);
            while (!this.f5966a.isEmpty() && drainTo <= i) {
                collection.add(this.f5966a.poll());
                drainTo++;
            }
            return drainTo;
        } finally {
            this.f5967b.unlock();
        }
    }

    public boolean contains(Object obj) {
        try {
            this.f5967b.lock();
            return super.contains(obj) || this.f5966a.contains(obj);
        } finally {
            this.f5967b.unlock();
        }
    }

    public void clear() {
        try {
            this.f5967b.lock();
            this.f5966a.clear();
            super.clear();
        } finally {
            this.f5967b.unlock();
        }
    }

    public boolean remove(Object obj) {
        try {
            this.f5967b.lock();
            return super.remove(obj) || this.f5966a.remove(obj);
        } finally {
            this.f5967b.unlock();
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public boolean removeAll(Collection<?> collection) {
        try {
            this.f5967b.lock();
            return this.f5966a.removeAll(collection) | super.removeAll(collection);
        } finally {
            this.f5967b.unlock();
        }
    }

    /* access modifiers changed from: package-private */
    public E a(int i, Long l, TimeUnit timeUnit) {
        switch (i) {
            case 0:
                return (E) ((b) super.take());
            case 1:
                return (E) ((b) super.peek());
            case 2:
                return (E) ((b) super.poll());
            case 3:
                return (E) ((b) super.poll(l.longValue(), timeUnit));
            default:
                return null;
        }
    }

    /* access modifiers changed from: package-private */
    public boolean a(int i, E e) {
        try {
            this.f5967b.lock();
            if (i == 1) {
                super.remove(e);
            }
            return this.f5966a.offer(e);
        } finally {
            this.f5967b.unlock();
        }
    }

    /* access modifiers changed from: package-private */
    public E b(int i, Long l, TimeUnit timeUnit) {
        E a2;
        while (true) {
            a2 = a(i, l, timeUnit);
            if (a2 == null || a(a2)) {
                return a2;
            }
            a(i, a2);
        }
        return a2;
    }

    /* access modifiers changed from: package-private */
    public boolean a(E e) {
        return e.areDependenciesMet();
    }

    public void d() {
        try {
            this.f5967b.lock();
            Iterator<E> it = this.f5966a.iterator();
            while (it.hasNext()) {
                E next = it.next();
                if (a(next)) {
                    super.offer(next);
                    it.remove();
                }
            }
        } finally {
            this.f5967b.unlock();
        }
    }

    /* access modifiers changed from: package-private */
    public <T> T[] a(T[] tArr, T[] tArr2) {
        int length = tArr.length;
        int length2 = tArr2.length;
        T[] tArr3 = (T[]) ((Object[]) Array.newInstance(tArr.getClass().getComponentType(), length + length2));
        System.arraycopy(tArr, 0, tArr3, 0, length);
        System.arraycopy(tArr2, 0, tArr3, length, length2);
        return tArr3;
    }
}
